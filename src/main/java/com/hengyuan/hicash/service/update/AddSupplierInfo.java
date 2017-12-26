package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.AddSupplierInfoService;
import com.hengyuan.hicash.parameters.request.user.AddSupplierInfoReq;
import com.hengyuan.hicash.parameters.response.user.AddSupplierInfoResp;
import com.hengyuan.hicash.service.validate.update.AddSupplierInfoVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/** 
 * 商户入驻-新增商户
 * @author Cary.Liu
 * @createDate 2015-07-10
 * 
 */
@WebServlet("/AddSupplierInfo")
public class AddSupplierInfo  extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(AddSupplierInfo.class);

	private static final long serialVersionUID = -4146078745705753888L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		AddSupplierInfoReq req = new AddSupplierInfoReq();
		req.setIdentifyCode(request.getParameter("identifyCode"));
		req.setCity(request.getParameter("city"));
		req.setProvince(request.getParameter("province"));
		req.setSupBusiLice(request.getParameter("supbusilice"));
		req.setRealName(request.getParameter("realName"));
		req.setSupIndustry(request.getParameter("supindustry"));
		req.setSupplierName(request.getParameter("supplierName"));
		req.setMobileNo(request.getParameter("mobileNo"));
		req.setSupplierUserName(request.getParameter("supplierusername"));
		req.setSupplierUserPassword(request.getParameter("supplieruserpassword"));
		req.setUuid(request.getParameter("uuid"));
		RecordUtils.writeRequest(logger, request, req);
		
		/* 验证表单重复提交 暂无*/
		
		AddSupplierInfoVal val = new AddSupplierInfoVal(req);
		String resultCode = val.validate();
		AddSupplierInfoResp resp = null;
		if (!ResultCodes.NORMAL.equals(resultCode)) {
			
			resp = new AddSupplierInfoResp();
			resp.setResultCode(resultCode);
			resp.setResultMsg(ResourceUtils.getString(resultCode));
		}else {
			
			AddSupplierInfoService addSupService = new AddSupplierInfoService();
			resp = (AddSupplierInfoResp) addSupService.responseValue(req);
			resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		}
		
		resp.setUuid(req.getUuid());
		RecordUtils.writeResponse(logger, req.getUuid(), resp);
		response.getWriter().write(resp.toJson());
	}
}
