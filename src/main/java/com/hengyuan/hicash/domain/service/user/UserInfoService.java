package com.hengyuan.hicash.domain.service.user;

import java.util.Date;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.UserInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.UserInfoResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 是否为蓝领用户 service
 * @author Cary.Liu
 * @createDate 2016-03-02
 *
 */
public class UserInfoService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		UserInfoReq userInfoReq = (UserInfoReq)parmRequest;
		UserInfoResp userInfoResp = new UserInfoResp();
		
		try {
			
			CustUserEntity custUser = queryCustUserByUserName(userInfoReq.getUserName());
			CustomerEntity customer = queryCustomerByUserName(userInfoReq.getUserName());
			
			if(custUser != null){
				
				userInfoResp.setUserName(userInfoReq.getUserName());
				userInfoResp.setRealName(customer.getRealName());
				userInfoResp.setCustType(customer.getCustType());
				userInfoResp.setInOneMonthReg(getInOnMonth(custUser.getCreateTime()));
				userInfoResp.setIsLanUserFlag(getIsLanUser(customer.getLanUserFlag()));
				
				resultCode = ResultCodes.NORMAL;
						
			}else{
				resultCode = ResultCodes.USERINFO_USER_NOTFOUND;
			}
			
		} catch (Exception e) {
			resultCode = ResultCodes.USERINFO_EXCEPTION;
			e.printStackTrace();
		} finally {
			ConnManager.closeConn();
		}
		
		userInfoResp.setResultCode(resultCode);
		return userInfoResp;
	}
	
	public static String getInOnMonth(String createTime){
		
		if(!StringUtils.isEmpty(createTime)){
			// 用户是在注册后1个月内
			long longTime = DateUtils.getDaysBetween(createTime, new Date());
			if(longTime < 31){
				return Consts.FINAL_NUMBER_1;
			}
		}
		
		return Consts.FINAL_NUMBER_0;
	}
	
	public static String getIsLanUser(String isLanUser){
		
		// 是为蓝领用户
		if (isLanUser != null&& Consts.FINAL_NUMBER_1.equals(isLanUser)) {
			return Consts.FINAL_NUMBER_1;
		}
		
		return Consts.FINAL_NUMBER_0;
	}
	
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	public CustUserEntity queryCustUserByUserName(String userName){
		
		CustUserQuery custUserQuery = new CustUserQuery();
		return custUserQuery.queryByUserName(userName);
	}
	
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	public CustomerEntity queryCustomerByUserName(String userName){
		
		CustomerQuery customerQuery = new CustomerQuery();
		return customerQuery.queryCustomerByUserName(userName);
	}

}
