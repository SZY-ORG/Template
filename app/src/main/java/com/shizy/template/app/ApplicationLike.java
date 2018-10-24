package com.shizy.template.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.shizy.template.BuildConfig;
import com.shizy.template.common.constant.ServerHost;
import com.shizy.template.common.mtj.StatisticsUtil;
import com.shizy.template.common.utils.ActivityManager;
import com.shizy.template.common.utils.AppUtil;
import com.shizy.template.common.utils.NetUtil;
import com.shizy.template.net.RetrofitHelper;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

import java.lang.ref.WeakReference;
import java.util.Locale;

/**
 * description
 *
 * @author dahu
 * time 2018/10/24 11:39.
 */
public class ApplicationLike extends DefaultApplicationLike {

	private static ApplicationLike mApplicationLike;
	private static Application application;

	private static WeakReference<Activity> mCurrentActivity;

	public ApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
		super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mApplicationLike = this;
		application = getApplication();
		initBugly(application);

		// BaseApplicationLike是Application的代理类，以前所有在Application的实现必须要全部拷贝到这里
		onApplicationCreate(application);

	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public void onBaseContextAttached(Context base) {
		super.onBaseContextAttached(base);
		// you must install multiDex whatever tinker is installed!
		//需要开启MultiDex,需要在dependencies中进行配置compile "com.android.support:multidex:1.0.1"才可以使用MultiDex.install方法；
		MultiDex.install(base);
		// 安装tinker
		// TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
		Beta.installTinker(this);
	}

	private void initialize() {
		AppUtil.init(application);
		RetrofitHelper.getInstance().init(application, ServerHost.getServerAddress());
		RetrofitHelper.getInstance().addHeaders(NetUtil.getHeaders());

		application.registerActivityLifecycleCallbacks(ActivityManager.getInstance());
	}

	private void initBugly(final Application application) {
		// 设置是否开启热更新能力，默认为true
		//Beta.enableHotfix = true;
		// 设置是否自动下载补丁，默认为true
		//Beta.canAutoDownloadPatch = false;
		// 设置是否自动合成补丁，默认为true
		//Beta.canAutoPatch = false;
		// 设置是否提示用户重启，默认为false
		Beta.canNotifyUserRestart = true;
		// 补丁回调接口
		Beta.betaPatchListener = new BetaPatchListener() {
			@Override
			public void onPatchReceived(String patchFile) {
				if (BuildConfig.DEBUG) {
					Toast.makeText(application, "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onDownloadReceived(long savedLength, long totalLength) {
				if (BuildConfig.DEBUG) {
					Toast.makeText(application,
							String.format(Locale.getDefault(), "%s %d%%",
									Beta.strNotificationDownloading,
									(int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
							Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onDownloadSuccess(String msg) {
				if (BuildConfig.DEBUG) {
					Toast.makeText(application, "补丁下载成功", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onDownloadFailure(String msg) {
				if (BuildConfig.DEBUG) {
					Toast.makeText(application, "补丁下载失败", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onApplySuccess(String msg) {
				if (BuildConfig.DEBUG) {
					Toast.makeText(application, "补丁应用成功", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onApplyFailure(String msg) {
				if (BuildConfig.DEBUG) {
					Toast.makeText(application, "补丁应用失败", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onPatchRollback() {
				if (BuildConfig.DEBUG) {
					Toast.makeText(application, "撤回补丁", Toast.LENGTH_SHORT).show();
				}
			}
		};

		//设置开发设备，默认为false，上传补丁如果下发范围指定为“开发设备”，需要调用此接口来标识开发设备
		Bugly.setIsDevelopmentDevice(application, BuildConfig.DEBUG);

		// 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
		BuglyStrategy strategy = new BuglyStrategy();
		// TODO: 2018/10/24
//		strategy.setAppChannel(AppUtil.getMarketChannel(getApplication()));
//		// 调试时，将第三个参数改为true
		Bugly.init(getApplication(), "appId", BuildConfig.DEBUG, strategy);

	}

	private void onApplicationCreate(Application application) {
		StatisticsUtil.init(application);
		initialize();
	}

}
