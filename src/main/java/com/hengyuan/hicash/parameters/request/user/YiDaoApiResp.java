package com.hengyuan.hicash.parameters.request.user;
/**
 * 	易道的api接口返回参数
 * 
 * @author 0493
 * @create 2017-07-11
 *
 */
public class YiDaoApiResp {

	private String Error;
	
	private String Details;
	
	private String Score;
	
	private String th_high;
	
	private String th_low;

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}

	public String getDetails() {
		return Details;
	}

	public void setDetails(String details) {
		Details = details;
	}

	public String getScore() {
		return Score;
	}

	public void setScore(String score) {
		Score = score;
	}

	public String getTh_high() {
		return th_high;
	}

	public void setTh_high(String th_high) {
		this.th_high = th_high;
	}

	public String getTh_low() {
		return th_low;
	}

	public void setTh_low(String th_low) {
		this.th_low = th_low;
	}

	
	
	
}
