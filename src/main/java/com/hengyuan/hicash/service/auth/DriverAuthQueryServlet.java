package com.hengyuan.hicash.service.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.app.TempApplyQuery;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.parameters.request.auth.DriverAuthQueryReq;
import com.hengyuan.hicash.parameters.response.auth.DriverAuthItem;
import com.hengyuan.hicash.parameters.response.auth.DriverAuthQueryResp;
import com.hengyuan.hicash.service.validate.auth.DriverAuthQueryVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 司机贷-车主认证查询
 * 
 * @author yangkun
 *
 */
@WebServlet("/DriverAuthQuery")
public class DriverAuthQueryServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(DriverAuthQueryServlet.class);

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DriverAuthQueryReq req = new DriverAuthQueryReq();
		req.setUuid(request.getParameter("uuid"));
		req.setIndustryCode(request.getParameter("industryCode"));
		req.setUsername(request.getParameter("username"));
		
		RecordUtils.writeRequest(logger, request, req);
		DriverAuthQueryVal infoVal = new DriverAuthQueryVal(req);
		String valResult = infoVal.validate();
		
		DriverAuthQueryResp resp = new DriverAuthQueryResp();
		
		if (!ResultCodes.NORMAL.endsWith(valResult)) {

			resp.setResultCode(valResult);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(valResult);
			resp.setResultMsg(resuMsg);
			resp.setUuid(req.getUuid());

		} else {
			TempApplyQuery tempApplyQuery=new TempApplyQuery();
			List<TempApplyEntity> tempOrderList=tempApplyQuery.queryTempApply(req.getUsername(),req.getIndustryCode());
			
			List<DriverAuthItem> driverAuthItems=new ArrayList<>();
			DriverAuthItem item=new DriverAuthItem();
			item.setIcoUrl("http://115.29.250.40:8081/NewHicashService/proImage/indexwycsjd.png");
			item.setType("5");
			item.setName("滴滴");

			String driverAccountNo="";
			//从最近一次临时订单中获取司机认证账号
			if(!CollectionUtils.isEmpty(tempOrderList)){
				if(!StringUtils.isEmpty(tempOrderList.get(0).getPhoneDataId())){
					driverAccountNo=tempOrderList.get(0).getPhoneDataId();
				}else if(tempOrderList.get(1)!=null){
					driverAccountNo=tempOrderList.get(1).getPhoneDataId();
				}
			}
			item.setDriverUsername(driverAccountNo);
			
			driverAuthItems.add(item);
			resp.setDriverAuthItems(driverAuthItems);
			
			resp.setUuid(req.getUuid());
			resp.setResultCode(ResultCodes.NORMAL);
		}
		RecordUtils.writeResponse(logger, req.getUuid(), resp);
		response.getWriter().write(resp.toJson());

	}
}
