package com.hengyuan.hicash.entity.app;

/**
 * @author Administrator
 *
 */
public class CallPhoneDataParam {
	private String appNo;

	/*电信*/
	private String phoneNo;//用户选择的手机号
    private String phoneDataId;//套餐Id
    private String proDataName;//商品名称（名称+颜色）

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
	 * @return the proDataName
	 */
	public String getProDataName() {
		return proDataName;
	}
	/**
	 * @param proDataName the proDataName to set
	 */
	public void setProDataName(String proDataName) {
		this.proDataName = proDataName;
	}
	/**
	 * @return the appNo
	 */
	public String getAppNo() {
		return appNo;
	}
	/**
	 * @param appNo the appNo to set
	 */
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

}
