package com.shizy.template.components.workbench;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.activity.BaseTitleActivity;

/**
 * description
 * 回单信息页面
 *
 * @author dahu
 * time 2018/10/25 10:45.
 */
public class ReceiptInfoActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receipt_info);
		initView();
	}

	private void initView() {
		setTitle(R.string.receipt_info);
		setRightText(R.string.ok);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

}
