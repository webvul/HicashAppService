package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.parameters.response.user.StuApp1Resp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 学生我的资料查询dao
 * 
 * @author Eric
 * @create date 2014-07-21
 *
 */
public class StuApp1Query extends AbstractDAO<StuApp1Resp> {

	private static Logger logger = Logger.getLogger(StuApp1Query.class);
	@Override
	public StuApp1Resp mapping(ResultSet rs) throws SQLException {
		StuApp1Resp resp = new StuApp1Resp();
		if(rs != null){
			
			resp.setSchoolName(StringUtils.valueOf(rs.getObject("school")));
			resp.setStudentId(StringUtils.valueOf(rs.getObject("student_id")));
			resp.setEducational(StringUtils.valueOf(rs.getObject("educational")));
			resp.setFaculties(StringUtils.valueOf(rs.getObject("faculties")));
			resp.setSpecialty(StringUtils.valueOf(rs.getObject("specialty")));
			if(org.apache.commons.lang.StringUtils.isEmpty(StringUtils.valueOf(rs.getObject("admission_Time")))){
				resp.setEntryYear(StringUtils.valueOf(rs.getObject("admission_Time")));
				resp.setEntryMonth(StringUtils.valueOf(rs.getObject("admission_Time")));
			}else{
				resp.setEntryYear(StringUtils.valueOf(rs.getObject("admission_Time")).substring(0,4));
				resp.setEntryMonth(StringUtils.valueOf(rs.getObject("admission_Time")).substring(5,7));
			}
			
			resp.setStudentClass(StringUtils.valueOf(rs.getObject("userclass")));
			resp.setFulltime(StringUtils.valueOf(rs.getObject("fullTime_flag")));
			resp.setSchoolid(StringUtils.valueOf(rs.getObject("school_id")));	
			resp.setStuType(StringUtils.valueOf(rs.getObject("STU_TYPE")));
			resp.setCustType(StringUtils.valueOf(rs.getObject("customerType")));
			resp.setEmailAdress(StringUtils.valueOf(rs.getObject("email_adress")));
//			resp.setSchoolPro(StringUtils.valueOf(rs.getObject("school_Area_Province")));
//			resp.setSchoolCity(StringUtils.valueOf(rs.getObject("school_Area_City")));
			resp.setQqNumber(StringUtils.valueOf(rs.getObject("qq_number")));
			resp.setHomePhoneArea(StringUtils.valueOf(rs.getObject("fixed_tel_area")));
			resp.setHomePhoneNum(StringUtils.valueOf(rs.getObject("fixed_tel")));

			resp.setNation(StringUtils.valueOf(rs.getObject("NATION"))); // 民族
			resp.setIdCardValStartDate(StringUtils.valueOf(rs.getObject("IDCARD_VALIDITY_STARTDATE"))); // 身份证有效期开始时间
			resp.setIdCardValEndDate(StringUtils.valueOf(rs.getObject("IDCARD_VALIDITY_ENDDATE"))); // 身份证有效期结束时间
			resp.setIdCardValidity(StringUtils.valueOf(rs.getObject("IDCARD_VALIDITY"))); // 份证有效期(期限)
			resp.setLoanUse(StringUtils.valueOf(rs.getObject("borrow_money_use")));
					
		}else{
			return null;
		}
		return resp;
	}
	
	public StuApp1Resp querystuApp1QureyResp(String userName) {
		
		List<String> list = new ArrayList<String>();
		list.add("school");
		list.add("student_id");
		list.add("educational");
		list.add("faculties");
		list.add("specialty");
		list.add("admission_Time");
		list.add("userclass");
		list.add("fullTime_flag");
		list.add("STU_TYPE");
		list.add("email_adress");
		list.add("school_id");
		list.add("customerType");
//		list.add("school_Area_Province");
//		list.add("school_Area_City");
		list.add("qq_number");
		list.add("fixed_tel");
		list.add("fixed_tel_area");
		
		/* 2016-03-09新增 */
		list.add("NATION");
		list.add("IDCARD_VALIDITY_STARTDATE");
		list.add("IDCARD_VALIDITY_ENDDATE");
		list.add("IDCARD_VALIDITY");
		list.add("borrow_money_use");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("username", userName);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_CUSTOMER, map);
		//记录日志
				RecordUtils.writeAction(logger, userName, sql); 
		return ConnManager.singleQuery(sql, this);
	}

}
