package com.hengyuan.hicash.domain.event.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ExceptionMsg;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.HyIndustryParam;
import com.hengyuan.hicash.entity.user.SaveMyMsgEntity;
import com.hengyuan.hicash.exception.SaveDeviceException;
import com.hengyuan.hicash.exception.SaveSupplierException;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

public class SaveMyMsgEvent extends AbstractDAO<CustCustomer>{
	
	private static Logger logger = Logger.getLogger(SaveMyMsgEvent.class);
	
	private String uuid;
	
	
	private List<String> lists = new ArrayList<String>();
	
	public SaveMyMsgEvent() {
		lists.add("NAME");
		lists.add("MOBILE");
		lists.add("IDENTITY_NO");
	
	}
	
	@Override
	public CustCustomer mapping(ResultSet rs) throws SQLException {
		CustCustomer custCustomer = null;
		if(rs!=null){
			custCustomer = new CustCustomer();
			custCustomer.setRealName(rs.getString("NAME"));
			custCustomer.setMobileNo(rs.getString("MOBILE"));
			custCustomer.setIdentityNo(rs.getString("IDENTITY_NO"));
		}
		return custCustomer;
	}

	
	public CustCustomer QueryInfo(String userName){

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", userName);
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.CUST_CUSTOMER, whereMap);
		logger.info("通过用户名查询客户信息");
		return ConnManager.singleQuery(sql, this);
	}

	/**
	 * 保存我的消息
	 * @param req
	 * @throws SaveSupplierException
	 */
	public void saveMyMsg(SaveMyMsgEntity entity) throws SaveDeviceException{
			
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("username", entity.getUsername());
		setMap.put("code", entity.getCode());
		setMap.put("mobile", entity.getMobile());
		setMap.put("id_no", entity.getId_no());
		setMap.put("name", entity.getName());
		setMap.put("is_read",entity.getIs_read());
		setMap.put("title", entity.getTitle());
		setMap.put("content", entity.getContent());
		setMap.put("operate", entity.getOperate());
		setMap.put("type", entity.getType());
		setMap.put("create_time", DateUtils.getDateStr(new Date()));
		if(StringUtils.isNotBlank(entity.getAppNo())){
			setMap.put("appNo", entity.getAppNo());
		}
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.CUST_USER_MESSAGE,setMap);

		RecordUtils.writeAction(logger, uuid, updateSql);
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new SaveDeviceException(ExceptionMsg.SAVE_INFO_EXCEPTION);
		}	
	}
}
