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
import com.hengyuan.hicash.parameters.response.user.StuApp2Resp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * hicash手机端学生提现申请2Dao
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuApp2Query extends AbstractDAO<StuApp2Resp> {

	private static Logger logger = Logger.getLogger(StuApp2Query.class);
	@Override
	public StuApp2Resp mapping(ResultSet rs) throws SQLException {
		StuApp2Resp resp = new StuApp2Resp();
		if(rs != null){
			
			
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
			
			resp.setExpressType(StringUtils.valueOf(rs.getObject("EXPRESS_ADDRESSTYPE")));
		}else{
			return null;
		}
		return resp;
	}
	
	public StuApp2Resp queryStuApp2Resp(String userName) {
		
		List<String> list = new ArrayList<String>();
		
		list.add("fimily_Area_Province");
		list.add("fimily_Area_City");
		list.add("fimily_Area_Area");
		list.add("fimily_Area_Road");
		
		list.add("school_Area_Province");
		list.add("school_Area_City");
		list.add("school_Area_Area");
		list.add("school_Area_Road");
		list.add("customerType");
		list.add("EXPRESS_ADDRESSTYPE");	
		list.add("EXPRESS_PROVINCE");
		list.add("EXPRESS_CITY");
		list.add("EXPRESS_AREA");
		list.add("EXPRESS_DETAIL");
//		list.add("now_Fimily_Adress");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("username", userName);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_CUSTOMER, map);
		//记录日志
				RecordUtils.writeAction(logger, userName, sql); 
		return ConnManager.singleQuery(sql, this);
	}

}
