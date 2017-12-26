package com.hengyuan.hicash.entity.param;

/**
 * 学校数据存放类
 * 
 * @author Eric
 * @create date 2014-08-14
 * 
 */
public class SchoolEntity {
	
	/** id */
	private Integer id;
	
	/** 学校名称 */
	private  String shcoolName;
	
	/** 学校代码 */
	private  String shcoolCode;
	
	/** 学校所在省份 */
	private  String province;
	
	/** 学校所在城市 */
	private  String city;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShcoolName() {
		return shcoolName;
	}

	public void setShcoolName(String shcoolName) {
		this.shcoolName = shcoolName;
	}

	public String getShcoolCode() {
		return shcoolCode;
	}

	public void setShcoolCode(String shcoolCode) {
		this.shcoolCode = shcoolCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
