package com.hengyuan.hicash.domain.rule;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.apply.ApproveRecordEvent;
import com.hengyuan.hicash.domain.event.apply.RuleRunResultEvent;
import com.hengyuan.hicash.domain.event.user.BlackCertNoUpdateEvent;
import com.hengyuan.hicash.domain.query.app.ApplicationPayQuery;
import com.hengyuan.hicash.domain.query.app.InputAppPayQuery;
import com.hengyuan.hicash.domain.query.param.SystemParamQuery;
import com.hengyuan.hicash.domain.query.rule.RulesQuery;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.service.remote.RemoteService;
import com.hengyuan.hicash.domain.service.user.TongDunValRulesService;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.app.InputAppPay;
import com.hengyuan.hicash.entity.app.RulesRunResult;
import com.hengyuan.hicash.entity.mktApp.ApprovalRecord;
import com.hengyuan.hicash.entity.param.SystemParamEntity;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.exception.SaveTongDunBlackException;
import com.hengyuan.hicash.exception.TongDunValRulesException;
import com.hengyuan.hicash.exception.UpdateAmountException;
import com.hengyuan.hicash.exception.UpdateRulesResultFaildException;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppPayReq;
import com.hengyuan.hicash.parameters.request.user.TongDunValRulesReq;
import com.hengyuan.hicash.parameters.response.param.FraudApiLoanResp;
import com.hengyuan.hicash.parameters.response.user.BlackCertNoUpdateResp;
import com.hengyuan.hicash.parameters.response.user.PyFxpfRemoteResp;
import com.hengyuan.hicash.parameters.response.user.TongDunValRulesResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RuleUtils;
import com.hengyuan.hicash.utils.Rules;
import com.hengyuan.hicash.utils.RulesRuslt;
import com.hengyuan.hicash.utils.StringUtils;

public class IntoRules {

	private static Logger logger = Logger.getLogger(IntoRules.class);
	
	private ApplicationPay applicationPay;

	private InputAppPay inputAppPay;

	private CustCustomer custCustomer;

	private String hits = Consts.hits;// 命中

	private String unhits = Consts.unhits;// 未命中

	private RuleRunResultEvent ruleRunResultEvent = new RuleRunResultEvent();

	public Map<Object, Object> exeApprRules(String appNo, String recordId)
			throws UpdateRulesResultFaildException {

		ApplicationPayQuery applicationQuery = new ApplicationPayQuery();

		InputAppPayQuery inputAppPayQuery = new InputAppPayQuery();

		applicationPay = applicationQuery.queryApplicationPayById(appNo);

		inputAppPay = inputAppPayQuery.queryInputAppByAppNo(appNo);

		CustcustomerQuery custcustomerQuery = new CustcustomerQuery();

		custCustomer = custcustomerQuery.queryCustCustomer(applicationPay
				.getUsername());

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("results", false);
		map.put("desc", "");
		map.put("refuse_flag", "");// 0 审批拒 1 进件拒-聚 2 进件拒-秉 3 进件拒-同 4 进件拒-自

		// 如果是嗨秒贷产品
		if (Consts.INDUSTRY_MDCP.equals(applicationPay.getIndustryCode()) || Consts.INDUSTRY_MDOH.equals(applicationPay.getIndustryCode())) {

			// 嗨秒贷年龄规则，18到35岁(包含)
			if (hits.equals(ageRuleForKHL2(18, recordId, 35))) {
				map.put("results", true);
				map.put("desc", "411");
				map.put("refuse_flag", "4");
				return map;
			}

			// 学历规则
			if (hits.equals(educationRule(recordId))) {
				map.put("results", true);
				map.put("desc", "416");
				map.put("refuse_flag", "4");
				return map;
			}
		} else {
			// 年龄规则 18岁以上
			if (hits.equals(ageRule(18, recordId))) {
				map.put("results", true);
				map.put("desc", "411");
				map.put("refuse_flag", "4");
				return map;
			}
		}

		// 身份证规则
		if (hits.equals(identityRule(recordId))) {
			map.put("results", true);
			map.put("desc", "221");
			if(bcIdentityFlag == 0){
				map.put("refuse_flag", "4"); // 命中本地黑名单
			} else{
				map.put("refuse_flag", "2"); // 命中秉承黑名单
			}
			return map;
		}

		// 手机号规则
		if (hits.equals(mobileRule(recordId))) {
			map.put("results", true);
			map.put("desc", "221");
			map.put("refuse_flag", "4");
			return map;
		}

		// 逾期规则
		if (hits.equals(overdueRule(recordId))) {
			map.put("results", true);
			map.put("desc", "241");
			map.put("refuse_flag", "4");
			return map;
		}

		// 学校黑名单
		if (hits.equals(schoolRule(recordId))) {
			map.put("results", true);
			map.put("desc", "221");
			map.put("refuse_flag", "4");
			return map;
		}

		return map;

	}

	/**
	 * 白领进件规则
	 * 
	 * @param appNo
	 * @param recordId
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	public Map<Object, Object> exeApprRulesForKHL2(String appNo, String recordId)
			throws UpdateRulesResultFaildException {

		ApplicationPayQuery applicationQuery = new ApplicationPayQuery();

		InputAppPayQuery inputAppPayQuery = new InputAppPayQuery();

		applicationPay = applicationQuery.queryApplicationPayById(appNo);

		inputAppPay = inputAppPayQuery.queryInputAppPay(appNo);

		CustcustomerQuery custcustomerQuery = new CustcustomerQuery();

		// ProductInfoQuery proQuery = new ProductInfoQuery();

		// ProductInfo proEntity =
		// proQuery.queryProductInfoById(applicationPay.getProductInfo());

		custCustomer = custcustomerQuery.queryCustCustomer(applicationPay
				.getUsername());

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("results", false);
		map.put("desc", "");
		map.put("refuse_flag", "");// 0 审批拒 1 进件拒-聚 2 进件拒-秉 3 进件拒-同 4 进件拒-自

		// 学历规则 专科以下自动拒绝
		// 白领婚庆服务，月子服务，装修服务不需要验证学历
		// if(Consts.APPFLOW_TYPE_CASH.equals(applicationPay.getPayType())){
		// if(hits.equals(educationRuleForKHL2(recordId))){
		// map.put("results", true);
		// map.put("desc", "416");
		// return map;
		// }
		// }

		// 如果是嗨秒贷产品
		if (Consts.INDUSTRY_MDCP.equals(applicationPay.getIndustryCode())  || Consts.INDUSTRY_MDOH.equals(applicationPay.getIndustryCode())) {

			// 嗨秒贷年龄规则，18到35岁(包含)
			if (hits.equals(ageRuleForKHL2(18, recordId, 35))) {
				map.put("results", true);
				map.put("desc", "411");
				map.put("refuse_flag", "4");
				return map;
			}

			// 学历规则 专科以下自动拒绝
			if (hits.equals(educationRuleForKHL2(recordId))) {
				map.put("results", true);
				map.put("desc", "416");
				map.put("refuse_flag", "4");
				return map;
			}
		} else {
			// 年龄规则，18到60岁(包含)
			if (hits.equals(ageRuleForKHL2(18, recordId, 60))) {
				map.put("results", true);
				map.put("desc", "411");
				map.put("refuse_flag", "4");
				return map;
			}

		}

		// 开放城市规则
		/*
		 * if(hits.equals(openCityRule(recordId))){ map.put("results", true);
		 * map.put("desc", "423"); return map; }
		 */

		// 身份证规则
		if (hits.equals(identityRule(recordId))) {
			map.put("results", true);
			map.put("desc", "221");
			if(bcIdentityFlag == 0){
				map.put("refuse_flag", "4"); // 命中本地黑名单
			} else{
				map.put("refuse_flag", "2"); // 命中秉承黑名单
			}
			return map;
		}

		// 手机号规则
		if (hits.equals(mobileRule(recordId))) {
			map.put("results", true);
			map.put("desc", "221");
			map.put("refuse_flag", "4");
			return map;
		}

		// 逾期规则
		if (hits.equals(overdueRule(recordId))) {
			map.put("results", true);
			map.put("desc", "241");
			map.put("refuse_flag", "4");
			return map;
		}

		return map;
	}

	/**
	 * 白领年龄规则
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	private String ageRuleForKHL2(int agePar, String recordId, int maxage)
			throws UpdateRulesResultFaildException {
		String str = unhits;
		int age = RuleUtils.getAppUseAge(custCustomer.getIdentityNo());

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0007");
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);
		if (age < agePar || age > maxage) {
			runResult.setRunResult(RulesRuslt.Y.toString());
			str = hits;
			runResult.setRunRsRecord("申请人当前年龄" + age + "命中规则，拒绝");
		} else {
			runResult.setRunRsRecord("申请人当前年龄" + age + "未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);

		return str;
	}

	/**
	 * 年龄规则-嗨秒贷
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	private String ageRuleForHMD(int agePar, String recordId, int maxage)
			throws UpdateRulesResultFaildException {
		String str = unhits;
		int age = RuleUtils.getAppUseAge(custCustomer.getIdentityNo());

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0012"); // RULEA0012，RULEA0007
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);
		if (age < agePar || age > maxage) {
			runResult.setRunResult(RulesRuslt.Y.toString());
			str = hits;
			runResult.setRunRsRecord("申请人当前年龄" + age + "命中规则，拒绝");
		} else {
			runResult.setRunRsRecord("申请人当前年龄" + age + "未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);

		return str;
	}
	
	/**
	 * 申请金额规则-嗨秒贷
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	private String priceRuleForHMD(BigDecimal eqPrice, String recordId)
			throws UpdateRulesResultFaildException {
		String str = unhits;

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0032");//Rules.RULEA0032
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);
		logger.info("申请金额:"+applicationPay.getApplyAmount());
		logger.info("比较结果："+(eqPrice.compareTo(new BigDecimal(applicationPay.getApplyAmount())) == 0));
		if (eqPrice.compareTo(new BigDecimal(applicationPay.getApplyAmount())) == 0) {
			runResult.setRunResult(RulesRuslt.Y.toString());
			str = hits;
			runResult.setRunRsRecord("申请人所申请金额" + applicationPay.getApplyAmount() + "命中规则，拒绝");
		} else {
			runResult.setRunRsRecord("申请人所申请金额" + applicationPay.getApplyAmount() + "未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);

		return str;
	}
	

	/**
	 * 白领学历规则
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	public String educationRuleForKHL2(String recordId)
			throws UpdateRulesResultFaildException {
		String str = unhits;

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0008");
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);

		if (Consts.EDUCATIONAL_6.equals(custCustomer.getNowEduCation())
				|| Consts.EDUCATIONAL_1.equals(custCustomer.getNowEduCation())) {
			str = hits;
			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("申请人当前学历" + custCustomer.getNowEduCation()
					+ ",命中规则，拒绝");
		} else {
			runResult.setRunRsRecord("申请人当前学历" + custCustomer.getNowEduCation()
					+ ",未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);
		return str;
	}

	/**
	 * 年龄规则
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	private String ageRule(int agePar, String recordId)
			throws UpdateRulesResultFaildException {
		String str = unhits;
		int age = RuleUtils.getAppUseAge(custCustomer.getIdentityNo());

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0001");
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);
		if (age < agePar) {
			runResult.setRunResult(RulesRuslt.Y.toString());
			str = hits;
			runResult.setRunRsRecord("申请人当前年龄" + age + "命中规则，拒绝");
		} else {
			runResult.setRunRsRecord("申请人当前年龄" + age + "未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);

		return str;
	}

	/**
	 * 学历规则
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	public String educationRule(String recordId)
			throws UpdateRulesResultFaildException {
		String str = unhits;

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0002");
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);

		// 原版：如果是专科且年龄大于24拒绝，或者专科以下拒绝；2015-10-21改成：专科以下拒绝
		// if((Consts.EDUCATIONAL_2.equals(custCustomer.getEducational()) &&
		// RuleUtils.getAppUseAge(custCustomer.getIdentityNo())>24) ||
		// Consts.EDUCATIONAL_1.equals(custCustomer.getEducational())){
		if (Consts.EDUCATIONAL_6.equals(custCustomer.getEducational())
				|| Consts.EDUCATIONAL_1.equals(custCustomer.getEducational())) {
			str = hits;
			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("申请人当前学历" + custCustomer.getEducational()
					+ "（" + queryDicName(custCustomer.getEducational())
					+ "），当前年龄"
					+ RuleUtils.getAppUseAge(custCustomer.getIdentityNo())
					+ "命中规则，拒绝");
		} else {
			runResult.setRunRsRecord("申请人当前学历" + custCustomer.getEducational()
					+ "（" + queryDicName(custCustomer.getEducational())
					+ "），当前年龄"
					+ RuleUtils.getAppUseAge(custCustomer.getIdentityNo())
					+ "未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);
		return str;
	}

	/** 是否命中秉承黑名单 0：命中本地黑名单，1：命中秉承黑名单 */
	private int bcIdentityFlag = 0;
	
	/**
	 * 身份证规则
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	public String identityRule(String recordId)
			throws UpdateRulesResultFaildException {
		String str = unhits;
		RulesQuery RulesQuery = new RulesQuery();

		// List<String> list =
		// RulesQuery.queryidentityNo(custCustomer.getIdentityNo());

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0003");
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);
		// 0：未命中、1：命中本地黑名单，2：命中远程黑名单、3：身份证为空
		// if(list!=null && !list.isEmpty()){
		int i = isBlackIdCard(custCustomer.getIdentityNo());
		if (i == 1) {
			str = hits;
			bcIdentityFlag = 0;
			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("申请人当前身份证号" + custCustomer.getIdentityNo()
					+ "命中规则[本地]，拒绝");
		} else if (i == 2) {
			str = hits;
			bcIdentityFlag = 1;
			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("申请人当前身份证号" + custCustomer.getIdentityNo()
					+ "命中规则[秉承]，拒绝");
		} else if (i == 3) {
			str = hits;
			bcIdentityFlag = 0;
			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("申请人当前身份证号" + custCustomer.getIdentityNo()
					+ "为空，拒绝");
		} else {
			runResult.setRunRsRecord("申请人当前身份证号" + custCustomer.getIdentityNo()
					+ "未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);
		return str;

	}
	
	/**
	 * 鹏元 
	 * 大学生贷款风险评分
	 * @return
	 * @throws UpdateRulesResultFaildException 
	 */
	public String pxFxpfRule(String recordId) throws UpdateRulesResultFaildException {
		String str = unhits;

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0022");
		runResult.setApplicationNo(Long.parseLong(inputAppPay.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);
		// 0：正常、1： 有风险
		int i = pyFxpfRemote(custCustomer);
		if (i == 1) {
			str = hits;
			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("客户属于鹏元征信D类客户，拒绝");
		} else {
			runResult.setRunRsRecord("风险评分正常，未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);
		return str;

	}
	
	/**
	 * 鹏元 黑名单
	 * 个人风险汇总查询规则
	 * @return
	 * @throws UpdateRulesResultFaildException 
	 */
	public String pxBlackRule(String recordId) throws UpdateRulesResultFaildException {
		String str = unhits;

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0023");
		runResult.setApplicationNo(Long.parseLong(inputAppPay.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);
		// 0：正常、1： 黑名单
		int i = pyBlackRemote(custCustomer);
		if (i == 1) {
			str = hits;
			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("客户属于鹏元征信黑名单客户，拒绝");
		} else {
			runResult.setRunRsRecord("客户非鹏元征信黑名单，未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);
		return str;

	}

	/**
	 * 身份证归属地3508、3509
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	public String identityAddressRule(String recordId)
			throws UpdateRulesResultFaildException {
		String str = unhits;

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0019");
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);

		// 身份证归属地为3508、3509
		if (custCustomer.getIdentityNo().startsWith("3508")
				|| custCustomer.getIdentityNo().startsWith("3509")) {
			str = hits;
			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("申请人当前身份证号" + custCustomer.getIdentityNo()
					+ "，归属地为[3508或者3509]命中规则，拒绝");
		} else {
			runResult.setRunRsRecord("申请人当前身份证号" + custCustomer.getIdentityNo()
					+ "归属地未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);
		return str;

	}

	/**
	 * 手机号规则
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	public String mobileRule(String recordId)
			throws UpdateRulesResultFaildException {

		String str = unhits;
		RulesQuery RulesQuery = new RulesQuery();
		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0004");
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);
		List<String> list = RulesQuery.queryMobile(custCustomer.getMobileNo());
		if (list != null && !list.isEmpty()) {
			str = hits;
			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("申请人当前手机号" + custCustomer.getMobileNo()
					+ "命中规则，拒绝");
		} else {
			runResult.setRunRsRecord("申请人当前手机号" + custCustomer.getMobileNo()
					+ "未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);
		return str;
	}

	/**
	 * 逾期规则
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	public String overdueRule(String recordId)
			throws UpdateRulesResultFaildException {
		String str = unhits;
		RulesQuery RulesQuery = new RulesQuery();

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0006");
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);
		List<String> list = RulesQuery.queryBlockCode(custCustomer
				.getIdentityNo());
		if (list != null && !list.isEmpty()) {
			str = hits;
			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("申请人当前逾期期数" + list.get(0) + "命中规则，拒绝");
		} else {
			runResult.setRunRsRecord("申请人当前逾期期数未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);
		return str;
	}

	/**
	 * 逾期规则-客户当前逾期
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	public String currOverdueRule(String recordId)
			throws UpdateRulesResultFaildException {
		String str = unhits;
		RulesQuery RulesQuery = new RulesQuery();

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0013");
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);
		List<String> list = RulesQuery.queryBlockCode(
				custCustomer.getIdentityNo(), 1);
		if (list != null && !list.isEmpty()) {
			str = hits;
			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("申请人当前逾期期数" + list.get(0) + "命中规则，拒绝");
		} else {
			runResult.setRunRsRecord("申请人当前逾期期数未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);
		return str;
	}

	/**
	 * 逾期规则-客户历史逾期 且 产生滞纳金次数>5
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	public String historyOverdueAndZnjCountRule(String recordId, String userName)
			throws UpdateRulesResultFaildException {
		String str = unhits;
		RulesQuery RulesQuery = new RulesQuery();

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0015");
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);
		// 历史逾期
		List<String> list = RulesQuery.queryHistoryOverdue(
				custCustomer.getIdentityNo(), 1);
		// 滞纳金次数
		String znjCount = RulesQuery.queryZnjCount(userName);

		if ((list != null && !list.isEmpty())
				&& StringUtils.getInt(znjCount) > 5) {
			str = hits;
			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("申请人历史逾期" + list.get(0)
					+ "期，并且产生滞纳金次数大于5次，命中规则，拒绝");
		} else {
			runResult.setRunRsRecord("申请人未历史逾期，或者产生滞纳金次数未大于5次，未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);
		return str;
	}

	/**
	 * 逾期规则-客户历史逾期>M2
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	public String historyOverdueRule(String recordId)
			throws UpdateRulesResultFaildException {
		String str = unhits;
		RulesQuery RulesQuery = new RulesQuery();

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0014");
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);
		List<String> list = RulesQuery.queryHistoryOverdue(
				custCustomer.getIdentityNo(), 2);
		if (list != null && !list.isEmpty()) {
			str = hits;
			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("申请人历史逾期期数" + list.get(0) + "命中规则，拒绝");
		} else {
			runResult.setRunRsRecord("申请人历史逾期期数未命中规则，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);
		return str;
	}

	/**
	 * 学校黑名单
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 */
	public String schoolRule(String recordId)
			throws UpdateRulesResultFaildException {

		String str = unhits;
		RulesQuery RulesQuery = new RulesQuery();

		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0005");
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);
		List<String> list = RulesQuery.querySchool(custCustomer.getSchoolId());
		if (list != null && !list.isEmpty()) {
			str = hits;
			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("申请人学校"
					+ RulesQuery.querySchoolName(custCustomer.getSchoolId())
					+ "命中黑名单，拒绝");
		} else {
			runResult.setRunRsRecord("申请人学校"
					+ RulesQuery.querySchoolName(custCustomer.getSchoolId())
					+ "未命中黑名单，通过");
		}

		ruleRunResultEvent.createRulesRunResult(runResult);
		return str;
	}

	/**
	 * 嗨秒贷进件规则
	 * 
	 * @param appNo
	 * @param recordId
	 * @return
	 * @throws UpdateRulesResultFaildException
	 * @throws TongDunValRulesException 
	 * @throws SaveTongDunBlackException 
	 */
	public Map<Object, Object> exeApprRulesForHMD(CreateAppPayReq req, String appNo, String recordId)
			throws UpdateRulesResultFaildException, SaveTongDunBlackException, TongDunValRulesException {

		ApplicationPayQuery applicationQuery = new ApplicationPayQuery();

		InputAppPayQuery inputAppPayQuery = new InputAppPayQuery();

		applicationPay = applicationQuery.queryApplicationPayById(appNo);

		inputAppPay = inputAppPayQuery.queryInputAppPay(appNo);

		CustcustomerQuery custcustomerQuery = new CustcustomerQuery();

		// ProductInfoQuery proQuery = new ProductInfoQuery();

		// ProductInfo proEntity =
		// proQuery.queryProductInfoById(applicationPay.getProductInfo());

		custCustomer = custcustomerQuery.queryCustCustomer(applicationPay
				.getUsername());

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("results", false);
		map.put("desc", "");
		map.put("refuse_flag", "");// 0 审批拒 1 进件拒-聚 2 进件拒-秉 3 进件拒-同 4 进件拒-自 5鹏元风险评分 6鹏元黑名单
		if (Consts.CUSTTYPE_KHL1.equals(custCustomer.getCustType())) {
		// 嗨秒贷年龄规则，18到30岁(包含)
		if (hits.equals(ageRuleForHMD(18, recordId, 30))) {
			map.put("results", true);
			map.put("desc", "411");
			map.put("refuse_flag", "4");
			return map;
		}
		}else if(Consts.CUSTTYPE_KHL2.equals(custCustomer.getCustType())){
			// 嗨秒贷年龄规则，18到35岁(包含)
			if (hits.equals(ageRuleForHMD(18, recordId, 35))) {
				map.put("results", true);
				map.put("desc", "411");
				map.put("refuse_flag", "4");
				return map;
			}	
		}
		
		// 2016-04-19  针对嗨秒贷业务，所有申请8000元金额的申请件全部做系统拒绝
		//系统拒绝原因是：分期金额或分期期数不符
		/*BigDecimal price = new BigDecimal(8000.00);
		if (hits.equals(priceRuleForHMD(price, recordId))) {
			map.put("results", true);
			map.put("desc", "730");
			map.put("refuse_flag", "4");
			return map;
		}*/

		/* 学历规则 专科以下自动拒绝  2015-11-13cary.liu 需求取消学历验证*/
		if (Consts.CUSTTYPE_KHL1.equals(custCustomer.getCustType())) {
			// 学生
			if (hits.equals(educationRule(recordId))) {
				map.put("results", true);
				map.put("desc", "416");
				map.put("refuse_flag", "4");
				return map;
			}
			
			/* 鹏元风险评分 */
			if (hits.equals(pxFxpfRule(recordId))) {
				map.put("results", true);
				map.put("desc", "416");
				map.put("refuse_flag", "5");
				return map;
			}
			
			/* 鹏元黑名单查询 */
			if (hits.equals(pxBlackRule(recordId))) {
				map.put("results", true);
				map.put("desc", "416");
				map.put("refuse_flag", "6");
				return map;
			}
			
			
		} else {
			// 白领 2015-09-15 白领取消学历验证 、2015-11-13cary.liu 需求再次屏蔽白领学历验证、2015-11-24再次验证
//			if (hits.equals(educationRuleForKHL2(recordId))) {
//				map.put("results", true);
//				map.put("desc", "416");
//				map.put("refuse_flag", "4");
//				
//				return map;
//			}
		}

		// 身份证规则
		if (hits.equals(identityRule(recordId))) {
			map.put("results", true);
			map.put("desc", "221");
			if(bcIdentityFlag == 0){
				map.put("refuse_flag", "4"); // 命中本地黑名单
			} else{
				map.put("refuse_flag", "2"); // 命中秉承黑名单
			}
			
			return map;
		}

		// 身份证归属地
		if (hits.equals(identityAddressRule(recordId))) {
			map.put("results", true);
			map.put("desc", "221");
			map.put("refuse_flag", "4");
			return map;
		}

		// 手机号规则
		if (hits.equals(mobileRule(recordId))) {
			map.put("results", true);
			map.put("desc", "221");
			map.put("refuse_flag", "4");
			return map;
		}

		// 逾期规则
		// if (hits.equals(overdueRule(recordId))) {
		// map.put("results", true);
		// map.put("desc", "241");
		// return map;
		// }

		// 逾期规则-客户当前逾期
		if (hits.equals(currOverdueRule(recordId))) {
			map.put("results", true);
			map.put("desc", "241");
			map.put("refuse_flag", "4");
			return map;
		}

		// 逾期规则-客户历史逾期>M2
		if (hits.equals(historyOverdueRule(recordId))) {
			map.put("results", true);
			map.put("desc", "241");
			map.put("refuse_flag", "4");
			return map;
		}

		// 逾期规则-客户历史逾期 且 产生滞纳金次数>5
		if (hits.equals(historyOverdueAndZnjCountRule(recordId,
				applicationPay.getUsername()))) {
			map.put("results", true);
			map.put("desc", "241");
			map.put("refuse_flag", "4");
			return map;
		}
		// 同盾规则
		if (hits.equals(tongDunRule(recordId, custCustomer))) {
			map.put("results", true);
			map.put("desc", "221");
			map.put("refuse_flag", "3");
			return map;
		}
		
		/* 执行同盾验证  2015-12-10*/
//		if(req.getIsTdValidate() != null && Consts.FINAL_NUMBER_1.equals(req.getIsTdValidate())){
//			tdValidate(req.getTdToken(), custCustomer.getRealName(), custCustomer.getMobileNo(), custCustomer.getIdentityNo(),custCustomer.getEmailAdress(),custCustomer.getQqNumber());
//		}
		
		return map;
	}
	
	/**
	 * 同盾验证
	 * @param name 用户姓名
	 * @param mobile 手机号码
	 * @param identityNo 身份证号码
	 */
	public void tdValidate(String token,String name, String mobile, String identityNo,String email,String qq){
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token_id", token);
		params.put("account_name", name);
		params.put("account_mobile", mobile);
		params.put("id_number", identityNo);

	
		params.put("account_email",email);
		params.put("qq_numbber", qq);
		
		
		RemoteService remoteService = new RemoteService();
		try {
			
			String resultStr = remoteService.remoteHttpAmt(Consts.SERVICE_TD001, params);
			
			FraudApiLoanResp resp = new Gson().fromJson(resultStr, FraudApiLoanResp.class);
			
			logger.info("\n同盾验证结果：【status："+ resp.getStatus() +",msg："+ resp.getMsg() +"】");
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (HttpReturnException e) {
			e.printStackTrace();
		} catch (HttpUrlRemoteException e) {
			e.printStackTrace();
		}
		
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
	 * 身份证是否命中黑名单 0：未命中、1：命中本地黑名单，2：命中远程黑名单、3：身份证为空
	 * 
	 * @param idCard
	 * @return
	 */
	public int isBlackIdCard(String idCard) {

		if (StringUtils.isEmpty(idCard)) {
			return 3;
		}

		// BlackCertNoUpdateReq updateMsgReq = new BlackCertNoUpdateReq();
		// updateMsgReq.setCertNo(idCard);
		//
		// BlackCertNoUpdateService blackService = new
		// BlackCertNoUpdateService();
		// BlackCertNoUpdateResp updateMsgResp =
		// (BlackCertNoUpdateResp)blackService.responseValue(updateMsgReq);
		//
		// // 命中黑名单
		// if(ResultCodes.BLACK_CERTNO_UPDATE_ALREADY.equals(updateMsgResp.getResultCode())){
		// return true;
		// }
		//
		// return false;

		try {

			HashMap<String, String> params = new HashMap<String, String>();
			params.put("certNo", idCard);

			RemoteService remoteService = new RemoteService();
			String resultStr = remoteService.remoteHttpAmt(
					Consts.SERVICE_SFHMD, params);

			BlackCertNoUpdateResp blackResp = new Gson().fromJson(resultStr,
					BlackCertNoUpdateResp.class);

			// 命中黑名单
			if (ResultCodes.BLACK_CERTNO_UPDATE_ALREADY.equals(blackResp
					.getResultCode())) {
				return 1;
			} else if (ResultCodes.BLACK_CERTNO_UPDATE_SAVEBLACK
					.equals(blackResp.getResultCode())) {
				return 2;
			} else {
				return 0;
			}

		} catch (Exception e) {
			System.out.println("远程调用【身份证验证：" + idCard + "】接口异常\n"
					+ e.getMessage());
			e.printStackTrace();
			return 0;
		}

	}
	
	/**
	 * 鹏远大学生贷款风险评分
	 * @param customer
	 * @param appNo
	 * @return 0： 评分合格 1 ：客户属于鹏元征信D类客户 
	 * @throws HttpException
	 * @throws IOException
	 * @throws HttpReturnException
	 * @throws HttpUrlRemoteException
	 */
	private int pyFxpfRemote(CustCustomer customer) {
		
		int i = 0;
		
		/* 参数 */
		String admissionTime = customer.getAdmissionTime(); // 入学时间 格式2015-01
		String[] admissionTimeArr = admissionTime.split("-");
		String universityName = customer.getSchool();
		String educationalName = queryParamNameByDicCode(customer.getEducational());
		String fulltime = customer.getFullTimeFlag();
//		if("KHL1".equals(customer.getCustType())){
//			universityName = customer.getSchool();
//		}else{
//			universityName = customer.getGraduatedSchool(); // 白领毕业学校
//		}
		if("博士".equals(educationalName)||"硕士".equals(educationalName)){
			educationalName += "研究生";
		}
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("documentno", customer.getIdentityNo());
		params.put("name", customer.getRealName());
		params.put("applicationno", inputAppPay.getApplicationNo());
		params.put("starttime", (admissionTimeArr != null && admissionTimeArr.length > 0) ? admissionTimeArr[0] : "");
		params.put("college", universityName);
		params.put("degree", educationalName);
		params.put("fulltime", ("SAS1".equals(fulltime) || "全日制".equals(fulltime)) ? "是" : "否");
		params.put("applymoney", "5000");
		
		RemoteService remoteService = new RemoteService();
		
		try {
			
			String resultStr = remoteService.remoteHttpAmt(Consts.SERVICE_PY001, params);
			
			PyFxpfRemoteResp resp = new Gson().fromJson(resultStr, PyFxpfRemoteResp.class);
			
			if("refuse".equals(resp.getStatus())){
				i = 1;
			}
			
			logger.info("***[身份证 " + customer.getIdentityNo() + "，申请件" + inputAppPay.getApplicationNo() + "]鹏远风险评分接口调用结果：" + resp.getStatus() + "，" + resp.getMsg());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("[鹏远大学生贷款风险评分 - 接口调用异常]\n" + e.getMessage());
		}

		return i;
	}
	
	/**
	 * 鹏元 黑名单
	 * @param customer
	 * @param appNo
	 * @return 0： 评分合格 1 ：客户属于鹏元征信D类客户 
	 * @throws HttpException
	 * @throws IOException
	 * @throws HttpReturnException
	 * @throws HttpUrlRemoteException
	 */
	private int pyBlackRemote(CustCustomer customer) {
		
		int i = 0;
		
		/* 参数 */
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("documentno", customer.getIdentityNo());
		params.put("name", customer.getRealName());
		params.put("applicationno", inputAppPay.getApplicationNo());
		
		RemoteService remoteService = new RemoteService();
		
		try {
			
			String resultStr = remoteService.remoteHttpAmt(Consts.SERVICE_PY002, params);
			
			PyFxpfRemoteResp resp = new Gson().fromJson(resultStr, PyFxpfRemoteResp.class);
			
			if("refuse".equals(resp.getStatus())){
				i = 1;
			}
			logger.info("***[身份证 " + customer.getIdentityNo() + "，申请件" + inputAppPay.getApplicationNo() + "]鹏远黑名单接口调用结果：" + resp.getStatus() + "，" + resp.getMsg());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("[鹏远黑名单 - 接口调用异常]\n" + e.getMessage());
		}

		return i;
	}

	public String queryDicName(String dicCode) {

		if (!StringUtils.isEmpty(dicCode)) {

			SystemParamQuery sysQuery = new SystemParamQuery();
			SystemParamEntity dic = sysQuery.queryParamByDicCode(dicCode);

			if (dic != null) {
				return dic.getDicName();
			} else {
				return "";
			}

		} else {
			return "";
		}
	}

	/**
	 * 同盾风险规则
	 * 
	 * @return
	 * @throws UpdateRulesResultFaildException
	 * @throws SaveTongDunBlackException
	 * @throws TongDunValRulesException 
	 * @throws UpdateAmountException
	 */
	public String tongDunRule(String recordId, CustCustomer customer)
			throws UpdateRulesResultFaildException, SaveTongDunBlackException, TongDunValRulesException {

		String str = unhits;

		// 记录规则执行记录
		RulesRunResult runResult = new RulesRunResult();
		runResult.setRuleNo("RULEA0020");
		runResult.setApplicationNo(Long.parseLong(inputAppPay
				.getApplicationNo()));
		runResult.setRunTime(DateUtils.getDateStr(new Date()));
		runResult.setRunResult(RulesRuslt.N.toString());
		runResult.setNumber(1);
		runResult.setRecordId(recordId);
		TongDunValRulesService rulesService = new TongDunValRulesService();
		TongDunValRulesReq rulesReq = new TongDunValRulesReq();
		rulesReq.setAccount_email(customer.getEmailAdress());
		rulesReq.setAccount_mobile(customer.getMobileNo());
		rulesReq.setAccount_name(customer.getRealName());
		rulesReq.setId_number(customer.getIdentityNo());
		rulesReq.setIp_address("");
		rulesReq.setToken_id("");// 唯一标识
		TongDunValRulesResp infoResp = (TongDunValRulesResp) rulesService
				.responseValue(rulesReq);

		if (infoResp.getResultCode().equals(ResultCodes.NORMAL)) {
			str = unhits;
			runResult.setRunRsRecord("申请人同盾风险验证姓名，身份证，邮箱，手机号码未命中规则，通过");
		} else if (infoResp.getResultCode().equals(
				ResultCodes.TONGDUN_VALRULES_SCORE_HIGH)) {
			str = hits;
			// 保存进黑名单
			BlackCertNoUpdateEvent event = new BlackCertNoUpdateEvent();
			event.saveTongDun(customer.getIdentityNo(), customer.getMobileNo(),
					customer.getRealName());

			runResult.setRunResult(RulesRuslt.Y.toString());
			runResult.setRunRsRecord("申请人同盾风险验证姓名，身份证，邮箱，手机号码命中规则，拒绝");

		}else{
			throw new TongDunValRulesException();
		}

		ruleRunResultEvent.createRulesRunResult(runResult);
		return str;
	}
	
	/**
	 * 获取系统参数名称
	 * @param dicCode
	 * @return
	 */
	public String queryParamNameByDicCode(String dicCode){
		
		SystemParamQuery query = new SystemParamQuery();
		SystemParamEntity param = query.queryParamByDicCode(dicCode);
		
		if(param != null) {
			return StringUtils.getString(param.getDicName());
		}
		
		return "";
	}
	/**
	 * 嗨车贷进件规则DCFQ
	 * @param appNo
	 * @param recordId
	 * @return
	 * @throws UpdateRulesResultFaildException 
	 */
	public Map<Object,Object> exeApprRulesForDCFQBlue(String appNo,String recordId) throws UpdateRulesResultFaildException {
		

		ApplicationPayQuery applicationQuery = new ApplicationPayQuery();
		 
		 InputAppPayQuery inputAppPayQuery = new InputAppPayQuery();
		 
		 applicationPay = applicationQuery.queryApplicationPayById(appNo);
		 
		 inputAppPay = inputAppPayQuery.queryInputAppPay(appNo);
		 
		 CustcustomerQuery custcustomerQuery = new CustcustomerQuery();
		 
//		 ProductInfoQuery proQuery = new ProductInfoQuery();
		 
//		 ProductInfo proEntity = proQuery.queryProductInfoById(applicationPay.getProductInfo());
		 
		 custCustomer = custcustomerQuery.queryCustCustomer(applicationPay.getUsername());
		 
		 Map<Object,Object> map = new HashMap<Object, Object>();
		 map.put("results", false);
		 map.put("desc", "");
		 
		//身份证规则
		 if(hits.equals(identityRule(recordId))){
			 map.put("results", true);
			 map.put("desc", "221");
			 return map;
		 }
		 
		 //手机号规则
		 if(hits.equals(mobileRule(recordId))){
			 map.put("results", true);
			 map.put("desc", "221");
			 return map;
		 }
		
		return map;
	}
	
	
	public Map<Object,Object> exeApprRulesBlue(String appNo,String recordId,String tdToken,String isTdValidate) throws UpdateRulesResultFaildException{
		
		 ApplicationPayQuery applicationQuery = new ApplicationPayQuery();
		 
		 InputAppPayQuery inputAppPayQuery = new InputAppPayQuery();
		 
		 applicationPay = applicationQuery.queryApplicationPayById(appNo);
		 
		 inputAppPay = inputAppPayQuery.queryInputAppByAppNo(appNo);
		 
		 CustcustomerQuery custcustomerQuery = new CustcustomerQuery();
		 
		 custCustomer = custcustomerQuery.queryCustCustomer(applicationPay.getUsername());
		 
		 Map<Object,Object> map = new HashMap<Object, Object>();
		 map.put("results", false);
		 map.put("desc", "");
		 map.put("refuse_flag", "");// 0 审批拒 1 进件拒-聚 2 进件拒-秉 3 进件拒-同 4 进件拒-自
		 
		 //年龄规则
//		 if(hits.equals(ageRule(18,recordId))){
//			 map.put("results", true);
//			 map.put("desc", "411");
//			 map.put("refuse_flag", "4");
//			 return map;
//		 }
		 //年龄规则  2016-03-04修改到18-35
		 if(hits.equals(ageRuleForKHL2(18,recordId,35))){
			 map.put("results", true);
			 map.put("desc", "411");
			 map.put("refuse_flag", "4");
			 return map;
		 }
		 
		 //学历规则
//		 if(hits.equals(educationRule(recordId))){
//			 map.put("results", true);
//			 map.put("desc", "416");
//			 return map;
//		 }
		 
		 //身份证规则
		 if(hits.equals(identityRule(recordId))){
			 map.put("results", true);
			 map.put("desc", "221");
			 if(bcIdentityFlag == 0){
					map.put("refuse_flag", "4"); // 命中本地黑名单
				} else{
					map.put("refuse_flag", "2"); // 命中秉承黑名单
				}
			 return map;
		 }
		 
		 //手机号规则
		 if(hits.equals(mobileRule(recordId))){
			 map.put("results", true);
			 map.put("desc", "221");
			 map.put("refuse_flag", "4");
			 return map;
		 }
		 
		 //逾期规则
		 if(hits.equals(overdueRule(recordId))){
			 map.put("results", true);
			 map.put("desc", "241");
			 map.put("refuse_flag", "4");
			 return map;
		 }
		 
		 //学校黑名单
		 if(hits.equals(schoolRule(recordId))){
			 map.put("results", true);
			 map.put("desc", "221");
			 map.put("refuse_flag", "4");
			 return map;
		 }
		 /* 执行同盾验证  2015-12-10*/
//			if(isTdValidate != null && Consts.FINAL_NUMBER_1.equals(isTdValidate)){
				tdValidate(tdToken, custCustomer.getRealName(), custCustomer.getMobileNo(), custCustomer.getIdentityNo(),custCustomer.getEmailAdress(),custCustomer.getQqNumber());
//			} 
		 return map;
		 
		
	}
	
	/**
	 * 白领进件规则
	 * @param appNo
	 * @param recordId
	 * @return
	 * @throws UpdateRulesResultFaildException 
	 */
	public Map<Object,Object> exeApprRulesForKHL2Blue(String appNo,String recordId,String tdToken,String isTdValidate) throws UpdateRulesResultFaildException {
		

		ApplicationPayQuery applicationQuery = new ApplicationPayQuery();
		 
		 InputAppPayQuery inputAppPayQuery = new InputAppPayQuery();
		 
		 applicationPay = applicationQuery.queryApplicationPayById(appNo);
		 
		 inputAppPay = inputAppPayQuery.queryInputAppPay(appNo);
		 
		 CustcustomerQuery custcustomerQuery = new CustcustomerQuery();
		 
//		 ProductInfoQuery proQuery = new ProductInfoQuery();
		 
//		 ProductInfo proEntity = proQuery.queryProductInfoById(applicationPay.getProductInfo());
		 
		 custCustomer = custcustomerQuery.queryCustCustomer(applicationPay.getUsername());
		 
		 Map<Object,Object> map = new HashMap<Object, Object>();
		 map.put("results", false);
		 map.put("desc", "");
		 map.put("refuse_flag", "");// 0 审批拒 1 进件拒-聚 2 进件拒-秉 3 进件拒-同 4 进件拒-自
		 
		 /* 军人不用验证身份证规则 */
		 if(!Consts.DFT_IDENTITY.equals(custCustomer.getIdentityNo())){
			 //年龄规则，小于等于17 或者大于等于61 2016-03-04修改到18-35
			 if(hits.equals(ageRuleForKHL2(18,recordId,35))){
				 map.put("results", true);
				 map.put("desc", "411");
				 map.put("refuse_flag", "4");
				 return map;
			 }
			 
			//身份证规则
			 if(hits.equals(identityRule(recordId))){
				 map.put("results", true);
				 map.put("desc", "221");
				 if(bcIdentityFlag == 0){
						map.put("refuse_flag", "4"); // 命中本地黑名单
					} else{
						map.put("refuse_flag", "2"); // 命中秉承黑名单
					}
				 return map;
			 }
		 }
		 
		
		 //学历规则  专科以下自动拒绝
		 //白领婚庆服务，月子服务，装修服务不需要验证学历
//		 if(Consts.APPFLOW_TYPE_CASH.equals(applicationPay.getPayType())){
//			 if(hits.equals(educationRuleForKHL2(recordId))){
//				 map.put("results", true);
//				 map.put("desc", "416");
//				 return map;
//			 }
//		 }
		 //开放城市规则
		/* if(hits.equals(openCityRule(recordId))){
			 map.put("results", true);
			 map.put("desc", "423");
			 return map;
		 }*/
		 
		 
		 
		 //手机号规则
		 if(hits.equals(mobileRule(recordId))){
			 map.put("results", true);
			 map.put("desc", "221");
			 map.put("refuse_flag", "4");
			 return map;
		 }
		 
		 //逾期规则
		 if(hits.equals(overdueRule(recordId))){
			 map.put("results", true);
			 map.put("desc", "241");
			 map.put("refuse_flag", "4");
			 return map;
		 }
//		 /* 执行同盾验证  2015-12-10*/
//			if(isTdValidate != null && Consts.FINAL_NUMBER_1.equals(isTdValidate)){
				tdValidate(tdToken, custCustomer.getRealName(), custCustomer.getMobileNo(), custCustomer.getIdentityNo(),custCustomer.getEmailAdress(),custCustomer.getQqNumber());
//			}  
		
		return map;
	}
	public static void main(String[] args) {
		
//		tdValidate("%E7%8E%8B%E8%8E%8E%E8%8E%89", "13871191522", "420106197610263283");
//		tdValidate("东方不败", "13871191522", "420106197610263283");
		
	}

}
