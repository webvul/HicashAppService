package com.hengyuan.hicash.domain.service.amount;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.amount.CreditAmtEvent;
import com.hengyuan.hicash.domain.query.user.BlackListQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.BlackListEntity;
import com.hengyuan.hicash.entity.user.CustLimitEntity;
import com.hengyuan.hicash.exception.UpdateCreditException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.amount.CreDreamAmtReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.amount.CreDreamAmtResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class CreDreamAmtNewService implements RespService{
private static Logger logger = Logger.getLogger(CreDreamAmtService.class);

	
	private BlackListQuery blackListQuery = new BlackListQuery();
	
	private CreditAmtEvent creditAmtEvent = new CreditAmtEvent();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		
		CreDreamAmtReq creDreamAmtReq = (CreDreamAmtReq) parmRequest;
		
		CreDreamAmtResp creDreamAmtResp = new CreDreamAmtResp();
		
		try{
			//根据内部规则得到额度
			BigDecimal amount = getCreditAmountNew(creDreamAmtReq);
			
			CustLimitEntity custLimitEntity = new CustLimitEntity();
			
			custLimitEntity.setUserName(creDreamAmtReq.getUsername());
			custLimitEntity.setTrustAmt(StringUtils.valueOf(amount));
			custLimitEntity.setUseAmt(StringUtils.valueOf(amount));
			custLimitEntity.setMaxAmt(StringUtils.valueOf(amount.add(amount.multiply(Consts.MAX_RATE))));
			custLimitEntity.setBlockAmt("0");
			custLimitEntity.setApplyAmt(StringUtils.valueOf(amount));
			
			//保存额度
			
			creditAmtEvent.saveCreaditAmt(custLimitEntity);
			creDreamAmtResp.setResultCode(ResultCodes.NORMAL);
			creDreamAmtResp.setAmount(String.valueOf(amount));
			
		}catch (UpdateCreditException e){
			
			creDreamAmtResp = new CreDreamAmtResp();
			creDreamAmtResp.setResultCode(ResultCodes.UPDATE_CREDIT_EXCEPTION);
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.UPDATE_CREDIT_EXCEPTION, e);
			
		}catch(Exception e){
			creDreamAmtResp = new CreDreamAmtResp();
			creDreamAmtResp.setResultCode(ResultCodes.SAVE_AMOUNT_EXCEPTION);
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.SAVE_AMOUNT_EXCEPTION, e);
		} finally {
			//ConnManager.closeConn();
		}

		
		return creDreamAmtResp;
	}
	
	/**
	 * 根据内部规则得到额度
	 * @param creDreamAmtReq
	 * @return
	 */
	public BigDecimal getCreditAmount(CreDreamAmtReq creDreamAmtReq){
		
		BigDecimal amount =BigDecimal.ZERO;
		
		boolean flag = matchBlackList(creDreamAmtReq.getRealname(), creDreamAmtReq.getIdentity(),creDreamAmtReq.getUuid());
		
		String type = creDreamAmtReq.getCustType();
		
		String Educational = creDreamAmtReq.getEducational();
		
		String monthIncome = creDreamAmtReq.getMonthIncome();
		
		//true 为命中黑名单
		if(!flag){
			
			 if(Consts.APP_CUSTOMER_TYPE_KHL2.equals(type)){
				  //如果是白领
//				 if(Consts.EDUCATIONAL_2.equals(Educational)){
//					 //专科
////					 amount = new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_01);
//					 amount = getKHL2Amount(monthIncome);
//					 
//				 }else if(Consts.EDUCATIONAL_3.equals(Educational)){
//					 //本科
////					 amount = new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_02);
//					 amount = getKHL2Amount(monthIncome);
//					 
//				 }else if(Consts.EDUCATIONAL_4.equals(Educational)){
//					 //硕士
////					 amount = new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_03);
//					 amount = getKHL2Amount(monthIncome);
//					 
//				 }else if(Consts.EDUCATIONAL_5.equals(Educational)){
//					 //博士
////					 amount = new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_04);
//					 amount = getKHL2Amount(monthIncome);
//					 
//				 }else{
//					 
//					 amount = new BigDecimal(Consts.DEFAULT_AMOUNT);
//				 }
				 amount = new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_05);
				 
			  }else if(Consts.APP_CUSTOMER_TYPE_KHL1.equals(type)){
				//如果是学生
						
//						if(Consts.EDUCATIONAL_3.equals(Educational)){
//							//本科
//							amount = new BigDecimal(Consts.CREDIT_AMOUNT_08);
//						}else if(Consts.EDUCATIONAL_2.equals(Educational)){
//							//专科
//							amount = new BigDecimal("6000.00");
//						}else if(Consts.EDUCATIONAL_4.equals(Educational)){
//							//硕士
//							amount = new BigDecimal(Consts.CREDIT_AMOUNT_02);
//						}else if(Consts.EDUCATIONAL_5.equals(Educational)){
//							//博士
//							amount = new BigDecimal(Consts.CREDIT_AMOUNT_01);
//						}else{
//							amount = new BigDecimal(Consts.DEFAULT_AMOUNT);
//						}
						
				  amount = new BigDecimal(Consts.CREDIT_AMOUNT_09);
			  }
			
		}
		
		return amount;
	}
	
	/**
	 * 验证黑名单
	 * @param realName
	 * @param identity
	 * @return
	 */
	public boolean matchBlackList(String realName,String identity,String uuid){
		
		BlackListEntity blackListEntity = blackListQuery.queryBlackList(realName, identity,uuid);
		
		if(blackListEntity==null){
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * 根据白领学历分配额度
	 * @param monthIncome 月收入
	 * @return
	 */
	public BigDecimal getKHL2Amount(String monthIncome){
		
		BigDecimal amount = new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_DEFAULT);
		if(monthIncome == null || "".equals(monthIncome)){
			return amount;
		}
		
		BigDecimal monIncome = new BigDecimal(monthIncome);
		
		amount = monIncome.divide(new BigDecimal(3),BigDecimal.ROUND_FLOOR).multiply(new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_PERIOD));
		
		if(amount.compareTo(new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_MAX)) >= 0){
			return new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_MAX);
		} else if (amount.compareTo(new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_DEFAULT)) < 0) {
			return new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_DEFAULT);
		} else {
			return amount;
		}
		
	}
	
	
	/**
	 * 根据内部规则得到额度
	 * @param creDreamAmtReq
	 * @return
	 */
	public BigDecimal getCreditAmountNew(CreDreamAmtReq creDreamAmtReq){
		
		BigDecimal amount =BigDecimal.ZERO;
		
		String type = creDreamAmtReq.getCustType();
	
			 if(Consts.APP_CUSTOMER_TYPE_KHL2.equals(type)){
				  //如果是白领
//				 if(Consts.EDUCATIONAL_2.equals(Educational)){
//					 //专科
////					 amount = new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_01);
//					 amount = getKHL2Amount(monthIncome);
//					 
//				 }else if(Consts.EDUCATIONAL_3.equals(Educational)){
//					 //本科
////					 amount = new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_02);
//					 amount = getKHL2Amount(monthIncome);
//					 
//				 }else if(Consts.EDUCATIONAL_4.equals(Educational)){
//					 //硕士
////					 amount = new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_03);
//					 amount = getKHL2Amount(monthIncome);
//					 
//				 }else if(Consts.EDUCATIONAL_5.equals(Educational)){
//					 //博士
////					 amount = new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_04);
//					 amount = getKHL2Amount(monthIncome);
//					 
//				 }else{
//					 
//					 amount = new BigDecimal(Consts.DEFAULT_AMOUNT);
//				 }
				 amount = new BigDecimal(Consts.COLLAR_CREDIT_AMOUNT_05);
				 
			  }else if(Consts.APP_CUSTOMER_TYPE_KHL1.equals(type)){
				//如果是学生
						
//						if(Consts.EDUCATIONAL_3.equals(Educational)){
//							//本科
//							amount = new BigDecimal(Consts.CREDIT_AMOUNT_08);
//						}else if(Consts.EDUCATIONAL_2.equals(Educational)){
//							//专科
//							amount = new BigDecimal("6000.00");
//						}else if(Consts.EDUCATIONAL_4.equals(Educational)){
//							//硕士
//							amount = new BigDecimal(Consts.CREDIT_AMOUNT_02);
//						}else if(Consts.EDUCATIONAL_5.equals(Educational)){
//							//博士
//							amount = new BigDecimal(Consts.CREDIT_AMOUNT_01);
//						}else{
//							amount = new BigDecimal(Consts.DEFAULT_AMOUNT);
//						}
						
				  amount = new BigDecimal(Consts.CREDIT_AMOUNT_09);
			  }

		
		return amount;
	}
	
}
