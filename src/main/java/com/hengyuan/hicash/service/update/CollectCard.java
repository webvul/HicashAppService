package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CollectCardService;
import com.hengyuan.hicash.parameters.request.user.CollectCardReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollectCardResp;
import com.hengyuan.hicash.service.validate.update.CollectCardVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 记录收款账户信息
 * 
 * @author Cary.Liu
 * @create date 2014-07-25
 */
@WebServlet("/CollectCard")
public class CollectCard extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(CollectCard.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CollectCardReq cardReq = new CollectCardReq();
		cardReq.setUserName(req.getParameter("userName"));//用户名
		cardReq.setRealName(req.getParameter("realName"));//真实姓名
		cardReq.setCardType(req.getParameter("cardType"));//卡类型
		cardReq.setBank(req.getParameter("bank"));//开户支行
		cardReq.setProvince(req.getParameter("province"));//开户城市-省
		cardReq.setCity(req.getParameter("city"));//开户城市-市
		cardReq.setOpenBank(req.getParameter("openBank"));//开户支行
		cardReq.setCardNum(req.getParameter("cardNum"));//收款账号
//		cardReq.setConfrimCardNum(req.getParameter("confrimCardNum"));//确认收款账号
		cardReq.setSaveOrUpdateFlag(req.getParameter("saveOrUpdateFlag"));//新增or修改的标志位
		cardReq.setDefaultCard(req.getParameter("defaultCard"));//是否设置为默认卡
		cardReq.setAreaCode(req.getParameter("areaCode"));//银行卡网点所在区域代码
		cardReq.setUuid(req.getParameter("uuid"));
		RecordUtils.writeRequest(logger, req, cardReq);
		/* 实例化参数验证 */
		CollectCardVal val = new CollectCardVal(cardReq);
		String result = val.validate();
		if (!ResultCodes.NORMAL.equals(result)) {
			
			CollectCardResp respon = new CollectCardResp();
			respon.setResultCode(result);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(result);
			respon.setResultMsg(resuMsg);
			
			respon.setUuid(cardReq.getUuid());
			
			RecordUtils.writeResponse(logger, cardReq.getUuid(), respon);
			
			resp.getWriter().write(respon.toJson());
		
		}else{
			CollectCardService cardService = new CollectCardService();
			
			CollectCardResp respon =  (CollectCardResp) cardService.responseValue(cardReq);
			
			String resuMsg = ResourceUtils.getString(respon.getResultCode());
			respon.setResultMsg(resuMsg);
			
			ParmResponse parmResponse =respon;
					
			parmResponse.setUuid(cardReq.getUuid());
			
			RecordUtils.writeResponse(logger, cardReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
	}
	public static void main(String[] args) {
		long current = System.currentTimeMillis();
		System.out.println(current);
	}
}
