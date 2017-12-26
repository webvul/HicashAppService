package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.mktApp.SaveMyMsgService;
import com.hengyuan.hicash.parameters.request.user.SaveMyMsgReq;
import com.hengyuan.hicash.parameters.response.user.SaveMyMsgResp;
import com.hengyuan.hicash.service.validate.update.SaveMyMsgVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;


/**
 * 
* @author xing.yuan
* 类说明:保存我的消息
 */
@WebServlet("/SaveMyMsg")
public class SaveMyMsg extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(SaveMyMsg.class);

	private static final long serialVersionUID = -4146078745705753888L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		SaveMyMsgReq req = new SaveMyMsgReq();
		
		req.setIndustryCode(request.getParameter("industrycode"));//消息code
		req.setUsername(request.getParameter("username"));//用户名
		req.setStatus(request.getParameter("status"));//状态
		req.setCode(request.getParameter("code"));//消息类型
		req.setAppNo(request.getParameter("appNo"));//流水号
		RecordUtils.writeRequest(logger, request, req);

		SaveMyMsgVal val = new SaveMyMsgVal(req);
		String resultCode = val.validate();
		SaveMyMsgResp resp = null;
		if (!ResultCodes.NORMAL.equals(resultCode)) {

			resp = new SaveMyMsgResp();
			resp.setResultCode(resultCode);
			resp.setResultMsg(ResourceUtils.getString(resultCode));
		} else {
			SaveMyMsgService saveMyMsgService = new SaveMyMsgService();
			
			resp = (SaveMyMsgResp) saveMyMsgService.responseValue(req);
			
			resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		}
		RecordUtils.writeResponse(logger, "", resp);
		response.getWriter().write(resp.toJson());
	}
	public static String checkNull(String str) {

		if (StringUtils.isEmpty(str) || "null".equals(str)) {
			return "";
		} else {
			return str;
		}
	}

}
