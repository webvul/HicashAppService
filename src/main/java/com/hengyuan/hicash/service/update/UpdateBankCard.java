package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.UpdateBankCardService;
import com.hengyuan.hicash.parameters.request.user.UpdateBankCardReq;
import com.hengyuan.hicash.parameters.response.user.UpdateBankCardResp;
import com.hengyuan.hicash.service.validate.update.UpdateBankCardVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 修改银行卡
 * 
 * @author Cary.Liu
 * @create 2014-09-29
 *
 */
@WebServlet("/UpdateBankCard")
public class UpdateBankCard extends HttpServlet {

	private static final long serialVersionUID = 6972738095397782410L;
	
	private static Logger logger = Logger.getLogger(UpdateBankCard.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		UpdateBankCardReq cardReq = new UpdateBankCardReq();
		cardReq.setUserName(req.getParameter("userName"));//用户名
		cardReq.setCardId(req.getParameter("cardId"));//银行卡主键
		cardReq.setBank(req.getParameter("bank"));//开户支行
		cardReq.setProvince(req.getParameter("province"));//开户城市-省
		cardReq.setCity(req.getParameter("city"));//开户城市-市
		cardReq.setOpenBank(req.getParameter("openBank"));//开户支行
		cardReq.setCardNum(req.getParameter("cardNum"));//收款账号
		cardReq.setDefaultCard(req.getParameter("defaultCard"));//是否设置为默认卡
		cardReq.setAreaCode(req.getParameter("areaCode"));//银行卡网点所在区域代码
		cardReq.setUuid(req.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, req, cardReq);
		
		RecordUtils.writeRequest(logger, req, cardReq);
		/* 实例化参数验证 */
		UpdateBankCardVal val = new UpdateBankCardVal(cardReq);
		String resultCode = val.validate();
		if (!ResultCodes.NORMAL.equals(resultCode)) {
			
			UpdateBankCardResp cardResp = new UpdateBankCardResp();
			cardResp.setResultCode(resultCode);
			cardResp.setResultMsg(ResourceUtils.getString(resultCode));
			cardResp.setUuid(cardReq.getUuid());
			
			RecordUtils.writeResponse(logger, cardReq.getUuid(), cardResp);
			resp.getWriter().write(cardResp.toJson());
		
		}else{
			
			UpdateBankCardService cardService = new UpdateBankCardService();
			UpdateBankCardResp cardResp = (UpdateBankCardResp) cardService.responseValue(cardReq);
			cardResp.setResultMsg(ResourceUtils.getString(resultCode));
			cardResp.setUuid(cardReq.getUuid());
			
			RecordUtils.writeResponse(logger, cardReq.getUuid(), cardResp);
			resp.getWriter().write(cardResp.toJson());
			
		}
		
	}
	
}
