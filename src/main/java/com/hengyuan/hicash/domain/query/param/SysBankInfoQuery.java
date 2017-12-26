package com.hengyuan.hicash.domain.query.param;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.BankBranchEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 获取系统支行 Dao
 * @author Cary.Liu
 * @createDate 2015-02-05
 *
 */
public class SysBankInfoQuery extends AbstractDAO<BankBranchEntity> {
	
	private static Logger logger = Logger.getLogger(SysBankInfoQuery.class);

	@Override
	public BankBranchEntity mapping(ResultSet rs) throws SQLException {

		BankBranchEntity entity = null;
		
		if(rs != null){
			entity = new BankBranchEntity();
			entity.setBankNum(StringUtils.valueOf(rs.getString("banknum")));
			String bankNo = StringUtils.valueOf(rs.getString("bankno"));
			if(bankNo != null){
				entity.setBankNo(bankNo.trim());
			}else{
				entity.setBankNo("");
			}
			entity.setBankName(StringUtils.valueOf(rs.getString("bankname")));
			entity.setBankCityCode(StringUtils.valueOf(rs.getString("bankcitycode")));
		}
		
		return entity;
	}

	public BankBranchEntity queryBankBranch(String bankNo,String bankNum){
		
		String querySql = "SELECT id,banknum,bankno,bankfathernum,bankname,bankcitycode FROM sys_bankinfo "
				+ " WHERE bankno = '"+ bankNo +"' AND banknum = '"+ bankNum +"'";
		
		RecordUtils.writeAction(logger, null, querySql);
		return ConnManager.singleQuery(querySql, this);
	}
	
	/**
	 * 查询支行和父行是否匹配
	 * @param bankNum
	 * @param bankNo
	 * @return
	 */
	public BankBranchEntity queryBankInfoByFatherCode(String bankNum,String bankNo){
		
		String querySql = "SELECT id,banknum,bankno,bankfathernum,bankname,bankcitycode FROM sys_bankinfo "
				+ " WHERE bankno = '"+ bankNo +"' AND banknum = '"+ bankNum +"'";
		
		return ConnManager.singleQuery(querySql, this);
	}
	
	/**
	 * 根据支行查询支行信息
	 * @param bankNum
	 * @param bankNo
	 * @return
	 */
	public BankBranchEntity queryBankInfoByNum(String bankNum){
		
		String querySql = "SELECT id,banknum,bankno,bankfathernum,bankname,bankcitycode FROM sys_bankinfo "
				+ " WHERE banknum = '"+ bankNum +"'";
		
		return ConnManager.singleQuery(querySql, this);
	}
	
}
