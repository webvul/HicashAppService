package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.hengyuan.hicash.dao.AbstractDBO;
import com.hengyuan.hicash.dao.collection.ConnRedis;
import com.hengyuan.hicash.entity.user.WxbagEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 微信红包查询
 * @author tan
 *
 */
public class WxBagQuery extends AbstractDBO<WxbagEntity>{
  
	@Override
	public WxbagEntity mapping(ResultSet rs) throws SQLException {
		WxbagEntity wxbagEntity=new WxbagEntity();
	
		if (rs != null) {
			wxbagEntity.setBagId(rs.getInt("bag_id"));
			wxbagEntity.setcTime(StringUtils.valueOf(rs.getObject("c_time")));
			wxbagEntity.setUserId(StringUtils.valueOf(rs.getObject("user_id")));
			wxbagEntity.setUserOrdersn(StringUtils.valueOf(rs.getObject("user_ordersn")));
			wxbagEntity.setEvenKey(StringUtils.valueOf(rs.getObject("event_key")));
			wxbagEntity.setEventGroup(StringUtils.valueOf(rs.getObject("event_group")));

		}else {
			
			wxbagEntity=null;
			
		}
		return wxbagEntity;
	}
			
	/**
	 * 
	 * 根据用户订单号查询红包
	 * @param userOrdersn
	 * @return
	 */
	public WxbagEntity queryApplicationPayById(String userOrdersn){
		
		String sql="select bag_id,user_id,user_ordersn,event_group,event_key,c_time from wx_bag where user_ordersn='"+userOrdersn+"'";
		
		RecordUtils.writeAction(logger, userOrdersn, sql);
		return ConnRedis.singleQuery(sql, this);
		
	}



	
}
