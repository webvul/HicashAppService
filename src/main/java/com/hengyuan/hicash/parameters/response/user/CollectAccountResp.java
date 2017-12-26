package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.entity.user.CollectAccountEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 收款账户信息查询 响应参数
 * 
 * @author Cary.Liu
 * @create date 2014-07-26
 * 
 */
public class CollectAccountResp extends ParmResponse {

	private List<CollectAccountEntity> accountList;

//	/** 真实姓名 */
//	private String realName;
//
//	/** 账户类型(开户行) */
//	private String cardType;
//
//	/** 开户城市-省 */
//	private String province;
//
//	/** 开户城市-市 */
//	private String city;
//
//	/** 开户支行 */
//	private String openBank;
//
//	/** 收款账号 */
//	private String cardNum;
	
	

	public List<CollectAccountEntity> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<CollectAccountEntity> accountList) {
		this.accountList = accountList;
	}

}
