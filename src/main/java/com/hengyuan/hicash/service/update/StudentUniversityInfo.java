package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.StudentSchoolInfoService;
import com.hengyuan.hicash.parameters.request.user.StudentSchoolInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StudentSchoolInfoResp;
import com.hengyuan.hicash.service.validate.update.StudentSchoolInfoVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 学生完善资料(修改学校信息)
 * 
 * @author Cary.Liu
 * @create date 2014-07-16
 */
@WebServlet("/StudentUniversityInfo")
public class StudentUniversityInfo extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(StudentUniversityInfo.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/* 组装请求参数 */
		StudentSchoolInfoReq updateMsgReq = new StudentSchoolInfoReq();
		
		updateMsgReq.setUserName(req.getParameter("userName"));
		updateMsgReq.setUuid(req.getParameter("uuid"));
		/*
		 * 学校信息
		 */
		updateMsgReq.setUpdateType(req.getParameter("updateType"));
		updateMsgReq.setAppNo(req.getParameter("appNo"));
		updateMsgReq.setStuType(req.getParameter("stuType"));//学生类型
		updateMsgReq.setSchoolId(req.getParameter("schoolId"));
		updateMsgReq.setIptSchoolName(req.getParameter("iptSchoolName"));
		
		updateMsgReq.setSchoolSystem(req.getParameter("schoolSystem"));
		updateMsgReq.setEducationBk(req.getParameter("educationBk"));
		updateMsgReq.setStudyTimeYear(req.getParameter("studyTimeYear"));
		updateMsgReq.setStudyTimeMonth(req.getParameter("studyTimeMonth"));
		updateMsgReq.setSltGrade(req.getParameter("sltGrade"));
		updateMsgReq.setIptStuId(req.getParameter("iptStuId"));
		updateMsgReq.setIptStuDepartment(req.getParameter("iptStuDepartment"));
		updateMsgReq.setIptStuMajor(req.getParameter("iptStuMajor"));
		
		RecordUtils.writeRequest(logger, req, updateMsgReq);
		StudentSchoolInfoVal infoVal = new StudentSchoolInfoVal(updateMsgReq);
		String valResult = infoVal.validate();
		if(!ResultCodes.NORMAL.endsWith(valResult)){
			
			StudentSchoolInfoResp  infoResp = new StudentSchoolInfoResp();
			infoResp.setResultCode(valResult);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(valResult);
			infoResp.setResultMsg(resuMsg);
			
			infoResp.setUuid(updateMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(), infoResp);
			resp.getWriter().write(infoResp.toJson());
			
		}else{
			
			StudentSchoolInfoService updateMsgService = new StudentSchoolInfoService();
			
			StudentSchoolInfoResp  infoResp = (StudentSchoolInfoResp) updateMsgService.responseValue(updateMsgReq);
			
			String resuMsg = ResourceUtils.getString(infoResp.getResultCode());
			infoResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse =infoResp;
			
			parmResponse.setUuid(updateMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
		
	}
	
}
