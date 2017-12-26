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
import com.hengyuan.hicash.parameters.request.user.NewCollarApp2UpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollarApp1UpdateResp;
import com.hengyuan.hicash.service.validate.update.CollarApp1UpdateVal;
import com.hengyuan.hicash.service.validate.update.NewCollarApp2UpdateVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash手机端白领提现申请2完善
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
@WebServlet("/NewCollarApp2Update")
public class NewCollarApp2Update extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(NewCollarApp2Update.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 组装请求参数
		NewCollarApp2UpdateReq updateMsgReq = new NewCollarApp2UpdateReq();

		updateMsgReq.setUserName(req.getParameter("userName")); // 用户名
		updateMsgReq.setUpdateType(req.getParameter("updateType"));
		updateMsgReq.setAppNo(req.getParameter("appNo"));

		/*
		 * 单位信息
		 */
		
		updateMsgReq.setUnitName(req.getParameter("unitName"));// 单位名称
		updateMsgReq.setInDepartment(req.getParameter("inDepartment"));
		updateMsgReq.setWorkDate(req.getParameter("workDate"));// 工作年限

		updateMsgReq
				.setUnitStartDateYear(req.getParameter("unitStartDateYear"));
		updateMsgReq.setUnitEndDateMonth(req.getParameter("unitEndDateMonth"));
		updateMsgReq.setUnitProvince(req.getParameter("unitProvince"));// 单位地址
		updateMsgReq.setUnitCity(req.getParameter("unitCity"));
		updateMsgReq.setUnitDistrict(req.getParameter("unitDistrict"));
		updateMsgReq.setUnitDetails(req.getParameter("unitDetails"));
		updateMsgReq.setExpressAddressType(req.getParameter("expressAddressType"));
		updateMsgReq.setExpressProvince(req.getParameter("expressProvince"));
		updateMsgReq.setExpressCity(req.getParameter("expressCity"));
		updateMsgReq.setExpressDistrict(req.getParameter("expressDistrict"));
		updateMsgReq.setExpressDetails(req.getParameter("expressDetails"));
		updateMsgReq.setUnitPhoneArea(req.getParameter("unitPhoneArea"));//单位区号
		updateMsgReq.setUnitPhoneNum(req.getParameter("unitPhoneNum"));//单位电话
		updateMsgReq.setMonthlyIncome(req.getParameter("monthlyIncome"));
		
		/**2016-4-13 teng*/
		updateMsgReq.setCompanyScale(req.getParameter("companyScale"));
		updateMsgReq.setCompanyWorkYear(req.getParameter("companyWorkYear"));
		updateMsgReq.setJobduties(req.getParameter("jobduties"));
		
		updateMsgReq.setUuid(req.getParameter("uuid"));
		RecordUtils.writeRequest(logger, req, updateMsgReq);

		NewCollarApp2UpdateVal infoValNext = new NewCollarApp2UpdateVal(updateMsgReq);
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
