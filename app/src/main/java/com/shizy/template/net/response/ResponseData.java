package com.shizy.template.net.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shizy on 2018/10/22.
 * 服务端返回的统一JSON结构
 *
 * 开启混淆需要保持成员变量
 * -keepclassmembers class **.response.ResponseData {
 * 	<fields>;
 * 	<init>();
 * }
 */

public class ResponseData<T> {

	private int code;
	private String msg;
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
