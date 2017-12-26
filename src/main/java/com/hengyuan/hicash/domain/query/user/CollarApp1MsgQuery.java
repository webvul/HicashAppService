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
import com.hengyuan.hicash.parameters.response.user.CollarApp1MsgResp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * 手机app白领我的资料查询1dao
 * 
 * @author LiHua.Ren
 * @create date 2015-06-17
 *
 */
public class CollarApp1MsgQuery extends AbstractDAO<CollarApp1MsgResp> {
	
	private static Logger logger = Logger.getLogger(CollarApp1MsgQuery.class);

	@Override
	public CollarApp1MsgResp mapping(ResultSet rs) throws SQLException {
		CollarApp1MsgResp resp = new CollarApp1MsgResp();
		if(rs != null){
			resp.setMaritalStatus(StringUtils.valueOf(rs.getObject("marital_Status")));
			resp.setSpouseName(StringUtils.valueOf(rs.getObject("spouse_Name")));
			resp.setSpouseMobile(StringUtils.valueOf(rs.getObject("spouse_Mobile")));
			resp.setSchool(StringUtils.valueOf(rs.getObject("graduated_School")));
			resp.setFulltime(StringUtils.valueOf(rs.getObject("fullTime_flag")));
			resp.setEducation(StringUtils.valueOf(rs.getObject("now_education")));
			if(org.apache.commons.lang.StringUtils.isEmpty(StringUtils.valueOf(rs.getObject("end_school_time")))){
				resp.setGradYear(StringUtils.valueOf(rs.getObject("end_school_time")));
				resp.setGradMonth(StringUtils.valueOf(rs.getObject("end_school_time")));
			}else{ 
				if(StringUtils.valueOf(rs.getObject("end_school_time")).length()>=7){
					resp.setGradYear(StringUtils.valueOf(rs.getObject("end_school_time")).substring(0,4));
					resp.setGradMonth(StringUtils.valueOf(rs.getObject("end_school_time")).substring(5,7));
				}else{
					resp.setGradYear(StringUtils.valueOf(rs.getObject("end_school_time")));
					resp.setGradMonth(StringUtils.valueOf(rs.getObject("end_school_time")));
				}
				
			}	
			resp.setHomePhoneArea(StringUtils.valueOf(rs.getObject("fixed_tel_area")));
			resp.setHomePhoneNum(StringUtils.valueOf(rs.getObject("fixed_tel")));
            resp.setQqNumber(StringUtils.valueOf(rs.getObject("qq_number")));
            
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
	
	public CollarApp1MsgResp queryCollarQureyResp(String userName) {
		
		List<String> selects = new ArrayList<String>();
		
		selects.add("marital_Status");
		selects.add("spouse_Name");
		selects.add("spouse_Mobile");
		selects.add("graduated_School");
		selects.add("fullTime_flag");
		selects.add("now_education");
		selects.add("end_school_time");
		selects.add("qq_number");//qq
		
		selects.add("fixed_tel_area");//家庭电话
		selects.add("fixed_tel");//家庭电话
		
		/* 2016-03-09新增 */
		selects.add("NATION");
		selects.add("IDCARD_VALIDITY_STARTDATE");
		selects.add("IDCARD_VALIDITY_ENDDATE");
		selects.add("IDCARD_VALIDITY");
		selects.add("borrow_money_use");
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		
		
		
		String querySql = MapAssemForSql.getSelectSql(selects, TableConsts.CUST_CUSTOMER, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, userName, querySql);
		return ConnManager.singleQuery(querySql.toString(), this);
	}

}
