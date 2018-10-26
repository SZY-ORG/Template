package com.shizy.template.components.personalcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shizy.template.R;
import com.shizy.template.common.utils.ClickUtil;
import com.shizy.template.common.view.fragment.BaseTitleFragment;

import butterknife.OnClick;

public class PersonalFragment extends BaseTitleFragment {

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_personal;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setTitle(R.string.personal_center);

	}

	@OnClick({R.id.personal_info, R.id.finance_info, R.id.deposit, R.id.about_us})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			// 个人信息列表
			case R.id.personal_info:
				startActivity(new Intent(getActivity(), PersonalInfoActivity.class));
				break;
			// 财务信息
			case R.id.finance_info:
				startActivity(new Intent(getActivity(), FinanceInfoActivity.class));
				break;
			// 押金
			case R.id.deposit:
				startActivity(new Intent(getActivity(), DepositActivity.class));
				break;
			// 关于
			case R.id.about_us:
				startActivity(new Intent(getActivity(), AboutUsActivity.class));
				break;
		}
	}


}
