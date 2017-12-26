package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.StuApp2UpdateService;
import com.hengyuan.hicash.parameters.request.user.StuApp2UpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuApp2UpdateResp;
import com.hengyuan.hicash.service.validate.update.StuApp2UpdateVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash手机端学生提现申请2完善
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
@WebServlet("/StuApp2UpdateMsg")
public class StuApp2UpdateMsg extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(StuApp2UpdateMsg.class);
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/* 组装请求参数 */
		StuApp2UpdateReq updateMsgReq = new StuApp2UpdateReq();
		
		updateMsgReq.setUuid(req.getParameter("uuid"));
		updateMsgReq.setUserName(req.getParameter("userName"));
		updateMsgReq.setUpdateType(req.getParameter("updateType"));
		updateMsgReq.setAppNo(req.getParameter("appNo"));
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
		RecordUtils.writeRequest(logger, req, updateMsgReq);
		/* 验证请求参数 */
		StuApp2UpdateVal contactInfoVal = new StuApp2UpdateVal(updateMsgReq);
		String valResult = contactInfoVal.validate();
		
		if(!ResultCodes.NORMAL.equals(valResult)){
			
			StuApp2UpdateResp contactInfoResp = new StuApp2UpdateResp();
			contactInfoResp.setResultCode(valResult);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(valResult);
			contactInfoResp.setResultMsg(resuMsg);
			
			contactInfoResp.setUuid(updateMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(), contactInfoResp);
			resp.getWriter().write(contactInfoResp.toJson());
		}else{
			//处理服务
			StuApp2UpdateService updateMsgService = new StuApp2UpdateService();
			
			StuApp2UpdateResp contactInfoResp = (StuApp2UpdateResp) updateMsgService.responseValue(updateMsgReq);
			
			String resuMsg = ResourceUtils.getString(contactInfoResp.getResultCode());
			contactInfoResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse =contactInfoResp;
			
			parmResponse.setUuid(updateMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
	}
}
