package com.hengyuan.hicash.service.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.auth.DriverAuthSaveService;
import com.hengyuan.hicash.parameters.request.auth.DriverAuthSaveReq;
import com.hengyuan.hicash.parameters.response.auth.DriverAuthSaveResp;
import com.hengyuan.hicash.service.validate.auth.DriverAuthSaveVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 司机贷-车主认证账号保存
 * 
 * @author yangkun
 *
 */
@WebServlet("/DriverAuthSave")
public class DriverAuthSaveServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(DriverAuthSaveServlet.class);

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DriverAuthSaveReq req = new DriverAuthSaveReq();
		req.setUuid(request.getParameter("uuid"));
		req.setTempApplicationNo(request.getParameter("tempApplicationNo"));
		req.setDriverUsername(request.getParameter("driverUsername"));
		req.setType(request.getParameter("type"));
		
		RecordUtils.writeRequest(logger, request, req);
		DriverAuthSaveVal infoVal = new DriverAuthSaveVal(req);
		String valResult = infoVal.validate();
		
		DriverAuthSaveResp resp = new DriverAuthSaveResp();
		
		if (!ResultCodes.NORMAL.endsWith(valResult)) {

			resp.setResultCode(valResult);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(valResult);
			resp.setResultMsg(resuMsg);
			resp.setUuid(req.getUuid());

		} else {
			DriverAuthSaveService service=new DriverAuthSaveService();
			resp=(DriverAuthSaveResp) service.responseValue(req);
			
			resp.setUuid(req.getUuid());
			String resuMsg = ResourceUtils.getString(valResult);
			resp.setResultMsg(resuMsg);
		}
		RecordUtils.writeResponse(logger, req.getUuid(), resp);
		response.getWriter().write(resp.toJson());

	}
}
