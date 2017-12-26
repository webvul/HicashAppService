package com.hengyuan.hicash.domain.event.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CustLetter;
import com.hengyuan.hicash.utils.RecordUtils;


/**
 * 
 * @author Cary.Liu
 * @createDate 2015-03-24
 */
public class CustLetterEvent  {
	
	private static Logger logger = Logger.getLogger(CustLetterEvent.class);
	
	public int addNoticeSendFlow(CustLetter custLetter) {
		String insertSql = "INSERT INTO cust_letter " + "(" + "USERNAME, "
				+ "SEND_USER, " + "LETTER_TITILE, " + "LETTER_CONTENT,"
				+ "STATUS, " + "CREATE_TIME)" + "VALUES" + "(" + "'"
				+ custLetter.getUsername() + "', " + "'"
				+ custLetter.getSendUser() + "', " + "'"
				+ custLetter.getLetterTitile() + "'," + "'"
				+ custLetter.getLetterContent() + "', " + "'"
				+ custLetter.getStatus() + "'," + "'"
				+ custLetter.getCreateTime() + "')";
		RecordUtils.writeAction(logger, null, insertSql);
		return ConnManager.executeUpdate(insertSql);
	}
}
