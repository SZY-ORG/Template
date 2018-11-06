/**
 * Copyright (c) 2013-2014 YunZhongXiaoNiao Tech
 */
package com.shizy.template.common.alioss;

import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 阿里云存储，上传文件工具类
 */
public class AliOSSUtil {

	private AliOSSUtil() {
	}

	/**
	 * 图片上传
	 *
	 * @param uploadFilePath localFile
	 * @param folder         folder name eg:avatar
	 * @param callBack       callback
	 * @return OSSAsyncTask or null
	 */
	public static OSSAsyncTask uploadStaticImage(String uploadFilePath, String folder, UploadCallBack callBack) {
		String remoteFileName = getStaticFileName(folder);
		return AliOSSHelper.getStaticHelper().asyncUploadLocalFile(uploadFilePath, remoteFileName, callBack);
	}

	/**
	 * pic/{folder[图片类型目录名称]}/{yyyy-mm-dd}/{curmillseconds[时间戳(精确到毫秒)]}_[随机数字].[图片后缀]
	 *
	 * @param folder folder
	 * @return eg: pic/avatar/2016-08-03/1470209290786_0.5413217062596232.jpg
	 */
	private static String getStaticFileName(String folder) {
		StringBuffer sb = new StringBuffer("pic/");
		sb.append(folder).append('/');

		long currentTimeMillis = System.currentTimeMillis();
		new SimpleDateFormat("yyyy-MM-dd").format(new Date(currentTimeMillis), sb, new FieldPosition(0));

		sb.append('/').append(currentTimeMillis).append('_');

		sb.append(new Random().nextDouble()).append(".jpg");

		return sb.toString();
	}

}
