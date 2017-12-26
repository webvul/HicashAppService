package com.hengyuan.hicash.domain.query.app;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.LoanProduct;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * @author Mary.Luo
 *
 */
public class ProductQuery extends AbstractDAO<LoanProduct> {
	
	private static Logger logger = Logger.getLogger(ProductQuery.class);

	@Override
	public LoanProduct mapping(ResultSet rs) throws SQLException {
		LoanProduct loanProduct = new LoanProduct();

		if (rs != null) {

			loanProduct.setCreditRate(rs.getString("credit_rate"));
			loanProduct.setCreditPayTime(rs.getString("credit_pay_time"));
			loanProduct.setPeriod(rs.getInt("Period"));
			loanProduct.setCreditday(rs.getString("CREDIT_DAY"));
			String custRate = StringUtils.valueOf(rs.getObject("CUST_RATE"));	
			if(custRate!= null && !custRate.equals("")){
				loanProduct.setCustRate(custRate);
			}else{
				loanProduct.setCustRate(Consts.FINAL_NUMBER_0);
			}
			loanProduct.setInverstorName(rs.getString("INVESTOR_NAME"));
			loanProduct.setLoanUnit(rs.getString("loan_unit"));
			loanProduct.setIndustryCode(StringUtils.valueOf(rs.getString("INDUSTRY_CODE")));
		}
		return loanProduct;
	}
	

	
	
	
	/**
	 * 根据产品ID查询产品信息
	 */
	public LoanProduct queryCreditProductById(String productInfo){
//		Period
		String sql="select Period,credit_rate,credit_pay_time,CUST_RATE,CREDIT_DAY,INVESTOR_NAME,loan_unit," +
				"INDUSTRY_CODE from loan_product where id='"+productInfo+"'";
		
		RecordUtils.writeAction(logger, productInfo, sql);
		return ConnManager.singleQuery(sql, this);
		
	}
	
	
}
