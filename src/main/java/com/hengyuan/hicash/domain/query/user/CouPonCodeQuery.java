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
import com.hengyuan.hicash.entity.user.CouPonCodeEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
/**
 * 优惠券明细查询
 * @author Administrator
 *
 */
public class CouPonCodeQuery extends AbstractDAO<CouPonCodeEntity> {
	
	private static Logger logger = Logger.getLogger(CouPonCodeQuery.class);
	
	private List<String> selectList = new ArrayList<String>();
	
	public CouPonCodeQuery() {
		selectList.add("coupon_code_id");
		selectList.add("coupon_id");
		selectList.add("coupon_code");
		selectList.add("is_enable");
		
		
	}
	
	@Override
	public CouPonCodeEntity mapping(ResultSet rs) throws SQLException {
		
		CouPonCodeEntity couPonEntity = null;
		if(rs!=null){
			couPonEntity = new CouPonCodeEntity();
			couPonEntity.setCouponCodeId(rs.getString("coupon_code_id"));
			couPonEntity.setCouponCode(rs.getString("coupon_code"));
			couPonEntity.setCouponId(rs.getString("coupon_id"));
			couPonEntity.setIsEnable(rs.getString("is_enable"));
			
		}else{
			System.out.println("查询为空");
		}
		
		return couPonEntity;
	}

	
	public CouPonCodeEntity queryCodeByCouId(String id){
	
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("coupon_id", id);
			
		String sql = MapAssemForSql.getSelectSql(selectList,TableConsts.COUPON_CODE,whereMap);
			// 记录日志
    	RecordUtils.writeAction(logger, null, sql);
			
		return ConnManager.singleQuery(sql, this);
		
	}
	
	
	public CouPonCodeEntity queryCodeByCode(String code){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("coupon_code", code);
			
		String sql = MapAssemForSql.getSelectSql(selectList,TableConsts.COUPON_CODE,whereMap);
			// 记录日志
    	RecordUtils.writeAction(logger, null, sql);
			
		return ConnManager.singleQuery(sql, this);
		
	}
	
	
      public CouPonCodeEntity queryCodeByCodeId(String codeId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("coupon_code_id", codeId);
			
		String sql = MapAssemForSql.getSelectSql(selectList,TableConsts.COUPON_CODE,whereMap);
			// 记录日志
    	RecordUtils.writeAction(logger, null, sql);
			
		return ConnManager.singleQuery(sql, this);
		
	}
	
}
