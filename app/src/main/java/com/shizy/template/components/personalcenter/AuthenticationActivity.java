package com.shizy.template.components.personalcenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.shizy.template.R;
import com.shizy.template.common.utils.ClickUtil;
import com.shizy.template.common.utils.ToastUtil;
import com.shizy.template.common.view.activity.BaseTitleActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description 资料审核页面
 *
 * @author dahu
 * time 2018/11/2 11:52.
 */
public class AuthenticationActivity extends BaseTitleActivity {

	@BindView(R.id.layout_avatar)
	protected RelativeLayout layout_avatar;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_authentication);
		initView();
	}

	private void initView() {
		setTitle(R.string.my_information);

	}

	@OnClick({R.id.layout_avatar, R.id.id_card_number_validity_date, R.id.driver_license_number_layout,
			R.id.permit_vehicle_type_layout})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			case R.id.layout_avatar:
				ToastUtil.showShort("头像");
				break;
			case R.id.id_card_number_validity_date:
				ToastUtil.showShort("身份证有效期");
				break;
			case R.id.driver_license_number_layout:
				ToastUtil.showShort("驾驶证有效期");
				break;
			case R.id.permit_vehicle_type_layout:
				ToastUtil.showShort("驾驶证有效期");
				break;
		}
	}

}
