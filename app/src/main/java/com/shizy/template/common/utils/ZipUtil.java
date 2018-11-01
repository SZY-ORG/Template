package com.shizy.template.common.utils;

import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class ZipUtil {

	public static byte[] compress(String content) throws IOException {
		if (content == null) {
			return null;
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream(content.length());
		GZIPOutputStream gos = new GZIPOutputStream(baos);
		gos.write(content.getBytes());
		gos.close();

		byte[] data = baos.toByteArray();
		baos.close();

		return data;
	}

	public static String compressToString(String content) throws IOException {
		byte[] data = compress(content);
		if (data != null) {
			return Base64.encodeToString(compress(content), Base64.DEFAULT);
		}
		return null;
	}

}
