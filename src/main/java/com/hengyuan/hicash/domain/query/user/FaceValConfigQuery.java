package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.FaceValConfigEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class FaceValConfigQuery extends AbstractDAO<FaceValConfigEntity>{
	private static Logger logger = Logger.getLogger(FaceValConfigQuery.class);
	
	private String QUERY_SQL="select * from face_val_config where 1=1 ";
	@Override
	public FaceValConfigEntity mapping(ResultSet rs) throws SQLException {
		 
		FaceValConfigEntity faceConfigEntity = new FaceValConfigEntity();
		
		if(rs!=null){
			
			faceConfigEntity.setCreateTime(StringUtils.valueOf(rs.getObject("CREATE_TIME")));
			faceConfigEntity.setCreateUser(StringUtils.valueOf(rs.getObject("create_user")));
			faceConfigEntity.setWhichPart(StringUtils.valueOf(rs.getObject("which_Part")));
			faceConfigEntity.setUpdateTime(StringUtils.valueOf(rs.getObject("update_time")));
			faceConfigEntity.setUpdateUser(StringUtils.valueOf(rs.getObject("update_user")));
			faceConfigEntity.setFstCount(StringUtils.valueOf(rs.getObject("fst_count")));
			faceConfigEntity.setSecCount(StringUtils.valueOf(rs.getObject("sec_count")));
			return faceConfigEntity;
		}else{
			
			System.out.println("查询的结果集为空");
			return null;
			
		}
	}
	
	/**
	 * 查询配置
	 * @param realName
	 * @param identity
	 * @return
	 */
	public FaceValConfigEntity queryFaceConfig(String uuid){
		
      StringBuffer querySql = new StringBuffer(QUERY_SQL);

		//记录日志
		RecordUtils.writeAction(logger, uuid, querySql.toString());
		
		return ConnManager.singleQuery(querySql.toString(), this);
		

	}


}
