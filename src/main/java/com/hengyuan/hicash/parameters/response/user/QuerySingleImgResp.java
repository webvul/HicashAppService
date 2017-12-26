package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.entity.user.CustTempApplyPicEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 图片查询 嗨秒贷图片查询返回参数类
 * 
 * @author LiHua.Ren
 * @createDate 2015-06-01
 *
 */
public class QuerySingleImgResp extends ParmResponse {

	/** 业务员现场照 */
	private String saleFrontUrl;
	
	/** 业务员现场照 */
	private String saleFrontUrlThum;
	
	/** 用户手持身份证正面照 */
	private String userIdcardFrontUrl;

	/** 身份证正面照 */
	private String idcardFrontUrl;

	/** 身份证反面照 */
	private String idcardVersoUrl;

	/** 银行卡正面 */
	private String bankCardFrontUrl;

	/** 银行卡反面 */
	private String bankCardVersoUrl;

	/* 小图 */
	/** 用户手持身份证正面照 */
	private String userIdcardFrontUrlThum;

	/** 身份证正面照 */
	private String idcardFrontUrlThum;

	/** 身份证反面照 */
	private String idcardVersoUrlThum;

	/** 银行卡正面 */
	private String bankCardFrontUrlThum;

	/** 银行卡反面 */
	private String bankCardVersoUrlThum;

	/** 学生证封面ZL04 */
	private String stuCardFrontUrl;
	/** 个人照片及基本信息ZL05 */
	private String stuPhotoInfoUrl;
	/** 注册登记信息ZL06 */
	private String stuRegistInfoUrl;
	/** 校园卡正面ZL07 */
	private String schoolCardFrontUrl;
	/** 校园卡反面ZL08 */
	private String schoolCardVersoUrl;

	/** 学生证封面ZL04 */
	private String stuCardFrontUrlThum;
	/** 个人照片及基本信息ZL05 */
	private String stuPhotoInfoUrlThum;
	/** 注册登记信息ZL06 */
	private String stuRegistInfoUrlThum;
	/** 校园卡正面ZL07 */
	private String schoolCardFrontUrlThum;
	/** 校园卡反面ZL08 */
	private String schoolCardVersoUrlThum;

	/** 工作证明图片集合 */
	private List<CustTempApplyPicEntity> jobCertifyList;

	/** 收入证明图片集合 */
	private List<CustTempApplyPicEntity> inComeCertifyList;

	public String getBankCardFrontUrl() {
		return bankCardFrontUrl;
	}

	public void setBankCardFrontUrl(String bankCardFrontUrl) {
		this.bankCardFrontUrl = bankCardFrontUrl;
	}

	public String getBankCardVersoUrl() {
		return bankCardVersoUrl;
	}

	public void setBankCardVersoUrl(String bankCardVersoUrl) {
		this.bankCardVersoUrl = bankCardVersoUrl;
	}

	public String getBankCardFrontUrlThum() {
		return bankCardFrontUrlThum;
	}

	public void setBankCardFrontUrlThum(String bankCardFrontUrlThum) {
		this.bankCardFrontUrlThum = bankCardFrontUrlThum;
	}

	public String getBankCardVersoUrlThum() {
		return bankCardVersoUrlThum;
	}

	public void setBankCardVersoUrlThum(String bankCardVersoUrlThum) {
		this.bankCardVersoUrlThum = bankCardVersoUrlThum;
	}

	public List<CustTempApplyPicEntity> getJobCertifyList() {
		return jobCertifyList;
	}

	public void setJobCertifyList(List<CustTempApplyPicEntity> jobCertifyList) {
		this.jobCertifyList = jobCertifyList;
	}

	public List<CustTempApplyPicEntity> getInComeCertifyList() {
		return inComeCertifyList;
	}

	public void setInComeCertifyList(
			List<CustTempApplyPicEntity> inComeCertifyList) {
		this.inComeCertifyList = inComeCertifyList;
	}

	public String getUserIdcardFrontUrlThum() {
		return userIdcardFrontUrlThum;
	}

	public void setUserIdcardFrontUrlThum(String userIdcardFrontUrlThum) {
		this.userIdcardFrontUrlThum = userIdcardFrontUrlThum;
	}

	public String getIdcardFrontUrlThum() {
		return idcardFrontUrlThum;
	}

	public void setIdcardFrontUrlThum(String idcardFrontUrlThum) {
		this.idcardFrontUrlThum = idcardFrontUrlThum;
	}

	public String getIdcardVersoUrlThum() {
		return idcardVersoUrlThum;
	}

	public void setIdcardVersoUrlThum(String idcardVersoUrlThum) {
		this.idcardVersoUrlThum = idcardVersoUrlThum;
	}

	/**
	 * @return the userIdcardFrontUrl
	 */
	public String getUserIdcardFrontUrl() {
		return userIdcardFrontUrl;
	}

	/**
	 * @param userIdcardFrontUrl
	 *            the userIdcardFrontUrl to set
	 */
	public void setUserIdcardFrontUrl(String userIdcardFrontUrl) {
		this.userIdcardFrontUrl = userIdcardFrontUrl;
	}

	/**
	 * @return the idcardFrontUrl
	 */
	public String getIdcardFrontUrl() {
		return idcardFrontUrl;
	}

	/**
	 * @param idcardFrontUrl
	 *            the idcardFrontUrl to set
	 */
	public void setIdcardFrontUrl(String idcardFrontUrl) {
		this.idcardFrontUrl = idcardFrontUrl;
	}

	/**
	 * @return the idcardVersoUrl
	 */
	public String getIdcardVersoUrl() {
		return idcardVersoUrl;
	}

	/**
	 * @param idcardVersoUrl
	 *            the idcardVersoUrl to set
	 */
	public void setIdcardVersoUrl(String idcardVersoUrl) {
		this.idcardVersoUrl = idcardVersoUrl;
	}

	/**
	 * @return the stuCardFrontUrl
	 */
	public String getStuCardFrontUrl() {
		return stuCardFrontUrl;
	}

	/**
	 * @param stuCardFrontUrl
	 *            the stuCardFrontUrl to set
	 */
	public void setStuCardFrontUrl(String stuCardFrontUrl) {
		this.stuCardFrontUrl = stuCardFrontUrl;
	}

	/**
	 * @return the stuPhotoInfoUrl
	 */
	public String getStuPhotoInfoUrl() {
		return stuPhotoInfoUrl;
	}

	/**
	 * @param stuPhotoInfoUrl
	 *            the stuPhotoInfoUrl to set
	 */
	public void setStuPhotoInfoUrl(String stuPhotoInfoUrl) {
		this.stuPhotoInfoUrl = stuPhotoInfoUrl;
	}

	/**
	 * @return the stuRegistInfoUrl
	 */
	public String getStuRegistInfoUrl() {
		return stuRegistInfoUrl;
	}

	/**
	 * @param stuRegistInfoUrl
	 *            the stuRegistInfoUrl to set
	 */
	public void setStuRegistInfoUrl(String stuRegistInfoUrl) {
		this.stuRegistInfoUrl = stuRegistInfoUrl;
	}

	/**
	 * @return the schoolCardFrontUrl
	 */
	public String getSchoolCardFrontUrl() {
		return schoolCardFrontUrl;
	}

	/**
	 * @param schoolCardFrontUrl
	 *            the schoolCardFrontUrl to set
	 */
	public void setSchoolCardFrontUrl(String schoolCardFrontUrl) {
		this.schoolCardFrontUrl = schoolCardFrontUrl;
	}

	/**
	 * @return the schoolCardVersoUrl
	 */
	public String getSchoolCardVersoUrl() {
		return schoolCardVersoUrl;
	}

	/**
	 * @param schoolCardVersoUrl
	 *            the schoolCardVersoUrl to set
	 */
	public void setSchoolCardVersoUrl(String schoolCardVersoUrl) {
		this.schoolCardVersoUrl = schoolCardVersoUrl;
	}

	/**
	 * @return the stuCardFrontUrlThum
	 */
	public String getStuCardFrontUrlThum() {
		return stuCardFrontUrlThum;
	}

	/**
	 * @param stuCardFrontUrlThum
	 *            the stuCardFrontUrlThum to set
	 */
	public void setStuCardFrontUrlThum(String stuCardFrontUrlThum) {
		this.stuCardFrontUrlThum = stuCardFrontUrlThum;
	}

	/**
	 * @return the stuPhotoInfoUrlThum
	 */
	public String getStuPhotoInfoUrlThum() {
		return stuPhotoInfoUrlThum;
	}

	/**
	 * @param stuPhotoInfoUrlThum
	 *            the stuPhotoInfoUrlThum to set
	 */
	public void setStuPhotoInfoUrlThum(String stuPhotoInfoUrlThum) {
		this.stuPhotoInfoUrlThum = stuPhotoInfoUrlThum;
	}

	/**
	 * @return the stuRegistInfoUrlThum
	 */
	public String getStuRegistInfoUrlThum() {
		return stuRegistInfoUrlThum;
	}

	/**
	 * @param stuRegistInfoUrlThum
	 *            the stuRegistInfoUrlThum to set
	 */
	public void setStuRegistInfoUrlThum(String stuRegistInfoUrlThum) {
		this.stuRegistInfoUrlThum = stuRegistInfoUrlThum;
	}

	/**
	 * @return the schoolCardFrontUrlThum
	 */
	public String getSchoolCardFrontUrlThum() {
		return schoolCardFrontUrlThum;
	}

	/**
	 * @param schoolCardFrontUrlThum
	 *            the schoolCardFrontUrlThum to set
	 */
	public void setSchoolCardFrontUrlThum(String schoolCardFrontUrlThum) {
		this.schoolCardFrontUrlThum = schoolCardFrontUrlThum;
	}

	/**
	 * @return the schoolCardVersoUrlThum
	 */
	public String getSchoolCardVersoUrlThum() {
		return schoolCardVersoUrlThum;
	}

	/**
	 * @param schoolCardVersoUrlThum
	 *            the schoolCardVersoUrlThum to set
	 */
	public void setSchoolCardVersoUrlThum(String schoolCardVersoUrlThum) {
		this.schoolCardVersoUrlThum = schoolCardVersoUrlThum;
	}

	public String getSaleFrontUrl() {
		return saleFrontUrl;
	}

	public void setSaleFrontUrl(String saleFrontUrl) {
		this.saleFrontUrl = saleFrontUrl;
	}

	public String getSaleFrontUrlThum() {
		return saleFrontUrlThum;
	}

	public void setSaleFrontUrlThum(String saleFrontUrlThum) {
		this.saleFrontUrlThum = saleFrontUrlThum;
	}

}
