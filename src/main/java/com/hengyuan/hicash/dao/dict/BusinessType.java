package com.hengyuan.hicash.dao.dict;

/**
 * 短信公共类
 * 
 * @author Eric
 * @create date 2014-07-22
 *
 */
public enum BusinessType {
	REGP("注册验证"), REGS("注册成功"), REGN("新注册成功"),REGI("新注册成功"), RSPW("重置密码"), FREA("冻结/激活"), BTPP("绑定手机验证"), IDNT(
			"身份认证通知"), LPNT("借款进度通知"), LFSL("借款满标通知"), RCMN("账户提现通知"), CNTS(
			"提现成功通知"), CNTF("提现失败通知"), NAPR("借款成功通知"), NAJR("借款失败通知"), FWNT(
			"流标通知"), RVNT("撤回通知"), CSET("签订借款合同通知"), APNO("提交申请通知"), INNO(
			"投资成功通知"), CUNO("客户预约通知"), MENO("预约提醒通知"), CMNO("更改预约通知"), LANO(
			"终审通知短信"), FANO("面签完成通知"), PANO("付款成功通知"), RENO("分案通知代表"), PMNO(
			"通知还款计划"), PMMY("还款提醒"), FMNO("扣款未成功"), PSNO("还款成功"), PPSN("部分还款成功"), RCSO(
			"充值成功"), MONO("逾期通知"), MPNO("提前还款申请"), RPNO("提前还款结果"), PASU(
			"分期申请成功"), CWDC("客户待分案"), CEPF("客户进入审批流程"), CBAN("客户回退通知"), CCAN(
			"客户取消申请"), FZSM("防诈骗短信") ,SHZC("商户入驻注册") ,HMDA("嗨秒贷申请通知") ,DXZA("电信专案申请通知"),
			SQDO("申请通知短信1"),SQDT("申请通知短信2"), SQJJ("申请拒绝通知");

	private String dispValue;

	BusinessType(String dispValue) {
		this.dispValue = dispValue;
	}

	public String getDispValue() {
		return dispValue;
	}
}
