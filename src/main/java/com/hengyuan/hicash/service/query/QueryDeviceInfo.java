package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CheckCreditService;
import com.hengyuan.hicash.domain.service.user.QueryDeviceInfoService;
import com.hengyuan.hicash.parameters.request.user.CheckCreditReq;
import com.hengyuan.hicash.parameters.request.user.QueryDeviceReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CheckCreditResp;
import com.hengyuan.hicash.parameters.response.user.QueryDeviceResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 查询滴滴司机贷客户能否授信
 * 
 * @author teng
 *
 */
@WebServlet("/QueryDeviceInfo")
public class QueryDeviceInfo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(QueryDeviceInfo.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		QueryDeviceReq queryDeviceReq = new QueryDeviceReq();
		queryDeviceReq.setApp_no(req.getParameter("app_no"));
		RecordUtils.writeRequest(logger, req, queryDeviceReq);

		String resultCode = validate(queryDeviceReq);

		if (!ResultCodes.NORMAL.equals(resultCode)) {
			QueryDeviceResp queryDeviceResp = new QueryDeviceResp();
			queryDeviceResp.setResultCode(resultCode);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(resultCode);
			queryDeviceResp.setResultMsg(resuMsg);
			resp.getWriter().write(queryDeviceResp.toJson());

		} else {
			QueryDeviceInfoService service = new QueryDeviceInfoService();
			QueryDeviceResp queryDeviceRespp = (QueryDeviceResp) service
					.responseValue(queryDeviceReq);
			String resuMsg = ResourceUtils.getString(queryDeviceRespp.getResultCode());
			queryDeviceRespp.setResultMsg(resuMsg);
			ParmResponse parmResponse = queryDeviceRespp;
			resp.getWriter().write(parmResponse.toJson());

		}

	}

	private String validate(QueryDeviceReq queryDeviceReq) {
		
		if (RegexValidate.isNull(queryDeviceReq.getApp_no())) {
			return ResultCodes.STU_UPDATE_APPNO_NOTNULL;
		}
		return ResultCodes.NORMAL;
	}

}
