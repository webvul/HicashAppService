package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.mktApp.CreateAppBlueCollarService;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppBlueCollarReq;
import com.hengyuan.hicash.parameters.response.mktApp.CreateAppBlueCollarResp;
import com.hengyuan.hicash.service.validate.update.CreateAppBlueCollarVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;



/**
 * 创建申请件 分为三个部分，保存客户信息，创建申请信息，创建贷款信息
 * 
 * @author LiHua.Ren
 * @create date 2016-01-25
 *
 */
@WebServlet("/CreateAppBlueCollar")
public class CreateAppBlueCollar extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(CreateAppBlueCollar.class);

	private static final long serialVersionUID = -4146078745705753888L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CreateAppBlueCollarReq createAppPayReq = new CreateAppBlueCollarReq();

		createAppPayReq.setIndustryCode(request.getParameter("industryCode"));
//		createAppPayReq.setApproveUser(request.getParameter("approveUser"));
		createAppPayReq.setUserName(request.getParameter("userName"));
		createAppPayReq.setProductName(request.getParameter("productName"));
		createAppPayReq.setTranPrice(request.getParameter("tranPrice"));
		createAppPayReq.setSupplierId(request.getParameter("supplierId"));
		createAppPayReq.setSiteId(request.getParameter("siteId"));
		createAppPayReq.setLoanProduct(request.getParameter("loanProduct"));//信贷产品ID
		createAppPayReq.setFirstRate(request.getParameter("firstRate"));
		createAppPayReq.setProductDetail(request.getParameter("productDetail"));
		
		createAppPayReq.setCustType(request.getParameter("custType"));
		createAppPayReq.setApplyType(request.getParameter("applyType"));
		
		createAppPayReq.setBankNo(request.getParameter("bankNo"));
		
//		createAppPayReq.setMerProPrice(request.getParameter("merProPrice")); // 商品总价 2016-02-23取消该参数
		createAppPayReq.setMerProId(request.getParameter("merProId"));
		
		createAppPayReq.setApplyUse(request.getParameter("applyUse")); // 2015-09-15 借款用途 DASHBOARD
		createAppPayReq.setCreateFlag(request.getParameter("createFlag")); // 1：走嗨商贷等聚信立流程
		createAppPayReq.setApplyFrom(request.getParameter("applyFrom")); // 申请来源 
		/* 2016-03-16新借款用途 */
		createAppPayReq.setLoanUse(request.getParameter("loanUse"));
		
		
		createAppPayReq.setUuid(request.getParameter("uuid"));
		RecordUtils.writeRequest(logger, request, createAppPayReq);
		
		/* 验证表单重复提交 暂无*/
		
		CreateAppBlueCollarVal val = new CreateAppBlueCollarVal(createAppPayReq);
		String resultCode = val.validate();
		CreateAppBlueCollarResp resp = null;
		if (!ResultCodes.NORMAL.equals(resultCode)) {
			
			resp = new CreateAppBlueCollarResp();
			resp.setResultCode(resultCode);
			resp.setResultMsg(ResourceUtils.getString(resultCode));
		}else {
			
			CreateAppBlueCollarService createAppPayService = new CreateAppBlueCollarService();
			resp = (CreateAppBlueCollarResp) createAppPayService.responseValue(createAppPayReq);
			resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		}
		
		resp.setUuid(createAppPayReq.getUuid());
		RecordUtils.writeResponse(logger, createAppPayReq.getUuid(), resp);
		response.getWriter().write(resp.toJson());
	}

}
