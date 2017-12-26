package com.hengyuan.hicash.domain.query.user;



import com.hengyuan.hicash.dao.collection.ConnRedis;
import com.hengyuan.hicash.entity.user.WxbagEntity;

/**
 * 
 * @author tan
 *
 */
public class WxBagAdd {
	
	/**
	 * 添加微信红包记录
	 * @param wxbagEntity
	 * @return
	 * @throws Exception
	 */
	public boolean addWxBag(WxbagEntity wxbagEntity) throws Exception{
		String querySql = "INSERT INTO wx_bag (user_id,user_ordersn,is_pay_money,user_phone,event_group,event_key,c_time) VALUES (" 
				+"'" + wxbagEntity.getUserId()+"'" +","
				+"'" + wxbagEntity.getUserOrdersn()+"'" +","
				+"'" + wxbagEntity.getIs_pay_money()+"'" +","
				+"'" + wxbagEntity.getUserPhone()+"'" +","
				+"'" + wxbagEntity.getEventGroup()+"'" +","
				+"'" + wxbagEntity.getEvenKey()+"'" +","
				+"'" + wxbagEntity.getcTime()+"')";				
		return ConnRedis.execute(querySql.toString());
}

}
