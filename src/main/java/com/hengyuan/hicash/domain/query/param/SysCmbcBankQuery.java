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
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.entity.param.SysCmbcEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
/**
 * 查询中投支持银行
 * @author leaf.Ren
 * @create 2016-08-01
 *
 */
public class SysCmbcBankQuery  extends AbstractDAO<SysCmbcEntity> {

	private List<String> column = new ArrayList<String>();
	
	public SysCmbcBankQuery(){
		
		column.add("bankCode");
		column.add("bankName");
		column.add("isEnable");
	}
	
	@Override
	public SysCmbcEntity mapping(ResultSet rs) throws SQLException {

		SysCmbcEntity entity = null;
		if(rs != null){
			entity = new SysCmbcEntity();
			entity.setBankCode(rs.getString("bankCode"));
			entity.setBankName(rs.getString("bankName"));
			entity.setIsEnable(rs.getString("isEnable"));
		}
		
		return entity;
	}

	
	/**
	 * 根据bankCode查询bankName
	 * @param bankCode 银行编码
	 * @param whichPart
	 * @return
	 */
	public SysCmbcEntity queryZTBankName(String bankCode,String whichPart){
		
		Map<String , Object> whereMap = new HashMap<String, Object>();
		whereMap.put("bankcode", bankCode);
		whereMap.put("whichPart", whichPart);
		whereMap.put("isEnable", 0);
		String querySql = MapAssemForSql.getSelectSql(column, TableConsts.SYS_CMBC_BANK, whereMap);
		return ConnManager.singleQuery(querySql, this);
	}
}
