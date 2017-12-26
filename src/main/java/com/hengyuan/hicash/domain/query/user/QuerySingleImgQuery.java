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
import com.hengyuan.hicash.parameters.response.user.QuerySingleImgResp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * @author Administrator
 *
 */
public class QuerySingleImgQuery extends AbstractDAO<QuerySingleImgResp> {

	private static Logger logger = Logger.getLogger(QuerySingleImgResp.class);

	@Override
	public QuerySingleImgResp mapping(ResultSet rs) throws SQLException {
		
		QuerySingleImgResp resp = new QuerySingleImgResp();
		if (rs != null) {

			resp.setIdcardFrontUrl(StringUtils.valueOf(rs
					.getObject("IDCARD_FRONT")));
			resp.setUserIdcardFrontUrl(StringUtils.valueOf(rs
					.getObject("USER_IDCARD_FRONT")));
			resp.setIdcardVersoUrl(StringUtils.valueOf(rs
					.getObject("IDCARD_VERSO")));
			resp.setSchoolCardFrontUrl(StringUtils.valueOf(rs
					.getObject("SCHOOL_CARD_FRONT")));
			resp.setSchoolCardVersoUrl(StringUtils.valueOf(rs
					.getObject("SCHOOL_CARD_VERSO")));
			resp.setStuCardFrontUrl(StringUtils.valueOf(rs
					.getObject("STU_CARD_FRONT")));
			resp.setStuPhotoInfoUrl(StringUtils.valueOf(rs
					.getObject("STU_PHOTO_INFO")));
			resp.setStuRegistInfoUrl(StringUtils.valueOf(rs
					.getObject("STU_REGIST_INFO")));

			/* 小图url */
			resp.setIdcardFrontUrlThum(StringUtils.valueOf(rs
					.getObject("IDCARD_FRONT_THUM")));
			resp.setUserIdcardFrontUrlThum(StringUtils.valueOf(rs
					.getObject("USER_IDCARD_FRONT_THUM")));
			resp.setIdcardVersoUrlThum(StringUtils.valueOf(rs
					.getObject("IDCARD_VERSO_THUM")));
			resp.setSchoolCardFrontUrlThum(StringUtils.valueOf(rs
					.getObject("SCHOOL_CARD_FRONT_THUM")));
			resp.setSchoolCardVersoUrlThum(StringUtils.valueOf(rs
					.getObject("SCHOOL_CARD_VERSO_THUM")));
			resp.setStuCardFrontUrlThum(StringUtils.valueOf(rs
					.getObject("STU_CARD_FRONT_THUM")));
			resp.setStuPhotoInfoUrlThum(StringUtils.valueOf(rs
					.getObject("STU_PHOTO_INFO_THUM")));
			resp.setStuRegistInfoUrlThum(StringUtils.valueOf(rs
					.getObject("STU_REGIST_INFO_THUM")));
		} else {
			return null;
		}
		return resp;
	}

	public QuerySingleImgResp querySingleResp(String userName, String appNo) {

		List<String> list = new ArrayList<String>();

		list.add("IDCARD_FRONT");
		list.add("USER_IDCARD_FRONT");
		list.add("IDCARD_VERSO");
		
		list.add("IDCARD_FRONT_THUM");
		list.add("USER_IDCARD_FRONT_THUM");
		list.add("IDCARD_VERSO_THUM");

		
		list.add("STU_CARD_FRONT");
		list.add("STU_PHOTO_INFO");
		list.add("STU_REGIST_INFO");
		list.add("SCHOOL_CARD_FRONT");
		list.add("SCHOOL_CARD_VERSO");
		
		list.add("STU_CARD_FRONT_THUM");
		list.add("STU_PHOTO_INFO_THUM");
		list.add("STU_REGIST_INFO_THUM");
		list.add("SCHOOL_CARD_FRONT_THUM");
		list.add("SCHOOL_CARD_VERSO_THUM");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("username", userName);
		map.put("temp_application_no", appNo);
		String sql = MapAssemForSql.getSelectSql(list,
				TableConsts.TEMP_APPLY_INFO, map);
		// 记录日志
		RecordUtils.writeAction(logger, userName, sql);
		return ConnManager.singleQuery(sql, this);
	}

}
