package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.BlackCertNoUpdateService;
import com.hengyuan.hicash.parameters.request.user.BlackCertNoUpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.BlackCertNoUpdateResp;
import com.hengyuan.hicash.service.validate.update.BlackCertNoUpdateVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 	webService接口查询身份证是否是黑名单
 * 
 * @author lihua.Ren
 * @create date 2015-08-26
 *
 */
@WebServlet("/BlackCertNoUpdate")
public class BlackCertNoUpdate extends  HttpServlet {


	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(BlackCertNoUpdate.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/* 组装请求参数 */
		BlackCertNoUpdateReq updateMsgReq = new BlackCertNoUpdateReq();
		updateMsgReq.setCertNo(req.getParameter("certNo"));
//		updateMsgReq.setUuid(req.getParameter("uuid"));
		updateMsgReq.setRequestIp(RecordUtils.getIpAddress(req));
	
		RecordUtils.writeRequest(logger, req, updateMsgReq);
		BlackCertNoUpdateVal infoVal = new BlackCertNoUpdateVal(updateMsgReq);
		String valResult = infoVal.validate();
		if(!ResultCodes.NORMAL.endsWith(valResult)){
			
			BlackCertNoUpdateResp infoResp = new BlackCertNoUpdateResp();
			infoResp.setResultCode(valResult);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(valResult);
			infoResp.setResultMsg(resuMsg);
			
			infoResp.setUuid(updateMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(), infoResp);
			resp.getWriter().write(infoResp.toJson());
			
		}else{
			
			BlackCertNoUpdateService updateMsgService = new BlackCertNoUpdateService();
			
			BlackCertNoUpdateResp  infoResp = (BlackCertNoUpdateResp) updateMsgService.responseValue(updateMsgReq);
			
			String resuMsg = ResourceUtils.getString(infoResp.getResultCode());
			infoResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse =infoResp;
			
			parmResponse.setUuid(updateMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
		
	}

}
