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
import com.hengyuan.hicash.entity.user.HyVipPeriod;
import com.hengyuan.hicash.utils.MapAssemForSql;

/** 
 * @author meng.zhang 
 * 2017年2月22日 下午5:46:22
 * 类说明 
 */
public class HyVipPeriodQuery extends AbstractDAO<HyVipPeriod>{

	private static Logger logger = Logger.getLogger(HyVipPeriodQuery.class);
	
	private List<String> columns = new ArrayList<String>();
	
	public HyVipPeriodQuery(){
		columns.add("id");
		columns.add("username");
		columns.add("period");
		columns.add("create_time");
	}
	
	
	@Override
	public HyVipPeriod mapping(ResultSet rs) throws SQLException {
		
		HyVipPeriod entity = new HyVipPeriod();
		
		if(rs != null){
			
			//entity.setId(rs.getInt("id"));
			entity.setUsername(rs.getString("username"));
			entity.setPeriod(rs.getInt("period"));
			//entity.setCreateTime(rs.getDate("create_time"));
			
		}
		
		return entity;
	}
	
	/** 通过用户名查询vip客户的期数*/
	public List<HyVipPeriod> findPeriodsByUserName(String username){
		
		List<String> selectList = new ArrayList<String>();
		selectList.add("period");
		selectList.add("username");
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", username);
		
		String sql = MapAssemForSql.getSelectSql(selectList,TableConsts.HY_VIP_PERIOD,whereMap);
		logger.info("通过用户名查询VIP客户期数sql="+sql);
		
		return ConnManager.executeQuery(sql, this);
	}

}
