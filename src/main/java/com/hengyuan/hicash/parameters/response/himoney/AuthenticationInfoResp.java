package com.hengyuan.hicash.parameters.response.himoney;

import java.util.List;

import com.hengyuan.hicash.entity.himoney.AuthenticationEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;


/**
 * 客户选择认证的项
 * 
 * @author xuexin
 * @create 2017年7月14日 09:53:10
 *
 */
public class AuthenticationInfoResp extends ParmResponse {
	
	/**信用总额度**/
	private String  total_quota;
	
	/**总信用系数分**/
	private String  total_score;
	
	
	public String getTotal_quota() {
		return total_quota;
	}

	public void setTotal_quota(String total_quota) {
		this.total_quota = total_quota;
	}

	public String getTotal_score() {
		return total_score;
	}

	public void setTotal_score(String total_score) {
		this.total_score = total_score;
	}

	/**返回集合*/
	private List<AuthenticationEntity> list;
	
	
	public List<AuthenticationEntity> getList() {
		return list;
	}

	public void setList(List<AuthenticationEntity> lists) {
		this.list = lists;
	}


}
