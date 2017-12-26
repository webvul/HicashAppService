package com.hengyuan.hicash.domain.event.notic;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.notic.NoticeSendflow;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * @author Cary.Liu
 * @createDate 2015-03-24
 *
 */
public class NoticeSendFlowEvent {
	private static Logger logger = Logger.getLogger(NoticeSendFlowEvent.class);
	public int addNoticeSendFlow(NoticeSendflow noticeSendflow) {
		String insertSql = "INSERT INTO notice_sendflow " + "( " + "USERNAME, "
				+ "BUSS_NO," + "TITLE, " + "TEMPLATE, " + "CONTENT, "
				+ "NTYPE, " + "RECEIVE_TELPHONE, " + "RECEIVE_EMAIL, "
				+ "SEND_TIME, " + "SEND_STATUS," + "OPERATOR, " + "SEND_FLAG, "
				+ "CREATE_TIME" + ")" + "VALUES" + "( " + "'"
				+ noticeSendflow.getUsername()
				+ "', "
				+ "'"
				+ noticeSendflow.getBussNo()
				+ "', "
				+ "'"
				+ noticeSendflow.getTitle()
				+ "', "
				+ "'"
				+ noticeSendflow.getTemplate()
				+ "', "
				+ "'"
				+ noticeSendflow.getContent()
				+ "', "
				+ "'"
				+ noticeSendflow.getNtype()
				+ "', "
				+ "'"
				+ noticeSendflow.getReceiveTelphone()
				+ "', "
				+ "'"
				+ noticeSendflow.getReceiveEmail()
				+ "', "
				+ "'"
				+ noticeSendflow.getSendTime()
				+ "', "
				+ "'"
				+ noticeSendflow.getSendStatus()
				+ "', "
				+ "'"
				+ noticeSendflow.getOperator()
				+ "', "
				+ "'"
				+ noticeSendflow.getSendFlag()
				+ "', "
				+ "'"
				+ noticeSendflow.getCreateTime() + "'" + ")";
		RecordUtils.writeAction(logger, null, insertSql);
		return ConnManager.executeUpdate(insertSql);
	}
	
	public int updateSendFlow(String status,String id){
		
		String sql="update notice_sendflow set SEND_STATUS ='"
				+ status + "' where id='"+id+"'";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeUpdate(sql);
		
	}
}
