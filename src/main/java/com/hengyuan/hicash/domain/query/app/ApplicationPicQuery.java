package com.hengyuan.hicash.domain.query.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.PicParam;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 申请件图片查询
 * @author jason.wei
 * @create date 20160826
 *
 */
public class ApplicationPicQuery extends AbstractDAO<PicParam> {
	
	private static Logger logger = Logger.getLogger(ApplicationPicQuery.class);
	
	private List<String> selectList = new ArrayList<String>();
	
	public ApplicationPicQuery() {
		selectList.add("pic_id");
		selectList.add("app_id");
		selectList.add("pic_name");
		selectList.add("pic_caption");
		selectList.add("pic_path");
		selectList.add("pic_type");
		selectList.add("update_date");
		selectList.add("update_user");
		selectList.add("create_date");
		selectList.add("create_user");
		selectList.add("thum_path");
	}
	
	@Override
	public PicParam mapping(ResultSet rs) throws SQLException {
		
		PicParam picParam = null;
		if(rs!=null){
			picParam = new PicParam();
			picParam.setPicId(rs.getString("pic_id"));
			picParam.setAppNo(rs.getString("app_id"));
			picParam.setPicName(rs.getString("pic_name"));
			picParam.setCaption(rs.getString("pic_caption"));
			picParam.setPicPath(rs.getString("pic_path"));
			picParam.setPicType(rs.getString("pic_type"));
			picParam.setThumPath(rs.getString("thum_path"));
		}else{
			System.out.println("申请件图片查询为空");
		}
		return picParam;
	}

	public List<PicParam> queryAppPicByAppNoAndType(String appNo, String picType){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();

		whereMap.put("app_id", appNo);
		whereMap.put("pic_type", picType);

		String sql = MapAssemForSql.getSelectSql(selectList,TableConsts.APP_PIC, whereMap);
		RecordUtils.writeAction(logger, appNo, sql.toString());
		return ConnManager.executeQuery(sql, this);
		
	}
	
	
	
}
