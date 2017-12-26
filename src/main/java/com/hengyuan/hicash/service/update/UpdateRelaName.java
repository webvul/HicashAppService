package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.UpdateRelaNameService;
import com.hengyuan.hicash.parameters.request.user.UpdateRelaNameReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.UpdateRelaNameResp;
import com.hengyuan.hicash.service.validate.update.UpdateRelaNameVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
* @author dong.liu 
* 2017-8-28 下午5:55:31
* 类说明:修改客户真实姓名
 */

@WebServlet("/UpdateRealName")
public class UpdateRelaName extends  HttpServlet {


	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(UpdateRelaName.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/**
		 *  组装请求参数 
		 */
		UpdateRelaNameReq upreq = new UpdateRelaNameReq();
		upreq.setUserName(req.getParameter("userName"));
		upreq.setRealName(req.getParameter("realName"));
		RecordUtils.writeRequest(logger, req, upreq);
		UpdateRelaNameVal infoVal = new UpdateRelaNameVal(upreq);
		String valResult = infoVal.validate();
		if(!ResultCodes.NORMAL.endsWith(valResult)){
			
			UpdateRelaNameResp upResp = new UpdateRelaNameResp();
			upResp.setResultCode(valResult);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(valResult);
			upResp.setResultMsg(resuMsg);
			RecordUtils.writeResponse(logger, null, upResp);
			resp.getWriter().write(upResp.toJson());
			
		}else{
			
			UpdateRelaNameService updateMsgService = new UpdateRelaNameService();
			
			UpdateRelaNameResp upResp  = (UpdateRelaNameResp) updateMsgService.responseValue(upreq);
			
			String resuMsg = ResourceUtils.getString(upResp.getResultCode());
			upResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse =upResp;
			
			RecordUtils.writeResponse(logger,"", parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
		
	}
}
