package com.hengyuan.hicash.domain.service.himoney;

import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.himoney.AuthenticationInfoQuery;
import com.hengyuan.hicash.domain.query.himoney.LineCreditInfoQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.himoney.AuthenticationEntity;
import com.hengyuan.hicash.entity.himoney.LineCreditInfoEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.himoney.AuthenticationInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.himoney.AuthenticationInfoResp;
import com.hengyuan.hicash.utils.HRIdentityUtil;


/**
 * 认证信息service
 * 
 * @author xuexin
 * @create 2017年7月14日 09:53:10
 *
 */
public class AuthenticationInfoQueryService implements RespService{

	private CustomerQuery customerQuery = new CustomerQuery();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		String resultCode = "";
		AuthenticationInfoReq req = (AuthenticationInfoReq)parmRequest;
		AuthenticationInfoResp resp = new AuthenticationInfoResp();
		try {
			//【注解1】判断用户是否为22及22岁以下，如果是则不需要展示学信网认证信息，如果是已经认证过的，则需要 展示
			 boolean isXuexin = false; 
			 CustomerEntity customer=customerQuery.queryCustByUser(req.getUserName());
			 if(customer!=null){
				 int age=HRIdentityUtil.getAppUseAge(customer.getIdentityNo());
				 if(age <= 22 || customer.getCustType().equals("KHL1")){
					 isXuexin = true;
				 }
			 }
	
			 List<AuthenticationEntity> list = getAuthenticationInfoList(req,isXuexin);
			 //额度信息
			 LineCreditInfoEntity creditEntity = getLineCreditInfo(req,isXuexin);
			 if(list != null && list.size() > 0){
				 resp.setList(list);
				 resp.setTotal_quota(String.valueOf(creditEntity.getTotalQuota()));
				 resp.setTotal_score(String.valueOf(creditEntity.getTotalScore()));
				 resultCode = ResultCodes.NORMAL;
			 }else{
				 resultCode = ResultCodes.MKTAPP_BUSICITY_NORESULT;
			 }
		} catch (Exception e) {
			e.printStackTrace();
			resultCode = ResultCodes.MKTAPP_BUSICITY_NORESULT;
		} finally {
			ConnManager.closeConn();
		}
		resp.setResultCode(resultCode);
		return resp;
	}

	/**
	 * 获取认证信息
	 * @param req
	 * @return
	 */
	public List<AuthenticationEntity> getAuthenticationInfoList(AuthenticationInfoReq req,boolean isXuexin){
		AuthenticationInfoQuery dao = new AuthenticationInfoQuery();
		return dao.queryAuthenticationInfoByAppNoUserName(req,isXuexin);
	}
	

	/**
	 *	获取用户额度信息
	 * @param req
	 * @return
	 */
	public LineCreditInfoEntity getLineCreditInfo(AuthenticationInfoReq req,boolean isXuexin){
		LineCreditInfoQuery dao = new LineCreditInfoQuery();
		return dao.queryLineCreditInfo(req,isXuexin).get(0);
	}
	
}
