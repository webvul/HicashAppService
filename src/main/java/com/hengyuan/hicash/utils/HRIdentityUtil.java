package com.hengyuan.hicash.utils;


import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 通过身份证号码计算性别，出生日期
 * by wangliang
 */
public class HRIdentityUtil {
	
	public static String getSex(String id) {
		String sex = id.substring(16, 17);
		if (Integer.parseInt(sex) % 2 == 0) {
			sex = "F";//女
		} else {
			sex = "M";//男

		}
		return sex;
	}
	
	/**
	 * 根据身份证号获取出生年月日
	 * @param id
	 * @return
	 */
	public static String getbirth (String id) {
		String birth = id.substring(6, 10)+"-"+id.substring(10, 12)+"-"+id.substring(12,14);
		
		return birth;
	}
	
	/**
	 * 根据身份证号获取生日
	 * @param id
	 * @return
	 */
	public static String getbirthDay (String id) {
		String birth = id.substring(10, 12)+"-"+id.substring(12,14);
		
		return birth;
	}
	
	/**
	 * 根据身份证号获取生日 年月日
	 * @param id
	 * @return
	 */
	public static String getyearbirthDay (String id) {
		String birth =id.substring(6, 10)+ id.substring(10, 12)+id.substring(12,14);
		
		return birth;
	}
	

	/**
	 * 根据申请人的出生日期计算年龄
	 * 
	 * @param birthday
	 * @return
	 */
	public static int getAppUseAge(String identityNo) {
		
		int age = 0;
		if (identityNo != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int now_year = calendar.get(Calendar.YEAR);
			int now_month = calendar.get(Calendar.MONTH) + 1;
			int now_day = calendar.get(Calendar.DAY_OF_MONTH);

			int idenno = Integer.parseInt(identityNo.substring(6, 10));
			int appmonth = Integer.parseInt(identityNo.substring(10, 12));
			int identityDay = Integer.parseInt(identityNo.substring(12, 14));
			age = now_year - idenno;
              System.out.println("age<18:"+age);
			if (age < 18) {
				return age;
			}

			if (now_month < appmonth) {
				age--;
	
				if (age < 18) {
					return age;
				}
				return age;
			} else if (now_month > appmonth) {
				return age;
			}

			age = now_year - idenno;
			if (now_day <= identityDay) {
				age--;
				if (age < 18) {
					return age;
				}
			}

			return age;
		}

		return age;
	}


	/**
	 * 根据申请人的出生年计算年龄
	 * 
	 * @param birthday
	 * @return
	 */
	public static int getUseAge(String identityNo) {

		int age = 0;
		if (identityNo != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int now_year = calendar.get(Calendar.YEAR);
			int idenno = Integer.parseInt(identityNo.substring(6, 10));
			age = now_year - idenno;
		}

		return age;
	}
	

	
	
	
	
	


	public static int checkAge(String identityNo) {

		String  idenno = identityNo.substring(6, 14);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Time birthday=null;
		try {
			birthday = new Time(sdf.parse(idenno).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		c1.setTime(birthday);
		c2.setTime(new Date());
		long time =Math.abs(c1.getTimeInMillis()-c2.getTimeInMillis())/(1000*60*60*24);
		int i=(int)time/365;
		return i;
	}

	
	
	public static void main(String[] args) {
     
		
		System.out.println(getAppUseAge("411224201608011416"));
	}
	

	/**
	 * 返回对照编码
	 * @param hiCode
	 * @return
	 */
	public static String getHyIndustryCode(String hiCode){
		
		 String hyIndustryCode="";
	
		if("SMTE".equals(hiCode)){
			hyIndustryCode="_shuma";
		}else if("MDCP".equals(hiCode)){
			hyIndustryCode="_miaodai";
		}else if("GXKH".equals(hiCode)||"YZKH".equals(hiCode)||"FCKH".equals(hiCode)||"PJHY".equals(hiCode)||"SYDK".equals(hiCode)){
			hyIndustryCode="_xindai";
		}else{
			hyIndustryCode="_"+hiCode;
		}
		return hyIndustryCode;
	}

}
