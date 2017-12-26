package com.hengyuan.hicash.domain.query.app;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.PicConfigEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 材料图片配置
 * @author think
 *
 */
public class PicConfigQuery extends AbstractDAO<PicConfigEntity> {
	
	private static Logger logger = Logger.getLogger(PicConfigQuery.class);

	private static final String QUERY_SQL = "SELECT HY_MATERIAL_CODE,HY_MATERIAL_NAME,NUMBER FROM hy_material_param WHERE 1 = 1 ";
	
	@Override
	public PicConfigEntity mapping(ResultSet rs) throws SQLException {

		PicConfigEntity entity = null;
		
		if(rs != null){
			
			entity = new PicConfigEntity();
			entity.setPicType(StringUtils.valueOf(rs.getObject("HY_MATERIAL_CODE")));
			entity.setPicDescName(StringUtils.valueOf(rs.getObject("HY_MATERIAL_NAME")));
			entity.setPicMaxNum(rs.getInt("NUMBER"));
		}
		
		return entity;
	}
	
	public PicConfigEntity queryByType(String picType){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND HY_MATERIAL_CODE = '"+ picType +"'");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
	/**
	 * 是否为多张类型
	 * @param picType
	 * @return
	 */
	public boolean isMorePic(String picType){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND HY_MATERIAL_CODE = '"+ picType +"' AND NUMBER > 1");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		PicConfigEntity entity = ConnManager.singleQuery(querySql.toString(), this);
		return entity != null ? true : false;
	}
	
}
