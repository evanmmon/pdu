package com.chuangkou.pdu.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * MD5加密工具类
 */
public class MD5Utils {

	private MD5Utils() {

	}

	/**
	 * 获取字符串的 MD5
	 */
	public static String encode(String str) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes("UTF-8"));
			byte messageDigest[] = md5.digest();
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", b));
			}

			return hexString.toString().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取文件的 MD5
	 */
	public static String encode(File file) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			FileInputStream inputStream = new FileInputStream(file);
			DigestInputStream digestInputStream = new DigestInputStream(inputStream, messageDigest);
			//必须把文件读取完毕才能拿到md5
			byte[] buffer = new byte[4096];
			while (digestInputStream.read(buffer) > -1) {
			}
			MessageDigest digest = digestInputStream.getMessageDigest();
			digestInputStream.close();
			byte[] md5 = digest.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : md5) {
				sb.append(String.format("%02X", b));
			}
			return sb.toString().toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getMessageDigest(byte[] buffer) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(buffer);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
