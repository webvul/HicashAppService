package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 	根据城市ID获取电信套餐内容：返回参数
 * 
 * @author lihua.Ren
 * @create date 2015-08-18
 *
 */
public class CallPhoneDataMsgResp extends ParmResponse {

     
     private String phoneDataName;
	 private String phoneDataLevel;
	 private String phoneDataVoice;
	 private String phoneDataFlow;
	 private String phoneDataOver;
	 private String freeCalls;
	 private String transferRemark;
	 private String remark;
	 private String id;
	 
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the phoneDataName
	 */
	public String getPhoneDataName() {
		return phoneDataName;
	}
	/**
	 * @param phoneDataName the phoneDataName to set
	 */
	public void setPhoneDataName(String phoneDataName) {
		this.phoneDataName = phoneDataName;
	}
	/**
	 * @return the phoneDataLevel
	 */
	public String getPhoneDataLevel() {
		return phoneDataLevel;
	}
	/**
	 * @param phoneDataLevel the phoneDataLevel to set
	 */
	public void setPhoneDataLevel(String phoneDataLevel) {
		this.phoneDataLevel = phoneDataLevel;
	}
	/**
	 * @return the phoneDataVoice
	 */
	public String getPhoneDataVoice() {
		return phoneDataVoice;
	}
	/**
	 * @param phoneDataVoice the phoneDataVoice to set
	 */
	public void setPhoneDataVoice(String phoneDataVoice) {
		this.phoneDataVoice = phoneDataVoice;
	}
	/**
	 * @return the phoneDataFlow
	 */
	public String getPhoneDataFlow() {
		return phoneDataFlow;
	}
	/**
	 * @param phoneDataFlow the phoneDataFlow to set
	 */
	public void setPhoneDataFlow(String phoneDataFlow) {
		this.phoneDataFlow = phoneDataFlow;
	}
	/**
	 * @return the phoneDataOver
	 */
	public String getPhoneDataOver() {
		return phoneDataOver;
	}
	/**
	 * @param phoneDataOver the phoneDataOver to set
	 */
	public void setPhoneDataOver(String phoneDataOver) {
		this.phoneDataOver = phoneDataOver;
	}
	/**
	 * @return the freeCalls
	 */
	public String getFreeCalls() {
		return freeCalls;
	}
	/**
	 * @param freeCalls the freeCalls to set
	 */
	public void setFreeCalls(String freeCalls) {
		this.freeCalls = freeCalls;
	}
	/**
	 * @return the transferRemark
	 */
	public String getTransferRemark() {
		return transferRemark;
	}
	/**
	 * @param transferRemark the transferRemark to set
	 */
	public void setTransferRemark(String transferRemark) {
		this.transferRemark = transferRemark;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	 
}
