//package com.shizy.template.common.map;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.hardware.Sensor;
//import android.hardware.SensorEvent;
//import android.hardware.SensorEventListener;
//import android.hardware.SensorManager;
//import android.support.annotation.DrawableRes;
//import android.util.SparseArray;
//import android.widget.ImageView;
//
//import com.amap.api.location.AMapLocation;
//import com.amap.api.location.AMapLocationClient;
//import com.amap.api.location.AMapLocationClientOption;
//import com.amap.api.location.AMapLocationListener;
//import com.amap.api.maps.AMap;
//import com.amap.api.maps.AMapException;
//import com.amap.api.maps.CameraUpdateFactory;
//import com.amap.api.maps.UiSettings;
//import com.amap.api.maps.model.BitmapDescriptor;
//import com.amap.api.maps.model.BitmapDescriptorFactory;
//import com.amap.api.maps.model.CameraPosition;
//import com.amap.api.maps.model.LatLng;
//import com.amap.api.maps.model.Marker;
//import com.amap.api.maps.model.MarkerOptions;
//import com.shizy.template.R;
//import com.shizy.template.common.utils.UIUtil;
//
//import java.lang.ref.WeakReference;
//
///**
// * description
// *
// * @author dahu
// * time 2018/11/6 16:24.
// */
//public class BaseMapViewImpl implements IBaseMapView, AMapLocationListener {
//
//	private BitmapDescriptor locateBitmapDesc = BitmapDescriptorFactory.
//			fromResource(R.drawable.ic_launcher_foreground);
//	private final AMap aMap;
//	private SparseArray<Bitmap> bitmaps;
//
//	private ImageView trafficView;
//	private UiSettings mUiSettings;
//
//	protected WeakReference<Context> context;
//
//	private LatLng curLocation;
//	private MySensorEventListener mSensorListener;
//	private SensorManager mSensorManager;
//	private Sensor mAccelerometerSensor;
//	private Sensor mMagneticSensor;
//	private float bearing;
//
//	public BaseMapViewImpl(Context context, AMap aMap) {
//		this.context = new WeakReference<>(context);
//		this.aMap = aMap;
//		bitmaps = new SparseArray<>();
//		mUiSettings = aMap.getUiSettings();
//		mUiSettings.setZoomControlsEnabled(false);
//	}
//
//	private void locationInit() {
//		Context context = this.context.get();
//		if (context == null) {
//			release();
//			return;
//		}
//		mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
//		mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//		mMagneticSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//		mSensorListener = new MySensorEventListener(this);
//		mSensorManager.registerListener(mSensorListener, mAccelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
//		mSensorManager.registerListener(mSensorListener, mMagneticSensor, SensorManager.SENSOR_DELAY_GAME);
//		startLocation();
//	}
//
//	private AMapLocationClient aMapLocationClient;
//
//	private void startLocation() {
//		Context context = this.context.get();
//		if (context == null) {
//			release();
//			return;
//		}
//		aMapLocationClient = new AMapLocationClient(context.getApplicationContext());
//
//		aMapLocationClient.setLocationListener(this);
//		AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
//		//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
//		aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//		//设置是否返回地址信息（默认返回地址信息）
//		aMapLocationClientOption.setNeedAddress(true);
//		//设置是否只定位一次,默认为false
//		aMapLocationClientOption.setOnceLocation(false);
//		//设置是否强制刷新WIFI，默认为强制刷新
//		aMapLocationClientOption.setWifiActiveScan(true);
//		//设置是否允许模拟位置,默认为false，不允许模拟位置
//		aMapLocationClientOption.setMockEnable(false);
//		//设置定位间隔,单位毫秒,默认为2000ms
//		aMapLocationClientOption.setInterval(2000);
//		//给定位客户端对象设置定位参数
//		aMapLocationClient.setLocationOption(aMapLocationClientOption);
//		//启动定位
//		aMapLocationClient.startLocation();
//	}
//
//	private long lastupdateTime;
//
//	private void updateMarker(float value) {
//		bearing = -value;
//		if (System.currentTimeMillis() - lastupdateTime > 500) {
//			if (locationMarker != null) {
//				locationMarker.setRotateAngle(bearing);
//			}
//			lastupdateTime = System.currentTimeMillis();
//		}
//	}
//
//	@Override
//	public void onTraffic() {
//		if (aMap == null) {
//			return;
//		}
//		if (aMap.isTrafficEnabled()) {
//			aMap.setTrafficEnabled(false);
//			trafficView.setImageResource(R.drawable.traffic_close);
//		} else {
//			aMap.setTrafficEnabled(true);
//			trafficView.setImageResource(R.drawable.traffic_open);
//		}
//	}
//
//	@Override
//	public void onLocation() {
//		changeCamera(curLocation);
//	}
//
//	@Override
//	public void onDriveRouteSearched(LatLonPoint startLatLng, LatLonPoint endLatLng) {
//		final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
//				startLatLng
//				, endLatLng);
//		RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DrivingDefault, null,
//				null, "");// 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
//		mRouteSearch.calculateDriveRouteAsyn(query);// 异步路径规划驾车模式查询
//	}
//
//	@Override
//	public void onDriveRouteSearched(LatLonPoint endNavi) {
//		if (curLocation == null) {
//			needDrawLocationTo = endNavi;
//			return;
//		}
//		onDriveRouteSearched(new LatLonPoint(curLocation.latitude, curLocation.longitude), endNavi);
//		needDrawLocationTo = null;
//	}
//
//	@Override
//	public void setTrafficView(ImageView imageView) {
//		this.trafficView = imageView;
//	}
//
//	@Override
//	public void setCompassEnabled(boolean enabled) {
//		mUiSettings.setCompassEnabled(enabled);
//	}
//
//	@Override
//	public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
//
//	}
//
//	@Override
//	public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
//
//		Context context = this.context.get();
//		if (context == null) {
//			release();
//			return;
//		}
//		if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
//			if (lastDrivingRouteOverLayer != null) {
//				lastDrivingRouteOverLayer.removeFromMap();
//			}
//
//			if (result != null && result.getPaths() != null) {
//				if (result.getPaths().size() > 0) {
//					final DrivePath drivePath = result.getPaths()
//							.get(0);
//					DrivingRtOverLay drivingRouteOverlay = new DrivingRtOverLay(
//							context, aMap, drivePath,
//							result.getStartPos(),
//							result.getTargetPos(), null);
//					drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
//					drivingRouteOverlay.removeFromMap();
//					drivingRouteOverlay.addToMap();
//					drivingRouteOverlay.zoomToSpan();
//					lastDrivingRouteOverLayer = drivingRouteOverlay;
//
//				} else if (result.getPaths() == null) {
//					UIUtil.showToast(R.string.toast_driver_route_search_error);
//				}
//
//			} else {
//				UIUtil.showToast(R.string.toast_driver_route_search_error);
//			}
//		} else {
//			UIUtil.showToast(R.string.toast_driver_route_search_error);
//		}
//	}
//
//	@Override
//	public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
//
//	}
//
//	@Override
//	public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {
//
//	}
//
//	@Override
//	public void onPause() {
//		releaseLocation();
//	}
//
//	@Override
//	public void onResume() {
//		locationInit();
//	}
//
//	@Override
//	public void release() {
//		if (locateBitmapDesc != null) {
//			locateBitmapDesc.recycle();
//			locateBitmapDesc = null;
//		}
//		releaseLocation();
//		Session.removeTempObject(Session.TempKEY.CURRENT_CITY);
//		Session.removeTempObject(Session.TempKEY.CURRENT_KEYWORDS);
//		for (int i = 0, nsize = bitmaps.size(); i < nsize; i++) {
//			Bitmap bitmap = bitmaps.valueAt(i);
//			if (bitmap != null && !bitmap.isRecycled()) {
//				bitmap.recycle();
//			}
//		}
//		bitmaps.clear();
//	}
//
//	@Override
//	public void changeCamera(LatLng latLng) {
//		if (latLng == null || (latLng.latitude == 0 && latLng.longitude == 0)) {
//			return;
//		}
//		aMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
//				latLng, 18, 0, 0)));
//	}
//
//	@Override
//	public void clearLastRoute() {
//		if (lastDrivingRouteOverLayer != null) {
//			lastDrivingRouteOverLayer.removeFromMap();
//			lastDrivingRouteOverLayer = null;
//		}
//	}
//
//	@Override
//	public void zoomIn() {
//		aMap.animateCamera(CameraUpdateFactory.zoomIn());
//	}
//
//	@Override
//	public void zoomOut() {
//		aMap.animateCamera(CameraUpdateFactory.zoomOut());
//	}
//
//	@Override
//	public Marker showMarker(LatLng latLng, Bitmap bitmap) {
//		return showMarker(latLng, bitmap, false);
//	}
//
//	@Override
//	public Marker showMarker(LatLng latLng, Bitmap bitmap, boolean autoMove) {
//		BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
//		MarkerOptions mark = new MarkerOptions()
//				.position(latLng)
//				.icon(bitmapDescriptor).title("").draggable(true);
//
//		Marker marker = aMap.addMarker(mark);
//		if (autoMove) {
//			changeCamera(latLng);
//		}
//		return marker;
//	}
//
//	@Override
//	public Marker showMarker(LatLng latLng, int markerResId) {
//		return showMarker(latLng, markerResId, false);
//	}
//
//	private Bitmap getBitmap(@DrawableRes int resId) {
//		Context context = this.context.get();
//		if (context == null) {
//			return null;
//		}
//		Bitmap bitmap = bitmaps.get(resId);
//		if (bitmap == null) {
//			bitmap = BitmapUtil.createMapMarkerIcon(context, "", resId);
//			bitmaps.put(resId, bitmap);
//		}
//		return bitmap;
//	}
//
//	@Override
//	public Marker showMarker(LatLng latLng, int markerResId, boolean autoMove) {
//		Bitmap bitmap = getBitmap(markerResId);
//		return showMarker(latLng, bitmap, autoMove);
//	}
//
//	@Override
//	public void updateMarker(Marker marker, int resId) {
//		Bitmap bitmap = getBitmap(resId);
//		updateMarker(marker,bitmap);
//	}
//
//	@Override
//	public void updateMarker(Marker marker, Bitmap bitmap) {
//		BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
//		marker.setIcon(bitmapDescriptor);
//	}
//
//	@Override
//	public void moveTo(LatLng latLng) {
//		if (latLng == null || (latLng.latitude == 0 && latLng.longitude == 0)) {
//			return;
//		}
//		aMap.animateCamera(CameraUpdateFactory.changeLatLng(latLng));
//	}
//
//	@Override
//	public void onLocationChanged(AMapLocation aMapLocation) {
//		curLocation = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
//		if (needDrawLocationTo != null) {
//			onDriveRouteSearched(needDrawLocationTo);
//		}
//		Session.putTempObject(Session.TempKEY.CURRENT_CITY, aMapLocation.getCity());
//
//		if (locationMarker == null) {
//			MarkerOptions options = new MarkerOptions()
//					.position(curLocation)
//					.icon(locateBitmapDesc).draggable(false);
//			options.anchor(0.5f, 0.5f);
//			locationMarker = aMap.addMarker(options);
//		} else {
//			locationMarker.setPosition(curLocation);
//		}
//		locationMarker.setRotateAngle(bearing);
//	}
//
//	private static class MySensorEventListener implements SensorEventListener {
//		WeakReference<BaseMapViewImpl> weakRefBaseView;
//
//		public MySensorEventListener(BaseMapViewImpl baseMapView) {
//			weakRefBaseView = new WeakReference<>(baseMapView);
//		}
//
//		private float[] accelerometerValues = new float[3];
//		private float[] magneticValues = new float[3];
//
//		@Override
//		public void onSensorChanged(SensorEvent event) {
//			BaseMapViewImpl baseMapView = weakRefBaseView.get();
//			if (baseMapView == null) {
//
//				return;
//			}
//			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//				// 注意赋值时要调用clone()方法
//				accelerometerValues = event.values.clone();
//			} else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
//				// 注意赋值时要调用clone()方法
//				magneticValues = event.values.clone();
//			}
//			float[] r = new float[9];
//			float[] values = new float[3];
//			SensorManager.getRotationMatrix(r, null, accelerometerValues, magneticValues);
//			SensorManager.getOrientation(r, values);
//			baseMapView.updateMarker((float) Math.toDegrees(values[0]));
//
//		}
//
//		@Override
//		public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//		}
//
//	}
//
//	private void releaseLocation() {
//
//		if (mSensorManager != null && mSensorListener != null) {
//			mSensorManager.unregisterListener(mSensorListener, mAccelerometerSensor);
//			mSensorManager.unregisterListener(mSensorListener, mMagneticSensor);
//			mSensorManager = null;
//			mSensorListener = null;
//			mAccelerometerSensor = null;
//			mMagneticSensor = null;
//		}
//		if (aMapLocationClient != null) {
//			aMapLocationClient.stopLocation();
//			aMapLocationClient.onDestroy();
//			aMapLocationClient = null;
//		}
//	}
//
//	private Marker locationMarker;
//
//}