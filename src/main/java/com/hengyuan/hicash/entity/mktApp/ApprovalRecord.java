package com.hengyuan.hicash.entity.mktApp;


/**
 * 审批记录表
 * 
 * @author Administrator
 * 
 */
public class ApprovalRecord {

	/** 主键ID */
	private Integer recordId;

	/** 申请件ID */
	private String applicationNo;

	/** 审批类型 */
	private String approvalType;

	/** 审批动作 */
	private String approvalAction;

	/** 审批描述 */
	private String approvalDesc;

	/** 审批结果 */
	private String approvalResult;
	
	private String createUser;
	
	private String createDate;

	public ApprovalRecord() {
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public String getApprovalAction() {
		return approvalAction;
	}

	public void setApprovalAction(String approvalAction) {
		this.approvalAction = approvalAction;
	}

	public String getApprovalDesc() {
		return approvalDesc;
	}

	public void setApprovalDesc(String approvalDesc) {
		this.approvalDesc = approvalDesc;
	}

	public String getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
