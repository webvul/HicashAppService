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
import com.hengyuan.hicash.entity.BlackListEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * 黑名单查询类
 * @author Laughing.peng
 * @create date 2014-07-22
 * 
 */
public class BlackListQuery extends AbstractDAO<BlackListEntity>{
	private static Logger logger = Logger.getLogger(BlackListQuery.class);
	@Override
	public BlackListEntity mapping(ResultSet rs) throws SQLException {
		 
		BlackListEntity blackListEntity = new BlackListEntity();
		
		if(rs!=null){
			
			blackListEntity.setIdentity(StringUtils.valueOf(rs.getObject("username")));
			blackListEntity.setIdentity(StringUtils.valueOf(rs.getObject("identityNo")));
			
			return blackListEntity;
		}else{
			
			System.out.println("查询的结果集为空");
			return null;
			
		}
	}
	
	/**
	 * 根据姓名和身份证号匹配黑名单
	 * @param realName
	 * @param identity
	 * @return
	 */
	public BlackListEntity queryBlackList(String realName,String identity,String uuid){
		
		List<String> list = new ArrayList<String>();
		list.add("identityNo");
		list.add("username");
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("username", realName);
		setMap.put("identityNo", identity);
		
		String str = MapAssemForSql.getSelectSql(list, TableConsts.BLACK_LIST, setMap);
		//记录日志
		RecordUtils.writeAction(logger, uuid, str.toString());
		return ConnManager.singleQuery(str.toString(), this);
	}

}
