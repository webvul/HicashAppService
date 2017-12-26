package com.hengyuan.hicash.domain.query.mktApp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.app.CustPayAccount;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.SqlAssemblyUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * @author Mary.Luo
 *
 */
public class CustPayAccountQuery extends AbstractDAO<CustPayAccount> {
	
	private static Logger logger = Logger.getLogger(CustPayAccountQuery.class);

	@Override
	public CustPayAccount mapping(ResultSet rs) throws SQLException {
		CustPayAccount custPayAccount = new CustPayAccount();

		if (rs != null) {
			custPayAccount.setInvestFlag(Integer.parseInt(rs
					.getString("INVEST_FLAG")));
		}else{
			custPayAccount=null;
		}
		return custPayAccount;
	}
	
	/**
	 * 根据applicationPay查询是否放入5i5dai
	 * @param appPay
	 * @return
	 */
	public CustPayAccount getPayAccount(ApplicationPay  appPay) {

		/** 首付支付比例 */
		String payType = appPay.getPayType()!=null ? appPay.getPayType() : "NORMAL";
		
		String productType = StringUtils.showFlag(appPay.getProductType()) ? "TS"
				: "SM";
		String nation = appPay.getNation();
		try {
			StringBuffer querySql = new StringBuffer("select * from cust_pay_account where 1 = 1 ");
			querySql.append(SqlAssemblyUtils.receiveEqualSign("city", appPay.getCityCode(), false));
			querySql.append(SqlAssemblyUtils.receiveEqualSign("cust_Type", appPay.getCustomerType(), false));
			querySql.append(SqlAssemblyUtils.receiveEqualSign("status", "1", false));
			querySql.append(SqlAssemblyUtils.receiveEqualSign("pay_Type", payType, false));
			querySql.append(SqlAssemblyUtils.receiveEqualSign("pro_Type", productType, false));
			if(nation!=null && !"".equals(nation)){
				querySql.append(SqlAssemblyUtils.receiveEqualSign("nation", nation, false));
			}else{
				querySql.append(" and (nation is null or nation = '')");
			}
			
			RecordUtils.writeAction(logger, appPay.getUsername(), querySql.toString());
			return ConnManager.singleQuery(querySql.toString(), this);
		} catch (Exception e) {
			
			StringBuffer querySql = new StringBuffer("select * from cust_pay_account where 1 = 1 ");
			
			querySql.append(SqlAssemblyUtils.receiveEqualSign("city", "000000", false));
			querySql.append(SqlAssemblyUtils.receiveEqualSign("cust_Type", appPay.getCustomerType(), false));
			querySql.append(SqlAssemblyUtils.receiveEqualSign("status", "1", false));
			querySql.append(SqlAssemblyUtils.receiveEqualSign("pay_Type", payType, false));
			querySql.append(SqlAssemblyUtils.receiveEqualSign("pro_Type", productType, false));
			querySql.append(SqlAssemblyUtils.receiveEqualSign("nation", nation, true));
			
			RecordUtils.writeAction(logger, appPay.getUsername(), querySql.toString());
			return ConnManager.singleQuery(querySql.toString(), this);
			
		}
	}
	
}
