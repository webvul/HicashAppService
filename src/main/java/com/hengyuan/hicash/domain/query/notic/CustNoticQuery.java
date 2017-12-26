package com.hengyuan.hicash.domain.query.notic;

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
import com.hengyuan.hicash.parameters.response.notic.NoticeInfoResp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class CustNoticQuery  extends AbstractDAO<NoticeInfoResp>{
	private static Logger logger = Logger.getLogger(CustNoticQuery.class);
	@Override
	public NoticeInfoResp mapping(ResultSet rs) throws SQLException {
		NoticeInfoResp resp = null;
		if (rs != null) {
			resp = new NoticeInfoResp();
			resp.setId(StringUtils.valueOf(rs.getObject("ID")));
			resp.setContent(StringUtils.valueOf(rs.getObject("CONTENT")));
			resp.setTitle(StringUtils.valueOf(rs.getObject("TITLE")));
			resp.setSmallImg(StringUtils.valueOf(rs.getObject("SMALL_IMG")));
			resp.setShowImg(StringUtils.valueOf(rs.getObject("SHOW_IMG")));
			resp.setType(StringUtils.valueOf(rs.getObject("TYPE")));
			resp.setNoticeFrom(StringUtils.valueOf(rs.getObject("noticeFrom")));
			resp.setNoticeTime(rs.getDate("noticepub_Time"));
			resp.setCreateUser(StringUtils.valueOf(rs.getObject("create_user")));
			
		}else {
			System.out.println("公告为空");
		}
		return resp;
	}
	
	
//	public NoticeInfoResp queryNotice() {
//		
//		NoticeInfoResp resp = new NoticeInfoResp();
//		
//		List<NoticDetilResp> detilResps = queryCustNotics();
//		
//		if (detilResps != null && detilResps.size() > 0) {
//			resp.setResultCode(ResultCodes.NORMAL);
//			resp.setDetilResps(detilResps);
//		}else {
//			resp.setResultCode(ResultCodes.NOTICE_DETAIL_NOT_FOUND);
//		}
//		
//		return resp;
//	}
	
	public NoticeInfoResp queryCustNotics(String id) {
		
		List<String> list = new ArrayList<String>();
		list.add("ID");
		list.add("CONTENT");
		list.add("TITLE");
		list.add("SMALL_IMG");
		list.add("SHOW_IMG");
		list.add("TYPE");
		list.add("noticeFrom");
		list.add("noticepub_Time");
		list.add("create_user");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("enabled", "1");
		map.put("ID", id);
		
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_NOTICE, map);
		//记录日志
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);
	}
	

}
