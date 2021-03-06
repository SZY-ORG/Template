package com.shizy.template.common.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;

import com.shizy.template.R;

/**
 * Created by shizy on 2018/10/22.
 */

public class CustomProgressDialog extends ProgressDialog {

	public CustomProgressDialog(Context context) {
		this(context, R.style.CustomProgressDialog);
	}

	public CustomProgressDialog(Context context, int theme) {
		super(context, theme);
		getWindow().setGravity(Gravity.CENTER);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_dialog_custom);
	}
}
