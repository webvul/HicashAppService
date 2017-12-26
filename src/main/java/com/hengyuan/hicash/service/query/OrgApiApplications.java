package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.param.HomePageInfoService;
import com.hengyuan.hicash.domain.service.user.JxlOrgApiApplicationsService;
import com.hengyuan.hicash.parameters.request.param.HomePageInfoReq;
import com.hengyuan.hicash.parameters.request.user.JxlOrgApiApplicationsReq;
import com.hengyuan.hicash.parameters.response.param.HomePageInfoResp;
import com.hengyuan.hicash.parameters.response.user.FastLoanFirstMsgResp;
import com.hengyuan.hicash.parameters.response.user.JxlOrgApiApplicationsResp;
import com.hengyuan.hicash.service.validate.query.FastLoanFirstMsgVal;
import com.hengyuan.hicash.service.validate.query.HomePageInfoVal;
import com.hengyuan.hicash.service.validate.query.JxlOrgApiApplicationsVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/** 
 * @author blanke.qin
 * 2017年1月11日 下午6:09:28
 * 类说明    提交申请表单获取回执信息
 */

@WebServlet("/OrgApiApplications")
public class OrgApiApplications  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5164602038435297001L;
	private static final Logger logger = Logger.getLogger(OrgApiApplications.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String  userName = req.getParameter("username");
		String  time = req.getParameter("time");
		String  industryCode = req.getParameter("industryCode");
		String  website = req.getParameter("website");
		String  password = req.getParameter("password");
		String  message = req.getParameter("message");
		String  seq_no = req.getParameter("seq_no");
		String  currentMsgType = req.getParameter("currentMsgType");
		
		//手机租赁 新加字段
		String  idCard = req.getParameter("idCard");
		String  name = req.getParameter("name");
		String  mobile = req.getParameter("mobile");
		String  familyName = req.getParameter("familyName");
		String  familyRelation = req.getParameter("familyRelation");
		String  familyMobile = req.getParameter("familyMobile");
		String  relaName = req.getParameter("relaName");
		String  relation = req.getParameter("relation");
		String  relaMobile = req.getParameter("relaMobile");
		
		JxlOrgApiApplicationsReq  infoReq = new  JxlOrgApiApplicationsReq();
		JxlOrgApiApplicationsResp infoResp = new JxlOrgApiApplicationsResp();
		 
		infoReq.setUsername(userName);
		infoReq.setCurrentMsgType(currentMsgType);
		infoReq.setMessage(message);
		infoReq.setIndustryCode(industryCode);
		infoReq.setSeq_no(seq_no);
		infoReq.setWebsite(website);
		infoReq.setTime(time);
		infoReq.setPassword(password);
		infoReq.setName(name);
		infoReq.setIdCard(idCard);
		infoReq.setMobile(mobile);
		
		//手机租赁 新加字段
		infoReq.setFamilyMobile(familyMobile);
		infoReq.setFamilyName(familyName);
		infoReq.setFamilyRelation(familyRelation);
		infoReq.setRelaMobile(relaMobile);
		infoReq.setRelaName(relaName);
		infoReq.setRelation(relation);
		
		RecordUtils.writeRequest(logger, req, infoReq);
		
		JxlOrgApiApplicationsVal val = new JxlOrgApiApplicationsVal(infoReq);
		
		String restult = val.validate();
		
		if(!ResultCodes.NORMAL.equals(restult)){
			
			infoResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			infoResp.setResultMsg(resuMsg);
			
		}else{
			
//			infoResp.setResultCode("1");
			
			JxlOrgApiApplicationsService jxlOrgApiApplicationsService = new JxlOrgApiApplicationsService();
			
			infoResp=(JxlOrgApiApplicationsResp) jxlOrgApiApplicationsService.responseValue(infoReq);
		}
		RecordUtils.writeResponse(logger, null, infoResp);
		resp.getWriter().write(infoResp.toJson());
		
	}
}
