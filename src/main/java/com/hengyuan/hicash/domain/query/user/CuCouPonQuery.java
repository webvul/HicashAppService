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
import com.hengyuan.hicash.entity.user.CouPonEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
/**
 * 优惠券查询
 * @author Administrator
 *
 */
public class CuCouPonQuery extends AbstractDAO<CouPonEntity> {
	
	private static Logger logger = Logger.getLogger(CuCouPonQuery.class);
	
	private List<String> selectList = new ArrayList<String>();
	
	public CuCouPonQuery() {
		selectList.add("coupon_id");
		selectList.add("coupon_name");
		selectList.add("coupon_type");
		selectList.add("discount");
		selectList.add("use_num");
		selectList.add("cus_num");
		selectList.add("cus_group");
		selectList.add("limit_amount");
		selectList.add("limit_stage");
		selectList.add("if_amount");
		selectList.add("if_number");
		selectList.add("apply_product");
		selectList.add("begin_time");
		selectList.add("end_time");
		selectList.add("register_begin_time");
		selectList.add("register_end_time");
		selectList.add("is_enable");
		selectList.add("message");
		selectList.add("use_url");
		selectList.add("url_type");
	}
	
	@Override
	public CouPonEntity mapping(ResultSet rs) throws SQLException {
		
		CouPonEntity couPonEntity = null;
		if(rs!=null){
			couPonEntity = new CouPonEntity();
			couPonEntity.setCouPonId(rs.getString("coupon_id"));
			couPonEntity.setCouPonName(rs.getString("coupon_name"));
			couPonEntity.setCouPonType(rs.getString("coupon_type"));
			couPonEntity.setDiscount(rs.getString("discount"));
			couPonEntity.setUseNum(rs.getString("use_num"));
			couPonEntity.setCusNum(rs.getString("cus_num"));
			couPonEntity.setCusGroup(rs.getString("cus_group"));
			couPonEntity.setLimitAmount(rs.getString("limit_amount"));
			couPonEntity.setLimitStage(rs.getString("limit_stage"));
			couPonEntity.setIfAmount(rs.getString("if_amount"));
			couPonEntity.setIfNumber(rs.getString("if_number"));
			couPonEntity.setApplyProduct(rs.getString("apply_product"));
			couPonEntity.setBeginTime(rs.getString("begin_time"));
			couPonEntity.setEndTime(rs.getString("end_time"));
			couPonEntity.setRegisterBeginTime(rs.getString("register_begin_time"));
			couPonEntity.setRegisterEndTime(rs.getString("register_end_time"));
			couPonEntity.setIsEnable(rs.getString("is_enable"));
			couPonEntity.setMessage(rs.getString("message"));
			couPonEntity.setUseUrl(rs.getString("use_url"));
			couPonEntity.setUrlType(rs.getString("url_type"));
		}else{
			System.out.println("查询为空");
		}
		
		return couPonEntity;
	}

	/**
	 * 根据id查看优惠券
	 * @param id
	 * @return
	 */
	public CouPonEntity queryByCouId(String id){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("coupon_id", id);
		whereMap.put("is_enable", 1);
			
		String sql = MapAssemForSql.getSelectSql(selectList,TableConsts.COUPON,whereMap);
			// 记录日志
    	RecordUtils.writeAction(logger, null, sql);
			
		return ConnManager.singleQuery(sql, this);
		
	}
}
