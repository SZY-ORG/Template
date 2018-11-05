package com.shizy.template.components.personalcenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.activity.BaseTitleActivity;

/**
 * description
 * 个人信息列表页
 *
 * @author dahu
 * @deprecated
 * time 2018/10/25 10:16.
 */
public class PersonalInfoActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		initView();
	}

	private void initView() {
		setTitle(R.string.personal_info);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

}
