package com.hengyuan.hicash.constant;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.hengyuan.hicash.entity.account.RateEntity;
import com.hengyuan.hicash.utils.RepayCalculateForNew;

public class BasicData {

	// 月利率范围
	public static Map<String, Object> low_rate_map = new HashMap<String, Object>();
	public static Map<String, Object> high_rate_map = new HashMap<String, Object>();

	static {

		high_rate_map.put(IndustryConsts.INDUSTRY_HINS, "0");
		high_rate_map.put(IndustryConsts.INDUSTRY_VIPD, "1.0");
		high_rate_map.put(IndustryConsts.INDUSTRY_DDSJ, "1.2");
		high_rate_map.put(IndustryConsts.INDUSTRY_MDCP, "1.4");
		high_rate_map.put(IndustryConsts.INDUSTRY_LDDD, "1.6");
		high_rate_map.put(IndustryConsts.INDUSTRY_DDCP, "2.0");
		high_rate_map.put(IndustryConsts.INDUSTRY_JYFQ, "1.1");

//		low_rate_map.put(IndustryConsts.INDUSTRY_HINS, "0");
//		low_rate_map.put(IndustryConsts.INDUSTRY_VIPD, "0.3");
//		low_rate_map.put(IndustryConsts.INDUSTRY_DDSJ, "0.5");
//		low_rate_map.put(IndustryConsts.INDUSTRY_MDCP, "0.7");
//		low_rate_map.put(IndustryConsts.INDUSTRY_LDDD, "0.9");
//		low_rate_map.put(IndustryConsts.INDUSTRY_DDCP, "1.0");
//		low_rate_map.put(IndustryConsts.INDUSTRY_JYFQ, "0.4");
		
		low_rate_map.put(IndustryConsts.INDUSTRY_HINS, "1.0");
		low_rate_map.put(IndustryConsts.INDUSTRY_VIPD, "1.0");
		low_rate_map.put(IndustryConsts.INDUSTRY_DDSJ, "1.0");
		low_rate_map.put(IndustryConsts.INDUSTRY_MDCP, "1.0");
		low_rate_map.put(IndustryConsts.INDUSTRY_LDDD, "1.0");
		low_rate_map.put(IndustryConsts.INDUSTRY_DDCP, "1.0");
		low_rate_map.put(IndustryConsts.INDUSTRY_JYFQ, "1.0");
	}

	public static void main(String[] args) {
		
		String rate = BasicData.low_rate_map.get("LDDD").toString();
		String period = "1";
		BigDecimal creditRate = new BigDecimal(rate).divide(new BigDecimal("360"), 10, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(14)).multiply(new BigDecimal("12"));
		BigDecimal amount = new BigDecimal("800");
		System.out.println(rate);
		RateEntity rateEntity = new RateEntity("0", creditRate+"", "0", period, null, null);
		Map<Object, Object> map = RepayCalculateForNew.getAllPayBackAmtPerMonth(rateEntity, amount);
		System.out.println(String.valueOf(map.get("totalAmt")));
	}
}
