package com.hengyuan.hicash.service.update.himoney;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.himoney.AuthenticationInfoUpdateService;
import com.hengyuan.hicash.entity.himoney.AuthenticationInfoEntity;
import com.hengyuan.hicash.parameters.request.himoney.AuthenticationInfoReq;
import com.hengyuan.hicash.parameters.response.himoney.AuthenticationInfoResp;
import com.hengyuan.hicash.service.validate.himoney.AuthenticationInfoValidata;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 根据流水号查询订单状态。
 * @author xuexin
 *
 * @date 2017年7月11日 18:52:44
 */
@WebServlet("/AuthenticationInfoUpdate")
public class AuthenticationInfoUpdate extends HttpServlet {

	private static Logger logger = Logger.getLogger(AuthenticationInfoUpdate.class);

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//解析参数转实体类
		AuthenticationInfoReq req = new AuthenticationInfoReq();
		req.setTempAppNo(request.getParameter("tempAppNo")); //临时订单号
		req.setUserName(request.getParameter("userName")); //用户名称
		req.setAuthId(request.getParameter("authId"));
		req.setStatus("status03");//认证中
		//log
		RecordUtils.writeRequest(logger, request, req);
		//校验参数是否合法	
		AuthenticationInfoResp resp = null;
		AuthenticationInfoValidata val = new AuthenticationInfoValidata(req);
		String resultCode = val.validateReq();
		
	  if(!ResultCodes.NORMAL.equals(resultCode)){			
		  resp = new AuthenticationInfoResp(); 
		  resp.setResultCode(resultCode);			
		}else{		
			AuthenticationInfoUpdateService service = new AuthenticationInfoUpdateService();
			resp =(AuthenticationInfoResp)service.responseValue(req);			
		}		
	  	resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		RecordUtils.writeResponse(logger, null, resp);
		response.getWriter().write(resp.toJson());
	}

}
