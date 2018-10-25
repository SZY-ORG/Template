package com.shizy.template.components.workbench;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.activity.BaseTitleActivity;

/**
 * description
 * 上传回单页面
 *
 * @author dahu
 * time 2018/10/25 10:44.
 */
public class UploadReceiptActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_receipt);
		initView();
	}

	private void initView() {
		setTitle(R.string.upload_receipt);
		setRightText(R.string.ok);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

}
