package com.hengyuan.hicash.domain.query.param;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.BannerEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 首页图片信息查询 DAO
 * @author Cary.Liu
 * @createDate 2015-06-05
 *
 */
public class BannerQuery extends AbstractDAO<BannerEntity> {

	private static final String QUERY_SQL = "SELECT PIC_TITLE,PIC_URL,APP_PIC,APP_PIC_THUM,CITY_CODE FROM hy_bannel_pic WHERE 1 = 1 ";
	
	private static Logger logger = Logger.getLogger(BannerQuery.class);
	
	@Override
	public BannerEntity mapping(ResultSet rs) throws SQLException {

		BannerEntity entity = null;
		if(rs != null){
			
			entity = new BannerEntity();
			entity.setPicTitle(StringUtils.valueOf(rs.getObject("PIC_TITLE")));
			entity.setPicActionUrl(StringUtils.valueOf(rs.getObject("PIC_URL")));
			entity.setPigBigPath(StringUtils.valueOf(rs.getObject("APP_PIC")));
			entity.setPicSmallPath(StringUtils.valueOf(rs.getObject("APP_PIC_THUM")));
		}
		
		return entity;
	}

	/**
	 * 根据城市查询首页图
	 * @param cityCode
	 * @return
	 */
	public List<BannerEntity> queryBannerInfoByCity(String cityCode){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND STATUS = 1 AND CITY_CODE = '" + cityCode + "'");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.executeQuery(querySql.toString(), this);
	}
	
}
