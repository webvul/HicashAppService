package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CouPonRecordEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
/**
 * 优惠券使用
 * @author Administrator
 *
 */
public class CouPonRecordQuery extends AbstractDAO<CouPonRecordEntity> {
	
	private static Logger logger = Logger.getLogger(CouPonRecordQuery.class);
	
	private List<String> selectList = new ArrayList<String>();
	
	public CouPonRecordQuery() {
		selectList.add("coupon_code_id");
		selectList.add("user_id");
		selectList.add("order_id");
		selectList.add("add_time");
		
		
	}
	
	@Override
	public CouPonRecordEntity mapping(ResultSet rs) throws SQLException {
		
		CouPonRecordEntity couPonEntity = null;
		if(rs!=null){
			couPonEntity = new CouPonRecordEntity();
			couPonEntity.setCouponCodeId(rs.getString("coupon_code_id"));
			couPonEntity.setUserId(rs.getString("user_id"));
			couPonEntity.setOrderId(rs.getString("order_id"));
			couPonEntity.setAddTime(rs.getString("add_time"));
			
		}else{
			System.out.println("查询为空");
		}
		
		return couPonEntity;
	}

	
	public List<CouPonRecordEntity> queryRecordByUserId(String userId){
	
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("user_id", userId);
			
		String sql = MapAssemForSql.getSelectSql(selectList,TableConsts.COUPON_RECORD,whereMap);
			// 记录日志
    	RecordUtils.writeAction(logger, null, sql);
			
		return ConnManager.executeQuery(sql, this);
		
	}
	
	
	
	public CouPonRecordEntity queryRecordByUserIdAndOrder(String userId,String orderId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("user_id", userId);
		whereMap.put("order_id", orderId);	
		String sql = MapAssemForSql.getSelectSql(selectList,TableConsts.COUPON_RECORD,whereMap);
			// 记录日志
    	RecordUtils.writeAction(logger, null, sql);
			
		return ConnManager.singleQuery(sql, this);
		
	}
}
