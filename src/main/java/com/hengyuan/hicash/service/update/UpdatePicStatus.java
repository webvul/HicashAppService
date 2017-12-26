package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.UpdatePicStatusService;
import com.hengyuan.hicash.parameters.request.user.QuerySingleImgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.upload.SaveSingleImgResp;
import com.hengyuan.hicash.service.validate.query.QuerySingleImgVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;
/**
 * 把图片改为有效
 * @author Administrator
 *
 */

@WebServlet("/UpdatePicStatus")
public class UpdatePicStatus extends HttpServlet{

private static final long serialVersionUID = 5176458728437190121L;
	
	private static Logger logger = Logger.getLogger(UpdatePicStatus.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		QuerySingleImgReq updateAfterReq = new QuerySingleImgReq();
		updateAfterReq.setUserName(req.getParameter("userName"));
		updateAfterReq.setUuid(req.getParameter("uuid"));
	
		RecordUtils.writeRequest(logger, req, updateAfterReq);
		
		QuerySingleImgVal val = new QuerySingleImgVal(updateAfterReq);
		String restult = val.validate();
		if (!ResultCodes.NORMAL.equals(restult)) {
			SaveSingleImgResp uploadResp = new SaveSingleImgResp();
			uploadResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			uploadResp.setResultMsg(resuMsg);
			uploadResp.setUuid(updateAfterReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateAfterReq.getUuid(), uploadResp);
			resp.getWriter().write(uploadResp.toJson());
		}else {
			UpdatePicStatusService  stuMsgService = new UpdatePicStatusService();
			SaveSingleImgResp valresp = (SaveSingleImgResp) stuMsgService.responseValue(updateAfterReq);

			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(updateAfterReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateAfterReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		
	}
}
