package com.hengyuan.hicash.domain.service.calculate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.app.ApplicationPayQuery;
import com.hengyuan.hicash.domain.query.product.RepayProgramQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.DdsjApplyCreditQuery;
import com.hengyuan.hicash.domain.query.user.DdsjLimitQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.RepayProgramEntity;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.entity.user.DdsjApplyCredit;
import com.hengyuan.hicash.entity.user.DdsjLimit;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.param.LoanAmtCalNewReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.calculate.RepayProgramNewResp;
import com.hengyuan.hicash.parameters.response.calculate.RepayProgramTHFQResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
 * @author teng
 *
 */
public class RepayProgramNewService implements RespService {

	private static Logger logger = Logger
			.getLogger(RepayProgramNewService.class);

	private CustomerQuery customerQuery = new CustomerQuery();

	private RepayProgramQuery repayProgramQuery = new RepayProgramQuery();

	private ApplicationPayQuery payQuery = new ApplicationPayQuery();

	private DdsjLimitQuery ddsjLimitQuery = new DdsjLimitQuery();

	private DdsjApplyCreditQuery   ddsjApplyCreditQuery=new DdsjApplyCreditQuery();
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		LoanAmtCalNewReq loanCal = (LoanAmtCalNewReq) parmRequest;
		ParmResponse parmResponse = null;
		if ("DDSJ".equals(loanCal.getIndustryCode())) {
			parmResponse = getDDSJRepayProgram(loanCal);
		} else if ("THFQ".equals(loanCal.getIndustryCode())) {
			parmResponse = getTHFQRepayProgram(loanCal);
		} else {
			logger.info("产品暂不支持......................");
		}
		return parmResponse;
	}

	private ParmResponse getTHFQRepayProgram(LoanAmtCalNewReq loanCal) {

		List<RepayProgramEntity> repayList = null;

		// 默认返点比率(如果该产品开通返点比例，就获取产品的返点比例 反之获取商户的返点比例)
		BigDecimal rebate = BigDecimal.ZERO;

		// 投资人
		String invest_name = Consts.HENGYUAN_USERNAME;

		// 默认未首次身份,当还款出现逾期为当前真是身份
		String cust_type = loanCal.getCustType();
		
		cust_type = "KHL1";

		// 申请金额  直接由300块调接口传入 2017-06-28
//		String amount = "300";
		String amount = loanCal.getLoanAmount();

		RepayProgramTHFQResp repayProgramResp = new RepayProgramTHFQResp();
		try {

			CustomerEntity customer = customerQuery.queryCustByUserName(loanCal
					.getUserName());

			String count = loanCal.getApplyCount();
			int c = Integer.parseInt(count);
			//金额不再这么计算，300调接口直接通过接口传过来  2017-06-28
			if(RegexValidate.isNull(amount)){
				if (c >= 2) {
					amount = "500";
				}else{
					amount = "300";
				}
			}
			
			if (customer == null) {
				throw new Exception("该用户未找到,用户名:" + loanCal.getUserName());
			}

			logger.info("amount1:"+amount);
			repayList = repayProgramQuery.queryHyRepay(cust_type,
					loanCal.getIndustryCode(), rebate, invest_name, amount);

			repayList = getNewList(repayList, c, amount, cust_type);

			repayProgramResp.setPeriods("期");
			repayProgramResp.setAmount(amount);
			repayProgramResp.setResultCode(ResultCodes.NORMAL);
			repayProgramResp.setList(repayList);
			repayProgramResp.setRebatePercent(String.valueOf(rebate));

		} catch (Exception e) {
			RecordUtils.writeError(logger, loanCal.getUuid(),
					ResultCodes.LOAN_PRO_FAIL, e);
			repayList = repayProgramQuery.queryHyRepay(cust_type,
					loanCal.getIndustryCode(), rebate, invest_name, amount);
			// 默认返回高费率
			repayList = getNewList(repayList, 1, amount, cust_type);

			// 默认返回响应实体
			repayProgramResp = (RepayProgramTHFQResp) deaf_resp(
					repayProgramResp, repayList, loanCal);
		} finally {
			ConnManager.closeConn();
		}
		return repayProgramResp;
	}

	private List<RepayProgramEntity> getNewList(
			List<RepayProgramEntity> repayList, int c, String amount,
			String cust_type) {
		logger.info("amount1---getNewList:"+amount);
		List<RepayProgramEntity> repayList_new = new ArrayList<RepayProgramEntity>();
		if (repayList != null && repayList.size() > 0) {
			for (RepayProgramEntity repayProgramEntity : repayList) {
				String serverRate = repayProgramEntity.getServerRate();
				String custRate = repayProgramEntity.getCustRate();

				// 期限14天
				repayProgramEntity.setTimeLimit("14");

				BigDecimal prin = new BigDecimal(amount).multiply(
						new BigDecimal(1).add(new BigDecimal(serverRate)
								.divide(new BigDecimal(100)))).setScale(0,
						BigDecimal.ROUND_HALF_UP);
				BigDecimal monthPayAmt = BigDecimal.ZERO;
				BigDecimal rate = new BigDecimal(custRate)
						.divide(new BigDecimal(100), 6,
								BigDecimal.ROUND_HALF_UP)
						.divide(new BigDecimal(360), 6,
								BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(14))
						.setScale(6, BigDecimal.ROUND_HALF_UP);
				monthPayAmt = prin.add(prin.multiply(rate)).setScale(0,
						BigDecimal.ROUND_HALF_UP);

				repayProgramEntity.setMonthPayAmt(monthPayAmt + "");

				if (c == 1
						&& repayProgramEntity.getName().indexOf(
								cust_type + "(1)") > 0) {
					repayList_new.add(repayProgramEntity);
					break;
				} else if (c == 2
						&& repayProgramEntity.getName().indexOf(
								cust_type + "(2)") > 0) {
					repayList_new.add(repayProgramEntity);
					break;
				} else if (c == 3) {
					if (repayProgramEntity.getName().indexOf(cust_type + "(3)") > 0) {
						repayList_new.add(repayProgramEntity);
						break;
					}
				} else if (c == 4) {
					if (repayProgramEntity.getName().indexOf(cust_type + "(4)") > 0) {
						repayList_new.add(repayProgramEntity);
						break;
					}
				} else if (c == 5) {
					if (repayProgramEntity.getName().indexOf(cust_type + "(5)") > 0) {
						repayList_new.add(repayProgramEntity);
						break;
					}
				} else if (c > 5) {
					if (repayProgramEntity.getName().indexOf(cust_type + "(6)") > 0) {
						repayList_new.add(repayProgramEntity);
						break;
					}
				}

			}

		}

		return repayList_new;
	}

	private ParmResponse getDDSJRepayProgram(LoanAmtCalNewReq loanCal) {

		List<RepayProgramEntity> repayList = null;

		// 申请金额
		BigDecimal applyAmount = BigDecimal.ZERO;

		// 剩余额度
		BigDecimal syAmount = BigDecimal.ZERO;

		// 还款后返还金额
		BigDecimal returnAmt = BigDecimal.ZERO;

		// 默认返点比率(如果该产品开通返点比例，就获取产品的返点比例 反之获取商户的返点比例)
		BigDecimal rebate = BigDecimal.ZERO;

		// 默认类型
		String cust_type = "KHL2";

		// 投资人
		String invest_name = Consts.HENGYUAN_USERNAME;

		RepayProgramNewResp repayProgramResp = new RepayProgramNewResp();
		repayProgramResp.setMinAmountTwelve("60000");
		try {

			CustomerEntity customer = customerQuery.queryCustByUserName(loanCal
					.getUserName());

			if (customer != null) {
				cust_type = customer.getCustType();
			} else {
				throw new Exception("该用户未找到,用户名:" + loanCal.getUserName());
			}

			DdsjLimit ddsjLimit = ddsjLimitQuery.queryLimit(loanCal
					.getUserName());

			List<ApplicationPay> list = payQuery.queryAppPayList(
					loanCal.getUserName(), loanCal.getIndustryCode());

			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {

					applyAmount = applyAmount.add(new BigDecimal(list.get(i)
							.getApplyAmount()));

					logger.info("流水号:" + list.get(i).getApplicationNo()
							+ ",申请金额:" + list.get(i).getApplyAmount());

					// String amount = availbleCreditQuery.getNotPayAmt(list
					// .get(i).getApplicationNo(), null);
					//
					// BigDecimal bigDecimal = BigDecimal.ZERO;
					// if (!StringUtils.isEmpty(amount)) {
					// bigDecimal = new BigDecimal(amount);
					// if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
					// returnAmt = returnAmt.add(new BigDecimal(list
					// .get(i).getApplyAmount()));
					// logger.info("流水号:" +
					// list.get(i).getApplicationNo()+",还款结清返还金额:"+returnAmt);
					// }
					// }

				}

			}

			DdsjApplyCredit ddsjApplyCredit =ddsjApplyCreditQuery.queryQx(loanCal.getUserName()); 
			// 已经授信
			if (ddsjLimit != null &&!"STATUS20".equals(ddsjApplyCredit.getStatus())) {

				if (new BigDecimal(ddsjLimit.getAmount())
						.compareTo(applyAmount) >= 0) {
					syAmount = new BigDecimal(ddsjLimit.getAmount()).subtract(
							applyAmount).add(returnAmt);
				}
				logger.info("剩余额度:" + syAmount);
				int kyAmount = syAmount.intValue() / 100 * 100;
				logger.info("可用额度:" + kyAmount);

				repayList = repayProgramQuery.queryHyRepay(cust_type,
						loanCal.getIndustryCode(), rebate, invest_name, null);

				if (repayList != null) {
					repayProgramResp.setMinAmount("1000");
					repayProgramResp.setKyAmount(String.valueOf(kyAmount));
					repayProgramResp.setPeriods("个月");
					repayProgramResp.setResultCode(ResultCodes.NORMAL);
					repayProgramResp.setList(repayList);
					repayProgramResp.setRebatePercent(String.valueOf(rebate));
				} else {
					repayProgramResp.setResultCode(ResultCodes.LOAN_PRO_FAIL);
					repayProgramResp.setResultMsg(ResourceUtils
							.getString(ResultCodes.LOAN_PRO_FAIL));
				}

			} else {
				// 默认返回高费率
				repayList = repayProgramQuery.queryHyRepay(
						customer.getCustType(), loanCal.getIndustryCode(),
						rebate, invest_name, null);// null 保留字段
				// 默认返回响应实体
				repayProgramResp = (RepayProgramNewResp) deaf_resp(
						repayProgramResp, repayList, loanCal);
			}

		} catch (Exception e) {
			RecordUtils.writeError(logger, loanCal.getUuid(),
					ResultCodes.LOAN_PRO_FAIL, e);
			// 默认返回高费率
			repayList = repayProgramQuery.queryHyRepay(cust_type,
					loanCal.getIndustryCode(), rebate, invest_name, null);
			// 默认返回响应实体
			repayProgramResp = (RepayProgramNewResp) deaf_resp(
					repayProgramResp, repayList, loanCal);

		} finally {
			ConnManager.closeConn();
		}
		return repayProgramResp;
	}

	private ParmResponse deaf_resp(ParmResponse parmResponse,
			List<RepayProgramEntity> repayList, LoanAmtCalNewReq loanCal) {
		String industryCode = loanCal.getIndustryCode();
		if ("DDSJ".equals(industryCode)) {
			RepayProgramNewResp repayProgramResp = (RepayProgramNewResp) parmResponse;
			repayProgramResp.setMinAmount("1000");
			repayProgramResp.setKyAmount("50000");
			repayProgramResp.setPeriods("个月");
			repayProgramResp.setResultCode(ResultCodes.NORMAL);
			repayProgramResp.setList(repayList);
			repayProgramResp.setRebatePercent("0");
			return repayProgramResp;
		} else if ("THFQ".equals(industryCode)) {
			RepayProgramTHFQResp repayProgramResp = (RepayProgramTHFQResp) parmResponse;
			repayProgramResp.setAmount("300");
			repayProgramResp.setPeriods("期");
			repayProgramResp.setResultCode(ResultCodes.NORMAL);
			repayProgramResp.setList(repayList);
			repayProgramResp.setRebatePercent("0");
			return repayProgramResp;
		}
		return null;
	}

	public static void main(String[] args) {
		BigDecimal prin = new BigDecimal(500).multiply(
				new BigDecimal(1).add(new BigDecimal(29.39)
						.divide(new BigDecimal(100)))).setScale(0,
				BigDecimal.ROUND_HALF_UP);
		System.out.println(prin);
		BigDecimal monthPayAmt = BigDecimal.ZERO;
		BigDecimal rate = new BigDecimal(12)
				.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP)
				.divide(new BigDecimal(360), 6, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(14))
				.setScale(6, BigDecimal.ROUND_HALF_UP);
		System.out.println(rate);
		monthPayAmt = prin.add(prin.multiply(rate)).setScale(0,
				BigDecimal.ROUND_HALF_UP);
		System.out.println(monthPayAmt);
	}
}
