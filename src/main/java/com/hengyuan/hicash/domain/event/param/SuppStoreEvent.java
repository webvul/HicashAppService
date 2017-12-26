package com.hengyuan.hicash.domain.event.param;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.mer.SupStoreEntity;
import com.hengyuan.hicash.exception.UpdateSupStoreException;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class SuppStoreEvent {

	private static Logger logger = Logger.getLogger(SuppStoreEvent.class);
	
	public void saveStore(SupStoreEntity entity) throws UpdateSupStoreException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("frameno", StringUtils.getString(entity.getFrameno()));
		setMap.put("sup_no", StringUtils.getString(entity.getSupNo()));
		setMap.put("sup_name", StringUtils.getString(entity.getSupName()));
		setMap.put("unit_name", StringUtils.getString(entity.getUnitName()));
		setMap.put("store_no", StringUtils.getString(entity.getStoreNo()));
		setMap.put("store_name", StringUtils.getString(entity.getStoreName()));
		setMap.put("city", StringUtils.getString(entity.getCity()));
		setMap.put("store_adree", StringUtils.getString(entity.getStoreAdree()));
		setMap.put("top_path", StringUtils.getString(entity.getTopPath()));
		setMap.put("next_path", StringUtils.getString(entity.getNextPath()));
		setMap.put("road_no", StringUtils.getString(entity.getRoadNo()));
		setMap.put("operate_power", StringUtils.getString(entity.getOperatePower()));
		setMap.put("operate_time", StringUtils.getString(entity.getOperateTime()));
		setMap.put("legal_name", StringUtils.getString(entity.getLegalName()));
		setMap.put("unit_phone", StringUtils.getString(entity.getUnitPhone()));
		setMap.put("create_time", DateUtils.getDateToStr(new Date()));
		setMap.put("status", StringUtils.getString(entity.getStatus()));
		setMap.put("PIC_PATH1", StringUtils.getString(entity.getStorePicUrl1()));
		if(!StringUtils.isEmpty(entity.getStorePicUrl2())){
			setMap.put("PIC_PATH2", StringUtils.getString(entity.getStorePicUrl2()));
		}
		if(!StringUtils.isEmpty(entity.getStorePicUrl3())){
			setMap.put("PIC_PATH3", StringUtils.getString(entity.getStorePicUrl3()));
		}
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.D_SUP_STORE, setMap);
		
		RecordUtils.writeAction(logger, null, updateSql);
		
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateSupStoreException();
		}
		
	}
	
}
