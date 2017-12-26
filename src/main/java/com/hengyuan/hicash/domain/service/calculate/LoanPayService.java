package com.hengyuan.hicash.domain.service.calculate;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.hengyuan.hicash.constant.BasicData;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.entity.account.RateEntity;
import com.hengyuan.hicash.parameters.request.user.LoanPayReq;
import com.hengyuan.hicash.parameters.request.user.LoanPayResp;
import com.hengyuan.hicash.utils.RepayCalculateForNew;

/**
 * 
 * @author teng
 *
 */
public class LoanPayService {

	private static Logger logger = Logger.getLogger(LoanPayService.class);

	public LoanPayResp todo(LoanPayReq loanPayReq) {
		LoanPayResp loanPayResp = new LoanPayResp();
		try {
			String low_rate = BasicData.low_rate_map.get(loanPayReq.getIndustryCode()).toString();
			String high_rate = BasicData.high_rate_map.get(loanPayReq.getIndustryCode()).toString();
			int period = 1;
			BigDecimal creditRate = new BigDecimal(low_rate);
			BigDecimal creditRate2 = new BigDecimal(high_rate);
			if (Integer.parseInt(loanPayReq.getDays()) < 30) {
				period = 1;
				creditRate = new BigDecimal(low_rate).divide(new BigDecimal("30"), 10, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(loanPayReq.getDays()));
				creditRate2 = new BigDecimal(high_rate).divide(new BigDecimal("30"), 10, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(loanPayReq.getDays()));
			} else {
				period = Integer.parseInt(loanPayReq.getDays()) / 30;
			}

			RateEntity rateEntity = new RateEntity("0", creditRate.multiply(new BigDecimal(12)) + "" + "", "0",
					period + "", null, null);
			RateEntity rateEntity2 = new RateEntity("0", creditRate2.multiply(new BigDecimal(12)) + "" + "", "0",
					period + "", null, null);
			Map<Object, Object> map = RepayCalculateForNew.getAllPayBackAmtPerMonth(rateEntity,
					new BigDecimal(loanPayReq.getAmount()));
			Map<Object, Object> map2 = RepayCalculateForNew.getAllPayBackAmtPerMonth(rateEntity2,
					new BigDecimal(loanPayReq.getAmount()));
			logger.info(JSON.toJSONString(map));
			loanPayResp.setLowRate(low_rate);
			loanPayResp.setHighRate(high_rate);
			loanPayResp.setLowPay(String.valueOf(map.get("totalAmt")));
			loanPayResp.setHighPay(String.valueOf(map2.get("totalAmt")));
			loanPayResp.setResultCode(ResultCodes.NORMAL);
		} catch (Exception e) {
			loanPayResp.setResultCode(ResultCodes.LOAN_PRO_ERRER);
		}
		return loanPayResp;
	}

}
