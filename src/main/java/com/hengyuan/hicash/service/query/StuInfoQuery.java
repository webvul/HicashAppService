package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.param.StuInfoService;
import com.hengyuan.hicash.parameters.request.param.StuInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.param.StuInfoResp;
import com.hengyuan.hicash.service.validate.query.StuInfoVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/** 
 * @author dong.liu 
 * 2017-1-9 下午6:09:58
 * 类说明 :学生个人信息查询
 */
@WebServlet("/StuInfoQuery")
public class StuInfoQuery extends HttpServlet{
	
	

	private static final long serialVersionUID = -4030614367830870998L;
	private static Logger logger = Logger.getLogger(StuInfoQuery.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		StuInfoReq stuInfoReq=new StuInfoReq();
		StuInfoResp stuInfoResp=new StuInfoResp();
		stuInfoReq.setUserName(req.getParameter("userName"));//用户名
		stuInfoReq.setUuid(req.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, req, stuInfoReq);
		StuInfoVal val = new StuInfoVal(stuInfoReq);
		String restult = val.validate();
		if (!ResultCodes.NORMAL.equals(restult)) {
		
			stuInfoResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			stuInfoResp.setResultMsg(resuMsg);
			stuInfoResp.setUuid(stuInfoReq.getUuid());
			
			RecordUtils.writeResponse(logger, stuInfoReq.getUuid(), stuInfoResp);
			resp.getWriter().write(stuInfoResp.toJson());
		}else {
			StuInfoService  stuInfoService = new StuInfoService();
			stuInfoResp = (StuInfoResp) stuInfoService.responseValue(stuInfoReq);

			String resuMsg = ResourceUtils.getString(stuInfoResp.getResultCode());
			stuInfoResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = stuInfoResp;
			parmResponse.setUuid(stuInfoReq.getUuid());
			
			RecordUtils.writeResponse(logger, stuInfoReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		
		
	}

	
	
	

}
