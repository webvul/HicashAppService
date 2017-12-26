package com.hengyuan.hicash.parameters.response.calculate;

import java.util.List;

import com.hengyuan.hicash.entity.RepayProgramEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 
 * @author teng
 *
 */
public class RepayProgramNewResp extends ParmResponse {

	/** 还款方案集合 */
	private List<RepayProgramEntity> list;

	/** 返点比例 */
	private String rebatePercent;

	/** app显示单位 */
	private String periods;

	/** 可用额度 */
	private String kyAmount;

	/** 是否既有客户 */
	private String is_jy;

	/** 可用最小额度 */
	private String minAmount;
	/**12期最小额度**/
	private String minAmountTwelve;

	
	
	
	public String getMinAmountTwelve() {
		return minAmountTwelve;
	}

	public void setMinAmountTwelve(String minAmountTwelve) {
		this.minAmountTwelve = minAmountTwelve;
	}

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

	public String getKyAmount() {
		return kyAmount;
	}

	public void setKyAmount(String kyAmount) {
		this.kyAmount = kyAmount;
	}

	public String getIs_jy() {
		return is_jy;
	}

	public void setIs_jy(String is_jy) {
		this.is_jy = is_jy;
	}

	public String getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(String minAmount) {
		this.minAmount = minAmount;
	}

}
