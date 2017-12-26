package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.QuerySingleImgService;
import com.hengyuan.hicash.parameters.request.user.QuerySingleImgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.QuerySingleImgResp;
import com.hengyuan.hicash.service.validate.query.QuerySingleImgVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 图片查询
 * 嗨秒贷图片查询
 * @author LiHua.Ren
 * @createDate 2015-06-01
 *
 */
@WebServlet("/QuerySingleImg")
public class QuerySingleImg extends HttpServlet{
	private static final long serialVersionUID = -4030614367830870998L;
	private static Logger logger = Logger.getLogger(QuerySingleImg.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		QuerySingleImgReq queryMsgReq = new QuerySingleImgReq();
		queryMsgReq.setUserName(req.getParameter("userName"));//用户名
		queryMsgReq.setUuid(req.getParameter("uuid"));
//		queryMsgReq.setTempAppNo(req.getParameter("tempAppNo"));
		
		RecordUtils.writeRequest(logger, req, queryMsgReq);
		QuerySingleImgVal val = new QuerySingleImgVal(queryMsgReq);
		String restult = val.validate();
		if (!ResultCodes.NORMAL.equals(restult)) {
			QuerySingleImgResp queyResp = new QuerySingleImgResp();
			queyResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			queyResp.setResultMsg(resuMsg);
			queyResp.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), queyResp);
			resp.getWriter().write(queyResp.toJson());
		}else {
			QuerySingleImgService  stuMsgService = new QuerySingleImgService();
			QuerySingleImgResp valresp = (QuerySingleImgResp) stuMsgService.responseValue(queryMsgReq);

			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		
		
	}

}
