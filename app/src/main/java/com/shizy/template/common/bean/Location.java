package com.shizy.template.common.bean;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationQualityReport;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 位置信息
 */
@DatabaseTable(tableName = "location")
public class Location extends BaseBean {

	@DatabaseField(generatedId = true)
	private long id;
	@DatabaseField
	private String uid;// 当前用户id
	@DatabaseField
	private int type;// 定位类型
	@DatabaseField
	private double longitude;// 经度
	@DatabaseField
	private double latitude;// 纬度
	@DatabaseField
	private float accuracy;// 精度
	@DatabaseField
	private double altitude;// 海拔
	@DatabaseField
	private float speed;// 速度
	@DatabaseField
	private float bearing;// 角度
	@DatabaseField
	private int satellites;//卫星个数
	@DatabaseField
	private long time;// 定位时间
	@DatabaseField
	private int wifiAble;// WIFI开关
	@DatabaseField
	private int gpsStatus;// GPS状态
	@DatabaseField
	private String networkType;// 网络类型

	public Location() {

	}

	public Location(AMapLocation location, String uid) {
		if (location != null) {
			this.uid = uid;
			type = location.getLocationType();
			longitude = location.getLongitude();
			latitude = location.getLatitude();
			accuracy = location.getAccuracy();
			altitude = location.getAltitude();
			speed = location.getSpeed();
			bearing = location.getBearing();
			satellites = location.getSatellites();
			time = location.getTime();

			AMapLocationQualityReport report = location.getLocationQualityReport();
			wifiAble = report.isWifiAble() ? 1 : 0;
			gpsStatus = report.getGPSStatus();
			networkType = report.getNetworkType();
			report = null;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getBearing() {
		return bearing;
	}

	public void setBearing(float bearing) {
		this.bearing = bearing;
	}

	public int getSatellites() {
		return satellites;
	}

	public void setSatellites(int satellites) {
		this.satellites = satellites;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getWifiAble() {
		return wifiAble;
	}

	public void setWifiAble(int wifiAble) {
		this.wifiAble = wifiAble;
	}

	public int getGpsStatus() {
		return gpsStatus;
	}

	public void setGpsStatus(int gpsStatus) {
		this.gpsStatus = gpsStatus;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

}
