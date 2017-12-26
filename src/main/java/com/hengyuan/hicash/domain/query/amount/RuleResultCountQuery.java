package com.hengyuan.hicash.domain.query.amount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.RecordUtils;



/**
 * 获取匹配数据结果接口DAO层查询
 * 
 * @author Mary.luo
 * @create date 2014-07-15
 */
public class RuleResultCountQuery extends AbstractDAO<Integer> {

	private static Logger logger = Logger.getLogger(RuleResultCountQuery.class);

	@Override
	public Integer mapping(ResultSet rs) throws SQLException {

		int total = 0;

		/* 判断查询结果是否存在 */
		if (rs != null) {
			
			total = rs.getInt("total");
		}
		return total;
	}
	
	public Integer queryCount(List<String> list,String ruleId,String uuid) {
		
		String sql = "SELECT COUNT(*) as total FROM hy_rule_inverst_info WHERE rule_id = '" + ruleId + "'";
		
		if(list.size()>0){
			String tmp ="";
			for(int i=0; i<list.size(); i++) {
				if(i == list.size()-1){
					tmp = tmp +"'"+list.get(i)+"'";
				}else{
					tmp = tmp +"'"+list.get(i)+"' ,";
				}
			}
			sql += " and rule_content in ("+tmp+")";
		}
		
		RecordUtils.writeAction(logger, uuid, sql);
		return ConnManager.singleQuery(sql, this);
		
	}
	
}
