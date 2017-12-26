package com.hengyuan.hicash.entity.param;

/**
 * 贷款状态枚举
 * 
 * @author LiHua.Ren
 * @create 2014-12-18
 */
public enum LoanStatus {
	
	LNEW("新建"),LPUB("正在投标中"),LWDR("已撤回"),
		LFIN("待审批"),//用于录入征信数据
		LWCS("待审批"),//用户电话调查
		LISP("借款审批中"),//用于借款审批环节
		LIEL("借款审批中"),//用于审批借款合同环节
		LFIS("还款完成"),LPFL("审核失败"),LEND("借款成功"),FLMR("流标");
	
	
	private String dispValue;

	LoanStatus(String dispValue) {
		this.dispValue = dispValue;
	}
	public String getDispValue() {
		return dispValue;
	}

}