package com.hengyuan.hicash.service.update;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.FundWaiMaiRepotExistService;
import com.hengyuan.hicash.parameters.request.user.FundWaiMaiReportExistReq;
import com.hengyuan.hicash.parameters.response.user.FundWaiMaiReportExistResp;
import com.hengyuan.hicash.service.query.YiDaoMark;
import com.hengyuan.hicash.service.validate.update.FundWaiMaiReportExistVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 通知来：公积金外卖报告（已经有报告，保存，跑批的时候用）
 * @author 0493
 * @create date 2017-05-13
 *
 */
@WebServlet("/FundWMReportExist")
public class FundWaiMaiReportExist extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(YiDaoMark.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FundWaiMaiRepotExistService service = new FundWaiMaiRepotExistService();
		FundWaiMaiReportExistResp resp = new FundWaiMaiReportExistResp();
		FundWaiMaiReportExistReq req = new FundWaiMaiReportExistReq();
		req.setIdentityNo(request.getParameter("identityNo"));
		req.setMobileNo(request.getParameter("mobileNo"));
		req.setRealName(request.getParameter("realName"));
//		req.setRealName(new String(request.getParameter("realName").getBytes(
//				"ISO-8859-1"), "UTF-8"));//测试环境
		req.setType(request.getParameter("type"));//公积金和外卖不能为空
		req.setUuid(UUID.randomUUID().toString());
		RecordUtils.writeRequest(logger, request, req);

		FundWaiMaiReportExistVal val = new FundWaiMaiReportExistVal(req);
		String result = val.validate();

		if (!ResultCodes.NORMAL.equals(result)) {

			resp.setResultCode(result);
			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(result);
			resp.setResultMsg(resuMsg);
			resp.setUuid(req.getUuid());
			RecordUtils.writeResponse(logger, resp.getUuid(), resp);
			response.getWriter().write(resp.toJson());
		} else {

			resp = (FundWaiMaiReportExistResp) service.responseValue(req);
			resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
			RecordUtils.writeResponse(logger, null, resp);
			response.getWriter().write(resp.toJson());
		}
	}


}
