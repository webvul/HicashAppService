package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.ExtractCashCountService;
import com.hengyuan.hicash.parameters.request.user.ExtractCashCountReq;
import com.hengyuan.hicash.parameters.response.user.ExtractCashCountResp;
import com.hengyuan.hicash.service.validate.query.ExtractCashCountVal;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 提现笔数
 * 
 * @author Cary.Liu
 * @create 2014-09-17
 *
 */
@WebServlet("/ExtractCashCount")
public class ExtractCashCount extends HttpServlet {

	private static final long serialVersionUID = -3459499222336992371L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ExtractCashCountReq cashCountReq = new ExtractCashCountReq();
		cashCountReq.setUuid(req.getParameter("uuid"));
		cashCountReq.setTrimNum(req.getParameter("trimNum"));
		
		ExtractCashCountVal cashCountVal = new ExtractCashCountVal(cashCountReq);
		String resultCode = cashCountVal.validate();
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			ExtractCashCountResp cashCountResp = new ExtractCashCountResp();
			cashCountResp.setResultCode(resultCode);
			cashCountResp.setResultMsg(ResourceUtils.getString(resultCode));
			
			resp.getWriter().write(cashCountResp.toJson());
		}else{
			
			ExtractCashCountService countService = new ExtractCashCountService();
			ExtractCashCountResp cashCountResp = (ExtractCashCountResp)countService.responseValue(cashCountReq);
			cashCountResp.setResultMsg(ResourceUtils.getString(cashCountResp.getResultCode()));
			cashCountResp.setUuid(cashCountReq.getUuid());
			
			resp.getWriter().write(cashCountResp.toJson());
			
		}
		
		
	}
	
	
}
