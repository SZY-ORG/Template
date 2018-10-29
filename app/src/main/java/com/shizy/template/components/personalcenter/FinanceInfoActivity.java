package com.shizy.template.components.personalcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shizy.template.R;
import com.shizy.template.common.utils.ClickUtil;
import com.shizy.template.common.view.activity.BaseTitleActivity;
import com.shizy.template.components.personalcenter.finance.CapitalFlowListActivity;
import com.shizy.template.components.personalcenter.finance.WithdrawActivity;
import com.shizy.template.components.personalcenter.finance.WithdrawHistoryListActivity;

import butterknife.OnClick;

/**
 * description
 * 财务信息
 *
 * @author dahu
 * time 2018/10/25 15:01.
 */
public class FinanceInfoActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finance_info);
		initView();
	}

	private void initView() {
		setTitle(R.string.finance_info);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

	@OnClick({R.id.capital_flow, R.id.withdraw, R.id.withdraw_history_list})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			// 流水列表页面
			case R.id.capital_flow:
				startActivity(new Intent(FinanceInfoActivity.this, CapitalFlowListActivity.class));
				break;
			// 提现
			case R.id.withdraw:
				startActivity(new Intent(FinanceInfoActivity.this, WithdrawActivity.class));
				break;
			// 提现历史
			case R.id.withdraw_history_list:
				startActivity(new Intent(FinanceInfoActivity.this, WithdrawHistoryListActivity.class));
				break;
		}
	}

}
