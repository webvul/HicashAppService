package com.hengyuan.hicash.service.query.custnotic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.notic.NoticeTitleService;
import com.hengyuan.hicash.parameters.request.notic.NoticeInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.notic.NoticeTitleResp;
import com.hengyuan.hicash.service.validate.query.custnotic.NoticeTitleVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash客户资料查询
 * 公告标题查询
 * @author Cary.Liu
 * @create date 2014-08-15
 *
 */
@WebServlet("/NoticeTitle")
public class NoticeTitle extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(NoticeTitle.class);

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
		RecordUtils.writeRequest(logger, req, custNoticReq);
		NoticeTitleVal noticeTitleVal = new NoticeTitleVal(custNoticReq);
		String resultCode = noticeTitleVal.validate();
		if(!ResultCodes.NORMAL.equals(resultCode)){
			NoticeTitleResp noticeTitleResp = new NoticeTitleResp();
			noticeTitleResp.setUuid(custNoticReq.getUuid());
			noticeTitleResp.setResultCode(resultCode);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(resultCode);
			noticeTitleResp.setResultMsg(resuMsg);
			
			RecordUtils.writeResponse(logger, custNoticReq.getUuid(), noticeTitleResp);
			resp.getWriter().write(noticeTitleResp.toJson());
		}else{
			
			NoticeTitleService custNoticService = new NoticeTitleService();
			NoticeTitleResp noticeTitleResp  = (NoticeTitleResp)custNoticService.responseValue(custNoticReq);
			
			String resuMsg = ResourceUtils.getString(noticeTitleResp.getResultCode());
			noticeTitleResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = noticeTitleResp;
			parmResponse.setUuid(custNoticReq.getUuid());
			
			RecordUtils.writeResponse(logger, custNoticReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
	}
	
}
