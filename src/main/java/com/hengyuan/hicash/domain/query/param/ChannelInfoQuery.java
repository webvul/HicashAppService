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
import com.hengyuan.hicash.entity.param.ChannelEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;

/**
 * 获取hicash频道 Dao
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ChannelInfoQuery extends AbstractDAO<ChannelEntity> {

	private List<String> columns = new ArrayList<String>();
	
	public ChannelInfoQuery(){
		
		columns.add("ID");
		columns.add("NAME");
		columns.add("SHOW_SEQUENCE");
		columns.add("CREATE_TIME");
	}
	
	@Override
	public ChannelEntity mapping(ResultSet rs) throws SQLException {

		ChannelEntity channelEntity = null;
		
		if(rs != null){
			
			channelEntity = new ChannelEntity();
			channelEntity.setChannelId(rs.getInt("ID"));
			channelEntity.setChannelName(rs.getString("NAME"));
			channelEntity.setShowSequence(rs.getInt("SHOW_SEQUENCE"));
			channelEntity.setCreateDate(rs.getString("CREATE_TIME"));
		}
		
		return channelEntity;
	}
	
	/**
	 * 获取频道集合
	 * @return
	 */
	public List<ChannelEntity> queryChannelList(){
		
		String querySql = MapAssemForSql.getSelectSqlByGroup(columns, TableConsts.CHANNEL,null," ORDER BY SHOW_SEQUENCE ASC");
		
		return ConnManager.executeQuery(querySql, this);
	}
	
	/**
	 * 获取频道
	 * @return
	 */
	public List<ChannelEntity> queryChannelByProId(String hyProId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("PRODUCT_ID", hyProId);
		
		String querySql = MapAssemForSql.getSelectSql(columns, TableConsts.CHANNEL,whereMap);
		
		return ConnManager.executeQuery(querySql, this);
	}
	
	/**
	 * 获取频道
	 * @return
	 */
	public List<ChannelEntity> queryChannelById(String channelId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("ID", channelId);
		
		String querySql = MapAssemForSql.getSelectSql(columns, TableConsts.CHANNEL,whereMap);
		
		return ConnManager.executeQuery(querySql, this);
	}
	
	/**
	 * 获取频道
	 * @return
	 */
	public List<ChannelEntity> queryMerType(){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("TYPE_FLAG", Consts.FINAL_NUMBER_1);
		
		String querySql = MapAssemForSql.getSelectSqlByGroup(columns, TableConsts.CHANNEL,whereMap," ORDER BY SHOW_SEQUENCE ASC");
		
		return ConnManager.executeQuery(querySql, this);
	}
	
	/**
	 * 获取hicashAPP分类
	 * @return
	 */
	public List<ChannelEntity> queryHicashAppType(){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("APP_TYPE_FLAG", Consts.FINAL_NUMBER_1);
		
		String querySql = MapAssemForSql.getSelectSqlByGroup(columns, TableConsts.CHANNEL,whereMap," ORDER BY SHOW_SEQUENCE ASC");
		
		return ConnManager.executeQuery(querySql, this);
	}
	
}
