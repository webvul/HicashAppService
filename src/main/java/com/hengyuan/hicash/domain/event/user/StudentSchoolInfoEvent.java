package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.SaveInfoException;
import com.hengyuan.hicash.parameters.request.user.StudentSchoolInfoReq;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 学生完善资料Dao(修改学校信息)
 * 
 * @author Cary.Liu
 * @create date 2014-07-16
 */
public class StudentSchoolInfoEvent {
	
	private static Logger logger = Logger.getLogger(StudentSchoolInfoEvent.class);

	/**
	 * 修改学校信息
	 * 
	 * @param updateMsgReq
	 * @return
	 * @throws SaveInfoException 
	 */
	public void updateSchoolInfo(StudentSchoolInfoReq updateMsgReq) 
			throws SaveInfoException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("STU_TYPE", updateMsgReq.getStuType());
		setMap.put("school_id", updateMsgReq.getSchoolId());
		setMap.put("school", updateMsgReq.getIptSchoolName());
		setMap.put("fullTime_flag", updateMsgReq.getSchoolSystem());
		setMap.put("educational", updateMsgReq.getEducationBk());
		setMap.put("admission_Time", updateMsgReq.getStudyTimeYear() + "-" + updateMsgReq.getStudyTimeMonth());
		setMap.put("USERCLASS", updateMsgReq.getSltGrade());
		setMap.put("student_id", updateMsgReq.getIptStuId());
		setMap.put("faculties", updateMsgReq.getIptStuDepartment());
		setMap.put("specialty", updateMsgReq.getIptStuMajor());
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", updateMsgReq.getUserName());
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_CUSTOMER, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, updateMsgReq.getUuid(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new SaveInfoException();
		}
	}

	
	
	/* 
	 * 一期客户信息单独保存暂不开放
	 * add by Andy.Niu 2014-08-09
	 *  */
	
	
	/*
	*//**
	 * 更新用户的学校信息 cust_school_info
	 * 
	 * @param updateMsgReq
	 * @param 
	 * @throws UpdateCustShcoolException 
	 *//*
	public void updateCustSchoolInfo(StudentSchoolInfoReq req) 
			throws UpdateCustShcoolException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("school_name", req.getIptSchoolName());
		setMap.put("educational_System", req.getSchoolSystem());
		setMap.put("educational_background", req.getEducationBk());
		setMap.put("admission_time", req.getStudyTimeYear() + "-" + req.getStudyTimeMonth());
		setMap.put("grade", req.getSltGrade());
		setMap.put("student_id", req.getIptStuId());
		setMap.put("department", req.getIptStuDepartment());
		setMap.put("major", req.getIptStuMajor());
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", req.getUserName());
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_SCHOOL_INFO, setMap, whereMap);
		
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateCustShcoolException();
		}
	}
	
	*//**
	 * 保存用户的学校信息
	 * @param req
	 * @return
	 * @throws SaveUniversityException 
	 *//*
	public void saveSchoolInfo(StudentSchoolInfoReq req) throws SaveUniversityException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("username", req.getUserName());
		setMap.put("school_name", req.getIptSchoolName());
		setMap.put("educational_System", req.getSchoolSystem());
		setMap.put("educational_background", req.getEducationBk());
		setMap.put("admission_time", req.getStudyTimeYear() + "-" + req.getStudyTimeMonth());
		setMap.put("grade", req.getSltGrade());
		setMap.put("student_id", req.getIptStuId());
		setMap.put("department", req.getIptStuDepartment());
		setMap.put("major", req.getIptStuMajor());
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.CUST_SCHOOL_INFO, setMap);
		
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new SaveUniversityException();
		}
	}*/
	

}
