package com.hengyuan.hicash.utils;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hengyuan.hicash.constant.Consts;

/**
 * 
 * @author Cary.Liu
 * @createDate 2015-05-11
 *
 */
public class StringUtils {

	/**
	 * 字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		
		if((str!=null) && !str.trim().equals("")&& !str.equals("null")){
			return false;
		}else{
			return true;
		}
	}

	
	/**
	 * 取得查询结果进行非空验证
	 * 
	 * @param o
	 * @return
	 * @throws SQLException
	 */
	public static String valueOf(Object o) throws SQLException {
		if (o != null) {
			if (!org.apache.commons.lang.StringUtils.isEmpty(o.toString())) {
				return o.toString();
			} else{
				return "";
			}
		} else {
			return "";
		}
		
	}
	
	/**
	 * 取得查询结果进行非空验证
	 * 
	 * @param o
	 * @return
	 * @throws SQLException
	 */
	public static String getString(Object o){
		if (o != null) {
			if (!org.apache.commons.lang.StringUtils.isEmpty(o.toString())) {
				return o.toString();
			} else{
				return "";
			}
		} else {
			return "";
		}
		
	}
	
	public static int getInt(String str){
		
		if(str != null && str.trim() != "" && str != "null"){
			return Integer.parseInt(str);
		}else{
			return 0;
		}
	}

	/**
	 * 获取全球唯一ID
	 * 
	 * @return
	 * @author Andy.Niu
	 * @create 2014-08-05
	 */
	/*public static String getUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}*/

	public static String valueOfBigDecimal(Object o) throws SQLException {
		if (o != null) {
			if (!com.mysql.jdbc.StringUtils.isNullOrEmpty(o.toString())) {
				return o.toString();
			} else{
				return "0";
			}
		} else {
			return "0";
		}
	}
	
	
	/**
	 * 字符补位方法
	 * 
	 * @param value
	 * @param str
	 * @param length
	 * @return
	 */
	public static String lampLeft(String value, String str, int length) {
		String rtnValue = value;
		if (value == null) {
			return "";
		}
		if (length - value.length() <= 0) {
			return value;
		}

		for (int i = 0; i < length - value.length(); i++) {
			rtnValue = str + rtnValue;
		}
		return rtnValue;
	}
	
	/** 通过身份证算性别 */
	public static String getGenderByIdCard(String idCard) {

		if (idCard == null || idCard.isEmpty()) {

			return "未知";
		}

		String sCardNum = idCard.substring(16, 17);

		if (Integer.parseInt(sCardNum) % 2 != 0) {

			return "男";

		} else {

			return "女";
		}
	}
	
	public static boolean showFlag(String type) {
		if (Consts.PRODUCT_TYPE_EDU.equals(type)
				|| Consts.PRODUCT_TYPE_CAR.equals(type)
				|| Consts.PRODUCT_TYPE_JOB.equals(type)
				|| Consts.PRODUCT_TYPE_HOURSE.equals(type)
				|| Consts.PRODUCT_TYPE_STU.equals(type)
				|| Consts.PRODUCT_TYPE_CTOUR.equals(type)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int getRandom(int min, int max){
		
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;

		return s;
	}
	
	/**
	 * 
	 * @param activeType 活动类型（申请嗨秒贷抽奖 MDCJ/分享抽奖 FXCJ/2016新分享抽奖活动NFXC）
	 * @return
	 */
	public static int getActiveNo(String activeType){
		
		if("MDCJ".equals(activeType)){
			
			return 1;
			
		}else if ("FXCJ".equals(activeType)){
			
			return 2;
			
		}else if ("NFXC".equals(activeType)){
			
			return 3;
			
		}
		
		return 0;
	}

   public static String  getMobile(String mobile){
	    mobile=mobile.replaceAll(" ", "");
		String temp1=mobile.substring(0,2);
		String temp2=mobile.substring(0,3);
		if("86".endsWith(temp1)){
			mobile=mobile.substring(2,mobile.length());
		}
		if("+86".endsWith(temp2)){
			mobile=mobile.substring(3,mobile.length());
		}
		mobile=mobile.replaceAll("\\+", "").replaceAll("\\-", "");
//		System.out.println(mobile);
//		String regExp = "^\\d{11}$";  
//		Pattern p = Pattern.compile(regExp);  
//	    Matcher m = p.matcher(mobile);  
	    return mobile;
		  
	   
	   
   }	
	
	
	public static void main(String[] args) {
		
		String mobile=" +861881+0-8407";
		    mobile=mobile.replaceAll(" ", "");
			String temp1=mobile.substring(0,2);
			String temp2=mobile.substring(0,3);
			if("86".endsWith(temp1)){
				mobile=mobile.substring(2,mobile.length());
			}
			if("+86".endsWith(temp2)){
				mobile=mobile.substring(3,mobile.length());
			}
			System.out.println("1111111");
			mobile=mobile.replaceAll("\\+", "").replaceAll("\\-", "");
			System.out.println(mobile);
			String regExp = "^\\d{11}$";  
			Pattern p = Pattern.compile(regExp);  
		    Matcher m = p.matcher(mobile); 
	    System.out.println( m.find());
		System.out.println(mobile+"长度:"+mobile.length());

		
	}
	
	
	/** 
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0 
     * 格式可支持各种字符 如 : 1.2.3a.1.是
     * @param version1
     * @param version2
     * @return 
     */  
    public static int compareVersion(String version1, String version2) throws Exception {  
        if (version1 == null || version2 == null) {  
            throw new Exception("compareVersion error:illegal params.");  
        }  
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；  
        String[] versionArray2 = version2.split("\\.");  
        int idx = 0;  
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值  
        int diff = 0;  
        while (idx < minLength  
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度  
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符  
            ++idx;  
        }  
        //需求要求传几位就比较几位  所以 不比较子版本了
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；  
        //diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;  
        return diff;  
    }  
    
    public static String getSelects(List<String> selects) {

		StringBuffer select = new StringBuffer("");
		if (selects != null && selects.size() > 0) {
			for (int i = 0; i < selects.size(); i++) {
				if (i == selects.size() - 1) {
					select.append(selects.get(i));
				} else {
					select.append(selects.get(i) + SqlOperators.COMMA);
				}

			}
		}

		return select.toString();
	}
    /**
     * 
    * map转换json.
    * <br>详细说明
    * @param map 集合
    * @return
    * @return String json字符串
    * @throws
    * @author slj
     */
    public static String mapToJson(Map<String, Object> map) {

        Set<String> keys = map.keySet();
        String key = "";
        Object value = "";
        StringBuffer jsonBuffer = new StringBuffer();
        jsonBuffer.append("{");
        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            key = (String) it.next();
            value = map.get(key);
//            jsonBuffer.append(key + ":" +"\""+ value+"\"");
            jsonBuffer.append("\""+key +"\""+":" + "\""+value+"\"");

            if (it.hasNext()) {
                jsonBuffer.append(",");
            }
        }
        jsonBuffer.append("}");
       

        return jsonBuffer.toString();
}
    
    
    
    /**
     * 
    * map转换json.
    * <br>详细说明
    * @param map 集合
    * @return
    * @return String json字符串
    * @throws
    * @author slj
     */
    public static String mapToJsons(Map<String, String> map) {

        Set<String> keys = map.keySet();
        String key = "";
        Object value = "";
        StringBuffer jsonBuffer = new StringBuffer();
        jsonBuffer.append("{");
        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            key = (String) it.next();
            value = map.get(key);
//            jsonBuffer.append(key + ":" +"\""+ value+"\"");
            jsonBuffer.append("\""+key +"\""+":" + "\""+value+"\"");

            if (it.hasNext()) {
                jsonBuffer.append(",");
            }
        }
        jsonBuffer.append("}");
       

        return jsonBuffer.toString();
}
}
