package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.param.StuInfoUpdateService;
import com.hengyuan.hicash.parameters.request.param.StuInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.param.StuInfoResp;
import com.hengyuan.hicash.service.validate.update.StuInfoUpdateVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
* @author dong.liu 
* 2017-1-10 下午2:43:51
* 类说明:学生个人信息修改
 */
@WebServlet("/StuInfoUpdate")
public class StuInfoUpdate extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(StuInfoUpdate.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 组装请求参数
		StuInfoReq stuInfoReq=new StuInfoReq();
		StuInfoResp stuInfoResp=new StuInfoResp();

		stuInfoReq.setUserName(req.getParameter("userName")); // 用户名
		stuInfoReq.setRealName(req.getParameter("realName"));
		stuInfoReq.setIdentityNo(req.getParameter("identityNo"));
		stuInfoReq.setEmailAdress(req.getParameter("emailAdress"));
		stuInfoReq.setMaritalStatus(req.getParameter("maritalStatus"));
		stuInfoReq.setOtherAdressProvince(req.getParameter("otherAdressProvince"));
		stuInfoReq.setOtherAdressCity(req.getParameter("otherAdressCity"));
		stuInfoReq.setOtherAdressArea(req.getParameter("otherAdressArea"));
		stuInfoReq.setOtherAccommodationAddress(req.getParameter("otherAccommodationAddress"));
		stuInfoReq.setUuid(req.getParameter("uuid"));
		stuInfoReq.setLoan_purpose(req.getParameter("loan_purpose"));
//		stuInfoReq.setIdcard_validity_startdate(req.getParameter("idcard_validity_startdate"));
//		stuInfoReq.setIdcard_validity_enddate(req.getParameter("idcard_validity_enddate"));
		RecordUtils.writeRequest(logger, req, stuInfoReq);

		StuInfoUpdateVal infoValNext = new StuInfoUpdateVal(stuInfoReq);
		String valResult = infoValNext.validate();

		if (!ResultCodes.NORMAL.endsWith(valResult)) {

			stuInfoResp.setResultCode(valResult);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(valResult);
			stuInfoResp.setResultMsg(resuMsg);

			stuInfoResp.setUuid(stuInfoReq.getUuid());

			RecordUtils.writeResponse(logger, stuInfoReq.getUuid(),
					stuInfoResp);
			resp.getWriter().write(stuInfoResp.toJson());

		} else {

			StuInfoUpdateService stuInfoUpdateService = new StuInfoUpdateService();

			stuInfoResp = (StuInfoResp) stuInfoUpdateService
					.responseValue(stuInfoReq);

			String resuMsg = ResourceUtils.getString(stuInfoResp
					.getResultCode());
			stuInfoResp.setResultMsg(resuMsg);

			ParmResponse parmResponse = stuInfoResp;
			parmResponse.setUuid(stuInfoResp.getUuid());
			RecordUtils.writeResponse(logger, stuInfoResp.getUuid(),
					parmResponse);
			// 响应参数
			resp.getWriter().write(parmResponse.toJson());

		}

	}
}
