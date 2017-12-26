package com.hengyuan.hicash.domain.query.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.InputAppPay;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * @author Cary.Liu
 *
 */
public class InputAppPayQuery extends AbstractDAO<InputAppPay> {
	
	private List<String> list;
	
	public InputAppPayQuery(){
		
		list = new ArrayList<String>();
		list.add("birthday");
		list.add("username");
		list.add("applicationno");
		list.add("identityno");
		list.add("mobileno");
		list.add("BANK_CARD_NO");
		list.add("creditName");
		list.add("creditDay");
		list.add("PROXY_OPENBANK");
		list.add("PROXY_BANKCARD");
		list.add("FIRST_REPAYMENT_DAY");
		list.add("COMPACT_CFM_DATE");
		list.add("REALNAME");
		list.add("PROXY_ACCOUNTVAL_RESULT");
		list.add("PROXY_MSG");
		
		list.add("dormAddress");
		list.add("dormTelArea");
		list.add("dormTel");
		list.add("unitAddress");
		list.add("unitTel");
		list.add("fatherName");
		list.add("fatherMobile");
		list.add("familyRelation");
		
		list.add("relaName");
		list.add("relaMobile");
		list.add("relation");
	}
	
	@Override
	public InputAppPay mapping(ResultSet rs) throws SQLException {
		
		InputAppPay inputAppPay = null;
		
		if (rs != null) {
			
			inputAppPay = new InputAppPay();
			inputAppPay.setRealName(rs.getString("REALNAME"));
			inputAppPay.setBirthday(rs.getString("birthday"));
			inputAppPay.setUserName(rs.getString("username"));
			inputAppPay.setApplicationNo(rs.getString("applicationno"));
			inputAppPay.setIdentityNo(rs.getString("identityno"));
			inputAppPay.setMobileNo(rs.getString("mobileno"));
			inputAppPay.setBankCardNo(rs.getString("BANK_CARD_NO"));
			inputAppPay.setCreditName(rs.getString("creditName"));
			inputAppPay.setCreditDay(rs.getString("creditDay"));
			inputAppPay.setProxyOpenBank(rs.getString("PROXY_OPENBANK"));
			inputAppPay.setProxyBankCard(rs.getString("PROXY_BANKCARD"));
			inputAppPay.setFirstPaymentDay(rs.getString("FIRST_REPAYMENT_DAY"));
			inputAppPay.setCompactCfmDate(rs.getString("COMPACT_CFM_DATE"));
			inputAppPay.setIsSucc(rs.getString("PROXY_ACCOUNTVAL_RESULT"));
			inputAppPay.setValidateMsg(rs.getString("PROXY_MSG"));
			
			inputAppPay.setDormAddress(StringUtils.valueOf(rs.getObject("dormAddress")));
			inputAppPay.setDormTelArea(StringUtils.valueOf(rs.getObject("dormTelArea")));
			inputAppPay.setDormTel(StringUtils.valueOf(rs.getObject("dormTel")));
			inputAppPay.setUnitAddress(StringUtils.valueOf(rs.getObject("unitAddress")));
			inputAppPay.setUnitTel(StringUtils.valueOf(rs.getObject("unitTel")));
			inputAppPay.setFatherName(StringUtils.valueOf(rs.getObject("fatherName")));
			inputAppPay.setFatherMobile(StringUtils.valueOf(rs.getObject("fatherMobile")));
			inputAppPay.setFamilyRelation(StringUtils.valueOf(rs.getObject("familyRelation")));
			
			inputAppPay.setRelaName(StringUtils.valueOf(rs.getObject("relaName")));
			inputAppPay.setRelaMobile(StringUtils.valueOf(rs.getObject("relaMobile")));
			inputAppPay.setRelation(StringUtils.valueOf(rs.getObject("relation")));
		}
		
		return inputAppPay;
	}
	
	/**
	 * 根据申请件流水号查询inputAppPay信息
	 */
	public InputAppPay queryInputAppByAppNo(String appNo){
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("applicationno", appNo);
		setMap.put("IS_PROXY", 1);
		String querySql = MapAssemForSql.getSelectSql(list, TableConsts.INPUT_APP, setMap);
		return ConnManager.singleQuery(querySql, this);
	}
	
	/**
	 * 根据申请件流水号查询inputAppPay信息
	 */
	public InputAppPay queryInputAppByAppNoAndUser(String appNo,String userName){
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("applicationno", appNo);
		setMap.put("username", userName);
		
		String querySql = MapAssemForSql.getSelectSql(list, TableConsts.INPUT_APP, setMap);
		return ConnManager.singleQuery(querySql, this);
	}
//	/**
//	 * 根据申请件流水号查询inputAppPay信息
//	 */
//	public InputAppPay queryInputAppPayByAppNo(String applicationNo) {
//		String sql="select * from d_input_app where applicationNo ='"+applicationNo+"'";
//		return ConnManager.singleQuery(sql, this);
//	}
	
	/**
	 * 根据申请件流水号查询inputAppPay信息
	 */
	public InputAppPay queryInputAppPay(String appPayNo){
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("applicationno", appPayNo);
		String querySql = MapAssemForSql.getSelectSql(list, TableConsts.INPUT_APP, setMap);
		
		RecordUtils.writeAction(logger, appPayNo, querySql);
		return ConnManager.singleQuery(querySql, this);
		
	}
	
	/**
	 * 根据用户名查询inputAppPay代扣信息
	 */
	public List<InputAppPay> queryDkInfoByAppNo(String userName){
		
		Map<String, Object> setMap = new HashMap<String, Object>();
//		setMap.put("userName", userName);
//		setMap.put("IS_PROXY", 1);
//		String querySql = MapAssemForSql.getSelectSql(list, TableConsts.INPUT_APP, setMap);
		
		String querySql = "SELECT DISTINCT PROXY_OPENBANK,PROXY_BANKCARD, " +
		" '' as REALNAME,'' as birthday,'' as username,'' as applicationno,'' as identityno, " +
		"'' as mobileno,'' as BANK_CARD_NO,'' as creditName, '' as creditDay, '' as FIRST_REPAYMENT_DAY,   "+
		"'' as COMPACT_CFM_DATE,'' as PROXY_ACCOUNTVAL_RESULT,'' as PROXY_MSG, '' as dormAddress, '' as dormTelArea, "+
		"'' as dormTel,'' as unitAddress,'' as unitTel, '' as fatherName, '' as fatherMobile, "+
		"'' as familyRelation,'' as relaName,'' as relaMobile, '' as relation, '' as fatherMobile "+
		" FROM d_input_app " 
		+" WHERE userName='"+userName+"' AND IS_PROXY=1 ";

		RecordUtils.writeAction(logger, userName, querySql.toString());
		return ConnManager.executeQuery(querySql, this);
	}
}
