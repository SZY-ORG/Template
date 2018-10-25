package com.shizy.template.components.account.api;

import com.shizy.template.components.account.bean.User;
import com.shizy.template.net.response.ResponseData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IAccountService {

	@POST("api/login.json")
	@FormUrlEncoded
	Observable<ResponseData<User>> login(@Field("mobile") String mobile, @Field("password") String password);

	@POST("api/signUp.json")
	@FormUrlEncoded
	Observable<ResponseData<User>> signUp(@Field("name") String name, @Field("mobile") String mobile, @Field("password") String password);

}
