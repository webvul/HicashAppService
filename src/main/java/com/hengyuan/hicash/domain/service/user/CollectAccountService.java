package com.hengyuan.hicash.domain.service.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.param.BankQuery;
import com.hengyuan.hicash.domain.query.user.CollectAccountQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.entity.user.CollectAccountEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CollectAccountReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollectAccountResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 收款账户信息查询 业务处理
 * 
 * @author Cary.Liu
 * @create date 2014-07-26
 * 
 */
public class CollectAccountService implements RespService {
	
	
	private static Logger logger = Logger.getLogger(CollectAccountService.class);

	private CollectAccountQuery accountQuery = new CollectAccountQuery();
//	private RecordExistQuery existQuery = new RecordExistQuery();
//	private ApplicationQuery applicationQuery = new ApplicationQuery();
	private String resultCode = "";

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CollectAccountReq accountReq = (CollectAccountReq) parmRequest;
		CollectAccountResp accountResp = new CollectAccountResp();

		try {
					
			List<CollectAccountEntity> entitys = 
					accountQuery.queryCardByUserName(accountReq.getUserName());
			
			if(entitys != null && entitys.size() > 0){
				List<CollectAccountEntity> accountEntitys = new ArrayList<CollectAccountEntity>();
				BankQuery bankQuery = new BankQuery();
				for (CollectAccountEntity collectAccountEntity : entitys) {
					//获取开户行
					BankEntity bankEntity = bankQuery.queryBankName(collectAccountEntity.getBank());
					if(bankEntity != null){
						collectAccountEntity.setBankName(bankEntity.getBankName());
					}else{
						collectAccountEntity.setBankName("");
					}
					accountEntitys.add(collectAccountEntity);
				}
				accountResp.setAccountList(accountEntitys);
				resultCode = ResultCodes.NORMAL;
			}else{
				resultCode = ResultCodes.CARD_NOT_FOUND;
			}
			
		} catch (Exception e) {
			resultCode = ResultCodes.CARD_QUERY_EXCEPTION;
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CARD_QUERY_EXCEPTION, e);
		}finally{
			ConnManager.closeConn();
		}

		accountResp.setResultCode(resultCode);
		return accountResp;
	}
	
}
