package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.TongDunValRulesService;
import com.hengyuan.hicash.parameters.request.user.TongDunValRulesReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuApp1UpdateResp;
import com.hengyuan.hicash.parameters.response.user.TongDunValRulesResp;
import com.hengyuan.hicash.service.validate.update.TongDunValRulesVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 同盾接口验证客户黑名单，客户身份证号码、手机号码、邮箱、qq
 * 
 * @author lihua.Ren
 * @create date 2015-11-10
 *
 */
@WebServlet("/TongDunValRules")
public class TongDunValRules extends HttpServlet {

	private static final long serialVersionUID = -3975255199164109072L;
	private static Logger logger = Logger.getLogger(TongDunValRules.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		/* 组装请求参数 */
		TongDunValRulesReq rulesReq = new TongDunValRulesReq();

		rulesReq.setAccount_email(req.getParameter("account_email"));

		rulesReq.setId_number(req.getParameter("id_number"));
		rulesReq.setAccount_mobile(req.getParameter("account_mobile"));
		rulesReq.setAccount_name(req.getParameter("account_name"));
		rulesReq.setToken_id(req.getParameter("token_id"));
		rulesReq.setIp_address(req.getParameter("ip_address"));
		rulesReq.setUuid(req.getParameter("uuid"));

		RecordUtils.writeRequest(logger, req, rulesReq);
		TongDunValRulesVal infoVal = new TongDunValRulesVal(rulesReq);
		String valResult = infoVal.validate();
		if (!ResultCodes.NORMAL.endsWith(valResult)) {

			StuApp1UpdateResp infoResp = new StuApp1UpdateResp();
			infoResp.setResultCode(valResult);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(valResult);
			infoResp.setResultMsg(resuMsg);

			infoResp.setUuid(rulesReq.getUuid());

			RecordUtils.writeResponse(logger, rulesReq.getUuid(), infoResp);
			resp.getWriter().write(infoResp.toJson());

		} else {

			TongDunValRulesService rulesService = new TongDunValRulesService();

			TongDunValRulesResp infoResp = (TongDunValRulesResp) rulesService
					.responseValue(rulesReq);

			String resuMsg = ResourceUtils.getString(infoResp.getResultCode());
			infoResp.setResultMsg(resuMsg);

			ParmResponse parmResponse = infoResp;

			parmResponse.setUuid(rulesReq.getUuid());

			RecordUtils.writeResponse(logger, rulesReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());

		}

	}

}
