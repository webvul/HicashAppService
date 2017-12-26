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
import com.hengyuan.hicash.parameters.response.user.NewCollarApp1MsgResp;
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
public class NewCollarApp1MsgQuery extends AbstractDAO<NewCollarApp1MsgResp> {
	
	private static Logger logger = Logger.getLogger(NewCollarApp1MsgQuery.class);

	@Override
	public NewCollarApp1MsgResp mapping(ResultSet rs) throws SQLException {
		NewCollarApp1MsgResp resp = new NewCollarApp1MsgResp();
		if(rs != null){
			resp.setMaritalStatus(StringUtils.valueOf(rs.getObject("marital_Status")));
			resp.setRealName(StringUtils.valueOf(rs.getObject("name")));
			resp.setIdentiyNo(StringUtils.valueOf(rs.getObject("identity_no")));
			resp.setHomePhoneArea(StringUtils.valueOf(rs.getObject("fixed_tel_area")));
			resp.setHomePhoneNum(StringUtils.valueOf(rs.getObject("fixed_tel")));
            resp.setQqNumber(StringUtils.valueOf(rs.getObject("qq_number")));
            
            resp.setNation(StringUtils.valueOf(rs.getObject("NATION"))); // 民族
			resp.setIdCardValStartDate(StringUtils.valueOf(rs.getObject("IDCARD_VALIDITY_STARTDATE"))); // 身份证有效期开始时间
			resp.setIdCardValEndDate(StringUtils.valueOf(rs.getObject("IDCARD_VALIDITY_ENDDATE"))); // 身份证有效期结束时间
			resp.setIdCardValidity(StringUtils.valueOf(rs.getObject("IDCARD_VALIDITY"))); // 份证有效期(期限)
			resp.setLoanUse(StringUtils.valueOf(rs.getObject("borrow_money_use")));
			
			
			resp.setExpressType(StringUtils.valueOf(rs.getObject("EXPRESS_ADDRESSTYPE")));
			resp.setFimilyPro(StringUtils.valueOf(rs.getObject("fimily_Area_Province")));
			resp.setFimilyCity(StringUtils.valueOf(rs.getObject("fimily_Area_City")));
			resp.setFimilyArea(StringUtils.valueOf(rs.getObject("fimily_Area_Area")));
			resp.setFimilyRoad(StringUtils.valueOf(rs.getObject("fimily_Area_Road")));	
			resp.setMonthlyConsumption(StringUtils.valueOf(rs.getObject("monthlyConsumption")));
			resp.setCustType(StringUtils.valueOf(rs.getObject("customerType")));
		}else{
			return null;
		}
		return resp;
	}
	
	public NewCollarApp1MsgResp queryCollarQureyResp(String userName) {
		
		List<String> selects = new ArrayList<String>();
		
		selects.add("marital_Status");
		selects.add("name");
		selects.add("identity_no");
		selects.add("fixed_tel_area");
		selects.add("fixed_tel");
		selects.add("qq_number");
		selects.add("NATION");
		selects.add("IDCARD_VALIDITY_STARTDATE");
		
		selects.add("IDCARD_VALIDITY_ENDDATE");
		selects.add("IDCARD_VALIDITY");
		selects.add("customerType");
		
		/* 2016-03-09新增 */
		selects.add("borrow_money_use");
		selects.add("EXPRESS_ADDRESSTYPE");
		selects.add("fimily_Area_Province");
		selects.add("fimily_Area_City");
		selects.add("fimily_Area_Area");
		selects.add("fimily_Area_Road");
		selects.add("monthlyConsumption");
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		
		
		
		String querySql = MapAssemForSql.getSelectSql(selects, TableConsts.CUST_CUSTOMER, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, userName, querySql);
		return ConnManager.singleQuery(querySql.toString(), this);
	}

}
