package com.hengyuan.hicash.service.update;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.DoOrNotFundService;
import com.hengyuan.hicash.parameters.request.user.DoOrNotFundReq;
import com.hengyuan.hicash.parameters.response.user.DoOrNotFundResp;
import com.hengyuan.hicash.service.query.YiDaoMark;
import com.hengyuan.hicash.service.validate.update.DoOrNotFundVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;
/**
 * 客户是否做公积金（前端告诉）
 * @author 0493
 * @create date 2017-05-13
 *
 */
@WebServlet("/DoOrNotFund")
public class DoOrNotFund  extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(YiDaoMark.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DoOrNotFundService service = new DoOrNotFundService();
		DoOrNotFundResp resp = new DoOrNotFundResp();
		DoOrNotFundReq req = new DoOrNotFundReq();
		req.setIdentityNo(request.getParameter("identityNo"));
		req.setRealName(request.getParameter("realName"));
		req.setIsOrNot(request.getParameter("isOrNot"));//是否做公积金，Y做，N不做
//		req.setRealName(new String(request.getParameter("realName").getBytes(
//				"ISO-8859-1"), "UTF-8"));//测试环境
		req.setUuid(UUID.randomUUID().toString());
		RecordUtils.writeRequest(logger, request, req);

		DoOrNotFundVal val = new DoOrNotFundVal(req);
		String result = val.validate();

		if (!ResultCodes.NORMAL.equals(result)) {

			resp.setResultCode(result);
			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(result);
			resp.setResultMsg(resuMsg);
			resp.setUuid(req.getUuid());
			RecordUtils.writeResponse(logger, resp.getUuid(), resp);
			response.getWriter().write(resp.toJson());
		} else {

			resp = (DoOrNotFundResp) service.responseValue(req);
			resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
			RecordUtils.writeResponse(logger, null, resp);
			response.getWriter().write(resp.toJson());
		}
	}


}
