package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.SaveOrUpdateAddressService;
import com.hengyuan.hicash.parameters.request.user.CustReceiveAddressReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CustAddressResp;
import com.hengyuan.hicash.service.validate.update.SaveOrUpdateAddressVal;
import com.hengyuan.hicash.utils.ResourceUtils;
/**
 * 删除收货地址
 * @author ding
 *
 */
@WebServlet("/SaveOrUpdateAddress")
public class SaveOrUpdateAddress extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		CustReceiveAddressReq custReq = new CustReceiveAddressReq();
		
		custReq.setCust_user(req.getParameter("cust_user"));//用户名
		custReq.setUuid(req.getParameter("uuid"));//uuid
		custReq.setId(req.getParameter("id"));
		custReq.setReal_name(req.getParameter("real_name"));
		custReq.setMobile(req.getParameter("mobile"));
		custReq.setProvince_code(req.getParameter("province_code"));
		custReq.setCity_code(req.getParameter("city_code"));
		custReq.setArea_code(req.getParameter("area_code"));
		custReq.setIs_default(req.getParameter("is_default"));
		custReq.setDetail_address(req.getParameter("detail_address"));
		
		SaveOrUpdateAddressVal statusVal = new SaveOrUpdateAddressVal(custReq);
		String resultCode = statusVal.validate();
		if(!ResultCodes.NORMAL.equals(resultCode)){
			CustAddressResp statusResp = new CustAddressResp();
			statusResp.setResultCode(resultCode);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(resultCode);
			statusResp.setResultMsg(resuMsg);
			statusResp.setUuid(custReq.getUuid());
			resp.getWriter().write(statusResp.toJson());
			
		}else{
			SaveOrUpdateAddressService  service = new SaveOrUpdateAddressService();
			CustAddressResp valresp = (CustAddressResp) service.responseValue(custReq);
			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(custReq.getUuid());
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
	}
}
