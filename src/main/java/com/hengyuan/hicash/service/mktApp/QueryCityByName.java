package com.hengyuan.hicash.service.mktApp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.domain.service.mktApp.QueryCityByNameService;
import com.hengyuan.hicash.parameters.request.mktApp.QueryCityByNameReq;
import com.hengyuan.hicash.parameters.response.mktApp.QueryCityByNameResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 根据名称获取城市
 * @author Cary.Liu
 * @createDate 2015-05-19
 *
 */
@WebServlet("/QueryCityByName")
public class QueryCityByName extends HttpServlet {

	private static final long serialVersionUID = 4276014570841305863L;
	
	private static Logger logger = Logger.getLogger(QueryCityByName.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		QueryCityByNameReq cityReq = new QueryCityByNameReq();
		cityReq.setCityName(req.getParameter("cityName"));
		RecordUtils.writeRequest(logger, req, cityReq);
		
		QueryCityByNameService cityService = new QueryCityByNameService();
		QueryCityByNameResp cityResp = (QueryCityByNameResp)cityService.responseValue(cityReq);
		cityResp.setResultMsg(ResourceUtils.getString(cityResp.getResultCode()));
		
		RecordUtils.writeResponse(logger, null, cityResp);
		resp.getWriter().write(cityResp.toJson());
	}
	
}
