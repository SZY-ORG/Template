package com.shizy.template.components.taskhall;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.activity.BaseTitleActivity;

/**
 * description
 * 接单/抢单确认页
 *
 * @author dahu
 * time 2018/10/25 10:41.
 */
public class BidConfirmActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bid_confirm);
		initView();
	}

	private void initView() {
		setTitle(R.string.bid_confirm);
		setRightText(R.string.ok);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}
}
