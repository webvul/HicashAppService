package com.hengyuan.hicash.domain.event.apply;

import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.CreateAppPayException;
import com.hengyuan.hicash.exception.CreateDdsjLimitException;
import com.hengyuan.hicash.exception.UpdateAppPayException;
import com.hengyuan.hicash.exception.UpdateCreateCreditException;
import com.hengyuan.hicash.exception.UpdateDdsjLimitException;
import com.hengyuan.hicash.exception.UpdateTempAppException;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 
* @author dong.liu 
* 2017-3-24 下午2:50:38
* 类说明:正式订单创建
 */
public class HinsCreateCreditEvent {
	private static Logger logger = Logger.getLogger(HinsCreateCreditEvent.class);
	/**
	 * 根据SQL语句更新申请件
	 * 
	 * @param updateSql
	 * @throws UpdateAppPayException
	 * @author Andy.Niu
	 * @create 2014-07-29
	 */
	public void updateCreateCreditBySql(String updateSql) throws UpdateAppPayException {
		RecordUtils.writeAction(logger, null, updateSql);
		/* 更新申请件状态 */
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new UpdateAppPayException();
		}
	}

	
	public void CreateCredit(Map<String, Object> creditMap)
			throws CreateAppPayException {

		String createSql = MapAssemForSql.getInsertSql(TableConsts.CREDIT_PAY,creditMap);
		RecordUtils.writeAction(logger, null, createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new CreateAppPayException();
		}

	}
	public void createHinsLimit(Map<String, Object> hinsLimitMap)
			throws CreateDdsjLimitException {

		String createSql = MapAssemForSql.getInsertSql(TableConsts.CREDIT_LIMIT,hinsLimitMap);
		RecordUtils.writeAction(logger, null, createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new CreateDdsjLimitException();
		}

	}
		
	public void updateHinsLimit(String username,String status)
			throws CreateDdsjLimitException {

		String sql="UPDATE credit_limit set status='"+status+"'  "+
				 " where username='"+username+"'";
		RecordUtils.writeAction(logger, null, sql);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new CreateDdsjLimitException();
		}

	}
		
		
	
	/**
	 * 更新临时订单表的正式订单号
	 * @param tempAppNo
	 * @param appNo
	 * @throws UpdateAppPayException
	 */
	public void updateTempCredit(String tempAppNo,String appNo) throws UpdateTempAppException{
		String sql="UPDATE credit_temp_pay set app_application_no='"+appNo+"' ,createapp_flag='Y'," +
				" update_date=now() " +
				" where temp_application_no='"+tempAppNo+"'";
		RecordUtils.writeAction(logger, null, sql);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateTempAppException();
		}
	}
	
	/**
	 * 授信单取消
	 * @param appNo
	 * @throws UpdateCreateCreditException
	 */
	public void updateCreateCredit(String appNo) throws UpdateCreateCreditException{
		String sql="UPDATE credit_pay set status='STATUS20',node='GBNODE'  " +
				" where app_application_no='"+appNo+"'";
		RecordUtils.writeAction(logger, null, sql);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateCreateCreditException();
		}
	}
	
	/**
	 * 授信单取消,更改授信额度为正常状态
	 * @param appNo
	 * @throws UpdateCreateCreditException
	 */
	public void updateHinsLimitSta(String userName) throws UpdateDdsjLimitException{
		String sql="UPDATE credit_limit set status='NOML' " +
				" where username='"+userName+"'";
		RecordUtils.writeAction(logger, null, sql);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateDdsjLimitException();
		}
	}
		
	
}
