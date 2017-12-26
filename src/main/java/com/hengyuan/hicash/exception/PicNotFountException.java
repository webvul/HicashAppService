package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 上传失败,未接收到图片
 * 
 * @author Cary.Liu
 *
 */
public class PicNotFountException extends Exception {

	private static final long serialVersionUID = -4913563865230270664L;

	public PicNotFountException(){
		super(ExceptionMsg.UPLOAD_PIC_NOTFOUNT_EXCEPION);
	}
	
}
