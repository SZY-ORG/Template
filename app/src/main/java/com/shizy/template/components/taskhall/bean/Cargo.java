package com.shizy.template.components.taskhall.bean;

import com.shizy.template.common.bean.BaseBean;
import com.shizy.template.common.bean.Range;

/**
 * 货物信息
 */
public class Cargo extends BaseBean {

	private int type;
	private String name;
	private Range quantity;// 数量
	private Range volume;// 体积
	private Range weight;// 重量

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Range getQuantity() {
		return quantity;
	}

	public void setQuantity(Range quantity) {
		this.quantity = quantity;
	}

	public Range getVolume() {
		return volume;
	}

	public void setVolume(Range volume) {
		this.volume = volume;
	}

	public Range getWeight() {
		return weight;
	}

	public void setWeight(Range weight) {
		this.weight = weight;
	}
}
