package com.hengyuan.hicash.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.entity.account.RateEntity;

/**
 * 还款金额计算类
 * @author Andy.Niu
 * @create date 2014-07-23
 */
public class RepayCalculateForNew {
	
	/**	
	 * 根据比例获取对应金额
	 * 
	 * @param tranPrice 成交价
	 * @param firstRate 支付比例
	 * @return
	 * @author Andy.Niu
	 * @create date 2014-07-23
	 */
	public static BigDecimal getPaymentByRate(BigDecimal tranPrice, BigDecimal rate) {
		return tranPrice.multiply(rate).divide(new BigDecimal(100), 0,
				BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 获取一笔贷款交易的实际贷款本金
	 * @param tranPrice	成交价格
	 * @param firstRate	首付比例
	 * @param serviceRate	服务费率
	 * @return
	 * @author Andy.Niu
	 * @create date 2014-07-23
	 */
	public static BigDecimal getLoanPrincipal(BigDecimal tranPrice,
			BigDecimal firstRate, BigDecimal serviceRate) {
		
		/* 获取实际需要的借款金额 */
		BigDecimal loanPrice = tranPrice.subtract(getPaymentByRate(tranPrice,firstRate));
		
		return getPaymentByRate(loanPrice, serviceRate).add(loanPrice);
	}
	
	/**
	 * 获取一笔贷款交易应该收取的服务费
	 * @param tranPrice 成交价格
	 * @param firstRate	首付比例
	 * @param serviceRate 服务费率
	 * @return
	 * @author Andy.Niu
	 * @create date 2014-07-23
	 */
	public static BigDecimal getServiceCharge(BigDecimal tranPrice,
			BigDecimal firstRate, BigDecimal serviceRate) {

		/* 获取实际需要的借款金额 */
		BigDecimal loanPrice = tranPrice.subtract(getPaymentByRate(tranPrice,firstRate));

		return getPaymentByRate(loanPrice, serviceRate);
	}

	
	/**
	 * 获取月管理费
	 * @param monthRate 月管理费率
	 * @param loanPrincipal	贷款本金
	 * @return 
	 * @author Andy.Niu
	 * @create date 2014-07-23
	 */
	public static BigDecimal getMonthFee(
			BigDecimal monthRate, BigDecimal loanPrincipal) {
		return loanPrincipal.multiply(monthRate)
				.divide(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
	}
	
	
	/**
	 * 计算月还款本息
	 * 
	 * @param period
	 * @param repayCalculatorResp
	 * @param principal
	 * @return
	 * @author Andy.Niu
	 * @create date 2014-07-23
	 */
	public static BigDecimal getMonthPayBackAmt(Integer period,
			BigDecimal creditRate, BigDecimal principal) {

		if (period == 0) {
			period = Consts.DEFAULT_PERIOD;
		}
		if (creditRate == null) {
			creditRate = Consts.DEFAULT_CREDIT_RATE;
		}
		BigDecimal monthIntRate = creditRate.divide(new BigDecimal(12), 6,
				BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100));
		
		if(new BigDecimal(0).compareTo(monthIntRate) == 0){
			return principal.divide(new BigDecimal(period), 0, BigDecimal.ROUND_HALF_UP);
		}
		
		return principal
				.multiply(monthIntRate)
				.multiply((new BigDecimal(1).add(monthIntRate)).pow(period))
				.divide(((new BigDecimal(1).add(monthIntRate)).pow(period)).subtract(new BigDecimal(
						1)), 0, BigDecimal.ROUND_HALF_UP);
	}
	
	public static void main(String[] args) {
		System.out.println(new BigDecimal(10).multiply(new BigDecimal(12)));
	}
	
	/**
	 * 根据期数、利率、本金按照等额本息计算月还款额
	 * 
	 * @param repayCalculatorResp
	 * @param principal
	 * @return
	 * @author Andy.Niu
	 * @create date 2014-07-23
	 */
	public static Map<Object,Object>  getAllPayBackAmtPerMonth(RateEntity rateEntity, BigDecimal loanPrincipal) {

		
	    String	period = rateEntity.getInstallments();
		BigDecimal creditRate = new BigDecimal(rateEntity.getCreditRate());
		BigDecimal mthRate = new BigDecimal(rateEntity.getMouthRate());
		
		/* 月还款本息 */
		BigDecimal monthPayback = BigDecimal.ZERO;
		
		/* 月管理费 */
		BigDecimal monthFee = BigDecimal.ZERO ;
		if(creditRate.compareTo(BigDecimal.ZERO) == 0){
			
			 monthPayback = loanPrincipal.divide(new BigDecimal(period),0,BigDecimal.ROUND_HALF_UP);
			
		}else{
			/* 月还款本息 */
			 monthPayback = getMonthPayBackAmt(Integer.parseInt(period), creditRate,loanPrincipal);
			
		}
		
		if(mthRate.compareTo(BigDecimal.ZERO) != 0){
			/* 月管理费 */
			 monthFee = getMonthFee(new BigDecimal(rateEntity.getMouthRate()), loanPrincipal);
		}
		
		

		
		
		Map<Object,Object> map = new HashMap<Object, Object>();
		map.put("monthFee", monthFee);
		map.put("monthPayback", monthPayback);
		map.put("totalAmt", monthPayback.add(monthFee));
		return map;
	}
	
	
}
