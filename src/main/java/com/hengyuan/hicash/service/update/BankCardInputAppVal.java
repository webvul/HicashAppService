package com.hengyuan.hicash.service.update;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.domain.query.user.ServiceConfigQuery;
import com.hengyuan.hicash.domain.service.mktApp.NoahValidIdentityService;
import com.hengyuan.hicash.entity.app.NoahValidIdentityEntity;
import com.hengyuan.hicash.entity.user.ServiceConfigEntity;
import com.hengyuan.hicash.parameters.response.user.BankCardInputAppResp;
import com.hengyuan.hicash.utils.HttpRemotePost;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 根据订单号修改绑定的代扣银行卡
 * 
 * @author Lihua.Ren
 * @createDate 2015-12-04
 *
 */
@WebServlet("/BankCardInputAppVal")
public class BankCardInputAppVal extends HttpServlet {
	private static final long serialVersionUID = -4030614367830870998L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		BankCardInputAppResp bankCardInputAppResp  = new BankCardInputAppResp();
		/*
		BankCardInputAppValReq updateApp = new BankCardInputAppValReq();
		updateApp.setMerId(req.getParameter("merId")); // 商户号
		updateApp.setTransDate(req.getParameter("transDate")); // 交易日期
		updateApp.setTransDate(req.getParameter("transTime")); // 交易时间
		updateApp.setSerialNo(req.getParameter("serialNo")); // 流水号
		updateApp.setAccNo(req.getParameter("accNo")); // 银行帐号
		updateApp.setAccName(req.getParameter("accName")); // 银行户名
		updateApp.setCertNo(req.getParameter("certNo")); // 证件号
		updateApp.setMobile(req.getParameter("mobile")); // 手机号
		updateApp.setBankNo(req.getParameter("bankNo")); // 银行编码
		updateApp.setUserName(req.getParameter("userName")); // 用户名
		 */
		
		Map<String, String> parmMap = new HashMap<String, String>();
		parmMap.put("accNo", req.getParameter("accNo"));					// 银行帐号
		parmMap.put("accName", req.getParameter("accName"));		// 银行户名
		parmMap.put("certNo", req.getParameter("certNo"));				// 证件号
		parmMap.put("mobile", req.getParameter("mobile"));				// 手机号
		parmMap.put("bankNo", req.getParameter("bankNo"));			// 银行编码
		parmMap.put("userName", req.getParameter("userName"));		// 用户名
		parmMap.put("appNo", req.getParameter("serialNo"));				// 流水号
		
		
		// 根据卡号，姓名，身份证号，手机号查询诺亚金通实名认证结果
		//String serialNo = req.getParameter("serialNo");	//流水号
		String bankCardNum = req.getParameter("accNo");
		String realName = req.getParameter("userName");
		String identityNo = req.getParameter("certNo");
		String bankNo = req.getParameter("bankNo");
		NoahValidIdentityService ns = new NoahValidIdentityService();
		
		// 诺亚金通实名认证结果
//		NoahValidIdentityEntity no = ns.getNoahValidIdentity(bankCardNum, realName,
//				identityNo, mobleNo);
		NoahValidIdentityEntity no = ns.getNoahValidIdentity(bankCardNum, realName,
				identityNo);
		
		/**未验证*/
		if(no.getRespCode() == null || !"01" .equals(no.getRespCode())){
			try {
				ServiceConfigQuery configQuery = new ServiceConfigQuery();
				ServiceConfigEntity serviceConfigEntity = configQuery.queryServiceByCode(Consts.SERVICE_NY001);
				String httpResp = HttpRemotePost.sendHttp(serviceConfigEntity.getServiceUrl(), parmMap);
				bankCardInputAppResp = new Gson().fromJson(httpResp,
						new TypeToken<BankCardInputAppResp>() {
						}.getType());
				
				if (bankCardInputAppResp.getRespCode().equals("01")) {
					updProxyFrom(req,resp);
				}else{
					if(StringUtils.isEmpty(bankCardInputAppResp.getTradeDesc())){
						String str = "{\"resultCode\":\"0\",\"resultMsg\":\""+bankCardInputAppResp.getRespMsg()+"\"}";
						resp.getWriter().write(str);
					}else{
						String str = "{\"resultCode\":\"0\",\"resultMsg\":\""+bankCardInputAppResp.getTradeDesc()+"\"}";
						resp.getWriter().write(str);
					}
				}
			} catch (Exception e) {
				String str = "{\"resultCode\":\"0\",\"resultMsg\":\"金通实名认证:验证异常！\"}";
				resp.getWriter().write(str);
				e.printStackTrace();
			}
			
		}else {
			updProxyFrom(req,resp);
		}
	}
	
	private void updProxyFrom(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		try {
			/* 调用  HimarsService/CollectCard 接口 更新客户银行卡信息  表cust_user_card*/
			HashMap<String, String> parmMap = new HashMap<String, String>();
			parmMap.put("userName", req.getParameter("userName"));//用户名
			parmMap.put("realName", req.getParameter("accName"));//持卡人姓名
			parmMap.put("cardType", "JJKT");//卡类型
			parmMap.put("bank", req.getParameter("bankNo"));//开户行
			parmMap.put("province", req.getParameter("dkProvince"));//开户城市-省
			parmMap.put("city", req.getParameter("dkCity"));//开户城市-市
			parmMap.put("areaCode", req.getParameter("dkAreaCode"));//开户城市-区
			parmMap.put("openBank", req.getParameter("dkBankBranch"));//开户支行
			parmMap.put("cardNum", req.getParameter("accNo"));//银行卡号
	//		parmMap.put("confrimCardNum", req.getParameter("confrimCardNum"));//确认收款账号
	//		parmMap.put("saveOrUpdateFlag", req.getParameter("saveOrUpdateFlag"));//新增or修改的标志位
			parmMap.put("defaultCard", "Y");//是否设置为默认卡
			parmMap.put("uuid",UUID.randomUUID().toString());
			String	httpResp = HttpRemotePost.sendHttp(ResourceUtils.getValue("HimarsService_CollectCard"), parmMap);
			BankCardInputAppResp bankCardInputAppResp = new Gson().fromJson(httpResp,new TypeToken<BankCardInputAppResp>() {}.getType());
			if (bankCardInputAppResp.getResultCode().equals("1")) {
				NoahValidIdentityService ns = new NoahValidIdentityService();
				ns.updateProxyFromByApplicationNo(req.getParameter("accNo"),req.getParameter("bankNo"),req.getParameter("serialNo"));
				/**已验证*/
				String str = "{\"resultCode\":\"1\",\"resultMsg\":\"金通实名认证:验证成功！\"}";
				resp.getWriter().write(str);
			}else{
				String str = "{\"resultCode\":\"0\",\"resultMsg\":\""+bankCardInputAppResp.getResultMsg()+"\"}";
				resp.getWriter().write(str);
			}
		} catch (Exception e) {
			String str = "{\"resultCode\":\"0\",\"resultMsg\":\"金通实名认证:验证异常！\"}";
			resp.getWriter().write(str);
			e.printStackTrace();
		}
	}
}
