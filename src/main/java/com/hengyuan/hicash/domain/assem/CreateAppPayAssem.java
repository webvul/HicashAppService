package com.hengyuan.hicash.domain.assem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.domain.query.mer.SupplierQuery;
import com.hengyuan.hicash.domain.query.mktApp.CreateAppPayQuery;
import com.hengyuan.hicash.domain.query.user.HyEmployeeQuery;
import com.hengyuan.hicash.entity.app.CreateAppPayEntity;
import com.hengyuan.hicash.entity.mer.MerProductEntity;
import com.hengyuan.hicash.entity.mer.SaleSiteEntity;
import com.hengyuan.hicash.entity.mer.SupplierEntity;
import com.hengyuan.hicash.entity.user.ApproveUser;
import com.hengyuan.hicash.entity.user.ApproveuserBusiness;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.entity.user.HyEmployeeEntity;
import com.hengyuan.hicash.entity.user.University;
import com.hengyuan.hicash.exception.ApproveBusinessException;
import com.hengyuan.hicash.exception.ApproveUserNotFound;
import com.hengyuan.hicash.exception.CustomerNotFoundException;
import com.hengyuan.hicash.exception.GenerateFlowNoException;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.exception.QueryFlowNoException;
import com.hengyuan.hicash.exception.SiteNotFoundException;
import com.hengyuan.hicash.exception.UniversityNotFound;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppBlueCollarReq;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppPayReq;
import com.hengyuan.hicash.parameters.request.param.LoanAmtCalReq;
import com.hengyuan.hicash.parameters.response.QueryShouQuanSucResp;
import com.hengyuan.hicash.parameters.response.user.LoanAmtCalResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.HttpRemotePost;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * 创建申请件对象组装
 * 
 * @author Andy.Niu
 * 
 */
public class CreateAppPayAssem {
	private static Logger logger = Logger
			.getLogger(CreateAppPayAssem.class);
	private CreateAppPayEntity applicationPay = new CreateAppPayEntity();
	private CreateAppPayQuery appPayQuery;
	private Map<String, Object> appPayMap;

	public CreateAppPayAssem(CreateAppPayQuery appPayQuery) {

		if (appPayQuery != null) {
			this.appPayQuery = appPayQuery;
		} else {
			this.appPayQuery = new CreateAppPayQuery();
		}
		appPayMap = new HashMap<String, Object>();
	}

	/**
	 * 初始化申请件状态信息
	 * 
	 * @author Andy.Niu
	 * @throws SQLException
	 * @create 2014-08-07
	 */
	public void initPayNodeAndStatus1() throws SQLException {

		/* 申请件认领 */
		applicationPay.setNode(Consts.FIRST_NODE);
		/* 未认领 */
		applicationPay.setStatus(Consts.FIRST_STATUS);
		/* 新申请 */
		applicationPay.setAllNode(Consts.FIRST_ALL_NODE);

		appPayMap.put("ALLNODE", Consts.FIRST_ALL_NODE);
		appPayMap.put("NODE", Consts.FIRST_NODE);
		appPayMap.put("STATUS", Consts.FIRST_STATUS);
		
	}

	/**
	 * 初始化申请件流水号和产品信息
	 * 
	 * @param productInfo
	 * @param loanProduct
	 * @param applyType
	 * @throws GenerateFlowNoException
	 * @throws QueryFlowNoException
	 * @author Andy.Niu
	 * @throws SQLException
	 * @create 2014-08-07
	 */
	public void initFlowNoAndProduct2(CreateAppPayReq tempReq,MerProductEntity proEntity)
			throws GenerateFlowNoException, QueryFlowNoException, SQLException {

		String appPayNo = appPayQuery.getAppPayNo(Consts.APPFLOW_TYPE_CASH,Consts.APPNO_GENERATE_SEQ);
		/* 获取商户返点 */
//		MerRebateQuery rebateQuery = new MerRebateQuery();
//		String rebate = rebateQuery.queryRebateByProId(tempReq.getMerProId());

		/* 申请件APPNo */
		applicationPay.setAppPayNo(appPayNo);

		/* 申请件产品ID */
//		applicationPay.setProductId(productInfo.getProId().toString());

		/* 产品类型 */
//		applicationPay.setProductType(productInfo.getProductType());

//		applicationPay.setProductName(productInfo.getProductName());
//		String productName = tempReq.getProductName();
//		if(Consts.APPFLOW_TYPE_CASH.equals(tempReq.getApplyType())){
//			productName = productInfo.getProductName();
//		}
		/* 申请产品的名称 (业务员填写产品名称) */
		applicationPay.setProductName(proEntity.getProName());

		/* 贷款产品ID */
		applicationPay.setLoanProductId(tempReq.getLoanProduct());

		/* 现金贷支付方式 */
		applicationPay.setPayType(tempReq.getApplyType());

		appPayMap.put("app_application_no", StringUtils.valueOf(appPayNo));
		
//		appPayMap.put("pro_id", StringUtils.valueOf(productInfo.getProId().toString()));
		
		appPayMap.put("MER_PRODUCTID", StringUtils.valueOf(tempReq.getMerProId()));
		
//		appPayMap.put("productType",StringUtils.valueOf(productInfo.getProductType()));
		
		appPayMap.put("product_name",proEntity.getProName());
		
//		appPayMap.put("PRODUCT_DETAIL", StringUtils.valueOf(tempReq.getProductDetail()));
		
		appPayMap.put("app_creditproduct_id", StringUtils.valueOf(tempReq.getLoanProduct()));
		
		appPayMap.put("pay_type", Consts.APPFLOW_TYPE_CASH);
		
		appPayMap.put("REBATE_PERCENT", Consts.FINAL_NUMBER_0);
		
		appPayMap.put("HY_INDUSTRY_CODE", proEntity.getIndustryCode());
		
		appPayMap.put("H_AGENT_LOGIC_CODE",Consts.HMD_DEFAULT_AGENTLOGICCODE);
		//推荐人 2017-01-10去掉
//		if(!tempReq.getUserName().equals(tempReq.getReference()))
//		{
//		appPayMap.put("reference", tempReq.getReference());
//		}
		
		//商品总价(商户输入 2015-03-16)
//		if(!StringUtils.isEmpty(tempReq.getMerProPrice())){
//			appPayMap.put("MER_PRO_PRICE", StringUtils.valueOf(tempReq.getMerProPrice()));
//		}else{
//			appPayMap.put("MER_PRO_PRICE",Consts.FINAL_NUMBER_0);
//		}
		
		if(!StringUtils.isEmpty(tempReq.getApplyFrom())){
			appPayMap.put("APPLY_FROM",tempReq.getApplyFrom());
		}
		
//		if(!StringUtils.isEmpty(tempReq.getBrowserStr())){
//			appPayMap.put("BROWSER_STR",tempReq.getBrowserStr());
//		}
		
		appPayMap.put("BROWSER_STR","默认用途_N_APP");
		
		/* 进件渠道 */
		appPayMap.put("inComeType", Consts.INCOME_HAPP);
		/*引流来源 */
		if (!StringUtils.isEmpty(tempReq.getImDrainage())) {
			appPayMap.put("mo_info", tempReq.getImDrainage());
		}
	}

	/**
	 * 初始化申请件申请用户信息
	 * 
	 * @param userName
	 * @throws CustomerNotFoundException
	 * @author Andy.Niu
	 * @throws SQLException
	 * @create 2014-08-06
	 */
	public void initCustomer3(String userName)
			throws CustomerNotFoundException, SQLException {

		CustCustomer custCustomer = appPayQuery.getCustCustomer(userName);

		/* 申请人用户名 */
		applicationPay.setUserName(userName);

		/* 申请人真实姓名 */
		applicationPay.setRealName(custCustomer.getRealName());

		/* 申请人手机号码 */
		applicationPay.setMobileNo(custCustomer.getMobileNo());

		/* 申请人身份证号 */
		applicationPay.setIdentityNo(custCustomer.getIdentityNo());

		/* 客户类型 */
		applicationPay.setCustType(custCustomer.getCustType());

		/* 设置申请地址 */
		if (Consts.APP_CUSTOMER_TYPE_KHL1.equals(custCustomer.getCustType())) {
			/* 学校地址*/
			applicationPay.setApplyAddress(custCustomer.getSchool());
			applicationPay.setSchoolId(custCustomer.getSchoolId());
			
			appPayMap.put("app_address", StringUtils.valueOf(custCustomer.getSchool()));
			appPayMap.put("SCHOOL_ID", StringUtils.valueOf(custCustomer.getSchoolId()));
			
		}else if (Consts.APP_CUSTOMER_TYPE_KHL2.equals(custCustomer.getCustType())) {
			//单位地址
			applicationPay.setApplyAddress(custCustomer.getUnitName());
			appPayMap.put("app_address",StringUtils.valueOf(custCustomer.getUnitName()));
		}
		/* 客户用户名 */
		appPayMap.put("app_username", StringUtils.valueOf(userName));
		appPayMap.put("app_user_realname",StringUtils.valueOf(custCustomer.getRealName()));
		appPayMap.put("app_user_mobile",StringUtils.valueOf(custCustomer.getMobileNo()));
		appPayMap.put("app_user_identityno", StringUtils.valueOf(custCustomer.getIdentityNo()));
		appPayMap.put("app_cust_type", StringUtils.valueOf(custCustomer.getCustType()));
		appPayMap.put("create_user", StringUtils.valueOf(userName));
		appPayMap.put("create_date", DateUtils.getDateStr(new Date()));
	}

	/**
	 * 初始化申请件当前所在区域
	 * 
	 * @param proviceCode
	 * @param cityCode
	 * @author Andy.Niu
	 * @throws SQLException
	 * @create 2014-08-06
	 */
	public void initCurrentCity4(String proviceCode, String cityCode)
			throws SQLException {

		/* 省份 */
		applicationPay.setProvice(proviceCode);
		/* 城市 */
		applicationPay.setCityCode(cityCode);

		appPayMap.put("provice", StringUtils.valueOf(proviceCode));
		appPayMap.put("app_city_code", StringUtils.valueOf(cityCode));
	}

	/**
	 * 初始化话申请件办单销售信息
	 * 
	 * @param saleCode
	 * @throws ApproveUserNotFound
	 * @throws UniversityNotFound
	 * @throws ApproveBusinessException
	 * @author Andy.Niu
	 * @throws SQLException
	 * @create 2014-08-07
	 */
	public void initSaleServer5(ApproveUser approveUser) throws UniversityNotFound,
			SQLException, ApproveBusinessException {

		/* 数码3C业务 */
		if (Consts.APPFLOW_TYPE_3C.equals(applicationPay.getPayType())) {
			initSaleServerNormal(approveUser);
		}

		/* 现金业务 */
		if (Consts.APPFLOW_TYPE_CASH.equals(applicationPay.getPayType())) {
			initSaleServerCash(approveUser);
		}
	}

	/**
	 * 加载商户业务员信息
	 * @param approveUser
	 * @throws SQLException 
	 * @throws SiteNotFoundException 
	 */
	public void initSupplierApprove(CreateAppPayReq tempReq,SaleSiteEntity siteEntity) throws SQLException, SiteNotFoundException{
		
		if(siteEntity != null){
			
			SupplierQuery supplierQuery = new SupplierQuery();
			SupplierEntity supplierEntity = supplierQuery.querySaleSiteById(siteEntity.getDefaultSupplier());
			
			appPayMap.put("app_supplier",StringUtils.valueOf(supplierEntity.getSupplierId()));
			appPayMap.put("supplier_name",StringUtils.valueOf(supplierEntity.getSupplierName()));
			appPayMap.put("app_sale_site",StringUtils.valueOf(siteEntity.getSiteName()));
			appPayMap.put("app_site_code",StringUtils.valueOf(siteEntity.getSiteId()));
//			appPayMap.put("logicCode",StringUtils.valueOf(approveUser.getLogicCode()));
		}else{
			throw new SiteNotFoundException();
		}
		
	}
	
	public void initAppOtherInfo(CreateAppPayReq tempReq, CustCustomer customer, CustUserEntity custUser,MerProductEntity proEntity) throws SQLException{
		
		/* 蓝领秒贷分业务员工号 */
		if("LLMD".equals(proEntity.getIndustryCode())){
			appPayMap.put("app_sale_code", customer.getInveteCode());
		}
		
		/* 部门逻辑码 */
		HyEmployeeQuery employeeQuery = new HyEmployeeQuery();
		HyEmployeeEntity entity = employeeQuery.queryEmployeeByCode(customer.getInveteCode());
		if(entity != null){
			String employeeCode = entity.getAgentLogicCode();
			if(employeeCode.length() >= 4){
				appPayMap.put("H_AGENT_LOGIC_CODE",employeeCode);
			}else{
				appPayMap.put("H_AGENT_LOGIC_CODE",Consts.HY_DEFAULT_EMPLOYEECODE);
			}
		}
		
		/* 进件来源 2017-01-10  1.19打开艳艳*/
		String custFrom = "";
		if(custUser.getCreateTime() != null && !"".equals(custUser.getCreateTime())){
			
			if(DateUtils.getDateToStr(new Date()).equals(custUser.getCreateTime())){
				custFrom = StringUtils.valueOf(customer.getCustFrom());
			}else {
				if(!"".equals(tempReq.getCustAppFrom())){
					custFrom = tempReq.getCustAppFrom();
				}
			}
			
		}else {
			if(!"".equals(tempReq.getCustAppFrom())){
				custFrom = tempReq.getCustAppFrom();
			}
		}
		
		appPayMap.put("CUST_FROM", custFrom);
		/* 2017-01-10去掉  1.19打开艳艳*/
		if(!StringUtils.isEmpty(tempReq.getActiveFrom())){
			appPayMap.put("ACTIVE_FROM", tempReq.getActiveFrom()); // 活动来源
		}
		
		appPayMap.put("DOUBLE_SALES", StringUtils.valueOf(custUser.getIsDoubleSales()));
		
	}
	
	/**
	 * 初始化申请件金额信息
	 * 
	 * @param loanAmt
	 * @throws HttpException
	 * @throws IOException
	 * @throws HttpReturnException
	 * @author Andy.Niu
	 * @throws SQLException
	 * @throws HttpUrlRemoteException 
	 * @create 2014-08-06
	 */
	public LoanAmtCalResp initAppPayAmt6(LoanAmtCalReq loanAmt) throws HttpException,
			IOException, HttpReturnException, SQLException, HttpUrlRemoteException {

		LoanAmtCalResp amtCalResp = appPayQuery.remoteHttpAmt(loanAmt);

		/* 默认折扣设置 */
		applicationPay.setDisCount(Consts.PAY_RATE);
//		/* 交易金额 */
//		applicationPay.setTranPrice(amtCalResp.getLoanPrincipal().toString());
		/* 还款期数 */
		applicationPay.setInstallMent(amtCalResp.getPayMentPeriod().toString());
		/* 月还款 */
		applicationPay.setMonthPay(amtCalResp.getMonthPayMent().toString());
		/* 实际贷款金额 */
		applicationPay.setApplyAmount(loanAmt.getTranPrice());
		/* 首付比率 */
		applicationPay.setFirstRate(loanAmt.getFirstRate());
		/* 首付金额 */
		applicationPay.setFirstPayMent(amtCalResp.getFirstPayMent().toString());
		/* 首付中含有的本金 */
		applicationPay.setFirstPrin(amtCalResp.getFirstPayMent().toString());
		
		if(Consts.APPFLOW_TYPE_CASH.equals(loanAmt.getPayType())){
			applicationPay.setTranPrice(amtCalResp.getLoanPrincipal().toString());
			appPayMap.put("tranPrice",StringUtils.valueOf(amtCalResp.getLoanPrincipal()));
		}else{
			appPayMap.put("tranPrice",StringUtils.valueOf(loanAmt.getTranPrice()));
			applicationPay.setTranPrice(loanAmt.getTranPrice());
		}
		
		appPayMap.put("app_dis_count", StringUtils.valueOf(Consts.PAY_RATE));
		appPayMap.put("isallocation", StringUtils.valueOf("0"));
		appPayMap.put("isMerchant", StringUtils.valueOf("0"));
//		appPayMap.put("tranPrice", StringUtils.valueOf(amtCalResp.getLoanPrincipal()));//amtCalResp.getLoanPrincipal()
		appPayMap.put("app_install_ment", StringUtils.valueOf(amtCalResp.getPayMentPeriod()));
		appPayMap.put("app_month_pay", StringUtils.valueOf(amtCalResp.getMonthPayMent()));
		appPayMap.put("APPLY_AMOUNT", StringUtils.valueOf(loanAmt.getTranPrice()));
		appPayMap.put("FIRST_PAY_RATE", StringUtils.valueOf(loanAmt.getFirstRate()));
		appPayMap.put("FIRST_PAY_AMOUNT", StringUtils.valueOf(amtCalResp.getFirstPayMent()));
		appPayMap.put("FIRST_PAY_PRINCIPAL", StringUtils.valueOf(amtCalResp.getFirstPayMent()));
		
		/* 2015-08-05 更改 cary.Liu */
		appPayMap.put("app_creditproduct_id", StringUtils.valueOf(amtCalResp.getLoanProduct()));
		applicationPay.setLoanProductId(StringUtils.valueOf(amtCalResp.getLoanProduct()));
	
		return amtCalResp;
	}

	/**
	 * 数码3C业务如果销售代码不为空 初始化申请件需要做的操作
	 * 
	 * @param approveUser
	 * @author Andy.Niu
	 * @throws SQLException
	 * @create 2014-08-06
	 */
	private void initSaleServerNormal(ApproveUser approveUser)
			throws SQLException {
		/* 数码3C业务暂不需要做设置 数码3C业务在商户系统认领申请件时统一做处理 */
		setApproveForPay(approveUser);

	}

	
	/**
	 * 现金类产品初始化办单业务员
	 * 
	 * @param approveUser
	 * @throws UniversityNotFound
	 * @throws ApproveBusinessException
	 * @throws ApproveUserNotFound
	 * @throws SQLException
	 */
	private void initSaleServerCash(ApproveUser approveUser)
			throws UniversityNotFound, ApproveBusinessException, SQLException {

		if (approveUser != null) {

			setApproveForPay(approveUser);

		} else {

			/* 客户未输入推荐码或者推荐码不存在 */
			/* 学生客户根据学校分配自动分案 */
			if (Consts.APP_CUSTOMER_TYPE_KHL1.equals(applicationPay.getCustType())) {
				divisionForCashStu();
			}

			/* 白领梦想基金自动分案 */
			if (Consts.APP_CUSTOMER_TYPE_KHL2.equals(applicationPay.getCustType())) {
				divisionForCashCollar();
			}
		}

	}

	/**
	 * 设置申请件销售人员信息
	 * 
	 * @param approveUser
	 * @author Andy.Niu
	 * @throws SQLException
	 * @create 2014-08-07
	 */
	private void setApproveForPay(ApproveUser approveUser) throws SQLException {

		if (approveUser != null) {

			/* 如果输入的销售代码存在，则直接将此申请件分配到该业务员 */
			applicationPay.setSaleCode(approveUser.getUserNumber());
			applicationPay.setSaleServer(approveUser.getUserName());

			/* 重置申请件所在省市区 */
			applicationPay.setProvice(approveUser.getProvice());
			applicationPay.setCityCode(approveUser.getCityCode());
			applicationPay.setAreaCode(approveUser.getArea());

			appPayMap.put("app_sale_code", StringUtils.valueOf(approveUser.getUserNumber()));
			appPayMap.put("app_sale_server", StringUtils.valueOf(approveUser.getUserName()));
			appPayMap.put("provice", StringUtils.valueOf(approveUser.getProvice()));
			appPayMap.put("app_city_code", StringUtils.valueOf(approveUser.getCityCode()));
			appPayMap.put("area", StringUtils.valueOf(approveUser.getArea()));

		}
	}

	/**
	 * 未填销售码学生现金产品自动分案
	 * 
	 * @throws UniversityNotFound
	 * @throws ApproveBusinessException
	 * @throws ApproveUserNotFound
	 * @author Andy.Niu
	 * @throws SQLException
	 * @create 2014-08-07
	 */
	private void divisionForCashStu() throws UniversityNotFound, SQLException {

		/* 学生客户根据学校分配自动分案 */
		if (Consts.APP_CUSTOMER_TYPE_KHL1.equals(applicationPay.getCustType())) {

			University university = appPayQuery
					.getUniversityById(applicationPay.getSchoolId());

			/* 如果当前所在城市就是学校所在城市，则进行自动分案功能 */
			if (applicationPay.getCityCode().equals(university.getCity())) {
				try {
					ApproveuserBusiness saleServer = appPayQuery
							.getApproveuserBusiness(applicationPay
									.getSchoolId());
					String saleCode = saleServer.getSaleCode();

					if (!org.apache.commons.lang.StringUtils.isEmpty(saleCode)) {

						ApproveUser approve = appPayQuery
								.getApproveUser(saleCode);
						setApproveForPay(approve);
					}

				} catch (ApproveUserNotFound e) {
					System.out.println("未查询到需要分配的销售人员信息，不进行分案步骤");

				} catch (ApproveBusinessException e) {
					System.out.println("未查询到需要分配的销售人员信息，不进行分案步骤");
					e.printStackTrace();
				}
			} else {
				System.out.println("学生所属学校和填写的城市不一致，不进行自动分案");
			}
		}
	}

	/**
	 * 未填销售代码白领现金自动分案
	 * 
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	private void divisionForCashCollar() {

		/* 白领自动分案暂无 */
		/* 白领分案预留 */
		if (Consts.APP_CUSTOMER_TYPE_KHL2.equals(applicationPay.getCustType())) {

		}
	}
	
	public CreateAppPayEntity getApplicationPay() {
		return applicationPay;
	}

	public void setApplicationPay(CreateAppPayEntity applicationPay) {
		this.applicationPay = applicationPay;
	}

	public CreateAppPayQuery getAppPayQuery() {
		return appPayQuery;
	}

	public void setAppPayQuery(CreateAppPayQuery appPayQuery) {
		this.appPayQuery = appPayQuery;
	}

	public Map<String, Object> getAppPayMap() {
		return appPayMap;
	}

	public void setAppPayMap(Map<String, Object> appPayMap) {
		this.appPayMap = appPayMap;
	}
	/**
	 * 初始化申请件流水号和产品信息
	 * 
	 * @param productInfo
	 * @param loanProduct
	 * @param applyType
	 * @throws GenerateFlowNoException
	 * @throws QueryFlowNoException
	 * @author Andy.Niu
	 * @throws SQLException
	 * @create 2014-08-07
	 */
	public void initFlowNoAndProductBlue2(CreateAppBlueCollarReq tempReq,MerProductEntity proEntity)
			throws GenerateFlowNoException, QueryFlowNoException, SQLException {

		String appPayNo = appPayQuery.getAppPayNo(tempReq.getApplyType(),Consts.APPNO_GENERATE_SEQ);
		/* 获取商户返点 */
		String industryCode = proEntity.getIndustryCode();

		/* 申请件APPNo */
		applicationPay.setAppPayNo(appPayNo);

		/* 申请产品的名称 (业务员填写产品名称) */
		applicationPay.setProductName(tempReq.getProductName());

		/* 贷款产品ID */
		applicationPay.setLoanProductId(tempReq.getLoanProduct());

		/* 现金贷支付方式 */
		applicationPay.setPayType(tempReq.getApplyType());

		appPayMap.put("app_application_no", StringUtils.valueOf(appPayNo));
		
		appPayMap.put("MER_PRODUCTID", StringUtils.valueOf(tempReq.getMerProId()));
		
		appPayMap.put("product_name",tempReq.getProductName());
		
		appPayMap.put("PRODUCT_DETAIL", StringUtils.valueOf(tempReq.getProductDetail()));
		
		appPayMap.put("app_creditproduct_id", StringUtils.valueOf(tempReq.getLoanProduct()));
		
		appPayMap.put("pay_type", Consts.APPFLOW_TYPE_CASH);
		
		appPayMap.put("REBATE_PERCENT", Consts.FINAL_NUMBER_0); // tempReq.getRebate()
		
		appPayMap.put("HY_INDUSTRY_CODE", !org.apache.commons.lang.StringUtils.isEmpty(industryCode)?industryCode:tempReq.getIndustryCode());
		
		//商品总价(商户输入 2015-03-16)
		if(!StringUtils.isEmpty(tempReq.getMerProPrice())){
			appPayMap.put("MER_PRO_PRICE", StringUtils.valueOf(tempReq.getMerProPrice()));
		}else{
			appPayMap.put("MER_PRO_PRICE",Consts.FINAL_NUMBER_0);
		}
		
//		appPayMap.put("APPLY_USE", tempReq.getApplyUse());
		
		/* 进件渠道 */
		appPayMap.put("inComeType", Consts.INCOMETYPE_LLSM);
	}
	/**
	 * 初始化申请件申请用户信息
	 * 
	 * @param userName
	 * @throws CustomerNotFoundException
	 * @author Andy.Niu
	 * @throws SQLException
	 * @create 2014-08-06
	 */
	public void initCustomerBlue3(String userName,ApproveUser approveUser)
			throws CustomerNotFoundException, SQLException {

		CustCustomer custCustomer = appPayQuery.getCustCustomer(userName);

		/* 申请人用户名 */
		applicationPay.setUserName(userName);

		/* 申请人真实姓名 */
		applicationPay.setRealName(custCustomer.getRealName());

		/* 申请人手机号码 */
		applicationPay.setMobileNo(custCustomer.getMobileNo());

		/* 申请人身份证号 */
		applicationPay.setIdentityNo(custCustomer.getIdentityNo());

		/* 客户类型 */
		applicationPay.setCustType(custCustomer.getCustType());

		/* 设置申请地址 */
		if (Consts.APP_CUSTOMER_TYPE_KHL1.equals(custCustomer.getCustType())) {
			/* 学校地址*/
			applicationPay.setApplyAddress(custCustomer.getSchool());
			applicationPay.setSchoolId(custCustomer.getSchoolId());
			
			appPayMap.put("app_address", StringUtils.valueOf(custCustomer.getSchool()));
			appPayMap.put("SCHOOL_ID", StringUtils.valueOf(custCustomer.getSchoolId()));
			
		}else if (Consts.APP_CUSTOMER_TYPE_KHL2.equals(custCustomer.getCustType()) || Consts.APP_CUSTOMER_TYPE_KHL3.equals(custCustomer.getCustType())) {
			//单位地址
			applicationPay.setApplyAddress(custCustomer.getUnitName());
			appPayMap.put("app_address",StringUtils.valueOf(custCustomer.getUnitName()));
		}
		/* 客户用户名 */
		appPayMap.put("app_username", StringUtils.valueOf(userName));
		appPayMap.put("app_user_realname",StringUtils.valueOf(custCustomer.getRealName()));
		appPayMap.put("app_user_mobile",StringUtils.valueOf(custCustomer.getMobileNo()));
		appPayMap.put("app_user_identityno", StringUtils.valueOf(custCustomer.getIdentityNo()));
		appPayMap.put("app_cust_type", StringUtils.valueOf(custCustomer.getCustType()));
		appPayMap.put("create_user", StringUtils.valueOf(approveUser.getUserName()));
		appPayMap.put("create_date", DateUtils.getDateStr(new Date()));
	}
	
	/**
	 * 初始化申请件当前所在区域
	 * 
	 * @param proviceCode
	 * @param cityCode
	 * @author Andy.Niu
	 * @throws SQLException
	 * @create 2014-08-06
	 */
	public void initCurrentCityBlue4(String proviceCode, String cityCode,String areaCode)
			throws SQLException {

		/* 省份 */
		applicationPay.setProvice(proviceCode);
		/* 城市 */
		applicationPay.setCityCode(cityCode);
		applicationPay.setAreaCode(areaCode);

		appPayMap.put("provice", StringUtils.valueOf(proviceCode));
		appPayMap.put("app_city_code", StringUtils.valueOf(cityCode));
		appPayMap.put("area", StringUtils.valueOf(areaCode));
	}
	/**
	 * 初始化话申请件办单销售信息
	 * 
	 * @param saleCode
	 * @throws ApproveUserNotFound
	 * @throws UniversityNotFound
	 * @throws ApproveBusinessException
	 * @author Andy.Niu
	 * @throws SQLException
	 * @create 2014-08-07
	 */
	public void initSaleServerBlue5(ApproveUser approveUser) throws UniversityNotFound,
			SQLException, ApproveBusinessException {

		/* 新增员工号 */
		
		/* 周转哥订单初始部门逻辑码为0002  */
		if(Consts.ZZG_INDUSTRY_CODE.equals(appPayMap.get("HY_INDUSTRY_CODE"))){
			appPayMap.put("H_AGENT_LOGIC_CODE",Consts.HY_DEFAULT_EMPLOYEECODE);
		}
		
		HyEmployeeQuery employeeQuery = new HyEmployeeQuery();
		HyEmployeeEntity entity = employeeQuery.queryEmployeeByCode(approveUser.getUserNumber());
		if(entity != null){
			String employeeCode = entity.getAgentLogicCode();
			if(employeeCode.length() >= 4){
				appPayMap.put("H_AGENT_LOGIC_CODE",employeeCode);
			}else{
				appPayMap.put("H_AGENT_LOGIC_CODE",Consts.HY_DEFAULT_EMPLOYEECODE);
			}
		}
		
		/* 数码3C业务 */
		if (Consts.APPFLOW_TYPE_3C.equals(applicationPay.getPayType())) {
			initSaleServerNormal(approveUser);
		}

		/* 现金业务 */
		if (Consts.APPFLOW_TYPE_CASH.equals(applicationPay.getPayType())) {
			initSaleServerCash(approveUser);
		}
	}
	
	/**
	 * 加载商户业务员信息
	 * @param approveUser
	 * @throws SQLException 
	 * @throws SiteNotFoundException 
	 */
	public void initSupplierApproveBlue(CreateAppBlueCollarReq tempReq,ApproveUser approveUser,SaleSiteEntity siteEntity) throws SQLException, SiteNotFoundException{
		
			SupplierQuery supplierQuery = new SupplierQuery();
			SupplierEntity supplierEntity = supplierQuery.querySaleSiteById(siteEntity.getDefaultSupplier());
			
			appPayMap.put("app_supplier",StringUtils.valueOf(supplierEntity.getSupplierId()));
			appPayMap.put("supplier_name",StringUtils.valueOf(supplierEntity.getSupplierName()));
			appPayMap.put("app_sale_site",StringUtils.valueOf(siteEntity.getSiteName()));
			appPayMap.put("app_site_code",StringUtils.valueOf(siteEntity.getSiteId()));
			appPayMap.put("logicCode",StringUtils.valueOf(approveUser.getLogicCode()));
			
	}

	public void initPayStatus(String industryCode,String userName) throws Exception {
		
		//查询是否授权
		
		
		//没有授权
		if("LDDD".equals(industryCode)||"MDCP".equals(industryCode)||"MDOH".equals(industryCode)||"LLMD".equals(industryCode)){
			CustCustomer custCustomer = appPayQuery.getCustCustomer(userName);
			Map<String,String> map=new HashMap<String, String>();
			map.put("identityNo", custCustomer.getIdentityNo());
			
			logger.info("身份证号:"+custCustomer.getIdentityNo()+"查询芝麻信用是否授权");
			logger.info("查询芝麻信用是否授权URL"+ResourceUtils.getString(Consts.ZMXY_URL));
			String str=HttpRemotePost.sendHttp(ResourceUtils.getValue(Consts.ZMXY_URL), map);
			logger.info("身份证号:"+custCustomer.getIdentityNo()+"调用接口返回结果:"+str);
			QueryShouQuanSucResp resp=JSON.parseObject(str,
					QueryShouQuanSucResp.class);
			logger.info("resp:"+resp.toJson());
			if(resp!=null){
				if(!"1".equals(resp.getResultCode())){
					/* 申请件认领 */
					applicationPay.setNode(Consts.FIRST_NODE);
					/* 未认证 */
					applicationPay.setStatus(Consts.ZMXY_STATUS);
					/* 新申请 */
					applicationPay.setAllNode(Consts.FIRST_ALL_NODE);

					appPayMap.put("ALLNODE", Consts.FIRST_ALL_NODE);
					appPayMap.put("NODE", Consts.FIRST_NODE);
					appPayMap.put("STATUS", Consts.ZMXY_STATUS);
				}
				
			}else{
				logger.info("身份证:"+custCustomer.getIdentityNo()+"查询芝麻信用返回str:"+str);
				//throw new Exception("身份证:"+custCustomer.getIdentityNo()+"查询芝麻信用是否授权返回对象为空!");
			}
			
		}
	}

}
