package com.shizy.template.components.taskhall.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizy.template.R;
import com.shizy.template.common.view.adapter.recyclerview.BaseAdapter;
import com.shizy.template.common.view.adapter.recyclerview.BaseViewHolder;
import com.shizy.template.components.taskhall.bean.Task;

import butterknife.BindView;

public class TaskHallAdapter extends BaseAdapter<Task, TaskHallAdapter.TaskViewHolder> {

	public TaskHallAdapter(Context context) {
		super(context);
	}

	@NonNull
	@Override
	public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_hall, parent, false);
		return new TaskViewHolder(this, view);
	}

	class TaskViewHolder extends BaseViewHolder<Task> {

		public TaskViewHolder(BaseAdapter adapter, View itemView) {
			super(adapter, itemView);
		}

		@Override
		public void bindData(Task data) {

		}
	}

}
