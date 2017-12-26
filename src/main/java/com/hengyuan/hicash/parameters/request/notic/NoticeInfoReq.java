package com.hengyuan.hicash.parameters.request.notic;


import com.hengyuan.hicash.parameters.request.RequestSequence;




/**
 * 公告传入参数类
 * 
 * @author Eric.shi
 * @create date 2014-07-25
 * 
 */
public class NoticeInfoReq extends RequestSequence {

	private static final long serialVersionUID = 1L;
	
	/** 公告ID */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
