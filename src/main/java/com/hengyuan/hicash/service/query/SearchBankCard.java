package com.hengyuan.hicash.service.query;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.SearchBankCardService;
import com.hengyuan.hicash.parameters.request.user.SearchBankCardReq;
import com.hengyuan.hicash.parameters.response.user.SearchBankCardResp;
import com.hengyuan.hicash.service.validate.query.SearchBankCardVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 查询银行卡信息
 * 
 * @author Cary.Liu
 * @createDate 2015-06-08
 *
 */
@WebServlet("/SearchBankCard")
public class SearchBankCard extends HttpServlet {

	private static final long serialVersionUID = -4868636845649934369L;
	
	private static Logger logger = Logger.getLogger(SearchBankCard.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		SearchBankCardReq cardReq = new SearchBankCardReq();
		
		cardReq.setUuid(req.getParameter("uuid"));
		cardReq.setUserName(req.getParameter("userName"));
		
		RecordUtils.writeRequest(logger, req, cardReq);
		SearchBankCardVal cardVal = new SearchBankCardVal(cardReq);
		String resultCode = cardVal.validate();
		SearchBankCardResp cardResp = null;
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			cardResp = new SearchBankCardResp();
			cardResp.setResultCode(resultCode);
			cardResp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			
			SearchBankCardService cardService = new SearchBankCardService();
			cardResp = (SearchBankCardResp)cardService.responseValue(cardReq);
			cardResp.setResultMsg(ResourceUtils.getString(cardResp.getResultCode()));
			
			//银行卡列表，默认银行卡显示在第一个
			cardResp.setList(cardService.responseCardRespList(cardReq));;
		}
		
		cardResp.setUuid(cardReq.getUuid());
		RecordUtils.writeResponse(logger, cardReq.getUuid(), cardResp);
		resp.getWriter().write(cardResp.toJson());
	}
	
	
}
