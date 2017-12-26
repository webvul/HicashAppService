package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;





/**
 * 获取客户学信数据信息响应类
 * @author teng
 * @date 2016年5月10日
 */
public class QueryLocalStuEduResp extends ParmResponse {

	private StuEducation stuEducation;

	public StuEducation getStuEducation() {
		return stuEducation;
	}

	public void setStuEducation(StuEducation stuEducation) {
		this.stuEducation = stuEducation;
	}
	
	
	
	
}
