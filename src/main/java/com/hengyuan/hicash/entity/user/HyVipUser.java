package com.hengyuan.hicash.entity.user;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author meng.zhang 2017年2月22日 下午3:50:58 类说明 vip用户表 hy_vip_user
 */

public class HyVipUser {
	private Integer id;
	
	/**用户名 */
	private String username;
	
	/**授信额度 */
	private BigDecimal amount;
	
	/**手机号 */
	private String mobile;
	
	/**初始期数 */
	private Integer initPeriod;
	
	/**状态:CANL/取消,冻结/FREZ,正常/NOML */
	private String status;
	
	/**是否VIP:0/否,1/是 */
	private Integer isVip;
	
	/**是否授信:0/未授信,1/授信完成 */
	private Integer isAuth;
	
	/**创建时间 */
	private Date createTime;
	
	/**描述 */
	private String message;
	
	/**客户分类:1/A类低费率,2/B类中费率,3/C类高费率**/
	private Integer type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getInitPeriod() {
		return initPeriod;
	}

	public void setInitPeriod(Integer initPeriod) {
		this.initPeriod = initPeriod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public Integer getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
