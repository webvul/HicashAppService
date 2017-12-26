package com.hengyuan.hicash.domain.service.user;



import java.util.List;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.amount.AvailBalanceQuery;
import com.hengyuan.hicash.domain.query.amount.RepayTotalBalanceQuery;
import com.hengyuan.hicash.domain.query.app.HKApplicationQuery;
import com.hengyuan.hicash.domain.query.app.HaierApplicationQuery;
import com.hengyuan.hicash.domain.query.user.WithHoldingBankCardQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.AvailBalanceReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.AvailBalanceResp;
import com.hengyuan.hicash.parameters.response.user.WithHoldingBankCardResp;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * 
 * 获取账户当前可用余额 service
 * @author Cary.Liu
 * @createDate 2015-07-03
 * 
 */
public class AvailBalanceService implements RespService{

	private String resultCode = "";
	
	private WithHoldingBankCardQuery bankQuery = new WithHoldingBankCardQuery();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		
		AvailBalanceReq availBalanceReq = (AvailBalanceReq)parmRequest;
		AvailBalanceResp availBalanceResp = new AvailBalanceResp();
		AvailBalanceQuery availBalanceQuery = new AvailBalanceQuery();
		HaierApplicationQuery haierApplicationQuery = new HaierApplicationQuery();
		HKApplicationQuery hkApplicationQuery = new HKApplicationQuery();
		
		
		try{
			String availBalance = availBalanceQuery.getAvailBalance(availBalanceReq.getUserName());
			if(availBalance != null && !StringUtils.isEmpty(availBalance)){
				
				availBalanceResp.setBalance(availBalance);
			}else{
				availBalanceResp.setBalance(Consts.FINAL_NUMBER_0);
			}
			availBalanceResp.setLateTotal(getRepayTotal(availBalanceReq.getUserName()));
		    double balance=Double.parseDouble(availBalance);
		    double lateTotal=Double.parseDouble(availBalanceResp.getLateTotal());
			double rechargeAmount = lateTotal-balance;
			if(rechargeAmount<=0){
				availBalanceResp.setRechargeAmount("0.00");
			}else{
				availBalanceResp.setRechargeAmount(StringUtils.valueOf(rechargeAmount));
			}
			/**
			 * 用户只有海尔借款，且非逾期
			 * 计算该客户还款中有多少订单
                                      计算该客户还款中有多少订单（属于海尔）
               -- 如果条件1 = 条件2
               -- 则查询满足订单的流水号
               -- 否则这个客户不满足
                                     满足的查看订单是否逾期

			 */
			String isHaier = "0";
			String bankCard = "";
			Integer hkApp = haierApplicationQuery.queryHKApp(availBalanceReq.getUserName());
			Integer hkhaiApp = haierApplicationQuery.queryHKAppHaier(availBalanceReq.getUserName());
			if(hkApp!=0 && hkhaiApp!=0 && hkApp == hkhaiApp){
				List<String> appList = hkApplicationQuery.queryHkApp(availBalanceReq.getUserName());
				if(appList!=null && appList.size()>0){
					Integer overApp =  haierApplicationQuery.queryOverdueApp(appList);
					if(overApp==0){
						isHaier="1";
						
					}
					for(int i=0;i<appList.size();i++){
						WithHoldingBankCardResp resp = bankQuery.queryBankMobile(appList.get(i));
						if(i!=appList.size()-1){
							bankCard+=resp.getBankCard().substring(resp.getBankCard().length()-4, resp.getBankCard().length())+"和";
						}else{
							bankCard+=resp.getBankCard().substring(resp.getBankCard().length()-4, resp.getBankCard().length());
						}
					
					}
				}
			}
			
			
			availBalanceResp.setBankCard(bankCard);
			availBalanceResp.setIsHaier(isHaier);
			
			if(isHaier.equals("1")){
				availBalanceResp.setSign_str("还款时无需充值，系统会从您绑定的银行卡（尾号"+bankCard+"）中扣除待还金额，请保证卡内余额充足。");
			}else{
				availBalanceResp.setSign_str("");
			}
			
			
			
			resultCode = ResultCodes.NORMAL;
			
		}catch(Exception e){
			e.printStackTrace();
			resultCode = ResultCodes.AVAILBALANCE_EXCEPTION;
		}  finally{
			ConnManager.closeConn();
		}
		
		availBalanceResp.setResultCode(resultCode);
		return availBalanceResp;
	}
	
	/**
	 * 近七天还款总额
	 * @param userName
	 * @return
	 */
	public String getRepayTotal(String userName){
		
		RepayTotalBalanceQuery totalQuery = new RepayTotalBalanceQuery();
		return totalQuery.getCurrentBalanceNew(userName);
	}

	
	
	
}
