package com.hengyuan.hicash.entity.mktApp;
/**
 * 
* @author dong.liu 
* 2017-4-7 下午3:01:53
* 类说明:正式授信单实体
 */
public class ApplyCredit {
	
	
	private String status;//审核状态
	
	private String node;//审核节点（审核中-SHNODE，审核完成-WCNODE,取消-GBNODE）
	
	private String appNo;//流水号
	
	private String username;//用户名

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	
}
