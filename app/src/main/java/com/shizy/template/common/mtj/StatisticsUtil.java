package com.shizy.template.common.mtj;

import android.content.Context;

import com.baidu.mobstat.StatService;

/**
 * description: 统计
 *
 * @author dahu
 * time 2018/10/23 11:04.
 */
public class StatisticsUtil {

	public static void init(Context context) {
		// 打开调试开关，可以查看logcat日志。版本发布前，为避免影响性能，移除此代码
		// 查看方法：adb logcat -s sdkstat
		StatService.setDebugOn(false);

		// 开启自动埋点统计，为保证所有页面都能准确统计，建议在Application中调用。
		// 第三个参数：autoTrackWebview：
		// 如果设置为true，则自动track所有webview；如果设置为false，则不自动track webview，
		// 如需对webview进行统计，需要对特定webview调用trackWebView() 即可。
		// 重要：如果有对webview设置过webchromeclient，则需要调用trackWebView() 接口将WebChromeClient对象传入，
		// 否则开发者自定义的回调无法收到。
		StatService.autoTrace(context, true, false);
	}

	/**
	 * 统计fragment的使用时长
	 *
	 * @param context
	 * @param event fragment的名称作为事件名称
	 */
	public static void onPageStart(Context context, String event) {
		StatService.onPageStart(context, event);
	}

	/**
	 * 统计fragment的使用时长
	 *
	 * @param context
	 * @param event fragment的名称作为事件名称
	 */
	public static void onPageEnd(Context context, String event) {
		StatService.onPageEnd(context, event);
	}

}
