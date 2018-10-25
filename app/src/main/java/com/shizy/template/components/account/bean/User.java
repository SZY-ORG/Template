package com.shizy.template.components.account.bean;

import com.shizy.template.common.bean.BaseBean;

public class User extends BaseBean {

	private String name;
	private String mobile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
