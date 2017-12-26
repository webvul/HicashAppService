package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.AppAgainBasicEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class AppAgainBasicQuery extends AbstractDAO<AppAgainBasicEntity> {
	
    private static Logger logger = Logger.getLogger(AppAgainBasicQuery.class);
   
    @Override
	public AppAgainBasicEntity mapping(ResultSet rs) throws SQLException {
	
    	
		if (rs != null) {
			AppAgainBasicEntity appAgainBasicEntity = new AppAgainBasicEntity();
			appAgainBasicEntity.setAppTempNo(StringUtils.valueOf(rs.getObject("appTempNo")));
			appAgainBasicEntity.setCustName(StringUtils.valueOf(rs.getObject("custName")));
			appAgainBasicEntity.setIdentityNo(StringUtils.valueOf(rs.getObject("identityNo")));
			appAgainBasicEntity.setMaritalStatus(StringUtils.valueOf(rs.getObject("maritalStatus")));
			appAgainBasicEntity.setCustCompany(StringUtils.valueOf(rs.getObject("custCompany")));
			appAgainBasicEntity.setImmediateRelation(StringUtils.valueOf(rs.getObject("immediateRelation")));
			appAgainBasicEntity.setImmediateName(StringUtils.valueOf(rs.getObject("immediateName")));
			appAgainBasicEntity.setCustMobileNo(StringUtils.valueOf(rs.getObject("custMobileNo")));
			appAgainBasicEntity.setOperatorStatus(StringUtils.valueOf(rs.getObject("operatorStatus")));
			appAgainBasicEntity.setEmergencyName(StringUtils.valueOf(rs.getObject("emergencyName")));
			appAgainBasicEntity.setEmergencyRelation(StringUtils.valueOf(rs.getObject("emergencyRelation")));
			
			
			appAgainBasicEntity.setWorkProvince(StringUtils.valueOf(rs.getObject("workProvince")));
			appAgainBasicEntity.setWorkCity(StringUtils.valueOf(rs.getObject("workCity")));
			appAgainBasicEntity.setWorkArea(StringUtils.valueOf(rs.getObject("workArea")));
			appAgainBasicEntity.setWorkRoad(StringUtils.valueOf(rs.getObject("workRoad")));
			appAgainBasicEntity.setLiveProvince(StringUtils.valueOf(rs.getObject("liveProvince")));
			appAgainBasicEntity.setLiveCity(StringUtils.valueOf(rs.getObject("liveCity")));
			appAgainBasicEntity.setLiveArea(StringUtils.valueOf(rs.getObject("liveArea")));
			appAgainBasicEntity.setLiveAddress(StringUtils.valueOf(rs.getObject("liveAddress")));
			appAgainBasicEntity.setCustType(StringUtils.valueOf(rs.getObject("custType")));
			appAgainBasicEntity.setxXStatus(StringUtils.valueOf(rs.getObject("xXStatus")));
			appAgainBasicEntity.setUsername(StringUtils.valueOf(rs.getObject("USERNAME")));
			
			return appAgainBasicEntity;
		} else {
			return null;
		}
	
	}
	
   public AppAgainBasicEntity queryCustInfo(String appTempNo){
	   String sql ="SELECT tempApp.TEMP_APPLICATION_NO AS appTempNo,customer.NAME AS custName,customer.IDENTITY_NO AS identityNo,customer.marital_Status AS maritalStatus,customer.unitName AS custCompany,customer.immediate_Relation AS immediateRelation,customer.immediate_Name AS immediateName,customer.emergency_Name AS emergencyName,customer.emergency_Relation AS emergencyRelation,customer.MOBILE AS custMobileNo,tempApp.JXL_PHONE_RESULT AS operatorStatus," +
			" work_Area_Province AS workProvince,work_Area_City AS workCity,work_Area_Area AS workArea,work_Area_Road AS workRoad, " +   
			" other_Adress_Province AS liveProvince,other_Adress_City AS liveCity,Other_Adress_Area AS liveArea,other_Accommodation_Address AS liveAddress," + 
			" customer.customerType AS custType,tempApp.REPORT_TYPE AS xXStatus,tempApp.USERNAME AS username" +
			" FROM cust_tempapply_info AS tempApp LEFT JOIN cust_customer AS customer ON tempApp.USERNAME = customer.USERNAME " +
	   		" WHERE tempApp.TEMP_APPLICATION_NO = '"+appTempNo+"' ";
	  RecordUtils.writeAction(logger, "", sql);
	  return ConnManager.singleQuery(sql, this);
   }

}
