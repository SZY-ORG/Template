package com.shizy.template.components.personalcenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.activity.BaseTitleActivity;

/**
 * description 修改密码
 *
 * @author dahu
 * time 2018/10/31 11:53.
 */
public class ModifyPasswordActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_password);
		initView();
	}

	private void initView() {
		setTitle(R.string.modify_password);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

}
