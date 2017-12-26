package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.SaveInviteCodeAndNameService;
import com.hengyuan.hicash.parameters.request.user.SaveInviteCodeAndNameReq;
import com.hengyuan.hicash.parameters.response.user.UpdateLanUserInfoResp;
import com.hengyuan.hicash.service.validate.update.SaveInviteCodeAndNameVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 保存蓝领用户的名字和邀请码
 * @author tan
 * @createDate 2016-08-18
 *
 */
@WebServlet("/SaveInviteCodeAndName")
public class SaveInviteCodeAndName extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(SaveInviteCodeAndName.class);

	private static final long serialVersionUID = -8118031793198039125L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		SaveInviteCodeAndNameReq userInfoReq = new SaveInviteCodeAndNameReq();
		userInfoReq.setUserName(req.getParameter("userName")); 
		userInfoReq.setInviteCode(req.getParameter("inviteCode")); 
		
		RecordUtils.writeRequest(logger, req, userInfoReq);
		
		
		UpdateLanUserInfoResp userInfoResp = null;
		SaveInviteCodeAndNameVal userInfoVal = new SaveInviteCodeAndNameVal(userInfoReq);
		String resultCode = userInfoVal.validate();
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			userInfoResp = new UpdateLanUserInfoResp();
			userInfoResp.setResultCode(resultCode);
			
		}else{
			
			SaveInviteCodeAndNameService userInfoService = new SaveInviteCodeAndNameService();
			userInfoResp = (UpdateLanUserInfoResp)userInfoService.responseValue(userInfoReq);
			
		}
		
		userInfoResp.setResultMsg(ResourceUtils.getString(userInfoResp.getResultCode()));
		RecordUtils.writeResponse(logger, userInfoReq.getUuid(), userInfoResp);
		resp.getWriter().write(userInfoResp.toJson());
	}

}



