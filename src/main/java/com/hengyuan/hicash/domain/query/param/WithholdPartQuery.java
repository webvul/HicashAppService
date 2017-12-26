package com.hengyuan.hicash.domain.query.param;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.WhichPartRecord;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.StringUtils;

public class WithholdPartQuery extends AbstractDAO<WhichPartRecord> {

	private List<String> column = new ArrayList<String>();
	
	public WithholdPartQuery(){
		
		column.add("username");
		column.add("withhold_part");
		column.add("create_time");
		column.add("id");
	}
	
	@Override
	public WhichPartRecord mapping(ResultSet rs) throws SQLException {

		WhichPartRecord entity = null;
		if(rs != null){
			entity = new WhichPartRecord();
			entity.setUserName(StringUtils.valueOf(rs.getObject("username")));
			entity.setWhichPart(StringUtils.valueOf(rs.getObject("withhold_part")));
			entity.setCreateTime(StringUtils.valueOf(rs.getObject("create_time")));
		}
		
		return entity;
	}

	
	
	/**
	 * 獲取代扣記錄
	 * @param userName 用戶名
	 * @param whichPart 代扣方
	 * @return
	 */
	public List<WhichPartRecord> queryWhichPart(String userName,String whichPart,String bankCardNo){
		
		Map<String , Object> whereMap = new HashMap<String, Object>();
		whereMap.put("withhold_part", whichPart);
		whereMap.put("username", userName);
		whereMap.put("bank_card_num", bankCardNo);
		String querySql = MapAssemForSql.getSelectSql(column, TableConsts.WITHHOLD_PART_RECORD, whereMap);
		
		return ConnManager.executeQuery(querySql.toString(), this);
	}
	
	/**
	 * 根据用户名,以及银行卡号查询数据
	 * @param userName 用戶名
	 * @param whichPart 代扣方
	 * @return
	 */
	public List<WhichPartRecord> queryWhichPartByUserBank(String userName,String bankNum){
		
		Map<String , Object> whereMap = new HashMap<String, Object>();
		whereMap.put("bank_card_num", bankNum);
		whereMap.put("username", userName);
		String querySql = MapAssemForSql.getSelectSql(column, TableConsts.WITHHOLD_PART_RECORD, whereMap);
		
		return ConnManager.executeQuery(querySql.toString(), this);
	}
}
