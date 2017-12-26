package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.UpdateLanUserInfoService;
import com.hengyuan.hicash.parameters.request.user.UpdateLanUserInfoReq;
import com.hengyuan.hicash.parameters.response.user.UpdateLanUserInfoResp;
import com.hengyuan.hicash.service.validate.update.UpdateLanUserInfoVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 更新蓝领用户资料
 * @author Cary.Liu
 * @createDate 2016-03-02
 *
 */
@WebServlet("/UpdateLanUserInfo")
public class UpdateLanUserInfo extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(UpdateLanUserInfo.class);

	private static final long serialVersionUID = -8118031793198039125L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		UpdateLanUserInfoReq userInfoReq = new UpdateLanUserInfoReq();
		userInfoReq.setUserName(req.getParameter("userName")); 
		userInfoReq.setInviteCode(req.getParameter("inviteCode")); 
		userInfoReq.setStoreCode(req.getParameter("storeCode")); 
		userInfoReq.setUserScenepicUrl(req.getParameter("userScenepicUrl"));
		userInfoReq.setUserScenepicThumUrl(req.getParameter("userScenepicThumUrl"));
		/* 2016-03-07 需求新增 */
		userInfoReq.setIsHaveStore(req.getParameter("isHaveStore"));
		userInfoReq.setUnitName(req.getParameter("unitName"));
		userInfoReq.setStoreName(req.getParameter("storeName"));
		userInfoReq.setProvince(req.getParameter("province"));
		userInfoReq.setCity(req.getParameter("city"));
		userInfoReq.setAddress(req.getParameter("address"));
		userInfoReq.setRoadNo(req.getParameter("roadNo"));
		userInfoReq.setOperatePower(req.getParameter("operatePower"));
		userInfoReq.setOperateTime(req.getParameter("operateTime"));
		userInfoReq.setLegalName(req.getParameter("legalName"));
		userInfoReq.setUnitPhone(req.getParameter("unitPhone"));
		userInfoReq.setUnitPicUrl(req.getParameter("unitPicUrl"));
		userInfoReq.setLocaPicUrl(req.getParameter("locaPicUrl"));
		userInfoReq.setStorePicUrl(req.getParameter("storePicUrl"));
		userInfoReq.setStorePicUrl1(req.getParameter("storePicUrl1"));
		userInfoReq.setStorePicUrl2(req.getParameter("storePicUrl2"));
		userInfoReq.setStorePicUrl3(req.getParameter("storePicUrl3"));
		
		RecordUtils.writeRequest(logger, req, userInfoReq);
		
		
		UpdateLanUserInfoResp userInfoResp = null;
		UpdateLanUserInfoVal userInfoVal = new UpdateLanUserInfoVal(userInfoReq);
		String resultCode = userInfoVal.validate();
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			userInfoResp = new UpdateLanUserInfoResp();
			userInfoResp.setResultCode(resultCode);
			
		}else{
			
			UpdateLanUserInfoService userInfoService = new UpdateLanUserInfoService();
			userInfoResp = (UpdateLanUserInfoResp)userInfoService.responseValue(userInfoReq);
			
		}
		
		userInfoResp.setResultMsg(ResourceUtils.getString(userInfoResp.getResultCode()));
		RecordUtils.writeResponse(logger, userInfoReq.getUuid(), userInfoResp);
		resp.getWriter().write(userInfoResp.toJson());
	}

}
