package com.shizy.template.components.personalcenter.finance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shizy.template.R;
import com.shizy.template.common.utils.ClickUtil;
import com.shizy.template.common.view.activity.BaseTitleActivity;

import butterknife.OnClick;

/**
 * description
 * 银行卡
 *
 * @author dahu
 * time 2018/10/25 10:17.
 */
public class BankcardInfoActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bankcard_info);
		initView();
	}

	private void initView() {
		setTitle(R.string.bankcard);
		setRightText(R.string.modify);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

	@OnClick({R.id.empty_view})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			// 添加银行卡
			case R.id.empty_view:
				startActivity(new Intent(BankcardInfoActivity.this, BindBankCardActivity.class));
				break;
		}
	}

}
