package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.UserNameCardService;
import com.hengyuan.hicash.parameters.request.user.UserNameCardReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.UserNameCardResp;
import com.hengyuan.hicash.service.validate.query.UserNameCardVal;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 用户真实姓名和身份证
 * @author Administrator
 *
 */
@WebServlet("/UserNameCard")
public class UserNameCard extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		UserNameCardReq userNameCardReq = new UserNameCardReq();
		userNameCardReq.setUserName(req.getParameter("userName"));//用户名
		userNameCardReq.setUuid(req.getParameter("uuid"));//uuid
		UserNameCardVal userNameCard = new UserNameCardVal(userNameCardReq);
		String resultCode = userNameCard.validate();
		if(!ResultCodes.NORMAL.equals(resultCode)){
			UserNameCardResp userNameResp = new UserNameCardResp();
			userNameResp.setResultCode(resultCode);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(resultCode);
			userNameResp.setResultMsg(resuMsg);
			userNameResp.setUuid(userNameCardReq.getUuid());
			resp.getWriter().write(userNameResp.toJson());
			
		}else{
			UserNameCardService  userService = new UserNameCardService();
			UserNameCardResp userNameResp = (UserNameCardResp) userService.responseValue(userNameCardReq);
			String resuMsg = ResourceUtils.getString(userNameResp.getResultCode());
			userNameResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = userNameResp;
			parmResponse.setUuid(userNameResp.getUuid());
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
	}
	

}
