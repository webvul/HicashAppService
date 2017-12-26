package com.hengyuan.hicash.entity.user;
/**
 * 优惠劵
 * @author Administrator
 *
 */
public class CouPonEntity {
	
	private String couPonId;
	//优惠券名称
	private String couPonName;
	//优惠券类型 1：优惠比例；2固定金额
	private String couPonType;
	//优惠比例 
	private String  discount;
	//券使用次数
	private String useNum;
	//客户使用次数
	private String cusNum;
	//限定客户
	private String cusGroup;
	//限定金额
	private String limitAmount;
	//限定期数
	private String limitStage;
	//金额判断条件 1：等于；2：大于等于；3：小于等于
	private String ifAmount;
	//期数判断条件 1：等于；2：大于等于；3：小于等于
	private String ifNumber;
	//适用产品
	private String applyProduct;
	//有效期开始
	private String beginTime;
	//有效期结束
	private String endTime;
	//注册日期开始
	private String registerBeginTime;
	//注册日期结束
	private String registerEndTime;
	//是否启用 1:启用;0:不启用
	private String isEnable;
	//优惠券文字
	private String message;
	//使用按钮链接
	private String useUrl;
	//链接类型 1h5 2app
	private String urlType;
	public String getCouPonId() {
		return couPonId;
	}
	public void setCouPonId(String couPonId) {
		this.couPonId = couPonId;
	}
	public String getCouPonName() {
		return couPonName;
	}
	public void setCouPonName(String couPonName) {
		this.couPonName = couPonName;
	}
	public String getCouPonType() {
		return couPonType;
	}
	public void setCouPonType(String couPonType) {
		this.couPonType = couPonType;
	}
	
	
	
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getUseNum() {
		return useNum;
	}
	public void setUseNum(String useNum) {
		this.useNum = useNum;
	}
	public String getCusNum() {
		return cusNum;
	}
	public void setCusNum(String cusNum) {
		this.cusNum = cusNum;
	}
	public String getCusGroup() {
		return cusGroup;
	}
	public void setCusGroup(String cusGroup) {
		this.cusGroup = cusGroup;
	}
	public String getLimitAmount() {
		return limitAmount;
	}
	public void setLimitAmount(String limitAmount) {
		this.limitAmount = limitAmount;
	}
	public String getLimitStage() {
		return limitStage;
	}
	public void setLimitStage(String limitStage) {
		this.limitStage = limitStage;
	}
	
	public String getIfAmount() {
		return ifAmount;
	}
	public void setIfAmount(String ifAmount) {
		this.ifAmount = ifAmount;
	}
	public String getIfNumber() {
		return ifNumber;
	}
	public void setIfNumber(String ifNumber) {
		this.ifNumber = ifNumber;
	}
	public String getApplyProduct() {
		return applyProduct;
	}
	public void setApplyProduct(String applyProduct) {
		this.applyProduct = applyProduct;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getRegisterBeginTime() {
		return registerBeginTime;
	}
	public void setRegisterBeginTime(String registerBeginTime) {
		this.registerBeginTime = registerBeginTime;
	}
	public String getRegisterEndTime() {
		return registerEndTime;
	}
	public void setRegisterEndTime(String registerEndTime) {
		this.registerEndTime = registerEndTime;
	}
	public String getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUseUrl() {
		return useUrl;
	}
	public void setUseUrl(String useUrl) {
		this.useUrl = useUrl;
	}
	public String getUrlType() {
		return urlType;
	}
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	
	
	
	
	
	
	
	

}
