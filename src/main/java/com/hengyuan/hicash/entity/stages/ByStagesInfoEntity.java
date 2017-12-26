package com.hengyuan.hicash.entity.stages;

import java.math.BigDecimal;

/** 
 * @author dong.liu 
 * 2016-10-14 下午2:26:05
 * 类说明 :滴答贷分期
 */
public class ByStagesInfoEntity {
	
	private Long applicationNo;//流水号
	
	private String startDate;//分期开始日期
	
	private BigDecimal amount;//分期金额
	
	private Integer number;//分期期数
	
	private String status;//当前分期状态,Y/N
	
	private String operator;//分期操作人
	
    private String createDate;//分期操作时间
	
    private String closeOper;//分期关闭人
    
    private String closeDate;//分期关闭时间

	public Long getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(Long applicationNo) {
		this.applicationNo = applicationNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCloseOper() {
		return closeOper;
	}

	public void setCloseOper(String closeOper) {
		this.closeOper = closeOper;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	
	
}
