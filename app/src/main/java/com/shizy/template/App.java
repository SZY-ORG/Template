package com.shizy.template;

import android.app.Application;

import com.shizy.template.common.constant.ServerHost;
import com.shizy.template.common.mtj.StatisticsUtil;
import com.shizy.template.common.utils.ActivityManager;
import com.shizy.template.common.utils.AppUtil;
import com.shizy.template.common.utils.NetUtil;
import com.shizy.template.net.RetrofitHelper;

public class App extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		initialize();
		StatisticsUtil.init(this);
	}

	private void initialize() {
		AppUtil.init(this);
		RetrofitHelper.getInstance().init(this, ServerHost.getServerAddress());
		RetrofitHelper.getInstance().addHeaders(NetUtil.getHeaders());

		registerActivityLifecycleCallbacks(ActivityManager.getInstance());
	}

}
