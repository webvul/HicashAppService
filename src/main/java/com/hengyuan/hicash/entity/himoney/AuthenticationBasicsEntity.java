package com.hengyuan.hicash.entity.himoney;


/**
 * 客户选择认证的项
 * 
 * @author xuexin
 * @create 2017年7月14日 09:53:10
 *
 */
public class AuthenticationBasicsEntity {
	
	/**主键*/
	private Integer id;
	
	/**认证项名称*/
	private String name;
	
	/**标题*/
	private String title;
	
	/**额度*/
	private Integer quota;
	
	/**分数*/
	private Integer score;
	
	/**是否禁用（0禁用 1启用）*/
	private Integer isDisable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getQuota() {
		return quota;
	}

	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
	}
	
	
}
