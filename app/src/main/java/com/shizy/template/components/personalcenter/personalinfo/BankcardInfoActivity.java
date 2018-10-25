package com.shizy.template.components.personalcenter.personalinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.activity.BaseTitleActivity;

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
		setRightText(R.string.ok);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

}
