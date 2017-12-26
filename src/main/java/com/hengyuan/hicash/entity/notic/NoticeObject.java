package com.hengyuan.hicash.entity.notic;

/**
 * 短信信息
 * 
 * @author Cary.Liu
 * @createDate 2015-03-24
 *
 */
public class NoticeObject {

	private NoticeConfigure noticeConf;
	private NoticeVariable noticeVar;
	public NoticeConfigure getNoticeConf() {
		return noticeConf;
	}
	public void setNoticeConf(NoticeConfigure noticeConf) {
		this.noticeConf = noticeConf;
	}
	public NoticeVariable getNoticeVar() {
		return noticeVar;
	}
	public void setNoticeVar(NoticeVariable noticeVar) {
		this.noticeVar = noticeVar;
	}
	
	
}
