package com.shizy.template.components.account.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.shizy.template.R;
import com.shizy.template.common.utils.ClickUtil;
import com.shizy.template.common.utils.RxJavaUtil;
import com.shizy.template.common.utils.UIUtil;
import com.shizy.template.common.view.activity.BaseActivity;
import com.shizy.template.components.account.api.IAccountService;
import com.shizy.template.components.account.bean.User;
import com.shizy.template.components.main.ui.MainActivity;
import com.shizy.template.net.ResponseException;
import com.shizy.template.net.ServiceFactory;
import com.shizy.template.net.progress.ProgressDialogObserver;
import com.shizy.template.net.response.ResponseData;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity {

	private static final int RC_SIGN_UP = 0x1;

	@BindView(R.id.mobile)
	protected EditText mMobileEdit;
	@BindView(R.id.password)
	protected EditText mPasswordEdit;
	@BindView(R.id.login_form)
	protected View mLoginFormView;

	private boolean isRequesting = false;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			toMain();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@OnEditorAction(R.id.password)
	protected boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
		if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
			attemptLogin();
			return true;
		}
		return false;
	}

	@OnClick({R.id.btn_login, R.id.tv_sign_up})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			case R.id.btn_login:
				attemptLogin();
				break;
			case R.id.tv_sign_up:
				toSignUp();
				break;
		}
	}

	private void attemptLogin() {
		if (isRequesting) {
			return;
		}

		mMobileEdit.setError(null);
		mPasswordEdit.setError(null);

		String mobile = mMobileEdit.getText().toString();
		String password = mPasswordEdit.getText().toString();

		boolean cancel = false;
		View focusView = null;

		if (TextUtils.isEmpty(mobile)) {
			mMobileEdit.setError(getString(R.string.error_mobile_empty));
			focusView = mMobileEdit;
			cancel = true;
		} else if (!isMobileValid(mobile)) {
			mMobileEdit.setError(getString(R.string.error_mobile_format));
			focusView = mMobileEdit;
			cancel = true;
		} else if (TextUtils.isEmpty(password)) {
			mPasswordEdit.setError(getString(R.string.error_password_empty));
			focusView = mPasswordEdit;
			cancel = true;
		} else if (!isPasswordValid(password)) {
			mPasswordEdit.setError(getString(R.string.error_password_invalid));
			focusView = mPasswordEdit;
			cancel = true;
		}

		if (cancel) {
			focusView.requestFocus();
		} else {
			UIUtil.hideSoftInput(getCurrentFocus());
//			login(mobile, password);
			toMain();
		}
	}

	private boolean isMobileValid(String mobile) {
		//TODO: 添加规则
		return mobile.trim().length() == 11;
	}

	private boolean isPasswordValid(String password) {
		//TODO: 添加规则
		return password.trim().length() >= 6;
	}

	private void toMain() {
		startActivity(new Intent(LoginActivity.this, MainActivity.class));
		finish();
	}

	private void toSignUp() {
		startActivityForResult(new Intent(LoginActivity.this, SignUpActivity.class), RC_SIGN_UP);
	}

	private void login(String mobile, String password) {
		isRequesting = true;

		ServiceFactory.getService(IAccountService.class)
				.login(mobile, password)
				.compose(RxJavaUtil.<ResponseData<User>>mainSchedulers())
				.as(this.<ResponseData<User>>bindLifecycle())
				.subscribe(new ProgressDialogObserver<User>(this) {
					@Override
					protected void onSuccess(ResponseData<User> responseData) {
						toMain();
					}

					@Override
					protected void onFailure(ResponseException e) {
						mPasswordEdit.setError(e.getMsg());
						mPasswordEdit.requestFocus();
					}

					@Override
					protected void onFinally() {
						super.onFinally();
						isRequesting = false;
					}
				});
	}
}

