package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.SaveInfoException;
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
public class StuApp1UpdateEvent {
	private static Logger logger = Logger.getLogger(StuApp1UpdateEvent.class);

	/**
	 * 修改学校信息
	 * 
	 * @param updateMsgReq
	 * @return
	 * @throws SaveInfoException 
	 */
	public void updateStuApp1(StuApp1UpdateReq updateMsgReq) 
			throws SaveInfoException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("STU_TYPE", updateMsgReq.getStuType());
		setMap.put("school_id", updateMsgReq.getSchoolId());
		setMap.put("school", updateMsgReq.getIptSchoolName());//学校名称
		setMap.put("fullTime_flag", updateMsgReq.getSchoolSystem());//
		setMap.put("educational", updateMsgReq.getEducationBk());//xueli
		setMap.put("admission_Time", updateMsgReq.getStudyTimeYear() + "-" + updateMsgReq.getStudyTimeMonth());
		setMap.put("USERCLASS", updateMsgReq.getSltGrade());
		setMap.put("student_id", updateMsgReq.getIptStuId());//学号
		setMap.put("faculties", updateMsgReq.getIptStuDepartment());//学院
		setMap.put("specialty", updateMsgReq.getIptStuMajor());//专业
		setMap.put("qq_number", updateMsgReq.getQqNumber());//qq
		setMap.put("qqNumber", updateMsgReq.getQqNumber());
		setMap.put("fixed_tel", updateMsgReq.getHomePhoneNum());//家庭电话
		setMap.put("fixed_tel_area", updateMsgReq.getHomePhoneArea());
		
		/* 2016-03-10屏蔽 本次不上线 */
		setMap.put("NATION", updateMsgReq.getNation());
		setMap.put("IDCARD_VALIDITY_STARTDATE", updateMsgReq.getIdCardValStartDate());
		setMap.put("IDCARD_VALIDITY_ENDDATE", updateMsgReq.getIdCardValEndDate());
		setMap.put("IDCARD_VALIDITY", updateMsgReq.getIdCardValidity());
		
		setMap.put("borrow_money_use", updateMsgReq.getLoanUse());
		
		//学校地址
//		setMap.put("school_Area_Province", updateMsgReq.getSchoolProvince());
//		setMap.put("school_Area_City", updateMsgReq.getSchoolCity());
//		setMap.put("school_Area_Area", updateMsgReq.getSchoolArea());
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
