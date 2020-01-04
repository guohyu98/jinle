package com.jyyq.platformcommon.common;

import org.apache.log4j.Logger;

import java.util.List;

public class LogVarUtils {
	
	private static Logger logger = Logger.getLogger(LogVarUtils.class);
	
	/**
	 * 方法开始
	 * @param method
	 */
	public static void start(String method) {
		logger.debug("==========="+method+"  start "+System.currentTimeMillis()+"===========");
	}
	
	/**
	 * 方法开始
	 * @param method
	 */
	public static void end(String method) {
		logger.debug("==========="+method+"  end "+System.currentTimeMillis()+"===========");
	}
	
	/**
	 * 方法开始
	 * @param method
	 */
	public static void end(String method, long time) {
		time = System.currentTimeMillis()-time;
		logger.debug("==========="+method+"  end "+ time +"===========");
	}
	
	/**
	 * 传入参数
	 * @param objs
	 */
	public static void input_show(Object... objs) {
		StringBuffer sb = new StringBuffer();
		sb.append("传入参数：(");
		if (objs == null || objs.length == 0) {
			sb.append("空)");
		} else {
			try {
				sb.append(objs[0]);
			} catch (Exception e) {
			}
			for (int i = 1; i < objs.length; i++) {
				try {
					sb.append(";" + objs[i].toString());
				} catch (Exception e) {
					sb.append(";NULL");
				}
			}
		}

		sb.append(")");
		logger.debug(sb.toString());
	}

	/**
	 * 传出参数
	 * @param objs
	 */
	public static void output_show(Object... objs) {
		StringBuffer sb = new StringBuffer();
		sb.append("输出参数：(");
		if (objs == null || objs.length == 0) {
			sb.append("空)");
		} else {
			try {
				sb.append(objs[0]);
			} catch (Exception e) {
			}
			for (int i = 1; i < objs.length; i++) {
				try {
					sb.append(";" + objs[i].toString());
				} catch (Exception e) {
				}
			}
		}
		sb.append(")");
		logger.debug(sb.toString());
	}
	
	/**
	 * 传出参数
	 * @param objs
	 */
	public static void debug(Object... objs) {
		StringBuffer sb = new StringBuffer();
		if (objs == null || objs.length == 0) {
//			sb.append("空");
		} else {
			try {
				sb.append(objs[0]);
			} catch (Exception e) {
			}
			for (int i = 1; i < objs.length; i++) {
				try {
					sb.append(";" + objs[i].toString());
				} catch (Exception e) {
				}
			}
		}
		logger.debug(sb.toString());
	}
	
	public static void info(Object... objs) {
		StringBuffer sb = new StringBuffer();
		if (objs == null || objs.length == 0) {
//			sb.append("空");
		} else {
			try {
				sb.append(objs[0]);
			} catch (Exception e) {
			}
			for (int i = 1; i < objs.length; i++) {
				try {
					sb.append(";" + objs[i].toString());
				} catch (Exception e) {
				}
			}
		}
		logger.info(sb.toString());
	}

	/**
	 * 显示一个对象的所有属性
	 * @param memo	显示名称
	 * @param object	对象
	 */
	public static void showJsonObject(String memo, Object object) {
		logger.debug("" + memo + ":" + FieldsUtil.getJSON(object));
	}
	
	/**
	 * 显示一个集合
	 * @param memo
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	public static void showJsonList(String memo,List list) {
		if (list == null || list.size() < 1){
			logger.debug("" + memo + ":" +"list is null");
		}else {
			logger.debug("" + memo + ":" +"list.size()="+list.size());
			for (Object record : list)
				logger.debug(FieldsUtil.getJSON(record));
		}
	}
	
	/**
	 * 显示一个数组
	 * @param memo
	 * @param arrays
	 */
	public static void showJsonArray(String memo, Object[] arrays){
		if (arrays == null || arrays.length < 1){
			logger.debug("" + memo + ":" +"array is null");
		}else {
			logger.debug("" + memo + ":" +"array.length="+arrays.length);
			String str = "[";
			for (int i=0;i<arrays.length;i++){
				if(i<arrays.length-1){
					str += arrays[i]+",";
				}else{
					str += arrays[i];
				}
			}
			str +="]";
			logger.debug(str);
		}
	}
	
	public static void error(Object object){
		logger.error(object);
	}
	
}
