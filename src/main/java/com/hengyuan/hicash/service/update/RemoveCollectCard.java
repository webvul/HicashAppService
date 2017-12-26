package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.RemoveCollectCardService;
import com.hengyuan.hicash.parameters.request.user.RemoveCollectCardReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.RemoveCollectCardResp;
import com.hengyuan.hicash.service.validate.update.RemoveCollectCardVal;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 删除银行卡
 * 
 * @author Cary.Liu
 * @create 2014-08-15
 *
 */
@WebServlet("/RemoveCollectCard")
public class RemoveCollectCard extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RemoveCollectCardReq cardReq = new RemoveCollectCardReq();
		cardReq.setUuid(req.getParameter("uuid"));
		cardReq.setCardId(req.getParameter("cardId")); //银行卡主键
		cardReq.setUserName(req.getParameter("userName"));
		
		RemoveCollectCardVal cardVal = new RemoveCollectCardVal(cardReq);
		String resultCode = cardVal.validate();
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			RemoveCollectCardResp cardResp = new RemoveCollectCardResp();
			cardResp.setUuid(cardReq.getUuid());
			cardResp.setResultCode(resultCode);
			String resultMsg = ResourceUtils.getString(resultCode);
			cardResp.setResultMsg(resultMsg);
			resp.getWriter().write(cardResp.toJson());
			
		}else{
			RemoveCollectCardService cardService = new RemoveCollectCardService();
			RemoveCollectCardResp removeCollectCardResp = (RemoveCollectCardResp)cardService.responseValue(cardReq);
			String resultMsg = ResourceUtils.getString(removeCollectCardResp.getResultCode());
			removeCollectCardResp.setResultMsg(resultMsg);
			ParmResponse response = removeCollectCardResp;
			response.setUuid(cardReq.getUuid());
			resp.getWriter().write(response.toJson());
		}
		
	}
	
}
