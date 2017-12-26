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
import com.hengyuan.hicash.parameters.request.user.CollarApp1UpdateReq;
import com.hengyuan.hicash.parameters.request.user.NewCollarApp1UpdateReq;
import com.hengyuan.hicash.parameters.request.user.NewCollarApp3UpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollarApp1UpdateResp;
import com.hengyuan.hicash.service.validate.update.CollarApp1UpdateVal;
import com.hengyuan.hicash.service.validate.update.NewCollarApp3UpdateVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash手机端白领提现申请3完善
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
@WebServlet("/NewCollarApp3Update")
public class NewCollarApp3Update extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(NewCollarApp3Update.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 组装请求参数
		NewCollarApp3UpdateReq updateMsgReq = new NewCollarApp3UpdateReq();

		updateMsgReq.setUserName(req.getParameter("userName")); // 用户名
		updateMsgReq.setUpdateType(req.getParameter("updateType"));
		updateMsgReq.setAppNo(req.getParameter("appNo"));

		/*
		 * 单位信息
		 */
		updateMsgReq.setSchoolName(req.getParameter("schoolName"));// 毕业院校
		updateMsgReq
				.setEducationalSystem(req.getParameter("educationalSystem"));// 学制
		updateMsgReq.setEducationBg(req.getParameter("educationBg")); // 最高学历
		updateMsgReq.setEndDateYear(req.getParameter("endDateYear"));// 毕业时间-年
		updateMsgReq.setEndDateMonth(req.getParameter("endDateMonth"));// 毕业时间-月
		updateMsgReq.setUuid(req.getParameter("uuid"));
		RecordUtils.writeRequest(logger, req, updateMsgReq);

		NewCollarApp3UpdateVal infoValNext = new NewCollarApp3UpdateVal(updateMsgReq);
		String valResult = infoValNext.validate();
		if (!ResultCodes.NORMAL.endsWith(valResult)) {

			CollarApp1UpdateResp basicInfoResp = new CollarApp1UpdateResp();
			basicInfoResp.setResultCode(valResult);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(valResult);
			basicInfoResp.setResultMsg(resuMsg);

			basicInfoResp.setUuid(updateMsgReq.getUuid());

			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(),
					basicInfoResp);
			resp.getWriter().write(basicInfoResp.toJson());

		} else {

			CollarApp1UpdateService collarUpdateMsgService = new CollarApp1UpdateService();

			CollarApp1UpdateResp basicInfoResp = (CollarApp1UpdateResp) collarUpdateMsgService
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
