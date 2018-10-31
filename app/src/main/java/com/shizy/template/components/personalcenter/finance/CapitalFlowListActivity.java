package com.shizy.template.components.personalcenter.finance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shizy.template.R;
import com.shizy.template.common.utils.RecyclerViewUtil;
import com.shizy.template.common.view.activity.BaseTitleActivity;
import com.shizy.template.common.view.adapter.recyclerview.BaseAdapter;
import com.shizy.template.components.personalcenter.finance.bean.CapitalFlow;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * description
 * 流水列表页面
 *
 * @author dahu
 * time 2018/10/25 10:24.
 */
public class CapitalFlowListActivity extends BaseTitleActivity {

	private XRecyclerView.LoadingListener mLoadingListener = new XRecyclerView.LoadingListener() {
		@Override
		public void onRefresh() {
			loadData(1);
		}

		@Override
		public void onLoadMore() {
			loadData(mPage + 1);
		}
	};

	private BaseAdapter.OnItemClickListener mOnItemClickListener = new BaseAdapter.OnItemClickListener() {
		@Override
		public void onItemClick(View view, int position) {
			CapitalFlow capitalFlow = mAdapter.getItem(position);
			if (capitalFlow != null) {
				Intent intent = new Intent(CapitalFlowListActivity.this, CapitalFlowDetailActivity.class);
				startActivity(intent);
			}
		}
	};

	@BindView(R.id.recycler_view)
	protected XRecyclerView mRecyclerView;

	private int mPage = 1;
	private CapitalFlowAdapter mAdapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capital_flow);
		initView();
	}

	private void initView() {
		setTitle(R.string.business_list);

		initRecyclerView();
		mRecyclerView.refresh();
	}

	private void initRecyclerView() {
		RecyclerViewUtil.configLVRecyclerView(this, mRecyclerView);
		mRecyclerView.setLoadingListener(mLoadingListener);
		mAdapter = new CapitalFlowAdapter(this);
		mAdapter.setOnItemClickListener(mOnItemClickListener);
		mRecyclerView.setAdapter(mAdapter);
	}

	private void loadData(final int page) {
		mRecyclerView.postDelayed(new Runnable() {
			@Override
			public void run() {
				mRecyclerView.refreshComplete();
				ArrayList<CapitalFlow> list = new ArrayList<>();
				for (int i = 0; i < 10; i++) {
					list.add(new CapitalFlow());
				}
				mAdapter.addAll(list);
			}
		}, 2000);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

}
