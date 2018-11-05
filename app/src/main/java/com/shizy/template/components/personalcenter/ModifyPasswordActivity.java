package com.shizy.template.components.personalcenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.shizy.template.R;
import com.shizy.template.common.utils.ClickUtil;
import com.shizy.template.common.utils.ToastUtil;
import com.shizy.template.common.view.activity.BaseTitleActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description 修改密码
 *
 * @author dahu
 * time 2018/10/31 11:53.
 */
public class ModifyPasswordActivity extends BaseTitleActivity {

	@BindView(R.id.old_pwd)
	protected EditText mOldPwd;
	@BindView(R.id.new_pwd)
	protected EditText mNewPwd;
	@BindView(R.id.confirm_new_pwd)
	protected EditText mConfirmNewPwd;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_password);
		initView();
	}

	private void initView() {
		setTitle(R.string.modify_password);

	}

	@OnClick({R.id.submit})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			case R.id.submit:
				String newPwd = mNewPwd.getText().toString().trim();
				String confirmPwd = mConfirmNewPwd.getText().toString().trim();
				if (TextUtils.isEmpty(newPwd)) {
					return;
				}
				if (TextUtils.isEmpty(confirmPwd)) {
					return;
				}
				if (!newPwd.equals(confirmPwd)) {
					ToastUtil.showShort("密码不一致");
					return;
				}
				ToastUtil.showShort("提交成功");
				break;
		}
	}


}
