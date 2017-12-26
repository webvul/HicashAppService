package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.apply.TempAppInfoEvent;
import com.hengyuan.hicash.exception.UpdateTempAppException;
import com.hengyuan.hicash.parameters.request.user.UpdateOperatorResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
 * @author wangliang
 * @date 2017年8月22日 上午10:23:01
 */
@WebServlet("/UpdateOperatorInfo")
public class UpdateOperatorInfo extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(UpdateOperatorInfo.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UpdateOperatorResp updateOperatorResp = new UpdateOperatorResp();
		
		TempAppInfoEvent tempAppInfoEvent = new TempAppInfoEvent();
		
		String appTempNo = request.getParameter("appTempNo");
		
		//8 已认证  9认证失败 0需认证
		String operatorFlag = request.getParameter("operatorFlag");
		
		//8 已认证  9认证失败 
		String xuexinFlag = request.getParameter("xuexinFlag");
		
		if(RegexValidate.isNull(appTempNo)){
			updateOperatorResp.setResultCode(ResultCodes.SERVICEPSWVAL_TEMPAPPNO_ISNULL);
		}else{
			try {
				tempAppInfoEvent.updateTempOperFlag(appTempNo,operatorFlag,xuexinFlag);
				updateOperatorResp.setResultCode(ResultCodes.NORMAL);
			} catch (UpdateTempAppException e) {
				e.printStackTrace();
				updateOperatorResp.setResultCode(ResultCodes.CREATEAPP_UPDATETEMP_EXCEPTION);
			}
		}
		
		updateOperatorResp.setResultMsg(ResourceUtils.getString(updateOperatorResp.getResultCode()));
		RecordUtils.writeResponse(logger, null, updateOperatorResp);
		response.getWriter().write(updateOperatorResp.toJson());
		
	}
	

}
