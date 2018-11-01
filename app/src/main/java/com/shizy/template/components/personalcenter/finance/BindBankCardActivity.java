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
 * description 绑定银行卡
 *
 * @author dahu
 * time 2018/11/1 14:16.
 */
public class BindBankCardActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bind_bankcard);
		initView();
	}

	private void initView() {
		setTitle(R.string.bind_bankcard);
	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

	@OnClick({R.id.save})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			// 保存
			case R.id.save:
				ToastUtil.showShort("保存");
				break;
		}
	}

}
