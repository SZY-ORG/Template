package com.shizy.template.components.workbench;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.view.activity.BaseTitleActivity;

/**
 * description 历史出车
 *
 * @author dahu
 * time 2018/11/2 15:44.
 */
public class ArrangementHistoryActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arrangement_history);
		initView();
	}

	private void initView() {
		setTitle(R.string.arrangement_history);

	}

	@Override
	protected void onClickTitleLeft() {

		super.onClickTitleLeft();
	}

}
