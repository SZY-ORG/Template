package com.shizy.template.common.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shizy.template.common.utils.RxJavaUtil;
import com.uber.autodispose.AutoDisposeConverter;

import butterknife.ButterKnife;

/**
 * Created by shizy on 2018/10/22.
 */

public abstract class BaseFragment extends Fragment {

	protected <T> AutoDisposeConverter<T> bindLifecycle() {
		return RxJavaUtil.bindLifecycle(this);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (getLayoutId() > 0) {
			View root = inflater.inflate(getLayoutId(), container, false);
			ButterKnife.bind(this, root);
			return root;
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	protected abstract int getLayoutId();

}
