package com.hengyuan.hicash.domain.event.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.RemoveCardException;
import com.hengyuan.hicash.exception.SaveCardException;
import com.hengyuan.hicash.exception.UpdateCardException;
import com.hengyuan.hicash.exception.UpdateDefaultCardException;
import com.hengyuan.hicash.parameters.request.param.CardParam;
import com.hengyuan.hicash.parameters.request.user.CollectCardReq;
import com.hengyuan.hicash.parameters.request.user.UpdateBankCardReq;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 记录收款账户信息Dao
 * 
 * @author Cary.Liu
 * @create date 2014-07-25
 */
public class CollectCardEvent{
	
	private static Logger logger = Logger.getLogger(CollectCardEvent.class);

	/**
	 * 保存收款账户信息
	 * 
	 * @param cardReq
	 * @return
	 * @throws SaveCardException 
	 */
	public void saveCollectCard(CollectCardReq req) throws SaveCardException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("REALNAME", req.getRealName());
		setMap.put("USERNAME", req.getUserName());
		setMap.put("CARD_TYPE", req.getCardType());
		setMap.put("BANK", req.getBank());
		setMap.put("PROVINCE", req.getProvince());
		setMap.put("CITY", req.getCity());
		setMap.put("OPEN_BANK_NUM", req.getOpenBank()); 
		setMap.put("OPEN_BANK", req.getOpenBankName());
		setMap.put("CARD_NUM", req.getCardNum());
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("CREATE_USER", req.getUserName());
		setMap.put("default_card", req.getDefaultCard());
		if(!StringUtils.isEmpty(req.getAreaCode())){
			setMap.put("AREA", req.getAreaCode());
		}
		if(!StringUtils.isEmpty(req.getMobile())){
			setMap.put("bank_mobile", req.getMobile());
		}
			
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.CUST_USER_CARD, setMap);
		//记录日志
				RecordUtils.writeAction(logger, req.getUuid(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new SaveCardException();
		}

	}
	
	/**
	 * 保存收款账户信息
	 * 
	 * @param cardReq
	 * @return
	 * @throws SaveCardException 
	 */
	public void saveUserCardByApp(CardParam param) throws SaveCardException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("USERNAME", param.getUserName());
		setMap.put("REALNAME", param.getRealName());
		setMap.put("CARD_TYPE", Consts.CARD_TYPE_JJKT); //暂支持借记卡
		setMap.put("BANK", param.getBank());
		setMap.put("PROVINCE", param.getProvince());
		setMap.put("CITY", param.getCity());
		setMap.put("OPEN_BANK_NUM", param.getOpenBankBranch()); 
		setMap.put("OPEN_BANK", param.getOpenBankBranchName());
		setMap.put("CARD_NUM", param.getBankCard());
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("CREATE_USER", param.getUserName());
		setMap.put("default_card", param.getIsDefaultCard());
		if(!StringUtils.isEmpty(param.getArea())){
			setMap.put("AREA", param.getArea());
		}
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.CUST_USER_CARD, setMap);
		//记录日志
		RecordUtils.writeAction(logger, null, updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new SaveCardException();
		}

	}

	/**
	 * 修改收款账户信息
	 * 
	 * @param cardReq
	 * @return
	 * @throws UpdateCardException 
	 */
	public void updateCollectCard(CollectCardReq req) throws UpdateCardException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("CARD_TYPE", req.getCardType());
		setMap.put("BANK", req.getBank());
		setMap.put("PROVINCE", req.getProvince());
		setMap.put("CITY", req.getCity());
		setMap.put("OPEN_BANK_NUM", req.getOpenBank()); 
		setMap.put("OPEN_BANK", req.getOpenBankName());
		setMap.put("CARD_NUM", req.getCardNum());
		setMap.put("update_date",DateUtils.getDateStr(new Date()));
		setMap.put("update_user", req.getUserName());
		setMap.put("default_card", req.getDefaultCard());
		if(!StringUtils.isEmpty(req.getAreaCode())){
			setMap.put("AREA", req.getAreaCode());
		}
		if(!StringUtils.isEmpty(req.getMobile())){
			setMap.put("bank_mobile", req.getMobile());
		}
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", req.getUserName());
		whereMap.put("CARD_NUM", req.getCardNum());
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER_CARD, setMap, whereMap);
		
		//记录日志
				RecordUtils.writeAction(logger, req.getUuid(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateCardException();
		}
		
	}
	
	/**
	 * 修改收款账户信息 hicashApp
	 * 
	 * @param cardReq
	 * @return
	 * @throws UpdateCardException 
	 */
	public void updateUserCardByApp(CardParam param) throws UpdateCardException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("CARD_TYPE", Consts.CARD_TYPE_JJKT); //暂支持借记卡
		setMap.put("BANK", param.getBank());
		setMap.put("PROVINCE", param.getProvince());
		setMap.put("CITY", param.getCity());
		setMap.put("OPEN_BANK_NUM", param.getOpenBankBranch()); 
		setMap.put("OPEN_BANK", param.getOpenBankBranchName());
		setMap.put("CARD_NUM", param.getBankCard());
		setMap.put("update_date",DateUtils.getDateStr(new Date()));
		setMap.put("update_user", param.getUserName());
		setMap.put("default_card", param.getIsDefaultCard());
		
		if(!StringUtils.isEmpty(param.getArea())){
			setMap.put("AREA", param.getArea());
		}
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", param.getUserName());
		whereMap.put("CARD_NUM", param.getBankCard());
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER_CARD, setMap, whereMap);
		
		//记录日志
		RecordUtils.writeAction(logger, null, updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateCardException();
		}
		
	}
	
	/**
	 * 修改收款账户信息
	 * 
	 * @param cardReq
	 * @return
	 * @throws UpdateCardException 
	 */
	public void updateCardByIdAndUser(UpdateBankCardReq req) throws UpdateCardException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
//		setMap.put("CARD_TYPE", req.getCardType());
		setMap.put("BANK", req.getBank());
		setMap.put("PROVINCE", req.getProvince());
		setMap.put("CITY", req.getCity());
		setMap.put("OPEN_BANK_NUM", req.getOpenBank()); 
		setMap.put("OPEN_BANK", req.getOpenBankName());
		setMap.put("CARD_NUM", req.getCardNum());
		setMap.put("update_date",DateUtils.getDateStr(new Date()));
		setMap.put("update_user", req.getUserName());
//		setMap.put("default_card", req.getDefaultCard());
		if(!StringUtils.isEmpty(req.getAreaCode())){
			setMap.put("AREA", req.getAreaCode());
		}
		
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", req.getUserName());
		whereMap.put("ID", req.getCardId());
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER_CARD, setMap, whereMap);
		
		//记录日志
		RecordUtils.writeAction(logger, req.getUuid(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateCardException();
		}
		
	}
	
	/**
	 * 更改用户的默认卡设置(设置该用户的卡为非默认卡)
	 * @param req
	 * @throws UpdateDefaultCardException 
	 */
	public void updateDefaultCard(CollectCardReq req){
		// throws UpdateDefaultCardException
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("default_card", Consts.DEFAULT_CARD_NO);
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", req.getUserName());
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER_CARD, setMap, whereMap);
		
		//记录日志
				RecordUtils.writeAction(logger, req.getUuid(), updateSql);
		ConnManager.executeUpdate(updateSql);
//		if(ConnManager.executeUpdate(updateSql) <= 0){
//			throw new UpdateDefaultCardException();
//		}
		
	}
	/**
	 * 更改用户的默认卡设置(设置该用户的卡为默认卡)
	 * @param req
	 * @throws UpdateDefaultCardException 
	 */
	public void updateDefaultCardY(String cardId)throws UpdateDefaultCardException{
		 
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("default_card", Consts.DEFAULT_CARD_YES);
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("ID", cardId);
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER_CARD, setMap, whereMap);
		
		//记录日志
		RecordUtils.writeAction(logger, cardId, updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateDefaultCardException();
		}
		
	}
	
	/**
	 * 更改用户的默认卡设置(设置该用户的卡为非默认卡)
	 * @param req
	 * @throws UpdateDefaultCardException 
	 */
	public void updateDefaultCardByApp(String userName){
		// throws UpdateDefaultCardException
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("default_card", Consts.DEFAULT_CARD_NO);
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER_CARD, setMap, whereMap);
		
		ConnManager.executeUpdate(updateSql);
	}
	
	
	/**
	 * 根据主键删除银行卡
	 * @param cardId
	 * @throws RemoveCardException  
	 */
	public void removeCard(String cardId) throws RemoveCardException {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("ID", cardId);
		
		String updateSql = MapAssemForSql.getDeleteSql(TableConsts.CUST_USER_CARD,whereMap);
		
		//记录日志
				RecordUtils.writeAction(logger, cardId, updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new RemoveCardException();
		}
	}

}
