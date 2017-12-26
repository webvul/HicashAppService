package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * hicash手机端白领提现申请3查询返回信息
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
public class NewCollarApp3MsgResp extends ParmResponse {
	
	/** 客户类型 */
	private String custType;
	/** 客户类型名称 */
	private String custTypeName;
	
	/** 毕业学校 */
	private String school;

	/** 最高学历 */
	private String education;

	/** 最高学历名称 */
	private String educationName;

	/** 毕业时间--年 */
	private String gradYear;

	/** 毕业时间--月 */
	private String gradMonth;

	/** 学制 */
	private String fulltime;

	/** 学制名称 */
	private String fulltimeName;

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCustTypeName() {
		return custTypeName;
	}

	public void setCustTypeName(String custTypeName) {
		this.custTypeName = custTypeName;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEducationName() {
		return educationName;
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	public String getGradYear() {
		return gradYear;
	}

	public void setGradYear(String gradYear) {
		this.gradYear = gradYear;
	}

	public String getGradMonth() {
		return gradMonth;
	}

	public void setGradMonth(String gradMonth) {
		this.gradMonth = gradMonth;
	}

	public String getFulltime() {
		return fulltime;
	}

	public void setFulltime(String fulltime) {
		this.fulltime = fulltime;
	}

	public String getFulltimeName() {
		return fulltimeName;
	}

	public void setFulltimeName(String fulltimeName) {
		this.fulltimeName = fulltimeName;
	}

	
}
