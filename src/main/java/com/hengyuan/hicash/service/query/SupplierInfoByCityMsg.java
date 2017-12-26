package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.SupplierInfoByCityMsgService;
import com.hengyuan.hicash.parameters.request.user.SupplierInfoByCityMsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.SupplierInfoByCityMsgResp;
import com.hengyuan.hicash.service.validate.query.SupplierInfoByCityMsgVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 根据城市code查询商户
 * 
 * @author lihua.Ren
 * @create date 2016-01-26
 *
 */
@WebServlet("/SupplierInfoByCityMsg")
public class SupplierInfoByCityMsg extends HttpServlet {

	private static final long serialVersionUID = -4030614367830870998L;
	private static Logger logger = Logger.getLogger(SupplierInfoByCityMsg.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		SupplierInfoByCityMsgReq queryMsgReq = new SupplierInfoByCityMsgReq();
		queryMsgReq.setCityCode(req.getParameter("cityCode"));
		queryMsgReq.setUuid(req.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, req, queryMsgReq);
		SupplierInfoByCityMsgVal val = new SupplierInfoByCityMsgVal(queryMsgReq);
		String restult = val.validate();
		if (!ResultCodes.NORMAL.equals(restult)) {
			SupplierInfoByCityMsgResp supplierResp = new SupplierInfoByCityMsgResp();
			supplierResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			supplierResp.setResultMsg(resuMsg);
			supplierResp.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), supplierResp);
			resp.getWriter().write(supplierResp.toJson());
		}else {
			SupplierInfoByCityMsgService  supplierService = new SupplierInfoByCityMsgService();
			SupplierInfoByCityMsgResp supplierResp = (SupplierInfoByCityMsgResp) supplierService.responseValue(queryMsgReq);

			String resuMsg = ResourceUtils.getString(supplierResp.getResultCode());
			supplierResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = supplierResp;
			parmResponse.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		
		
	}


}
