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
import com.hengyuan.hicash.parameters.response.user.NewCollarApp3MsgResp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * 手机app白领我的资料查询3dao
 * 
 * @author LiHua.Ren
 * @create date 2015-06-17
 *
 */
public class NewCollarApp3MsgQuery extends AbstractDAO<NewCollarApp3MsgResp> {
	
	private static Logger logger = Logger.getLogger(NewCollarApp3MsgQuery.class);

	@Override
	public NewCollarApp3MsgResp mapping(ResultSet rs) throws SQLException {
		NewCollarApp3MsgResp resp = new NewCollarApp3MsgResp();
		if(rs != null){
			resp.setSchool(StringUtils.valueOf(rs.getObject("graduated_School")));
			resp.setFulltime(StringUtils.valueOf(rs.getObject("fullTime_flag")));
			resp.setEducation(StringUtils.valueOf(rs.getObject("now_education")));
			resp.setCustType(StringUtils.valueOf(rs.getObject("customerType")));
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
		}else{
			return null;
		}
		return resp;
	}
	
	public NewCollarApp3MsgResp queryCollarQureyResp(String userName) {
		
		List<String> selects = new ArrayList<String>();
		
		selects.add("graduated_School");
		selects.add("fullTime_flag");
		selects.add("now_education");
		selects.add("customerType");
		selects.add("end_school_time");
		
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		
		
		
		String querySql = MapAssemForSql.getSelectSql(selects, TableConsts.CUST_CUSTOMER, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, userName, querySql);
		return ConnManager.singleQuery(querySql.toString(), this);
	}

}
