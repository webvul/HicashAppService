package com.hengyuan.hicash.constant;


/**
 * 接口返回消息集合 
 * 
 * @author Cary.Liu
 * @create date 2014-07-17
 *
 */
public class ResultMsgs {
	/** UUID为空 */
	public static final String UUIDNULL_MSG = "UUID为空";
	
	/** UUID非法字符 */
	public static final String UUIDILLEGAL_MSG = "UUID非法字符";
	
	/** 注册服务器处理异常 */
	public static final String REGISTER_EXCEPTION_MSG = "注册服务处理异常";

	/** 用户名已经存在 */
	public static final String REGISTER_EXIST_MSG = "用户名已经存在";

	/** 创建用户异常 */
	public static final String REGISTER_USER_EXCEPTION_MSG = "创建用户异常 ";

	/** 用户授权异常 */
	public static final String REGISTER_AUTH_EXCEPTION_MSG = "用户授权异常";

	/** 用户信息创建异常 */
	public static final String REGISTER_CUST_EXCEPTION_MSG = "用户信息创建异常";

	/** 账户信息创建异常 */
	public static final String REGISTER_ACCOUNT_EXCEPTION_MSG = "账户信息创建异常";
	
	/** 用户名不存在 */
	public static final String REGISTER_NOT_EXIST_MSG = "用户名不存在";
	
	/** 用户查询异常 */
	public static final String USER_QUERY_EXCEPTION_MSG = "用户查询异常";
	
	
	/** 用户名为空 */
	public static final String USER_ERROR_ISNULL_MSG = "用户名为空";
	
	/** 用户名格式错误 */
	public static final String USER_NAME_ERROR_MSG = "用户名格式错误";
	
	/** 密码为空 */
	public static final String USER_PASS_ERROR_ISNULL_MSG = "密码为空";
	
	/** 密码长度不够 */
	public static final String USER_PASS_ERROR_LENGTH_MSG = "密码长度不够";
	
	/** 两次输入的密码不一致 */
	public static final String USER_CONPASS_ERROR_MSG = "两次输入的密码不一致";
	
	/** 输入的手机号码为空 */
	public static final String USER_PHONE_ERROR_ISNULL_MSG = "输入的手机号码为空";
	
	/** 输入的手机号码格式错误 */
	public static final String USER_PHONE_ERROR_MSG = "输入的手机号码格式错误";
	
	/** 用户名长度不正确(6-20) */
	public static final String USER_NAME_ERROR_LENGTH_MSG = "用户名长度不正确(6-20)";
	
	/** 用户名包含中文 */
	public static final String USER_NAME_ERROR_CANTCHINESE_MSG = "用户名包含中文";
	
	/** 用户名必须6-20位数字、字母或下划线组合且不包含非法字符 */
	public static final String USER_NAME_ERROR_CANTCHAR_MSG = "用户名必须6-20位数字、字母或下划线组合且不包含非法字符";
	
	/** 密码或确认密码不能为中文 */
	public static final String PASS_WORD_CANTCHAR_MSG = "密码或确认密码不能为中文";
	
	/** 确认密码为空 */
	public static final String DOUBLE_PASS_ERROR_ISNULL_MSG = "确认密码为空";
	
	/** 密码必须8-20位数字、字母或下划线组合且不包含非法字符 */
	public static final String USER_PASS_ERROR_MSG = "密码必须8-20位数字、字母或下划线组合且不包含非法字符";
	
	
	
	/* 用户登录 */

	/** 用户登录异常 */
	public static final String USER_LOGIN_ERROR_MSG = "用户登录异常";

	/** 用户名不存在 */
	public static final String USER_EXIST_FALSE_MSG = "用户名不存在";

	/** 原始密码输入有误 */
	public static final String USER_OLDPASSWORD_INPUT_FAIL_MSG = "原始密码输入有误";

	/** 该用户连续登录没有成功,账户被锁定 */
	public static final String USER_LOCKED_MSG = "该用户连续登录没有成功,账户被锁定";
	
	/** 用户名为空 */
	public static final String USERNAME_IS_NULL_MSG = "用户名为空";
	
	/** 密码为空 */
	public static final String USERPASS_IS_NULL_MSG = "密码为空 ";
	
	
	/* 用户注册信息完善 */

	/** 用户信息完善服务器处理异常 */
	public static final String SAVE_REGISTER_INFO_EXCEPTION_MSG = "用户信息完善服务器处理异常";
	
	/** 需要的账务服务调用地址未找到 */
	public static final String HTTP_URL_REMOTE_EXCEPTION_MSG = "需要的账务服务调用地址未找到";
	
	/** 调用用户授信额度远程服务异常 */
	public static final String USER_AMOUNT_HTTP_ERROR_MSG = "调用用户授信额度远程服务异常";
	
	/** 账务服务参数返回处理错误 */
	public static final String USER_AMOUNT_IO_ERROR_MSG = "账务服务参数返回处理错误";

	/** 用户注册信息保存失败 */
	public static final String USER_REGIST_FAIL_MSG = "用户注册信息保存失败";
	
	/** 身份证已经存在 */
	public static final String USER_REGIST_IDENTITY_ONLY_MSG = "身份证已经存在";
	
	
	/** 客户类型不能为空 */
	public static final String CUST_TYPE_ISNULL_MSG = "客户类型不能为空";
	
	/** 学历选择为空 */
	public static final String CUST_EDUCATION_ISNULL_MSG = "学历选择为空";
	
	/** 月收入不能为空 */
	public static final String CUST_MOUTHINCOME_ISNULL_MSG = "月收入不能为空";
	
	/** 月收入输入错误 */
	public static final String CUST_MOUTHINCOME_ERROR_MSG = "月收入输入错误";
	
	/** 单位不能为空 */
	public static final String CUST_UNITNAME_ISNULL_MSG = "单位不能为空 ";
	
	/** 单位长度输入错误 */
	public static final String CUST_UNITNAME_ERROR_MSG = "单位长度输入错误";
	
	/** 邮箱不能为空 */
	public static final String CUST_EMAIL_ISNULL_MSG = "邮箱不能为空";
	
	/** 邮箱格式错误 */
	public static final String CUST_EMAIL_ERROR_MSG = "邮箱格式错误";
	
	/** 身份证不能为空 */
	public static final String CUST_IDENTITYNO_ISNULL_MSG = "身份证不能为空";
	
	/** 身份证格式错误(15位或者18位正确的身份证格式) */
	public static final String CUST_IDENTITYNO_ERROR_MSG = "身份证格式错误(15位或者18位正确的身份证格式)";
	
	/** 真实姓名不能为空 */
	public static final String CUST_REALNAME_ISNULL_MSG = "真实姓名不能为空";
	
	/** 真实姓名输入非法(只能为1-20位中文或者'.'、'·') */
	public static final String CUST_REALNAME_ERROR_MSG = "真实姓名输入非法(只能为1-20位中文或者'.'、'·')";
	
	/** 学校不能为空*/
	public static final String CUST_SCHOOL_ISNULL_MSG = "学校不能为空";
	
	/** 学号不能为空*/
	public static final String CUST_STUID_ISNULL_MSG = "学号不能为空";
	
	/** 学号输入非法*/
	public static final String CUST_STUID_ERROR_MSG = "学号输入非法";
	
	/** 邀请码错误，非法字符*/
	public static final String INVITATION_ERROR_MSG = "邀请码错误，非法字符";
	
	/** 邀请码长度错误*/
	public static final String INVITATION_LEN_ERROR_MSG = "邀请码长度错误";
	
	/** 学校长度不正确(0-50)*/
	public static final String CUST_SCHOOL_NAME_LENGTH_MSG = "学校长度不正确(0-50)";
	
	/** 学号长度不正确(0-18)*/
	public static final String CUST_STUID_LENGTH_MSG = "学号长度不正确(0-18)";
	
	/** 月收入非法字符 */
	public static final String CUST_MOUTHINCOME_FALSE_MSG = "月收入非法字符";
	
	/** 月收入长度不正确 */
	public static final String CUST_MOUTHINCOME_LENGTH_MSG = "月收入长度不正确";
	
	/** 学校名称输入非法(只能输入中文学校名)*/
	public static final String CUST_SCHOOL_FALSE_MSG = "学校名称输入非法(只能输入中文学校名)";
	
	/** 学历非法(1-5位字母数字) */
	public static final String CUST_EDUCATION_LENGTH_MSG = "学历非法(1-5位字母数字)";
	

	

	/* 身份证唯一验证 */

	/** 身份证唯一验证异常 */
	public static final String USER_IDENTITY_ERROR_MSG = "身份证唯一验证异常";
	
	/** 身份证已经存在 */
	public static final String USER_IDENTITY_ONLY_MSG = "身份证已经存在";
	
	/** 身份证不能为空 */
	public static final String USER_IDENTITY_NOTNULL_MSG = "身份证不能为空";
	
	/** 身份证格式错误(15位或者18位正确的身份证格式) */
	public static final String USER_IDENTITY_FALSE_MSG = "身份证格式错误(15位或者18位正确的身份证格式)";
	
	
	
	
	
	/* 学生-基本资料保存 */
	
	/** 学生基本资料保存异常 */
	public static final String STU_INFO_EXCEPTION_MSG = "学生基本资料保存异常";
	
	/** 学生基本资料保存失败 */
	public static final String STU_INFO_SAVE_EXCEPTION_MSG = "学生基本资料保存失败";
	
	/** 缺少客户类型 */
	public static final String USER_CUSTTYPE_NULL_INPUT_MSG = "缺少客户类型";
	
	/** 客户类型输入非法 */
	public static final String USER_CUSTTYPE_FALSE_MSG = "客户类型输入非法";
	
	/** 固定电话输入格式错误 */
	public static final String USER_TEL_FALSE_MSG = "固定电话输入格式错误";
	
	
	
	/* 学生-保存客户学校信息 */
	
	/** 学校资料保存异常 */
	public static final String STU_SHCOOL_INFO_EXCEPTION_MSG = "学校资料保存异常";
	
	/** 保存学校信息异常 */
	public static final String STU_UNIVERSITY_INFO_EXCEPTION_MSG = "保存学校信息异常";
	
	/** 保存客户学校信息（单表）异常 */
	public static final String STU_UNIVERSITY_EXCEPTION_MSG = "保存客户学校信息（单表）异常";
	
	/** 更新客户学校关系异常 */
	public static final String STU_UPDATE_SCHOOL_EXCEPTION_MSG = "更新客户学校关系异常";
	
	/** 学校名称不能为空 */
	public static final String STU_UPDATE_SCHOOL_NAME_NOTNULL_MSG = "学校名称不能为空";
	
	/** 学制不能为空 */
	public static final String STU_UPDATE_SCHOOL_SYSTEM_NOTNULL_MSG = "学制不能为空";
	
	/** 学历不能为空 */
	public static final String STU_UPDATE_SCHOOL_EDUCATION_NOTNULL_MSG = "学历不能为空";
	
	/** 入学时间不能为空 */
	public static final String STU_UPDATE_SCHOOL_STUDYTIME_NOTNULL_MSG = "入学时间不能为空";
	
	/** 年级不能为空 */
	public static final String STU_UPDATE_SCHOOL_GRADE_NOTNULL_MSG = "年级不能为空";
	
	/** 学号不能为空 */
	public static final String STU_UPDATE_SCHOOL_STUID_NOTNULL_MSG = "学号不能为空";
	
	/**所在院系不能为空 */
	public static final String STU_UPDATE_SCHOOL_DPM_NOTNULL_MSG = "所在院系不能为空";
	
	/** 专业不能为空 */
	public static final String STU_UPDATE_SCHOOL_MAJOR_NOTNULL_MSG = "专业不能为空";
	
	/** 学号输入非法(只能是数字或者字母) */
	public static final String STU_UPDATE_SCHOOL_STUID_FALSE_MSG = "学号输入非法(只能是数字或者字母)";
	
	/**所在院系输入非法(只能输入中文) */
	public static final String STU_UPDATE_SCHOOL_DPM_FALSE_MSG = "所在院系输入非法(只能输入中文)";
	
	/** 专业输入非法(只能输入中文) */
	public static final String STU_UPDATE_SCHOOL_MAJOR_FALSE_MSG = "专业输入非法(只能输入中文)";
	
	/** 学校名称输入非法(只能输入中文) */
	public static final String STU_UPDATE_SCHOOL_NAME_FALSE_MSG = "学校名称输入非法(只能输入中文)";
	
	/** 学校名称长度不符合 */
	public static final String STU_UPDATE_SCHOOL_NAME_LENGTH_MSG = "学校名称长度不符合";
	
	/** 学号长度不正确 */
	public static final String STU_UPDATE_SCHOOL_STUID_LENGTH_MSG = "学号长度不正确";
	
	/** 专业长度不正确 */
	public static final String STU_UPDATE_SCHOOL_MAJOR_LENGTH_MSG = "专业长度不正确";
	
	/**所在院系长度为1-20 */
	public static final String STU_UPDATE_SCHOOL_DPM_LENGTH_MSG = "所在院系长度为1-20";
	
	/** 学制非法(1-5字母数字) */
	public static final String STU_UPDATE_SCHOOL_SYSTEM_LENGTH_MSG = "学制非法(1-5字母数字)";
	
	/** 学历非法(1-5字母数字) */
	public static final String STU_UPDATE_SCHOOL_EDUCATION_LENGTH_MSG = "学历非法(1-5字母数字)";
	
	/** 年级非法(1-5字母数字) */
	public static final String STU_UPDATE_SCHOOL_GRADE_LENGTH_MSG = "年级非法(1-5字母数字)";
	
	
	
	
	/* 学生-保存客户联系信息 */
	
	/** 保存客户联系信息异常 */
	public static final String CONTACT_EXCEPTION_MSG = "保存客户联系信息异常";
	
	/** 更新客户联系信息失败 */
	public static final String UPDATE_CONTACT_EXCEPTION_MSG = "更新客户联系信息失败";
	
	/** 新增客户联系信息失败 */
	public static final String SAVE_CONTACT_EXCEPTION_MSG = "新增客户联系信息失败";
	
	/** 请选择完整的学校地址区域 */
	public static final String STU_CONTACT_SCHOOL_AREA_NOTNULL_MSG = "请选择完整的学校地址区域 ";
	
	/** 学校详细地址不能为空 */
	public static final String STU_CONTACT_SCHOOL_AREA_DETAIL_NOTNULL_MSG = "学校详细地址不能为空";
	
	/** 请选择完整的家庭地址区域 */
	public static final String STU_CONTACT_HOME_AREA_NOTNULL_MSG = "请选择完整的家庭地址区域";
	
	/** 家庭详细地址不能为空 */
	public static final String STU_CONTACT_HOME_AREA_DETAIL_NOTNULL_MSG = "家庭详细地址不能为空";
	
	/** 请选择完整的现居住地址区域 */
	public static final String STU_CONTACT_NOW_AREA_NOTNULL_MSG = "请选择完整的现居住地址区域";
	
	/** 现居住详细地址不能为空 */
	public static final String STU_CONTACT_NOW_AREA_DETAIL_NOTNULL_MSG = "现居住详细地址不能为空";
	
	/** 学校详细地址输入非法(不能包含特殊字符) */
	public static final String STU_CONTACT_SCHOOL_AREA_DETAIL_FALSE_MSG = "学校详细地址输入非法(不能包含特殊字符)";
	
	/** 家庭详细地址输入非法(不能包含特殊字符) */
	public static final String STU_CONTACT_HOME_AREA_DETAIL_FALSE_MSG = "家庭详细地址输入非法(不能包含特殊字符)";
	
	/** 现居住详细地址输入非法(不能包含特殊字符) */
	public static final String STU_CONTACT_NOW_AREA_DETAIL_FALSE_MSG = "现居住详细地址输入非法(不能包含特殊字符)";
	
	/** 学校详细地址长度不正确 */
	public static final String STU_CONTACT_SCHOOL_AREA_DETAIL_LENGTH_MSG = "学校详细地址长度不正确";
	
	/** 家庭详细地址长度不正确 */
	public static final String STU_CONTACT_HOME_AREA_DETAIL_LENGTH_MSG = "家庭详细地址长度不正确";
	
	/** 现居住详细地址长度不正确 */
	public static final String STU_CONTACT_NOW_AREA_DETAIL_LENGTH_MSG = "现居住详细地址长度不正确";
	
	/** 学校地址(省)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_SCHOOL_PROVINCE_FALSE_MSG = "学校地址(省)非法字符(只能位6位数字代码)";
	
	/** 学校地址(市)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_SCHOOL_CITY_FALSE_MSG = "学校地址(市)非法字符(只能位6位数字代码)";
	
	/** 学校地址(区)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_SCHOOL_AREA_FALSE_MSG = "学校地址(区)非法字符(只能位6位数字代码)";
	
	/** 家庭地址(省)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_HOME_PROVINCE_FALSE_MSG = "家庭地址(省)非法字符(只能位6位数字代码)";
	
	/** 家庭地址(市)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_HOME_CITY_FALSE_MSG = "家庭地址(市)非法字符(只能位6位数字代码)";
	
	/** 家庭地址(区)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_HOME_AREA_FALSE_MSG = "家庭地址(区)非法字符(只能位6位数字代码)";
	
	/** 现居住地址(省)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_NOW_PROVINCE_FALSE_MSG = "现居住地址(省)非法字符(只能位6位数字代码)";
	
	/** 现居住地址(市)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_NOW_CITY_FALSE_MSG = "现居住地址(市)非法字符(只能位6位数字代码)";
	
	/** 现居住地址(区)非法字符(只能位6位数字代码) */
	public static final String STU_CONTACT_NOW_AREA_FALSE_MSG = "现居住地址(区)非法字符(只能位6位数字代码)";
	
	
	
	/* 学生-保存客户联系人信息 */
	
	/** 保存客户联系人信息异常 */
	public static final String CONTACTS_EXCEPTION_MSG = "保存客户联系人信息异常";
	
	/** 更新客户联系人信息失败 */
	public static final String UPDATE_CONTACTS_EXCEPTION_MSG = "更新客户联系人信息失败";
	
	/** 新增客户联系人信息失败 */
	public static final String SAVE_CONTACTS_EXCEPTION_MSG = "新增客户联系人信息失败";
	
	/** 直系亲属姓名不能为空 */
	public static final String STU_CONTACTS_FAMILY_NAME_NOTNULL_MSG = "直系亲属姓名不能为空";
	
	/** 直系亲属关系不能为空 */
	public static final String STU_CONTACTS_FAMILY_RELATION_NOTNULL_MSG = "直系亲属关系不能为空";
	
	/** 直系亲属工作单位不能为空 */
	public static final String STU_CONTACTS_FAMILY_UNIT_NOTNULL_MSG = "直系亲属工作单位不能为空";
	
	/** 直系亲属手机不能为空 */
	public static final String STU_CONTACTS_FAMILY_PHONE_NOTNULL_MSG = "直系亲属手机不能为空";
	
	/** 直系亲属地址不能为空 */
	public static final String STU_CONTACTS_FAMILY_ADDRESS_NOTNULL_MSG = "直系亲属地址不能为空";
	
	/** 紧急联系人姓名不能为空 */
	public static final String STU_CONTACTS_CONTACT_NAME_NOTNULL_MSG = "紧急联系人姓名不能为空";
	
	/** 紧急联系人关系不能为空 */
	public static final String STU_CONTACTS_CONTACT_RELATION_NOTNULL_MSG = "紧急联系人关系不能为空";
	
	/** 紧急联系人工作单位不能为空 */
	public static final String STU_CONTACTS_CONTACT_UNIT_NOTNULL_MSG = "紧急联系人工作单位不能为空";
	
	/** 紧急联系人手机不能为空 */
	public static final String STU_CONTACTS_CONTACT_PHONE_NOTNULL_MSG = "紧急联系人手机不能为空 ";
	
	/** 紧急联系人地址不能为空 */
	public static final String STU_CONTACTS_CONTACT_ADDRESS_NOTNULL_MSG = "紧急联系人地址不能为空";
	
	/** 直系亲属姓名输入非法(只能输入中文) */
	public static final String STU_CONTACTS_FAMILY_NAME_FALSE_MSG = "直系亲属姓名输入非法(只能输入中文)";
	
	/** 直系亲属工作单位输入非法字符 */
	public static final String STU_CONTACTS_FAMILY_UNIT_FALSE_MSG = "直系亲属工作单位输入非法字符";
	
	/** 直系亲属手机输入非法 */
	public static final String STU_CONTACTS_FAMILY_PHONE_FALSE_MSG = "直系亲属手机输入非法";
	
	/** 直系亲属地址非法字符 */
	public static final String STU_CONTACTS_FAMILY_ADDRESS_FALSE_MSG = "直系亲属地址非法字符";
	
	/** 紧急联系人姓名非法字符(只能为中文) */
	public static final String STU_CONTACTS_CONTACT_NAME_FALSE_MSG = "紧急联系人姓名非法字符(只能为中文)";
	
	/** 紧急联系人工作单位输入非法字符 */
	public static final String STU_CONTACTS_CONTACT_UNIT_FALSE_MSG = "紧急联系人工作单位输入非法字符";
	
	/** 紧急联系人手机输入非法*/
	public static final String STU_CONTACTS_CONTACT_PHONE_FALSE_MSG = "紧急联系人手机输入非法";
	
	/** 紧急联系人地址输入非法 */
	public static final String STU_CONTACTS_CONTACT_ADDRESS_FALSE_MSG = "紧急联系人地址输入非法";
	
	/** 紧急联系人(姓名)输入长度错误 */
	public static final String STU_CONTACTS_CONTACT_NAME_LENGTH_MSG = "紧急联系人(姓名)输入长度错误";
	
	/** 直系联系人(姓名)输入长度错误 */
	public static final String STU_CONTACTS_FAMILY_NAME_LENGTH_MSG = "直系联系人(姓名)输入长度错误";
	
	/** 直系亲属工作单位长度不对 */
	public static final String STU_CONTACTS_FAMILY_UNIT_LENGTH_MSG = "直系亲属工作单位长度不对";
	
	/** 紧急联系人(工作单位)长度不对 */
	public static final String STU_CONTACTS_CONTACTS_UNIT_LENGTH_MSG = "紧急联系人(工作单位)长度不对";
	
	/** 直系亲属地址非法长度 */
	public static final String STU_CONTACTS_FAMILY_ADDRESS_LENGTH_MSG = "直系亲属地址非法长度";
	
	/** 紧急联系人地址非法长度 */
	public static final String STU_CONTACTS_CONTACT_ADDRESS_LENGTH_MSG = "紧急联系人地址非法长度";
	
	/** 紧急联系人姓名不能与直系亲属姓名相同 */
	public static final String STU_CONTACTS_NAME_EQ_FALSE_MSG = "紧急联系人姓名不能与直系亲属姓名相同";
	
	/** 紧急联系人关系不能与直系亲属关系相同 */
	public static final String STU_CONTACTS_RELATION_EQ_FALSE_MSG = "紧急联系人关系不能与直系亲属关系相同";
	
	/** 紧急联系人手机不能与直系亲属手机相同 */
	public static final String STU_CONTACTS_PHONE_EQ_FALSE_MSG = "紧急联系人手机不能与直系亲属手机相同";
	
	/** 直系亲属关系非法(1-5字母数字) */
	public static final String STU_CONTACTS_FAMILY_RELATION_FALSE_MSG = "直系亲属关系非法(1-5字母数字)";
	
	/** 紧急联系人关系非法(1-5字母数字) */
	public static final String STU_CONTACTS_RELATION_FALSE_MSG= "紧急联系人关系非法(1-5字母数字)";
	
	
	
	/* 白领-基本资料保存 */
	
	/** 基本资料保存异常 */
	public static final String COLLAR_INFO_EXCEPTION_MSG = "基本资料保存异常";
	
	/** 基本资料保存失败 */
	public static final String COLLAR_INFO_SAVE_EXCEPTION_MSG = "基本资料保存失败";
	
	/** 毕业院校不能为空 */
	public static final String COLLAR_INFO_SCHOOLNAME_NOTNULL_MSG = "毕业院校不能为空";
	
	/** 毕业时间不能为空 */
	public static final String COLLAR_INFO_SCHOOLENDTIME_NOTNULL_MSG = "毕业时间不能为空";
	
	/** 最高学历不能为空 */
	public static final String COLLAR_INFO_EDUCATION_NOTNULL_MSG = "最高学历不能为空";
	
	/** 学制不能为空 */
	public static final String COLLAR_INFO_SYSTEM_NOTNULL_MSG = "学制不能为空";
	
	/** 婚姻状况不能为空 */
	public static final String COLLAR_INFO_MATRIMONY_NOTNULL_MSG = "婚姻状况不能为空";
	
	/** 住房状况不能为空 */
	public static final String COLLAR_INFO_HOUSING_NOTNULL_MSG = "住房状况不能为空";
	
	/** 毕业院校输入非法(只能输入中文学校) */
	public static final String COLLAR_INFO_SCHOOLNAME_FALSE_MSG = "毕业院校输入非法(只能输入中文学校)";
	
	/** 毕业院校长度不正确 */
	public static final String COLLAR_INFO_SCHOOLNAME_LENGTH_MSG = "毕业院校长度不正确";
	
	/** 学制非法(1-5字母数字) */
	public static final String COLLAR_INFO_SYSTEM_FALSE_MSG = "学制非法(1-5字母数字)";
	
	/** 婚姻状况非法(1-5字母数字) */
	public static final String COLLAR_INFO_MATRIMONY_FALSE_MSG = "婚姻状况非法(1-5字母数字)";
	
	/** 住房状况非法(1-5字母数字) */
	public static final String COLLAR_INFO_HOUSING_FALSE_MSG = "住房状况非法(1-5字母数字)";
	
	
	
	/* 白领-单位信息保存 */
	
	/** 单位信息保存处理异常 */
	public static final String COLLAR_UNIT_EXCEPTION_MSG = "单位信息保存处理异常";
	
	/** 客户信息保存异常 */
	public static final String COLLAR_UNIT_INFO_EXCEPTION_MSG = "客户信息保存异常";
	
	/** 单位信息更新异常 */
	public static final String COLLAR_UNIT_UPDATE_EXCEPTION_MSG = "单位信息更新异常";
	
	/** 单位信息创建异常 */
	public static final String COLLAR_UNIT_SAVE_EXCEPTION_MSG = "单位信息创建异常";
	
	/** 单位名称不能为空 */
	public static final String COLLAR_UNIT_NAME_NOTNULL_MSG = "单位名称不能为空";
	
	/** 行业类别不能为空 */
	public static final String COLLAR_UNIT_TYPE_NOTNULL_MSG = "行业类别不能为空";

	/** 单位性质不能为空 */
	public static final String COLLAR_UNIT_ATTR_NOTNULL_MSG = "单位性质不能为空";
	
	/** 请选择完整的单位入职时间 */
	public static final String COLLAR_UNIT_STARTDATE_NOTNULL_MSG = "请选择完整的单位入职时间";
	
	/** 单位总工作年限不能为空 */
	public static final String COLLAR_UNIT_WORKTIME_NOTNULL_MSG = "单位总工作年限不能为空";
	
	/** 单位入职部门不能为空 */
	public static final String COLLAR_UNIT_DPM_NOTNULL_MSG = "单位入职部门不能为空";
	
	/** 单位入职务不能为空 */
	public static final String COLLAR_UNIT_JOBTITLE_NOTNULL_MSG = "单位入职务不能为空";
	
	/** 单位名称输入非法(不能包含特殊字符) */
	public static final String COLLAR_UNIT_NAME_FALSE_MSG = "单位名称输入非法(不能包含特殊字符)";
	
	/** 单位入职部门输入非法(不能包含特殊字符) */
	public static final String COLLAR_UNIT_DPM_FALSE_MSG = "单位入职部门输入非法(不能包含特殊字符)";
	
	/** 单位入职务输入非法(不能包含特殊字符) */
	public static final String COLLAR_UNIT_JOBTITLE_FALSE_MSG = "单位入职务输入非法(不能包含特殊字符)";
	
	/** 工号输入非法(只能是数字或字母组合) */
	public static final String COLLAR_UNIT_JOBID_FALSE_MSG = "工号输入非法(只能是数字或字母组合)";
	
	/** 单位名称长度不正确 */
	public static final String COLLAR_UNIT_NAME_LENGTH_MSG = "单位名称长度不正确";
	
	/** 单位入职部门长度不正确 */
	public static final String COLLAR_UNIT_DPM_LENGTH_MSG = "单位入职部门长度不正确";
	
	/** 单位入职务长度不正确 */
	public static final String COLLAR_UNIT_JOBTITLE_LENGTH_MSG = "单位入职务长度不正确 ";
	
	/** 工号输入长度不正确 */
	public static final String COLLAR_UNIT_JOBID_LENGTH_MSG = "工号输入长度不正确";
	
	/** 固定电话输入格式错误 */
	public static final String COLLAR_UNIT_TEL_FALSE_MSG = "固定电话输入格式错误";
	
	/** 行业类别非法(1-5字母数字) */
	public static final String COLLAR_UNIT_TYPE_FALSE_MSG = "行业类别非法(1-5字母数字)";
	
	/** 单位性质非法(1-5字母数字) */
	public static final String COLLAR_UNIT_ATTR_FALSE_MSG = "单位性质非法(1-5字母数字)";
	
	
	
	/* 白领-资信信息保存 */
	
	/** 资信信息处理异常 */
	public static final String COLLAR_CREDIT_EXCEPTION_MSG = "资信信息处理异常";
	
	/** 资信信息更新异常 */
	public static final String COLLAR_CREDIT_UPDATE_EXCEPTION_MSG = "资信信息更新异常";
	
	/** 资信信息保存异常 */
	public static final String COLLAR_CREDIT_SAVE_EXCEPTION_MSG = "资信信息保存异常";
	
	
	/** 当前月收入非法字符 */
	public static final String COLLAR_MONTH_INCOME_EXCEPTION_MSG = "当前月收入非法字符";
	
	/** 当前月消费非法字符 */
	public static final String COLLAR_MONTH_CONSUMPTION_EXCEPTION_MSG = "当前月消费非法字符";
	
	/** 房租月供非法字符 */
	public static final String COLLAR_MONTH_HOUSERENT_EXCEPTION_MSG = "房租月供非法字符";
	
	/** 贷款总额非法字符 */
	public static final String COLLAR_LOANTOTAL_EXCEPTION_MSG = "贷款总额非法字符";
	
	/** 贷款数量非法字符 */
	public static final String COLLAR_LOANCOUNT_EXCEPTION_MSG = "贷款数量非法字符";
	
	/** 贷款月供非法字符 */
	public static final String COLLAR_LOANPAYMENTS_EXCEPTION_MSG = "贷款月供非法字符";
	
	/** 信用卡数量非法字符 */
	public static final String COLLAR_CREDITCARDCOUNT_EXCEPTION_MSG = "信用卡数量非法字符";
	
	/** 信用卡最高额度非法字符 */
	public static final String COLLAR_CREDITCARDLIMIT_EXCEPTION_MSG = "信用卡最高额度非法字符";
	
	/** 信用卡总额度非法字符 */
	public static final String COLLAR_CREDITCARDTOTAL_EXCEPTION_MSG = "信用卡总额度非法字符";
	
	/** 当前月收入不能为空 */
	public static final String COLLAR_MONTH_INCOME_NOTNULL_MSG = "当前月收入不能为空";
	
	/** 当前月消费不能为空 */
	public static final String COLLAR_MONTH_CONSUMPTION_NOTNULL_MSG = "当前月消费不能为空";
	
	/** 当前月收入长度不正确 */
	public static final String COLLAR_MONTH_INCOME_LENGTH_MSG = "当前月收入长度不正确";
	
	/** 当前月消费长度不正确 */
	public static final String COLLAR_MONTH_CONSUMPTION_LENGTH_MSG = "当前月消费长度不正确";
	
	/** 房租月供长度不正确 */
	public static final String COLLAR_MONTH_HOUSERENT_LENGTH_MSG = "房租月供长度不正确";
	
	/** 贷款总额长度不正确 */
	public static final String COLLAR_LOANTOTAL_LENGTH_MSG = "贷款总额长度不正确";
	
	/** 贷款数量长度不正确 */
	public static final String COLLAR_LOANCOUNT_LENGTH_MSG = "贷款数量长度不正确";
	
	/** 贷款月供长度不正确 */
	public static final String COLLAR_LOANPAYMENTS_LENGTH_MSG = "贷款月供长度不正确";
	
	/** 信用卡数量长度不正确 */
	public static final String COLLAR_CREDITCARDCOUNT_LENGTH_MSG = "信用卡数量长度不正确";
	
	/** 信用卡最高额度长度不正确 */
	public static final String COLLAR_CREDITCARDLIMIT_LENGTH_MSG = "信用卡最高额度长度不正确";
	
	/** 信用卡总额度长度不正确 */
	public static final String COLLAR_CREDITCARDTOTAL_LENGTH_MSG = "信用卡总额度长度不正确";
	
	
	/* 白领-联系信息段保存 */
	
	/** 白领联系信息处理异常 */
	public static final String COLLAR_CONTACT_EXCEPTION_MSG = "白领联系信息处理异常";
	
	/** 白领联系信息更新异常 */
	public static final String COLLAR_CONTACT_UPDATE_EXCEPTION_MSG = "白领联系信息更新异常";
	
	/** 白领联系信息保存异常 */
	public static final String COLLAR_CONTACT_SAVE_EXCEPTION_MSG = "白领联系信息保存异常";
	
	/** 请选择完整的户籍地址区域 */
	public static final String COLLAR_CONTACT_REGIS_AREA_NOTNULL_MSG = "请选择完整的户籍地址区域";
	
	/** 户籍详细地址不能为空 */
	public static final String COLLAR_CONTACT_REGISL_DETAIL_NOTNUL_MSG = "户籍详细地址不能为空";
	
	/** 请选择完整的单位地址区域 */
	public static final String COLLAR_CONTACT_UNIT_AREA_NOTNULL_MSG = "请选择完整的单位地址区域 ";
	
	/** 单位详细地址不能为空 */
	public static final String COLLAR_CONTACT_UNIT_DETAIL_NOTNUL_MSG = "单位详细地址不能为空";
	
	/** 户籍详细地址输入非法 */
	public static final String COLLAR_CONTACT_REGISL_DETAIL_FALSE_MSG = "户籍详细地址输入非法";
	
	/** 单位详细地址输入非法 */
	public static final String COLLAR_CONTACT_UNIT_DETAIL_FALSE_MSG = "单位详细地址输入非法";
	
	/** 户籍详细地址长度不正确 */
	public static final String COLLAR_CONTACT_REGISL_DETAIL_LENGTH_MSG = "户籍详细地址长度不正确 ";
	
	/** 单位详细地址长度不正确 */
	public static final String COLLAR_CONTACT_UNIT_DETAIL_LENGTH_MSG = "单位详细地址长度不正确";
	
	/** 请选择完整的家庭地址区域 */
	public static final String COLLAR_CONTACT_HOME_AREA_NOTNULL_MSG = "请选择完整的家庭地址区域";
	
	/** 家庭详细地址不能为空 */
	public static final String COLLAR_CONTACT_HOME_DETAIL_NOTNUL_MSG = "家庭详细地址不能为空";
	
	/** 家庭详细地址输入非法 */
	public static final String COLLAR_CONTACT_HOME_DETAIL_FALSE_MSG = "家庭详细地址输入非法";
	
	/** 家庭详细地址长度不正确 */
	public static final String COLLAR_CONTACT_HOME_DETAIL_LENGTH_MSG = "家庭详细地址长度不正确";
	
	/** 请选择完整的现居地址区域 */
	public static final String COLLAR_CONTACT_NOWHOME_AREA_NOTNULL_MSG = "请选择完整的现居地址区域";
	
	/** 现居详细地址不能为空 */
	public static final String COLLAR_CONTACT_NOWHOME_DETAIL_NOTNUL_MSG = "现居详细地址不能为空";
	
	/** 现居详细地址输入非法 */
	public static final String COLLAR_CONTACT_NOWHOME_DETAIL_FALSE_MSG = "现居详细地址输入非法";
	
	/** 现居详细地址长度不正确 */
	public static final String COLLAR_CONTACT_NOWHOME_DETAIL_LENGTH_MSG = "现居详细地址长度不正确";
	
	/** 户籍地址(省)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_REGIS_PROVINCE_FALSE_MSG = "户籍地址(省)非法字符(只能为6位数字代码)";
	
	/** 户籍地址(市)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_REGIS_CITY_FALSE_MSG = "户籍地址(市)非法字符(只能为6位数字代码)";
	
	/** 户籍地址(区)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_REGIS_AREA_FALSE_MSG = "户籍地址(区)非法字符(只能为6位数字代码)";
	
	/** 单位地址(省)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_UNIT_PROVINCE_FALSE_MSG = "单位地址(省)非法字符(只能为6位数字代码)";
	
	/** 单位地址(市)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_UNIT_CITY_FALSE_MSG = "单位地址(市)非法字符(只能为6位数字代码)";
	
	/** 单位地址(区)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_UNIT_AREA_FALSE_MSG = "单位地址(区)非法字符(只能为6位数字代码)";
	
	/** 家庭地址(省)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_HOME_PROVINCE_FALSE_MSG = "家庭地址(省)非法字符(只能为6位数字代码)";
	
	/** 家庭地址(市)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_HOME_CITY_FALSE_MSG = "家庭地址(市)非法字符(只能为6位数字代码)";
	
	/** 家庭地址(区)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_HOME_AREA_FALSE_MSG = "家庭地址(区)非法字符(只能为6位数字代码)";
	
	/** 现居地址(省)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_NOWHOME_PROVINCE_FALSE_MSG = "现居地址(省)非法字符(只能为6位数字代码)";
	
	/** 现居地址(市)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_NOWHOME_CITY_FALSE_MSG = "现居地址(市)非法字符(只能为6位数字代码)";
	
	/** 现居地址(区)非法字符(只能为6位数字代码) */
	public static final String COLLAR_CONTACT_NOWHOME_AREA_FALSE_MSG = "现居地址(区)非法字符(只能为6位数字代码)";
	
	
	
	
	
	/* 白领-联系人信息段保存 */
	
	/** 白领联系人信息处理异常 */
	public static final String COLLAR_CONTACTS_EXCEPTION_MSG = "白领联系人信息处理异常";
	
	/** 白领联系人信息更新异常 */
	public static final String COLLAR_CONTACTS_UPDATE_EXCEPTION_MSG = "白领联系人信息更新异常";
	
	/** 白领联系人信息保存异常 */
	public static final String COLLAR_CONTACTS_SAVE_EXCEPTION_MSG = "白领联系人信息保存异常";
	
	/** 直系亲属姓名不能为空 */
	public static final String COLLAR_CONTACTS_FAMILY_NAME_NOTNULL_MSG = "直系亲属姓名不能为空";
	
	/** 直系亲属姓名输入非法(只能输入中文) */
	public static final String COLLAR_CONTACTS_FAMILY_NAME_FALSE_MSG = "直系亲属姓名输入非法(只能输入中文)";
	
	/** 直系亲属姓名输入非法(长度为2-20) */
	public static final String COLLAR_CONTACTS_FAMILY_NAME_LENGTH_FALSE_MSG = "直系亲属姓名输入非法(长度为2-20)";
	
	/** 紧急联系人不能为空 */
	public static final String COLLAR_CONTACTS_CONTACT_NAME_NOTNULL_MSG = "紧急联系人不能为空";
	
	/** 紧急联系人输入非法(只能输入中文) */
	public static final String COLLAR_CONTACTS_CONTACT_NAME_FALSE_MSG = "紧急联系人输入非法(只能输入中文)";
	
	/** 紧急联系人输入非法(长度为2-20) */
	public static final String COLLAR_CONTACTS_CONTACT_NAME_LENGTH_FALSE_MSG = "紧急联系人输入非法(长度为2-20)";
	
	/** 直系亲属关系不能为空  */
	public static final String COLLAR_FAMILYRELATION_NAME_FALSE_MSG = "直系亲属关系不能为空";
	
	/** 紧急联系人关系不能为空  */
	public static final String COLLAR_CONTACTRELATION_NAME_FALSE_MSG = "紧急联系人关系不能为空";
	
	/** 直系亲属单位不能为空 */
	public static final String COLLAR_FAMILYWORKUNIT_NOTNULL_MSG = "直系亲属单位不能为空";
	
	/** 直系亲属单位输入非法(不可以包含特殊字符) */
	public static final String COLLAR_FAMILYWORKUNIT_FALSE_MSG = "直系亲属单位输入非法(不可以包含特殊字符)";
	
	/** 直系亲属单位输入非法(长度为5-20) */
	public static final String COLLAR_FAMILYWORKUNIT_LENGTH_FALSE_MSG = "直系亲属单位输入非法(长度为5-20)";
	
	/** 紧急联系人单位不能为空 */
	public static final String COLLAR_CONTACTWORKUNIT_NOTNULL_MSG = "紧急联系人单位不能为空";
	
	/** 紧急联系人单位输入非法(不可以包含特殊字符) */
	public static final String COLLAR_CONTACTWORKUNIT_FALSE_MSG = "紧急联系人单位输入非法(不可以包含特殊字符)";
	
	/** 紧急联系人单位输入非法(长度为5-20) */
	public static final String COLLAR_CONTACTWORKUNIT_LENGTH_FALSE_MSG = "紧急联系人单位输入非法(长度为5-20)";
	
	/** 直系亲属(手机)不能为空 */
	public static final String COLLAR_FAMILYPHONE_FALSE_MSG = "直系亲属(手机)不能为空";
	
	/** 直系亲属(手机)输入非法 */
	public static final String COLLAR_FAMILYPHONE_LENGTH_FALSE_MSG = "直系亲属(手机)输入非法";
	
	/** 紧急联系人(手机)不能为空 */
	public static final String COLLAR_CONTACTPHONE_FALSE_MSG = "紧急联系人(手机)不能为空";
	
	/** 紧急联系人(手机)输入非法 */
	public static final String COLLAR_CONTACTPHONE_LENGTH_FALSE_MSG = "紧急联系人(手机)输入非法";
	
	/** 直系亲属(地址)不能为空 */
	public static final String COLLAR_FAMILYADDRESS_NOTNULL_MSG = "直系亲属(地址)不能为空";
	
	/** 直系亲属(地址)输入非法(不可以包含特殊字符) */
	public static final String COLLAR_FAMILYADDRESS_FALSE_MSG = "直系亲属(地址)输入非法(不可以包含特殊字符)";
	
	/** 直系亲属(地址)输入非法(长度为5-20) */
	public static final String COLLAR_FAMILYADDRESS_LENGTH_FALSE_MSG = "直系亲属(地址)输入非法(长度为5-20)";
	
	/** 直系亲属(地址)不能为空 */
	public static final String COLLAR_CONTACTADDRESS_NOTNULL_MSG = "直系亲属(地址)不能为空";
	
	/** 紧急联系人(地址)输入非法(不可以包含特殊字符) */
	public static final String COLLAR_CONTACTADDRESS_FALSE_MSG = "紧急联系人(地址)输入非法(不可以包含特殊字符)";
	
	/** 紧急联系人(地址)输入非法(长度为5-20) */
	public static final String COLLAR_CONTACTADDRESS_LENGTH_FALSE_MSG = "紧急联系人(地址)输入非法(长度为5-20)";
	
	/** 紧急联系人姓名不能与直系亲属姓名相同 */
	public static final String COLLAR_CONTACTS_NAME_EQ_FALSE_MSG = "紧急联系人姓名不能与直系亲属姓名相同";
	
	/** 紧急联系人关系不能与直系亲属关系相同  */
	public static final String COLLAR_CONTACTRELATION_EQ_FALSE_MSG = "紧急联系人关系不能与直系亲属关系相同";
	
	/** 紧急联系人手机不能与直系亲属手机相同 */
	public static final String COLLAR_CONTACT_PHONE_EQ_FALSE_MSG = "紧急联系人手机不能与直系亲属手机相同";
	
	/** 直系亲属关系非法(1-5字母数字)  */
	public static final String COLLAR_FAMILY_RELATION_FALSE_MSG = "直系亲属关系非法(1-5字母数字)";
	
	/** 紧急联系人关系非法(1-5字母数字)  */
	public static final String COLLAR_CONTACTS_RELATION_FALSE_MSG = "紧急联系人关系非法(1-5字母数字)";
	
	
	/* 修改密码 */
	
	/** 服务器处理异常 */
	public static final String USER_PASSWORD_EXCEPTION_MSG = "服务器处理异常";
	
	/** 用户名不存在 */
	public static final String USER_USERNAME_NOT_FIND_MSG = "用户名不存在";
	
	/** 原始密码错误 */
	public static final String USER_PASSWORD_INPUT_ERROR_MSG = "原始密码错误";
	
	/** 密码修改失败 */
	public static final String USER_UPDATE_PASSWORD_FAIL_MSG = "密码修改失败";
	

	/** 原始密码输入不能为空 */
	public static final String USER_OLDPASSWORD_NULL_FALSE_MSG = "原始密码输入不能为空";

	/** 密码与确认密码不一致 */
	public static final String USER_PASSWORD_NOT_EQUAL_MSG = "密码与确认密码不一致";

	/** 密码格式错误 */
	public static final String USER_NEWPASSWORD_FORMAT_ERROR_MSG = "密码格式错误";

	/** 新密码和原始密码相同 */
	public static final String USER_OLDPASSWORD_EQUAL_MSG = "新密码和原始密码相同";

	/** 用户名不能为空 */
	public static final String USER_USERNAME_NULL_FALSE_MSG = "用户名不能为空";
	
	/** 新密码不能为空 */
	public static final String USER_NEWUSERNAME_NULL_FALSE_MSG = "新密码不能为空";
	
	/** 新密码格式错误 */
	public static final String USER_PASSWORD_ERROR_FALSE_MSG = "新密码格式错误";
	
	/** 确认密码不能为空 */
	public static final String USER_RESTPASSWORD_ERROR_NULL_MSG = "确认密码不能为空";
	
	/** 确认密码格式错误 */
	public static final String USER_RESTPASSWORD_ERROR_FALSE_MSG = "确认密码格式错误";
	
	/** 新密码长度不对 */
	public static final String USER_PASSWORD_ERROR_LENGTH_MSG = "新密码长度不对";
	
	/** 确认密码长度不正确 */
	public static final String USER_RESTPASSWORD_LENGTH_MSG = "确认密码长度不正确";
	
	/** 原始密码必须8-20位数字、字母或下划线组合且不包含非法字符 */
	public static final String USER_OLDPASSWORD_LENGTH_MSG = "原始密码必须8-20位数字、字母或下划线组合且不包含非法字符";
	
	/** 新密码必须8-20位数字、字母或下划线组合且不包含非法字符 */
	public static final String USER_NEWPASSWORD_LENGTH_MSG = "新密码必须8-20位数字、字母或下划线组合且不包含非法字符";
	
	/** 新密码为空 */
	public static final String USER_NEWPASSWORD_ISNULL_MSG = "新密码为空";
	

	
	/* 银行卡 */
	
	/** 银行卡信息处理异常 */
	public static final String CARD_EXCEPTION_MSG = "银行卡信息处理异常";
	
	/** 银行卡信息更新失败 */
	public static final String CARD_UPDATE_EXCEPTION_MSG = "银行卡信息更新失败";
	
	/** 银行卡信息保存失败 */
	public static final String CARD_SAVE_EXCEPTION_MSG = "银行卡信息保存失败";
	
	/** 银行卡账户类型-开户行为空 */
	public static final String CARD_ISNULL_MSG = "银行卡账户类型-开户行为空";
	
	/** 开户行省不能为空 */
	public static final String CARD_PROVINCE_ISNULL_MSG = "开户行省不能为空";
	
	/** 开户行市不能为空 */
	public static final String CARD_CITY_ISNULL_MSG = "开户行市不能为空";
	
	/** 开户行支行不能为空 */
	public static final String CARD_ADDRESS_ISNULL_MSG = "开户行支行不能为空";
	
	/** 银行卡号为空 */
	public static final String CARD_NUM_ISNULL_MSG = "银行卡号为空";
	
	/** 银行卡号格式不对 */
	public static final String CARD_NUM_ERROR_MSG = "银行卡号格式不对";
	
	/** 银行卡号长度不对 */
	public static final String CARD_NUM_LEN_ERROR_MSG = "银行卡号长度不对";
	
	/** 确认账号输入不正确 */
	public static final String CARD_NUM_COM_ERROR_MSG = "确认账号输入不正确";
	
	/** 新增或修改标志输入非法 */
	public static final String ADD_CHANGE_ERROR_MSG = "新增或修改标志输入非法";
	
	/** 确认银行卡号为空 */
	public static final String COFIRM_CARD_NUM_ISNULL_MSG = "确认银行卡号为空";
	
	/** 确认银行卡号格式不对 */
	public static final String COFIRM_CARD_NUM_ERROR_MSG = "确认银行卡号格式不对";
	
	/** 确认银行卡号长度不对 */
	public static final String COFIRM_CARD_NUM_LEN_ERROR_MSG = "确认银行卡号长度不对";
	
	/** 银行卡真实姓名不能为空 */
	public static final String CARD_REALNAME_NOTNLULL_MSG = "银行卡真实姓名不能为空";
	
	/** 银行卡真实姓名输入非法(只能为1-20位中文)  */
	public static final String CARD_REALNAME_FALSE_MSG = "银行卡真实姓名输入非法(只能为1-20位中文)";
	
	/** 开户行支行长度不正确1-10 */
	public static final String CARD_BANCK_LENGTH_MSG = "开户行支行长度不正确1-10";
	
	/** 开户行支行输入包含非法字符 */
	public static final String CARD_BANCK_FALSE_MSG = "开户行支行输入包含非法字符";
	
	/** 开户行省非法字符(只能为6位数字代码)*/
	public static final String CARD_PROVINCE_VAL_FALSE_MSG = "开户行省非法字符(只能为6位数字代码)";
	
	/** 开户行市非法字符(只能为6位数字代码) */
	public static final String CARD_CITY_VAL_FALSE_MSG = "开户行市非法字符(只能为6位数字代码)";
	
	/** 银行卡账户类型-开户行非法(1-5位字母数字) */
	public static final String CARD_BANK_TYPE_MSG = "银行卡账户类型-开户行非法(1-5位字母数字)";
	
	
	/* 银行卡信息查询 */
	
	
	/** 银行卡查询处理异常 */
	public static final String CARD_QUERY_EXCEPTION_MSG = "银行卡查询处理异常";
	
	/** 银行卡查询用户不存在 */
	public static final String CARD_QUERY_USER_EXIST_MSG = "银行卡查询用户不存在";
	
	/** 未创建过银行卡信息 */
	public static final String CARD_NOT_FOUND_MSG = "未创建过银行卡信息";
	
	
	/* 忘记密码获取手机验证码 */
	
	/** 获取验证码服务器处理异常 */
	public static final String FORGE_PASSWORD_EXCEPTION_MSG = "获取验证码服务器处理异常";
	
	/** 对应手机号码不存在于系统 */
	public static final String FORGET_MOBILE_NOT_FOUND_MSG = "对应手机号码不存在于系统";
	
	/** 发送手机验证码失败 */
	public static final String SEND_PASSWORD_CODE_EXCEPTION_MSG = "发送手机验证码失败";
	
	/** 记录发送认证信息失败*/
	public static final String SEND_CODE_SAVE_ERORR_MSG = "记录发送认证信息失败";
	
	/** 验证码不能为空 */
	public static final String FORGETPASSWORd_CODE_NOTNULL_MSG = "验证码不能为空";
	
	/** 手机号码为空 */
	public static final String FORGETPASSWORd_PHONE_ERROR_ISNULL_MSG = "手机号码为空";
	
	/** 输入的手机号码格式错误 */
	public static final String FORGETPASSWORd_PHONE_ERROR_MSG = "输入的手机号码格式错误";
	
	
	/* 重置密码 */
	
	/** 重置密码操作异常 */
	public static final String RESET_PASSWORD_EXCEPTION_MSG = "重置密码操作异常";
	
	/** 重置密码短信发送失败，重置密码失败 */
	public static final String RESET_PASSWORD_SEND_EXCEPTION_MSG = "重置密码短信发送失败，重置密码失败";
	
	/** 重置密码失败 */
	public static final String RESET_PASSWORD_UPDATE_EXCEPTION_MSG = "重置密码失败";
	
	/** 新密码不能为空 */
	public static final String RESET_NEWPSSWORD_NOTNULL_MSG = "新密码不能为空";
	
	/** 新密码格式错误 */
	public static final String RESET_NEWPSSWORD_FALSE_MSG = "新密码格式错误";
	
	/** 确认密码不能为空 */
	public static final String RESET_RESTPASSWORD_ISNULL_MSG = "确认密码不能为空";
	
	/** 确认密码格式错误 */
	public static final String RESET_RESTPASSWORD_FALSE_MSG = "确认密码格式错误";
	
	/** 密码与确认密码不一致 */
	public static final String RESET_PASSWORD_NOT_EQ_MSG = "密码与确认密码不一致";
	
	
	
	
	
	
	
	
	/* 修改绑定手机发送短信验证码 */
	
	/** 修改绑定手机异常 */
	public static final String UPDATE_MOBILE_EXCEPTION_MSG = "修改绑定手机异常";
	
	/** 修改绑定手机短信发送失败 */
	public static final String UPDATE_MOBILE_SEND_EXCEPTION_MSG = "修改绑定手机短信发送失败";
	
	/** 新手机号码不能和原手机号相同 */
	public static final String UPDATE_MOBILENO_ERROR_MSG = "新手机号码不能和原手机号相同";
	
	/** 新手机号码已经存在，不能使用 */
	public static final String UPDATE_NEW_MOBILE_EXIST_MSG = "新手机号码已经存在，不能使用";
	
	/** 记录验证码信息失败 */
	public static final String UPDATE_NEW_MOBILE_EXCEPTION_MSG = "记录验证码信息失败";
	
	
	
	/* 更换绑定手机 */
	
	/** 更换手机号码操作异常 */
	public static final String CHANGE_MOBILE_EXCEPTION_MSG = "更换手机号码操作异常";
	
	/** 对应用户不存在 */
	public static final String CHANGE_MOBILE_USER_NOT_FOUND_MSG = "对应用户不存在";
	
	/** 验证码错误 */
	public static final String CHANGE_MOBILE_CODE_ERROR_MSG = "验证码错误";
	
	/** 验证码过期 */
	public static final String CHANGE_MOBILE_CODE_TIMEOUT_MSG = "验证码过期";
	
	/** 删除原有认证信息失败 */
	public static final String DELETE_MOBILE_CER_ERROR_MSG = "删除原有认证信息失败";
	
	/** 更新客户手机号码失败 */
	public static final String UPDATE_USER_MOBILE_ERROR_MSG = "更新客户手机号码失败";
	
	/** 保存新的手机认证记录失败 */
	public static final String SAVE_MOBILE_CER_ERROR_MSG = "保存新的手机认证记录失败";
	
	/** 更新客户手机登录信息失败 */
	public static final String UPDATE_USER_LOGIN_ERROR_MSG = "更新客户手机登录信息失败";
	
	/** 原手机号为空 */
	public static final String UPDATE_OLD_MOBILE_ERROR_MSG = "原手机号为空";
	
	/** 原手机号格式错误 */
	public static final String UPDATE_OLD_MOBIL_ERROR_EXIST_MSG = "原手机号格式错误";
	
	/** 新手机号为空 */
	public static final String UPDATE_NEW_MOBILE_ERROR_MSG = "新手机号为空";
	
	/** 新手机号格式错误 */
	public static final String UPDATE_NEW_MOBIL_ERROR_EXIST_MSG = "新手机号格式错误";
	
	/** 验证码为空 */
	public static final String UPDATE_NEW_MOBIL_CERTIFICATIONCODE_EXIST_MSG = "验证码为空";
	
	/** 验证码存在非法字符 */
	public static final String UPDATE_NEW_MOBIL_CERTIFICATIONCODE_ERROR_MSG = "验证码存在非法字符";
	
	/** 验证码长度不正确 */
	public static final String UPDATE_NEW_MOBIL_CERTIFICATIONCODE_LENGTH_MSG = "验证码长度不正确";
	
	
	/*  查询学校信息异常 */
	
	/** 学校信息查询异常 */
	public static final String STU_INFO_QUERY_EXCEPTION_MSG = "学校信息查询异常";
	
	/** 学校信息查询无结果 */
	public static final String STU_INFO_NOT_FOUND_MSG = "学校信息查询无结果";
	
	/** 客户类型异常,输入的不是学生用户名 */
	public static final String STU_INFO_CUSTOMER_FOUND_MSG = "客户类型异常,输入的不是学生用户名";
	
	
	/* 查询联系人信息 */
	
	/** 联系人信息查询异常 */
	public static final String CONCATS_QUERY_EXCEPTION_MSG = "联系人信息查询异常";
	
	/** 联系人信息查询无结果集 */
	public static final String CONCATS_QUERY_NOT_FOUND_MSG = "联系人信息查询无结果集";
	
	
	/* 联系信息查询 */
	
	/** 联系信息查询异常 */
	public static final String CONCAT_QUERY_EXCEPTION_MSG = "联系信息查询异常";
	
	/** 联系信息查询无结果集 */
	public static final String CONCAT_QUERY_NOT_FOUND_MSG = "联系信息查询无结果集";
	
	
	/* 资信信息查询 */
	
	/** 资信信息查询异常 */
	public static final String CREDIT_QUERY_EXCEPTION_MSG = "资信信息查询异常";
	
	/** 资信信息查询无结果集 */
	public static final String CREDIT_QUERY_NOT_FOUND_MSG = "资信信息查询无结果集";
	
	/** 客户类型异常,输入的不是白领用户名 */
	public static final String CREDIT_QUERY_CUSTTYPE_NOT_FOUND_MSG = "客户类型异常,输入的不是白领用户名";
	
	
	/* 单位信息 */
	
	/** 单位信息查询异常 */
	public static final String UNIT_QUERY_EXCEPTION_MSG = "单位信息查询异常";
	
	/** 单位信息查询无结果集 */
	public static final String UNIT_QUERY_NOT_FOUND_MSG = "单位信息查询无结果集";
	
	/** 客户类型异常,输入的不是白领用户名 */
	public static final String UNIT_QUERY_CUSTYPE_NOT_FOUND_MSG = "客户类型异常,输入的不是白领用户名";
	
	
	/* 白领信息查询 */
	
	/** 个人信息查询异常 */
	public static final String COLLAR_QUERY_EXCEPTION_MSG = "个人信息查询异常";
	
	/** 个人信息查询无结果集 */
	public static final String COLLAR_QUERY_NOT_FOUND_MSG = "个人信息查询无结果集";
	
	/** 客户类型异常,输入的不是白领用户名 */
	public static final String COLLAR_QUERY_CUSTTYPE_NOT_FOUND_MSG = "客户类型异常,输入的不是白领用户名";
	
	
	/* 白领个人详细资料查询 */
	
	/** 个人详细信息查询异常 */
	public static final String COLLAR_DETAIL_QUERY_EXCEPTION_MSG = "个人详细信息查询异常";
	
	/** 个人详细信息查询无结果集 */
	public static final String COLLAR_DETAIL_QUERY_NOT_FOUND_MSG = "个人详细信息查询无结果集";
	
	/** 客户类型异常,输入的不是白领用户名 */
	public static final String COLLAR_DETAIL_CUSTTYPE_NOT_FOUND_MSG = "客户类型异常,输入的不是白领用户名";
	
	/* 学生个人资料查询 */
	
	/** 个人详细信息查询异常 */
	public static final String STU_DETAIL_QUERY_EXCEPTION_MSG = "个人详细信息查询异常";
	
	/** 个人详细信息查询无结果集 */
	public static final String STU_DETAIL_QUERY_NOT_FOUND_MSG = "个人详细信息查询无结果集";
	
	/** 客户类型异常,输入的不是学生用户名 */
	public static final String STU_DETAIL_CUSTTYPE_NOT_FOUND_MSG = "客户类型异常,输入的不是学生用户名";
	/* 公告查询 */
	
	/** 公告信息查询异常 */
	public static final String NOTICE_QUERY_EXCEPTION_MSG = "公告信息查询异常";
	
	/** 公告信息查询无结果集 */
	public static final String NOTICE_QUERY_NOT_FOUND_MSG = "公告信息查询无结果集";
	
	/** 无公告明细信息 */
	public static final String NOTICE_DETAIL_NOT_FOUND_MSG = "无公告明细信息";
	
	
	/* 忘记密码手机验证码提交 */
	
	/** 服务器处理异常 */
	public static final String FORGET_PASSWORD_NOBILE_EXCEPTION_MSG = "服务器处理异常";
	
	/** 手机号码错误 */
	public static final String FORGET_PASSWORD_MOBILE_NOT_FOUND_MSG = "手机号码错误";
	
	/** 验证码错误 */
	public static final String FORGET_PASSWORD_CODE_ERROR_MSG = "验证码错误";
	
	/** 验证码超时 */
	public static final String FORGET_PASSWORD_CODE_TIMEOUT_MSG = "验证码超时";
	
	/** 验证码非法字符 */
	public static final String FORGET_PASSWORd_CODE_FALSE_MSG = "验证码非法字符";
	
	/** 验证码长度不正确1-6 */
	public static final String FORGET_PASSWORd_CODE_LENGTH_MSG = "验证码长度不正确1-6";
	
	/* 授信额度* */
	
	/** 用户授信异常 */
	public static final String SAVE_AMOUNT_EXCEPTION_MSG = "用户授信异常";
	
	/** 保存用户授信额度失败 */
	public static final String SAVE_AMOUNT_FAIL_MSG = "保存用户授信额度失败";
	
	/** 保存用户额度信息 */
	public static final String UPDATE_CREDIT_EXCEPTION_MSG = "保存用户额度信息";
	
	
	
}
