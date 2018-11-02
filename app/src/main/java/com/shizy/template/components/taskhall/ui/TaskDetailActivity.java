package com.shizy.template.components.taskhall.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shizy.template.R;
import com.shizy.template.common.utils.RxJavaUtil;
import com.shizy.template.common.view.activity.BaseTitleActivity;
import com.shizy.template.components.taskhall.api.ITaskService;
import com.shizy.template.components.taskhall.bean.TaskDetail;
import com.shizy.template.net.ServiceFactory;
import com.shizy.template.net.response.ResponseData;
import com.shizy.template.net.response.ResponseObserver;

/**
 * description
 * 任务详情
 *
 * @author dahu
 * time 2018/10/25 10:29.
 */
public class TaskDetailActivity extends BaseTitleActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_detail);
		initView();
	}

	private void initView() {
		setTitle(R.string.task_detail);
	}

	private void getTaskDetail() {
		ServiceFactory.getService(ITaskService.class).getTaskDetail("123")
				.compose(RxJavaUtil.<ResponseData<TaskDetail>>mainSchedulers())
				.as(this.<ResponseData<TaskDetail>>bindLifecycle())
				.subscribe(new ResponseObserver<TaskDetail>() {
					@Override
					protected void onSuccess(ResponseData<TaskDetail> responseData) {

					}
				});
	}

}
