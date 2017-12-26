package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.UpdateCustCardService;
import com.hengyuan.hicash.parameters.request.user.UpdateCustCardReq;
import com.hengyuan.hicash.parameters.response.user.UpdateCustCardResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
 * @author fish
 *
 * @date 2017年1月15日 下午1:59:41
 */
@WebServlet("/UpdateCustCard")
public class UpdateCustCard extends HttpServlet {

	private static Logger logger = Logger.getLogger(UpdateCustCard.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UpdateCustCardService service = new UpdateCustCardService();

		UpdateCustCardReq req = new UpdateCustCardReq();

		req.setUserName(request.getParameter("userName"));
		req.setPermanentAddressProvince(request.getParameter("permanentAddressProvince"));
		req.setPermanentAddressCity(request.getParameter("permanentAddressCity"));
		req.setPermanentAddressArea(request.getParameter("permanentAddressArea"));
		req.setPermanentAddressRaod(request.getParameter("permanentAddressRaod"));
		req.setNation(request.getParameter("nation"));
		req.setIdCardValStartDate(request.getParameter("idCardValStartDate"));
		req.setIdCardValEndDate(request.getParameter("idCardValEndDate"));
		req.setName(request.getParameter("name"));
		req.setIdentityNo(request.getParameter("identityNo"));
		req.setIdcardFrom(request.getParameter("idcardFrom"));
		req.setHyIndustryCode(request.getParameter("hyIndustryCode"));
		RecordUtils.writeRequest(logger, request, req);
		UpdateCustCardResp resp = (UpdateCustCardResp) service.responseValue(req);
		if(resp.getResultCode().equals(ResultCodes.HINS_SEX_IS_GIRL)){
			resp.setBut_url_1("index");
			resp.setBut_msg_1("本商城仅针对各位女神，男神请绕道现金产品");
		}
		resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		RecordUtils.writeResponse(logger, null, resp);

		response.getWriter().write(resp.toJson());
	}

}
