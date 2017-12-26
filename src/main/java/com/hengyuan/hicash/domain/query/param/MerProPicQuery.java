package com.hengyuan.hicash.domain.query.param;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.MerProPicEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 商户商品图片 Dao
 * @author Cary.Liu
 * @date 2014-12-19
 *
 */
public class MerProPicQuery extends AbstractDAO<MerProPicEntity> {

	private List<String> columns = new ArrayList<String>();
	
	public MerProPicQuery(){
		
		columns.add("ID");
		columns.add("PRODUCT_ID");
		columns.add("PIC_TYPE");
		columns.add("PIC_PATH");
	}
	
	@Override
	public MerProPicEntity mapping(ResultSet rs) throws SQLException {

		MerProPicEntity picEntity = null;
		
		if(rs != null){
			
			picEntity = new MerProPicEntity();
			picEntity.setPicId(StringUtils.valueOf(rs.getObject("ID")));
			picEntity.setProId(StringUtils.valueOf(rs.getObject("PRODUCT_ID")));
			picEntity.setPicType(StringUtils.valueOf(rs.getObject("PIC_TYPE")));
			picEntity.setPicPath(ResourceUtils.getValue(Consts.PROPIC_RESOURCE_URL) + StringUtils.valueOf(rs.getObject("PIC_PATH")));
		}
		
		return picEntity;
	}
	
	public List<MerProPicEntity> queryPicList(String proId){
		
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("PRODUCT_ID", proId);
		
		String querySql = MapAssemForSql.getSelectSql(columns, TableConsts.MER_PRODUCT_PIC,where);
		
		return ConnManager.executeQuery(querySql, this);
	}
	
	public MerProPicEntity queryPicByType(String proId,String picType){
		
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("PRODUCT_ID", proId);
		where.put("PIC_TYPE", picType);
		
		String querySql = MapAssemForSql.getSelectSql(columns, TableConsts.MER_PRODUCT_PIC,where);
		
		return ConnManager.singleQuery(querySql, this);
	}
	
}
