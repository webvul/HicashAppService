package com.hengyuan.hicash.service.query;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.amount.AppScheduleService;
import com.hengyuan.hicash.parameters.request.amount.AppScheduleReq;
import com.hengyuan.hicash.parameters.request.user.RestServerPwdReq;
import com.hengyuan.hicash.parameters.response.amount.AppScheduleResp;
import com.hengyuan.hicash.parameters.response.user.RestServerPwdResp;
import com.hengyuan.hicash.service.validate.query.AppScheduleVal;
import com.hengyuan.hicash.service.validate.query.RestServerPwdVal;
import com.hengyuan.hicash.utils.HttpRemotePost;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
* @author blanke.qin
* 2017年4月6日 上午9:55:19
* 类说明   运营商忘记服务密码链接
 */
@WebServlet("/RestServerPwd")
public class RestServerPwd extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(RestServerPwd.class);

	private static final long serialVersionUID = 8142973742612616485L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RestServerPwdReq restServerPwdReq = new RestServerPwdReq();
		restServerPwdReq.setMobile(req.getParameter("mobile"));
		
		RecordUtils.writeRequest(logger, req, restServerPwdReq);
		RestServerPwdVal restServerPwdVal = new RestServerPwdVal(restServerPwdReq);
		String resultCode = restServerPwdVal.validate();
		RestServerPwdResp	restServerPwdResp = new RestServerPwdResp();
        String str;
        String  mobileHome="";
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			
			restServerPwdResp.setResultCode(resultCode);
			restServerPwdResp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
           HashMap<String, String> parmMap = new HashMap<String, String>();
			parmMap.put("mobile", restServerPwdReq.getMobile());
             try {	
				str= HttpRemotePost.sendNoTimeHttp(ResourceUtils.getValue(Consts.MOBILE_URL),parmMap);
				JSONObject object = JSONObject.parseObject(str);
				if(!object.isEmpty()){
					mobileHome=object.getString("mobileHome");	
					mobileHome= mobileHome.substring(2,4);
				}
			} catch (Exception e) {
				restServerPwdResp = new RestServerPwdResp();
				restServerPwdResp.setResultCode("5000");
				restServerPwdResp.setResultMsg("获取手机号归属地异常");
			} 
             
              if("联通".equals(mobileHome)){
	          restServerPwdResp.setUrl("https://uac.10010.com/cust/resetpwd/inputName");
              } 
              if("电信".equals(mobileHome)){
                restServerPwdResp.setUrl("http://wapzt.189.cn/wap/wap_updatapwd.html");
               } 
              if("移动".equals(mobileHome)){
                  restServerPwdResp.setUrl("http://www.sh.10086.cn/sh/wsyyt/busi/90.jsp?keyString=ME5OTEKzsAQKJBAOkBJhcg==,4fyNyobOi4o9FiKdWLopJw==,f5KolnrwwYajaGJ/ISEI2g");
              } 
             restServerPwdResp.setResultCode(resultCode);
  			restServerPwdResp.setResultMsg(ResourceUtils.getString(resultCode));
		}
		
		RecordUtils.writeResponse(logger,restServerPwdResp.getUuid(), restServerPwdResp);
		resp.getWriter().write(restServerPwdResp.toJson());
	}
	
	
	public static void main(String[] args) {
		String a="上海移动";
		System.out.println(a.substring(2, 4));
	}

}
