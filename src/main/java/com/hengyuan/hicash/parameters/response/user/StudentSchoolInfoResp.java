package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;


/**
 * hicashi学生用户完善资料的响应对象(修改学校信息)
 * 
 * @author Cary.Liu
 * @create date 2014-07-16
 *
 */
public class StudentSchoolInfoResp extends ParmResponse {

	/** 返回代码 */
	private String resultCode;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

}
