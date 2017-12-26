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
import com.hengyuan.hicash.parameters.response.user.StuApp3Resp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * hicash手机端学生提现申请3查询Dao
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuApp3Query extends AbstractDAO<StuApp3Resp> {

	private static Logger logger = Logger.getLogger(StuApp3Query.class);
	@Override
	public StuApp3Resp mapping(ResultSet rs) throws SQLException {
		StuApp3Resp resp = new StuApp3Resp();
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

		}else{
			return null;
		}
		return resp;
	}
	
	public StuApp3Resp queryStuApp3Resp(String userName) {
		
		List<String> list = new ArrayList<String>();
		
		
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
