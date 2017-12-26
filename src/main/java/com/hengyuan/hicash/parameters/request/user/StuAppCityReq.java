package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * hicash手机端学生提现申请根据省查询城市请求参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuAppCityReq extends RequestSequence {

	private static final long serialVersionUID = 1207484073049460535L;
//	private String uuid;
	private String provinceCode;

	/**
	 * @return the provinceCode
	 */
	public String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * @param provinceCode
	 *            the provinceCode to set
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

//	/**
//	 * @return the uuid
//	 */
//	public String getUuid() {
//		return uuid;
//	}
//
//	/**
//	 * @param uuid
//	 *            the uuid to set
//	 */
//	public void setUuid(String uuid) {
//		this.uuid = uuid;
//	}

}
