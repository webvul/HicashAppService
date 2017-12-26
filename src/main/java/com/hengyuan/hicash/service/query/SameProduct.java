package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.param.SameProductService;
import com.hengyuan.hicash.parameters.request.param.SameProductReq;
import com.hengyuan.hicash.parameters.response.param.SameProductResp;
import com.hengyuan.hicash.service.validate.query.SameProductVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 同款商品查询
 * @author Cary.Liu
 * @createDate 2015-04-23
 *
 */
@WebServlet("/SameProduct")
public class SameProduct extends HttpServlet {

	private static final long serialVersionUID = -2655607230863374393L;
	
	private static Logger logger = Logger.getLogger(SameProduct.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		SameProductReq sameReq = new SameProductReq();
		sameReq.setCityCode(req.getParameter("cityCode"));
//		sameReq.setOrderMark(req.getParameter("orderMark"));
		sameReq.setProClass(req.getParameter("proClass"));
		String pageNo = req.getParameter("pageNo");
		if(!StringUtils.isEmpty(pageNo) && !"".equals(pageNo)){
			sameReq.setPageNo(pageNo);
		}
		RecordUtils.writeRequest(logger, req, sameReq);
		
		SameProductVal sameVal = new SameProductVal(sameReq);
		String resultCode = sameVal.validate();
		
		SameProductResp sameResp = null;
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			sameResp = new SameProductResp();
			sameResp.setResultCode(resultCode);
			sameResp.setResultMsg(ResourceUtils.getString(resultCode));
			
		}else{
			
			SameProductService sameService = new SameProductService();
			sameResp = (SameProductResp)sameService.responseValue(sameReq);
			sameResp.setResultMsg(ResourceUtils.getString(sameResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, null, sameResp);
		resp.getWriter().write(sameResp.toJson());
	}
	
}
