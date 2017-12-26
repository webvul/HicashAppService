package com.hengyuan.hicash.entity.user;

/**
 * 员工
 * 
 * @author think
 *
 */
public class HyEmployeeEntity {

	/** 员工用户名 */
	private String userName;

	/** 姓名 */
	private String realName;

	/** 员工号 */
	private String employeeCode;

	/** 逻辑编码 */
	private String agentLogicCode;

	private String province;

	private String city;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getAgentLogicCode() {
		return agentLogicCode;
	}

	public void setAgentLogicCode(String agentLogicCode) {
		this.agentLogicCode = agentLogicCode;
	}

}
