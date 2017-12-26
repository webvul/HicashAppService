package com.hengyuan.hicash.domain.query.user;

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
import com.hengyuan.hicash.parameters.response.user.CallPhoneDataMsgResp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 	根据城市ID获取电信套餐内容dao
 * 
 * @author lihua.Ren
 * @create date 2015-08-18
 *
 */
public class CallPhoneDataMsgQuery extends AbstractDAO<CallPhoneDataMsgResp> {

	private static Logger logger = Logger.getLogger(CallPhoneDataMsgQuery.class);
	@Override
	public CallPhoneDataMsgResp mapping(ResultSet rs) throws SQLException {
		CallPhoneDataMsgResp resp = new CallPhoneDataMsgResp();
		if(rs != null){
			resp.setFreeCalls(StringUtils.valueOf(rs.getObject("FREE_CALLS")));
			resp.setPhoneDataFlow(StringUtils.valueOf(rs.getObject("PHONE_DATA_FLOW")));
			resp.setPhoneDataLevel(StringUtils.valueOf(rs.getObject("PHONE_DATA_LEVEL")));
			resp.setPhoneDataName(StringUtils.valueOf(rs.getObject("PHONE_DATA_NAME")));
			resp.setPhoneDataOver(StringUtils.valueOf(rs.getObject("PHONE_DATA_OVER")));
			resp.setPhoneDataVoice(StringUtils.valueOf(rs.getObject("PHONE_DATA_VOICE")));
			resp.setRemark(StringUtils.valueOf(rs.getObject("REMARK")));
			resp.setTransferRemark(StringUtils.valueOf(rs.getObject("TRANSFER_REMARK")));
			resp.setId(StringUtils.valueOf(rs.getObject("id")));
		}else{
			return null;
		}
		return resp;
	}
	
	public CallPhoneDataMsgResp queryCallPhoneDataResp(String cityCode) {
		
		List<String> list = new ArrayList<String>();
		list.add("FREE_CALLS");
		list.add("PHONE_DATA_FLOW");
		list.add("PHONE_DATA_LEVEL");
		list.add("PHONE_DATA_NAME");
		list.add("PHONE_DATA_OVER");
		list.add("PHONE_DATA_VOICE");
		list.add("REMARK");
		list.add("TRANSFER_REMARK");
		list.add("id");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CITY_ID", cityCode);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CALL_PHONE_DATA, map);
		//记录日志
				RecordUtils.writeAction(logger, cityCode, sql); 
		return ConnManager.singleQuery(sql, this);
	}

}
