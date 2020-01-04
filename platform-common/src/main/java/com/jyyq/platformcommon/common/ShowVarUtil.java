package com.jyyq.platformcommon.common;

import java.util.List;

public class ShowVarUtil {
	

	/**
	 * 方法开始
	 * @param method
	 */
	public static void start(String method) {
		System.out.println("==========="+method+"  start "+System.currentTimeMillis()+"===========");
	}
	
	/**
	 * 方法开始
	 * @param method
	 */
	public static void end(String method) {
		System.out.println("==========="+method+"  end "+System.currentTimeMillis()+"===========");
	}
	
	/**
	 * 方法开始
	 * @param method
	 */
	public static void end(String method, long time) {
		time = System.currentTimeMillis()-time;
		System.out.println("==========="+method+"  end "+ time +"===========");
	}
	
	public static void input_show(Object... objs) {
		System.out.print("传入参数：(");
		if (objs == null || objs.length == 0) {
			System.out.print("空)");
		} else {
			try {
				System.out.print(objs[0]);
			} catch (Exception e) {
			}
			for (int i = 1; i < objs.length; i++) {
				try {
					System.out.print(";" + objs[i].toString());
				} catch (Exception e) {
					System.out.print(";NULL");
				}
			}
		}

		System.out.println(")");

	}

	/**
	 * 输出参数
	 * @param objs
	 */
	public static void output_show(Object... objs) {
		System.out.print("输出参数：(");
		if (objs == null || objs.length == 0) {
			System.out.print("空)");
		} else {
			try {
				System.out.print(objs[0]);
			} catch (Exception e) {
			}
			for (int i = 1; i < objs.length; i++) {
				try {
					System.out.print(";" + objs[i].toString());
				} catch (Exception e) {
				}
			}
		}
		System.out.println(")");
	}

	/**
	 * 参数展示
	 * @param objs
	 */
	public static void debug(Object... objs) {
		if (objs == null || objs.length == 0) {
			System.out.print("空)");
		} else {
			try {
				System.out.print(objs[0]);
			} catch (Exception e) {
			}
			for (int i = 1; i < objs.length; i++) {
				try {
					System.out.print(";" + objs[i].toString());
				} catch (Exception e) {
				}
			}
		}
		System.out.println("");
	}

	public static void showJsonObject(String memo, Object object) {
		System.out.println("" + memo + ":" + FieldsUtil.getJSON(object));
	}
	
	@SuppressWarnings("rawtypes")
	public static void showJsonList(String memo,List list) {
		if (list == null || list.size() < 1){
			System.out.println("" + memo + ":" +"list is null");
		}else{
			System.out.println("" + memo + ":" +"list.size()="+list.size());
			for (Object record : list)
				System.out.println(FieldsUtil.getJSON(record));
		}
	}
	

	/**
	 * 显示一个数组
	 * @param memo
	 * @param arrays
	 */
	public static void showJsonArray(String memo, Object[] arrays){
		if (arrays == null || arrays.length < 1){
			System.out.println("" + memo + ":" +"array is null");
		}else {
			System.out.println("" + memo + ":" +"array.length="+arrays.length);
			String str = "[";
			for (int i=0;i<arrays.length;i++){
				if(i<arrays.length-1){
					str += arrays[i]+",";
				}else{
					str += arrays[i];
				}
			}
			str +="]";
			System.out.println(str);
		}
	}

}
