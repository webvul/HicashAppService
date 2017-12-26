package com.hengyuan.hicash.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class ResourceUtils {
	
private static final Properties properties = new Properties();
	
	private static final Properties cfgProp = new Properties();
	
	static{

		try {
			
			properties.load(new ClassPathResource("properties/zh_message.properties").getInputStream());
			
			cfgProp.load(new ClassPathResource("properties/config.properties").getInputStream());
			
		} catch (IOException e) {
			System.out.println("获取配置文件异常");
			e.printStackTrace();
		}
		
	}
	
	public static String getString(String key) {
		
		String value="";
		if(properties.get(key) != null && !properties.get(key).toString().equals("")){
			value = properties.get(key).toString();
		}
		return value;
	}
	
	public static String getValue(String key) {
		
		String value="";
		if(cfgProp.get(key) != null && !cfgProp.get(key).toString().equals("")){
			value = cfgProp.get(key).toString();
		}
		return value;
	}	 	
}
