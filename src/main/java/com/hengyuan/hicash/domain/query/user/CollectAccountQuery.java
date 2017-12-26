package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CollectAccountEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 收款账户信息查询Dao
 * 
 * @author Cary.Liu
 * @create date 2014-07-26
 * 
 */
public class CollectAccountQuery extends AbstractDAO<CollectAccountEntity> {
	private static Logger logger = Logger.getLogger(CollectAccountQuery.class);
	
	private List<String> list;
	
	public CollectAccountQuery(){
		list = new ArrayList<String>();
		list.add("ID");
		list.add("REALNAME");
		list.add("CARD_TYPE");
		list.add("PROVINCE");
		list.add("CITY");
		list.add("AREA");
		list.add("OPEN_BANK");
		list.add("BANK");
		list.add("CARD_NUM");
		list.add("default_card");
		list.add("OPEN_BANK_NUM");
	}

	@Override
	public CollectAccountEntity mapping(ResultSet rs) throws SQLException {
		
		CollectAccountEntity accountEntity = null;
		if(rs!=null){
			accountEntity = new CollectAccountEntity();
			accountEntity.setCardId(StringUtils.valueOf(rs.getObject("ID")));
			accountEntity.setRealName(StringUtils.valueOf(rs.getObject("REALNAME")));
			accountEntity.setCardType(StringUtils.valueOf(rs.getObject("CARD_TYPE")));//卡类型
			accountEntity.setProvince(StringUtils.valueOf(rs.getObject("PROVINCE")));//开户省
			accountEntity.setCity(StringUtils.valueOf(rs.getObject("CITY")));//开户市
			accountEntity.setOpenBank(StringUtils.valueOf(rs.getObject("OPEN_BANK_NUM")));//开户支行
			accountEntity.setOpenBankName(StringUtils.valueOf(rs.getObject("OPEN_BANK")));
			accountEntity.setBank(StringUtils.valueOf(rs.getObject("BANK")));//开户行
			accountEntity.setCardNum(StringUtils.valueOf(rs.getObject("CARD_NUM")));//卡号
			accountEntity.setDefaultCard(StringUtils.valueOf(rs.getObject("default_card")));//默认银行卡标志
			accountEntity.setArea(StringUtils.valueOf(rs.getObject("AREA")));
			
		}else{
			System.out.println("收款账户信息查询为空");
		}
		
		return accountEntity;
	}
	
	/**
	 * 根据申请件单号查询申请人资料
	 * @param appNo
	 * @return
	 */
//	public CollectAccountEntity queryInputAppByAppNo(String appNo){
//		List<String> list = new ArrayList<String>();
//		list.add("BANK_TYPE");
//		list.add("bank_province");
//		list.add("bank_city");
//		list.add("BANK_BRANCH");
//		list.add("BANK_CARD_NO");
//		
//		Map<String, Object> whereMap = new HashMap<String, Object>();
//		whereMap.put("applicationNo", appNo);
//		
//		String sql = MapAssemForSql.getSelectSql(list, TableConsts.INPUT_APP, whereMap);
//		
//		return ConnManager.singleQuery(sql, this);
//	}
	
	
	/**
	 * 根据申请件单号查询申请人资料
	 * @param appNo
	 * @return
	 */
	public List<CollectAccountEntity> queryCardByUserName(String userName){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		
		String orderBy = " ORDER BY CREATE_TIME DESC";
		String sql = MapAssemForSql.getSelectSqlByGroupOrOrder(list, TableConsts.CUST_USER_CARD, whereMap,orderBy);
		
		//记录日志
		RecordUtils.writeAction(logger, userName, sql);
		
		return ConnManager.executeQuery(sql, this);
	}

	/**
	 * 根据用户名和银行卡号查询该银行卡是否存在
	 * @param userName 用户名
	 * @param idEntityNo 银行卡号
	 */
	public boolean queryIdEntityNoExist(String userName,String idEntityNo){

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		whereMap.put("CARD_NUM", idEntityNo);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER_CARD, whereMap);
		
		//记录日志
		RecordUtils.writeAction(logger, userName, sql);
		CollectAccountEntity accountEntity = ConnManager.singleQuery(sql, this);
		if(accountEntity != null){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据用户名和银行卡主键查询该银行卡
	 * @param userName 用户名
	 * @param cardId 银行卡主键
	 */
	public CollectAccountEntity queryCardAccount(String userName,String cardId){
	
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("id", cardId);
		whereMap.put("username", userName);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER_CARD, whereMap);
		CollectAccountEntity accountEntity = ConnManager.singleQuery(sql, this);
		if(accountEntity != null){
			return accountEntity;
		}
		return null;
		
	}
	
	/**
	 * 查询用户默认银行卡
	 * @param userName 用户名
	 */
	public CollectAccountEntity queryCardByDefault(String userName){
	
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		whereMap.put("DEFAULT_CARD", Consts.DEFAULT_CARD_YES);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER_CARD, whereMap);
		return ConnManager.singleQuery(sql, this);
	}
	
	/**
	 * 查询用户所有银行卡，默认的银行卡显示在前面
	 * @param userName 用户名
	 */
	public List<CollectAccountEntity> queryCardList(String userName){
		String sql =" SELECT ID,REALNAME,CARD_TYPE,PROVINCE,CITY,AREA,OPEN_BANK,BANK,CARD_NUM,default_card,OPEN_BANK_NUM FROM cust_user_card WHERE 1 = 1 AND username='"+userName+"' ORDER BY DEFAULT_CARD DESC";
		return ConnManager.executeQuery(sql,this);
	}
	
}
