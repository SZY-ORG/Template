package com.shizy.template.components.main.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import com.shizy.template.R;
import com.shizy.template.common.utils.LogUtil;
import com.shizy.template.common.utils.NetUtil;
import com.shizy.template.common.utils.PermissionUtil;
import com.shizy.template.common.view.activity.BaseActivity;
import com.shizy.template.net.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

public class LauncherActivity extends BaseActivity {

	private static final int RC_PERMISSION = 1;

	private Runnable mToMain = new Runnable() {
		@Override
		public void run() {
			startActivity(new Intent(LauncherActivity.this, MainActivity.class));
			finish();
		}
	};

	private Handler mHandler = new Handler();

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		for (int i = 0; i < permissions.length; i++) {
			LogUtil.d("---------");
			LogUtil.d("---------" + permissions[i] + ":" + grantResults[i]);
			LogUtil.d("---------");
		}
		for (int result : grantResults) {
			if (result != PackageManager.PERMISSION_GRANTED) {
				showPermissionDeniedDialog();
				return;
			}
		}
		allPermissionGranted();
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);

		List<String> permissions = PermissionUtil.getPermissions();
		List<String> denied = new ArrayList<>();
		for (String permission : permissions) {
			if (!PermissionUtil.isGranted(permission)) {
				denied.add(permission);
			}
		}

		if (denied.size() > 0) {
			String[] deniedPermissions = new String[denied.size()];
			denied.toArray(deniedPermissions);
			ActivityCompat.requestPermissions(this, deniedPermissions, RC_PERMISSION);
		} else {
			allPermissionGranted();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mHandler.removeCallbacks(mToMain);
	}

	private void allPermissionGranted() {
		RetrofitHelper.getInstance().addHeaders(NetUtil.getHeaders());

		mHandler.postDelayed(mToMain, 2000);
	}

	private void showPermissionDeniedDialog() {
		new AlertDialog.Builder(this)
				.setMessage(R.string.permission_denied)
				.setCancelable(false)
				.setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						finish();
					}
				})
				.create()
				.show();
	}

}
