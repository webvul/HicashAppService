package com.hengyuan.hicash.dao.dict;

public enum CertificationType {
	
	IDEC("身份认证", 10), VIDC("视频认证", 10), DEGC("学历认证", 10), MOBD("手机绑定", 0), MOCR("手机实名认证", 10), HOCR("户籍认证", 10),  
	IDPC("身份证照片", 10), BATC("银行卡流水", 10), WAGC("工资卡", 10), DRIC("行驶证", 10), DRLI("驾照", 10), 
	LACC("劳动合同", 10), BULC("营业执照", 10), PESC("个人社保", 10), MARC("结婚证", 10), HOMC("户口本", 10),
	HPCC("房产证", 10), PBOC("央行征信记录", 10), PESE("个人社保", 10), SDMB("公共事业(水电煤)缴费单", 10), 
	WCCI("完成用户资料", 10), WCGI("完成单位资料", 10), WCLI("完成联系人资料", 10), EMCR("邮箱认证", 10);
	
	private String dispValue;
	private Integer score;

	CertificationType(String dispValue, Integer score) {
		this.dispValue = dispValue;
		this.score = score;
	}

	public String getDispValue() {
		return dispValue;
	}

	public Integer getScore() {
		return score;
	}
	
}
