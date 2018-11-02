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
 *
 * @author dahu
 * time 2018/10/31 14:43.
 */
public class BusinessDetailActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capital_flow_detail);
		initView();
	}

	private void initView() {
		setTitle(R.string.business_list);

	}

	@OnClick({R.id.have_question})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			// 对交易有疑问
			case R.id.have_question:
				ToastUtil.showShort("对交易有疑问");
				break;
		}
	}

}
