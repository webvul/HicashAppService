package com.hengyuan.hicash.parameters.response.calculate;

import java.util.List;

import com.hengyuan.hicash.entity.RepayProgramEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 
 * 获取还款方案参数返回类
 * 
 * @author Andy.Niu
 * @create date 2014-07-23
 */
public class RepayProgramResp extends ParmResponse {

	/** 返回代码 */
	/* private String resultCode; */

	/** 还款方案集合 */
	private List<RepayProgramEntity> perList;
	
	/** 还款方案集合 */
	private List<RepayProgramEntity> dayList;

	/** 返点比例 */
	private String rebatePercent;

	/** 可用额度 */
	private String kyAmount;

	/** 是否Vip */
	private String isVip;

	/** 可用最小额度 */
	private String minAmount;

	/** 状态 */
	private String status;

	/** vip等级 **/
	private String custVipGrade;
	
	/** 14天Vip可申请金额控制 **/
	private String appAmountFourteen;

	// 准vip按钮文字
	private String btn_str;
	// 准vip弹框标题
	private String box_title;
	// 准vip弹框内容
	private String box_msg;
	// 准vip弹框按钮文字
	private String box_btn;
	//是否授信
	private String iscredit;

	public String getRebatePercent() {
		return rebatePercent;
	}

	public void setRebatePercent(String rebatePercent) {
		this.rebatePercent = rebatePercent;
	}

	public List<RepayProgramEntity> getPerList() {
		return perList;
	}

	public void setPerList(List<RepayProgramEntity> perList) {
		this.perList = perList;
	}

	public List<RepayProgramEntity> getDayList() {
		return dayList;
	}

	public void setDayList(List<RepayProgramEntity> dayList) {
		this.dayList = dayList;
	}

	public String getKyAmount() {
		return kyAmount;
	}

	public void setKyAmount(String kyAmount) {
		this.kyAmount = kyAmount;
	}

	public String getIsVip() {
		return isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}

	public String getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(String minAmount) {
		this.minAmount = minAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustVipGrade() {
		return custVipGrade;
	}

	public void setCustVipGrade(String custVipGrade) {
		this.custVipGrade = custVipGrade;
	}
	
	public String getBtn_str() {
		return btn_str;
	}

	public void setBtn_str(String btn_str) {
		this.btn_str = btn_str;
	}

	public String getBox_msg() {
		return box_msg;
	}

	public void setBox_msg(String box_msg) {
		this.box_msg = box_msg;
	}

	public String getBox_btn() {
		return box_btn;
	}

	public void setBox_btn(String box_btn) {
		this.box_btn = box_btn;
	}

	public String getBox_title() {
		return box_title;
	}

	public void setBox_title(String box_title) {
		this.box_title = box_title;
	}

	public String getIscredit() {
		return iscredit;
	}

	public void setIscredit(String iscredit) {
		this.iscredit = iscredit;
	}

	public String getAppAmountFourteen() {
		return appAmountFourteen;
	}

	public void setAppAmountFourteen(String appAmountFourteen) {
		this.appAmountFourteen = appAmountFourteen;
	}
	
	
}
