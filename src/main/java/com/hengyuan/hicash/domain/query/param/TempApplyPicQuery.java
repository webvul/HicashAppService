package com.hengyuan.hicash.domain.query.param;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.TempApplyPicEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 临时申请件图片DAO
 * 
 * @author Cary.Liu
 * @createDate 2015-07-20
 *
 */
public class TempApplyPicQuery extends AbstractDAO<TempApplyPicEntity> {

	private static Logger logger = Logger.getLogger(TempApplyPicQuery.class);

	private static final String QUERY_SQL = "SELECT pic_id,username,pic_type,pic_name,pic_path,thum_path,pic_caption FROM cust_tempapply_pic WHERE 1 = 1 ";

	@Override
	public TempApplyPicEntity mapping(ResultSet rs) throws SQLException {

		TempApplyPicEntity entity = null;

		if (rs != null) {

			entity = new TempApplyPicEntity();
			entity.setPicId(StringUtils.valueOf(rs.getObject("pic_id")));
			entity.setUserName(StringUtils.valueOf(rs.getObject("username")));
			entity.setPicType(StringUtils.valueOf(rs.getObject("pic_type")));
			entity.setPicName(StringUtils.valueOf(rs.getObject("pic_name")));
			entity.setPicPath(StringUtils.valueOf(rs.getObject("pic_path")));
			entity.setThumPath(StringUtils.valueOf(rs.getObject("thum_path")));
			entity.setPicCaption(StringUtils.valueOf(rs.getObject("pic_caption")));

		}

		return entity;
	}

	/**
	 * 获取临时申请图片
	 * 
	 * @param userName
	 * @return
	 */
	public List<TempApplyPicEntity> queryTempApplyPic(String userName) {

		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND username = '" + userName + "'");

		RecordUtils.writeAction(logger, null, querySql.toString());

		return ConnManager.executeQuery(querySql.toString(), this);
	}

	/**
	 * 根据用户名和图片类型判断是否存在的记录
	 * 
	 * @param userName
	 * @param imgType
	 * @return
	 */
	public int isExist(String userName, String imgType) {
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND username = '" + userName + "'");
		querySql.append(" AND pic_type = '" + imgType + "'");
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.executeQuery(querySql.toString(), this).size();
	}

	/**
	 * 根据用户名和图片类型查询图片id
	 * 
	 * @param userName
	 * @param imgType
	 * @return
	 */
	public TempApplyPicEntity getPicId(String userName, String imgType) {
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND username = '" + userName + "'");
		querySql.append(" AND pic_type = '" + imgType + "'");
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.executeQuery(querySql.toString(), this).get(0);
	}

}
