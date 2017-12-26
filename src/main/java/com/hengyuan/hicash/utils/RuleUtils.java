package com.hengyuan.hicash.utils;

import java.util.Calendar;
import java.util.Date;

public class RuleUtils {

	public static void main(String[] args) {
//		System.out.println(getAppUseAge("370802199603313649"));
		System.out.println(getAppUseAge("441323198411175063"));
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
				int now_month = calendar.get(Calendar.MONTH)+1;
				int now_day  = calendar.get(Calendar.DAY_OF_MONTH);

				int idenno = Integer.parseInt(identityNo.substring(6, 10));
				int appmonth = Integer.parseInt(identityNo.substring(10, 12));
				int identityDay = Integer.parseInt(identityNo.substring(12, 14));
				age = now_year - idenno;
				
				if (age < 18) {
					return age;
				}
				
				if(now_month < appmonth ){
					age--;
					if (age < 18) {
						return age;
					}
				}else if(now_month > appmonth){
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
	
}
