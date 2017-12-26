package com.hengyuan.hicash.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 日期类操作工具类
 * @author Cary.Liu
 * @create date 2014-07-16
 * 
 */
public class DateUtils {
	
	
	/**
	 * 获取yyMMdd格式的日期
	 * @param date
	 * @return
	 */
	public static String getDateStrByFlow(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 获取yyyyMMdd格式的日期
	 * @param date
	 * @return
	 */
	public static String getDateStrByFlow2(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 获取两个时间之间相差的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException 
	 */
	public static long getDaysBetween(String startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return 0L;
		}
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ;
		long time1=1l;
		try {
			time1 = sdf.parse(startDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        long time2 = endDate.getTime();
        long diff = time2 - time1;
        long day = diff / (24 * 60 * 60 * 1000);

        //return ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        return day;
    }
	/**
	 * 获取两个时间之间相差的小时数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException 
	 */
	public static long getHoursBetween(String startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return 0L;
		}
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ;
		long time1=1l;
		try {
			time1 = sdf.parse(startDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        long time2 = endDate.getTime();
        long diff = time2 - time1;
        long day = diff / (60 * 60 * 1000);

        //return ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        return day;
    }
	public static String getDateStr(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 将对应字符串转化为日期对象
	 * @param dateStr
	 * @return
	 */
	public static String getDateByStr(String dateStr){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		String timeStr = null;
		try {
			date = sdf.parse(dateStr);
			timeStr = sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timeStr;
	}
	
	/**
	 * 获取当前时间往后n分钟的时间
	 * @param date
	 * @param afterMunute 
	 * @return
	 */
	public static String getAfterMinuteTime(String afterMunute){
		
		Calendar can = Calendar.getInstance();
		
		can.add(Calendar.MINUTE, Integer.parseInt(afterMunute));
		Date afterTime = can.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return format.format(afterTime);
	}
	
	/**
	 * 将对应字符串转化为日期对象
	 * @param dateStr
	 * @return
	 */
	public static String getTimeStamp(String dateStr){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		String timeStr = null;
		try {
			date = sdf.parse(dateStr);
			timeStr = new SimpleDateFormat("yyyyMMdd").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timeStr;
	}
	
	public static String formatDuring(long mss) {  
	    long days = mss / (1000 * 60 * 60 * 24);  
	    long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);  
	    long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);  
	    long seconds = (mss % (1000 * 60)) / 1000;  
	    return days + " 天 " + hours + "小时 " + minutes + "分钟 "  
	            + seconds + "秒";  
	} 
	
	/**
	 * 将对应字符串转化为日期对象
	 * @param dateStr
	 * @return
	 */
	public static String getDateChStr(Date date){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		
		return sdf.format(date);
	}
	
	/**
	 * 是否超过了当前时间
	 * @param currTime
	 * @return
	 * @throws ParseException 
	 */
	public static boolean outCurrentTimeFlag(String validateTime) throws ParseException{
		
		Calendar nowTime = Calendar.getInstance();
		Calendar valTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		valTime.setTime(sdf.parse(validateTime));
		if (nowTime.before(valTime)) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 是否超过了当前日期
	 * @param currTime
	 * @return
	 * @throws ParseException 
	 */
	public static boolean outCurrentDateFlag(String validateTime) throws ParseException{
		
		Calendar nowTime = Calendar.getInstance();
		Calendar valTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		valTime.setTime(sdf.parse(validateTime));
		if (nowTime.before(valTime)) {
			return true;
		}else{
			return false;
		}
	}
	
	public static String timeFormatToDateStr(String str){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = "";
		
		try {
			
			Date date = sdf.parse(str);
			dateStr = sdf.format(date);
			
		} catch (ParseException e) {
			System.out.println("时间转换异常[" + str + "]");
			e.printStackTrace();
		}
		
		return dateStr;
	}
	
	public static String getDateToStr(Date date){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public static void main(String[] args) {
		
		
		try {
			
		
//		System.out.println(getDateByStr("2015-03-25 14:32:16"));
		
//		System.out.println(formatDuring(223200));
		
//		Date date = new Date();
//		long timeStamp = date.getTime();
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//		System.out.println(timeStamp);
//		System.out.println(outCurrentTimeFlag("2015-12-28 21:57:01"));
//		System.out.println(timeFormatToDateStr("2015-12-28 21:57:01"));
		
//		System.out.println(getDateToStr(new Date()).equals("2016-01-04"));
//		System.out.println(getDaysBetween("2015-12-11 21:57:01", new Date()));
		System.out.println(getDateStrByFlow2(new Date()));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 得到当前时间天
	 * @param dateStr
	 * @return
	 */
	public static String getCurrentDay(){
		
		Date dateStr = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		return sdf.format(dateStr);
	}
	/**
	 *
	 * 把当前时间解析为字符串
	 * @return
	 */
	public static String getNowTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());
	}
	
	
	/**
	 * 获取传入指定月数后的日期
	 * @param monthIndex 指定月份数
	 * @param flag 是否指定日期
	 * @param dayIndex 指定月份中的天数
	 * @return
	 */
	public static String getNextMonth(int monthIndex,boolean flag,int dayIndex){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(nowDate);// 获得当前时间

		if(flag){
			// 日期不变，把时间设定为00：00：00
//			cal.set(Calendar.HOUR_OF_DAY, 0);
//			cal.set(Calendar.MINUTE, 00);
//			cal.set(Calendar.SECOND, 00);
			cal.set(Calendar.DAY_OF_MONTH, dayIndex);
		}
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + monthIndex);
		
		return sdf.format(cal.getTime());
	}
	

	public static String getAfterMonth(String date,int month){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now;
		try {
			now = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			System.out.println(sdf.format(calendar.getTime()));
			calendar.add(Calendar.MONTH, month);
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 public static int compare_date(String DATE1, String DATE2) {
	       
	       
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");//采用simpleDateFormat处理日期(yyyy-mm-dd hh:mm:ss.0)
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > dt2.getTime()) {//比较long型的毫秒数
	                return 1;
	            } else if (dt1.getTime() < dt2.getTime()) {
	                return -1;
	            } else {
	                return 0;
	            }
	        } catch (ParseException exception) {
	            exception.printStackTrace();
	        }
	        return 0;
	    }
	
	
	
}
