package com.hengyuan.hicash.service.query;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.app.ApplicationPayQuery;
import com.hengyuan.hicash.domain.query.user.HinsApplyCreditQuery;
import com.hengyuan.hicash.domain.query.user.NSLimitQuery;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.user.HinsApplyCredit;
import com.hengyuan.hicash.entity.user.Link;
import com.hengyuan.hicash.entity.user.NSLimit;
import com.hengyuan.hicash.parameters.request.user.PageMessageReq;
import com.hengyuan.hicash.parameters.response.user.PageMessageResp;
import com.hengyuan.hicash.service.validate.query.PageMessageVal;
import com.hengyuan.hicash.utils.Base64;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * @author blanke.qin 2017年4月6日 上午11:54:54 类说明
 */
@WebServlet("/PageMessage")
public class PageMessage extends HttpServlet {
	private static Logger logger = Logger.getLogger(PageMessage.class);
	private static final long serialVersionUID = 3195098942001612859L;
	
	private ApplicationPayQuery payQuery = new ApplicationPayQuery();
	private HinsApplyCreditQuery hinsApplyCreditQuery = new HinsApplyCreditQuery();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PageMessageReq pageMessageReq = new PageMessageReq();
		pageMessageReq.setPage_code(req.getParameter("page_code"));
		pageMessageReq.setIs_zm(req.getParameter("is_zm"));
		pageMessageReq.setIs_xx(req.getParameter("is_xx"));
		pageMessageReq.setIs_gj(req.getParameter("is_gj"));
		pageMessageReq.setUserName(req.getParameter("userName"));
		

		RecordUtils.writeRequest(logger, req, pageMessageReq);
		PageMessageVal pageMessageVal = new PageMessageVal(pageMessageReq);
		String resultCode = pageMessageVal.validate();
		PageMessageResp pageMessageResp = new PageMessageResp();

		BigDecimal Amount = new BigDecimal(50000);

		if (!StringUtils.isEmpty(pageMessageReq.getIs_xx())
				&& "Y".equals(pageMessageReq.getIs_xx())) {
			Amount = Amount.subtract(new BigDecimal(5000));
		}

		if (!StringUtils.isEmpty(pageMessageReq.getIs_zm())
				&& "Y".equals(pageMessageReq.getIs_zm())) {
			Amount = Amount.subtract(new BigDecimal(10000));
		}

//		Amount = Amount.divide(new BigDecimal(10000)).setScale(1);

		if (!ResultCodes.NORMAL.equals(resultCode)) {
			pageMessageResp.setResultCode(resultCode);
			pageMessageResp.setResultMsg(ResourceUtils.getString(resultCode));
		} else {
			List<Link>  list=new ArrayList();
			if ("A001".equals(pageMessageReq.getPage_code())) {
				pageMessageResp.setLimit_desc("最高可享¥" + Amount + "额度");
				pageMessageResp.setBomb_box_desc("");
				pageMessageResp.setBut_msg_1("");
				pageMessageResp.setBut_msg_2("");
				pageMessageResp.setIs_skip("");
				pageMessageResp.setSkip_msg("");
			}
			if ("A002".equals(pageMessageReq.getPage_code())) {
				pageMessageResp.setLimit_desc("可选择跳过，但最高额度为¥"+Amount+"");
				pageMessageResp.setBomb_box_desc("");
				pageMessageResp.setBut_msg_1("跳过");
				pageMessageResp.setBut_msg_2("跳过之后影响你的最高额度");
				pageMessageResp.setIs_skip("");
				pageMessageResp.setSkip_msg("");
			}
			if ("A003".equals(pageMessageReq.getPage_code())) {
				pageMessageResp
						.setLimit_desc("直接提交可跳过，但最高额度降为¥" + Amount + "");
				pageMessageResp.setBomb_box_desc("");
				pageMessageResp.setBut_msg_1("");
				pageMessageResp.setBut_msg_2("");
				pageMessageResp.setIs_skip("");
				pageMessageResp.setSkip_msg("");
				pageMessageResp.setPlainText("");
//				 Link link=new Link();
//				 link.setLocation("10");
//				 link.setLength("13");
//				 link.setUrl("http://www.cfca.com.cn");
//				 list.add(link);
//				 pageMessageResp.setLink(list);
				 pageMessageResp.setBomb_capion("");
			}
			if ("A004".equals(pageMessageReq.getPage_code())) {
				if ("Y".equals(pageMessageReq.getIs_xx())
						&& "Y".equals(pageMessageReq.getIs_zm())) {
					pageMessageResp.setBomb_box_desc("您跳过芝麻信用认证，学信网认证。目前最高额度仅为¥"
							+ Amount + "");

				} else if ("Y".equals(pageMessageReq.getIs_xx())) {

					pageMessageResp.setBomb_box_desc("您跳过学信网认证。目前最高额度仅为¥"
							+ Amount + "");

				} else if ("Y".equals(pageMessageReq.getIs_zm())) {

					pageMessageResp.setBomb_box_desc("您跳过芝麻信用认证。目前最高额度仅为¥"
							+ Amount + "");
				}
				pageMessageResp.setLimit_desc("认证成功后，最高可贷¥"+Amount+"");
				pageMessageResp.setBut_msg_1("返回认证");
				pageMessageResp.setBut_msg_2("确认提交");
				pageMessageResp.setIs_skip("Y");
				pageMessageResp.setSkip_msg("");
			}
			//嗨女神首页
			if ("N001".equals(pageMessageReq.getPage_code())) {
				
				queryHNSHome(pageMessageResp,pageMessageReq);
				
			}
			//嗨女神授信流程
			if("N002".equals(pageMessageReq.getPage_code())){
				pageMessageResp.setLadylimit_desc("完成女神认证，可享最高¥20000分期购额度");
				pageMessageResp.setPlainText("");
//				 Link link=new Link();
//				 link.setLocation("10");
//				 link.setLength("13");
//				 link.setUrl("http://www.cfca.com.cn");
//				 list.add(link);
//				 pageMessageResp.setLink(list);
				 pageMessageResp.setBomb_capion("");
			}
			//嗨女神授信外卖
			if("N003".equals(pageMessageReq.getPage_code())){
				pageMessageResp.setLadylimit_desc("完成女神认证，可享最高¥20000分期购额度");
				pageMessageResp.setLadyblow_desc("三大外卖网站任选其一，选择常用的外卖网站可增加女神分期购额度。");
				pageMessageResp.setPlainText("");
//				 Link link=new Link();
//				 link.setLocation("10");
//				 link.setLength("12");
//				 link.setUrl("http://www.cfca.com.cn");
//				 list.add(link);
//				 pageMessageResp.setLink(list);
				 pageMessageResp.setBomb_capion("");
			}
			
			//嗨女神授信芝麻，公积金，学信网
			if("N004".equals(pageMessageReq.getPage_code())){
				pageMessageResp.setLadylimit_desc("女神有跳过的权力，但最高分期购额度将降低");
				pageMessageResp.setLadyblow_desc("跳过后将影响女神分期购额度，信息越完善，额度越高。");
			
			}
			//嗨女神授信提交弹出
			if("N005".equals(pageMessageReq.getPage_code())){
				if ("Y".equals(pageMessageReq.getIs_xx())
						&& "Y".equals(pageMessageReq.getIs_zm()) && "Y".equals(pageMessageReq.getIs_gj())) {
					pageMessageResp.setBomb_box_desc("您跳过了学信网、芝麻信用认证、公积金认证，最高分期购额度将降低。返回认证可提高额度");

				}else if ("Y".equals(pageMessageReq.getIs_xx())
						&& "Y".equals(pageMessageReq.getIs_zm()) ) {
					
					pageMessageResp.setBomb_box_desc("您跳过了学信网、芝麻信用认证，最高分期购额度将降低。返回认证可提高额度");

				}else if ("Y".equals(pageMessageReq.getIs_gj())
						&& "Y".equals(pageMessageReq.getIs_zm()) ) {
					
					pageMessageResp.setBomb_box_desc("您跳过了芝麻信用认证、公积金认证，最高分期购额度将降低。返回认证可提高额度");

				}else if ("Y".equals(pageMessageReq.getIs_gj())
						&& "Y".equals(pageMessageReq.getIs_xx()) ) {
					
					pageMessageResp.setBomb_box_desc("您跳过了学信网、公积金认证，最高分期购额度将降低。返回认证可提高额度");

				} else if ("Y".equals(pageMessageReq.getIs_xx())) {

					pageMessageResp.setBomb_box_desc("您跳过了学信网，最高分期购额度将降低。返回认证可提高额度");

				} else if ("Y".equals(pageMessageReq.getIs_zm())) {

					pageMessageResp.setBomb_box_desc("您跳过了芝麻信用认证，最高分期购额度将降低。返回认证可提高额度");
				}else if("Y".equals(pageMessageReq.getIs_gj())){
					pageMessageResp.setBomb_box_desc("您跳过了公积金认证，最高分期购额度将降低。返回认证可提高额度");
				}
				
				pageMessageResp.setBut_msg_1("返回认证");
				pageMessageResp.setBut_msg_2("确认提交");
			}
			
			//嗨女神授信提交完成
			if("N006".equals(pageMessageReq.getPage_code())){
				pageMessageResp.setLady_desc("女神认证资料已提交");
				pageMessageResp.setLadylimit_desc("认证成功后，最高分期购额度¥20000元");
				pageMessageResp.setLadybut_msg_1("本尊知道了");
				pageMessageResp.setLadylimit_desc2("审核将在24小时内完成，请耐心等待");
			}
			
			//嗨女神提额提交完成
			if("N007".equals(pageMessageReq.getPage_code())){
				pageMessageResp.setLady_desc("女神提额资料已提交");
				pageMessageResp.setLadylimit_desc("提额成功后，最高分期购额度¥20000元");
				pageMessageResp.setLadybut_msg_1("本尊知道了");
				pageMessageResp.setLadylimit_desc2("请耐心等待审核完成，留意成功提额短信");
			}
			
			//滴滴贷
			if("D001".equals(pageMessageReq.getPage_code())){
				
				
//					pageMessageResp.setPlainText("本人已认真阅读《投保须知》、《交通团体意外伤害保险》条款");
//					 Link link=new Link();
//					 link.setLocation("7");
//					 link.setLength("6");
//					 link.setUrl("http://www.cfca.com.cn");
//					 list.add(link);
//					 
//					 Link link2=new Link();
//					 link2.setLocation("14");
//					 link2.setLength("12");
//					 link2.setUrl("http://www.cfca.com.cn");
//					 list.add(link2);
//					 pageMessageResp.setLink(list);
				
				
				pageMessageResp.setPlainText("");
				 pageMessageResp.setLink(list);
				
				
			}
			
			//嗨钱来
			if("D002".equals(pageMessageReq.getPage_code())){
				pageMessageResp.setLimit_desc("任有其一账号即可申请");
				pageMessageResp.setDd_img_url(ResourceUtils.getValue(Consts.PIC_SERVER_URL)+ResourceUtils.getValue(Consts.APP_PIC_URL)+"/liucheng.png");

				pageMessageResp.setDd_h5_url(ResourceUtils.getValue(Consts.H5_URL)+"/hins_pro_cont/creditExtent/creditExtension.html ");
				
			}
			//嗨钱来学信网
			if("D003".equals(pageMessageReq.getPage_code())){
				pageMessageResp.setLimit_desc("可借额度(元):+1000，通过系数(分):+50");
				
			}
			//嗨钱来滴滴
			if("D004".equals(pageMessageReq.getPage_code())){
				pageMessageResp.setLimit_desc("可借额度(元):+1000，通过系数(分):+50");
				pageMessageResp.setSkip_msg("您的网约车账户信息只会被用于信用认证，并不会被用作他用。嗨钱网严格保障您的信息安全，杜绝用户信息泄露。");
			}
			//嗨钱来外卖
			if("D005".equals(pageMessageReq.getPage_code())){
				pageMessageResp.setLimit_desc("可借额度(元):+1000，通过系数(分):+50");
				
			}
			//嗨钱来公积金
			if("D006".equals(pageMessageReq.getPage_code())){
				pageMessageResp.setLimit_desc("可借额度(元):+1500，通过系数(分):+80");
				
			}
			//嗨钱来信用认证
			if("D007".equals(pageMessageReq.getPage_code())){
				pageMessageResp.setLimit_desc("基本认证已完成 现只差一步 完成信用认证可拿钱");
				pageMessageResp.setSkip_msg("至少选择一项进行认证，认证越多审核系数越高，通过率越高");
				
			}
			
			//嗨钱来被拒后过来
			if("D008".equals(pageMessageReq.getPage_code())){
				pageMessageResp.setLimit_desc("请确认您的信息是否正确，如需修改，请点击编辑，确认无误，点击下一步 ");
				
			}
			if("S001".equals(pageMessageReq.getPage_code())){
//				pageMessageResp.setPlainText("本人已认真阅读并遵守《运营商数据查询服务协议》");
//				 Link link=new Link();
//				 link.setLocation("10");
//				 link.setLength("13");
//				 link.setUrl("http://www.cfca.com.cn");
//				 list.add(link);
//				 pageMessageResp.setLink(list);
//				 pageMessageResp.setBomb_capion("请同意《运营商数据查询服务协议》");
				
				pageMessageResp.setPlainText("");
				 pageMessageResp.setLink(list);
				 pageMessageResp.setBomb_capion("");
				 
			}
			if("S002".equals(pageMessageReq.getPage_code())){
//				pageMessageResp.setPlainText("本人已认真阅读并遵守《学信网数据查询服务协议》");
//				 Link link=new Link();
//				 link.setLocation("10");
//				 link.setLength("13");
//				 link.setUrl("http://www.cfca.com.cn");
//				 list.add(link);
//				 pageMessageResp.setLink(list);
//				 pageMessageResp.setBomb_capion("请同意《学信网数据查询服务协议》");
				pageMessageResp.setPlainText("");
				 pageMessageResp.setLink(list);
				 pageMessageResp.setBomb_capion("");
			}
			if("S003".equals(pageMessageReq.getPage_code())){
//				pageMessageResp.setPlainText("本人已认真阅读并遵守《外卖数据查询服务协议》");
//				 Link link=new Link();
//				 link.setLocation("10");
//				 link.setLength("12");
//				 link.setUrl("http://www.cfca.com.cn");
//				 list.add(link);
//				 pageMessageResp.setLink(list);
//				 pageMessageResp.setBomb_capion("请同意《外卖数据查询服务协议》");
				pageMessageResp.setPlainText("");
				 pageMessageResp.setLink(list);
				 pageMessageResp.setBomb_capion("");
			}
			if("S004".equals(pageMessageReq.getPage_code())){
//				pageMessageResp.setPlainText("本人已认真阅读并遵守《滴滴出行数据查询服务协议》");
//				 Link link=new Link();
//				 link.setLocation("10");
//				 link.setLength("14");
//				 link.setUrl("http://www.cfca.com.cn");
//				 list.add(link);
//				 pageMessageResp.setLink(list);
//				 pageMessageResp.setBomb_capion("请同意《滴滴出行数据查询服务协议》");
				pageMessageResp.setPlainText("");
				 pageMessageResp.setLink(list);
				 pageMessageResp.setBomb_capion("");
			}
			if("S005".equals(pageMessageReq.getPage_code())){
//				pageMessageResp.setPlainText("本人已认真阅读并遵守《滴滴司机数据查询服务协议》");
//				 Link link=new Link();
//				 link.setLocation("10");
//				 link.setLength("14");
//				 link.setUrl("http://www.cfca.com.cn");
//				 list.add(link);
//				 pageMessageResp.setLink(list);
//				 pageMessageResp.setBomb_capion("请同意《滴滴司机数据查询服务协议》");
				pageMessageResp.setPlainText("");
				 pageMessageResp.setLink(list);
				 pageMessageResp.setBomb_capion("");
			}
			if("S006".equals(pageMessageReq.getPage_code())){
//				pageMessageResp.setPlainText("本人已认真阅读并遵守《公积金数据查询服务协议》");
//				 Link link=new Link();
//				 link.setLocation("10");
//				 link.setLength("13");
//				 link.setUrl("http://www.cfca.com.cn");
//				 list.add(link);
//				 pageMessageResp.setLink(list);
//				 pageMessageResp.setBomb_capion("请同意《公积金数据查询服务协议》");
				pageMessageResp.setPlainText("");
				 pageMessageResp.setLink(list);
				 pageMessageResp.setBomb_capion("");
			}
			//注册页面协议
			if("S007".equals(pageMessageReq.getPage_code())){
				pageMessageResp.setPlainText("本人已认真阅读并遵守《注册协议》及《信息收集授权协议》");
				 Link link=new Link();
				 link.setLocation("10");
				 link.setLength("6");
				 link.setUrl("https://m.hicash.cn/newweb/agreement/registAgreement.html?comeFrom=APP");
				 list.add(link);
				 
				 Link link2=new Link();
				 link2.setLocation("17");
				 link2.setLength("10");
				 link2.setUrl("https://m.hicash.cn/newweb/agreement/thirdParty.html?comeFrom=APP");
				 list.add(link2);
				 pageMessageResp.setLink(list);
				 pageMessageResp.setBomb_capion("请同意《注册协议》及《信息收集授权协议》");
			}
			//签约按钮:温馨提示(除嗨女神的其他所有行业)
			if("S008".equals(pageMessageReq.getPage_code())){
				
				resultCode = pageMessageVal.validate2();
				
				if (!ResultCodes.NORMAL.equals(resultCode)) {
					pageMessageResp.setResultCode(resultCode);
					pageMessageResp.setResultMsg(ResourceUtils.getString(resultCode));
				}else {
					ApplicationPay appPay = payQuery.queryCurrApplyAmount(pageMessageReq.getUserName());//查询申请金额和信贷产品id
					if(appPay!=null){
						String productId = appPay.getCreditProductId();
						String applyAmount = appPay.getApplyAmount();
						String realName = Base64.getBase64(appPay.getUserRealName());
						//测试地址
//						pageMessageResp.setSign_url("http://115.29.193.125/newweb/agreement/reminderText.html?loanProduct="+productId+"&tranPrice="+applyAmount+"&realName="+realName);
						//生产地址
						//pageMessageResp.setSign_url("http://m.hicash.cn/newweb/agreement/reminderText.html?loanProduct="+productId+"&tranPrice="+applyAmount+"&realName="+realName);		
						
						pageMessageResp.setSign_url(ResourceUtils.getValue("hicashAppUrl") + "/newweb/agreement/reminderText.html?loanProduct="+productId+"&tranPrice="+applyAmount+"&realName="+realName);	
					}	
				}
			}
			if("F001".equals(pageMessageReq.getPage_code())){
				 pageMessageResp.setSign_msg("利率按实际的借款月数收取，每月收取借款金额的\n1.0%；\n此外您还需对每笔成功的借款支付手机验证费、银行卡\n验证费、身份验证费、征信审核费、信息发布费、客户\n服务费、客户端维护费等的平台服务费以及账户管理\n费。");
			}if("F002".equals(pageMessageReq.getPage_code())){
				pageMessageResp.setSign_msg("利率按实际的借款月数收取，每月收取借款金额的\n0.7%-1.4%，实际利率将根据您的资料最终审核确定，\n提供真实有效的资料有助于提高您的审批通过率；\n此外您还需对每笔成功的借款支付手机验证费、银行卡\n验证费、身份验证费、征信审核费、信息发布费、客户\n服务费、客户端维护费等的平台服务费以及账户管理\n费。");
			}
			if("J001".equals(pageMessageReq.getPage_code())){
			pageMessageResp.setLimit_desc("请收到人行征信查询码后，再来补充。");
			pageMessageResp.setSign_msg("人行征信查询码会在24小时内发送到您手机。\n人行征信查询码有效期7天。\n请收到人行征信查询码后补充完成并提交。");
		    }
			
			pageMessageResp.setResultCode(resultCode);
			pageMessageResp.setResultMsg(ResourceUtils.getString(resultCode));
		}

		RecordUtils.writeResponse(logger, pageMessageResp.getUuid(),
				pageMessageResp);
		resp.getWriter().write(pageMessageResp.toJson());
	}

	public static void main(String[] args) {
		BigDecimal Amount = new BigDecimal(50000);

		Amount = Amount.subtract(new BigDecimal(5000));
		System.out.println(String.valueOf(Amount));
		Amount = Amount.subtract(new BigDecimal(10000));
		System.out.println(String.valueOf(Amount));
		Amount = Amount.divide(new BigDecimal(10000)).setScale(1);
		System.out.println(String.valueOf(Amount));
	}
	
	
	
	/**
	 * 嗨女神首页文案
	 * @param pageMessageResp
	 * @param pageMessageReq
	 * @return
	 */
	public PageMessageResp queryHNSHome(PageMessageResp pageMessageResp,PageMessageReq pageMessageReq){
		
		NSLimitQuery nsLimitQuery=new NSLimitQuery();
		NSLimit nsLimit=nsLimitQuery.queryLimit(pageMessageReq.getUserName());
		if(nsLimit==null){
			pageMessageResp.setLady_desc("完成女神认证 热门单品随心买");
			pageMessageResp.setLadylimit_desc("最高可享¥20000元分期购额度");
			pageMessageResp.setLadybut_msg_1("女神认证");
			pageMessageResp.setLadylimit_desc2("");
			pageMessageResp.setAmount_color_1("20000");
			pageMessageResp.setLady_type("1");
		}else {
			if("Y".equals(nsLimit.getIs_credit())){
				pageMessageResp.setLady_desc("完成女神认证 热门单品随心买");
				pageMessageResp.setLadylimit_desc("最高可享¥20000元分期购额度");
				pageMessageResp.setLadybut_msg_1("女神认证");
				pageMessageResp.setLadylimit_desc2("");
				pageMessageResp.setAmount_color_1("20000");
				pageMessageResp.setLady_type("1");
			}else{
				
				HinsApplyCredit hins=hinsApplyCreditQuery.queryRefuseApp(pageMessageReq.getUserName());
				
				 if (nsLimit.getStatus().equals("SXZ")){
					pageMessageResp.setLady_desc("完成女神认证 热门单品随心买");
					pageMessageResp.setLadylimit_desc("最高可享¥20000元分期购额度");
					pageMessageResp.setLadybut_msg_1("女神认证");
					pageMessageResp.setLadylimit_desc2("");
					pageMessageResp.setAmount_color_1("20000");
					pageMessageResp.setLady_type("1");
				}else if(hins.getStatus().equals("STATUS21")||hins.getStatus().equals("STATUS20"))
				{
					pageMessageResp.setLady_desc("完成女神认证 热门单品随心买");
					pageMessageResp.setLadylimit_desc("最高可享¥20000元分期购额度");
					pageMessageResp.setLadybut_msg_1("女神认证");
					pageMessageResp.setLadylimit_desc2("");
					pageMessageResp.setAmount_color_1("20000");
					pageMessageResp.setLady_type("1");
				} else{
					// 申请金额
					BigDecimal applyAmount = BigDecimal.ZERO;

					// 剩余额度
					BigDecimal syAmount = BigDecimal.ZERO;

					// 还款后返还金额
					BigDecimal returnAmt = BigDecimal.ZERO;	
					List<ApplicationPay> list = payQuery.queryAppPayList(
							pageMessageReq.getUserName(), Consts.HINS);

					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {

							applyAmount = applyAmount.add(new BigDecimal(list.get(i)
									.getApplyAmount()));


						}

					}			
					if (new BigDecimal(nsLimit.getAmount())
							.compareTo(applyAmount) >= 0) {
						syAmount = new BigDecimal(nsLimit.getAmount()).subtract(
								applyAmount).add(returnAmt);
					}
					logger.info("剩余额度:" + syAmount);
					int kyAmount = syAmount.intValue() / 100 * 100;
					logger.info("可用额度:" + kyAmount);	
					
					pageMessageResp.setLady_desc("欢迎女神！提额畅享狂买之旅");
					pageMessageResp.setLadylimit_desc("您的认证额度：¥"+nsLimit.getAmount()+"元");
					pageMessageResp.setLadybut_msg_1("女神请提额");
					pageMessageResp.setLadylimit_desc2("您的可用额度：¥"+syAmount.intValue()+""+"元");
					pageMessageResp.setAmount_color_1(nsLimit.getAmount());
					pageMessageResp.setAmount_color_2(syAmount.intValue()+"");
					pageMessageResp.setLady_type("2");
				}
				
			}
		}
		
		return pageMessageResp;
	}
}
