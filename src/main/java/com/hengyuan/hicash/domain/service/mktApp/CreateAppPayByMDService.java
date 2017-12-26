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

import com.google.gson.Gson;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.assem.CreateAppPayAssem;
import com.hengyuan.hicash.domain.assem.InputAppPayAssem;
import com.hengyuan.hicash.domain.event.apply.ApplicationPicEvent;
import com.hengyuan.hicash.domain.event.apply.ApplyPayEvent;
import com.hengyuan.hicash.domain.event.apply.ApproveRecordEvent;
import com.hengyuan.hicash.domain.event.apply.AutoBatchRuleEvent;
import com.hengyuan.hicash.domain.event.apply.CouponRecordEvent;
import com.hengyuan.hicash.domain.event.apply.CreateAppPayEvent;
import com.hengyuan.hicash.domain.event.apply.CreateLoanLoanUpdate;
import com.hengyuan.hicash.domain.event.apply.CustLimitEvent;
import com.hengyuan.hicash.domain.event.apply.InputAppPayEvent;
import com.hengyuan.hicash.domain.event.apply.LoanLoanRecordEvent;
import com.hengyuan.hicash.domain.event.apply.TempAppInfoEvent;
import com.hengyuan.hicash.domain.event.user.CollectCardEvent;
import com.hengyuan.hicash.domain.query.activity.NewYearLotterQuery;
import com.hengyuan.hicash.domain.query.app.ApplicationPayQuery;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.app.ProductQuery;
import com.hengyuan.hicash.domain.query.app.TempApplyQuery;
import com.hengyuan.hicash.domain.query.mer.MerProductQuery;
import com.hengyuan.hicash.domain.query.mer.SaleSiteQuery;
import com.hengyuan.hicash.domain.query.mer.SupStoreQuery;
import com.hengyuan.hicash.domain.query.mktApp.AcctAccountQuery;
import com.hengyuan.hicash.domain.query.mktApp.CreateAppPayQuery;
import com.hengyuan.hicash.domain.query.mktApp.CustPayAccountQuery;
import com.hengyuan.hicash.domain.query.mktApp.IndustryTypeQuery;
import com.hengyuan.hicash.domain.query.mktApp.LoanloanQuery;
import com.hengyuan.hicash.domain.query.param.CityQuery;
import com.hengyuan.hicash.domain.query.param.SysBankInfoQuery;
import com.hengyuan.hicash.domain.query.param.SystemParamQuery;
import com.hengyuan.hicash.domain.query.param.TempApplyPicQuery;
import com.hengyuan.hicash.domain.query.user.CollectAccountQuery;
import com.hengyuan.hicash.domain.query.user.CouponQuery;
import com.hengyuan.hicash.domain.query.user.CustLimitQuery;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.notic.ExternalService;
import com.hengyuan.hicash.domain.service.notic.NoticeSendService;
import com.hengyuan.hicash.domain.service.remote.RemoteService;
import com.hengyuan.hicash.domain.service.user.DoubleIdentityValService;
import com.hengyuan.hicash.entity.activity.NewYearLotteryEntity;
import com.hengyuan.hicash.entity.app.AcctAccount;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.app.CustPayAccount;
import com.hengyuan.hicash.entity.app.InputAppPay;
import com.hengyuan.hicash.entity.app.LoanProduct;
import com.hengyuan.hicash.entity.app.PicParam;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.entity.mer.MerProductEntity;
import com.hengyuan.hicash.entity.mer.SaleSiteEntity;
import com.hengyuan.hicash.entity.mer.SupStoreEntity;
import com.hengyuan.hicash.entity.mktApp.ApprovalRecord;
import com.hengyuan.hicash.entity.mktApp.LoanLoan;
import com.hengyuan.hicash.entity.param.AutoBatchRuleEntity;
import com.hengyuan.hicash.entity.param.BankBranchEntity;
import com.hengyuan.hicash.entity.param.CityParam;
import com.hengyuan.hicash.entity.param.NoticeSendParam;
import com.hengyuan.hicash.entity.param.SystemParamEntity;
import com.hengyuan.hicash.entity.param.TempApplyPicEntity;
import com.hengyuan.hicash.entity.user.Coupon;
import com.hengyuan.hicash.entity.user.CouponRecord;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.CustLimitEntity;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.exception.AppExitErrorException;
import com.hengyuan.hicash.exception.ApplyNotInOneMonthException;
import com.hengyuan.hicash.exception.ApproveBusinessException;
import com.hengyuan.hicash.exception.CreateAppPayException;
import com.hengyuan.hicash.exception.CreateInputPayException;
import com.hengyuan.hicash.exception.CustTypeRoleException;
import com.hengyuan.hicash.exception.CustomerNotFoundException;
import com.hengyuan.hicash.exception.DDNotFoundException;
import com.hengyuan.hicash.exception.DayException;
import com.hengyuan.hicash.exception.GenerateFlowNoException;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.exception.MDNotFoundException;
import com.hengyuan.hicash.exception.MerProductNotFoundException;
import com.hengyuan.hicash.exception.OneMonthApplyException;
import com.hengyuan.hicash.exception.ProductNotFoundException;
import com.hengyuan.hicash.exception.QueryFlowNoException;
import com.hengyuan.hicash.exception.QueryUserCardNotFoundException;
import com.hengyuan.hicash.exception.ReferenceRecordException;
import com.hengyuan.hicash.exception.RepeatApplyMDException;
import com.hengyuan.hicash.exception.SaveAutoRuleAppException;
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
import com.hengyuan.hicash.exception.UpdateCustUserException;
import com.hengyuan.hicash.exception.UpdateCustomerTimeException;
import com.hengyuan.hicash.exception.UpdateTempAppException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppPayReq;
import com.hengyuan.hicash.parameters.request.param.CardParam;
import com.hengyuan.hicash.parameters.request.param.LoanAmtCalReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.mktApp.CreateAppPayResp;
import com.hengyuan.hicash.parameters.response.user.LoanAmtCalResp;
import com.hengyuan.hicash.parameters.response.user.PyFxpfRemoteResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.LoanPeriod;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * 创建申请件业务处理类
 * 
 * @author Cary.Liu
 * @createDate 2015-05-21
 */
public class CreateAppPayByMDService implements RespService {

	private static Logger logger = Logger
			.getLogger(CreateAppPayByMDService.class);

	private LoanloanQuery loanloanQuery = new LoanloanQuery();

	private CouponQuery couponQuery = new CouponQuery();

	private SysBankInfoQuery sysBankInfoQuery = new SysBankInfoQuery();

	LoanAmtCalResp loanAmtCalResp = null;

	private DoubleIdentityValService doubleIdentityValService = new DoubleIdentityValService();

	String monthPay = "";// 月还款
	String installMent = "";// 还款期数;

	/** 2016新年活动-是否有抽奖机会 */
	private String lotteryFlag = Consts.FINAL_NUMBER_0;

	private CustCustomer customer = null;
	private CustUserEntity custUser = null;
	private MerProductEntity proEntity = null;
	private TempApplyEntity tempApplyEntity = null;

	@Override
	public synchronized ParmResponse responseValue(ParmRequest parmRequest) {

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
						/* 创建申请件 */
						applicationNo = initApplicationPay(req);
						queryPayMsg(applicationNo);
						createAppPayResp.setApplyAmount(req.getTranPrice());

						/* 2017-01-10 新版嗨钱APP上线 无推荐 */
						// if
						// ((!RegexValidate.isNull(req.getReference()))&&(!req.getUserName().equals(req.getReference())))
						// {
						// ReferenceRecordEvent recordEvent = new
						// ReferenceRecordEvent();
						// ReferenceRecordEntity recordEntity = new
						// ReferenceRecordEntity();
						// recordEntity.setBussiness(req.getBussiness());
						// recordEntity.setRecommend(req.getRecommend());
						// recordEntity.setReference(req.getReference());
						// recordEntity.setReferenceIP(req.getReferenceIP());
						// recordEntity.setReferenceTime(req.getReferenceTime());
						// recordEntity.setUpPage(req.getUpPage());
						// recordEntity.setAppNo(applicationNo);
						// recordEntity.setHyIndustryCode(proEntity.getIndustryCode());
						//
						// recordEvent.saveReferenceRecord(recordEntity);
						//
						// }

						ConnManager.commit();
						// 申请成功插入推荐信息表

						// 2016-12-1 创建订单不发短信
						// createSuccAfter(req,applicationNo);

						resultCode = ResultCodes.NORMAL;
						/** 进件成功后注入redis缓冲 */
						/*
						 * String userOrdersn =req.getTempAppNo(); String
						 * userName =req.getUserName(); String nowTime =
						 * DateUtils.getNowTime(); String
						 * userPhone=req.getUserName(); WxbagEntity wxbagEntity
						 * = new WxbagEntity(); wxbagEntity.setUserId(userName);
						 * wxbagEntity.setcTime(nowTime);
						 * wxbagEntity.setUserOrdersn(userOrdersn);
						 * wxbagEntity.setIs_pay_money(0);
						 * wxbagEntity.setUserPhone(userPhone); 取活动eventkey
						 * Jedis jedis = new Jedis("10.139.97.184",6379);
						 * jedis.auth("hengyuan-redis123"); logger.info(
						 * "连接数据库缓冲jedis…………………………………………………………………………………………>" +
						 * jedis); Set<String> keys =
						 * jedis.keys("wx_event:*:into"); String evenGroup = "";
						 * String event_key = ""; for(String name : keys){
						 * evenGroup = jedis.hget(name, "event_group");;
						 * event_key = jedis.hget(name, "event_key"); }
						 * logger.info
						 * ("活动eventGroup……………………………………………………………………>: " +
						 * evenGroup);
						 * logger.info("活动event_key…………………………………………………………………………>: "
						 * + event_key); wxbagEntity.setEvenKey(event_key);
						 * wxbagEntity.setEventGroup(evenGroup); logger.info(
						 * "写入微信实体类的数据…………………………………………………………………………………………>");
						 * 写入微信红包wx_bag WxBagAdd wxBag = new WxBagAdd();
						 * wxBag.addWxBag(wxbagEntity); logger.info(
						 * "写入微信红包wx_bag…………………………………………………………………………………………>成功" +
						 * wxBag); // 0为执行插入返回的自增ID WxBagQuery wxBagQuery = new
						 * WxBagQuery(); WxbagEntity wxBagId =
						 * wxBagQuery.queryApplicationPayById(userOrdersn);
						 * 注册成功后写入REDIS缓存 String wx_bag_name = " wx_bag:"+
						 * evenGroup + ":"+ event_key + "::" + userName + ":"+
						 * userPhone + ":" +userOrdersn+ ":0:"+
						 * wxBagId.getBagId(); jedis.hset(wx_bag_name,
						 * "user_id", userName); jedis.hset(wx_bag_name,
						 * "user_ordersn", userOrdersn); jedis.hset(wx_bag_name,
						 * "user_phone", userPhone); jedis.hset(wx_bag_name,
						 * "event_group", evenGroup); jedis.hset(wx_bag_name,
						 * "event_key", event_key); jedis.hset(wx_bag_name,
						 * "c_time", nowTime); logger.info(
						 * "写入REDIS缓存成功wx_bag_name;………………………………………………………………>" +
						 * wx_bag_name);
						 */
					} else {
						resultCode = ResultCodes.CREATEAPPPAY_MINAMOUNT;
					}

				} else {
					resultCode = ResultCodes.CREATEAPPPAY_USEAMOUNT_BZ;
				}

			} else {
				resultCode = ResultCodes.CREATEAPP_REPEATSUBMIT_FAIL;
			}

		} catch (ApplyNotInOneMonthException e) {
			resultCode = ResultCodes.CREATEAPP_APPLY_OUTONEMONTH;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (OneMonthApplyException e) {
			resultCode = ResultCodes.CREATEAPP_CANTNOT_APPLY;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (RepeatApplyMDException e) {
			resultCode = ResultCodes.CREATEAPP_REPEAMD_FAIL;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (TempApplyNotFoundException e) {
			resultCode = ResultCodes.CREATEAPP_TEMPAPPLY_NOTFOUND;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (CustTypeRoleException e) {
			resultCode = ResultCodes.CREATEAPP_CUSTROLE_FAIL;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (MerProductNotFoundException e) {
			resultCode = ResultCodes.CREATEAPP_MERPRO_NOTFOUND;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (CustomerNotFoundException e) {
			resultCode = ResultCodes.CREATEAPP_APPLYINFO_NOT_FOUND;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (AppExitErrorException e) {
			resultCode = ResultCodes.CREATEAPP_REPEAT_FAILD;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (MDNotFoundException e) {
			resultCode = ResultCodes.MD_CLOSE;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (DDNotFoundException e) {
			resultCode = ResultCodes.DDCP_ADDRESS;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (UpdateCustUserException e) {
			resultCode = ResultCodes.CREATEAPP_UPDATECUST_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (SaveAutoRuleAppException e) {
			resultCode = ResultCodes.CREATEAPP_SAVEAUTORULE_FAIL;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (UpdateApplicationPicException e) {
			resultCode = ResultCodes.CREATEAPP_UPDATEPIC_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (UpdateCardException e) {
			resultCode = ResultCodes.CREATEAPP_UPDATECARD_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (SaveCardException e) {
			resultCode = ResultCodes.CREATEAPP_UPDATECARD_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (UpdateTempAppException e) {
			resultCode = ResultCodes.CREATEAPP_UPDATETEMP_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (GenerateFlowNoException e) {
			resultCode = ResultCodes.CREATEAPP_GETAPPNO_FAILD;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (HttpException e) {
			resultCode = ResultCodes.CREATEAPP_GETACCT_FAILD;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (SiteNotFoundException e) {
			resultCode = ResultCodes.CREATEAPP_SITE_NOTFOUND;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (ProductNotFoundException e) {
			resultCode = ResultCodes.CREATEAPP_PROINFO_NOTFOUND;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (CreateAppPayException e) {
			resultCode = ResultCodes.CREATEAPP_CREAT_FAILD;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (UpdateAppPayException e) {
			resultCode = ResultCodes.CREATEAPP_UPDATENODE_FAILD;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (SQLException e) {
			resultCode = ResultCodes.CREATEAPP_SQL_ERROR;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (QueryFlowNoException e) {
			resultCode = ResultCodes.CREATEAPP_QUERY_APPNO_FAILD;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (UniversityNotFound e) {
			resultCode = ResultCodes.CREATEAPP_SCHOOLINFO_NOT_FOUND;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (ApproveBusinessException e) {
			resultCode = ResultCodes.CREATEAPP_LINKINFO_NOT_FOUND;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (IOException e) {
			resultCode = ResultCodes.CREATEAPP_IO_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (HttpReturnException e) {
			resultCode = ResultCodes.CREATEAPP_ACCOUNT_ERROR;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (CreateInputPayException e) {
			resultCode = ResultCodes.CREATEAPP_INPUT_ERROR;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (UpdateCustomerTimeException e) {
			resultCode = ResultCodes.CREATEAPP_ENDSCHOOLTIME_ERROR;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (QueryUserCardNotFoundException e) {
			resultCode = ResultCodes.CREATEAPP_CARD_NOTFOUND;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (ReferenceRecordException e) {
			resultCode = ResultCodes.SAVE_REFERENCE_RECORD_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (SaveTongDunBlackException e) {
			resultCode = ResultCodes.TONGDUN_SAVEBLACE_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (TongDunValRulesException e) {
			resultCode = ResultCodes.TONGDUN_VALRULES_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (DayException e) {
			resultCode = ResultCodes.Day_HOURS_EXCEPTOIN;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (Exception e) {
			resultCode = ResultCodes.CREATEAPPPAY_EXCEPTOIN;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} finally {
			ConnManager.closeConn();
		}

		System.out
				.println("耗时[" + (System.currentTimeMillis() - start) + "]ms");
		createAppPayResp.setResultCode(resultCode);
		createAppPayResp.setMonthPayAmount(monthPay);
		createAppPayResp.setMonthInstallMent(installMent);
		createAppPayResp.setAppPayNo(applicationNo);
		createAppPayResp.setLotteryFlag(lotteryFlag);

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
		// if (req.getTranPrice().isEmpty()) {
		// return false;
		// }
		// if (Consts.APPFLOW_TYPE_3C.equals(req.getApplyType())) {
		// return true;
		// }
		// AvailbleCreditQuery creditQuery = new AvailbleCreditQuery();
		// String useAmount = creditQuery.getAvailCredit(req.getUserName(),
		// req.getUuid());
		// if (useAmount != null) {
		// if (new BigDecimal(useAmount).compareTo(new BigDecimal(req
		// .getTranPrice())) >= 0) {
		// return true;
		// }
		// }
		return true;
		// return false;
	}

	/**
	 * 判断用户使用的最低额度不能小于1000
	 * 
	 * @param useAmount
	 * @return
	 */
	public boolean minUseAmount(String useAmount) {
		if (useAmount != null) {
			if (new BigDecimal(useAmount).compareTo(new BigDecimal(100)) >= 0) {
				return true;
			}
		}
		return false;
	}

	public String initApplicationPay(CreateAppPayReq tempReq) throws Exception {

		CreateAppPayQuery appPayQuery = new CreateAppPayQuery();

		/* 创建申请PAY */
		Map<String, Object> appPayMap = getAppPayMap(tempReq, proEntity,
				appPayQuery);
		ApplyPayEvent applyPayEvent = new ApplyPayEvent();
		applyPayEvent.createAppPay(appPayMap);

		/* 创建inputApp */
		String appPayNo = appPayMap.get("app_application_no").toString();
		Map<String, Object> inputPayMap = getInputApp(tempReq, proEntity,
				appPayNo);
		if (!StringUtils.isEmpty(customer.getSpouseMobile())) {
			inputPayMap.put("spouseMobileNo", customer.getSpouseMobile());
		}
		if (!StringUtils.isEmpty(customer.getSpouseName())) {
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

		doubleIdentityValService.withholdPartInsert(tempReq.getUserName(),
				"TDYZ", tempReq.getBankCard());

		/* 将临时表图片信息更新到申请件图片表 */
		updateAppPicInfo(tempReq.getUserName(), appPayNo,
				proEntity.getIndustryCode());

		logger.info("\n#####[" + tempApp.getIndustryCode() + "进件"
				+ tempApp.getApplicationNo() + "]：走跑批自动规则流程开始----");
		saveAutoRuleRecord(tempApp, customer);
		logger.info("\n#####[" + tempApp.getIndustryCode() + "进件"
				+ tempApp.getApplicationNo() + "]：走跑批自动规则流程结束-----");

		// 现金类型冻结额度 根据金额还有用户名修改冻结金额
		if (Consts.APPFLOW_TYPE_CASH.equals(tempReq.getApplyType())) {
			updateUserInfoByUserName(new BigDecimal(tempApp.getApplyAmount()),
					tempReq.getUserName());
		}

		// 保存优惠券记录
		saveCoupon(appPayNo, tempReq);
		
		// 生成loan_loan(建账)
		createLoan(appPayNo, loanProduct, tempReq.getUuid(), tempReq);

		String tempCreateFlag = Consts.FINAL_NUMBER_1;

		/* 更新临时申请表 */
		TempAppInfoEvent tempApplyEvent = new TempAppInfoEvent();
		tempApplyEvent.createAppSuccFlag(tempReq.getUserName(),
				tempReq.getTempAppNo(), appPayNo, tempCreateFlag);

		/*** wangliang-----------2017-4-10-----禁用begin **/
		/** 2016-03-10号上线需求 嗨秒贷产品4000以下（包含）走进件自动规则（批量） */
		/*
		 * Map<Object, Object> map = new HashMap<Object, Object>(); boolean flag
		 * = false; String recordId = "";
		 * 
		 * if(isHmdPriceBurst(tempApp.getIndustryCode(),
		 * tempApp.getApplyAmount())){
		 * 
		 * logger.info("\n#####[嗨秒贷进件" + tempApp.getApplicationNo() +
		 * "]：走跑批自动规则流程");
		 * 
		 * 学生调用鹏远大学生评分 2016-06-07 2016-08-23 去掉,在规则服务统一调用 //
		 * if(Consts.CUSTTYPE_KHL1.equals(tempApp.getCustomerType())){ // //
		 * CustcustomerQuery custcustomerQuery = new CustcustomerQuery(); // //
		 * CustCustomer custCustomer =
		 * custcustomerQuery.queryCustCustomer(applicationPay //
		 * .getUsername()); // // pyFxpfRemote(custCustomer,
		 * tempApp.getApplicationNo()); // }
		 * 
		 * saveAutoRuleRecord(tempApp, customer);
		 * 
		 * }else
		 * if(tempApp.getIndustryCode().equals("LLMD")){//周转哥无金额限制,直接走全自动规则
		 * logger.info("\n#####[蓝领秒贷进件" + tempApp.getApplicationNo() +
		 * "]：走跑批自动规则流程"); saveAutoRuleRecord(tempApp, customer); }else if
		 * (tempApp.getIndustryCode().equals("LDDD")){
		 * 
		 * logger.info("\n#####[滴答贷" + tempApp.getApplicationNo() +
		 * "]：走跑批自动规则流程"); saveAutoRuleRecord(tempApp, customer);
		 * 
		 * }else if (tempApp.getIndustryCode().equals("DDCP")){
		 * 
		 * logger.info("\n#####[滴滴贷" + tempApp.getApplicationNo() +
		 * "]：走跑批自动规则流程"); saveAutoRuleRecord(tempApp, customer);
		 * 
		 * }else if (tempApp.getIndustryCode().equals("VIPD")){
		 * 
		 * logger.info("\n#####[VIP贷" + tempApp.getApplicationNo() +
		 * "]：走跑批自动规则流程"); saveAutoRuleRecord(tempApp, customer);
		 * 
		 * }
		 * 
		 * else{
		 * 
		 * logger.info("\n#####[嗨秒贷进件" + tempApp.getApplicationNo() +
		 * "]：走进件规则流程");
		 * 
		 * // 执行进件规则 saveRunRulesRecord(appPayNo, ""); IntoRules intoRules = new
		 * IntoRules();
		 * 
		 * RulesQuery rulesQuery = new RulesQuery(); recordId =
		 * rulesQuery.queryRechordId(appPayNo);
		 * 
		 * try { //
		 * if(Consts.APP_CUSTOMER_TYPE_KHL1.equals(custCustomer.getCustType())){
		 * // map = intoRules.exeApprRules(appPayNo,recordId); // }else{ // map
		 * = intoRules.exeApprRulesForKHL2(appPayNo,recordId); // } 嗨秒贷规则 map =
		 * intoRules.exeApprRulesForHMD(tempReq,appPayNo, recordId);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * flag = (Boolean) map.get("results");
		 * 
		 * }
		 * 
		 * 
		 * logger.info("\n#####[滴答贷" + flag + "]：标志位为。。。。。。。");
		 * 
		 * 
		 * String tempCreateFlag = Consts.FINAL_NUMBER_1;
		 * 
		 * if (flag) { // 命中规则 取消申请件 ApplicationPayQuery applicationQuery = new
		 * ApplicationPayQuery(); ApplicationPay appay = applicationQuery
		 * .queryApplicationPayById(appPayNo);
		 * appay.setStatus(ProcessConst.STATUS21);
		 * appay.setNode(ProcessConst.NODE12);
		 * appay.setAllNode(ProcessConst.ALL_NODE_GB);
		 * appay.setApprovalProcessEndDate(DateUtils.getDateStr(new Date()));
		 * appay.setApprovalProcessEndName("system");
		 * appay.setApprovalProcessEndnode("S");
		 * appay.setFaceRefause(String.valueOf(map.get("desc")));
		 * appay.setFaceResult("refuse");
		 * appay.setRefuseFlag(String.valueOf(map.get("refuse_flag"))); //0 审批拒
		 * 1 进件拒-聚 2 进件拒-秉 3 进件拒-同 4 进件拒-自 5鹏元风险评分 6鹏元黑名单
		 * 
		 * appay.setMerchAntEndTime(DateUtils.getDateStr(new Date()));
		 * 
		 * createAppPayUpdate.updateApplicationPay(appay);
		 * 
		 * ApproveRecordEvent approveRecordEvent = new ApproveRecordEvent();
		 * approveRecordEvent.updateRecord(recordId); // 这里抛出异常 命中规则 // throw
		 * new RuleValException(); // 临时申请件关闭交易 tempCreateFlag =
		 * Consts.FINAL_NUMBER_2;
		 * 
		 * 发送进件拒绝通知短信 sendValidateCode(customer.getMobileNo(),
		 * customer.getUserName(),customer.getRealName());
		 * 
		 * } else { // 现金类型冻结额度 根据金额还有用户名修改冻结金额 if
		 * (Consts.APPFLOW_TYPE_CASH.equals(tempReq.getApplyType())) {
		 * updateUserInfoByUserName( new BigDecimal(tempApp.getApplyAmount()),
		 * tempReq.getUserName()); }
		 * 
		 * // 生成loan_loan(建账) createLoan(appPayNo, loanProduct,
		 * tempReq.getUuid(), tempReq); // 创建成功发送短信
		 * 
		 * applicationSuccSmscash(custCustomer.getMobileNo(),
		 * custCustomer.getEmailAdress(), createLoanPlanReq.getUserName());
		 * createLoanPlanResp.setResultCode(ResultCodes.NORMAL);
		 * 
		 * // sendHmdSuccess(custCustomer.getMobileNo(), //
		 * custCustomer.getUserName()); 2016新年抽奖活动 每一个用户第一次成功申请嗨秒贷获取一次抽奖机会
		 * if(isLotteryFlag(tempReq.getUserName())){
		 * 
		 * CustUserEvent custEvent = new CustUserEvent();
		 * custEvent.updateUserLotteryNum(tempReq.getUserName()); lotteryFlag =
		 * Consts.FINAL_NUMBER_1; }
		 * 
		 * 更新临时申请表 TempAppInfoEvent tempApplyEvent = new TempAppInfoEvent();
		 * tempApplyEvent.createAppSuccFlag(tempReq.getUserName(),
		 * tempReq.getTempAppNo(), appPayNo, tempCreateFlag);
		 * 
		 * 二次营销老客户直接到初审节点 //
		 * if(Consts.FINAL_NUMBER_1.equals(custUser.getIsDoubleSales())){ //
		 * doubleSalesUserUpdateNode(tempReq, appPayNo); // }
		 * 
		 * }
		 */

		// 创建成功发送短信
		// applicationSuccSmscash(customer.getMobileNo(),customer.getEmailAdress(),tempReq.getUserName());

		// } catch (Exception e) {
		// }
		/*** wangliang-----------2017-4-10-----禁用end---对应前面注释 **/

		return appPayNo;
	}

	private void saveCoupon(String appPayNo, CreateAppPayReq tempReq) {
		if (!StringUtils.isEmpty(tempReq.getCoupon_id())) {

			CouponRecordEvent event = new CouponRecordEvent();
			CouponRecord record = new CouponRecord();
			record.setCoupon_code_id(tempReq.getCoupon_id());
			record.setOrder_id(appPayNo);
			record.setUser_id(customer.getUserName());
			record.setAux_info("创建订单保存优惠券使用记录!");
			event.saveCouponRecord(record);
			logger.info("保存订单保存优惠券使用记录!");
		} else {
			logger.info("优惠券id为空:" + tempReq.getCoupon_id());
		}

	}

	/**
	 * 将申请件插入自动规则记录表
	 * 
	 * @param appPay
	 * @param customer
	 * @throws SaveAutoRuleAppException
	 */
	private void saveAutoRuleRecord(ApplicationPay appPay, CustCustomer customer)
			throws SaveAutoRuleAppException {

		AutoBatchRuleEvent event = new AutoBatchRuleEvent();
		AutoBatchRuleEntity entity = new AutoBatchRuleEntity();

		entity.setAppNo(appPay.getApplicationNo());
		entity.setLoanProduct(appPay.getCreditProductId());
		entity.setApplyAmount(appPay.getApplyAmount());
		entity.setUserName(customer.getUserName());
		entity.setCustType(customer.getCustType());
		entity.setStatus(Consts.FINAL_NUMBER_0); // 为跑批状态

		event.saveAutoRuleApp(entity);

	}

	/**
	 * 是否为嗨秒贷产品 且 申请金额在3000以下
	 * 
	 * @param industryCode
	 * @param applyAmount
	 * @return
	 */
	private boolean isHmdPriceBurst(String industryCode, String applyAmount) {

		if (RegexValidate.isIndustryHmd(industryCode)
				&& new BigDecimal(applyAmount).compareTo(new BigDecimal(
						Consts.HMD_PRICE_BURST_001)) <= 0) {
			return true;
		}

		return false;
	}

	/**
	 * 更新二次营销客户订单状态 2016-01-07
	 * 
	 * @param tempReq
	 * @param appNo
	 * @throws UpdateAppPayException
	 * @throws UpdateTempAppException
	 */
	private void doubleSalesUserUpdateNode(CreateAppPayReq tempReq, String appNo)
			throws UpdateAppPayException, UpdateTempAppException {

		/* 更改申请件节点到初审 */
		ApplyPayEvent appEvent = new ApplyPayEvent();
		appEvent.updateAppNodeToCs(appNo);

		/* 更新临时件状态到审批 */
		TempAppInfoEvent tempAppEvent = new TempAppInfoEvent();
		tempAppEvent.updateTempAppToCs(tempReq.getUserName(),
				tempReq.getTempAppNo(), Consts.FINAL_NUMBER_3);

	}

	/**
	 * 嗨秒贷申请成功 发送短信通知
	 * 
	 * @throws SendMobileMsgException
	 * */
	public void sendHmdSuccess(String mobile, String username) {
		ExternalService externalService = new ExternalService();
		externalService.sendApplyHmdSucc(mobile, username);
	}

	/**
	 * 申请件创建成功，发送短信
	 * 
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
			throws Exception {

		CreateAppPayAssem appPayAssem = new CreateAppPayAssem(appPayQuery);
		/* 获取门店(售点) */
		SaleSiteQuery siteQuery = new SaleSiteQuery();
		SaleSiteEntity siteEntity = siteQuery.querySaleSiteById(tempReq
				.getSiteId());
		if (siteEntity == null) {
			throw new SiteNotFoundException();
		}

		/* 初始化申请件状态信息 未认领件 */
		appPayAssem.initPayNodeAndStatus1();

		// /* 初始化申请件状态信息(芝麻信用) 2016-11-02*/
		// appPayAssem.initPayStatus(proEntity.getIndustryCode(),tempReq.getUserName());

		/* 初始化流水号和产品信息 */
		appPayAssem.initFlowNoAndProduct2(tempReq, proEntity);

		/* 初始化客户信息 */
		appPayAssem.initCustomer3(tempReq.getUserName());

		/* 初始化地域信息 申请件地址(初始默认售点所在地址) 2016-02-23 学生用学校所在地，白领用单位所在地 */
		// appPayAssem.initCurrentCity4(siteEntity.getProvince(),siteEntity.getCity());
		// String appProvince = "";
		// String appCity = "";
		// if(Consts.CUSTTYPE_KHL1.equals(customer.getCustType())){
		// appProvince = customer.getSchoolAreaProvince();
		// appCity = customer.getSchoolAreaCity();
		// }else{
		// // 蓝领用户使用门店地址
		// if(!Consts.FINAL_NUMBER_1.equals(customer.getLanUserFlag())){
		// appProvince = customer.getWorkAreaProvince();
		// appCity = customer.getWorkAreaCity();
		// }else {
		// appProvince = siteEntity.getProvince();
		// appCity = siteEntity.getCity();
		// }
		// }
		// if(StringUtils.isEmpty(appProvince) || StringUtils.isEmpty(appCity)){
		// appProvince = siteEntity.getProvince();
		// appCity = siteEntity.getCity();
		// }
		// logger.info("\n==========城市代码：" + appProvince + "，" + appCity);
		CityParam appCity = getAppCity(siteEntity);
		appPayAssem.initCurrentCity4(appCity.getProvinceCode(),
				appCity.getCityCode());

		/* 加载其他参数 */
		appPayAssem.initAppOtherInfo(tempReq, customer, custUser, proEntity);

		/* 初始化办单人员信息 */
		// appPayAssem.initSaleServer5(approveUser);

		/* 初始化商户售点 */
		appPayAssem.initSupplierApprove(tempReq, siteEntity);

		LoanAmtCalReq loanAmtCalReq = new LoanAmtCalReq();
		loanAmtCalReq.setTranPrice(tempReq.getTranPrice());
		loanAmtCalReq.setFirstRate(Consts.DEAFAULT_RATE);// 不传,默认0首付
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
			RepeatApplyMDException, OneMonthApplyException,
			ApplyNotInOneMonthException, MDNotFoundException,
			DDNotFoundException, DayException {

		ApplicationPayQuery query = new ApplicationPayQuery();
		CustcustomerQuery custcustomerQuery = new CustcustomerQuery();

		// 一个月之内是否有订单被拒绝
		/*
		 * List<ApplicationPay> jjPays =
		 * query.queryAppByBeforeMonth(req.getUserName()); if (jjPays != null &&
		 * jjPays.size() > 0) { throw new OneMonthApplyException(); }
		 */

		/* 是否存在该用户 */
		customer = custcustomerQuery.queryCustCustomer(req.getUserName());
		custUser = queryCustUserByUserName(req.getUserName());
		if (customer == null) {
			throw new CustomerNotFoundException();
		}

		/* 获取商品信息 */
		MerProductQuery merProQuery = new MerProductQuery();
		proEntity = merProQuery.queryMerProByProId(req.getMerProId());
		if (proEntity == null) {
			throw new MerProductNotFoundException();
		}
		// if(proEntity.getIndustryCode().equals("MDCP")||proEntity.getIndustryCode().equals("MDOH")){
		// throw new MDNotFoundException();
		// }

		// if(proEntity.getIndustryCode().equals("DDCP")){
		// if(!(customer.getOtherAdressCity().equals("440100")||customer.getOtherAdressCity().equals("110100")||customer.getOtherAdressCity().equals("310100")||customer.getOtherAdressCity().equals("440300"))){
		// throw new DDNotFoundException();
		// }
		// }

		/* 获取用户临时申请单信息 */
		TempApplyQuery tempAppQuery = new TempApplyQuery();
		tempApplyEntity = tempAppQuery.queryTempApplyByNo(req.getUserName(),
				req.getTempAppNo());

		/**
		 * 申请件是否超过48小时
		 */
		long day = DateUtils.getHoursBetween(tempApplyEntity.getCreateDate(),
				new Date());
		if (day > 48) {
			throw new DayException();
		}

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

		// String applyType = Consts.APPFLOW_TYPE_CASH;
		// if(!Consts.APPFLOW_TYPE_CASH.equalsIgnoreCase(req.getApplyType())){
		// applyType = Consts.APPFLOW_TYPE_3C;
		// pays = query.querySByName3C(req.getUserName());
		// }else{
		// pays = query.querySByName(req.getUserName(),
		// ProcessConst.STATUS01,applyType);
		// }

		/* 是否重复申请 2017-1-23 wangliang 提前至进件处理 */
		/*
		 * List<ApplicationPay> pays =
		 * query.queryExistAppNew(req.getUserName()); if (pays != null &&
		 * pays.size() > 0) { throw new RepeatApplyMDException(); }
		 */

		// if(pays.size()>0){
		// throw new AppExitErrorException();
		// }

		/* 嗨秒贷重复申请验证 */
		// if
		// (Consts.INDUSTRY_LDDD.equals(proEntity.getIndustryCode())||proEntity.getIndustryCode().equals("MDCP")||proEntity.getIndustryCode().equals("MDOH")||proEntity.getIndustryCode().equals("LLMD"))
		// {
		// List<ApplicationPay> appByMdList =
		// query.queryAppByHmdNew(req.getUserName());
		// if (appByMdList != null && appByMdList.size() > 1) {
		// throw new RepeatApplyMDException();
		// }
		// }

		if (Consts.INDUSTRY_LDDD.equals(proEntity.getIndustryCode())) {
			List<ApplicationPay> appByMdList = query.queryAppByHmdNew(req
					.getUserName());
			if (appByMdList != null && appByMdList.size() > 1) {
				throw new RepeatApplyMDException();
			}
		}

		/* 蓝领嗨秒贷只能在用户注册一个月内申请 */
		/*
		 * if(Consts.INDUSTRY_LLMD.equals(proEntity.getIndustryCode())){ long
		 * longTime = DateUtils.getDaysBetween(custUser.getCreateTime(), new
		 * Date()); if(longTime > 30){ throw new ApplyNotInOneMonthException();
		 * } }
		 */

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
			// 2017-01-10去掉学校信息,改为走学信网
			// inputAppPay.setStudentType(custCustomer.getStuType()); // 学生类型
			//
			// inputAppPay.setSchool(custCustomer.getSchool());// 学校
			//
			// inputAppPay.setStudentId(custCustomer.getStudentId());// 学号
			//
			// inputAppPay.setEducational(custCustomer.getEducational());// 在读学历
			//
			// if (custCustomer.getAdmissionTime() == null
			// || custCustomer.getAdmissionTime().equals("")) {
			//
			// } else {
			//
			// inputAppPay.setAdmissionYear(custCustomer.getAdmissionTime()
			// .substring(0, 4));// 入学时间-年
			//
			// inputAppPay.setAdmissionDay(custCustomer.getAdmissionTime()
			// .substring(5, 7));// 入学时间-月
			//
			// }
			//
			// inputAppPay.setFaculties(custCustomer.getFaculties());// 所在院系
			//
			// inputAppPay.setSchoolProv(custCustomer.getSchoolAreaProvince());//
			// 院校--省
			//
			// inputAppPay.setSchoolCity(custCustomer.getSchoolAreaCity());//
			// 院校--市
			//
			// inputAppPay.setSchoolArea(custCustomer.getSchoolAreaArea());//
			// 院校--区
			//
			// inputAppPay.setSchoolAddress(custCustomer.getSchoolAreaRoad());//
			// 院校地址
			//
			// inputAppPay.setSpecialty(custCustomer.getSpecialty());// 专业
			//
			// inputAppPay.setUserGreade(custCustomer.getUserclass());// 用户年级

		} else {

			/* 2016-01-11蓝领新增 start */

			inputAppPay.setLanUserFlag(custCustomer.getLanUserFlag());
			inputAppPay.setInveteCode(custCustomer.getInveteCode());
			inputAppPay.setStoreCode(custCustomer.getStoreCode());

			/* 2016-01-11蓝领新增 end */

			inputAppPay.setEducation(custCustomer.getNowEduCation());// 白领学历

			if (custCustomer.getEndSchoolTime() == null
					|| custCustomer.getEndSchoolTime().equals("")
					|| custCustomer.getEndSchoolTime().length() < 8) {

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

			inputAppPay.setCompanyScale(custCustomer.getCompanyScale());

			inputAppPay.setCompanyWorkYear(custCustomer.getCompanyWorkYear());

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

		/* 2016-03-09 新增民族、身份证有效期 */
		inputAppPay.setNation(custCustomer.getNation());
		inputAppPay.setIdCardValStartDate(custCustomer.getIdCardValStartDate());
		inputAppPay.setIdCardValEndDate(custCustomer.getIdCardValEndDate());
		inputAppPay.setIdCardValidity(custCustomer.getIdCardValidity());
		inputAppPay.setLoanUse(custCustomer.getLoanUse());

		/* 2016-04-20 新增单位规模 单位工作年限 */
		inputAppPay.setCompanyScale(custCustomer.getCompanyScale());
		inputAppPay.setCompanyWorkYear(custCustomer.getCompanyWorkYear());

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
		BigDecimal discount = BigDecimal.ZERO;
		if (!StringUtils.isEmpty(tempReq.getCoupon_id())) {
			Coupon coupon = couponQuery.queryCoupon(tempReq.getCoupon_id());
			if (coupon != null && !StringUtils.isEmpty(coupon.getCoupon())) {
				discount = new BigDecimal(coupon.getCoupon());
			}

		}
		loan.setCoupon(String.valueOf(discount));

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
			int num = createLoanLoanUpdate.createLoan(loan);
			logger.info("创建loan_loan结束num:"+num);
			
			LoanLoan queryLoan = loanloanQuery.queryLoanLoanByAppId(loan
					.getApplicationId());
			LoanLoanRecordEvent loanLoanRecordEvent = new LoanLoanRecordEvent();
			// 取消预授权
			int num2 = loanLoanRecordEvent.createLoanRecord(queryLoan);
			logger.info("创建loan_loan_record结束num2:"+num2);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

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

			req.setTranPrice(tempApplyEntity.getApplyPrice());
			req.setLoanProduct(tempApplyEntity.getLoanProduct());
			req.setImDrainage(tempApplyEntity.getImDrainage());
		}

		return req;
	}

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
		param.setProvince(req.getProvince());
		param.setCity(req.getCity());
		param.setIsDefaultCard(Consts.DEFAULT_CARD_YES);
		//2017-08嗨钱迭代去掉开户网点
//		param.setOpenBankBranch(req.getOpenBankBranch());
		param.setArea(req.getArea());
//		BankBranchEntity en = sysBankInfoQuery.queryBankInfoByNum(req
//				.getOpenBankBranch());
//		if (en != null) {
//			param.setOpenBankBranchName(en.getBankName());
//		} else {
//			param.setOpenBankBranchName("");
//		}
		param.setOpenBankBranchName("");
		cardEvent.updateDefaultCardByApp(req.getUserName());

		if (isExist) {
			cardEvent.updateUserCardByApp(param);
		} else {
			cardEvent.saveUserCardByApp(param);
		}
	}

	/**
	 * 更新申请件图片信息
	 * 
	 * @throws UpdateApplicationPicException
	 */
	public void updateAppPicInfo(String userName, String appNo,
			String industryCode) throws UpdateApplicationPicException {

		if (customer != null) {

			TempApplyPicQuery tempApplyQuery = new TempApplyPicQuery();
			List<TempApplyPicEntity> tempPicList = tempApplyQuery
					.queryTempApplyPic(userName);
			ApplicationPicEvent picEvent = new ApplicationPicEvent();

			if (tempPicList != null && tempPicList.size() > 0) {

				List<PicParam> picList = new ArrayList<PicParam>();

				for (TempApplyPicEntity tempPic : tempPicList) {
					if (picList.size() == 0) {
						logger.info("用户名:" + tempPic.getUserName() + "更新第一张图片");
						PicParam picParam = new PicParam();
						picParam.setAppNo(appNo);
						picParam.setPicType(tempPic.getPicType());
						picParam.setPicName(tempPic.getPicName());
						picParam.setPicPath(tempPic.getPicPath());
						picParam.setThumPath(tempPic.getThumPath());
						picParam.setCaption(tempPic.getPicCaption());
						picList.add(picParam);
					} else {
						boolean flag = true;
						for (int i = 0; i < picList.size(); i++) {
							if (tempPic.getPicType().equals(
									picList.get(i).getPicType())) {
								logger.info("用户名:" + tempPic.getUserName()
										+ "图片重复");
								flag = false;
								break;

							}
						}
						if (flag) {
							PicParam picParam = new PicParam();
							picParam.setAppNo(appNo);
							picParam.setPicType(tempPic.getPicType());
							picParam.setPicName(tempPic.getPicName());
							picParam.setPicPath(tempPic.getPicPath());
							picParam.setThumPath(tempPic.getThumPath());
							picParam.setCaption(tempPic.getPicCaption());
							picList.add(picParam);
						}

					}

				}
				for (PicParam picParam : picList) {
					picEvent.saveAppPic(picParam, userName);
				}

			}

			/* 蓝领嗨秒贷 新增材料 申请人现场照 */
			// if(Consts.INDUSTRY_LLMD.equals(industryCode)){
			// PicParam picParam = new PicParam();
			// picParam.setAppNo(appNo);
			// picParam.setPicType(Consts.IMG_TYPE_ZL01);
			// picParam.setPicName(Consts.IMG_TYPE_ZL01);
			// picParam.setPicPath(customer.getUserScenepicUrl());
			// picParam.setThumPath(customer.getUserScenepicThumUrl());
			// picParam.setCaption("嗨秒贷");
			// picEvent.saveAppPic(picParam, userName);
			// }
		}

	}

	/**
	 * 创建成功后非事物操作
	 * 
	 * @param req
	 */
	private void createSuccAfter(CreateAppPayReq req, String appNo) {

		CustcustomerQuery customerQuery = new CustcustomerQuery();
		CustCustomer custCustomer = customerQuery.queryCustCustomer(req
				.getUserName());

		// 申请成功，发送手机短信 2015-12-23更改 Cary.Liu
		NoticeSendParam param = new NoticeSendParam();
		param.setMobile(custCustomer.getMobileNo());
		param.setUserName(custCustomer.getUserName());
		param.setRealName(custCustomer.getRealName());
		param.setCustType(custCustomer.getCustType());
		param.setIndustryCode(proEntity.getIndustryCode());
		param.setAppNo(appNo);
		sendSuccNotice(param);

		/* 调用91征信报告 */
		// if(is91ZxRemoteFlag(proEntity.getIndustryCode(),
		// custCustomer.getCustType())){
		// remoteHttpBy91zx(custCustomer.getRealName(),
		// custCustomer.getIdentityNo());
		// }

	}

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userName
	 * @return
	 */
	public CustUserEntity queryCustUserByUserName(String userName) {
		CustUserQuery custUserQuery = new CustUserQuery();
		return custUserQuery.queryByUserName(userName);
	}

	/**
	 * 是否给予用户抽奖机会
	 * 
	 * @return
	 */
	private boolean isLotteryFlag(String userName) {

		CustUserEntity custUser = queryCustUserByUserName(userName);

		if (custUser != null) {

			/* 是否有过抽奖记录 */
			NewYearLotterQuery lotQuery = new NewYearLotterQuery();
			List<NewYearLotteryEntity> list = lotQuery.queryUserlotteryRecord(
					userName, Consts.FINAL_NUMBER_2);

			/* 当用户 没有抽过奖& 抽奖剩余次数为1 & 没有抽奖记录 */
			if (custUser.getIsLottery() == 0 && custUser.getLotteryNum() > 0
					&& (list == null || list.size() == 0)) {
				return true;
			}

			/* 当用户 没有抽过奖 & 抽奖剩余次数为0 & 没有抽奖记录 */
			if (custUser.getIsLottery() == 0 && custUser.getLotteryNum() == 0
					&& (list == null || list.size() == 0)) {
				return true;
			}

		}

		return false;
	}

	/**
	 * 是否调用91征信
	 * 
	 * @param industryCode
	 * @param custType
	 * @return
	 */
	private boolean is91ZxRemoteFlag(String industryCode, String custType) {

		/*
		 * CYCL 乘用车辆分期 GDYY 营运车辆分期 ESYC 二手乘用车分期 YZKH 优质行业客户分期 FCKH 房产客户分期 GXKH
		 * 高薪客户分期 ELWZ 微整形分期 YLHY 医疗整形行业
		 */

		if ("CYCL".equals(industryCode) || "GDYY".equals(industryCode)
				|| "ESYC".equals(industryCode) || "YZKH".equals(industryCode)
				|| "FCKH".equals(industryCode) || "GXKH".equals(industryCode)) {
			return true;
		}
		/* 白领嗨美贷 */
		if (("ELWZ".equals(industryCode) || "YLHY".equals(industryCode))
				&& Consts.CUSTTYPE_KHL2.equals(custType)) {
			return true;
		}

		return false;
	}

	/**
	 * 91征信调用
	 * 
	 * @param industryCode
	 * @param realName
	 * @param idCard
	 */
	private void remoteHttpBy91zx(String realName, String idCard) {

		RemoteService remoteService = new RemoteService();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("realName", realName);
		params.put("idCard", idCard);

		try {

			String resultStr = remoteService.remoteHttpAmt(
					Consts.SERVICE_91ZX1, params);

			logger.info("\n***[91征信报告接口[姓名：" + realName + "，身份证：" + idCard
					+ "]调用结果：" + resultStr);

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("[91征信报告 - 接口调用异常]\n" + e.getMessage());
		}

	}

	/**
	 * 发送申请拒绝手机短信
	 * */
	public void sendValidateCode(String mobile, String username, String realName) {

		ExternalService externalService = new ExternalService();
		externalService.sendApplyAppFailSms(mobile, username, realName);
	}

	private CityParam getAppCity(SaleSiteEntity siteEntity) {

		logger.info("\n#####[蓝领嗨秒贷城市区分]门店号码：" + customer.getStoreCode() + "--"
				+ customer.getCustType());

		CityParam param = new CityParam();
		String appProvince = "";
		String appCity = "";
		String appArea = "";

		if (Consts.CUSTTYPE_KHL1.equals(customer.getCustType())) {
			// 学生-学校地址----2017-01-19没有学校地址了，取现居住地址
			appProvince = customer.getOtherAdressProvince();
			appCity = customer.getOtherAdressCity();
		} else {

			if (!Consts.FINAL_NUMBER_1.equals(customer.getLanUserFlag())) {
				// 白领-单位地址
				appProvince = customer.getWorkAreaProvince();
				appCity = customer.getWorkAreaCity();
			} else {

				logger.info("\n#####[蓝领嗨秒贷城市区分]门店号码：" + customer.getStoreCode());

				/* 蓝领-门店地址 */
				SupStoreQuery storeQuery = new SupStoreQuery();
				SupStoreEntity supStore = storeQuery
						.querySupStoreByStoreNo(customer.getStoreCode());

				if (supStore != null) {

					appProvince = new CityQuery().queryProvinceCode(supStore
							.getCity());
					appCity = supStore.getCity();
				} else {

					appProvince = siteEntity.getProvince();
					appCity = siteEntity.getCity();
				}

			}
		}

		param.setProvinceCode(appProvince);
		param.setCityCode(appCity);
		param.setAreaCode(appArea);

		return param;
	}

	/**
	 * 鹏远大学生贷款风险评分
	 * 
	 * @param customer
	 * @param appNo
	 * @return 0： 评分合格 1 ：客户属于鹏元征信D类客户
	 * @throws HttpException
	 * @throws IOException
	 * @throws HttpReturnException
	 * @throws HttpUrlRemoteException
	 */
	private int pyFxpfRemote(CustCustomer customer, String appNo) {

		int i = 0;

		/* 参数 */
		String admissionTime = customer.getAdmissionTime(); // 入学时间 格式2015-01
		String[] admissionTimeArr = admissionTime.split("-");
		String universityName = customer.getSchool();
		String educationalName = queryParamNameByDicCode(customer
				.getEducational());
		String fulltime = customer.getFullTimeFlag();
		// if("KHL1".equals(customer.getCustType())){
		// universityName = customer.getSchool();
		// }else{
		// universityName = customer.getGraduatedSchool(); // 白领毕业学校
		// }
		if ("博士".equals(educationalName) || "硕士".equals(educationalName)) {
			educationalName += "研究生";
		}

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("documentno", customer.getIdentityNo());
		params.put("name", customer.getRealName());
		params.put("applicationno", appNo);
		params.put(
				"starttime",
				(admissionTimeArr != null && admissionTimeArr.length > 0) ? admissionTimeArr[0]
						: "");
		params.put("college", universityName);
		params.put("degree", educationalName);
		params.put("fulltime",
				("SAS1".equals(fulltime) || "全日制".equals(fulltime)) ? "是" : "否");
		params.put("applymoney", "5000");

		RemoteService remoteService = new RemoteService();

		try {

			String resultStr = remoteService.remoteHttpAmt(
					Consts.SERVICE_PY001, params);

			PyFxpfRemoteResp resp = new Gson().fromJson(resultStr,
					PyFxpfRemoteResp.class);

			if ("refuse".equals(resp.getStatus())) {
				i = 1;
			}

			logger.info("***[身份证 " + customer.getIdentityNo() + "，申请件" + appNo
					+ "]鹏远风险评分接口调用结果：" + resp.getStatus() + "，" + resp.getMsg());

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("[鹏远大学生贷款风险评分 - 接口调用异常]\n" + e.getMessage());
		}

		return i;
	}

	/**
	 * 获取系统参数名称
	 * 
	 * @param dicCode
	 * @return
	 */
	public String queryParamNameByDicCode(String dicCode) {

		SystemParamQuery query = new SystemParamQuery();
		SystemParamEntity param = query.queryParamByDicCode(dicCode);

		if (param != null) {
			return StringUtils.getString(param.getDicName());
		}

		return "";
	}
}
