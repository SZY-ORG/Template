package com.shizy.template.components.common.ui.list;

import com.shizy.template.common.bean.City;
import com.shizy.template.common.bean.IListItem;
import com.shizy.template.common.utils.RxJavaUtil;
import com.shizy.template.components.common.api.ICommonService;
import com.shizy.template.net.ServiceFactory;
import com.shizy.template.net.response.ResponseData;
import com.shizy.template.net.response.ResponseObserver;

import java.lang.ref.WeakReference;
import java.util.List;

public class SelectListDataFactory {

	public interface Type {
		String OPEN_CITY = "open_city";
	}

	public interface DataListener {
		void setData(List<IListItem> data);
	}

	public static void loadData(String type, IListItem param, SelectListActivity activity) {
		switch (type) {
			case Type.OPEN_CITY:
				getOpenCityList(activity);
				break;
		}
	}

	private static void getOpenCityList(SelectListActivity activity) {
		final WeakReference<SelectListActivity> weakRef = new WeakReference<>(activity);
		ServiceFactory.getService(ICommonService.class)
				.getOpenCityList()
				.compose(RxJavaUtil.<ResponseData<List<City>>>mainSchedulers())
				.as(RxJavaUtil.<ResponseData<List<City>>>bindLifecycle(activity))
				.subscribe(new ResponseObserver<List<City>>() {
					@Override
					protected void onSuccess(ResponseData<List<City>> responseData) {
						List<City> list = responseData.getData();
						SelectListActivity callback = weakRef.get();
						if (callback != null) {
							callback.setData(list);
						}
					}
				});
	}

}
