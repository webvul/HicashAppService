package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.StuAppProvinceService;
import com.hengyuan.hicash.parameters.request.user.StuAppProvinceReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuAppProvinceResp;
import com.hengyuan.hicash.service.validate.query.StuAppProvinceVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;


/**
 * hicash手机端学生提现申请完善1查询出所有的省
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
@WebServlet("/StuAppProvince")
public class StuAppProvince extends HttpServlet {

	private static final long serialVersionUID = -5977206550198328092L;
	private static Logger logger = Logger.getLogger(StuAppProvince.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		StuAppProvinceReq provinceReq = new StuAppProvinceReq();

		provinceReq.setUuid(req.getParameter("uuid"));

		RecordUtils.writeRequest(logger, req, provinceReq);
		StuAppProvinceVal val = new StuAppProvinceVal(provinceReq);
		String restult = val.validate();
		if (!ResultCodes.NORMAL.equals(restult)) {
			StuAppProvinceResp stuAppProvinceResp = new StuAppProvinceResp();
			stuAppProvinceResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			stuAppProvinceResp.setResultMsg(resuMsg);
			stuAppProvinceResp.setUuid(provinceReq.getUuid());
			
			RecordUtils.writeResponse(logger, provinceReq.getUuid(), stuAppProvinceResp);
			resp.getWriter().write(stuAppProvinceResp.toJson());
		}else{
			StuAppProvinceService service = new StuAppProvinceService();

			StuAppProvinceResp ProvinceResp = (StuAppProvinceResp) service
					.responseValue(provinceReq);

			ProvinceResp.setResultMsg(ResourceUtils.getString(ProvinceResp
					.getResultCode()));

			ParmResponse parmResponse = ProvinceResp;
			
			parmResponse.setUuid(provinceReq.getUuid());
			RecordUtils.writeResponse(logger, provinceReq.getUuid(),
					parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
	}
}
