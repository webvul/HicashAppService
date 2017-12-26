package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.domain.service.param.ChannelInfoService;
import com.hengyuan.hicash.parameters.request.param.ChannelInfoReq;
import com.hengyuan.hicash.parameters.response.param.ChannelInfoResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 获取频道列表
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
@WebServlet("/ChannelInfo")
public class ChannelInfo extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(ChannelInfo.class);

	private static final long serialVersionUID = -5203334097401648190L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ChannelInfoReq infoReq = new ChannelInfoReq();
		infoReq.setChannelType(req.getParameter("channelType"));
		infoReq.setCityCode(req.getParameter("cityCode"));
		RecordUtils.writeRequest(logger, req, infoReq);
		
		ChannelInfoService infoService = new ChannelInfoService();
		ChannelInfoResp infoResp = (ChannelInfoResp)infoService.responseValue(infoReq);
		infoResp.setResultMsg(ResourceUtils.getString(infoResp.getResultCode()));
		
		RecordUtils.writeResponse(logger, null, infoResp);
		resp.getWriter().write(infoResp.toJson());
	}
	
}
