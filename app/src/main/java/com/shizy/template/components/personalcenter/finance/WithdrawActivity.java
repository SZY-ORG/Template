package com.shizy.template.components.personalcenter.finance;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.activity.BaseTitleActivity;

/**
 * description
 * 提现操作页面
 *
 * @author dahu
 * time 2018/10/25 10:19.
 */
public class WithdrawActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdraw);
		initView();
	}

	private void initView() {
		setTitle(R.string.withdraw);
		setRightText(R.string.ok);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

}
