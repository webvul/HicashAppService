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

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ProcessConst;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.assem.CreateAppPayAssem;
import com.hengyuan.hicash.domain.assem.InputAppPayAssem;
import com.hengyuan.hicash.domain.event.apply.ApplicationPicEvent;
import com.hengyuan.hicash.domain.event.apply.ApplyPayEvent;
import com.hengyuan.hicash.domain.event.apply.ApproveRecordEvent;
import com.hengyuan.hicash.domain.event.apply.CreateAppPayEvent;
import com.hengyuan.hicash.domain.event.apply.CreateLoanLoanUpdate;
import com.hengyuan.hicash.domain.event.apply.CustLimitEvent;
import com.hengyuan.hicash.domain.event.apply.InputAppPayEvent;
import com.hengyuan.hicash.domain.event.apply.LoanLoanRecordEvent;
import com.hengyuan.hicash.domain.event.apply.TempAppInfoEvent;
import com.hengyuan.hicash.domain.event.user.CallPhoneDataNumUpEvent;
import com.hengyuan.hicash.domain.event.user.CollectCardEvent;
import com.hengyuan.hicash.domain.query.amount.AvailbleCreditQuery;
import com.hengyuan.hicash.domain.query.app.ApplicationPayQuery;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.app.ProductQuery;
import com.hengyuan.hicash.domain.query.app.TempApplyQuery;
import com.hengyuan.hicash.domain.query.mer.MerProductQuery;
import com.hengyuan.hicash.domain.query.mer.SaleSiteQuery;
import com.hengyuan.hicash.domain.query.mktApp.AcctAccountQuery;
import com.hengyuan.hicash.domain.query.mktApp.CreateAppPayQuery;
import com.hengyuan.hicash.domain.query.mktApp.CustPayAccountQuery;
import com.hengyuan.hicash.domain.query.mktApp.IndustryTypeQuery;
import com.hengyuan.hicash.domain.query.mktApp.LoanloanQuery;
import com.hengyuan.hicash.domain.query.param.TempApplyPicQuery;
import com.hengyuan.hicash.domain.query.rule.RulesQuery;
import com.hengyuan.hicash.domain.query.user.CallPhoneNumMsgQuery;
import com.hengyuan.hicash.domain.query.user.CollectAccountQuery;
import com.hengyuan.hicash.domain.query.user.CustLimitQuery;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.rule.IntoRules;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.notic.ExternalService;
import com.hengyuan.hicash.domain.service.notic.NoticeSendService;
import com.hengyuan.hicash.entity.app.AcctAccount;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.app.CallPhoneDataParam;
import com.hengyuan.hicash.entity.app.CustPayAccount;
import com.hengyuan.hicash.entity.app.InputAppPay;
import com.hengyuan.hicash.entity.app.LoanProduct;
import com.hengyuan.hicash.entity.app.PicParam;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.entity.mer.MerProductEntity;
import com.hengyuan.hicash.entity.mer.SaleSiteEntity;
import com.hengyuan.hicash.entity.mktApp.ApprovalRecord;
import com.hengyuan.hicash.entity.mktApp.LoanLoan;
import com.hengyuan.hicash.entity.param.NoticeSendParam;
import com.hengyuan.hicash.entity.param.TempApplyPicEntity;
import com.hengyuan.hicash.entity.user.CallPhoneNumEntity;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.CustLimitEntity;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.exception.AppExitErrorException;
import com.hengyuan.hicash.exception.ApproveBusinessException;
import com.hengyuan.hicash.exception.CallPhoneDataNumException;
import com.hengyuan.hicash.exception.CallPhoneDataNumLockException;
import com.hengyuan.hicash.exception.CancelCallarAppException;
import com.hengyuan.hicash.exception.CreateAppPayException;
import com.hengyuan.hicash.exception.CreateInputPayException;
import com.hengyuan.hicash.exception.CustTypeRoleException;
import com.hengyuan.hicash.exception.CustomerNotFoundException;
import com.hengyuan.hicash.exception.GenerateFlowNoException;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.exception.MerProductNotFoundException;
import com.hengyuan.hicash.exception.ProductNotFoundException;
import com.hengyuan.hicash.exception.QueryFlowNoException;
import com.hengyuan.hicash.exception.QueryUserCardNotFoundException;
import com.hengyuan.hicash.exception.RepeatApplyMDException;
import com.hengyuan.hicash.exception.RuleValException;
import com.hengyuan.hicash.exception.SaveCardException;
import com.hengyuan.hicash.exception.SaveTongDunBlackException;
import com.hengyuan.hicash.exception.SendMobileMsgException;
import com.hengyuan.hicash.exception.SiteNotFoundException;
import com.hengyuan.hicash.exception.TempApplyNotFoundException;
import com.hengyuan.hicash.exception.TongDunValRulesException;
import com.hengyuan.hicash.exception.UniversityNotFound;
import com.hengyuan.hicash.exception.UpdateAppPayException;
import com.hengyuan.hicash.exception.UpdateApplicationPicException;
import com.hengyuan.hicash.exception.UpdateCardException;
import com.hengyuan.hicash.exception.UpdateCustomerTimeException;
import com.hengyuan.hicash.exception.UpdateInfoException;
import com.hengyuan.hicash.exception.UpdateInputAppException;
import com.hengyuan.hicash.exception.UpdateTempAppException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppPayReq;
import com.hengyuan.hicash.parameters.request.param.CardParam;
import com.hengyuan.hicash.parameters.request.param.LoanAmtCalReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.mktApp.CreateAppPayResp;
import com.hengyuan.hicash.parameters.response.user.LoanAmtCalResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.LoanPeriod;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * 创建申请件业务处理类
 * 
 * @author LiHua.Ren
 * @createDate 2015-08-18
 */
public class CreateCallPhoneDataService implements RespService {

	private static Logger logger = Logger
			.getLogger(CreateCallPhoneDataService.class);

	private LoanloanQuery loanloanQuery = new LoanloanQuery();

	LoanAmtCalResp loanAmtCalResp = null;

	String monthPay = "";// 月还款
	String installMent = "";// 还款期数;

	private CustCustomer customer = null;
	private MerProductEntity proEntity = null;
	private TempApplyEntity tempApplyEntity = null;

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		/* 创建申请件 */
		CreateAppPayResp createAppPayResp = new CreateAppPayResp();
		String resultCode = "";
		String applicationNo = "";
		Long start = System.currentTimeMillis();

		try {

			CreateAppPayReq req = (CreateAppPayReq) parmRequest;
			// 查询是否是申请的第几笔
			checkApp(req);
			/* 订单是否已经提交过 */
			if (Consts.FINAL_NUMBER_0.equals(tempApplyEntity.getCreateAppFlg())) {

				req = getCreateReq(req);
				
				if (useAmountFlag(req)) {

					if (minUseAmount(req.getTranPrice())) {

						ConnManager.beginTransaction();
						// 判断手机号码是否可用
						checkPhoneNum(req);
						/* 创建申请件 */
						applicationNo = initApplicationPay(req);
						queryPayMsg(applicationNo);
						createAppPayResp.setApplyAmount(req.getTranPrice());
						resultCode = ResultCodes.NORMAL;
						ConnManager.commit();
						// applicationEntity = queryAppPayByNo(applicationNo);
						
						createSuccAfter(req,applicationNo);
						
					} else {
						resultCode = ResultCodes.CREATEAPPPAY_MINAMOUNT;
					}

				} else {
					resultCode = ResultCodes.CREATEAPPPAY_USEAMOUNT_BZ;
				}

			} else {
				resultCode = ResultCodes.CREATEAPP_REPEATSUBMIT_FAIL;
			}

		} catch (SendMobileMsgException e) {
			// resultCode = ResultCodes.CREATEAPPPAY_SMSMOBILE_FAIL;
			resultCode = ResultCodes.NORMAL;
			logger.info("[嗨秒贷申请成功【单号：" + applicationNo + "】，短信发送失败。]");
		} catch (RepeatApplyMDException e) {
			resultCode = ResultCodes.CREATEAPP_REPEAMD_FAIL;
			e.printStackTrace();
		} catch (UpdateApplicationPicException e) {
			resultCode = ResultCodes.CREATEAPP_UPDATEPIC_EXCEPTION;
			e.printStackTrace();
			ConnManager.rollback();
		} catch (UpdateCardException e) {
			resultCode = ResultCodes.CREATEAPP_UPDATECARD_EXCEPTION;
			ConnManager.rollback();
		} catch (SaveCardException e) {
			resultCode = ResultCodes.CREATEAPP_UPDATECARD_EXCEPTION;
			ConnManager.rollback();
		} catch (UpdateTempAppException e) {
			resultCode = ResultCodes.CREATEAPP_UPDATETEMP_EXCEPTION;
			ConnManager.rollback();
		} catch (TempApplyNotFoundException e) {
			resultCode = ResultCodes.CREATEAPP_TEMPAPPLY_NOTFOUND;
		} catch (MerProductNotFoundException e) {
			resultCode = ResultCodes.CREATEAPP_MERPRO_NOTFOUND;
		} catch (CustTypeRoleException e) {
			resultCode = ResultCodes.CREATEAPP_CUSTROLE_FAIL;
		} catch (GenerateFlowNoException e) {
			ConnManager.rollback();
			resultCode = ResultCodes.CREATEAPP_GETAPPNO_FAILD;
		} catch (AppExitErrorException e) {
			// ConnManager.rollback();
			// 该用户已经存在申请件，不能重复申请
			resultCode = ResultCodes.CREATEAPP_REPEAT_FAILD;
		} catch (HttpException e) {
			ConnManager.rollback();
			/* 调用账务服务金额计算失败 */
			resultCode = ResultCodes.CREATEAPP_GETACCT_FAILD;
		} catch (SiteNotFoundException e) {
			ConnManager.rollback();
			/* 售点未查询到 */
			resultCode = ResultCodes.CREATEAPP_SITE_NOTFOUND;
		} catch (ProductNotFoundException e) {
			ConnManager.rollback();
			/* 申请实物产品未查询到 */
			resultCode = ResultCodes.CREATEAPP_PROINFO_NOTFOUND;
		} catch (CreateAppPayException e) {
			ConnManager.rollback();
			/* 创建申请件对象失败 */
			resultCode = ResultCodes.CREATEAPP_CREAT_FAILD;
		} catch (UpdateAppPayException e) {
			ConnManager.rollback();
			/* 更改申请件节点失败 */
			resultCode = ResultCodes.CREATEAPP_UPDATENODE_FAILD;
		} catch (CancelCallarAppException e) {
			// ConnManager.rollback();
			/* 白领自动取消申请件 */
			resultCode = ResultCodes.CREATEAPP_CALLRA_CANCEL;
		} catch (RuleValException e) {
			resultCode = ResultCodes.CREATEAPP_RULE_IN;
		} catch (SQLException e) {
			ConnManager.rollback();
			/* 处理失败 */
			resultCode = ResultCodes.CREATEAPP_SQL_ERROR;
		} catch (QueryFlowNoException e) {
			ConnManager.rollback();
			/* 当天流水序号查询失败 */
			resultCode = ResultCodes.CREATEAPP_QUERY_APPNO_FAILD;
		} catch (CustomerNotFoundException e) {
			// ConnManager.rollback();
			/* 申请客户信息未查询到 */
			resultCode = ResultCodes.CREATEAPP_APPLYINFO_NOT_FOUND;
		} catch (UniversityNotFound e) {
			ConnManager.rollback();
			/* 学校信息未找到 */
			resultCode = ResultCodes.CREATEAPP_SCHOOLINFO_NOT_FOUND;
		} catch (ApproveBusinessException e) {
			ConnManager.rollback();
			/* 销售人员和学校对应关系未查询到 */
			resultCode = ResultCodes.CREATEAPP_LINKINFO_NOT_FOUND;
		} catch (IOException e) {
			ConnManager.rollback();
			/* 接收账务服务返回处理异常 */
			resultCode = ResultCodes.CREATEAPP_IO_EXCEPTION;
		} catch (HttpReturnException e) {
			ConnManager.rollback();
			/* 账务服务连接异常 */
			resultCode = ResultCodes.CREATEAPP_ACCOUNT_ERROR;
		} catch (CreateInputPayException e) {
			ConnManager.rollback();
			/* 创建申请件信息对象异常 */
			resultCode = ResultCodes.CREATEAPP_INPUT_ERROR;
		} catch (UpdateCustomerTimeException e) {
			ConnManager.rollback();
			/* 创建申请件信息对象异常 */
			resultCode = ResultCodes.CREATEAPP_ENDSCHOOLTIME_ERROR;
		} catch (QueryUserCardNotFoundException e) {
			ConnManager.rollback();
			/* 创建申请件卡号信息异常 */
			resultCode = ResultCodes.CREATEAPP_CARD_NOTFOUND;
		} catch (CallPhoneDataNumException e) {
			ConnManager.rollback();
			/* 选择的手机号码不存在 */
			resultCode = ResultCodes.CALL_PHONE_DATA_CAN_NOT_FOUND;
		} catch (UpdateInfoException e) {
			ConnManager.rollback();
			/* 锁定手机号码异常 */
			resultCode = ResultCodes.CALL_PHONE_DATA_SAVE_PHONE_EXCEPTION;
		} catch (CallPhoneDataNumLockException e) {
			/* 手机号码被锁定 */
			ConnManager.rollback();
			resultCode = ResultCodes.CALL_PHONE_DATA_CAN_NOT_USE;
		} catch (SaveTongDunBlackException e) {
			ConnManager.rollback();
			resultCode =ResultCodes.TONGDUN_SAVEBLACE_EXCEPTION;
		} catch (TongDunValRulesException e) {
			ConnManager.rollback();
			resultCode =ResultCodes.TONGDUN_VALRULES_EXCEPTION;
		} 
		catch (Exception e) {
			ConnManager.rollback();
			resultCode = ResultCodes.CREATEAPPPAY_EXCEPTOIN;
			e.printStackTrace();
		} 
		finally {
			ConnManager.closeConn();
		}

		System.out
				.println("耗时[" + (System.currentTimeMillis() - start) + "]ms");
		createAppPayResp.setResultCode(resultCode);
		createAppPayResp.setMonthPayAmount(monthPay);
		createAppPayResp.setMonthInstallMent(installMent);
		createAppPayResp.setAppPayNo(applicationNo);

		return createAppPayResp;
	}

	/**
	 * 查询申请件的月还款额和还款期数
	 */
	public void queryPayMsg(String appNo) {
		ApplicationPayQuery payQuery = new ApplicationPayQuery();
		ApplicationPay applicationPay = payQuery.queryApplicationPayById(appNo);
		if (applicationPay != null) {
			monthPay = applicationPay.getMonthPay();
			installMent = applicationPay.getInstallMent();
		}

	}

	/**
	 * 判断用户的申请额度是否大于可用额度
	 * 
	 * @param userName
	 *            用户名
	 * @param 申请额度
	 * @return
	 */
	public boolean useAmountFlag(CreateAppPayReq req) {
		if (req.getTranPrice().isEmpty()) {
			return false;
		}
		if (Consts.APPFLOW_TYPE_3C.equals(req.getApplyType())) {
			return true;
		}
		AvailbleCreditQuery creditQuery = new AvailbleCreditQuery();
		String useAmount = creditQuery.getAvailCredit(req.getUserName(),
				req.getUuid());
		if (useAmount != null) {
			if (new BigDecimal(useAmount).compareTo(new BigDecimal(req
					.getTranPrice())) >= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断用户使用的最低额度不能小于1000
	 * 
	 * @param useAmount
	 * @return
	 */
	public boolean minUseAmount(String useAmount) {
		if (useAmount != null) {
			if (new BigDecimal(useAmount).compareTo(new BigDecimal(1000)) >= 0) {
				return true;
			}
		}
		return false;
	}

	public String initApplicationPay(CreateAppPayReq tempReq)
			throws ProductNotFoundException, CreateAppPayException,
			HttpException, SQLException, GenerateFlowNoException,
			QueryFlowNoException, CustomerNotFoundException,
			UniversityNotFound, ApproveBusinessException, IOException,
			HttpReturnException, CreateInputPayException,
			UpdateCustomerTimeException, HttpUrlRemoteException,
			UpdateAppPayException, UpdateInputAppException,
			CancelCallarAppException, RuleValException,
			QueryUserCardNotFoundException, SiteNotFoundException,
			UpdateTempAppException, UpdateCardException, SaveCardException,
			UpdateApplicationPicException, SendMobileMsgException,SaveTongDunBlackException, TongDunValRulesException {

		CreateAppPayQuery appPayQuery = new CreateAppPayQuery();
		/* 获取申请的实物产品 */
		// ProductInfo productInfo =
		// appPayQuery.getProductInfo(tempReq.getProId());

		/* 创建申请PAY */// 这里为loanAmtCalResp赋值
		Map<String, Object> appPayMap = getAppPayMap(tempReq, proEntity,
				appPayQuery);
		ApplyPayEvent applyPayEvent = new ApplyPayEvent();
		applyPayEvent.createAppPay(appPayMap);

		/* 创建inputApp */
		String appPayNo = appPayMap.get("app_application_no").toString();
		Map<String, Object> inputPayMap = getInputApp(tempReq, proEntity,
				appPayNo);
		if (customer.getSpouseMobile() != null
				&& !"".equals(customer.getSpouseMobile())
				&& !customer.getSpouseMobile().equalsIgnoreCase("null")) {
			inputPayMap.put("spouseMobileNo", customer.getSpouseMobile());
		}
		if (customer.getSpouseName() != null
				&& !"".equals(customer.getSpouseName())
				&& !customer.getSpouseName().equalsIgnoreCase("null")) {
			inputPayMap.put("spouseRealName", customer.getSpouseName());
		}

		InputAppPayEvent inputAppPayEvent = new InputAppPayEvent();
		inputAppPayEvent.createInputAppPay(inputPayMap);

		/* 模拟操作 设置申请件状态到设置资料 */
		// applyPayEvent.updateAppPayToSetInfoStatus(appPayNo);
		/* 修改inputApp creditName */
		updateCreditName(appPayMap.get("app_month_pay").toString(), appPayMap
				.get("app_install_ment").toString(),
				appPayMap.get("app_application_no").toString());

		/**  */
		// 根据appNO修改d_application_pay表
		ApplicationPay applicationPay = getApp(customer, appPayNo);
		// 根据appNO修改d_input_pay表
		InputAppPay inputAppPay = getInputAppPay(customer, appPayNo);

		ProductQuery productQuery = new ProductQuery();
		// CreditRelationQuery creditRelationQuery = new CreditRelationQuery();
		ApplicationPayQuery applicationPayQuery = new ApplicationPayQuery();
		ApplicationPay tempApp = applicationPayQuery
				.queryApplicationPayById(appPayNo);
		// CreditRelation rCreditRelation =
		// creditRelationQuery.queryCreditReMerchant(tempApp.getProductInfo(),
		// tempApp.getInstallMent());

		// LoanProduct loanProduct =
		// productQuery.queryCreditProductById(tempReq.getLoanProduct());
		LoanProduct loanProduct = productQuery
				.queryCreditProductById(StringUtils.valueOf(loanAmtCalResp
						.getLoanProduct()));
		CreateAppPayEvent createAppPayUpdate = new CreateAppPayEvent();
		// CreateLoanPlanReq createLoanPlanReq = (CreateLoanPlanReq)
		// parmRequest;
		createAppPayUpdate.updateApplicationPay(applicationPay);
		createAppPayUpdate.updateInputAppPay(inputAppPay);
		/* 更新用户还款账户 */
		updateUserCardInfo(tempReq);

		/* 将临时表图片信息更新到申请件图片表 */
		updateAppPicInfo(tempReq.getUserName(), appPayNo);//
		// /*将临时表中,选择的手机号码，套餐id,商品名字更新到申请件表*/
		updateAppDxInfo(tempReq.getUserName(), appPayNo, tempReq.getIsDx(),
				tempReq.getTempAppNo());
		CustcustomerQuery custcustomerQuery = new CustcustomerQuery();
		// 根据userName查询出客户个人基本信息
		CustCustomer custCustomer = custcustomerQuery.queryCustCustomer(tempReq
				.getUserName());

		// 执行进件规则
		saveRunRulesRecord(appPayNo, "");
		Map<Object, Object> map = new HashMap<Object, Object>();
		IntoRules intoRules = new IntoRules();

		RulesQuery rulesQuery = new RulesQuery();
		String recordId = rulesQuery.queryRechordId(appPayNo);

		try {
			if (Consts.APP_CUSTOMER_TYPE_KHL1
					.equals(custCustomer.getCustType())) {
				map = intoRules.exeApprRules(appPayNo, recordId);
			} else {
				map = intoRules.exeApprRulesForKHL2(appPayNo, recordId);
			}
			/* 嗨秒贷规则 */
			map = intoRules.exeApprRulesForHMD(tempReq, appPayNo, recordId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean flag = (Boolean) map.get("results");
		String tempCreateFlag = Consts.FINAL_NUMBER_1;

		if (flag) {
			// 命中规则 取消申请件
			ApplicationPayQuery applicationQuery = new ApplicationPayQuery();
			ApplicationPay appay = applicationQuery
					.queryApplicationPayById(appPayNo);
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
			// throw new RuleValException();
			// 临时申请件关闭交易
			tempCreateFlag = Consts.FINAL_NUMBER_2;
			
			/* 发送进件拒绝通知短信 */
//			sendValidateCode(customer.getMobileNo(), customer.getUserName());
			
		} else {
			// 现金类型冻结额度 根据金额还有用户名修改冻结金额
			if (Consts.APPFLOW_TYPE_CASH.equals(tempReq.getApplyType())) {
				updateUserInfoByUserName(
						new BigDecimal(tempApp.getApplyAmount()),
						tempReq.getUserName());
			}
			// 生成loan_loan(建账)
			createLoan(appPayNo, loanProduct, tempReq.getUuid(), tempReq);
			// 创建成功发送短信
			/*
			 * applicationSuccSmscash(custCustomer.getMobileNo(),
			 * custCustomer.getEmailAdress(), createLoanPlanReq.getUserName());
			 * createLoanPlanResp.setResultCode(ResultCodes.NORMAL);
			 */
			// 申请成功，发送手机短信 2015-12-23更改 Cary.Liu
//			NoticeSendParam param = new NoticeSendParam();
//			param.setMobile(custCustomer.getMobileNo());
//			param.setUserName(custCustomer.getUserName());
//			param.setRealName(custCustomer.getRealName());
//			param.setCustType(custCustomer.getCustType());
//			param.setIndustryCode(proEntity.getIndustryCode());
//			param.setAppNo(appPayNo);
//			sendSuccNotice(param);
			// 电信专案申请成功，发送手机短信
//			sendDxzaSuccess(custCustomer.getMobileNo(),
//					custCustomer.getUserName());
		}

		/* 更新临时申请表 */
		//
		TempAppInfoEvent tempApplyEvent = new TempAppInfoEvent();
		tempApplyEvent.createAppSuccFlagDx(tempReq.getUserName(),
				tempReq.getTempAppNo(), appPayNo, tempCreateFlag,
				tempReq.getIsDx());

		// 创建成功发送短信
		// applicationSuccSmscash(customer.getMobileNo(),customer.getEmailAdress(),tempReq.getUserName());

		// } catch (Exception e) {
		// }

		return appPayNo;
	}

	/**
	 * 电信专案申请成功 发送短信通知
	 * 
	 * @throws SendMobileMsgException
	 * */
	public void sendDxzaSuccess(String mobile, String username)
			throws SendMobileMsgException {
		ExternalService externalService = new ExternalService();
		externalService.sendApplyDxzaSucc(mobile, username);
	}

	/**
	 * 申请件床架成功，发送短信
	 * @param param
	 * @throws SendMobileMsgException
	 */
	public void sendSuccNotice(NoticeSendParam param) {

		NoticeSendService sendService = new NoticeSendService(param);
		sendService.sendAppApplyNotice();
	}
	
	public Map<String, Object> getCustomerMap(CreateAppPayReq tempReq) {

		Map<String, Object> customer = new HashMap<String, Object>();
		// 白领毕业时间
		customer.put("end_school_time", tempReq.getEndSchoolTime());
		customer.put("username", tempReq.getUserName());

		return customer;
	}

	public Map<String, Object> getAppPayMap(CreateAppPayReq tempReq,
			MerProductEntity proEntity, CreateAppPayQuery appPayQuery)
			throws SQLException, GenerateFlowNoException, QueryFlowNoException,
			CustomerNotFoundException, UniversityNotFound,
			ApproveBusinessException, HttpException, IOException,
			HttpReturnException, HttpUrlRemoteException, SiteNotFoundException {

		CreateAppPayAssem appPayAssem = new CreateAppPayAssem(appPayQuery);
		/* 获取门店(售点) */
		SaleSiteQuery siteQuery = new SaleSiteQuery();
		SaleSiteEntity siteEntity = siteQuery.querySaleSiteById(tempReq
				.getSiteId());
		if (siteEntity == null) {
			throw new SiteNotFoundException();
		}
		
		CustUserEntity custUser = queryCustUserByUserName(tempReq.getUserName());

		/* 初始化申请件状态信息 未认领件 */
		appPayAssem.initPayNodeAndStatus1();

		/* 初始化流水号和产品信息 */
		appPayAssem.initFlowNoAndProduct2(tempReq, proEntity);

		/* 初始化客户信息 */
		appPayAssem.initCustomer3(tempReq.getUserName());

		/* 初始化地域信息 申请件地址(初始默认售点所在地址) */
		appPayAssem.initCurrentCity4(siteEntity.getProvince(),
				siteEntity.getCity());

		/* 加载其他参数 */
		appPayAssem.initAppOtherInfo(tempReq, customer, custUser,proEntity);
		
		/* 初始化办单人员信息 */
		// appPayAssem.initSaleServer5(approveUser);

		/* 初始化商户售点 */
		appPayAssem.initSupplierApprove(tempReq, siteEntity);

		LoanAmtCalReq loanAmtCalReq = new LoanAmtCalReq();
		loanAmtCalReq.setTranPrice(tempReq.getTranPrice());
		loanAmtCalReq.setFirstRate(Consts.DEAFAULT_RATE);//不传,默认0首付
		loanAmtCalReq.setLoanProduct(tempReq.getLoanProduct());
		loanAmtCalReq.setUuid(tempReq.getUuid());
		loanAmtCalReq.setPayType(tempReq.getApplyType());

		// loanAmtCalReq.setCustType(tempReq.getCustType());
		// loanAmtCalReq.setCityCode(approveUser.getCityCode());
		// loanAmtCalReq.setProductId(tempReq.getProId());
		// loanAmtCalReq.setMerProId(tempReq.getMerProId());
		/* 初始化金额信息 */
		loanAmtCalResp = appPayAssem.initAppPayAmt6(loanAmtCalReq);

		return appPayAssem.getAppPayMap();
	}

	public Map<String, Object> getInputApp(CreateAppPayReq tempReq,
			MerProductEntity proEntity, String appPayNo) throws SQLException,
			QueryUserCardNotFoundException {

		InputAppPayAssem inputAppPayAssem = new InputAppPayAssem();
		return inputAppPayAssem.getInputAppMap(tempReq, proEntity, appPayNo);
	}

	public void checkApp(CreateAppPayReq req) throws AppExitErrorException,
			CustomerNotFoundException, MerProductNotFoundException,
			CustTypeRoleException, TempApplyNotFoundException,
			RepeatApplyMDException {

		ApplicationPayQuery query = new ApplicationPayQuery();
		CustcustomerQuery custcustomerQuery = new CustcustomerQuery();

		/* 是否重复申请 */
		// List<ApplicationPay> pays = new ArrayList<ApplicationPay>();
		// String applyType = Consts.APPFLOW_TYPE_CASH;
		// if(!Consts.APPFLOW_TYPE_CASH.equalsIgnoreCase(req.getApplyType())){
		// applyType = Consts.APPFLOW_TYPE_3C;
		// pays = query.querySByName3C(req.getUserName());
		// }else{
		// pays = query.querySByName(req.getUserName(),
		// ProcessConst.STATUS01,applyType);
		// }
		/* 是否存在该用户 */
		customer = custcustomerQuery.queryCustCustomer(req.getUserName());
		if (customer == null) {
			throw new CustomerNotFoundException();
		}
		/* 获取商品信息 */
		MerProductQuery merProQuery = new MerProductQuery();
		proEntity = merProQuery.queryMerProByProId(req.getMerProId());
		if (proEntity == null) {
			throw new MerProductNotFoundException();
		}
		// 商品名字设置为名字+颜色
		// proEntity.setProName(proEntity.getProName()+"-"+proEntity.getProColor());
		/* 获取用户临时申请单信息 */
		TempApplyQuery tempAppQuery = new TempApplyQuery();
		tempApplyEntity = tempAppQuery.queryTempApplyByDX(req.getUserName(),
				req.getTempAppNo(), req.getIsDx());

		/* 该客户类型是否有申请指定行业的权限 */
		IndustryTypeQuery industryQuery = new IndustryTypeQuery();
		boolean custRole = industryQuery.queryIndustryByCust(req.getCustType(),
				proEntity.getIndustryCode());
		if (!custRole) {
			throw new CustTypeRoleException();
		}

		if (tempApplyEntity == null) {
			throw new TempApplyNotFoundException();
		}

		/* 是否重复申请 */
		List<ApplicationPay> pays = query.queryExistApp(req.getUserName());
		if (pays != null && pays.size() > 0) {
			throw new RepeatApplyMDException();
		}

		/* 嗨秒贷重复申请验证 */
		if (Consts.INDUSTRY_DXSM.equals(proEntity.getIndustryCode())) {
			List<ApplicationPay> appByMdList = query.queryAppByIndustry(
					req.getUserName(), Consts.INDUSTRY_DXSM);
			if (appByMdList != null && appByMdList.size() > 0) {
				throw new RepeatApplyMDException();
			}
		}

		// RepeatApplyMDException

		// if(pays.size()>0){
		// throw new AppExitErrorException();
		// }

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

		// applicationPay.setApplicationNode("NO01");// 插入电话客服岗节点

		// applicationPay.setApplicationStatus("ZT01");// 插入申请状态

		applicationPay.setUsername(custCustomer.getUserName());// 插入用户名

		applicationPay.setUserIdentityNo(custCustomer.getIdentityNo());// 插入用户身份证号码
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		applicationPay.setMerchAntEndTime(sdf.format(new Date()));// 商户提交时间

		// applicationPay.setAppStage("APP1");// 设置申请状态

		applicationPay.setPayMentFlag(false);// 设置还款标识

		// applicationPay.setNode("NODE06");// 信用审核

		// applicationPay.setStatus("STATUS07");// 等待初审

		// applicationPay.setAllNode("SHNODE");// 审核中

		// mod by mary 流程节点修改为设置产品资料 start
		// applicationPay.setNode("NODE03");
		// applicationPay.setStatus("STATUS04");
		// applicationPay.setAllNode("ZLNODE");
		// end

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

		inputAppPay.setQqNumber(custCustomer.getQqNumber()); // qq号码

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

			inputAppPay.setStudentType(custCustomer.getStuType()); // 学生类型

			inputAppPay.setSchool(custCustomer.getSchool());// 学校

			inputAppPay.setStudentId(custCustomer.getStudentId());// 学号

			inputAppPay.setEducational(custCustomer.getEducational());// 在读学历

			if (custCustomer.getAdmissionTime() == null
					|| custCustomer.getAdmissionTime().equals("")) {

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

			inputAppPay.setEducation(custCustomer.getNowEduCation());// 白领学历

			if (custCustomer.getEndSchoolTime() == null
					|| custCustomer.getEndSchoolTime().equals("")) {

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
				if (com.mysql.jdbc.StringUtils.isNullOrEmpty(custCustomer
						.getSpouseMobile())) {
					if (!com.mysql.jdbc.StringUtils.isNullOrEmpty(custCustomer
							.getSpouseMobile())) {
						inputAppPay.setSpouseMobileNo(Long
								.parseLong(custCustomer.getSpouseMobile()));// 配偶手机号码
					}

				}

			}

			inputAppPay.setUnitIndustry(custCustomer.getUnitProperties());// 单位性质

			inputAppPay
					.setHousingCondition(custCustomer.getHousingConditions());// 住房状况

			inputAppPay.setEmployeeNumber(custCustomer.getJobNumber());// 工号

			inputAppPay.setCensusProvince(custCustomer
					.getPermanentAddressProvince());// 户籍地址--省

			inputAppPay.setCensusCity(custCustomer.getPermanentAddressCity());// 户籍地址--市

			inputAppPay.setCensusArea(custCustomer.getPermanentAddressArea());// 户籍地址--区

			inputAppPay
					.setCensusAddress(custCustomer.getPermanentAddressRaod());// 户籍地址--详细地址

			inputAppPay.setMonthlyConsumption(custCustomer
					.getMonthlyConsumption());// 月消费

			inputAppPay.setMonthRent(custCustomer.getMonthRent());// 房租月供

			inputAppPay.setLoanCount(custCustomer.getLoanCount());// 贷款数量

			inputAppPay.setLoanTotalAmount(custCustomer.getLoanTotalAmount());// 贷款总额

			inputAppPay.setLoanMonthRent(custCustomer.getLoanMonthRent());// 贷款月供

			inputAppPay.setCreditCardCount(custCustomer.getCreditCardCount());// 信用卡数量

			inputAppPay.setCreditTotalAmount(custCustomer
					.getCreditTotalAmount());// 信用卡总额度

			inputAppPay.setCreditHigthAmount(custCustomer
					.getCreditHigthAmount());// 信用卡最高额度

			if (custCustomer.getEntryTime() == null
					|| custCustomer.getEntryTime().equals("")) {

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

		return inputAppPay;
	}

	public void updateUserInfoByUserName(BigDecimal applyAmt, String userName) {
		CustLimitQuery custLimitQuery = new CustLimitQuery();
		CustLimitEvent custLimitEvent = new CustLimitEvent();
		// 根据用户名查询可用金额
		CustLimitEntity custLimit = custLimitQuery
				.queryCustLimitByUserName(userName);

		// 更改冻结金额 原来冻结金额+现在调额额度
		BigDecimal blockAmt = new BigDecimal(custLimit.getBlockAmt())
				.add(applyAmt);

		// 更新冻结金额
		custLimitEvent.updateBlockAmtByUserName(blockAmt, userName);
	}

	public void createLoan(String applicationNo, LoanProduct loanProduct,
			String uuid, CreateAppPayReq tempReq) throws HttpException,
			IOException, HttpReturnException, HttpUrlRemoteException {
		ApplicationPayQuery applicationQuery = new ApplicationPayQuery();

		ProductQuery productQuery = new ProductQuery();
		// ProductInfoQuery productInfoQuery = new ProductInfoQuery();
		MerProductQuery merProQuery = new MerProductQuery();

		ApplicationPay applicationPay = applicationQuery
				.queryApplicationPayById(applicationNo);

		LoanLoan loan = loanloanQuery.queryLoanLoanByAppId(applicationNo);

		if (loan == null) {
			loan = new LoanLoan();
		} else {
			// LoanLoanRecordEvent loanLoanRecordEvent = new
			// LoanLoanRecordEvent();
			// // 取消预授权
			// loanLoanRecordEvent.createLoanRecord(loan);

		}

		loan.setProductId(applicationPay.getCreditProductId());

		LoanProduct creditProduct = productQuery.queryCreditProductById(loan
				.getProductId() + "");

		// ProductInfo productInfo = productInfoQuery
		// .queryProductInfoById(applicationPay.getProductInfo() + "");
		MerProductEntity proEntity = merProQuery
				.queryMerProByProId(applicationPay.getMerProId());

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
		/*
		 * if (isFirstPay(applicationPay)) { loan.setAmount(new
		 * BigDecimal(applicationPay.getTranPrice()) .subtract(new
		 * BigDecimal(applicationPay .getFirstPayPrincipal())) + ""); } else {
		 * loan.setAmount(applicationPay.getTranPrice()); }
		 */

		// LoanAmtCalReq loanAmtCalReq = new LoanAmtCalReq();
		// loanAmtCalReq.setCustType(applicationPay.getCustomerType());
		// loanAmtCalReq.setCityCode(applicationPay.getCityCode());
		// loanAmtCalReq.setPayType(applicationPay.getPayType());
		// loanAmtCalReq.setTranPrice(applicationPay.getApplyAmount());
		// loanAmtCalReq.setFirstRate(applicationPay.getFirstPayRate());
		// loanAmtCalReq.setLoanProduct(applicationPay.getCreditProductId());
		// loanAmtCalReq.setProductId(applicationPay.getProductInfo());
		// loanAmtCalReq.setMerProId(tempReq.getMerProId());
		//
		// loanAmtCalReq.setUuid(uuid);
		//
		// String loanAmount = getLoanAmount(loanAmtCalReq);
		// loan.setAmount(loanAmount);
		loan.setAmount(loanAmtCalResp.getLoanPrincipal().toString());

		loan.setInterestRate(creditProduct.getCreditRate());
		// DitdicQuery ditdicQuery = new DitdicQuery();
		// DitDic ditdic = ditdicQuery.getDicNameByDicCode(creditProduct
		// .getCreditPayTime());
		// Integer months = Integer.parseInt(ditdic.getDicName());
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
	 * 
	 * @param loanAmt
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 * @throws HttpReturnException
	 * @throws HttpUrlRemoteException
	 */
	public String getLoanAmount(LoanAmtCalReq loanAmt) throws HttpException,
			IOException, HttpReturnException, HttpUrlRemoteException {

		CreateAppPayQuery appPayQuery = new CreateAppPayQuery();

		LoanAmtCalResp amtCalResp = appPayQuery.remoteHttpAmt(loanAmt);

		String loanAmount = amtCalResp.getLoanPrincipal().toString();

		return loanAmount;

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

			LoanLoan queryLoan = loanloanQuery.queryLoanLoanByAppId(loan
					.getApplicationId());
			LoanLoanRecordEvent loanLoanRecordEvent = new LoanLoanRecordEvent();
			// 取消预授权
			loanLoanRecordEvent.createLoanRecord(queryLoan);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// try {
		// // add by zdh at 2012/10/30
		// List<String> mailLst = new ArrayList<String>();
		// // mailLst.add(ResourceUtils.getString("marster.mail1"));
		// // mailLst.add(ResourceUtils.getString("marster.mail2"));
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// for (String mailAddr : mailLst) {
		// if (!StringUtils.isNullOrEmpty(mailAddr)) {
		// NoticeSendflow noticeMailFlow = new NoticeSendflow();
		// noticeMailFlow.setUsername("MARSTER");
		// noticeMailFlow.setTitle("发布借款通知");
		// noticeMailFlow.setReceiveEmail(mailAddr);
		// noticeMailFlow.setNtype("MAIL");
		// noticeMailFlow.setSendFlag("O");
		// noticeMailFlow.setOperator("亨元金融");
		// noticeMailFlow.setReceiveTelphone("");
		// noticeMailFlow.setSendTime(sdf.format(new Date()));
		// noticeMailFlow.setContent("借款人（"
		// + username
		// + "）于"
		// + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
		// .format(new Date()) + "发布了【"
		// + loan.getTitle() + "】借款，借款金额（" + loan.getAmount()
		// + "）");
		// //发送邮件给管理员
		// // sendMailToMaster(noticeMailFlow);
		// }
		// }
		// } catch (Exception e) {
		// }

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
	 * 
	 * @param appNo
	 * @return
	 */
	public ApplicationEntity queryAppPayByNo(String appNo) {

		ApplicationQuery query = new ApplicationQuery();

		return query.queryAppPayByAppNO(appNo);
	}

	public void updateCreditName(String monthPay, String installment,
			String appNo) {

		InputAppPayEvent payEvent = new InputAppPayEvent();
		InputAppPay inputApp = new InputAppPay();
		inputApp.setCreditName(monthPay + "元×" + installment + "期");
		inputApp.setApplicationNo(appNo);
		payEvent.updateInputAppCreditName(inputApp);
	}

	public CreateAppPayReq getCreateReq(CreateAppPayReq req) {

		if (tempApplyEntity != null) {

			req.setTranPrice(tempApplyEntity.getApplyPrice());// 这个是不是商品的价格
			req.setLoanProduct(tempApplyEntity.getLoanProduct());// 这个是不是传的商品ID，如果是的，那么保存的时候，保存进去这两个字段，去掉自己增加的这两个字段
		}

		return req;
	}

	// public String getProductPrice(CreateAppPayReq req)
	// throws MerProductNotFoundException, ProPriceFalseException {
	// MerProductQuery query = new MerProductQuery();
	// MerProductEntity entity = query.queryMerProByProId(req.getMerProId());
	// if (entity != null) {
	// String reqAmount = String.valueOf(new BigDecimal(req.getProPrice())
	// .multiply(new BigDecimal(100)).longValue());
	// String dataAmount = String.valueOf(new BigDecimal(
	// getProductPrice(req)).multiply(new BigDecimal(100))
	// .longValue());
	// if (!reqAmount.equals(dataAmount)) {
	// throw new ProPriceFalseException();
	// }
	// return entity.getProPrice();
	// } else {
	// throw new MerProductNotFoundException();
	//
	// }
	//
	// }

	/**
	 * 更新用户收款账户
	 * 
	 * @param req
	 * @throws UpdateCardException
	 * @throws SaveCardException
	 */
	public void updateUserCardInfo(CreateAppPayReq req)
			throws UpdateCardException, SaveCardException {

		CollectCardEvent cardEvent = new CollectCardEvent();
		CollectAccountQuery accountQuery = new CollectAccountQuery();

		boolean isExist = accountQuery.queryIdEntityNoExist(req.getUserName(),
				req.getBankCard());
		CardParam param = new CardParam();
		param.setUserName(req.getUserName());
		param.setRealName(customer.getRealName());
		param.setBankType(Consts.CARD_TYPE_JJKT);
		param.setBank(req.getOpenBank());
		param.setBankCard(req.getBankCard());
		if (Consts.FINAL_NUMBER_0.equals(req.getIsSynPerAcct())) {
			param.setIsDefaultCard(Consts.DEFAULT_CARD_NO);
		} else {
			param.setIsDefaultCard(Consts.DEFAULT_CARD_YES);
		}

		if (Consts.DEFAULT_CARD_YES.equals(param.getIsDefaultCard().trim())) {
			cardEvent.updateDefaultCardByApp(req.getUserName());
		}

		if (isExist) {
			cardEvent.updateUserCardByApp(param);
		} else {
			cardEvent.saveUserCardByApp(param);
		}
	}

	public void updateAppDxInfo(String userName, String appPayNo, String isDx,
			String tempNo) throws UpdateAppPayException {
		if (customer != null) {

			TempApplyQuery tempApplyQuery = new TempApplyQuery();
			List<TempApplyEntity> tempPhoneList = tempApplyQuery
					.queryTempPhoneData(userName, isDx, tempNo);

			if (tempPhoneList != null && tempPhoneList.size() > 0) {

				List<CallPhoneDataParam> callPhoneDataList = new ArrayList<CallPhoneDataParam>();

				for (TempApplyEntity tempEntity : tempPhoneList) {

					CallPhoneDataParam phoneDataParam = new CallPhoneDataParam();
					phoneDataParam.setAppNo(appPayNo);
					phoneDataParam.setPhoneDataId(tempEntity.getPhoneDataId());
					phoneDataParam.setPhoneNo(tempEntity.getPhoneNo());
					phoneDataParam.setProDataName(tempEntity.getProDataName());
					callPhoneDataList.add(phoneDataParam);
				}

				ApplyPayEvent picEvent = new ApplyPayEvent();
				for (CallPhoneDataParam picParam : callPhoneDataList) {
					picEvent.updateAppDx(picParam);
				}

			}

		}
	}

	/**
	 * 更新申请件图片信息
	 * 
	 * @throws UpdateApplicationPicException
	 */
	public void updateAppPicInfo(String userName, String appNo)
			throws UpdateApplicationPicException {

		if (customer != null) {

			TempApplyPicQuery tempApplyQuery = new TempApplyPicQuery();
			List<TempApplyPicEntity> tempPicList = tempApplyQuery
					.queryTempApplyPic(userName);

			if (tempPicList != null && tempPicList.size() > 0) {

				List<PicParam> picList = new ArrayList<PicParam>();

				for (TempApplyPicEntity tempPic : tempPicList) {

					PicParam picParam = new PicParam();
					picParam.setAppNo(appNo);
					picParam.setPicType(tempPic.getPicType());
					picParam.setPicName(tempPic.getPicType());
					picParam.setPicPath(tempPic.getPicPath());
					picParam.setThumPath(tempPic.getThumPath());
					picParam.setCaption(tempPic.getPicCaption());
					picList.add(picParam);
				}

				ApplicationPicEvent picEvent = new ApplicationPicEvent();
				for (PicParam picParam : picList) {
					picEvent.saveAppPic(picParam, userName);
				}

			}

			// List<PicParam> picList = new ArrayList<PicParam>();
			//
			// /* 用户手持身份证正面照 */
			// if(!org.apache.commons.lang.StringUtils.isEmpty(customer.getUserIdcardFrontUrl())
			// &&
			// !Consts.FINAL_NULL_STR.equals(customer.getUserIdcardFrontUrl())){
			// PicParam picParam = new PicParam();
			// picParam.setAppNo(appNo);
			// picParam.setPicType(Consts.IMG_TYPE_ZL112);
			// picParam.setPicName(Consts.IMG_TYPE_ZL112);
			// picParam.setPicPath(customer.getUserIdcardFrontUrl());
			// picParam.setThumPath(customer.getUserIdcardFrontUrlThum());
			// picParam.setCaption(Consts.HMD_UPLOAD_FROM);
			// picList.add(picParam);
			// }
			// /* 身份证正面照 */
			// if(!org.apache.commons.lang.StringUtils.isEmpty(customer.getIdcardFrontUrl())
			// && !Consts.FINAL_NULL_STR.equals(customer.getIdcardFrontUrl())){
			// PicParam picParam = new PicParam();
			// picParam.setAppNo(appNo);
			// picParam.setPicType(Consts.IMG_TYPE_ZL02);
			// picParam.setPicName(Consts.IMG_TYPE_ZL02);
			// picParam.setPicPath(customer.getIdcardFrontUrl());
			// picParam.setThumPath(customer.getIdcardFrontUrlThum());
			// picParam.setCaption(Consts.HMD_UPLOAD_FROM);
			// picList.add(picParam);
			// }
			// /* 身份证反面照 */
			// if(!org.apache.commons.lang.StringUtils.isEmpty(customer.getIdcardVersoUrl())
			// && !Consts.FINAL_NULL_STR.equals(customer.getIdcardVersoUrl())){
			// PicParam picParam = new PicParam();
			// picParam.setAppNo(appNo);
			// picParam.setPicType(Consts.IMG_TYPE_ZL03);
			// picParam.setPicName(Consts.IMG_TYPE_ZL03);
			// picParam.setPicPath(customer.getIdcardVersoUrl());
			// picParam.setThumPath(customer.getIdcardVersoUrlThum());
			// picParam.setCaption(Consts.HMD_UPLOAD_FROM);
			// picList.add(picParam);
			// }
			//
			// /* 学生证封面 */
			// if(!org.apache.commons.lang.StringUtils.isEmpty(customer.getStuCardFront())
			// && !Consts.FINAL_NULL_STR.equals(customer.getStuCardFront())){
			// PicParam picParam = new PicParam();
			// picParam.setAppNo(appNo);
			// picParam.setPicType(Consts.IMG_TYPE_ZL04);
			// picParam.setPicName(Consts.IMG_TYPE_ZL04);
			// picParam.setPicPath(customer.getStuCardFront());
			// picParam.setThumPath(customer.getStuCardFrontThum());
			// picParam.setCaption(Consts.HMD_UPLOAD_FROM);
			// picList.add(picParam);
			// }
			//
			// /* 个人照片及基本信息 */
			// if(!org.apache.commons.lang.StringUtils.isEmpty(customer.getStuPhotoInfo())
			// && !Consts.FINAL_NULL_STR.equals(customer.getStuPhotoInfo())){
			// PicParam picParam = new PicParam();
			// picParam.setAppNo(appNo);
			// picParam.setPicType(Consts.IMG_TYPE_ZL05);
			// picParam.setPicName(Consts.IMG_TYPE_ZL05);
			// picParam.setPicPath(customer.getStuPhotoInfo());
			// picParam.setThumPath(customer.getStuPhotoInfoThum());
			// picParam.setCaption(Consts.HMD_UPLOAD_FROM);
			// picList.add(picParam);
			// }
			//
			// /* 注册登记信息 */
			// if(!org.apache.commons.lang.StringUtils.isEmpty(customer.getStuRegistInfo())
			// && !Consts.FINAL_NULL_STR.equals(customer.getStuRegistInfo())){
			// PicParam picParam = new PicParam();
			// picParam.setAppNo(appNo);
			// picParam.setPicType(Consts.IMG_TYPE_ZL06);
			// picParam.setPicName(Consts.IMG_TYPE_ZL06);
			// picParam.setPicPath(customer.getStuRegistInfo());
			// picParam.setThumPath(customer.getStuRegistInfoThum());
			// picParam.setCaption(Consts.HMD_UPLOAD_FROM);
			// picList.add(picParam);
			// }
			//
			// /* 校园卡正面 */
			// if(!org.apache.commons.lang.StringUtils.isEmpty(customer.getSchoolCardFront())
			// && !Consts.FINAL_NULL_STR.equals(customer.getSchoolCardFront())){
			// PicParam picParam = new PicParam();
			// picParam.setAppNo(appNo);
			// picParam.setPicType(Consts.IMG_TYPE_ZL07);
			// picParam.setPicName(Consts.IMG_TYPE_ZL07);
			// picParam.setPicPath(customer.getSchoolCardFront());
			// picParam.setThumPath(customer.getSchoolCardFrontThum());
			// picParam.setCaption(Consts.HMD_UPLOAD_FROM);
			// picList.add(picParam);
			// }
			//
			// /* 校园卡反面 */
			// if(!org.apache.commons.lang.StringUtils.isEmpty(customer.getSchoolCardVerso())
			// && !Consts.FINAL_NULL_STR.equals(customer.getSchoolCardVerso())){
			// PicParam picParam = new PicParam();
			// picParam.setAppNo(appNo);
			// picParam.setPicType(Consts.IMG_TYPE_ZL08);
			// picParam.setPicName(Consts.IMG_TYPE_ZL08);
			// picParam.setPicPath(customer.getSchoolCardVerso());
			// picParam.setThumPath(customer.getSchoolCardVersoThum());
			// picParam.setCaption(Consts.HMD_UPLOAD_FROM);
			// picList.add(picParam);
			// }
			//
			// ApplicationPicEvent picEvent = new ApplicationPicEvent();
			// for (PicParam picParam : picList) {
			// picEvent.saveAppPic(picParam, userName);
			// }

		}

	}

	/* 查询手机号码是否被使用 */
	public boolean queryIsorUse(String phone) throws CallPhoneDataNumException {
		CallPhoneNumMsgQuery query = new CallPhoneNumMsgQuery();
		CallPhoneNumEntity entity = query.queryIsorUse(phone);
		if (entity == null) {
			throw new CallPhoneDataNumException();
		}
		if (entity.getIsoruse().equals("1")) {//被使用
			return true;
		} else {
			return false;
		}

	}

	public void checkPhoneNum(CreateAppPayReq req)
			throws  UpdateInfoException, CallPhoneDataNumLockException, CallPhoneDataNumException {
		// 查询，选择的手机号码是否可用，如果可用则锁定，如果不可用则返回code
		if (queryIsorUse(req.getPhoneNum())) {// 如果被锁定
			throw new CallPhoneDataNumLockException();
		} else {//
			CallPhoneDataNumUpEvent event = new CallPhoneDataNumUpEvent();
			event.updatePhoneStatue(req);
		}

	}
	
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	public CustUserEntity queryCustUserByUserName(String userName){
		CustUserQuery custUserQuery = new CustUserQuery();
		return custUserQuery.queryByUserName(userName);
	}
	
	/**
	 * 创建成功后非事物操作
	 * @param req
	 */
	private void createSuccAfter(CreateAppPayReq req, String appNo){
		
		CustcustomerQuery customerQuery = new CustcustomerQuery();
		CustCustomer custCustomer = customerQuery.queryCustCustomer(req.getUserName());
		
		// 申请成功，发送手机短信 2015-12-23更改 Cary.Liu
		NoticeSendParam param = new NoticeSendParam();
		param.setMobile(custCustomer.getMobileNo());
		param.setUserName(custCustomer.getUserName());
		param.setRealName(custCustomer.getRealName());
		param.setCustType(custCustomer.getCustType());
		param.setIndustryCode(proEntity.getIndustryCode());
		param.setAppNo(appNo);
		sendSuccNotice(param);
		
	}
	
	/**
	 * 发送申请拒绝手机短信
	 * */
	public void sendValidateCode(String mobile, String username, String realName) {
		
		ExternalService externalService = new ExternalService();
		externalService.sendApplyAppFailSms(mobile,username,realName);
	}
	
}
