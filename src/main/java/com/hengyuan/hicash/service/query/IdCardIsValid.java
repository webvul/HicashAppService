package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.IdCardIsValidService;
import com.hengyuan.hicash.parameters.request.user.IdCardIsValidReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.IdCardIsValidResp;
import com.hengyuan.hicash.service.validate.query.IdCardIsValidVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash改版17年:判断是否支持该产品(滴滴贷)
 * 
 * @author 0493
 * @create date 2017-01-09
 *
 */
@WebServlet("/IdCardIsValid")
public class IdCardIsValid extends HttpServlet {


	private static Logger logger = Logger.getLogger(IdCardIsValid.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		IdCardIsValidReq checkAppReq = new IdCardIsValidReq();

		checkAppReq.setUsername(req.getParameter("username"));
		
		RecordUtils.writeRequest(logger, req, checkAppReq);
		IdCardIsValidVal val = new IdCardIsValidVal(checkAppReq);
		String result = val.validate();
		if (!ResultCodes.NORMAL.equals(result)) {
			IdCardIsValidResp valresp = new IdCardIsValidResp();
			valresp.setResultCode(result);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(result);
			valresp.setResultMsg(resuMsg);
			valresp.setUuid(checkAppReq.getUuid());
			RecordUtils.writeResponse(logger, checkAppReq.getUuid(), valresp);
			resp.getWriter().write(valresp.toJson());
		}else {
		
			IdCardIsValidService idCardIsValidService = new IdCardIsValidService();
			
			IdCardIsValidResp valresp = (IdCardIsValidResp) idCardIsValidService.responseValue(checkAppReq);
			
			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(checkAppReq.getUuid());
			
			RecordUtils.writeResponse(logger, checkAppReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		}
}
