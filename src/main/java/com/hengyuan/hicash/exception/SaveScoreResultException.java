package com.hengyuan.hicash.exception;


/**
 * 
 * @author fish
 *
 * @date 2017年1月9日 下午1:52:22
 */
public class SaveScoreResultException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaveScoreResultException() {
		super("保存全自动规则表{hy_score_record}异常");
	}

}
