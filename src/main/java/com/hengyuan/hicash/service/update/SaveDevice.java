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
import com.hengyuan.hicash.domain.service.mktApp.CustDeviceService;
import com.hengyuan.hicash.parameters.request.user.CustDeviceReq;
import com.hengyuan.hicash.parameters.response.user.CustDeviceResp;
import com.hengyuan.hicash.service.validate.update.CustDeviceVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;


/**
 * 
* @author dong.liu 
* 2017-4-7 下午6:26:42
* 类说明:保存设备信息
 */
@WebServlet("/SaveDevice")
public class SaveDevice extends HttpServlet {

	private static Logger logger = Logger.getLogger(SaveDevice.class);

	private static final long serialVersionUID = -4146078745705753888L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		CustDeviceReq req = new CustDeviceReq();
		
		req.setApp_no(request.getParameter("app_no"));
		req.setSb_system(request.getParameter("sb_system"));
		req.setSb_type(request.getParameter("sb_type"));
		req.setType(request.getParameter("type"));
		req.setUdid(request.getParameter("udid"));
		req.setUsername(request.getParameter("username"));
		// end
		RecordUtils.writeRequest(logger, request, req);

		CustDeviceVal val = new CustDeviceVal(req);
		String resultCode = val.validate();
		CustDeviceResp resp = null;
		if (!ResultCodes.NORMAL.equals(resultCode)) {

			resp = new CustDeviceResp();
			resp.setResultCode(resultCode);
			resp.setResultMsg(ResourceUtils.getString(resultCode));
		} else {
			CustDeviceService custDeviceService = new CustDeviceService();
			resp = (CustDeviceResp) custDeviceService
					.responseValue(req);
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
