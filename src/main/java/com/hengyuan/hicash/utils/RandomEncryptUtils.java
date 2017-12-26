package com.hengyuan.hicash.utils;

import org.apache.log4j.Logger;

public class RandomEncryptUtils {
	
	private static Logger logger = Logger.getLogger(RandomEncryptUtils.class);

	/**
	 * 数字加密
	 * @param prizeNum 加密数字 （大于0 小于 50）
	 * @param randomNum 随机字符串数字（大于0 小于6位数）
	 * @param index （大于0 小于10）
	 * @return
	 */
	public static String encrypt(int prizeNum, int randomNum, int index) {
		
		String encStr = "";
		
		try {
			
			if(randomNum > 0 && index > 0){
				
				int encNum = ((prizeNum * randomNum) << index) - randomNum;
				
				encStr = Base64.encode(encNum + "-" + randomNum + "-" + index);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[数字加密异常]：\n" + e.getMessage());
		}
		
		
		
		return encStr;
	}
	
	/**
	 * 数字解密
	 * @param str
	 * @return
	 */
	public static int decrypt(String str, Integer randomNum) {
		
		int decNum = 0;
		
		try {
			
			if(str != null && !"".equals(str)){
				
				String base64DecStr = Base64.decode(str);
				
				String[] strs = base64DecStr.split("-");
				
				Integer number = Integer.parseInt(strs[0]);
//				Integer randomNum = Integer.parseInt(strs[1]);
				Integer index = Integer.parseInt(strs[2]);
				//int encNum = ((prizeNum * randomNum) << index) - randomNum;
				decNum = ((number + randomNum) >> index) / randomNum;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[数字解密异常]：\n" + e.getMessage());
		}
		
		return decNum;
	}
	
	public static void main(String[] args) {
		
//		String str = "liuxinyu";
//		String base64EncStr = Base64.encode(str);
//		String base64DecStr = Base64.decode(base64EncStr);
//		System.out.println(base64EncStr + "\t" + base64DecStr);
		
//		System.out.println(3<<4);
//		System.out.println((4*2)>>2);
		
//		int i = 102;
//		int randomStr = Integer.parseInt(RandomStringUtils.random(5, false, true));
//		System.out.println(randomStr);
//		String encStr = encrypt(i, randomStr, 6);
//		int decStr = decrypt(encStr, randomStr);
//		System.out.println(encStr);
//		System.out.println(decStr);
		System.out.println(decrypt("NTc1NjQ5OS05MTM3My00", 91373));
		
	}
	
}
