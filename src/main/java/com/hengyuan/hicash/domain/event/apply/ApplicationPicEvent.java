package com.hengyuan.hicash.domain.event.apply;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.PicParam;
import com.hengyuan.hicash.exception.UpdateAppPayException;
import com.hengyuan.hicash.exception.UpdateApplicationPicException;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 申请件图片更新
 * 
 * @author Cary.Liu
 * @create 2014-10-16
 *
 */
public class ApplicationPicEvent {
	
	private static Logger logger = Logger.getLogger(ApplicationPicEvent.class);

	public void saveAppPic(PicParam entity,String userName) throws UpdateApplicationPicException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("app_id", entity.getAppNo());
		setMap.put("pic_name", entity.getPicName());
		setMap.put("pic_path", entity.getPicPath());
		setMap.put("pic_type", entity.getPicType());	
		setMap.put("thum_path", entity.getThumPath());
		setMap.put("pic_caption", entity.getCaption());
		setMap.put("create_user", userName);
		setMap.put("create_date", DateUtils.getDateStr(new Date()));
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.APP_PIC, setMap);
		
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateApplicationPicException();
		}
	}
	
	
	/**
	 * 更新申请件图片
	 * @param applicationPay
	 * @throws UpdateApplicationPicException 
	 */
	public void updateAppPic(Map<String, Object> appPicMap, Map<String, Object> whereMap) throws UpdateApplicationPicException  {
		String createSql = MapAssemForSql.getUpdateSql(TableConsts.APP_PIC, appPicMap, whereMap);
		RecordUtils.writeAction(logger, null, createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new UpdateApplicationPicException();
		}
	}
	
}
