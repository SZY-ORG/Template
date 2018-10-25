package com.shizy.template.components.personalcenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.fragment.BaseTitleFragment;

public class PersonalFragment extends BaseTitleFragment {

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_personal;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setTitle(R.string.personal_center);
	}

}
