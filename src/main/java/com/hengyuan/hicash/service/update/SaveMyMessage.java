package com.hengyuan.hicash.service.update;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.mktApp.SaveMyMessageService;
import com.hengyuan.hicash.domain.service.mktApp.SaveMyMsgService;
import com.hengyuan.hicash.parameters.request.user.SaveMyMessageReq;
import com.hengyuan.hicash.parameters.request.user.SaveMyMsgReq;
import com.hengyuan.hicash.parameters.response.user.SaveMyMessageResp;
import com.hengyuan.hicash.parameters.response.user.SaveMyMsgResp;
import com.hengyuan.hicash.service.validate.update.SaveMyMessageVal;
import com.hengyuan.hicash.service.validate.update.SaveMyMsgVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;


/**
 * 
* @author xing.yuan
* 类说明:保存我的消息
 */
@WebServlet("/SaveMyMessage")
public class SaveMyMessage extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(SaveMyMessage.class);

	private static final long serialVersionUID = -4146078745705753888L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		SaveMyMessageReq req = new SaveMyMessageReq();
	
		req.setCode(request.getParameter("code"));//消息code
		req.setContent(request.getParameter("content"));//消息内容
		req.setUserName(request.getParameter("userName"));//用户名
		req.setIdNo(request.getParameter("idNo"));//身份证号
		//req.setIsRead(request.getParameter("isRead"));//是否已读:1/已读,0/未读
		req.setMobile(request.getParameter("mobile"));//手机号
		//req.setName( new String(request.getParameter("name").getBytes("ISO8859_1"),"utf-8"));//姓名
		req.setName(request.getParameter("name"));//姓名
		req.setOperate(request.getParameter("operate"));//功能
		req.setTitle(request.getParameter("title"));//标题
		req.setType(request.getParameter("type"));//消息类型
		req.setAppNo(request.getParameter("appNo"));//流水号
		
		RecordUtils.writeRequest(logger, request, req);

		SaveMyMessageVal val = new SaveMyMessageVal(req);
		String resultCode = val.validate();
		SaveMyMessageResp resp = null;
		if (!ResultCodes.NORMAL.equals(resultCode)) {

			resp = new SaveMyMessageResp();
			resp.setResultCode(resultCode);
			resp.setResultMsg(ResourceUtils.getString(resultCode));
		} else {
			SaveMyMessageService saveMyMessageService = new SaveMyMessageService();
			
			resp = (SaveMyMessageResp) saveMyMessageService.responseValue(req);
			
			resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		}
		RecordUtils.writeResponse(logger, "", resp);
		response.getWriter().write(resp.toJson());
	}


}
