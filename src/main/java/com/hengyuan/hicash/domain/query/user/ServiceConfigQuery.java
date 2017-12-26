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
import com.hengyuan.hicash.entity.user.ServiceConfigEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

public class ServiceConfigQuery extends AbstractDAO<ServiceConfigEntity>{

	private static Logger logger = Logger.getLogger(ServiceConfigQuery.class);
	private List<String> list;
	
	public ServiceConfigQuery(){
		list = new ArrayList<String>();
		
		list.add("SERVICE_CODE");
		list.add("SERVICE_NAME");
		list.add("SERVICE_URL");
	}
	
	@Override
	public ServiceConfigEntity mapping(ResultSet rs) throws SQLException {
		
		ServiceConfigEntity serviceConfig = new ServiceConfigEntity();
		
		if(rs!=null){
			
			serviceConfig.setServiceCode(rs.getString("SERVICE_CODE"));
			serviceConfig.setServiceName(rs.getString("SERVICE_NAME"));
			serviceConfig.setServiceUrl(rs.getString("SERVICE_URL"));
			
		}else{
			
			System.out.println("查询的结果集为空");
			
			return null;
		}
		
		return serviceConfig;
	}
	
	/**
	 * 根据服务代码查询要调用的服务
	 * @param code
	 * @return
	 */
	public ServiceConfigEntity queryServiceByCode(String code){
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("SERVICE_CODE", code);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.SERVICE_CONGIG, setMap);
		//记录日志
				RecordUtils.writeAction(logger, code, sql); 
		return ConnManager.singleQuery(sql, this);
		
	}
	
	
}
