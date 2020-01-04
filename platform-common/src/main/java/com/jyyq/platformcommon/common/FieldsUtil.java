package com.jyyq.platformcommon.common;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FieldsUtil {
	public static void initObject(Object o) {

		try {
			java.lang.reflect.Field[] fields = o.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				if (fields[i].getName().equals("serialVersionUID") == false) {
					fields[i].setAccessible(true);
					Class<?> typeClass = fields[i].getType();
					if (typeClass == String.class) {
						fields[i].set(o, "");
					} else if (typeClass == Integer.class) {
						fields[i].set(o, 0);
					} else if (typeClass == Long.class) {
						fields[i].set(o, 0L);
					} else if (typeClass == Double.class) {
						fields[i].set(o, 0);
					} else if (typeClass == Boolean.class) {
						fields[i].set(o, false);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getAllFieldName(Object o) {
		ArrayList<String> alRet = new ArrayList<String>();
		java.lang.reflect.Field[] fields = o.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equals("serialVersionUID") == false) {
				alRet.add(fields[i].getName());
			}
		}

		return alRet;
	}

	public static JSONObject getJSON(Object obj) {
		if (obj == null)
			return null;

		JSONObject ret = new JSONObject();
		try {
			java.lang.reflect.Field[] fields = obj.getClass()
					.getDeclaredFields();
			if (fields != null) {
				for (int i = 0; i < fields.length; i++) {
					if (fields[i].getName().equals("serialVersionUID") == false) {
						fields[i].setAccessible(true);
						ret.put(fields[i].getName(), fields[i].get(obj));
					}
				}
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void getAllDesc0(Object o, Class<?> c) {
		StringBuilder sb = new StringBuilder();
		sb.append("public void getAllDesc(){");
		sb.append("ArrayList<BasicColumn> al = new ArrayList<BasicColumn>();\n");
		try {
			java.lang.reflect.Field[] fields = c.getDeclaredFields();
			if (fields != null) {
				for (int i = 0; i < fields.length; i++) {
					if (fields[i].getName().equals("serialVersionUID") == false)
						sb.append("al.add(new BasicColumn(\""
								+ fields[i].getName() + "\", \"\", false));\n");
				}
			}

			sb.append("JSONArray array = new JSONArray();\n");
			sb.append("for(BasicColumn record:al)\n");
			sb.append("array.put(record.getJsonobject());\n");
			sb.append("System.out.println(array.toString());");
			sb.append("}");

			// System.out.println(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void showJsonObject(Object object) {
		System.out.println(FieldsUtil.getJSON(object));
	}

	@SuppressWarnings("rawtypes")
	public static void showJsonList(List list) {
		if (list == null || list.size() < 1)
			System.out.println("list is null");
		System.out.println("list.size()="+list.size());
		for (Object record : list)
			System.out.println(FieldsUtil.getJSON(record));
	}
}
