package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.QueryDeviceInfoService;
import com.hengyuan.hicash.domain.service.user.QueryValNameService;
import com.hengyuan.hicash.parameters.request.user.QueryValNameReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.QueryValNameResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.ResourceUtils;


/**
 * 
* @author dong.liu 
* 2017-9-6 上午10:28:52
* 类说明
 */
@WebServlet("/QueryValName")
public class QueryValName extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(QueryValName.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		QueryValNameReq queryValNameReq = new QueryValNameReq();
		queryValNameReq.setUserName(req.getParameter("userName"));
		RecordUtils.writeRequest(logger, req, queryValNameReq);

		String resultCode = validate(queryValNameReq);

		if (!ResultCodes.NORMAL.equals(resultCode)) {
			QueryValNameResp queryValNameResp = new QueryValNameResp();
			queryValNameResp.setResultCode(resultCode);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(resultCode);
			queryValNameResp.setResultMsg(resuMsg);
			resp.getWriter().write(queryValNameResp.toJson());

		} else {
			QueryValNameService service = new QueryValNameService();
			QueryValNameResp queryValNameResp = (QueryValNameResp) service
					.responseValue(queryValNameReq);
			String resuMsg = ResourceUtils.getString(queryValNameResp.getResultCode());
			queryValNameResp.setResultMsg(resuMsg);
			ParmResponse parmResponse = queryValNameResp;
			resp.getWriter().write(parmResponse.toJson());

		}

	}

	private String validate(QueryValNameReq queryValNameReq) {
		
		if (RegexValidate.isNull(queryValNameReq.getUserName())) {
			return ResultCodes.ADDMERAPP_USERNAME_ISNULL;
		}
		return ResultCodes.NORMAL;
	}

}
