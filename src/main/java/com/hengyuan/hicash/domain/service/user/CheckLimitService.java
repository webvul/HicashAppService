package com.hengyuan.hicash.domain.service.user;

import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.DdsjApplyCreditQuery;
import com.hengyuan.hicash.domain.query.user.DdsjLimitQuery;
import com.hengyuan.hicash.domain.query.user.LoanStatusQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.LoanLoanAccEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.entity.user.DdsjApplyCredit;
import com.hengyuan.hicash.entity.user.DdsjLimit;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CheckLimitReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CheckLimitResp;

/**
 * 查询滴滴司机贷能否提额
 * 
 * @author teng
 *
 */
public class CheckLimitService implements RespService {

	private String resultCode = "";
	private String is_Limit = "Y";
	private String is_return = "N";
	private String but_msg_1 = "";
	private String but_url_1 = "";

	private DdsjLimitQuery ddsjLimitQuery = new DdsjLimitQuery();
	private CustomerQuery customerQuery = new CustomerQuery();
	private DdsjApplyCreditQuery ddsjApplyCreditQuery = new DdsjApplyCreditQuery();
	private LoanStatusQuery loanStatusQuery = new LoanStatusQuery();

	public ParmResponse responseValue(ParmRequest parmRequest) {

		CheckLimitReq req = (CheckLimitReq) parmRequest;
		CheckLimitResp resp = new CheckLimitResp();

		try {
			String username = req.getUserName();

			DdsjLimit ddsjLimit = ddsjLimitQuery.queryLimit(username);
			if ((ddsjLimit != null && "Y".equals(ddsjLimit.getIs_credit()))
					|| ddsjLimit == null) {
				is_Limit = "N";
				String msg = "您尚未完成车主认证。";
				but_msg_1 = "立即认证";
				but_url_1 = "ljrz";
				is_return = "Y";
				resp.setResultMsg(msg);
				resp.setBut_url_1(but_url_1);
				resp.setBut_msg_1(but_msg_1);
				resp.setIs_limit(is_Limit);
				resp.setIs_return(is_return);
			} else {
				resp = getLimt(ddsjLimit, username, resp);
			}

			resultCode = ResultCodes.NORMAL;

		} catch (Exception e) {
			e.printStackTrace();
			resultCode = ResultCodes.CHECK_LIMIT_EXCEPTION;
		}
		resp.setResultCode(resultCode);
		resp.setIs_limit(is_Limit);
		resp.setIs_return(is_return);
		return resp;
	}

	public CheckLimitResp getLimt(DdsjLimit ddsjLimit, String username,
			CheckLimitResp resp) {

		List<DdsjLimit> limit = ddsjLimitQuery.querylatelyTeDate(username);
		DdsjApplyCredit ddsj=ddsjApplyCreditQuery.queryAppByByuserName(username);
		String msg = "SUCCESS";
		CustomerEntity customer = customerQuery
				.queryCustomerByUserName(username);

		// 不支持学生
		if (customer != null) {
			if ("KHL1".equals(customer.getCustType())) {
				msg = "滴滴司机贷暂不支持学生申请，请选择其他产品";
				is_Limit = "N";
				but_msg_1 = "去看看别的";
				but_url_1 = "qtcp";

				return this.getResp(msg);
			}
		}
		
		// 判断是否授信审核中，授信审核中不能授信
		if (ddsj != null && "SHNODE".equals(ddsj.getNode())&&"SX".equals(ddsj.getCredit_type())) {
			is_Limit = "N";
			msg = "您的车主身份正在认证中，请稍后再来。";
			but_msg_1 = "本车主知道了";
			but_url_1 = "zdl";
			return this.getResp(msg);
		}

		// 判断是否提额审核中，提额审核中不能授信
		if (ddsj != null && "SHNODE".equals(ddsj.getNode())&&"TE".equals(ddsj.getCredit_type())) {
			is_Limit = "N";
			msg = "您的提额申请正在审核，请勿重复操作。";
			but_msg_1 = "本车主知道了";
			but_url_1 = "zdl";
			return this.getResp(msg);
		}		

		
		if (ddsjLimit != null) {
			// 判断是否授信审核中，授信中不能提额
			// 判断是否提额中，提额中不能提额
			if ("SXZ".equals(ddsjLimit.getStatus())) {
				is_Limit = "N";
				msg = "您的车主身份正在认证中，请稍后再来。";
				but_msg_1 = "本车主知道了";
				but_url_1 = "zdl";

				return this.getResp(msg);
			} else if ("TEZ".equals(ddsjLimit.getStatus())) {
				is_Limit = "N";
				msg = "您的提额申请正在审核，请勿重复操作。";
				but_msg_1 = "本车主知道了";
				but_url_1 = "zdl";

				return this.getResp(msg);
			}
		}

		if (ddsjLimit == null
				|| (ddsjLimit != null && "0".equals(ddsjLimit
						.getSx_success_time()))) {
			is_Limit = "N";
			msg = "您尚未完成车主认证。";
			but_msg_1 = "立即认证";
			but_url_1 = "ljrz";
			return this.getResp(msg);
		}

		// 滴滴司机贷当前逾期，不能提额
		LoanLoanAccEntity acc = loanStatusQuery.queryStatus(username, "DDSJ");
		if (Integer.parseInt(acc.getLoanStatus()) > 0) {
			is_Limit = "N";
			msg = "您的车主身份已过期，请重新认证。";
			but_msg_1 = "立即认证";
			but_url_1 = "ljrz";

			return this.getResp(msg);
		}

		// 一个月已提额
		if (limit != null && limit.size() > 0) {
			is_Limit = "N";
			msg = "每位老司机每月限提额一次，下月请早。";
			but_msg_1 = "本车主知道了";
			but_url_1 = "zdl";

			return this.getResp(msg);
		}

		if ("Y".equals(is_Limit)) {
			msg = "信息越完善，额度越高。";
			but_msg_1 = "立即完善信息提额";
			but_url_1 = "ljte";

			return this.getResp(msg);
		}

		DdsjApplyCredit ddsjApplyCredit = ddsjApplyCreditQuery
				.queryRefuseApp(username);
		// 最近一笔授信被拒绝
		if (ddsjApplyCredit != null
				&& "STATUS21".equals(ddsjApplyCredit.getStatus())) {
			is_Limit = "N";
			msg = "您的司机贷审核未通过，不能提额。欢迎申请我们的其他产品";
			but_msg_1 = "去看看别的";
			but_url_1 = "qtcp";

			return this.getResp(msg);
		}

		return this.getResp(msg);
	}

	public CheckLimitResp getResp(String msg) {
		CheckLimitResp resp = new CheckLimitResp();
		resp.setIs_limit(is_Limit);
		resp.setBut_msg_1(but_msg_1);
		resp.setBut_url_1(but_url_1);
		resp.setResultMsg(msg);
		return resp;
	}

}
