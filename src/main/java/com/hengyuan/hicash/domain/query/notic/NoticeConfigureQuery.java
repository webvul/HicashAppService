package com.hengyuan.hicash.domain.query.notic;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.notic.NoticeConfigure;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * @author Cary.Liu
 * @createDate 2015-03-24
 */
public class NoticeConfigureQuery extends AbstractDAO<NoticeConfigure> {
	
	private static Logger logger = Logger.getLogger(NoticeConfigureQuery.class);

	@Override
	public NoticeConfigure mapping(ResultSet rs) throws SQLException {
		NoticeConfigure noticeConfigure = new NoticeConfigure();
		if (rs != null ) {
			
			noticeConfigure.setSmsFlag((rs.getString("sms_flag").equals("1")?true:false));
			noticeConfigure.setSmsTemplate(rs.getString("sms_template"));
			noticeConfigure.setSmsSendType(rs.getString("sms_send_type"));
			
			noticeConfigure.setEmailFlag((rs.getString("email_flag").equals("1")?true:false));
			noticeConfigure.setEmailTemplate(rs.getString("email_template"));
			noticeConfigure.setEmailSendType(rs.getString("email_send_type"));
			
			noticeConfigure.setStationLetterFlag((rs.getString("station_letter_flag").equals("1")?true:false));
			
		}
		return noticeConfigure;
	}
	
	/**
	 * 根据bussNo查询短信模板
	 */
	public NoticeConfigure qryNoticeConfigure(String bussId){
		
		String sql="select sms_flag,sms_template,sms_send_type,email_flag,email_template,email_send_type,station_letter_flag from notice_configure WHERE buss_id='"+bussId+"'";
		RecordUtils.writeAction(logger, bussId, sql);
		
		return ConnManager.singleQuery(sql, this);
		
	}

}
