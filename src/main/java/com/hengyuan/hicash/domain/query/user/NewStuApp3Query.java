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
import com.hengyuan.hicash.parameters.response.user.NewStuApp3Resp;
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
public class NewStuApp3Query extends AbstractDAO<NewStuApp3Resp> {

	private static Logger logger = Logger.getLogger(NewStuApp3Query.class);
	@Override
	public NewStuApp3Resp mapping(ResultSet rs) throws SQLException {
		NewStuApp3Resp resp = new NewStuApp3Resp();
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
					
		}else{
			return null;
		}
		return resp;
	}
	
	public NewStuApp3Resp querystuApp1QureyResp(String userName) {
		
		List<String> list = new ArrayList<String>();
		list.add("school");
		list.add("student_id");
		list.add("educational");
		list.add("faculties");
		list.add("specialty");
		list.add("admission_Time");
		list.add("userclass");
//		list.add("emergency_Job");
		list.add("fullTime_flag");
//		list.add("emergency_Adress");
		list.add("school_id");
		list.add("STU_TYPE");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("username", userName);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_CUSTOMER, map);
		//记录日志
				RecordUtils.writeAction(logger, userName, sql); 
		return ConnManager.singleQuery(sql, this);
	}

}
