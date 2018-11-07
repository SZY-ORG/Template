package com.shizy.template.components.workbench.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shizy.template.R;
import com.shizy.template.common.view.adapter.recyclerview.BaseAdapter;
import com.shizy.template.common.view.adapter.recyclerview.BaseViewHolder;
import com.shizy.template.components.workbench.bean.Work;

/**
 * description
 *
 * @author dahu
 * time 2018/11/5 11:47.
 */
public class WorkListAdapter extends BaseAdapter<Work, WorkListAdapter.ArrangementHistoryViewHolder> {

	public WorkListAdapter(Context context) {
		super(context);
	}

	@NonNull
	@Override
	public WorkListAdapter.ArrangementHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_arrangement_history, parent, false);
		return new WorkListAdapter.ArrangementHistoryViewHolder(this, view);
	}

	class ArrangementHistoryViewHolder extends BaseViewHolder<Work> {

		public ArrangementHistoryViewHolder(BaseAdapter adapter, View itemView) {
			super(adapter, itemView);
		}

		@Override
		public void bindData(Work data) {

		}
	}

}