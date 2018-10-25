package com.shizy.template.components.taskhall;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.activity.BaseTitleActivity;

/**
 * description
 * 任务详情
 *
 * @author dahu
 * time 2018/10/25 10:29.
 */
public class TaskDetailActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_detail);
		initView();
	}

	private void initView() {
		setTitle(R.string.task_detail);
		setRightText(R.string.ok);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

}
