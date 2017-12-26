package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 没有找到售点
 * @author Cary.Liu
 *
 */
public class SiteNotFoundException extends Exception {

	private static final long serialVersionUID = 8522145881654660674L;

	public SiteNotFoundException(){
		super(ExceptionMsg.SITE_NOTFOUND_EXCEPTION);
	}
	
}
