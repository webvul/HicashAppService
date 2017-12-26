package com.hengyuan.hicash.domain.service.himoney;

import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.himoney.AuthenticationInfoUpdateEvent;
import com.hengyuan.hicash.domain.event.himoney.LineCreditInfoUpdateEvent;
import com.hengyuan.hicash.domain.query.himoney.AuthenticationBasicsQuery;
import com.hengyuan.hicash.domain.query.himoney.AuthenticationInfoTableQuery;
import com.hengyuan.hicash.domain.query.himoney.LineCreditInfoQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.himoney.AuthenticationBasicsEntity;
import com.hengyuan.hicash.entity.himoney.AuthenticationInfoEntity;
import com.hengyuan.hicash.entity.himoney.LineCreditInfoEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.himoney.AuthenticationInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.himoney.AuthenticationInfoResp;
import com.hengyuan.hicash.utils.HRIdentityUtil;

/**
 * 提交认证项
 * 
 * @author xuexin
 * @create 2017年7月18日 16:51:37
 */
public class AuthenticationInfoUpdateService implements RespService {
	
	private static Logger logger = Logger.getLogger(AuthenticationInfoUpdateService.class);

	private AuthenticationInfoUpdateEvent event = new AuthenticationInfoUpdateEvent();
	
	private AuthenticationInfoTableQuery infoQuery =  new AuthenticationInfoTableQuery();
	
	private AuthenticationBasicsQuery basicsQuery =  new AuthenticationBasicsQuery();

	private LineCreditInfoQuery lineCreditInfoQuery =  new LineCreditInfoQuery();
	
	private LineCreditInfoUpdateEvent lineCreditEvent = new LineCreditInfoUpdateEvent();
	
	private CustomerQuery customerQuery = new CustomerQuery();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		AuthenticationInfoReq req= (AuthenticationInfoReq) parmRequest;
		AuthenticationInfoResp resp = new AuthenticationInfoResp();
		String resultCode = "";
		try {
			
			//查询用户是否已经提交过认证项
			List<AuthenticationInfoEntity> list = infoQuery.queryAuthenticationInfo(req);
			if(list.size() < 1 ){
				event.saveAuthTicaInfo(req); //保存用户认证项
				
				AuthenticationBasicsEntity basicsEntity = basicsQuery.queryApplicationPayById(req);//查询认证项默认额度和分数
				
				 boolean isXuexin = false; 
				 CustomerEntity customer = customerQuery.queryCustByUser(req.getUserName());
				 if(customer!=null){
					 int age=HRIdentityUtil.getAppUseAge(customer.getIdentityNo());
					 if(age <= 22  || customer.getCustType().equals("KHL1")){
						 isXuexin = true;
					 }
				 }
				
				LineCreditInfoEntity lineCreditEntity = lineCreditInfoQuery.queryLineCreditInfo(req,isXuexin).get(0);//查询当前用户是否存在认证信息
				
				lineCreditEntity.setTotalQuota(lineCreditEntity.getTotalQuota()+basicsEntity.getQuota());//更新额度
				lineCreditEntity.setTotalScore(lineCreditEntity.getTotalScore()+basicsEntity.getScore());//更新分数
				
				//System.out.println("--------------------"+(!lineCreditEntity.getAppNo().equals("1") && !lineCreditEntity.getAppNo().equals("2")));
				if(!lineCreditEntity.getAppNo().equals("1") && !lineCreditEntity.getAppNo().equals("2")){ //存在则更新
					lineCreditEvent.updateLineCreditInfo(lineCreditEntity);
				}else{//不存在则新增
					lineCreditEntity.setAppNo(req.getTempAppNo());
					lineCreditEvent.saveLineCreditInfo(lineCreditEntity);
				}
				resultCode = ResultCodes.NORMAL;
			}else{
				resultCode = ResultCodes.EXISTS_AUTHENTICATION;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnManager.closeConn();
		}
		resp.setResultCode(resultCode);
		return resp;
	}

}
