package com.hengyuan.hicash.constant;


/**
 * 
 * 接口返回代码集合 客户信息服务类接口代码返回结果以3开头，依次排序
 * 
 * @author Cary.Liu
 * @createDate 2015-05-25
 */
public class ResultCodes {

	/** 接口处理正常时返回代码 */
	public static final String NORMAL = "1";

	/** UUID为空 */
	public static final String UUIDNULL = "2";

	/** UUID非法字符 */
	public static final String UUIDILLEGAL = "3";

	/** 没有找到结果 */
	public static final String NO_RESULT = "4";

	/** TOKEN验证成功 */
	public static final String ACCESS_IS_SUCC = "200";

	/** TOKEN验证失败 */
	public static final String ACCESS_IS_FAIL = "500";

	/* 发送获取额度验证码 */

	/** 发送验证码异常 */
	public static final String SENDAMOUNTCODE_EXCEPTION = "30000";

	/** 保存验证码失败 */
	public static final String SENDAMOUNTCODE_SAVECODE_FAIL = "30001";

	/** 验证码发送失败 */
	public static final String SENDAMOUNTCODE_SENDCODE_FAIL = "30002";

	/** 用户名不存在 */
	public static final String SENDAMOUNTCODE_USER_NOTEXIST = "30003";

	/** 手机号码已存在 */
	public static final String SENDAMOUNTCODE_MOBILE_ISEXIST = "30004";

	/** 手机号码不能为空 */
	public static final String SENDAMOUNTCODE_MOBILE_ISNULL = "30051";

	/** 手机号码格式错误 */
	public static final String SENDAMOUNTCODE_MOBILE_FAIL = "30052";

	/* 注册 */

	/** 注册服务器处理异常 */
	public static final String REGISTER_EXCEPTION = "30100";

	/** 用户名或者手机号码已存在 */
	public static final String REGISTER_USER_EXIST = "30101";

	/** 身份证号码已存在 */
	public static final String REGISTER_IDCARD_EXIST = "30102";

	/** 创建用户异常 */
	public static final String REGISTER_USER_EXCEPTION = "30103";

	/** 用户授权异常 */
	public static final String REGISTER_AUTH_EXCEPTION = "30104";

	/** 用户信息创建异常 */
	public static final String REGISTER_CUST_EXCEPTION = "30105";

	/** 账户信息创建异常 */
	public static final String REGISTER_ACCOUNT_EXCEPTION = "30106";

	/** 用户名不存在 */
	public static final String REGISTER_NOT_EXIST = "30107";

	/** 用户查询异常 */
	public static final String REGISTER_QUERY_EXCEPTION = "30108";

	/** 验证码超时 */
	public static final String REGISTER_IDENTIFY_OVERTIME = "30109";

	/** 验证码错误 */
	public static final String REGISTER_IDENTIFY_FAIL = "30110";

	/** 请先获取验证码 */
	public static final String REGISTER_GETIDENTIFY_FAIL = "30111";

	/** 请使用接收验证码的手机号码 */
	public static final String REGISTER_MOBILE_FAIL = "30112";

	/** 更新用户页面索引异常 */
	public static final String REGISTER_PAGEINDEX_UPDATEEXCEPTION = "30113";

	/** 未找到邀请码 */
	public static final String REGISTER_INVITECODE_NOTFOUND = "30114";

	/** 未找到门店号 */
	public static final String REGISTER_STORECODE_NOTFOUND = "30115";

	/** 无效门店号 */
	public static final String REGISTER_STORECODE_INVALID = "30116";

	/** 店名称已存在 */
	public static final String REGISTER_STORENAME_EXIST = "30117";

	/** 客户类型不能为空 */
	public static final String REGISTER_CUSTTYPE_ISNULL = "30151";

	/** 客户类型格式错误 */
	public static final String REGISTER_CUSTTYPE_ILLEGAL = "30152";

	/** 城市不能为空 */
	public static final String REGISTER_LIVECITY_ISNULL = "30153";

	/** 城市格式错误 */
	public static final String REGISTER_LIVECITY_ILLEGAL = "30154";

	/** 手机号码不能为空 */
	public static final String REGISTER_MOBILE_ISNULL = "30155";

	/** 手机号码格式错误 */
	public static final String REGISTER_MOBILE_ILLEGAL = "30156";

	/** 验证码不能为空 */
	public static final String REGISTER_IDENTIFYCODE_ISNULL = "30157";

	/** 密码不能为空 */
	public static final String REGISTER_PASSWORD_ISNULL = "30158";

	/** 密码格式错误 */
	public static final String REGISTER_PASSWORD_ILLEGAL = "30159";

	/** 真实姓名不能为空 */
	public static final String REGISTER_REALNAME_ISNULL = "30160";

	/** 真实姓名格式错误 */
	public static final String REGISTER_REALNAME_ILLEGAL = "30161";

	/** 身份证号码不能为空 */
	public static final String REGISTER_IDCARD_ISNULL = "30162";

	/** 身份证号码格式错误 */
	public static final String REGISTER_IDCARD_ILLEGAL = "30163";

	/** 用户名不能为空 */
	public static final String USER_ERROR_ISNULL = "30164";

	/** 用户名必须6-20位数字、字母或下划线组合且不包含非法字符 */
	public static final String USER_NAME_ERROR_CANTCHAR = "30165";

	/** 邀请码不能为空 */
	public static final String REGISTER_INVITECODE_ISNULL = "30166";

	/** 邀请码格式错误 */
	public static final String REGISTER_INVITECODE_ILLEGAL = "30167";

	/** 门店号不能为空 */
	public static final String REGISTER_STORECODE_ISNULL = "30168";

	/** 门店号格式错误 */
	public static final String REGISTER_STORECODE_ILLEGAL = "30169";

	/** 图片url为空 */
	public static final String REGISTER_PICURL_ISNULL = "30170";

	/* 用户登录 */

	/** 用户登录异常 */
	public static final String LOGIN_EXCEPTION = "30200";

	/** 用户名不存在 */
	public static final String LOGIN_NO_EXIST = "30201";

	/** 密码输入有误 */
	public static final String LOGIN_PASSWORD_FAIL = "30202";

	/** 由于密码错误次数较多，为了您的账户安全，账户已被锁定，24小时后可正常登录 */
	public static final String LOGIN_USER_LOCKED = "30203";

	/** 用户名或者密码错误 */
	public static final String LOGIN_USERORPSW_FAIL = "30204";

	/** 用户名不能为空 */
	public static final String LOGIN_USERNAME_IS_NULL = "30252";

	/** 用户名格式错误 */
	public static final String LOGIN_USERNAME_ILLEGAL = "30253";

	/** 密码不能为空 */
	public static final String LOGIN_PASSWORD_IS_NULL = "30254";

	/** 密码格式错误 */
	public static final String LOGIN_PASSWORD_ILLEGAL = "30255";

	/** 请选择城市 */
	public static final String LOGIN_CITYCODE_IS_NULL = "30256";

	/** 城市代码格式错误 */
	public static final String LOGIN_CITYCODE_ILLEGAL = "30257";

	/* hicashAPP商品展示 */

	/** 获取展示商品异常 */
	public static final String PROSHOWINFO_EXCEPTION = "30300";

	/** 城市代码不能为空 */
	public static final String PROSHOWINFO_CITYCODE_ISNULL = "30351";

	/** 城市代码非法 */
	public static final String PROSHOWINFO_CITYCODE_ILLEGAL = "30352";

	/** 商品类型不能为空 */
	public static final String PROSHOWINFO_CHANNELID_ISNULL = "30353";

	/** 商品类型非法 */
	public static final String PROSHOWINFO_CHANNELID_ILLEGAL = "30354";

	/* 商品详情 */

	/** 获取详情异常 */
	public static final String PROSHOWDETAIL_EXCEPTION = "40400";

	/** 商品ID不能为空 */
	public static final String PROSHOWDETAIL_PROID_ISNULL = "40451";

	/** 商品ID格式错误 */
	public static final String PROSHOWDETAIL_PROID_ILLEGAL = "40452";

	/* 获取频道分类 */

	/** 获取频道异常 */
	public static final String CHANNELINFO_EXCEPTION = "30500";

	/* 获取同款商品 */

	/** 获取同款商品异常 */
	public static final String SAMEPRODUCT_EXCEPTION = "30600";

	/** 请选择城市 */
	public static final String SAMEPRODUCT_NOCITY = "30651";

	/** 城市代码格式错误 */
	public static final String SAMEPRODUCT_CITY_ILLEGAL = "30652";

	/** 商品款型不能为空 */
	public static final String SAMEPRODUCT_PROCLASS_ISNULL = "30653";

	/* 申请件进度查询 */

	/** 申请进度获取异常 */
	public static final String APPSCHEDULE_EXCEPTION = "30800";

	/* 获取城市 */

	/** 获取定位城市异常 */
	public static final String QUERYCITY_EXCEPTION = "30900";

	/** 未找到该城市 */
	public static final String QUERYCITY_CITY_NOTFOUND = "30901";

	/* 创建申请件 */

	/** 创建申请件异常 */
	public static final String CREATEAPPPAY_EXCEPTOIN = "31000";

	/** 申请额度不能小于1000 */
	public static final String CREATEAPPPAY_MINAMOUNT = "31001";

	/** 可用额度不足 */
	public static final String CREATEAPPPAY_USEAMOUNT_BZ = "31002";

	/** 未找到商品 */
	public static final String CREATEAPP_MERPRO_NOTFOUND = "31003";

	/** 没有申请权限 */
	public static final String CREATEAPP_CUSTROLE_FAIL = "31004";

	/** 流水号生成失败 */
	public static final String CREATEAPP_GETAPPNO_FAILD = "31005";

	/** 创建申请件对象失败,还有一笔，不能重复申请 */
	public static final String CREATEAPP_REPEAT_FAILD = "31006";

	/** 调用账务服务金额计算失败 */
	public static final String CREATEAPP_GETACCT_FAILD = "31007";

	/** 未找到售点 */
	public static final String CREATEAPP_SITE_NOTFOUND = "31008";

	/** 申请实物产品未查询到 */
	public static final String CREATEAPP_PROINFO_NOTFOUND = "31009";

	/** 创建申请件对象失败 */
	public static final String CREATEAPP_CREAT_FAILD = "31010";

	/** 更新申请件节点失败 */
	public static final String CREATEAPP_UPDATENODE_FAILD = "31011";

	/** 暂不做白领业务 */
	public static final String CREATEAPP_CALLRA_CANCEL = "31012";

	/** 命中规则 */
	public static final String CREATEAPP_RULE_IN = "31013";

	/** 申请SQL处理失败 */
	public static final String CREATEAPP_SQL_ERROR = "31014";

	/** 当天流水序号查询失败 */
	public static final String CREATEAPP_QUERY_APPNO_FAILD = "31015";

	/** 申请客户信息未查询到 */
	public static final String CREATEAPP_APPLYINFO_NOT_FOUND = "31016";

	/** 业务员信息未查询到 */
	public static final String CREATEAPP_APPROVEUSER_NOT_FOUND = "31017";

	/** 学校信息未找到 */
	public static final String CREATEAPP_SCHOOLINFO_NOT_FOUND = "31018";

	/** 销售人员和学校对应关系未查询到 */
	public static final String CREATEAPP_LINKINFO_NOT_FOUND = "31019";

	/** 接收账务服务返回处理异常 */
	public static final String CREATEAPP_IO_EXCEPTION = "31020";

	/** 账务服务连接异常 */
	public static final String CREATEAPP_ACCOUNT_ERROR = "31021";

	/** 创建申请件信息对象异常 */
	public static final String CREATEAPP_INPUT_ERROR = "31022";

	/** 修改客户信息毕业时间错误 */
	public static final String CREATEAPP_ENDSCHOOLTIME_ERROR = "31023";

	/** 查询卡号信息错误 */
	public static final String CREATEAPP_CARD_NOTFOUND = "31024";

	/** 未找到用户临时申请单 */
	public static final String CREATEAPP_TEMPAPPLY_NOTFOUND = "31025";

	/** 更新临时申请异常 */
	public static final String CREATEAPP_UPDATETEMP_EXCEPTION = "31026";

	/** 更新用户收款账户异常 */
	public static final String CREATEAPP_UPDATECARD_EXCEPTION = "31027";

	/** 更新上传资料异常 */
	public static final String CREATEAPP_UPDATEPIC_EXCEPTION = "31028";

	/** 重复申请，请重新登录申请 */
	public static final String CREATEAPP_REPEATSUBMIT_FAIL = "31029";

	/** 您已有审核中的申请，不能再次申请，谢谢！ */
	public static final String CREATEAPP_REPEAMD_FAIL = "31030";

	/** 短信发送失败 */
	public static final String CREATEAPPPAY_SMSMOBILE_FAIL = "31031";

	/** 商品ID不能为空 */
	public static final String CREATEAPPPAY_MERPROID_ISNULL = "31051";

	/** 商品ID格式错误 */
	public static final String CREATEAPPPAY_MERPROID_ILLEGAL = "31052";

	/** 商品名称不能为空 */
	public static final String CREATEAPPPAY_PRODUCTNAME_ISNULL = "31053";

	/** 商品价格不能为空 */
	public static final String CREATEAPPPAY_PRICE_ISNULL = "31054";

	/** 商品价格格式错误 */
	public static final String CREATEAPPPAY_PRICE_ILLEGAL = "31055";

	/** 商户ID不能为空 */
	public static final String CREATEAPPPAY_SUPPLIERID_ISNULL = "31056";

	/** 商户ID格式错误 */
	public static final String CREATEAPPPAY_SUPPLIERID_ILLEGAL = "31057";

	/** 售点ID不能为空 */
	public static final String CREATEAPPPAY_SITEID_ISNULL = "31058";

	/** 售点ID格式错误 */
	public static final String CREATEAPPPAY_SITEID_ILLEGAL = "31059";

	/** 请选择期数 */
	public static final String CREATEAPPPAY_LOANPRODUCT_ISNULL = "31060";

	/** 期数格式错误 */
	public static final String CREATEAPPPAY_LOANPRODUCT_ILLEGAL = "31061";

	/** 请选择首付比率 */
	public static final String CREATEAPPPAY_FIRSTRATE_ISNULL = "31062";

	/** 首付比率格式错误 */
	public static final String CREATEAPPPAY_FIRSTRATE_ILLEGAL = "31063";

	/** 申请类型不能为空 */
	public static final String CREATEAPPPAY_APPLYTYPE_ISNULL = "31064";

	/** 申请类型格式错误 */
	public static final String CREATEAPPPAY_APPLYTYPE_ILLEGAL = "31065";

	/** 客户类型不能为空 */
	public static final String CREATEAPPPAY_CUSTTYPE_ISNULL = "31066";

	/** 客户类型格式错误 */
	public static final String CREATEAPPPAY_CUSTTYPE_ILLEGAL = "31067";

	/** 持卡人姓名不能为空 */
	public static final String CREATEAPPPAY_BANKREALNAME_ISNULL = "31068";

	/** 持卡人姓名格式错误 */
	public static final String CREATEAPPPAY_BANKREALNAME_ILLEGAL = "31069";

	/** 请选择开户银行 */
	public static final String CREATEAPPPAY_OPENBAN_ISNULL = "31070";

	/** 开户银行格式错误 */
	public static final String CREATEAPPPAY_OPENBAN_ILLEGAL = "31071";

	/** 银行卡号不能为空 */
	public static final String CREATEAPPPAY_BANKCARD_ISNULL = "31072";

	/** 银行卡号格式错误 */
	public static final String CREATEAPPPAY_BANKCARD_ILLEGAL = "31073";

	/** 请勾选银行卡是否同步到个人账户 */
	public static final String CREATEAPPPAY_BANKSYN_ISNULL = "31074";

	/** 银行卡同步标志格式错误 */
	public static final String CREATEAPPPAY_BANKSYN_ILLEGAL = "31075";

	/** 临时申请件单号不能为空 */
	public static final String CREATEAPPPAY_TEMPAPPNO_ISNULL = "31076";

	/** 临时申请件单号格式错误 */
	public static final String CREATEAPPPAY_TEMPAPPNO_ILLEGAL = "31077";

	/** 返点比率不能为空 */
	public static final String CREATEAPPPAY_REBATE_ISNULL = "31078";

	/** 返点比率格式错误 */
	public static final String CREATEAPPPAY_REBATE_ILLEGAL = "31079";

	/** 一个月内已被拒绝一次，不能再次申请 */
	public static final String CREATEAPP_CANTNOT_APPLY = "31080";

	/** 更新客户信息异常 */
	public static final String CREATEAPP_UPDATECUST_EXCEPTION = "31081";

	/** 蓝领嗨秒贷业务需在注册一个月之内申请 */
	public static final String CREATEAPP_APPLY_OUTONEMONTH = "31082";

	/** 申请件记录自动规则异常 */
	public static final String CREATEAPP_SAVEAUTORULE_FAIL = "31083";

	/** 请选择完整的现居地址区域 */
	public static final String PROVINCE_CITY_AREA_ISNULL = "31262";

	/** 现居详细地址不能为空 */
	public static final String XJDZ_ADDRESS_ISNULL = "31263";

	/** 来源不能为空 */
	public static final String APPLY_FROM_ISNULL = "37518";

	/** 来源格式不正确 */
	public static final String APPLY_FROM_ILLEGAL = "37519";

	/** 开户省份不能为空 */
	public static final String CREATEAPP_BANK_PRO_NOTNULL = "37520";

	/** 开户城市不能为空 */
	public static final String CREATEAPP_BANK_CITY_NOTNULL = "37521";

	/* 上传图片 */

	/** 上传图片异常 */
	public static final String UPLOADPIC_EXCEPTION = "31100";

	/** 用户名不存在 */
	public static final String UPLOADPIC_USER_NOTFOUND = "31101";

	/** 未找到用户申请记录 */
	public static final String UPLOADPIC_USERAPPLY_NOTFOUND = "31102";

	/** Request不能为空 */
	public static final String UPLOADPIC_REQUEST_ISNULL = "31103";

	/** 上传失败，未接收到图片 */
	public static final String UPLOADPIC_APPPIC_NOTFOUND = "31104";

	/** 图片上传失败 */
	public static final String UPLOADPIC_APPPIC_FAIL = "31105";

	/* 修改密码 */

	/** 服务处理异常 */
	public static final String CHANGEPSW_EXCEPTION = "31400";

	/** 用户名不存在 */
	public static final String CHANGEPSW_USERNAME_NOTFOUND = "31402";

	/** 原始密码错误 */
	public static final String CHANGEPSW_ORDPSW_FAIL = "31403";

	/** 修改密码失败 */
	public static final String CHANGEPSW_PASSWORD_FAIL = "31404";

	/** 原始密码不能为空 */
	public static final String CHANGEPSW_OLDPASSWORD_ISNULL = "31451";

	/** 密码与确认密码不一致 */
	public static final String CHANGEPSW_NOT_EQUAL = "31452";

	/** 密码格式错误 */
	public static final String CHANGEPSW_NEWPSWFORMAT_FAIL = "31453";

	/** 新密码和原始密码不能相同 */
	public static final String CHANGEPSW_OLDPASSWORD_EQUAL = "31454";

	/** 用户名不能为空 */
	public static final String CHANGEPSW_USERNAME_ISNULLNULL = "31455";

	/** 新密码不能为空 */
	public static final String CHANGEPSW_NEWPSW_ISNULLNULL = "31456";

	/** 新密码格式错误 */
	public static final String CHANGEPSW_NEWPSW_ILLEGAL = "31463";

	/* 获取首页图片，热卖商品 */

	/** 首页加载异常 */
	public static final String HOMEPAGE_EXCEPTION = "32500";

	/** 请选择城市 */
	public static final String HOMEPAGE_CITY_ISNULL = "32551";

	/** 城市代码格式非法 */
	public static final String HOMEPAGE_CITY_ILLEGAL = "32552";

	/* 验证用户服务密码 */

	/** 服务密码验证异常 */
	public static final String SERVICEPSWVAL_EXCEPTION = "32600";

	/** 用户名或者申请件单号不存在 */
	public static final String SERVICEPSWVAL_APPUSER_NOTFOUND = "32601";

	/** 获取授权失败 */
	public static final String SERVICEPSWVAL_AUTHORIZE_FAIL = "32602";

	/** 生成报告失败 */
	public static final String SERVICEPSWVAL_VALIDATE_FAIL = "32603";

	/** 临时申请件单号不存在 */
	public static final String SERVICEPSWVAL_TEMPAPPNO_NOTFOUND = "32604";

	/** 手机服务密码错误 */
	public static final String SERVICEPSWVAL_SERVICEPSW_FAIL = "32605";

	/** 动态密码错误 */
	public static final String SERVICEPSWVAL_DTPSW_FAIL = "32606";

	/** 动态密码失效系统已自动重新下发 */
	public static final String SERVICEPSWVAL_DTPSW_FAIL2 = "32607";

	/** 简单密码或初始密码无法登录 */
	public static final String SERVICEPSWVAL_ESAYPSW_FAIL = "32608";

	/** 动态密码超时 */
	public static final String SERVICEPSWVAL_DTPSW_OUTTIME = "32609";

	/** 服务密码不能为空 */
	public static final String SERVICEPSWVAL_CODE_ISNULL = "32651";

	/** 服务密码格式错误 */
	public static final String SERVICEPSWVAL_CODE_ILLEGAL = "32652";

	/** 申请件单号不能为空 */
	public static final String SERVICEPSWVAL_APPNO_ISNULL = "32653";

	/** 申请件单号格式错误 */
	public static final String SERVICEPSWVAL_APPNO_ILLEGAL = "32654";

	/** 临时件单号不能为空 */
	public static final String SERVICEPSWVAL_TEMPAPPNO_ISNULL = "32655";

	/** 临时件单号格式错误 */
	public static final String SERVICEPSWVAL_TEMPAPPNO_ILLEGAL = "32656";

	/** 动态密码不能为空 */
	public static final String SERVICEPSWVAL_DTPSW_ISNULL = "32657";

	/** 授权标志不能为空 */
	public static final String SERVICEPSWVAL_TOKEN_ISNULL = "32658";

	/** 运营商代码不能为空 */
	public static final String SERVICEPSWVAL_WEBSITE_ISNULL = "32659";

	/* 用户忘记密码 */

	/** 验证码发送异常 */
	public static final String SENDPSWCODE_EXCEPTION = "32700";

	/** 验证码发送失败 */
	public static final String SENDPSWCODE_SEND_FAIL = "32701";

	/** 验证码保存失败 */
	public static final String SENDPSWCODE_SAVE_FAIL = "32702";

	/** 手机号码不存在，请使用绑定手机号 */
	public static final String SENDPSWCODE_MOBILE_NOTFOUND = "32703";

	/** 验证信息异常 */
	public static final String SENDPSWCODE_VALIDATE_EXCEPTION = "32704";

	/** 请使用接收验证码的手机号码 */
	public static final String SENDPSWCODE_INPUTMOBILE_FAIL = "32705";

	/** 验证码错误 */
	public static final String SENDPSWCODE_VALIDATECODE_FAIL = "32706";

	/** 验证码超时 */
	public static final String SENDPSWCODE_VALIDATECODE_OUTTIME = "32707";

	/** 修改密码异常 */
	public static final String RESETPSW_EXCEPTION = "32708";

	/** 手机号码不能为空 */
	public static final String SENDPSWCODE_MOBILE_ISNULL = "32751";

	/** 手机号码格式错误 */
	public static final String SENDPSWCODE_MOBILE_ILLEGAL = "32752";

	/** 手机验证码不能为空 */
	public static final String VALIDATEPSWCODE_VALIDATECODE_ISNULL = "32753";

	/** 密码不能为空 */
	public static final String RESETPSW_PSW_ISNULL = "32754";

	/** 密码格式错误 */
	public static final String RESETPSW_PSW_ILLEGAL = "32755";

	/* 学生-保存客户学校信息 */

	/** 学校资料保存异常 */
	public static final String STU_SHCOOL_INFO_EXCEPTION = "40600";

	/** 保存学校信息异常 */
	public static final String STU_UNIVERSITY_INFO_EXCEPTION = "30602";

	/** 保存客户学校信息（单表）异常 */
	public static final String STU_UNIVERSITY_EXCEPTION = "30603";

	/** 更新客户学校关系异常 */
	public static final String STU_UPDATE_SCHOOL_EXCEPTION = "30604";

	/** 更新用户页面索引异常 */
	public static final String STU_UPDATE_PAGE_INDEX_EXCEPTION = "30605";

	/** 申请件流水号不存在 */
	public static final String STU_UPDATE_APPNO_ISNOTEXIST = "30606";

	/** 修改学生类型异常 */
	public static final String STU_UPDATE_STUTYPE_EXCEPTION = "30607";

	/** 配偶手机号码不能与当前用户手机号码相同 */
	public static final String STU_UPDATE_MOBILE_ILLEGAL = "30608";

	/** 学校ID不能为空 */
	public static final String STU_UPDATE_SCHOOL_NAME_NOTNULL = "40651";

	/** 学制不能为空 */
	public static final String STU_UPDATE_SCHOOL_SYSTEM_NOTNULL = "40652";

	/** 学历不能为空 */
	public static final String STU_UPDATE_SCHOOL_EDUCATION_NOTNULL = "40653";

	/** 入学时间不能为空 */
	public static final String STU_UPDATE_SCHOOL_STUDYTIME_NOTNULL = "40654";

	/** 年级不能为空 */
	public static final String STU_UPDATE_SCHOOL_GRADE_NOTNULL = "30655";

	/** 学号不能为空 */
	public static final String STU_UPDATE_SCHOOL_STUID_NOTNULL = "30656";

	/** 所在院系不能为空 */
	public static final String STU_UPDATE_SCHOOL_DPM_NOTNULL = "30657";

	/** 专业不能为空 */
	public static final String STU_UPDATE_SCHOOL_MAJOR_NOTNULL = "30658";

	/** 学号输入非法(只能是数字或者字母) */
	public static final String STU_UPDATE_SCHOOL_STUID_FALSE = "30659";

	/** 所在院系输入非法(只能输入中文) */
	public static final String STU_UPDATE_SCHOOL_DPM_FALSE = "30660";

	/** 专业输入非法(只能输入中文) */
	public static final String STU_UPDATE_SCHOOL_MAJOR_FALSE = "30661";

	/** 学校ID输入非法(只能输入数字) */
	public static final String STU_UPDATE_SCHOOL_NAME_FALSE = "30662";

	/** 学校ID长度不符合 */
	public static final String STU_UPDATE_SCHOOL_NAME_LENGTH = "30663";

	/** 学号长度不正确 */
	public static final String STU_UPDATE_SCHOOL_STUID_LENGTH = "30664";

	/** 专业长度不正确 */
	public static final String STU_UPDATE_SCHOOL_MAJOR_LENGTH = "30665";

	/** 所在院系长度为1-20 */
	public static final String STU_UPDATE_SCHOOL_DPM_LENGTH = "30666";

	/** 学制非法(1-5字母数字) */
	public static final String STU_UPDATE_SCHOOL_SYSTEM_LENGTH = "30667";

	/** 学历非法(1-5字母数字) */
	public static final String STU_UPDATE_SCHOOL_EDUCATION_LENGTH = "30668";

	/** 年级非法(1-5字母数字) */
	public static final String STU_UPDATE_SCHOOL_GRADE_LENGTH = "30669";

	/** 入学时间年非法(只能为四位数字) */
	public static final String STU_UPDATE_SCHOOL_DATE_YEAR_ILLEGAL = "30670";

	/** 入学时间月非法(只能为二位数字) */
	public static final String STU_UPDATE_SCHOOL_DATE_MONTH_ILLEGAL = "30671";

	/** 学生类型不能为空 */
	public static final String STU_UPDATE_STUTYPE_NOTNULL = "30672";

	/** 学生类型非法 */
	public static final String STU_UPDATE_STUTYPE_FAIL = "30673";

	/** 修改类型不能为空 */
	public static final String STU_UPDATE_TYPE_NOTNULL = "30674";

	/** 修改类型非法 */
	public static final String STU_UPDATE_TYPE_FAIL = "30675";

	/** 申请件流水号不能为空 */
	public static final String STU_UPDATE_APPNO_NOTNULL = "30676";

	/** 申请件流水号非法 */
	public static final String STU_UPDATE_APPNO_FAIL = "30677";

	/* 学生-保存客户联系信息 */

	/** 保存客户联系信息异常 */
	public static final String CONTACT_EXCEPTION = "40700";

	/** 更新客户联系信息失败 */
	public static final String UPDATE_CONTACT_EXCEPTION = "30702";

	/** 新增客户联系信息失败 */
	public static final String SAVE_CONTACT_EXCEPTION = "30703";

	/** 更用户页面索引异常 */
	public static final String UPDATE_PAGE_INDEX_EXCEPTION = "30704";

	/** 请选择完整的学校地址区域 */
	public static final String STU_CONTACT_SCHOOL_AREA_NOTNULL = "40751";

	/** 学校详细地址不能为空 */
	public static final String STU_CONTACT_SCHOOL_AREA_DETAIL_NOTNULL = "40752";

	/** 请选择完整的家庭地址区域 */
	public static final String STU_CONTACT_HOME_AREA_NOTNULL = "40753";

	/** 家庭详细地址不能为空 */
	public static final String STU_CONTACT_HOME_AREA_DETAIL_NOTNULL = "40754";

	/** 请选择完整的现居住地址区域 */
	public static final String STU_CONTACT_NOW_AREA_NOTNULL = "40755";

	/** 现居住详细地址不能为空 */
	public static final String STU_CONTACT_NOW_AREA_DETAIL_NOTNULL = "40756";

	/** 学校详细地址输入非法(不能包含特殊字符) */
	public static final String STU_CONTACT_SCHOOL_AREA_DETAIL_FALSE = "40757";

	/** 家庭详细地址输入非法(不能包含特殊字符) */
	public static final String STU_CONTACT_HOME_AREA_DETAIL_FALSE = "40758";

	/** 现居住详细地址输入非法(不能包含特殊字符) */
	public static final String STU_CONTACT_NOW_AREA_DETAIL_FALSE = "40759";

	/** 学校详细地址长度不正确 */
	public static final String STU_CONTACT_SCHOOL_AREA_DETAIL_LENGTH = "30760";

	/** 家庭详细地址长度不正确 */
	public static final String STU_CONTACT_HOME_AREA_DETAIL_LENGTH = "30761";

	/** 现居住详细地址长度不正确 */
	public static final String STU_CONTACT_NOW_AREA_DETAIL_LENGTH = "30762";

	/** 学校地址(省)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_SCHOOL_PROVINCE_FALSE = "30763";

	/** 学校地址(市)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_SCHOOL_CITY_FALSE = "30764";

	/** 学校地址(区)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_SCHOOL_AREA_FALSE = "30765";

	/** 家庭地址(省)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_HOME_PROVINCE_FALSE = "30766";

	/** 家庭地址(市)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_HOME_CITY_FALSE = "30767";

	/** 家庭地址(区)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_HOME_AREA_FALSE = "30768";

	/** 现居住地址(省)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_NOW_PROVINCE_FALSE = "30769";

	/** 现居住地址(市)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_NOW_CITY_FALSE = "30770";

	/** 现居住地址(区)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_NOW_AREA_FALSE = "30771";

	/** 修改类型不能为空 */
	public static final String USER_COLLACT_TYPE_NOTNULL = "30772";

	/** 修改类型错误 */
	public static final String USER_COLLACT_TYPE_FALSE = "30773";

	/** 申请件不能为空 */
	public static final String USER_COLLACT_APPNO_NOTNULL = "30774";

	/** 申请件输入非法 */
	public static final String USER_COLLACT_APPNO_FALSE = "30775";

	/* 学生-保存客户联系人信息 */

	/** 保存客户联系人信息异常 */
	public static final String CONTACTS_EXCEPTION = "40800";

	/** 更新客户联系人信息失败 */
	public static final String UPDATE_CONTACTS_EXCEPTION = "40802";

	/** 新增客户联系人信息失败 */
	public static final String SAVE_CONTACTS_EXCEPTION = "40803";

	/** 直系亲属姓名不能为空 */
	public static final String STU_CONTACTS_FAMILY_NAME_NOTNULL = "40851";

	/** 直系亲属关系不能为空 */
	public static final String STU_CONTACTS_FAMILY_RELATION_NOTNULL = "40852";

	/** 直系亲属工作单位不能为空 */
	public static final String STU_CONTACTS_FAMILY_UNIT_NOTNULL = "30853";

	/** 直系亲属手机不能为空 */
	public static final String STU_CONTACTS_FAMILY_PHONE_NOTNULL = "30854";

	/** 直系亲属地址不能为空 */
	public static final String STU_CONTACTS_FAMILY_ADDRESS_NOTNULL = "30855";

	/** 紧急联系人姓名不能为空 */
	public static final String STU_CONTACTS_CONTACT_NAME_NOTNULL = "30856";

	/** 紧急联系人关系不能为空 */
	public static final String STU_CONTACTS_CONTACT_RELATION_NOTNULL = "30857";

	/** 紧急联系人工作单位不能为空 */
	public static final String STU_CONTACTS_CONTACT_UNIT_NOTNULL = "30858";

	/** 紧急联系人手机不能为空 */
	public static final String STU_CONTACTS_CONTACT_PHONE_NOTNULL = "30859";

	/** 紧急联系人地址不能为空 */
	public static final String STU_CONTACTS_CONTACT_ADDRESS_NOTNULL = "30860";

	/** 直系亲属姓名输入非法(只能输入中文) */
	public static final String STU_CONTACTS_FAMILY_NAME_FALSE = "30861";

	/** 直系亲属工作单位输入非法字符 */
	public static final String STU_CONTACTS_FAMILY_UNIT_FALSE = "30862";

	/** 直系亲属手机输入非法 */
	public static final String STU_CONTACTS_FAMILY_PHONE_FALSE = "30863";

	/** 直系亲属地址非法字符 */
	public static final String STU_CONTACTS_FAMILY_ADDRESS_FALSE = "30864";

	/** 紧急联系人姓名非法字符(只能为中文) */
	public static final String STU_CONTACTS_CONTACT_NAME_FALSE = "30865";

	/** 紧急联系人工作单位输入非法字符 */
	public static final String STU_CONTACTS_CONTACT_UNIT_FALSE = "30866";

	/** 紧急联系人手机输入非法 */
	public static final String STU_CONTACTS_CONTACT_PHONE_FALSE = "30867";

	/** 紧急联系人地址输入非法 */
	public static final String STU_CONTACTS_CONTACT_ADDRESS_FALSE = "30868";

	/** 紧急联系人(姓名)输入长度错误 */
	public static final String STU_CONTACTS_CONTACT_NAME_LENGTH = "30869";

	/** 直系联系人(姓名)输入长度错误 */
	public static final String STU_CONTACTS_FAMILY_NAME_LENGTH = "30872";

	/** 直系亲属工作单位长度不对 */
	public static final String STU_CONTACTS_FAMILY_UNIT_LENGTH = "30873";

	/** 紧急联系人(工作单位)长度不对 */
	public static final String STU_CONTACTS_CONTACTS_UNIT_LENGTH = "30874";

	/** 直系亲属地址非法长度 */
	public static final String STU_CONTACTS_FAMILY_ADDRESS_LENGTH = "30875";

	/** 紧急联系人地址非法长度 */
	public static final String STU_CONTACTS_CONTACT_ADDRESS_LENGTH = "30876";

	/** 紧急联系人姓名不能与直系亲属姓名相同 */
	public static final String STU_CONTACTS_NAME_EQ_FALSE = "30877";

	/** 紧急联系人关系不能与直系亲属关系相同 */
	public static final String STU_CONTACTS_RELATION_EQ_FALSE = "30878";

	/** 紧急联系人手机不能与直系亲属手机相同 */
	public static final String STU_CONTACTS_PHONE_EQ_FALSE = "30879";

	/** 直系亲属关系非法(1-5字母数字) */
	public static final String STU_CONTACTS_FAMILY_RELATION_FALSE = "30880";

	/** 紧急联系人关系非法(1-5字母数字) */
	public static final String STU_CONTACTS_RELATION_FALSE = "30881";

	/** 修改类型不能为空 */
	public static final String USER_COLLACTS_TYPE_NOTNULL = "30882";

	/** 修改类型错误 */
	public static final String USER_COLLACTS_TYPE_FALSE = "30883";

	/** 申请件不能为空 */
	public static final String USER_COLLACTS_APPNO_NOTNULL = "30884";

	/** 申请件输入非法 */
	public static final String USER_COLLACTS_APPNO_FALSE = "30885";

	/* 银行卡 */

	/** 银行卡信息处理异常 */
	public static final String CARD_EXCEPTION = "31500";

	/** 银行卡信息更新失败 */
	public static final String CARD_UPDATE_EXCEPTION = "31502";

	/** 银行卡信息保存失败 */
	public static final String CARD_SAVE_EXCEPTION = "31503";

	/** 取消用户默认银行卡失败 */
	public static final String CARD_DEFAULT_CARD_EXCEPTION = "31504";

	/** 用户不存在 */
	public static final String CARD_SAVE_USEREXIST = "31505";

	/** 未查到该银行卡的验证记录 */
	public static final String BANK_CARD_NOT_VERIFIED = "31506";

	/** 银行卡账户类型-开户行为空 */
	public static final String CARD_ISNULL = "31552";

	/** 开户行省不能为空 */
	public static final String CARD_PROVINCE_ISNULL = "31553";

	/** 开户行市不能为空 */
	public static final String CARD_CITY_ISNULL = "31554";

	/** 开户行支行不能为空 */
	public static final String CARD_ADDRESS_ISNULL = "31555";

	/** 银行卡号为空 */
	public static final String CARD_NUM_ISNULL = "31556";

	/** 银行卡号格式不对 */
	public static final String CARD_NUM_ERROR = "31557";

	/** 银行卡号长度不对 */
	public static final String CARD_NUM_LEN_ERROR = "31558";

	/** 确认账号输入不正确 */
	public static final String CARD_NUM_COM_ERROR = "31559";

	/** 新增或修改标志输入非法 */
	public static final String ADD_CHANGE_ERROR = "31562";

	/** 确认银行卡号为空 */
	public static final String COFIRM_CARD_NUM_ISNULL = "31563";

	/** 确认银行卡号格式不对 */
	public static final String COFIRM_CARD_NUM_ERROR = "31564";

	/** 确认银行卡号长度不对 */
	public static final String COFIRM_CARD_NUM_LEN_ERROR = "31565";

	/** 银行卡真实姓名不能为空 */
	public static final String CARD_REALNAME_NOTNLULL = "31566";

	/** 银行卡真实姓名输入非法(只能为1-20位中文) */
	public static final String CARD_REALNAME_FALSE = "31567";

	/** 开户行支行长度不正确 */
	public static final String CARD_BANCK_LENGTH = "31568";

	/** 开户行支行输入包含非法字符 */
	public static final String CARD_BANCK_FALSE = "31569";

	/** 开户行省非法字符(只能为6位数字代码) */
	public static final String CARD_PROVINCE_VAL_FALSE = "31570";

	/** 开户行市非法字符(只能为6位数字代码) */
	public static final String CARD_CITY_VAL_FALSE = "31571";

	/** 银行卡账户类型-开户行非法(1-5位字母数字) */
	public static final String CARD_BANK_TYPE = "31572";

	/** 默认银行卡标志不能为空 */
	public static final String CARD_DEFAULT_NOTNULL = "31573";

	/** 默认银行卡标志非法(必须为Y/N) */
	public static final String CARD_DEFAULT_FALSE = "31574";

	/** 卡类型不能为空 */
	public static final String CARD_TYPE_NOTNULL = "31575";

	/** 卡类型输入不正确(暂只支持借记卡-JJKT) */
	public static final String CARD_TYPE_FALSE = "31576";

	/** 开户行区不能为空 */
	public static final String CARD_AREA_ISNULL = "31577";

	/** 开户行区非法 */
	public static final String CARD_AREA_ILLEGAL = "31578";

	/** 开户行不存在或者支行不匹配 */
	public static final String CARD_BANK_ILLEGAL = "31579";

	/* 银行卡信息查询 */

	/** 银行卡查询处理异常 */
	public static final String CARD_QUERY_EXCEPTION = "31600";

	/** 银行卡查询用户不存在 */
	public static final String CARD_QUERY_USER_EXIST = "31601";

	/** 未创建过银行卡信息 */
	public static final String CARD_NOT_FOUND = "31602";

	/* 忘记密码获取手机验证码 */

	/** 获取验证码服务器处理异常 */
	public static final String FORGE_PASSWORD_EXCEPTION = "31700";

	/** 对应手机号码不存在于系统 */
	public static final String FORGET_MOBILE_NOT_FOUND = "31701";

	/** 发送手机验证码失败 */
	public static final String SEND_PASSWORD_CODE_EXCEPTION = "31702";

	/** 记录发送认证信息失败 */
	public static final String SEND_CODE_SAVE_ERORR = "31703";

	/** 验证码不能为空 */
	public static final String FORGETPASSWORd_CODE_NOTNULL = "31751";

	/** 手机号码为空 */
	public static final String FORGETPASSWORd_PHONE_ERROR_ISNULL = "31752";

	/** 输入的手机号码格式错误 */
	public static final String FORGETPASSWORd_PHONE_ERROR = "31753";

	/* 重置密码 */

	/** 重置密码操作异常 */
	public static final String RESET_PASSWORD_EXCEPTION = "31800";

	/** 重置密码短信发送失败，重置密码失败 */
	public static final String RESET_PASSWORD_SEND_EXCEPTION = "31802";

	/** 重置密码失败 */
	public static final String RESET_PASSWORD_UPDATE_EXCEPTION = "31803";

	/** 新密码不能为空 */
	public static final String RESET_NEWPSSWORD_NOTNULL = "31851";

	/** 新密码格式错误 */
	public static final String RESET_NEWPSSWORD_FALSE = "31852";

	/** 确认密码不能为空 */
	public static final String RESET_RESTPASSWORD_ISNULL = "31853";

	/** 确认密码格式错误 */
	public static final String RESET_RESTPASSWORD_FALSE = "31854";

	/** 密码与确认密码不一致 */
	public static final String RESET_PASSWORD_NOT_EQ = "31855";

	/* 修改绑定手机发送短信验证码 */

	/** 修改绑定手机异常 */
	public static final String UPDATE_MOBILE_EXCEPTION = "31900";

	/** 修改绑定手机短信发送失败 */
	public static final String UPDATE_MOBILE_SEND_EXCEPTION = "31902";

	/** 新手机号码不能和原手机号相同 */
	public static final String UPDATE_MOBILENO_ERROR = "31903";

	/** 新手机号码已经存在，不能使用 */
	public static final String UPDATE_NEW_MOBILE_EXIST = "31904";

	/** 记录验证码信息失败 */
	public static final String UPDATE_NEW_MOBILE_EXCEPTION = "31905";

	/** 原始手机号输入错误 */
	public static final String UPDATE_MOBILENO_OLD_FAIL = "31906";

	/* 更换绑定手机 */

	/** 更换手机号码操作异常 */
	public static final String CHANGE_MOBILE_EXCEPTION = "32000";

	/** 对应用户不存在 */
	public static final String CHANGE_MOBILE_USER_NOT_FOUND = "32001";

	/** 验证码错误 */
	public static final String CHANGE_MOBILE_CODE_ERROR = "32002";

	/** 验证码过期 */
	public static final String CHANGE_MOBILE_CODE_TIMEOUT = "32003";

	/** 删除原有认证信息失败 */
	public static final String DELETE_MOBILE_CER_ERROR = "32004";

	/** 更新客户手机号码失败 */
	public static final String UPDATE_USER_MOBILE_ERROR = "32005";

	/** 保存新的手机认证记录失败 */
	public static final String SAVE_MOBILE_CER_ERROR = "32006";

	/** 更新客户手机登录信息失败 */
	public static final String UPDATE_USER_LOGIN_ERROR = "32007";

	/** 原手机号为空 */
	public static final String UPDATE_OLD_MOBILE_ERROR = "32050";

	/** 原手机号格式错误 */
	public static final String UPDATE_OLD_MOBIL_ERROR_EXIST = "32051";

	/** 新手机号为空 */
	public static final String UPDATE_NEW_MOBILE_ERROR = "32052";

	/** 新手机号格式错误 */
	public static final String UPDATE_NEW_MOBIL_ERROR_EXIST = "32053";

	/** 验证码为空 */
	public static final String UPDATE_NEW_MOBIL_CERTIFICATIONCODE_EXIST = "32054";

	/** 验证码存在非法字符 */
	public static final String UPDATE_NEW_MOBIL_CERTIFICATIONCODE_ERROR = "32055";

	/** 验证码长度不正确 */
	public static final String UPDATE_NEW_MOBIL_CERTIFICATIONCODE_LENGTH = "32056";

	/** 手机号码和获取验证码的手机号码不一致 */
	public static final String MOBILE_NEWMOBILE_ERROR = "32057";

	/* 学生个人资料查询 */

	/** 个人详细信息查询异常 */
	public static final String STU_DETAIL_QUERY_EXCEPTION = "32800";

	/** 个人详细信息查询无结果集 */
	public static final String STU_DETAIL_QUERY_NOT_FOUND = "32801";

	/** 客户类型异常,输入的不是学生用户名 */
	public static final String STU_DETAIL_CUSTTYPE_NOT_FOUND = "32850";

	/* 公告查询 */

	/** 公告信息查询异常 */
	public static final String NOTICE_QUERY_EXCEPTION = "32900";

	/** 公告信息查询无结果集 */
	public static final String NOTICE_QUERY_NOT_FOUND = "32901";

	/** 无公告明细信息 */
	public static final String NOTICE_DETAIL_NOT_FOUND = "32902";

	/** id不能为空 */
	public static final String NOTICE_DETAIL_ID_NOTNULL = "32951";

	/** id输入非法(1-5位数字) */
	public static final String NOTICE_DETAIL_ID_FALSE = "32952";

	/* 忘记密码手机验证码提交 */

	/** 服务器处理异常 */
	public static final String FORGET_PASSWORD_NOBILE_EXCEPTION = "33000";

	/** 手机号码错误 */
	public static final String FORGET_PASSWORD_MOBILE_NOT_FOUND = "33002";

	/** 验证码错误 */
	public static final String FORGET_PASSWORD_CODE_ERROR = "33003";

	/** 验证码超时 */
	public static final String FORGET_PASSWORD_CODE_TIMEOUT = "33004";

	/** 验证码非法字符 */
	public static final String FORGET_PASSWORd_CODE_FALSE = "33051";

	/** 验证码长度不正确1-6 */
	public static final String FORGET_PASSWORd_CODE_LENGTH = "33052";

	/* 授信额度* */

	/** 用户授信异常 */
	public static final String SAVE_AMOUNT_EXCEPTION = "33100";

	/** 保存用户授信额度失败 */
	public static final String SAVE_AMOUNT_FAIL = "33101";

	/** 保存用户额度信息 */
	public static final String UPDATE_CREDIT_EXCEPTION = "33102";

	/* 手机号码唯一性验证 */

	/** 手机唯一性验证异常 */
	public static final String MOBILE_ONLY_VALIDATE_EXCEPTION = "33200";

	/** 收机唯一性验证失败 */
	public static final String MOBILE_ONLY_VALIDATE_FAIL = "33201";

	/** 该号码不存在 */
	public static final String MOBILE_ONLY_VALIDATE_EXIST_FALSE = "33202";

	/** 该号码存在 */
	public static final String MOBILE_ONLY_VALIDATE_EXIST_TRUE = "33203";

	/** 手机号码不能为空 */
	public static final String MOBILE_ONLY_VALIDATE_ISNULL = "33251";

	/** 手机号码非法(11位正常手机号格式) */
	public static final String MOBILE_ONLY_VALIDATE_FALSE = "33252";

	/* 删除银行卡 */

	/** 删除银行卡异常 */
	public static final String REMOVE_CARD_ACCOUNT_EXCEPTION = "33500";

	/** 未找到该银行卡 */
	public static final String REMOVE_CARD_ACCOUNT_NOTFOUND = "33501";

	/** 设置默认银行卡失败 */
	public static final String REMOVE_CARD_DEFAULT_FAIL = "33502";

	/** 银行卡主键不能为空 */
	public static final String REMOVE_CARD_ID_NOTNULL = "33551";

	/** 银行卡主键非法 */
	public static final String REMOVE_CARD_ID_FALSE = "33552";

	/* 账户安全 */

	/** 查询账户安全信息异常 */
	public static final String ACCOUNT_SECURITY = "33600";

	/* 查询用户银行卡 */

	/** 银行卡查询异常 */
	public static final String SEARCHBANKCARD_EXCEPTION = "33800";

	/** 银行卡不存在 */
	public static final String SEARCHBANKCARD_CARD_NOEXIST = "33801";

	/** 银行卡主键不能为空 */
	public static final String SEARCHBANKCARD_ID_NOTNULL = "33851";

	/** 银行卡主键输入非法 */
	public static final String SEARCHBANKCARD_ID_ILLEGAL = "33852";

	/* 修改银行卡 */

	/** 修改银行卡系统繁忙 */
	public static final String UPDATEBANKCARD_EXCEPTION = "33900";

	/** 卡号未找到 */
	public static final String UPDATEBANKCARD_CARD_NOEXIST = "33901";

	/** 修改银行卡异常 */
	public static final String UPDATEBANKCARD_UPDATE_EXCEPTION = "33902";

	/* 查询用户是否已经激活账户和获取过授信额度 */

	/** 用户状态查询异常 */
	public static final String USER_ACCOUNTSTATUS_EXCEPTION = "33700";

	/** 用户不存在 */
	public static final String USER_ACCOUNTSTATUS_USER_EXIST = "33701";

	/* 统计提现成功笔数 */
	/** 获取提现成功笔数异常 */
	public static final String EXTRACTCASH_COUNT_EXCEPTION = "39951";

	/** 调整系数格式错误 */
	public static final String EXTRACTCASH_TRIM_ILLEGAL = "39952";

	/* 统计注册人数 */

	/** 注册人数统计异常 */
	public static final String REGISTERCOUNT_EXCEPTION = "39970";

	/** 统计类型不能为空 */
	public static final String REGISTERCOUNT_TYPE_NOTNULL = "39991";

	/** 统计类型输入非法 */
	public static final String REGISTERCOUNT_TYPE_ILLEGAL = "39992";

	/* 二次营销 - 获取首付比例 */

	/** 查询异常 */
	public static final String PAY_RATE_ERROR = "32100";

	/** 城市代码为空 */
	public static final String CITY_CODE_ISNULL = "34451";

	/* 二次营销 - 获取业务开放城市 */

	/** 城市获取异常 */
	public static final String MKTAPP_BUSICITY_EXCEPTION = "34500";

	/** 未查询到结果 */
	public static final String MKTAPP_BUSICITY_NORESULT = "34501";

	/** 客户类型不能为空 */
	public static final String MKTAPP_BUSICITY_CUSTTYPE_ISNULL = "34551";

	/** 客户类型非法 */
	public static final String MKTAPP_BUSICITY_CUSTTYPE_ILLEGAL = "34552";

	/* 短信通知商户销售 */

	public static final String SEND_NOTICE_APP_EXCEPTION = "34600";

	/** 未找到申请件或者售点为空 */
	public static final String SEND_NOTICE_APP_NOTEXIST = "34601";

	/** 该申请件已经通知过业务员 */
	public static final String SEND_NOTICE_APP_SENDED = "34602";

	/** 未找到售点对应的业务员 */
	public static final String SEND_NOTICE_APP_NOTFOUND = "34603";
	/* 立刻秒贷-2015-05-27 LiHua.Ren */
	/** 秒贷金额期数保存失败 */
	public static final String FAST_LOAN_SAVE_FAIL = "35100";
	/** 秒贷金额期数保存异常 */
	public static final String FAST_LOAN_SAVE_EXCEPTION = "35101";
	/** 秒贷金额不能为空 */
	public static final String FAST_LOAN_AMOUNT_NOTNULL = "35102";
	/** 秒贷金额非法字符 */
	public static final String FAST_LOAN_AMOUNT_EXCEPTION = "35103";
	/** 秒贷金额长度不正确 */
	public static final String FAST_LOAN_AMOUNT_LENGTH = "35104";
	/** 秒贷期数非整型 */
	public static final String FAST_LOAN_PERIOD_NOTRIGHT = "35105";
	/** 请选择完整的合同快递地址 */
	public static final String STU_APP_EXPRESS_AREANOTNULL = "35106";
	/** 合同快递地址(省)非法字符(只能为6位数字代码) */
	public static final String STU_APP_EXPRESS_PROVINCE_FALSE = "35107";

	/** 合同快递地址(市)非法字符(只能为6位数字代码) */
	public static final String STU_APP_EXPRESS_CITY_FALSE = "35108";

	/** 合同快递地址(区)非法字符(只能为6位数字代码) */
	public static final String STU_APP_EXPRESSL_AREA_FALSE = "35109";
	/** 合同快递详细地址不能为空 */
	public static final String STU_APP_EXPRESSL_AREA_DETAIL_NOTNULL = "35110";
	/** 合同快递详细地址输入非法(不能包含特殊字符) */
	public static final String STU_APP_EXPRESSL_AREA_DETAIL_FALSE = "35111";
	/** 合同快递详细地址长度不正确 */
	public static final String STU_APP_EXPRESSL_AREA_DETAIL_LENGTH = "35112";

	/** 城市为非法字符(只能位6位数字代码) */
	public static final String STU_APP_AREA_CITY_FALSE = "35113";
	/** 请选择城市 */
	public static final String STU_APP_AREA_CITY_NOTNULL = "35114";
	/** 省为非法字符(只能位6位数字代码) */
	public static final String STU_APP_AREA_PRO_FALSE = "35115";
	/** 请选择省 */
	public static final String STU_APP_AREA_PRO_NOTNULL = "35116";
	/** 请选择正确的合同邮寄地址 */
	public static final String STU_APP_EXPRESSL_ADDRESSTYPE_FALSE = "35117";
	/** 秒贷查询图片无结果集 */
	public static final String STU_APP_QUERYIMG_NOT_FOUND = "35118";
	/** 秒贷查询图片异常 */
	public static final String STU_APP_QUERYIMG_EXPRESION = "35119";
	/* 学生提现申请app参数验证 */
	// 修改类型非法
	public static final String STU_APP_UPDATE_TYPE_FAIL = "35120";
	/** 修改类型不能为空 */
	public static final String STU_APP_UPDATE_TYPE_NOTNULL = "35121";
	/** 申请件流水号非法 */
	public static final String STU_APP_UPDATE_APPNO_FAIL = "35122";
	/** 请选择完整的学校地址区域 */
	public static final String STU_APP_SCHOOL_AREA_NOTNULL = "35123";

	/** 学校地址(省)非法字符(只能位6位数字代码) */
	public static final String STU_APP_SCHOOL_PROVINCE_FALSE = "35124";

	/** 学校地址(市)非法字符(只能位6位数字代码) */
	public static final String STU_APP_SCHOOL_CITY_FALSE = "35125";

	/** 学校地址(区)非法字符(只能位6位数字代码) */
	public static final String STU_APP_SCHOOL_AREA_FALSE = "35126";
	/** 学校ID不能为空 */
	public static final String STU_APP_UPDATE_SCHOOL_NAME_NOTNULL = "35127";
	/** 学校ID输入非法(只能输入数字) */
	public static final String STU_APP_UPDATE_SCHOOL_NAME_FALSE = "35128";
	/** 学校ID长度不符合1-10 */
	public static final String STU_APP_UPDATE_SCHOOL_NAME_LENGTH = "35129";
	/** 学制不能为空 */
	public static final String STU_APP_UPDATE_SCHOOL_SYSTEM_NOTNULL = "35130";
	/** 学制非法(1-5字母数字) */
	public static final String STU_APP_UPDATE_SCHOOL_SYSTEM_LENGTH = "35131";

	/** 学历不能为空 */
	public static final String STU_APP_UPDATE_SCHOOL_EDUCATION_NOTNULL = "35132";
	/** 学历非法(1-5字母数字) */
	public static final String STU_APP_UPDATE_SCHOOL_EDUCATION_LENGTH = "35133";
	/** 所在院系不能为空 */
	public static final String STU_APP_UPDATE_SCHOOL_DPM_NOTNULL = "35134";
	/** 所在院系输入非法(只能输入中文) */
	public static final String STU_APP_UPDATE_SCHOOL_DPM_FALSE = "35135";
	/** 所在院系长度为1-20 */
	public static final String STU_APP_UPDATE_SCHOOL_DPM_LENGTH = "35136";

	/** 请填写完整的入学时间 */
	public static final String STU_APP_UPDATE_SCHOOL_STUDYTIME_NOTNULL = "35137";

	/** 入学时间年非法(只能为四位数字) */
	public static final String STU_APP_UPDATE_SCHOOL_DATE_YEAR_ILLEGAL = "35138";

	/** 入学时间月非法(只能为二位数字) */
	public static final String STU_APP_UPDATE_SCHOOL_DATE_MONTH_ILLEGAL = "35139";
	/** 年级不能为空 */
	public static final String STU_APP_UPDATE_SCHOOL_GRADE_NOTNULL = "35140";
	/** 年级非法(1-5字母数字) */
	public static final String STU_APP_UPDATE_SCHOOL_GRADE_LENGTH = "35141";
	/** 学号不能为空 */
	public static final String STU_APP_UPDATE_SCHOOL_STUID_NOTNULL = "35142";
	/** 学号长度不正确 */
	public static final String STU_APP_UPDATE_SCHOOL_STUID_LENGTH = "35143";
	/** 专业不能为空 */
	public static final String STU_APP_UPDATE_SCHOOL_MAJOR_NOTNULL = "35144";
	/** 专业输入非法(只能输入中文) */
	public static final String STU_APP_UPDATE_SCHOOL_MAJOR_FALSE = "35145";

	/** 专业长度不正确 */
	public static final String STU_APP_UPDATE_SCHOOL_MAJOR_LENGTH = "35146";
	/** 学号输入非法(只能数字和字母) */
	public static final String STU_APP_UPDATE_SCHOOL_STUID_FALSE = "35147";

	/** 学生类型不能为空 */
	public static final String STU_APP_UPDATE_STUTYPE_NOTNULL = "35148";

	/** 学生类型非法 */
	public static final String STU_APP_UPDATE_STUTYPE_FAIL = "35149";
	/** 学校地址(区)非法字符(只能位6位数字代码) */
	public static final String STU_APP_CONTACT_SCHOOL_AREA_FALSE = "35150";
	/** 学校详细地址不能为空 */
	public static final String STU_APP_SCHOOL_AREA_DETAIL_NOTNULL = "35151";

	/** 请选择完整的家庭地址区域 */
	public static final String STU_APP_HOME_AREA_NOTNULL = "35152";
	/** 家庭地址(省)非法字符(只能位6位数字代码) */
	public static final String STU_APP_CONTACT_HOME_PROVINCE_FALSE = "35153";

	/** 家庭地址(市)非法字符(只能位6位数字代码) */
	public static final String STU_APP_CONTACT_HOME_CITY_FALSE = "35154";

	/** 家庭地址(区)非法字符(只能位6位数字代码) */
	public static final String STU_APP_CONTACT_HOME_AREA_FALSE = "35155";
	/** 学校详细地址输入非法(不能包含特殊字符) */
	public static final String STU_APP_SCHOOL_AREA_DETAIL_FALSE = "35156";
	/** 学校详细地址长度不正确 */
	public static final String STU_APP_SCHOOL_AREA_DETAIL_LENGTH = "35157";
	/** 家庭详细地址不能为空 */
	public static final String STU_APP_HOME_AREA_DETAIL_NOTNULL = "35158";
	/** 家庭详细地址输入非法(不能包含特殊字符) */
	public static final String STU_APP_HOME_AREA_DETAIL_FALSE = "35159";
	/** 家庭详细地址长度不正确 */
	public static final String STU_APP_HOME_AREA_DETAIL_LENGTH = "35160";
	/** 直系亲属姓名不能为空 */
	public static final String STU_APP_FAMILY_NAME_NOTNULL = "35161";

	/** 直系亲属关系不能为空 */
	public static final String STU_APP_FAMILY_RELATION_NOTNULL = "35162";

	/** 直系亲属工作单位不能为空 */
	public static final String STU_APP_FAMILY_UNIT_NOTNULL = "35163";

	/** 直系亲属手机不能为空 */
	public static final String STU_APP_FAMILY_PHONE_NOTNULL = "35164";

	/** 直系亲属地址不能为空 */
	public static final String STU_APP_FAMILY_ADDRESS_NOTNULL = "35165";

	/** 紧急联系人姓名不能为空 */
	public static final String STU_APP_CONTACT_NAME_NOTNULL = "35166";

	/** 紧急联系人关系不能为空 */
	public static final String STU_APP_CONTACT_RELATION_NOTNULL = "35167";

	/** 紧急联系人手机不能为空 */
	public static final String STU_APP_CONTACT_PHONE_NOTNULL = "35168";

	/** 直系亲属姓名输入非法(只能输入中文) */
	public static final String STU_APP_FAMILY_NAME_FALSE = "35169";

	/** 直系亲属工作单位输入非法字符 */
	public static final String STU_APP_FAMILY_UNIT_FALSE = "35170";

	/** 直系亲属手机输入非法 */
	public static final String STU_APP_FAMILY_PHONE_FALSE = "35171";

	/** 直系亲属地址非法字符 */
	public static final String STU_APP_FAMILY_ADDRESS_FALSE = "35172";

	/** 紧急联系人姓名非法字符(只能为中文) */
	public static final String STU_APP_CONTACT_NAME_FALSE = "35173";

	/** 紧急联系人手机输入非法 */
	public static final String STU_APP_CONTACT_PHONE_FALSE = "35174";

	/** 紧急联系人(姓名)输入长度错误 */
	public static final String STU_APP_CONTACT_NAME_LENGTH = "35175";

	/** 直系联系人(姓名)输入长度错误 */
	public static final String STU_APP_FAMILY_NAME_LENGTH = "35176";

	/** 直系亲属工作单位长度不对 */
	public static final String STU_APP_FAMILY_UNIT_LENGTH = "35177";

	/** 直系亲属地址非法长度 */
	public static final String STU_APP_FAMILY_ADDRESS_LENGTH = "35178";

	/** 紧急联系人地址非法长度 */
	public static final String STU_APP_CONTACT_ADDRESS_LENGTH = "35179";

	/** 紧急联系人姓名不能与直系亲属姓名相同 */
	public static final String STU_APP_NAME_EQ_FALSE = "35180";

	/** 紧急联系人关系不能与直系亲属关系相同 */
	public static final String STU_APP_RELATION_EQ_FALSE = "35181";

	/** 紧急联系人手机不能与直系亲属手机相同 */
	public static final String STU_APP_PHONE_EQ_FALSE = "35182";

	/** 直系亲属关系非法(1-5字母数字) */
	public static final String STU_APP_FAMILY_RELATION_FALSE = "35183";

	/** 紧急联系人关系非法(1-5字母数字) */
	public static final String STU_APP_RELATION_FALSE = "35184";
	/** 保存客户基本信息失败 */
	public static final String STU_APP1_INFO_EXCEPTION = "35185";
	/** 更新客户联系信息失败 */
	public static final String UPDATE_APP1_CONTACT_EXCEPTION = "35186";
	/** 保存客户联系信息异常 */
	public static final String CONTACT_APP_EXCEPTION = "35187";
	/** 更新客户联系人信息失败 */
	public static final String UPDATE_APP_EXCEPTION = "35188";
	/** 保存客户联系人信息异常 */
	public static final String APP_EXCEPTION = "35189";
	/** 个人详细信息查询异常 */
	public static final String STU_APP_DETAIL_QUERY_EXCEPTION = "35190";

	/** 个人详细信息查询无结果集 */
	public static final String STU_APP_DETAIL_QUERY_NOT_FOUND = "35191";
	/** 客户类型异常,输入的不是学生用户名 */
	public static final String STU_APP_DETAIL_CUSTTYPE_NOT_FOUND = "35192";
	/** 流水号不为空 */
	public static final String STU_APP_QUERY_FASTLOAN_NO = "35193";
	/** 该订单已申请完成，不能再操作此订单 */
	public static final String STU_APP_APPLY_NOT_OPERO = "35194";
	/** 请填写正确的家庭固话 */
	public static final String USER_TEL_FALSE = "35195";

	/** 请填写正确的qq号码 */
	public static final String USER_QQ_FALSE = "35196";
	/** QQ号码不能为空 */
	public static final String USER_QQ_NOTNUll = "35197";
	/** 请填写正确的单位固话 */
	public static final String COLLAR_UNIT_TEL_ILLEGAL = "35198";

	/** 当前月收入不能为空 */
	public static final String COLLAR_MONTH_INCOME_NOTNULL = "35199";

	/** 当前月消费不能为空 */
	public static final String COLLAR_MONTH_CONSUMPTION_NOTNULL = "35200";

	/** 当前月收入长度不正确 */
	public static final String COLLAR_MONTH_INCOME_LENGTH = "35201";

	/** 当前月消费长度不正确 */
	public static final String COLLAR_MONTH_CONSUMPTION_LENGTH = "35202";
	/** 当前月收入非法字符 */
	public static final String COLLAR_MONTH_INCOME_EXCEPTION = "35203";

	/** 当前月消费非法字符 */
	public static final String COLLAR_MONTH_CONSUMPTION_EXCEPTION = "35204";
	/** 客户号不能为空 */
	public static final String PROXY_DEDUCTMONEY_RECHARGE_CUSTID_ISNULL = "35205";
	/** 订单号不能为空 */
	public static final String PROXY_DEDUCTMONEY_RECHARGE_ORDERID_ISNULL = "35206";
	/** 手机令牌不能为空 */
	public static final String PROXY_DEDUCTMONEY_RECHARGE_PHONETOKEN_ISNULL = "35207";
	/** 手机验证码不能为空 */
	public static final String PROXY_DEDUCTMONEY_RECHARGE_PHONECODE_ISNULL = "35208";
	/** 更改代扣银行卡验证表失败 */
	public static final String HMD_PROXYDEDUCT_RECORD_UPDATE_EXCEPTION = "35209";
	/** 姓名格式不正确 */
	public static final String TONGDUN_VALRULES_NAME_FALSE = "35210";
	/** 请求同盾验证客户黑名单接口异常 */
	public static final String TONGDUN_VALRULES_EXCEPTION = "35211";

	/** 验证黑名单异常 */
	public static final String TONGDUN_VALRULES_SELF_EXCEPTION = "35212";

	/** 风险系数高 */
	public static final String TONGDUN_VALRULES_SCORE_HIGH = "35213";
	/** 调用同盾接口失败 */
	public static final String TONGDUN_VALRULES_FALSE = "35214";
	/** 保存同盾验证客户黑名单异常 */
	public static final String TONGDUN_SAVEBLACE_EXCEPTION = "35215";
	/** 手机令牌不能为空 */
	public static final String CMBC_IDENTIFYCONFIRM_PHONE_TOKEN_NOTNULL = "35216";
	/** 已经实名认证 */
	public static final String CMBC_IDENTIFY_SEND_CODE_SUCC = "35217";
	/** 认证操作处理中 */
	public static final String CMBC_IDENTIFY_CONFIRM_WAIT = "35218";
	/** 无验证码信息 */
	public static final String CMBC_IDENTIFY_CONFIRM_CODE_ISNULL = "35219";
	/** 验证码错误 */
	public static final String CMBC_IDENTIFY_CONFIRM_CODE_FALSE = "35220";
	/** 手机令牌错误 */
	public static final String CMBC_IDENTIFY_CONFIRM_TOKEN_FALSE = "35221";
	/** 发送短信验证码失败！ */
	public static final String CMBC_IDENTIFY_SEND_CODE_FALSE = "35222";
	/** 实名认证失败 ！ */
	public static final String CMBC_IDENTIFY_CONFIRM_FALSE = "35223";
	/** 银行编码不能为空 ！ */
	public static final String CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL = "35224";
	/** 民生银行实名认证发送短信验证码异常 ！ */
	public static final String CMBC_IDENTIFY_SENDCODE_EXCEPTION = "35225";
	/** 民生银行实名认证验证短信验证码异常！ */
	public static final String CMBC_IDENTIFY_CONFIRM_EXCEPTION = "35226";
	/** 民生银行实名认证更新验证状态异常！ */
	public static final String CMBC_IDENTIFY_CONFIRM_STATUS_EXCEPTION = "35227";
	/** 订单号错误 */
	public static final String PROXY_DEDUCTMONEYNEW_ORDERID_FALSE = "35228";
	/** 客户号错误 */
	public static final String PROXY_DEDUCTMONEYNEW_CUSTID_FALSE = "35229";
	/** 手机令牌错误 */
	public static final String PROXY_DEDUCTMONEYNEW_PHONETOKEN_FALSE = "35230";

	/** 订单号，客户号，手机令牌错误 */
	public static final String PROXY_DEDUCTMONEYNEW_FALSE = "35231";

	/** 查询银行卡信息异常 */
	public static final String WITH_HOLDINGBANK_CARDQUERY_EXCEPTION = "35232";

	/** 民生快捷充值，更新申请件绑定卡异常 */
	public static final String CMBC_IDENTIFY_CARD_EXCEPTION = "35233";

	/** 查询银行卡验证记录异常 */
	public static final String QUERY_BANK_CARD_VALIDATE_EXCEPTION = "35300";

	/* 白领申请提现手机端 */
	/** 修改类型不能为空 */
	public static final String COLLAR_UPDATE_TYPE_NOTNULL = "36100";
	/** 修改类型错误 */
	public static final String COLLAR_UPDATE_TYPE_FALSE = "36101";

	/** 申请件不能为空 */
	public static final String COLLAR_UPDATE_APPNO_NOTNULL = "36102";

	/** 申请件输入非法 */
	public static final String COLLAR_UPDATE_APPNO_FALSE = "36103";

	/** 配偶姓名不能为空 */
	public static final String COLLAR_INFO_SPOUSENAME_ISNULL = "36104";

	/** 配偶姓名长度或者格式不正确 */
	public static final String COLLAR_INFO_SPOUSENAME_ILLEGAL = "36105";
	/** 配偶手机号不能为空 */
	public static final String COLLAR_INFO_MOBILE_ISNULL = "36106";

	/** 配偶手机号长度或者格式不正确 */
	public static final String COLLAR_INFO_MOBILE_ILLEGAL = "36107";
	// /** 毕业时间年不能为空 */
	// public static final String CUST_REGISTINFO_ENDYEAR_NOTNULL = "36108";

	/** 毕业时间年非法 */
	public static final String CUST_REGISTINFO_ENDYEAR_FAIL = "36109";

	// /** 毕业时间月不能为空 */
	// public static final String CUST_REGISTINFO_ENDMON_NOTNULL = "36110";

	/** 毕业时间月非法 */
	public static final String CUST_REGISTINFO_ENDMON_FAIL = "36111";
	/** 毕业院校不能为空 */
	public static final String COLLAR_INFO_SCHOOLNAME_NOTNULL = "36112";

	/** 毕业时间不能为空 */
	public static final String COLLAR_INFO_SCHOOLENDTIME_NOTNULL = "36113";

	/** 最高学历不能为空 */
	public static final String COLLAR_INFO_EDUCATION_NOTNULL = "36114";

	/** 学制不能为空 */
	public static final String COLLAR_INFO_SYSTEM_NOTNULL = "36115";
	/** 毕业院校输入非法(只能输入中文学校) */
	public static final String COLLAR_INFO_SCHOOLNAME_FALSE = "36116";

	/** 毕业院校长度不正确 */
	public static final String COLLAR_INFO_SCHOOLNAME_LENGTH = "36117";

	/** 学制非法(1-5字母数字) */
	public static final String COLLAR_INFO_SYSTEM_FALSE = "36118";
	/** 学历非法(1-5字母数字) */
	public static final String COLLAR_UPDATE_SCHOOL_EDUCATION_LENGTH = "36119";
	/** 基本资料保存失败 */
	public static final String COLLAR_INFO_SAVE_EXCEPTION = "36120";
	/** 基本资料保存异常 */
	public static final String COLLAR_INFO_EXCEPTION = "36121";
	/** 婚姻状况不能为空 */
	public static final String COLLAR_INFO_MATRIMONY_NOTNULL = "36122";
	/** 婚姻状况非法(1-5字母数字) */
	public static final String COLLAR_INFO_MATRIMONY_FALSE = "36123";
	/* 白领个人详细资料查询 */

	/** 个人详细信息查询异常 */
	public static final String COLLAR_DETAIL_QUERY_EXCEPTION = "36124";

	/** 个人详细信息查询无结果集 */
	public static final String COLLAR_DETAIL_QUERY_NOT_FOUND = "36125";
	/** 客户类型异常,输入的不是白领用户名 */
	public static final String COLLAR_DETAIL_CUSTTYPE_NOT_FOUND = "36126";

	/** 单位名称不能为空 */
	public static final String COLLAR_UNIT_NAME_NOTNULL = "36127";

	/** 单位名称输入非法(不能包含特殊字符) */
	public static final String COLLAR_UNIT_NAME_FALSE = "36128";
	/** 单位名称长度不正确 */
	public static final String COLLAR_UNIT_NAME_LENGTH = "36129";

	/** 单位入职部门长度不正确 */
	public static final String COLLAR_UNIT_DPM_LENGTH = "36130";

	/** 单位入职务长度不正确 */
	public static final String COLLAR_UNIT_JOBTITLE_LENGTH = "36131";
	/** 单位总工作年限不能为空 */
	public static final String COLLAR_UNIT_WORKTIME_NOTNULL = "36132";

	/** 单位入职部门不能为空 */
	public static final String COLLAR_UNIT_DPM_NOTNULL = "36133";

	/** 单位入职部门输入非法(不能包含特殊字符) */
	public static final String COLLAR_UNIT_DPM_FALSE = "36134";
	/** 请选择完整的单位入职时间 */
	public static final String COLLAR_UNIT_STARTDATE_NOTNULL = "36135";
	/** 单位入职时间(年)输入非法(只能为4位数字代码) */
	public static final String COLLAR_UNIT_STARTDATE_YEAR_ILLEGAL = "36136";

	/** 单位入职时间(月)输入非法(只能为2位数字代码) */
	public static final String COLLAR_UNIT_STARTDATE_MONTH_ILLEGAL = "36137";
	/** 请选择完整的单位地址区域 */
	public static final String COLLAR_CONTACT_UNIT_AREA_NOTNULL = "36138";

	/** 单位详细地址不能为空 */
	public static final String COLLAR_CONTACT_UNIT_DETAIL_NOTNUL = "36139";
	/** 单位地址(省)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_UNIT_PROVINCE_FALSE = "36140";

	/** 单位地址(市)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_UNIT_CITY_FALSE = "36141";

	/** 单位地址(区)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_UNIT_AREA_FALSE = "36142";
	/** 单位详细地址输入非法 */
	public static final String COLLAR_CONTACT_UNIT_DETAIL_FALSE = "36143";
	/** 单位详细地址长度不正确 */
	public static final String COLLAR_CONTACT_UNIT_DETAIL_LENGTH = "36144";

	/** 民族不能为空 */
	public static final String USERUPDATEINFO_NATION_ISNULL = "36145";

	/** 民族格式错误 */
	public static final String USERUPDATEINFO_NATION_ILLEGAL = "36146";

	/** 请选择完整的身份证有效期限 */
	public static final String USERUPDATEINFO_IDCARDVAL_ISNULL = "36147";

	/** 身份证有效期限格式错误 */
	public static final String USERUPDATEINFO_IDCARDVAL_ILLEGAL = "36148";

	/** 请选择身份证有效期 */
	public static final String USERUPDATEINFO_IDCARDTIME_ISNULL = "36149";

	/** 身份证有效期格式错误 */
	public static final String USERUPDATEINFO_IDCARDTIME_ILLEGAL = "36150";

	/* 获取用户可用余额 */

	/** 获取可用余额异常 */
	public static final String AVAILBALANCE_EXCEPTION = "36200";

	/* 商户入驻-发送验证码 */

	/** 发送验证码异常 */
	public static final String SENDSUPPLIERCODE_EXCEPTION = "36300";

	/** 手机号码已经存在 */
	public static final String SENDSUPPLIERCODE_MOBILE_EXIST = "36301";

	/** 验证码发送失败 */
	public static final String SENDSUPPLIERCODE_SENDCODE_FAIL = "36302";

	/** 保存验证码失败 */
	public static final String SENDSUPPLIERCODE_SAVECODE_FAIL = "36303";

	/** 手机号码不能为空 */
	public static final String SENDSUPPLIERCODE_MOBILE_ISNULL = "36351";

	/** 手机号码格式错误 */
	public static final String SENDSUPPLIERCODE_MOBILE_ILLEGAL = "36352";

	/* 新增商户 */

	/** 注册商户异常 */
	public static final String ADDMERAPP_EXCETPION = "36400";

	/** 手机号码已经存在 */
	public static final String ADDMERAPP_MOBILE_EXIST = "36401";

	/** 注册商户错误 */
	public static final String ADDMERAPP_REGISTER_FAIL1 = "36402";

	/** 注册商户错误 */
	public static final String ADDMERAPP_REGISTER_FAIL2 = "36403";

	/** 注册商户错误 */
	public static final String ADDMERAPP_REGISTER_FAIL3 = "36404";

	/** 请先获取验证码 */
	public static final String ADDMERAPP_IDCODE_NOTFOUND = "36405";

	/** 商户用户名已经存在 */
	public static final String ADDMERAPP_USERNAME_EXIST = "36406";

	/** 商户名称不能为空 */
	public static final String ADDMERAPP_MERUSERNAME_ISNULL = "36451";

	/** 商户联系人不能为空 */
	public static final String ADDMERAPP_CONTACTNAME_ISNULL = "36452";

	/** 用户名格式错误 */
	public static final String ADDMERAPP_MERUSERNAME_ILLEGAL = "36453";

	/** 密码格式错误 */
	public static final String ADDMERAPP_MERPASSWORD_ILLEGAL = "36454";

	/** 新增商户失败 */
	public static final String ADDMERAPP_ADDSUP_ERROR = "36455";

	/** 手机号码不能为空 */
	public static final String ADDMERAPP_MOBILE_ISNULL = "36456";

	/** 手机号码格式错误 */
	public static final String ADDMERAPP_MOBILE_ILLEGAL = "36457";

	/** 注册密码不能为空 */
	public static final String ADDMERAPP_MERPASSWORD_ISNULL = "36458";

	/** 用户名不能为空 */
	public static final String ADDMERAPP_USERNAME_ISNULL = "36459";

	/** 验证码不能为空 */
	public static final String ADDMERAPP_IDCODE_ISNULL = "36460";

	/** 验证码格式错误 */
	public static final String ADDMERAPP_IDCODE_ILLEGAL = "36461";

	/* 用户银行卡验证 */

	/** 银行卡验证异常 */
	public static final String PROXYDEDUCTMONEY_EXCETPION = "36500";

	/** 用户名不存在 */
	public static final String PROXYDEDUCTMONEY_USER_NOTFOUND = "36501";

	/** 银行卡认证失败，请您重新输入新的银行账户！ */
	public static final String PROXYDEDUCTMONEY_BANKCARD_FAIL1 = "36502";

	/** 银行卡认证失败，请您重新输入新的银行账户！ */
	public static final String PROXYDEDUCTMONEY_BANKCARD_FAIL2 = "36503";

	/** 银行卡认证失败，请您重新输入新的银行账户！ */
	public static final String PROXYDEDUCTMONEY_BANKCARD_FAIL3 = "36504";

	/** 嗨秒贷代扣充值异常 */
	public static final String PROXYDEDUCTMONEY_PROXY_EXCEPTION = "36505";

	/** 扣款处理中 */
	public static final String PROXYDEDUCTMONEY_DOING = "36506";

	/** 余额不足 */
	public static final String PROXYDEDUCTMONEY_BANLANCE_LACK = "36507";

	/** 请求超时，请稍后再试 */
	public static final String PROXYDEDUCTMONEY_REMOTE_OUTTIME = "36508";

	/** 您没有审核通过的申请件，暂不支持代扣方式的充值！ */
	public static final String PROXYDEDUCTMONEY_HKAPP_NOTFOUND = "36509";

	/** 代扣初始化异常 */
	public static final String PROXYDEDUCTMONEY_INIT_EXCEPTION = "36510";

	/** 获取代扣流水异常 */
	public static final String PROXYDEDUCTMONEY_FLOWNO_EXCEPTION = "36511";

	/** 银行卡不能为空 */
	public static final String PROXYDEDUCTMONEY_BANKCARD_ISNULL = "36551";

	/** 银行卡格式错误 */
	public static final String PROXYDEDUCTMONEY_BANKCARD_ILLEGAL = "36552";

	/** 开户行不能为空 */
	public static final String PROXYDEDUCTMONEY_OPENBANK_ISNULL = "36553";

	/** 开户行格式错误 */
	public static final String PROXYDEDUCTMONEY_OPENBANK_ILLEGAL = "36554";

	/** 金额不能为空 */
	public static final String PROXYDEDUCTMONEY_AMOUNT_ISNULL = "36555";

	/** 金额格式错误 */
	public static final String PROXYDEDUCTMONEY_AMOUNT_ILLEGAL = "36556";

	/* 新年抽奖活动 */

	/** 服务器异常 */
	public static final String NEWYEARLOT_EXCEPTION = "36800";

	/** 未找到用户 */
	public static final String NEWYEARLOT_USER_NOTFOUND = "36801";

	/** 对不起，活动还未开始 */
	public static final String NEWYEARLOT_ACT_NOSTART = "36802";

	/** 对不起，活动已经结束 */
	public static final String NEWYEARLOT_ACT_ENDED = "36803";

	/** 对不起，您已经抽过一次 */
	public static final String NEWYEARLOT_LOTONE = "36804";

	/** 对不起，您的抽奖机会为0 */
	public static final String NEWYEARLOT_LOTNUM_ISZERO = "36805";

	/** 活动类型不能为空 */
	public static final String NEWYEARLOT_ACTTYPE_ISNULL = "36851";

	/** 活动类型格式错误 */
	public static final String NEWYEARLOT_ACTTYPE_ILLEGAL = "36852";

	/** 该活动仅支持新用户，感谢您的参与 */
	public static final String NOT_IN_ACTIVITY_TIME = "36853";

	/* 蓝领用户注册成功，抽奖 */

	/** 服务器异常 */
	public static final String REGLOTTERY_EXCEPTION = "36900";

	/** 未找到用户 */
	public static final String REGLOTTERY_USER_NOTFOUND = "36901";

	/** 已经抽过一次，不能重复抽奖 */
	public static final String REGLOTTERY_REPEAT_LOTTERY = "36902";

	/** 非法错误 */
	public static final String REGLOTTERY_SAVETRAN_EXCEPTION = "36903";

	/** 非法错误 */
	public static final String REGLOTTERY_RECORDLOTTERY_EXCEPTION = "36904";

	/** 非法错误 */
	public static final String REGLOTTERY_UPDATECUST_EXCEPTION = "36905";

	// 电信专案手机接口
	/** 套餐详细信息查询无结果集 */
	public static final String CALL_PHONE_DATA_NOT_FOUND = "37000";
	/** 套餐详细信息查询异常 */
	public static final String CALL_PHONE_DATA_QUERY_EXCEPTION = "37001";
	/** 页数索引为正整数 */
	public static final String CALL_PHONE_NUM_PAGEINDEX_FALSE = "37002";
	/** 每页显示条数为正整数 */
	public static final String CALL_PHONE_NUM_PAGENUM_FALSE = "37003";
	/** 城市对应手机号码查询无结果集 */
	public static final String CALL_PHONE_NUM_NOT_FOUND = "37004";
	/** 城市对应手机号码查询异常 */
	public static final String CALL_PHONE_NUM_QUERY_EXCEPTION = "37005";

	/** 商品金额不能为空 */
	public static final String CALL_PHONE_DATA_AMOUNT_NOTNULL = "37006";
	/** 商品金额非法字符 */
	public static final String CALL_PHONE_DATA_AMOUNT_EXCEPTION = "37007";
	/** 商品金额长度不正确 */
	public static final String CALL_PHONE_DATA_AMOUNT_LENGTH = "37008";

	/** 商品期数非整型 */
	public static final String CALL_PHONE_DATA_PERIOD_NOTRIGHT = "37009";
	/** 套餐ID不能为空 */
	public static final String CALL_PHONE_DATA_NUM_ID_NOTNULL = "37010";
	/** 套餐ID为正整数 */
	public static final String CALL_PHONE_DATA_NUM_ID_NOTRIGHT = "37011";
	/** 订单类型(电信标志dx不能为空) */
	public static final String CALL_PHONE_DATA_DX_ID_NOTRIGHT = "37012";
	/** 电信接口保存临时订单失败 */
	public static final String CALL_PHONE_DATA_SAVE_FAIL = "37013";
	/** 电信接口保存临时订单异常 */
	public static final String CALL_PHONE_DATA_SAVE_EXCEPTION = "37014";

	/** 申请件不能为空 */
	public static final String USER_UPDATE_APPNO_NOTNULL = "37015";
	/** 申请件输入非法 */
	public static final String USER_UPDATE_APPNO_FALSE = "37016";

	/** 申请件查询无结果集 */
	public static final String CALL_PHONE_QUARTET_PROTOCOL_NULL = "37017";
	/** 申请件查询异常 */
	public static final String CALL_PHONE_QUARTET_PROTOCOL_EXCEPTION = "37018";
	/** 邮箱不能为空 */
	public static final String CALL_PHONE_QUARTET_PROTOCOL_EMAIL_ISNULL = "37019";

	/** 邮箱格式错误 */
	public static final String CALL_PHONE_QUARTET_PROTOCOL_EMAIL_ERROR = "37020";
	/** 更新邮箱异常 */
	public static final String CALL_PHONE_QUARTET_PROTOCOL_EMAIL_EXCEPTION = "37021";
	/** 身份证号码为空 */
	public static final String BLACK_CERTNO_UPDATE_NOTNULL = "37023";
	/** 已经是黑名单 */
	public static final String BLACK_CERTNO_UPDATE_ALREADY = "37024";
	/** 保存进黑名单 */
	public static final String BLACK_CERTNO_UPDATE_SAVEBLACK = "37025";

	/** 验证未通过，指用户名或密码错误 */
	public static final String BLACK_CERTNO_UPDATE_FALLUSERPWD = "37026";
	/** 无权操作 */
	public static final String BLACK_CERTNO_UPDATE_NOROUL = "37027";
	/** 余额不足 */
	public static final String BLACK_CERTNO_UPDATE_BALANCESMALL = "37028";
	/** 服务器故障或查询异常 */
	public static final String BLACK_CERTNO_UPDATE_FALLSERVICE = "37029";

	/** 保存进黑名单异常 */
	public static final String BLACK_CERTNO_UPDATE_FALLSERVICE_EXCEPTION = "37030";

	/** 手机号码被锁定 */
	public static final String CALL_PHONE_DATA_CAN_NOT_USE = "37031";
	/** 选择的手机号码不存在 */
	public static final String CALL_PHONE_DATA_CAN_NOT_FOUND = "37032";
	/** 锁定手机号码异常 */
	public static final String CALL_PHONE_DATA_SAVE_PHONE_EXCEPTION = "37033";

	/** 保存推荐信息异常 */
	public static final String SAVE_REFERENCE_RECORD_EXCEPTION = "37034";

	/* 获取用户还款总额 */

	/** 获取系统数据异常 */
	public static final String REPAYMENTAMOUNT_EXCEPTION = "36600";

	/* 删除临时申请件图片 */

	/** 删除图片异常 */
	public static final String REMOVEAPPPIC_EXCETPION = "36700";

	/** 图片ID不能为空 */
	public static final String REMOVEAPPPIC_PICID_ISNULL = "36751";

	/** 图片ID格式错误 */
	public static final String REMOVEAPPPIC_PICID_ILLEGAL = "36752";
	/** 验证不通过 */
	public static final String UPDATE_INPUTAPP_CARD_APP_VAL_FALSE = "35237";
	/** 更改申请件代扣银行卡异常 */
	public static final String UPDATE_INPUTAPP_CARD_EXCEPTION = "35234";
	/** 无申请件信息 */
	public static final String UPDATE_INPUTAPP_CARD_APP_EXCEPTION = "35235";
	/** 验证银行卡异常 */
	public static final String UPDATE_INPUTAPP_CARD_APP_VAL_EXCEPTION = "35236";

	/** 使用额度不能小于1000 */
	public static final String CERATE_APPPAY_USEAMOUNT_MIN_FALSE = "35239";
	/** 可用额度不足 */
	public static final String CERATE_APPPAY_USEAMOUNT_FALSE = "35240";
	/** 专案号和申请行业不匹配 */
	public static final String CREATEAPP_TASKNO_FAIL = "35241";

	/** 未找到专案号 */
	public static final String CREATEAPP_TASKNO_NOTFOUND = "35242";

	/** 流水号生成失败 */
	public static final String CREATE_APPPAY_GET_APPNO_FAILD = "35243";

	/** 创建申请件对象失败,还有一笔，不能重复申请 */
	public static final String CREATE_APPPAY_REPEAT_FAILD = "35244";
	/** 调用账务服务金额计算失败 */
	public static final String CREATE_APPPAY_GET_ACCT_FAILD = "35245";

	/** 申请实物产品未查询到 */
	public static final String CREATE_APPPAY_QUERY_PROINFO_FAILD = "35246";

	/** 创建申请件对象失败 */
	public static final String CREATE_APPPAY_CREAT_APP_FAILD = "35247";

	/** 更新申请件节点失败 */
	public static final String CREATE_APPPAY_UPDATE_NODE_FAILD = "35248";

	/** 暂不做白领业务 */
	public static final String CREATE_APPPAY_CALLRA_CANCEL = "35249";

	/** 命中规则 */
	public static final String CREATE_APPPAY_RULE_IN = "35250";

	/** 申请SQL处理失败 */
	public static final String CREATE_APPPAY_SQL_ERROR = "35251";

	/** 当天流水序号查询失败 */
	public static final String CREATE_APPPAY_QUERY_APPNO_FAILD = "35252";

	/** 申请客户信息未查询到 */
	public static final String CREATE_APPPAY_APPLYINFO_NOT_FOUND = "35253";

	/** 业务员信息未查询到 */
	public static final String CREATE_APPPAY_APPROVEUSER_NOT_FOUND = "35254";

	/** 学校信息未找到 */
	public static final String CREATE_APPPAY_SCHOOLINFO_NOT_FOUND = "35255";

	/** 销售人员和学校对应关系未查询到 */
	public static final String CREATE_APPPAY_LINKINFO_NOT_FOUND = "35256";

	/** 接收账务服务返回处理异常 */
	public static final String CREATE_APPPAY_IO_EXCEPTION = "35257";
	/** 账务服务连接异常 */
	public static final String CREATE_APPPAY_ACCOUNT_ERROR = "35258";

	/** 创建申请件信息对象异常 */
	public static final String CREATE_APPPAY_INPUT_ERROR = "35259";
	/** 临时申请状态更新异常 */
	public static final String REGISTER_APPLYTEMP_EXCEPTION = "35260";

	/** 修改客户信息毕业时间错误 */
	public static final String END_SCHOOL_TIME_ERROR = "35261";

	/** 查询卡号信息错误 */
	public static final String USER_CARD_NOT_FOUND = "35262";

	/** 创建申请件服务器处理异常 */
	public static final String CREATE_APPPAY_EXCEPTION = "35263";

	/** 业务员账号不能为空 */
	public static final String CREATEPAY_APPROUSER_ISNULL = "35264";

	/** 业务员账号非法 */
	public static final String CREATEPAY_APPROUSER_ILLEGAL = "35265";

	/** 用户名不能为空 */
	public static final String USERNAME_NOT_NULL = "35266";

	/** 用户名输入非法(必须6-20位数字、字母或下划线组合且不包含非法字符) */
	public static final String USERNAME_ILLEGAL = "35267";
	/** 商品名称不能为空 */
	public static final String REGISTER_PRODUCTNAME_ISNULL = "35268";

	/** 借款金额为空 */
	public static final String APPLYAMOUNT_ERROR_ISNULL = "35269";

	/** 借款金额非法 */
	public static final String APPLYAMOUNT_ERROR_CANTCHAR = "35270";

	/** 借款金额长度不正确 */
	public static final String APPLYAMOUNT_ERROR_LENGTH = "35271";

	/** 销售码为空 */
	public static final String SALECODE_ERROR_ISNULL = "35272";

	/** 销售码长度不正确 */
	public static final String SALECODE_ERROR_LENGTH = "35273";

	/** 产品类别存在非法字符 */
	public static final String PRODUCTTYPE_ERROR_CANTCHAR = "35274";

	/** 产品类别长度不正确 */
	public static final String PRODUCTTYPE_ERROR_ISNULL = "35275";

	/** 员工号不能为空 */
	public static final String SALECODE_ISNULL = "35276";

	/** 银行卡主键不能为空 */
	public static final String CREATEAPPPAY_BANKCARDNO_ISNULL = "35277";

	/** 银行卡主键非法 */
	public static final String CREATEAPPPAY_BANKCARDNO_ILLEGAL = "35278";

	/** 备注不能为空 */
	public static final String CREATEAPPPAY_DETAIL_ISNULL = "35279";

	/** 产品规格参数字符过长 */
	public static final String CREATEAPPPAY_DETAIL_ILLEGAL = "35280";

	/** 商品总价格式非法 */
	public static final String CREATEAPPPAY_PRO_PRICE_ILLEGAL = "35281";

	/** 客户类型存在非法字符 */
	public static final String CUSTTYPE_ERROR_CANTCHAR = "35282";

	/** 首付比率为空 */
	public static final String FIRSTRATE_ERROR_ISNULL = "35283";

	/** 首付比率存在非法字符 */
	public static final String FIRSTRATE_ERROR_CANTCHAR = "35284";

	/** 贷款产品ID为空 */
	public static final String LOANPRODUCT_ERROR_ISNULL = "35285";

	/** 申请件类型为空 */
	public static final String APPLYTYPE_ERROR_ISNULL = "35286";

	/** 申请方式存在非法字符 */
	public static final String APPLYTYPE_ERROR_CANTCHAR = "35287";

	/** 客户类型为空 */
	public static final String CUSTTYPE_ERROR_ISNULL = "35288";
	/** 商户ID不能为空 */
	public static final String CREATEPAY_SUPPLIERID_ISNULL = "35289";

	/** 商户ID非法 */
	public static final String CREATEPAY_SUPPLIERID_ILLEGAL = "35290";

	/** 售点ID不能为空 */
	public static final String CREATEPAY_SITEID_ISNULL = "35291";

	/** 售点ID违法 */
	public static final String CREATEPAY_SITEID_ILLEGAL = "35292";
	/** 首付比率长度不正确 */
	public static final String FIRSTRATE_ERROR_LENGTH = "35293";

	/** 不符合条件(非蓝领用户或未完善蓝领注册信息) */
	public static final String CREATEPAY_ISNOTLANUSER = "35294";

	/** 借款用途不能为空 */
	public static final String CREATEAPPPAY_LOANUSE_ISNULL = "35295";

	/** 借款用途格式错误 */
	public static final String CREATEAPPPAY_LOANUSE_ILLEGAL = "35296";

	/** 单位规模不能为空 */
	public static final String COLLAR_SCALE_ISNULL = "35297";

	/** 单位工作年限不能为空 */
	public static final String COLLAR_WORKYEAR_ISNULL = "35298";

	/** 职位不能为空 */
	public static final String COLLAR_JOB_ISNULL = "35299";

	/* 提交申请件-蓝领数码 */

	/** 系统异常 */
	public static final String SAVEAPPACCT_EXCEPTION = "37100";

	/** 未找到用户 */
	public static final String SAVEAPPACCT_USER_NOTFOUND = "37101";

	/** 未找到申请件 */
	public static final String SAVEAPPACCT_APP_NOTFOUND = "37102";

	/** 系统异常 */
	public static final String SAVEAPPACCT_EXCEPTION1 = "37102";

	/** 系统异常 */
	public static final String SAVEAPPACCT_EXCEPTION2 = "37103";

	/** 系统异常 */
	public static final String SAVEAPPACCT_EXCEPTION3 = "37104";

	/** 系统异常 */
	public static final String SAVEAPPACCT_EXCEPTION4 = "37105";

	/** 不能重复提交 */
	public static final String SAVEAPPACCT_REPEATCMT_FAIL = "37106";

	/** 请选择开户银行 */
	public static final String SAVEAPPACCT_OPENBAN_ISNULL = "37151";

	/** 开户银行格式错误 */
	public static final String SAVEAPPACCT_OPENBAN_ILLEGAL = "37152";

	/** 银行卡号不能为空 */
	public static final String SAVEAPPACCT_BANKCARD_ISNULL = "37153";

	/** 银行卡号格式错误 */
	public static final String SAVEAPPACCT_BANKCARD_ILLEGAL = "37154";

	/** 请勾选是否开通银行卡代扣 */
	public static final String SAVEAPPACCT_BANKSYN_ISNULL = "37155";

	/** 开通银行卡代扣格式错误 */
	public static final String SAVEAPPACCT_BANKSYN_ILLEGAL = "37156";

	/** 申请件单号不能为空 */
	public static final String SAVEAPPACCT_APPNO_ISNULL = "37157";

	/** 申请件单号格式错误 */
	public static final String SAVEAPPACCT_APPNO_ILLEGAL = "37158";

	/* 新年抽奖（红包）活动 */

	/** 服务器异常 */
	public static final String NEWYEARREDPACK_EXCEPTION = "37200";

	/** 未找到用户 */
	public static final String NEWYEARREDPACK_USER_NOTFOUND = "37201";

	/** 对不起，活动还未开始 */
	public static final String NEWYEARREDPACK_ACT_NOSTART = "37202";

	/** 对不起，活动已经结束 */
	public static final String NEWYEARREDPACK_ACT_ENDED = "37203";

	/** 对不起，您已经抽过一次 */
	public static final String NEWYEARREDPACK_LOTONE = "37204";

	/** 对不起，您的抽奖机会为0 */
	public static final String NEWYEARREDPACK_LOTNUM_ISZERO = "37205";

	/** 活动类型不能为空 */
	public static final String NEWYEARREDPACK_ACTTYPE_ISNULL = "37251";

	/** 活动类型格式错误 */
	public static final String NEWYEARREDPACK_ACTTYPE_ILLEGAL = "37252";

	/* 获取用户信息 */

	/** 获取用户信息异常 */
	public static final String USERINFO_EXCEPTION = "37300";

	/** 用户不存在 */
	public static final String USERINFO_USER_NOTFOUND = "37301";

	/* 修改蓝领用户信息 */

	/** 更新用户信息异常 */
	public static final String UPDATELANUSERINFO_EXCEPTION = "37400";

	/** 用户不存在 */
	public static final String UPDATELANUSERINFO_USER_NOTFOUND = "37401";

	/** 更新门店异常 */
	public static final String UPDATELANUSERINFO_SAVESTORE_EXCEPTION = "37402";

	/** 获取门店号序列异常 */
	public static final String UPDATELANUSERINFO_QUERYSEQ_EXCEPTION = "37403";

	/** 邀请码不能为空 */
	public static final String UPDATELANUSERINFO_INVITECODE_ISNULL = "37451";

	/** 邀请码格式错误 */
	public static final String UPDATELANUSERINFO_INVITECODE_ILLEGAL = "37452";

	/** 门店号不能为空 */
	public static final String UPDATELANUSERINFO_STORECODE_ISNULL = "37453";

	/** 门店号格式错误 */
	public static final String UPDATELANUSERINFO_STORECODE_ILLEGAL = "37454";

	/** 图片url为空 */
	public static final String UPDATELANUSERINFO_PICURL_ISNULL = "37455";

	/** 请选择是已有门店 */
	public static final String UPDATELANUSERINFO_ISHAVESTORE_ISNULL = "37456";

	/** 是否已有门店格式错误 */
	public static final String UPDATELANUSERINFO_ISHAVESTORE_ILLEGAL = "37457";

	/** 单位名称不能为空 */
	public static final String UPDATELANUSERINFO_UNITNAME_ISNULL = "37458";

	/** 单位名称格式错误 */
	public static final String UPDATELANUSERINFO_UNITNAME_ILLEGAL = "37459";

	/** 店名不能为空 */
	public static final String UPDATELANUSERINFO_STORENAME_ISNULL = "37460";

	/** 店名格式错误 */
	public static final String UPDATELANUSERINFO_STORENAME_ILLEGAL = "37461";

	/** 请选择完整的省份和城市 */
	public static final String UPDATELANUSERINFO_CITY_ISNULL = "37462";

	/** 省份或者城市格式错误 */
	public static final String UPDATELANUSERINFO_CITY_ILLEGAL = "37463";

	/** 店址不能为空 */
	public static final String UPDATELANUSERINFO_ADDRESS_ISNULL = "37464";

	/** 店址格式错误 */
	public static final String UPDATELANUSERINFO_ADDRESS_ILLEGAL = "37465";

	/** 路牌号不能为空 */
	public static final String UPDATELANUSERINFO_ROADNO_ISNULL = "37466";

	/** 路牌号格式错误 */
	public static final String UPDATELANUSERINFO_ROADNO_ILLEGAL = "37467";

	/** 经营权限不能为空 */
	public static final String UPDATELANUSERINFO_OPERATEPOW_ISNULL = "37468";

	/** 经营权限格式错误 */
	public static final String UPDATELANUSERINFO_OPERATEPOW_ILLEGAL = "37469";

	/** 经营时间不能为空 */
	public static final String UPDATELANUSERINFO_OPERATETIM_ISNULL = "37470";

	/** 经营时间格式错误 */
	public static final String UPDATELANUSERINFO_OPERATETIM_ILLEGAL = "37471";

	/** 法人姓名不能为空 */
	public static final String UPDATELANUSERINFO_LEGALNAME_ISNULL = "37472";

	/** 法人姓名格式错误 */
	public static final String UPDATELANUSERINFO_LEGALNAME_ILLEGAL = "37473";

	/** 单位电话不能为空 */
	public static final String UPDATELANUSERINFO_UNITPHONE_ISNULL = "37474";

	/** 单位电话格式错误 */
	public static final String UPDATELANUSERINFO_UNITPHONE_ILLEGAL = "37475";

	/** 请上传单位照片 */
	public static final String UPDATELANUSERINFO_UNITPIC_ISNULL = "37476";

	/** 请上传位置定位照片 */
	public static final String UPDATELANUSERINFO_LOCAPIC_ISNULL = "37477";

	/** 请上传门店照片 */
	public static final String UPDATELANUSERINFO_STOREPIC_ISNULL = "37478";

	/** 您还有照片未上传 */
	public static final String UPDATELANUSERINFO_OTHERPIC_ISNULL = "37479";

	/** 请上传工商网查询照片 */
	public static final String UPDATELANUSERINFO_GSWCXPIC_ISNULL = "37480";

	/** 客户收货地址信息：详细地址不能为空 */
	public static final String CUST_REC_ADRESS_DETAIL_NOTNULL = "37481";

	/** 客户收货地址信息：详细地址输入非法(不能包含特殊字符) */
	public static final String CUST_REC_ADRESS_DETAIL_FALSE = "37482";

	/** 增加地址异常 */
	public static final String ADD_ADRESS_EXCEPTION = "37483";
	/**修改收货地址异常*/
	public static final String UPDATE_ADDRESS_EXCCEPTION = "37492";
	/**查看收货地址异常*/
	public static final String QUERY_ADDRESS_EXCEPTION = "37493";
	// /** 诺亚验证失败 */
	public static final String NOAH_VALID_FAIL = "37484";
	/** 开户省份不能为空 */
	public static final String VALID_BANK_PRO_NOTNULL = "37485";
	/** 开户城市不能为空 */
	public static final String VALID_BANK_CITY_NOTNULL = "37486";
	/** 开户区不能为空 */
	public static final String VALID_BANK_AREA_NOTNULL = "37487";

	/** 支行号不能为空 */
	public static final String VALID_BANK_BATCH_NOTNULL = "37488";

	public static final String VALID_BANK_AAAA_NOTNULL = "37489";

	/** 查询信息为空 */
	public static final String DK_INFO_BYAPP_NOTFOUND = "37490";

	/** 查询代扣信息异常 */
	public static final String DK_INFO_BYAPP_EXCEPTION = "37491";

	/** 秒贷已关闭 */
	public static final String MD_CLOSE = "38000";

	/** 滴滴贷限广沪北深 */
	public static final String DDCP_ADDRESS = "38001";

	/** 验证来源不能为空 */
	public static final String DK_INFO_VALID_ISNULL = "37492";

	/** 行业代码不能为空 */
	public static final String INDUSTRY_CODE_ISNULL = "37493";
	/** 滴滴贷归属地非上海，不支持 */
	public static final String DDCP_NOTSH_UNSU = "37494";
	/** 滴滴贷学生，不支持 */
	public static final String DDCP_STU_UNSU = "37495";
	/** 滴答贷学生，信用卡，不支持 */
	public static final String LDDD_STU_DJK = "37496";

	/** 验证信用卡不能为空 */
	public static final String CREDIT_CARD_ISNULL = "37497";

	/** 单位电话区号不能为空 */
	public static final String UNIT_PHONE_AREA_ISNULL = "37498";

	/** 是否支持申请异常 */
	public static final String GS_ZCDD_EXCEPTION = "37499";

	/** 身份证号码不能为空 */
	public static final String IDCARD_ISNULL = "37500";

	/** 分数不能为空 */
	public static final String SCORE_ISNULLL = "37501";

	/** 分数格式错误 */
	public static final String SCORE_ILLEGAL = "37502";

	/** 高阈值为空 */
	public static final String THRESHOLD_HIGH_ISNULL = "37503";

	/** 低阈值为空 */
	public static final String THRESHOLD_LOW_ISNULL = "37504";

	/** 人脸识别失败 */
	public static final String YIDAO_MARK_ERROR = "37505";

	/** 客户信息未找到 */
	public static final String CUSTOMER_NOT_FOUNT = "37506";

	/** 更新客户信息异常 */
	public static final String UPDATE_CUSTOMER_EXCEPTION = "37507";

	/** 图片类型不能为空 */
	public static final String IMG_TYPE_ISNULL = "37508";

	/** 上传类型不为空 */
	public static final String UPLOAD_TYPE_ISNULL = "37509";

	/* jxl运营商认证信息 */

	/** 用户名不能为空 */
	public static final String USERNAME_ISNULL = "37510";

	/** time不能为空 */
	public static final String TIME_ISNULL = "37511";

	/** 行业代码不能为空 */
	public static final String IDDUSTRYCODE_ISNULL = "37512";

	/** 网站英文名称不能为空 */
	public static final String WEBSITE_ISNULL = "37513";

	/** 服务密码不能为空 */
	public static final String PASSWORD_ISNULL = "37514";

	/** 短信验证码不能为空 */
	public static final String MESSAGE_ISNULL = "37515";

	/** 认证流水token不能为空 */
	public static final String SEQ_NO_ISNULL = "37516";

	/** 短信类型不能为空 */
	public static final String CURRENTMAGType_ISNULL = "37517";

	/** time不能不为1234 异常 */
	public static final String TIME_EXCEPTION = "37518";

	/** 创建申请件异常 */
	public static final String Day_HOURS_EXCEPTOIN = "37522";
	/** 您在一个月内申请过该产品并被拒绝，您可以去首页选择其他产品。 */
	public static final String MONTH_HYIN_REGIST = "37523";

	/** 您当前还有一笔未完成的产品 */
	public static final String USER_APP_ING = "37524";
	/** 身份证签发机关不能为空 */
	public static final String IDCARDFROM_ISNULL = "37525";

	/** 银行未找到 */
	public static final String BANK_NOT_FOUND = "37526";

	/** 查询人脸识别时间异常. */
	public static final String FACERECOGNITION_EXCEPTION = "37527";

	/** VIP贷的申请金额不能大于可用余额 */
	public static final String Not_Sufficient_Funds_For_VIPD = "37528";

	/** 未授信 */
	public static final String Not_Have_Credit_Extension = "37529";

	/** 申请期数不在可允许期数内 */
	public static final String Not_Allow_Periods = "37530";

	/** 您有一笔未结清的订单，请结清后再申请 */
	public static final String UNCLEARED_APP = "37531";

	/** 金额申请必须大于0 */
	public static final String AMOUNT_IS_TOO_SMALL = "37532";

	/** 期数不能为0 */
	public static final String PERIODS_IS_ZERO = "37533";

	/** 实物产品ID为空 */
	public static final String PROID_ERROR_ISNULL = "10160";

	/** 实物产品ID非法字符 */
	public static final String PROID_ERROR_ILLEGAL = "10176";

	/** 商户id为空 */
	public static final String SUPPLIERID_ISNULL = "10182";

	/** 投资人信息错误 */
	public static final String INVEUSER_ERROR = "14102";

	/** 查询结果为空 */
	public static final String LOAN_PRO_FAIL = "10701";

	/** 查询结果异常 */
	public static final String LOAN_PRO_ERRER = "10700";

	/** 贷款计算出现异常 */
	public static final String LOAN_AMT_CAL_EXCEPTION = "10600";

	/** 节点不能为空 */
	public static final String NODE_ISNULL = "10601";
	/** 状态不能为空 */
	public static final String STATUS_ISNULL = "10602";

	/** 查询能否授信异常 */
	public static final String CHECK_CREDIT_EXCEPTION = "10603";

	/** 查询能否提额异常 */
	public static final String CHECK_LIMIT_EXCEPTION = "10604";

	/** 申请次数为空 */
	public static final String APPLY_COUNT_ISNULL = "10605";

	/** 未授信: 您尚未完成车主认证，认证后方可申请。 */
	public static final String DDSJ_LIMIT_INFO_1 = "10606";
	/** 授信审核中: 您的车主身份正在认证中，请稍后再来。 */
	public static final String DDSJ_LIMIT_INFO_2 = "10607";
	/** 逾期:您的车主身份已过期，请重新认证。 */
	public static final String DDSJ_LIMIT_INFO_3 = "10608";
	/** 您的额度已用完，但可以提额。 */
	public static final String DDSJ_LIMIT_INFO_4 = "10609";
	/** 已提额或最高额度 */
	public static final String DDSJ_LIMIT_INFO_5 = "10610";
	/** 滴滴司机贷暂不支持学生申请，请选择其他产品 */
	public static final String DDSJ_LIMIT_INFO_6 = "10611";

	/** 申请金额不能低于5000 */
	public static final String DDSJ_LIMIT_5000 = "10612";
	
	/** 申请金额不能大于10000 */
	public static final String DDSJ_LIMIT_10000 = "10613";
	
	/** 申请金额不能大于9000 */
	public static final String DDSJ_LIMIT_9000 = "10614";
	
	/** 申请金额必须在10000至50000之间 */
	public static final String DDSJ_LIMIT_1_5 = "10615";
	
	/** 额度信息为空,需要授信,并且不能提现 */
	public static final String DDSJ_LIMIT_BUTTON_1 = "10620";

	/** 该笔申请不存在 */
	public static final String DDSJ_CREDIT_IS_NULL = "10621";

	/** 该笔申请不在审核中 */
	public static final String DDSJ_CREDIT_NODE = "10622";
	/** 该笔申请取消异常 */
	public static final String DDSJ_APPLYTEMP_EXCEPTION = "10623";

	/** 保存设备信息异常 */
	public static final String DDSJ_CUST_DEVICE_EXCEPTION = "10624";
	/** 用户标示不能为空 */
	public static final String DDSJ_USER_UDID_IS_NULL = "10625";
	/** 类型不能为空 */
	public static final String DDSJ_TYPE_IS_NULL = "10626";
	/** 设备型号不能为空 */
	public static final String DDSJ_SB_TYPE_IS_NULL = "10627";
	/** 设备系统不能为空 */
	public static final String DDSJ_SB_SYSTEM_IS_NULL = "10628";
	/** 查询设备信息为空 */
	public static final String DDSJ_DEVICE_IS_NULL = "10629";
	/** 查询设备信息异常 */
	public static final String DDSJ_QUERY_DEVICE_EXCEPTION = "10630";
	/** 保存我的消息异常 */
	public static final String DDSJ_MY_MESSAGE_IS_NULL = "10631";
	/** 消息code不能为空 */
	public static final String DDSJ_CODE_IS_NULL = "10632";

	/** 配偶姓名不能和紧急联系人或直系亲属姓名重复 */
	public static final String COLLAR_INFO_RE = "10633";
	/** 配偶手机号不能和紧急联系人手机号或直系亲属手机号重复 */
	public static final String COLLAR_INFO_RE_MOBIEL = "10634";
	/** 授信订单更新异常 */
	public static final String DDSJ_CREDIT_UPDATE_EXCEPTION = "10635";
	/** 授信额度状态更新异常 */
	public static final String DDSJ_LIMIT_UPDATE_EXCEPTION = "10636";

	/** 不符合授信条件 */
	public static final String CHECK_DDSJ_CREDIT_EXCEPTION = "10637";

	/** 不符合提额条件 */
	public static final String CHECK_DDSJ_LIMIT_EXCEPTION = "10638";
	/** 人脸识别异常，请稍后再试 */
	public static final String CHECK_FACE_VAL_EXCEPTION = "10640";
	/** 人脸识别结果为空 */
	public static final String CHECK_FACE_WHICHPART_EXCEPTION = "10641";
	/** 嗨女神仅限女性申请 */
	public static final String HINS_SEX_IS_GIRL = "10639";
	
	/** 请选择正确的识别机构 */
	public static final String CHECK_FACE_WHICHPART_NULL= "10642";
	/**请选择区*/
	public static final String AREA_NOTNULL = "10643";
	/**请填写详细地址*/
	public static final String DETAIL_ADDRESS = "10644";
	/**收货地址id不能为空*/
	public static final String ADDRESS_ID_ISNULL = "10645";
	

	/** 公积金外卖记录保存异常 */
	public static final String CHECK_GJJ_ISORNOT_NULL= "10646";
	
	/** 请选择类型，公积金或者外卖 */
	public static final String CHECK_GJJ_WM_NULL= "10647";
	
	/** 公积金授权记录保存异常 */
	public static final String CHECK_GJJ_RECORD_EXCEPTION= "10648";
	/** 请选择是否做公积金 */
	public static final String CHECK_GJJ_ISNULL= "10649";
	////抱歉您的人脸识别已失败多次，今日识别已达上限，请明日再来申请
	public static final String CHECK_FACE_INOT_SUPPORT="10650";
	//秒贷和滴滴贷3期不能申请
	public static final String CHECK_H5_CANNOT_APPLY="10651";
	//秒贷和滴滴贷学生不能申请
	public static final String CHECK_MDDD_KHL1_APPLY="10652";
	//你还不符vip12期标准,请选择3期!
	public static final String CHECK_VIPD_12_APPLY="10653";
	
	/** 嗨钱来-认证ID不能为空 */
	public static final String AUTHID_ERROR_ISNULL= "50001";
	
	/** 嗨钱来-临时订单号不能为空 */
	public static final String TEMPAPPNO_ERROR_ISNULL= "50002";
	
	/** 嗨钱来-已经认证过请勿重复提交认证*/
	public static final String EXISTS_AUTHENTICATION= "50003";
	
	//滴滴司机账号不能为空
	public static final String DDSJ_RESERVER_NUMBER_ISNULL="10654";
	
	//滴滴司机密码不能为空
	public static final String DDSJ_RESERVER_PASSWORD_ISNULL="10655";

	//滴答贷正在调整，敬请持续关注！
	public static final String CHECK_LDDD_BEING_ADJUSTED="10656";
	
	//不支持该 贷款期限(对不起，该期额度已被抢光哦！请选择3个月借款期限！)
	public static final String CHECK_NO_SUPPORORT_PER="10657";
	
	//精英分期正在调整(对不起，精英分期正在调整，敬请持续关注！)
	public static final String CHECK_JYFQ_BEING_ADJUSTED="10658";
	
	
	/** 嗨女神的提现的额度累计合同金额大于等于1000，不能申请其他现金类产品（滴答贷，嗨秒贷，嗨钱来）*/
	public static final String IS_DISABLE_MONEY= "50004";
	
	/** 手机运营商标志错误或者为空*/
	public static final String OPER_FLAG_ERROR = "50504";
	
	/** 学信网标志错误或者为空*/
	public static final String XUEXIN_FLAG_ERROR = "50505";
	
	/** 修改真实姓名异常*/
	public static final String UPDATE_RELANAME_EXCEPTION = "50506";
	/** 单位行业不能为空*/
	public static final String UNIT_PROPERTIES_ISNULL = "50507";
	
	/** 申请金额不能大于3000 */
	public static final String MDCP_LIMIT_3000 = "10714";
	
	/** 认证项不能为空 */
	public static final String AUTNEN_IS_NOTNULL = "10715";
	/** 认证状态不能为空 */
	public static final String AUTNENSTATUS_IS_NOTNULL = "10716";
	
	/** 认证状态更新失败 */
	public static final String AUTNENSTATUS_SAVE_EXCEPTION = "10717";
	
	/**司机认证 类型不能为空**/
	public static final String DRIVER_AUTH_TYPE_NOTNULL="666";
	
	/**司机认证 暂不支持**/
	public static final String DRIVER_AUTH_TYPE_NOTSUPPER="667";
}
