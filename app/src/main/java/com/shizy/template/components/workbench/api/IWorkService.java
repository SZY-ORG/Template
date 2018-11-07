package com.shizy.template.components.workbench.api;

import com.shizy.template.common.bean.Pagination;
import com.shizy.template.components.taskhall.bean.Task;
import com.shizy.template.net.response.ResponseData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWorkService {

	@GET("api/task/list.json")
	Observable<ResponseData<Pagination<Task>>> getArrangementList(@Query("timestamp") String timestamp);

}
