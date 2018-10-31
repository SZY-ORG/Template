package com.shizy.template.components.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shizy.template.R;
import com.shizy.template.common.utils.UIUtil;
import com.shizy.template.common.view.activity.BaseActivity;
import com.shizy.template.components.main.bean.TabEntity;
import com.shizy.template.components.personalcenter.PersonalFragment;
import com.shizy.template.components.taskhall.ui.TaskHallFragment;
import com.shizy.template.components.workbench.WorkbenchFragment;
import com.shizy.template.service.LocationService;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

	private static final String KEY_TAB_INDEX = "main_tab_index";
	private static final long EXIT_INTERVAL = 2000;

	private static final int[] IDS_TITLE = {R.string.task_hall, R.string.workbench, R.string.personal_center};
	private static final int[] IDS_ICON_NORMAL = {R.drawable.tab_task_hall_n, R.drawable.tab_workbench_n, R.drawable.tab_personal_center_n};
	private static final int[] IDS_ICON_SELECTED = {R.drawable.tab_task_hall_s, R.drawable.tab_workbench_s, R.drawable.tab_personal_center_s};

	@BindView(R.id.layout_tab)
	protected CommonTabLayout mTabLayout;

	private ArrayList<Fragment> mFragmentList = new ArrayList<>();

	private long mLastBackTime;

	@Override
	public void onBackPressed() {
		if (System.currentTimeMillis() - mLastBackTime > EXIT_INTERVAL) {
			UIUtil.showToast(R.string.msg_exit_app);
			mLastBackTime = System.currentTimeMillis();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initTabs();

		int tabIndex = 0;
		if (savedInstanceState != null) {
			tabIndex = savedInstanceState.getInt(KEY_TAB_INDEX);
		}
		mTabLayout.setCurrentTab(tabIndex);

		startService(new Intent(this, LocationService.class));
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_TAB_INDEX, mTabLayout.getCurrentTab());
	}

	private void initTabs() {
		mFragmentList.add(new TaskHallFragment());
		mFragmentList.add(new WorkbenchFragment());
		mFragmentList.add(new PersonalFragment());
		mTabLayout.setTabData(getTabData(), this, R.id.layout_container, mFragmentList);
	}

	private ArrayList<CustomTabEntity> getTabData() {
		ArrayList<CustomTabEntity> list = new ArrayList<>();
		for (int i = 0; i < IDS_TITLE.length; i++) {
			list.add(new TabEntity(getString(IDS_TITLE[i]), IDS_ICON_SELECTED[i], IDS_ICON_NORMAL[i]));
		}
		return list;
	}

}
