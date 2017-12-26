package com.hengyuan.hicash.domain.service.mktApp;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ProcessConst;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.assem.CreateAppPayAssem;
import com.hengyuan.hicash.domain.assem.InputAppPayAssem;
import com.hengyuan.hicash.domain.event.apply.AppExceptionEvent;
import com.hengyuan.hicash.domain.event.apply.ApplyPayEvent;
import com.hengyuan.hicash.domain.event.apply.ApproveRecordEvent;
import com.hengyuan.hicash.domain.event.apply.CreateAppPayEvent;
import com.hengyuan.hicash.domain.event.apply.CreateLoanLoanUpdate;
import com.hengyuan.hicash.domain.event.apply.CustLimitEvent;
import com.hengyuan.hicash.domain.event.apply.InputAppPayEvent;
import com.hengyuan.hicash.domain.event.apply.LoanLoanRecordEvent;
import com.hengyuan.hicash.domain.event.apply.TempAppInfoEvent;
import com.hengyuan.hicash.domain.query.amount.AvailbleCreditQuery;
import com.hengyuan.hicash.domain.query.app.ApplicationPayQuery;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.app.CustLimitBlueQuery;
import com.hengyuan.hicash.domain.query.app.ProductQuery;
import com.hengyuan.hicash.domain.query.mer.MerProductQuery;
import com.hengyuan.hicash.domain.query.mer.SaleSiteQuery;
import com.hengyuan.hicash.domain.query.mktApp.AcctAccountQuery;
import com.hengyuan.hicash.domain.query.mktApp.CreateAppPayQuery;
import com.hengyuan.hicash.domain.query.mktApp.CustPayAccountQuery;
import com.hengyuan.hicash.domain.query.mktApp.IndustryTypeQuery;
import com.hengyuan.hicash.domain.query.mktApp.LoanloanQuery;
import com.hengyuan.hicash.domain.query.mktApp.PayFlowNoQuery;
import com.hengyuan.hicash.domain.query.rule.RulesQuery;
import com.hengyuan.hicash.domain.query.user.ApproveUserQuery;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.query.user.HyEmployeeQuery;
import com.hengyuan.hicash.domain.rule.IntoRules;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.AcctAccount;
import com.hengyuan.hicash.entity.app.AppExceptionEntity;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.app.CustPayAccount;
import com.hengyuan.hicash.entity.app.InputAppPay;
import com.hengyuan.hicash.entity.app.LoanProduct;
import com.hengyuan.hicash.entity.mer.MerProductEntity;
import com.hengyuan.hicash.entity.mer.SaleSiteEntity;
import com.hengyuan.hicash.entity.mktApp.ApprovalRecord;
import com.hengyuan.hicash.entity.mktApp.IndustryTypeEntity;
import com.hengyuan.hicash.entity.mktApp.LoanLoan;
import com.hengyuan.hicash.entity.user.ApproveUser;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.CustLimit;
import com.hengyuan.hicash.entity.user.HyEmployeeEntity;
import com.hengyuan.hicash.exception.AppExitErrorException;
import com.hengyuan.hicash.exception.ApproveBusinessException;
import com.hengyuan.hicash.exception.ApproveUserNotFound;
import com.hengyuan.hicash.exception.CancelCallarAppException;
import com.hengyuan.hicash.exception.CreateAppPayException;
import com.hengyuan.hicash.exception.CreateInputPayException;
import com.hengyuan.hicash.exception.CustTypeRoleException;
import com.hengyuan.hicash.exception.CustomerNotFoundException;
import com.hengyuan.hicash.exception.GenerateFlowNoException;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.exception.IsNotLanUserException;
import com.hengyuan.hicash.exception.MerProductNotFoundException;
import com.hengyuan.hicash.exception.OneMonthApplyException;
import com.hengyuan.hicash.exception.ProductNotFoundException;
import com.hengyuan.hicash.exception.QueryFlowNoException;
import com.hengyuan.hicash.exception.QueryUserCardNotFoundException;
import com.hengyuan.hicash.exception.RuleValException;
import com.hengyuan.hicash.exception.SiteNotFoundException;
import com.hengyuan.hicash.exception.TaskNoIndustryFailException;
import com.hengyuan.hicash.exception.TaskNoNotFoundException;
import com.hengyuan.hicash.exception.UniversityNotFound;
import com.hengyuan.hicash.exception.UpdateAppPayException;
import com.hengyuan.hicash.exception.UpdateCustomerTimeException;
import com.hengyuan.hicash.exception.UpdateInputAppException;
import com.hengyuan.hicash.exception.UpdateTempAppException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppBlueCollarReq;
import com.hengyuan.hicash.parameters.request.param.LoanAmtCalReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.mktApp.CreateAppBlueCollarResp;
import com.hengyuan.hicash.parameters.response.user.LoanAmtCalResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.LoanPeriod;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * 创建申请件 
 * 创建蓝领数码申请 
 * @author LiHua.Ren
 * @createDate 2016-01-25
 * */
public class CreateAppBlueCollarService implements RespService {
	
	private static Logger logger = Logger.getLogger(CreateAppBlueCollarService.class);
	
	private LoanloanQuery loanloanQuery = new LoanloanQuery();
	
	LoanAmtCalResp loanAmtCalResp = null;
	
	private ApplicationEntity applicationEntity = null;
	
	String monthPay = "";//月还款
	String installMent = "";//还款期数;
	
	private CustCustomer customer = null;
	private MerProductEntity proEntity = null;
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		
		/* 创建申请件 */
		CreateAppBlueCollarResp createAppPayResp = new CreateAppBlueCollarResp();
		String resultCode = "";
		String applicationNo = "";
		Long start = System.currentTimeMillis();
		
		try {
			
			CreateAppBlueCollarReq req = (CreateAppBlueCollarReq)parmRequest;
			//查询是否是申请的第几笔
			checkApp(req);
			if(useAmountFlag(req)){
				
				if(minUseAmount(req.getTranPrice())){
					
					ConnManager.beginTransaction();
					/* 创建申请件 */
					applicationNo = initApplicationPay(req);
					queryPayMsg(applicationNo);
					createAppPayResp.setApplyAmount(req.getTranPrice());
					resultCode = ResultCodes.NORMAL;
					ConnManager.commit();
					applicationEntity = queryAppPayByNo(applicationNo);
				}else{
					resultCode = ResultCodes.CERATE_APPPAY_USEAMOUNT_MIN_FALSE;
				}
				
			}else{
				resultCode = ResultCodes.CERATE_APPPAY_USEAMOUNT_FALSE;
			}
		} catch (IsNotLanUserException e){
			resultCode = ResultCodes.CREATEPAY_ISNOTLANUSER;
		} catch (TaskNoIndustryFailException e){
			resultCode = ResultCodes.CREATEAPP_TASKNO_FAIL;
		} catch (TaskNoNotFoundException e){
			resultCode = ResultCodes.CREATEAPP_TASKNO_NOTFOUND;
		} catch (MerProductNotFoundException e){
			resultCode = ResultCodes.CREATEAPP_MERPRO_NOTFOUND;
		} catch (CustTypeRoleException e){
			resultCode = ResultCodes.CREATEAPP_CUSTROLE_FAIL;
		} catch (GenerateFlowNoException e) {
			ConnManager.rollback();
			/* 流水号生成失败 */
			resultCode = ResultCodes.CREATE_APPPAY_GET_APPNO_FAILD;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_GET_APPNO_FAILD, e);
//			resultCode = ResultCodes.CREATE_APPPAY_REPEAT_FAILD;
//			/* 记录错误信息 */
//			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_REPEAT_FAILD, null);
		}catch (AppExitErrorException e) {
//			ConnManager.rollback();
			//该用户已经存在申请件，不能重复申请
			resultCode = ResultCodes.CREATE_APPPAY_REPEAT_FAILD;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_REPEAT_FAILD, null);
		}catch (HttpException e) {
			ConnManager.rollback();
			/* 调用账务服务金额计算失败 */
			resultCode = ResultCodes.CREATE_APPPAY_GET_ACCT_FAILD;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_GET_ACCT_FAILD, e);
			
		} catch(SiteNotFoundException e){
			ConnManager.rollback();
			/* 售点未查询到 */
			resultCode = ResultCodes.CREATEAPP_SITE_NOTFOUND;
		} catch (ProductNotFoundException e) {
			ConnManager.rollback();
			/* 申请实物产品未查询到 */
			resultCode = ResultCodes.CREATE_APPPAY_QUERY_PROINFO_FAILD;
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_QUERY_PROINFO_FAILD, e);
			
		} catch (CreateAppPayException e) {
			ConnManager.rollback();
			/* 创建申请件对象失败 */
			resultCode = ResultCodes.CREATE_APPPAY_CREAT_APP_FAILD;
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_CREAT_APP_FAILD, e);
			
		} catch (UpdateAppPayException e){
			ConnManager.rollback();
			/* 更改申请件节点失败 */
			resultCode = ResultCodes.CREATE_APPPAY_UPDATE_NODE_FAILD;
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_UPDATE_NODE_FAILD, e);
		} catch (CancelCallarAppException e){
//			ConnManager.rollback();
			/* 白领自动取消申请件*/
			resultCode = ResultCodes.CREATE_APPPAY_CALLRA_CANCEL;
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_CALLRA_CANCEL, e);
		} catch (RuleValException e){
			resultCode = ResultCodes.CREATE_APPPAY_RULE_IN;
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_RULE_IN, e);
		} catch (SQLException e) {
			ConnManager.rollback();
			/* 处理失败 */
			resultCode = ResultCodes.CREATE_APPPAY_SQL_ERROR;
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_SQL_ERROR, e);
			
		} catch (QueryFlowNoException e) {
			ConnManager.rollback();
			/* 当天流水序号查询失败 */
			resultCode = ResultCodes.CREATE_APPPAY_QUERY_APPNO_FAILD;
			
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_QUERY_APPNO_FAILD, e);
			
		} catch (CustomerNotFoundException e) {
//			ConnManager.rollback();
			/* 申请客户信息未查询到 */
			resultCode = ResultCodes.CREATE_APPPAY_APPLYINFO_NOT_FOUND;
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_APPLYINFO_NOT_FOUND, e);
			
		} catch (ApproveUserNotFound e){
			resultCode = ResultCodes.CREATE_APPPAY_APPROVEUSER_NOT_FOUND;
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_APPLYINFO_NOT_FOUND, e);
		} catch (UniversityNotFound e) {
			ConnManager.rollback();
			/* 学校信息未找到 */
			resultCode = ResultCodes.CREATE_APPPAY_SCHOOLINFO_NOT_FOUND;
			
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_SCHOOLINFO_NOT_FOUND, e);
			
		} catch (ApproveBusinessException e) {
			ConnManager.rollback();
			/* 销售人员和学校对应关系未查询到 */
			resultCode = ResultCodes.CREATE_APPPAY_LINKINFO_NOT_FOUND;
			
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_LINKINFO_NOT_FOUND, e);
			
		} catch (IOException e) {
			ConnManager.rollback();
			/* 接收账务服务返回处理异常 */
			resultCode = ResultCodes.CREATE_APPPAY_IO_EXCEPTION;
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_IO_EXCEPTION, e);
			
		} catch (HttpReturnException e) {
			ConnManager.rollback();
			/* 账务服务连接异常 */
			resultCode = ResultCodes.CREATE_APPPAY_ACCOUNT_ERROR;
			
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_ACCOUNT_ERROR, e);
			
		} catch (CreateInputPayException e) {
			ConnManager.rollback();
			/* 创建申请件信息对象异常 */
			resultCode = ResultCodes.CREATE_APPPAY_INPUT_ERROR;
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_INPUT_ERROR, e);
		} catch (UpdateCustomerTimeException e) {
			ConnManager.rollback();
			/* 创建申请件信息对象异常 */
			resultCode = ResultCodes.END_SCHOOL_TIME_ERROR;
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.END_SCHOOL_TIME_ERROR, e);
		} catch(UpdateTempAppException e){
			ConnManager.rollback();
			resultCode = ResultCodes.REGISTER_APPLYTEMP_EXCEPTION;
		} catch (QueryUserCardNotFoundException e) {
			ConnManager.rollback();
			/* 创建申请件卡号信息异常 */
			resultCode = ResultCodes.USER_CARD_NOT_FOUND;
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.USER_CARD_NOT_FOUND, e);
		} catch (Exception e){
			e.printStackTrace();
			ConnManager.rollback();
			/* 服务器处理异常 */
			resultCode = ResultCodes.CREATE_APPPAY_EXCEPTION;
			
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATE_APPPAY_EXCEPTION, e);
			e.printStackTrace();
		} finally {
			ConnManager.closeConn();
		}
		
		
		System.out.println("耗时[" + (System.currentTimeMillis() - start) + "]ms");
		if(applicationEntity != null ){
//			createAppPayResp.setFirstMoney(applicationEntity.getFirstPayMoney());//首付金额
			createAppPayResp.setProName(applicationEntity.getProductName());
//			createAppPayResp.setSupplierName(applicationEntity.getSupplierName());
//			createAppPayResp.setSiteName(applicationEntity.getSiteName());
		}
		createAppPayResp.setResultCode(resultCode);
		createAppPayResp.setMonthPayAmount(monthPay);
		createAppPayResp.setMonthInstallMent(installMent);
		createAppPayResp.setAppPayNo(applicationNo);
		
		return createAppPayResp;
	}

	/**
	 * 查询申请件的月还款额和还款期数
	 */
	public void queryPayMsg(String appNo){
		ApplicationPayQuery payQuery = new ApplicationPayQuery();
		ApplicationPay applicationPay = payQuery.queryApplicationPayById(appNo);
		if(applicationPay != null){
			monthPay = applicationPay.getMonthPay();
			installMent = applicationPay.getInstallMent();
		}
		
	}
	
	/**
	 * 判断用户的申请额度是否大于可用额度
	 * @param userName 用户名
	 * @param 申请额度
	 * @return
	 */
	public boolean useAmountFlag(CreateAppBlueCollarReq req){
		if(req.getTranPrice().isEmpty()){
			return false;
		}
		if(Consts.APPFLOW_TYPE_3C.equals(req.getApplyType())){
			return true;
		}
		AvailbleCreditQuery creditQuery = new AvailbleCreditQuery();
		String useAmount = creditQuery.getAvailCredit(req.getUserName(), req.getUuid());
		if(useAmount != null){
			if(new BigDecimal(useAmount).compareTo(new BigDecimal(req.getTranPrice())) >= 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断用户使用的最低额度不能小于1000
	 * @param useAmount
	 * @return
	 */
	public boolean minUseAmount(String useAmount){
		if(useAmount != null){
			if(new BigDecimal(useAmount).compareTo(new BigDecimal(1000)) >= 0){
				return true;
			}
		}
		return false;
	}
	
	public String initApplicationPay(CreateAppBlueCollarReq tempReq) throws ProductNotFoundException,
			CreateAppPayException, HttpException, SQLException,
			GenerateFlowNoException, QueryFlowNoException,
			CustomerNotFoundException, ApproveUserNotFound, UniversityNotFound,
			ApproveBusinessException, IOException, HttpReturnException, CreateInputPayException ,UpdateCustomerTimeException, HttpUrlRemoteException, UpdateTempAppException, UpdateAppPayException, UpdateInputAppException, CancelCallarAppException, RuleValException, QueryUserCardNotFoundException, SiteNotFoundException{
		
		ApproveUserQuery approveQuery = new ApproveUserQuery();
//		ApproveUser approveUser = approveQuery.queryApprovByUserName(tempReq.getApproveUser());
		ApproveUser approveUser = approveQuery.queryApprovByNum(customer.getInveteCode());
		if(approveUser == null){
			System.out.println("业务员未找到");
			throw new ApproveUserNotFound();
		}
		
		CreateAppPayQuery appPayQuery = new CreateAppPayQuery();
		//修改白领毕业年限
//		if (Consts.APP_CUSTOMER_TYPE_KHL2.equals(tempReq.getCustType())&&!"".equals(tempReq.getEndSchoolTime())) {
//			CustCustomerEvent custCustomerEvent=new CustCustomerEvent();
//			
//			Map<String, Object> customer=getCustomerMap(tempReq);
//			custCustomerEvent.updateEndSchooLTime(customer);
//		}
		
		/* 创建申请PAY */
		Map<String, Object> appPayMap = getAppPayMap(tempReq, proEntity,appPayQuery,approveUser);
		ApplyPayEvent applyPayEvent = new ApplyPayEvent();
		applyPayEvent.createAppPay(appPayMap);
		
		/* 创建inputApp */
		String appPayNo = appPayMap.get("app_application_no").toString();
		Map<String, Object> inputPayMap = getInputApp(tempReq, proEntity, appPayNo,approveUser.getUserName());
		InputAppPayEvent inputAppPayEvent = new InputAppPayEvent();
		inputAppPayEvent.createInputAppPay(inputPayMap);
		
		/* 模拟操作  设置申请件状态到设置资料 */
		applyPayEvent.updateAppPayToSetInfoStatus(appPayNo);
		/* 修改inputApp creditName */
		updateCreditName(appPayMap.get("app_month_pay").toString(), appPayMap.get("app_install_ment").toString(), appPayMap.get("app_application_no").toString());
		
		/**  */
		// 根据appNO修改d_application_pay表
		ApplicationPay applicationPay = getApp(customer,appPayNo);
		// 根据appNO修改d_input_pay表
		InputAppPay inputAppPay = getInputAppPay(customer,appPayNo);
		
		ProductQuery productQuery = new ProductQuery();
//		CreditRelationQuery creditRelationQuery = new CreditRelationQuery();
		ApplicationPayQuery applicationPayQuery = new ApplicationPayQuery();
		ApplicationPay tempApp = applicationPayQuery.queryApplicationPayById(appPayNo);

		LoanProduct loanProduct = productQuery.queryCreditProductById(StringUtils.valueOf(loanAmtCalResp.getLoanProduct()));
		CreateAppPayEvent createAppPayUpdate = new CreateAppPayEvent();
//		CreateLoanPlanReq createLoanPlanReq = (CreateLoanPlanReq) parmRequest;
		createAppPayUpdate.updateApplicationPay(applicationPay);
		createAppPayUpdate.updateInputAppPay(inputAppPay);
		
		/* 如果是走聚信立产品，则创建临时申请件 */
//		if(Consts.FINAL_NUMBER_1.equals(tempReq.getCreateFlag())){
			createTempApp(tempApp);
//		}
		
		CustcustomerQuery custcustomerQuery = new CustcustomerQuery();
		// 根据userName查询出客户个人基本信息
		CustCustomer custCustomer = custcustomerQuery.queryCustCustomer(tempReq.getUserName());
		
		// 执行进件规则
		saveRunRulesRecord(appPayNo, "");
		Map<Object, Object> map = new HashMap<Object, Object>();
		IntoRules intoRules = new IntoRules();

		RulesQuery rulesQuery = new RulesQuery();
		String recordId = rulesQuery.queryRechordId(appPayNo);
			
		try {

			if (Consts.APP_CUSTOMER_TYPE_KHL1.equals(custCustomer.getCustType())) {
				map = intoRules.exeApprRulesBlue(appPayNo, recordId, "", "");
			} else {
				map = intoRules.exeApprRulesForKHL2Blue(appPayNo, recordId, "","");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boolean flag = (Boolean) map.get("results");
		logger.info("规则日志开始");
		if (logger.isInfoEnabled())
		{
			logger.info("规则日志");
			logger.info(JSON.toJSONString(map));
		}
		
		
		if (flag) {
			// 命中规则 取消申请件
			ApplicationPayQuery applicationQuery = new ApplicationPayQuery();
			ApplicationPay appay = applicationQuery.queryApplicationPayById(appPayNo);
			appay.setStatus(ProcessConst.STATUS21);
			appay.setNode(ProcessConst.NODE12);
			appay.setAllNode(ProcessConst.ALL_NODE_GB);
			appay.setApprovalProcessEndDate(DateUtils.getDateStr(new Date()));
			appay.setApprovalProcessEndName("system");
			appay.setApprovalProcessEndnode("S");
			appay.setFaceRefause(String.valueOf(map.get("desc")));
			appay.setFaceResult("refuse");

			appay.setMerchAntEndTime(DateUtils.getDateStr(new Date()));

			createAppPayUpdate.updateApplicationPay(appay);

			ApproveRecordEvent approveRecordEvent = new ApproveRecordEvent();
			approveRecordEvent.updateRecord(recordId);
			// 这里抛出异常 命中规则
			throw new RuleValException();

		} else {
			
			// 现金类型冻结额度 根据金额还有用户名修改冻结金额
			if (Consts.APPFLOW_TYPE_CASH.equals(tempReq.getApplyType())) {
				updateUserInfoByUserName(
						new BigDecimal(tempApp.getApplyAmount()),
						tempReq.getUserName());
			}
			// 生成loan_loan(建账)
			createLoan(appPayNo, loanProduct, tempReq.getUuid(), tempReq);

		}
		
		return appPayNo;
	}
	
	private void hsdHandle(String appNo,String saleCode) throws ApproveUserNotFound, UpdateAppPayException{
		
		HyEmployeeQuery employeeQuery = new HyEmployeeQuery();
		HyEmployeeEntity employee = employeeQuery.queryEmployeeByCode(saleCode);
		if(employee != null){
			
			HashMap<String, Object> setMap = new HashMap<String, Object>();
			setMap.put("provice", employee.getProvince());
			setMap.put("app_city_code", employee.getCity());
			setMap.put("area", "000000");
			

			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("app_application_no", appNo);
			
			CreateAppPayEvent appEvent = new CreateAppPayEvent();
			appEvent.updateAppBlue(setMap, whereMap);
			
		}else{
			logger.info("\n[创建订单 嗨商贷-SYDK] 员工号：" + saleCode + " 业务员未找到");
			System.out.println("[嗨商贷]业务员未找到");
			throw new ApproveUserNotFound();
		}
		
	}

	/**
	 * 创建临时申请
	 * @param applicationPay
	 * @throws GenerateFlowNoException
	 * @throws QueryFlowNoException
	 * @throws UpdateTempAppException 
	 */
	private void createTempApp(ApplicationPay applicationPay) throws GenerateFlowNoException, QueryFlowNoException, UpdateTempAppException{
		
		PayFlowNoQuery appNoQuery = new PayFlowNoQuery();
		String tempAppNo = appNoQuery.queryAppFlowNoBlue(Consts.APPFLOW_TYPE_CASH, Consts.TEMP_APP_SEQ);
		
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("TEMP_APPLICATION_NO", tempAppNo);
		
		inputMap.put("USERNAME", applicationPay.getUsername());
		inputMap.put("APPLY_PRICE", applicationPay.getApplyAmount());
		inputMap.put("LOAN_PRODUCT", applicationPay.getCreditProductId());
		inputMap.put("CREATE_DATE", DateUtils.getDateStr(new Date()));
		inputMap.put("CREATEAPP_FLAG", Consts.FINAL_NUMBER_0);
		inputMap.put("APP_APPLICATION_NO", applicationPay.getApplicationNo());
		inputMap.put("CREATE_FROM", "DASHBOARD");
		
		TempAppInfoEvent tempAppEvent = new TempAppInfoEvent();
		
		tempAppEvent.createTempApp(inputMap);
	}
	
	
//	public Map<String, Object> getCustomerMap(CreateAppBlueCollarReq tempReq){
//		
//		Map<String,Object>  customer= new HashMap<String, Object>();
//		//白领毕业时间
////		customer.put("end_school_time", tempReq.getEndSchoolTime());
//		customer.put("username", tempReq.getUserName());
//		
//		return customer;
//	}
	
	public Map<String, Object> getAppPayMap(CreateAppBlueCollarReq tempReq,
			MerProductEntity proEntity, CreateAppPayQuery appPayQuery,ApproveUser approveUser)
			throws SQLException, GenerateFlowNoException, QueryFlowNoException,
			CustomerNotFoundException, UniversityNotFound,
			ApproveBusinessException, HttpException, IOException,
			HttpReturnException, HttpUrlRemoteException, SiteNotFoundException {

		CreateAppPayAssem appPayAssem = new CreateAppPayAssem(appPayQuery);
		
		SaleSiteQuery siteQuery = new SaleSiteQuery();
		SaleSiteEntity siteEntity = siteQuery.querySaleSiteById(tempReq.getSiteId());
		if(siteEntity == null){
			throw new SiteNotFoundException();
		}

		/* 初始化申请件状态信息  未认领件*/
		appPayAssem.initPayNodeAndStatus1();

		/* 初始化流水号和产品信息 */
		appPayAssem.initFlowNoAndProductBlue2(tempReq,proEntity);

		/* 初始化客户信息 */
		appPayAssem.initCustomerBlue3(tempReq.getUserName(),approveUser);

		/* 初始化地域信息  申请件地址(初始默认业务员所在地址)*/
		appPayAssem.initCurrentCityBlue4(siteEntity.getProvince(), siteEntity.getCity(),siteEntity.getArea());

		/* 初始化办单人员信息 */
		appPayAssem.initSaleServerBlue5(approveUser);
		
		/* 初始化商户售点 */
		appPayAssem.initSupplierApproveBlue(tempReq,approveUser,siteEntity);

		LoanAmtCalReq loanAmtCalReq = new LoanAmtCalReq();
		loanAmtCalReq.setPayType(tempReq.getApplyType());
		loanAmtCalReq.setTranPrice(tempReq.getTranPrice());
		loanAmtCalReq.setFirstRate(tempReq.getFirstRate());
		loanAmtCalReq.setLoanProduct(tempReq.getLoanProduct());
		
//		loanAmtCalReq.setIndustryCode(proEntity.getIndustryCode());
//		loanAmtCalReq.setSupplierId(tempReq.getSupplierId());
		loanAmtCalReq.setUuid(tempReq.getUuid());
		
//		loanAmtCalReq.setCustType(tempReq.getCustType());
//		loanAmtCalReq.setCityCode(approveUser.getCityCode());
//		loanAmtCalReq.setPayType(tempReq.getApplyType());
//		loanAmtCalReq.setProductId(tempReq.getProId());
//		loanAmtCalReq.setMerProId(tempReq.getMerProId());
		/* 初始化金额信息 */
		loanAmtCalResp = appPayAssem.initAppPayAmt6(loanAmtCalReq);

		return appPayAssem.getAppPayMap();
	}
	
	public Map<String, Object> getInputApp(CreateAppBlueCollarReq tempReq,
			MerProductEntity proEntity,String appPayNo,String approveUserName) throws SQLException, QueryUserCardNotFoundException{
		
		InputAppPayAssem inputAppPayAssem = new InputAppPayAssem();
		return inputAppPayAssem.getInputAppMapBlue(tempReq, proEntity, appPayNo,approveUserName);
	}
	
	public void checkApp(CreateAppBlueCollarReq req) throws AppExitErrorException, CustomerNotFoundException, MerProductNotFoundException, CustTypeRoleException, OneMonthApplyException, TaskNoNotFoundException, TaskNoIndustryFailException, IsNotLanUserException{
		
		
		ApplicationPayQuery query=new ApplicationPayQuery();
		CustcustomerQuery custcustomerQuery=new CustcustomerQuery();
		
		// 一个月之内是否有订单被拒绝
//		List<ApplicationPay> jjPays = query.queryAppByBeforeMonth(req.getUserName());
//		if(jjPays != null && jjPays.size() > 0){
//			throw new OneMonthApplyException();
//		}
		
		//是否重复申请
		List<ApplicationPay> pays = new ArrayList<ApplicationPay>();
		String applyType = Consts.APPFLOW_TYPE_CASH;
		if(!Consts.APPFLOW_TYPE_CASH.equalsIgnoreCase(req.getApplyType())){
			applyType = Consts.APPFLOW_TYPE_3C;
			pays = query.querySByName3C(req.getUserName());
		}else{
			pays = query.querySByName(req.getUserName(), ProcessConst.STATUS01,applyType);
		}
		
		//是否存在该用户
		customer = custcustomerQuery.queryCustCustomer(req.getUserName());
		if(customer == null){
			throw new CustomerNotFoundException();
		}
		/* 获取商品信息 */
		MerProductQuery merProQuery = new MerProductQuery();
		proEntity = merProQuery.queryMerProByProId(req.getMerProId());
		if(proEntity == null){
			throw new MerProductNotFoundException();
		}
		/* 行业为空 */
		if(proEntity.getIndustryCode() == null || proEntity.getIndustryCode().equals("")){
			proEntity.setIndustryCode(req.getIndustryCode());
		}
		/* 该客户类型是否有申请指定行业的权限 */
		IndustryTypeQuery industryQuery = new IndustryTypeQuery();
//		boolean custRole = industryQuery.queryIndustryByCust(req.getCustType(), proEntity.getIndustryCode());
		IndustryTypeEntity custIndustry = industryQuery.queryIndustryByIndustry(req.getCustType(), proEntity.getIndustryCode());
		if(custIndustry == null){
			throw new CustTypeRoleException();
		}
		if(pays.size()>0){
			throw new AppExitErrorException();
		}
		
		/* 是否为蓝领用户、是否填写邀请码和门店号 */
		String flag = customer.getLanUserFlag(); // 1：蓝领用户
		if(StringUtils.isEmpty(flag) || !Consts.FINAL_NUMBER_1.equals(flag) || StringUtils.isEmpty(customer.getInveteCode()) ||StringUtils.isEmpty(customer.getStoreCode())){
			throw new IsNotLanUserException();
		}
		
		
	//蓝领数码无专案号	
//		// 专案号验证
//		if(!RegexValidate.isNull(req.getCaseNum())){
//			
//			TaskParamQuery taskQuery = new TaskParamQuery();
//			TaskParamEntity taskEntity = taskQuery.queryTaskParamByNo(req.getCaseNum());
//			if(taskEntity == null){
//				throw new TaskNoNotFoundException();
//			}else{
//				// 专案号和所申请行业不匹配
//				if(!taskEntity.getIndustryCode().equals(custIndustry.getCreditCode())){
//					throw new TaskNoIndustryFailException();
//				}
//			}
//		}
		
	}
	
	
	
	
	public ApplicationPay getApp(CustCustomer custCustomer, String appPayNo) {
		ApplicationPay applicationPay = new ApplicationPay();
		applicationPay.setApplicationNo(appPayNo);

		if ("KHL1".equals(custCustomer.getCustType())) {

			applicationPay.setCustomerType("KHL1");

			applicationPay.setAddress(custCustomer.getSchool());

			applicationPay.setGradeWork(custCustomer.getUserclass());// 年级

		} else {

			applicationPay.setAddress(custCustomer.getUnitName());

			applicationPay.setGradeWork(custCustomer.getWorkExperience());// 工作年限

		}
		applicationPay.setUserMobile(custCustomer.getMobileNo());// 用户手机号码

		applicationPay.setUserRealName(custCustomer.getRealName());// 用户真实姓名

//		applicationPay.setApplicationNode("NO01");// 插入电话客服岗节点

//		applicationPay.setApplicationStatus("ZT01");// 插入申请状态

		applicationPay.setUsername(custCustomer.getUserName());// 插入用户名


		applicationPay.setUserIdentityNo(custCustomer.getIdentityNo());// 插入用户身份证号码
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		applicationPay.setMerchAntEndTime(sdf.format(new Date()));// 商户提交时间

//		applicationPay.setAppStage("APP1");// 设置申请状态

		applicationPay.setPayMentFlag(false);// 设置还款标识

//		applicationPay.setNode("NODE06");// 信用审核

//		applicationPay.setStatus("STATUS07");// 等待初审
		

//		applicationPay.setAllNode("SHNODE");// 审核中
		
		//mod by mary 流程节点修改为设置产品资料 start
//				applicationPay.setNode("NODE03");
//				applicationPay.setStatus("STATUS04");
//				applicationPay.setAllNode("ZLNODE");
		//end

		applicationPay.setReceiveFlag(false);

		applicationPay.setReceiveCodeFlag(false);

		applicationPay.setCloseFlag(false);

		applicationPay.setCheckFlag(false);

		applicationPay.setContractFlag(false);

		applicationPay.setDeliveryFlag(false);

		applicationPay.setSubmitFlag(false);

		applicationPay.setUploadFlag(false);

		applicationPay.setCompFlag(false);

		// 是否开放给5i5dai投资
		CustPayAccountQuery custPayAccountQuery = new CustPayAccountQuery();
		CustPayAccount custPayAccount = custPayAccountQuery
				.getPayAccount(applicationPay);
		if (custPayAccount != null) {
			applicationPay.setInvestMode(custPayAccount.getInvestFlag());
		}
		return applicationPay;
	}
	
	public InputAppPay getInputAppPay(CustCustomer custCustomer, String appPayNo) {
		InputAppPay inputAppPay = new InputAppPay();

		inputAppPay.setApplicationNo(appPayNo);

		inputAppPay.setRealName(custCustomer.getRealName());// 姓名

		inputAppPay.setIdentityNo(custCustomer.getIdentityNo());// 身份证

		inputAppPay.setNowFixedTel(custCustomer.getFixedTel());// 固定电话

		inputAppPay.setNowFixedTelArea(custCustomer.getFixedTelArea());// 固定电话区号

		inputAppPay.setValid(custCustomer.getValid());// 身份证有效期

		inputAppPay.setEmailAddress(custCustomer.getEmailAdress());// 电子邮箱

		inputAppPay.setMobileNo(custCustomer.getMobileNo());// 手机号码

		/***** 直系亲属信息 ******/

		inputAppPay.setFatherName(custCustomer.getImmediateName());// 亲属姓名

		inputAppPay.setFamilyRelation(custCustomer.getImmediateRelation());// 亲属关系

		inputAppPay.setFatherMobile(custCustomer.getImmediateMobile());// 亲属手机

		inputAppPay.setWorkUnits(custCustomer.getImmediateJob());// 亲属单位

		inputAppPay.setWorkUnitsAddress(custCustomer.getImmediateAdress());// 亲属单位地址

		/***** 紧急联系人信息 ******/

		inputAppPay.setRelaName(custCustomer.getEmergencyName());// 紧急联系人姓名

		inputAppPay.setRelation(custCustomer.getEmergencyRelation());// 紧急联系人关系

		inputAppPay.setRelaMobile(custCustomer.getEmergencyMobile());// 紧急联系人手机

		inputAppPay.setRelaWorkUnits(custCustomer.getEmergencyJob());// 紧急联系人单位

		inputAppPay.setRelaWorkUnitsAddress(custCustomer.getEmergencyAdress());// 紧急联系人单位地址

		inputAppPay.setFullTimeFlag(custCustomer.getFullTimeFlag());// 是否全日制

		inputAppPay.setUserName(custCustomer.getUserName());// 用户名userGreade



		inputAppPay.setCreateUser(custCustomer.getUserName());// 创建人

		/** 家庭地址 */
		inputAppPay.setFamProv(custCustomer.getFimilyAreaProvince());// 家庭--省

		inputAppPay.setFamCity(custCustomer.getFimilyAreaCity());// 家庭--市

		inputAppPay.setFamArea(custCustomer.getFimilyAreaArea());// 家庭--区

		inputAppPay.setDormAddress(custCustomer.getFimilyAreaRoad());// 家庭地址

		/** 现居地址 */

		inputAppPay.setNowProv(custCustomer.getOtherAdressProvince());// 现居--省

		inputAppPay.setNowCity(custCustomer.getOtherAdressCity());// 现居--市

		inputAppPay.setNowArea(custCustomer.getOtherAdressArea());// 现居--区

		inputAppPay.setNowAddress(custCustomer.getOtherAccommodationAddress());// 现居地址

		if ("KHL1".equals(custCustomer.getCustType())) {

			inputAppPay.setSchool(custCustomer.getSchool());// 学校

			inputAppPay.setStudentId(custCustomer.getStudentId());// 学号

			inputAppPay.setEducational(custCustomer.getEducational());// 在读学历

			if (custCustomer.getAdmissionTime() == null || custCustomer.getAdmissionTime().equals("")) {

			} else {

				inputAppPay.setAdmissionYear(custCustomer.getAdmissionTime()
						.substring(0, 4));// 入学时间-年

				inputAppPay.setAdmissionDay(custCustomer.getAdmissionTime()
						.substring(5, 7));// 入学时间-月

			}

			inputAppPay.setFaculties(custCustomer.getFaculties());// 所在院系

			inputAppPay.setSchoolProv(custCustomer.getSchoolAreaProvince());// 院校--省

			inputAppPay.setSchoolCity(custCustomer.getSchoolAreaCity());// 院校--市

			inputAppPay.setSchoolArea(custCustomer.getSchoolAreaArea());// 院校--区

			inputAppPay.setSchoolAddress(custCustomer.getSchoolAreaRoad());// 院校地址

			inputAppPay.setSpecialty(custCustomer.getSpecialty());// 专业

			inputAppPay.setUserGreade(custCustomer.getUserclass());// 用户年级

		} else {

			/* 2016-01-11蓝领新增 start */
			
			inputAppPay.setLanUserFlag(custCustomer.getLanUserFlag());
			inputAppPay.setInveteCode(custCustomer.getInveteCode());
			inputAppPay.setStoreCode(custCustomer.getStoreCode());
			
			/* 2016-01-11蓝领新增 end */
			
			inputAppPay.setEducation(custCustomer.getNowEduCation());// 白领学历

			if (custCustomer.getEndSchoolTime() == null || custCustomer.getEndSchoolTime().equals("")) {

			} else {

				inputAppPay.setGraduationYear(custCustomer.getEndSchoolTime()
						.substring(0, 4));// 白领毕业时间--年

				inputAppPay.setGraduationMonth(custCustomer.getEndSchoolTime()
						.substring(5, 7));// 白领毕业时间--月

			}

			inputAppPay.setWorkExperience(custCustomer.getWorkExperience());// 白领工作年限

			inputAppPay.setSectors(custCustomer.getSectors());// 白领行业类别

			inputAppPay.setUnitName(custCustomer.getUnitName());// 白领单位名称

			inputAppPay.setSchool(custCustomer.getGraduatedSchool());// 白领毕业学校

			inputAppPay.setUnitTelArea(custCustomer.getUnitTelArea());// 白领单位固定电话区号

			inputAppPay.setUnitTel(custCustomer.getUnitTel());// 白领单位固定电话

			inputAppPay.setMonthlyIncome(custCustomer.getMonthlyIncome());// 白领月收入

			inputAppPay.setOfficeSector(custCustomer.getJobNature());// 白领任职部门

			inputAppPay.setJobDuties(custCustomer.getJobDuties());// 白领职务

			inputAppPay.setMaritalStatus(custCustomer.getMaritalStatus());// 婚姻状况

			if ("Q001".equals(custCustomer.getMaritalStatus())) {

				inputAppPay.setSpouseRealName(custCustomer.getSpouseName());// 配偶姓名
				
					if (!StringUtils.isEmpty(customer.getSpouseMobile())) {
						inputAppPay.setSpouseMobileNo(Long
								.parseLong(custCustomer.getSpouseMobile()));// 配偶手机号码
					}

				
			}

			inputAppPay.setUnitIndustry(custCustomer.getUnitProperties());// 单位性质

			inputAppPay.setHousingCondition(custCustomer.getHousingConditions());// 住房状况

			inputAppPay.setEmployeeNumber(custCustomer.getJobNumber());// 工号

			inputAppPay.setCensusProvince(custCustomer.getPermanentAddressProvince());// 户籍地址--省

			inputAppPay.setCensusCity(custCustomer.getPermanentAddressCity());// 户籍地址--市

			inputAppPay.setCensusArea(custCustomer.getPermanentAddressArea());// 户籍地址--区

			inputAppPay.setCensusAddress(custCustomer.getPermanentAddressRaod());// 户籍地址--详细地址

			inputAppPay.setMonthlyConsumption(custCustomer.getMonthlyConsumption());// 月消费

			inputAppPay.setMonthRent(custCustomer.getMonthRent());// 房租月供

			inputAppPay.setLoanCount(custCustomer.getLoanCount());// 贷款数量

			inputAppPay.setLoanTotalAmount(custCustomer.getLoanTotalAmount());// 贷款总额

			inputAppPay.setLoanMonthRent(custCustomer.getLoanMonthRent());// 贷款月供

			inputAppPay.setCreditCardCount(custCustomer.getCreditCardCount());// 信用卡数量

			inputAppPay.setCreditTotalAmount(custCustomer
					.getCreditTotalAmount());// 信用卡总额度

			inputAppPay.setCreditHigthAmount(custCustomer
					.getCreditHigthAmount());// 信用卡最高额度

			if (custCustomer.getEntryTime() == null  || custCustomer.getEntryTime().equals("")) {

			} else {

				inputAppPay.setEntryTimeYear(custCustomer.getEntryTime()
						.substring(0, 4));// 白领入职时间--年

				inputAppPay.setEntryTimeMonth(custCustomer.getEntryTime()
						.substring(5, 7));// 白领入职时间--月

			}

			inputAppPay.setWorkUnitProv(custCustomer.getWorkAreaProvince());// 单位---省

			inputAppPay.setWorkUnitCity(custCustomer.getWorkAreaCity());// 单位---市

			inputAppPay.setWorkUnitArea(custCustomer.getWorkAreaArea());// 单位---市

			inputAppPay.setUnitAddress(custCustomer.getWorkAreaRoad());// 单位地址

		}
		// 根据身份证信息计算性别
		inputAppPay.setGender(StringUtils.getGenderByIdCard(custCustomer
				.getIdentityNo()));// 性别

		/* 出生日期 */
		inputAppPay.setBirthday(inputAppPay.getIdentityNo().substring(6, 10)
				+ "-" + inputAppPay.getIdentityNo().substring(10, 12) + "-"
				+ inputAppPay.getIdentityNo().substring(12, 14));

		inputAppPay.setLoanUse(custCustomer.getLoanUse());
		return inputAppPay;
	}
	
	public void updateUserInfoByUserName(BigDecimal applyAmt, String userName) {
		CustLimitBlueQuery custLimitQuery = new CustLimitBlueQuery();
		CustLimitEvent custLimitEvent = new CustLimitEvent();
		// 根据用户名查询可用金额
		CustLimit custLimit = custLimitQuery.getCustLimitByUserName(userName);

		// 更改冻结金额 原来冻结金额+现在调额额度
		BigDecimal blockAmt = custLimit.getBlockAmt().add(applyAmt);

		// 更新冻结金额
		custLimitEvent.updateBlockAmtByUserName(blockAmt, userName);
	}
	
	public void createLoan(String applicationNo, LoanProduct loanProduct,String uuid,CreateAppBlueCollarReq tempReq) throws HttpException, IOException, HttpReturnException, HttpUrlRemoteException {
		ApplicationPayQuery applicationQuery = new ApplicationPayQuery();
		
		ProductQuery productQuery = new ProductQuery();
//		ProductInfoQuery productInfoQuery = new ProductInfoQuery();
		MerProductQuery merProQuery = new MerProductQuery();

		ApplicationPay applicationPay = applicationQuery.queryApplicationPayById(applicationNo);

		LoanLoan loan = loanloanQuery.queryLoanLoanByAppId(applicationNo);

		if (loan == null) {
			loan = new LoanLoan();
		} else {
//			LoanLoanRecordEvent loanLoanRecordEvent = new LoanLoanRecordEvent();
//			// 取消预授权
//			loanLoanRecordEvent.createLoanRecord(loan);

		}

		loan.setProductId(applicationPay.getCreditProductId());

		LoanProduct creditProduct = productQuery.queryCreditProductById(loan.getProductId());
		
//		ProductInfo productInfo = productInfoQuery
//				.queryProductInfoById(applicationPay.getProductInfo() + "");
		MerProductEntity proEntity = merProQuery.queryMerProByProId(applicationPay.getMerProId());

		if (creditProduct == null) {

			// logger.error("申请对应的信贷产品不存在");
			return;
		}

		if (proEntity == null) {

			// logger.error("申请对应的实物产品不存在");
			return;
		}

		loan.setApplicationId(applicationPay.getApplicationNo());
		loan.setCustRate(creditProduct.getCustRate());
		// 是否含有首付款
	/*	if (isFirstPay(applicationPay)) {
			loan.setAmount(new BigDecimal(applicationPay.getTranPrice())
					.subtract(new BigDecimal(applicationPay
							.getFirstPayPrincipal()))
					+ "");
		} else {
			loan.setAmount(applicationPay.getTranPrice());
		}*/
		
		LoanAmtCalReq loanAmtCalReq = new LoanAmtCalReq();
//		loanAmtCalReq.setCustType(applicationPay.getCustomerType());
//		loanAmtCalReq.setCityCode(applicationPay.getCityCode());
//		loanAmtCalReq.setPayType(applicationPay.getPayType());
		loanAmtCalReq.setTranPrice(applicationPay.getApplyAmount());
		loanAmtCalReq.setFirstRate(applicationPay.getFirstPayRate());
		loanAmtCalReq.setLoanProduct(applicationPay.getCreditProductId());
//		loanAmtCalReq.setProductId(applicationPay.getProductInfo());
//		loanAmtCalReq.setMerProId(tempReq.getMerProId());
		
		loanAmtCalReq.setUuid(uuid);
		
//		String loanAmount = getLoanAmount(loanAmtCalReq);
		LoanAmtCalResp amtCalResp = getLoanAmount(loanAmtCalReq);
		
		loan.setAmount(loanAmtCalResp.getLoanPrincipal().toString());
		
		if(loanAmtCalResp.getLoanPrincipal().compareTo(amtCalResp.getLoanPrincipal()) != 0){
			/* 创建申请件 和 创建loan_loan 两次计算金额有差异，记录该申请件 */
			AppExceptionEvent exceptionEvent = new AppExceptionEvent();
			AppExceptionEntity exceptionEntity = new AppExceptionEntity();
			exceptionEntity.setUserName(tempReq.getUserName());
			exceptionEntity.setAppNo(applicationNo);
			exceptionEntity.setLoanProductApp(tempReq.getLoanProduct());
			exceptionEntity.setLoanProductLoan(loanAmtCalReq.getLoanProduct());
			exceptionEntity.setLoanAmountApp(loanAmtCalResp.getLoanPrincipal());
			exceptionEntity.setLoanAmountLoan(amtCalResp.getLoanPrincipal());
			exceptionEvent.recordExceptionApp(exceptionEntity);
		}
		

		loan.setInterestRate(creditProduct.getCreditRate());
//		DitdicQuery ditdicQuery = new DitdicQuery();
//		DitDic ditdic = ditdicQuery.getDicNameByDicCode(creditProduct
//				.getCreditPayTime());
//		Integer months = Integer.parseInt(ditdic.getDicName());
		Integer months = creditProduct.getPeriod();

		LoanPeriod loanPeriod = LoanPeriod.mapLoanPeriod(months);

		if (loanPeriod == null) {

			// logger.error("信贷产品对应的期数不在枚举类中");

			return;
		}

		loan.setMonths(loanPeriod);

		loan.setPurpose("PERC");

		loan.setTitle(proEntity.getProName());

		loan.setDesc(proEntity.getProTitle());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		loan.setCreateTime(sdf.format(new Date()));
		
		AcctAccountQuery acctAccountQuery = new AcctAccountQuery();
		AcctAccount acctAccount = acctAccountQuery
				.queryCustCustomer(applicationPay.getUsername());
		createLoan(applicationPay.getUsername(), acctAccount.getId(), loan,
				null);

	}
	
	/**
	 * 获取用户的贷款金额
	 * @param loanAmt
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 * @throws HttpReturnException
	 * @throws HttpUrlRemoteException 
	 */
	public LoanAmtCalResp getLoanAmount(LoanAmtCalReq loanAmt) throws HttpException, IOException, HttpReturnException, HttpUrlRemoteException{
		
		CreateAppPayQuery appPayQuery = new CreateAppPayQuery();
		
		LoanAmtCalResp amtCalResp = appPayQuery.remoteHttpAmt(loanAmt);
		
//		String loanAmount = amtCalResp.getLoanPrincipal().toString();
		
		return amtCalResp;
		
	}
	
	public String createLoan(String username, String accountId, LoanLoan loan,
			String uploadedFile) {

		try {
			String filePath = "";
			if (uploadedFile != null) {
				// 該地方還有用，暫時注釋 20140722
				// String fileName = username + "_"
				// + userQuery.getLoanHeadPhotoFlowNo();
				// filePath = fileService.uploadLoanHeaderPhoto(uploadedFile,
				// username, fileName);// 上传结果头像
			}

			loan.setUsername(username);
			loan.setImgPath(filePath);
			loan.setAccountId(accountId);
			loan.setStatus("LPUB");
			loan.setNoticeProcessFlag(true);
			loan.setCompletedInvestAmt("0.00");
			loan.setTotalInvertsSum("0");
			loan.setPercent("0.00");

			CreateLoanLoanUpdate createLoanLoanUpdate = new CreateLoanLoanUpdate();
			createLoanLoanUpdate.createLoan(loan);
			
			LoanLoan queryLoan = loanloanQuery.queryLoanLoanByAppId(
					loan.getApplicationId());
			LoanLoanRecordEvent loanLoanRecordEvent = new LoanLoanRecordEvent();
			// 取消预授权
			loanLoanRecordEvent.createLoanRecord(queryLoan);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

//		try {
//			// add by zdh at 2012/10/30
//			List<String> mailLst = new ArrayList<String>();
//			// mailLst.add(ResourceUtils.getString("marster.mail1"));
//			// mailLst.add(ResourceUtils.getString("marster.mail2"));
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			for (String mailAddr : mailLst) {
//				if (!StringUtils.isNullOrEmpty(mailAddr)) {
//					NoticeSendflow noticeMailFlow = new NoticeSendflow();
//					noticeMailFlow.setUsername("MARSTER");
//					noticeMailFlow.setTitle("发布借款通知");
//					noticeMailFlow.setReceiveEmail(mailAddr);
//					noticeMailFlow.setNtype("MAIL");
//					noticeMailFlow.setSendFlag("O");
//					noticeMailFlow.setOperator("亨元金融");
//					noticeMailFlow.setReceiveTelphone("");
//					noticeMailFlow.setSendTime(sdf.format(new Date()));
//					noticeMailFlow.setContent("借款人（"
//							+ username
//							+ "）于"
//							+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
//									.format(new Date()) + "发布了【"
//							+ loan.getTitle() + "】借款，借款金额（" + loan.getAmount()
//							+ "）");
//					//发送邮件给管理员
////					 sendMailToMaster(noticeMailFlow);
//				}
//			}
//		} catch (Exception e) {
//		}

		return null;
	}
	
	/**
	 * 保存规则执行记录
	 * 
	 * @param applicationNo
	 */
	private void saveRunRulesRecord(String applicationNo, String desc) {
		ApprovalRecord record = new ApprovalRecord();
		record.setApplicationNo(applicationNo);
		record.setApprovalType("OPE2");
		record.setApprovalAction("FA02");
		record.setApprovalDesc(desc);
		record.setApprovalResult("RES1");
		record.setCreateUser("system");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setCreateDate(sdf.format(new Date()));
		ApproveRecordEvent approveRecordEvent = new ApproveRecordEvent();
		approveRecordEvent.createAppRecord(record);
	}

	/**
	 * 查询申请件
	 * @param appNo
	 * @return
	 */
	public ApplicationEntity queryAppPayByNo(String appNo){
		
		ApplicationQuery query = new ApplicationQuery();
		
		return query.queryAppPayByAppNO(appNo);
	}
	
	public void updateCreditName(String monthPay,String installment,String appNo) {
		
		InputAppPayEvent payEvent = new InputAppPayEvent();
		InputAppPay inputApp = new InputAppPay();
		inputApp.setCreditName(monthPay + "元×" + installment + "期");
		inputApp.setApplicationNo(appNo);
		payEvent.updateInputAppCreditName(inputApp);
	}
	
}
