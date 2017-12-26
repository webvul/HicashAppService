package com.hengyuan.hicash.domain.query.mktApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 查询首付比率
 * @author Cary.Liu
 * @create 2015-03-12
 *
 */
public class PaymenyQuery extends AbstractDAO<String> {

	
	@Override
	public String mapping(ResultSet rs) throws SQLException {

		String tmp = "" ;
		
		if(rs != null){
			tmp = StringUtils.valueOf(rs.getObject("proportion"));
		}
		
		return tmp;
	}

	/**
	 * 查询首付比率
	 * @param cityCode 城市代码
	 * @param custType 客户类型
	 * @return
	 */
    public List<String> queryPayRate(String cityCode,String custType){
    	
    	String sql ="SELECT proportion FROM d_proportion_pay WHERE city ='"+ cityCode +"' and client_Type = '"+ custType +"' ORDER BY proportion";
  
    	 return ConnManager.executeQuery(sql, this);
    }
    
}
