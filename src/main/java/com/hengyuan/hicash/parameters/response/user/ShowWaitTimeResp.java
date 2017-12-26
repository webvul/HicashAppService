package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.entity.user.Agreement;
import com.hengyuan.hicash.entity.user.Link;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * @author 2017年1月9日 下午9:44:25 类说明 获取借款进度 返回结果
 */
public class ShowWaitTimeResp extends ParmResponse {
	
	
	//总金额
	private String applyAmt;
	//用户名 
	private  String userName;
	// 审批等待时间
	private String time;
	// 最大审批等待时间
	private String maxTime;
	// 期数
	private String term;
	// 月供
	private String monthPayAmt;
	// 当前时间
	private String date;
	// 审批是否通过
	private String is_pass;
	// 产品名称
	private String pro_name;
	//文本提示1
	private String textVersion;
	//文本提示2
	private String textVersionto;
	//连接文本
	private String  plainText ;
	//地址   数组
	private List<Link>  link;
	//合同金额
	private String tranPrice;
	//贷款产品id
	private String loanProduct;
	//行业代码
	private String industryCode;
	
	private String iCode;
	
	private String realAddress;
	
	//是否使用优惠券 1使用
	private String isUseDis;
	
	//优惠券信息
	private String discount_name;
	
	private String discount_str;
	//商品名称
	private String goodsName;
	//商品图片
	private String picture;
	//每期应还本息金额(最小)
	private String lowPay;
	//每期应还本息金额(最大)
	private String highPay;
	
	//vip现金抵用券 
	private String vipCashVoucher;

	//确认金额签约文本
	private String confirmCashAndSignText;
	
	//提示人脸识别文本
	private String faceRecognitionText;
	
	//同意签约协议文本
	private String agreeSignText;

	//是否显示人脸识别按钮 1:显示 2:不显示
//	private String showFaceButton;
	
	//进件来源（申请来源）
	private String applyFrom;

	//h5签约app进件文字提示
	private String h5SignIfFromAppText;
	
	//签约协议链接数组
	private List<Link> agreeSignLinks;
	
	//是否是嗨钱来; 1:是嗨钱来 0:不是嗨钱来
	private String isHiCash;
	
	//人脸识别认证成功文本
	private String faceRecognitionSuccessText;
	
//	//嗨钱来借款费率说明
//	private String rateADesc;
//	
//
//	//滴答，秒贷，vip,司机贷借款费率说明
//	private String rateBDesc;
	
	private String isCancel;
	
	private List<Agreement> agreements;//返回协议链接App
	
	private List<Agreement> agreementsH5;//返回协议链接H5
	
	
	public void setConfirmCashAndSignText(String confirmCashAndSignText) {
		this.confirmCashAndSignText = confirmCashAndSignText;
	}
	public void setAgreeSignText(String agreeSignText) {
		this.agreeSignText = agreeSignText;
	}
	public void setH5SignIfFromAppText(String h5SignIfFromAppText) {
		this.h5SignIfFromAppText = h5SignIfFromAppText;
	}
	public void setFaceRecognitionSuccessText(String faceRecognitionSuccessText) {
		this.faceRecognitionSuccessText = faceRecognitionSuccessText;
	}
//	public void setRateADesc(String rateADesc) {
//		this.rateADesc = rateADesc;
//	}
//	public void setRateBDesc(String rateBDesc) {
//		this.rateBDesc = rateBDesc;
//	}
	public String getVipCashVoucher() {
		return vipCashVoucher;
	}
	public void setVipCashVoucher(String vipCashVoucher) {
		this.vipCashVoucher = vipCashVoucher;
	}
	public String getFaceRecognitionText() {
		return faceRecognitionText;
	}
	public void setFaceRecognitionText(String faceRecognitionText) {
		this.faceRecognitionText = faceRecognitionText;
	}
	public String getAgreeSignText() {
		return agreeSignText;
	}

	public String getH5SignIfFromAppText() {
		return h5SignIfFromAppText;
	}

	public List<Link> getAgreeSignLinks() {
		return agreeSignLinks;
	}
	public void setAgreeSignLinks(List<Link> agreeSignLinks) {
		this.agreeSignLinks = agreeSignLinks;
	}
	public String getIsHiCash() {
		return isHiCash;
	}
	public void setIsHiCash(String isHiCash) {
		this.isHiCash = isHiCash;
	}
	public String getFaceRecognitionSuccessText() {
		return faceRecognitionSuccessText;
	}

	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getMonthPayAmt() {
		return monthPayAmt;
	}
	public void setMonthPayAmt(String monthPayAmt) {
		this.monthPayAmt = monthPayAmt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIs_pass() {
		return is_pass;
	}
	public void setIs_pass(String is_pass) {
		this.is_pass = is_pass;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getApplyAmt() {
		return applyAmt;
	}
	public void setApplyAmt(String applyAmt) {
		this.applyAmt = applyAmt;
	}

	public String getTextVersion() {
		return textVersion;
	}
	public void setTextVersion(String textVersion) {
		this.textVersion = textVersion;
	}
	public String getTextVersionto() {
		return textVersionto;
	}
	public void setTextVersionto(String textVersionto) {
		this.textVersionto = textVersionto;
	}
	public String getPlainText() {
		return plainText;
	}
	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}
	public List<Link> getLink() {
		return link;
	}
	public void setLink(List<Link> link) {
		this.link = link;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}
	
	
	public String getTranPrice() {
		return tranPrice;
	}
	public void setTranPrice(String tranPrice) {
		this.tranPrice = tranPrice;
	}
	public String getLoanProduct() {
		return loanProduct;
	}
	public void setLoanProduct(String loanProduct) {
		this.loanProduct = loanProduct;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getiCode() {
		return iCode;
	}
	public void setiCode(String iCode) {
		this.iCode = iCode;
	}
	public String getRealAddress() {
		return realAddress;
	}
	public void setRealAddress(String realAddress) {
		this.realAddress = realAddress;
	}
	public String getDiscount_str() {
		return discount_str;
	}
	public void setDiscount_str(String discount_str) {
		this.discount_str = discount_str;
	}
	public String getIsUseDis() {
		return isUseDis;
	}
	public void setIsUseDis(String isUseDis) {
		this.isUseDis = isUseDis;
	}
	public String getDiscount_name() {
		return discount_name;
	}
	public void setDiscount_name(String discount_name) {
		this.discount_name = discount_name;
	}
	public String getLowPay() {
		return lowPay;
	}
	public void setLowPay(String lowPay) {
		this.lowPay = lowPay;
	}
	public String getHighPay() {
		return highPay;
	}
	public void setHighPay(String highPay) {
		this.highPay = highPay;
	}
	
	public String getConfirmCashAndSignText() {
		return confirmCashAndSignText;
	}
	
	public String getApplyFrom() {
		return this.applyFrom;
	}
	
	public void setApplyFrom(String applyFrom) {
		this.applyFrom = applyFrom;
	}
	public String getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}
	public List<Agreement> getAgreements() {
		return agreements;
	}
	public void setAgreements(List<Agreement> agreements) {
		this.agreements = agreements;
	}
	public List<Agreement> getAgreementsH5() {
		return agreementsH5;
	}
	public void setAgreementsH5(List<Agreement> agreementsH5) {
		this.agreementsH5 = agreementsH5;
	}
	
//	public String getRateADesc() {
//		return rateADesc;
//	}
//	
//	public String getRateBDesc() {
//		return rateBDesc;
//	}
	
	
}
