package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.UpdateAddressDefaultService;
import com.hengyuan.hicash.parameters.request.user.CustReceiveAddressReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CustAddressResp;
import com.hengyuan.hicash.service.validate.update.CustReceiveAddressVal;
import com.hengyuan.hicash.utils.ResourceUtils;
/**
 * 删除收货地址
 * @author ding
 *
 */
@WebServlet("/UpdateAddressDefault")
public class UpdateAddressDefault extends HttpServlet{

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
		
		custReq.setUuid(req.getParameter("uuid"));//uuid
		custReq.setId(req.getParameter("id"));
		
		CustReceiveAddressVal statusVal = new CustReceiveAddressVal(custReq);
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
			UpdateAddressDefaultService  service = new UpdateAddressDefaultService();
			CustAddressResp valresp = (CustAddressResp) service.responseValue(custReq);
			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(custReq.getUuid());
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
	}
}
