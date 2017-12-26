package com.hengyuan.hicash.domain.query.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * @author Cary.Liu
 * @create 2014-09-25
 */
public class ApplicationPayQuery extends AbstractDAO<ApplicationPay> {
	
	private static Logger logger = Logger.getLogger(ApplicationPayQuery.class);

	@Override
	public ApplicationPay mapping(ResultSet rs) throws SQLException {
		ApplicationPay applicationPay=new ApplicationPay();
	
		if (rs != null) {
			applicationPay.setProductInfo(StringUtils.valueOf(rs.getObject("pro_id")));
			applicationPay.setMonthPay(StringUtils.valueOf(rs.getObject("app_month_pay")));
			applicationPay.setInstallMent(StringUtils.valueOf(rs.getObject("app_install_ment")));
			applicationPay.setCreditProductId(StringUtils.valueOf(rs.getObject("app_creditproduct_id")));
			applicationPay.setTranPrice(StringUtils.valueOf(rs.getObject("tranPrice")));
			applicationPay.setFirstPayPrincipal(StringUtils.valueOf(rs.getObject("first_pay_principal")));
			applicationPay.setUsername(StringUtils.valueOf(rs.getObject("app_username")));
			applicationPay.setCreateDate(StringUtils.valueOf(rs.getObject("create_date")));
			applicationPay.setApplicationNo(StringUtils.valueOf(rs.getObject("app_application_no")));
			applicationPay.setApplyAmount(StringUtils.valueOf(rs.getObject("apply_amount")));
			applicationPay.setAllNode(StringUtils.valueOf(rs.getObject("allnode")));
			applicationPay.setCustomerType(StringUtils.valueOf(rs.getObject("app_cust_type")));
			applicationPay.setCityCode(StringUtils.valueOf(rs.getObject("app_city_code")));
			applicationPay.setPayType(StringUtils.valueOf(rs.getObject("pay_type")));
			applicationPay.setFirstPayRate(StringUtils.valueOf(rs.getObject("FIRST_PAY_RATE")));
			applicationPay.setProductType(StringUtils.valueOf(rs.getObject("productType")));
			applicationPay.setMerProId(StringUtils.valueOf(rs.getObject("MER_PRODUCTID")));
			applicationPay.setIndustryCode(StringUtils.valueOf(rs.getObject("hy_industry_code")));
			applicationPay.setUserRealName(StringUtils.valueOf(rs.getObject("app_user_realname")));
			applicationPay.setApplyFrom(StringUtils.valueOf(rs.getObject("apply_from")));
		}else {
			
			applicationPay=null;
			
		}
		return applicationPay;
	}
	
	/** 根据AppNo查询申请表信息
	 * @param applicationNo
	 * @return ApplicationPay
	 */
	public ApplicationPay queryApplicationPayById(String applicationNo){
		
		String sql="select app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode ,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from from d_application_pay where app_application_no='"+applicationNo+"'";
		
		RecordUtils.writeAction(logger, applicationNo, sql);
		return ConnManager.singleQuery(sql, this);
		
	}
	
	/** 根据AppNo和用户名查询申请表信息
	 * @param applicationNo
	 * @return ApplicationPay
	 */
	public ApplicationPay queryAppPayByIdAndUser(String appNo,String appUserName){
		
		String sql="select app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from from d_application_pay where app_application_no='"+appNo+"' AND app_username = '"+appUserName+"'";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);
		
	}
	
	
	
	/**
	 * 根据用户名跟状态查询申请件信息
	 * @param userName
	 * @param status
	 * @return List<ApplicationPay>
	 */
	public List<ApplicationPay> queryNearestCase(String userName,String status){
		
		String sql="select app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from from d_application_pay " +
			" where app_username='" +userName+"'"+
			" and app_application_Status='" +status+"'"+
			"order by app_application_no desc";
		
		RecordUtils.writeAction(logger, userName, sql);
		return ConnManager.executeQuery(sql, this);
		
	}
	
	
	/**
	 * 根据用户名跟状态查询申请件信息
	 * @param userName
	 * @param status
	 * @return List<ApplicationPay>
	 */
	public List<ApplicationPay> querySByName(String userName,String status,String applyType){
		
		String sql="select app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from from d_application_pay " +
			" where app_username='" +userName+"'"+
			" and status='" +status+"'"+
			" and pay_type='"+applyType+"'"+
			"order by app_application_no desc";
		
		RecordUtils.writeAction(logger, userName, sql);
		return ConnManager.executeQuery(sql, this);
		
	}
	
	/**
	 * 3C根据用户名跟状态查询申请件信息
	 * @param userName
	 * @return List<ApplicationPay>
	 */
	public List<ApplicationPay> querySByName3C(String userName){
		
		String sql="select app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from from d_application_pay " +
			" where app_username='" +userName+"'"+
			" and pay_type = 'NORMAL'"+
			" and ALLNODE NOT IN ('HKNODE','GBNODE') " +
			"order by app_application_no desc";
		
		RecordUtils.writeAction(logger, userName, sql);
		return ConnManager.executeQuery(sql, this);
		
	}
	
	/**
	 * 
	 * @param userName
	 * @return List<ApplicationPay>
	 */
	public List<ApplicationPay> queryExistApp(String userName){
		
		String sql="select app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from from d_application_pay " +
			" where app_username='" +userName+"'"+
			" and ALLNODE NOT IN ('HKNODE','GBNODE') " +
			"order by app_application_no desc";
		
		RecordUtils.writeAction(logger, userName, sql);
		return ConnManager.executeQuery(sql, this);
		
	}
	
	/**
	 * 
	 * @param userName
	 * @return List<ApplicationPay>
	 */
	public List<ApplicationPay> queryExistAppNew(String userName){
		
		String sql="select app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from from d_application_pay " +
			" where app_username='" +userName+"'"+
			" and ALLNODE NOT IN ('GBNODE','HKNODE') " +"and STATUS not in ('STATUS16')"+
			"order by app_application_no desc";
		
		RecordUtils.writeAction(logger, userName, sql);
		return ConnManager.executeQuery(sql, this);
		
	}
	
	/**
	 * 根据用户名查询是否有未完成订单
	 * @param userName
	 * @param status
	 * @return List<ApplicationPay>
	 */
	public List<ApplicationPay> queryFinishPay(String userName){
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from FROM d_application_pay WHERE 1 = 1 AND app_username = '"+userName+"' AND ALLNODE IN ('NENODE','ZLNODE','SHNODE','THNODE','WCNODE')";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 根据用户名,产品行业查询是否有未完成订单
	 * @param userName
	 * @param industryCode 二级行业
	 * @return List<ApplicationPay>
	 */
	public List<ApplicationPay> queryAppByIndustry(String userName,String industryCode){
		//AND ALLNODE IN ('NENODE','ZLNODE','SHNODE')'HKNODE',
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from FROM d_application_pay WHERE 1 = 1 AND app_username = '"+userName+"' AND hy_industry_code = '"+ industryCode +"' AND ALLNODE NOT IN ('GBNODE')";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 根据用户名,产品行业查询是否有未完成订单
	 * @param userName
	 * @param industryCode 二级行业
	 * @return List<ApplicationPay>
	 */
	public List<ApplicationPay> queryAppByHmd(String userName){
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from FROM d_application_pay WHERE 1 = 1 AND app_username = '"+userName+"' AND hy_industry_code IN ('MDCP','MDOH','LLMD') AND ALLNODE NOT IN ('GBNODE')";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	
	/**
	 * 根据用户名,产品行业查询是否有未完成订单
	 * @param userName
	 * @param industryCode 二级行业
	 * @return List<ApplicationPay>
	 */
	public List<ApplicationPay> queryAppByHmdNew(String userName){
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from FROM d_application_pay WHERE 1 = 1 AND app_username = '"+userName+"' AND hy_industry_code IN ('MDCP','MDOH','LLMD','LDDD') AND ALLNODE  IN ('SHNODE')";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 根据用户名查询是否有一个月内被拒绝的订单
	 * @param userName
	 * @return List<ApplicationPay>
	 */
	public List<ApplicationPay> queryAppByBeforeMonth(String userName){
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from FROM d_application_pay WHERE 1 = 1 AND app_username = '"+userName+"' AND STATUS = 'STATUS21' AND CREATE_DATE > DATE_ADD(NOW(), INTERVAL -1 MONTH) AND hy_industry_code IN ('MDCP','LDDD','DDCP')";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 根据用户名查询是否有一个月内被拒绝的订单(DDSJ)
	 * @param userName
	 * @return List<ApplicationPay>
	 */
	public List<ApplicationPay> queryAppByBeforeMonthDdsj(String userName,String hyIndustryCode){
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from FROM d_application_pay WHERE 1 = 1 AND app_username = '"+userName+"' AND STATUS = 'STATUS21' AND CREATE_DATE > DATE_ADD(NOW(), INTERVAL -1 MONTH) AND hy_industry_code ='"+hyIndustryCode+"' ";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
		
//	
//	public ApplicationPay queryByUserInfo(CreateLoanPlanReq createLoanPlanReq){
//		List<String> selects = new ArrayList<String>();
//		
//		selects.add("app_cust_type");
//		selects.add("app_city_code");
//		selects.add("pay_type");
//		selects.add("FIRST_PAY_RATE");
//		
//		
//		selects.add("tranPrice");
//		selects.add("pro_id");
//		selects.add("app_month_pay");
//		selects.add("app_install_ment");
//		selects.add("app_creditproduct_id");
//		selects.add("app_creditproduct_id");
//		selects.add("first_pay_principal");
//		selects.add("app_username");
//		selects.add("create_date");
//		selects.add("app_application_no");
//		selects.add("apply_amount");
//		selects.add("allnode");
//		Map<String, Object> conMap = new HashMap<String, Object>();
//		conMap.put("app_username", createLoanPlanReq.getUserName());
//		conMap.put("app_application_no", createLoanPlanReq.getAppNo());
//		conMap.put("allnode", "NENODE");
//		conMap.put("node", "NODE01");
//		conMap.put("status", "STATUS01");
//		
//		String custSql = MapAssemForSql.getSelectSql(selects, TableConsts.APPLICATION_PAY, conMap);
//		RecordUtils.writeAction(logger, createLoanPlanReq.getUuid(), custSql);
//		return ConnManager.singleQuery(custSql, this);
//	}
//
	
	/** 根据用户名查询申请表状态
	 * @param applicationNo
	 * @return ApplicationPay
	 */
	public List<ApplicationPay> queryAppPayByUser(String appUserName){
		String industry_code="DXSM";
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from FROM d_application_pay WHERE ALLNODE IN ('NENODE','ZLNODE','SHNODE','THNODE','WCNODE','HKNODE','GBNODE') AND STATUS NOT IN ('STATUS20','STATUS21')"+
		"  AND app_username = '"+appUserName+"' AND HY_INDUSTRY_CODE='"+industry_code+"'";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
		
	}
	
	
	/** 根据用户名和行业查询一个月内是否有拒绝的单子
	 * @param applicationNo
	 * @return ApplicationPay
	 */
	public List<ApplicationPay> queryAppByUserHyIndustry(String userName,String hyIndustry){
		
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from FROM d_application_pay WHERE 1 = 1 AND app_username = '"+userName+"' AND STATUS = 'STATUS21' AND CREATE_DATE > DATE_ADD(NOW(), INTERVAL -1 MONTH) AND " +
				  " hy_industry_code ='"+hyIndustry+"'";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
		
	}
	/**
	 * 查询是否在审核中的单子
	 * @param userName
	 * @return
	 */
	public  List<ApplicationPay> queryAppIng(String userName){
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from  FROM d_application_pay  WHERE app_username='"+userName+"'AND ALLNODE NOT IN ('GBNODE','HKNODE')";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	
	
	}
	/**
	 * 查询VIP和司机贷在还款中的单子
	 * @param userName
	 * @return
	 */
	public  List<ApplicationPay> queryVipDdsj(String userName){
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from  FROM d_application_pay " +
				"  WHERE app_username='"+userName+"' AND HY_INDUSTRY_CODE in ('VIPD','DDSJ')  AND ALLNODE='HKNODE' ";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	
	
	}
	
	/**
	 * 查询非司机贷在还款中的的单子
	 * @param userName
	 * @return
	 */
	public  List<ApplicationPay> queryNoDdsjAppIng(String userName){
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from " +
				" FROM d_application_pay  WHERE app_username='"+userName+"'AND ALLNODE ='HKNODE' AND HY_INDUSTRY_CODE!='DDSJ' ";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	
	
	}	
	/**
	 * 查询VIP贷在审核中的单子
	 * @param userName
	 * @return
	 */
	public  List<ApplicationPay> queryAppSh(String userName){
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from  FROM d_application_pay  WHERE app_username='"+userName+"' and hy_industry_code='VIPD' AND ALLNODE NOT IN ('HKNODE','GBNODE')";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	
	
	}
	
	/**
	 * 查询VIP贷在否在审核中的单子
	 * @param userName
	 * @return
	 */
	public  List<ApplicationPay> queryAppVipSh(String userName){
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from  FROM d_application_pay  WHERE app_username='"+userName+"' and hy_industry_code='VIPD' AND ALLNODE NOT IN ('GBNODE')";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	
	
	}
	/**
	 * 根据行业查询是否在审核中的单子
	 * @param userName
	 * @return
	 */
	public  List<ApplicationPay> queryApp(String userName,String hyIndustryCode){
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment, " +
				" app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no , " +
				" apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from  FROM d_application_pay  WHERE " +
				" app_username='"+userName+"'AND ALLNODE NOT IN ('GBNODE','HKNODE')";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	
	
	}	
	/**
	 * 查询是Vip贷List
	 * @param userName
	 * @return
	 */
	public List<ApplicationPay> queryVipDList(String userName){
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from FROM d_application_pay WHERE app_username='"+userName+"' AND HY_INDUSTRY_CODE = 'VIPD' AND STATUS NOT IN ('STATUS20','STATUS21')";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 查询用户非vip贷在审核中与放款中与还款中的订单
	 * */
	public List<ApplicationPay> queryAppNotVipD(String userName){
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from FROM d_application_pay WHERE app_username='"+userName+"' AND HY_INDUSTRY_CODE != 'VIPD' AND ALLNODE NOT IN ('GBNODE')";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	
	public List<ApplicationPay> queryAppPayList(String userName,String industryCode){
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from FROM d_application_pay WHERE app_username='"+userName+"' AND HY_INDUSTRY_CODE = '"+industryCode+"' AND ALLNODE NOT IN ('GBNODE')";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	
	/*根据用户名查询申请(提现)金额*/
	
	public  ApplicationPay queryApplyAmount(String userName){
		
		String sql="select app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode ,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from from d_application_pay where app_username='"+userName+"'";
		
		RecordUtils.writeAction(logger, userName, sql);
		return ConnManager.singleQuery(sql, this);
		
	}
	
	/**
	 * 查询用户DDSJ贷还款中的订单金额
	 * */
	public List<ApplicationPay> queryAmount(String userName){
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from FROM d_application_pay WHERE app_username='"+userName+"' AND HY_INDUSTRY_CODE = 'DDSJ' AND STATUS NOT IN ('STATUS20','STATUS21')";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	
	/**
	 * 嗨女神近三个月分期购
	 * @param userName
	 * @return
	 */
	public List<ApplicationPay> queryHINSApp(String userName){
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from FROM d_application_pay WHERE app_username='"+userName+"' AND HY_INDUSTRY_CODE = 'HINS' AND CREATE_DATE > DATE_ADD(NOW(), INTERVAL -3 MONTH)";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	
	
	/**
	 * 嗨女神第一单
	 * @param userName
	 * @return
	 */
	public ApplicationPay queryHINSAppFirst(String userName){
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from FROM d_application_pay WHERE app_username='"+userName+"' AND HY_INDUSTRY_CODE = 'HINS' order by CREATE_DATE limit 1";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);
	}
	
	/**
	 * 嗨女神订单状态
	 * @param appnp
	 * @return
	 */
	public String queryHINSAppStatus(String appNo){
		String sql="SELECT status FROM d_application_pay WHERE app_application_no = '"+appNo+"' ";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.getString(sql);
	}
	
	
	
	/**
	 * 根据用户名查询是否存在有滴答贷，嗨秒贷，嗨钱来还款中的单子
	 * @param userName
	 * @return
	 */
	public  List<ApplicationPay> queryAppExistAuditing(String userName){
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment, " +
				" app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no , " +
				" apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from  FROM d_application_pay  WHERE " +
				" app_username='"+userName+"'AND ALLNODE  IN ('HKNODE') AND hy_industry_code in('DDCP','LDDD','MDCP')";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}	
	
	/**
	 * 根据用户名查询是否否有嗨女神的订单，在审核中和还款中。
	 * @param userName
	 * @return
	 */
	public  List<ApplicationPay> queryHinsApp(String userName){
		
		String sql="SELECT app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment, " +
				" app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no , " +
				" apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from  FROM d_application_pay  WHERE " +
				" app_username='"+userName+"'AND ALLNODE NOT IN ('GBNODE') AND hy_industry_code = 'HINS' ";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}	
	/*根据用户名查询当前申请(提现)金额*/
	
	public  ApplicationPay queryCurrApplyAmount(String userName){
		
		String sql="select app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,"
				+ "tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode ,productType,MER_PRODUCTID,"
				+ "hy_industry_code,app_user_realname,apply_from from d_application_pay where app_username='"+userName+"'"+" AND allnode='SHNODE' ORDER BY create_date DESC";
		
		RecordUtils.writeAction(logger, userName, sql);
		return ConnManager.singleQuery(sql, this);
		
	}
	
	/** 根据AppNo和用户名查询申请表信息
	 * @param applicationNo
	 * @return ApplicationPay
	 */
	public List<ApplicationPay> queryAppPayByUserName(String appUserName){
		
		String sql="select app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from from d_application_pay where app_username = '"+appUserName+"'";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
		
	}
	
	
	/** 查询app最近一个正式单
	 * @param applicationNo
	 * @return ApplicationPay
	 */
	public ApplicationPay queryAppPayByUserNameAndApp(String appUserName){
		
		String sql="select app_cust_type,app_city_code,pay_type,FIRST_PAY_RATE,pro_id,app_month_pay,app_install_ment,app_creditproduct_id,tranPrice,first_pay_principal,app_username,create_date,app_application_no ,apply_amount,allnode,productType,MER_PRODUCTID,hy_industry_code,app_user_realname,apply_from from d_application_pay where app_username = '"+appUserName+"'  order by create_date desc limit 1";
		
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);
		
	}
	
}
