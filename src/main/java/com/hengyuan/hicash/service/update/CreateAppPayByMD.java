package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.mktApp.CreateAppPayByMDService;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppPayReq;
import com.hengyuan.hicash.parameters.response.mktApp.CreateAppPayResp;
import com.hengyuan.hicash.service.validate.update.CreateAppPayByMDVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

import org.apache.commons.lang.StringUtils;


/**
 * 创建申请件 创建嗨秒贷申请
 * 
 * @author Cary.Liu
 * @createDate 2015-05-27
 */
@WebServlet("/CreateAppPayByMD")
public class CreateAppPayByMD extends HttpServlet {

	private static Logger logger = Logger.getLogger(CreateAppPayByMD.class);

	private static final long serialVersionUID = -4146078745705753888L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		CreateAppPayReq createAppPayReq = new CreateAppPayReq();

		createAppPayReq.setUuid(request.getParameter("uuid"));
		createAppPayReq.setRebate(request.getParameter("rebate"));
		createAppPayReq.setUserName(request.getParameter("userName"));
		/* 代扣账户信息 */
		createAppPayReq.setTempAppNo(request.getParameter("tempAppNo"));
		createAppPayReq.setBankRealName(request.getParameter("bankRealName"));
		createAppPayReq.setOpenBank(request.getParameter("openBank"));
		createAppPayReq.setBankCard(request.getParameter("bankCard"));
		createAppPayReq.setProvince(request.getParameter("province"));
		createAppPayReq.setCity(request.getParameter("city"));
//		createAppPayReq.setIsSynPerAcct(request.getParameter("isSynPerAcct"));
		/* 其他信息 */
		createAppPayReq.setMerProId(request.getParameter("merProId"));
		createAppPayReq.setProductName(request.getParameter("productName"));
		createAppPayReq.setSupplierId(request.getParameter("supplierId"));
		createAppPayReq.setSiteId(request.getParameter("siteId"));
		createAppPayReq.setFirstRate(request.getParameter("firstRate"));
		createAppPayReq.setCustType(request.getParameter("custType"));
		createAppPayReq.setApplyType(request.getParameter("applyType"));

		createAppPayReq.setApplyFrom(request.getParameter("applyFrom"));// 申请来源
		createAppPayReq.setBrowserStr(request.getParameter("browserStr"));
		
		createAppPayReq.setLoanProduct(request.getParameter("loanProduct"));
		createAppPayReq.setTranPrice(request.getParameter("tranPrice"));

		createAppPayReq.setMdFlag(Consts.FINAL_NUMBER_1);
		//2017-08嗨钱迭代去掉网点
//		createAppPayReq.setOpenBankBranch(request.getParameter("openBankBranch"));
		createAppPayReq.setArea(request.getParameter("area"));
		createAppPayReq.setCoupon_id(request.getParameter("coupon_id"));//优惠券id
		// 新增的推荐信息start
//		createAppPayReq.setReference(checkNull(request.getParameter("reference")));
//		createAppPayReq.setBussiness(checkNull(request.getParameter("bussiness")));
//		createAppPayReq.setReferenceIP(checkNull(request.getParameter("referenceIP")));
//		createAppPayReq.setReferenceTime(checkNull(request.getParameter("referenceTime")));
//		createAppPayReq.setRecommend(checkNull(request.getParameter("recommend")));
//		createAppPayReq.setUpPage(checkNull(request.getParameter("upPage")));
		
//		createAppPayReq.setIsTdValidate(checkNull(request.getParameter("isTdValidate")));
//		createAppPayReq.setTdToken(checkNull(request.getParameter("tdToken")));
		createAppPayReq.setCustAppFrom(checkNull(request.getParameter("custAppFrom")));
		
//		createAppPayReq.setActiveFrom(checkNull(request.getParameter("activeFrom")));
//		createAppPayReq.setCustInvitecode(checkNull(request.getParameter("custInvitecode")));
		
		// end
		RecordUtils.writeRequest(logger, request, createAppPayReq);

		/* 验证表单重复提交 暂无 */

		CreateAppPayByMDVal val = new CreateAppPayByMDVal(createAppPayReq);
		String resultCode = val.validate();
		CreateAppPayResp resp = null;
		if (!ResultCodes.NORMAL.equals(resultCode)) {

			resp = new CreateAppPayResp();
			resp.setResultCode(resultCode);
			resp.setResultMsg(ResourceUtils.getString(resultCode));
		} else {

			CreateAppPayByMDService createAppPayService = new CreateAppPayByMDService();
			resp = (CreateAppPayResp) createAppPayService
					.responseValue(createAppPayReq);
			resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		}

		resp.setUuid(createAppPayReq.getUuid());
		RecordUtils.writeResponse(logger, createAppPayReq.getUuid(), resp);
		response.getWriter().write(resp.toJson());
	}
	public static String checkNull(String str) {

		if (StringUtils.isEmpty(str) || "null".equals(str)) {
			return "";
		} else {
			return str;
		}
	}
}
