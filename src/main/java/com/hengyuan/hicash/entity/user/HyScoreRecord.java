package com.hengyuan.hicash.entity.user;

/**
 * 
 * @author fish
 *
 * @date 2017年1月10日 下午8:08:15
 */
public class HyScoreRecord {

	private String id;
	private String idNo;
	private String score;
	private String result;
	private String createDate;
	private String thresholdHigh;
	private String thresholdLow;
	private String whichPart;
	private String tempNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getThresholdHigh() {
		return thresholdHigh;
	}

	public void setThresholdHigh(String thresholdHigh) {
		this.thresholdHigh = thresholdHigh;
	}

	public String getThresholdLow() {
		return thresholdLow;
	}

	public void setThresholdLow(String thresholdLow) {
		this.thresholdLow = thresholdLow;
	}

	public String getWhichPart() {
		return whichPart;
	}

	public void setWhichPart(String whichPart) {
		this.whichPart = whichPart;
	}

	public String getTempNo() {
		return tempNo;
	}

	public void setTempNo(String tempNo) {
		this.tempNo = tempNo;
	}

}
