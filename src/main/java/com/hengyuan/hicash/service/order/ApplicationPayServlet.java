package com.hengyuan.hicash.service.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.domain.service.order.ApplicationPayService;
import com.hengyuan.hicash.parameters.request.order.ApplicationPayReq;
import com.hengyuan.hicash.parameters.response.order.ApplicationPayResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 根据流水号查询订单状态。
 * @author xuexin
 *
 * @date 2017年7月11日 18:52:44
 */
@WebServlet("/ApplicationPayServlet")
public class ApplicationPayServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(ApplicationPayServlet.class);

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ApplicationPayService service = new ApplicationPayService();

		ApplicationPayReq req = new ApplicationPayReq();
		req.setAppNo(request.getParameter("appNo"));
	
		RecordUtils.writeRequest(logger, request, req);
		ApplicationPayResp resp = (ApplicationPayResp) service.responseValue(req);
		resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		RecordUtils.writeResponse(logger, null, resp);

		response.getWriter().write(resp.toJson());
	}

}
