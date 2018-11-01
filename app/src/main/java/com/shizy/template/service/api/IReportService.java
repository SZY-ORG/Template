package com.shizy.template.service.api;

import com.shizy.template.net.response.ResponseData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IReportService {

	@POST("/api/location/upload")
	Call<ResponseData<Void>> uploadLocation(@Body String content);

}
