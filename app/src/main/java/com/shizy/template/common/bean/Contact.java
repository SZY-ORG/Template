package com.shizy.template.common.bean;

import java.util.List;

/**
 * 联系人
 */
public class Contact extends BaseBean {

	private String name;
	private List<String> numbers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}
}
