package com.hengyuan.hicash.parameters.response.order;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 临时订单是否存在  返回参数
 * @author yangkun
 *
 */
public class TempOrderIsExistResp extends ParmResponse{

	/**是否存在临时单  0-不存在  1-存在**/
	private String isExist;
	/**行业代码**/
	private String industryCode;
	/**临时订单号**/
	private String tempApplicationNo;
	
	private String period;//期数
	
	private String applyRice;//申请金额
	
	private String loanProductId;//产品id
	
	
	
	public String getIsExist() {
		return isExist;
	}
	public void setIsExist(String isExist) {
		this.isExist = isExist;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getTempApplicationNo() {
		return tempApplicationNo;
	}
	public void setTempApplicationNo(String tempApplicationNo) {
		this.tempApplicationNo = tempApplicationNo;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getApplyRice() {
		return applyRice;
	}
	public void setApplyRice(String applyRice) {
		this.applyRice = applyRice;
	}
	public String getLoanProductId() {
		return loanProductId;
	}
	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
	}
	
}
