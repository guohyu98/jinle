package com.jyyq.platformcommon.common;

import java.io.ByteArrayInputStream;

/**
 * 工具类--基本类型转换
 * @author yu
 *
 */
public class BaseTypeUtils {

	/**
	 * Long-->long
	 * @param obj
	 * @return
	 */
	public static long long2base(Long obj){
		try {
			long ret = obj;
			return ret;
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * Integer-->int
	 * @param obj
	 * @return
	 */
	public static int integer2base(Integer obj){
		try {
			int ret = obj;
			return ret;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static byte[] shortToByteArray(short shortNum) {
		byte[] byteArray = new byte[2];
		for (int i = 0; i < 2; i++) {
			byteArray[i] = (byte) ((shortNum >> (8 - 8 * i)) & 0xFF);
		}
		return byteArray;
	}

	public static byte[] intToByteArray(int intNum) {
		byte[] byteArray = new byte[4];
		for (int i = 0; i < 4; i++) {
			byteArray[i] = (byte) ((intNum >> (24 - 8 * i)) & 0xFF);
		}
		return byteArray;
	}

	public static short byteArrayToShort(byte[] byteArray) {
		short value;
		value = (short) (((byteArray[0] << 8) & 0xFF00) + (byteArray[1] & 0xFF));
		return value;
	}

	public static short byteArrayToShort(int index, byte[] byteArray) {
		short value;
		byte[] b = new byte[2];
		for (int i = 0; i < b.length; i++) {
			b[i] = byteArray[index + i];
		}

		value = (short) (((b[0] << 8) & 0xFF00) + (b[1] & 0xFF));
		return value;
	}

	public static int byteArrayToInt(byte[] byteArray) {
		int value = 0;
		value = ((byteArray[0] << 24) & 0xFF000000)
				+ ((byteArray[1] << 16) & 0xFF0000)
				+ ((byteArray[2] << 8) & 0xFF00) + (byteArray[3] & 0xFF);
		return value;
	}

	public static long strToLong(String s) {
		long ret = 0;
		try {
			ret = Long.parseLong(s);
		} catch (Exception e) {
			ret = 0;
		}
		return ret;
	}

	public static int strToInt(String s) {
		int ret = 0;
		try {
			ret = Integer.parseInt(s);
		} catch (Exception e) {
			ret = 0;
		}
		return ret;
	}

	public static int strToInt(String s, int def) {
		int ret = 0;
		try {
			ret = Integer.parseInt(s);
		} catch (Exception e) {
			ret = def;
		}
		return ret;
	}

	public static float strToFloat(String s) {
		float ret = 0;
		try {
			ret = Float.parseFloat(s);
		} catch (Exception e) {
			ret = 0;
		}
		return ret;
	}

	public static float strToFloat(String s, float d) {
		float ret = 0;
		try {
			ret = Float.parseFloat(s);
		} catch (Exception e) {
			ret = d;
		}
		return ret;
	}

	public static double strToDouble(String s) {
		double ret = 0;
		try {
			ret = Double.parseDouble(s);
		} catch (Exception e) {
			ret = 0;
		}
		return ret;
	}

	public static double strToDouble(String s, double d) {
		double ret = 0;
		try {
			ret = Double.parseDouble(s);
		} catch (Exception e) {
			ret = d;
		}
		return ret;
	}

	public static String intToStr(int a) {
		String ret = String.format("%d", a);

		return ret;
	}

	public static String longToStr(long a) {
		String ret = String.format("%d", a);

		return ret;
	}

	public static String formatNullStr(String str) {
		if (str == null) {
			return "";
		}

		return str;

	}

	public static boolean isValidString(String str) {
		if (str != null && str.trim().length() > 0) {
			return true;
		}

		return false;
	}

	/**
	 * 获取 单字节长度 + String
	 *
	 * @param bais
	 * @return
	 */
	public static byte[] getByteStrBytes(ByteArrayInputStream bais) {
		final int defMaxLen = 127;
		try {
			int tempLen = bais.read();
			if (tempLen >= 0 && tempLen < defMaxLen) {
				byte[] appIdArr = new byte[tempLen];
				bais.read(appIdArr);
				return appIdArr;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取 单字节长度 + String
	 *
	 * @param bais
	 * @param maxLen
	 * @return
	 */
	public static byte[] getByteStrBytes(ByteArrayInputStream bais, int maxLen) {
		final int defMaxLen = 127;
		try {
			int tempLen = bais.read();
			if (tempLen >= 0 && tempLen < maxLen && tempLen < defMaxLen) {
				byte[] appIdArr = new byte[tempLen];
				bais.read(appIdArr);
				return appIdArr;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取 int长度 + String
	 *
	 * @param bais
	 * @return
	 */
	public static byte[] getIntStrBytes(ByteArrayInputStream bais) {
		final int defMaxLen = 1024 * 1024;
		try {
			byte[] tempIntArr = new byte[4];
			bais.read(tempIntArr);
			int tempLen = byteArrayToInt(tempIntArr);

			if (tempLen >= 0 && tempLen < defMaxLen) {
				byte[] appIdArr = new byte[tempLen];
				bais.read(appIdArr);
				return appIdArr;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取 int长度 + String
	 *
	 * @param bais
	 * @param maxLen
	 * @return
	 */
	public static byte[] getIntStrBytes(ByteArrayInputStream bais, int maxLen) {
		final int defMaxLen = 1024 * 1024;
		try {
			byte[] tempIntArr = new byte[4];
			bais.read(tempIntArr);
			int tempLen = byteArrayToInt(tempIntArr);

			if (tempLen >= 0 && tempLen < maxLen && tempLen < defMaxLen) {
				byte[] appIdArr = new byte[tempLen];
				bais.read(appIdArr);
				return appIdArr;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 比较金额字符串，精确到分
	 *
	 * @param m1
	 * @param m2
	 * @return
	 */
	public static boolean isSameMoney(String m1, String m2) {
		double d1 = strToDouble(m1);
		double d2 = strToDouble(m2);
		int i1 = (int) Math.round(d1 * 100.00);
		int i2 = (int) Math.round(d2 * 100.00);
		if (i1 == i2) {
			return true;
		} else {
			return false;
		}
	}
	
}
