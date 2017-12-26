package com.hengyuan.hicash.domain.event.apply;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdateTempAppException;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 临时申请表 event
 * @author Cary.Liu
 * @createDate 2015-05-26
 *
 */
public class DreportStatusEvent {
	
	private static Logger logger = Logger.getLogger(DreportStatusEvent.class);


	/**
	 * 保存认证状态
	 * @param input
	 * @throws UpdateTempAppException
	 */
	public void createTempApp(HashMap<String, Object> input) throws UpdateTempAppException{
		
		String sql = MapAssemForSql.getInsertSql(TableConsts.D_REPORT_STATUS, input);
		
		RecordUtils.writeAction(logger, null, sql);
		
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new UpdateTempAppException();
		}
	}

	
	public void updateReport(String appNo) throws UpdateTempAppException{
		
		String sql = "UPDATE D_REPORT_STATUS SET confirm_date =now() WHERE temp_appno = '"+ appNo +"'";
		
		RecordUtils.writeAction(logger, null, sql);
		
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new UpdateTempAppException();
		}
		
	}
	
	
	
	
}
