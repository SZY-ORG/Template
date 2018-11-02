package com.shizy.template.components.account.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shizy.template.R;
import com.shizy.template.common.bean.City;
import com.shizy.template.common.utils.ClickUtil;
import com.shizy.template.common.utils.RxJavaUtil;
import com.shizy.template.common.utils.UIUtil;
import com.shizy.template.common.view.activity.BaseTitleActivity;
import com.shizy.template.components.account.api.IAccountService;
import com.shizy.template.components.account.bean.User;
import com.shizy.template.components.common.ui.list.SelectListActivity;
import com.shizy.template.components.common.ui.list.SelectListDataFactory;
import com.shizy.template.net.ResponseException;
import com.shizy.template.net.ServiceFactory;
import com.shizy.template.net.progress.ProgressDialogObserver;
import com.shizy.template.net.response.ResponseData;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册页面
 */
public class SignUpActivity extends BaseTitleActivity {

	@BindView(R.id.name)
	protected EditText mNameEdit;
	@BindView(R.id.mobile)
	protected EditText mMobileEdit;
	@BindView(R.id.password)
	protected EditText mPasswordEdit;
	@BindView(R.id.repeat_password)
	protected EditText mRepeatPasswordEdit;
	@BindView(R.id.tv_city)
	protected TextView mCityTv;

	private boolean isRequesting = false;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			City city = (City) data.getSerializableExtra(SelectListDataFactory.Type.OPEN_CITY);
			mCityTv.setText(city.getName());
		}
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		setTitle(R.string.sign_up);
	}

	@OnClick({R.id.layout_city, R.id.btn_sign_up})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			case R.id.layout_city:
				chooseCity();
				break;
			case R.id.btn_sign_up:
				attemptSignUp();
				break;
		}
	}

	private void chooseCity() {
		SelectListActivity.Option option = new SelectListActivity.Option();
		option.setTitle(getString(R.string.residential_city));
		option.setType(SelectListDataFactory.Type.OPEN_CITY);
		SelectListActivity.launchForResult(this, option, 0);
	}

	private void attemptSignUp() {
		if (isRequesting) {
			return;
		}

		mNameEdit.setError(null);
		mMobileEdit.setError(null);
		mPasswordEdit.setError(null);
		mRepeatPasswordEdit.setError(null);

		String name = mNameEdit.getText().toString();
		String mobile = mMobileEdit.getText().toString();
		String password = mPasswordEdit.getText().toString();
		String repeatPassword = mRepeatPasswordEdit.getText().toString();

		if (TextUtils.isEmpty(name)) {
			mNameEdit.setError(getString(R.string.error_name_empty));
			mNameEdit.requestFocus();
			return;
		}

		if (TextUtils.isEmpty(mobile)) {
			mMobileEdit.setError(getString(R.string.error_mobile_empty));
			mMobileEdit.requestFocus();
			return;
		}
		if (!isMobileValid(mobile)) {
			mMobileEdit.setError(getString(R.string.error_mobile_format));
			mMobileEdit.requestFocus();
			return;
		}

		if (TextUtils.isEmpty(password)) {
			mPasswordEdit.setError(getString(R.string.error_password_empty));
			mPasswordEdit.requestFocus();
			return;
		}
		if (!isPasswordValid(password)) {
			mPasswordEdit.setError(getString(R.string.error_password_invalid));
			mPasswordEdit.requestFocus();
			return;
		}

		if (TextUtils.equals(password, repeatPassword)) {
			mPasswordEdit.setError(getString(R.string.error_repeat_password));
			mPasswordEdit.requestFocus();
			return;
		}

		UIUtil.hideSoftInput(getCurrentFocus());
		signUp(name, mobile, password);
	}

	private void goBack() {
		setResult(RESULT_OK);
		finish();
	}

	private boolean isMobileValid(String mobile) {
		//TODO: 添加规则
		return mobile.trim().length() == 11;
	}

	private boolean isPasswordValid(String password) {
		//TODO: 添加规则
		return password.trim().length() >= 6;
	}

	private void signUp(String name, String mobile, String password) {
		isRequesting = true;

		ServiceFactory.getService(IAccountService.class)
				.signUp(name, mobile, password)
				.compose(RxJavaUtil.<ResponseData<User>>mainSchedulers())
				.as(this.<ResponseData<User>>bindLifecycle())
				.subscribe(new ProgressDialogObserver<User>(this) {
					@Override
					protected void onSuccess(ResponseData<User> responseData) {
						UIUtil.showToast(responseData.getMsg());
						goBack();
					}

					@Override
					protected void onFailure(ResponseException e) {
						UIUtil.showToast(e.getMsg());
					}

					@Override
					protected void onFinally() {
						super.onFinally();
						isRequesting = false;
					}
				});
	}

}
