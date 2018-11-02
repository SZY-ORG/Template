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
import com.shizy.template.components.personalcenter.finance.adapter.OutAccountAdapter;
import com.shizy.template.components.personalcenter.finance.bean.OutAccountMoney;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * description 未入账金额
 *
 * @author dahu
 * time 2018/11/2 10:25.
 */
public class OutAccountMoneyActivity extends BaseTitleActivity {

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

	private OutAccountAdapter.OnItemClickListener mOnItemClickListener = new BaseAdapter.OnItemClickListener() {
		@Override
		public void onItemClick(View view, int position) {
			OutAccountMoney outAccountMoney = mAdapter.getItem(position);
			if (position == 0) {
				Intent intent = new Intent(OutAccountMoneyActivity.this, UnsettlementMoneyActivity.class);
				startActivity(intent);
			} else if (position == 1) {
				Intent intent = new Intent(OutAccountMoneyActivity.this, FrozenMoneyActivity.class);
				startActivity(intent);
			}
		}
	};

	@BindView(R.id.recycler_view)
	protected XRecyclerView mRecyclerView;

	private int mPage = 1;
	private OutAccountAdapter mAdapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_out_of_account_money);
		initView();
	}

	private void initView() {
		setTitle(R.string.out_of_account_money);

		initRecyclerView();
		mRecyclerView.refresh();
	}

	private void initRecyclerView() {
		RecyclerViewUtil.configLVRecyclerView(this, mRecyclerView);
		mRecyclerView.setLoadingListener(mLoadingListener);
		mAdapter = new OutAccountAdapter(this);
		mAdapter.setOnItemClickListener(mOnItemClickListener);
		mRecyclerView.setAdapter(mAdapter);
	}

	private void loadData(final int page) {
		mRecyclerView.postDelayed(new Runnable() {
			@Override
			public void run() {
				mRecyclerView.refreshComplete();
				ArrayList<OutAccountMoney> list = new ArrayList<>();
				for (int i = 0; i < 10; i++) {
					list.add(new OutAccountMoney());
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
