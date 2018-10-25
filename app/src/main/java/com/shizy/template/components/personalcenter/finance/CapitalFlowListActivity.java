package com.shizy.template.components.personalcenter.finance;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.utils.AppUtil;
import com.shizy.template.common.view.activity.BaseTitleActivity;

/**
 * description
 * 流水列表页面
 *
 * @author dahu
 * time 2018/10/25 10:24.
 */
public class CapitalFlowListActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capital_flow);
		initView();
	}

	private void initView() {
		setTitle(AppUtil.getVersionName());
		setRightText(R.string.ok);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

}
