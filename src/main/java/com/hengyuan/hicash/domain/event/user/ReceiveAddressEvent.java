package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.user.AddressQuery;
import com.hengyuan.hicash.exception.SaveAddressException;
import com.hengyuan.hicash.exception.UpdateAddressException;
import com.hengyuan.hicash.parameters.request.user.CustReceiveAddressReq;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 收货地址
 * @author ding
 *
 */
public class ReceiveAddressEvent {
	
	private static Logger logger = Logger.getLogger(ReceiveAddressEvent.class);
	
	AddressQuery addressQuery = new AddressQuery();
	
	/**
	 * 新增收货地址 
	 * @param req
	 * @throws SaveAddressException 
	 * @throws UpdateAddressException 
	
	 */
	public void createAddress(CustReceiveAddressReq req) throws SaveAddressException, UpdateAddressException  {

		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("cust_user", req.getCust_user());
		setMap.put("real_name", req.getReal_name());
		setMap.put("mobile", req.getMobile());
		setMap.put("province_code", req.getProvince_code());
		setMap.put("city_code", req.getCity_code());
		setMap.put("area_code", req.getArea_code());
		setMap.put("detail_address", req.getDetail_address());
		if(req.getIs_default().equals("1")){
			if(addressQuery.queryAddressByDefult("1", req.getCust_user())==null){
				setMap.put("is_default", "1");
			}else{
				req.setIs_default("0");
				UpdateIsDefauleAll(req);
				setMap.put("is_default", "1");
			}
		}else{
			setMap.put("is_default", "0");
		}
		
		
		setMap.put("create_user", req.getCust_user());
		setMap.put("create_time", DateUtils.getNowTime());
		setMap.put("status", 1);
		if(req.getRemark()!=null && !req.getRemark().isEmpty()){
			setMap.put("remark", req.getRemark());
		}

		String createSql = MapAssemForSql.getInsertSql(TableConsts.CUST_RECEIVE_ADDRESS,setMap);
		RecordUtils.writeAction(logger, req.getUuid(), createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new SaveAddressException();
		}

	}
	
	
	
	/**
	 * 更新收货地址 
	 * @param req
	 * @throws SaveAddressException 
	 * @throws UpdateAddressException 
	
	 */
	public void UpdateAddress(CustReceiveAddressReq req) throws  UpdateAddressException  {

		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("cust_user", req.getCust_user());
		setMap.put("real_name", req.getReal_name());
		setMap.put("mobile", req.getMobile());
		setMap.put("province_code", req.getProvince_code());
		setMap.put("city_code", req.getCity_code());
		setMap.put("area_code", req.getArea_code());
		setMap.put("detail_address", req.getDetail_address());
		if(req.getIs_default().equals("1")){
			if(addressQuery.queryAddressByDefult("1", req.getCust_user())==null){
				setMap.put("is_default", "1");
			}else{
				req.setIs_default("0");
				UpdateIsDefauleAll(req);
				setMap.put("is_default", "1");
			}
		}else{
			setMap.put("is_default", "0");
		}
		setMap.put("create_user", req.getCust_user());
		setMap.put("create_time", DateUtils.getNowTime());
		
		
		Map<String, Object> wheremMap = new HashMap<String, Object>();

		wheremMap.put("id", req.getId());

		String createSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_RECEIVE_ADDRESS,setMap,wheremMap);
		RecordUtils.writeAction(logger, req.getUuid(), createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new UpdateAddressException();
		}

	}
	
	
	
	/**
	 * 改成默认收货地址
	 * @param req
	 * @throws UpdateAddressException
	 */
	public void UpdateIsDefaule(CustReceiveAddressReq req) throws  UpdateAddressException  {

		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("is_default", req.getIs_default());
		
		
		
		Map<String, Object> wheremMap = new HashMap<String, Object>();

		wheremMap.put("id", req.getId());

		String createSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_RECEIVE_ADDRESS,setMap,wheremMap);
		RecordUtils.writeAction(logger, req.getUuid(), createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new UpdateAddressException();
		}

	}
	
	
	
	/**
	 * 全部改变默认地址状态
	 * @param req
	 * @throws UpdateAddressException
	 */
	public void UpdateIsDefauleAll(CustReceiveAddressReq req) throws  UpdateAddressException  {

		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("is_default", req.getIs_default());
		
		
		Map<String, Object> wheremMap = new HashMap<String, Object>();

		wheremMap.put("cust_user", req.getCust_user());
		

		String createSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_RECEIVE_ADDRESS,setMap,wheremMap);
		RecordUtils.writeAction(logger, req.getUuid(), createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new UpdateAddressException();
		}

	}
	
	
	
	/**
	 * 删除地址
	 * @param req
	 * @throws UpdateAddressException
	 */
	public void UpdateStatus(CustReceiveAddressReq req) throws  UpdateAddressException  {

		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("status", 0);
		
		Map<String, Object> wheremMap = new HashMap<String, Object>();

		wheremMap.put("id", req.getId());

		String createSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_RECEIVE_ADDRESS,setMap,wheremMap);
		RecordUtils.writeAction(logger, req.getUuid(), createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new UpdateAddressException();
		}

	}
	
	
}
