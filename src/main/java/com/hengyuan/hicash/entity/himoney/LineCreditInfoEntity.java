package com.hengyuan.hicash.entity.himoney;


/**
 * 查询用户额度。
 * 
 * @author xuexin
 * @create 2017年7月14日 09:53:10
 *
 */
public class LineCreditInfoEntity {
	
	/**主键*/
	private Integer id;
	
	/**用户名称*/
	private String appNo;
	
	/**额度*/
	private Integer totalQuota;
	
	/**分数*/
	private Integer totalScore;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public Integer getTotalQuota() {
		return totalQuota;
	}

	public void setTotalQuota(Integer totalQuota) {
		this.totalQuota = totalQuota;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	
}
