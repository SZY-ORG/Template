package com.shizy.template.components.personalcenter.finance.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shizy.template.R;
import com.shizy.template.common.view.adapter.recyclerview.BaseAdapter;
import com.shizy.template.common.view.adapter.recyclerview.BaseViewHolder;
import com.shizy.template.components.personalcenter.finance.bean.UnsettlementMoney;

/**
 * description 未入账金额adapter
 *
 * @author dahu
 * time 2018/10/31 14:23.
 */
public class UnsettlementMoneyAdapter extends BaseAdapter<UnsettlementMoney, UnsettlementMoneyAdapter.UnsettlementMoneyViewHolder> {

	public UnsettlementMoneyAdapter(Context context) {
		super(context);
	}

	@NonNull
	@Override
	public UnsettlementMoneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_out_account, parent, false);
		return new UnsettlementMoneyViewHolder(this, view);
	}

	class UnsettlementMoneyViewHolder extends BaseViewHolder<UnsettlementMoney> {

		public UnsettlementMoneyViewHolder(BaseAdapter adapter, View itemView) {
			super(adapter, itemView);
		}

		@Override
		public void bindData(UnsettlementMoney data) {

		}
	}

}
