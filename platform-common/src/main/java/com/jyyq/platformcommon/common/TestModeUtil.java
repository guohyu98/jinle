package com.jyyq.platformcommon.common;

public class TestModeUtil {
	public static boolean isTestMode(){
		return isTestMode_1();
	}
	
	private  static  boolean isTestMode_1() {
		byte[] data = FileUtils.getFileBytes("d:/test/test.txt");
		if (data != null) {
			String s = new String(data);
			if (s.equalsIgnoreCase("1"))
				return true;
		}
		return false;
	}
}
