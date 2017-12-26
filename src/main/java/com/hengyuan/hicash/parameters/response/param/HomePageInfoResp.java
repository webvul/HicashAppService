package com.hengyuan.hicash.parameters.response.param;

import java.util.List;

import com.hengyuan.hicash.entity.param.BannerEntity;
import com.hengyuan.hicash.entity.param.ProShowEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * app首页展示(图片和热卖商品) resp
 * 
 * @author Cary.Liu
 * @createDate 2015-06-05
 *
 */
public class HomePageInfoResp extends ParmResponse {

	/** 还款提醒数 */
	private int hkNumber;

	/** 进度查询数 */
	private int jdNumber;

	/** 嗨秒贷数 */
	private int hmdNumber;

	/** 图片集合 */
	private List<BannerEntity> bannerList;

	/** 商品集合 */
	private List<ProShowEntity> proList;

	public int getHkNumber() {
		return hkNumber;
	}

	public void setHkNumber(int hkNumber) {
		this.hkNumber = hkNumber;
	}

	public int getJdNumber() {
		return jdNumber;
	}

	public void setJdNumber(int jdNumber) {
		this.jdNumber = jdNumber;
	}

	public int getHmdNumber() {
		return hmdNumber;
	}

	public void setHmdNumber(int hmdNumber) {
		this.hmdNumber = hmdNumber;
	}

	public List<BannerEntity> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<BannerEntity> bannerList) {
		this.bannerList = bannerList;
	}

	public List<ProShowEntity> getProList() {
		return proList;
	}

	public void setProList(List<ProShowEntity> proList) {
		this.proList = proList;
	}

}
