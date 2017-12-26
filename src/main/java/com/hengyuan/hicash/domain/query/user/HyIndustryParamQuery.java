package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.HyIndustryParam;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class HyIndustryParamQuery extends AbstractDAO<HyIndustryParam> {

	@Override
	public HyIndustryParam mapping(ResultSet rs) throws SQLException {
		HyIndustryParam HyIndustryParam = new HyIndustryParam();
		if(rs!=null){
			HyIndustryParam.setHyIndustryCode(StringUtils.valueOf(rs.getObject("HY_INDUSTRY_CODE")));
			HyIndustryParam.setHyIndustryName(StringUtils.valueOf(rs.getObject("HY_INDUSTRY_NAME")));
		}else{
			HyIndustryParam=null;
		}
		return HyIndustryParam;
	}
	/**
	 * 根据行业代码查询行业中文名称
	 * 
	 * @param userName
	 * @return
	 */
	public HyIndustryParam queryIndustryName(String industryCode) {

		String sql = "SELECT * FROM hy_industry_param WHERE 1 = 1 AND HY_INDUSTRY_CODE = '"
				+ industryCode+"'";

		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);
	}
}
