package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.StuApp3UpdateService;
import com.hengyuan.hicash.parameters.request.user.StuApp3UpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuApp3UpdateResp;
import com.hengyuan.hicash.service.validate.update.StuApp3UpdateVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash手机端学生提现申请3完善
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
@WebServlet("/StuApp3UpdateMsg")
public class StuApp3UpdateMsg extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(StuApp3UpdateMsg.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/* 组装请求参数 */
		StuApp3UpdateReq updateMsgReq = new StuApp3UpdateReq();
		
		updateMsgReq.setUserName(req.getParameter("userName"));
		updateMsgReq.setUuid(req.getParameter("uuid"));
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
//		updateMsgReq.setContactAddress(req.getParameter("contactAddress"));
		RecordUtils.writeRequest(logger, req, updateMsgReq);
		StuApp3UpdateVal infoVal = new StuApp3UpdateVal(updateMsgReq);
		String valResult = infoVal.validate();
		
		/* 验证请求参数 */
		if(!ResultCodes.NORMAL.equals(valResult)){
			
			StuApp3UpdateResp infoResp = new StuApp3UpdateResp();
			infoResp.setResultCode(valResult);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(valResult);
			infoResp.setResultMsg(resuMsg);
			
			infoResp.setUuid(updateMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(), infoResp);
			resp.getWriter().write(infoResp.toJson());
			
		}else{
			/* 处理服务 */
			StuApp3UpdateService updateMsgService = new StuApp3UpdateService();
			
			StuApp3UpdateResp infoResp = (StuApp3UpdateResp) updateMsgService.responseValue(updateMsgReq);
			
			String resuMsg = ResourceUtils.getString(infoResp.getResultCode());
			infoResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse =infoResp;
			
			parmResponse.setUuid(updateMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
	}
}
