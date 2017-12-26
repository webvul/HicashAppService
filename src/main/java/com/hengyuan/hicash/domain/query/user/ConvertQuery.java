package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.ServiceConvertEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 用户信息查询
 * 
 * @author mary.luo
 * @create date 2014-08-11
 * 
 */
public class ConvertQuery extends AbstractDAO<ServiceConvertEntity> {

	@Override
	public ServiceConvertEntity mapping(ResultSet rs) throws SQLException {

		ServiceConvertEntity serviceConvertEntity = null;
		if (rs != null) {
			serviceConvertEntity = new ServiceConvertEntity();
			serviceConvertEntity.setServiceIp(StringUtils.valueOf(rs.getObject("SERVICE_IP")));
			serviceConvertEntity.setServicePort(StringUtils.valueOf(rs.getObject("SERVICE_PORT")));
			serviceConvertEntity.setServiceUrl(StringUtils.valueOf(rs.getObject("SERVICE_URL")));
			serviceConvertEntity.setLimitIp(StringUtils.valueOf(rs.getObject("LIMIT_IP")));
			serviceConvertEntity.setEncoding(StringUtils.valueOf(rs.getObject("ENCODING")));
		} else {
			serviceConvertEntity = null;
		}

		return serviceConvertEntity;
	}

	/**
	 * 查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public ServiceConvertEntity getUrlByCode(String serviceCode) {
		List<String> list = new ArrayList<String>();
		list.add("SERVICE_NAME");
		list.add("SERVICE_IP");
		list.add("SERVICE_PORT");
		list.add("SERVICE_URL");
		list.add("LIMIT_IP");
		list.add("ENCODING");
		
		Map<String, Object> setmapMap = new HashMap<String, Object>();
		setmapMap.put("service_code", serviceCode);
		setmapMap.put("isenable", "1");

		String loanQuery = MapAssemForSql.getSelectSql(list,
				TableConsts.SERVICE_CONVERT, setmapMap);
		ServiceConvertEntity urlConvert = ConnManager.singleQuery(
				loanQuery.toString(), this);
		ConnManager.closeConn();
		if (urlConvert != null) {
			return urlConvert;
		} else {
			return null;
		}
	}

}
