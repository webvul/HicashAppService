package com.hengyuan.hicash.service.activity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.activity.NewYearLotteryService;
import com.hengyuan.hicash.parameters.request.activity.NewYearLotteryReq;
import com.hengyuan.hicash.parameters.response.activity.NewYearLotteryResp;
import com.hengyuan.hicash.service.validate.update.NewYearLotteryVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 新年抽奖活动
 * @author Cary.Liu
 * @createDate 2015-12-29
 *
 */
@WebServlet("/NewYearLottery")
public class NewYearLottery extends HttpServlet {

	private static final long serialVersionUID = 7364865812137269682L;
	
	private static Logger logger = Logger.getLogger(NewYearLottery.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		NewYearLotteryReq lotReq = new NewYearLotteryReq();
		lotReq.setUserName(req.getParameter("userName"));
		lotReq.setActivityType(req.getParameter("activityType"));
		RecordUtils.writeRequest(logger, req, lotReq);
		
		NewYearLotteryResp lotResp = null;
		NewYearLotteryVal lotVal = new NewYearLotteryVal(lotReq);
		String resultCode = lotVal.validate();
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			lotResp = new NewYearLotteryResp(); 
			lotResp.setResultCode(resultCode);
			
		}else{
			
			NewYearLotteryService lotService = new NewYearLotteryService();
			lotResp = (NewYearLotteryResp)lotService.responseValue(lotReq);
			
		}
		
		lotResp.setResultMsg(ResourceUtils.getString(lotResp.getResultCode()));
		RecordUtils.writeResponse(logger, null, lotResp);
		resp.getWriter().write(lotResp.toJson());
	}
	
}
