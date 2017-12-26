package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.NewStuApp1UpdateService;
import com.hengyuan.hicash.domain.service.user.StuApp1UpdateService;
import com.hengyuan.hicash.parameters.request.user.NewStuApp1UpdateReq;
import com.hengyuan.hicash.parameters.request.user.StuApp1UpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuApp1UpdateResp;
import com.hengyuan.hicash.service.validate.update.NewStuApp1UpdateVal;
import com.hengyuan.hicash.service.validate.update.StuApp1UpdateVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash手机端学生提现申请1完善
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
@WebServlet("/NewStuApp1UpdateMsg")
public class NewStuApp1UpdateMsg extends HttpServlet {


	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(NewStuApp1UpdateMsg.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/* 组装请求参数 */
		NewStuApp1UpdateReq updateMsgReq = new NewStuApp1UpdateReq();
		
		updateMsgReq.setUserName(req.getParameter("userName"));
		updateMsgReq.setUuid(req.getParameter("uuid"));
		/*
		 * 学校信息
		 */
		updateMsgReq.setUpdateType(req.getParameter("updateType"));
		updateMsgReq.setAppNo(req.getParameter("appNo"));
		
		updateMsgReq.setQqNumber(req.getParameter("qqNumber"));//qq号码
		updateMsgReq.setHomePhoneArea(req.getParameter("homePhoneArea"));
		updateMsgReq.setHomePhoneNum(req.getParameter("homePhoneNum"));
		/* 2016-03-09 新增 */
		updateMsgReq.setNation(req.getParameter("nation"));
		updateMsgReq.setIdCardValStartDate(req.getParameter("idCardValStartDate"));
		updateMsgReq.setIdCardValEndDate(req.getParameter("idCardValEndDate"));
		updateMsgReq.setIdCardValidity(req.getParameter("idCardValidity"));
		
		updateMsgReq.setLoanUse(req.getParameter("loanUse"));
		
		/*
		 * 联系信息
		 */
		updateMsgReq.setSchoolProvince(req.getParameter("schoolProvince"));
		updateMsgReq.setSchoolCity(req.getParameter("schoolCity"));
		updateMsgReq.setSchoolDistrict(req.getParameter("schoolDistrict"));
		updateMsgReq.setSchoolDetails(req.getParameter("schoolDetails"));
		updateMsgReq.setHomeProvince(req.getParameter("homeProvince"));
		updateMsgReq.setHomeCity(req.getParameter("homeCity"));
		updateMsgReq.setHomeDistrict(req.getParameter("homeDistrict"));
		updateMsgReq.setHomeDetails(req.getParameter("homeDetails"));
		updateMsgReq.setExpressAddressType(req.getParameter("expressAddressType"));
		updateMsgReq.setExpressProvince(req.getParameter("expressProvince"));
		updateMsgReq.setExpressCity(req.getParameter("expressCity"));
		updateMsgReq.setExpressDistrict(req.getParameter("expressDistrict"));
		updateMsgReq.setExpressDetails(req.getParameter("expressDetails"));
		updateMsgReq.setRealName(req.getParameter("realName"));
		updateMsgReq.setIdentiyNo(req.getParameter("identiyNo"));

		
		
		RecordUtils.writeRequest(logger, req, updateMsgReq);
		NewStuApp1UpdateVal infoVal = new NewStuApp1UpdateVal(updateMsgReq);
		String valResult = infoVal.validate();
		if(!ResultCodes.NORMAL.endsWith(valResult)){
			
			StuApp1UpdateResp infoResp = new StuApp1UpdateResp();
			infoResp.setResultCode(valResult);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(valResult);
			infoResp.setResultMsg(resuMsg);
			
			infoResp.setUuid(updateMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(), infoResp);
			resp.getWriter().write(infoResp.toJson());
			
		}else{
			
			NewStuApp1UpdateService updateMsgService = new NewStuApp1UpdateService();
			
			StuApp1UpdateResp  infoResp = (StuApp1UpdateResp) updateMsgService.responseValue(updateMsgReq);
			
			String resuMsg = ResourceUtils.getString(infoResp.getResultCode());
			infoResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse =infoResp;
			
			parmResponse.setUuid(updateMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
		
	}
	

}
