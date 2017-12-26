package com.hengyuan.hicash.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 行业常量
 * 
 * @author teng
 *
 */
public class IndustryConsts {

	/*---- 所有一级行业 begin------- */
	/** 数码行业 */
	public static final String INDUSTRY_SMHY = "SMHY";

	/** 婚庆行业 */
	public static final String INDUSTRY_HQHY = "HQHY";

	/** 教育培训行业 */
	public static final String INDUSTRY_PXHY = "PXHY";

	/** 机动车行业 */
	public static final String INDUSTRY_JDHY = "JDHY";

	/** 医疗整形行业 */
	public static final String INDUSTRY_YLHY = "YLHY";

	/** 嗨居贷 */
	public static final String INDUSTRY_ZXHY = "ZXHY";

	/** 旅游行业 */
	public static final String INDUSTRY_LYHY = "LYHY";

	/** 现金业务 */
	public static final String INDUSTRY_ZZHY = "ZZHY";

	/** 嗨秒贷行业 */
	public static final String INDUSTRY_MDHY = "MDHY";

	/** 家电行业 */
	public static final String INDUSTRY_EJHY = "EJHY";

	/** 嗨房贷 */
	public static final String INDUSTRY_FDHY = "FDHY";

	/** 嗨薪贷 */
	public static final String INDUSTRY_HXHY = "HXHY";

	/** 体育行业, 管家二期新增 */
	public static final String INDUSTRY_TYHY = "TYHY";

	/** 车抵行业 */
	public static final String INDUSTRY_DYHY = "DYHY";

	/*---- 所有一级行业 end------- */

	/* 所有二级行业 */

	/** 数码分期 */
	public static final String INDUSTRY_SMTE = "SMTE";

	/** 新数码分期 */
	public static final String INDUSTRY_SMNE = "SMNE";

	/** 微整形分期 */
	public static final String INDUSTRY_ELWZ = "ELWZ";

	/** 婚纱摄影分期 */
	public static final String INDUSTRY_HXSY = "HXSY";

	/** 珠宝分期 */
	public static final String INDUSTRY_HQZB = "HQZB";

	/** 礼仪分期 */
	public static final String INDUSTRY_HQLY = "HQLY";

	/** 周转哥分期 */
	public static final String INDUSTRY_ZZ01 = "ZZ01";

	/** 乘用车辆分期 */
	public static final String INDUSTRY_CYCL = "CYCL";

	/** 营运车辆分期 */
	public static final String INDUSTRY_GDYY = "GDYY";

	/** 自住房装修分期 */
	public static final String INDUSTRY_ZZZX = "ZZZX";

	/** 投资房装修分期 */
	public static final String INDUSTRY_TZZX = "TZZX";

	/** 培训分期 */
	public static final String INDUSTRY_JYPX = "JYPX";

	/** 旅游分期 */
	public static final String INDUSTRY_LYFQ = "LYFQ";

	/** 电动自行车分期 */
	public static final String INDUSTRY_DDCH = "DDCH";

	/** 摩托车行业 */
	public static final String INDUSTRY_MTCH = "MTCH";

	/** 嗨秒贷 */
	public static final String INDUSTRY_MDCP = "MDCP";

	/** 滴答贷 */
	public static final String INDUSTRY_DDHY = "DDHY";

	/** 双拥分期 */
	public static final String INDUSTRY_SYFF = "SYFF";

	/** 嗨商贷 */
	public static final String INDUSTRY_SYDK = "SYDK";

	/** 二手乘用车分期 */
	public static final String INDUSTRY_ESYC = "ESYC";

	/** 江铃陆风汽车分期 */
	public static final String INDUSTRY_LFYC = "LFYC";

	/** 电信数码 */
	public static final String INDUSTRY_DXSM = "DXSM";

	/** 家电分期 */
	public static final String INDUSTRY_EJFQ = "EJFQ";

	/** 车辆抵押分期 */
	public static final String INDUSTRY_DCFQ = "DCFQ";

	/** 租房分期 */
	public static final String INDUSTRY_ZFFQ = "ZFFQ";

	/** 嗨秒贷（老客户） */
	public static final String INDUSTRY_MDOH = "MDOH";

	/** 蓝领数码分期 */
	public static final String INDUSTRY_LLSM = "LLSM";

	/** 蓝领嗨秒贷 */
	public static final String INDUSTRY_LLMD = "LLMD";

	/** 优质行业客户分期 */
	public static final String INDUSTRY_YZKH = "YZKH";

	/** 房产客户分期 */
	public static final String INDUSTRY_FCKH = "FCKH";

	/** 高薪客户分期 */
	public static final String INDUSTRY_GXKH = "GXKH";

	/** 普及版客户分期 */
	public static final String INDUSTRY_PJHY = "PJHY";

	/** 线上数码 */
	public static final String INDUSTRY_XSSM = "XSSM";

	/** 嗨秒贷公测版 */
	public static final String INDUSTRY_MDCS = "MDCS";

	/** HiSports产品 */
	public static final String INDUSTRY_SPORTS = "TYYP";

	/** 车抵分期 */
	public static final String INDUSTRY_CDFQ = "CDFQ";

	/** 300快 */
	public static final String INDUSTRY_THFQ = "THFQ";

	/** 女神 */
	public static final String INDUSTRY_HINS = "HINS";

	/** 滴答 */
	public static final String INDUSTRY_LDDD = "LDDD";

	/** 嗨钱来(滴滴贷) */
	public static final String INDUSTRY_DDCP = "DDCP";

	/** vip */
	public static final String INDUSTRY_VIPD = "VIPD";

	/** 滴滴司机 */
	public static final String INDUSTRY_DDSJ = "DDSJ";

	/** 精英分期*/
	public static final String INDUSTRY_JYFQ = "JYFQ";

	/* 所有二级行业组合 */
	public static List<String> list = new ArrayList<String>();

	/**
	 * 初始化二级行业组合
	 */
	static {

		list.add(INDUSTRY_SMHY); // 数码行业

	}

}
