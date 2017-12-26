package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 学生用户查看资料 响应 联系信息
 * 
 * @author Cary.Liu
 * @create date 2014-07-22
 * 
 */
public class StuContactInfoResp extends ParmResponse {

	/** 返回代码 */
	private String resultCode;

	/** 家庭地址--省 */
	private String fimilyPro;

	/** 家庭地址--市 */
	private String fimilyCity;

	/** 家庭地址--区 */
	private String fimilyArea;

	/** 家庭街道地址 */
	private String fimilyRoad;

	// 现居
	/** 现居地址--省 */
	private String nowProvince;

	/** 现居地址--市 */
	private String nowCity;

	/** 现居地址--县区 */
	private String nowArea;

	/** 现居街道地址 */
	private String nowAddress;

	/** 学校地址--省 */
	private String schoolPro;

	/** 学校地址--市 */
	private String schoolCity;

	/** 学校地址--区 */
	private String schoolArea;

	/** 学校街道地址 */
	private String schoolRoad;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getFimilyPro() {
		return fimilyPro;
	}

	public void setFimilyPro(String fimilyPro) {
		this.fimilyPro = fimilyPro;
	}

	public String getFimilyCity() {
		return fimilyCity;
	}

	public void setFimilyCity(String fimilyCity) {
		this.fimilyCity = fimilyCity;
	}

	public String getFimilyArea() {
		return fimilyArea;
	}

	public void setFimilyArea(String fimilyArea) {
		this.fimilyArea = fimilyArea;
	}

	public String getFimilyRoad() {
		return fimilyRoad;
	}

	public void setFimilyRoad(String fimilyRoad) {
		this.fimilyRoad = fimilyRoad;
	}

	public String getNowProvince() {
		return nowProvince;
	}

	public void setNowProvince(String nowProvince) {
		this.nowProvince = nowProvince;
	}

	public String getNowCity() {
		return nowCity;
	}

	public void setNowCity(String nowCity) {
		this.nowCity = nowCity;
	}

	public String getNowArea() {
		return nowArea;
	}

	public void setNowArea(String nowArea) {
		this.nowArea = nowArea;
	}

	public String getNowAddress() {
		return nowAddress;
	}

	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}

	public String getSchoolPro() {
		return schoolPro;
	}

	public void setSchoolPro(String schoolPro) {
		this.schoolPro = schoolPro;
	}

	public String getSchoolCity() {
		return schoolCity;
	}

	public void setSchoolCity(String schoolCity) {
		this.schoolCity = schoolCity;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public String getSchoolRoad() {
		return schoolRoad;
	}

	public void setSchoolRoad(String schoolRoad) {
		this.schoolRoad = schoolRoad;
	}

}
