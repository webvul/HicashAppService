package com.hengyuan.hicash.domain.query.param;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.ChannelEntity;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 获取hicash频道 Dao
 * @author Cary.Liu
 * @date 2015-04-13
 *
 */
public class ChannelCityQuery extends AbstractDAO<ChannelEntity> {

	private static final String QUERY_SQL = "SELECT b.ID,b.NAME,b.SHOW_SEQUENCE,b.CREATE_TIME,a.CITY_CODE,b.INDUSTRY_CODE,b.CHANNEL_PIC_PATH FROM hy_channel_city a "
			+ " LEFT JOIN channel_table b ON a.CHANNEL_ID = b.ID "
			+ " WHERE 1 = 1 AND a.STATUS = 1";
	
	@Override
	public ChannelEntity mapping(ResultSet rs) throws SQLException {

		ChannelEntity channelEntity = null;
		
		if(rs != null){
			
			channelEntity = new ChannelEntity();
			channelEntity.setChannelId(rs.getInt("ID"));
			channelEntity.setChannelName(StringUtils.valueOf(rs.getString("NAME")));
			channelEntity.setShowSequence(rs.getInt("SHOW_SEQUENCE"));
			channelEntity.setCreateDate(StringUtils.valueOf(rs.getString("CREATE_TIME")));
			channelEntity.setCityCode(StringUtils.valueOf(rs.getString("CITY_CODE")));
			channelEntity.setIndustryCode(StringUtils.valueOf(rs.getString("INDUSTRY_CODE")));
			channelEntity.setShowPicPath(StringUtils.valueOf(rs.getString("CHANNEL_PIC_PATH")));
		}
		
		return channelEntity;
	}
	
	/**
	 * 获取频道集合
	 * @return
	 */
	public List<ChannelEntity> queryChannelList(String cityCode){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND a.CITY_CODE = '"+ cityCode +"' ORDER BY b.SHOW_SEQUENCE ASC");
		
		 return ConnManager.executeQuery(querySql.toString(), this);
	}
	
	
	
}
