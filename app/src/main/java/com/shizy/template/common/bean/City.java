package com.shizy.template.common.bean;

public class City extends BaseBean implements IListItem {

	private String id;
	private String name;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}
