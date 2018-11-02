package com.shizy.template.components.personalcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shizy.template.R;
import com.shizy.template.common.utils.ClickUtil;
import com.shizy.template.common.view.activity.BaseTitleActivity;
import com.shizy.template.components.personalcenter.finance.BankcardInfoActivity;
import com.shizy.template.components.personalcenter.finance.BusinessListActivity;
import com.shizy.template.components.personalcenter.finance.OutAccountMoneyActivity;
import com.shizy.template.components.personalcenter.finance.WithdrawActivity;

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

	@OnClick({R.id.business_list, R.id.withdraw, R.id.bankcard, R.id.deposit, R.id.frozen_money})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			// 未入账金额
			case R.id.frozen_money:
				startActivity(new Intent(FinanceInfoActivity.this, OutAccountMoneyActivity.class));
				break;
			// 提现
			case R.id.withdraw:
				startActivity(new Intent(FinanceInfoActivity.this, WithdrawActivity.class));
				break;
			// 银行卡
			case R.id.bankcard:
				startActivity(new Intent(FinanceInfoActivity.this, BankcardInfoActivity.class));
				break;
			// 出车明细页面
			case R.id.business_list:
				startActivity(new Intent(FinanceInfoActivity.this, BusinessListActivity.class));
				break;
			// 押金
			case R.id.deposit:
				startActivity(new Intent(FinanceInfoActivity.this, DepositActivity.class));
				break;
		}
	}

}
