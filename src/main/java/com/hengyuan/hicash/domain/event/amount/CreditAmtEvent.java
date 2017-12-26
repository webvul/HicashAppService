package com.hengyuan.hicash.domain.event.amount;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CustLimitEntity;
import com.hengyuan.hicash.exception.UpdateCreditException;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 
 * 用户额度表更新类
 * @author Laughing.peng
 * @create date 2014-07-22
 * 
 */
public class CreditAmtEvent {
	
	private static Logger logger = Logger.getLogger(CreditAmtEvent.class);
	/**
	 * 保存用户的授信额度
	 * @param custLimitEntity
	 * @return
	 * @throws UpdateCreditException 
	 */
	public void saveCreaditAmt(CustLimitEntity custLimitEntity) throws UpdateCreditException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("APPLY_AMOUNT",custLimitEntity.getApplyAmt());
		setMap.put("BLOCK_AMOUNT", custLimitEntity.getBlockAmt());
		setMap.put("MAX_AMOUNT", custLimitEntity.getMaxAmt());
		setMap.put("TRUST_AMOUNT", custLimitEntity.getTrustAmt());
		setMap.put("USE_AMOUNT", custLimitEntity.getUseAmt());
		setMap.put("USER_NAME", custLimitEntity.getUserName());
		
		String sql = MapAssemForSql.getInsertSql(TableConsts.CUST_LIMIT, setMap);
		//记录日志
				RecordUtils.writeAction(logger, null, sql);
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new UpdateCreditException();
		}
	}

	

}
