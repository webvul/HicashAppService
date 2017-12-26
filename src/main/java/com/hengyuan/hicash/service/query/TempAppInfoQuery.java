package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.param.TempAppInfoInfoService;
import com.hengyuan.hicash.parameters.request.param.TempAppInofoReq;
import com.hengyuan.hicash.parameters.response.param.TempAppInofoResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
* @author dong.liu 
* 2017-12-20 下午5:51:59
* 类说明 :临时订单状态查询
 */
@WebServlet("/TempAppInfoQuery")
public class TempAppInfoQuery extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(TempAppInfoQuery.class);

	private static final long serialVersionUID = -5203334097401648190L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		TempAppInofoReq infoReq = new TempAppInofoReq();
		TempAppInofoResp infoResp =new TempAppInofoResp();
		infoReq.setTempAppNo(req.getParameter("tempAppNo"));
		RecordUtils.writeRequest(logger, req, infoReq);
		String restult = this.validate(infoReq);
		if (!ResultCodes.NORMAL.equals(restult)) {
			infoResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			infoResp.setResultMsg(resuMsg);
			RecordUtils.writeResponse(logger, "", infoResp);
			resp.getWriter().write(infoResp.toJson());
		}else{
			TempAppInfoInfoService infoService = new TempAppInfoInfoService();
			 infoResp = (TempAppInofoResp)infoService.responseValue(infoReq);
			infoResp.setResultMsg(ResourceUtils.getString(infoResp.getResultCode()));
			RecordUtils.writeResponse(logger, null, infoResp);
			resp.getWriter().write(infoResp.toJson());
		}
		
	}
	
	
	public String validate(TempAppInofoReq infoReq){
			
			if (RegexValidate.isNull(infoReq.getTempAppNo())) {
				return ResultCodes.CREATEAPPPAY_TEMPAPPNO_ISNULL;
			}
			return ResultCodes.NORMAL;
		}
		
	
	
}
