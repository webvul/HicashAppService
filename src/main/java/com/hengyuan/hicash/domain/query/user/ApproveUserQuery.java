package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.ApproveUser;
import com.hengyuan.hicash.exception.ApproveUserNotFound;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 后台用户
 * 
 * @author Cary.Liu
 * @create 2015-03-24
 */
public class ApproveUserQuery extends AbstractDAO<ApproveUser> {
	

	public static final String QUERY_APPROVEUSER_SQL = "SELECT ID,USERNAME,PASSWORD,SALT,ENABLED,REAL_NAME,logic_code,mobileNo,EMAIL_ADRESS,USER_TYPE,USER_NUMBER,provice,city_code,AREA,supInfoId,SALE_SITE FROM approve_user WHERE 1 = 1";
	
	@Override
	public ApproveUser mapping(ResultSet rs) throws SQLException {
		ApproveUser approveUser = null;
		if (rs != null) {
			approveUser = new ApproveUser();
			approveUser.setId(StringUtils.valueOf(rs.getObject("ID")));
			approveUser.setUserName(StringUtils.valueOf(rs.getObject("USERNAME")));
			approveUser.setPassWord(StringUtils.valueOf(rs.getObject("PASSWORD")));
			approveUser.setSalt(StringUtils.valueOf(rs.getObject("SALT")));
			approveUser.setEnabled(String.valueOf(rs.getObject("ENABLED")));
			approveUser.setRealName(StringUtils.valueOf(rs.getObject("REAL_NAME")));
			approveUser.setLogicCode(StringUtils.valueOf(rs.getObject("logic_code")));
			approveUser.setMobileNo(StringUtils.valueOf(rs.getObject("mobileNo")));
			approveUser.setEmailAddress(StringUtils.valueOf(rs.getObject("EMAIL_ADRESS")));
			approveUser.setUserType(StringUtils.valueOf(rs.getObject("USER_TYPE")));
			approveUser.setUserNumber(StringUtils.valueOf(rs.getObject("USER_NUMBER")));
			approveUser.setProvice(StringUtils.valueOf(rs.getString("provice")));
			approveUser.setCityCode(StringUtils.valueOf(rs.getString("city_code")));
			approveUser.setArea(StringUtils.valueOf(rs.getString("AREA")));
			approveUser.setSupInfoId(StringUtils.valueOf(rs.getString("supInfoId")));
			approveUser.setSaleSite(StringUtils.valueOf(rs.getString("SALE_SITE")));
			
		}
		return approveUser;
	}
	
	/**
	 * 根据用户名或者员工号查找用户
	 * @param userName
	 * @return
	 */
	public ApproveUser queryUserByUserNameOrNum(String userName){
		
		StringBuffer querySql = new StringBuffer(QUERY_APPROVEUSER_SQL);
		querySql.append(" AND USERNAME = '"+userName+"' OR USER_NUMBER = '"+userName+"'");
		
		return ConnManager.singleQuery(querySql.toString(), this);
		
	}
	
	/**
	 * 用户登录查询
	 * @return
	 */
	public ApproveUser queryUserByUserNameAndPsw(String userName,String passWord){
		
		StringBuffer querySql = new StringBuffer(QUERY_APPROVEUSER_SQL);
		querySql.append(" AND (USERNAME = '"+userName+"' OR USER_NUMBER = '"+userName+"') and PASSWORD = '"+passWord+"'");
		
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
	/**
	 * 根据用户名查询后台用户
	 * @return
	 */
	public ApproveUser queryApprovByUserName(String userName){
		
		StringBuffer querySql = new StringBuffer(QUERY_APPROVEUSER_SQL);
		querySql.append(" AND USERNAME = '"+userName+"'");
		
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
	/**
	 * 根据员工号查询后台用户
	 * @return
	 */
	public ApproveUser queryApprovByNum(String number){
		
		StringBuffer querySql = new StringBuffer(QUERY_APPROVEUSER_SQL);
		querySql.append(" AND USER_NUMBER = '"+number+"'");
		
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
	/**
	 * 根据手机号码查询后台用户
	 * @return
	 */
	public ApproveUser queryApprovByMobile(String mobileNo){
		
		StringBuffer querySql = new StringBuffer(QUERY_APPROVEUSER_SQL);
		querySql.append(" AND (MOBILENO = '"+ mobileNo +"' OR USERNAME = '"+ mobileNo +"')");
		
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
	/**
	 * 根据销售码查询ApproveUser表
	 * @param saleCode
	 * @return
	 * @throws ApproveUserNotFound 
	 * @author Andy.Niu
	 * @create 2014-08-06
	 */
	public ApproveUser getApproveUserBySaleCode(String saleCode) throws ApproveUserNotFound{
		
		saleCode = getRealSaleCode(saleCode);
		
		if(saleCode == null){
			return null;
		}else{
//			String sql="SELECT USERNAME,REAL_NAME,logic_code,mobileNo,EMAIL_ADRESS," +
//					"USER_TYPE,USER_NUMBER,provice,CITY_CODE,AREA,supInfoId,SALE_SITE " +
//					"FROM approve_user " +
//					"WHERE USER_NUMBER = '" + saleCode + "'";
			StringBuffer querySql = new StringBuffer(QUERY_APPROVEUSER_SQL);
			querySql.append(" AND USER_NUMBER = '" + saleCode + "'");
			
//			RecordUtils.writeAction(logger, saleCode, querySql);
			
			ApproveUser approveUser = ConnManager.singleQuery(querySql.toString(), this);
			
			if(approveUser != null){
				return approveUser;
			}else{
				throw new ApproveUserNotFound();
			}
		}
	}
	
	public String getRealSaleCode(String saleCode){
		
		if(RegexValidate.isNumber(saleCode)){
			return saleCode;
		}else{
			if(com.mysql.jdbc.StringUtils.isNullOrEmpty(saleCode)){
				return null;
			}else{
				if(saleCode.indexOf(Consts.USERNUMBER_PREX) == 0){
					return saleCode.substring(4);
				}else{
					return null;
				}
			}
		}
	}
	
}
