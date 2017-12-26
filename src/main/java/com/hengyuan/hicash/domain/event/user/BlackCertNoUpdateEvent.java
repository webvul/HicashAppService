package com.hengyuan.hicash.domain.event.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.SaveTongDunBlackException;
import com.hengyuan.hicash.exception.UpdateAmountException;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * @author Administrator
 *
 */
public class BlackCertNoUpdateEvent {
	private static Logger logger = Logger.getLogger(BlackCertNoUpdateEvent.class);
	public void saveIdentity(String identityno)
			throws UpdateAmountException {

		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("identityNo", identityno);
		setMap.put("create_date",DateUtils.getDateStr(new Date()));
		setMap.put("origin", "FRBC");
		setMap.put("reason", "身份证号命中黑名单");
		

		String updateSql =  MapAssemForSql.getInsertSql(TableConsts.BLACK_LIST, setMap);
		
		RecordUtils.writeAction(logger, identityno, updateSql);
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new UpdateAmountException();
		}
	}
	
	public void saveTongDun(String identityno,String mobileNo,String userName)
			throws  SaveTongDunBlackException {

		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("identityNo", identityno);
		setMap.put("create_date",DateUtils.getDateStr(new Date()));
		setMap.put("origin", "TDFX");
		setMap.put("username", userName);
		setMap.put("reason", "命中同盾风险决策");
		setMap.put("mobileNo", mobileNo);

		

		String updateSql =  MapAssemForSql.getInsertSql(TableConsts.BLACK_LIST, setMap);
		
		RecordUtils.writeAction(logger, identityno, updateSql);
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new SaveTongDunBlackException();
		}
	}

}
