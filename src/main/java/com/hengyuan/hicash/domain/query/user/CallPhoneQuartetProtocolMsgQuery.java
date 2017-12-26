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
import com.hengyuan.hicash.parameters.request.user.CallPhoneQuartetProtocolMsgReq;
import com.hengyuan.hicash.parameters.response.user.CallPhoneQuartetProtocolMsgResp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * @author Administrator
 *
 */
public class CallPhoneQuartetProtocolMsgQuery extends AbstractDAO<CallPhoneQuartetProtocolMsgResp>{
	
//	/** 根据AppNo查询申请表姓名，申请产品名字，每月还款日，每月还款e
//	 * @param applicationNo
//	 * @return ApplicationPay
//	 */
	private static Logger logger = Logger.getLogger(CallPhoneQuartetProtocolMsgQuery.class);
	@Override
	public CallPhoneQuartetProtocolMsgResp mapping(ResultSet rs) throws SQLException {
		CallPhoneQuartetProtocolMsgResp resp = new CallPhoneQuartetProtocolMsgResp();
		if(rs != null){

			resp.setRealName(StringUtils.valueOf(rs.getObject("realname")));
			resp.setAppProName(StringUtils.valueOf(rs.getObject("productname")));
			resp.setCreditName(StringUtils.valueOf(rs.getObject("creditName")));
			resp.setCreditDay(StringUtils.valueOf(rs.getObject("creditDay")));
			resp.setUserName(StringUtils.valueOf(rs.getObject("username")));
			
		}else{
			return null;
		}
		return resp;
	}
	
	public CallPhoneQuartetProtocolMsgResp queryCallPhoneQuartet(CallPhoneQuartetProtocolMsgReq req) {
		
		List<String> list = new ArrayList<String>();
		
		list.add("creditName");
		list.add("creditDay");
		list.add("realname");
		list.add("username");
		list.add("productname");
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("applicationNo", req.getAppNo());
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.INPUT_APP, map);
		//记录日志
		RecordUtils.writeAction(logger, req.getAppNo(), sql); 
		return ConnManager.singleQuery(sql, this);
	}

}
