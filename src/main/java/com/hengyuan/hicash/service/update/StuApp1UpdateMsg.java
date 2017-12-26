package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.StuApp1UpdateService;
import com.hengyuan.hicash.parameters.request.user.StuApp1UpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuApp1UpdateResp;
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
@WebServlet("/StuApp1UpdateMsg")
public class StuApp1UpdateMsg extends HttpServlet {


	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(StuApp1UpdateMsg.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/* 组装请求参数 */
		StuApp1UpdateReq updateMsgReq = new StuApp1UpdateReq();
		
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
		updateMsgReq.setQqNumber(req.getParameter("qqNumber"));//qq号码
		updateMsgReq.setHomePhoneArea(req.getParameter("homePhoneArea"));
		updateMsgReq.setHomePhoneNum(req.getParameter("homePhoneNum"));
		/* 2016-03-09 新增 */
		updateMsgReq.setNation(req.getParameter("nation"));
		updateMsgReq.setIdCardValStartDate(req.getParameter("idCardValStartDate"));
		updateMsgReq.setIdCardValEndDate(req.getParameter("idCardValEndDate"));
		updateMsgReq.setIdCardValidity(req.getParameter("idCardValidity"));
		
		updateMsgReq.setLoanUse(req.getParameter("loanUse"));
		
		//设置宿舍地址：省，市
//		updateMsgReq.setSchoolProvince(req.getParameter("schoolProvince"));
//		updateMsgReq.setSchoolCity(req.getParameter("schoolCity"));
//		updateMsgReq.setSchoolArea(req.getParameter("schoolArea"));
		RecordUtils.writeRequest(logger, req, updateMsgReq);
		StuApp1UpdateVal infoVal = new StuApp1UpdateVal(updateMsgReq);
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
			
			StuApp1UpdateService updateMsgService = new StuApp1UpdateService();
			
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
