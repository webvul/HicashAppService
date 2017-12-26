package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.SaveInfoException;
import com.hengyuan.hicash.parameters.request.user.NewStuApp1UpdateReq;
import com.hengyuan.hicash.parameters.request.user.NewStuApp2UpdateReq;
import com.hengyuan.hicash.parameters.request.user.StuApp1UpdateReq;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * hicash手机端学生提现申请1完善Dao
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class NewStuApp2UpdateEvent {
	private static Logger logger = Logger.getLogger(NewStuApp2UpdateEvent.class);

	/**
	 * 修改学校信息
	 * 
	 * @param updateMsgReq
	 * @return
	 * @throws SaveInfoException 
	 */
	public void updateStuApp1(NewStuApp2UpdateReq updateMsgReq) 
			throws SaveInfoException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("immediate_Name", updateMsgReq.getFamilyName());
		setMap.put("immediate_Relation", updateMsgReq.getFamilyRelation());
		setMap.put("immediate_Job", updateMsgReq.getFamilyWorkUnit());
		setMap.put("immediate_Mobile", updateMsgReq.getFamilyPhone());
		setMap.put("immediate_Adress", updateMsgReq.getFamilyAddress());
		setMap.put("emergency_Name", updateMsgReq.getContactName());
		setMap.put("emergency_Relation", updateMsgReq.getContactRelation());
//		setMap.put("emergency_Job", updateMsgReq.getContactWorkUnit());
		setMap.put("emergency_Mobile", updateMsgReq.getContactPhone());
		if(updateMsgReq.getSpouseName()!=null||updateMsgReq.getSpouseMobile()!=null){
		setMap.put("spouse_Name", updateMsgReq.getSpouseName());
		setMap.put("spouse_Mobile", updateMsgReq.getSpouseMobile());
		}
//		setMap.put("emergency_Adress", updateMsgReq.getContactAddress());
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", updateMsgReq.getUserName());
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_CUSTOMER, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, updateMsgReq.getUuid(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new SaveInfoException();
		}
	}
	
}
