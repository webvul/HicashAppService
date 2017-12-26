package com.hengyuan.hicash.domain.query.app;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 提现笔数 查询DAO
 * 
 * @author Cary.Liu
 * @create 2014-09-17
 *
 */
public class ExtractCashCountQuery extends AbstractDAO<Integer> {

	private static Logger logger = Logger.getLogger(ExtractCashCountQuery.class);
	
	@Override
	public Integer mapping(ResultSet rs) throws SQLException {
		
		if(rs != null){
			return rs.getInt("SUCCOUNT");
		}
		
		return 0;
	}
	
	/**
	 * 统计2014-09-17之后的提现成功笔数
	 * 
	 * @return
	 */
	public Integer queryExtractCount(){
		
		String querySql = "SELECT COUNT(*) AS SUCCOUNT FROM d_application_pay WHERE 1 =1 AND pay_type = 'CASH' AND (node = 'NODE11' OR STATUS = 'STATUS27')"
				+ " AND FROM_UNIXTIME(UNIX_TIMESTAMP(create_date), '%Y-%m-%d')>=FROM_UNIXTIME(UNIX_TIMESTAMP(CURDATE()), '%Y-%m-%d')";
		
		RecordUtils.writeAction(logger, null, querySql);
		return ConnManager.singleQuery(querySql, this);
	}
	
}
