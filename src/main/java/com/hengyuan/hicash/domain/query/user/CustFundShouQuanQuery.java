package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CustFundShouquanEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class CustFundShouQuanQuery extends AbstractDAO<CustFundShouquanEntity>{
	private static Logger logger = Logger.getLogger(CustFundShouQuanQuery.class);
	
	
	@Override
	public CustFundShouquanEntity mapping(ResultSet rs) throws SQLException {
		 
		CustFundShouquanEntity hyRecord = new CustFundShouquanEntity();
		
		if(rs!=null){
			hyRecord.setCreate_time(StringUtils.valueOf(rs.getObject("create_time")));
			hyRecord.setId_no(StringUtils.valueOf(rs.getObject("id_no")));
			hyRecord.setShou_quan(StringUtils.valueOf(rs.getObject("shou_quan")));
			hyRecord.setName(StringUtils.valueOf(rs.getObject("name")));
			hyRecord.setId(StringUtils.valueOf(rs.getObject("id")));
			hyRecord.setType(StringUtils.valueOf(rs.getObject("type")));
			
			
			return hyRecord;
		}else{
			
			System.out.println("查询的结果集为空");
			return null;
			
		}
	}
	
	/**
	 * 根据身份证号查询公积金记录
	 * @param realName
	 * @param identity
	 * type1:外卖，2;公积金
	 * @return
	 */
	public CustFundShouquanEntity queryGJJIdNo(String idNo,String uuid){
		
      StringBuffer querySql = new StringBuffer("select create_time,id_no,name,type,shou_quan,id,type from cust_fund_shouquan where 1=1 ");
     
		querySql.append(" AND id_no = '" + idNo + "'");
		querySql.append(" AND type = 'GJJ'");
	
		
		//记录日志
		RecordUtils.writeAction(logger, uuid, querySql.toString());
		
		return ConnManager.singleQuery(querySql.toString(), this);
		

	}
}
