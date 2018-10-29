package com.shizy.template.components.taskhall.bean;

import com.google.gson.annotations.SerializedName;
import com.shizy.template.common.bean.Address;
import com.shizy.template.common.bean.BaseBean;
import com.shizy.template.common.bean.Range;

public class Task extends BaseBean {

	/**
	 * 车型
	 * 需求车数
	 * 要求到仓时间
	 * 货物重量
	 * 是否需要装卸货
	 * 提货地址
	 * 卸货地址
	 * 工作时间段
	 * 预计配送点数
	 * 预计行驶里程数
	 */
	private String id;
	private int type;
	private int status;
	private Cargo cargo;// 货物信息
	private Range mileage;// 预计行驶里程数
	@SerializedName("delivery_point")
	private Range deliveryPoint;// 预计配送点数
	@SerializedName("loading_address")
	private Address loadingAddress;// 装货地址

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
