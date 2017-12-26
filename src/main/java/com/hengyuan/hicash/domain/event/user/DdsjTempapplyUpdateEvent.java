package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdateCustomerException;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;


public class DdsjTempapplyUpdateEvent {

private static Logger logger = Logger.getLogger(DdsjTempapplyUpdateEvent.class);
	
	//根据用户名修改pwd
	public int updPwdFromTempAppNo(String password,String mobile,String tempAppNo){
		
		String updSql = "UPDATE ddsj_tempapply_credit set ddsj_auth_phone = '"+mobile+"' , ddsj_auth_password = '" + password + "' where temp_application_no = " +tempAppNo+"";
		RecordUtils.writeAction(logger, null, updSql);
		return ConnManager.executeUpdate(updSql);
	}
	
}
