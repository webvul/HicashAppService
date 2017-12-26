package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 图片查询
 * 嗨秒贷图片查询请求参数类
 * @author LiHua.Ren
 * @createDate 2015-06-01
 *
 */
public class QuerySingleImgReq extends RequestSequence {

	private static final long serialVersionUID = -2271251560794240860L;
//	private String tempAppNo;
	private String userName;
	
//	/**
//	 * @return the tempAppNo
//	 */
//	public String getTempAppNo() {
//		return tempAppNo;
//	}
//	/**
//	 * @param tempAppNo the tempAppNo to set
//	 */
//	public void setTempAppNo(String tempAppNo) {
//		this.tempAppNo = tempAppNo;
//	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
