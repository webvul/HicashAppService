package com.hengyuan.hicash.entity.himoney;



/**
 * 客户选择认证的项
 * 
 * @author xuexin
 * @create 2017年7月14日 09:53:10
 *
 */
public class AuthenticationEntity {
	
	/**主键*/
	private Integer id;
	
	/**认证名称*/
	private String name;
	
	/**标题*/
	private String title;
	
	/**额度*/
	private String quota;
	
	/**分数*/
	private String score;
	
	/**status02 审核中【待数据推送】 ，status03 已完成*/
	private String status;
	
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

	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
