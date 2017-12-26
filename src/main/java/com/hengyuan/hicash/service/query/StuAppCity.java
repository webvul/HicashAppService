package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.StuAppCityService;
import com.hengyuan.hicash.parameters.request.user.StuAppCityReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuAppCityResp;
import com.hengyuan.hicash.service.validate.query.StuAppCityVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash手机端学生提现申请完善1查询出对应省得城市
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
@WebServlet("/StuAppCity")
public class StuAppCity  extends HttpServlet{


	private static final long serialVersionUID = -5977206550198328092L;
	private static Logger logger = Logger.getLogger(StuAppCity.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		StuAppCityReq cityReq = new StuAppCityReq();
		cityReq.setProvinceCode(req.getParameter("provinceCode"));

		cityReq.setUuid(req.getParameter("uuid"));

		RecordUtils.writeRequest(logger, req, cityReq);
		StuAppCityVal val = new StuAppCityVal(cityReq);
		String restult = val.validate();
		if (!ResultCodes.NORMAL.equals(restult)) {
			StuAppCityResp stuAppCityResp = new StuAppCityResp();
			stuAppCityResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			stuAppCityResp.setResultMsg(resuMsg);
			stuAppCityResp.setUuid(cityReq.getUuid());
			
			RecordUtils.writeResponse(logger, cityReq.getUuid(), stuAppCityResp);
			resp.getWriter().write(stuAppCityResp.toJson());
		}else{
			StuAppCityService service = new StuAppCityService();

			StuAppCityResp CityResp = (StuAppCityResp) service
					.responseValue(cityReq);

			CityResp.setResultMsg(ResourceUtils.getString(CityResp
					.getResultCode()));

			ParmResponse parmResponse = CityResp;
			
			parmResponse.setUuid(cityReq.getUuid());
			RecordUtils.writeResponse(logger, cityReq.getUuid(),
					parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
		}	
	

}
