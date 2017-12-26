package com.hengyuan.hicash.entity.activity;

import java.math.BigDecimal;

public class LotteryConfigEntity {

	/** 奖项ID */
	private int prizeId;

	/** 奖项名称 */
	private String prizeName;

	/** 中奖概率（1/n） */
	private BigDecimal prizeChance;

	/** 奖品数量 */
	private int prizeNum;

	/** 已经中奖次数 */
	private int alreadyLottoNumber;

	/** 抽奖次数 */
	private int lottoNum;

	/** 备注 */
	private String desc;

	/** 活动类型 */
	private String lotteryType;

	public String getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}

	public int getAlreadyLottoNumber() {
		return alreadyLottoNumber;
	}

	public void setAlreadyLottoNumber(int alreadyLottoNumber) {
		this.alreadyLottoNumber = alreadyLottoNumber;
	}

	public int getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(int prizeId) {
		this.prizeId = prizeId;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public BigDecimal getPrizeChance() {
		return prizeChance;
	}

	public void setPrizeChance(BigDecimal prizeChance) {
		this.prizeChance = prizeChance;
	}

	public int getPrizeNum() {
		return prizeNum;
	}

	public void setPrizeNum(int prizeNum) {
		this.prizeNum = prizeNum;
	}

	public int getLottoNum() {
		return lottoNum;
	}

	public void setLottoNum(int lottoNum) {
		this.lottoNum = lottoNum;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
