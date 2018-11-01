package com.shizy.template.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.shizy.template.service.task.LocationReportTask;

import java.util.Timer;

/**
 * 后台上报报务
 */
public class ReportService extends Service {

	private static final long DELAY = 5000;
	private static final long PERIOD_LOCATION_REPORT = 5 * 60 * 1000;

	private Timer mTimer;

	@Override
	public void onCreate() {
		super.onCreate();
		mTimer = new Timer();
		mTimer.schedule(new LocationReportTask(this), DELAY, PERIOD_LOCATION_REPORT);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mTimer.cancel();
		mTimer = null;
	}
}
