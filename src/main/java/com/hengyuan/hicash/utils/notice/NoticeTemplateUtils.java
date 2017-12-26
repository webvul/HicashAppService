package com.hengyuan.hicash.utils.notice;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hengyuan.hicash.domain.service.notic.ExternalService;
import com.hengyuan.hicash.entity.notic.NoticeSendflow;
import com.hengyuan.hicash.entity.notic.NoticeVariable;

/**
 * 消息(短信/邮件)通知模板工具类
 * @author Cary.Liu
 * @createDate 2015-03-24
 *
 */
public class NoticeTemplateUtils {

	/**
	 * 发送短信通知业务员用户已经提交申请
	 * @param mobile
	 * @param username
	 * @param noticeVar
	 */
	public void sendSmsToApprove(String mobile,String username,NoticeVariable noticeVar) {

		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(username);
		noticeFlow.setBussNo("SQTZ");
		noticeFlow.setReceiveTelphone(mobile);
//		noticeFlow.setReceiveEmail(email);
		noticeFlow.setTitle("待处理申请");

//		NoticeVariable noticeVar = new NoticeVariable();
		ExternalService externalService = new ExternalService();
		externalService.SuccSendSms(noticeFlow, "SQTZ", noticeVar);

	}
	
	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		NoticeVariable noticeVar = new NoticeVariable();
		noticeVar.setUsername("liuxinyu");
		noticeVar.setBussMobile("18217015991");
		noticeVar.setRealname("刘欣宇");
		noticeVar.setOperateTime(sdf.format(new Date()));
		noticeVar.setProduct("婚庆服务-测试短信");
		new NoticeTemplateUtils().sendSmsToApprove("18217015991", "liuxinyu",noticeVar);
	}
	
}
