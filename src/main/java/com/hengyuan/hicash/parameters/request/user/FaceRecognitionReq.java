package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
 * @author fish
 *
 * @date 2017年2月22日 上午11:19:33
 */
public class FaceRecognitionReq extends RequestSequence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idNo;

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

}
