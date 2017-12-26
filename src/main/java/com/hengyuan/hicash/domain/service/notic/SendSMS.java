package com.hengyuan.hicash.domain.service.notic;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;

import com.hengyuan.hicash.domain.event.notic.NoticeSendFlowEvent;
import com.hengyuan.hicash.domain.event.user.CustLetterEvent;
import com.hengyuan.hicash.entity.notic.NoticeConfigure;
import com.hengyuan.hicash.entity.notic.NoticeObject;
import com.hengyuan.hicash.entity.notic.NoticeSendflow;
import com.hengyuan.hicash.entity.notic.NoticeVariable;
import com.hengyuan.hicash.entity.user.CustLetter;

public class SendSMS {

	public void recordNotice(NoticeSendflow noticeFlow, NoticeObject noticeObj) {
		NoticeSendFlowEvent noticeSendFlowEvent=new NoticeSendFlowEvent();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (noticeFlow == null) {
			return;
		}
		
		noticeFlow.setCreateTime(sdf.format(new Date()));
		
		NoticeConfigure noticeConf = noticeObj.getNoticeConf();
		if (noticeConf.getSmsFlag() == true) {//记录短消息通知
			NoticeSendflow noticeSmsFlow = new NoticeSendflow();
			BeanUtils.copyProperties(noticeFlow, noticeSmsFlow);
			
			noticeSmsFlow.setTemplate(noticeConf.getSmsTemplate());
			noticeSmsFlow.setContent(transferContent(noticeSmsFlow.getTemplate(), noticeObj));
			noticeSmsFlow.setNtype("SMS");
			noticeSmsFlow.setOperator("亨元金融");
			
			if ("1".equals(noticeConf.getSmsSendType())) {
				noticeSmsFlow.setSendStatus("WAIT");
				noticeSmsFlow.setSendFlag("O");//实时
				noticeSmsFlow.setSendTime(sdf.format(new Date()));
				noticeSendFlowEvent.addNoticeSendFlow(noticeSmsFlow);
			} else {
				noticeSmsFlow.setSendStatus("WAIT");
				noticeSmsFlow.setSendFlag("B");//批量
				noticeSendFlowEvent.addNoticeSendFlow(noticeSmsFlow);
			}
		}
		
		if (noticeConf.getEmailFlag() == true) {//记录邮件通知
			NoticeSendflow noticeMailFlow = new NoticeSendflow();
			BeanUtils.copyProperties(noticeFlow, noticeMailFlow);
			
			noticeMailFlow.setTemplate(noticeConf.getEmailTemplate());
			noticeMailFlow.setContent(transferContent(noticeMailFlow.getTemplate(), noticeObj));
			noticeMailFlow.setNtype("MAIL");
			noticeMailFlow.setOperator("亨元金融");
			
			if ("1".equals(noticeConf.getEmailSendType())) {
				// 如果是实时发送
//				new SendMailThread(noticeMailFlow).run();
				noticeMailFlow.setSendStatus("WAIT");
				noticeMailFlow.setSendFlag("O");//实时
				noticeMailFlow.setSendTime(sdf.format(new Date()));
				noticeSendFlowEvent.addNoticeSendFlow(noticeMailFlow);
				
				Thread thread = new Thread(new SendMailThread(noticeMailFlow));
				thread.start();
			} else {
				noticeMailFlow.setSendStatus("WAIT");
				noticeMailFlow.setSendFlag("B");//批量
				
				noticeSendFlowEvent.addNoticeSendFlow(noticeMailFlow);
			}

		}
		CustLetterEvent custLetterEvent=new CustLetterEvent();
		if (noticeConf.getStationLetterFlag() == true) {//发送站内信
			CustLetter stationLetter = new CustLetter();
			stationLetter.setUsername(noticeFlow.getUsername());
			stationLetter.setSendUser("5i5dai");
			stationLetter.setLetterTitile(noticeFlow.getTitle());
			stationLetter.setLetterContent(transferContent(noticeConf.getStationLetterTemplate(), noticeObj));
			stationLetter.setStatus(false);
			stationLetter.setCreateTime(sdf.format(new Date()));
			
			custLetterEvent.addNoticeSendFlow(stationLetter);
		}
	}
	
	/**
	 * 转换发送模板内容，将变量值替换为对应的值
	 * 
	 * @param content
	 * @param noticeObj
	 * @return
	 */
	private String transferContent(String content, NoticeObject noticeObj) {
		Charset charset = Charset.forName("UTF-8");
		
		Pattern pattern = Pattern.compile("\\$\\{(\\w*)\\.(\\w*)\\}", Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(charset.decode(ByteBuffer.wrap(content.getBytes())));
		Map<Var, String> result = new HashMap<Var, String>();
		while (matcher.find()) {
			Var var = new Var();
			var.table = matcher.group(1);
			var.column = matcher.group(2);
			result.put(var, "");
		}
		
		Map<String, Object> dataMap = getVariableMap(noticeObj.getNoticeVar());
		
		for (Entry<Var, String> entry : result.entrySet()) {
			content = content.replaceAll("\\$\\{" + entry.getKey().table + "\\."
					+ entry.getKey().column + "\\}", 
					getVarValue(entry.getKey().table, entry.getKey().column, dataMap));
		}
		
		return content;
	}
	
	class Var {
		String table;
		String column;
	}
	/**
	 * 转换变量类成Map
	 * 
	 * @param noticeVar
	 * @return
	 */
	private Map<String, Object> getVariableMap(NoticeVariable noticeVar) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("username", noticeVar.getUsername());
		dataMap.put("validateUrl", noticeVar.getValidateUrl());
		dataMap.put("mobileValidateCode", noticeVar.getMobileValidateCode());
		
		dataMap.put("email", noticeVar.getEmail());
		dataMap.put("emailAccessUrl", noticeVar.getEmailAccessUrl());
		dataMap.put("publishLoanUrl", noticeVar.getPublishLoanUrl());
		dataMap.put("investUrl", noticeVar.getInvestUrl());
		dataMap.put("operateTime", noticeVar.getOperateTime());
		
		dataMap.put("realname", noticeVar.getRealname());
		dataMap.put("loanDetailUrl", noticeVar.getLoanDetailUrl());
		dataMap.put("uploadMaterialslUrl", noticeVar.getUploadMaterialslUrl());
		dataMap.put("telphone", noticeVar.getTelphone());
		dataMap.put("loanTitle", noticeVar.getLoanTitle());
		dataMap.put("percent", noticeVar.getPercent());
		dataMap.put("operateAmt", noticeVar.getOperateAmt());
		dataMap.put("reason", noticeVar.getReason());
		dataMap.put("effectTimes", noticeVar.getEffectTimes());
		dataMap.put("password", noticeVar.getPassword());
		dataMap.put("product", noticeVar.getProduct());
		dataMap.put("bussName", noticeVar.getBussName());
		dataMap.put("bussAddr", noticeVar.getBussAddr());
		dataMap.put("bussMobile", noticeVar.getBussMobile());
		dataMap.put("repayedAmt", noticeVar.getRepayedAmt());
		
		
		
		
		
		return dataMap;
	}
	/**
	 * 获取对应变量的值
	 * 
	 * @param key
	 * @param value
	 * @param dataMap
	 * @return
	 */
	private String getVarValue(String key, String value, Map<String, Object> dataMap) {
		String str = "";
		if ("noticeVariable".equals(key) && null != dataMap.get(value)) {
			str = dataMap.get(value).toString();
		}

		return str;
	}
}
