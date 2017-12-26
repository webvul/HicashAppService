package com.hengyuan.hicash.domain.query.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ProcessConst;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 申请件信息查询
 * @author Cary.Liu
 * @create date 2014-07-26
 *
 */
public class ApplicationQuery extends AbstractDAO<ApplicationEntity> {
	
	private static Logger logger = Logger.getLogger(ApplicationQuery.class);
	
	private List<String> selectList = new ArrayList<String>();
	
	public ApplicationQuery() {
		selectList.add("app_application_no");
		selectList.add("app_month_pay");
		selectList.add("app_install_ment");
		selectList.add("app_city_code");
		selectList.add("pay_type");
		selectList.add("apply_amount");
		selectList.add("FIRST_PAY_RATE");
		selectList.add("app_creditproduct_id");
		selectList.add("pro_id");
		selectList.add("SEND_APRO_FLAG");
		selectList.add("app_site_code");
		selectList.add("app_user_realname");
		selectList.add("product_name");
		selectList.add("app_cust_type");
		selectList.add("create_date");
		selectList.add("tranPrice");
		selectList.add("ALLNODE");
		selectList.add("NODE");
		selectList.add("STATUS");
		selectList.add("hy_industry_code");
		selectList.add("app_username");
		selectList.add("apply_from");
	}
	
	@Override
	public ApplicationEntity mapping(ResultSet rs) throws SQLException {
		
		ApplicationEntity applicationEntity = null;
		if(rs!=null){
			applicationEntity = new ApplicationEntity();
			applicationEntity.setAppNo(rs.getString("app_application_no"));
			applicationEntity.setMonthPay(rs.getString("app_month_pay"));
			applicationEntity.setInstallMent(rs.getString("app_install_ment"));
			applicationEntity.setCityCode(rs.getString("app_city_code"));
			applicationEntity.setPayType(rs.getString("pay_type"));
			applicationEntity.setApplyAmount(rs.getString("apply_amount"));
			applicationEntity.setTranPrice("tranPrice");
			applicationEntity.setFirstRate(rs.getString("FIRST_PAY_RATE"));
			applicationEntity.setLoanProduct(rs.getString("app_creditproduct_id"));
			applicationEntity.setProductId(rs.getString("pro_id"));
			
			applicationEntity.setSendApproFlag(rs.getString("SEND_APRO_FLAG"));
			applicationEntity.setSiteCode(rs.getString("app_site_code"));
			applicationEntity.setAppRealName(rs.getString("app_user_realname"));
			applicationEntity.setProductName(rs.getString("product_name"));
			applicationEntity.setCustType(rs.getString("app_cust_type"));
			applicationEntity.setCreateDate(rs.getString("create_date"));
			
			applicationEntity.setAllnode(rs.getString("ALLNODE"));
			applicationEntity.setNode(rs.getString("NODE"));
			applicationEntity.setStatus(rs.getString("STATUS"));
			applicationEntity.setIndustryCode(rs.getString("hy_industry_code"));
			applicationEntity.setAppUsername(rs.getString("app_username"));
			applicationEntity.setApplyFrom(rs.getString("apply_from"));
			
		}else{
			System.out.println("申请件查询为空");
		}
		
		return applicationEntity;
	}

	/**
	 * 根据用户名查询申请件信息
	 * @param userName
	 * @return
	 */
	public ApplicationEntity queryAppPayByUserName(String userName){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("app_username", userName);
		whereMap.put("node", ProcessConst.NODE01);
		whereMap.put("status", ProcessConst.STATUS01);
		
		String sql = MapAssemForSql.getSelectSqlByGroupOrOrder(selectList, TableConsts.APPLICATION_PAY, whereMap,"ORDER BY create_date");
		
		//记录日志
		RecordUtils.writeAction(logger, userName, sql);
		List<ApplicationEntity> appList = ConnManager.executeQuery(sql, this);
		if(!appList.isEmpty()&&appList.size()>0){
			return appList.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 查询申请件单号
	 * @param userName
	 * @return
	 */
	public String queryAppNo(String userName){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("app_username", userName);
		
		String sql = MapAssemForSql.getSelectSqlByGroupOrOrder(selectList, TableConsts.APPLICATION_PAY, whereMap,"ORDER BY create_date");
		
		//记录日志
		RecordUtils.writeAction(logger, userName, sql);
		List<ApplicationEntity> appList = ConnManager.executeQuery(sql, this);
		if(!appList.isEmpty()&&appList.get(0).getAppNo()!=null){
			return appList.get(0).getAppNo();
		}else{
			return null;
		}
	}
	
	/**
	 * 查询流水号是否存在
	 * @param appNo
	 * @return
	 */
	public boolean queryAppNoExist(String appNo) {

		Map<String, Object> whereMap = new HashMap<String, Object>();

		whereMap.put("app_application_no", appNo);

		String sql = MapAssemForSql.getSelectSql(selectList,TableConsts.APPLICATION_PAY, whereMap);

		// 记录日志
		RecordUtils.writeAction(logger, appNo, sql);
		ApplicationEntity app = ConnManager.singleQuery(sql, this);
		if (app != null) {
			return true;
		}
		return false;
	}
	
	public ApplicationEntity queryAppByUserAndAppNo(String userName,String appNo){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();

		whereMap.put("app_application_no", appNo);
		whereMap.put("app_username", userName);

		String sql = MapAssemForSql.getSelectSql(selectList,TableConsts.APPLICATION_PAY, whereMap);

		return ConnManager.singleQuery(sql, this);
	}
	
	/**
	 * 根据单号查询申请件信息
	 * @param appNo
	 * @return
	 */
	public ApplicationEntity queryAppPayByAppNO(String appNo){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("app_application_no", appNo);
		
		String sql = MapAssemForSql.getSelectSql(selectList, TableConsts.APPLICATION_PAY, whereMap);
		
		return ConnManager.singleQuery(sql, this);
	}
	
	/**
	 * 获取还款中和交易完成订单
	 * @param appNo
	 * @return
	 */
	public List<ApplicationEntity> queryHkApp(String userName){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("app_username", userName);
		
		String sql = MapAssemForSql.getSelectSql(selectList, TableConsts.APPLICATION_PAY, whereMap);
		sql += " AND (ALLNODE IN ('HKNODE') OR (ALLNODE = 'GBNODE' AND STATUS = 'STATUS27'))";
		
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 根据用户名查询申请件信息
	 * @param appNo
	 * @return
	 */
	public ApplicationEntity queryAppPayByUserNameAndStatus(String userName){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("app_username", userName);
		
		String sql = MapAssemForSql.getSelectSql(selectList, TableConsts.APPLICATION_PAY, whereMap);
		sql += " AND ALLNODE = 'WCNODE' AND STATUS = 'STATUS15' limit 1";
		
		return ConnManager.singleQuery(sql, this);
	}
	
}
