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
import com.hengyuan.hicash.entity.user.CustReceiveAddressEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class AddressQuery extends AbstractDAO<CustReceiveAddressEntity> {

	private List<String> lists = new ArrayList<String>();

	public AddressQuery() {
		lists.add("ID");
		lists.add("cust_user");
		lists.add("real_name");
		lists.add("mobile");
		lists.add("province_code");
		lists.add("city_code");
		lists.add("area_code");
		lists.add("detail_address");
		lists.add("is_default");
		lists.add("remark");
	}

	@Override
	public CustReceiveAddressEntity mapping(ResultSet rs) throws SQLException {
		
		CustReceiveAddressEntity entity = null;
		
		if(rs != null){
			entity = new CustReceiveAddressEntity();
			entity.setId(StringUtils.valueOf(rs.getObject("ID")));
			entity.setCust_user(StringUtils.valueOf(rs.getObject("cust_user")));
			entity.setReal_name(StringUtils.valueOf(rs.getObject("real_name")));
			entity.setMobile(StringUtils.valueOf(rs.getObject("mobile")));
			entity.setProvince_code(StringUtils.valueOf(rs.getObject("province_code")));
			entity.setCity_code(StringUtils.valueOf(rs.getObject("city_code")));
			entity.setArea_code(StringUtils.valueOf(rs.getObject("area_code")));
			entity.setIs_default(StringUtils.valueOf(rs.getObject("is_default")));
			entity.setDetail_address(StringUtils.valueOf(rs.getObject("detail_address")));
			entity.setRemark(StringUtils.valueOf(rs.getObject("remark")));
		}
		
		return entity;
	}
	
	/**
	 * 查看收货地址
	 * @param userName
	 * @return
	 */
	public List<CustReceiveAddressEntity> queryAddress(String userName){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("cust_user", userName);
		whereMap.put("status", 1);
		
		String sql = MapAssemForSql.getSelectSqlByGroupOrOrder(lists, TableConsts.CUST_RECEIVE_ADDRESS, whereMap,"order by is_default desc,create_time desc ");
		//记录日志
	    RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	
	/**
	 * 根据id查看收货地址
	 * @param userName
	 * @return
	 */
	public CustReceiveAddressEntity queryAddressById(String id){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("id", id);
		
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.CUST_RECEIVE_ADDRESS, whereMap);
		//记录日志
	    RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);
	}
	
	
	/**
	 * 查看默认地址
	 * @param isdefault
	 * @param userName
	 * @return
	 */
	public CustReceiveAddressEntity queryAddressByDefult(String isdefault ,String userName){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("is_default", isdefault);
		whereMap.put("cust_user", userName);
		
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.CUST_RECEIVE_ADDRESS, whereMap);
		//记录日志
	    RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);
	}
	
	/**
	 * 查看家庭地址
	 * @param isdefault
	 * @param userName
	 * @return
	 */
	public CustReceiveAddressEntity queryAddressByRemark(String remark,String userName){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("remark", remark);
		whereMap.put("cust_user", userName);
		
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.CUST_RECEIVE_ADDRESS, whereMap);
		//记录日志
	    RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);
	}
	
}
