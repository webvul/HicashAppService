package com.hengyuan.hicash.domain.service.notic;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import com.hengyuan.hicash.dao.dict.BusinessType;
import com.hengyuan.hicash.domain.query.notic.NoticeConfigureQuery;
import com.hengyuan.hicash.entity.notic.NoticeConfigure;
import com.hengyuan.hicash.entity.notic.NoticeObject;
import com.hengyuan.hicash.entity.notic.NoticeSendflow;
import com.hengyuan.hicash.entity.notic.NoticeVariable;


public class SendNoticeUtils {
	private static Logger logger = Logger.getLogger(SendNoticeUtils.class);
	private static final Properties props = new Properties();
	static {
		try {
			props.load(new ClassPathResource("properties/mail.properties").getInputStream());
		} catch (IOException e) {
			logger.fatal("邮件服务初始化失败", e);
		}
	}
	
	/**
	 * 发送借款进度通知
	 * 
	 * @param SendSMS
	 * @param emailAddress
	 * @param noticeVar
	 */
	public static void sendLoanProcessNotice(SendSMS sendSMS, NoticeVariable noticeVar) {
		String urlRoot = props.getProperty("url.root");
		
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("恭喜您，您的借款“" + noticeVar.getLoanTitle() + "”已经完成了" + noticeVar.getPercent() + "！");
		noticeFlow.setBussNo(BusinessType.LPNT.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
	
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(BusinessType.LPNT.toString());
		if (noticeConf == null) {
			logger.error("找不到借款进度通知发送模板，发送借款进度通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		noticeVar.setLoanDetailUrl(urlRoot + noticeVar.getLoanDetailUrl());
		noticeVar.setUploadMaterialslUrl(urlRoot + noticeVar.getUploadMaterialslUrl());
		noticeObject.setNoticeVar(noticeVar);
		sendSMS.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 发送借款满标通知
	 * 
	 * @param SendSMS
	 * @param noticeVar
	 */
	public static void sendLoanFullScaleNotice(SendSMS SendSMS, NoticeVariable noticeVar) {
		String urlRoot = props.getProperty("url.root");
		
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("恭喜您，您的借款“" + noticeVar.getLoanTitle() + "”已经满标，请上传相应材料！");
		noticeFlow.setBussNo(BusinessType.LFSL.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());

		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
		
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(BusinessType.LFSL.toString());
		if (noticeConf == null) {
			logger.error("找不到借款满标通知发送模板，发送借款满标通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		noticeVar.setLoanDetailUrl(urlRoot + noticeVar.getLoanDetailUrl());
		noticeVar.setUploadMaterialslUrl(urlRoot + noticeVar.getUploadMaterialslUrl());
		noticeObject.setNoticeVar(noticeVar);
		SendSMS.recordNotice(noticeFlow, noticeObject);
	}

	/**
	 * 发送撤回借款通知
	 * 
	 * @param SendSMS
	 * @param noticeVar
	 */
	public static void sendBackLoanNotice(SendSMS SendSMS, NoticeVariable noticeVar) {
		String urlRoot = props.getProperty("url.root");
		
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("申请取消通知");
		noticeFlow.setBussNo(BusinessType.RVNT.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());

		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
		
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(BusinessType.RVNT.toString());
		if (noticeConf == null) {
			logger.error("找不到撤回借款通知发送模板，发送撤回借款通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		noticeVar.setLoanDetailUrl(urlRoot + noticeVar.getLoanDetailUrl());
		noticeObject.setNoticeVar(noticeVar);
		noticeVar.setProduct(noticeVar.getLoanTitle());
		SendSMS.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 发送账户提现通知
	 * 
	 * @param SendSMS
	 * @param noticeVar
	 */
	public static void sendToCashNotice(SendSMS SendSMS, NoticeVariable noticeVar) {
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("账户提现通知");
		noticeFlow.setBussNo(BusinessType.RCMN.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
		
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(BusinessType.RCMN.toString());
		if (noticeConf == null) {
			logger.error("找不到账户提现通知发送模板，发送账户提现通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		noticeObject.setNoticeVar(noticeVar);
		SendSMS.recordNotice(noticeFlow, noticeObject);
		
	}
	
	/**
	 * 发送身份认证通知
	 * 
	 * @param SendSMS
	 * @param noticeVar
	 */
	public static void sendCertyRzNotice(SendSMS SendSMS, NoticeVariable noticeVar) {
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("身份认证通知-亨元金融");
		noticeFlow.setBussNo(BusinessType.IDNT.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
		
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(BusinessType.IDNT.toString());
		if (noticeConf == null) {
			logger.error("找不到身份认证通知发送模板，发送身份认证通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		noticeObject.setNoticeVar(noticeVar);
		SendSMS.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 发送借款成功通知
	 * 
	 * @param SendSMS
	 * @param noticeVar
	 */
	public static void sendLoanSuccNotice(SendSMS SendSMS, NoticeVariable noticeVar) {
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("恭喜您，您的借款[" + noticeVar.getLoanTitle() + "]借款成功");
		noticeFlow.setBussNo(BusinessType.NAPR.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
		
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(BusinessType.NAPR.toString());
		if (noticeConf == null) {
			logger.error("找不到借款成功通知发送模板，发送借款成功通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		String urlRoot = props.getProperty("url.root");
		noticeVar.setLoanDetailUrl(urlRoot + noticeVar.getLoanDetailUrl());
		noticeVar.setValidateUrl(urlRoot + noticeVar.getValidateUrl());
		noticeObject.setNoticeVar(noticeVar);
		SendSMS.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 发送借款失败通知
	 * 
	 * @param SendSMS
	 * @param noticeVar
	 */
	public static void sendLoanFailNotice(SendSMS SendSMS, NoticeVariable noticeVar) {
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("您的借款[" + noticeVar.getLoanTitle() + "]没有通过审核。");
		noticeFlow.setBussNo(BusinessType.NAJR.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
		
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(BusinessType.NAJR.toString());
		if (noticeConf == null) {
			logger.error("找不到借款失败通知发送模板，发送借款失败通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		String urlRoot = props.getProperty("url.root");
		noticeVar.setLoanDetailUrl(urlRoot + noticeVar.getLoanDetailUrl());
		noticeObject.setNoticeVar(noticeVar);
		SendSMS.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 发送借款流标通知
	 * 
	 * @param SendSMS
	 * @param noticeVar
	 */
	public static void sendLoanLBNotice(SendSMS SendSMS, NoticeVariable noticeVar) {
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("您的借款[" + noticeVar.getLoanTitle() + "]已经流标。");
		noticeFlow.setBussNo(BusinessType.FWNT.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
		
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(BusinessType.FWNT.toString());
		if (noticeConf == null) {
			logger.error("找不到借款流标通知发送模板，发送借款流标通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		String urlRoot = props.getProperty("url.root");
		noticeVar.setLoanDetailUrl(urlRoot + noticeVar.getLoanDetailUrl());
		noticeObject.setNoticeVar(noticeVar);
		SendSMS.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 发送账户提现失败通知
	 * 
	 * @param SendSMS
	 * @param noticeVar
	 */
	public static void sendCashFailNotice(SendSMS SendSMS, NoticeVariable noticeVar) {
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("账户提现失败通知");
		noticeFlow.setBussNo(BusinessType.CNTF.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
		
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(BusinessType.CNTF.toString());
		if (noticeConf == null) {
			logger.error("找不到账户提现失败通知发送模板，发送账户提现失败通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		noticeObject.setNoticeVar(noticeVar);
		SendSMS.recordNotice(noticeFlow, noticeObject);
		
	}
	
	/**
	 * 发送账户提现成功通知
	 * 
	 * @param SendSMS
	 * @param noticeVar
	 */
	public static void sendCashSuccNotice(SendSMS SendSMS, NoticeVariable noticeVar) {
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("账户提现成功通知");
		noticeFlow.setBussNo(BusinessType.CNTS.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
		
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(BusinessType.CNTS.toString());
		if (noticeConf == null) {
			logger.error("找不到账户提现成功通知发送模板，发送账户提现成功通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		noticeObject.setNoticeVar(noticeVar);
		SendSMS.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 发送贷款第零期费用全部支付完成通知
	 * @param SendSMS
	 * @param noticeVar
	 */
	public static void sendPreChagrgeSuccNotice(SendSMS SendSMS, NoticeVariable noticeVar) {
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("第零期费用全部支付完成");
		noticeFlow.setBussNo(BusinessType.PANO.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
		
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(BusinessType.PANO.toString());
		if (noticeConf == null) {
			logger.error("找不到第零期费用全部支付完成通知发送模板，发送第零期费用全部支付完成通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		noticeObject.setNoticeVar(noticeVar);
		SendSMS.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 发送贷款第一期之后，含第一期支付通知
	 * @param SendSMS
	 * @param noticeVar
	 */
	public static void sendPayBackSuccNotice(SendSMS SendSMS, NoticeVariable noticeVar, Boolean fullPayed) {
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("借款还款");
		noticeFlow.setBussNo(fullPayed ? BusinessType.PSNO.toString() : BusinessType.PPSN.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
		
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(fullPayed ? BusinessType.PSNO.toString() : BusinessType.PPSN.toString());
		if (noticeConf == null) {
			logger.error("找不到借款还款通知发送模板，发送借款还款通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		noticeObject.setNoticeVar(noticeVar);
		SendSMS.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 充值成功通知
	 * @param SendSMS
	 * @param noticeVar
	 */
	public static void sendRechargeSuccNotice(SendSMS SendSMS, NoticeVariable noticeVar) {
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("充值成功");
		noticeFlow.setBussNo(BusinessType.RCSO.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
		
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure( BusinessType.RCSO.toString());
		if (noticeConf == null) {
			logger.error("找不到充值成功通知发送模板，发送充值成功通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		noticeObject.setNoticeVar(noticeVar);
		SendSMS.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 发送还款提醒通知
	 * @param SendSMS
	 * @param noticeVar
	 */
	public static void sendPayBackNotice(SendSMS SendSMS, NoticeVariable noticeVar) {
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("还款提醒");
		noticeFlow.setBussNo(BusinessType.PMMY.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
		
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(BusinessType.PMMY.toString());
		if (noticeConf == null) {
			logger.error("找不到还款提醒通知发送模板，发送还款提醒通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		noticeObject.setNoticeVar(noticeVar);
		SendSMS.recordNotice(noticeFlow, noticeObject);
	}
	
	/**
	 * 发送还款提醒通知
	 * @param SendSMS
	 * @param noticeVar
	 */
	public static void sendOverDueNotice(SendSMS SendSMS, NoticeVariable noticeVar) {
		NoticeSendflow noticeFlow = new NoticeSendflow();
		noticeFlow.setUsername(noticeVar.getUsername());
		noticeFlow.setTitle("逾期提醒");
		noticeFlow.setBussNo(BusinessType.MONO.toString());
		noticeFlow.setReceiveEmail(noticeVar.getEmail());
		noticeFlow.setReceiveTelphone(noticeVar.getTelphone());
		
		NoticeObject noticeObject = new NoticeObject();
		NoticeConfigureQuery noticeConfigureQuery=new NoticeConfigureQuery();
		
		NoticeConfigure noticeConf = noticeConfigureQuery.qryNoticeConfigure(BusinessType.MONO.toString());
		if (noticeConf == null) {
			logger.error("找不到逾期提醒通知发送模板，发送逾期提醒通知中止！");
			return;
		}
		noticeObject.setNoticeConf(noticeConf);
		
		noticeObject.setNoticeVar(noticeVar);
		SendSMS.recordNotice(noticeFlow, noticeObject);
	}
}
