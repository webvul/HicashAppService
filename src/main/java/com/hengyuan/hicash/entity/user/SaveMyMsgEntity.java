package com.hengyuan.hicash.entity.user;

public class SaveMyMsgEntity {
	
	/** 行业代码*/
	private String industryCode;
	
	/** 用户名*/
	private String username;

	/** 是否已读:1/已读,0/未读 */
	private String is_read;
	
	/** 结果 */
	private String status;
	
	/** 消息Code : 司机认证/SJEZ  提额审核/TESH  提现审核/TXSH*/
	private String code;
	
	/** 手机号 */
	private String mobile;
	
	/** 流身份证号 */
	private String id_no;
	
	/** 姓名 */
	private String name;
	
	/** 流水号 */
	private String appNo;
	
	/** 标题 */
	private String title;

	/** 内容 */
	private String content;
	
	/** 功能 */
	private String operate;

	/** 消息类型 */
	private String type;
	
	/** 创建时间 */
	private String create_time;

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIs_read() {
		return is_read;
	}

	public void setIs_read(String is_read) {
		this.is_read = is_read;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getId_no() {
		return id_no;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
