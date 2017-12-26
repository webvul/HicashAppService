package com.hengyuan.hicash.utils;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author dong.liu 
 * 2017-12-15 上午11:55:29
 * 类说明 
 */
public class BorrowmoneyuseUtil {
	
	public static Map<String, String> useMap=new HashMap<String, String>();

	public static Map<String, String> getUseMap() {
		return useMap;
	}

	public static void setUseMap(Map<String, String> useMap) {
		BorrowmoneyuseUtil.useMap = useMap;
	}
	
	static{
		        useMap.put("JK01","装修");
				useMap.put("JK02","家具家居");
				useMap.put("JK03","家用电器");
				useMap.put("JK04","教育");
				useMap.put("JK05","健康医疗");
				useMap.put("JK06","旅游");
				useMap.put("JK07","日常消费");
				useMap.put("JK08","正常");
				useMap.put("JK09","归还外债");
				useMap.put("JK10","租房");
				useMap.put("JK11","手机数码");
				useMap.put("JK12","婚庆");
				useMap.put("JK13","其他");

		
		
	}
	

}
