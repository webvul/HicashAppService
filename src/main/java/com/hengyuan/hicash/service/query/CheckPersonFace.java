package com.hengyuan.hicash.service.query;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CheckPersonFaceService;
import com.hengyuan.hicash.parameters.request.user.CheckPersonFaceReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CheckPersonFaceResp;
import com.hengyuan.hicash.service.validate.query.CheckPersonFaceVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;


/**
 *判断是否符合做人脸识别的条件
 *判断是否走公安
 * 根据数据库设置来确定认证流程（情况1：设置走易道，易道只有两次机会 。情况2：设置走face++：face++有三次机会 。情况3：设置走易道和face++，易道有两次机会，face++有2次机会）
 * 范围：（1）易道：两次的概念是：同人当天，两个临时单号算2次；（2）face++：同人，当天2次（包含一个临时单号做2次）
 * (1)2017-05-26修改为：易道，两次概念更改为，一个临时单当天只能两次；
 * (2)face是同一个人两次
 * 
 * @author 0493
 * @create date 2017-05-11
 *
 */
@WebServlet("/CheckPersonFace")
public class CheckPersonFace  extends HttpServlet {

	private static final long serialVersionUID = -7344907605527164991L;
	private static Logger logger = Logger.getLogger(CheckPersonFace.class);
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CheckPersonFaceReq checkFaceReq = new CheckPersonFaceReq();
		
		checkFaceReq.setTempNo(req.getParameter("tempNo"));//为了统计失败次数
		checkFaceReq.setIdNo(req.getParameter("idNo"));
		checkFaceReq.setUuid(UUID.randomUUID().toString());
		
		RecordUtils.writeRequest(logger, req, checkFaceReq);
		CheckPersonFaceVal val = new CheckPersonFaceVal(checkFaceReq);
		String result = val.validate();
		if (!ResultCodes.NORMAL.equals(result)) {
			CheckPersonFaceResp personResp = new CheckPersonFaceResp();
			personResp.setResultCode(result);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(result);
			personResp.setResultMsg(resuMsg);
			personResp.setUuid(checkFaceReq.getUuid());
			RecordUtils.writeResponse(logger, personResp.getUuid(), personResp);
			resp.getWriter().write(personResp.toJson());
		}else {
		
			CheckPersonFaceService checkPersonFaceService = new CheckPersonFaceService();
			
			CheckPersonFaceResp valresp = (CheckPersonFaceResp) checkPersonFaceService.responseValue(checkFaceReq);
			
//			//2017-08-25,，8月嗨钱迭代，只用face做人脸，colin总说，为了以后还可以用易道，这个先写死face；2017-10-24修改走易道face
			valresp.setWhichPart("FACE");
			
			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(checkFaceReq.getUuid());
			
			RecordUtils.writeResponse(logger, checkFaceReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		}


}
