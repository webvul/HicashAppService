package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

public class QueryMyMsgReq extends RequestSequence{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7164897392758028418L;
	
	/*用户名*/
	private String userName;

	/** 当前页 */
	private String curPage = "1";

	/** 每页显示最大行 */
	private Integer maxLine = 10;

	
	
	
	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public Integer getMaxLine() {
		return maxLine;
	}

	public void setMaxLine(Integer maxLine) {
		this.maxLine = maxLine;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
