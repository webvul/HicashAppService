package com.hengyuan.hicash.domain.event.notic;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.notic.NoticeConfigure;
import com.hengyuan.hicash.entity.notic.NoticeObject;
import com.hengyuan.hicash.entity.notic.NoticeSendflow;
import com.hengyuan.hicash.entity.notic.NoticeVariable;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 发送短信公共类
 * 
 * @author Cary.Liu
 * @createDate 2015-07-22
 */
public class NoticeEvent  {
	
	private static Logger logger = Logger.getLogger(NoticeEvent.class);
	
	/**
	 * 生成发送短信数据
	 * 
	 * @return
	 */
	public int recordNotice(NoticeSendflow noticeFlow,NoticeObject noticeObject) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		noticeFlow.setCreateTime(sdf.format(new Date()));
		
		NoticeConfigure noticeConf = noticeObject.getNoticeConf();
		//记录短消息通知
		if (noticeConf.getSmsFlag() == true) {
			
			noticeFlow.setTemplate(noticeConf.getSmsTemplate());
			noticeFlow.setContent(transferContent(noticeFlow.getTemplate(), noticeObject));
			noticeFlow.setNtype("SMS");
			noticeFlow.setOperator("亨元金融");

			if ("1".equals(noticeConf.getSmsSendType())) {
				noticeFlow.setSendStatus("WAIT");
				noticeFlow.setSendFlag("O");//实时
				noticeFlow.setSendTime(sdf.format(new Date()));
				
			} else {
				noticeFlow.setSendStatus("WAIT");
				noticeFlow.setSendFlag("B");//批量
				//entityManager.persist(noticeSmsFlow);
			}
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("USERNAME", noticeFlow.getUsername());
		dataMap.put("BUSS_NO", noticeFlow.getBussNo());
		dataMap.put("TITLE", noticeFlow.getTitle());
		dataMap.put("TEMPLATE", noticeFlow.getTemplate());
		dataMap.put("CONTENT", noticeFlow.getContent());
		dataMap.put("NTYPE", noticeFlow.getNtype());
		dataMap.put("RECEIVE_TELPHONE", noticeFlow.getReceiveTelphone());
		dataMap.put("SEND_STATUS", noticeFlow.getSendStatus());
		dataMap.put("SEND_TIME", DateUtils.getDateStr(new Date()));
		dataMap.put("OPERATOR", noticeFlow.getOperator());
		dataMap.put("SEND_FLAG", noticeFlow.getSendFlag());
		dataMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		
		String sql= MapAssemForSql.getInsertSql(TableConsts.NOTICE_SENDFLOW, dataMap);
		//记录日志
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeUpdate(sql);
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
	
	class Var {
		String table;
		String column;
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
