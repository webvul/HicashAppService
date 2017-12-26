package com.hengyuan.hicash.domain.service.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.param.WithHoldPardEvent;
import com.hengyuan.hicash.domain.event.user.CollectCardEvent;
import com.hengyuan.hicash.domain.event.user.NoahValidIdentityEvent;
import com.hengyuan.hicash.domain.query.app.NoahValidIdentityQuery;
import com.hengyuan.hicash.domain.query.param.BankQuery;
import com.hengyuan.hicash.domain.query.param.SysBankInfoQuery;
import com.hengyuan.hicash.domain.query.param.WithholdPartQuery;
import com.hengyuan.hicash.domain.query.user.CollectAccountQuery;
import com.hengyuan.hicash.domain.query.user.ServiceConfigQuery;
import com.hengyuan.hicash.entity.app.NoahValidIdentityEntity;
import com.hengyuan.hicash.entity.param.BankBranchEntity;
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.entity.param.WhichPartRecord;
import com.hengyuan.hicash.entity.user.ServiceConfigEntity;
import com.hengyuan.hicash.exception.DInputProxyBankException;
import com.hengyuan.hicash.exception.SaveCardException;
import com.hengyuan.hicash.exception.UpdateCardException;
import com.hengyuan.hicash.parameters.request.user.CollectCardReq;
import com.hengyuan.hicash.parameters.request.user.DoubleIdentityValReq;
import com.hengyuan.hicash.parameters.response.user.BankCardInputAppResp;
import com.hengyuan.hicash.parameters.response.user.DoubleIdentityValResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.HttpRemotePost;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;





public class DoubleIdentityValService {
	private static Logger logger = Logger
			.getLogger(DoubleIdentityValService.class);

	/**
	 * 调用诺亚接口
	 * 
	 * @param doubleValReq
	 * @return
	 */
	public DoubleIdentityValResp noahValidResult(
			DoubleIdentityValReq doubleValReq) {

		NoahValidIdentityQuery noahValidQuery = new NoahValidIdentityQuery();

		// 查询是否在诺亚验证成功过
		NoahValidIdentityEntity noahValidEntity = noahValidQuery.queryNoahSucc(
				doubleValReq.getAccountNo(), doubleValReq.getAccountName(),
				doubleValReq.getCertNo(), doubleValReq.getMobileNo());

		DoubleIdentityValResp doubleResp = new DoubleIdentityValResp();

		/** 未验证通过 */
		if (noahValidEntity.getRespCode() == null
				|| !"01".equals(noahValidEntity.getRespCode())) {
			try {
               logger.info("用户"+doubleValReq.getUserName()+":开始调用诺亚");
				Map<String, String> parmMap = new HashMap<String, String>();
				parmMap.put("accNo", doubleValReq.getAccountNo()); // 银行帐号
				parmMap.put("accName", doubleValReq.getAccountName()); // 银行户名
				parmMap.put("certNo", doubleValReq.getCertNo()); // 证件号
				parmMap.put("mobile", doubleValReq.getMobileNo()); // 手机号
				parmMap.put("bankNo", doubleValReq.getBankCode()); // 银行编码
				parmMap.put("userName", doubleValReq.getUserName()); // 用户名
				parmMap.put("appNo", doubleValReq.getAppNo()); // 流水号

				ServiceConfigQuery configQuery = new ServiceConfigQuery();
				ServiceConfigEntity serviceConfigEntity = configQuery
						.queryServiceByCode(Consts.SERVICE_NY001);
				String httpResp = HttpRemotePost.sendHttp(
						serviceConfigEntity.getServiceUrl(), parmMap);

				// 诺亚返回结果
				BankCardInputAppResp bankCardInputAppResp = new Gson()
						.fromJson(httpResp,
								new TypeToken<BankCardInputAppResp>() {
								}.getType());
				 logger.info("用户"+doubleValReq.getUserName()+":诺亚返回结果："+new Gson().toJson(bankCardInputAppResp));
				
				 if (bankCardInputAppResp.getRespCode().equals("01")) {// 如果验证成功
					
					doubleResp= updateDInputApp(doubleValReq);
				} else {// 诺亚验证失败
					
					if (StringUtils
							.isEmpty(bankCardInputAppResp.getTradeDesc())) {
						doubleResp.setResultMsg(bankCardInputAppResp
								.getRespMsg());//如果诺亚银行没返回,提示自定义信息
						doubleResp.setResultCode(ResultCodes.NOAH_VALID_FAIL);

					} else {
						doubleResp.setResultMsg(bankCardInputAppResp
								.getTradeDesc());//如果诺亚银行有返回信息,提示诺亚返回的信息
						doubleResp.setResultCode(ResultCodes.NOAH_VALID_FAIL);
					}
				}
			} catch (Exception e) {
				logger.error("调用诺亚接口异常:", e);
				doubleResp.setResultMsg("验证失败");
				doubleResp.setResultCode(ResultCodes.NOAH_VALID_FAIL);
			}

		} else {// 验证通过

			doubleResp= updateDInputApp(doubleValReq);
			
		}
		return doubleResp;// resultCode=normal,诺亚认证成功

	}

	/**
	 * 插入代扣记录表
	 * 
	 * @param doubleValReq
	 * @return
	 */
	public DoubleIdentityValResp updateDInputApp(DoubleIdentityValReq doubleValReq) {

		// 

		DoubleIdentityValResp doubleResp = new DoubleIdentityValResp();
		
		try{

			CollectCardReq collectCardReq=new CollectCardReq();
			collectCardReq.setUserName(doubleValReq.getUserName());//用户名
			collectCardReq.setRealName(doubleValReq.getAccountName());//持卡人姓名
			collectCardReq.setCardType("JJKT");//卡类型
			collectCardReq.setBank(doubleValReq.getBankCode());//开户行
			collectCardReq.setProvince(doubleValReq.getDkProvince());//开户城市-省
			collectCardReq.setCity(doubleValReq.getDkCity());//开户城市-市
			collectCardReq.setAreaCode(doubleValReq.getDkAreaCode());//开户城市-区
			collectCardReq.setOpenBank(doubleValReq.getDkBankBranch());//开户支行
			collectCardReq.setCardNum(doubleValReq.getAccountNo());//银行卡号
			collectCardReq.setDefaultCard("Y");//是否设置为默认卡
			collectCardReq.setMobile(doubleValReq.getMobileNo());//预留手机号
			collectCardReq.setUuid(UUID.randomUUID().toString());
			DoubleIdentityValResp bankCardInputAppResp= updateUserColleCard(collectCardReq);
			
			//如果更改收款银行成功
			if (bankCardInputAppResp.getResultCode().equals("1")) {
				ConnManager.beginTransaction();
				// 插入验证记录表
				withholdPartInsert(doubleValReq.getUserName(),doubleValReq.getWhichPart(),doubleValReq.getAccountNo());
				
				if(!StringUtils.isEmpty(doubleValReq.getAppNo())){
				//更新验证标志
				updateInput(doubleValReq.getAccountNo(),doubleValReq.getBankCode(),doubleValReq.getAppNo(),doubleValReq.getWhichPart());
				
				}
				ConnManager.commit();
				doubleResp.setResultCode(ResultCodes.NORMAL);
				doubleResp.setResultMsg("验证成功！");	
			
			}else{
				
				doubleResp.setResultCode(ResultCodes.NOAH_VALID_FAIL);
				doubleResp.setResultMsg(bankCardInputAppResp.getResultMsg());	
				logger.info("更改用户收款卡号失败"+doubleValReq.getUserName());
			}
		
		} catch (DInputProxyBankException e) {
			logger.error("验证银行卡失败"+doubleValReq.getUserName(), e);
			logger.info("验证银行卡失败"+doubleValReq.getUserName(), e);
			doubleResp.setResultCode(ResultCodes.NOAH_VALID_FAIL);
			doubleResp.setResultMsg("验证失败！");
			ConnManager.rollback();
		
		}catch(Exception e){
			logger.error("更改验证,收款银行卡失败"+doubleValReq.getUserName(), e);
			logger.info("更改验证,收款银行卡失败info"+doubleValReq.getUserName(), e);
			doubleResp.setResultCode(ResultCodes.NOAH_VALID_FAIL);
			doubleResp.setResultMsg("验证失败！");
			ConnManager.rollback();
		}finally{
			ConnManager.closeConn();
		}

		return doubleResp;
	}

	// 更新验证银行结果到d——input_app
	public void updateInput(String accountNo, String bankCode, String appNo,	String validOk) throws DInputProxyBankException { // 更新代扣银行卡至d_input_app表

		try {

			NoahValidIdentityEvent noahValidEvent = new NoahValidIdentityEvent();

			noahValidEvent.updateInputByAppNo(accountNo, bankCode, appNo,
					validOk);
		}catch(Exception e){
			throw new DInputProxyBankException();
		}
		

	}

	/**
	 * 增加验证记录
	 * 
	 * @param userName
	 * @param whichPart
	 *            ,代扣方:ZTCM,NOAH
	 * @throws DInputProxyBankException 
	 */
	public void withholdPartInsert(String userName, String whichPart,String accountNo) throws DInputProxyBankException {

		WithholdPartQuery whichQuery = new WithholdPartQuery();

		List<WhichPartRecord> listPart = whichQuery.queryWhichPart(userName,
				whichPart,accountNo);
     
		// 如果已经存在，则不添加
		if (listPart.size() < 1) {
			 logger.info(userName+"增加记录表");
			Map<String, Object> inputMap = new HashMap<String, Object>();
			inputMap.put("USERNAME", userName);
			inputMap.put("withhold_part", whichPart);
			inputMap.put("create_time", DateUtils.getDateStr(new Date()));
			inputMap.put("bank_card_num", accountNo);
			WithHoldPardEvent withHoldEvent = new WithHoldPardEvent();
			withHoldEvent.withholdPartInsert(inputMap);
			
		}

	}
	
/**
 * 更改用户收款银行卡
 * @param cardReq
 * @return
 */
	public DoubleIdentityValResp updateUserColleCard(CollectCardReq cardReq) {
		
		CollectAccountQuery accountQuery = new CollectAccountQuery();
		CollectCardEvent cardEvent = new CollectCardEvent();
		
		DoubleIdentityValResp doubleResp = new DoubleIdentityValResp();
		
		String resultCode="";

		try {
				//判断支行和父行是否匹配
				if(isBankFalg(cardReq.getBank(),cardReq.getOpenBank())){
						//获取支行对应的名称
						cardReq.setOpenBankName(queryBankInfoName(cardReq.getOpenBank()));
					
						//根据用户名,银行卡号查询是否存在
						boolean isExist = accountQuery.queryIdEntityNoExist(cardReq.getUserName(), cardReq.getCardNum());
						
						ConnManager.beginTransaction();
						
						if(Consts.DEFAULT_CARD_YES.equals(cardReq.getDefaultCard().trim())){//如果是默认卡
							cardEvent.updateDefaultCard(cardReq);//把用户所有卡修改为非默认
						}
						
						if(isExist){//如果已经存在,则更改
							
							cardEvent.updateCollectCard(cardReq);
							
						}else{//不存在,则增加
							
							
							cardEvent.saveCollectCard(cardReq);
						}
						
						
					
						resultCode = ResultCodes.NORMAL;
						
						ConnManager.commit();

				}else{
					resultCode = ResultCodes.CARD_BANK_ILLEGAL;
				}
				
	
			
		} catch (UpdateCardException e) {
			ConnManager.rollback();
			resultCode = ResultCodes.CARD_UPDATE_EXCEPTION;
			logger.error(cardReq.getUserName()+"更新用户其他收款银行卡为非默认失败");
			/* 记录错误信息 */
			RecordUtils.writeError(logger, cardReq.getUserName(), ResultCodes.CARD_UPDATE_EXCEPTION, e);
		} 

		catch (SaveCardException e) {
			
			ConnManager.rollback();
			resultCode = ResultCodes.CARD_SAVE_EXCEPTION;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, cardReq.getUserName(), ResultCodes.CARD_SAVE_EXCEPTION, e);
			logger.error(cardReq.getUserName()+"保存用户收款银行卡失败");
		} catch (Exception e) {
			ConnManager.rollback();
			resultCode = ResultCodes.CARD_EXCEPTION;
			/* 记录错误信息 */
			RecordUtils.writeError(logger,cardReq.getUserName(), ResultCodes.CARD_EXCEPTION, e);
			logger.error(cardReq.getUserName()+"更新用户收款银行卡异常");
		}finally{
			ConnManager.closeConn();
		}

		doubleResp.setResultMsg(ResourceUtils.getString(resultCode));
		doubleResp.setResultCode(resultCode);
		logger.info(cardReq.getUserName()+"更改用户首款银行卡返回数据："+new Gson().toJson(doubleResp));
		return doubleResp;
	}
	
	

	/**
	 * 验证开户行和支行是否匹配
	 * 
	 * @param bankCode 开户行编码CMBC
	 * @param branchBank 开户支行
	 * @return
	 */
	public boolean isBankFalg(String bankCode,String branchBank){
		
		BankQuery bankQuery = new BankQuery();
		//获取开户行数字编码
		BankEntity bank = bankQuery.queryBankName(bankCode);
		
		if(bank != null){
			SysBankInfoQuery bankinfoQuery = new SysBankInfoQuery();
			BankBranchEntity bankInfo = bankinfoQuery.queryBankInfoByFatherCode(branchBank, bank.getBankNo());
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
