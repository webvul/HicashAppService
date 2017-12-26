package com.hengyuan.hicash.domain.service.calculate;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.amount.AvailbleCreditQuery;
import com.hengyuan.hicash.domain.query.app.ApplicationPayQuery;
import com.hengyuan.hicash.domain.query.mer.MerProQuery;
import com.hengyuan.hicash.domain.query.product.RepayProgramQuery;
import com.hengyuan.hicash.domain.query.user.HyVipPeriodQuery;
import com.hengyuan.hicash.domain.query.user.HyVipUserQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.RepayProgramEntity;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.param.MerProductInfoEntity;
import com.hengyuan.hicash.entity.user.HyVipPeriod;
import com.hengyuan.hicash.entity.user.HyVipUser;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.param.LoanAmtCalReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.calculate.RepayProgramResp;
import com.hengyuan.hicash.utils.ResourceUtils;

public class RepayProgramService implements RespService {

	private static Logger logger = Logger.getLogger(RepayProgramService.class);
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		
		LoanAmtCalReq loanCal = (LoanAmtCalReq) parmRequest;
		
		RepayProgramResp repayProgramResp = new RepayProgramResp();
		
		MerProQuery merProQuery = new MerProQuery();
		
		RepayProgramQuery repayProgramQuery = new RepayProgramQuery();
		
		ApplicationPayQuery payQuery = new ApplicationPayQuery();
		
		AvailbleCreditQuery availbleCreditQuery = new AvailbleCreditQuery();
		
		HyVipUserQuery hyVipUserQuery = new HyVipUserQuery();
		
		HyVipPeriodQuery hyVipPeriodQuery = new HyVipPeriodQuery();
		
		String useAppNo = "";
		BigDecimal applyAmount = BigDecimal.ZERO;
		//Vip贷期数
		String period = "";
		//剩余额度
		BigDecimal syAmount = BigDecimal.ZERO;
		//已还本金
		BigDecimal prinPaid = BigDecimal.ZERO;
		
		String btn_str = "";
		
		String box_msg = "";
		
		String box_btn = "";
		
		String box_title = "";
		
		String iscredit = "";
		
		List<RepayProgramEntity> dayRepayList = null;
		
		dayRepayList = repayProgramQuery.querydayRepayList("KHL2","VIPD", new BigDecimal(0), loanCal.getUuid());
		
		List<HyVipUser> hyVipUserList = hyVipUserQuery.isCreditExtension(loanCal.getUserName());
		
		if(hyVipUserList.size() > 0){
			
			HyVipUser hyVipUser = hyVipUserQuery.isCreditExtension(loanCal.getUserName()).get(0);
		
			List<HyVipPeriod> hyVipPeriodList = hyVipPeriodQuery.findPeriodsByUserName(loanCal.getUserName());
			
			List<ApplicationPay> VipDList = payQuery.queryVipDList(loanCal.getUserName());
			 
			MerProductInfoEntity merProductInfoEntity = merProQuery.queryMerProductByid(loanCal.getMerProId(), loanCal.getUuid());
			
			//拼接流水号，sum（已提额度）
			 if(VipDList.size() > 0 ){
				 for (int i = 0; i < VipDList.size(); i++) {
					 if(StringUtils.isEmpty(useAppNo)){
						useAppNo =  "'"+VipDList.get(i).getApplicationNo()+"'";
						applyAmount = new BigDecimal(VipDList.get(i).getApplyAmount());
					 }else{
						useAppNo =  useAppNo+",'"+VipDList.get(i).getApplicationNo()+"'";
						applyAmount = applyAmount.add(new BigDecimal(VipDList.get(i).getApplyAmount()));
					 }
				 }
				// prinPaid = new BigDecimal(availbleCreditQuery.getPrinPaid(useAppNo, ""));
				 if(availbleCreditQuery.getPrinPaid(useAppNo, "")==null||"".equals(availbleCreditQuery.getPrinPaid(useAppNo, ""))){
					 prinPaid = BigDecimal.ZERO;
				 }else{
					 prinPaid = new BigDecimal(availbleCreditQuery.getPrinPaid(useAppNo, ""));
				 }
				 
			 }
			 
			try {
				//已经授信
				if(hyVipUser.getIsVip() == 1 &&"NOML".equals(hyVipUser.getStatus())){
					
					 //查询客户可选期数
					 if(hyVipPeriodList.size()>0){
						 for (int i = 0; i < hyVipPeriodList.size(); i++) {
							 if(StringUtils.isEmpty(period)){
								 period = ""+hyVipPeriodList.get(i).getPeriod()+"";
							 }else{
								 period = period+","+hyVipPeriodList.get(i).getPeriod()+"";
							 }
						 }
					 }else{
						 period = hyVipUser.getInitPeriod().toString();
					 }
					//可用额度 = 授信额度 - 已提额度 + 已还本金
					//判断hy_vip_user表，是否授权
					//授权成功取授信额度,否则返回错误
					//已提额度： 通过用户名，行业代码（VIPD），状态不在（Status20，Status21）查询申请件。sum(apply_amount)
					//已还本金：前面拿到流水号，关联loan_loan，再关联loan_replay_plan。sum(已还本金ALL)
					syAmount = hyVipUser.getAmount().subtract(applyAmount).add(prinPaid);
					System.out.println("剩余额度++++++++++++++++++++++"+syAmount);
					int kyAmount = syAmount.intValue() / 100 * 100;
					System.out.println("可用额度++++++++++++++++++++++"+kyAmount);
					
					if(merProductInfoEntity != null ){
						BigDecimal rebate = BigDecimal.ZERO;
						String industry = merProductInfoEntity.getIndustryCode();
						if(!StringUtils.isEmpty(industry)){
							
							  if("1".equals(merProductInfoEntity.getIsRebate())){
							    	//如果该产品开通返点比例，就获取产品的返点比例  反之获取商户的返点比例
							    	if(!StringUtils.isEmpty(merProductInfoEntity.getMerProductRPT())){
							    		rebate = new BigDecimal(merProductInfoEntity.getMerProductRPT());
							    	}
							    }else{
							    	if(!StringUtils.isEmpty(merProductInfoEntity.getSupplierRPT())){
							    		rebate =  new BigDecimal(merProductInfoEntity.getSupplierRPT());
							    	}
							    }

								List<RepayProgramEntity> repayList = null;
							  	
								repayList = repayProgramQuery.queryHyRepayProgram(loanCal.getCustType(),industry, rebate, loanCal.getUuid(),period,hyVipUser.getType());
								dayRepayList = repayProgramQuery.querydayRepayList(loanCal.getCustType(),industry, rebate, loanCal.getUuid(),hyVipUser.getType());
								
								if(repayList!=null){
									repayProgramResp.setMinAmount("100");
									repayProgramResp.setKyAmount(String.valueOf(kyAmount));
									repayProgramResp.setIsVip(String.valueOf(hyVipUser.getIsVip()));
									//repayProgramResp.setPeriods("期");
									repayProgramResp.setResultCode(ResultCodes.NORMAL);
									repayProgramResp.setPerList(repayList);
									repayProgramResp.setRebatePercent(String.valueOf(rebate));
									repayProgramResp.setStatus(hyVipUser.getStatus());
									repayProgramResp.setCustVipGrade(String.valueOf(hyVipUser.getType()));
								}else{
									repayProgramResp.setResultCode(ResultCodes.LOAN_PRO_FAIL);
									repayProgramResp.setResultMsg(ResourceUtils.getString(ResultCodes.LOAN_PRO_FAIL));
								}
								
						}else{
							repayProgramResp.setResultCode(ResultCodes.LOAN_PRO_ERRER);
							repayProgramResp.setResultMsg("产品行业代码为空");
						}
					  
					}else{
						repayProgramResp.setResultCode(ResultCodes.LOAN_PRO_FAIL);
						repayProgramResp.setResultMsg(ResourceUtils.getString(ResultCodes.LOAN_PRO_FAIL));
					}
					
					//vip显示文字
					btn_str = "立享特权";
					
					//页面显示文字（关于准vip）
					if(hyVipUser.getIsAuth() == 0){
						btn_str = "授信成为VIP";
						box_msg = "欢迎回归，由于3个月内您未有借款，您的部分资料已失效，请再次填写完善。符合VIP资格的用户即享更高额度、更低利率。";
						box_btn = "知道了";
						box_title = "成为VIP";
						iscredit = "0";
						
					}
					repayProgramResp.setBtn_str(btn_str);
					repayProgramResp.setBox_msg(box_msg);
					repayProgramResp.setBox_title(box_title);
					repayProgramResp.setBox_btn(box_btn);
					repayProgramResp.setIscredit(iscredit);
					
				}else{
					btn_str = "立享特权";
					repayProgramResp.setBtn_str(btn_str);
					List<RepayProgramEntity> repayListNo = null;
					repayListNo = repayProgramQuery.queryHyRepayProgramNo("KHL2","VIPD", new BigDecimal(0), loanCal.getUuid());
					repayProgramResp.setMinAmount("100");
					repayProgramResp.setKyAmount("10000");
					repayProgramResp.setIsVip("0");
					//repayProgramResp.setPeriods("期");
					repayProgramResp.setResultCode(ResultCodes.NORMAL);
					repayProgramResp.setPerList(repayListNo);
					repayProgramResp.setRebatePercent("0");
					repayProgramResp.setStatus(hyVipUser.getStatus());
					repayProgramResp.setCustVipGrade("");
				}
				
			} catch (Exception e) {
				btn_str = "立享特权";
				repayProgramResp.setBtn_str(btn_str);
				List<RepayProgramEntity> repayListNo = null;
				repayListNo = repayProgramQuery.queryHyRepayProgramNo("KHL2","VIPD", new BigDecimal(0), loanCal.getUuid());
				repayProgramResp.setMinAmount("100");
				repayProgramResp.setKyAmount("10000");
				repayProgramResp.setIsVip("0");
				//repayProgramResp.setPeriods("期");
				repayProgramResp.setResultCode(ResultCodes.NORMAL);
				repayProgramResp.setPerList(repayListNo);
				repayProgramResp.setRebatePercent("0");
				repayProgramResp.setStatus(hyVipUser.getStatus());
				repayProgramResp.setCustVipGrade("");
			}
			
		}else{
			btn_str = "立享特权";
			repayProgramResp.setBtn_str(btn_str);
			List<RepayProgramEntity> repayListNo = null;
			repayListNo = repayProgramQuery.queryHyRepayProgramNo("KHL2","VIPD", new BigDecimal(0), loanCal.getUuid());
			repayProgramResp.setMinAmount("100");
			repayProgramResp.setKyAmount("10000");
			repayProgramResp.setIsVip("0");
			//repayProgramResp.setPeriods("期");
			repayProgramResp.setResultCode(ResultCodes.NORMAL);
			repayProgramResp.setPerList(repayListNo);
			repayProgramResp.setRebatePercent("0");
			repayProgramResp.setStatus("CANL");
			repayProgramResp.setCustVipGrade("");
		}
		
		repayProgramResp.setDayList(dayRepayList);
		repayProgramResp.setAppAmountFourteen("0");
		
		return repayProgramResp;
	}
	
	public void programListAddHg(RepayProgramResp resp){
		if(resp==null||resp.getPerList()==null||resp.getPerList().size()==0){
			return;
		}
		resp.getPerList().add(initProgram(6));
		resp.getPerList().add(initProgram(9));
		resp.getPerList().add(initProgram(12));
	}
	
	private RepayProgramEntity initProgram(int installments){
		RepayProgramEntity beanTemp=new RepayProgramEntity();
		beanTemp.setLoanProduct("0");
		beanTemp.setName(installments+"期普通借款");
		beanTemp.setInstallments(installments+"");
		beanTemp.setPeriods("个月");
		beanTemp.setMthFee("");
		beanTemp.setServerRate("");
		beanTemp.setCustRate("");
		return beanTemp;
	}

}
