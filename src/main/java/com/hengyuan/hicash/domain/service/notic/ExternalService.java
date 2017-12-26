package com.hengyuan.hicash.domain.service.notic;

import com.hengyuan.hicash.dao.dict.BusinessType;
import com.hengyuan.hicash.domain.event.notic.NoticeEvent;
import com.hengyuan.hicash.domain.query.notic.NoticQuery;
import com.hengyuan.hicash.domain.query.notic.NoticeConfigureQuery;
import com.hengyuan.hicash.entity.notic.NoticeConfigure;
import com.hengyuan.hicash.entity.notic.NoticeObject;
import com.hengyuan.hicash.entity.notic.NoticeSendflow;
import com.hengyuan.hicash.entity.notic.NoticeVariable;
import com.hengyuan.hicash.exception.SendMobileMsgException;


/**
 * 发送短信公共类
 * 
 * @author Cary.Liu
 * @createDate 2015-03-24
 *
 */
public class ExternalService{
	
	NoticQuery noticQuery = new NoticQuery();
	
	/**
	 * hicash提交申请 发送短息
	 * @createDate 2015-03-24
	 * @param noticeFlow
	 * @param bussNo
	 * @param noticeVar
	 */
	public void  SuccSendSms(NoticeSendflow noticeFlow,String bussNo, NoticeVariable noticeVar ){
		SendSMS sendSMS = new SendSMS();
		NoticeConfigureQuery noticeConfigureQuery = new NoticeConfigureQuery();
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(bussNo);
//		if (noticeConf == null) {
			// logger.error("找不到短信发送模板，发送短信中止！");
//		}

		noticeObject.setNoticeConf(noticeConf);
		noticeObject.setNoticeVar(noticeVar);
		sendSMS.recordNotice(noticeFlow, noticeObject);
	   }

	
	
	/**
	 * 重置密码
	 * 发送短信验证 如果失败，需要抛出SendMobileMsgException异常
	 * 
	 * @return validateCode 短信验证码
	 */
	public String sendValidateMessages(String mobileNum,String username) 
			throws SendMobileMsgException {
		String validateCode = org.apache.commons.lang.RandomStringUtils.random(
				5, false, true);
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(username);
		noticeFlow.setBussNo(BusinessType.BTPP.toString());
		noticeFlow.setReceiveTelphone(mobileNum);
		noticeFlow.setTitle("重置密码验证");
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigure noticeConf = noticQuery.queryNoticeConfigure(BusinessType.BTPP.toString());
		if (noticeConf == null) {
			System.out.println("找不到模板");
			return null;
		}
		noticeObject.setNoticeConf(noticeConf);
		NoticeVariable noticeVar = new NoticeVariable();
		noticeVar.setUsername(username);
		noticeVar.setMobileValidateCode(validateCode);
		noticeObject.setNoticeVar(noticeVar);
		
		NoticeEvent noticeEvent = new NoticeEvent();
		noticeEvent.recordNotice(noticeFlow,noticeObject);
		//noticeImplService.recordNotice(noticeFlow, noticeObject);
		return validateCode;
	}
	
	
	/**
	 * 忘记密码修改成功，发送短信
	 * @param username
	 * @param password
	 * @param mobile
	 * 
	 */
   public void  SuccSendSms(String username,String mobile,String pssword)
		   throws SendMobileMsgException{
	 
	   	NoticeSendflow noticeFlow = new NoticeSendflow();
	   	noticeFlow.setUsername(username);
		noticeFlow.setBussNo(BusinessType.RSPW.toString());
		noticeFlow.setReceiveTelphone(mobile);
		noticeFlow.setTitle("重置密码成功"); 

		NoticeVariable noticeVar = new NoticeVariable();
		noticeVar.setPassword(pssword);
	   
	   
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigure noticeConf = noticQuery.queryNoticeConfigure(BusinessType.RSPW.toString());
		if (noticeConf == null) {
			System.out.println("找不到模板");
		}
	   
		noticeObject.setNoticeConf(noticeConf);
		noticeObject.setNoticeVar(noticeVar);
		NoticeEvent noticeEvent = new NoticeEvent();
		noticeEvent.recordNotice(noticeFlow,noticeObject);
	   
   }
   
   
   /**
	 * 发送短信验证 如果失败，需要抛出SendMobileMsgException异常
	 * 
	 * @return validateCode 短信验证码
	 */
	public String sendValidateMessage(String mobileNum,String username)
			throws SendMobileMsgException {
		String validateCode = org.apache.commons.lang.RandomStringUtils.random(5, false, true);
//		String validateCode = "111111";
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(username);
		noticeFlow.setBussNo(BusinessType.BTPP.toString());
		noticeFlow.setReceiveTelphone(mobileNum);
		noticeFlow.setTitle("手机绑定验证");
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigure noticeConf = noticQuery.queryNoticeConfigure(BusinessType.BTPP.toString());
		if (noticeConf == null) {
			System.out.println("找不到模板");
			return null;
		}
		noticeObject.setNoticeConf(noticeConf);
		NoticeVariable noticeVar = new NoticeVariable();
		noticeVar.setUsername(username);
		noticeVar.setMobileValidateCode(validateCode);
		noticeObject.setNoticeVar(noticeVar);
		NoticeEvent noticeEvent = new NoticeEvent();
		noticeEvent.recordNotice(noticeFlow,noticeObject);
		return validateCode;
	}
	
	/**
	 * 发送短信验证 如果失败，需要抛出SendMobileMsgException异常
	 * 商户入驻注册验证码
	 * @return validateCode 短信验证码
	 */
	public String smsSupplierCode(String mobileNum)throws SendMobileMsgException {
		
		String bussNo = BusinessType.SHZC.toString();
		
//		String validateCode = org.apache.commons.lang.RandomStringUtils.random(6, false, true);
		String validateCode = "111111";
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(mobileNum);
		noticeFlow.setBussNo(bussNo);
		noticeFlow.setReceiveTelphone(mobileNum);
		noticeFlow.setTitle("商户入驻注册");
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigure noticeConf = noticQuery.queryNoticeConfigure(bussNo);
		if (noticeConf == null) {
			System.out.println("找不到模板");
			return null;
		}
		noticeObject.setNoticeConf(noticeConf);
		NoticeVariable noticeVar = new NoticeVariable();
		noticeVar.setUsername(mobileNum);
		noticeVar.setMobileValidateCode(validateCode);
		noticeObject.setNoticeVar(noticeVar);
		NoticeEvent noticeEvent = new NoticeEvent();
		noticeEvent.recordNotice(noticeFlow,noticeObject);
		return validateCode;
	}
	
	/**
	 * 嗨秒贷申请成功
	 * 发送通知短信 
	 * 如果失败，需要抛出SendMobileMsgException异常
	 */
	public void sendApplyHmdSucc(String mobileNum, String username) {
		
		/* 短信模板代码 */
		String bussNo = BusinessType.HMDA.toString();
		
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(username);
		noticeFlow.setBussNo(bussNo);
		noticeFlow.setReceiveTelphone(mobileNum);
		noticeFlow.setTitle(BusinessType.valueOf(bussNo).getDispValue());

		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigure noticeConf = noticQuery.queryNoticeConfigure(bussNo);
		if (noticeConf == null) {
			System.out.println("找不到模板");
		}
		noticeObject.setNoticeConf(noticeConf);
		NoticeVariable noticeVar = new NoticeVariable();
		noticeVar.setUsername(username);
		noticeObject.setNoticeVar(noticeVar);
		NoticeEvent noticeEvent = new NoticeEvent();
		noticeEvent.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 嗨秒贷申请成功
	 * 发送通知短信 
	 * 如果失败，需要抛出SendMobileMsgException异常
	 */
	public void sendApplyDxzaSucc(String mobileNum, String username)throws SendMobileMsgException {
		
		/* 短信模板代码 */
		String bussNo = BusinessType.DXZA.toString();
		
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(username);
		noticeFlow.setBussNo(bussNo);
		noticeFlow.setReceiveTelphone(mobileNum);
		noticeFlow.setTitle(BusinessType.valueOf(bussNo).getDispValue());

		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigure noticeConf = noticQuery.queryNoticeConfigure(bussNo);
		if (noticeConf == null) {
			System.out.println("找不到模板");
		}
		noticeObject.setNoticeConf(noticeConf);
		NoticeVariable noticeVar = new NoticeVariable();
		noticeVar.setUsername(username);
		noticeObject.setNoticeVar(noticeVar);
		NoticeEvent noticeEvent = new NoticeEvent();
		noticeEvent.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 发送申请通知短信1
	 * 发送通知短信 
	 * 如果失败，需要抛出SendMobileMsgException异常
	 */
	public void sendAppApplyModel1(String mobileNum, String username, String realName, String bussAddr){
		
		/* 短信模板代码 */
		String bussNo = BusinessType.SQDO.toString();
		
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(username);
		noticeFlow.setBussNo(bussNo);
		noticeFlow.setReceiveTelphone(mobileNum);
		noticeFlow.setTitle(BusinessType.valueOf(bussNo).getDispValue());

		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigure noticeConf = noticQuery.queryNoticeConfigure(bussNo);
		if (noticeConf == null) {
			System.out.println("找不到模板");
		}
		noticeObject.setNoticeConf(noticeConf);
		NoticeVariable noticeVar = new NoticeVariable();
		noticeVar.setUsername(username);
		noticeObject.setNoticeVar(noticeVar);
		noticeVar.setRealname(realName);
		noticeVar.setBussAddr(bussAddr);
		NoticeEvent noticeEvent = new NoticeEvent();
		noticeEvent.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 发送申请通知短信2
	 * 发送通知短信 
	 * 如果失败，需要抛出SendMobileMsgException异常
	 */
	public void sendAppApplyModel2(String mobileNum, String username, String realName){
		
		/* 短信模板代码 */
		String bussNo = BusinessType.SQDT.toString();
		
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(username);
		noticeFlow.setBussNo(bussNo);
		noticeFlow.setReceiveTelphone(mobileNum);
		noticeFlow.setTitle(BusinessType.valueOf(bussNo).getDispValue());

		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigure noticeConf = noticQuery.queryNoticeConfigure(bussNo);
		if (noticeConf == null) {
			System.out.println("找不到模板");
		}
		noticeObject.setNoticeConf(noticeConf);
		NoticeVariable noticeVar = new NoticeVariable();
		noticeVar.setUsername(username);
		noticeObject.setNoticeVar(noticeVar);
		noticeVar.setRealname(realName);
		NoticeEvent noticeEvent = new NoticeEvent();
		noticeEvent.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 发送注册成功短信
	 */
	public void sendRegSuccSms(String mobileNum, String username, String realName) {

		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(username);
		noticeFlow.setBussNo(BusinessType.REGN.toString());
		noticeFlow.setReceiveTelphone(mobileNum);
		noticeFlow.setTitle("注册成功");

		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigure noticeConf = noticQuery.queryNoticeConfigure(BusinessType.REGN.toString());
		if (noticeConf == null) {
			System.out.println("找不到模板");
		}
		noticeObject.setNoticeConf(noticeConf);
		NoticeVariable noticeVar = new NoticeVariable();
		noticeVar.setUsername(username);
		noticeVar.setRealname(realName);
//		noticeVar.setMobileValidateCode(validateCode);
		noticeObject.setNoticeVar(noticeVar);
		NoticeEvent noticeEvent = new NoticeEvent();
		noticeEvent.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 发送申请拒绝通知短信
	 */
	public void sendApplyAppFailSms(String mobileNum, String username,String realName) {

		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(username);
		noticeFlow.setBussNo(BusinessType.SQJJ.toString());
		noticeFlow.setReceiveTelphone(mobileNum);
		noticeFlow.setTitle("申请拒绝通知");

		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigure noticeConf = noticQuery.queryNoticeConfigure(BusinessType.SQJJ.toString());
		if (noticeConf == null) {
			System.out.println("找不到模板");
		}
		noticeObject.setNoticeConf(noticeConf);
		NoticeVariable noticeVar = new NoticeVariable();
		noticeVar.setUsername(username);
		noticeVar.setRealname(realName);
//		noticeVar.setMobileValidateCode(validateCode);
		noticeObject.setNoticeVar(noticeVar);
		NoticeEvent noticeEvent = new NoticeEvent();
		noticeEvent.recordNotice(noticeFlow, noticeObject);
	}
   
}
