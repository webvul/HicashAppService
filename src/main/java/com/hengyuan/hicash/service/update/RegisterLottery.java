package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.RegisterLotteryService;
import com.hengyuan.hicash.parameters.request.user.RegisterLotteryReq;
import com.hengyuan.hicash.parameters.response.user.RegisterLotteryResp;
import com.hengyuan.hicash.service.validate.update.RegisterLotteryVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 用户注册抽奖
 * @author Cary.Liu
 * @createDate 2016-01-11
 *
 */
@WebServlet("/RegisterLottery")
public class RegisterLottery extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(RegisterLottery.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RegisterLotteryReq lotteryReq = new RegisterLotteryReq();
		lotteryReq.setUserName(req.getParameter("userName"));
		RecordUtils.writeRequest(logger, req, lotteryReq);
		
		RegisterLotteryResp lotteryResp = null;
		RegisterLotteryVal lotteryVal = new RegisterLotteryVal(lotteryReq);
		String resultCode = lotteryVal.validate();
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			lotteryResp = new RegisterLotteryResp();
			lotteryResp.setResultCode(resultCode);
			
		}else{
			
			RegisterLotteryService lotteryService = new RegisterLotteryService();
			lotteryResp = (RegisterLotteryResp)lotteryService.responseValue(lotteryReq);
			
		}
		
		lotteryResp.setResultMsg(ResourceUtils.getString(lotteryResp.getResultCode()));
		lotteryResp.setUuid(lotteryReq.getUuid());
		RecordUtils.writeResponse(logger, lotteryReq.getUuid(), lotteryResp);
		resp.getWriter().write(lotteryResp.toJson());
	}
	
}
