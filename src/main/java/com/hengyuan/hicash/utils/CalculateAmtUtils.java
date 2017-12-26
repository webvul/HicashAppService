package com.hengyuan.hicash.utils;

import com.hengyuan.hicash.constant.Consts;


public class CalculateAmtUtils {

	/**
	 * 根据期数、利率、本金按照等额本息计算月还款额
	 * @param loanProduct
	 * @param principal
	 * @return
	 */
//	public static BigDecimal getAllPayBackAmtPerMonth(LoanProductEntity loanProduct, BigDecimal principal) {
//		
//		Integer period;
//		BigDecimal creditRate;
//		if (loanProduct == null) {
//			period = 11;
//			creditRate = new BigDecimal(9.84);
//		} else {
//			period = loanProduct.getPeriod();
//			creditRate = loanProduct.getCreditRate();
//		}
//		BigDecimal monthPayback = getMonthPayBackAmt(period, creditRate, principal);
//		BigDecimal monthFee = getMonthFee(loanProduct, principal);
//		return monthPayback.add(monthFee);	
//	}
//	
//	 /**
//	   *  计算月还款本息
//	   * @param period
//	   * @param creditRate
//	   * @param principal
//	   * @return
//	   */
//     public static BigDecimal getMonthPayBackAmt(int period, BigDecimal creditRate, BigDecimal principal) {
//
//		if (period == 0) {
//			period = 11;
//		} 
//		if (creditRate == null) {
//			creditRate = Consts.DEFAULT_YEAR_RATE;
//		}
//		BigDecimal monthIntRate = creditRate.divide(new BigDecimal(12), 6, BigDecimal.ROUND_HALF_UP)
//				.divide(new BigDecimal(100));
//		return principal
//				.multiply(monthIntRate)
//				.multiply(
//						(new BigDecimal(1).add(monthIntRate)).pow(period))
//				.divide(((new BigDecimal(1).add(monthIntRate))
//						.pow(period)).subtract(new BigDecimal(1)),0, BigDecimal.ROUND_HALF_UP);
//	}
//     
//     /**
// 	 *  计算月管理费
// 	 * @param loanProduct
// 	 * @param principal
// 	 * @return
// 	 */
// 	public static BigDecimal getMonthFee(LoanProductEntity loanProduct, BigDecimal principal) {
// 		return principal.multiply(loanProduct.getMthFeeRate()).divide(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
// 	}
 	
 	/**
	 * 获取信贷产品类型
	 * @param custType
	 * @param payType
	 * @return
	 */
	public static String getCreditProductType(String custType,String payType){
		
		String creditType = Consts.APP_CUSTOMER_TYPE_KHL2.equals(custType) ? Consts.CREDIT_PRODUCT_CR02: Consts.CREDIT_PRODUCT_CR01;
		
		/* 现金产品 */
		if (Consts.APPFLOW_TYPE_CASH.equals(payType)) {
			creditType = Consts.CREDIT_PRODUCT_CR03;
		}else{
			creditType = Consts.APP_CUSTOMER_TYPE_KHL2.equals(custType) ? Consts.CREDIT_PRODUCT_CR02: Consts.CREDIT_PRODUCT_CR01;
		}
		
		return creditType;
	}
	
}   
