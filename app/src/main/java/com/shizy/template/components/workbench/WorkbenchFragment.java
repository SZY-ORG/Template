package com.shizy.template.components.workbench;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.fragment.BaseTitleFragment;

/**
 * description
 * 首页——每日出车
 * @author dahu
 * time 2018/10/26 16:25.
 */
public class WorkbenchFragment extends BaseTitleFragment {

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_workbench;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setTitle(R.string.workbench);
	}

}
