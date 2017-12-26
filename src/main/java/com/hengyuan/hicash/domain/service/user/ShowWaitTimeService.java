package com.hengyuan.hicash.domain.service.user;


import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.app.AcctPayQuery;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.app.ProductQuery;
import com.hengyuan.hicash.domain.query.param.AreaQuery;
import com.hengyuan.hicash.domain.query.param.CityQuery;
import com.hengyuan.hicash.domain.query.param.ProvinceQuery;
import com.hengyuan.hicash.domain.query.user.CouPonCodeQuery;
import com.hengyuan.hicash.domain.query.user.CouPonRecordQuery;
import com.hengyuan.hicash.domain.query.user.CuCouPonQuery;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.calculate.LoanPayService;
import com.hengyuan.hicash.entity.app.AccountPayEntity;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.entity.app.LoanProduct;
import com.hengyuan.hicash.entity.user.Agreement;
import com.hengyuan.hicash.entity.user.AgreementDetail;
import com.hengyuan.hicash.entity.user.CouPonCodeEntity;
import com.hengyuan.hicash.entity.user.CouPonEntity;
import com.hengyuan.hicash.entity.user.CouPonRecordEntity;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.Link;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.LoanPayReq;
import com.hengyuan.hicash.parameters.request.user.LoanPayResp;
import com.hengyuan.hicash.parameters.request.user.ShowWaitTimeReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.LoanAmtCalResp;
import com.hengyuan.hicash.parameters.response.user.ShowWaitTimeResp;
import com.hengyuan.hicash.utils.HttpClientSendPost;
import com.hengyuan.hicash.utils.LoanAmtCalUtil;
import com.hengyuan.hicash.utils.ResourceUtils;


/** 
 * @author blianke.qin 
 * 2017年1月10日 上午11:36:22
 * 类说明   获取借款进度 
 */
public class ShowWaitTimeService implements RespService{

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		
		ShowWaitTimeReq showWaitTimeReq=(ShowWaitTimeReq)parmRequest;
		ShowWaitTimeResp showWaitTimeResp=new ShowWaitTimeResp();
		
		ApplicationQuery applicationQuery = new ApplicationQuery();
		ProductQuery productQuery = new ProductQuery();
		AcctPayQuery acctPayQuery = new AcctPayQuery();
		
		ApplicationEntity applicationEntity=new ApplicationEntity();
		LoanProduct loanProduct=new LoanProduct();
		CustcustomerQuery custcustomerQuery= new CustcustomerQuery();
		ProvinceQuery provinceQuery = new ProvinceQuery();
		CityQuery cityQuery = new CityQuery();
		AreaQuery areaQuery = new AreaQuery();
		applicationEntity =applicationQuery.queryAppPayByAppNO(showWaitTimeReq.getAppNo());
		
		if(applicationEntity!=null){
			
			loanProduct=productQuery.queryCreditProductById(applicationEntity.getLoanProduct());
			
			//产品名称
			showWaitTimeResp.setPro_name(applicationEntity.getProductName());
			
			//用户名
			showWaitTimeResp.setUserName(applicationEntity.getAppUsername());
			
			//申请金额
			showWaitTimeResp.setApplyAmt(new BigDecimal(applicationEntity.getApplyAmount()).setScale(2, BigDecimal.ROUND_HALF_UP)+"元");
			
		    //当前时间
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			showWaitTimeResp.setDate(formatter.format(new Date()));
			
			if("HINS".equals(applicationEntity.getIndustryCode())){
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("orderId", applicationEntity.getAppNo());
				/*jsonObject.put("orderId", "71701052311658");*/
				String url = ResourceUtils.getValue(Consts.HINS_PIC_URL);
				
				try {
					Map<Integer, String> strTDInfo = HttpClientSendPost.sendXMLDataByPost(url, jsonObject,showWaitTimeReq.getAuthorization());
					String reqMap = strTDInfo.get(200);
					JSONObject jsonObj = (JSONObject) JSONObject.parseObject(reqMap);
					String resultCode = String.valueOf(jsonObj.getString("resultCode"));
					if("1".equals(resultCode)){
						JSONArray jsonArray =  jsonObj.getJSONArray("orders");
						Iterator<Object> it = jsonArray.iterator();
						while (it.hasNext()) {
							JSONObject ob = (JSONObject) it.next();
							showWaitTimeResp.setPicture(ob.getString("smallImageUrl"));
							showWaitTimeResp.setGoodsName(ob.getString("productName"));
							
						}
					}  	 
				}catch (Exception e){
					 e.printStackTrace();
				}
			}
			
			if("DDCP".equals(applicationEntity.getIndustryCode())){
				showWaitTimeResp.setIsHiCash("1");
			}else{
				showWaitTimeResp.setIsHiCash("0");
			}
			 //月供
			showWaitTimeResp.setMonthPayAmt(new BigDecimal(applicationEntity.getMonthPay()).setScale(2, BigDecimal.ROUND_HALF_UP) +"元");
			
			
			//期数
			if("LDDD".equals(applicationEntity.getIndustryCode())){
				showWaitTimeResp.setTerm("1期");
			}else if("HINS".equals(applicationEntity.getIndustryCode())){
				showWaitTimeResp.setTerm(applicationEntity.getInstallMent()+"个月");
			}else{
				showWaitTimeResp.setTerm(applicationEntity.getInstallMent()+"期");
			}
			//审批等待时间   目前写死
			 showWaitTimeResp.setTime("5-7分钟");
			//最大审批等待时间   目前写死
			 showWaitTimeResp.setMaxTime("7");
			//审批是否通过
			 String allnode =applicationEntity.getAllnode();
			 String  status=applicationEntity.getStatus();
			
			 //未认领         NENODE  NODE01  STATUS01 
			 //申请拒绝     GBNODE  NODE12  TATUS21
			 //申请取消     GBNODE  NODE12  STATUS20
			 //电子签章     SHNODE NODE06  STATUS36
			
			 List<Link>  list = new ArrayList<Link>();
			 
			 //行业代码
			 showWaitTimeResp.setIndustryCode(applicationEntity.getIndustryCode());
			 
			 LoanPayService loanPayService = new LoanPayService();
			 LoanPayReq loanPayReq = new LoanPayReq();
			 loanPayReq.setAmount(applicationEntity.getApplyAmount());
			 loanPayReq.setIndustryCode(applicationEntity.getIndustryCode());
			 
			 if("LDDD".equals(applicationEntity.getIndustryCode())){
				 loanPayReq.setDays(loanProduct.getCreditday());
			 }else {
				 Integer a = new Integer(0);
				 a = Integer.valueOf(applicationEntity.getInstallMent())*30;
				 loanPayReq.setDays(String.valueOf(a));
			 }
			 LoanPayResp loanPayResp =loanPayService.todo(loanPayReq);
			 
			 showWaitTimeResp.setLowPay(String.valueOf(new BigDecimal(loanPayResp.getLowPay()).setScale(2, BigDecimal.ROUND_HALF_UP)));			 
			 showWaitTimeResp.setHighPay(String.valueOf(new BigDecimal(loanPayResp.getHighPay()).setScale(2, BigDecimal.ROUND_HALF_UP)));
			 
			 CustCustomer custCustomer = custcustomerQuery.queryCustCustomer(applicationEntity.getAppUsername());
			 if(custCustomer!=null){
				 //身份证
				 showWaitTimeResp.setiCode(custCustomer.getIdentityNo());
				String address = provinceQuery.queryProvince(custCustomer.getOtherAdressProvince()).getProvName()
							        +cityQuery.queryCity(custCustomer.getOtherAdressCity()).getCityName()
							        +areaQuery.queryArea(custCustomer.getOtherAdressArea()).getAreaName()
							        +custCustomer.getOtherAccommodationAddress();
				 showWaitTimeResp.setRealAddress(address);
			 }
			
			 
			//优惠券
			CouPonEntity couPonEntity = queryCouPonByUserName(custCustomer.getUserName(),showWaitTimeReq.getAppNo());
			if(couPonEntity != null){
				//比例优惠
				if(couPonEntity.getCouPonType().equals("1")){
					showWaitTimeResp.setDiscount_str(subZeroAndDot(couPonEntity.getDiscount())+"%");
				}else if(couPonEntity.getCouPonType().equals("2")){
					showWaitTimeResp.setDiscount_str(subZeroAndDot(couPonEntity.getDiscount())+"元");
				}
				 showWaitTimeResp.setDiscount_name(couPonEntity.getCouPonName());
				
			}else{
				showWaitTimeResp.setDiscount_str("");
				 showWaitTimeResp.setDiscount_name("");
			}
			
		
				
		 if("GBNODE".equals(allnode)&&"STATUS21".equals(status)){
			 showWaitTimeResp.setTextVersion("资料审核未通过");
			 showWaitTimeResp.setTextVersionto("您的某些资料不符合申请条件  请您一个月后再做尝试，感谢您的支持");
			 showWaitTimeResp.setPlainText("");
			 showWaitTimeResp.setLink(list);
			 
			 showWaitTimeResp.setIs_pass("false"); 
			 
		 }else if("SHNODE".equals(allnode)&&!"STATUS36".equals(status)){
			 if("JYFQ".equals(applicationEntity.getIndustryCode())||
				"DDSJ".equals(applicationEntity.getIndustryCode())	 
					 ){
				 showWaitTimeResp.setTextVersion("请耐心等待审核");
				 showWaitTimeResp.setTextVersionto("审核结果会以短信和站内消息通知,请注意查收,审核通过后,登录签约拿钱");
				 
			 }else{
				 showWaitTimeResp.setTextVersion("资料审核中"); 
			 }
	    	
			
		 }else if("STATUS20".equals(status)){
			 showWaitTimeResp.setIsCancel("true"); 
		 } 
		 else if("STATUS36".equals(status)){
			 showWaitTimeResp.setTextVersion("资料审核已通过");
			 showWaitTimeResp.setTextVersionto("您好，请尽快签约领取借款");
			
			//vip现金抵用券
			showWaitTimeResp.setVipCashVoucher(null);
			
			showWaitTimeResp.setApplyFrom(applicationEntity.getApplyFrom());
			
			showWaitTimeResp.setConfirmCashAndSignText("请确认您的借款金额后签约提现。");
			
			showWaitTimeResp.setH5SignIfFromAppText("为了你的资金安全\n请进行人脸识别验证，刷脸拿钱。");
			
			showWaitTimeResp.setAgreeSignText("本人承诺已认真阅读并将遵守");
			
			showWaitTimeResp.setFaceRecognitionSuccessText("人脸识别认证成功，点击【立即签约】拿钱");
			
//			showWaitTimeResp.setRateADesc("利率按实际的借款月数收取，每月收取借款金额的\n1.0%；\n此外您还需对每笔成功的借款支付手机验证费、银行卡\n验证费、身份验证费、征信审核费、信息发布费、客户\n服务费、客户端维护费等的平台服务费以及账户管理\n费。");
//			
//			showWaitTimeResp.setRateBDesc("利率按实际的借款月数收取，每月收取借款金额的\n0.7%-1.4%，实际利率将根据您的资料最终审核确定，\n提供真实有效的资料有助于提高您的审批通过率；\n此外您还需对每笔成功的借款支付手机验证费、银行卡\n验证费、身份验证费、征信审核费、信息发布费、客户\n服务费、客户端维护费等的平台服务费以及账户管理\n费。");
	
			//http://115.29.193.125/newweb/agreement/principalLoanAgree.html?loanProduct=期数ID&tranPrice=金额&userName=用户名 &comefrom=APP
			
			//生产ip
//			String productUrl = "http://m.hicash.cn";
			String productUrl = ResourceUtils.getValue("hicashAppUrl");
			
			//测试ip 
			 //String productUrl = "http://115.29.193.125";
			
			 List<Link> agreeSignLinks = new ArrayList<Link>();
			 //签约三协议
			 Link principalLoanAgreeLink = new Link();
			 principalLoanAgreeLink.setUrl("http://m.hicash.cn/newweb/agreement/principalLoanAgree.html?loanProduct="+applicationEntity.getLoanProduct()+"&tranPrice="+applicationEntity.getApplyAmount()+"&userName="+applicationEntity.getAppUsername()+"&comefrom=APP");
			 agreeSignLinks.add(principalLoanAgreeLink);
			 Link platformserAgreeLink = new Link();
			 platformserAgreeLink.setUrl("http://m.hicash.cn/newweb/agreement/platformserAgree.html?loanProduct="+applicationEntity.getLoanProduct()+"&tranPrice="+applicationEntity.getApplyAmount()+"&userName="+applicationEntity.getAppUsername()+"&comefrom=APP");
			 agreeSignLinks.add(platformserAgreeLink);
			 Link monthManageAgreeLink = new Link();
			 monthManageAgreeLink.setUrl("http://m.hicash.cn/newweb/agreement/monthManageAgree.html?loanProduct="+applicationEntity.getLoanProduct()+"&tranPrice="+applicationEntity.getApplyAmount()+"&userName="+applicationEntity.getAppUsername()+"&comefrom=APP");
			 agreeSignLinks.add(monthManageAgreeLink);
			
			 showWaitTimeResp.setAgreeSignLinks(agreeSignLinks);
			 
			 //获取协议链接
			 getAgreement(productUrl,applicationEntity,loanProduct,showWaitTimeResp);
			
			 Link link=new Link();
			 link.setLocation("19");
			 link.setLength("22");
			 link.setUrl("http://www.cfca.com.cn");
			 list.add(link);
			 
			 //司机贷12期订单协议为海尔协议，其他原来亨元协议。
			 if("DDSJ".equals(applicationEntity.getIndustryCode())&&"haier_finance".equals(loanProduct.getInverstorName())){
				 showWaitTimeResp.setPlainText("本人已认真阅读并同意遵守CFCA网站（http://www.cfca.com.cn）发布的《数字认证业务规则》中规定的相关义务。您对下方“同意签章”按钮的点击视为您对《海尔消费金融有限公司征信查询授权书》及相关《海尔消费金融有限公司个人借款合同》的确认及签署。");
				 Link link1=new Link();
				 link1.setLocation("83");
				 link1.setLength("19");
				 link1.setUrl("https://m.hicash.cn/newweb/agreement/Certificate.html");
				 list.add(link1);
				 
				 Link link2=new Link();
				 link2.setLocation("105");
				 link2.setLength("18");
				 link2.setUrl("https://m.hicash.cn/newweb/agreement/driverContract.html");
				 list.add(link2);
			 
			 }else if("DDCP".equals(applicationEntity.getIndustryCode())&&"3".equals(applicationEntity.getInstallMent())){
				 showWaitTimeResp.setPlainText("本人已认真阅读并同意遵守CFCA网站（http://www.cfca.com.cn）发布的《数字认证业务规则》中规定的相关义务。您对下方“同意签章”按钮的点击视为您对《亨元金融信息服务协议》、《保险经纪委托协议》、《交通团体意外伤害保险》条款以及《交通团体意外伤害保险》被保险人确认函的确认及签署。"); 
				 Link link1=new Link();
				 link1.setLocation("83");
				 link1.setLength("12");
				 //测试
				 //link1.setUrl("http://115.29.193.125/newweb/agreement/declaretwo.html?comefrom=APP");
				 //生产
				 link1.setUrl("https://m.hicash.cn/newweb/agreement/declaretwo.html?comefrom=APP");
				 list.add(link1); 
				 
				 Link link2=new Link();
				 link2.setLocation("96");
				 link2.setLength("10");
				 //测试
				 //link1.setUrl("http://115.29.193.125/newweb/agreement/declaretwo.html?comefrom=APP");
				 //生产
				 link2.setUrl("https://m.hicash.cn/newweb/agreement/declaretwo.html?comefrom=APP");
					
				 list.add(link2); 
				 
				 
				 Link link3=new Link();
				 link3.setLocation("107");
				 link3.setLength("12");
				 //测试
				 //link1.setUrl("http://115.29.193.125/newweb/agreement/declaretwo.html?comefrom=APP");
				 //生产
				 link3.setUrl("https://m.hicash.cn/newweb/agreement/declaretwo.html?comefrom=APP");
					
				 list.add(link3); 
				 
				 Link link4=new Link();
				 link4.setLocation("123");
				 link4.setLength("12");
				 //测试
				 //link1.setUrl("http://115.29.193.125/newweb/agreement/declaretwo.html?comefrom=APP");
				 //生产
				 link4.setUrl("https://m.hicash.cn/newweb/agreement/declaretwo.html?comefrom=APP");
					
				 list.add(link4); 
				 
			 }else{
				 showWaitTimeResp.setPlainText("本人已认真阅读并同意遵守CFCA网站（http://www.cfca.com.cn）发布的《数字认证业务规则》中规定的相关义务。您对下方“同意签章”按钮的点击视为您对《亨元金融信息服务协议》的确认及签署。"); 
				 Link link1=new Link();
				 link1.setLocation("83");
				 link1.setLength("12");
				 //测试
				 //link1.setUrl("http://115.29.193.125/newweb/agreement/declaretwo.html?comefrom=APP");
				 //生产
				 link1.setUrl("https://m.hicash.cn/newweb/agreement/declaretwo.html?comefrom=APP");
				 list.add(link1); 
				 
				
				 
			 }
		
			 /*Link link2=new Link();
			 link2.setLocation("98");
			 link2.setLength("6");
			 //测试
			 link2.setUrl("http://115.29.193.125/app/miaodai/declarethree.html");
			//生产
			//link1.setUrl("https://m.hicash.cn/app/miaodai/declaretwo.html");
			 
			 list.add(link2);*/
			 showWaitTimeResp.setLink(list);
			 
			 showWaitTimeResp.setIs_pass("true"); 
			 
		 }else{
			 showWaitTimeResp.setIs_pass(""); 
			 showWaitTimeResp.setTextVersion("");
			 showWaitTimeResp.setTextVersionto("");
			 showWaitTimeResp.setPlainText("");
			 showWaitTimeResp.setLink(list);
		 }
		 
		 //合同金额
		 AccountPayEntity accountPayEntity = acctPayQuery.getLoanInfoByAppNo(applicationEntity.getAppUsername(), showWaitTimeReq.getAppNo());
		 if(accountPayEntity!=null){
			 showWaitTimeResp.setTranPrice(new BigDecimal(applicationEntity.getApplyAmount()).setScale(2, BigDecimal.ROUND_HALF_UP)+"");
			 showWaitTimeResp.setLoanProduct(accountPayEntity.getLoanProduct());
		 }else{
			 showWaitTimeResp.setTranPrice("");
			 showWaitTimeResp.setLoanProduct("");
		 }
		 
		 showWaitTimeResp.setResultCode(ResultCodes.NORMAL);
		 
		}else{
			
			showWaitTimeResp.setResultCode(ResultCodes.NO_RESULT);
			showWaitTimeResp.setResultMsg(ResourceUtils.getString(ResultCodes.NO_RESULT));
		
		}
		
		return showWaitTimeResp;
	}


	public static void main(String[] args) {
		String termStr = "3个月";
	
		System.out.println(Integer.parseInt(termStr.substring(0, termStr.indexOf("个月")))*30);
		
//		String  a="本人已认真阅读并同意遵守CFCA网站（http：//www.cfca.com.cn）发布的《数字认证业务规则》中规定的相关义务。您对下方“同意签章”按钮的点击视为您对《亨元金融金融信息服务协议》及相关《三方协议》的确认及签署。";
//	    System.out.println(a.substring(19,41));
	
	}
	
	
	
	/**
	 * 根据用户名和订单号查看已使用优惠券
	 * @param userName
	 * @param AppNo
	 * @return
	 */
	public CouPonEntity queryCouPonByUserName(String userName,String AppNo){
		CouPonRecordQuery couPonQuery = new CouPonRecordQuery();
		CouPonRecordEntity couPonRecordEntity = couPonQuery.queryRecordByUserIdAndOrder(userName, AppNo);
		
		CouPonEntity couPonEntity = null;
		if(couPonRecordEntity != null){
			CouPonCodeEntity couPonCodeEntity = queryCouByCodeId(couPonRecordEntity.getCouponCodeId());
			if(couPonCodeEntity != null){
				couPonEntity = queryCouPonId(couPonCodeEntity.getCouponId());
			}
					
		}
		
		return couPonEntity;
	}
	
	
	/**
	 * 根据ID查看优惠券
	 * @param id
	 * @return
	 */
	public CouPonEntity queryCouPonId(String id){
		CuCouPonQuery couPonQuery = new CuCouPonQuery();
		return couPonQuery.queryByCouId(id);
	}
	
	
	/**
	 * 根据codeid查询优惠劵明细
	 * @param code
	 * @return
	 */
	public CouPonCodeEntity queryCouByCodeId(String codeId){
		CouPonCodeQuery couPonCodeQuery = new CouPonCodeQuery();
		return couPonCodeQuery.queryCodeByCodeId(codeId);
	}
	
	
	public static String subZeroAndDot(String s){    
        if(s.indexOf(".") > 0){    
            s = s.replaceAll("0+?$", "");//去掉多余的0    
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉    
        }    
        return s;    
    }

	private void getAgreement(String productUrl, ApplicationEntity applicationEntity,LoanProduct loanProduct, ShowWaitTimeResp showWaitTimeResp ) {
		
		//app用的参数
		 String parameterApp = "?loanProduct="+applicationEntity.getLoanProduct()+"&tranPrice="+applicationEntity.getApplyAmount()+"&userName="+applicationEntity.getAppUsername()+"&appNo="+applicationEntity.getAppNo()+"&comefrom=APP";
		
		 String desFront = "本人承诺已认真阅读并将遵守";
		 String desBelow = "本人自愿购买以下产品及服务，本人勾选同意的行为为本人自愿签订勾选的合同，本人已充分了解并完全理解本人勾选的全部合同条款以及合同条款的全部内容。本人一旦勾选并签订以下合同，即意味着本人愿意承担一切法律后果。";
		 //这里协议链接是返回给app用的
		 List<Agreement> agreements = new ArrayList<Agreement>();
		 
		 Agreement agreement = new Agreement();
		 List<Agreement> insurances = new ArrayList<Agreement>();
		 Agreement insurance = new Agreement();
		 insurance.setDesFront(desFront);
		 insurance.setName("《本金借款协议》");
		 insurance.setUrl(productUrl+"/newweb/agreement/principalLoanAgree_new.html"+parameterApp);
		 insurance.setDesBehind("");
		 insurances.add(insurance);
		 Agreement insurance7 = new Agreement();
		 insurance7.setName("《借款用途确认书》");
		 insurance7.setUrl(productUrl+"/newweb/agreement/usageLoan.html"+parameterApp);
		 insurance7.setDesBehind("");
		 insurances.add(insurance7);
		 agreement.setInsurance(insurances);
		 agreement.setDesBelow(desBelow);
		 agreements.add(agreement);
		 
		 Agreement agreement1 = new Agreement();
		 List<Agreement> insurances1 = new ArrayList<Agreement>();
		 Agreement insurance1 = new Agreement();
		 insurance1.setDesFront(desFront);
		 insurance1.setName("《消费资讯综合采购服务协议》");
		 insurance1.setUrl(productUrl+"/newweb/agreement/platformserAgree_new.html"+parameterApp);
		 insurance1.setDesBehind("");
		 
		 
		 String tranPriceStr = applicationEntity.getApplyAmount();
		 String firstRateStr = applicationEntity.getFirstRate();
		 String loanProductId = applicationEntity.getLoanProduct();
		 LoanAmtCalResp loanAmtCalResp ;
		 try {
			 loanAmtCalResp = LoanAmtCalUtil.calculateLoanAmount(new BigDecimal(tranPriceStr), new BigDecimal(firstRateStr), loanProductId);
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				loanAmtCalResp = null;
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				loanAmtCalResp = null;
				e.printStackTrace();
			} catch (HttpReturnException e) {
				loanAmtCalResp = null;
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HttpUrlRemoteException e) {
				loanAmtCalResp = null;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		 List<AgreementDetail> detailList = new ArrayList<AgreementDetail>();
		 AgreementDetail detail1 = new AgreementDetail();
		 detail1.setName("消费综合费");
		 detail1.setContent(String.valueOf(loanAmtCalResp.getTotMoth()));
		 AgreementDetail detail2 = new AgreementDetail();
		 detail2.setName("每期本息还款");
		 detail2.setContent(String.valueOf(loanAmtCalResp.getEverMoth()));
		 AgreementDetail detail3 = new AgreementDetail();
		 detail3.setName("借款期限");
		 String termUnit = "";
		 if(loanProduct.getLoanUnit() != null){
			 if("M".equals(loanProduct.getLoanUnit())){
				 termUnit = "个月";
			 }if("D".equals(loanProduct.getLoanUnit())){
				 termUnit = "天";
			 }
		 }
		 String term1 = applicationEntity.getInstallMent() + termUnit;
		 detail3.setContent(term1);
		 detailList.add(detail1);
		 detailList.add(detail2);
		 detailList.add(detail3);
		 insurances1.add(insurance1);
		 agreement1.setInsurance(insurances1);
		 agreement1.setDesTitle("综合消费详情");
		 agreement1.setDesUrl(ResourceUtils.getValue("consumeDetailUrl")+parameterApp);
//		 agreement1.setDesUrl("http://115.29.193.125/newweb/personalCenter/consumptDetail.html");
		 agreement1.setDesTitleBgColor("#FD7F5F");
		 agreement1.setDesDetails(detailList);
		 agreements.add(agreement1);
		 
		 Agreement agreement2 = new Agreement();
		 List<Agreement> insurances2 = new ArrayList<Agreement>();
		 Agreement insurance2 = new Agreement();
		 insurance2.setDesFront(desFront);
		 insurance2.setName("《会员服务协议》");
		 insurance2.setUrl(productUrl+"/newweb/agreement/monthManageAgree_new.html"+parameterApp);
		 insurance2.setDesBehind("");
		 insurances2.add(insurance2);
		 
		 List<AgreementDetail> detailList2 = new ArrayList<AgreementDetail>();
		 AgreementDetail detail4 = new AgreementDetail();
		 detail4.setName("会员服务费");
		 detail4.setContent(String.valueOf(loanAmtCalResp.getMonthFee().
					multiply(new BigDecimal(loanAmtCalResp.getPayMentPeriod()))));
		 AgreementDetail detail5 = new AgreementDetail();
		 detail5.setName("每期本息还款");
		 detail5.setContent(String.valueOf(loanAmtCalResp.getMonthFee()));
		 AgreementDetail detail6 = new AgreementDetail();
		 detail6.setName("借款期限");
		 detail6.setContent(term1);
		 detailList2.add(detail4);
		 detailList2.add(detail5);
		 detailList2.add(detail6);
		 
		 agreement2.setDesTitle("会员资讯详情");
//		 agreement2.setDesUrl("http://115.29.193.125/newweb/personalCenter/membershipDetail.html");
		 agreement2.setDesUrl(ResourceUtils.getValue("informationDetailUrl")+parameterApp);
//		 agreement2.setDesTitleBgColor("#FD7F5F");
		 agreement2.setDesDetails(detailList2);
		 agreement2.setInsurance(insurances2);
		 agreements.add(agreement2);

		 Agreement agreement3 = new Agreement();
		 List<Agreement> insurances3 = new ArrayList<Agreement>();
		 Agreement insurance3 = new Agreement();
		 insurance3.setDesFront(desFront);
		 insurance3.setName("《永安借款人意外确认函》");
		 insurance3.setUrl(productUrl+"/newweb/agreement/ywxAgree_new.html"+parameterApp);
		 insurance3.setDesBehind("");
		 insurances3.add(insurance3);
		 
		 List<AgreementDetail> detailList3 = new ArrayList<AgreementDetail>();
		 AgreementDetail detail7 = new AgreementDetail();
		 detail7.setName("保险服务费");
		 detail7.setContent(String.valueOf(loanAmtCalResp.getjCopePremium().add(loanAmtCalResp.getwCopePremium())));
		 AgreementDetail detail8 = new AgreementDetail();
		 detail8.setName("每期本息还款");
		 detail8.setContent(String.valueOf(loanAmtCalResp.getjMerPermium().add(loanAmtCalResp.getwMerPermium())));
		 AgreementDetail detail9 = new AgreementDetail();
		 detail9.setName("借款期限");
		 detail9.setContent(term1);
		 detailList3.add(detail7);
		 detailList3.add(detail8);
		 detailList3.add(detail9);
		
		 agreement3.setDesDetails(detailList3);
		 
		 Agreement insurance4 = new Agreement();
		 insurance4.setName("《深圳众诚泰保险经纪委托协议》");
		 insurance4.setUrl(productUrl+"/newweb/agreement/insubrokAgree_new.html"+parameterApp);
		 insurance4.setDesBehind("");
		 insurances3.add(insurance4);
		 Agreement insurance5 = new Agreement();
		 insurance5.setName("《昆仑团险被保险人确认函》");
		 insurance5.setUrl(productUrl+"/newweb/agreement/txLetterAgree_new.html"+parameterApp);
		 insurance5.setDesBehind("");
		 insurances3.add(insurance5);
		 agreement3.setInsurance(insurances3);
		 agreements.add(agreement3);
		 
		 Agreement agreement4 = new Agreement();
		 
		 List<Agreement> insurances4 = new ArrayList<Agreement>();
		 Agreement insurance6 = new Agreement();
		 insurance6.setDesFront(desFront);
		 insurance6.setName("《委托划扣授权书》");
		 insurance6.setUrl(productUrl+"/newweb/agreement/entrustAgree.html"+parameterApp);
		 insurance6.setDesBehind("");
		 insurances4.add(insurance6);
		 agreement4.setInsurance(insurances4);
		 agreements.add(agreement4);			 
		 
		 
		 showWaitTimeResp.setAgreements(agreements);
		 
		 String parameterH5 = "?loanProduct="+applicationEntity.getLoanProduct()+"&tranPrice="+applicationEntity.getApplyAmount()+"&userName="+applicationEntity.getAppUsername()+"&appNo="+applicationEntity.getAppNo();
		 //这里协议链接是返回给h5用的
		 List<Agreement> agreementsH5 = new ArrayList<Agreement>();
		 
		 Agreement agreement_h = new Agreement();
		 List<Agreement> insurances_h = new ArrayList<Agreement>();
		 Agreement insurance_h = new Agreement();
		 insurance_h.setDesFront(desFront);
		 insurance_h.setName("《本金借款协议》");
		 insurance_h.setUrl(productUrl+"/newweb/agreement/principalLoanAgree_new.html"+parameterH5);
		 insurance_h.setDesBehind("");
		 insurances_h.add(insurance_h);
		 Agreement insurance_h7 = new Agreement();
		 insurance_h7.setName("《借款用途确认书》");
		 insurance_h7.setUrl(productUrl+"/newweb/agreement/usageLoan.html"+parameterH5);
		 insurance_h7.setDesBehind("");
		 insurances_h.add(insurance_h7);
		 agreement_h.setInsurance(insurances_h);
		 agreement_h.setDesBelow(desBelow);
		 agreementsH5.add(agreement_h);
		 
		 Agreement agreement_h1 = new Agreement();
		 List<Agreement> insurances_h1 = new ArrayList<Agreement>();
		 Agreement insurance_h1 = new Agreement();
		 insurance_h1.setDesFront(desFront);
		 insurance_h1.setName("《消费资讯综合采购服务协议》");
		 insurance_h1.setUrl(productUrl+"/newweb/agreement/platformserAgree_new.html"+parameterH5);
		 insurance_h1.setDesBehind("");
		 insurances_h1.add(insurance_h1);
		 agreement_h1.setInsurance(insurances_h1);
		 
		 agreement_h1.setDesDetails(detailList);
		 agreement_h1.setDesTitle("综合消费详情");
		 agreement_h1.setDesUrl(ResourceUtils.getValue("consumeDetailUrl"));
		 agreement_h1.setDesTitleBgColor("#FD7F5F");	 
		 agreementsH5.add(agreement_h1);
		 
		 Agreement agreement_h2 = new Agreement();
		 List<Agreement> insurances_h2 = new ArrayList<Agreement>();
		 Agreement insurance_h2 = new Agreement();
		 insurance_h2.setDesFront(desFront);
		 insurance_h2.setName("《会员服务协议》");
		 insurance_h2.setUrl(productUrl+"/newweb/agreement/monthManageAgree_new.html"+parameterH5);
		 insurance_h2.setDesBehind("");
		 insurances_h2.add(insurance_h2);

		 agreement_h2.setDesTitle("会员资讯详情");
		 agreement_h2.setDesUrl(ResourceUtils.getValue("informationDetailUrl"));
		 agreement_h2.setDesDetails(detailList2);
		 
		 agreement_h2.setInsurance(insurances_h2);
		 agreementsH5.add(agreement_h2);
		 
		 Agreement agreement_h3 = new Agreement();
		 List<Agreement> insurances_h3 = new ArrayList<Agreement>();
		 Agreement insurance_h3 = new Agreement();
		 insurance_h3.setDesFront(desFront);
		 insurance_h3.setName("《永安借款人意外确认函》");
		 insurance_h3.setUrl(productUrl+"/newweb/agreement/ywxAgree_new.html"+parameterH5);
		 insurance_h3.setDesBehind("");
		 insurances_h3.add(insurance_h3);
		
		 agreement_h3.setDesDetails(detailList3);

		 Agreement insurance_h4 = new Agreement();
		 insurance_h4.setName("《深圳众诚泰保险经纪委托协议》");
		 insurance_h4.setUrl(productUrl+"/newweb/agreement/insubrokAgree_new.html"+parameterH5);
		 insurance_h4.setDesBehind("");
		 insurances_h3.add(insurance_h4);
		 Agreement insurance_h5 = new Agreement();
		 insurance_h5.setName("《昆仑团险被保险人确认函》");
		 insurance_h5.setUrl(productUrl+"/newweb/agreement/txLetterAgree_new.html"+parameterH5);
		 insurance_h5.setDesBehind("");
		 insurances_h3.add(insurance_h5);
		 agreement_h3.setInsurance(insurances_h3);
		 agreementsH5.add(agreement_h3);
		 
		 Agreement agreement_h4 = new Agreement();
		 List<Agreement> insurances_h4 = new ArrayList<Agreement>();
		 Agreement insurance_h6 = new Agreement();
		 insurance_h6.setDesFront(desFront);
		 insurance_h6.setName("《委托划扣授权书》");
		 insurance_h6.setUrl(productUrl+"/newweb/agreement/entrustAgree.html"+parameterH5);
		 insurance_h6.setDesBehind("");
		 insurances_h4.add(insurance_h6);
		 agreement_h4.setInsurance(insurances_h4);
		 agreementsH5.add(agreement_h4);			 
		 showWaitTimeResp.setAgreementsH5(agreementsH5);
		
	}

}
