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
import com.hengyuan.hicash.parameters.response.user.NewCollarApp2MsgResp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * 手机app白领我的资料查询2dao
 * 
 * @author LiHua.Ren
 * @create date 2015-06-17
 *
 */
public class NewCollarApp2MsgQuery extends AbstractDAO<NewCollarApp2MsgResp> {
	
	private static Logger logger = Logger.getLogger(NewCollarApp2MsgQuery.class);

	@Override
	public NewCollarApp2MsgResp mapping(ResultSet rs) throws SQLException {
		NewCollarApp2MsgResp resp = new NewCollarApp2MsgResp();
		if(rs != null){
			resp.setJobName(StringUtils.valueOf(rs.getObject("unitName")));
			resp.setWorkerDep(StringUtils.valueOf(rs.getObject("job_nature")));
			
			resp.setWorkerTime(StringUtils.valueOf(rs.getObject("workExperience")));
			if(org.apache.commons.lang.StringUtils.isEmpty(StringUtils.valueOf(rs.getObject("entry_time")))){
			resp.setWorkerYear(StringUtils.valueOf(rs.getObject("entry_time")));
			resp.setWorkerMonth(StringUtils.valueOf(rs.getObject("entry_time")));
		}else{
			if(StringUtils.valueOf(rs.getObject("entry_time")).length()>=7)
			{
				resp.setWorkerYear(StringUtils.valueOf(rs.getObject("entry_time")).substring(0,4));
				resp.setWorkerMonth(StringUtils.valueOf(rs.getObject("entry_time")).substring(5,7));	
				
			}else{
				resp.setWorkerYear(StringUtils.valueOf(rs.getObject("entry_time")));
				resp.setWorkerMonth(StringUtils.valueOf(rs.getObject("entry_time")));
				
			}
			
		}
			resp.setWorkProvince(StringUtils.valueOf(rs.getObject("work_Area_Province")));
			resp.setWorkCity(StringUtils.valueOf(rs.getObject("work_Area_City")));
			resp.setWorkArea(StringUtils.valueOf(rs.getObject("work_Area_Area")));
			resp.setWorkRoad(StringUtils.valueOf(rs.getObject("work_Area_Road")));		
			resp.setExpressProvince(StringUtils.valueOf(rs.getObject("EXPRESS_PROVINCE")));
			resp.setExpressCity(StringUtils.valueOf(rs.getObject("EXPRESS_CITY")));
			resp.setExpressDistrict(StringUtils.valueOf(rs.getObject("EXPRESS_AREA")));
			resp.setExpressDetails(StringUtils.valueOf(rs.getObject("EXPRESS_DETAIL")));
			
			resp.setExpressType(StringUtils.valueOf(rs.getObject("EXPRESS_ADDRESSTYPE")));
			
			resp.setUnitPhoneArea(StringUtils.valueOf(rs.getObject("unit_tel_area")));
			resp.setUnitPhoneNum(StringUtils.valueOf(rs.getObject("unit_tel")));
			resp.setJobduties(StringUtils.valueOf(rs.getObject("job_duties")));
			resp.setCompanyScale(StringUtils.valueOf(rs.getObject("COMPANY_SCALE")));
			resp.setCompanyWorkYear(StringUtils.valueOf(rs.getObject("COMPANY_WORK_YEAR")));
		}else{
			return null;
		}
		return resp;
	}
	
	public NewCollarApp2MsgResp queryCollarQureyResp(String userName) {
		
		List<String> selects = new ArrayList<String>();
		
		selects.add("unitName");
		selects.add("job_nature");
		selects.add("workExperience");
		selects.add("entry_time");
		selects.add("work_Area_Province");
		selects.add("work_Area_City");
		selects.add("work_Area_Area");
		selects.add("work_Area_Road");
		
		selects.add("EXPRESS_PROVINCE");
		selects.add("EXPRESS_CITY");
		selects.add("EXPRESS_AREA");
		
		
		selects.add("EXPRESS_DETAIL");
		selects.add("EXPRESS_ADDRESSTYPE");
		selects.add("unit_tel_area");
		selects.add("unit_tel");
		selects.add("job_duties");
		selects.add("COMPANY_SCALE");
		selects.add("COMPANY_WORK_YEAR");
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		
		
		
		String querySql = MapAssemForSql.getSelectSql(selects, TableConsts.CUST_CUSTOMER, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, userName, querySql);
		return ConnManager.singleQuery(querySql.toString(), this);
	}

}
