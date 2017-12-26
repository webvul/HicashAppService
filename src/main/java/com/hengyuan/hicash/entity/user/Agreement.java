package com.hengyuan.hicash.entity.user;

import java.util.List;

/**
 * @author xing.yuan
 * @date 2017年11月30日 上午10:08:17
 * 类说明
 */
public class Agreement {

	private String desFront;//文字描述
	
	private String name;//名称
	
	private String url;//协议路径
	
	private String desBehind;
	
	private String desBelow;//文案
	
	private String desTitle;//
	
	private String desUrl;//
	
	private String desTitleBgColor;//

	private List<AgreementDetail> desDetails;

	private List<Agreement> insurance;//保险协议数组

	public String getDesFront() {
		return desFront;
	}

	public void setDesFront(String desFront) {
		this.desFront = desFront;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesBehind() {
		return desBehind;
	}

	public void setDesBehind(String desBehind) {
		this.desBehind = desBehind;
	}

	public List<Agreement> getInsurance() {
		return insurance;
	}

	public void setInsurance(List<Agreement> insurance) {
		this.insurance = insurance;
	}

	public String getDesBelow() {
		return desBelow;
	}

	public void setDesBelow(String desBelow) {
		this.desBelow = desBelow;
	}
	
	public String getDesTitle() {
		return desTitle;
	}

	public void setDesTitle(String desTitle) {
		this.desTitle = desTitle;
	}

	public String getDesUrl() {
		return desUrl;
	}

	public void setDesUrl(String desUrl) {
		this.desUrl = desUrl;
	}

	public String getDesTitleBgColor() {
		return desTitleBgColor;
	}

	public void setDesTitleBgColor(String desTitleBgColor) {
		this.desTitleBgColor = desTitleBgColor;
	}

	public List<AgreementDetail> getDesDetails() {
		return desDetails;
	}

	public void setDesDetails(List<AgreementDetail> desDetails) {
		this.desDetails = desDetails;
	}
	
}
