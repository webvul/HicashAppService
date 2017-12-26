package com.hengyuan.hicash.domain.query.param;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.ProShowEntity;
import com.hengyuan.hicash.parameters.request.param.ProShowInfoReq;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 产品展示信息 Dao
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ProShowInfoQuery extends AbstractDAO<ProShowEntity> {
	
	private static Logger logger = Logger.getLogger(ProShowInfoQuery.class);

	private static final String QUERY_SQL = "SELECT f.HY_INDUSTRY_NAME,a.ID,a.CITY_CODE,a.CHANNEL_ID,a.MER_PRO_ID,b.PRO_NAME,b.PRO_TYPE,b.PRO_TITLE,b.PRO_DESC,b.PRICE,b.PRO_CLASS,b.INDUSTRY,"
				+ " (FLOOR(b.PRICE/24)) AS MONTHLY,c.supplier_name,d.PIC_TYPE,d.PIC_PATH,e.siteid,b.SPIKE_PRICE,IFNULL((FLOOR(b.SPIKE_PRICE/24)),0) AS MSMONTHLY,"
				+ " CONCAT(FLOOR(HOUR(TIMEDIFF(NOW(),b.SPIKE_END_TIME))/24),'天',MOD(HOUR(TIMEDIFF(NOW(),b.SPIKE_END_TIME)),24),'小时',MINUTE(TIMEDIFF(NOW(),b.SPIKE_END_TIME)),'分钟') AS MSTIME"
				+ " FROM product_show_info a"
				+ " LEFT JOIN MER_PRODUCT_INFO b ON a.MER_PRO_ID = b.ID"
				+ " LEFT JOIN d_supplier_info c ON b.SUPPLIER_ID = c.supplierid"
				+ " LEFT JOIN MER_PRODUCT_PIC d ON b.ID = d.PRODUCT_ID"
				+ " LEFT JOIN d_sale_site e ON b.SUPPLIER_ID = e.site_default_supplier  "
				+ " LEFT JOIN hy_industry_param f ON b.INDUSTRY = f.HY_INDUSTRY_CODE"
				+ " WHERE 1 = 1 ";
	
	@Override
	public ProShowEntity mapping(ResultSet rs) throws SQLException {

		ProShowEntity proShowEntity = null;
		
		if(rs != null){
			
			proShowEntity = new ProShowEntity();
			proShowEntity.setProShowId(StringUtils.valueOf((rs.getObject("ID"))));
			proShowEntity.setCityCode(StringUtils.valueOf((rs.getObject("CITY_CODE"))));
			proShowEntity.setChannelId(StringUtils.valueOf((rs.getObject("CHANNEL_ID"))));
			proShowEntity.setMerProId(StringUtils.valueOf((rs.getObject("MER_PRO_ID"))));
			proShowEntity.setProName(StringUtils.valueOf((rs.getObject("PRO_NAME"))));
			proShowEntity.setProTitle(StringUtils.valueOf((rs.getObject("PRO_TITLE"))));
			proShowEntity.setProDesc(StringUtils.valueOf((rs.getObject("PRO_DESC"))));
			proShowEntity.setMonthly(StringUtils.valueOf((rs.getObject("MONTHLY"))));
			proShowEntity.setSupplierName(StringUtils.valueOf((rs.getObject("supplier_name"))));
			proShowEntity.setPicType(StringUtils.valueOf((rs.getObject("PIC_TYPE"))));
			String picPath = StringUtils.valueOf((rs.getObject("PIC_PATH")));
			proShowEntity.setPicPath(!org.apache.commons.lang.StringUtils.isEmpty(picPath) ? (ResourceUtils.getValue(Consts.PROPIC_RESOURCE_URL) + picPath) : picPath);
			proShowEntity.setProType(StringUtils.valueOf((rs.getObject("PRO_TYPE"))));
			proShowEntity.setPrice(StringUtils.valueOf((rs.getObject("PRICE"))));
			proShowEntity.setProClass(StringUtils.valueOf((rs.getObject("PRO_CLASS"))));
			proShowEntity.setIndustryCode(StringUtils.valueOf((rs.getObject("INDUSTRY"))));
			proShowEntity.setIndustryName(StringUtils.valueOf((rs.getObject("HY_INDUSTRY_NAME"))));
			
			proShowEntity.setMsPrice(StringUtils.valueOf((rs.getObject("SPIKE_PRICE"))));
			proShowEntity.setMsMonthlyPrice(StringUtils.valueOf((rs.getObject("MSMONTHLY"))));
			proShowEntity.setMsTime(StringUtils.valueOf((rs.getObject("MSTIME"))));
		}
		
		return proShowEntity;
	}
	
	/**
	 * 获取展示商品
	 * @param proShowReq
	 * @return
	 */
	public List<ProShowEntity> queryProShowList(ProShowInfoReq proShowReq){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		
		querySql.append(" AND CITY_CODE = '" + proShowReq.getCityCode() + "' AND CHANNEL_ID = '" + proShowReq.getChannelId() + "' AND (d.PIC_TYPE = 'CPSL' OR d.PIC_TYPE IS NULL OR d.PIC_TYPE = '') AND b.STATUS IN('STATUS03') ");
		/* 价格搜索 */
//		if(Consts.SEARCH_PRICE_LT.equals(proShowReq.getPriceSearch())){
//			querySql.append(" AND b.PRICE < 5000");
//		}else if (Consts.SEARCH_PRICE_MD.equals(proShowReq.getPriceSearch())){
//			querySql.append(" AND b.PRICE >= 5000 AND b.PRICE <= 10000");
//		}else if (Consts.SEARCH_PRICE_GT.equals(proShowReq.getPriceSearch())){
//			querySql.append(" AND b.PRICE > 10000");
//		}
		/* 商圈搜索 */
//		if(proShowReq.getBusinessCircle()!= null && !StringUtils.isEmpty(proShowReq.getBusinessCircle())){
//			querySql.append(" AND e.siteid IN (SELECT SITE_ID FROM hy_business_circle_detail WHERE BUSINESS_CIRCLE_ID = "+proShowReq.getBusinessCircle()+")");
//		}
		
		querySql.append(" GROUP BY a.MER_PRO_ID ORDER BY a.POSITION ASC LIMIT 10");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		
		return ConnManager.executeQuery(querySql.toString(), this);
	}
	
	/**
	 * 获取热卖展示商品
	 * @param proShowReq
	 * @return
	 */
	public List<ProShowEntity> queryHotProList(String cityCode){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		
		querySql.append(" AND CITY_CODE = '" + cityCode + "' AND b.HOT_FLAG = 1 AND (d.PIC_TYPE = 'CPSL' OR d.PIC_TYPE IS NULL OR d.PIC_TYPE = '') AND b.STATUS IN('STATUS03') ");
		
		querySql.append(" GROUP BY a.MER_PRO_ID ORDER BY a.POSITION ASC LIMIT 10");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		
		return ConnManager.executeQuery(querySql.toString(), this);
	}
	
}
