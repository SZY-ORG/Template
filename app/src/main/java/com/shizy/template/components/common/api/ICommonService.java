package com.shizy.template.components.common.api;

import com.shizy.template.common.bean.City;
import com.shizy.template.net.response.ResponseData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ICommonService {

	@GET("api/city.json")
	Observable<ResponseData<List<City>>> getOpenCityList();

}
