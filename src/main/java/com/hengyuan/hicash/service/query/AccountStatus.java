package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.AccountStatusService;
import com.hengyuan.hicash.parameters.request.user.AccountStatusReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.AccountStatusResp;
import com.hengyuan.hicash.service.validate.query.AccountStatusVal;
import com.hengyuan.hicash.utils.ResourceUtils;



/**
 * 查看账户状态（是否激活账户和）
 * 
 * @author Cary.Liu
 * @create 2014-08-24
 *
 */
@WebServlet("/AccountStatus")
public class AccountStatus extends  HttpServlet{

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		AccountStatusReq accountStatusReq = new AccountStatusReq();
		accountStatusReq.setUserName(req.getParameter("userName"));//用户名
		accountStatusReq.setUuid(req.getParameter("uuid"));//uuid
		AccountStatusVal statusVal = new AccountStatusVal(accountStatusReq);
		String resultCode = statusVal.validate();
		if(!ResultCodes.NORMAL.equals(resultCode)){
			AccountStatusResp statusResp = new AccountStatusResp();
			statusResp.setResultCode(resultCode);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(resultCode);
			statusResp.setResultMsg(resuMsg);
			statusResp.setUuid(accountStatusReq.getUuid());
			resp.getWriter().write(statusResp.toJson());
			
		}else{
			AccountStatusService  statusService = new AccountStatusService();
			AccountStatusResp valresp = (AccountStatusResp) statusService.responseValue(accountStatusReq);
			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(accountStatusReq.getUuid());
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
	}
	
}
