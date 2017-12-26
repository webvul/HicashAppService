package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdateInputAppException;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 申请件信息更新Dao
 * @author Lizc
 *
 */
public class InputAppEvent {

	private static Logger logger = Logger.getLogger(InputAppEvent.class);
	
	/**
	 * 修改学生类型
	 * @param studentType
	 * @throws UpdateInputAppException  
	 */
	public void updateStudentType(String appNo, String studentType)
			 throws UpdateInputAppException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("STUDENT_TYPE", studentType);
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("applicationNo", appNo);
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.INPUT_APP, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, appNo, updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateInputAppException();
		}
		
	}
	public void updateInputAppInfo(String appNo,Map<String, Object> setMap)
			 throws UpdateInputAppException {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("applicationNo", appNo);
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.INPUT_APP, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, appNo, updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateInputAppException();
		}
		
	}
}
