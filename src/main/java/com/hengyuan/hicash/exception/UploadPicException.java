package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 上传图片失败
 * @author Cary.Liu
 *
 */
public class UploadPicException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UploadPicException(){
		super(ExceptionMsg.UPLOAD_PIC_EXCEPTION);
	}

}
