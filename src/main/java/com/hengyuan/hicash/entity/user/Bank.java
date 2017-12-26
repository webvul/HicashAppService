package com.hengyuan.hicash.entity.user;

/**
 * 开户银行的枚举
 * @author Cary.Liu
 * @create 2014-07-28
 *
 */
public enum Bank {
	
	CCB("中国建设银行"),
	ICBC("中国工商银行"),
	BOC("中国银行"),
	ABC("中国农业银行"),
	BOCO("交通银行"),
	CMB("招商银行"),
	CMBC("中国民生银行"),
	HXBC("华夏银行"),
	CIB("兴业银行"),
	SPDB("上海浦东发展银行"),
	NJBK("南京银行"),
	GDB("广东发展银行"),
	CITI("中信银行"),
	CEB("光大银行"),
	PSBC("中国邮政储蓄银行"),
	SDB("深圳发展银行"),
	CGB("广发银行");
//	HXZF("环迅支付"),
//	YLZX("银联在线"),
//	ZFB("支付宝");

	
	private String dispValue;
	
	Bank(String dispValue) {
		this.dispValue = dispValue;
	}
	
	public String getDispValue() {
		return dispValue;
	}
	
}
