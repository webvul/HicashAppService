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
import com.hengyuan.hicash.entity.user.MobileUpdateEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * 修改密码的查询dao
 * 
 * @author Eric
 * @create date 2014-07-22
 *
 */
public class MobileUpdateSendCodeQuery extends AbstractDAO<MobileUpdateEntity>{

	private static Logger logger = Logger.getLogger(MobileUpdateSendCodeQuery.class);
	private List<String> list;
	
	public MobileUpdateSendCodeQuery(){
		list = new ArrayList<String>();
		
		list.add("MOBILENO");
		
		list.add("MOBILE_VALIDATE_CODE_VALID_TIME");
	}
	
	@Override
	public MobileUpdateEntity mapping(ResultSet rs) throws SQLException {

		MobileUpdateEntity entity = new MobileUpdateEntity();
		
		if (rs != null) {
			entity.setMobile(StringUtils.valueOf(rs.getObject("MOBILENO")));
			
			entity.setMobileValidateCodeValidTime(StringUtils.valueOf(rs.getObject("MOBILE_VALIDATE_CODE_VALID_TIME")));
		}else {
			System.out.println("数据为空");
			return null;
		}
		
		return entity;
	}
		
	/**
	 * 查询用户表
	 * @return
	 */
	public MobileUpdateEntity queryByMobile(String userName){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, userName, sql);
		return ConnManager.singleQuery(sql, this);
	}
	
	/**
	 * 查询用户表
	 * @return
	 */
	public MobileUpdateEntity isMobileExists(String mobile){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("mobileno", mobile);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, mobile, sql);
		return ConnManager.singleQuery(sql, this);
	}
	
}
