package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CallPhoneNumEntity;
import com.hengyuan.hicash.parameters.request.user.CallPhoneNumMsgReq;
import com.hengyuan.hicash.parameters.response.user.CallPhoneNumMsgResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 根据城市ID,页数，显示条数获取电话号码dao
 * 
 * @author lihua.Ren
 * @create date 2015-08-18
 *
 */
public class CallPhoneNumMsgQuery extends AbstractDAO<CallPhoneNumEntity> {

	private static Logger logger = Logger.getLogger(CallPhoneNumMsgQuery.class);
	private final static String QUERY_SQL = "select phone_num,inoruse from call_phone_num where 1=1";

	@Override
	public CallPhoneNumEntity mapping(ResultSet rs) throws SQLException {

		CallPhoneNumEntity callPhoneEntity = null;

		if (rs != null) {

			callPhoneEntity = new CallPhoneNumEntity();
			callPhoneEntity.setPhoneNum(rs.getString("phone_num"));
			callPhoneEntity.setIsoruse(rs.getString("inoruse"));

		}

		return callPhoneEntity;
	}

	/**
	 * 获取同款商品
	 * 
	 * @param proShowReq
	 * @return
	 */
	public CallPhoneNumMsgResp queryPhoneList(CallPhoneNumMsgReq paramReq) {
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND city_id = '"+ paramReq.getCityCode() +"'  AND inoruse = '0'");
		// 记录日志
		RecordUtils.writeAction(logger, null, querySql.toString());

		CallPhoneNumMsgResp phoneListResp = null;
		// 每页显示的行数
		Integer maxLine = Integer.valueOf(paramReq.getPageNum());
		// 总记录数
		List<CallPhoneNumEntity> merProCount = ConnManager.executeQuery(
				querySql.toString(), this);
		// 总页数
		Integer countPage = countPage(maxLine, merProCount.size());

		if (countPage != 0) {
			phoneListResp = new CallPhoneNumMsgResp();

			// 当前页
			Integer curPage = sumCurPage(paramReq.getPageIndex(), countPage);
			// 查询起始行
			Integer firstLine = (curPage - 1) * maxLine;

			String listSql = querySql.toString() + " LIMIT " + firstLine + ","
					+ maxLine + "";
			// 列表集合
			List<CallPhoneNumEntity> proList = ConnManager.executeQuery(listSql,
					this);

			phoneListResp.setPhoneNums(proList);
//			proListResp.setCurPage(curPage + "");
//			proListResp.setCountPage(!merProCount.isEmpty() ? countPage : 0);
		}

		return phoneListResp;
	}
	public CallPhoneNumEntity queryPhoneList(String phoneNum) {

					CallPhoneNumEntity phoneNumEntity = ConnManager.singleQuery(QUERY_SQL,
							this);
					// 记录日志
					RecordUtils.writeAction(logger, null, QUERY_SQL.toString());
					return phoneNumEntity;
					
	
	
}
//根据手机号码查看是否可用
	public CallPhoneNumEntity queryIsorUse(String phone){
		String QUERY_SQL1 = "select phone_num,inoruse from call_phone_num where phone_num='"+phone+"'";
		CallPhoneNumEntity entity = ConnManager.singleQuery(QUERY_SQL1.toString(), this);
		return entity;
	}

}
