package com.shizy.template.components.workbench;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shizy.template.R;
import com.shizy.template.common.utils.RecyclerViewUtil;
import com.shizy.template.common.view.activity.BaseTitleActivity;
import com.shizy.template.common.view.adapter.recyclerview.BaseAdapter;
import com.shizy.template.components.taskhall.ui.TaskDetailActivity;
import com.shizy.template.components.workbench.adapter.ArrangementHistoryListAdapter;
import com.shizy.template.components.workbench.bean.Work;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * description 历史出车
 *
 * @author dahu
 * time 2018/11/2 15:44.
 */
public class ArrangementHistoryActivity extends BaseTitleActivity {

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
			Work work = mAdapter.getItem(position);
			if (work != null) {
				Intent intent = new Intent(ArrangementHistoryActivity.this, TaskDetailActivity.class);
				startActivity(intent);
			}
		}
	};

	@BindView(R.id.recycler_view)
	protected XRecyclerView mRecyclerView;

	private int mPage = 1;
	private ArrangementHistoryListAdapter mAdapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arrangement_history);
		initView();
	}

	private void initView() {
		setTitle(R.string.arrangement_history);
		initRecyclerView();
		mRecyclerView.refresh();
	}

	private void initRecyclerView() {
		RecyclerViewUtil.configLVRecyclerView(this, mRecyclerView);
		mRecyclerView.setLoadingListener(mLoadingListener);
		mAdapter = new ArrangementHistoryListAdapter(this);
		mAdapter.setOnItemClickListener(mOnItemClickListener);
		mRecyclerView.setAdapter(mAdapter);
	}

	private void loadData(final int page) {
		mRecyclerView.postDelayed(new Runnable() {
			@Override
			public void run() {
				mRecyclerView.refreshComplete();
				ArrayList<Work> list = new ArrayList<>();
				for (int i = 0; i < 10; i++) {
					list.add(new Work());
				}
				mAdapter.addAll(list);
			}
		}, 2000);

	}

}
