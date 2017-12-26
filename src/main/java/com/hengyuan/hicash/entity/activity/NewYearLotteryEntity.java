package com.hengyuan.hicash.entity.activity;

/**
 * 新年活动抽奖记录
 * 
 * @author Cary.Liu
 * @createDate 2015-12-29
 *
 */
public class NewYearLotteryEntity {

	private Integer id;

	/** 用户名 */
	private String userName;

	/** 姓名 */
	private String realName;

	/** 手机号码 */
	private String mobile;

	/** 中奖奖项ID */
	private String zjPrizeNum;

	/** 中奖奖品名称 */
	private String zjPrizeName;

	/** 以抽奖奖项ID（多个） */
	private String cjPrizeNums;

	/** 抽奖时间 */
	private String createTime;

	/** 快递地址-省份 */
	private String expressProvince;

	/** 快递地址-城市 */
	private String expressCity;

	/** 快递地址-区/县 */
	private String expressArea;

	/** 快递地址-详细地址 */
	private String expressDetail;
	
	/** 活动类型 */
	private String lotteryType;

	public String getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}

	public String getZjPrizeName() {
		return zjPrizeName;
	}

	public void setZjPrizeName(String zjPrizeName) {
		this.zjPrizeName = zjPrizeName;
	}

	public String getZjPrizeNum() {
		return zjPrizeNum;
	}

	public void setZjPrizeNum(String zjPrizeNum) {
		this.zjPrizeNum = zjPrizeNum;
	}

	public String getCjPrizeNums() {
		return cjPrizeNums;
	}

	public void setCjPrizeNums(String cjPrizeNums) {
		this.cjPrizeNums = cjPrizeNums;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getExpressProvince() {
		return expressProvince;
	}

	public void setExpressProvince(String expressProvince) {
		this.expressProvince = expressProvince;
	}

	public String getExpressCity() {
		return expressCity;
	}

	public void setExpressCity(String expressCity) {
		this.expressCity = expressCity;
	}

	public String getExpressArea() {
		return expressArea;
	}

	public void setExpressArea(String expressArea) {
		this.expressArea = expressArea;
	}

	public String getExpressDetail() {
		return expressDetail;
	}

	public void setExpressDetail(String expressDetail) {
		this.expressDetail = expressDetail;
	}

}
