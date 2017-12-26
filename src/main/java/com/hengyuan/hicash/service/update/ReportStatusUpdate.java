package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CollarApp1UpdateService;
import com.hengyuan.hicash.domain.service.user.ReportStatusUpdateService;
import com.hengyuan.hicash.parameters.request.param.DreportStatusReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.param.DreportStatusResp;
import com.hengyuan.hicash.service.validate.update.ReportStatusUpdateVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
* @author dong.liu 
* 2017-12-23 上午11:03:59
* 类说明:认证状态更改
 */
@WebServlet("/ReportStatusUpdate")
public class ReportStatusUpdate extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ReportStatusUpdate.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 组装请求参数
		DreportStatusReq updateMsgReq = new DreportStatusReq();

		updateMsgReq.setTempAppNo(req.getParameter("tempAppNo"));
		updateMsgReq.setAuthen(req.getParameter("authen"));
		updateMsgReq.setAuthenStatus(req.getParameter("authenStatus"));

		updateMsgReq.setUuid(req.getParameter("uuid"));
		RecordUtils.writeRequest(logger, req, updateMsgReq);

		ReportStatusUpdateVal infoValNext = new ReportStatusUpdateVal(updateMsgReq);
		String valResult = infoValNext.validate();
		if (!ResultCodes.NORMAL.endsWith(valResult)) {

			DreportStatusResp basicInfoResp = new DreportStatusResp();
			basicInfoResp.setResultCode(valResult);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(valResult);
			basicInfoResp.setResultMsg(resuMsg);

			basicInfoResp.setUuid(updateMsgReq.getUuid());

			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(),
					basicInfoResp);
			resp.getWriter().write(basicInfoResp.toJson());

		} else {

			ReportStatusUpdateService collarUpdateMsgService = new ReportStatusUpdateService();

			DreportStatusResp basicInfoResp = (DreportStatusResp) collarUpdateMsgService
					.responseValue(updateMsgReq);

			String resuMsg = ResourceUtils.getString(basicInfoResp
					.getResultCode());
			basicInfoResp.setResultMsg(resuMsg);

			ParmResponse parmResponse = basicInfoResp;
			parmResponse.setUuid(updateMsgReq.getUuid());
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(),
					parmResponse);
			// 响应参数
			resp.getWriter().write(parmResponse.toJson());

		}

	}
}
