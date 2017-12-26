package com.hengyuan.hicash.constant;

public class ProcessConst {
	
	/*     流程节点    */
	
	/** 申请件认领 */
	public static String NODE01 = "NODE01";
	
	/** 设置产品 */
	public static String NODE02 = "NODE02";
	
	/** 设置客户资料 */
	public static String NODE03 = "NODE03";
	
	/** 上传附件 */
	public static String NODE04 = "NODE04";
	
	/** 上传协议签字页 */
	public static String NODE05 = "NODE05";
	
	/** 信用审核中 */
	public static String NODE06 = "NODE06";
	
	/** 支付首付款 */
	public static String NODE07 = "NODE07";
	
	/** 提货 */
	public static String NODE08 = "NODE08";
	
	/** 上传销售凭证 */
	public static String NODE09 = "NODE09";
	
	/** 销售完成 */
	public static String NODE10 = "NODE10";
	
	/** 还款中 */
	public static String NODE11 = "NODE11";
	
	/** 交易关闭 */
	public static String NODE12 = "NODE12";
	
	
	
	/*     流程状态      */
	
	/** 未认领 */
	public static String STATUS01 = "STATUS01";
	
	/** 已认领 */
	public static String STATUS02 = "STATUS02";
	
	/** 设置产品 */
	public static String STATUS03 = "STATUS03";
	
	/** 设置资料 */
	public static String STATUS04 = "STATUS04";
	
	/** 上传附件 */
	public static String STATUS05 = "STATUS05";
	
	/** 上传协议签字页 */
	public static String STATUS06 = "STATUS06";
	
	/** 等待初审 */
	public static String STATUS07 = "STATUS07";
	
	/** 初审中 */
	public static String STATUS08 = "STATUS08";
	
	/** 等待终审 */
	public static String STATUS09 = "STATUS09";
	
	/** 终审中 */
	public static String STATUS10 = "STATUS10";
	
	/** 审核通过 */
	public static String STATUS11 = "STATUS11";
	
	/** 等待提货 */
	public static String STATUS12 = "STATUS12";
	
	/** 上传销售凭证 */
	public static String STATUS13 = "STATUS13";
	
	/** 销售凭证确认 */
	public static String STATUS14 = "STATUS14";
	
	/** 财务确认 */
	public static String STATUS15 = "STATUS15";
	
	/** 地方行政确认 */
	public static String STATUS16 = "STATUS16";
	
	/** 等待归档 */
	public static String STATUS17 = "STATUS17";
	
	/** 归档中 */
	public static String STATUS18 = "STATUS18";
	
	/** 归档完成 */
	public static String STATUS19 = "STATUS19";
	
	/** 取消申请 */
	public static String STATUS20 = "STATUS20";
	
	/** 申请拒绝 */
	public static String STATUS21 = "STATUS21";
	
	/** 等待特权审批 */
	public static String STATUS22 = "STATUS22";
	
	/** 特权审批中 */
	public static String STATUS23 = "STATUS23";
	
	/** 审批驳回 */
	public static String STATUS24 = "STATUS24";
	
	/**销售凭证驳回*/
	public static String STATUS25 = "STATUS25";
	
	/** 终审驳回 */
	public static String STATUS26 = "STATUS26";
	
	/** 交易完成 */
	public static String STATUS27 = "STATUS27";
	
	
	
	/* 大节点 */
	
	/** 新申请 */
	public static String ALL_NODE_NE = "NENODE";
	
	/** 资料准备中 */
	public static String ALL_NODE_ZL = "ZLNODE";
	
	/** 审核中 */
	public static String ALL_NODE_SH = "SHNODE";
	
	/** 提货中 */
	public static String ALL_NODE_TH = "THNODE";
	
	/** 销售完成 */
	public static String ALL_NODE_WC = "WCNODE";
	
	/** 还款中 */
	public static String ALL_NODE_HK = "HKNODE";
	
	/** 交易关闭 */
	public static String ALL_NODE_GB = "GBNODE";
	

	
	/* 动作代码 */
	
	/** 认领申请件 */
	public static String AC01 = "AC01";
	
	/** 设置产品价格*/
	public static String AC02 = "AC02";
	
	/** 设置产品下一步*/
	public static String AC03 = "AC03";
	
	/** 设置资料下一步*/
	public static String AC04 = "AC04";
	
	/** 设置资料上一步*/
	public static String AC05 = "AC05";
	
	/** 附件上传完成*/
	public static String AC06 = "AC06";
	
	/** 附件上传上一步*/
	public static String AC07 = "AC07";
	
	/** 提交申请 */
	public static String AC08 = "AC08";
	
	/** 上传协议上一步 */
	public static String AC09 = "AC09";
	
	/** 开始初审 */
	public static String AC10 = "AC10";
	
	/** 初审通过 */
	public static String AC11 = "AC11";
	
	/** 初审驳回 */
	public static String AC12 = "AC12";
	
	/** 终审驳回至商户 */
	public static String AC17 = "AC17";
	
	/** 终审通过 */
	public static String AC14 = "AC14";
	
	/** 特权审批通过 */
	public static String AC20 = "AC20";
	
	/** 销售凭证确认 */
	public static String AC24 = "AC24";
	
	/** 特权审批拒绝申请 */
	public static String AC21 = "AC21";
	
	/** 终审拒绝申请 */
	public static String AC15 = "AC15";
	
	/**提货码确认*/
	public static String AC23 = "AC23";
	
	/** 设置产品下一步*/
	public static String AC31 = "AC31";
	
	/** 取消申请 */
	public static String AC34 = "AC34";
	
	/**交易关闭*/
	public static String AC35 = "AC35";
	
	/**上传销售凭证     	销售凭证驳回*/
	public static String AC32 = "AC32";
	
	/** 开始初审， 终审驳回状态 */
	public static String AC33 = "AC33";
	
	/** 开始终审 */
	public static String AC13 = "AC13";
	
	/** 财务确认通过 */
	public static String AC28 = "AC28";
	
	/** 首付款支付完成 */
	public static String AC22 = "AC22";
	
	/** 销售凭证驳回 */
	public static String AC26 = "AC26";
	
	/** 取消申请 */
	
	/** 客服取消申请 */
	
	
	
	/* 操作日志来源 */
	
	/** 商户系统 */
	public static String HIS_TYPE_MER = "MER";
	
	/** 审批系统 */
	public static String HIS_TYPE_APPR = "APPR";
	
}
