package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.parameters.request.user.FaceRecognitionReq;

/**
 * 
 * @author fish
 *
 * @date 2017年2月22日 上午11:32:45
 */
public class FaceRecognitionQuery extends AbstractDAO<String> {

	public int query(FaceRecognitionReq req) throws SQLException {
		String sql = "SELECT COUNT(*) FROM HY_SCORE_RECORD WHERE  ID_NO = '" + req.getIdNo() + "' AND "
				+ "ABS(TIMESTAMPDIFF(MINUTE ,NOW(),CREATE_DATE)) < 10";

		ResultSet rs = ConnManager.getConn().createStatement().executeQuery(sql);
		int value = 0;
		if (rs.next()) {
			value = rs.getInt(1);
		}
		return value;
	}

	@Override
	public String mapping(ResultSet rs) throws SQLException {
		if (rs != null) {
			return ResultCodes.NORMAL;
		}
		return null;
	}

}
