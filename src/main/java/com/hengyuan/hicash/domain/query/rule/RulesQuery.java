package com.hengyuan.hicash.domain.query.rule;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class RulesQuery extends AbstractDAO<String>{

	
	@Override
	public String mapping(ResultSet rs) throws SQLException {
		String str="";
		if (rs != null) {
			str =  StringUtils.valueOf(rs.getObject(("sigle"))) ;
		}
		
		return str;
	}
	
	/**
	 * 身份证号是在黑名单
	 * @param identityNo
	 * @return
	 */
	public List<String>  queryidentityNo(String identityNo){
		String sql ="select identityNo as sigle from d_black_list where identityNo = '"+identityNo+"'";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 手机号是否在黑名单
	 * @param mobile
	 * @return
	 */
	public List<String>  queryMobile(String mobile){
		
		String sql ="select mobileNo as sigle from d_black_list where mobileNo = '"+mobile+"'";
		
		return ConnManager.executeQuery(sql, this);
	}
	/**
	 * 逾期M2以上
	 * @param identityNo
	 * @return
	 */
	public List<String> queryBlockCode(String identityNo){
		
		String sql ="select curr_overdue_term as sigle from cust_honesty where curr_overdue_term >= 2 and identity_no = '"+identityNo+"' ";
	  
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 客户当前逾期
	 * @param identityNo
	 * @param term 期数
	 * @return
	 */
	public List<String> queryBlockCode(String identityNo,Integer term){
		
		String sql ="select curr_overdue_term as sigle from cust_honesty where curr_overdue_term >= "+ term +" and identity_no = '"+identityNo+"' ";
	  
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 客户历史逾期
	 * @param identityNo
	 * @param term 期数
	 * @return
	 */
	public List<String> queryHistoryOverdue(String identityNo,Integer term){
		
		String sql ="select history_overdue_term as sigle from cust_honesty where history_overdue_term >= "+ term +" and identity_no = '"+identityNo+"' ";
	  
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 客户历史产生滞纳金次数
	 * @param identityNo
	 * @return
	 */
	public String queryZnjCount(String userName){
		
		String sql ="SELECT COUNT(*) AS sigle FROM loan_repay_plan WHERE username = '"+ userName +"' AND lpc > 0" ;
	  
		return ConnManager.singleQuery(sql, this);
	}
	
	/***
	 * 查询学校黑名单
	 * @param id
	 * @return
	 */
	public List<String> querySchool(String id){
		String sql="select school_id as sigle from university_blacklist where school_id = '"+id+"' and black_flag = '0' ";
		
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 查询学校名称
	 * @param id
	 * @return
	 */
	public String querySchoolName(String id){
		
		String sql="select shcool_name as sigle from university where ID = "+id+"";
		
		return ConnManager.singleQuery(sql, this);
	}
	
	/**
	 * 
	 * @param appNo
	 * @return
	 */
	public String queryRechordId(String appNo){
		String sql  = "select recordId as sigle from  d_approval_record where applicationNo = '"+appNo+"' and approvalAction = 'FA02'";
		return ConnManager.singleQuery(sql, this);
		
	}
	/**
	 * 验证身份证黑名单一天只能验证一次
	 * @param identifyNo身份证号
	 * @return
	 */
	public String queryIdentifyRecord(String identifyNo){
		String sql="select IDENTITY_NO as sigle from  d_black_record where IDENTITY_NO = '"+identifyNo+"' and to_days(create_time) = to_days(now())" ;
		return ConnManager.singleQuery(sql, this);
	}
	
	/**
	 * 验证身份证是否是黑名单
	 * @param identifyNo身份证号
	 * @return
	 */
	public String queryIdentifyRecordYN(String identifyNo){
		String sql="select result_code as sigle from  d_black_record where IDENTITY_NO = '"+identifyNo+"'" ;
		return ConnManager.singleQuery(sql, this);
	}
}
