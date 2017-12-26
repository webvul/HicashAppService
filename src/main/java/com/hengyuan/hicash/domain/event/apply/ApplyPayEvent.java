package com.hengyuan.hicash.domain.event.apply;

import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.app.CallPhoneDataParam;
import com.hengyuan.hicash.exception.CreateAppPayException;
import com.hengyuan.hicash.exception.UpdateAppPayException;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 申请件更新实现类
 * 
 * @author Cary.Liu
 * @create 2014-08-04
 */
public class ApplyPayEvent {
	private static Logger logger = Logger.getLogger(ApplyPayEvent.class);
	/**
	 * 根据SQL语句更新申请件
	 * 
	 * @param updateSql
	 * @throws UpdateAppPayException
	 * @author Andy.Niu
	 * @create 2014-07-29
	 */
	public void updatePayBySql(String updateSql) throws UpdateAppPayException {
		RecordUtils.writeAction(logger, null, updateSql);
		/* 更新申请件状态 */
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new UpdateAppPayException();
		}
	}

	
	public void createAppPay(Map<String, Object> appPayMap)
			throws CreateAppPayException {

		String createSql = MapAssemForSql.getInsertSql(TableConsts.APPLICATION_PAY,appPayMap);
		RecordUtils.writeAction(logger, null, createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new CreateAppPayException();
		}

	}
	
	/**
	 * 修改申请件状态到设置资料
	 * @param appNo
	 * @throws UpdateAppPayException
	 */
	public void updateAppPayToSetInfoStatus(String appNo) throws UpdateAppPayException{
		
		String updateSql = "UPDATE d_application_pay SET ALLNODE = 'ZLNODE',node = 'NODE04',status = 'STATUS05' WHERE app_application_no = '"+appNo+"'";
		RecordUtils.writeAction(logger, null, updateSql);
		/* 更新申请件状态 */
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new UpdateAppPayException();
		}
	}
	/**
	 * 修改申请件期数，月还款额
	 * @param applicationPay
	 * @throws UpdateAppPayException
	 */
	public void updateApp(ApplicationPay applicationPay) throws UpdateAppPayException{
		String sql="UPDATE d_application_pay set app_creditproduct_id='"+applicationPay.getCreditProductId()+"' ,  app_month_pay='"+applicationPay.getMonthPay()+"' ,  app_install_ment='"+applicationPay.getInstallMent()+"' , tranPrice='"+applicationPay.getTranPrice()+"' , APPLY_AMOUNT='"+applicationPay.getApplyAmount()+"' , producttype='"+applicationPay.getProductType()+"' where app_application_no='"+applicationPay.getApplicationNo()+"'";
		RecordUtils.writeAction(logger, null, sql);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateAppPayException();
		}
	}
	/**
	 * 修改商品名字，选择手机号码，套餐ID；
	 * @param applicationPay
	 * @throws UpdateAppPayException
	 */
	public void updateAppDx(CallPhoneDataParam applicationPay) throws UpdateAppPayException{
		String code="0153";
		String service="hynj008";
		String sql="UPDATE d_application_pay set product_name='"+applicationPay.getProDataName()+"' ,  dx_pro_phone='"+applicationPay.getPhoneNo()+"'  ,  dx_phonedata_id='"+applicationPay.getPhoneDataId()+"', app_sale_code='"+code+"'   , app_sale_server='"+service+"'  where app_application_no='"+applicationPay.getAppNo()+"'";
		RecordUtils.writeAction(logger, null, sql);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateAppPayException();
		}
	}
	
	/**
	 * 更新申请件节点到审批
	 * @param applicationPay
	 * @throws UpdateAppPayException
	 */
	public void updateAppNodeToCs(String appNo) throws UpdateAppPayException{
		
		String sql="UPDATE d_application_pay SET ALLNODE = 'SHNODE',node = 'NODE06',status = 'STATUS07' WHERE app_application_no = '"+appNo+"'";
		RecordUtils.writeAction(logger, null, sql);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateAppPayException();
		}
		
	}
	
	public void updateAppPay(Map<String, Object> appPayMap, Map<String, Object> whereMap) throws UpdateAppPayException  {

		String createSql = MapAssemForSql.getUpdateSql(TableConsts.APPLICATION_PAY, appPayMap, whereMap);
		RecordUtils.writeAction(logger, null, createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new UpdateAppPayException();
		}

	}
	
//	/**
//	 * 更改额度/期数
//	 * @param appPay
//	 * @throws UpdateAppPayException 
//	 */
//	public void updateAppByChangeAmount(ApplicationEntity appPay) throws UpdateAppPayException{
//		
//		Map<String, Object> updateCol = new HashMap<String, Object>();
//		updateCol.put("app_creditproduct_id", appPay.getLoanProduct());
//		updateCol.put("APPLY_AMOUNT", appPay.getApplyAmount());
//		updateCol.put("tranPrice", appPay.getTranPrice());
//		updateCol.put("app_month_pay", appPay.getMonthPay());
//		updateCol.put("app_install_ment", appPay.getInstallMent());
//		updateCol.put("FIRST_PAY_AMOUNT", appPay.getFirstPayMoney());
//		updateCol.put("FIRST_PAY_PRINCIPAL", appPay.getFirstPayMoney());
//		
//		Map<String, Object> whereCol = new HashMap<String, Object>();
//		whereCol.put("app_application_no", appPay.getAppNo());
//		
//		String updateSql =  MapAssemForSql.getUpdateSql(TableConsts.APPLICATION_PAY, updateCol,whereCol);
//		RecordUtils.writeAction(logger, null, updateSql);
//		
//		if(ConnManager.executeUpdate(updateSql) < 1){
//			throw new UpdateAppPayException();
//		}
//	}
	

}
