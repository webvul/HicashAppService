package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.domain.service.user.UpdateCollarPersonInfoService;
import com.hengyuan.hicash.parameters.request.user.CollarPersonReq;
import com.hengyuan.hicash.parameters.response.user.CollarPersonResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
 * @author fish
 *白领专用
 * @date 2017年1月10日 上午9:44:41
 */
@WebServlet("/UpdateCollarPersonInfo")
public class UpdateCollarPersonInfo extends HttpServlet {

	private static Logger logger = Logger.getLogger(UpdateCollarPersonInfo.class);

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

		UpdateCollarPersonInfoService service = new UpdateCollarPersonInfoService();
		
		CollarPersonReq req = new CollarPersonReq();
		req.setUuid(request.getParameter("uuid"));
		req.setUserName(request.getParameter("userName"));				//用户名
		req.setReal_name(request.getParameter("real_name"));			//真实姓名
		req.setId_no(request.getParameter("id_no"));					//身份证号码
		req.setMarital_status(request.getParameter("marital_status"));	//婚姻状况
		req.setEducation_code(request.getParameter("education_code"));	//最高学历
		req.setProvince(request.getParameter("province"));				//省
		req.setCity(request.getParameter("city"));						//市
		req.setArea(request.getParameter("area"));						//区
		req.setAddress(request.getParameter("address"));				//现居住详细地址
		req.setEmail(request.getParameter("email"));					//电子邮箱
		req.setUnit_phone_area(request.getParameter("unit_phone_area"));//单位电话区号
		//司机贷中也使用此字段
		req.setUnit_name(request.getParameter("unit_name"));			//单位名称
		req.setUnit_phone(request.getParameter("unit_phone"));			//单位电话
		req.setWork_year(request.getParameter("work_year"));			//工作年限
		req.setUnit_province(request.getParameter("unit_province"));	//单位省
		req.setUnit_city(request.getParameter("unit_city"));			//单位市
		req.setUnit_area(request.getParameter("unit_area"));			//单位区
		req.setUnit_address(request.getParameter("unit_address"));		//单位详细地址
		req.setUnit_properties(request.getParameter("unit_properties"));//行业性质
		req.setLoan_purpose(request.getParameter("loan_purpose"));
//		req.setCredit_card(request.getParameter("credit_card"));
//		req.setIdcard_validity_startdate(request.getParameter("idcard_validity_startdate"));
//		req.setIdcard_validity_enddate(request.getParameter("idcard_validity_enddate"));
		
		//新增加字段是否兼职0：其他 1兼职 2全职（司机贷中使用）
		String fulltimeDriver  = request.getParameter("fulltimeDriver")==null || request.getParameter("fulltimeDriver").equals("")?"":request.getParameter("fulltimeDriver"); 
		req.setFulltimeDriver(fulltimeDriver);
		req.setQqNumber(service.subQQEmail(req.getEmail(), "@qq.com")); //记录当前用户所有填写的QQ邮箱的QQ号 ，如果不是QQ邮箱则不截取

		RecordUtils.writeRequest(logger, request, req);
		CollarPersonResp resp = (CollarPersonResp) service.responseValue(req);
		resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		RecordUtils.writeResponse(logger, null, resp);

		response.getWriter().write(resp.toJson());
	}

}
