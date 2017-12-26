package com.hengyuan.hicash.domain.service.user;

import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.HinsApplyCreditQuery;
import com.hengyuan.hicash.domain.query.user.HinsLimitQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.entity.user.HinsApplyCredit;
import com.hengyuan.hicash.entity.user.HinsLimit;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CheckCreditReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.HinscheckCreditResp;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
* @author dong.liu 
* 2017-5-10 下午8:58:54
* 类说明:嗨女神判断能否授信
 */
public class HinsCheckCreditService implements RespService {

	private String resultCode = "";
	private String is_auth = "Y";
	private String is_return = "N";
	private String but_msg_1 = "";
//	private String but_msg_2 = "";
	private String but_url_1 = "";
//	private String but_url_2 = "";
	private HinsLimitQuery limitQuery = new HinsLimitQuery();
	private CustomerQuery customerQuery = new CustomerQuery();
	private HinsApplyCreditQuery hinsApplyCreditQuery = new HinsApplyCreditQuery();

	public ParmResponse responseValue(ParmRequest parmRequest) {

		CheckCreditReq req = (CheckCreditReq) parmRequest;
		HinscheckCreditResp resp = new HinscheckCreditResp();

		try {
			String username = req.getUsername();

			HinsLimit hinsLimit = limitQuery.queryLimit(username);
//			if (hinsLimit != null && "Y".equals(hinsLimit.getIs_credit())) {
//				is_return = "Y";
	//		}else{
				resp = getAuth(hinsLimit, username, resp);
	//		}

			resultCode = ResultCodes.NORMAL;

		} catch (Exception e) {
			e.printStackTrace();
			resultCode = ResultCodes.CHECK_CREDIT_EXCEPTION;
		}
		resp.setResultCode(resultCode);
		resp.setIs_auth(is_auth);
		resp.setIs_return(is_return);
		return resp;
	}

	private HinscheckCreditResp getAuth(HinsLimit hinsLimit, String username,
			HinscheckCreditResp resp) {

		List<HinsApplyCredit> hinsApplyCredit = hinsApplyCreditQuery
				.queryAppByBeforeMonth(username);
		HinsApplyCredit hins=hinsApplyCreditQuery.queryRefuseApp(username);
		String msg = "SCCESS";
		CustomerEntity customer = customerQuery
				.queryCustomerByUserName(username);
		
		//已知男性身份
		if (customer != null ) {
			String sex=StringUtils.getGenderByIdCard(customer.getIdentityNo());
			if("男".equals(sex)){
				msg = "本商城仅针对各位女神，男神请绕道现金产品";
				is_auth = "N";
				but_msg_1 = "立即去拿钱";
				but_url_1 = "index";
	
				return this.getResp(msg);
			}
			
		}
		// 授信被拒一个月内，不能授信
		if (hinsApplyCredit != null && hinsApplyCredit.size() > 0) {
			msg = "抱歉，您当月认证失败，请下月再来";
			is_auth = "N";
			but_msg_1 = "先贷点钱回家";
			but_url_1 = "index";
			return this.getResp(msg);
		}

		
		// 判断是否授信审核中，授信审核中不能授信
		if (hins != null && "SHNODE".equals(hins.getNode())&&"SX".equals(hins.getCredit_type())) {
			msg = "女神的养成都需要时间来沉淀，请耐心等待审核通过，留意通过短信";
			is_auth = "N";
			is_return = "N";
			but_msg_1 = "本尊已阅";
			but_url_1 = "";
			return this.getResp(msg);
		}

		if (hinsLimit != null) {
			// 判断是否授信审核中，授信审核中不能授信
			// 判断是否提额中，提额中不能授信
			if ("SXZ".equals(hinsLimit.getStatus())) {
				msg = "女神的养成都需要时间来沉淀，请耐心等待审核通过，留意通过短信";
				is_auth = "N";
				is_return = "N";
				but_msg_1 = "本尊已阅";
				but_url_1 = "";

				return this.getResp(msg);
			}	
		}
		
		/**
		 * 强制授信
		 */
		if (hinsLimit != null && "Y".equals(hinsLimit.getIs_credit())) {
			is_auth = "Y";
			is_return = "Y";
			return this.getResp(msg);
		}
		
		
		// 不支持学生
		if (customer != null && "KHL1".equals(customer.getCustType())) {
			msg = "抱歉嗨女神商城暂时只支持白领身份申请，你可以试试其他产品";
			is_auth = "N";
			but_msg_1 = "先贷点钱回家";
			but_url_1 = "index";

			return this.getResp(msg);
		}

//		// 首次授信
//		if (hinsLimit == null
//				|| (hinsLimit != null && "0".equals(hinsLimit
//						.getSx_success_time()))) {
//			msg = "注册时请使用您平时常用的联络手机，非您的工作手机。无常用联系人通话记录的手机可能无法通过。现仅限滴滴快车及专车司机申请。其他类型的司机贷正在紧密开发中，敬请期待。";
//			but_msg_1 = "老司机借款法宝";
//			but_msg_2 = "已知晓，下一步";
//			but_url_1 = "sjdgl";
//			but_url_2 = "czrz";
//			return this.getResp(msg);
//		}

		return this.getResp(msg);
	}

	public HinscheckCreditResp getResp(String msg) {
		HinscheckCreditResp resp = new HinscheckCreditResp();
		resp.setIs_auth(is_auth);
		resp.setResultMsg(msg);
		resp.setIs_return(is_return);
		resp.setBut_msg_1(but_msg_1);
		resp.setBut_url_1(but_url_1);
		return resp;
	}
}
