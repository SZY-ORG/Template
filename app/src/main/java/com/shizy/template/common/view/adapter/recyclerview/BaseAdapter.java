package com.shizy.template.common.view.adapter.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T, VH extends BaseViewHolder<T>> extends RecyclerView.Adapter<VH> {

	protected final Context mContext;
	protected final List<T> mData;

	public BaseAdapter(Context context) {
		this(context, null);
	}

	public BaseAdapter(Context context, List<T> data) {
		mContext = context;
		mData = data == null ? new ArrayList<T>() : new ArrayList<T>(data);
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	@Override
	public void onBindViewHolder(@NonNull VH holder, int position) {
		holder.bindData(mData.get(position));
	}

	public void setData(List<T> data) {
		mData.clear();
		addAll(data);
	}

	public void add(T item) {
		mData.add(item);
		notifyDataSetChanged();
	}

	public void addAll(List<T> items) {
		mData.addAll(items);
		notifyDataSetChanged();
	}

	public void remove(T item) {
		mData.remove(item);
		notifyDataSetChanged();
	}

	public void clear() {
		mData.clear();
		notifyDataSetChanged();
	}

}
