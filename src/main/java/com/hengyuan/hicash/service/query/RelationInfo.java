package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.hengyuan.hicash.domain.service.user.RelationInfoService;
import com.hengyuan.hicash.parameters.request.user.RelationInfoReq;
import com.hengyuan.hicash.parameters.response.user.RelationInfoResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
 * @author fish
 *
 * @date 2017年1月11日 上午9:33:38
 */
@WebServlet("/RelationInfo")
public class RelationInfo extends HttpServlet {

	private static Logger logger = Logger.getLogger(RelationInfo.class);

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

		RelationInfoService service = new RelationInfoService();

		RelationInfoReq req = new RelationInfoReq();

		// 获取用户名
		req.setUserName(request.getParameter("userName"));

		RecordUtils.writeRequest(logger, request, req);
		RelationInfoResp resp = (RelationInfoResp) service.responseValue(req);
		resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		RecordUtils.writeResponse(logger, null, resp);

		response.getWriter().write(resp.toJson());

	}

}
