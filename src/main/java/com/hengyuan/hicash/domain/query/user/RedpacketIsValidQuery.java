package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ExceptionMsg;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.LoginEntity;
import com.hengyuan.hicash.entity.user.RedpacketInfoEntity;
import com.hengyuan.hicash.exception.SaveDeviceException;
import com.hengyuan.hicash.parameters.response.user.RedpacketIsValidResp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * @author xing.yuan
 * @date 2017年12月21日 下午4:04:07
 * 类说明
 */
public class RedpacketIsValidQuery extends AbstractDAO<RedpacketInfoEntity>{

	private static Logger logger = Logger.getLogger(RedpacketIsValidQuery.class);
	private String uuid;
	private List<String> lists = new ArrayList<String>();
	
	public RedpacketIsValidQuery() {
		lists.add("redpacket_amount");
		lists.add("status");
	}
	
	@Override
	public RedpacketInfoEntity mapping(ResultSet rs) throws SQLException {

		RedpacketInfoEntity redpacketInfoEntity = new RedpacketInfoEntity();
		
		if(rs!=null){
			redpacketInfoEntity.setRedpacketAmount(rs.getString("redpacket_amount"));
			redpacketInfoEntity.setStatus(rs.getString("status"));
		}
		
		return redpacketInfoEntity;
	}
	public RedpacketIsValidResp  queryRedpacketAmount(String appNo,LoginEntity loginEntity){
		List<RedpacketInfoEntity> list=new ArrayList<RedpacketInfoEntity>();
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("applicationno", appNo);
		String sql = null;
		sql = MapAssemForSql.getSelectSqlByGroupOrOrder(lists, TableConsts.REDPACKET_INFO, whereMap," order by create_date desc limit 1 ");
		System.out.println(sql);
		logger.info("通过用流水号查询红包金额");
		RecordUtils.writeAction(logger, null, sql);
		list= ConnManager.executeQuery(sql, this);
		RedpacketIsValidResp resp=null;
		if(list.size()>0){
			resp = new RedpacketIsValidResp();
			for (RedpacketInfoEntity redpacketInfo:list) {
				redpacketInfo.setId(loginEntity.getId());
				redpacketInfo.setUserName(loginEntity.getUserName());
				resp.setRedpacketInfo(redpacketInfo);
			}
		}
		return resp;
	}
	
	public void updateStatus(String appNo) throws SaveDeviceException{
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("applicationno", appNo);
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("status", "0");
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.REDPACKET_INFO,setMap,whereMap);

		RecordUtils.writeAction(logger, uuid, updateSql);
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new SaveDeviceException(ExceptionMsg.UPDATE_INFO_EXCEPTION);
		}
	}
}
