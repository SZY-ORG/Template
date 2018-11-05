package com.shizy.template.app;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

public class App extends TinkerApplication {

	public App() {
		//tinkerFlags 表示Tinker支持的类型 dex only、library only or all suuport，default: TINKER_ENABLE_ALL
		//delegateClassName Application代理类 自定义的BaseApplicationLike
		//loaderClassName Tinker的加载器，使用默认TinkerLoader即可
		//tinkerLoadVerifyFlag 加载dex或者lib是否验证md5，默认为false

		// TODO: 2018/10/24 delegateClassName需要修改成正式包名
		super(ShareConstants.TINKER_ENABLE_ALL, "com.shizy.template.app.ApplicationLike",
				"com.tencent.tinker.loader.TinkerLoader", false);
	}


}
