package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.AppAgainBasicService;
import com.hengyuan.hicash.parameters.request.user.AppAgainBasicReq;
import com.hengyuan.hicash.parameters.request.user.AppAgainBasicResp;
import com.hengyuan.hicash.service.validate.query.AppAgainBasicVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
 * @author wangliang
 * @date 2017年8月22日 上午10:23:01
 */
@WebServlet("/AppAgainBasicInfo")
public class AppAgainBasicInfo extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(AppAgainBasicInfo.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AppAgainBasicReq appAgainBasicReq = new AppAgainBasicReq();
		AppAgainBasicResp appAgainBasicResp = new AppAgainBasicResp();
		
		appAgainBasicReq.setAppTempNo(request.getParameter("appTempNo"));
		RecordUtils.writeRequest(logger, request, appAgainBasicReq);
		
		AppAgainBasicVal appAgainBasicVal = new AppAgainBasicVal(appAgainBasicReq);
		String resultCode = appAgainBasicVal.validate();
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			appAgainBasicResp.setResultCode(resultCode);
		}else{
			AppAgainBasicService appAgainBasicService = new AppAgainBasicService();
			appAgainBasicResp = (AppAgainBasicResp)appAgainBasicService.responseValue(appAgainBasicReq);
		}
		
		appAgainBasicResp.setResultMsg(ResourceUtils.getString(appAgainBasicResp.getResultCode()));
		RecordUtils.writeResponse(logger, null, appAgainBasicResp);
		response.getWriter().write(appAgainBasicResp.toJson());
		
	}

}
