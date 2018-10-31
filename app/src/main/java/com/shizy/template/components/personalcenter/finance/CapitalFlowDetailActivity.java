package com.shizy.template.components.personalcenter.finance;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.activity.BaseTitleActivity;

/**
 * description
 *
 * @author dahu
 * time 2018/10/31 14:43.
 */
public class CapitalFlowDetailActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capital_flow_detail);
		initView();
	}

	private void initView() {
		setTitle(R.string.business_list);

	}
}
