package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.param.ProShowDetailService;
import com.hengyuan.hicash.parameters.request.param.ProShowDetailReq;
import com.hengyuan.hicash.parameters.response.param.ProShowDetailResp;
import com.hengyuan.hicash.service.validate.query.ProShowDetailVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 产品展示详情
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
@WebServlet("/ProShowDetail")
public class ProShowDetail extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(ProShowDetail.class);

	private static final long serialVersionUID = 5482544295448042571L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ProShowDetailReq proShowReq = new ProShowDetailReq();
		proShowReq.setUuid(req.getParameter("uuid"));
		proShowReq.setMerProId(req.getParameter("merProId"));
		proShowReq.setQuerySiteMark(req.getParameter("querySiteMark"));
		
		RecordUtils.writeRequest(logger, req, proShowReq);
		ProShowDetailVal proShowVal = new ProShowDetailVal(proShowReq);
		String resultCode = proShowVal.validate();
		ProShowDetailResp proShowResp = null;
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			proShowResp = new ProShowDetailResp();
			proShowResp.setResultCode(resultCode);
		}else{
			ProShowDetailService proShowService = new ProShowDetailService();
			proShowResp = (ProShowDetailResp)proShowService.responseValue(proShowReq);
		}
		
		proShowResp.setResultMsg(ResourceUtils.getString(proShowResp.getResultCode()));
		proShowResp.setUuid(proShowReq.getUuid());
		RecordUtils.writeResponse(logger, proShowReq.getUuid(), proShowResp);
		resp.getWriter().write(proShowResp.toJson());
	}
	
	
}
