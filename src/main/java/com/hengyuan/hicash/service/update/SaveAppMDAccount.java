package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.SaveAppAccountMDService;
import com.hengyuan.hicash.parameters.request.user.SaveAppAccountReq;
import com.hengyuan.hicash.parameters.response.user.SaveAppAccountResp;
import com.hengyuan.hicash.service.validate.update.SaveAppAccountVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 保存申请件账户资料
 * 提交申请（秒贷驳回后提交）
 * @author Cary.Liu
 * @createDate 2016-01-26
 *
 */
@WebServlet("/SaveAppAccountMD")
public class SaveAppMDAccount extends HttpServlet {

	private static Logger logger = Logger.getLogger(SaveAppMDAccount.class);
	
	private static final long serialVersionUID = -7329529014860616429L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		SaveAppAccountReq reqParam = new SaveAppAccountReq();
		reqParam.setUserName(req.getParameter("userName"));
		reqParam.setAppNo(req.getParameter("appNo"));
		reqParam.setOpenBank(req.getParameter("openBank"));
		reqParam.setBankCard(req.getParameter("bankCard"));
		reqParam.setIsOpenProxy(req.getParameter("isOpenProxy"));
		RecordUtils.writeRequest(logger, req, reqParam);
		
		SaveAppAccountResp respParam = null;
		SaveAppAccountVal val = new SaveAppAccountVal(reqParam);
		String resultCode = val.validate();
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			respParam = new SaveAppAccountResp();
			respParam.setResultCode(resultCode);
			
		}else{
			
			SaveAppAccountMDService acctService = new SaveAppAccountMDService();
			respParam = (SaveAppAccountResp)acctService.responseValue(reqParam);
			
		}
		
		respParam.setResultMsg(ResourceUtils.getString(respParam.getResultCode()));
		RecordUtils.writeResponse(logger, null, respParam);
		resp.getWriter().write(respParam.toJson());
	}

}
