package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.StuAppSchoolService;
import com.hengyuan.hicash.parameters.request.user.StuAppSchoolReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuAppSchoolResp;
import com.hengyuan.hicash.service.validate.query.StuAppSchoolVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash手机端学生提现申请完善1查询出对应城市的大学
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
@WebServlet("/StuAppSchool")
public class StuAppSchool  extends HttpServlet{


	private static final long serialVersionUID = -5977206550198328092L;
	private static Logger logger = Logger.getLogger(StuAppSchool.class);
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		StuAppSchoolReq schoolReq = new StuAppSchoolReq();

		schoolReq.setUuid(req.getParameter("uuid"));
		schoolReq.setCityCode(req.getParameter("cityCode"));
		RecordUtils.writeRequest(logger, req, schoolReq);
		StuAppSchoolVal val = new StuAppSchoolVal(schoolReq);
		String restult = val.validate();
		if (!ResultCodes.NORMAL.equals(restult)) {
			StuAppSchoolResp stuAppschoolResp = new StuAppSchoolResp();
			
			stuAppschoolResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			stuAppschoolResp.setResultMsg(resuMsg);
			stuAppschoolResp.setUuid(schoolReq.getUuid());
			
			RecordUtils.writeResponse(logger, schoolReq.getUuid(), stuAppschoolResp);
			resp.getWriter().write(stuAppschoolResp.toJson());
		}else{
			StuAppSchoolService service = new StuAppSchoolService();

			StuAppSchoolResp schoolResp = (StuAppSchoolResp) service
					.responseValue(schoolReq);

			schoolResp.setResultMsg(ResourceUtils.getString(schoolResp
					.getResultCode()));

			ParmResponse parmResponse = schoolResp;
			
			parmResponse.setUuid(schoolReq.getUuid());
			RecordUtils.writeResponse(logger, schoolReq.getUuid(),
					parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
	}
}
