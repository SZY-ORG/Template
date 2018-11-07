package com.shizy.template.components.workbench;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.shizy.template.R;
import com.shizy.template.common.utils.ClickUtil;
import com.shizy.template.common.view.fragment.BaseTitleFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description 首页——每日出车
 *
 * @author dahu
 * time 2018/10/26 16:25.
 */
public class WorkbenchFragment extends BaseTitleFragment implements AMap.OnMarkerClickListener {

	@BindView(R.id.map)
	protected MapView mapView;

	private MarkerOptions markerOption, markerOption1;
	private AMap aMap;
	private LatLng latlng = new LatLng(39.91746, 116.396481);
	private LatLng latlng1 = new LatLng(39.91790, 116.396481);

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_workbench;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setTitle(R.string.workbench);
		setRightText(R.string.arrangement_history);
		// 此方法必须重写
		mapView.onCreate(savedInstanceState);

		aMap = mapView.getMap();
		setUpMap();
	}

	private void setUpMap() {
		aMap.setOnMarkerClickListener(this);

		// 往地图上添加marker
		addMarkersToMap();
		aMap.setMyLocationEnabled(false);

		UiSettings uiSettings = aMap.getUiSettings();
		uiSettings.setRotateGesturesEnabled(false);
//		uiSettings.setMyLocationButtonEnabled(true);


	}

	/**
	 * 在地图上添加marker
	 */
	private void addMarkersToMap() {

		markerOption = new MarkerOptions().icon(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
				.position(latlng)
				.draggable(true);
		aMap.addMarker(markerOption);

		markerOption1 = new MarkerOptions().icon(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
				.position(latlng1)
				.draggable(true);

		ArrayList<MarkerOptions> markerOptions = new ArrayList<>();
		markerOptions.add(markerOption);
		markerOptions.add(markerOption1);
		aMap.addMarkers(markerOptions, true);
	}

	@Override
	public boolean onMarkerClick(Marker marker) {

		Toast.makeText(getActivity(), "您点击了Marker:" + marker.getId(), Toast.LENGTH_LONG).show();
		return true;
	}

	@OnClick({R.id.traffic, R.id.my_location})
	protected void onClick(View view) {
		if (!ClickUtil.isValid()) {
			return;
		}
		switch (view.getId()) {
			// 交通状况
			case R.id.traffic:
				aMap.setTrafficEnabled(!aMap.isTrafficEnabled());
				break;
			// 我的位置
			case R.id.my_location:
				startLocation();
				break;
		}
	}

	private void startLocation() {
		AMapLocationClient aMapLocationClient = new AMapLocationClient(getActivity());

		aMapLocationClient.setLocationListener(new AMapLocationListener() {
			@Override
			public void onLocationChanged(AMapLocation aMapLocation) {

			}
		});
		AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
		//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
		aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
		//设置是否返回地址信息（默认返回地址信息）
		aMapLocationClientOption.setNeedAddress(true);
		//设置是否只定位一次,默认为false
		aMapLocationClientOption.setOnceLocation(false);
		//设置是否强制刷新WIFI，默认为强制刷新
		aMapLocationClientOption.setWifiActiveScan(true);
		//设置是否允许模拟位置,默认为false，不允许模拟位置
		aMapLocationClientOption.setMockEnable(false);
		//设置定位间隔,单位毫秒,默认为2000ms
		aMapLocationClientOption.setInterval(2000);
		//给定位客户端对象设置定位参数
		aMapLocationClient.setLocationOption(aMapLocationClientOption);
		//启动定位
		aMapLocationClient.startLocation();
	}
	/**
	 * 方法必须重写
	 */
	@Override
	public void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	public void onPause() {
		super.onPause();
		mapView.onPause();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	protected void onClickTitleRight() {
		startActivity(new Intent(getActivity(), ArrangementHistoryActivity.class));
		super.onClickTitleRight();
	}

}
