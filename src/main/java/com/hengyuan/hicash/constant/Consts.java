package com.hengyuan.hicash.constant;

import java.math.BigDecimal;

import com.alibaba.fastjson.parser.deserializer.StringFieldDeserializer;

public class Consts {
	
	public static final String ENCODING = "UTF-8"; 
	
	public static final String SO_ENCODING = "ISO-8859-1";
	
	public static final String FINAL_BOOLEAN_TRUE = "true";
	
	/** 嗨秒贷创建订单默认部门逻辑代码 */
	public static final String HMD_DEFAULT_AGENTLOGICCODE = "0004";
	
	/** 修改操作 */
	public static final String UPDATE = "1";
	
	/** 新增操作 */
	public static final String CREATE = "0";
	
	/** 设置为默认卡 */
	public static final String DEFAULT_CARD_YES = "Y";
	
	/** 不设置为默认卡 */
	public static final String DEFAULT_CARD_NO = "N";
	
	public static final String FINAL_NULL_STR = "null";
	
	public static final String HMD_UPLOAD_FROM = "秒贷APP端上传";
	
	public static final String JKO7 = "JKO7";
	
	
	/** 验证码过期时间 */
	public static final String CODE_TIME_OUT = "10";
	
	/** 最高额度涨幅 */
	public static final BigDecimal MAX_RATE = new BigDecimal("0.2");
	
	/** 周转哥行业代码 */
	public static final String ZZG_INDUSTRY_CODE = "ZZ01";
	
	/** 当天申请流水序号 */
	public static String APPFLOW_SEQ = "APPFLOW_SEQ";
	
	/** 临时申请序列 */
	public static String TEMP_APPFLOW_SEQ = "TEMP_APPFLOW_SQL";
	
	/** 代扣申请序列 */
	public static String PROXY_DEDUCT_SEQ = "PROXY_DEDUCT_SEQ";
	
	/** 门店号序列 */
	public static String SUP_STORE_SEQ = "SUP_STORE_SEQ";
	
	
	/** 员工号前缀 */
	public static final String USERNUMBER_PREX = "HYJR";
	
	public static final String DTY_CODE_PRTY = "PRTY";
	
	
	/* 客户类型 */
	
	/** 学生 */
	public static String CUSTTYPE_KHL1 = "KHL1";
	
	/** 白领 */
	public static String CUSTTYPE_KHL2 = "KHL2";
	
	/** 学生 */
	public static String CUSTTYPE_KHL1_NAME = "学生";
	
	/** 白领 */
	public static String CUSTTYPE_KHL2_NAME = "白领";
	
	/** 学生 */
	public static String APP_CUSTOMER_TYPE_KHL1 = "KHL1";
	
	/** 白领 */
	public static String APP_CUSTOMER_TYPE_KHL2 = "KHL2";
	
	/** 学生 */
	public static String APP_CUSTOMER_TYPE_KHL1_NAME = "学生";
	
	/** 白领 */
	public static String APP_CUSTOMER_TYPE_KHL2_NAME = "白领";
	
	/** 用户注册默认权限 */
	public static final String DEFAULT_AUTHORITY = "2";
	
	/* 学生类型 */
	
	/** 在校学生 */
	public static String STUDENT_TYPE_XSL1 = "XSL1";
	
	/** 应届无工作 */
	public static String STUDENT_TYPE_XSL2 = "XSL2";
	
	/** 应届有工作 */
	public static String STUDENT_TYPE_XSL3 = "XSL3";
	
	
	/* 学历 */
	
	/**预科*/
	public static final String EDUCATIONAL_1 ="EDC1";
	
	/**专科*/
	public static final String EDUCATIONAL_2 ="EDC2";
	
	/**本科*/
	public static final String EDUCATIONAL_3 ="EDC3";
	
	/**硕士*/
	public static final String EDUCATIONAL_4 ="EDC4";
	
	/**博士*/
	public static final String EDUCATIONAL_5 ="EDC5";
	
	/**专科以下*/
	public static final String EDUCATIONAL_6 = "EDC6";
	
	public static final String DEFAULT_AMOUNT="3000"; //默认额度
	
	/**白领授信额度**/
	
	public static final String COLLAR_CREDIT_AMOUNT_01="8000";//大专
	
	public static final String COLLAR_CREDIT_AMOUNT_02="10000";//本科
	
	public static final String COLLAR_CREDIT_AMOUNT_03="15000";//硕士
	
	public static final String COLLAR_CREDIT_AMOUNT_04="20000";//博士
	
	public static final String COLLAR_CREDIT_AMOUNT_05="100000";//调整后额度2015-04-18
	
	
	
	/* 白领授信额度 2015-01-19 改版 */
	
	/** 白领默认额度(大专以上)*/
	public static final String COLLAR_CREDIT_AMOUNT_DEFAULT = "10000";
	
	/** 白领最高额度 */
	public static final String COLLAR_CREDIT_AMOUNT_MAX = "50000";
	
	public static final String COLLAR_CREDIT_AMOUNT_PERIOD = "15";
	

	/**学生授信额度*/
	//额度Default
	
	public static final String CREDIT_AMOUNT_01="15000"; //博士研究生
	
	public static final String CREDIT_AMOUNT_02="10000";  //硕士研究生
	
	public static final String CREDIT_AMOUNT_06="6000";//专科普通
	
	public static final String CREDIT_AMOUNT_08="8000"; //211一本
	
	public static final String CREDIT_AMOUNT_09="20000"; //学生调整后额度2015-04-18
	
	
	
	
	/**已填写基本信息*/
	public static String NOT_BASIC="NOTID_3";
	
	/**用户授信额度要调用的服务代码*/
	public static String AMOUNT_SERVICE_CODE="USSX";
	
	/* 卡类型 */
	
	/** 借记卡 */
	public static String OPENBANK_CARD_TYPE = "JJKT";
	

	
	
	/** 注册成功后，客户身份选择 */
	public static final String PAGE_INDEX_0 = "0";
	
//	/** 获取额度信息提交页面-暂时没有进入逻辑 */
//	public static final String PAGE_INDEX_1 = "1";
	
	/** 充值激活页面，账户获取额度成功以后设置此值 */
	public static final String PAGE_INDEX_2 = "2";
	
	/** 充值激活账户成功后，设置此值 */
	public static final String PAGE_INDEX_3 = "3";
	
	
	
//	/** 学生申请提现页面，暂无进入逻辑处理 */
//	public static final String PAGE_INDEX_4 = "4";
	
	/** 学生申请提现-学校信息录入页面， 申请提现提交成功后设置*/
	public static final String PAGE_INDEX_5 = "5";
	
	/** 学生申请提现-个人联系信息，学校信息录入成功后设置此值*/
	public static final String PAGE_INDEX_6 = "6";
	
	/** 学生申请提现-联系人信息，个人联系信息录入成功后设置*/
	public static final String PAGE_INDEX_7 = "7";
	
	
//	/** 白领申请提现页面，暂无进入逻辑处理 */
//	public static final String PAGE_INDEX_8 = "8";
	
	/** 白领单位信息录入页面，白领提现成功后设置此值 */
	public static final String PAGE_INDEX_9 = "9";
	
	/** 白领资信信息录入页面，白领单位信息保存成功后设置此值 */
	public static final String PAGE_INDEX_10 = "10";
	
	/** 白领联系信息录入页面，白领资信信息保存成功后设置此值 */
	public static final String PAGE_INDEX_11 = "11";
	
	/** 白领联系人信息录入页面，白领联系信息保存成功后设置此值 */
	public static final String PAGE_INDEX_12 = "12";
	
	
	

	/* 公告查询类型 */
	
	/** 公告打开标志 */
	public static String NOTICE_QUERY_TYPE = "1";
	
	/* 修改学生学校信息(修改类型) */
	
	/** 完善资料 */
	public static String UPDATE_UNIVERSITY_TYPE_P = "P";
	
	/** 修改个人资料 */
	public static String UPDATE_UNIVERSITY_TYPE_U = "U";
	
	/* 交易类型 */
	
	/** 线上充值 */
	public static String TRAN_TYPE_XSCC = "XSCC";
	
	
	/* 远程调用 */
	
	/** 提交申请 */
	public static final String HCA0008 = "HC-A0008";
	
	/** 梦想基金计算器 */
	public static final String HCA0004 = "HC-A0004";
	
	/** UUID */
	public static final String UUID = "uuid";
	
	/** 用户名 */
	public static final String USERNAME = "userName";
	
	/** 用户类型 */
	public static final String CUSTTYPE = "custType";
	
	/** 流水号 */
	public static final String APPPAYNO = "appPayNo";
	
	/** 城市ID */
	public static final String CITYCODE = "cityCode";
	
	/** 分期类型 */
	public static final String PAYTYPE = "payType";
	
	/** 成交金额 */
	public static final String TRANPRICE = "tranPrice";
	
	/** 首付比例 */
	public static final String FIRSTRATE = "firstRate";
	
	/** 信贷产品ID */
	public static final String LOANPRODUCT = "loanProduct";
	
	/** 实物产品ID */
	public static final String PRODUCTID = "productId";
	
	
	

	/* 地址选择 */
	
	/** 学校地址 */
	public static final String UNIVERSITY_CODE = "AD01";
	
	/** 单位地址 */
	public static final String UNIT_CODE = "AD03";
	
	/** 家庭地址 */
	public static final String HOME_CODE = "AD02";
	
	/** 其他地址 */
	public static final String OTHER_CODE = "AD04";
	
	public static final int FINAL_INT_1 = 1;
	
	public static final int FINAL_INT_2 = 2;
	
	public static final int FINAL_INT_3 = 3;
	
	/** 常量0 */
	public static final String FINAL_NUMBER_0 = "0";
	
	/** 常量1 */
	public static final String FINAL_NUMBER_1 = "1";
	
	/** 常量2 */
	public static final String FINAL_NUMBER_2 = "2";
	
	/** 常量3 */
	public static final String FINAL_NUMBER_3 = "3";
	
	/** 常量4 */
	public static final String FINAL_NUMBER_4 = "4";
	
	/** 常量5 */
	public static final String FINAL_NUMBER_5 = "5";
	
	/** 常量6 */
	public static final String FINAL_NUMBER_6 = "6";
	
	/** 常量7 */
	public static final String FINAL_NUMBER_7 = "7";
	
	/** 常量8 */
	public static final String FINAL_NUMBER_8 = "8";
	
	public static final String PROXY_STR1 = "用户：{1}，时间：{2}，代扣扣款金额￥{3}";
	
	public static final String PROXY_STR2 = "代扣初始插入记录";
	
	/** 是否为电信申请 */
	public static final String IS_DX = "dx";
	public static final BigDecimal FINAL_DECIMAL_0 = new BigDecimal("0.00");
	
	public static final BigDecimal FINAL_DECIMAL_1 = new BigDecimal("1.00");
	public static final BigDecimal FINAL_DECIMAL_2 = new BigDecimal("5.00");
	public static final String REMOTE_RESULT_00 = "00";
	
	public static final String REMOTE_RESULT_01 = "01";
	
	public static final String REMOTE_RESULT_04 = "04";
	
	public static final String REMOTE_RESULT_404 = "404";
	
	/** 代扣鉴权发送短信成功 */
	public static final String PROXY_STATUS_CODE_SUCC = "CODESUCC";
	/** 代扣鉴权发送短信失败 */
	public static final String PROXY_STATUS_CODE_FAIL = "CODEFALT";
	
	/** 等待代扣 处理中状态 */
	public static final String PROXY_STATUS_WAIT = "WAIT";
	
	/** 代扣成功 */
	public static final String PROXY_STATUS_SUCC = "SUCC";
	
	/** 代扣失败 */
	public static final String PROXY_STATUS_FAIL = "FAIL";
	
	/** 余额不足 */
	public static final String PROXY_STATUS_YEBZ = "YEBZ";

	/** 民生银行实名认证发送短信成功 */
	public static final String SEND_CODE_STATUS_SUCC = "SENDSUCC";
	
	/** 民生银行实名认证发送短信失败 */
	public static final String SEND_CODE_STATUS_FAIL = "SENDFAIL";
	/** 民生银行实名认证处理中*/
	public static final String IDENTIFY_STATUS_WAIT = "VALWAIT";
	
	/** 民生银行实名认证成功 */
	public static final String IDENTIFY_STATUS_SUCC = "VALSUCC";
	
	/** 民生银行实名认证失败 */
	public static final String IDENTIFY_STATUS_FAIL = "VALFAIL";
	
	/* 婚姻状况 */
	
	/** 已婚 */
	public static final String HUNYIN_Q001 = "Q001";
	
	/** 未婚 */
	public static final String HUNYIN_Q002 = "Q002";
	
	/** 离异 */
	public static final String HUNYIN_Q003 = "Q003";
	
	/** 丧偶 */
	public static final String HUNYIN_Q004 = "Q004";
	
	
	/** 3C索引号 */
	public static String APPFLOW_TYPE_3C_INDEX = "7";
	
	/** 现金索引号 */
	public static String APPFLOW_TYPE_CASH_INDEX = "3";
	
	/** 申请类型-3C */
	public static String APPFLOW_TYPE_3C = "NORMAL";
	
	/** 申请类型-现金 */
	public static String APPFLOW_TYPE_CASH = "CASH";
	
	public static final String NORMAL_NAME = "数码分期";
	
	public static final String CASH_NAME = "现金分期";
	
	/** 信贷产品类型 */
	public static String CREDIT_PRODUCT_TYPE = "CRPT";
	
	/** 实物产品贷款 */
	public static String CREDIT_PRODUCT_CR01 = "CR01";
	
	/** 白领产品贷款 */
	public static String CREDIT_PRODUCT_CR02 = "CR02";
	
	/** 现金产品贷款 */
	public static String CREDIT_PRODUCT_CR03 = "CR03";
	
	public static final String QUERY_SITE_MARK = "DETAIL";
	
	/** hicashAPP频道分类 */
	public static final String CHANNEL_TYPE_HICASH = "HICASH";
	
	/** hicashAPP频道分类 */
	public static final String CHANNEL_TYPE_APP = "APP";
	
	/** 调用还款计算器*/
	public final static String SERVICE_PAY_CALCULE_AMT = "AS003";
	
	/** 调用还款计算器*/
	public final static String SERVICE_PAY_CALCULE_AMT_NEW = "AS004";
	
	/** 用户银行账户信息验证 */
	public final static String SERVICE_BANKCARD_VAL = "ZX001";
	
	/** 代扣扣款 */
	public final static String SERVICE_BANKCARD_PROXYDEDUCT = "ZX002";
	
	/** 代扣快捷鉴权 */
	public final static String SERVICE_BANKCARD_PROXYDEDUCT_NEW = "ZX003";
	/** 代扣快捷充值 */
	public final static String SERVICE_BANKCARD_PROXYDEDUCT_RECHARGE = "ZX004";
	/** 身份实名认证发送短信验证码 */
	public final static String CMBC_IDENTIFY_SENDCODE = "ZX005";
	/**民生实名认证验证验证码*/
	public final static String CMBC_IDENTIFY_SENDCODE_VAL = "ZX006";
	/**民生实名认证结果查询*/
	public final static String CMBC_IDENTIFY_QUERY_VAL = "ZX007";
	/** 验证身份证是否在黑名单中 */
	public final static String SERVICE_SFHMD = "SFHMD";
	
	/** 同盾规则验证 */
	public final static String SERVICE_TD001 = "TD001";
	
	/** 鹏元征信大学生评分接口 */
	public final static String SERVICE_PY001 = "PY001";
	
	/** 鹏元征信个人风险汇总查询接口 */
	public final static String SERVICE_PY002 = "PY002";
	
	/** 91征信报告接口 */
	public final static String SERVICE_91ZX1 = "91ZX1";
	
	/** 诺亚金通实名认证接口 */
	public final static String SERVICE_NY001 = "NY001";
	
	/** 查询手机号归属地 */
	public final static String SERVICE_SJGS = "SHGS";
	
	/**  */
	public final static String FIRM_USER_NAME = "HUITONG_FINANCE";
	
	/** 默认贷款计算期数 */
	public static final Integer DEFAULT_PERIOD = 12;
	
	/** 默认贷款费率 */
	public static final BigDecimal DEFAULT_CREDIT_RATE = new BigDecimal(0);
	
	/* 初始申请件状态 */
	
	/** 默认大节点 */
	public final static String FIRST_ALL_NODE = "NENODE";
	
	/** 默认节点 */
	public final static String FIRST_NODE = "NODE01";
	
	/** 默认状态 */
	public final static String FIRST_STATUS = "STATUS01";

	/** 芝麻信用默认状态 */
	public final static String ZMXY_STATUS = "STATUS58";
	
	/** 等待系统审核 */
	public final static String SYS_STATUS = "STATUS48";
			
	/** 默认比率 */		
	public final static String PAY_RATE = "10.00";
	
	/** 默认比率 */		
	public final static String DEAFAULT_RATE = "0.00";
	
	/** 生成申请件单号的3-4位编号 */
	public static final String APPNO_GENERATE_SEQ = "01";
	
	/**命中*/
	public static final String hits ="1";
	
	/**未命中*/
	public static final String unhits ="2";
	
	/** 进件渠道 */
	public static final String INCOME_HAPP = "HAPP";
	
	/** 教育培训类 */
	public static String PRODUCT_TYPE_EDU = "04";
	
	/** 驾校贷 */
	public static String PRODUCT_TYPE_CAR = "07";
	
	/** 助业贷 */
	public static String PRODUCT_TYPE_JOB = "08";
	
	/** 助房贷 */
	public static String PRODUCT_TYPE_HOURSE = "09";
	
	/** 助学贷 */
	public static String PRODUCT_TYPE_STU = "10";
	
	/** 旅游贷 */
	public static String PRODUCT_TYPE_CTOUR = "11";
	
	/* 上传图片时用到的请求参数名 */
	
	/** 用户名 */
	public static final String UPLOAD_USERNAME = "userName";
	
	/** 图片类型 */
	public static final String UPLOAD_IMGTYPE = "imgType";
	
	/** 临时申请件单号 */
	public static final String UPLOAD_TEMPAPPNO = "tempAppNo";
	
	/** 图片上传路径 */
	public static final String PIC_UPLOAD_PATH = "pic_upload_path";
	
/* 图片后缀名*/
	
	public static final String PIC_AGO=".jpg";
		
	public static final String PIC_JPEG = "JPEG";
	
	public static final String PIC_TEMP = "/temp/app_pic/";
	
	public static final String SEPARATOR = "/";
	
	/** 用户手持身份证正面照 */
	public static final String IMG_TYPE_ZL112 = "ZL112";
	
	/** 现场照片*/
	public static final String IMG_TYPE_ZL01 = "ZL01";
	
	/** 身份证正面 */
	public static final String IMG_TYPE_ZL02 = "ZL02";
	
	/** 学生证封面 */
	public static final String IMG_TYPE_ZL04 = "ZL04";
	
	/** 个人照片及基本信息 */
	public static final String IMG_TYPE_ZL05 = "ZL05";
	
	/** 注册登记信息*/
	public static final String IMG_TYPE_ZL06 = "ZL06";
	
	/** 身份证反面 */
	public static final String IMG_TYPE_ZL03 = "ZL03";
	
	/** 校园卡正面 */
	public static final String IMG_TYPE_ZL07 = "ZL07";
	
	/** 校园卡正面 */
	public static final String IMG_TYPE_ZL08 = "ZL08";
	
	/** 收入证明 	 */
	public static final String IMG_TYPE_ZL84 = "ZL84";
	
	/** 工作证明 */
	public static final String IMG_TYPE_ZL137 = "ZL137";
	
	/** 银行卡照片正面 */
	public static final String IMG_TYPE_ZL40 = "ZL40";
	
	/** 银行卡照片反面 */
	public static final String IMG_TYPE_ZL80 = "ZL80";
	
	public static final String PIC_RESOURCE_URL = "resource_url";
	
	/** 商户店铺url */
	public static final String FENQIMALL_URL = "mer_fenqimall_url";
	
	/** 商品图片资源url */
	public static final String PROPIC_RESOURCE_URL = "pro_pic_resource";
	
	/** 创建渠道 */
	public static final String CREATEAPP_FROM_HICASHAPP = "嗨秒贷-app";
	
	/** 创建渠道 电信专项*/
	public static final String CREATEAPP_FROM_HICASHAPPDX = "电信专项";
	
	/** 银行卡类型：借记卡 */
	public static final String CARD_TYPE_JJKT = "JJKT";
	
	/** 聚立信提交类型 */
	public static final String SUBMITTYPE_MOBILE = "MOBILE";
	
	/** 商户管理员默认权限ID */
	public static final String SUPPLIER_ADMIN_AUTHRO = "20";
	
	/** 二级行业代码-嗨秒贷*/
	public static final String INDUSTRY_MDCP = "MDCP";
	
	/** 老客户-嗨秒贷*/
	public static final String INDUSTRY_MDOH = "MDOH";
	
	/** 蓝领-嗨秒贷*/
	public static final String INDUSTRY_LLMD = "LLMD";
	
	/** 滴答贷*/
	public static final String INDUSTRY_LDDD = "LDDD";
	
	/** VIP贷*/
	public static final String INDUSTRY_VIPD = "VIPD";
	/** 滴滴贷*/
	public static final String INDUSTRY_DDCP = "DDCP";
	
	/** 二级行业代码-电信专案*/
	public static final String INDUSTRY_DXSM = "DXSM";
	
	/** 黑名单请求地址*/
	public static final String BLACKURL="blackUrl";
	/** 黑名单请求用户名*/
	public static final String	BLACKUSERNAME="blackUserName";
	/** 黑名单请求密码*/
   public static final String	BLACKPASSWORK="blackPassWord";
   /** 黑名单请求加密字符串*/
   public static final String	APIKEY = "apikey";
   /** 黑名单请求方法*/
   public static final String	BLACKMETHOD = "blackMethod";
   
   
   /* 接口来源 */
   
   /** 秉承接口 */
   public static final String FROMTYPE_FRBC = "FRBC";
   
   public static final String FINDAL_DESC1 = "秉承黑名单调用";
   
// 直系亲属关系
	/** 父亲 */
	public static String RELATION_ZX_01 = "DIR1";
	/** 母亲 */
	public static String RELATION_ZX_02 = "DIR2";
	/** 子女 */
	public static String RELATION_ZX_03 = "DIR3";
	/** 配偶 */
	public static String RELATION_ZX_04 = "DIR4";

	// 紧急联系人关系
	/** 父亲 */
	public static String RELATION_JJ_01 = "EMR1";
	/** 母亲 */
	public static String RELATION_JJ_02 = "EMR2";
	/** 子女 */
	public static String RELATION_JJ_03 = "EMR3";
	/** 配偶 */
	public static String RELATION_JJ_04 = "EMR4";
	/** 兄弟姐妹 */
	public static String RELATION_JJ_05 = "EMR5";
	/** 同事 */
	public static String RELATION_JJ_06 = "EMR6";
	/** 朋友 */
	public static String RELATION_JJ_07 = "EMR7";
	/** 同学 */
	public static String RELATION_JJ_08 = "EMR8";
	
	
	public static final String INCOMETYPE_LLSM = "LLSM";
	/** 默认员工号 */
	public static final String HY_DEFAULT_EMPLOYEECODE = "0002";
	/** 临时申请自增长流水序号 */
	public static String TEMP_APP_SEQ = "TEMP_APPFLOW_SQL";
	
	

	
	/** 默认身份证号码 */
	public static final String DFT_IDENTITY = "111111111111111111";
		/** 军人 */
	public static String APP_CUSTOMER_TYPE_KHL3 = "KHL3";
	
	/** 嗨秒贷行业申请金额3000 */
	public static String HMD_PRICE_BURST = "3000";
	
	/** 嗨秒贷行业申请金额4000 */
	public static String HMD_PRICE_BURST_001 = "4000";
	/**代扣方*/
	public static String NYDK="NYDK";
	public static String ZTDK="ZTDK";
	public static String ALLDK="ALLDK";
	public static String ALLNO="ALLNO";

	public static String ZMXY_URL="zmxy_url";
	
	public static String PDL_AUTH_URL="pdl_auth_url";
	public static String PDL_APP_URL="pdl_app_url";
	public static String JXL_APP_URL="jxl_app_url";
	public static String JXL_AUTH_URL="jxl_auth_url";
	public static String DRIVER_CREDIT_URL="driver_credit_url";
	public static String MOBILE_URL="mobile_url";
	public static String PIC_SERVER_URL = "pic_server_url";
	
	/** 设置为Y */
	public static final String DEFAULT_YES = "Y";
	
	
	/** 亨元投资人 */
	public static String HENGYUAN_USERNAME = "david_fu";
	
	/** 常量Y */
	public static final String FINAL_NUMBER_Y = "Y";
	/** 常量N */
	public static final String FINAL_NUMBER_N = "N";
	/** 申请类型-滴滴司机 */
	public static String APPFLOW_TYPE_DDSJ = "DDSJ";
	public static final String INDUSTRY_DDSJ = "DDSJ";
	/** 司机贷临时申请号开头号码 */
	public static String APPFLOW_TYPE_DDSJ_INDEX = "5";
	/** 提额 */
	public static String DDSJ_CREDIT_TYPE_TE = "TE";
	/** 授信 */
	public static String DDSJ_CREDIT_TYPE_SX = "SX";
	
	/** 授信审核中 */
	public static String DDSJ_CREDIT_TYPE_SXZ= "SXZ";
	/** 提额审核中 */
	public static String DDSJ_CREDIT_TYPE_TEZ= "TEZ";
	/** 审核完成 */
	public static String DDSJ_CREDIT_TYPE_TNOML= "NOML";	/** 贷款计算出现异常 */
	/** 贷款计算出现异常 */
	public static final String DDSJ_LIMIT_EXCEPTION = "37534";
	/** 贷款计算出现异常 */
	public static final String APPLICATION_STATUS21 = "STATUS21";//审核拒绝
	
	
	public static final String PIC_THUM_URL = "PIC_THUM_URL";
	
	public static final String PIC_BIG_URL = "PIC_BIG_URL";
	/** 默认节点 */
	public final static String SHENHE_NODE = "SHNODE";
	
	/**嗨女神行业代码*/
	public final static String HINS = "HINS";
	
	/*嗨女神商品图片URL*/
	public final static String HINS_PIC_URL = "PIC_HINS_THUM_URL";
	//app图片地址
	public static final String APP_PIC_URL = "APP_PIC_URL";
	//h5前缀
	public static final String H5_URL = "h5_url";
	
	/**用户在其他现金类产品中(（滴答贷，嗨秒贷，嗨钱来）),存在还款中的单子，则申请嗨女神体现额度不能大于1000**/
	public static final int HINS_DISABLE_AMOUNT = 1000;
	
	public final static String xuexin_info = "xuexin_info";
	//认证状态 去验证
	public static String AUTHEN_STATUS_0="0";
	//认证状态 认证中
	public static String AUTHEN_STATUS_1="1";
	//认证状态 已认证
	public static String AUTHEN_STATUS_2="2";
	//认证状态 待补充
	public static String AUTHEN_STATUS_3="3";

		
	//精英分期
	public static String JYFQ="JYFQ";
	
	//滴滴司机
	public static String DDSJ="DDSJ";
	
	//临时单-认证状态初始值
	public static final String INIT_AUTH_STATUS = "0000000000";
	//订单状态：正常
	public static final String NORMAL_STATUS = "0";
	//订单状态：取消
	public static final String CANCLE_STATUS = "1";
	

}