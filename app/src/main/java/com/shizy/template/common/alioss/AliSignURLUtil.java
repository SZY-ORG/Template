/**
 * Copyright (c) 2013-2014 YunZhongXiaoNiao Tech
 */
package com.shizy.template.common.alioss;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.shizy.template.common.utils.LogUtil;

/**
 * description
 *
 * @author dahu
 * time 2018/11/5 16:18.
 */
public class AliSignURLUtil {

	private AliSignURLUtil() {
	}

	// 如果Bucket是私有的，需要签出有签名的URL，并指定过期时间

	/**
	 * 如果Bucket是私有的，需要签出有签名的URL，并指定过期时间
	 *
	 * @param oss
	 * @param bucketName
	 * @param fileName   上传到阿里云，存储时保存的文件名，可加斜线/，表示文件夹
	 */
	public static void presignConstrainedURL(OSS oss, String bucketName, String fileName) {
		try {
			// 获取签名url，过期时间为5分钟
			String url = oss.presignConstrainedObjectURL(bucketName, fileName, 5 * 60);
			LogUtil.d("signContrainedURL get url: " + url);
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	// 如果Bucket是公共读的，那么可以签出public的URL，不需要设置过期时间

	/**
	 * 如果Bucket是公共读的，那么可以签出public的URL，不需要设置过期时间
	 *
	 * @param oss
	 * @param bucketName
	 * @param fileName   上传到阿里云，存储时保存的文件名，可加斜线/，表示文件夹
	 * @return
	 */
	public static String presignPublicURL(OSS oss, String bucketName, String fileName) {
		String url = "";
		try {
			// 获取签名url，没有过期时间
			url = oss.presignPublicObjectURL(bucketName, fileName);
			LogUtil.d("signPublicURL get url: " + url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
}
