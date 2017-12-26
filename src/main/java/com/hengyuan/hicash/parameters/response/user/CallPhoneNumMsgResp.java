package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.entity.user.CallPhoneNumEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 	根据城市ID,页数，显示条数获取电话号码：返回参数
 * 
 * @author lihua.Ren
 * @create date 2015-08-18
 *
 */
public class CallPhoneNumMsgResp extends ParmResponse {
	private List<CallPhoneNumEntity> phoneNums;

	/**
	 * @return the phoneNums
	 */
	public List<CallPhoneNumEntity> getPhoneNums() {
		return phoneNums;
	}

	/**
	 * @param phoneNums the phoneNums to set
	 */
	public void setPhoneNums(List<CallPhoneNumEntity> phoneNums) {
		this.phoneNums = phoneNums;
	}
	
}
