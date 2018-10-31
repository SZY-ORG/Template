package com.shizy.template.components.personalcenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.activity.BaseTitleActivity;

/**
 * description
 * 设置
 *
 * @author dahu
 * time 2018/10/31 10:47.
 */
public class SettingActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initView();
	}

	private void initView() {
		setTitle(R.string.setting);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

}
