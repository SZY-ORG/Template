package com.shizy.template.common.view.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

	public BaseViewHolder(BaseAdapter adapter, View itemView) {
		super(itemView);
		itemView.setOnClickListener(adapter);
		ButterKnife.bind(this, itemView);
	}

	public abstract void bindData(T data);

}
