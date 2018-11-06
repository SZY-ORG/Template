package com.shizy.template.components.account.bean;

import com.contrarywind.interfaces.IPickerViewData;

public class VehicleType implements IPickerViewData {

	private String name;

	public VehicleType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPickerViewText() {
		return getName();
	}
}
