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
import com.shizy.template.components.personalcenter.personalinfo.BankcardInfoActivity;

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

	@OnClick({R.id.business_list, R.id.withdraw, R.id.bankcard})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			// 流水列表页面
			case R.id.business_list:
				startActivity(new Intent(FinanceInfoActivity.this, CapitalFlowListActivity.class));
				break;
			// 提现
			case R.id.withdraw:
				startActivity(new Intent(FinanceInfoActivity.this, WithdrawActivity.class));
				break;
			// 银行卡
			case R.id.bankcard:
				startActivity(new Intent(FinanceInfoActivity.this, BankcardInfoActivity.class));
				break;
		}
	}

}
