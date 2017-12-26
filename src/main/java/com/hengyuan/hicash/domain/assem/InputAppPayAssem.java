package com.hengyuan.hicash.domain.assem;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.domain.query.param.SysBankInfoQuery;
import com.hengyuan.hicash.domain.query.user.CustUserCardQuery;
import com.hengyuan.hicash.entity.mer.MerProductEntity;
import com.hengyuan.hicash.entity.param.BankBranchEntity;
import com.hengyuan.hicash.entity.user.CustUserCard;
import com.hengyuan.hicash.exception.QueryUserCardNotFoundException;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppBlueCollarReq;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppPayReq;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * inputApp更新操作
 * @author Andy.Niu
 * @create 2014-08-07
 */
public class InputAppPayAssem {

	
	/**
	 * 初始化inputApp
	 * @param tempReq
	 * @param productInfo
	 * @param applicationPayNo
	 * @return
	 * @throws SQLException
	 * @throws QueryUserCardNotFoundException 
	 */
	public Map<String,Object> getInputAppMap(CreateAppPayReq tempReq, MerProductEntity proEntity, String applicationPayNo) throws SQLException, QueryUserCardNotFoundException{
		
		Map<String,Object> inputPay = new HashMap<String, Object>();
		
		inputPay.put("applicationNo", StringUtils.valueOf(applicationPayNo));
	
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String day = sdf.format(new Date());
		inputPay.put("creditDay", StringUtils.valueOf("每月" + day + "号"));
		inputPay.put("creditUse", StringUtils.valueOf("用以支付或购买" + proEntity.getProName()));
		inputPay.put("creditType", StringUtils.valueOf("等额本息"));
		inputPay.put("create_user", tempReq.getUserName());
		inputPay.put("create_date", StringUtils.valueOf(DateUtils.getDateStr(new Date())));
		inputPay.put("borrow_money_use", Consts.JKO7);
		
		// 2017-01-10
		/* 收款账户信息 */
		inputPay.put("BANK_TYPE", StringUtils.valueOf(tempReq.getOpenBank()));
		inputPay.put("BANK_CARD_NO", StringUtils.valueOf(tempReq.getBankCard()));
		/* 代扣银行卡信息 */
		inputPay.put("IS_PROXY", Consts.FINAL_NUMBER_1);
		inputPay.put("PROXY_OPENBANK", StringUtils.valueOf(tempReq.getOpenBank()));
		inputPay.put("PROXY_BANKCARD", StringUtils.valueOf(tempReq.getBankCard()));
		
//		//根据卡号ID查询卡号信息
//		if(Consts.APPFLOW_TYPE_CASH.equals(tempReq.getApplyType())){
//			
//			/* 如果代扣银行卡号同步到个人收款账户 */
//			if(Consts.FINAL_NUMBER_1.equals(tempReq.getMdFlag())){
//				if(Consts.FINAL_NUMBER_1.equals(tempReq.getIsSynPerAcct())){
//					inputPay.put("BANK_TYPE", StringUtils.valueOf(tempReq.getOpenBank()));
//					inputPay.put("BANK_CARD_NO", StringUtils.valueOf(tempReq.getBankCard()));
//				}
//			}
//			
//			
////			CustUserCard userCard = getUserCard(tempReq);
////			inputPay.put("BANK_TYPE", StringUtils.valueOf(tempReq.getOpenBank()));
////			inputPay.put("bank_province", StringUtils.valueOf(userCard.getProvince()));
////			inputPay.put("bank_city", StringUtils.valueOf(userCard.getCity()));
////			inputPay.put("BANK_BRANCH", StringUtils.valueOf(userCard.getOpenBank()));
////			inputPay.put("BANK_BRANCH", StringUtils.valueOf(queryOpenBankName(userCard.getOpenBank())));
////			inputPay.put("BANK_CARD_NO", StringUtils.valueOf(tempReq.getBankCard()));
//		}
//		// 嗨秒贷申请 
//		if(Consts.FINAL_NUMBER_1.equals(tempReq.getMdFlag())){
//			/* 代扣银行卡信息 */
//			inputPay.put("IS_PROXY", Consts.FINAL_NUMBER_1); // 2016-02-17新增（所有嗨秒贷行业必须代扣）
//			inputPay.put("PROXY_OPENBANK", StringUtils.valueOf(tempReq.getOpenBank()));
//			inputPay.put("PROXY_BANKCARD", StringUtils.valueOf(tempReq.getBankCard()));
//		}
//		//电信申请
//		if(Consts.IS_DX.equals(tempReq.getIsDx())){
//			inputPay.put("dx_pro_name", StringUtils.valueOf(proEntity.getProName()+"-"+tempReq.getProColor()));//商品名字+颜色
//			inputPay.put("dx_pro_phone", StringUtils.valueOf(tempReq.getPhoneNum()));//选择的手机号码
//			inputPay.put("dx_phonedata_id", StringUtils.valueOf(tempReq.getPhoneDataId()));//电信套餐ID
//			/* 代扣银行卡信息 */
//			inputPay.put("PROXY_OPENBANK", StringUtils.valueOf(tempReq.getOpenBank()));
//			inputPay.put("PROXY_BANKCARD", StringUtils.valueOf(tempReq.getBankCard()));
//		}
		return inputPay;
	}
	
	
    public CustUserCard getUserCard(CreateAppPayReq tempReq) throws QueryUserCardNotFoundException{
    	
    	CustUserCardQuery CustUserCardQuery = new CustUserCardQuery();
    	CustUserCard userCard = CustUserCardQuery.getCustUserCard(tempReq);
    	if(userCard == null){
    	   throw new QueryUserCardNotFoundException();
    	}
    	return userCard;
    }
	
    /**
     * 查询支行名称
     * @param openBank
     * @return
     */
	public String queryOpenBankName(String openBank){
		
		SysBankInfoQuery bankInfoQuery = new SysBankInfoQuery(); 
		BankBranchEntity bankEntity = bankInfoQuery.queryBankInfoByNum(openBank);
		if(bankEntity != null){
			return bankEntity.getBankName();
		}
		return openBank;
	}
	/**
	 * 初始化inputApp
	 * @param tempReq
	 * @param productInfo
	 * @param applicationPayNo
	 * @return
	 * @throws SQLException
	 * @throws QueryUserCardNotFoundException 
	 */
	public Map<String,Object> getInputAppMapBlue(CreateAppBlueCollarReq tempReq, 
			MerProductEntity proEntity, String applicationPayNo,String approveUserName) throws SQLException, QueryUserCardNotFoundException{
		
		Map<String,Object> inputPay = new HashMap<String, Object>();
		
		inputPay.put("applicationNo", StringUtils.valueOf(applicationPayNo));
		inputPay.put("productName", StringUtils.valueOf(proEntity.getProName()));
//		inputPay.put("productType", StringUtils.valueOf(productInfo.getProductType()));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String day = sdf.format(new Date());
//		inputPay.put("creditDay", StringUtils.valueOf("每月" + day + "号"));
		inputPay.put("creditUse", StringUtils.valueOf("用以支付或购买" + proEntity.getProName()));
		inputPay.put("creditType", StringUtils.valueOf("等额本息"));
		inputPay.put("create_user", approveUserName);
		inputPay.put("create_date", StringUtils.valueOf(DateUtils.getDateStr(new Date())));
		
		//根据卡号ID查询卡号信息
		if(Consts.APPFLOW_TYPE_CASH.equals(tempReq.getApplyType())){
			
			// 车辆抵押，租房分期 行业不添加收款账户
			if(!"DCFQ".equals(proEntity.getIndustryCode()) && !"ZFFQ".equals(proEntity.getIndustryCode())){
				
				CustUserCard userCard = getUserCardBlue(tempReq);
				
				inputPay.put("BANK_TYPE", StringUtils.valueOf(userCard.getBank()));
				inputPay.put("bank_province", StringUtils.valueOf(userCard.getProvince()));
				inputPay.put("bank_city", StringUtils.valueOf(userCard.getCity()));
//				inputPay.put("BANK_BRANCH", StringUtils.valueOf(userCard.getOpenBank()));
				inputPay.put("BANK_BRANCH", StringUtils.valueOf(queryOpenBankName(userCard.getOpenBank())));
				inputPay.put("BANK_CARD_NO", StringUtils.valueOf(userCard.getCardNum()));
				
				
			}
			
		}
		
//		/* 租房分期字段 */
//		if(!StringUtils.isEmpty(tempReq.getCaseNum())){
//			inputPay.put("ZF_CASENUM", StringUtils.valueOf(tempReq.getCaseNum()));
//		}
//		if(!StringUtils.isEmpty(tempReq.getFdRealName())){
//			inputPay.put("ZF_FD_REALNAME", StringUtils.valueOf(tempReq.getFdRealName()));
//		}
//		if(!StringUtils.isEmpty(tempReq.getFdIdentity())){
//			inputPay.put("ZF_FD_IDENTITY", StringUtils.valueOf(tempReq.getFdIdentity()));
//		}
//		if(!StringUtils.isEmpty(tempReq.getZfAddress())){
//			inputPay.put("ZF_ADDRESS", StringUtils.valueOf(tempReq.getZfAddress()));
//		}
//		if(!StringUtils.isEmpty(tempReq.getMonthRent())){
//			inputPay.put("ZF_MONTH_RENT", StringUtils.valueOf(tempReq.getMonthRent()));
//		}
//		if(!StringUtils.isEmpty(tempReq.getStartDate())){
//			inputPay.put("ZF_START_DATE", StringUtils.valueOf(tempReq.getStartDate()));
//		}
//		if(!StringUtils.isEmpty(tempReq.getZfTerm())){
//			inputPay.put("ZF_TERM", StringUtils.valueOf(tempReq.getZfTerm()));
//		}
//		if(!StringUtils.isEmpty(tempReq.getFdOpenBank())){
//			inputPay.put("ZF_FD_OPENBANK", StringUtils.valueOf(tempReq.getFdOpenBank()));
//		}
//		if(!StringUtils.isEmpty(tempReq.getFdCardNo())){
//			inputPay.put("ZF_FD_CARDNO", StringUtils.valueOf(tempReq.getFdCardNo()));
//		}
//		if(!StringUtils.isEmpty(tempReq.getPayMethod())){
//			inputPay.put("ZF_PAY_METHOD", StringUtils.valueOf(tempReq.getPayMethod()));
//		}
		
		/* 计算首次还款日 */
		String firstRepaymentDate = firstRepaymentDate(DateUtils.getCurrentDay());
		
		inputPay.put("FIRST_REPAYMENT_DAY", firstRepaymentDate);
		inputPay.put("creditDay", "每月" + firstRepaymentDate.substring(8) + "号");
//		inputPay.put("COMPACT_CFM_DATE", DateUtils.getDateStrChina(new Date()));
		
		/* 2016-03-16 新增 借款用途 */
		inputPay.put("borrow_money_use", tempReq.getLoanUse());
		
		return inputPay;
	}
	
	
    public CustUserCard getUserCardBlue(CreateAppBlueCollarReq tempReq) throws QueryUserCardNotFoundException{
    	
    	CustUserCardQuery CustUserCardQuery = new CustUserCardQuery();
    	CustUserCard userCard = CustUserCardQuery.getCustUserCardBlue(tempReq);
    	if(userCard == null){
    	   throw new QueryUserCardNotFoundException();
    	}
    	return userCard;
    }
    
	/**
	 * 获取首次还款日
	 * @param day 格式：05
	 * @return
	 */
	private String firstRepaymentDate(String day){
		
		int intDay = Integer.parseInt(day);
		String paymentDate = "";
		
		if (intDay >= 1 && intDay <= 5 ){
			
			paymentDate = DateUtils.getNextMonth(Consts.FINAL_INT_1,true,5);
			
		} else if (intDay >= 6 && intDay <= 10){
			
			paymentDate = DateUtils.getNextMonth(Consts.FINAL_INT_1,true,10);
			
		} else if (intDay >= 11 && intDay <= 15){
			
			paymentDate = DateUtils.getNextMonth(Consts.FINAL_INT_1,true,15);
			
		} else if (intDay >= 16 && intDay <= 20){
			
			paymentDate = DateUtils.getNextMonth(Consts.FINAL_INT_1,true,20);
			
		} else if (intDay >= 21 && intDay <= 31){
			
			paymentDate = DateUtils.getNextMonth(Consts.FINAL_INT_1,true,25);
			
		} else {
			paymentDate = DateUtils.getNextMonth(Consts.FINAL_INT_1,true,25);
		}
		
		return paymentDate;
	}
	
}
