package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CollarApp2MsgService;
import com.hengyuan.hicash.parameters.request.user.CollarApp2MsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollarApp2MsgResp;
import com.hengyuan.hicash.service.validate.query.CollarApp2MsgVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash白领资料查询
 * 
 * @author LiHua.Ren
 * @create date 2015-05-23
 *
 */
@WebServlet("/CollarApp2Msg")
public class CollarApp2Msg extends HttpServlet {

	private static final long serialVersionUID = -8485963175175699076L;
	private static Logger logger = Logger.getLogger(CollarApp2Msg.class);	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CollarApp2MsgReq queryMsgReq = new CollarApp2MsgReq();
		
		queryMsgReq.setUserName(req.getParameter("userName"));//用户名
		
		queryMsgReq.setUuid(req.getParameter("uuid"));//uuid
		RecordUtils.writeRequest(logger, req, queryMsgReq);
		CollarApp2MsgVal val = new CollarApp2MsgVal(queryMsgReq);
		String result = val.validate();
		if (!ResultCodes.NORMAL.equals(result)) {
			CollarApp2MsgResp valresp = new CollarApp2MsgResp();
			valresp.setResultCode(result);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(result);
			valresp.setResultMsg(resuMsg);
			valresp.setUuid(queryMsgReq.getUuid());
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), valresp);
			resp.getWriter().write(valresp.toJson());
		}else {
		
			CollarApp2MsgService  collarQueryService = new CollarApp2MsgService();
			
			CollarApp2MsgResp valresp = (CollarApp2MsgResp) collarQueryService.responseValue(queryMsgReq);
			
			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		}
}
