package com.shizy.template.common.map;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;

/**
 * description
 *
 * @author dahu
 * time 2018/11/6 16:23.
 */
public interface IBaseMapView {

	void onTraffic();

	void onLocation();

//	void onDriveRouteSearched(LatLonPoint startLatLng, LatLonPoint endLatLng);

//	void onDriveRouteSearched(LatLonPoint endNavi);

	void setTrafficView(ImageView imageView);

	void setCompassEnabled(boolean enabled);

	void onPause();

	void onResume();

	void release();

	void changeCamera(LatLng latLng);

	void clearLastRoute();

	void zoomIn();

	void zoomOut();

	Marker showMarker(LatLng latLng, Bitmap bitmap);

	Marker showMarker(LatLng latLng, Bitmap bitmap, boolean autoMove);

	Marker showMarker(LatLng latLng, int markerResId);

	Marker showMarker(LatLng latLng, int markerResId, boolean autoMove);

	void updateMarker(Marker marker, int resId);

	void updateMarker(Marker marker, Bitmap resId);

	void moveTo(LatLng latLng);

}
