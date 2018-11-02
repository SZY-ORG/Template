package com.shizy.template.components.personalcenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.activity.BaseTitleActivity;

/**
 * description 资料审核页面
 *
 * @author dahu
 * time 2018/11/2 11:52.
 */
public class AuthenticationActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_authentication);
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
