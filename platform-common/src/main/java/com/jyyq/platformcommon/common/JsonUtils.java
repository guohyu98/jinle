package com.jyyq.platformcommon.common;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {
	
	public static int safeGetIntValue(JSONObject obj, String key) {
        int ret = 0;
        try {
            ret = obj.getInt(key);
        } catch (Exception e) {
            ret = 0;
        }
        return ret;
    }

	public static long safeGetLongValue(JSONObject obj, String key) {
        long ret = 0;
        try {
            ret = obj.getInt(key);
        } catch (Exception e) {
            ret = 0;
        }
        return ret;
    }
	
    public static String safeGetStrValue(JSONObject obj, String key) {
        String ret = "";
        try {
            ret = obj.getString(key);
        } catch (Exception e) {
            ret = "";
        }
        return ret;
    }

    public static JSONObject safeGetObject(JSONObject obj, String key) {
        JSONObject ret = null;
        try {
            ret = obj.getJSONObject(key);
        } catch (Exception e) {
            ret = null;
        }
        return ret;
    }
    
    public static JSONArray safeGetArray(JSONObject obj, String key) {
        JSONArray ret = null;
        try {
            ret = obj.getJSONArray(key);
        } catch (Exception e) {
            System.out.println("----- 没有key:" + key);
            ret = null;
        }
        return ret;
    }

    /**
     * 判断jsonStr是否能形成有效jsonObject
     *
     * @param jsonStr
     * @return
     */
    public static boolean isValidJsonObjStr(String jsonStr) {
        if (jsonStr == null || jsonStr.length() < 1) {
            return false;
        }

        try {
            String str = jsonStr.trim();
            char s = str.charAt(0);
            char e = str.charAt(str.length() - 1);
            if (s == '{' && e == '}') {
                return true;
            }

//			System.out.println("errJson1:" + str);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean safeGetBooleanValue(JSONObject obj, String key) {
        boolean ret = false;
        try {
            ret = obj.getBoolean(key);
        } catch (Exception e) {
            ret = false;
        }
        return ret;
    }
    
    public static double safeGetDoubleValue(JSONObject obj, String key) {
        double ret = 0.0;
        try {
            ret = obj.getDouble(key);
        } catch (Exception e) {
            ret = 0.0;
        }
        return ret;
    }

}
