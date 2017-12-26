package com.hengyuan.hicash.parameters.response.notic;

import java.util.Date;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 公告返回参数
 * 
 * @author Eric.shi
 * @create date 2014-07-25
 * 
 */
public class NoticeInfoResp extends ParmResponse {

	/** 返回代码 */
	private String resultCode;

	private String id;

	/** 标题 */
	private String title;

	/** 详情 */
	private String content;

	/** 显示标志 */
	private Boolean enabled;

	/** 缩略图 */
	private String smallImg;

	/** 展示大图 */
	private String showImg;

	private String type;

	private String noticeFrom;
	/** 发布日期 */
	private Date noticeTime;

	private String createUser;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getSmallImg() {
		return smallImg;
	}

	public void setSmallImg(String smallImg) {
		this.smallImg = smallImg;
	}

	public String getShowImg() {
		return showImg;
	}

	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNoticeFrom() {
		return noticeFrom;
	}

	public void setNoticeFrom(String noticeFrom) {
		this.noticeFrom = noticeFrom;
	}

	public Date getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

}
