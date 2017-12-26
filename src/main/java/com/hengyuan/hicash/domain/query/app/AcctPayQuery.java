package com.hengyuan.hicash.domain.query.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.AccountPayEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class AcctPayQuery extends AbstractDAO<AccountPayEntity> {

	private static Logger logger = Logger.getLogger(AcctPayQuery.class);
	
	@Override
	public AccountPayEntity mapping(ResultSet rs) throws SQLException {

		AccountPayEntity accountPayEntity = new AccountPayEntity();
		
		/* 判断查询结果是否存在 */
		if (rs != null) {
			accountPayEntity.setAppPayNo(StringUtils.valueOf(rs.getObject("app_application_no")));
			accountPayEntity.setAllNode(StringUtils.valueOf(rs.getObject("allnode")));
			accountPayEntity.setNode(StringUtils.valueOf(rs.getObject("node")));
			accountPayEntity.setStatus(StringUtils.valueOf(rs.getObject("status")));
			accountPayEntity.setApplyType(StringUtils.valueOf(rs.getObject("pay_type")));
			accountPayEntity.setLoanAmount(StringUtils.valueOf(rs.getObject("loanAmt")));
			accountPayEntity.setApplyAmount(StringUtils.valueOf(rs.getObject("apply_amount")));
			accountPayEntity.setProductId(StringUtils.valueOf(rs.getObject("pro_id")));
			accountPayEntity.setProductName(StringUtils.valueOf(rs.getObject("product_name")));
			accountPayEntity.setApplyDate(StringUtils.valueOf(rs.getObject("applyDate")));
			accountPayEntity.setCreateDate(StringUtils.valueOf(rs.getObject("createDate")));
			accountPayEntity.setInstallMent(StringUtils.valueOf(rs.getObject("app_install_ment")));
			accountPayEntity.setCustType(StringUtils.valueOf(rs.getObject("app_cust_type")));
			accountPayEntity.setFirstPayRate(StringUtils.valueOf(rs.getObject("first_pay_rate")));
			accountPayEntity.setMthPayFee(StringUtils.valueOf(rs.getObject("app_month_pay")));
			accountPayEntity.setFirstAmount(StringUtils.valueOf(rs.getObject("FIRST_PAY_AMOUNT")));
			accountPayEntity.setMerProId(StringUtils.valueOf(rs.getObject("MER_PRODUCTID")));
			accountPayEntity.setIndustryCode(StringUtils.valueOf(rs.getObject("HY_INDUSTRY_CODE")));
			
			accountPayEntity.setTreatyUploadFlag(StringUtils.valueOf(rs.getObject("app_treaty_upload_flag")));
			accountPayEntity.setTreatyUploadURL(StringUtils.valueOf(rs.getObject("app_treaty_upload_url")));
			
			accountPayEntity.setRejectDesc(StringUtils.valueOf(rs.getObject("REJECT_DESC")));
			accountPayEntity.setAppcheckdesc(StringUtils.valueOf(rs.getObject("app_check_desc")));
			accountPayEntity.setRejectCause(StringUtils.valueOf(rs.getObject("rejectCause")));
			accountPayEntity.setLoanProduct(StringUtils.valueOf(rs.getObject("app_creditproduct_id")));
			
			accountPayEntity.setAppProductId(StringUtils.valueOf(rs.getObject("app_creditproduct_id")));
			
		} else {
			accountPayEntity= null;
		}

		return accountPayEntity;
	}

	
	/**
	 * 根据userName获取 d_application_pay 信息
	 * @param accountInfoReq
	 * @return
	 */
	public List<AccountPayEntity> getLoanInfo( String userName) {

		String availBalanceSql = "SELECT a.app_application_no,a.allnode,a.node,a.status ,a.pay_type,a.HY_INDUSTRY_CODE,IFNULL(b.AMOUNT,0.00) AS loanAmt, a.apply_amount as apply_amount, " +
				"a.product_name,a.pro_id,DATE_FORMAT(a.merchant_endtime, '%Y-%m-%d') AS applyDate,DATE_FORMAT(a.create_date, '%Y-%m-%d') AS createDate,a.app_install_ment,a.app_cust_type,a.app_month_pay, " +
				"a.first_pay_rate,a.pro_id,a.MER_PRODUCTID,app_creditproduct_id,app_treaty_upload_flag,a.app_treaty_upload_url,FIRST_PAY_AMOUNT,a.REJECT_DESC,a.app_check_desc,a.rejectCause, a.app_creditproduct_id  " +
				"FROM d_application_pay a LEFT OUTER JOIN " +
				"loan_loan b ON a.app_application_no = b.APPLICATION_ID " +
				"WHERE (a.app_username='" + userName + "' AND a.status != 'STATUS20')  " +
						"OR (a.app_username='" + userName + "' AND a.status = 'STATUS20' " +
								"AND IFNULL(CANCEL_TYPE,'') != 'CUST') ORDER BY node ASC,createdate DESC";
		
		RecordUtils.writeAction(logger, null, availBalanceSql);
		return ConnManager.executeQuery(availBalanceSql, this);

	}
	
	/**
	 * 根据userName 和appNo获取 d_application_pay 信息
	 * @author Cary.Liu
	 * @param accountInfoReq
	 * @return
	 */
	public AccountPayEntity getLoanInfoByAppNo(String userName, String appNo) {

//		String querySql = "SELECT a.app_application_no,a.allnode,a.node,a.status ,a.pay_type,IFNULL(b.AMOUNT,0.00) AS loanAmt,a.apply_amount, " +
//				"a.product_name,DATE_FORMAT(a.create_date, '%Y-%m-%d') AS createDate,a.app_install_ment,a.app_cust_type, " +
//				"a.app_month_pay  ,a.first_pay_rate  " +
//                 "FROM d_application_pay a "+
//                " WHERE a.app_username='"+userName+"' AND  app_application_no = '"+appNo+"' order by createdate DESC  , node ASC ";
		
		String querySql = "SELECT a.app_application_no,a.allnode,a.node,a.status ,a.pay_type,a.HY_INDUSTRY_CODE,IFNULL(b.AMOUNT,0.00) AS loanAmt, a.apply_amount as apply_amount, " +
				"a.product_name,a.pro_id,DATE_FORMAT(a.merchant_endtime, '%Y-%m-%d') AS applyDate,DATE_FORMAT(a.create_date, '%Y-%m-%d') AS createDate,a.app_install_ment,a.app_cust_type,a.app_month_pay, " +
				"a.first_pay_rate,a.MER_PRODUCTID,app_creditproduct_id,a.app_treaty_upload_flag,a.app_treaty_upload_url,FIRST_PAY_AMOUNT,a.REJECT_DESC,a.app_check_desc,a.rejectCause  " +
				"FROM d_application_pay a LEFT OUTER JOIN " +
				"loan_loan b ON a.app_application_no = b.APPLICATION_ID " +
				"WHERE ((a.app_username='" + userName + "' AND a.status != 'STATUS20')  " +
						"OR (a.app_username='" + userName + "' AND a.status = 'STATUS20' " +
								"AND IFNULL(CANCEL_TYPE,'') != 'CUST')) AND  app_application_no = '"+appNo+"' order by createdate DESC  , node ASC ";
		
		RecordUtils.writeAction(logger, null, querySql);
		return ConnManager.singleQuery(querySql, this);

	}
	
}
