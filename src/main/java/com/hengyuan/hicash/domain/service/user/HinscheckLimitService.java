package com.hengyuan.hicash.domain.service.user;

import java.math.BigDecimal;
import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.HinsApplyCreditQuery;
import com.hengyuan.hicash.domain.query.user.HinsLimitQuery;
import com.hengyuan.hicash.domain.query.user.LoanStatusQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.LoanLoanAccEntity;
import com.hengyuan.hicash.entity.user.HinsApplyCredit;
import com.hengyuan.hicash.entity.user.HinsLimit;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.HcheckLimitReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.HinsCheckLimitResp;

/**
 * 
* @author dong.liu 
* 2017-5-11 下午5:48:33
* 类说明 :判断能否提额
 */
public class HinscheckLimitService implements RespService {

	private String resultCode = "";
	private String is_Limit = "Y";
	private String is_return = "N";
	private String but_msg_1 = "";
	private String but_url_1 = "";

	private HinsLimitQuery hinsLimitQuery = new HinsLimitQuery();
	private HinsApplyCreditQuery hinsApplyCreditQuery = new HinsApplyCreditQuery();
	private LoanStatusQuery loanStatusQuery = new LoanStatusQuery();

	public ParmResponse responseValue(ParmRequest parmRequest) {

		HcheckLimitReq req = (HcheckLimitReq) parmRequest;
		HinsCheckLimitResp resp = new HinsCheckLimitResp();

		try {
			String username = req.getUserName();

			HinsLimit HinsLimit = hinsLimitQuery.queryLimit(username);
				resp = getLimt(HinsLimit, username, resp);

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

	public HinsCheckLimitResp getLimt(HinsLimit hinsLimit, String username,
			HinsCheckLimitResp resp) {

		List<HinsLimit> limit = hinsLimitQuery.querylatelyTeDate(username);
		HinsApplyCredit hins=hinsApplyCreditQuery.queryAppByuserName(username);//查询最新一笔提额单
		String msg = "SUCCESS";
		

		// 判断是否提额审核中，提额审核中不能授信
		if (hins != null && "SHNODE".equals(hins.getNode())&&"TE".equals(hins.getCredit_type())) {
			is_Limit = "N";
			msg = "提额申请正在审核，请稍后";
			but_msg_1 = "本尊已阅";
			but_url_1 = "";
			return this.getResp(msg);
		}		

		
		if (hinsLimit != null) {
			// 判断是否授信审核中，授信中不能提额
			// 判断是否提额中，提额中不能提额
			 if ("TEZ".equals(hinsLimit.getStatus())) {
				 is_Limit = "N";
					msg = "提额申请正在审核，请稍后";
					but_msg_1 = "本尊已阅";
					but_url_1 = "";
				return this.getResp(msg);
			}
		}

		// 一个月已提额
		if (limit != null && limit.size() > 0) {
			is_Limit = "N";
			msg = "女神也不能太贪心哦~每月限提额一次，下月请早";
			but_msg_1 = "本尊已阅";
			but_url_1 = "";

			return this.getResp(msg);
		}
		HinsLimit hivo=hinsLimitQuery.queryLimit(username);
		if(hivo!=null){
			if(new BigDecimal(hivo.getAmount()).compareTo(new BigDecimal(20000))==0){
				is_Limit = "N";
				msg = "信用女神就是你，额度已达巅峰提无可提";
				but_msg_1 = "立即买买买";
				but_url_1 = "ggq";
				
			}
		}

		// 嗨女神当前逾期，不能提额
		LoanLoanAccEntity acc = loanStatusQuery.queryStatus(username, "HINS");
		if (Integer.parseInt(acc.getLoanStatus()) > 0) {
			is_Limit = "N";
			msg = "美人健忘，您的分期购订单有逾期，请尽快还款，方可提额";
			but_msg_1 = "立即还款";
			but_url_1 = "czhk";

			return this.getResp(msg);
		}
		//查询最新一笔授信单
		HinsApplyCredit hinsApplyCredit=hinsApplyCreditQuery.queryRefuseApp(username);
		// 最近一笔授信被拒绝
		if (hinsApplyCredit != null
				&& "STATUS21".equals(hinsApplyCredit.getStatus())) {
			is_Limit = "N";
			msg = "抱歉，您当月认证失败，请下月再来";
			but_msg_1 = "先贷点钱回家";
			but_url_1 = "index";

			return this.getResp(msg);
		}

		return this.getResp(msg);
	}

	public HinsCheckLimitResp getResp(String msg) {
		HinsCheckLimitResp resp = new HinsCheckLimitResp();
		resp.setIs_limit(is_Limit);
		resp.setBut_msg_1(but_msg_1);
		resp.setBut_url_1(but_url_1);
		resp.setResultMsg(msg);
		return resp;
	}

}
