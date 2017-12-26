package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.domain.service.user.CollarPersonInfoService;
import com.hengyuan.hicash.parameters.request.user.CollarPersonReq;
import com.hengyuan.hicash.parameters.response.user.CollarPersonResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
 * @author fish
 *
 * @date 2017年1月9日 下午4:43:43
 */
@WebServlet("/CollarPersonInfo")
public class CollarPersonInfo extends HttpServlet {

	private static Logger logger = Logger.getLogger(CollarPersonInfo.class);

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
		
		CollarPersonInfoService service = new CollarPersonInfoService();
		CollarPersonReq req = new CollarPersonReq();

		// 获取用户名
		req.setUserName(request.getParameter("userName"));

		RecordUtils.writeRequest(logger, request, req);
		CollarPersonResp resp = (CollarPersonResp) service.responseValue(req);
		resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		RecordUtils.writeResponse(logger, null, resp);
		
		System.out.println(resp.toJson());

		response.getWriter().write(resp.toJson());

	}

}
