package com.shizy.template.components.personalcenter.finance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shizy.template.R;
import com.shizy.template.common.utils.ClickUtil;
import com.shizy.template.common.utils.ToastUtil;
import com.shizy.template.common.view.activity.BaseTitleActivity;

import butterknife.OnClick;

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
	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

	@OnClick({R.id.btn_withdraw, R.id.tv_withdraw_protocol})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			// 确认提现
			case R.id.btn_withdraw:
				ToastUtil.showShort("提现成功");
				break;
			// 提现协议
			case R.id.tv_withdraw_protocol:
				ToastUtil.showShort("提现协议");
				break;
		}
	}

}
