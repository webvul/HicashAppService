package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * hicash手机端学生提现申请根据城市查询学校请求参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuAppSchoolReq extends RequestSequence{

	private static final long serialVersionUID = 2733124823620746442L;
//	private String uuid;
	
	private String cityCode;
	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
//	/**
//	 * @return the uuid
//	 */
//	public String getUuid() {
//		return uuid;
//	}
//	/**
//	 * @param uuid the uuid to set
//	 */
//	public void setUuid(String uuid) {
//		this.uuid = uuid;
//	}
	
}
