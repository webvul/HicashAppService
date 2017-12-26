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
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * CustUser查询类
 * 
 * @author Cary.Liu
 * @createDate 2015-12-22
 *
 */
public class CustUserQuery extends AbstractDAO<CustUserEntity> {
	
	private static Logger logger = Logger.getLogger(CustUserQuery.class);
	private List<String> list;
	
	public CustUserQuery(){
		list = new ArrayList<String>();
		list.add("username");
		list.add("MOBILENO");
		list.add("AMOUNT_VALIDATE_CODE");//授信额度验证码
		list.add("AMOUNT_VALIDATE_CODE_TIME");//授信额度验证码有效时间
		list.add("AMOUNT_VALIDATE_TEMP_MOBILE");//接收验证码的手机号
		list.add("LOGIN_TOKEN");
		list.add("PAGE_INDEX");
		list.add("EMAIL_ADDRESS");//邮箱
		
		list.add("MOBILE_VALIDATE_CODE");//修改密码发送验证码
		list.add("MOBILE_VALIDATE_CODE_VALID_TIME");//修改密码验证码有效时间
		list.add("DOUBLE_SALES");
		
		list.add("IS_LOTTERY");
		list.add("LOTTERY_NUM");
		list.add("CREATE_TIME");
		
	}
	
	@Override
	public CustUserEntity mapping(ResultSet rs) throws SQLException {
		CustUserEntity resp = new CustUserEntity();
		
		if (rs != null) {
			resp.setUsername(StringUtils.valueOf(rs.getObject("username")));
			resp.setMobileNo(StringUtils.valueOf(rs.getObject("MOBILENO")));
			resp.setEmailAddress(StringUtils.valueOf(rs.getObject("EMAIL_ADDRESS")));
			if(rs.getString("AMOUNT_VALIDATE_CODE")!=null){
				resp.setAmountValidateCode(StringUtils.valueOf(rs.getObject("AMOUNT_VALIDATE_CODE")));
			}
			
			if(rs.getString("AMOUNT_VALIDATE_CODE_TIME")!=null){
				resp.setAmountValidateCodeTime(StringUtils.valueOf(rs.getObject("AMOUNT_VALIDATE_CODE_TIME")));
			}
			
			if(rs.getString("AMOUNT_VALIDATE_TEMP_MOBILE")!=null){
				resp.setAmountValidateTempMobile(StringUtils.valueOf(rs.getObject("AMOUNT_VALIDATE_TEMP_MOBILE")));
			}
			
			if(rs.getString("LOGIN_TOKEN")!=null){
				resp.setLoginToken(StringUtils.valueOf(rs.getObject("LOGIN_TOKEN")));
			}
			
			if (rs.getString("PAGE_INDEX") != null) {
				resp.setPageIndex(StringUtils.valueOf(rs.getObject("PAGE_INDEX")));
			}
			
			resp.setMobileValidateCode(StringUtils.valueOf(rs.getObject("MOBILE_VALIDATE_CODE")));
			resp.setMobileValidateCodeValidTime(StringUtils.valueOf(rs.getObject("MOBILE_VALIDATE_CODE_VALID_TIME")));
			resp.setIsDoubleSales(StringUtils.valueOf(rs.getObject("DOUBLE_SALES")));
			
			resp.setIsLottery(rs.getInt("IS_LOTTERY"));
			resp.setLotteryNum(rs.getInt("LOTTERY_NUM"));
			resp.setCreateTime(StringUtils.valueOf(rs.getObject("CREATE_TIME")));
			
		}else {
			return null;
		}
		return resp;
	}

	/**
	 * 根据用户名获取
	 * */
	public CustUserEntity queryByUserName(String username) {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("USERNAME", username);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, username, sql);
		return ConnManager.singleQuery(sql, this);
	}
	
	
	/**
	 * HICASH用户名是否已经存在
	 * @param userName
	 * @return
	 */
	public boolean isUserExist(String userName){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);

		String querySql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER, whereMap);
		CustUserEntity custUser = ConnManager.singleQuery(querySql, this);
		
		return custUser != null ? true : false;
	}
	
	/**
	 * 根据用户名和登陆TOKEN查询用户
	 * @param userName
	 * @param token
	 * @return
	 */
	public CustUserEntity queryUserByToken(String userName,String token){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		whereMap.put("LOGIN_TOKEN", token);
		
		String querySql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER, whereMap);
		return ConnManager.singleQuery(querySql, this);
	}
	
	/**
	 * 根据用户名和登陆TOKEN查询用户
	 * @param userName
	 * @param token
	 * @return
	 */
	public CustUserEntity queryUserByMobile(String mobileNo){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("MOBILENO", mobileNo);
		
		String querySql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER, whereMap);
		return ConnManager.singleQuery(querySql, this);
	}
	
}
