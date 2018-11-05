package com.shizy.template.components.personalcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shizy.template.R;
import com.shizy.template.common.utils.ClickUtil;
import com.shizy.template.common.utils.ToastUtil;
import com.shizy.template.common.view.fragment.BaseTitleFragment;
import com.shizy.template.components.account.ui.PerfectInfoActivity;

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

	@OnClick({R.id.img_avatar, R.id.finance_info, R.id.modify_password,
			R.id.my_vehicle, R.id.authentication})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			// 头像
			case R.id.img_avatar:
				ToastUtil.showShort("头像");
				break;
			// 钱包
			case R.id.finance_info:
				startActivity(new Intent(getActivity(), FinanceInfoActivity.class));
				break;
			// 我的车辆
			case R.id.my_vehicle:
				startActivity(new Intent(getActivity(), MyVehicleActivity.class));
				break;
			// 修改密码
			case R.id.modify_password:
				startActivity(new Intent(getActivity(), ModifyPasswordActivity.class));
				break;
			// 完善资料
			case R.id.authentication:
				startActivity(new Intent(getActivity(), PerfectInfoActivity.class));
				break;
		}
	}


}
