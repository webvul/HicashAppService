package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
 * @author blanke.qin 2017年4月6日 上午11:57:37 类说明
 */
public class PageMessageReq extends RequestSequence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3401935908160009308L;
    //页面编码
	private String page_code;
	//是否跳过芝麻
	private String is_zm;
	//是否跳过学信
	private String is_xx;
	
	//用户名
	private String userName;
	//是否跳过公积金
	private String is_gj;
	
	

	public String getPage_code() {
		return page_code;
	}

	public void setPage_code(String page_code) {
		this.page_code = page_code;
	}

	public String getIs_zm() {
		return is_zm;
	}

	public void setIs_zm(String is_zm) {
		this.is_zm = is_zm;
	}

	public String getIs_xx() {
		return is_xx;
	}

	public void setIs_xx(String is_xx) {
		this.is_xx = is_xx;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIs_gj() {
		return is_gj;
	}

	public void setIs_gj(String is_gj) {
		this.is_gj = is_gj;
	}

	

	
   
	
}
