package com.shizy.template.components.common.ui.list;

import android.content.Context;

import com.shizy.template.R;
import com.shizy.template.common.view.adapter.CommonAdapter;
import com.shizy.template.common.view.adapter.ViewHolder;
import com.shizy.template.common.bean.IListItem;

public class SelectListAdapter extends CommonAdapter<IListItem> {

	public SelectListAdapter(Context context) {
		super(context, R.layout.item_select_list);
	}

	@Override
	protected void convert(ViewHolder holder, IListItem item) {
		holder.setText(R.id.tv_title, item.getName());
	}
}
