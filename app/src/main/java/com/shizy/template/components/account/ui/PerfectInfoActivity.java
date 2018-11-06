package com.shizy.template.components.account.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.BasePickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shizy.template.R;
import com.shizy.template.common.constant.AppConstant;
import com.shizy.template.common.utils.ClickUtil;
import com.shizy.template.common.utils.DateUtil;
import com.shizy.template.common.utils.ImageLoader;
import com.shizy.template.common.utils.UIUtil;
import com.shizy.template.common.view.activity.BaseTitleActivity;
import com.shizy.template.components.common.ui.PickPhotoActivity;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class PerfectInfoActivity extends BaseTitleActivity {

	private static final int RC_AVATAR = 0x1;
	private static final int RC_ID_FRONT = 0x2;
	private static final int RC_ID_BACK = 0x3;
	private static final int RC_ID_IN_HAND = 0x4;
	private static final int RC_DRIVER_LICENSE = 0x5;

	private View.OnClickListener mDatePickerClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			if (!ClickUtil.isValid()) {
				return;
			}
			if (mTimePickerView == null) {
				return;
			}
			switch (view.getId()) {
				case R.id.tv_ok:
					mTimePickerView.returnData();
					break;
				case R.id.tv_cancel:
					break;
				case R.id.tv_forever:
					((TextView) findViewById((Integer) view.getTag())).setText(R.string.long_term);
					break;
			}
			mTimePickerView.dismiss();
			mTimePickerView = null;
		}
	};

	@BindView(R.id.iv_avatar)
	protected ImageView mAvatarIv;
	@BindView(R.id.edit_id_number)
	protected EditText mIdNumberEdit;
	@BindView(R.id.tv_id_period)
	protected TextView mIdPeriodTv;
	@BindView(R.id.edit_driver_license_number)
	protected EditText mDriverLicenseNumberEdit;
	@BindView(R.id.tv_driver_license_period)
	protected TextView mDriverLicensePeriodTv;
	@BindView(R.id.tv_vehicle_permitted)
	protected TextView mVehiclePermittedTv;
	@BindView(R.id.iv_id_card_front)
	protected ImageView mIdCardFrontIv;
	@BindView(R.id.iv_id_card_back)
	protected ImageView mIdCardBackIv;
	@BindView(R.id.iv_id_card_in_hand)
	protected ImageView mIdCardInHandIv;
	@BindView(R.id.iv_driver_license_photo)
	protected ImageView mDriverLicensePhotoIv;
	@BindView(R.id.btn_submit)
	protected Button mSubmitBtn;

	private SparseArray<String> mUrls = new SparseArray<>();

	private TimePickerView mTimePickerView;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			String url = data.getStringExtra(AppConstant.EXT_DATA);
			mUrls.put(requestCode, url);
			switch (requestCode) {
				case RC_AVATAR:
					setAvatarUrl(url);
					break;
				case RC_ID_FRONT:
					setImageUrl(mIdCardFrontIv, url);
					break;
				case RC_ID_BACK:
					setImageUrl(mIdCardBackIv, url);
					break;
				case RC_ID_IN_HAND:
					setImageUrl(mIdCardInHandIv, url);
					break;
				case RC_DRIVER_LICENSE:
					setImageUrl(mDriverLicensePhotoIv, url);
					break;
			}
		}
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfect_info);

		setTitle(R.string.driver_join);
		setRightText(R.string.skip);
	}

	@Override
	protected void onClickTitleRight() {
		super.onClickTitleRight();
		finish();
	}

	@OnClick({R.id.iv_avatar, R.id.tv_id_period, R.id.tv_driver_license_period, R.id.tv_vehicle_permitted,
			R.id.iv_id_card_front, R.id.iv_id_card_back, R.id.iv_id_card_in_hand, R.id.iv_driver_license_photo,
			R.id.btn_submit})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		UIUtil.hideSoftInput(getCurrentFocus());

		switch (view.getId()) {
			case R.id.iv_avatar:
				pickPhoto(RC_AVATAR);
				break;
			case R.id.tv_id_period:
			case R.id.tv_driver_license_period:
				showPeriodDialog(view.getId());
				break;
			case R.id.tv_vehicle_permitted:
				break;
			case R.id.iv_id_card_front:
				pickPhoto(RC_ID_FRONT);
				break;
			case R.id.iv_id_card_back:
				pickPhoto(RC_ID_BACK);
				break;
			case R.id.iv_id_card_in_hand:
				pickPhoto(RC_ID_IN_HAND);
				break;
			case R.id.iv_driver_license_photo:
				pickPhoto(RC_DRIVER_LICENSE);
				break;
			case R.id.btn_submit:
				attemptSubmit();
				break;
		}
	}

	private void pickPhoto(int code) {
		int cropSize = 0;
		String uploadFolder = AppConstant.UPLOAD_FOLDER_CERTIFICATION;
		if (code == RC_AVATAR) {
			cropSize = AppConstant.AVATAR_SIZE;
			uploadFolder = AppConstant.UPLOAD_FOLDER_AVATAR;
		}
		PickPhotoActivity.launchForResult(this, uploadFolder, cropSize, cropSize, code);
	}

	private void showPeriodDialog(final int viewId) {
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.YEAR, 20);// 最多20年

		mTimePickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				TextView tv = findViewById(viewId);
				tv.setText(DateUtil.YYYY_MM_DD.format(date));
			}
		}).setLayoutRes(R.layout.dialog_date_picker, new CustomListener() {
			@Override
			public void customLayout(View v) {
				TextView cancelTv = v.findViewById(R.id.tv_cancel);
				TextView okTv = v.findViewById(R.id.tv_ok);
				TextView titleTv = v.findViewById(R.id.tv_title);
				TextView foreverTv = v.findViewById(R.id.tv_forever);

				titleTv.setText(viewId == R.id.tv_id_period ? R.string.id_period : R.string.driver_license_period);
				cancelTv.setOnClickListener(mDatePickerClickListener);
				okTv.setOnClickListener(mDatePickerClickListener);
				foreverTv.setOnClickListener(mDatePickerClickListener);
				foreverTv.setTag(viewId);
			}
		})
				.isDialog(true)
				.setRangDate(startDate, endDate)
				.setType(new boolean[]{true, true, true, false, false, false})
				.isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
				.build();

		setPickerDialogParams(mTimePickerView);

		mTimePickerView.show();
	}

	private void setPickerDialogParams(BasePickerView pickerView) {
		Dialog dialog = pickerView.getDialog();
		if (dialog != null) {
			FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT,
					Gravity.BOTTOM);
			params.leftMargin = 0;
			params.rightMargin = 0;
			pickerView.getDialogContainerLayout().setLayoutParams(params);

			Window window = dialog.getWindow();
			if (window != null) {
				window.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
				window.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
			}
		}
	}

	private void setAvatarUrl(String url) {
		ImageLoader.Option option = ImageLoader.Option.create();
		option.setCircleCrop(true);
		ImageLoader.load(this, url, mAvatarIv, option);
	}

	private void setImageUrl(ImageView imageView, String url) {
		ImageLoader.load(this, url, imageView);
	}

	private void attemptSubmit() {

	}

}
