/**
 * Copyright (c) 2013-2014 YunZhongXiaoNiao Tech
 */
package com.shizy.template.common.alioss;

/**
 * description
 *
 * @author dahu
 * time 2018/11/5 16:17.
 */
public interface UploadCallBack {

	void onProgress(long bytesWritten, long totalSize);

	void onSuccess(String url);

	void onFailure(String exception);

}
