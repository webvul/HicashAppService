package com.hengyuan.hicash.entity.app;



public class RulesRunResult  {
	private Integer id;
	
	/** 规则编号 */
	private String ruleNo;
	
	/** 申请单序号 */
	private Long applicationNo;
	
	/** 规则运行时间 */
	private String runTime;
	
	/** 规则运行结果（人工查看、拒绝） */
	private String runResult;
	
	/** 规则运行结果记录 */
	private String runRsRecord;
	
	/**标志位*/
	private Integer number;
	
	/** 业务操作记录ID  (recordId) */
	private String recordId;
	
	


	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	
	

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public Long getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(Long applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	public String getRunResult() {
		return runResult;
	}

	public void setRunResult(String runResult) {
		this.runResult = runResult;
	}

	public String getRunRsRecord() {
		return runRsRecord;
	}

	public void setRunRsRecord(String runRsRecord) {
		this.runRsRecord = runRsRecord;
	}
	
	
}
