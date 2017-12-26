package com.hengyuan.hicash.domain.event.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ExceptionMsg;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.SaveApproveAuthroException;
import com.hengyuan.hicash.exception.SaveApproveException;
import com.hengyuan.hicash.exception.SaveSupplierException;
import com.hengyuan.hicash.parameters.request.user.AddSupplierInfoReq;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;


/** 
 * 商户入驻-新增商户 event
 * @author Cary.Liu
 * @createDate 2015-07-10
 * 
 */
public class AddSupplierInfoEvent {
	
	private static Logger logger = Logger.getLogger(AddSupplierInfoEvent.class);
	
	private String uuid;
	
	public AddSupplierInfoEvent(String uuid){
		this.uuid = uuid;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 保存商户信息
	 * @param req
	 * @throws SaveSupplierException
	 */
	public void saveSupplierInfo(AddSupplierInfoReq req) throws SaveSupplierException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("SUPPLIER_NAME", req.getSupplierName());
		setMap.put("suppliertel", req.getMobileNo());//联系人手机号码
		setMap.put("sup_charge_tel", req.getMobileNo());//负责人手机号码
		setMap.put("supplier_username", req.getSupplierUserName());
		setMap.put("SUP_CON_NAME", req.getRealName());
		setMap.put("sup_industry", req.getSupIndustry());
		setMap.put("provice", req.getProvince());
		setMap.put("city", req.getCity());
		setMap.put("CREATE_DATE", DateUtils.getDateStr(new Date()));
		setMap.put("CREATE_USER", req.getSupplierUserName());

		String updateSql = MapAssemForSql.getInsertSql(TableConsts.SUPPLIER,setMap);

		RecordUtils.writeAction(logger, uuid, updateSql);
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new SaveSupplierException(ExceptionMsg.SAVE_SUPPLIER_EXCEPTION);
		}
		
	}

	/**
	 * 保存登陆账号
	 * @param req
	 * @throws SaveApproveException
	 */
	public void saveApproveUser(AddSupplierInfoReq req,String supplierId) throws SaveApproveException{
		
		String pwd = req.getSupplierUserPassword();
		String salt = RandomStringUtils.random(20, true, true);
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		String password = passwordEncoder.encodePassword(pwd, salt);
		String createDate = DateUtils.getDateStr(new Date());
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("supInfoId", supplierId);
		setMap.put("real_name", req.getRealName());
		setMap.put("mobileno",req.getMobileNo());
		setMap.put("username",req.getSupplierUserName());
		setMap.put("password",password);
		setMap.put("salt",salt);
		setMap.put("provice",req.getProvince());
		setMap.put("city_code",req.getCity());
		setMap.put("CREATE_TIME",createDate);
		setMap.put("CREATE_DATE",createDate);
		setMap.put("CREATE_USER",req.getSupplierUserName());
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.APPROVE_USER,setMap);
		
		RecordUtils.writeAction(logger,uuid,updateSql);
		
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new SaveApproveException(ExceptionMsg.SAVE_APPROVEUSER_EXCEPTION);
		}

	}
	
	/**
	 * 给登陆用户授权
	 * @throws SaveApproveAuthroException 
	 */
	public void saveApproveAuthro(String approveUserId) throws SaveApproveAuthroException{

		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("USER_ID", approveUserId);
		setMap.put("AUTHORITY_ID", Consts.SUPPLIER_ADMIN_AUTHRO);
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.APPROVE_USER_AUTHORITY,setMap);
		
		RecordUtils.writeAction(logger,uuid,updateSql);
		
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new SaveApproveAuthroException(ExceptionMsg.SAVE_APPROVE_AHTOR_EXCEPTION);
		}
	}
	
}
