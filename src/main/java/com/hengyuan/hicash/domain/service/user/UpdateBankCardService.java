package com.hengyuan.hicash.domain.service.user;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.CollectCardEvent;
import com.hengyuan.hicash.domain.query.param.BankQuery;
import com.hengyuan.hicash.domain.query.param.SysBankInfoQuery;
import com.hengyuan.hicash.domain.query.user.CollectAccountQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.BankBranchEntity;
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.entity.user.CollectAccountEntity;
import com.hengyuan.hicash.exception.UpdateCardException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.UpdateBankCardReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.UpdateBankCardResp;

/**
 * 修改银行卡 处理逻辑
 * 
 * @author Cary.Liu
 * @create 2014-09-29
 *
 */
public class UpdateBankCardService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		UpdateBankCardReq cardReq = (UpdateBankCardReq)parmRequest;
		UpdateBankCardResp cardResp = new UpdateBankCardResp();
		
		try {
			
			if(isBankFalg(cardReq)){
				
				if(cardExistFlag(cardReq)){
					//获取支行对应的名称
					cardReq.setOpenBankName(queryBankInfoName(cardReq.getOpenBank()));
					updateCardInfo(cardReq);
					resultCode = ResultCodes.NORMAL;
					
				}else{
					resultCode = ResultCodes.UPDATEBANKCARD_CARD_NOEXIST;
				}
				
			}else{
				resultCode = ResultCodes.CARD_BANK_ILLEGAL;
			}
			
		} catch (UpdateCardException e){
			resultCode = ResultCodes.UPDATEBANKCARD_UPDATE_EXCEPTION;
		} catch (Exception e) {
			resultCode = ResultCodes.UPDATEBANKCARD_EXCEPTION;
		} finally {
			ConnManager.closeConn();
		}
		
		cardResp.setResultCode(resultCode);
		return cardResp;
	}
	
	/**
	 * 根据用户名和银行卡主键查询卡信息
	 * @param cardReq
	 * @return
	 */
	public boolean cardExistFlag (UpdateBankCardReq cardReq){
		
		CollectAccountQuery accountQuery = new CollectAccountQuery();
		CollectAccountEntity entity = accountQuery.queryCardAccount(cardReq.getUserName(), cardReq.getCardId());
		if(entity != null){
			return true;
		}
		return false;
	}

	/**
	 * 修改银行卡信息
	 * @param cardReq
	 * @throws UpdateCardException
	 */
	public void updateCardInfo(UpdateBankCardReq cardReq) throws UpdateCardException{
		
		CollectCardEvent cardEvent = new CollectCardEvent();
		cardEvent.updateCardByIdAndUser(cardReq);
	}
	
	/**
	 * 验证开户行和支行是否匹配
	 * @param cardReq
	 * @return
	 */
	public boolean isBankFalg(UpdateBankCardReq cardReq){
		
		BankQuery bankQuery = new BankQuery();
		BankEntity bank = bankQuery.queryBankName(cardReq.getBank());
		if(bank != null){
			SysBankInfoQuery bankinfoQuery = new SysBankInfoQuery();
			BankBranchEntity bankInfo = bankinfoQuery.queryBankInfoByFatherCode(cardReq.getOpenBank(), bank.getBankNo());
			if(bankInfo != null){
				return true;
			}else{
				return false;
			}
		}
		
		return false;
	}
	
	/**
	 * 获取支行名称
	 * @param openBank
	 * @return
	 */
	public String queryBankInfoName(String openBank){
		SysBankInfoQuery bankInfoQuery = new SysBankInfoQuery();
		
		BankBranchEntity bankInfo = bankInfoQuery.queryBankInfoByNum(openBank);
		if(bankInfo != null){
			return bankInfo.getBankName();
		}
		return "";
	}
	
}
