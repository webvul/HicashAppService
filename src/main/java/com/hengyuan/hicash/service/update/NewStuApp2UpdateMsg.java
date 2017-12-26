package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.NewStuApp2UpdateService;
import com.hengyuan.hicash.domain.service.user.StuApp1UpdateService;
import com.hengyuan.hicash.parameters.request.user.NewStuApp2UpdateReq;
import com.hengyuan.hicash.parameters.request.user.StuApp1UpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuApp1UpdateResp;
import com.hengyuan.hicash.service.validate.update.NewStuApp2UpdateVal;
import com.hengyuan.hicash.service.validate.update.StuApp1UpdateVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash手机端学生提现申请2完善
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
@WebServlet("/NewStuApp2UpdateMsg")
public class NewStuApp2UpdateMsg extends HttpServlet {


	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(NewStuApp2UpdateMsg.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/* 组装请求参数 */
		NewStuApp2UpdateReq updateMsgReq = new NewStuApp2UpdateReq();
		
		updateMsgReq.setUserName(req.getParameter("userName"));
		updateMsgReq.setUuid(req.getParameter("uuid"));
		/*
		 * 学校信息
		 */
		updateMsgReq.setUpdateType(req.getParameter("updateType"));
		updateMsgReq.setAppNo(req.getParameter("appNo"));
		
		/*
		 * 联系人信息
		 */
		updateMsgReq.setFamilyName(req.getParameter("familyName"));
		updateMsgReq.setContactName(req.getParameter("contactName"));
		updateMsgReq.setFamilyRelation(req.getParameter("familyRelation"));
		updateMsgReq.setContactRelation(req.getParameter("contactRelation"));
		updateMsgReq.setFamilyWorkUnit(req.getParameter("familyWorkUnit"));
//		updateMsgReq.setContactWorkUnit(req.getParameter("contactWorkUnit"));
		updateMsgReq.setFamilyPhone(req.getParameter("familyPhone"));
		updateMsgReq.setContactPhone(req.getParameter("contactPhone"));
		updateMsgReq.setFamilyAddress(req.getParameter("familyAddress"));
		updateMsgReq.setSpouseName(req.getParameter("spouseName"));
		updateMsgReq.setSpouseMobile(req.getParameter("spouseMobile"));
		
		//设置宿舍地址：省，市
//		updateMsgReq.setSchoolProvince(req.getParameter("schoolProvince"));
//		updateMsgReq.setSchoolCity(req.getParameter("schoolCity"));
//		updateMsgReq.setSchoolArea(req.getParameter("schoolArea"));
		RecordUtils.writeRequest(logger, req, updateMsgReq);
		NewStuApp2UpdateVal infoVal = new NewStuApp2UpdateVal(updateMsgReq);
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
			
			NewStuApp2UpdateService updateMsgService = new NewStuApp2UpdateService();
			
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
