package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

public class CheckCreditResp extends ParmResponse {

	/** 是否可以授信 */
	private String is_auth;

	/** 是否跳转授信页面 */
	private String is_return;

	/** 按钮描述 */
	private String but_msg_2;

	/** 按钮描述 */
	private String but_msg_1;
	
	

	private String but_url_1;
	
	private String but_url_2;
	//变色文字
	private String color_str;
	
	public String getBut_url_1() {
		return but_url_1;
	}

	public void setBut_url_1(String but_url_1) {
		this.but_url_1 = but_url_1;
	}

	public String getBut_url_2() {
		return but_url_2;
	}

	public void setBut_url_2(String but_url_2) {
		this.but_url_2 = but_url_2;
	}

	public String getIs_auth() {
		return is_auth;
	}

	public void setIs_auth(String is_auth) {
		this.is_auth = is_auth;
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

	public String getBut_msg_2() {
		return but_msg_2;
	}

	public void setBut_msg_2(String but_msg_2) {
		this.but_msg_2 = but_msg_2;
	}

	public String getColor_str() {
		return color_str;
	}

	public void setColor_str(String color_str) {
		this.color_str = color_str;
	}

	
	
	

}
