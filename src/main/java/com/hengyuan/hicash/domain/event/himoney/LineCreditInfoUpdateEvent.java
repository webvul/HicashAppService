package com.hengyuan.hicash.domain.event.himoney;


import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.himoney.LineCreditInfoEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 保存用户额度信息Dao
 * 
 * @author xuexin
 * @create 2017年7月17日 10:29:07
 */
public class LineCreditInfoUpdateEvent {

	private static Logger logger = Logger.getLogger(LineCreditInfoUpdateEvent.class);


	/**
	 * 保存用户额度信息
	 * 
	 * @param req
	 * @return
	 * @throws SaveException
	 */
	public void saveLineCreditInfo(LineCreditInfoEntity entity) {

		String sql = "INSERT INTO line_credit_info (total_quota,total_score,app_no)"
				 + "VALUES"
				 +"('"+entity.getTotalQuota()+"','"+entity.getTotalScore()+"','"+entity.getAppNo()+"')";
		RecordUtils.writeAction(logger, null, sql);
		ConnManager.executeUpdate(sql);
	}
	
	
	/**
	 * 修改用户额度信息
	 * 
	 * @param req
	 * @return
	 * @throws SaveException 
	 */
	public void updateLineCreditInfo(LineCreditInfoEntity entity){
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("app_no", entity.getAppNo());
		setMap.put("total_quota", entity.getTotalQuota());
		setMap.put("total_score", entity.getTotalScore());
			
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("id", entity.getId());
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.LINE_CREDIT_INFO, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger,entity.getAppNo(), updateSql);
		try {
			ConnManager.executeUpdate(updateSql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
