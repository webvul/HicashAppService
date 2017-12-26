package com.hengyuan.hicash.entity.user;
/**
 * @author xing.yuan
 * @date 2017年12月21日 下午3:32:38
 * 类说明
 */
public class RedpacketInfoEntity {
	
	private String id;
	
	private String userName;
	
	/*红包金额*/
	private String redpacketAmount;
	
	/*是否可用*/
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRedpacketAmount() {
		return redpacketAmount;
	}

	public void setRedpacketAmount(String redpacketAmount) {
		this.redpacketAmount = redpacketAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
