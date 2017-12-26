package com.hengyuan.hicash.service.query;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.hengyuan.hicash.domain.service.param.BankBinService;
import com.hengyuan.hicash.parameters.request.param.BankBinReq;
import com.hengyuan.hicash.parameters.response.param.BankBinResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
 * @author fish
 *
 * @date 2017年2月14日 上午10:23:01
 */
@WebServlet("/BankBin")
public class BankBin extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(BankBin.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BankBinService service = new BankBinService();
		BankBinReq req = new BankBinReq();

		// 获取用户名
		req.setBankCard(request.getParameter("bankCard"));

		RecordUtils.writeRequest(logger, request, req);
		BankBinResp resp = (BankBinResp) service.responseValue(req);
		resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		RecordUtils.writeResponse(logger, null, resp);

		response.getWriter().write(resp.toJson());

	}
	
}
