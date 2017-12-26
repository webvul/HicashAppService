package com.hengyuan.hicash.parameters.response.calculate;

import java.util.List;

import com.hengyuan.hicash.entity.RepayProgramEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 
 * @author teng
 *
 */
public class RepayProgramTHFQResp extends ParmResponse {

	/** 还款方案集合 */
	private List<RepayProgramEntity> list;

	/** 返点比例 */
	private String rebatePercent;

	/** app显示单位 */
	private String periods;
	
	/** 申请金额 */
	private String amount;


	public List<RepayProgramEntity> getList() {
		return list;
	}

	public void setList(List<RepayProgramEntity> list) {
		this.list = list;
	}

	public String getRebatePercent() {
		return rebatePercent;
	}

	public void setRebatePercent(String rebatePercent) {
		this.rebatePercent = rebatePercent;
	}

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
