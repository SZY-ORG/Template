package com.shizy.template.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.shizy.template.common.bean.Location;
import com.shizy.template.common.constant.SPConstant;
import com.shizy.template.common.db.DatabaseHelper;
import com.shizy.template.common.utils.LogUtil;
import com.shizy.template.common.utils.SPUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.sql.SQLException;

/**
 * 定位服务
 */
public class LocationService extends Service {

	public static final int INTERVAL_SHORT = 5 * 1000;
	public static final int INTERVAL_LONG = 30 * 1000;

	@IntDef({INTERVAL_SHORT, INTERVAL_LONG})
	@Retention(RetentionPolicy.SOURCE)
	public @interface Interval {
	}

	public class LocationBinder extends Binder {

		public LocationService getService() {
			return LocationService.this;
		}

	}

	//声明定位回调监听器
	public AMapLocationListener mLocationListener = new AMapLocationListener() {
		@Override
		public void onLocationChanged(AMapLocation aMapLocation) {
			if (aMapLocation == null) {
				return;
			}
			if (aMapLocation.getErrorCode() == 0) {
				LogUtil.d("onLocationChanged");
				saveLocation(aMapLocation);
			} else {
				LogUtil.e("location failed!");
			}
		}
	};

	private final IBinder mBinder = new LocationBinder();

	//声明AMapLocationClient类对象
	private AMapLocationClient mLocationClient = null;
	//声明AMapLocationClientOption对象
	private AMapLocationClientOption mLocationOption = null;

	private DatabaseHelper mDatabaseHelper = null;

	@Override
	public void onCreate() {
		mDatabaseHelper = DatabaseHelper.getHelper(this);
		initLocation();
		startSelf();
	}

	private void initLocation() {
		initClientOption();

		//初始化定位
		mLocationClient = new AMapLocationClient(getApplicationContext());
		//设置定位回调监听
		mLocationClient.setLocationListener(mLocationListener);
		//给定位客户端对象设置定位参数
		mLocationClient.setLocationOption(mLocationOption);
		//启动定位
		mLocationClient.startLocation();
	}

	private void initClientOption() {
		//初始化AMapLocationClientOption对象
		mLocationOption = new AMapLocationClientOption();
		//设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
		mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
		//设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
		mLocationOption.setInterval(INTERVAL_LONG);
		//设置是否返回地址信息（默认返回地址信息）
		mLocationOption.setNeedAddress(true);
		//设置是否允许模拟位置,默认为true，允许模拟位置
		mLocationOption.setMockEnable(false);
		//单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
		mLocationOption.setHttpTimeOut(20000);
		//关闭缓存机制
		mLocationOption.setLocationCacheEnable(false);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mDatabaseHelper = null;
		DatabaseHelper.releaseHelper();
		if (mLocationClient != null) {
			mLocationClient.stopLocation();
			mLocationClient.onDestroy();
			mLocationClient = null;
		}
	}

	/**
	 * 启动自己，避免通过bind启动时，unBind后服务被关闭
	 */
	private void startSelf() {
		startService(new Intent(this, LocationService.class));
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	private void saveLocation(AMapLocation aMapLocation) {
		if (aMapLocation == null) {
			return;
		}
//		String uid = SPUtil.getString(SPConstant.UID, null);
		String uid = "123";
		if (TextUtils.isEmpty(uid)) {
			return;
		}
		try {
			mDatabaseHelper.getLocationDao().create(new Location(aMapLocation, uid));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public AMapLocation getLastLocation() {
		if (mLocationClient != null) {
			mLocationClient.getLastKnownLocation();
		}
		return null;
	}

	public void setInterval(@Interval int interval) {
		if (mLocationClient == null || mLocationOption == null) {
			return;
		}
		mLocationOption.setInterval(interval);
		if (mLocationClient.isStarted()) {
			mLocationClient.stopLocation();
		}
		mLocationClient.startLocation();
	}

}
