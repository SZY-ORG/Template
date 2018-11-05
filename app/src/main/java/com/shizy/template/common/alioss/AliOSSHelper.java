package com.shizy.template.common.alioss;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.HttpUtil;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.shizy.template.common.utils.AppUtil;
import com.shizy.template.common.utils.LogUtil;
import com.shizy.template.common.utils.UIUtil;

/**
 * description 以bucket_name区分helper instance
 *
 * @author dahu
 * time 2018/11/5 16:18.
 */
public class AliOSSHelper {

	public static final String TAG = "AliOSSHelper";

	// OSS 配置常量
	private static final String ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";

	public static final String YN_TMS_ACCESSKEY_ID = "ym16wBKdwMdgbNzS";
	public static final String YN_TMS_ACCESSKEY_SECRET = "UvAyQMQvzSBAVA1XxSbDiQ6ajElvFA";
	private static final String YN_TMS_BUCKET_NAME = "yn-tms";

	// 线上环境
	private static final String YN_STATIC_ACCESSKEY_ID = "uVZW7mfROpt9nn8p";
	private static final String YN_STATIC_ACCESSKEY_SECRET = "aoFfxnEnNpZ5V7EEiJp3cs6R8IZJ3q";
	private static final String YN_STATIC_BUCKET_NAME = "yn-static";

	// 测试/开发环境
	private static final String YN_STATIC_TEST_ACCESSKEY_ID = "deelFK4KM2CmL0Xq";
	private static final String YN_STATIC_TEST_ACCESSKEY_SECRET = "m4HsmAG2Vbn5OZY0badpdD4MKyDB2e";
	private static final String YN_STATIC_TEST_BUCKET_NAME = "test-yn-static";

	private static final ClientConfiguration DEFAULT_CONFIGURATION = new ClientConfiguration();

	private static AliOSSHelper ynTMSInstance;
	private static AliOSSHelper ynStaticInstance;

	static {
		// 打开OSSLog
		if (AppUtil.isProductEnv()) {
			OSSLog.disableLog();
		} else {
			OSSLog.enableLog();
		}

		// 配置 default client configuration
		ClientConfiguration conf = DEFAULT_CONFIGURATION;
		// 连接超时，默认15秒
		conf.setConnectionTimeout(15 * 1000);

		// socket超时，默认15秒
		conf.setSocketTimeout(15 * 1000);

		// 最大并发请求数，默认5个
		conf.setMaxConcurrentRequest(5);

		// 失败后最大重试次数，默认2次
		conf.setMaxErrorRetry(2);
	}

	public static AliOSSHelper getTMSHelper() {
		if (ynTMSInstance == null) {

			String id = YN_TMS_ACCESSKEY_ID;
			String secret = YN_TMS_ACCESSKEY_SECRET;
			OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(id, secret);

			ClientConfiguration configuration = DEFAULT_CONFIGURATION;
			Context appContext = AppUtil.getContext();
			OSS oss = new OSSClient(appContext, ENDPOINT, credentialProvider, configuration);

			String bucketName = YN_TMS_BUCKET_NAME;

			ynTMSInstance = new AliOSSHelper(oss, bucketName);
		}

		return ynTMSInstance;
	}

	public static AliOSSHelper getStaticHelper() {

		if (ynStaticInstance == null) {

			String id = YN_STATIC_ACCESSKEY_ID;
			String secret = YN_STATIC_ACCESSKEY_SECRET;
			OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(id, secret);

			ClientConfiguration configuration = DEFAULT_CONFIGURATION;
			Context appContext = AppUtil.getContext();
			OSS oss = new OSSClient(appContext, ENDPOINT, credentialProvider, configuration);

			String bucketName = YN_STATIC_BUCKET_NAME;

			ynStaticInstance = new AliOSSHelper(oss, bucketName) {
				@Override
				protected String getUrl(String objectKey) {

					String hostUrl;

					if (isDebugMode()) {
						hostUrl = "http://test-yn-static.img-cn-beijing.aliyuncs.com/";
					} else {
						hostUrl = "http://pic.yunniao.cn/";
					}

					return hostUrl + HttpUtil.urlEncode(objectKey, "utf-8");
				}
			};

		}

		// update debug mode
		boolean isProductEnv = AppUtil.isProductEnv();
		if (ynStaticInstance.isDebugMode() == isProductEnv) {

			String bucketName;
			String keyId;
			String keySecret;

			if (isProductEnv) {

				bucketName = YN_STATIC_BUCKET_NAME;
				keyId = YN_STATIC_ACCESSKEY_ID;
				keySecret = YN_STATIC_ACCESSKEY_SECRET;

			} else {
				bucketName = YN_STATIC_TEST_BUCKET_NAME;
				keyId = YN_STATIC_TEST_ACCESSKEY_ID;
				keySecret = YN_STATIC_TEST_ACCESSKEY_SECRET;
			}

			ynStaticInstance.setBucketName(bucketName);
			OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(keyId, keySecret);
			ynStaticInstance.updateOSSCredentialProvider(credentialProvider, !isProductEnv);

		}

		return ynStaticInstance;
	}


	private OSS ossClient;
	private String bucketName;

	private boolean isDebugMode = false;

	private AliOSSHelper(OSS ossClient, String bucketName) {
		if (ossClient == null) {
			throw new NullPointerException("oss client is null");
		}

		if (TextUtils.isEmpty(bucketName)) {
			throw new NullPointerException("bucketName is invalid: bucketName=" + bucketName);
		}

		this.ossClient = ossClient;
		this.bucketName = bucketName;
	}

	void setBucketName(String bucketName) {
		if (TextUtils.isEmpty(bucketName)) {
			throw new NullPointerException("bucketName is invalid: bucketName=" + bucketName);
		}

		this.bucketName = bucketName;
	}

	boolean isDebugMode() {
		return isDebugMode;
	}

	void updateOSSCredentialProvider(OSSCredentialProvider ossCredentialProvider, boolean isDebugMode) {
		ossClient.updateCredentialProvider(ossCredentialProvider);
		this.isDebugMode = isDebugMode;
	}

	public OSSAsyncTask asyncUploadLocalFile(String localFilePath, String remoteFilePath, UploadCallBack callBack) {
		try {
			return asyncPutObjectFromLocalFile(localFilePath, remoteFilePath, callBack);
		} catch (Exception e) {
			LogUtil.e(TAG, e);
			return null;
		}
	}

	private void runOnUIThread(Runnable runnable) {
		UIUtil.runOnUIThread(runnable);
	}

	// 从本地文件上传，使用非阻塞的异步接口
	private OSSAsyncTask asyncPutObjectFromLocalFile(String localFilePath, final String objectKey, final UploadCallBack callBack) {

		OSS oss = this.ossClient;
		String bucketName = this.bucketName;

		// 构造上传请求
		PutObjectRequest put = new PutObjectRequest(bucketName, objectKey, localFilePath);

		// 异步上传时可以设置进度回调
		put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
			@Override
			public void onProgress(PutObjectRequest request, final long currentSize, final long totalSize) {
				LogUtil.d("PutObject currentSize: " + currentSize + " totalSize: " + totalSize);
				if (callBack != null) {
					runOnUIThread(new Runnable() {
						@Override
						public void run() {
							callBack.onProgress(currentSize, totalSize);
						}
					});
				}
			}
		});

		OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
			@Override
			public void onSuccess(PutObjectRequest request, PutObjectResult result) {
				LogUtil.d("PutObject = " + "UploadSuccess");

				if (callBack != null) {
					runOnUIThread(new Runnable() {
						@Override
						public void run() {
							callBack.onSuccess(getUrl(objectKey));
						}
					});
				}
			}

			@Override
			public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {

				StringBuilder stringBuffer = new StringBuilder();

				// 请求异常
				if (clientExcepion != null) {
					// 本地异常如网络异常等
					clientExcepion.printStackTrace();
					if (null != clientExcepion.getMessage()) {
						stringBuffer.append("c:");
						stringBuffer.append(clientExcepion.getMessage());
					}

				}
				if (serviceException != null) {
					// 服务异常
					LogUtil.e("ErrorCode = " + serviceException.getErrorCode());
					LogUtil.e("RequestId = " + serviceException.getRequestId());
					LogUtil.e("HostId = " + serviceException.getHostId());
					LogUtil.e("RawMessage = " + serviceException.getRawMessage());
					if (null != serviceException.getMessage()) {
					}

					stringBuffer.append("s:");
					stringBuffer.append(serviceException.toString());
				}

				if (callBack != null) {

					final String exStr = stringBuffer.toString();
					runOnUIThread(new Runnable() {
						@Override
						public void run() {
							callBack.onFailure(exStr);
						}
					});
				}
			}
		});

		return task;
	}

	protected String getUrl(String objectKey) {
		return AliSignURLUtil.presignPublicURL(ossClient, bucketName, objectKey);
	}

}
