package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.domain.service.user.UpdateRelationInfoService;
import com.hengyuan.hicash.parameters.request.user.RelationInfoReq;
import com.hengyuan.hicash.parameters.response.user.RelationInfoResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * @author fish
 *
 * @date 2017年1月11日 上午10:36:59
 */
@WebServlet("/UpdateRelationInfo")
public class UpdateRelationInfo extends HttpServlet {

	private static Logger logger = Logger.getLogger(UpdateRelationInfo.class);

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

		UpdateRelationInfoService service = new UpdateRelationInfoService();

		RelationInfoReq req = new RelationInfoReq();

		req.setUuid(request.getParameter("uuid"));
		
		req.setUserName(request.getParameter("userName"));// 获取用户名
		req.setMarital_status(request.getParameter("marital_status"));
		
		req.setImmediate_name(request.getParameter("immediate_name"));
		req.setImmediate_relation(request.getParameter("immediate_relation"));
		req.setImmediate_mobile(StringUtils.getMobile(request.getParameter("immediate_mobile")));
		
		req.setEmergency_name(request.getParameter("emergency_name"));
		req.setEmergency_relation(request.getParameter("emergency_relation"));
		req.setEmergency_mobile(StringUtils.getMobile(request.getParameter("emergency_mobile")));
		
		//配偶信息不做必填项
		req.setSpouse_name(request.getParameter("spouse_name"));
		req.setSpouse_mobile(request.getParameter("spouse_mobile"));
		/*if(null != request.getParameter("spouse_mobile") && !"".equals(request.getParameter("spouse_mobile"))){
			req.setSpouse_mobile(StringUtils.getMobile(request.getParameter("spouse_mobile")));
		}*/

		RecordUtils.writeRequest(logger, request, req);
		RelationInfoResp resp = (RelationInfoResp) service.responseValue(req);
		resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		RecordUtils.writeResponse(logger, null, resp);

		response.getWriter().write(resp.toJson());
	}
}
