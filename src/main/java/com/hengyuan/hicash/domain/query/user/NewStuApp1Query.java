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
import com.hengyuan.hicash.parameters.response.user.NewStuApp1Resp;
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
public class NewStuApp1Query extends AbstractDAO<NewStuApp1Resp> {

	private static Logger logger = Logger.getLogger(NewStuApp1Query.class);
	@Override
	public NewStuApp1Resp mapping(ResultSet rs) throws SQLException {
		NewStuApp1Resp resp = new NewStuApp1Resp();
		if(rs != null){
			
			resp.setRealName(StringUtils.valueOf(rs.getObject("name")));
			resp.setIdentiyNo(StringUtils.valueOf(rs.getObject("identity_no")));
			resp.setFimilyPro(StringUtils.valueOf(rs.getObject("fimily_Area_Province")));
			resp.setFimilyCity(StringUtils.valueOf(rs.getObject("fimily_Area_City")));
			resp.setFimilyArea(StringUtils.valueOf(rs.getObject("fimily_Area_Area")));
			resp.setFimilyRoad(StringUtils.valueOf(rs.getObject("fimily_Area_Road")));	
			resp.setSchoolPro(StringUtils.valueOf(rs.getObject("school_Area_Province")));
			resp.setSchoolCity(StringUtils.valueOf(rs.getObject("school_Area_City")));
			resp.setSchoolArea(StringUtils.valueOf(rs.getObject("school_Area_Area")));
			resp.setSchoolRoad(StringUtils.valueOf(rs.getObject("school_Area_Road")));
			resp.setCustType(StringUtils.valueOf(rs.getObject("customerType")));	
			resp.setExpressProvince(StringUtils.valueOf(rs.getObject("EXPRESS_PROVINCE")));
			resp.setExpressCity(StringUtils.valueOf(rs.getObject("EXPRESS_CITY")));
			resp.setExpressDistrict(StringUtils.valueOf(rs.getObject("EXPRESS_AREA")));
			resp.setExpressDetails(StringUtils.valueOf(rs.getObject("EXPRESS_DETAIL")));
//			resp.setExpressType(StringUtils.valueOf(rs.getObject("EXPRESS_PROVINCE")));
			
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
	
	public NewStuApp1Resp querystuApp1QureyResp(String userName) {
		
		List<String> list = new ArrayList<String>();
		list.add("name");
		list.add("identity_no");
		list.add("fimily_Area_Province");
		list.add("fimily_Area_City");
		list.add("fimily_Area_Area");
		list.add("fimily_Area_Road");
		list.add("school_Area_Province");
		list.add("school_Area_City");
		list.add("school_Area_Area");
		list.add("school_Area_Road");
		list.add("customerType");
		list.add("qq_number");
		list.add("fixed_tel");
		list.add("fixed_tel_area");
		list.add("EXPRESS_PROVINCE");
		list.add("EXPRESS_CITY");
		list.add("EXPRESS_AREA");
		list.add("EXPRESS_DETAIL");
		
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
