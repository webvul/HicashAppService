package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CollarApp2UpdateService;
import com.hengyuan.hicash.parameters.request.user.CollarApp2UpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollarApp2UpdateResp;
import com.hengyuan.hicash.service.validate.update.CollarApp2UpdateVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 手机端App1白领完善资料2
 * 
 * @author LiHua.Ren
 * @create date 2015-06-17
 */
@WebServlet("/CollarApp2Update")
public class CollarApp2Update extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CollarApp2Update.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 组装请求参数
		CollarApp2UpdateReq updateMsgReq = new CollarApp2UpdateReq();

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
		updateMsgReq.setHomeProvince(req.getParameter("homeProvince"));
		updateMsgReq.setHomeCity(req.getParameter("homeCity"));
		updateMsgReq.setHomeDistrict(req.getParameter("homeDistrict"));
		updateMsgReq.setHomeDetails(req.getParameter("homeDetails"));
		updateMsgReq.setUnitPhoneArea(req.getParameter("unitPhoneArea"));//单位区号
		updateMsgReq.setUnitPhoneNum(req.getParameter("unitPhoneNum"));//单位电话
		updateMsgReq.setMonthlyConsumption(req.getParameter("monthlyConsumption"));
		updateMsgReq.setMonthlyIncome(req.getParameter("monthlyIncome"));
		
		/**2016-4-13 teng*/
		updateMsgReq.setCompanyScale(req.getParameter("companyScale"));
		updateMsgReq.setCompanyWorkYear(req.getParameter("companyWorkYear"));
		updateMsgReq.setJobduties(req.getParameter("jobduties"));
		
		updateMsgReq.setUuid(req.getParameter("uuid"));
		RecordUtils.writeRequest(logger, req, updateMsgReq);

		CollarApp2UpdateVal infoValNext = new CollarApp2UpdateVal(updateMsgReq);
		String valResult = infoValNext.validate();

		if (!ResultCodes.NORMAL.endsWith(valResult)) {

			CollarApp2UpdateResp basicInfoResp = new CollarApp2UpdateResp();
			basicInfoResp.setResultCode(valResult);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(valResult);
			basicInfoResp.setResultMsg(resuMsg);

			basicInfoResp.setUuid(updateMsgReq.getUuid());

			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(),
					basicInfoResp);
			resp.getWriter().write(basicInfoResp.toJson());

		} else {

			CollarApp2UpdateService collarUpdateMsgService = new CollarApp2UpdateService();

			CollarApp2UpdateResp basicInfoResp = (CollarApp2UpdateResp) collarUpdateMsgService
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
