package com.shizy.template.components.common.ui.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shizy.template.R;
import com.shizy.template.common.bean.IListItem;
import com.shizy.template.common.utils.LogUtil;
import com.shizy.template.common.view.activity.BaseTitleActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

public class SelectListActivity extends BaseTitleActivity {

	private static final String EXTRA_OPTION = "option";

	@BindView(R.id.list_view)
	protected ListView mListView;

	private SelectListAdapter mAdapter;
	private Option mOption;
	private int mSelectedPosition;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			setResult(data);
		}
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_list);
		mOption = (Option) getIntent().getSerializableExtra(EXTRA_OPTION);
		if (mOption == null) {
			LogUtil.e("Missing param!");
			finish();
			return;
		}
		setTitle(mOption.getTitle());

		mAdapter = new SelectListAdapter(this);
		mListView.setAdapter(mAdapter);

		SelectListDataFactory.loadData(mOption.getType(), mOption.getParam(), this);
	}

	@OnItemClick(R.id.list_view)
	protected void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		mSelectedPosition = position;
		Option next = mOption.getNext();
		if (next != null) {// 跳转到下一级
			next.setParam(mAdapter.getItem(position));
			SelectListActivity.launchForResult(this, next, 0);
		} else {
			setResult(new Intent());
		}
	}

	void setData(List list) {
		if (mAdapter != null) {
			mAdapter.addAll(list);
		}
	}

	private void setResult(Intent data) {
		data.putExtra(mOption.getType(), mAdapter.getItem(mSelectedPosition));
		setResult(RESULT_OK, data);
		finish();
	}

	public static void launchForResult(Activity activity, Option option, int requestCode) {
		if (activity == null || option == null) {
			return;
		}
		Intent intent = new Intent(activity, SelectListActivity.class);
		intent.putExtra(EXTRA_OPTION, option);
		activity.startActivityForResult(intent, requestCode);
	}

	public static class Option implements Serializable {

		private String type;
		private String title;
		private IListItem param;
		private Option next;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public IListItem getParam() {
			return param;
		}

		public void setParam(IListItem param) {
			this.param = param;
		}

		public Option getNext() {
			return next;
		}

		public void setNext(Option next) {
			this.next = next;
		}
	}

}
