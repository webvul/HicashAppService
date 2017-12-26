package com.hengyuan.hicash.domain.service.user;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ProcessConst;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.apply.ApplicationPicEvent;
import com.hengyuan.hicash.domain.event.apply.ApplyPayEvent;
import com.hengyuan.hicash.domain.event.apply.CreateAppPayEvent;
import com.hengyuan.hicash.domain.event.apply.InputAppPayEvent;
import com.hengyuan.hicash.domain.event.apply.TempAppInfoEvent;
import com.hengyuan.hicash.domain.event.user.CollectCardEvent;
import com.hengyuan.hicash.domain.query.app.ApplicationPicQuery;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.param.TempApplyPicQuery;
import com.hengyuan.hicash.domain.query.user.CollectAccountQuery;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.mktApp.CreateAppPayByMDService;
import com.hengyuan.hicash.domain.service.notic.NoticeSendService;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.entity.app.InputAppPay;
import com.hengyuan.hicash.entity.app.PicParam;
import com.hengyuan.hicash.entity.param.NoticeSendParam;
import com.hengyuan.hicash.entity.param.TempApplyPicEntity;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.exception.SaveCardException;
import com.hengyuan.hicash.exception.SendMobileMsgException;
import com.hengyuan.hicash.exception.StoreCodeInvalidException;
import com.hengyuan.hicash.exception.StoreCodeNotFoundException;
import com.hengyuan.hicash.exception.UpdateAppPayException;
import com.hengyuan.hicash.exception.UpdateApplicationPicException;
import com.hengyuan.hicash.exception.UpdateCardException;
import com.hengyuan.hicash.exception.UpdateInputAppException;
import com.hengyuan.hicash.exception.UpdateTempAppException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.param.CardParam;
import com.hengyuan.hicash.parameters.request.user.SaveAppAccountReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.SaveAppAccountResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 保存申请件账户资料 service
 * 提交申请（蓝领数码业务）
 * @author Cary.Liu
 * @createDate 2016-01-26
 *
 */
public class SaveAppAccountService implements RespService {

	private static Logger logger = Logger.getLogger(SaveAppAccountService.class);
	
	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		SaveAppAccountReq reqParam = (SaveAppAccountReq)parmRequest;
		SaveAppAccountResp respParam = new SaveAppAccountResp();
		
		try {
			
			CustomerEntity customer = queryCustomer(reqParam.getUserName());
			
			if(customer != null){
				
				ApplicationEntity appPay = queryApp(reqParam.getUserName(), reqParam.getAppNo());
				
				if(appPay != null){
					// 不能重复提交到审核
					if(!ProcessConst.ALL_NODE_SH.equals(appPay.getAllnode())){
						
						/* 业务请求验证 */
						reqValidate(customer, appPay);
						
						ConnManager.beginTransaction();
						
						/* 更新用户收款账户 */
						updateUserCardInfo(reqParam, customer);
						
						/* 更新用户申请件信息 */
						updateInputAppInfo(reqParam.getUserName(), reqParam.getAppNo());
						
						/* 更新申请件代扣账户信息 */
						updateInputApp(reqParam);
						
						/* 更新申请件节点 */
						updateAppPay(reqParam);
						
						/* 拷贝图片 */
						updateAppPic(reqParam);
						
						/* 是否走聚信立 (蓝领数码、蓝领秒贷初次提交走聚信立) */
//						if(!ProcessConst.STATUS24.equals(appPay.getStatus())){
						if(ProcessConst.STATUS05.equals(appPay.getStatus()) || ProcessConst.STATUS01.equals(appPay.getStatus())){
							updateJxlNode(reqParam.getAppNo());
						}
						
						ConnManager.commit();
						
						/* 申请成功，发送短信（聚信立验证短信） */
						smsSuccNotice(reqParam, customer, appPay);
						
						resultCode = ResultCodes.NORMAL;
						
					}else{
						resultCode = ResultCodes.SAVEAPPACCT_REPEATCMT_FAIL;
					}
					
				}else{
					resultCode = ResultCodes.SAVEAPPACCT_APP_NOTFOUND;
				}
				
			}else{
				resultCode = ResultCodes.SAVEAPPACCT_USER_NOTFOUND;
			}
			
		} catch (StoreCodeNotFoundException e) {
			resultCode = ResultCodes.REGISTER_STORECODE_NOTFOUND;
			e.printStackTrace();
		} catch (StoreCodeInvalidException e) {
			resultCode = ResultCodes.REGISTER_STORECODE_INVALID;
			e.printStackTrace();
		} catch (UpdateCardException e){
			resultCode = ResultCodes.SAVEAPPACCT_EXCEPTION1;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (UpdateInputAppException e){
			resultCode = ResultCodes.SAVEAPPACCT_EXCEPTION2;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (UpdateAppPayException e){
			resultCode = ResultCodes.SAVEAPPACCT_EXCEPTION3;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (UpdateTempAppException e){
			resultCode = ResultCodes.SAVEAPPACCT_EXCEPTION4;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			resultCode = ResultCodes.SAVEAPPACCT_EXCEPTION;
			ConnManager.rollback();
		} finally {
			ConnManager.closeConn();
		}
		
		respParam.setResultCode(resultCode);
		return respParam;
	}
	
	/** 
	 * 将申请件图片从临时表拷贝至申请件图片表
	 * @param reqParam
	 * @throws UpdateApplicationPicException
	 */
	public void updateAppPic(SaveAppAccountReq reqParam) throws UpdateApplicationPicException {
		String userName = reqParam.getUserName();//用户名
		String appNo = reqParam.getAppNo();//流水号
		TempApplyPicQuery tempApplyQuery = new TempApplyPicQuery();
		List<TempApplyPicEntity> tempPicList = tempApplyQuery.queryTempApplyPic(userName);
		
		for(TempApplyPicEntity tempApplyPic : tempPicList){
			//根据流水号以及图片类型 查询  application_pic表中是否存在数据
			ApplicationPicQuery appPicQuery = new ApplicationPicQuery();
			List<PicParam> picList = appPicQuery.queryAppPicByAppNoAndType(appNo, tempApplyPic.getPicType());
			ApplicationPicEvent applicationPicEvent = new ApplicationPicEvent();
			if(picList != null && picList.size() == 1){//不为空,或等于1,更新
				Map<String, Object> appPicMap  = new HashMap<String, Object>();
				Map<String, Object> whereMap = new HashMap<String, Object>();
				whereMap.put("pic_id", picList.get(0).getPicId());
				appPicMap.put("pic_caption", tempApplyPic.getPicCaption());
				appPicMap.put("pic_path", replace(tempApplyPic.getPicPath()));
				appPicMap.put("thum_path", replace(tempApplyPic.getThumPath()));
				appPicMap.put("update_user", userName);
				appPicMap.put("update_date", DateUtils.getDateStr(new Date()));
				applicationPicEvent.updateAppPic(appPicMap, whereMap);
			}else{//为空,或大于1张,保存申请件图片
				PicParam picParam = new PicParam();
				picParam.setAppNo(appNo);
				picParam.setCaption(tempApplyPic.getPicCaption());
				picParam.setPicName(tempApplyPic.getPicName());
				picParam.setPicPath(replace(tempApplyPic.getPicPath()));
				picParam.setPicType(tempApplyPic.getPicType());
				picParam.setThumPath(replace(tempApplyPic.getThumPath()));
				applicationPicEvent.saveAppPic(picParam, userName);
			}
		}
	}

	private String replace(String str){
		if(StringUtils.isEmpty(str)) return null;
		return str.replace("\\", "/");
	}
	/**
	 * 更新inputApp资料
	 * @param userName
	 * @param appNo
	 * @throws UpdateInputAppException
	 */
	private void updateInputAppInfo(String userName, String appNo) throws UpdateInputAppException{
		
		CustcustomerQuery customerInfoQuery = new CustcustomerQuery();
		CustCustomer customer = customerInfoQuery.queryCustCustomer(userName);
		
		CreateAppPayByMDService createAppService = new CreateAppPayByMDService();
		InputAppPay inputAppPay = createAppService.getInputAppPay(customer, appNo);
		
		CreateAppPayEvent createAppPayUpdate = new CreateAppPayEvent();
		createAppPayUpdate.updateInputAppPay(inputAppPay);
	}
	
	/**
	 * 业务请求验证
	 * @param reqParam
	 * @throws StoreCodeNotFoundException 
	 * @throws StoreCodeInvalidException 
	 */
	private void reqValidate(CustomerEntity customer, ApplicationEntity appPay) throws StoreCodeNotFoundException, StoreCodeInvalidException{
		
		/* 蓝领数码、蓝领秒贷业务-客户门店号是否审核通过 */
		if(RegexValidate.isIndustryLlsm(appPay.getIndustryCode()) || RegexValidate.isIndustryLlmd(appPay.getIndustryCode())){
			
			// STATUS05 蓝领数码   STATUS01 蓝领秒贷
			if(!ProcessConst.STATUS05.equals(appPay.getStatus()) || !ProcessConst.STATUS01.equals(appPay.getStatus())){
				
				/* 门店号是否存在 */
				/*SupStoreQuery storeQuery = new SupStoreQuery();
				SupStoreEntity storeEntity = storeQuery.querySupStoreByStoreNo(customer.getStoreCode());
				if(storeEntity == null){
					throw new StoreCodeNotFoundException();
				}*/
				
				/* 门店号是否有效（审核通过） */
			/*	if(!Consts.FINAL_NUMBER_1.equals(storeEntity.getStatus())){
					throw new StoreCodeInvalidException();
				}*/
				
			}
			
		}
		
	}
	
	/**
	 * 发送短信
	 * @param reqParam
	 * @param customer
	 */
	private void smsSuccNotice(SaveAppAccountReq reqParam, CustomerEntity customer, ApplicationEntity appPay){
		
		NoticeSendParam smsparam = new NoticeSendParam();
		smsparam.setMobile(customer.getMobile());
		smsparam.setUserName(customer.getUserName());
		smsparam.setRealName(customer.getRealName());
		smsparam.setCustType(customer.getCustType());
		smsparam.setIndustryCode(appPay.getIndustryCode());
		smsparam.setAppNo(reqParam.getAppNo());
		sendSuccNotice(smsparam);
	}
	
	/**
	 * 申请件创建成功，发送短信
	 * @param param
	 * @throws SendMobileMsgException
	 */
	public void sendSuccNotice(NoticeSendParam param) {

		NoticeSendService sendService = new NoticeSendService(param);
		sendService.sendAppApplyNotice();
	}
	
	/**
	 * 聚信立流程
	 * @param appNo
	 * @throws UpdateAppPayException
	 * @throws UpdateTempAppException
	 */
	private void updateJxlNode(String appNo) throws UpdateAppPayException, UpdateTempAppException{
		
		Map<String,Object> input = new HashMap<String, Object>();
		input.put("ALLNODE", Consts.FIRST_ALL_NODE);
		input.put("NODE", Consts.FIRST_NODE);
		input.put("STATUS", Consts.FIRST_STATUS);
		
		Map<String,Object> condit = new HashMap<String, Object>();
		condit.put("APP_APPLICATION_NO", appNo);
		ApplyPayEvent appPayEvent = new ApplyPayEvent();
		appPayEvent.updateAppPay(input, condit);
		
		/* 更新临时申请表 */
		TempAppInfoEvent tempEvent = new TempAppInfoEvent();
		tempEvent.updateTempCreateAppFlag(appNo);
	}
	
	/**
	 * 更新申请件节点
	 * @param reqParam
	 * @throws UpdateAppPayException
	 */
	private void updateAppPay(SaveAppAccountReq reqParam) throws UpdateAppPayException{
		
		Map<String, Object> appPayMap = new HashMap<String, Object>();
		appPayMap.put("ALLNODE", ProcessConst.ALL_NODE_SH);
		appPayMap.put("status", ProcessConst.STATUS07);
		appPayMap.put("node", ProcessConst.NODE06);
		appPayMap.put("merchant_endtime", DateUtils.getDateStr(new Date()));
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("app_application_no", reqParam.getAppNo());
		
		ApplyPayEvent appPayEvent = new ApplyPayEvent();
		appPayEvent.updateAppPay(appPayMap, whereMap);
	}
	
	/**
	 * 更新申请件代扣信息
	 * @param reqParam
	 * @throws SQLException
	 * @throws UpdateInputAppException
	 */
	private void updateInputApp(SaveAppAccountReq reqParam) throws SQLException, UpdateInputAppException{
		
		InputAppPayEvent inputAppEvent = new InputAppPayEvent();
		Map<String,Object> inputPay = new HashMap<String, Object>();
		inputPay.put("PROXY_OPENBANK", StringUtils.valueOf(reqParam.getOpenBank()));
		inputPay.put("PROXY_BANKCARD", StringUtils.valueOf(reqParam.getBankCard()));
		inputPay.put("IS_PROXY", reqParam.getIsOpenProxy());
		
		inputAppEvent.updateInputAppPay(inputPay, reqParam.getAppNo());
	}
	
	/**
	 * 更新用户收款账户
	 * 
	 * @param req
	 * @throws UpdateCardException
	 * @throws SaveCardException
	 */
	public void updateUserCardInfo(SaveAppAccountReq req, CustomerEntity customer) throws UpdateCardException, SaveCardException {

		CollectCardEvent cardEvent = new CollectCardEvent();
		CollectAccountQuery accountQuery = new CollectAccountQuery();

		boolean isExist = accountQuery.queryIdEntityNoExist(req.getUserName(),req.getBankCard());
		
		CardParam param = new CardParam();
		param.setUserName(req.getUserName());
		param.setRealName(customer.getRealName());
		param.setBankType(Consts.CARD_TYPE_JJKT);
		param.setBank(req.getOpenBank());
		param.setBankCard(req.getBankCard());
		param.setIsDefaultCard(Consts.DEFAULT_CARD_YES);
//		if (Consts.FINAL_NUMBER_0.equals(req.getIsSynPerAcct())) {
//			param.setIsDefaultCard(Consts.DEFAULT_CARD_NO);
//		} else {
//			param.setIsDefaultCard(Consts.DEFAULT_CARD_YES);
//		}
		if (Consts.DEFAULT_CARD_YES.equals(param.getIsDefaultCard().trim())) {
			cardEvent.updateDefaultCardByApp(req.getUserName());
		}

		if (isExist) {
			cardEvent.updateUserCardByApp(param);
		} else {
			cardEvent.saveUserCardByApp(param);
		}
	}
	
	private ApplicationEntity queryApp(String userName,String appNo){
		
		ApplicationQuery appQuery = new ApplicationQuery();
		
		return appQuery.queryAppByUserAndAppNo(userName, appNo); 
	}
	
	private CustomerEntity queryCustomer(String userName){
		
		CustomerQuery customerQuery = new CustomerQuery();
		
		return customerQuery.queryCustByUserName(userName);
	}

}
