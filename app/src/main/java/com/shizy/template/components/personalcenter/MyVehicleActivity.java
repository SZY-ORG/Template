package com.shizy.template.components.personalcenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.shizy.template.R;
import com.shizy.template.common.utils.ClickUtil;
import com.shizy.template.common.utils.ToastUtil;
import com.shizy.template.common.view.activity.BaseTitleActivity;
import com.shizy.template.components.personalcenter.util.InputLower2UpperCase;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description 我的车辆
 *
 * @author dahu
 * time 2018/10/31 11:38.
 */
public class MyVehicleActivity extends BaseTitleActivity {

	@BindView(R.id.vehicle_number)
	protected EditText etVehicleNumber;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_vehicle);
		initView();
	}

	private void initView() {
		setTitle(R.string.my_vehicle);
		etVehicleNumber.setTransformationMethod(new InputLower2UpperCase());
	}

	@OnClick({R.id.confirm_to_submit, R.id.vehicle_type_layout})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			case R.id.confirm_to_submit:
				ToastUtil.showShort("提交成功");
				break;
			case R.id.vehicle_type_layout:
				ToastUtil.showShort("选择车型");
				break;
		}
	}


}
