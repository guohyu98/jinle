package com.jyyq.platformcommon.common;

import java.util.Random;

/**
 * 随机数
 */
public class RandomDigtalUtil {
	 public static String getByLength(int length) {
		String base = "0123456789";//去除loi01
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 获取一定长度的随机字符串
	 *
	 * @param length 指定字符串长度
	 * @return 一定长度的字符串
	 */
	public static String getRandomStringByLength(int length) {
		String base = "abcdefghjkmnpqrstuvwxyz23456789";//去除loi01
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
}
