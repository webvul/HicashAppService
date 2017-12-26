package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.NewStuApp1Service;
import com.hengyuan.hicash.domain.service.user.NewStuApp3Service;
import com.hengyuan.hicash.parameters.request.user.StuAppReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.NewStuApp1Resp;
import com.hengyuan.hicash.parameters.response.user.NewStuApp3Resp;
import com.hengyuan.hicash.service.validate.query.StuAppVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash手机端学生提现申请1查询
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
@WebServlet("/NewStuApp3Msg")
public class NewStuApp3Msg extends HttpServlet {

	private static final long serialVersionUID = -4030614367830870998L;
	private static Logger logger = Logger.getLogger(NewStuApp3Msg.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		StuAppReq queryMsgReq = new StuAppReq();
		queryMsgReq.setUserName(req.getParameter("userName"));//用户名
		queryMsgReq.setUuid(req.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, req, queryMsgReq);
		StuAppVal val = new StuAppVal(queryMsgReq);
		String restult = val.validate();
		if (!ResultCodes.NORMAL.equals(restult)) {
			NewStuApp3Resp stuApp3Resp = new NewStuApp3Resp();
			stuApp3Resp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			stuApp3Resp.setResultMsg(resuMsg);
			stuApp3Resp.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), stuApp3Resp);
			resp.getWriter().write(stuApp3Resp.toJson());
		}else {
			NewStuApp3Service  stuMsgService = new NewStuApp3Service();
			NewStuApp3Resp valresp = (NewStuApp3Resp) stuMsgService.responseValue(queryMsgReq);

			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		
		
	}


}
