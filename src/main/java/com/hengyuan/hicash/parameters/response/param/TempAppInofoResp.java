package com.hengyuan.hicash.parameters.response.param;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 获取频道列表 返回参数
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class TempAppInofoResp extends ParmResponse {
	private String applyRice;//申请金额
	
	private String period;//期数
	
	private String iscard;//能否绑卡 0否 1是
	
	private String  idcardStatus;//身份认证 /车主认证
	
	private String industryCode;//行业代码
	
	private String personStatus;//人行征信状态
	
	private String basicStatus;//基本信息
	
	private String creditStatus;//信用认证

	public String getApplyRice() {
		return applyRice;
	}

	public void setApplyRice(String applyRice) {
		this.applyRice = applyRice;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getIscard() {
		return iscard;
	}

	public void setIscard(String iscard) {
		this.iscard = iscard;
	}

	public String getIdcardStatus() {
		return idcardStatus;
	}

	public void setIdcardStatus(String idcardStatus) {
		this.idcardStatus = idcardStatus;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getPersonStatus() {
		return personStatus;
	}

	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}

	public String getBasicStatus() {
		return basicStatus;
	}

	public void setBasicStatus(String basicStatus) {
		this.basicStatus = basicStatus;
	}

	public String getCreditStatus() {
		return creditStatus;
	}

	public void setCreditStatus(String creditStatus) {
		this.creditStatus = creditStatus;
	}
	


}
