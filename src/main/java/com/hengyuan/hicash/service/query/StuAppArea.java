package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.StuAppAreaService;
import com.hengyuan.hicash.parameters.request.user.StuAppAreaReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuAppAreaResp;
import com.hengyuan.hicash.service.validate.query.StuAppAreaVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash手机端学生提现申请完善1查询出对应城市的区域
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
@WebServlet("/StuAppArea")
public class StuAppArea  extends HttpServlet{


	private static final long serialVersionUID = -5977206550198328092L;
	private static Logger logger = Logger.getLogger(StuAppArea.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		StuAppAreaReq areaReq = new StuAppAreaReq();

		areaReq.setUuid(req.getParameter("uuid"));
		areaReq.setCityCode(req.getParameter("cityCode"));
		RecordUtils.writeRequest(logger, req, areaReq);
		StuAppAreaVal val = new StuAppAreaVal(areaReq);
		String restult = val.validate();
		if (!ResultCodes.NORMAL.equals(restult)) {
			StuAppAreaResp stuAppAreaResp = new StuAppAreaResp();
			stuAppAreaResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			stuAppAreaResp.setResultMsg(resuMsg);
			stuAppAreaResp.setUuid(areaReq.getUuid());
			
			RecordUtils.writeResponse(logger, areaReq.getUuid(), stuAppAreaResp);
			resp.getWriter().write(stuAppAreaResp.toJson());
		}else{
			StuAppAreaService service = new StuAppAreaService();

			StuAppAreaResp areaResp = (StuAppAreaResp) service
					.responseValue(areaReq);

			areaResp.setResultMsg(ResourceUtils.getString(areaResp
					.getResultCode()));

			ParmResponse parmResponse = areaResp;
			
			parmResponse.setUuid(areaReq.getUuid());
			RecordUtils.writeResponse(logger, areaReq.getUuid(),
					parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
		}	
}
