package com.hengyuan.hicash.service.query.custnotic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.notic.NoticeInfoService;
import com.hengyuan.hicash.parameters.request.notic.NoticeInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.notic.NoticeInfoResp;
import com.hengyuan.hicash.service.validate.query.custnotic.NoticeInfoVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash客户资料查询
 * 公告详情查询
 * @author Eric.shi
 * @create date 2014-07-25
 *
 */
@WebServlet("/NoticeInfo")
public class NoticeInfo extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(NoticeInfo.class);
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		NoticeInfoReq custNoticReq = new NoticeInfoReq();
		custNoticReq.setUuid(req.getParameter("uuid"));
		custNoticReq.setId(req.getParameter("noticeId"));
		RecordUtils.writeRequest(logger, req, custNoticReq);
		NoticeInfoVal infoVal = new NoticeInfoVal(custNoticReq);
		String resultCode = infoVal.validate();
		if(!ResultCodes.NORMAL.equals(resultCode)){
			NoticeInfoResp infoResp = new NoticeInfoResp();
			infoResp.setUuid(custNoticReq.getUuid());
			infoResp.setResultCode(resultCode);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(resultCode);
			infoResp.setResultMsg(resuMsg);
			
			RecordUtils.writeResponse(logger, custNoticReq.getUuid(), infoResp);
			resp.getWriter().write(infoResp.toJson());
		}else{
			
			NoticeInfoService custNoticService = new NoticeInfoService();

			NoticeInfoResp noticeInfoResp  = (NoticeInfoResp)custNoticService.responseValue(custNoticReq);
			String resuMsg = ResourceUtils.getString(resultCode);
			noticeInfoResp.setResultMsg(resuMsg);
			ParmResponse parmResponse = noticeInfoResp;
			parmResponse.setUuid(custNoticReq.getUuid());
			
			RecordUtils.writeResponse(logger, custNoticReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
	}
	
}
