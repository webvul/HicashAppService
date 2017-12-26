package com.hengyuan.hicash.parameters.request.param;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
* @author dong.liu 
* 2017-12-20 下午6:15:10
* 类说明:
 */
public class TempAppInofoReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 临时订单号*/
	private String tempAppNo;
	
	

	public String getTempAppNo() {
		return tempAppNo;
	}

	public void setTempAppNo(String tempAppNo) {
		this.tempAppNo = tempAppNo;
	}

	
	
	
	
}
