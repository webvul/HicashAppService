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
import com.hengyuan.hicash.entity.app.InputAppPay;
import com.hengyuan.hicash.entity.user.CustUserCard;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppBlueCollarReq;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppPayReq;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 获取银行卡接口DAO层查询
 * 
 * @author Mary.luo
 * @create date 2014-07-15
 */
public class CustUserCardQuery extends AbstractDAO<CustUserCard> {
	
	private List<String> list;
	
	public CustUserCardQuery(){
		list = new ArrayList<String>();
		
		list.add("card_type");
		list.add("province");
		list.add("city");
		list.add("open_bank");
		list.add("card_num");
		list.add("bank");
	}

	private static Logger logger = Logger.getLogger(CustUserCardQuery.class);
	
	@Override
	public CustUserCard mapping(ResultSet rs) throws SQLException {
		CustUserCard userCard = new CustUserCard();

		/* 判断查询结果是否存在 */
		if (rs != null) {
			userCard.setCardType(String.valueOf(rs.getObject("card_type")));
			userCard.setProvince(String.valueOf(rs.getObject("province")));
			userCard.setCity(String.valueOf(rs.getObject("city")));
			userCard.setOpenBank(String.valueOf(rs.getObject("open_bank")));
			userCard.setCardNum(String.valueOf(rs.getObject("card_num")));
			userCard.setBank(String.valueOf(rs.getObject("bank")));
			
		} else {
			userCard=null;
		}

		return userCard;
	}

	/**
	 * 获取用户冻结金额SQL创建
	 * 
	 * @param dreamAmountReq
	 * @return dreamAmountResp
	 * @author Mary.Luo
	 * @create date 2014-07-15
	 */
	public CustUserCard getCustUserCard(CreateAppPayReq dreamAmountReq) {
		
		
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("id", dreamAmountReq.getBankNo());
		setMap.put("username", dreamAmountReq.getUserName());
		
		String dreamAmountSql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER_CARD, setMap);
		
		
		RecordUtils.writeAction(logger, dreamAmountReq.getUuid(), dreamAmountSql);

		return ConnManager.singleQuery(dreamAmountSql, this);
	}

	/**
	 * 获取用户银行卡信息
	 * 
	 * @create date 2014-10-27
	 */
	public CustUserCard queryUserCard(String cardId, String userName) {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("id", cardId);
		setMap.put("username", userName);
		
		String querySql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER_CARD, setMap);
		
		
		RecordUtils.writeAction(logger, null, querySql);

		return ConnManager.singleQuery(querySql, this);
	}
	
	/**
	 * 获取用户冻结金额SQL创建
	 * 
	 * @param dreamAmountReq
	 * @return dreamAmountResp
	 * @author Mary.Luo
	 * @create date 2014-07-15
	 */
	public CustUserCard getCustUserCardBlue(CreateAppBlueCollarReq dreamAmountReq) {
		
		
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("id", dreamAmountReq.getBankNo());
		setMap.put("username", dreamAmountReq.getUserName());
		
		String dreamAmountSql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER_CARD, setMap);
		
		
		RecordUtils.writeAction(logger, dreamAmountReq.getUuid(), dreamAmountSql);

		return ConnManager.singleQuery(dreamAmountSql, this);
	}

	
	/**
	 * 查询默认银行卡
	 * @param userName
	 * @return
	 */
   public CustUserCard getCustUserCardDeFault(String userName) {
		
		
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("default_card", "Y");
		setMap.put("username",userName);
		
		String dreamAmountSql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER_CARD, setMap);
		
		
		RecordUtils.writeAction(logger, null, dreamAmountSql);

		return ConnManager.singleQuery(dreamAmountSql, this);
	}
   
	/**
	 * 根据用户名查询CustUserCard银行卡
	 */
	public List<CustUserCard> queryInfo(String userName){
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("username", userName);

		String querySql =MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER_CARD, setMap);
		RecordUtils.writeAction(logger, userName, querySql.toString());
		return ConnManager.executeQuery(querySql, this);
	}
}
