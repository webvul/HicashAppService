package com.hengyuan.hicash.domain.query.amount;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.RepayPlanEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class LoanPlanAQuery extends AbstractDAO<RepayPlanEntity> {

	private static Logger logger = Logger.getLogger(LoanPlanAQuery.class);
	
	@Override
	public RepayPlanEntity mapping(ResultSet rs) throws SQLException {

		RepayPlanEntity repayPlan = new RepayPlanEntity();
		
		/* 判断查询结果是否存在 */
		if (rs != null) {
			
			//还款总金额
			repayPlan.setSumprincipal(StringUtils.valueOf(rs.getObject("sumprincipal")));
		} else {
			repayPlan= null;
		}

		return repayPlan;
	}

	
	

	
	public RepayPlanEntity getSumPrince(String userName,String appPayNo){
		
//		String availBalanceSql = "SELECT ifnull(sum(TOTAL_AMT-TOTAL_AMT_PAID),0.00) as sumprincipal FROM loan_repay_plan WHERE loan_id IN ( "+
//			 " SELECT id FROM loan_loan a  LEFT JOIN  d_application_pay  b "+
//			 " ON a.APPLICATION_ID = b.app_application_no "+
//			 " WHERE b.app_username='"+userName+"' AND b.ALLNODE=" +
//			 "'HKNODE' AND b.app_application_no = '"+appPayNo+"')";
		
		String availBalanceSql = "SELECT IFNULL(SUM(TOTAL_AMT-TOTAL_AMT_PAID),0.00) AS sumprincipal "
				+ " FROM loan_repay_plan c JOIN loan_loan a ON c.loan_id = a.id LEFT JOIN d_application_pay b ON a.APPLICATION_ID = b.app_application_no "
				+ " WHERE b.app_application_no = '"
				+ appPayNo
				+ "' AND b.app_username='"
				+ userName
				+ "' AND b.ALLNODE = 'HKNODE'";

			RecordUtils.writeAction(logger, null, availBalanceSql);
			return ConnManager.singleQuery(availBalanceSql, this);
	}


}
