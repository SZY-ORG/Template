package com.shizy.template.components.common.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.shizy.template.R;
import com.shizy.template.common.alioss.AliOSSUtil;
import com.shizy.template.common.alioss.UploadCallBack;
import com.shizy.template.common.constant.AppConstant;
import com.shizy.template.common.utils.ClickUtil;
import com.shizy.template.common.utils.ImageUtil;
import com.shizy.template.common.utils.LogUtil;
import com.shizy.template.common.utils.RxJavaUtil;
import com.shizy.template.common.utils.UIUtil;
import com.shizy.template.common.view.activity.BaseActivity;

import java.io.File;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.DisposableObserver;

public class PickPhotoActivity extends BaseActivity {

	private static final String FORMAT_IMG_NAME = "IMG-%s.jpg";

	private static final String EXTRA_CROP_WIDTH = "crop_width";
	private static final String EXTRA_CROP_HEIGHT = "crop_height";
	private static final String EXTRA_FOLDER = "folder";

	private static final int RC_CAPTURE = 0x1;
	private static final int RC_PICK = 0x2;
	private static final int RC_CROP = 0x3;

	private ProgressDialog mProgressDialog;

	private File mCaptureFile;
	private File mCropFile;
	private File mCompressedFile;
	private int mCropWidth;
	private int mCropHeight;
	private String mUploadFolder;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
				case RC_CAPTURE:
					handleCaptureResult(data);
					break;
				case RC_PICK:
					handlePickResult(data);
					break;
				case RC_CROP:
					handleCropResult(data);
					break;
			}
		}
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pick_photo);

		Intent intent = getIntent();
		mUploadFolder = intent.getStringExtra(EXTRA_FOLDER);
		if (TextUtils.isEmpty(mUploadFolder)) {
			mUploadFolder = AppConstant.EXT_DATA;
		}
		mCropWidth = intent.getIntExtra(EXTRA_CROP_WIDTH, 0);
		mCropHeight = intent.getIntExtra(EXTRA_CROP_HEIGHT, 0);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		releaseFiles();
	}

	private void releaseFiles() {
		if (mCaptureFile != null && mCaptureFile.exists()) {
			mCaptureFile.delete();
		}
		mCaptureFile = null;

		if (mCropFile != null && mCropFile.exists()) {
			mCropFile.delete();
		}
		mCropFile = null;

		if (mCompressedFile != null && mCompressedFile.exists()) {
			mCompressedFile.delete();
		}
		mCompressedFile = null;
	}

	@OnClick({R.id.tv_capture, R.id.tv_pick, R.id.tv_cancel})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		releaseFiles();
		switch (view.getId()) {
			case R.id.tv_capture:
				capture();
				break;
			case R.id.tv_pick:
				pick();
				break;
			case R.id.tv_cancel:
				finish();
				break;
		}
	}

	private void capture() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		mCaptureFile = createImageFile();
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCaptureFile));
		intent.putExtra("return-data", false);
		startActivityForResult(intent, RC_CAPTURE);
	}

	private void pick() {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		startActivityForResult(intent, RC_PICK);
	}

	private void cropImage(File sourceImage) {
		try {
			mCropFile = createImageFile();
			Uri imageOut = Uri.fromFile(mCropFile);
			Uri imageIn = Uri.fromFile(sourceImage);

			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(imageIn, "image/*");
			intent.putExtra("crop", true);
			intent.putExtra("scale", true);
			intent.putExtra("scaleUpIfNeeded", true);
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			intent.putExtra("outputX", mCropWidth);
			intent.putExtra("outputY", mCropHeight);
			intent.putExtra("noFaceDetection", true);
			intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageOut);

			startActivityForResult(intent, RC_CROP);
		} catch (Exception ex) {
			mCropFile = null;
			UIUtil.showToast(R.string.crop_photo_failed);
			LogUtil.e("requestCropImage error: " + sourceImage.getAbsolutePath());
		}
	}

	private void compressImage(final File imgFile) {
		showProgressDialog();
		Observable.create(new ObservableOnSubscribe<File>() {
			@Override
			public void subscribe(ObservableEmitter<File> emitter) throws Exception {
				mCompressedFile = createImageFile();
				emitter.onNext(ImageUtil.compressImage(imgFile, mCompressedFile));
				emitter.onComplete();
			}
		}).compose(RxJavaUtil.<File>mainSchedulers())
				.as(this.<File>bindLifecycle())
				.subscribe(new DisposableObserver<File>() {
					@Override
					public void onNext(File file) {
						uploadImage(file);
					}

					@Override
					public void onError(Throwable e) {
						hideProgressDialog();
						UIUtil.showToast(R.string.compress_photo_failed);
					}

					@Override
					public void onComplete() {

					}
				});
	}

	private void uploadImage(File imgFile) {
		showProgressDialog();

		UploadCallBack uploadCallBack = new UploadCallBack() {
			@Override
			public void onProgress(long bytesWritten, long totalSize) {
			}

			@Override
			public void onSuccess(String url) {
				hideProgressDialog();
				if (!TextUtils.isEmpty(url)) {
					UIUtil.showToast(R.string.upload_success);
					setResult(url);
				} else {
					onFailure("Failure: callback success but result url invalid. url=" + url);
				}
			}

			@Override
			public void onFailure(String exception) {
				hideProgressDialog();
				UIUtil.showToast(exception);
			}
		};

		if (AliOSSUtil.uploadStaticImage(imgFile.getAbsolutePath(), mUploadFolder, uploadCallBack) == null) {
			uploadCallBack.onFailure("Failure: create oss task failed");
		}
	}

	private void setResult(String url) {
		Intent data = new Intent();
		data.putExtra(AppConstant.EXT_DATA, url);
		setResult(RESULT_OK, data);
		finish();
	}

	private File createImageFile() {
		File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		String name = String.format(FORMAT_IMG_NAME, String.valueOf(System.currentTimeMillis()));
		return new File(path, name);
	}

	private boolean isNeedCrop() {
		return mCropWidth > 0 && mCropHeight > 0;
	}

	private void handleCaptureResult(Intent data) {
		if (mCaptureFile == null || !mCaptureFile.exists()) {
			UIUtil.showToast(R.string.capture_failed);
			return;
		}

		handleImage(mCaptureFile);
	}

	private void handlePickResult(Intent data) {
		Uri uri = data.getData();
		if (uri == null) {
			UIUtil.showToast(R.string.pick_photo_failed);
			return;
		}

		String imagePath = ImageUtil.uriToPath(this, uri);
		if (TextUtils.isEmpty(imagePath)) {
			UIUtil.showToast(R.string.pick_photo_failed);
			return;
		}

		handleImage(new File(imagePath));
	}

	private void handleImage(File imgFile) {
		if (isNeedCrop()) {
			cropImage(imgFile);
			return;
		}
		compressImage(imgFile);
	}

	private void handleCropResult(Intent data) {
		if (mCropFile == null || !mCropFile.exists()) {
			UIUtil.showToast(R.string.crop_photo_failed);
			return;
		}
		compressImage(mCropFile);
	}

	private void showProgressDialog() {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setCancelable(false);
			mProgressDialog.setMessage(getString(R.string.handling));
		}
		if (!mProgressDialog.isShowing()) {
			mProgressDialog.show();
		}
	}

	private void hideProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.hide();
		}
	}

	public static void launchForResult(Activity activity, int requestCode) {
		launchForResult(activity, AppConstant.UPLOAD_FOLDER_DEFAULT, 0, 0, requestCode);
	}

	public static void launchForResult(Activity activity, String folder, int cropWidth, int cropHeight, int requestCode) {
		if (activity == null) {
			return;
		}
		Intent intent = new Intent(activity, PickPhotoActivity.class);
		intent.putExtra(EXTRA_FOLDER, folder);
		intent.putExtra(EXTRA_CROP_WIDTH, cropWidth);
		intent.putExtra(EXTRA_CROP_HEIGHT, cropHeight);
		activity.startActivityForResult(intent, requestCode);
	}

}
