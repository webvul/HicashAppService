package com.hengyuan.hicash.domain.query.mktApp;

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
import com.hengyuan.hicash.entity.app.AcctAccount;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * @author Mary.Luo
 *
 */
public class AcctAccountQuery extends AbstractDAO<AcctAccount> {
	
	private static Logger logger = Logger.getLogger(AcctAccountQuery.class);

	private List<String> list;

	public AcctAccountQuery() {
		list = new ArrayList<String>();

		list.add("ID");
	}

	@Override
	public AcctAccount mapping(ResultSet rs) throws SQLException {
		AcctAccount acctAccount = new AcctAccount();
		if (rs != null) {

			acctAccount.setId(StringUtils.valueOf(rs.getObject("ID")));
		}
		return acctAccount;
	}

	/**
	 * 根据userName查询acctAccount信息
	 * 
	 * @param userName
	 * @return
	 */
	public AcctAccount queryCustCustomer(String userName) {

		String sql = "select ID from acct_account where username='" + userName
				+ "'";
		
		RecordUtils.writeAction(logger, userName, sql);
		return ConnManager.singleQuery(sql, this);

	}

	/**
	 * 通过用户名获取acctAccount对象
	 * 
	 * @param userName
	 * @return
	 * @author Mary.Luo
	 * @create 2014-08-01
	 */
	public AcctAccount getAcctByUserName(String userName) {

		Map<String, Object> setMap = new HashMap<String, Object>();

		setMap.put("USERNAME", userName);

		StringBuffer loanQuery = new StringBuffer(MapAssemForSql.getSelectSql(
				list, TableConsts.ACC_ACCOUNT, setMap));
		RecordUtils.writeAction(logger, userName, loanQuery.toString());
		return ConnManager.singleQuery(loanQuery.toString(), this);
	}

}
