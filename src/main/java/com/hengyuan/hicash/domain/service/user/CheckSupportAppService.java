package com.hengyuan.hicash.domain.service.user;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.IndustryConsts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.amount.AvailbleCreditQuery;
import com.hengyuan.hicash.domain.query.app.ApplicationPayQuery;
import com.hengyuan.hicash.domain.query.app.ApplicationPicQuery;
import com.hengyuan.hicash.domain.query.app.ProductQuery;
import com.hengyuan.hicash.domain.query.mktApp.CreateAppPayQuery;
import com.hengyuan.hicash.domain.query.mktApp.LoanloanQuery;
import com.hengyuan.hicash.domain.query.user.CustTempApplyPicQuery;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.DdsjApplyCreditQuery;
import com.hengyuan.hicash.domain.query.user.DdsjLimitQuery;
import com.hengyuan.hicash.domain.query.user.HinsApplyCreditQuery;
import com.hengyuan.hicash.domain.query.user.HinsLimitQuery;
import com.hengyuan.hicash.domain.query.user.HyVipPeriodQuery;
import com.hengyuan.hicash.domain.query.user.HyVipUserQuery;
import com.hengyuan.hicash.domain.query.user.LoanStatusQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.app.LoanLoanAccEntity;
import com.hengyuan.hicash.entity.app.LoanProduct;
import com.hengyuan.hicash.entity.app.PicParam;
import com.hengyuan.hicash.entity.mktApp.LoanLoan;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.CustTempApplyPicEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.entity.user.DdsjApplyCredit;
import com.hengyuan.hicash.entity.user.DdsjLimit;
import com.hengyuan.hicash.entity.user.HinsApplyCredit;
import com.hengyuan.hicash.entity.user.HinsLimit;
import com.hengyuan.hicash.entity.user.HyVipPeriod;
import com.hengyuan.hicash.entity.user.HyVipUser;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CheckCreditReq;
import com.hengyuan.hicash.parameters.request.user.CheckSupportAppReq;
import com.hengyuan.hicash.parameters.request.user.IdCardIsValidReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CheckLimitResp;
import com.hengyuan.hicash.parameters.response.user.CheckSupportAppResp;
import com.hengyuan.hicash.parameters.response.user.HinsCheckLimitResp;
import com.hengyuan.hicash.parameters.response.user.HinscheckCreditResp;
import com.hengyuan.hicash.parameters.response.user.IdCardIsValidResp;
import com.hengyuan.hicash.utils.ResourceUtils;

public class CheckSupportAppService implements RespService {

	private static Logger logger = Logger.getLogger(CheckSupportAppService.class);

	private ApplicationPayQuery payQuery = new ApplicationPayQuery();

	private ApplicationPayQuery applicationPayQuery = new ApplicationPayQuery();

	private CheckLimitService checkLimitService = new CheckLimitService();

	private CustomerQuery customerQuery = new CustomerQuery();
	
	private ProductQuery productQuery = new ProductQuery();
	
	private LoanloanQuery loanQuery = new LoanloanQuery();

	private String msg = "";
	private String isTiE = "N";// 默认不提额

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CheckSupportAppReq appReq = (CheckSupportAppReq) parmRequest;
		CheckSupportAppResp appResp = new CheckSupportAppResp();

		String resultCode = ResultCodes.NORMAL;
		appResp.setSuccess("true");

		try {

			// 远程调用
			//
			// Map<String,String>parmMap=new HashMap<String,String>();
			// parmMap.put("mobile", appReq.getMobile());
			//
			// ServiceConfigQuery configQuery = new ServiceConfigQuery();
			// ServiceConfigEntity serviceConfigEntity = configQuery
			// .queryServiceByCode(Consts.SERVICE_SJGS);
			//
			// String httpResp = HttpRemotePost.sendHttp(
			// serviceConfigEntity.getServiceUrl(), parmMap);
			// logger.info("手机号："+appReq.getMobile()+"归属地返回："+httpResp);
			//
			// 调用征信服务获取手机归属地
			// appResp = new Gson()
			// .fromJson(httpResp,new TypeToken<CheckSupportAppResp>()
			// {}.getType());
			//
			// if(appResp.getResultCode().equals(ResultCodes.NORMAL)){
			// String guishu=appResp.getMobileHome();
			// List guishuList=new ArrayList();
			//
			// guishuList.add("上海移动");
			// guishuList.add("上海联通");
			// guishuList.add("上海电信");
			// //非上海滴滴贷
			// if((!guishuList.contains(guishu))&&appReq.getIndustryCode().equals("DDCP")){
			// resultCode = ResultCodes.DDCP_NOTSH_UNSU;
			// appResp.setSuccess("false");
			// }

			
			/**默认所有身份证都未过期- 上生产，注意，注意，注意，注意：身份份证先默认不过期，等上线，需要删掉这个代码**/
			/*
			appResp.setIdcard_isexpired("N");
			// 返回个人头像照url
			CustTempApplyPicQuery custTempApplyPicQuery = new CustTempApplyPicQuery();
			List<CustTempApplyPicEntity> listl68 = custTempApplyPicQuery.queryInfoByUnPt(appReq.getUser_name(),"ZL168");
			if (listl68 == null || listl68.size() < 1) {
				appResp.setIdcard_ZL168url("");
			} else {
				appResp.setIdcard_ZL168url(ResourceUtils.getValue("pic_server_url") + listl68.get(0).getPicPath());
			}
			*/
	
			
			
			/**默认全部身份证都已经过期，所有人都需要重新上传。**/
			/*
			appResp.setIdcard_isexpired("Y");//已过期
			appResp.setIdcard_ZL168url("");
			*/
			 
			
			/**判断身份证是否过期--新需求：是否有身份证照片，如果已经过期，或者没有身份证照，需要重新上传身份证。如果不是，则不需要上传身份证- 2017年7月26日 15:56:53  宋亚要求更新**/  

			//默认进来为已过期
			appResp.setIdcard_isexpired("Y");//已过期
			appResp.setIdcard_ZL168url(""); //无个人头像照片
			
			CreateAppPayQuery appPayQuery = new CreateAppPayQuery(); 
			CustCustomer custCustomer =	appPayQuery.getCustCustomer(appReq.getUser_name());
			CustTempApplyPicQuery custTempApplyPicQuery = new CustTempApplyPicQuery(); 
			
			//判断身份有效期
			IdCardIsValidReq checkAppReq = new IdCardIsValidReq();
			checkAppReq.setUsername(appReq.getUser_name());
			checkAppReq.setApplyFrom(appReq.getApplyFrom());
			IdCardIsValidService idCardIsValidService = new IdCardIsValidService();
			IdCardIsValidResp valresp = (IdCardIsValidResp) idCardIsValidService.responseValue(checkAppReq);
			List<CustTempApplyPicEntity> listPic = custTempApplyPicQuery.queryInfoByUnPic(appReq.getUser_name());
			if(listPic !=null && listPic.size() >= 2){ 	
			if("Y".equals(valresp.getIsValid())){
				//未过期,返回相应图片
				
				if("H5".equals(appReq.getApplyFrom())){
					List<CustTempApplyPicEntity> listl02 = custTempApplyPicQuery.queryInfoByUnPt(appReq.getUser_name(), "ZL02");
					List<CustTempApplyPicEntity> listl03 = custTempApplyPicQuery.queryInfoByUnPt(appReq.getUser_name(), "ZL03");
					
					List<CustTempApplyPicEntity> listl12 = custTempApplyPicQuery.queryInfoByUnPt(appReq.getUser_name(), "ZL112");//手持身份证照
					appResp.setIdcard_ZL168url(ResourceUtils.getValue( "pic_server_url") + listl12.get(0).getPicPath());
					
					appResp.setIdcard_ZL02url(ResourceUtils.getValue( "pic_server_url") + listl02.get(0).getPicPath());
					appResp.setIdcard_ZL03url(ResourceUtils.getValue( "pic_server_url") + listl03.get(0).getPicPath());
					appResp.setIdcard_isexpired("N");
				}
				
				
				appResp.setPermanentAddressProvince(custCustomer.getPermanentAddressProvince());
				appResp.setPermanentAddressCity(custCustomer.getPermanentAddressCity());
				appResp.setPermanentAddressArea(custCustomer.getPermanentAddressArea());
				appResp.setPermanentAddressRaod(custCustomer.getPermanentAddressRaod());
				appResp.setName(custCustomer.getRealName());
				appResp.setNation(custCustomer.getNation());
				appResp.setIdCardValStartDate(custCustomer.getIdCardValStartDate());
				appResp.setIdCardValEndDate(custCustomer.getIdCardValEndDate());
				appResp.setIdentityNo(custCustomer.getIdentityNo());
				appResp.setIdcardFrom(custCustomer.getIdCardFrom());
		
				
				
			}
			//app
			if(!"H5".equals(checkAppReq.getApplyFrom())){
				boolean flag = false;
				
				List<CustTempApplyPicEntity> listl68 = custTempApplyPicQuery.queryInfoByUnPt(appReq.getUser_name(), "ZL168");//头像照
				if(listl68!=null && listl68.size()>0){
					appResp.setIdcard_ZL168url(ResourceUtils.getValue( "pic_server_url") + listl68.get(0).getPicPath());
				}
				
				
				//如果是app请求 如果最近单子是H5和pc 就不给照片
				
				ApplicationPay app = applicationPayQuery.queryAppPayByUserNameAndApp(appReq.getUser_name());
				if(app!=null){
					if(app.getApplyFrom()!=null && (app.getApplyFrom().equals("HTML5")||app.getApplyFrom().equals("PC"))){
						appResp.setIdcard_isexpired("Y");
						appResp.setIdcard_ZL02url("");
						appResp.setIdcard_ZL03url("");
					}else{
						
						ApplicationPicQuery appPicQuery = new ApplicationPicQuery();
						List<PicParam> pic02List = appPicQuery.queryAppPicByAppNoAndType(app.getApplicationNo(), "ZL02");
					    List<PicParam> pic03List = appPicQuery.queryAppPicByAppNoAndType(app.getApplicationNo(), "ZL03");
						
					    if(pic02List!=null && pic02List.size()>0 && pic03List!=null && pic03List.size()>0 && listl68!=null && listl68.size()>0){
						if(pic02List.get(0).getCaption().equals("APP") && pic03List.get(0).getCaption().equals("APP")){
							flag =true;
						}
					   }
					    if(flag){
					    	 if( !StringUtils.isEmpty(custCustomer.getIdCardValEndDate())){  //查询到过期日期
									SimpleDateFormat df= new SimpleDateFormat("yyyyMMdd");		//设置日期格式
									String nowTime = df.format(new Date());						// new Date()为获取当前系统时间
									int result = nowTime.compareTo(custCustomer.getIdCardValEndDate()); 
									if(result <= 0){
										appResp.setIdcard_isexpired("N");
										appResp.setIdcard_ZL02url(ResourceUtils.getValue( "pic_server_url") + pic02List.get(0).getPicPath());
										appResp.setIdcard_ZL03url(ResourceUtils.getValue( "pic_server_url") + pic03List.get(0).getPicPath());
									}
								}
					    }
					   
					}
				}else{
					appResp.setIdcard_isexpired("Y");
					appResp.setIdcard_ZL02url("");
					appResp.setIdcard_ZL03url("");
				}
				
				}
				
//				ApplicationPicQuery appPicQuery = new ApplicationPicQuery();
//			    if(app != null){
//			    	List<PicParam> pic02List = appPicQuery.queryAppPicByAppNoAndType(app.getApplicationNo(), "ZL02");
//					List<PicParam> pic03List = appPicQuery.queryAppPicByAppNoAndType(app.getApplicationNo(), "ZL03");
//					if(pic02List!=null && pic02List.size()>0 && pic03List!=null && pic03List.size()>0){
//						if(pic02List.get(0).getCaption().equals("APP") && pic03List.get(0).getCaption().equals("APP")){
//							flag =true;
//						}
//					}
//					
//					if(flag){
//						
//						if( !StringUtils.isEmpty(custCustomer.getIdCardValEndDate())){  //查询到过期日期
//							SimpleDateFormat df= new SimpleDateFormat("yyyyMMdd");		//设置日期格式
//							String nowTime = df.format(new Date());						// new Date()为获取当前系统时间
//							int result = nowTime.compareTo(custCustomer.getIdCardValEndDate()); 
//							if(result <= 0){
//								appResp.setIdcard_isexpired("N");
//								appResp.setIdcard_ZL02url(ResourceUtils.getValue( "pic_server_url") + pic02List.get(0).getPicPath());
//								appResp.setIdcard_ZL03url(ResourceUtils.getValue( "pic_server_url") + pic03List.get(0).getPicPath());
//							}
//						}
//					}else{
//						
//						appResp.setIdcard_isexpired("Y");//已过期
//					}
//			    }else{
//			    	
//					appResp.setIdcard_isexpired("Y");//已过期
//			    }
			}
			
		
		/*	
			CreateAppPayQuery appPayQuery = new CreateAppPayQuery(); 

			CustCustomer custCustomer =	appPayQuery.getCustCustomer(appReq.getUser_name());
			CustTempApplyPicQuery custTempApplyPicQuery = new CustTempApplyPicQuery(); 
			
			List<CustTempApplyPicEntity> listPic = custTempApplyPicQuery.queryInfoByUnPic(appReq.getUser_name());
	
			if(listPic !=null && listPic.size() >= 2){ 							//有身份证正反面照片
				if( !StringUtils.isEmpty(custCustomer.getIdCardValEndDate())){  //查询到过期日期
					SimpleDateFormat df= new SimpleDateFormat("yyyyMMdd");		//设置日期格式
					String nowTime = df.format(new Date());						// new Date()为获取当前系统时间
					int result = nowTime.compareTo(custCustomer.getIdCardValEndDate()); 
					if(result <= 0){
						appResp.setIdcard_isexpired("N");						//未过期
						//返回个人头像照url 
						List<CustTempApplyPicEntity> listl68 = custTempApplyPicQuery.queryInfoByUnPt(appReq.getUser_name(), "ZL168");
						List<CustTempApplyPicEntity> listl02 = custTempApplyPicQuery.queryInfoByUnPt(appReq.getUser_name(), "ZL02");
						List<CustTempApplyPicEntity> listl03 = custTempApplyPicQuery.queryInfoByUnPt(appReq.getUser_name(), "ZL03");
						if(listl68==null||listl68.size() > 0){
							appResp.setIdcard_ZL168url(ResourceUtils.getValue( "pic_server_url") + listl68.get(0).getPicPath());
						}
						if(listl02==null||listl02.size() > 0){
							appResp.setIdcard_ZL02url(ResourceUtils.getValue( "pic_server_url") + listl02.get(0).getPicPath());
						}
						if(listl03==null||listl03.size() > 0){
							appResp.setIdcard_ZL03url(ResourceUtils.getValue( "pic_server_url") + listl03.get(0).getPicPath());
						}
						appResp.setPermanentAddressProvince(custCustomer.getPermanentAddressProvince());
						appResp.setPermanentAddressCity(custCustomer.getPermanentAddressCity());
						appResp.setPermanentAddressArea(custCustomer.getPermanentAddressArea());
						appResp.setPermanentAddressRaod(custCustomer.getPermanentAddressRaod());
						appResp.setName(custCustomer.getRealName());
						appResp.setNation(custCustomer.getNation());
						appResp.setIdCardValStartDate(custCustomer.getIdCardValStartDate());
						appResp.setIdCardValEndDate(custCustomer.getIdCardValEndDate());
						appResp.setIdentityNo(custCustomer.getIdentityNo());
						appResp.setIdcardFrom(custCustomer.getIdCardFrom());
						
					}
				}
			}*/
			
			

			// 2017-06-07:滴滴贷，秒贷3期，H5不能申请
			String applyFrom = appReq.getApplyFrom();
			if (!StringUtils.isEmpty(applyFrom)) {
				List<String> regCodeList = new ArrayList<String>();
//				regCodeList.add(Consts.INDUSTRY_MDCP);
				regCodeList.add(Consts.INDUSTRY_DDCP);
				if (applyFrom.equals("H5") && regCodeList.contains(appReq.getIndustryCode()) && appReq.getPeriods().equals("3")) {

					appResp.setResultCode(ResultCodes.CHECK_H5_CANNOT_APPLY);
					appResp.setSuccess("false");

					return appResp;
				}

			}

			// 秒贷学生和滴滴学生，不支持申请
			List<String> regCodeList = new ArrayList<String>();
//			regCodeList.add(Consts.INDUSTRY_MDCP);
//			regCodeList.add(Consts.INDUSTRY_DDCP);
			if (regCodeList.contains(appReq.getIndustryCode()) && appReq.getCustType().equals(Consts.CUSTTYPE_KHL1)) {
				appResp.setResultCode(ResultCodes.CHECK_MDDD_KHL1_APPLY);
				appResp.setSuccess("false");

				return appResp;
			}

			if (payQuery.queryAppByUserHyIndustry(appReq.getUser_name(), appReq.getIndustryCode()).size() > 0) {

				appResp.setResultCode(ResultCodes.MONTH_HYIN_REGIST);
				appResp.setSuccess("false");

				return appResp;
			}
			
//			if (!Consts.INDUSTRY_VIPD.equals(appReq.getIndustryCode())) {
//				// 查询客户在审核的单子
//				List<ApplicationPay> appListp = new ArrayList<ApplicationPay>();
//	
//				appListp = payQuery.queryAppVipSh(appReq.getUser_name());
//	
//				// 查询VIP贷在审批中
//				if (appListp.size() > 0) {
//	
//					appResp.setResultCode(ResultCodes.CREATEAPP_REPEAMD_FAIL);
//					appResp.setSuccess("false");
//	
//					return appResp;
//	
//				}
//			}
//			
			
			// 2017-02-22 vip贷的进件约束
			if (Consts.INDUSTRY_VIPD.equals(appReq.getIndustryCode())) {

				if (appReq.getPeriods().equals("12")) {
					appResp.setResultCode(ResultCodes.CHECK_VIPD_12_APPLY);
					appResp.setSuccess("false");
					return appResp;
				}
				
				HyVipUserQuery hyVipUserQuery = new HyVipUserQuery();
				HyVipPeriodQuery hyVipPeriodQuery = new HyVipPeriodQuery();

				// VIP贷拒绝一个月
				if (payQuery.queryAppByUserHyIndustry(appReq.getUser_name(), appReq.getIndustryCode()).size() > 0) {

					appResp.setResultCode(ResultCodes.MONTH_HYIN_REGIST);
					appResp.setSuccess("false");

					return appResp;
				}

				// 查询VIP客户在审核的单子
				List<ApplicationPay> appList = new ArrayList<ApplicationPay>();

				appList = payQuery.queryAppSh(appReq.getUser_name());

				// 查询VIP贷在审批中
				if (appList.size() > 0) {

					appResp.setResultCode(ResultCodes.CREATEAPP_REPEAMD_FAIL);
					appResp.setSuccess("false");

					return appResp;

				}

				List<ApplicationPay> notVipD = payQuery.queryAppNotVipD(appReq.getUser_name());

				// 非VIP贷在审批中，放款中，还款中
				if (notVipD != null && notVipD.size() > 0) {
					appResp.setResultCode(ResultCodes.UNCLEARED_APP);
					appResp.setSuccess("false");

					return appResp;
				}
				
				List<HyVipUser> hyVipUserList = hyVipUserQuery.isCreditExtension(appReq.getUser_name());

				// 申请期数不在可允许期数内
				// 如果hyVipPeriod表没有期数，就去hyVipUser查初期期数
				List<HyVipPeriod> hyVipPeriodList = hyVipPeriodQuery.findPeriodsByUserName(appReq.getUser_name());

				List<String> list = new ArrayList<String>();

				if (hyVipUserList != null && hyVipUserList.size() > 0 && hyVipUserList.get(0).getInitPeriod() != null
						&& !hyVipUserList.get(0).getInitPeriod().equals("")) {

					list.add(String.valueOf(hyVipUserList.get(0).getInitPeriod()));
				}

				if (hyVipPeriodList != null && hyVipPeriodList.size() > 0) {

					for (HyVipPeriod pr : hyVipPeriodList) {
						if (pr.getPeriod() != null && !pr.getPeriod().equals("")) {
							list.add(String.valueOf(pr.getPeriod()));
						}
					}
				}
				
				LoanProduct loanProduct = productQuery.queryCreditProductById(appReq.getLoanProductId());
				
				if(StringUtils.isEmpty(loanProduct.getCreditday())){
					// 判断申请期数不在可允许期数内
					if (!list.contains(appReq.getPeriods())) {
						appResp.setResultCode(ResultCodes.Not_Allow_Periods);
						appResp.setSuccess("false");

						return appResp;
					}
				}
				
				// 可用额度OK
				if (!appReq.getTranPrice().equals("")) {

					AvailbleCreditQuery availbleCreditQuery = new AvailbleCreditQuery();

					List<ApplicationPay> VipDList = payQuery.queryVipDList(appReq.getUser_name());
					// 拼接流水号，sum（已提额度）
					String useAppNo = "";

					// 剩余额度
					BigDecimal syAmount = BigDecimal.ZERO;

					// 已提额度
					BigDecimal applyAmount = BigDecimal.ZERO;

					// 已还本金
					BigDecimal prinPaid = BigDecimal.ZERO;

					if (VipDList.size() > 0) {
						for (int i = 0; i < VipDList.size(); i++) {
							if (StringUtils.isEmpty(useAppNo)) {
								useAppNo = "'" + VipDList.get(i).getApplicationNo() + "'";
								applyAmount = new BigDecimal(VipDList.get(i).getApplyAmount());
							} else {
								useAppNo = useAppNo + ",'" + VipDList.get(i).getApplicationNo() + "'";
								applyAmount = applyAmount.add(new BigDecimal(VipDList.get(i).getApplyAmount()));
							}
						}

						if (availbleCreditQuery.getPrinPaid(useAppNo, "") == null
								|| availbleCreditQuery.getPrinPaid(useAppNo, "").equals("")) {
							prinPaid = BigDecimal.ZERO;
						} else {
							prinPaid = new BigDecimal(availbleCreditQuery.getPrinPaid(useAppNo, ""));
						}
					}
					// 可用额度 = 授信额度 - 已提额度 + 已还本金
					syAmount = hyVipUserList.get(0).getAmount().subtract(applyAmount).add(prinPaid);
					System.out.println("剩余额度++++++++++++++++++++++" + syAmount);
					// 四舍五入 *100取整
					int kyAmount = syAmount.intValue() / 100 * 100;
					System.out.println("可用额度++++++++++++++++++++++" + kyAmount);

					if (Integer.valueOf(appReq.getTranPrice()) > kyAmount) {
						appResp.setResultCode(ResultCodes.Not_Sufficient_Funds_For_VIPD);
						appResp.setSuccess("false");

						return appResp;
					}
				}

				appResp.setResultCode(resultCode);
				appResp.setSuccess("true");

				// 没有授信不能通过 0为没有授信
				// 没有授信记录，客户授信为空或者为0
				if (hyVipUserList == null || hyVipUserList.size() == 0 || hyVipUserList.get(0).getIsAuth() == null
						|| hyVipUserList.get(0).getIsAuth().equals("") || hyVipUserList.get(0).getIsAuth() == 0) {

					appResp.setIsCredit("0");

				} else {

					// 已授信
					appResp.setIsCredit("1");
				}
				// 2017-03-26司机贷的进件约束
			} else if (Consts.INDUSTRY_DDSJ.equals(appReq.getIndustryCode())) {
				
				
				//一期金额不能超过9000
				if("1".equals(appReq.getPeriods())){
					if (Integer.valueOf(appReq.getTranPrice()) > 9000) {
						appResp.setResultCode(ResultCodes.DDSJ_LIMIT_9000);
						appResp.setSuccess("false");
						return appResp;
					}
				}else if("3".equals(appReq.getPeriods())){
					if (Integer.valueOf(appReq.getTranPrice()) > 10000) {
						appResp.setResultCode(ResultCodes.DDSJ_LIMIT_10000);
						appResp.setSuccess("false");
						return appResp;
					}
				}else if("6".equals(appReq.getPeriods())){
					if (Integer.valueOf(appReq.getTranPrice()) < 10000 || Integer.valueOf(appReq.getTranPrice()) > 50000) {
						appResp.setResultCode(ResultCodes.DDSJ_LIMIT_1_5);
						appResp.setSuccess("false");
						return appResp;
					}
				}
				

				logger.info("司机贷进件,用户名:" + appReq.getUser_name() + "开始验证是否可以提现,是否要跳转授信页面");
				DdsjLimitQuery ddsjLimitQuery = new DdsjLimitQuery();
				DdsjLimit ddsjLimit = ddsjLimitQuery.queryLimit(appReq.getUser_name());
				CustomerEntity customer = customerQuery.queryCustomerByUserName(appReq.getUser_name());
				DdsjApplyCreditQuery ddsjApplyCreditQuery=new DdsjApplyCreditQuery();
				List<DdsjApplyCredit> ddsjApplyCredit = ddsjApplyCreditQuery
						.queryAppByBeforeMonth(appReq.getUser_name());
				if (ddsjLimit == null) {
					appResp.setIsCash("N");
					appResp.setIsJump("Y");
					// resultCode =ResultCodes.DDSJ_LIMIT_INFO_1;
					msg = "您尚未完成车主认证，认证后方可申请。";
					appResp.setStrButton("立即认证");
					appResp.setStrButton_url("ljrz");
					logger.info("司机贷进件,用户名:" + appReq.getUser_name() + "查询额度信息表为空,需要授信,并且不能提现");

				} else {
					appResp.setIsJump(ddsjLimit.getIs_credit());
					// 根据用户名查询是否有一个月内被拒绝的订单(DDSJ)
					List<ApplicationPay> applicationPaylist = applicationPayQuery
							.queryAppByBeforeMonthDdsj(appReq.getUser_name(), "DDSJ");
					LoanStatusQuery loanStatusQuery = new LoanStatusQuery();
					// 查询是否逾期
					LoanLoanAccEntity vo = loanStatusQuery.queryStatus(appReq.getUser_name(), appReq.getIndustryCode());

					// 查询司机贷是否在审核中的单子
					List<ApplicationPay> ingAppList = payQuery.queryAppIng(appReq.getUser_name());

					// 查询非司机贷在审核中或还款中的订单
					List<ApplicationPay> ddppList = payQuery.queryNoDdsjAppIng(appReq.getUser_name());

					
					
					if ("Y".equals(ddsjLimit.getIs_credit())) {
						appResp.setIsCash("N");
						appResp.setIsJump("Y");
						// resultCode =ResultCodes.DDSJ_LIMIT_INFO_1;
						msg = "您尚未完成车主认证，认证后方可申请。";
						appResp.setStrButton("立即认证");
						appResp.setStrButton_url("ljrz");

					} else if (ddsjLimit.getSx_success_time() != null
							&& Integer.valueOf(ddsjLimit.getSx_success_time()) < 1) {
						appResp.setIsCash("N");
						appResp.setIsJump("Y");
						// resultCode =ResultCodes.DDSJ_LIMIT_INFO_1;
						msg = "您尚未完成车主认证，认证后方可申请。";
						appResp.setStrButton("立即认证");
						appResp.setStrButton_url("ljrz");
						// 2.判断是否授信审核中
					} else if ("".endsWith(ddsjLimit.getStatus())
							|| Consts.DDSJ_CREDIT_TYPE_SXZ.equals(ddsjLimit.getStatus())) {
						logger.info("司机贷进件,用户名:" + appReq.getUser_name() + "授信审核中不能提现");
						appResp.setIsCash("N");
						// resultCode =ResultCodes.DDSJ_LIMIT_INFO_2;
						msg = "您的车主身份正在认证中，请稍后再来。";
						appResp.setStrButton("本车主知道了");
						appResp.setStrButton_url("zdl");
						// 3.判断是否有逾期
					} else if (vo == null || Integer.parseInt(vo.getLoanStatus()) > 0) {
						logger.info("司机贷进件,用户名:" + appReq.getUser_name() + "一个月内有逾期不能提现");
						appResp.setIsCash("N");
						// resultCode =ResultCodes.DDSJ_LIMIT_INFO_3;
						msg = "您的车主身份已过期，请重新认证。";
						appResp.setIsJump("Y");
						appResp.setStrButton("立即认证");
						appResp.setStrButton_url("ljrz");
						// 判断是否 是否在审核中的单子
					} else if (ingAppList.size() > 0) {
						// msg="您当前还有一笔未完成的产品";
						logger.info("您当前还有一笔未完成的产品");
						resultCode = ResultCodes.USER_APP_ING;
						appResp.setIsCash("N");
						appResp.setIsJump("N");
						appResp.setSuccess("false");
					}else if (ddppList.size() > 0) {
						// msg="您当前还有一笔未完成的产品";
						logger.info("您当前还有一笔未完成的产品");
						resultCode = ResultCodes.USER_APP_ING;
						appResp.setIsCash("N");
						appResp.setIsJump("N");
						appResp.setSuccess("false");
					} 
//					else if (applicationPaylist.size() > 0) {
//						resultCode = ResultCodes.MONTH_HYIN_REGIST;
//						// msg="您在一个月内申请过该产品并被拒绝，您可以去首页选择其他产品。";
//						appResp.setIsCash("N");
//						appResp.setIsJump("N");
//					}
					// 授信被拒一个月内，不能授信
					else if (ddsjApplyCredit != null && ddsjApplyCredit.size() > 0) {
						logger.info("司机贷进件,用户名:" + appReq.getUser_name() + "本月授信认证失败");
						appResp.setIsCash("N");
						// resultCode =ResultCodes.DDSJ_LIMIT_INFO_3;
						msg = "您本月认证失败，不可提现。可以尝试申请其他产品。";
						appResp.setIsJump("N");
						appResp.setStrButton("看看别的去");
						appResp.setStrButton_url("index");
					}					
					else {
						logger.info(
								"司机贷进件,用户名:" + appReq.getUser_name() + "判断授信额度是否大于0,amount:" + ddsjLimit.getAmount());
						// //查询已使用额度
						// List<ApplicationPay>
						// queryAmount=payQuery.queryAmount(appReq.getUser_name());
						// //已用额度
						// BigDecimal applyAmount=BigDecimal.ZERO;
						// if (queryAmount.size() > 0) {
						// for (int i = 0; i < queryAmount.size(); i++) {
						// applyAmount = applyAmount.add(new
						// BigDecimal(queryAmount.get(i).getApplyAmount()));
						// }
						// }

						// 申请金额,计算上本次额度
						BigDecimal applyAmount = BigDecimal.ZERO;// 初始为本地申请金额

						// 剩余额度
						BigDecimal syAmount = BigDecimal.ZERO;

						// 还款后返还金额
						BigDecimal returnAmt = BigDecimal.ZERO;
						List<ApplicationPay> list = payQuery.queryAppPayList(appReq.getUser_name(),
								appReq.getIndustryCode());

						if (list != null && list.size() > 0) {
							for (int i = 0; i < list.size(); i++) {

								applyAmount = applyAmount.add(new BigDecimal(list.get(i).getApplyAmount()));

								// String amount =
								// availbleCreditQuery.getNotPayAmt(list
								// .get(i).getApplicationNo(), null);
								//
								// BigDecimal bigDecimal = BigDecimal.ZERO;
								// if (!StringUtils.isEmpty(amount)) {
								// bigDecimal = new BigDecimal(amount);
								// if (bigDecimal.compareTo(BigDecimal.ZERO) ==
								// 0) {
								// returnAmt = returnAmt.add(new BigDecimal(list
								// .get(i).getApplyAmount()));
								// }
								// }

							}

						}
						if (new BigDecimal(ddsjLimit.getAmount()).compareTo(applyAmount) >= 0) {
							syAmount = new BigDecimal(ddsjLimit.getAmount()).subtract(applyAmount).add(returnAmt);
						}
						logger.info("剩余额度:" + syAmount);
						int kyAmount = syAmount.intValue() / 100 * 100;
						logger.info("可用额度:" + kyAmount);
						// 3.判断授信额度是否>0
						if (new BigDecimal(ddsjLimit.getAmount()).compareTo(BigDecimal.ZERO) == 1
								&& syAmount.compareTo(BigDecimal.ZERO) == 1) {
							logger.info("用户名" + appReq.getUser_name() + "有可用额度");
							appResp.setIsCash("Y");
						} else {
							// 如果额度为0,再判断是否可以提额
							CheckLimitResp resp = new CheckLimitResp();
							resp = checkLimitService.getLimt(ddsjLimit, appReq.getUser_name(), resp);
							appResp.setIsJump(resp.getIs_return());
							appResp.setIsCash("N");
							if ("N".equals(resp.getIs_limit())) {
								// resultCode =ResultCodes.DDSJ_LIMIT_INFO_5;
								msg = "已提额或最高额度";
								appResp.setStrButton("看看别的去");
								appResp.setStrButton_url("index");
							} else {
								// resultCode =ResultCodes.DDSJ_LIMIT_INFO_4;
								msg = "您的额度已用完，但可以提额。";
								appResp.setStrButton("立即提额");
								appResp.setStrButton_url("ljtx");
							}

						}
					}
				}

				// 不支持学生
				if (customer != null) {
					if ("KHL1".equals(customer.getCustType())) {
						// resultCode =ResultCodes.DDSJ_LIMIT_INFO_6;
						msg = "网约车司机贷暂不支持学生申请，请选择其他产品";
						appResp.setIsCash("N");
						appResp.setStrButton("看看别的去");
						appResp.setStrButton_url("index");
					}
				}
				appResp.setBut_msg_1(msg);
			} else if (Consts.HINS.equals(appReq.getIndustryCode())) {
				
				appResp = checkHins(appReq, appResp);
				
			} else {
				
				//嗨女神的提现的额度累计合同金额大于等于1000，不能申请其他现金类产品（滴答贷，嗨秒贷，嗨钱来）2017年8月1日 10:37:58 - 
				List<LoanLoan> loanList = loanQuery.queryLoanAmount(appReq.getUser_name());
				Double amout = 0d;
				for(LoanLoan loan:loanList){
					amout += Double.valueOf(loan.getAmount());
				}
				if(amout >= Consts.HINS_DISABLE_AMOUNT){
					//现有嗨女神待还订单，请还款后再尝试申请
					appResp.setSuccess("false");
					appResp.setResultCode(ResultCodes.IS_DISABLE_MONEY);
					appResp.setResultMsg("现有嗨女神待还订单，请还款后再尝试申请");
					return appResp;
				}
				
				
				// 滴滴贷学生
				// 一个月内被拒绝
				List<ApplicationPay> monthRegisList = payQuery.queryAppByUserHyIndustry(appReq.getUser_name(),
						appReq.getIndustryCode());
				// 查询是否在审核中的单子
				List<ApplicationPay> ingAppList = payQuery.queryAppIng(appReq.getUser_name());
				// 查询vip,ddsj在还款中
				List<ApplicationPay> vipDdsjList = payQuery.queryVipDdsj(appReq.getUser_name());

//				if (appReq.getIndustryCode().equals("DDCP") && appReq.getCustType().equals(Consts.CUSTTYPE_KHL1)) {
//					resultCode = ResultCodes.DDCP_STU_UNSU;
//					appResp.setSuccess("false");
//
//					// 滴答贷，学生，信用卡
//				} else 
					
				if (appReq.getIndustryCode().equals("LDDD") && appReq.getCustType().equals(Consts.CUSTTYPE_KHL1)
						&& appReq.getIs_type().equals("Y")) {
					resultCode = ResultCodes.LDDD_STU_DJK;
					appResp.setSuccess("false");

				} else if (monthRegisList.size() > 0) {// 1个 月内被拒绝

					resultCode = ResultCodes.MONTH_HYIN_REGIST;
					appResp.setSuccess("false");

				} else if (ingAppList.size() > 0) {
					resultCode = ResultCodes.USER_APP_ING;

					appResp.setSuccess("false");
				}else if (vipDdsjList.size() > 0) {
					resultCode = ResultCodes.USER_APP_ING;

					appResp.setSuccess("false");
				} else {
					resultCode = ResultCodes.NORMAL;
					appResp.setSuccess("true");
				}
			}

		} catch (Exception e) {
			logger.error("手机号：" + appReq.getMobile() + "判断是否符合申请异常：", e);
			logger.info("手机号：" + appReq.getMobile() + "判断是否符合申请异常：", e);
			resultCode = ResultCodes.GS_ZCDD_EXCEPTION;
			appResp.setSuccess("false");
		}

		appResp.setResultCode(resultCode);
		return appResp;
	}

	/**
	 * 跑批：条件包含：未授信。授信成功超过三个月未分期购。授信成功，最后一次还款超过三个月。 说明1.未认证，符合条件，进入认证流程。
	 * 
	 * 说明2.身份为男性，弹出提示框，告知仅限女性
	 * 
	 * 说明3.已认证失败过，并且一个月内，告知暂时不可授信，过段时间再来。
	 * 
	 * 说明4.认证审核中，弹出提示框，告知审核中，稍后再来。
	 * 
	 * 说明5.已认证，但可用额度不够，本月未提额，弹出提示框，有提额按钮，和返回列表页按钮。
	 * 
	 * 说明6.已认证，额度不够，本月已提额，弹出提示框提示超出可用额度，有返回列表按钮
	 * 
	 * 说明7.已认证，额度够，进入订单确认页。 未授信：去认证；授信了：去购买
	 * 
	 * @param appReq
	 */
	public CheckSupportAppResp checkHins(CheckSupportAppReq appReq, CheckSupportAppResp appResp) {

		//嗨女神新增逻辑：只要有现金类产品（滴答贷，嗨秒贷，嗨钱来）在还款中，嗨女神累计提现只能小于1000的额度
		//查询（滴答贷，嗨秒贷，嗨钱来）还款中的单子
		List<ApplicationPay> listApp = payQuery.queryAppExistAuditing(appReq.getUser_name());
		if(listApp != null && listApp.size() > 0){ //限制额度
			Double  tranPriceNum = 0d;
			//查询是否有嗨女神的订单，在审核中和还款中。
			List<ApplicationPay> hinslistApp = payQuery.queryHinsApp(appReq.getUser_name());
			for(ApplicationPay hinsApp:hinslistApp){
				tranPriceNum += Double.valueOf(hinsApp.getTranPrice());
			}
			//累计当前用户嗨女神的申请金额
			tranPriceNum += Double.valueOf(appReq.getTranPrice());
			//如果申请总金额小于1000，则可以继续申请，反之不允许继续申请
			if(Consts.HINS_DISABLE_AMOUNT < tranPriceNum){
				//现有待还订单过多，请还款后再尝试购买
				appResp.setBut_msg_1("现有待还订单过多，请还款后再尝试购买");
				appResp.setStrButton("去看看别的");
				appResp.setStrButton_url("hinslb");
				appResp.setIsCash("N");
				return appResp;
			}
		}

		
		
		String hiUserName = appReq.getUser_name();

		HinsLimitQuery hinsLimitQuery = new HinsLimitQuery();

		// 逻辑：首页未授信情况的情况：limit为空，或者limit中iscredit=Y，iscredit=N；不需要授信，并且额度足够，则去体现：提现判断，是否符合体现条件（自己提现），//额度不足：去提额（刘东提额接口）

		HinsLimit hinsLimit = hinsLimitQuery.queryLimit(hiUserName);
		LoanStatusQuery loanStatusQuery = new LoanStatusQuery();
		// 查询是否逾期
		LoanLoanAccEntity vo = loanStatusQuery.queryStatus(hiUserName, appReq.getIndustryCode());
		// 根据用户名查询是否有一个月内被拒绝的订单(嗨女神)
		List<ApplicationPay> applicationPayNvlist = applicationPayQuery.queryAppByBeforeMonthDdsj(appReq.getUser_name(),
				"HINS");

		// 1：是否能跳转到授信页面，2：是否能授信
		if (hinsLimit == null) {// 从未授信过，跳转到授信页面

			// 开始授信，说明1
			appResp = sxIng(hiUserName, appResp);
			appResp.setBut_msg_1(msg);
			logger.info("嗨女神产品是否支持,用户名:" + hiUserName + "查询额度信息表为空,需要授信,并且不能提现");

		} else {// 已经授信过，判断是否要再次授信

			// 查询用户最近一笔授信单
			HinsApplyCreditQuery hinsApplyCreditQuery = new HinsApplyCreditQuery();
			HinsApplyCredit hinsCredit = hinsApplyCreditQuery.queryRefuseApp(hiUserName);

			if ("Y".equals(hinsLimit.getIs_credit())) {// 如果需要授信

				// 开始授信
				appResp = sxIng(hiUserName, appResp);
				appResp.setBut_msg_1(msg);
				logger.info("嗨女神产品是否支持,用户名:" + hiUserName + "查询额度信息表为空,需要授信,并且不能提现");
			} else if (hinsCredit != null && "STATUS21".equals(hinsCredit.getStatus())) {// 最近一次授信失败。,再去授信

				// 开始授信
				appResp = sxIng(hiUserName, appResp);
				appResp.setBut_msg_1(msg);
				logger.info("嗨女神产品是否支持,用户名:" + hiUserName + "最近一次授信被拒绝，需要授信");
			} else if (hinsLimit.getSx_success_time() != null && Integer.valueOf(hinsLimit.getSx_success_time()) < 1) {// 从未认证成功过，（授信自己取消）
				// 开始授信
				appResp = sxIng(hiUserName, appResp);
				appResp.setBut_msg_1(msg);
				logger.info("嗨女神产品是否支持,用户名:" + hiUserName + ",授信成功次数为0，需要授信");
			} else if (hinsCredit != null && "SHNODE".equals(hinsCredit.getNode())
					&& "SX".equals(hinsCredit.getCredit_type())) {
				// 开始授信
				appResp = sxIng(hiUserName, appResp);
				appResp.setBut_msg_1(msg);
				logger.info("嗨女神产品是否支持,用户名:" + hiUserName + ",授信审核中，需要调用授信");

			} else {// 已认证，判断提现

				logger.info("嗨女神授信,用户名:" + hiUserName + "判断授信额度是否大于0,amount:" + hinsLimit.getAmount());

				// 申请金额，本地也计算上
				BigDecimal applyAmount = new BigDecimal(appReq.getTranPrice());

				// 剩余额度
				BigDecimal syAmount = BigDecimal.ZERO;

				// 还款后返还金额
				BigDecimal returnAmt = BigDecimal.ZERO;
				List<ApplicationPay> list = payQuery.queryAppPayList(hiUserName, appReq.getIndustryCode());

				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {

						applyAmount = applyAmount.add(new BigDecimal(list.get(i).getApplyAmount()));
					}

				}
				if (new BigDecimal(hinsLimit.getAmount()).compareTo(applyAmount) >= 0) {
					syAmount = new BigDecimal(hinsLimit.getAmount()).subtract(applyAmount).add(returnAmt);
				}
				logger.info("嗨女神产品是否支持,用户名:" + hiUserName + "，剩余额度:" + syAmount);

				int kyAmount = syAmount.intValue() / 100 * 100;

				logger.info("嗨女神产品是否支持,用户名:" + hiUserName + "，可用额度:" + kyAmount);

				// 已认证，额度够，进入订单确认页。
				if (new BigDecimal(hinsLimit.getAmount()).compareTo(BigDecimal.ZERO) == 1
						&& syAmount.compareTo(BigDecimal.ZERO) == 1) {
					// 3.判断授信额度是否>0，剩余额度大于0，可以申请提现
					logger.info("嗨女神产品是否支持,用户名:" + hiUserName + "，有可用额度，进入订单确认页");

					appResp.setIsCash("Y");

					// appResp.setBut_msg_1(msg);

				} else {// 额度不够,调用调额接口

					// 说明5.已认证，但可用额度不够，本月未提额，弹出提示框，有提额按钮，和返回列表页按钮。
					// 说明6.已认证，额度不够，本月已提额，弹出提示框提示超出可用额度，有返回列表按钮
					HinscheckLimitService checkLimitService = new HinscheckLimitService();
					HinsCheckLimitResp checkLimitResp = new HinsCheckLimitResp();

					// 女神，您的可用额度不足，且当月不可提额或已是最高额度
					// 按钮：去看看别的
					// ggq
					// 商品列表页
					boolean isTeInterFace = false;
					HinsLimit hivo = hinsLimitQuery.queryLimit(hiUserName);
					if (hivo != null) {

						checkLimitResp = checkLimitService.getLimt(hinsLimit, hiUserName, checkLimitResp);// 刘东提额接口

						if (new BigDecimal(hivo.getAmount()).compareTo(new BigDecimal(20000)) == 0
								|| checkLimitResp.getIs_limit().equals("N")) {

							checkLimitResp.setResultMsg("女神，您的可用额度不足，且当月不可提额或已是最高额度");
							checkLimitResp.setBut_msg_1("去看看别的");
							checkLimitResp.setBut_url_1("hinslb");
							checkLimitResp.setIs_return("N");
							isTeInterFace = true;
						}
					}

					if (!isTeInterFace) {

						checkLimitResp = checkLimitService.getLimt(hinsLimit, hiUserName, checkLimitResp);// 刘东提额接口,如果能提额刘东返回的resultmsg是Sucess，这边需要转换
						logger.info("嗨女神产品是否支持,用户名:" + hiUserName + ",调用提额接口，" + checkLimitResp.toJson());

						if (checkLimitResp.getIs_limit().equals("Y")) {// 如果可以提额
							checkLimitResp.setResultMsg("女神，您的可用额度不足，快去提额吧~");
							checkLimitResp.setBut_msg_1("立即提额");
							checkLimitResp.setIs_return("N");
							isTiE = "Y";
						} else {// 如果不能提额，提示刘东的消息

							checkLimitResp.setIs_return("N");
						}

					}
					msg = checkLimitResp.getResultMsg();
					appResp.setBut_msg_1(msg);
					appResp.setStrButton(checkLimitResp.getBut_msg_1());
					appResp.setStrButton_url(checkLimitResp.getBut_url_1());
					appResp.setIsCash("N");
					appResp.setIsJump(checkLimitResp.getIs_return());// 跳转到授信页面
					appResp.setIsTiE(isTiE);
					logger.info("嗨女神产品是否支持,用户名:" + hiUserName + ",可用额度不够返回，" + checkLimitResp.toJson());
				}
			}

		}

		return appResp;

	}

	// 去授信
	public CheckSupportAppResp sxIng(String userName, CheckSupportAppResp appResp) {

		HinsCheckCreditService service = new HinsCheckCreditService();
		CheckCreditReq checkReq = new CheckCreditReq();
		checkReq.setUsername(userName);
		// 判断能否授信（说明2，说明3，说明4）
		HinscheckCreditResp hcheckCreditResp = (HinscheckCreditResp) service.responseValue(checkReq);
		logger.info("嗨女神产品是否支持,用户名:" + userName + ",调用授信接口，" + hcheckCreditResp.toJson());
		appResp.setIsCash("N");
		appResp.setIsJump(hcheckCreditResp.getIs_auth());// 是否可以授信

		if ("N".equals(hcheckCreditResp.getIs_auth())) {// 如果不可授信

			logger.info("嗨女神产品是否支持,用户名:" + userName + ",调用授信接口，返回不能授信，所以不支持");
			msg = hcheckCreditResp.getResultMsg();
			appResp.setStrButton(hcheckCreditResp.getBut_msg_1());
			appResp.setStrButton_url(hcheckCreditResp.getBut_url_1());
		} else {// 可以授信
			//msg = "想买心仪美物？女神需要先认证获取分期购额度后方可购买哟~";
			msg="您尚未完成女神认证，请先认证开启狂购模式";
			appResp.setStrButton("立即认证");
			appResp.setStrButton_url(hcheckCreditResp.getBut_url_1());
		}

		return appResp;
	}
	/**
	 * 不支持 期数map
	 * key 产品industryCode
	 * value 不支持期数 多个英文逗号隔开
	 */
	private static Map<String, String[]> noSupporMap=new HashMap<String, String[]>();
	static {
		noSupporMap.put(IndustryConsts.INDUSTRY_MDCP, new String[]{"6","9","12"});
		noSupporMap.put(IndustryConsts.INDUSTRY_VIPD, new String[]{"6","9","12"});
	}
	
	/**
	 * 验证是否支持该产品
	 * @param checkAppReq
	 * @return
	 */
	public CheckSupportAppResp isSupportProduct(CheckSupportAppReq checkAppReq ){
		CheckSupportAppResp checkSupportAppResp=new CheckSupportAppResp();
		
		checkSupportAppResp.setResultCode(ResultCodes.NORMAL);
		
		String industryCode=checkAppReq.getIndustryCode();
		
		//滴答贷紧急下线
		if(IndustryConsts.INDUSTRY_LDDD.equals(industryCode)){
			checkSupportAppResp.setResultCode(ResultCodes.CHECK_LDDD_BEING_ADJUSTED);
			return checkSupportAppResp;
		}
		//精英分期产品调整中
//		if(IndustryConsts.INDUSTRY_JYFQ.equals(industryCode)){
//			checkSupportAppResp.setResultCode(ResultCodes.CHECK_JYFQ_BEING_ADJUSTED);
//			return checkSupportAppResp;
//		}
		//验证 期数是否支持（6，9，12）
//		String[] noSupporPerArray=noSupporMap.get(industryCode);
//		if(noSupporPerArray!=null){
//			for(String s:noSupporPerArray){
//				if(s.equals(checkAppReq.getPeriods())){
//					checkSupportAppResp.setResultCode(ResultCodes.CHECK_NO_SUPPORORT_PER);
//					return checkSupportAppResp;
//				}
//			}
//		}
		//6，9，12 期数双层校验  产品id=0的 肯定是 6，9，12 双层校验目的为了解决app端有的产品传期数了有的没传
//		if("0".equals(checkAppReq.getLoanProductId())){
//			checkSupportAppResp.setResultCode(ResultCodes.CHECK_NO_SUPPORORT_PER);
//			return checkSupportAppResp;
//		}
		
		
		return checkSupportAppResp;
	}
	

	public static void main(String[] args) {

		String aa ="3000.00";
		Double  a = Double .valueOf(aa);
		System.out.println(a);

	}

}
