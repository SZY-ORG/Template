package com.shizy.template.service.task;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.shizy.template.common.bean.Location;
import com.shizy.template.common.db.DatabaseHelper;
import com.shizy.template.common.utils.ZipUtil;
import com.shizy.template.net.ServiceFactory;
import com.shizy.template.net.response.ResponseCode;
import com.shizy.template.net.response.ResponseData;
import com.shizy.template.service.api.IReportService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Response;

public class LocationReportTask extends TimerTask {

	private Context mContext;

	public LocationReportTask(Context context) {
		mContext = context.getApplicationContext();
	}

	@Override
	public void run() {
		DatabaseHelper helper = DatabaseHelper.getHelper(mContext);

		try {
			Dao<Location, Long> dao = helper.getLocationDao();
			List<Location> list = dao.queryForAll();
			if (list == null || list.size() == 0) {
				return;
			}
			Gson gson = new Gson();
			String content = ZipUtil.compressToString(gson.toJson(list));
			if (TextUtils.isEmpty(content)) {
				return;
			}

			Call<ResponseData<Void>> call = ServiceFactory.getService(IReportService.class).uploadLocation(content);
			Response<ResponseData<Void>> response = call.execute();
			ResponseData<Void> data = response.body();
			if (data != null && data.getCode() == ResponseCode.SUCCESS) {
				dao.delete(list);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			DatabaseHelper.releaseHelper();
		}
	}

}
