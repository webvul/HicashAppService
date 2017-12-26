package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.RedpacketIsValidService;
import com.hengyuan.hicash.parameters.request.user.RedpacketIsValidReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.RedpacketIsValidResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * @author xing.yuan
 * @date 2017年12月21日 下午3:28:12
 * 类说明
 */
@WebServlet("/RedpacketIsValid")
public class RedpacketIsValid extends HttpServlet{

	private static final long serialVersionUID = -4030614367830870998L;
	private static Logger logger = Logger.getLogger(RedpacketIsValid.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RedpacketIsValidReq redpacketIsValidReq = new RedpacketIsValidReq();
		redpacketIsValidReq.setType(req.getParameter("type"));
		if("1".equals(redpacketIsValidReq.getType())){//验证登录
			redpacketIsValidReq.setMoblieNo(req.getParameter("mobileNo"));//手机号
			redpacketIsValidReq.setPassword(req.getParameter("password"));//密码
		}else if("2".equals(redpacketIsValidReq.getType())){//领取红包
			redpacketIsValidReq.setMoblieNo(req.getParameter("userName"));//用户名
		}
		
		RecordUtils.writeRequest(logger, req, redpacketIsValidReq);
		
		String restult = null;
		if (RegexValidate.isNull(redpacketIsValidReq.getMoblieNo())) {
			restult = ResultCodes.SENDPSWCODE_MOBILE_ISNULL;
		}else if(RegexValidate.isNull(redpacketIsValidReq.getType())){
			restult = ResultCodes.NEWYEARREDPACK_ACTTYPE_ISNULL;
		}else{
			restult = ResultCodes.NORMAL;
		}
		
		if (!ResultCodes.NORMAL.equals(restult)) {
			RedpacketIsValidResp redpacketIsValidResp = new RedpacketIsValidResp();
			redpacketIsValidResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			redpacketIsValidResp.setResultMsg(resuMsg);
			redpacketIsValidResp.setUuid(redpacketIsValidReq.getUuid());
			
			RecordUtils.writeResponse(logger, redpacketIsValidReq.getUuid(), redpacketIsValidResp);
			resp.getWriter().write(redpacketIsValidResp.toJson());
		}else {
			RedpacketIsValidService  redpacketIsValidService = new RedpacketIsValidService();
			RedpacketIsValidResp redpacketIsValidResp = (RedpacketIsValidResp) redpacketIsValidService.responseValue(redpacketIsValidReq);
			
			System.out.println(redpacketIsValidResp.toJson());
			logger.info("返回结果:"+redpacketIsValidResp.toJson());
			String resuMsg = ResourceUtils.getString(redpacketIsValidResp.getResultCode());
			redpacketIsValidResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = redpacketIsValidResp;
			parmResponse.setUuid(redpacketIsValidReq.getUuid());
			
			RecordUtils.writeResponse(logger, redpacketIsValidReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		
	}
}
