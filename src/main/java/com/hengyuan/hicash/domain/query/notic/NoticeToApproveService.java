package com.hengyuan.hicash.domain.query.notic;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.user.ApproveUserQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.HyUserSiteQuery;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.entity.notic.NoticeVariable;
import com.hengyuan.hicash.entity.user.ApproveUser;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.entity.user.HyUserSite;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.notice.NoticeTemplateUtils;

/**
 * 短信通知业务员
 * @author Cary.Liu
 * @create 2015-03-24
 *
 */
public class NoticeToApproveService {
	
	private static Logger logger = Logger.getLogger(NoticeToApproveService.class);
	
	private String resultCode = "00000";

	private NoticeTemplateUtils noticeUtil = new NoticeTemplateUtils();
	
	/**
	 * 短信通知业务员
	 * @param userName
	 * @param appNo
	 */
	public String sendToApprove(String userName,String appNo){
		
		try {
			
			ApplicationEntity appEntity = queryApp(userName, appNo);
			CustomerEntity customer = queryCust(userName);
			
			if(appEntity != null && (appEntity.getSiteCode() != null && !StringUtils.isEmpty(appEntity.getSiteCode())) && !(Consts.APP_CUSTOMER_TYPE_KHL1.equals(appEntity.getCustType())&&Consts.APPFLOW_TYPE_CASH.equals(appEntity.getPayType()))){
				
				if(!Consts.FINAL_NUMBER_1.equals(appEntity.getSendApproFlag())){
					
					List<HyUserSite> hyUserSites = queryHyUserSites(appEntity.getSiteCode());
					if(hyUserSites != null && hyUserSites.size() > 0){
						for (HyUserSite hyUserSite : hyUserSites) {
							
							ApproveUser approve = queryApproveUser(hyUserSite.getUserNum());
							if(approve!= null && customer.getMobile() != null){
								System.out.println("短信通知：业务员【"+approve.getRealName()+"】手机号：【"+approve.getMobileNo()+"】");
								
								NoticeVariable noticeVar = new NoticeVariable();
//								noticeVar.setUsername(approve.getUserName());
								noticeVar.setBussMobile(customer.getMobile());
								noticeVar.setRealname(appEntity.getAppRealName());
								noticeVar.setOperateTime(DateUtils.getDateByStr(appEntity.getCreateDate()));
								noticeVar.setProduct(appEntity.getProductName());
								
								noticeUtil.sendSmsToApprove(approve.getMobileNo(), approve.getUserName(), noticeVar);
								
							}else{
								System.out.println("短信通知有误：员工号【"+hyUserSite.getUserNum()+"】");
							}
							
						}
						/* 更改发送短信标志 */
						setSendMark(appNo);
						
						resultCode = ResultCodes.NORMAL;
					}else{
						resultCode = ResultCodes.SEND_NOTICE_APP_NOTFOUND;
					}
					
				}else{
					resultCode = ResultCodes.SEND_NOTICE_APP_SENDED;
				}
				
			}else{
				resultCode = ResultCodes.SEND_NOTICE_APP_NOTEXIST;
			}
			
		} catch (Exception e) {
			resultCode = ResultCodes.SEND_NOTICE_APP_EXCEPTION;
		}
		
		logger.info("短信通知业务员代码【"+ resultCode +"】");
		return resultCode;
	}
	
	public ApplicationEntity queryApp(String userName,String appNo){
		
		ApplicationQuery appQuery = new ApplicationQuery();
		return appQuery.queryAppByUserAndAppNo(userName, appNo);
	}
	
	public List<HyUserSite> queryHyUserSites(String siteCode){
		
		HyUserSiteQuery query = new HyUserSiteQuery();
		
		return query.querySiteListBySite(siteCode);
	}
	
	public ApproveUser queryApproveUser(String userNumber){
		
		ApproveUserQuery approveQuery = new ApproveUserQuery();
		return approveQuery.queryApprovByNum(userNumber);
	}
	
	public CustomerEntity queryCust(String userName){
		
		CustomerQuery custQuery = new CustomerQuery();
		
		return custQuery.queryCustomerByUserName(userName);
	}
	
	/**
	 * 标志为已发送短信
	 * @param appNo
	 */
	public void setSendMark(String appNo){
		
		String updateSql = "UPDATE d_application_pay SET SEND_APRO_FLAG = '1' WHERE app_application_no = '"+ appNo +"'";
		ConnManager.executeUpdate(updateSql);
	}
	
}
