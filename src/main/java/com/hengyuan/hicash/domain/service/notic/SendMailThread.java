package com.hengyuan.hicash.domain.service.notic;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.domain.event.notic.NoticeSendFlowEvent;
import com.hengyuan.hicash.entity.notic.NoticeSendflow;
import com.hengyuan.hicash.exception.SendMailException;

public class SendMailThread implements Runnable {
	private NoticeSendflow noticeObj;
	private static Logger logger = Logger.getLogger(SendMailThread.class);

	public SendMailThread(NoticeSendflow noticeObj) {
		this.noticeObj = noticeObj;
	}

	public void run() {
		String status = sendMail(this.noticeObj);
		
		NoticeSendFlowEvent noticeSendFlowEvent=new NoticeSendFlowEvent();
		
		noticeSendFlowEvent.updateSendFlow(status, noticeObj.getId());
	}
	
	/**
	 * 发送邮件
	 * @param 	noticeObj
	 * @return	发送结果
	 */
	private String sendMail(NoticeSendflow noticeObj) {
		try {
			//实现发送邮件
			EmailSendClient.sendMail(noticeObj.getReceiveEmail(), noticeObj.getTitle(), 
					noticeObj.getContent(), null);
		} catch (SendMailException e) {
			// TODO: handle exception
			logger.error("username:" + noticeObj.getUsername() + "bussNo:" + noticeObj.getBussNo() 
					+ "，发送邮件出现异常，", e);
			return "FAIL";
		}
		
		return "SUCC";
	}


}
