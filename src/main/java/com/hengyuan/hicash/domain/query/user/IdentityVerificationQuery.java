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
import com.hengyuan.hicash.entity.user.IdentityVerificationEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 身份证验证查询类
 * 
 * @author laughing.Peng
 * @create date 2014-07-21
 *
 */
public class IdentityVerificationQuery extends AbstractDAO<IdentityVerificationEntity>{
	private static Logger logger = Logger.getLogger(IdentityVerificationQuery.class);
	private List<String> list;
	
	public IdentityVerificationQuery(){
		list = new ArrayList<String>();
		list.add("IDENTITY_NO");
		list.add("USERNAME");
	}
	
	@Override
	public IdentityVerificationEntity mapping(ResultSet rs)
			throws SQLException {
		
		IdentityVerificationEntity identityVerificationEntity = new IdentityVerificationEntity();
		
		if(rs != null){
			
			identityVerificationEntity.setUsername(StringUtils.valueOf(rs.getObject("USERNAME")));
			identityVerificationEntity.setIdentity(StringUtils.valueOf(rs.getObject("IDENTITY_NO")));
			
		}else{
			
			System.out.println("查询的结果集为空");
			
			return null;
		}
		
		return identityVerificationEntity;
	}
	
	
	/**根据身份证号查询用户
	 * 
	 * @param identity
	 * @return
	 */
	public List<IdentityVerificationEntity> queryIdentityList(String identity){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("IDENTITY_NO", identity);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_CUSTOMER, whereMap)
				+ " AND platform_Mark = '1' ";
		//记录日志
		RecordUtils.writeAction(logger, identity, sql);
		return ConnManager.executeQuery(sql,this);
	}
	
}
