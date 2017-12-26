package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.UpdateTempAppInfoService;
import com.hengyuan.hicash.parameters.request.user.UpdateTempAppInfoReq;
import com.hengyuan.hicash.parameters.response.user.UpdateTempAppInfoResp;
import com.hengyuan.hicash.service.validate.update.UpdateTempAppInfoVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;


/**
 * 临时订单表保存验证状态
* @author dong.liu 
* 2017-3-2 下午2:16:38
* 类说明
 */
@WebServlet("/UpdateTempAppInfo")
public class UpdateTempAppInfo  extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(UpdateTempAppInfo.class);

	private static final long serialVersionUID = -4146078745705753888L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UpdateTempAppInfoReq req = new UpdateTempAppInfoReq();
		req.setTempAppNo(request.getParameter("tempAppNo"));
		req.setApplyFrom(request.getParameter("applyFrom"));//
		req.setCustType(request.getParameter("custType"));//
		req.setIndustryCode(request.getParameter("industryCode"));//industryCode
		req.setNode(request.getParameter("node"));//node
		req.setStatus(request.getParameter("status"));//status
		
		RecordUtils.writeRequest(logger, request, req);
		
		/* 验证表单重复提交 暂无*/
		
		UpdateTempAppInfoVal val = new UpdateTempAppInfoVal(req);
		String resultCode = val.validate();
		UpdateTempAppInfoResp resp = null;
		if (!ResultCodes.NORMAL.equals(resultCode)) {
			
			resp = new UpdateTempAppInfoResp();
			resp.setResultCode(resultCode);
			resp.setResultMsg(ResourceUtils.getString(resultCode));
		}else {
			
			UpdateTempAppInfoService updateTempService = new UpdateTempAppInfoService();
			resp = (UpdateTempAppInfoResp) updateTempService.responseValue(req);
			resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, "", resp);
		response.getWriter().write(resp.toJson());
	}
}
