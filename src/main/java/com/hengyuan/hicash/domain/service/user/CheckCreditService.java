package com.hengyuan.hicash.domain.service.user;

import java.math.BigDecimal;
import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.DdsjApplyCreditQuery;
import com.hengyuan.hicash.domain.query.user.DdsjLimitQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.entity.user.DdsjApplyCredit;
import com.hengyuan.hicash.entity.user.DdsjLimit;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CheckCreditReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CheckCreditResp;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 查询滴滴司机贷能否授信
 * 
 * @author teng
 *
 */
public class CheckCreditService implements RespService {

	private String resultCode = "";
	private String is_auth = "Y";
	private String is_return = "N";
	private String but_msg_1 = "";
	private String but_msg_2 = "";
	private String but_msg_3 = "";
	private String but_url_1 = "";
	private String but_url_2 = "";
	private String but_url_3 = "";
	private DdsjLimitQuery ddsjLimitQuery = new DdsjLimitQuery();
	private CustomerQuery customerQuery = new CustomerQuery();
	private DdsjApplyCreditQuery ddsjApplyCreditQuery = new DdsjApplyCreditQuery();

	public ParmResponse responseValue(ParmRequest parmRequest) {

		CheckCreditReq req = (CheckCreditReq) parmRequest;
		CheckCreditResp resp = new CheckCreditResp();

		try {
			String username = req.getUsername();

			DdsjLimit ddsjLimit = ddsjLimitQuery.queryLimit(username);
			if (ddsjLimit != null && "Y".equals(ddsjLimit.getIs_credit())) {
				is_return = "Y";
			}else{
				resp = getAuth(ddsjLimit, username, resp,req);
			}

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

	private CheckCreditResp getAuth(DdsjLimit ddsjLimit, String username,
			CheckCreditResp resp,CheckCreditReq req) throws Exception {

		List<DdsjApplyCredit> ddsjApplyCredit = ddsjApplyCreditQuery
				.queryAppByBeforeMonth(username);
		DdsjApplyCredit ddsj=ddsjApplyCreditQuery.queryAppByByuserName(username);
		

		String msg = "SCCESS";
		CustomerEntity customer = customerQuery
				.queryCustomerByUserName(username);

		// 不支持学生
		if (customer != null && "KHL1".equals(customer.getCustType())) {
			msg = "网约车司机贷暂不支持学生申请，请选择其他产品";
			is_auth = "N";
			but_msg_2 = "看看别的去";
			but_url_2 = "index";

			return this.getResp(msg);
		}

		// 授信被拒一个月内，不能授信
		if (ddsjApplyCredit != null && ddsjApplyCredit.size() > 0) {
			msg = "您本月认证失败，请提高征信或完善资料下月再试。您还可以选择其他产品。";
			is_auth = "N";
			but_msg_2 = "看看别的去";
			but_url_2 = "index";
			return this.getResp(msg);
		}

		// 判断是否授信审核中，授信审核中不能授信
		if (ddsj != null && "SHNODE".equals(ddsj.getNode())&&"SX".equals(ddsj.getCredit_type())) {
			msg = "您的认证资料正在审核中，请耐心等待。";
			is_auth = "N";
			is_return = "N";
			but_msg_2 = "本车主知道了";
			but_url_2 = "zdl";
			return this.getResp(msg);
		}

		// 判断是否提额审核中，提额审核中不能授信
		if (ddsj != null && "SHNODE".equals(ddsj.getNode())&&"TE".equals(ddsj.getCredit_type())) {
			msg = "车主认证成功。";
			is_auth = "N";
			is_return = "N";
			but_msg_2 = "立即提现";
			but_url_2 = "ljtx";
			return this.getResp(msg);
		}		
		if (ddsjLimit != null) {
			// 判断是否授信审核中，授信审核中不能授信
			// 判断是否提额中，提额中不能授信
			if ("SXZ".equals(ddsjLimit.getStatus())) {
				msg = "您的认证资料正在审核中，请耐心等待。";
				is_auth = "N";
				is_return = "N";
				but_msg_2 = "本车主知道了";
				but_url_2 = "zdl";

				return this.getResp(msg);
			} else if ("TEZ".equals(ddsjLimit.getStatus())) {
				msg = "车主认证成功。";
				is_auth = "N";
				is_return = "N";
				but_msg_2 = "立即提现";
				but_url_2 = "ljtx";

				return this.getResp(msg);
				// 是否必须授信为N
			} else if ("N".equals(ddsjLimit.getIs_credit())) {
				if (ddsjLimit.getAmount() != null
						&& new BigDecimal(ddsjLimit.getAmount())
								.compareTo(BigDecimal.ZERO) > 0) {

					msg = "车主认证成功。";
					is_auth = "N";
					but_msg_2 = "立即提现";
					but_url_2 = "ljtx";

					return this.getResp(msg);
				}
			}
		}

		// 首次授信
		if (ddsjLimit == null
				|| (ddsjLimit != null && "0".equals(ddsjLimit
						.getSx_success_time()))) {
			msg = "注册时请使用您平时常用的联络手机，非您的工作手机。无常用联系人通话记录的手机可能无法通过。";
			if (!RegexValidate.isNull(req.getVersion())) {

				if (StringUtils.compareVersion(req.getVersion(),
						"3.0.0.0") >= 0) {
					
					but_msg_1 = "我不是车主";
					but_msg_2 = "车主认证";
					but_url_1 = "index";
					but_url_2 = "czrz";
					
					}
				}else{

					but_msg_1 = "老司机借款法宝";
					but_msg_2 = "下一步";
					but_url_1 = "sjdgl";
					but_url_2 = "czrz";
					
					
				}
			
			return this.getResp(msg);
		}

		return this.getResp(msg);
	}

	public CheckCreditResp getResp(String msg) {
		CheckCreditResp resp = new CheckCreditResp();
		resp.setIs_auth(is_auth);
		resp.setResultMsg(msg);
		resp.setBut_msg_1(but_msg_1);
		resp.setBut_msg_2(but_msg_2);
		resp.setBut_url_1(but_url_1);
		resp.setBut_url_2(but_url_2);
		resp.setColor_str("");
		return resp;
	}
}
