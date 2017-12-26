package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
 * 电信专案,保存产品请求参数
 * 
 * @author LiHua.Ren
 * @createDate 2015-08-18
 */
public class CallPhoneDataNumUpdateReq extends RequestSequence {

	private static final long serialVersionUID = 1L;
	private String userName;//用户名
	private String phoneDataId;//套餐id
	private String appType;//申请类型，标志哪种类型的单子，值dx
	private String appFlowNo;//流水号
	private String phoneNum;//手机号码
	private String proMerId;//商品id
	private String proPrice;//商品价格总价格
	private String proColor;//颜色
	private String proPeriod;//期数ID
	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the phoneDataId
	 */
	public String getPhoneDataId() {
		return phoneDataId;
	}
	/**
	 * @param phoneDataId the phoneDataId to set
	 */
	public void setPhoneDataId(String phoneDataId) {
		this.phoneDataId = phoneDataId;
	}
	/**
	 * @return the appType
	 */
	public String getAppType() {
		return appType;
	}
	/**
	 * @param appType the appType to set
	 */
	public void setAppType(String appType) {
		this.appType = appType;
	}
	/**
	 * @return the appFlowNo
	 */
	public String getAppFlowNo() {
		return appFlowNo;
	}
	/**
	 * @param appFlowNo the appFlowNo to set
	 */
	public void setAppFlowNo(String appFlowNo) {
		this.appFlowNo = appFlowNo;
	}
	/**
	 * @return the proMerId
	 */
	public String getProMerId() {
		return proMerId;
	}
	/**
	 * @param proMerId the proMerId to set
	 */
	public void setProMerId(String proMerId) {
		this.proMerId = proMerId;
	}
	/**
	 * @return the proPrice
	 */
	public String getProPrice() {
		return proPrice;
	}
	/**
	 * @param proPrice the proPrice to set
	 */
	public void setProPrice(String proPrice) {
		this.proPrice = proPrice;
	}
	/**
	 * @return the proColor
	 */
	public String getProColor() {
		return proColor;
	}
	/**
	 * @param proColor the proColor to set
	 */
	public void setProColor(String proColor) {
		this.proColor = proColor;
	}
	/**
	 * @return the proPeriod
	 */
	public String getProPeriod() {
		return proPeriod;
	}
	/**
	 * @param proPeriod the proPeriod to set
	 */
	public void setProPeriod(String proPeriod) {
		this.proPeriod = proPeriod;
	}

}
