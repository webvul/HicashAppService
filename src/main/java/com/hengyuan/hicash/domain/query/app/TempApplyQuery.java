package com.hengyuan.hicash.domain.query.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * 用户临时申请资料（嗨秒贷） DAO
 * @author Cary.Liu
 * @createDate 2015-05-26
 *
 */
public class TempApplyQuery extends AbstractDAO<TempApplyEntity> {

	private static Logger logger = Logger.getLogger(TempApplyQuery.class);
	
	public static final String QUERY_SQL = "SELECT * FROM CUST_TEMPAPPLY_INFO WHERE 1 = 1 ";
	
	public TempApplyEntity mapping(ResultSet rs) throws SQLException {

		TempApplyEntity entity = null;
		if(rs != null){
			
			entity = new TempApplyEntity();
			entity.setId(StringUtils.valueOf(rs.getObject("ID")));
			entity.setTempAppNo(StringUtils.valueOf(rs.getObject("TEMP_APPLICATION_NO")));
			entity.setUserName(StringUtils.valueOf(rs.getObject("USERNAME")));
			entity.setApplyPrice(StringUtils.valueOf(rs.getObject("APPLY_PRICE")));
			entity.setLoanProduct(StringUtils.valueOf(rs.getObject("LOAN_PRODUCT")));
			entity.setUserIdcardFrontUrl(StringUtils.valueOf(rs.getObject("USER_IDCARD_FRONT")));
			entity.setUserIdcardFrontUrlThum(StringUtils.valueOf(rs.getObject("USER_IDCARD_FRONT_THUM")));
			entity.setIdcardFrontUrl(StringUtils.valueOf(rs.getObject("IDCARD_FRONT")));
			entity.setIdcardFrontUrlThum(StringUtils.valueOf(rs.getObject("IDCARD_FRONT_THUM")));
			entity.setIdcardVersoUrl(StringUtils.valueOf(rs.getObject("IDCARD_VERSO")));
			entity.setIdcardVersoUrlThum(StringUtils.valueOf(rs.getObject("IDCARD_VERSO_THUM")));
			entity.setCreateDate(StringUtils.valueOf(rs.getObject("CREATE_DATE")));
			entity.setCreateAppFlg(StringUtils.valueOf(rs.getObject("CREATEAPP_FLAG")));
			entity.setPhoneDataId(StringUtils.valueOf(rs.getObject("phone_data_id")));
			entity.setPhoneNo(StringUtils.valueOf(rs.getObject("pro_phonenum")));
			entity.setProDataName(StringUtils.valueOf(rs.getObject("pro_name")));
			entity.setValidateType(StringUtils.valueOf(rs.getObject("validate_type")));
			entity.setImDrainage(StringUtils.valueOf(rs.getObject("im_drainage")));
			
		}
		
		return entity;
	}
	
	public List<TempApplyEntity> queryTempApply(String userName){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND USERNAME = '"+ userName +"' order by CREATE_DATE desc");
		
		return ConnManager.executeQuery(querySql.toString(), this);
	}
	
	public List<TempApplyEntity> queryTempApply(String userName,String industryCode){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND PROMER_ID='"+industryCode+"'");
		querySql.append(" AND USERNAME = '"+ userName +"' order by CREATE_DATE desc");
		
		return ConnManager.executeQuery(querySql.toString(), this);
	}
	
/**
 * 查询有效的临时订单
 * @param userName
 * @param industryCode
 * @return
 */
	public List<TempApplyEntity> queryIsExitTempApply(String userName,String industryCode){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND PROMER_ID='"+industryCode+"'");
		querySql.append(" AND USERNAME='"+userName+"'");
		querySql.append(" AND PRO_NAME='"+0+"'");
		querySql.append(" AND APP_APPLICATION_NO IS NULL");
		
		return ConnManager.executeQuery(querySql.toString(), this);
	}
	
	public TempApplyEntity queryTempApplyByNo(String userName,String tempAppNo){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND USERNAME = '"+ userName +"' AND TEMP_APPLICATION_NO = '"+ tempAppNo +"'");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		
		return ConnManager.singleQuery(querySql.toString(), this);
	}

	/**
	 * 查询用户名是否存在
	 * @param appNo
	 * @return
	 */
	public TempApplyEntity queryUnameExist(String userName) {


		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND USERNAME = '"+ userName +"'");

		TempApplyEntity app = ConnManager.singleQuery(querySql.toString(), this);
		
		return app;
	}
     public TempApplyEntity queryTempApplyByDX(String userName,String tempAppNo,String isDx){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND USERNAME = '"+ userName +"' AND TEMP_APPLICATION_NO = '"+ tempAppNo +"' and app_type = '"+isDx+"'");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		
		return ConnManager.singleQuery(querySql.toString(), this);
	}
     
     public List<TempApplyEntity> queryTempPhoneData(String userName,String isDx,String tempAppNo){
 		
 		StringBuffer querySql = new StringBuffer(QUERY_SQL);
 		querySql.append(" AND USERNAME = '"+ userName +"' AND TEMP_APPLICATION_NO = '"+ tempAppNo +"' and app_type = '"+isDx+"'");
 		
 		RecordUtils.writeAction(logger, null, querySql.toString());
 		
 		return ConnManager.executeQuery(querySql.toString(), this);
 	}
     
     
     public List<TempApplyEntity> queryTempData(String tempAppNo){
  		
  		StringBuffer querySql = new StringBuffer(QUERY_SQL);
  		querySql.append("  AND TEMP_APPLICATION_NO = '"+ tempAppNo +"' ");
  		
  		RecordUtils.writeAction(logger, null, querySql.toString());
  		
  		return ConnManager.executeQuery(querySql.toString(), this);
  	}
      
     
     
     public TempApplyEntity queryTempByTempNo(String tempAppNo){
 		
 		StringBuffer querySql = new StringBuffer(QUERY_SQL);
 		querySql.append(" AND TEMP_APPLICATION_NO = '"+ tempAppNo +"'");
 		
 		RecordUtils.writeAction(logger, null, querySql.toString());
 		
 		return ConnManager.singleQuery(querySql.toString(), this);
 	}
     
     //运行商
     public int jxlCount(String tempAppNo){
    	 String count_sql="SELECT COUNT(pay.mobile) "+
			"FROM cust_tempapply_info info LEFT JOIN cust_customer pay ON info.username = pay.username "+
			"WHERE EXISTS (SELECT * FROM jxl_person per WHERE pay.IDENTITY_NO = per.id_card_num "+  
			"AND pay.mobile = per.phoneNumber AND pay.name = per.real_name AND DATEDIFF(NOW(),per.reportdatetime) < 30)";
    	 StringBuffer querySql = new StringBuffer(count_sql);
  		querySql.append(" AND info.id = '"+ tempAppNo +"'");
  		RecordUtils.writeAction(logger, null, querySql.toString());
  		return ConnManager.getRowsCount(querySql.toString());
     }
 	
     //人行征信
     public int creditCount(String tempAppNo){
    	 String count_sql="SELECT COUNT(pay.mobile)"+
	    	 "FROM cust_tempapply_info info LEFT JOIN cust_customer pay ON info.username = pay.username "+
	    	 "WHERE "+
	    	  "info.id='"+ tempAppNo +"' AND "+
	    	 "EXISTS (SELECT 1 FROM hy_report_record per WHERE pay.IDENTITY_NO = per.id_no "+  
	    	 "AND pay.mobile = per.mobile AND pay.name = per.name AND  TIMESTAMPDIFF(DAY,per.CREATE_TIME,NOW()) <= 7 "+
	    	 "AND per.type=3 AND per.status=0  "+
	    	 ")";
  		RecordUtils.writeAction(logger, null, count_sql);
  		return ConnManager.getRowsCount(count_sql);
     }
 	
     //司机认证
     public int ddsjCount(String tempAppNo){
    	 String count_sql="SELECT COUNT(pay.mobile)"+
	    	 "FROM cust_tempapply_info info LEFT JOIN cust_customer pay ON info.username = pay.username "+
	    	 "WHERE "+
	    	  "info.id='"+ tempAppNo +"' AND "+
	    	 "EXISTS (SELECT 1 FROM hy_report_record per WHERE pay.IDENTITY_NO = per.id_no "+  
	    	 "AND info.PHONE_DATA_ID = per.mobile AND pay.name = per.name AND  TIMESTAMPDIFF(DAY,per.CREATE_TIME,NOW()) <= 7 "+
	    	 "AND per.type=3 AND per.status=0  "+
	    	 ")";
  		RecordUtils.writeAction(logger, null, count_sql);
  		return ConnManager.getRowsCount(count_sql);
     }
 	   
}
