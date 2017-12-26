package com.hengyuan.hicash.domain.service.amount;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ProcessConst;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.amount.AvailBalanceQuery;
import com.hengyuan.hicash.domain.query.amount.AvailbleCreditQuery;
import com.hengyuan.hicash.domain.query.amount.LoanLoanAccQuery;
import com.hengyuan.hicash.domain.query.amount.LoanPlanAQuery;
import com.hengyuan.hicash.domain.query.amount.OverdueCountQuery;
import com.hengyuan.hicash.domain.query.amount.RepayTotalBalanceQuery;
import com.hengyuan.hicash.domain.query.app.AcctPayQuery;
import com.hengyuan.hicash.domain.query.app.TempApplyQuery;
import com.hengyuan.hicash.domain.query.param.MerProQuery;
import com.hengyuan.hicash.domain.query.param.ProductInfoQuery;
import com.hengyuan.hicash.domain.query.user.CustLimitQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.AccountPay;
import com.hengyuan.hicash.entity.app.AccountPayEntity;
import com.hengyuan.hicash.entity.app.LoanLoanAccEntity;
import com.hengyuan.hicash.entity.app.RepayPlanEntity;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.entity.user.CustLimitEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.amount.AppScheduleReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.amount.AppScheduleResp;
import com.hengyuan.hicash.utils.ShowNode;

/**
 * 查询申请件进度 业务处理
 * @author Cary.Liu
 * @createDate 2015-04-24
 *
 */
public class AppScheduleService implements RespService {

	private String resultCode = "";
	
	/** 还款中申请件 */
	private List<AccountPay> repayPlansPay = new ArrayList<AccountPay>();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		AppScheduleReq scheduleReq = (AppScheduleReq)parmRequest;
		AppScheduleResp scheduleResp = new AppScheduleResp();
		
		try {
			/* 可用额度 */
//			scheduleResp.setAvailCredit(getAvailAmount(scheduleReq.getUserName()));
			/* 授信额度 */
//			scheduleResp.setCreditAmt(getMaxAmount(scheduleReq.getUserName()));
			/* 获取可用余额 */
//			scheduleResp.setBalance(getAvailBalance(scheduleReq.getUserName()));
			/* 近七天还款总额 */
//			scheduleResp.setLateTotal(getRepayTotal(scheduleReq.getUserName()));
			/* 逾期数 */
			scheduleResp.setOverDueCount(getOverCount(scheduleReq.getUserName()));
			
			List<AccountPay> accountPays = getLoanInfo(scheduleReq.getUserName());
			if(accountPays != null && accountPays.size() > 0 ){
				scheduleResp.setAppList(accountPays);
			}else{
				scheduleResp.setAppList(new ArrayList<AccountPay>());
			}
			scheduleResp.setRepayPlans(repayPlansPay);
			
			resultCode = ResultCodes.NORMAL;
		} catch (Exception e) {
			resultCode = ResultCodes.APPSCHEDULE_EXCEPTION;
		} finally{
			ConnManager.closeConn();
		}
		
		scheduleResp.setResultCode(resultCode);
		return scheduleResp;
	}
	
	/**
	 * 获取用户可用额度
	 * @param userName
	 * @return
	 */
	public String getAvailAmount(String userName){
		
		AvailbleCreditQuery availAmountQuery = new AvailbleCreditQuery();
		return availAmountQuery.getAvailCredit(userName, null);
	}
	
	/**
	 * 获取用户最高授信额度
	 * @param userName
	 * @return
	 */
	public String getMaxAmount(String userName){
		
		CustLimitQuery limitQuery = new CustLimitQuery();
		CustLimitEntity limitEntity = limitQuery.queryCustLimitByUserName(userName);
		if(limitEntity != null){
			return limitEntity.getTrustAmt();
		}
		return Consts.FINAL_NUMBER_0;
	}
	
	/**
	 * 获取用户可用余额
	 * @param userName
	 * @return
	 */
	public String getAvailBalance(String userName){
		
		AvailBalanceQuery balanceQuery = new AvailBalanceQuery();
		return balanceQuery.getAvailBalance(userName);
	}
	
	/**
	 * 近七天还款总额
	 * @param userName
	 * @return
	 */
	public String getRepayTotal(String userName){
		
		RepayTotalBalanceQuery totalQuery = new RepayTotalBalanceQuery();
		return totalQuery.getCurrentBalance(userName);
	}
	
	/**
	 * 获取逾期数
	 * @param userName
	 * @return
	 */
	public String getOverCount(String userName){
		
		OverdueCountQuery overdueQuery = new OverdueCountQuery();
		return overdueQuery.getOverCountByUserName(userName);
	}
	
	protected List<AccountPay> getLoanInfo(String userName) {

		// 根据userName获取我的申请件基本信息

		AcctPayQuery accQuery = new AcctPayQuery();

		List<AccountPayEntity> payList = accQuery.getLoanInfo(userName);
		
		List<AccountPay> acctPays = null;
		if(payList != null && payList.size() > 0){
			
			acctPays = new ArrayList<AccountPay>();
//			List<RepayPlanEntity> repayPlans= null;
			RepayPlanEntity  repayPlan = null;
			LoanLoanAccQuery loanAccQuery=new LoanLoanAccQuery();
			LoanPlanAQuery loanPlanAQuery=new LoanPlanAQuery();
//			LoanPlanQuery  loanPlanQuery=new LoanPlanQuery();
			TempApplyQuery applyQuery = new TempApplyQuery();
			List<TempApplyEntity> tempList = applyQuery.queryTempApply(userName);
			String tempNo = "";
			if(tempList != null && tempList.size() > 0){
				tempNo = tempList.get(0).getTempAppNo();
			}
			for(int i = 0; i < payList.size(); i++){
				
//				repayPlans = new ArrayList<RepayPlanEntity>();
				//如果在还款中则还款计划设置为对应值
				AccountPayEntity acctpayEntity=payList.get(i);
				
				AccountPay acctPay = getAccountPay(acctpayEntity);
				
				acctPay.setTemAppPayNo(tempNo);
				
				
				LoanLoanAccEntity loanLoanAccEntity = null;
				if(acctpayEntity.getAllNode().endsWith(ProcessConst.ALL_NODE_HK) || ProcessConst.STATUS27.equals(acctpayEntity.getStatus())){
					
					//查询还款计划数据
					repayPlan=loanPlanAQuery.getSumPrince(userName, acctpayEntity.getAppPayNo());
					loanLoanAccEntity=loanAccQuery.getLoanPlanInfo(userName, acctpayEntity.getAppPayNo());
//					repayPlans=loanPlanQuery.getLoanPlanInfo(userName, acctpayEntity.getAppPayNo());
					
					if(loanLoanAccEntity != null ){
						acctPay = getAccountPayForLoanAcc(acctPay,loanLoanAccEntity);
						acctPay.setSumprincipal(repayPlan.getSumprincipal());
					}
					if(acctpayEntity.getAllNode().equals(ProcessConst.ALL_NODE_HK)){
						/* 正在还款中的申请件 */
						repayPlansPay.add(acctPay);
					}
				}
				
//				acctPay.setRepayPlans(repayPlans);
				
				//不在还款中则设置为null 这个方法返回为返回数据
				
				acctPays.add(acctPay);
			}
			
		}
		
		return acctPays;
	}
	
	protected AccountPay  getAccountPay(AccountPayEntity acctpayEntity){
		ProductInfoQuery infoQuery = new ProductInfoQuery();
		MerProQuery proQuery = new MerProQuery();
		AccountPay  acctPay=new AccountPay();
		
		acctPay.setAllNode(acctpayEntity.getAllNode());
		acctPay.setApplyDate(acctpayEntity.getApplyDate());
		
		acctPay.setApplyType(acctpayEntity.getApplyType());
		acctPay.setAppPayNo(acctpayEntity.getAppPayNo());
		acctPay.setInstallMent(acctpayEntity.getInstallMent());
		acctPay.setLoanAmount(!StringUtils.isEmpty(acctpayEntity.getLoanAmount())?acctpayEntity.getLoanAmount():Consts.FINAL_NUMBER_0);
		acctPay.setApplyAmount(!StringUtils.isEmpty(acctpayEntity.getApplyAmount())?acctpayEntity.getApplyAmount():Consts.FINAL_NUMBER_0);
		
		acctPay.setNode(acctpayEntity.getNode());
		String productName = infoQuery.queryProNameById(acctpayEntity.getProductId());
		if(productName != null && !"".equals(productName)){
			acctPay.setProductName(productName);
		}else{
			acctPay.setProductName(proQuery.queryMerProById(acctpayEntity.getMerProId()).getMerProName());
		}
		acctPay.setAppProductName(acctpayEntity.getProductName());
		acctPay.setMerProductId(acctpayEntity.getMerProId());
		
		if(Consts.APPFLOW_TYPE_CASH.equals(acctpayEntity.getApplyType())){
			acctPay.setApplyTypeName(Consts.CASH_NAME);
		}else{
			acctPay.setApplyTypeName(Consts.NORMAL_NAME);
		}
		acctPay.setStatus(acctpayEntity.getStatus());
		acctPay.setCustType(acctpayEntity.getCustType());
		acctPay.setMthPayFee(acctpayEntity.getMthPayFee());
		acctPay.setFirstPayRate(acctpayEntity.getFirstPayRate());
		acctPay.setFirstAmount(!StringUtils.isEmpty(acctpayEntity.getFirstAmount())?acctpayEntity.getFirstAmount():Consts.FINAL_NUMBER_0);
		acctPay.setCreateDate(acctpayEntity.getCreateDate());
		
		acctPay.setTreatyUploadFlag(acctpayEntity.getTreatyUploadFlag());
		acctPay.setTreatyUploadURL(acctpayEntity.getTreatyUploadURL());
		
		acctPay.setShowAllNode(ShowNode.showAllNode(acctpayEntity.getAllNode(),acctpayEntity.getStatus())); // 显示节点
		acctPay.setIsCancel(ShowNode.isCanCancel(acctpayEntity.getAllNode()));
		acctPay.setIsFirst(ShowNode.isFirst(acctpayEntity.getNode(), acctpayEntity.getFirstAmount()));
		
		acctPay.setIndustryCode(acctpayEntity.getIndustryCode());
		acctPay.setRejectDesc(acctpayEntity.getRejectDesc());
		acctPay.setAppcheckdesc(acctpayEntity.getAppcheckdesc());
		acctPay.setRejectCause(acctpayEntity.getRejectCause());
		acctPay.setLoanProduct(acctpayEntity.getLoanProduct());
		
		acctPay.setAppProductId(acctpayEntity.getAppProductId());
		return acctPay;
	}
	
	protected AccountPay  getAccountPayForLoanAcc(AccountPay acctPay,LoanLoanAccEntity loanLoanAccEntity){
		acctPay.setCurTerm(loanLoanAccEntity.getCurTerm());
		acctPay.setCurPayAmt(loanLoanAccEntity.getCurPayAmt());
		acctPay.setBalance(loanLoanAccEntity.getBalance());
		acctPay.setRemainFee(loanLoanAccEntity.getRemainFee());
		acctPay.setRemainInt(loanLoanAccEntity.getRemainInt());
		acctPay.setRemainPrin(loanLoanAccEntity.getRemainPrin());
	    acctPay.setNextStmtDate(loanLoanAccEntity.getNextStmtDate());
	    
//	    if(!StringUtils.isEmpty(loanLoanAccEntity.getAvailAbleDate())){
//	    	if(loanLoanAccEntity.getAvailAbleDate().length()>=10){
//	    	    acctPay.setSubDate(loanLoanAccEntity.getAvailAbleDate().substring(8, 10));
//	    	}
//	    }
	    if(!StringUtils.isEmpty(loanLoanAccEntity.getNextStmtDate())){
	    	if(loanLoanAccEntity.getNextStmtDate().length()>=10){
	    	    acctPay.setSubDate(loanLoanAccEntity.getNextStmtDate().substring(8, 10));
	    	}
	    }
	    
	    acctPay.setUnpaidFee(loanLoanAccEntity.getUnpaidFee());
	    acctPay.setUnpaidInt(loanLoanAccEntity.getUnpaidInt());
	    acctPay.setUnpaidMthFee(loanLoanAccEntity.getUnpaidMthFee());
	    acctPay.setUnpaidPrin(loanLoanAccEntity.getUnpaidPrin());
	    acctPay.setTotalTerm(loanLoanAccEntity.getTotalTerm());
	    acctPay.setCreditName(loanLoanAccEntity.getCreditName());
		return acctPay;
	}

}
