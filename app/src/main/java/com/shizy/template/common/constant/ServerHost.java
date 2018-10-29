package com.shizy.template.common.constant;

import com.shizy.template.BuildConfig;
import com.shizy.template.common.utils.SPUtil;

public class ServerHost {

	public static final String SERVER_DEV = "http://192.168.1.2:8080/";
	public static final String SERVER_MASTER = "http://10.113.9.36:8080/";
	public static final String SERVER_STABLE = "http://192.168.1.2:8080/";

	private static String sServerAddress = SPUtil.getString(SPConstant.SERVER_ADDRESS, BuildConfig.DEBUG ? SERVER_MASTER : SERVER_STABLE);

	public static String getServerAddress() {
		return sServerAddress;
	}

	private ServerHost() {
		throw new RuntimeException("Can not invoke constructor!");
	}

}
