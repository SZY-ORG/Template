package com.shizy.template.components.taskhall.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shizy.template.R;
import com.shizy.template.common.view.activity.BaseTitleActivity;

import butterknife.BindView;

/**
 * 接单历史
 */
public class TaskHistoryActivity extends BaseTitleActivity {

	@BindView(R.id.recycler_view)
	protected XRecyclerView mRecyclerView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_history);

		setTitle(R.string.task_history);
	}
}
