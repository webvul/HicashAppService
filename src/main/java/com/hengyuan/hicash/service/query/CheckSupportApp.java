package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CheckSupportAppService;
import com.hengyuan.hicash.parameters.request.user.CheckSupportAppReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CheckSupportAppResp;
import com.hengyuan.hicash.parameters.response.user.NewCollarApp1MsgResp;
import com.hengyuan.hicash.service.validate.query.CheckSupportAppVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash改版17年:判断是否支持该产品(滴滴贷)
 * 
 * @author 0493
 * @create date 2017-01-09
 *
 */
@WebServlet("/CheckSupportApp")
public class CheckSupportApp extends HttpServlet {


	private static Logger logger = Logger.getLogger(CheckSupportApp.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CheckSupportAppReq checkAppReq = new CheckSupportAppReq();
		
		checkAppReq.setCustType(req.getParameter("custType"));
		checkAppReq.setIndustryCode(req.getParameter("industryCode"));
		checkAppReq.setIs_type(req.getParameter("is_type"));
		checkAppReq.setMobile(req.getParameter("mobile"));
		checkAppReq.setUser_name(req.getParameter("user_name"));
		checkAppReq.setPeriods(req.getParameter("periods"));
		checkAppReq.setTranPrice(req.getParameter("tranPrice"));
		checkAppReq.setApplyFrom(req.getParameter("applyFrom"));
		checkAppReq.setLoanProductId(req.getParameter("loanProductId"));
		
		checkAppReq.setUuid(req.getParameter("uuid"));//uuid
		
		RecordUtils.writeRequest(logger, req, checkAppReq);
		CheckSupportAppVal val = new CheckSupportAppVal(checkAppReq);
		String result = val.validate();
		if (!ResultCodes.NORMAL.equals(result)) {
			NewCollarApp1MsgResp valresp = new NewCollarApp1MsgResp();
			valresp.setResultCode(result);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(result);
			valresp.setResultMsg(resuMsg);
			valresp.setUuid(checkAppReq.getUuid());
			RecordUtils.writeResponse(logger, checkAppReq.getUuid(), valresp);
			resp.getWriter().write(valresp.toJson());
		}else if(Consts.INDUSTRY_LDDD.equals(checkAppReq.getIndustryCode())){
			//by yangkun 20171204  滴答贷 下线
			CheckSupportAppResp valresp =new CheckSupportAppResp();
			valresp.setResultCode(ResultCodes.CHECK_LDDD_BEING_ADJUSTED);
			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			valresp.setUuid(checkAppReq.getUuid());
			RecordUtils.writeResponse(logger, checkAppReq.getUuid(), valresp);
			resp.getWriter().write(valresp.toJson());
		}/*else if(Consts.INDUSTRY_DDSJ.equals(checkAppReq.getIndustryCode())){
			//by yangkun 20171212  司机贷 下线
			CheckSupportAppResp valresp =new CheckSupportAppResp();
			valresp.setResultCode(ResultCodes.CHECK_LDDD_BEING_ADJUSTED);
			
			valresp.setResultMsg("对不起,司机贷正在调整，敬请持续关注！");
			valresp.setUuid(checkAppReq.getUuid());
			RecordUtils.writeResponse(logger, checkAppReq.getUuid(), valresp);
			resp.getWriter().write(valresp.toJson());
		}*/
		
		else {
		
			CheckSupportAppService checkSupportService = new CheckSupportAppService();
			
			CheckSupportAppResp valresp=checkSupportService.isSupportProduct(checkAppReq);
			
			if(valresp.getResultCode().equals(ResultCodes.NORMAL)){
				valresp = (CheckSupportAppResp) checkSupportService.responseValue(checkAppReq);
			}
			
			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			if(Consts.INDUSTRY_DDSJ.equals(checkAppReq.getIndustryCode())){
				if("Y".equals(valresp.getIsCash())){
					valresp.setResultCode("0000");
					valresp.setResultMsg("对不起,司机贷正在调整，敬请持续关注！");
					
				}else {
					valresp.setIsJump("Y");
					valresp.setStrButton("看看别的去");
					valresp.setStrButton_url("index");
				}
				
			}
		
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(checkAppReq.getUuid());
			
			
			RecordUtils.writeResponse(logger, checkAppReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		}
}
