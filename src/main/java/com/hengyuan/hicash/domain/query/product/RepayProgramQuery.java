package com.hengyuan.hicash.domain.query.product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.RepayProgramEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class RepayProgramQuery extends AbstractDAO<RepayProgramEntity> {

	private static Logger logger = Logger.getLogger(RepayProgramQuery.class);

	@Override
	public RepayProgramEntity mapping(ResultSet rs) throws SQLException {

		if (rs != null) {

			RepayProgramEntity repayProgramEntity = new RepayProgramEntity();

			repayProgramEntity.setInstallments(StringUtils.valueOf(rs
					.getObject("installments")));
			repayProgramEntity.setLoanProduct(StringUtils.valueOf(rs
					.getObject("loanProduct")));
			repayProgramEntity
					.setName(StringUtils.valueOf(rs.getObject("name")));
			repayProgramEntity.setMthFee(StringUtils.valueOf(rs
					.getObject("mthFee")));
			repayProgramEntity.setCustRate(StringUtils.valueOf(rs
					.getObject("custRate")));
			repayProgramEntity.setServerRate(StringUtils.valueOf(rs
					.getObject("serverRate")));
			
			try {
				//M月;Q季;W周;D日;Y年
				if(StringUtils.valueOf(rs.getObject("loanUnit")).isEmpty()){
					repayProgramEntity.setPeriods("");
				}else if(StringUtils.valueOf(rs.getObject("loanUnit")).equals("M")){
					repayProgramEntity.setPeriods("个月");
				}else if(StringUtils.valueOf(rs.getObject("loanUnit")).equals("Q")){
					repayProgramEntity.setPeriods("个季度");
				}else if(StringUtils.valueOf(rs.getObject("loanUnit")).equals("W")){
					repayProgramEntity.setPeriods("周");
				}else if(StringUtils.valueOf(rs.getObject("loanUnit")).equals("Y")){
					repayProgramEntity.setPeriods("年");
				}else{
					repayProgramEntity.setPeriods("天");
					repayProgramEntity.setInstallments(StringUtils.valueOf(rs.getObject("creditDay")));
					BigDecimal yearsRate = new BigDecimal(repayProgramEntity.getCustRate());
					BigDecimal day = new BigDecimal(StringUtils.valueOf(rs.getObject("creditDay")));
					BigDecimal creditRate = yearsRate.divide(new BigDecimal("360"),10,BigDecimal.ROUND_HALF_UP)
							.multiply(day).multiply(new BigDecimal("12"));
					repayProgramEntity.setCustRate(String.valueOf(creditRate));
				}
			} catch (Exception e) {
				logger.info("查询的不是Vip贷");
			}
			
			

			return repayProgramEntity;
		} else {
			System.out.println("查询的结果为空");
			return null;
		}

	}

	public List<RepayProgramEntity> queryRepayProgram(String custType,
			String industryCode, BigDecimal rebatePercent, String uuid) {

		String sql = "SELECT a.loan_product_id AS loanProduct,b.period as installments,b.NAME as name FROM d_credit_config AS a "
				+ " LEFT JOIN loan_product AS b  ON a.loan_product_id = b.id"
				+ " WHERE a.cust_type = '"
				+ custType
				+ "' AND a.hy_industry_code = '"
				+ industryCode
				+ "' AND rebate_percent = "
				+ rebatePercent
				+ " ORDER BY b.period ASC";

		RecordUtils.writeAction(logger, uuid, sql);
		return ConnManager.executeQuery(sql, this);
	}

	public List<RepayProgramEntity> queryHeRepayProgram(String custType,
			String industryCode, BigDecimal rebatePercent, String uuid) {

		String sql = "SELECT a.he_loan_product_id AS loanProduct,b.period as installments,b.NAME as name FROM d_credit_config AS a "
				+ " LEFT JOIN loan_product AS b  ON a.he_loan_product_id = b.id"
				+ " WHERE a.cust_type = '"
				+ custType
				+ "' AND a.hy_industry_code = '"
				+ industryCode
				+ "' AND rebate_percent = "
				+ rebatePercent
				+ " ORDER BY b.period ASC";

		RecordUtils.writeAction(logger, uuid, sql);
		return ConnManager.executeQuery(sql, this);
	}

	public List<RepayProgramEntity> queryHrRepayProgram(String custType,
			String industryCode, BigDecimal rebatePercent, String uuid) {

		String sql = "SELECT a.hr_loan_product_id AS loanProduct,b.period as installments,b.NAME as name FROM d_credit_config AS a "
				+ " LEFT JOIN loan_product AS b  ON a.hr_loan_product_id = b.id"
				+ " WHERE a.cust_type = '"
				+ custType
				+ "' AND a.hy_industry_code = '"
				+ industryCode
				+ "' AND rebate_percent = "
				+ rebatePercent
				+ " ORDER BY b.period ASC";

		RecordUtils.writeAction(logger, uuid, sql);
		return ConnManager.executeQuery(sql, this);
	}

	/**
	 * 查询投资人是david_fu的信贷产品
	 * 
	 * @param custType
	 * @param industry
	 * @param rebate
	 * @param uuid
	 * @return
	 */
	public List<RepayProgramEntity> queryHyRepayProgram(String custType,
			String industry, BigDecimal rebate, String uuid, String period,
			int custVipGrade) {

		String sql = "SELECT a.hy_loan_product_id AS loanProduct,b.period AS installments,b.NAME as name,"
				+ "b.MTH_FEE_RATE AS mthFee,b.CUST_RATE AS custRate,b.SERVER_RATE AS serverRate,"
				+ "b.CREDIT_DAY AS creditDay,b.loan_unit as loanUnit"
				+ " FROM d_credit_config AS a "
				+ " LEFT JOIN loan_product AS b  ON a.hy_loan_product_id = b.id"
				+ " WHERE a.cust_type = '"
				+ custType
				+ "' AND a.hy_industry_code = '"
				+ industry
				+ "' AND rebate_percent = "
				+ rebate
				/* 注释掉 by yangkun 20171207 不需要这过滤这个字段了 返回vip全量期数
				 * + " AND period in ("
				+ period
				+ ") "*/
				+ " AND CUST_VIP_GRADE = "
				+ custVipGrade
				+ " AND a.hy_loan_product_id is not null"
				/*+ " AND b.CREDIT_DAY is null"*/
				+" AND b.loan_unit='M'"
				+ " ORDER BY b.period ASC";

		RecordUtils.writeAction(logger, uuid, sql);
		return ConnManager.executeQuery(sql, this);
	}

	public List<RepayProgramEntity> queryHyRepayProgramNo(String custType,
			String industry, BigDecimal rebate, String uuid) {

		String sql = "SELECT a.hy_loan_product_id AS loanProduct,b.period AS installments,b.NAME as name,"
				+ "b.MTH_FEE_RATE AS mthFee,b.CUST_RATE AS custRate,b.SERVER_RATE AS serverRate,"
				+ "b.CREDIT_DAY AS creditDay,b.loan_unit as loanUnit FROM d_credit_config AS a "
				+ " LEFT JOIN loan_product AS b  ON a.hy_loan_product_id = b.id"
				+ " WHERE a.cust_type = '"
				+ custType
				+ "' AND a.hy_industry_code = '"
				+ industry
				+ "' AND rebate_percent = "
				+ rebate
				+ " AND CUST_VIP_GRADE = 1"
				+ " AND a.hy_loan_product_id is not null"
				/*+ " AND b.CREDIT_DAY is null"*/
				+ " AND b.loan_unit='M'"
				+ " ORDER BY b.period ASC";

		RecordUtils.writeAction(logger, uuid, sql);
		return ConnManager.executeQuery(sql, this);
	}

	public List<RepayProgramEntity> queryHyRepay(String custType,
			String industry, BigDecimal rebate, String invest_name,
			String other_param) {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT");
		sql.append(" a.hy_loan_product_id AS loanProduct");
		sql.append(" ,b.period AS installments");
		sql.append(" ,b.NAME as name");
		sql.append(" ,b.MTH_FEE_RATE AS mthFee");
		sql.append(" ,b.CUST_RATE AS custRate");
		sql.append(" ,b.SERVER_RATE AS serverRate");
		sql.append(" FROM d_credit_config AS a ");
		sql.append(" INNER JOIN loan_product AS b");

		if (Consts.HENGYUAN_USERNAME.equals(invest_name)) {
			sql.append(" ON a.hy_loan_product_id = b.id");
		}

		sql.append(" AND a.cust_type = '").append(custType).append("'");
		sql.append(" AND a.hy_industry_code = '").append(industry).append("'");
		sql.append(" AND a.rebate_percent = '").append(rebate).append("'");

//		if (!StringUtils.isEmpty(other_param)) {
//			sql.append(" AND b.other_param = '").append(other_param).append("'");
//		}

		sql.append(" AND a.hy_loan_product_id is not null");
		sql.append(" ORDER BY b.period ASC");

		RecordUtils.writeAction(logger, null, sql.toString());
		return ConnManager.executeQuery(sql.toString(), this);
	}
	
	
	public List<RepayProgramEntity> querydayRepayList(String custType,
			String industry, BigDecimal rebate, String uuid) {

		String sql = "SELECT a.hy_loan_product_id AS loanProduct,b.period AS installments,b.NAME as name,"
				+ "b.MTH_FEE_RATE AS mthFee,b.CUST_RATE AS custRate,b.SERVER_RATE AS serverRate,"
				+ "b.CREDIT_DAY AS creditDay,b.loan_unit as loanUnit FROM d_credit_config AS a "
				+ " LEFT JOIN loan_product AS b ON a.hy_loan_product_id = b.id"
				+ " WHERE a.cust_type = '"
				+ custType
				+ "' AND a.hy_industry_code = '"
				+ industry
				+ "' AND rebate_percent = "
				+ rebate
				+ " AND CUST_VIP_GRADE = 1"
				+ " AND a.hy_loan_product_id is not null"
				/*+ " AND b.CREDIT_DAY is not null"*/
				+" AND b.loan_unit='D'"
				+ " ORDER BY b.period ASC";

		RecordUtils.writeAction(logger, uuid, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	
	public List<RepayProgramEntity> querydayRepayList(String custType,
			String industry, BigDecimal rebate, String uuid,int custVipGrade) {

		String sql = "SELECT a.hy_loan_product_id AS loanProduct,b.period AS installments,b.NAME as name,"
				+ "b.MTH_FEE_RATE AS mthFee,b.CUST_RATE AS custRate,b.SERVER_RATE AS serverRate,"
				+ "b.CREDIT_DAY AS creditDay,b.loan_unit as loanUnit"
				+ " FROM d_credit_config AS a "
				+ " LEFT JOIN loan_product AS b  ON a.hy_loan_product_id = b.id"
				+ " WHERE a.cust_type = '"
				+ custType
				+ "' AND a.hy_industry_code = '"
				+ industry
				+ "' AND rebate_percent = "
				+ rebate
				+ " AND CUST_VIP_GRADE = "
				+ custVipGrade
				+ " AND a.hy_loan_product_id is not null"
				/*+ " AND b.CREDIT_DAY is not null"*/
				+" AND b.loan_unit='D'"
				+ " ORDER BY b.period ASC";

		RecordUtils.writeAction(logger, uuid, sql);
		return ConnManager.executeQuery(sql, this);
	}

}
