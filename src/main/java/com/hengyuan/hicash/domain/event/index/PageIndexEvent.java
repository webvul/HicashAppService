package com.hengyuan.hicash.domain.event.index;

import java.util.HashMap;
import java.util.Map;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdatePageIndexException;
import com.hengyuan.hicash.utils.MapAssemForSql;

/**
 * 用户页面索引值更改事件
 * @author Cary.Liu
 * @create 2014-08-15
 *
 */
public class PageIndexEvent {

	
	public void updateUserPageIndex(String userName , String indexValue) throws UpdatePageIndexException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("PAGE_INDEX", indexValue);
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		
		String sql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER, setMap, whereMap);
		
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new UpdatePageIndexException();
		}
		
	}
	
}
