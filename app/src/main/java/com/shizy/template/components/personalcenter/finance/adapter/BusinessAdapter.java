package com.shizy.template.components.personalcenter.finance.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shizy.template.R;
import com.shizy.template.common.view.adapter.recyclerview.BaseAdapter;
import com.shizy.template.common.view.adapter.recyclerview.BaseViewHolder;
import com.shizy.template.components.personalcenter.finance.bean.BusinessList;

/**
 * description 业务明细列表adapter
 *
 * @author dahu
 * time 2018/10/31 14:23.
 */
public class BusinessAdapter extends BaseAdapter<BusinessList, BusinessAdapter.CapitalFlowViewHolder> {

	public BusinessAdapter(Context context) {
		super(context);
	}

	@NonNull
	@Override
	public CapitalFlowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_business, parent, false);
		return new CapitalFlowViewHolder(this, view);
	}

	class CapitalFlowViewHolder extends BaseViewHolder<BusinessList> {

		public CapitalFlowViewHolder(BaseAdapter adapter, View itemView) {
			super(adapter, itemView);
		}

		@Override
		public void bindData(BusinessList data) {

		}
	}

}
