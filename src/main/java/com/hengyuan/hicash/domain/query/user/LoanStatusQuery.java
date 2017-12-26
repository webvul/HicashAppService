package com.hengyuan.hicash.domain.query.user;

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
import com.hengyuan.hicash.entity.app.LoanLoanAccEntity;
import com.hengyuan.hicash.entity.user.DdsjLimitEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;
/**
 * 
* @author dong.liu 
* 2017-3-26 下午3:49:21
* 类说明
 */
public class LoanStatusQuery extends AbstractDAO<LoanLoanAccEntity>{
	private static Logger logger = Logger.getLogger(LoanStatusQuery.class);
	@Override
	public LoanLoanAccEntity mapping(ResultSet rs) throws SQLException {
		 
		LoanLoanAccEntity vo = new LoanLoanAccEntity();
		
		if(rs!=null){
			vo.setLoanStatus(StringUtils.valueOf(rs.getObject("loan_status")));
			return vo;
		}else{
			logger.info("查询结果为空");
			return null;
			
		}
	}
	
	/**
	 * 根据姓名和身份证号匹配黑名单
	 * @param realName
	 * @param identity
	 * @return
	 */
	public LoanLoanAccEntity queryStatus(String username,String hyIndustryCode){

		String sql=" SELECT count(loan_status) as loan_status FROM d_application_pay a LEFT JOIN " +
				"loan_loan b ON a.app_application_no = b.application_id "+
                " LEFT JOIN loan_loan_acc c ON b.id = c.loan_id  WHERE "+
				"a.app_username = '"+username+"' and a.hy_industry_code =  '"+hyIndustryCode+"' "+
                "AND c.loan_status = 'D' ";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);

	}

}
