package com.shizy.template.components.personalcenter.finance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shizy.template.R;
import com.shizy.template.common.view.adapter.recyclerview.BaseAdapter;
import com.shizy.template.common.view.adapter.recyclerview.BaseViewHolder;
import com.shizy.template.components.personalcenter.finance.bean.CapitalFlow;

/**
 * description 业务明细列表adapter
 *
 * @author dahu
 * time 2018/10/31 14:23.
 */
public class CapitalFlowAdapter extends BaseAdapter<CapitalFlow, CapitalFlowAdapter.CapitalFlowViewHolder> {

	public CapitalFlowAdapter(Context context) {
		super(context);
	}

	@NonNull
	@Override
	public CapitalFlowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_capital_flow, parent, false);
		return new CapitalFlowViewHolder(this, view);
	}

	class CapitalFlowViewHolder extends BaseViewHolder<CapitalFlow> {

		public CapitalFlowViewHolder(BaseAdapter adapter, View itemView) {
			super(adapter, itemView);
		}

		@Override
		public void bindData(CapitalFlow data) {

		}
	}

}
