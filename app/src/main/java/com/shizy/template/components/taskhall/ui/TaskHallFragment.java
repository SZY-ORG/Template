package com.shizy.template.components.taskhall.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shizy.template.R;
import com.shizy.template.common.bean.Pagination;
import com.shizy.template.common.utils.RecyclerViewUtil;
import com.shizy.template.common.utils.RxJavaUtil;
import com.shizy.template.common.utils.UIUtil;
import com.shizy.template.common.view.fragment.BaseTitleFragment;
import com.shizy.template.components.taskhall.api.ITaskService;
import com.shizy.template.components.taskhall.bean.Task;
import com.shizy.template.net.ResponseException;
import com.shizy.template.net.ServiceFactory;
import com.shizy.template.net.response.ResponseData;
import com.shizy.template.net.response.ResponseObserver;

import java.util.List;

import butterknife.BindView;

public class TaskHallFragment extends BaseTitleFragment {

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

	@BindView(R.id.recycler_view)
	protected XRecyclerView mRecyclerView;

	private int mPage = 1;
	private String mTimestamp;
	private TaskHallAdapter mAdapter;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_task_hall;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setTitle(R.string.task_hall);

		initRecyclerView();
		mRecyclerView.refresh();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (mRecyclerView != null) {
			mRecyclerView.destroy();
			mRecyclerView = null;
		}
	}

	private void initRecyclerView() {
		RecyclerViewUtil.configLVRecyclerView(getActivity(), mRecyclerView);
		mRecyclerView.setLoadingListener(mLoadingListener);
		mAdapter = new TaskHallAdapter(getActivity());
		mRecyclerView.setAdapter(mAdapter);
	}

	private void loadData(final int page) {
		ServiceFactory.getService(ITaskService.class)
				.getTaskList(page, mTimestamp)
				.compose(RxJavaUtil.<ResponseData<Pagination<Task>>>mainSchedulers())
				.as(this.<ResponseData<Pagination<Task>>>bindLifecycle())
				.subscribe(new ResponseObserver<Pagination<Task>>() {
					@Override
					protected void onSuccess(ResponseData<Pagination<Task>> responseData) {
						Pagination<Task> pagination = responseData.getData();
						mPage = pagination.getPage();
						List<Task> list = pagination.getList();
						if (mPage == 1) {
							mAdapter.setData(list);
							mTimestamp = pagination.getTimestamp();
						} else {
							mAdapter.addAll(list);
						}
					}

					@Override
					protected void onFailure(ResponseException e) {
						UIUtil.showToast(e.getMsg());
					}

					@Override
					protected void onFinally() {
						if (mPage == 1) {
							mRecyclerView.refreshComplete();
						} else {
							mRecyclerView.loadMoreComplete();
						}
					}
				});
	}

}
