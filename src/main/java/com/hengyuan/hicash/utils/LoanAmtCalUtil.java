package com.hengyuan.hicash.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;

import com.google.gson.Gson;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.parameters.response.user.LoanAmtCalResp;

public class LoanAmtCalUtil {
	
   
	/**
	 * 获取计算后的贷款金额
	 * @param loanAmtParm
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 * @throws HttpReturnException
	 * @throws HttpUrlRemoteException
	 */
	public static LoanAmtCalResp  calculateLoanAmount(BigDecimal tranPrice,BigDecimal firstRate,String loanProduct) throws HttpException, IOException, HttpReturnException, HttpUrlRemoteException{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tranPrice", tranPrice);
		map.put("firstRate", firstRate);
		map.put("loanProduct", loanProduct);
		
		String url = "http://115.29.250.40:8080/HicashService/LoanAmtCalculateForNew";
		String str =  HttpRemotePost.sendHttp2(url, map);
		LoanAmtCalResp loanAmtCalResp = new Gson().fromJson(str, LoanAmtCalResp.class);
		
		return loanAmtCalResp;
		
	}
	
	
}
