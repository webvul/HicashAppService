package com.hengyuan.hicash.service.mktApp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.HyAddressService;
import com.hengyuan.hicash.parameters.request.user.HyAddressReq;
import com.hengyuan.hicash.parameters.response.user.HyAddressResp;
import com.hengyuan.hicash.service.validate.update.HyAddressVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 新增收货地址
 * @author Cary.Liu
 * @createDate 2015-05-19
 *
 */
@WebServlet("/AddAdress")
public class AddAdress extends HttpServlet {
	
private static final long serialVersionUID = 4276014570841305863L;
	
	private static Logger logger = Logger.getLogger(AddAdress.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HyAddressReq hyAddressReq = new HyAddressReq();
		hyAddressReq.setRealName(req.getParameter("realName"));
		hyAddressReq.setUserName(req.getParameter("userName"));
		hyAddressReq.setMobile(req.getParameter("mobile"));
		hyAddressReq.setEmailAddress(req.getParameter("emailAddress"));
		RecordUtils.writeRequest(logger, req, hyAddressReq);
		
		
		HyAddressResp lotResp = null;
		HyAddressVal lotVal = new HyAddressVal(hyAddressReq);
		String resultCode = lotVal.validate();
		
        if(!ResultCodes.NORMAL.equals(resultCode)){			
			lotResp = new HyAddressResp(); 
			lotResp.setResultCode(resultCode);			
		}else{		
			HyAddressService lotService = new HyAddressService();
			lotResp = (HyAddressResp)lotService.responseValue(hyAddressReq);			
		}		
		lotResp.setResultMsg(ResourceUtils.getString(lotResp.getResultCode()));
		RecordUtils.writeResponse(logger, null, lotResp);
		resp.getWriter().write(lotResp.toJson());
	}
	

}
