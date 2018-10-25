package com.shizy.template.components.taskhall;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.fragment.BaseTitleFragment;

public class TaskHallFragment extends BaseTitleFragment {

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_task_hall;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setTitle(R.string.task_hall);
	}

}
