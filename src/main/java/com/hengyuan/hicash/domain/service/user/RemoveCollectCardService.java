package com.hengyuan.hicash.domain.service.user;

import java.util.List;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.CollectCardEvent;
import com.hengyuan.hicash.domain.query.user.CollectAccountQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CollectAccountEntity;
import com.hengyuan.hicash.exception.RemoveCardException;
import com.hengyuan.hicash.exception.UpdateDefaultCardException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.RemoveCollectCardReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.RemoveCollectCardResp;

/**
 * 删除银行卡 逻辑处理
 * 
 * @author Cary.Liu
 * @create 2014-08-15
 * 
 */
public class RemoveCollectCardService implements RespService {

	private CollectAccountQuery accountQuery = new CollectAccountQuery();
	private String resultCode = "";

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		RemoveCollectCardReq cardReq = (RemoveCollectCardReq) parmRequest;
		RemoveCollectCardResp cardResp = new RemoveCollectCardResp();

		try {
			ConnManager.beginTransaction();
			CollectAccountEntity accountEntity = queryCardAccount(cardReq.getUserName(), cardReq.getCardId());
			if (accountEntity != null) {
				String defaultCard = accountEntity.getDefaultCard();
				if (Consts.DEFAULT_CARD_YES.trim().equals(defaultCard.trim())) {
					removeCard(cardReq.getCardId());
					String cardId = queryCardOrderTime(cardReq.getUserName());
					if(cardId != null){
						updateDefaultY(cardId);
					}
					
				} else {
					removeCard(cardReq.getCardId());
				}
				ConnManager.commit();
				resultCode = ResultCodes.NORMAL;
			} else {
				resultCode = ResultCodes.REMOVE_CARD_ACCOUNT_NOTFOUND;
			}
		} catch (RemoveCardException e){
			resultCode = ResultCodes.REMOVE_CARD_ACCOUNT_EXCEPTION;
			ConnManager.rollback();
		} catch (UpdateDefaultCardException e){
			resultCode = ResultCodes.REMOVE_CARD_DEFAULT_FAIL;
			ConnManager.rollback();
		} catch (Exception e) {
			resultCode = ResultCodes.REMOVE_CARD_ACCOUNT_EXCEPTION;
			ConnManager.rollback();
		}finally{
			ConnManager.closeConn();
		}

		cardResp.setResultCode(resultCode);
		return cardResp;
	}

	/**
	 * 查询银行卡信息
	 * 
	 * @param userName
	 * @param cardId
	 * @return
	 */
	public CollectAccountEntity queryCardAccount(String userName, String cardId) {

		return accountQuery.queryCardAccount(userName, cardId);
	}

	/**
	 * 根据主键删除银行卡
	 * 
	 * @param cardId
	 * @throws RemoveCardException
	 */
	public void removeCard(String cardId) throws RemoveCardException {

		CollectCardEvent cardEvent = new CollectCardEvent();
		cardEvent.removeCard(cardId);
	}
	
	/**
	 * 查询用户的最近银行卡Id
	 * @param userName
	 * @return
	 */
	public String queryCardOrderTime(String userName){
		List<CollectAccountEntity> entity = accountQuery.queryCardByUserName(userName);
		if(!entity.isEmpty()&&entity.size()>0){
			return entity.get(0).getCardId();
		}
		return null;
	}
	
	/**
	 * 更新用户的默认银行卡
	 * @param cardId
	 * @throws UpdateDefaultCardException
	 */
	public void updateDefaultY(String cardId) throws UpdateDefaultCardException{
		
		CollectCardEvent cardEvent = new CollectCardEvent();
		cardEvent.updateDefaultCardY(cardId);
	}

}
