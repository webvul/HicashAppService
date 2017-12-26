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
import com.hengyuan.hicash.entity.user.HyVipUser;
import com.hengyuan.hicash.utils.MapAssemForSql;

/** 
 * @author meng.zhang 
 * 2017年2月22日 下午4:06:47
 * 类说明 
 */
public class HyVipUserQuery extends AbstractDAO<HyVipUser>{

	private static Logger logger = Logger.getLogger(HyVipUserQuery.class);
	
	private List<String> columns = new ArrayList<String>();
	
	public HyVipUserQuery(){
		
		columns.add("id");
		columns.add("username");
		columns.add("amount");
		columns.add("mobile");
		columns.add("init_period");
		columns.add("status");
		columns.add("is_vip");
		columns.add("is_auth");
		columns.add("create_time");
		columns.add("message");
		columns.add("type");
	}
	
	@Override
	public HyVipUser mapping(ResultSet rs) throws SQLException {
		
		HyVipUser entity = null;
		
		if(rs != null){
			entity = new HyVipUser();
			entity.setUsername(rs.getString("username"));
			entity.setAmount(rs.getBigDecimal("amount"));
			entity.setMobile(rs.getString("mobile"));
			entity.setInitPeriod(rs.getInt("init_period"));
			entity.setStatus(rs.getString("status"));
			entity.setIsVip(rs.getInt("is_vip"));
			entity.setIsAuth(rs.getInt("is_auth"));
			entity.setType(rs.getInt("type"));
		}
		
		return entity;
	}
	
	/** 通过用户名和电话查询VIP客户是否授信*/
	public List<HyVipUser> isCreditExtension(String username){
		
		List<String> selectList = new ArrayList<String>();
		selectList.add("username");
		selectList.add("amount");
		selectList.add("mobile");
		selectList.add("init_period");
		selectList.add("status");
		selectList.add("is_vip");
		selectList.add("is_auth");
		selectList.add("type");
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", username);
		
		String sql = MapAssemForSql.getSelectSql(selectList,TableConsts.HY_VIP_USER,whereMap);
		logger.info("通过用户名和电话查询VIP客户是否授信sql="+sql);
		
		return ConnManager.executeQuery(sql, this);
	}

}
