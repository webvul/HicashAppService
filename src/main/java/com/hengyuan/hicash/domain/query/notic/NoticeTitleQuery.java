package com.hengyuan.hicash.domain.query.notic;

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
import com.hengyuan.hicash.parameters.response.notic.NoticDetilResp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.StringUtils;

public class NoticeTitleQuery  extends AbstractDAO<NoticDetilResp>{

	@Override
	public NoticDetilResp mapping(ResultSet rs) throws SQLException {
		NoticDetilResp resp = new NoticDetilResp();
		if (rs != null) {
			resp.setNoticeId(StringUtils.valueOf(rs.getObject("ID")));
			resp.setTitle(StringUtils.valueOf(rs.getObject("TITLE")));
			resp.setNoticeTime(rs.getDate("noticepub_Time"));
			
		}else {
			System.out.println("公告为空");
		}
		return resp;
	}
	
	
	/**
	 * 查询公告标题
	 */
	public List<NoticDetilResp> queryNoticeTitle() {
		
		List<String> list = new ArrayList<String>();
		list.add("ID");
		list.add("TITLE");
		list.add("noticepub_Time");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("enabled", Consts.NOTICE_QUERY_TYPE);
		String orderBysql = "order by noticepub_Time desc";
		
		String sql = MapAssemForSql.getSelectSqlByGroupOrOrder(list, TableConsts.CUST_NOTICE, map,orderBysql);
		
		return ConnManager.executeQuery(sql, this);
	}
	

}
