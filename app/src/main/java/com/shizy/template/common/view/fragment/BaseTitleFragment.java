package com.shizy.template.common.view.fragment;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shizy.template.R;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class BaseTitleFragment extends BaseFragment {

	@BindView(R.id.tv_title)
	protected TextView mTitleTv;
	@BindView(R.id.tv_title_left)
	protected TextView mTitleLeftTv;
	@BindView(R.id.iv_title_left)
	protected ImageView mTitleLeftIv;
	@BindView(R.id.tv_title_right)
	protected TextView mTitleRightTv;

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setLeftImage(0);
	}

	@OnClick({R.id.tv_title_left, R.id.iv_title_left})
	protected void onClickTitleLeft() {

	}

	@OnClick(R.id.tv_title_right)
	protected void onClickTitleRight() {
	}

	public void setTitle(CharSequence title) {
		if (mTitleTv != null) {
			mTitleTv.setText(title);
		}
	}

	public void setTitle(@StringRes int titleId) {
		if (mTitleTv != null) {
			mTitleTv.setText(titleId);
		}
	}

	public void setLeftImage(@DrawableRes int resId) {
		if (mTitleLeftIv != null) {
			mTitleLeftIv.setImageResource(resId);
			mTitleLeftIv.setVisibility(View.VISIBLE);
			mTitleLeftTv.setVisibility(View.GONE);
		}
	}

	public void setLeftText(CharSequence text) {
		if (mTitleLeftTv != null) {
			mTitleLeftTv.setText(text);
			mTitleLeftTv.setVisibility(View.VISIBLE);
			mTitleLeftIv.setVisibility(View.GONE);
		}
	}

	public void setLeftText(@StringRes int resId) {
		setLeftText(getString(resId));
	}

	public void setRightText(CharSequence text) {
		if (mTitleRightTv != null) {
			mTitleRightTv.setText(text);
			mTitleRightTv.setVisibility(View.VISIBLE);
		}
	}

	public void setRightText(@StringRes int resId) {
		setRightText(getString(resId));
	}

}
