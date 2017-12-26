package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.FastLoanFirstUpService;
import com.hengyuan.hicash.parameters.request.user.FastLoanFirstUpReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.FastLoanFirstUpResp;
import com.hengyuan.hicash.service.validate.update.FastLoanFirstVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;



/**
 * 秒贷金额期数创建
 * 
 * @author LiHua.Ren
 * @create date 2015-05-26
 */
@WebServlet("/FastLoanFirst")
public class FastLoanFirst extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(FastLoanFirst.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		//组装请求参数
		FastLoanFirstUpReq fastUpdate = new FastLoanFirstUpReq();
		
		fastUpdate.setUserName(req.getParameter("userName")); //用户名
		fastUpdate.setFastAmt(req.getParameter("fastAmt"));
		fastUpdate.setFastPeriod(req.getParameter("fastPeriod"));	
		fastUpdate.setAppFlowNo(req.getParameter("appFlowNo"));
		fastUpdate.setUuid(req.getParameter("uuid"));
		RecordUtils.writeRequest(logger, req, fastUpdate);
		FastLoanFirstVal infoVal = new FastLoanFirstVal(fastUpdate);
		String valResult = infoVal.validate();
		
		if(!ResultCodes.NORMAL.endsWith(valResult)){
			
			FastLoanFirstUpResp basicInfoResp = new FastLoanFirstUpResp();
			basicInfoResp.setResultCode(valResult);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(valResult);
			basicInfoResp.setResultMsg(resuMsg);
			
			basicInfoResp.setUuid(fastUpdate.getUuid());
			
			RecordUtils.writeResponse(logger, fastUpdate.getUuid(), basicInfoResp);
			resp.getWriter().write(basicInfoResp.toJson());
			
		}else{
			
			FastLoanFirstUpService collarUpdateMsgService = new FastLoanFirstUpService();

//			//查询业务流水号
//			PayFlowNoQuery pfnQuery=new PayFlowNoQuery();
//			String appFlowNo="";
//				try {
//					appFlowNo=pfnQuery.generateFlowNo("CASH");
//					fastUpdate.setAppFlowNo(appFlowNo);
//				} catch (GenerateFlowNoException e) {
//        			e.printStackTrace();
//				} catch (QueryFlowNoException e) {
//
//					e.printStackTrace();
//				}
	        FastLoanFirstUpResp fastInfoResp = (FastLoanFirstUpResp) collarUpdateMsgService.responseValue(fastUpdate);
			String resuMsg = ResourceUtils.getString(fastInfoResp.getResultCode());
			fastInfoResp.setResultMsg(resuMsg);
//			fastInfoResp.setAppFlowNo(appFlowNo);
			ParmResponse parmResponse =fastInfoResp;
			parmResponse.setUuid(fastUpdate.getUuid());
			RecordUtils.writeResponse(logger, fastUpdate.getUuid(), parmResponse);
			//响应参数
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
	}
}
