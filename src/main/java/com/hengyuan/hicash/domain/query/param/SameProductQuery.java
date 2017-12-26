package com.hengyuan.hicash.domain.query.param;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.ProShowEntity;
import com.hengyuan.hicash.parameters.request.param.SameProductReq;
import com.hengyuan.hicash.parameters.response.param.SameProductResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 产品展示信息 Dao
 * 
 * @author Cary.Liu
 * @createDate 2015-04-23
 *
 */
public class SameProductQuery extends AbstractDAO<ProShowEntity> {

	private final static String QUERY_SQL = "SELECT b.ID AS MER_PRO_ID,e.site_city_code AS CITY_CODE,b.PRO_NAME,b.PRO_TYPE,b.PRO_TITLE,b.PRO_DESC,b.PRICE,"
			+ " (FLOOR(b.PRICE/24)) AS MONTHLY,b.SUPPLIER_ID,c.supplier_name,e.siteid,e.site_sale_name,e.site_address,d.PIC_TYPE,d.PIC_PATH"
			+ " FROM MER_PRODUCT_INFO b"
			+ " LEFT JOIN d_supplier_info c ON b.SUPPLIER_ID = c.supplierid"
			+ " LEFT JOIN MER_PRODUCT_PIC d ON b.ID = d.PRODUCT_ID"
			+ " JOIN d_sale_site e ON b.SUPPLIER_ID = e.site_default_supplier"
			+ " WHERE 1 = 1 ";
	
	@Override
	public ProShowEntity mapping(ResultSet rs) throws SQLException {

		ProShowEntity proShowEntity = null;
		
		if(rs != null){
			
			proShowEntity = new ProShowEntity();
//			proShowEntity.setProShowId(rs.getString("ID"));
			proShowEntity.setCityCode(rs.getString("CITY_CODE"));
//			proShowEntity.setChannelId(rs.getString("CHANNEL_ID"));
			proShowEntity.setMerProId(rs.getString("MER_PRO_ID"));
			proShowEntity.setProName(rs.getString("PRO_NAME"));
			proShowEntity.setProTitle(rs.getString("PRO_TITLE"));
			proShowEntity.setProDesc(rs.getString("PRO_DESC"));
			proShowEntity.setMonthly(rs.getString("MONTHLY"));
			proShowEntity.setSupplierName(rs.getString("supplier_name"));
			proShowEntity.setPicType(rs.getString("PIC_TYPE"));
			proShowEntity.setPicPath(rs.getString("PIC_PATH"));
			proShowEntity.setProType(rs.getString("PRO_TYPE"));
			proShowEntity.setPrice(rs.getString("PRICE"));
			
			proShowEntity.setSiteId(rs.getString("siteid"));
			proShowEntity.setSiteName(rs.getString("site_sale_name"));
			proShowEntity.setSiteAddress(rs.getString("site_address"));
			
		}
		
		return proShowEntity;
	}
	
	/**
	 * 获取同款商品
	 * @param proShowReq
	 * @return
	 */
	public SameProductResp queryProShowList(SameProductReq paramReq){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		
		/* 上架 */
		querySql.append(" AND (d.PIC_TYPE = 'CPSL' OR d.PIC_TYPE IS NULL OR d.PIC_TYPE = '') AND b.STATUS IN('STATUS03')");
		/* 同城 */
		if(!StringUtils.isEmpty(paramReq.getCityCode())){
			querySql.append(" AND e.site_city_code = '"+ paramReq.getCityCode() +"'");
		}
		/* 同款 */
		querySql.append(" AND b.PRO_CLASS = '" + paramReq.getProClass() + "' ORDER BY MONTHLY ASC");// GROUP BY a.MER_PRO_ID
		//记录日志
		RecordUtils.writeAction(logger, null, querySql.toString());
		
		SameProductResp proListResp = null;
		
		Integer maxLine = paramReq.getMaxLine();
		//总记录数
		List<ProShowEntity> merProCount = ConnManager.executeQuery(querySql.toString(), this);
		//总页数
		Integer countPage = countPage(maxLine, merProCount.size());
		
		if(countPage != 0){
			proListResp = new SameProductResp();
			
			//当前页
			Integer curPage = sumCurPage(paramReq.getPageNo(), countPage);
			//查询起始行
			Integer firstLine = (curPage - 1) * paramReq.getMaxLine();
			
			String listSql = querySql.toString()+" LIMIT "+firstLine+","+maxLine+"";
			//列表集合
			List<ProShowEntity> proList = ConnManager.executeQuery(listSql, this);
			
			proListResp.setProShowList(proList);
			proListResp.setCurPage(curPage + "");
			proListResp.setCountPage(!merProCount.isEmpty()?countPage:0);
		}
		
		return proListResp;
	}
	
}
