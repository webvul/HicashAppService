package com.hengyuan.hicash.exception;

/**
 * 
 * @author fish
 *
 * @date 2017年1月10日 下午6:26:39
 */
public class UploadAppPicException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UploadAppPicException(){
		super("上传图片信息失败");
	}
}
