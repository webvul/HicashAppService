package com.hengyuan.hicash.domain.event.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.DInputProxyBankException;

public class NoahValidIdentityEvent {
	private static Logger logger = Logger.getLogger(NoahValidIdentityEvent.class);
	/**
	 * 验证银行卡成功后更新d_input_app表proxy_from列为 'NYDK'
	 * @param serialNo	流水号
	 */
	public void updateProxyFromByApplicationNo(String bankCardNum,
			String accName, String serialNo) {
		String updateSql = "UPDATE d_input_app SET proxy_from = 'NYDK' , PROXY_BANKCARD = '" +bankCardNum 
				+ "' , PROXY_OPENBANK = '" + accName 
				+ "'  WHERE applicationNo = "+serialNo;
//		String updateSql = "UPDATE d_input_app SET proxy_from = 'ZTDK' , PROXY_BANKCARD = '" +bankCardNum 
//				+ "' , PROXY_OPENBANK = '" + accName 
//				+ "'  WHERE applicationNo = "+serialNo;
		logger.info("更新银行卡："+updateSql);
		ConnManager.executeUpdate(updateSql);
	}
	
	
	
	/**
	 * 验证银行卡成功后更新d_input_app表proxy_from的值为NODK,ZTDK
	 * @param appNo	流水号
	 * @throws DInputProxyBankException 
	 */
	public void updateInputByAppNo(String bankCardNum,
			String bankCode, String appNo,String whichPart) throws DInputProxyBankException {
		
		String updateSql = "UPDATE d_input_app SET proxy_from = '"+whichPart+"', PROXY_BANKCARD = '" +bankCardNum 
				+ "',PROXY_OPENBANK = '" + bankCode 
				+ "'  WHERE applicationNo = "+appNo;

		logger.info("更新d_input_app中银行卡："+updateSql);
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new DInputProxyBankException();
		}
	
	}
	
	
}
