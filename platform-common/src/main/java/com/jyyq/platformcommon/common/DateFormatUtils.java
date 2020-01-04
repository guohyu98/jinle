package com.jyyq.platformcommon.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 工具类--时间格式转换
 * @author yu
 *
 */
public class DateFormatUtils {
	
	/**
	 * 根据long型时间返回当前小时
	 * @param milliseconds	自1970年1月1日0时起的毫秒数
	 * @return	传入毫秒数对应的小时
	 */
	public static int getCurrHour(long milliseconds) // 传入时间为毫秒级，返回14位格式串
	{
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(myTimeZone, myLocale);

		c.setTime(new Date(milliseconds));

		return c.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 根据long型时间返回当前分钟
	 * @param milliseconds	自1970年1月1日0时起的毫秒数
	 * @return	传入毫秒数对应的分钟
	 */
	public static int getCurrMinute(long milliseconds) // 传入时间为毫秒级，返回14位格式串
	{
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(myTimeZone, myLocale);

		c.setTime(new Date(milliseconds));

		return c.get(Calendar.MINUTE);

	}
	
	/**
	 * 根据long型时间返回当前秒
	 * @param milliseconds	自1970年1月1日0时起的毫秒数
	 * @return	传入毫秒数对应的秒
	 */
	public static int getCurrSecond(long milliseconds) // 传入时间为毫秒级，返回14位格式串
	{
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(myTimeZone, myLocale);

		c.setTime(new Date(milliseconds));

		return c.get(Calendar.SECOND);

	}
	
	/**
	 * long型日期转换为8位字符串(yyyyMMdd)
	 * @param milliseconds 	1493706834855
	 * @return	20170502143354
	 */
	public static String get8StrFromMillis(long milliseconds) // 传入时间为毫秒级，返回8位格式串
	{
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(myTimeZone, myLocale);

		c.setTime(new Date(milliseconds));

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);

		String retStr = String.format("%04d%02d%02d", year, month, day);
		return retStr;
	}
	
	/**
	 * long型日期转换为10位字符串(yyyy-MM-dd)
	 * @param milliseconds	1493706834855
	 * @return	2017-05-02
	 */
	public static String get10StrFromMillis(long milliseconds) // 传入时间为毫秒级，返回10位格式串
	{
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(myTimeZone, myLocale);

		c.setTime(new Date(milliseconds));

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);

		String retStr = String.format("%04d-%02d-%02d", year, month, day);
		return retStr;
	}
	
	/**
	 * long型日期转换为14位字符串(yyyyMMddhhmmss)
	 * @param milliseconds 	1493706834855
	 * @return	20170502143354
	 */
	public static String get14StrFromMillis(long milliseconds) // 传入时间为毫秒级，返回14位格式串
	{
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(myTimeZone, myLocale);

		c.setTime(new Date(milliseconds));

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);

		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);

		String retStr = String.format("%04d%02d%02d%02d%02d%02d", year, month,
				day, hour, minute, second);
		return retStr;
	}

	/**
	 * 14位字符串转换为long型日期(yyyyMMddhhmmss)
	 * @param time14Str		20170101103825
	 * @return		1483238305755
	 */
	public static long getMillisFrom14Str(String time14Str) // timeStr is like
															// '20020301235959'
	{
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar mc = Calendar.getInstance(myTimeZone, myLocale);
		
		int year = BaseTypeUtils.strToInt(time14Str.substring(0, 4));
		int month = BaseTypeUtils.strToInt(time14Str.substring(4, 6)) - 1;
		int day = BaseTypeUtils.strToInt(time14Str.substring(6, 8));
		int hour = BaseTypeUtils.strToInt(time14Str.substring(8, 10));
		int min = BaseTypeUtils.strToInt(time14Str.substring(10, 12));
		int second = BaseTypeUtils.strToInt(time14Str.substring(12, 14));
		
		mc.set(year, month, day, hour, min, second);
		
		return mc.getTimeInMillis();
	}
	
	/**
	 * long型日期转换为17位字符串(yyyyMMddhhmmss毫秒)
	 * @param milliseconds 	1493706834855
	 * @return	20170502143354855
	 */
	public static String get17StrFromMillis(long milliseconds) // 传入时间为毫秒级，返回17位格式串
	{
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(myTimeZone, myLocale);

		c.setTime(new Date(milliseconds));

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);

		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		int milSecond = c.get(Calendar.MILLISECOND);

		String retStr = String.format("%04d%02d%02d%02d%02d%02d%03d", year,
				month, day, hour, minute, second, milSecond);
		return retStr;

	}

	/**
	 * 17位字符串(yyyyMMddhhmmss毫秒)转换为long型日期
	 * @param time17Str 	2017-05-02 14:33:54
	 * @return	20170502143354855
	 */
	public static long getMillisFrom17Str(String time17Str) // timeStr is like
															// '20020301235959888'
	{
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar mc = Calendar.getInstance(myTimeZone, myLocale);

		int year = BaseTypeUtils.strToInt(time17Str.substring(0, 4));
		int month = BaseTypeUtils.strToInt(time17Str.substring(4, 6)) - 1;
		int day = BaseTypeUtils.strToInt(time17Str.substring(6, 8));
		int hour = BaseTypeUtils.strToInt(time17Str.substring(8, 10));
		int min = BaseTypeUtils.strToInt(time17Str.substring(10, 12));
		int second = BaseTypeUtils.strToInt(time17Str.substring(12, 14));

		mc.set(year, month, day, hour, min, second);

		return mc.getTimeInMillis();
	}
	
	/**
	 * long型日期转换为19位字符串(yyyy-MM-dd hh:mm:ss)
	 * @param milliseconds 	1493706834855
	 * @return	2017-05-02 14:33:54
	 */
	public static String get19StrFromMillis(long milliseconds) // 传入时间为毫秒级，返回19位格式串
	{
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(myTimeZone, myLocale);

		c.setTime(new Date(milliseconds));

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);

		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);

		String retStr = String.format("%04d-%02d-%02d %02d:%02d:%02d", year,
				month, day, hour, minute, second);
		return retStr;

	}

	/**
	 * 19位字符串(yyyy-MM-dd hh:mm:ss)转换为long型日期
	 * @param time19Str 	2017-05-02 14:33:54
	 * @return	1493706834913
	 */
	public static long getMillisFrom19Str(String time19Str) // timeStr is like
															// '2002-03-01
															// 23:59:59'
	{
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar mc = Calendar.getInstance(myTimeZone, myLocale);

		int year = BaseTypeUtils.strToInt(time19Str.substring(0, 4));
		int month = BaseTypeUtils.strToInt(time19Str.substring(5, 7)) - 1;
		int day = BaseTypeUtils.strToInt(time19Str.substring(8, 10));
		int hour = BaseTypeUtils.strToInt(time19Str.substring(11, 13));
		int min = BaseTypeUtils.strToInt(time19Str.substring(14, 16));
		int second = BaseTypeUtils.strToInt(time19Str.substring(17, 19));

		mc.set(year, month, day, hour, min, second);
		return mc.getTimeInMillis();
	}
	
	/**
	 * long型日期转换为字符串
	 * @param milliseconds 	1493706834855
	 * @return	5月2日14点33分
	 */
	public static String getShowMMDDHHmm(long milliseconds) // 传入时间为毫秒级，返回19位格式串
	{
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(myTimeZone, myLocale);
		c.setTime(new Date(milliseconds));

		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		String retStr = String.format("%d月%d日%d点%02d分", month, day, hour, minute);
		return retStr;
	}
	
	/**
	 * 获取指定日期往前几天的19位日期
	 * @param date
	 * @param day
	 * @return
	 */
	public static String getDateBefore(long date, int day) {  
		Date d = new Date(date);
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);  
        return get19StrFromMillis(now.getTimeInMillis());
    }   
	
	/**
	 * 获取指定日期往后几天的19位日期
	 * @param date
	 * @param day
	 * @return
	 */
	public static String getDateAfter(long date, int day){
		Date d = new Date(date);
		Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
        return get19StrFromMillis(now.getTimeInMillis());
	}
	
	/**
	 * 获取指定日期往后几月的19位日期
	 * @param date
	 * @param month
	 * @return
	 */
	public static String getDateAfterMonth(long date, int month){
		Date now = new Date(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.MONTH, month);
		return get19StrFromMillis(calendar.getTimeInMillis());
	}
	
	/**
	 * 获取指定日期往后几年的19位日期
	 * @param date
	 * @param year
	 * @return
	 */
	public static String getDateAfterYear(long date, int year){
		Date now = new Date(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.YEAR, year);
		return get19StrFromMillis(calendar.getTimeInMillis());
	}
	
	//获取指定日期+几年
	public static Date getDateAddYear(long time, int year){
		String afterYearStr = getDateAfterYear(time, year);
		Date afterYear = getDateFromStr(afterYearStr, "yyyy-MM-dd HH:mm:ss");
		String returnStr = getDateBefore(afterYear.getTime(), 1);
		return getDateFromStr(returnStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取8位日期的前一天(yyyyMMdd)
	 * @param date8Str 	20170101
	 * @return		20161231
	 */
	public static String getTheDayBeforeDate8Str(String date8Str) {
		final long oneDayMilSecs = 86400000L;

		String time14Str = date8Str + "120101";
		long now = getMillisFrom14Str(time14Str);

		return get8StrFromMillis(now - oneDayMilSecs);
	}

	/**
	 * 10位日期的前一天(yyyy-MM-dd)
	 * @param date10Str		(yyyy-MM-dd)
	 * @return		2016-12-31
	 */
	public static String getTheDayBeforeDate10Str(String date10Str) {
		final long oneDayMilSecs = 86400000L;

		String time19Str = date10Str + " 12:01:01";
		long now = getMillisFrom19Str(time19Str);

		return get10StrFromMillis(now - oneDayMilSecs);
	}

	/**
	 * 10位日期的后一天(yyyy-MM-dd)
	 * @param date10Str		(yyyy-MM-dd)
	 * @return		2017-01-02
	 */
	public static String getTheDayAfterDate10Str(String date10Str) {
		final long oneDayMilSecs = 86400000L;

		String time19Str = date10Str + " 12:01:01";
		long now = getMillisFrom19Str(time19Str);

		return get10StrFromMillis(now + oneDayMilSecs);
	}
	
	/**
	 * 本周的第一天 
	 * @return	yyyyMMdd
	 */
	public static String getFirstDayOfThisWeek() {
		long tCurr = System.currentTimeMillis();
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(myTimeZone, myLocale);

		c.setTime(new Date(tCurr));
		int a = c.get(Calendar.DAY_OF_WEEK);
		int dayofweek = (a + 5) % 7; // 1-7:日一二三四五六 ，转换后为 6,0~5

		long tOld = tCurr - 86400000l * dayofweek;
		c.setTime(new Date(tOld));
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);

		String ret = String.format("%04d%02d%02d", year, month, day);

		return ret;
	}
	
	/**
	 * 星期几	0星期日	1-6 星期一到星期六
	 * @param date8	
	 * @return
	 */
	public static int getDayOfWeek(String  date8){
		long time = getMillisFrom14Str(date8+"000001");
		
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(myTimeZone, myLocale);
		c.setTime(new Date(time));
		int   week   =   c.get(Calendar.DAY_OF_WEEK)-1;
		return week;
	}

	/**
	 * 获取当月第一天  格式yyyy-MM-dd
	 * @return
	 */
	public static String getFirstDayOfThisMonth() {
		long tCurr = System.currentTimeMillis();
		TimeZone myTimeZone = TimeZone.getTimeZone("GMT+8");
		Locale myLocale = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(myTimeZone, myLocale);

		c.setTime(new Date(tCurr));
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = 1;

		String ret = String.format("%04d-%02d-%02d", year, month, day);

		return ret;
	}
	
	
	public static String getShowInterval(long interval) {
		String retStr = "";

		final long dayMills = 86400000L;
		final long hourMills = 3600000L;
		final long minuteMills = 60000L;
		final long secondMills = 1000L;

		int days = (int) (interval / dayMills);
		int hours = (int) (interval % dayMills / hourMills);
		int minutes = (int) (interval % dayMills % hourMills / minuteMills);
		int seconds = (int) (interval % dayMills % hourMills % minuteMills / secondMills);
		int milSeconds = (int) (interval % dayMills % hourMills % minuteMills % secondMills);

		boolean dayFlag = false;
		boolean hourFlag = false;
		boolean minuteFlag = false;
		boolean secondFlag = false;
		boolean milSecondFlag = false;

		if (days > 0) {
			dayFlag = true;
		}
		if (hours > 0) {
			hourFlag = true;
		}
		if (minutes > 0) {
			minuteFlag = true;
		}
		if (seconds > 0) {
			secondFlag = true;
		}
		if (milSeconds > 0) {
			milSecondFlag = true;
		}

		if (dayFlag) {
			retStr += "" + days + "天";
		}
		if (dayFlag || hourFlag) {
			retStr += "" + hours + "小时";
		}
		if (dayFlag || hourFlag || minuteFlag) {
			retStr += "" + minutes + "分";
		}
		if (dayFlag || hourFlag || minuteFlag || secondFlag) {
			retStr += "" + seconds + "秒";
		}
		if (dayFlag || hourFlag || minuteFlag || secondFlag || milSecondFlag) {
			retStr += "" + milSeconds + "毫秒";
		}

		return retStr;

	}
	
	/**
	 * 转换时间格式为指定格式字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getShowStrFromDate(Date date, String pattern){
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}
	
	/**
	 * 将字符串转换为时间
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date getDateFromStr(String dateStr, String pattern){
		DateFormat df = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
