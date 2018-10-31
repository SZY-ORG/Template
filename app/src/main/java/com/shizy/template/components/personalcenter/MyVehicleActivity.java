package com.shizy.template.components.personalcenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.activity.BaseTitleActivity;

/**
 * description 我的车辆
 *
 * @author dahu
 * time 2018/10/31 11:38.
 */
public class MyVehicleActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_vehicle);
		initView();
	}

	private void initView() {
		setTitle(R.string.my_vehicle);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}
}
