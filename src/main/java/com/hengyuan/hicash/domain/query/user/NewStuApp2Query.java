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
import com.hengyuan.hicash.parameters.response.user.NewStuApp2Resp;
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
public class NewStuApp2Query extends AbstractDAO<NewStuApp2Resp> {

	private static Logger logger = Logger.getLogger(NewStuApp2Query.class);
	@Override
	public NewStuApp2Resp mapping(ResultSet rs) throws SQLException {
		NewStuApp2Resp resp = new NewStuApp2Resp();
		if(rs != null){
			
			resp.setNameSala(StringUtils.valueOf(rs.getObject("immediate_Name")));
			resp.setTionSala(StringUtils.valueOf(rs.getObject("immediate_Relation")));
			resp.setMobileSala(StringUtils.valueOf(rs.getObject("immediate_Mobile")));
			resp.setWorkUnitsSala(StringUtils.valueOf(rs.getObject("immediate_Job")));
			resp.setAddressSala(StringUtils.valueOf(rs.getObject("immediate_Adress")));
			resp.setRelaName(StringUtils.valueOf(rs.getObject("emergency_Name")));
			resp.setRelation(StringUtils.valueOf(rs.getObject("emergency_Relation")));
//			resp.setRelaWorkUnits(StringUtils.valueOf(rs.getObject("emergency_Job")));
			resp.setRelaMobile(StringUtils.valueOf(rs.getObject("emergency_Mobile")));
//			resp.setRelaAddress(StringUtils.valueOf(rs.getObject("emergency_Adress")));

			resp.setCustType(StringUtils.valueOf(rs.getObject("customerType")));	
			resp.setSpouseName(StringUtils.valueOf(rs.getObject("spouse_Name")));
			resp.setSpouseMobile(StringUtils.valueOf(rs.getObject("spouse_Mobile")));	
		}else{
			return null;
		}
		return resp;
	}
	
	public NewStuApp2Resp querystuApp1QureyResp(String userName) {
		
		List<String> list = new ArrayList<String>();
		list.add("spouse_Name");
		list.add("spouse_Mobile");
		list.add("immediate_Name");
		list.add("immediate_Relation");
		list.add("immediate_Mobile");
		list.add("immediate_Job");
		list.add("immediate_Adress");
		list.add("emergency_Name");
		list.add("emergency_Relation");
//		list.add("emergency_Job");
		list.add("emergency_Mobile");
//		list.add("emergency_Adress");
		list.add("customerType");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("username", userName);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_CUSTOMER, map);
		//记录日志
				RecordUtils.writeAction(logger, userName, sql); 
		return ConnManager.singleQuery(sql, this);
	}

}
