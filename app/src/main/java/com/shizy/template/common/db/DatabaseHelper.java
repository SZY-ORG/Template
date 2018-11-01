package com.shizy.template.common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.shizy.template.common.bean.Location;
import com.shizy.template.common.utils.LogUtil;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "database.db";
	private static final int DATABASE_VERSION = 1;

	private Dao<Location, Long> mLocationDao = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Location.class);
		} catch (SQLException e) {
			LogUtil.e("Can't create database", e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, Location.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			LogUtil.e("Can't drop databases", e);
		}
	}

	public Dao<Location, Long> getLocationDao() throws SQLException {
		if (mLocationDao == null) {
			mLocationDao = getDao(Location.class);
		}
		return mLocationDao;
	}

	@Override
	public void close() {
		super.close();
		mLocationDao = null;
	}

	public static DatabaseHelper getHelper(Context context) {
		return OpenHelperManager.getHelper(context, DatabaseHelper.class);
	}

	public static void releaseHelper() {
		OpenHelperManager.releaseHelper();
	}

}
