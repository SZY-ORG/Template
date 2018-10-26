package com.shizy.template.common.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shizy.template.R;

public class RecyclerViewUtil {

	/**
	 * 线性纵向RecyclerView
	 *
	 * @param context
	 * @param recyclerView
	 */
	public static void configLVRecyclerView(Context context, XRecyclerView recyclerView) {
		LinearLayoutManager layoutManager = new LinearLayoutManager(context);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		configRecyclerView(context, recyclerView, layoutManager);
	}

	/**
	 * 配置RecyclerView
	 *
	 * @param recyclerView
	 */
	public static void configRecyclerView(Context context, XRecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
		recyclerView.setLayoutManager(layoutManager);

		recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
		recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
//		recyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

		recyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);

		recyclerView.getDefaultFootView().setLoadingHint(ResourcesUtil.getString(R.string.loading));
		recyclerView.getDefaultFootView().setNoMoreHint(ResourcesUtil.getString(R.string.no_more_data));
	}

}
