package com.hengyuan.hicash.entity.user;
/** 
 * @author dong.liu 
 * 2017-3-26 下午3:03:19
 * 类说明 :滴滴司机额度信息表
 */
public class DdsjLimitEntity {
	
	private String username;//用户名
	private String sx_mobile;//授信成功工作手机号
	private String sx_mobile_password;//授信滴滴密码
	private String amount;//授信额度
	private String latelySx_success_date;//最近一次授信成功时间
	private String latelyTe_success_date;//最近一次提额时间
	private String sx_success_time;//授信成功次数
	private String is_credit;//是否必须授信逻辑(必须：Y；其他：N)
	private String status;//状态（授信审核中，提额审核中，审核完成）
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSx_mobile() {
		return sx_mobile;
	}
	public void setSx_mobile(String sx_mobile) {
		this.sx_mobile = sx_mobile;
	}
	public String getSx_mobile_password() {
		return sx_mobile_password;
	}
	public void setSx_mobile_password(String sx_mobile_password) {
		this.sx_mobile_password = sx_mobile_password;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getLatelySx_success_date() {
		return latelySx_success_date;
	}
	public void setLatelySx_success_date(String latelySx_success_date) {
		this.latelySx_success_date = latelySx_success_date;
	}
	public String getLatelyTe_success_date() {
		return latelyTe_success_date;
	}
	public void setLatelyTe_success_date(String latelyTe_success_date) {
		this.latelyTe_success_date = latelyTe_success_date;
	}
	public String getSx_success_time() {
		return sx_success_time;
	}
	public void setSx_success_time(String sx_success_time) {
		this.sx_success_time = sx_success_time;
	}
	public String getIs_credit() {
		return is_credit;
	}
	public void setIs_credit(String is_credit) {
		this.is_credit = is_credit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	

}
