package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.UpdateMsgStatusService;
import com.hengyuan.hicash.parameters.request.user.UpdateMsgStatusReq;
import com.hengyuan.hicash.parameters.response.user.UpdateMsgStatusResp;
import com.hengyuan.hicash.service.validate.update.UpdateMsgStatusInfoVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;


/*
 * 更新是否已读
 * 
 * */
@WebServlet("/UpdateMsgStatus")
public class UpdateMsgStatus extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(UpdateMsgStatus.class);

	private static final long serialVersionUID = -4146078745705753888L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UpdateMsgStatusReq req = new UpdateMsgStatusReq();
		req.setId(request.getParameter("id"));//消息id
		req.setIsRead(request.getParameter("is_read"));//状态:1/已读,0/未读
		
		RecordUtils.writeRequest(logger, request, req);
		
		/* 验证表单重复提交 暂无*/
		
		UpdateMsgStatusInfoVal val = new UpdateMsgStatusInfoVal(req);
		String resultCode = val.validate();
		UpdateMsgStatusResp resp = null;
		if (!ResultCodes.NORMAL.equals(resultCode)) {
			
			resp = new UpdateMsgStatusResp();
			resp.setResultCode(resultCode);
			resp.setResultMsg(ResourceUtils.getString(resultCode));
		}else {
			
			UpdateMsgStatusService updateMsgService = new UpdateMsgStatusService();
			resp = (UpdateMsgStatusResp) updateMsgService.responseValue(req);
			resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, "", resp);
		response.getWriter().write(resp.toJson());
	}
}
