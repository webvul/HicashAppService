package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

public class CheckLimitResp extends ParmResponse {

	/** 是否可以提额 */
	private String is_limit;

	/** 是否跳转授信页面 */
	private String is_return;
	
	/** 按钮描述 */
	private String but_msg_1;

	private String but_url_1;
	
	
	public String getBut_url_1() {
		return but_url_1;
	}

	public void setBut_url_1(String but_url_1) {
		this.but_url_1 = but_url_1;
	}

	public String getIs_limit() {
		return is_limit;
	}

	public void setIs_limit(String is_limit) {
		this.is_limit = is_limit;
	}

	public String getIs_return() {
		return is_return;
	}

	public void setIs_return(String is_return) {
		this.is_return = is_return;
	}

	public String getBut_msg_1() {
		return but_msg_1;
	}

	public void setBut_msg_1(String but_msg_1) {
		this.but_msg_1 = but_msg_1;
	}

}
