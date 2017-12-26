package com.hengyuan.hicash.parameters.response.user;

import java.util.List;



import com.hengyuan.hicash.entity.user.University;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * hicash手机端学生提现申请根据城市查询学校返回参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuAppSchoolResp extends ParmResponse{
	/** 返回代码 */
//	private String resultCode;
	private List<University> schools ;
	/**
	 * @return the resultCode
	 */
//	public String getResultCode() {
//		return resultCode;
//	}
//	/**
//	 * @param resultCode the resultCode to set
//	 */
//	public void setResultCode(String resultCode) {
//		this.resultCode = resultCode;
//	}
	/**
	 * @return the schools
	 */
	public List<University> getSchools() {
		return schools;
	}
	/**
	 * @param schools the schools to set
	 */
	public void setSchools(List<University> schools) {
		this.schools = schools;
	}
	
}
