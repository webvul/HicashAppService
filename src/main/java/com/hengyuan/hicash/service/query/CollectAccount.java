package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CollectAccountService;
import com.hengyuan.hicash.parameters.request.user.CollectAccountReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollectAccountResp;
import com.hengyuan.hicash.service.validate.query.CollectAccountVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 收款账户信息查询
 * 
 * @author Cary.Liu
 * @create date 2014-07-26
 * 
 */
@WebServlet("/CollectAccount")
public class CollectAccount extends HttpServlet {

	private static final long serialVersionUID = 1L;

	

	private static Logger logger = Logger.getLogger(CollectAccount.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		CollectAccountReq accountReq = new CollectAccountReq();
		//用户名
		accountReq.setUserName(req.getParameter("userName"));
		accountReq.setUuid(req.getParameter("uuid"));
		RecordUtils.writeRequest(logger, req, accountReq);
		/* 实例化参数验证 */
		CollectAccountVal val = new CollectAccountVal(accountReq);
		String restult = val.validate();
		if(!ResultCodes.NORMAL.equals(restult)){
			
			CollectAccountResp respon = new CollectAccountResp();
			respon.setResultCode(restult);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			respon.setResultMsg(resuMsg);
			
			respon.setUuid(accountReq.getUuid());
			
			RecordUtils.writeResponse(logger, accountReq.getUuid(), respon);
			resp.getWriter().write(respon.toJson());
			
		}else{
			
			CollectAccountService accountService = new CollectAccountService();
			
			CollectAccountResp respon = (CollectAccountResp) accountService.responseValue(accountReq);
			
			String resuMsg = ResourceUtils.getString(respon.getResultCode());
			respon.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = respon;
			parmResponse.setUuid(accountReq.getUuid());
			
			RecordUtils.writeResponse(logger, accountReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}

	}

}
