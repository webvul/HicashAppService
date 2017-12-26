package com.hengyuan.hicash.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.entity.user.Bank;


/**
 * 正则验证
 * @author Andy.Niu
 * @create 2014-08-01
 *
 */
public class RegexValidate {
	
	/* 判断一个字符串是否为空 */
	public static boolean isNull(String str) {
		 
         if (StringUtils.isEmpty(str) ||
        		 "null".equals(str)) {
             return true;
         }
         return false;
    }
	
	/***
	 * "^\\d+$"　　//非负整数（正整数   +   0）    
  		"^[0-9]*[1-9][0-9]*$"　　//正整数    
  		"^((-\\d+)|(0+))$"　　//非正整数（负整数   +   0）    
  		"^-[0-9]*[1-9][0-9]*$"　　//负整数    
  		"^-?\\d+$"　　　　//整数    
  		"^\\d+(\\.\\d+)?$"　　//非负浮点数（正浮点数   +   0）    
  		"^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"　　//正浮点数    
  		"^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$"　　//非正浮点数（负浮点数   +   0）    
  		"^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$"　　//负浮点数    
  		"^(-?\\d+)(\\.\\d+)?$"　　//浮点数
	 * **/
	
	
	/* 判断一个字符串是否为非负整数 */
	public static boolean isDigitNotNeg(String Str) {
		String reg="^\\d+$";
		
	    return Pattern.matches(reg,Str);

	}
	
	/* 判断一个字符串是否为整数 */
	public static boolean isDigit(String Str) {
		String reg="^-?\\d+$";
		
	    return Pattern.matches(reg,Str);

	}
	
	/* 判断一个字符串是否为正整数 */
	public static boolean isDigitPos(String Str) {
		String reg="^[0-9]*[1-9][0-9]*$";
		
	    return Pattern.matches(reg,Str);

	}
	
	/* 判断一个字符串是否为负整数 */
	public static boolean isDigitNeg(String Str) {
		String reg="^-[0-9]*[1-9][0-9]*$";
		
	    return Pattern.matches(reg,Str);

	}
	
	/* 判断一个字符串是否为正数 */
	public static boolean isPlusDigit(String Str) {
		
		String reg="/^\\d+(?=\\.{0,1}\\d+$|$)/";
		
		return Pattern.matches(reg,Str);

	}
	
	/* 判断一个字符串是否为浮点数 */
	public static boolean isDigitFloat(String Str) {
		
		String reg="^(-?\\d+)(\\.\\d+)?$";
		
		return Pattern.matches(reg,Str);

	}
	
	/* 判断一个字符串是否为正浮点数 */
	public static boolean isDigitFloatPos(String Str) {
		
		String reg="^\\d+(\\.\\d+)?$";
		
		return Pattern.matches(reg,Str);

	}
	
	/* 判断一个字符串是否为负浮点数 */
	public static boolean isDigitFloatNeg(String Str) {
		
		String reg="^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$";
		
		return Pattern.matches(reg,Str);

	}
	
	
	/* 判断uuid */
	public static boolean isUuid(String str) {
		String reg= "^[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}$";
		
		return Pattern.matches(reg,str);
	}
	
	/* 判断身份证 */
	public static boolean isIdCard(String str) {
		String reg= "([0-9]{17}([0-9]|X|x))|([0-9]{15})";
		
		return Pattern.matches(reg,str);
	}
	
	/* 判断中文 */
	public static boolean isChinese(String str) {
		String reg= "[\u4E00-\u9FA5]";
		Pattern p=Pattern.compile(reg);
		Matcher m=p.matcher(str);
		if(m.find()) {
		   return true;
		}
		
		return false;
	}
	/**
	 * 只能输入中文 
	 */
	public static boolean isOnlyChinese(String str){
		String regex = "^[\u4e00-\u9fa5]+$";
		
		return Pattern.matches(regex, str);
	}
	
	/**
	 * 真实姓名验证(只能输入中文或者'.'，'·')
	 */
	public static boolean isRealName(String str){
		String regex = "^[\u3400-\u9fa5|.|·]+$";
		
		return Pattern.matches(regex, str);
	}
	
	/* 判断日期格式 */
	public static boolean isDate(String str) {
		String reg= "\\d{4}-\\d{2}-\\d{2}";
		
		return Pattern.matches(reg,str);
	}
	
	/* 验证长度n-m位 */
	public static boolean isLength(String str,int n,int m) {
		if (isNull(str)==false) {
			if (str.length() >= n && str.length()<=m) {
				return true;
			}
		}
		return false;
	}
	/** 验证密码 6位数字加字母组合 */
	public static boolean isNewPassword(String str) {
		 return str.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$");
	}
	
	/* 验证特殊字符 */
	public static boolean isStringFilter(String str) {
		 return str.matches("\\w+");
	}
	
	/* 验证用户名 */
	public static boolean isUsername(String str) {
		//return str.matches("^(?![0-9_]+$)(?![a-zA-Z_]+$)[0-9A-Za-z_]{6,20}$");
		 return str.matches("^[0-9]|[A-Za-z]|[0-9A-Za-z_]{6,20}$");
	}
	
	/** 验证业务员用户名 */
	public static boolean isApproveUsername(String str) {
		//return str.matches("^(?![0-9_]+$)(?![a-zA-Z_]+$)[0-9A-Za-z_]{6,20}$");
		 return str.matches("^[0-9]|[A-Za-z]|[0-9A-Za-z_.]{3,20}$");
	}
	
	/** 验证密码 */
	public static boolean isPassword(String str) {
		 return str.matches("^(?![0-9_]+$)(?![a-zA-Z_]+$)[0-9A-Za-z_]{8,20}$");
	}
	
	/** 验证密码 */
	public static boolean isUserPassword(String str) {
		 return str.matches("^[0-9A-Za-z_]{6,20}$");
	}
	
	/** 验证手机号码(以1开头的11为数字) */
	public static boolean isIphon(String str) {
//		 return str.matches("^((13[[0-9],\\D])|(15[[0-9],\\D])|(14[[0-9],\\D])|(18[[0-9],\\D]))\\d{8}$");
		return str.matches("^1\\d{10}$");
	}
	
	
	/* 验证两个字符串是否一致 */
	public static boolean isCon(String str1,String str2) {
		if (!isNull(str1)&& !isNull(str2)) {
			if (str1.trim().equals(str2.trim())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断客户类型
	 */
	public static boolean isCustType(String str) {
		if (Consts.CUSTTYPE_KHL1.equals(str) 
				|| Consts.CUSTTYPE_KHL2.equals(str)) {
			return true;
		}
		
		return false;
	}
	/**
	 * 判断合同快递地址类型学生
	 */
	public static boolean isExpressType(String str) {
		if (!Consts.UNIVERSITY_CODE.equals(str) 
				&& !Consts.HOME_CODE.equals(str)
				&&!Consts.OTHER_CODE.equals(str)) {
			return true;
		}
		
		return false;
	}
	/**
	 * 判断合同快递地址类型白领
	 */
	public static boolean isExpressTypeCollar(String str) {
		if (!Consts.UNIT_CODE.equals(str) 
				&&!Consts.OTHER_CODE.equals(str)&&!Consts.HOME_CODE.equals(str)) {
			return true;
		}
		
		return false;
	}
	/**
	 * 判断学生类型
	 * @param str
	 * @return
	 */
	public static boolean isStuType(String str){
		
		if (!Consts.STUDENT_TYPE_XSL1.equals(str)
				&& !Consts.STUDENT_TYPE_XSL2.equals(str)
				&& !Consts.STUDENT_TYPE_XSL3.equals(str)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 验证数字 6-20位
	 * @param str
	 * @return
	 */
	public static boolean isBankCard(String str) {
		 return str.matches("^[0-9]{6,20}");
	}
	
	/**
	 * 验证开户行代码
	 * @param str
	 * @return
	 */
	public static boolean isOpenBank(String str) {
		
		try {
			if(str != null){
				if(!StringUtils.isEmpty(Bank.valueOf(str).getDispValue())){
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		
		
		return false;
	}
	
	/**
	 * 固定号码验证
	 * @param str  格式 0731-7561872
	 * @return
	 */
	public static boolean isTel(String area , String num){
		String regex = "(^(\\d{3,4}-)?\\d{7,8})$|(13[0-9]{9})";
		
		return Pattern.matches(regex, area+"-"+num);
	}
	
	/* 验证邮箱 */
	public static boolean isEmail(String str) {
		 return str.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
	}
	
	/* 验证学号 */
	public static boolean isStuId(String str) {
		 return str.matches("^[a-zA-Z0-9]*$");
	}
	
	/* 验证学号 */
	public static boolean isCard(String str) {
		 return str.matches("^[0-9]*$");
	}
	
	/* 验证数字 */
	public static boolean isNumber(String str) {
		 return str.matches("^[0-9]*$");
	}
	
	/**
	 * 判断下拉列表年
	 * @param str
	 * @return
	 */
	public static boolean isYear(String str){
		return str.matches("^[0-9]{4}");
	}
	
	/**
	 * 判断下拉列表月
	 * @param str
	 * @return
	 */
	public static boolean isMonth(String str){
		return str.matches("^[0-9]{2}");
	}
	
	/**
	 * 判断字符串只能是数字或者字母
	 * @param str
	 * @return
	 */
	public static boolean isNumOrLetter(String str){
		String reg = "^[0-9a-zA-Z]*";
		
		return Pattern.matches(reg, str);
	}
	
	/**
	 * 验证特殊字符(可以包含中文,字母，数字)
	 * @param str
	 * @return
	 */
	public static boolean isStrFilter(String str){
//		if(isStringFilter(str)||isOnlyChinese(str)){
//			return true;
//		}
//		return false; [\u4e00-\u9fa5]+$
		String regex = "^[\\w\u4e00-\u9fa5]+$";
		return Pattern.matches(regex, str);
	}
	
	/**
	 * 验证下拉列表值范围
	 */
	public static boolean isSelect(String str){
		
		String regex = "^[a-zA-Z0-9]{0,6}$";
		return Pattern.matches(regex, str);
		
	}
	
	/**
	 * 验证地址下拉列表值范围(如：省、市、区)
	 */
	public static boolean isSelectToAddress(String str){
		
		String regex = "^[0-9]{6}$";
		
		return Pattern.matches(regex, str);
	}
	
	/**
	 * 申请方式
	 * 3C:NORMAL  现金:CASH
	 * @param str
	 * @return
	 */
	public static boolean isApplyType(String str) {
		if (Consts.APPFLOW_TYPE_3C.equals(str) 
				|| Consts.APPFLOW_TYPE_CASH.equals(str)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 *  只能是数字
	 */
	public static boolean isOnlyNumber(String str) {
		String reg = "[0-9]{1,11}";
		
		return Pattern.matches(reg,str);
	}
	
	/**
	 *  验证长数字
	 */
	public static boolean isLongNumber(String str) {
		String reg = "[0-9]{1,20}";
		
		return Pattern.matches(reg,str);
	}
	
	public static boolean isSysMark(String str){
		
		if(Consts.FINAL_NUMBER_0.equals(str) || Consts.FINAL_NUMBER_1.equals(str)){
			return true;
		}
		return false;
	}
	
	/**
	 * 申请件流水号格式是否正确
	 * @param str
	 * @return
	 */
	public static boolean isAppNo(String str){
		
		String regex = "^[0-9]{14}$";
		
		return Pattern.matches(regex, str);
	}
	
	/**
	 * 蓝领数码行业
	 * @param industryCode
	 * @return
	 */
	public static boolean isIndustryLlsm(String industryCode){
		
		if ("LLSM".equals(industryCode)) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 蓝领秒贷行业
	 * @param industryCode
	 * @return
	 */
	public static boolean isIndustryLlmd(String industryCode){
		
		if ("LLMD".equals(industryCode)) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 嗨秒贷行业
	 * @param industryCode
	 * @return
	 */
	public static boolean isIndustryHmd(String industryCode){

		if ("MDCP".equals(industryCode) || "MDOH".equals(industryCode)
				|| "LLMD".equals(industryCode)) {

			return true;
		}
		
		return false;
	}
	
	public static boolean isApplyFrom(String from){

		if ("APP".equals(from) || "HTML5".equals(from)
				|| "PC".equals(from)||"APPiOS".equals(from)||"APPAndroid".equals(from)) {

			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
//		System.out.println(isDigitFloatPos("25.5"));
//		System.out.println(isNumOrLetter("sdf1"));
//		System.out.println(isStringFilter("a12345678"));
//		System.out.println(isStrFilter("水电费sdf水电费你看##"));
//		System.out.println(isIphon("18217015991"));
//		System.out.println(isOnlyChinese("三等奖你偶发n"));
//		System.out.println(isBankCard("111111111111111111111"));
//		System.out.println(isUsername("sddsss_@"));
//		System.out.println(isNull("#######"));
//		System.out.println(isRealName("说")); 
//		System.out.println(isCon("11", "11"));
//		System.out.println(isSelect("sasdl"));
//		System.out.println(isSelectToAddress("213111"));
//		System.out.println(isIdCard("703199312143253"));
//		System.out.println(isYear("2012"));
//		System.out.println(isMonth("080"));
//		System.out.println(isUserPassword("dddddd"));
//		System.out.println(isDigitPos("0"));
//		System.out.println(isDigitPos("10"));
//		String str = Bank.valueOf("ICsBC").getDispValue();
//		System.out.println(str);
//		System.out.println(RuleUtils.getAppUseAge("230521197909182912"));
//		String str = "用户：{1}，时间：{2}，代扣扣款金额￥{3}";
//		System.out.println(str.replace("{1}", "东方不败").replace("{2}", DateUtils.getDateStr(new Date())).replace("{3}", "100"));
//		PayFlowNoQuery flowQuery = new PayFlowNoQuery();
//		try {
//			System.out.println(com.hengyuan.hicash.utils.StringUtils.lampLeft(flowQuery.queryFlowNo(Consts.PROXY_DEDUCT_SEQ), "0", 5));
//		} catch (QueryFlowNoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(isOnlyChinese("时间的话放假"));
		System.out.println(!isAppNo("31701011013897"));
	}
	/**
	 * 备注
	 * @param str
	 * @param i GB2312编码时的字节长度  如100个英文==50个中文
	 * @return
	 */
	public static boolean isDescFlag(String str,int i){
		
		boolean flag = false;
		
		if(StringUtils.isEmpty(str) || str.length() > i){
			return flag;
		}
		
		try {
			/* 将字符串传化为GB2312编码的字节长度 */
			int count = str.getBytes("GB2312").length;
			if(count <= i){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[备注信息验证失败："+str+"]");
		}
		
		return flag;
	}
	
	/* 判断一个字符串是否为纯数字 */
	public static boolean isOnlyNum(String Str) {
		
		String reg="^[0-9]*$";
		
		return Pattern.matches(reg,Str);

	}
	
}
