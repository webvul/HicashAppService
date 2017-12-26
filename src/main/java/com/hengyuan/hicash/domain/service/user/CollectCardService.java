package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.CollectCardEvent;
import com.hengyuan.hicash.domain.query.param.BankQuery;
import com.hengyuan.hicash.domain.query.param.SysBankInfoQuery;
import com.hengyuan.hicash.domain.query.user.AccountIdQuery;
import com.hengyuan.hicash.domain.query.user.CollectAccountQuery;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.BankBranchEntity;
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.entity.user.Bank;
import com.hengyuan.hicash.exception.SaveCardException;
import com.hengyuan.hicash.exception.UpdateCardException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CollectCardReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollectCardResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 记录收款账户信息处理类
 * 
 * @author Cary.Liu
 * @create date 2014-07-25
 */
public class CollectCardService implements RespService {
	
	private static Logger logger = Logger.getLogger(CollectCardService.class);

	private CollectCardEvent cardEvent = new CollectCardEvent();
	private CollectAccountQuery accountQuery = new CollectAccountQuery();
	private AccountIdQuery idQuery = new AccountIdQuery();
	private String resultCode = "";
	private String cardType = "";
	private String bank = "";
	private String cardId = "";
	private String cardNum = "";

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CollectCardReq cardReq = (CollectCardReq) parmRequest;
		CollectCardResp cardResp = new CollectCardResp();

		try {
			
			if(isUserExist(cardReq.getUserName())){
				
				if(isBankFalg(cardReq)){
					//获取支行对应的名称
					cardReq.setOpenBankName(queryBankInfoName(cardReq.getOpenBank()));
					
					boolean isExist = accountQuery.queryIdEntityNoExist(cardReq.getUserName(), cardReq.getCardNum());
					
					if(isExist){
						cardEvent.updateCollectCard(cardReq);
					}else{
						
						cardId = idQuery.queryUpdateId();
						if(Consts.DEFAULT_CARD_YES.equals(cardReq.getDefaultCard().trim())){
							cardEvent.updateDefaultCard(cardReq);
						}
						cardEvent.saveCollectCard(cardReq);
					}
					for (Bank b : Bank.values()) {
						if(b.name().equals(cardReq.getBank())){
							cardResp.setBankName(b.getDispValue());
							break;
						}
					}
					cardType = cardReq.getCardType();
					bank = cardReq.getBank();
					cardNum = cardReq.getCardNum();
					resultCode = ResultCodes.NORMAL;
					
				}else{
					resultCode = ResultCodes.CARD_BANK_ILLEGAL;
				}
				
			}else{
				resultCode = ResultCodes.CARD_SAVE_USEREXIST;
			}
			
			
		} catch (UpdateCardException e) {
			
			resultCode = ResultCodes.CARD_UPDATE_EXCEPTION;
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CARD_UPDATE_EXCEPTION, e);
		} catch (SaveCardException e) {
			
			resultCode = ResultCodes.CARD_SAVE_EXCEPTION;
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CARD_SAVE_EXCEPTION, e);
		} catch (Exception e) {
			
			resultCode = ResultCodes.CARD_EXCEPTION;
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CARD_EXCEPTION, e);
		}finally{
			ConnManager.closeConn();
		}

//		catch (UpdateDefaultCardException e) {
//		resultCode = ResultCodes.CARD_DEFAULT_CARD_EXCEPTION;
//		ConnManager.rollback();
		cardResp.setCardNum(cardNum);
		cardResp.setCardType(cardType);
		cardResp.setBank(bank);
		cardResp.setCardId(cardId);
		cardResp.setResultCode(resultCode);
		return cardResp;
	}
	
	/**
	 * 验证用户是否存在
	 * @param userName
	 * @return
	 */
	public boolean isUserExist(String userName){
		
		CustUserQuery custUserQuery = new CustUserQuery();
		return custUserQuery.isUserExist(userName);
	}
	
	/**
	 * 验证开户行和支行是否匹配
	 * @param cardReq
	 * @return
	 */
	public boolean isBankFalg(CollectCardReq cardReq){
		
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
