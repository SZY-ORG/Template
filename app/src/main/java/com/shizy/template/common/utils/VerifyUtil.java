package com.shizy.template.common.utils;

import android.text.TextUtils;

import com.shizy.template.R;

/**
 * 校验工具类
 */
public class VerifyUtil {

	// 从右侧第2位开始向左，共17位。计算公式(2^(i - 1)) % 11
	private static final int[] ID_NUMBER_WEIGHT = {
			2, 4, 8, 5, 10, 9, 7, 3, 6, 1, 2, 4, 8, 5, 10, 9, 7
	};

	private static final char[] ID_NUMBER_CHECK_CODE = {
			'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'
	};

	/**
	 * 检验姓名
	 *
	 * @param name
	 * @return
	 */
	public static boolean isName(String name) {
		if (TextUtils.isEmpty(name)) {
			return false;
		}
		int min = ResourcesUtil.getInteger(R.integer.name_len_min);
		int max = ResourcesUtil.getInteger(R.integer.name_len_max);
		int len = name.length();
		return len >= min && len <= max;
	}

	/**
	 * 是否为手机号
	 *
	 * @param number
	 * @return
	 */
	public static boolean isMobileNumber(String number) {
		if (TextUtils.isEmpty(number)) {
			return false;
		}
		return number.matches("^1\\d{10}$");
	}

	/**
	 * 是否为有效密码
	 *
	 * @param password
	 * @return
	 */
	public static boolean isValidPassword(String password) {
		if (TextUtils.isEmpty(password)) {
			return false;
		}
		int min = ResourcesUtil.getInteger(R.integer.password_len_min);
		int max = ResourcesUtil.getInteger(R.integer.password_len_max);
		int len = password.length();
		return len >= min && len <= max;
	}

	/**
	 * 是否为身份证号
	 *
	 * @param number
	 * @return
	 */
	public static boolean isIdNumber(String number) {
		if (TextUtils.isEmpty(number)) {
			return false;
		}
		if (number.matches("^\\d{17}(\\d|X|x)$")) {
			int sum = 0;
			for (int i = 1; i < number.length(); i++) {
				int value = Integer.parseInt(number.charAt(number.length() - 1 - i) + "");
				int weight = ID_NUMBER_WEIGHT[i - 1];
				sum += (value * weight);
			}

			char code = ID_NUMBER_CHECK_CODE[sum % 11];
			char last = number.toUpperCase().charAt(number.length() - 1);
			return code == last;
		}
		return false;
	}

}
