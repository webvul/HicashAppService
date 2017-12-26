package com.hengyuan.hicash.domain.query.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.NoahValidIdentityEntity;

public class NoahValidIdentityQuery extends AbstractDAO<NoahValidIdentityEntity> {

	@Override
	public NoahValidIdentityEntity mapping(ResultSet rs) throws SQLException {
		NoahValidIdentityEntity nve = new NoahValidIdentityEntity();
		if (rs != null) {
			nve.setId(rs.getInt("ID"));
			nve.setCreateTime(rs.getDate("CREATE_TIME"));
			nve.setValidDateTime(rs.getDate("VALID_DATETIME"));
			nve.setBankCardNum(rs.getString("BANK_CARD_NUM"));
			nve.setRealName(rs.getString("REAL_NAME"));
			nve.setBankCode(rs.getString("BANK_CODE"));
			nve.setIdentityNo(rs.getString("IDENTITY_NO"));
			nve.setMobleNo(rs.getString("MOBILE_NO"));
			nve.setBankReturnCode(rs.getString("BANK_RETURN_CODE"));
			nve.setBankReturnMsg(rs.getString("BANK_RETURN_MSG"));
			nve.setRespCode(rs.getString("RESP_CODE"));
			nve.setRespMsg(rs.getString("RESP_MSG"));
			nve.setSerialNo(rs.getString("SERIAL_NO"));
			nve.setUserName(rs.getString("USER_NAME"));
			nve.setBankName(rs.getString("BANK_NAME"));
			nve.setTradeCode(rs.getString("TRADE_CODE"));
			nve.setTradeDesc(rs.getString("TRADE_DESC"));
		}
		return nve;
	}

	/**
	 * 查询诺亚金通实名认证结果实体类
	 * 
	 * @param bankCardNum
	 * @param realName
	 * @param identityNo
	 * @param mobleNo
	 * @return
	 */
	public NoahValidIdentityEntity getNoahValidIdentity(String bankCardNum, String realName, String identityNo){
		String sql ="SELECT ID, CREATE_TIME, VALID_DATETIME, BANK_CARD_NUM,REAL_NAME,BANK_CODE, "+
				" IDENTITY_NO, MOBILE_NO,BANK_RETURN_CODE,BANK_RETURN_MSG," +
				" RESP_CODE, RESP_MSG, SERIAL_NO, USER_NAME,BANK_NAME," +
				" TRADE_CODE, TRADE_DESC FROM NOAH_VALID_IDENTITY WHERE BANK_CARD_NUM = '" + bankCardNum +
				"' AND REAL_NAME = '"+ realName+ "' AND IDENTITY_NO ='" +identityNo + "' AND RESP_CODE = '01'"  +
				" ORDER BY CREATE_TIME DESC LIMIT 1";
			System.out.println("appservice ===========================查询:" + sql);
		return ConnManager.singleQuery(sql, this);
		
	}
	
	
	/**
	 * 查询诺亚金通实名认证结果
	 * 
	 * @param bankCardNum
	 * @param realName
	 * @param identityNo
	 * @param mobleNo
	 * @return
	 */
	public NoahValidIdentityEntity queryNoahSucc(String bankCardNum, String realName, String identityNo,String mobileNo){
		String sql ="SELECT ID, CREATE_TIME, VALID_DATETIME, BANK_CARD_NUM,REAL_NAME,BANK_CODE, "+
				" IDENTITY_NO, MOBILE_NO,BANK_RETURN_CODE,BANK_RETURN_MSG," +
				" RESP_CODE, RESP_MSG, SERIAL_NO, USER_NAME,BANK_NAME," +
				" TRADE_CODE, TRADE_DESC FROM NOAH_VALID_IDENTITY WHERE BANK_CARD_NUM = '" + bankCardNum +
				"' AND REAL_NAME = '"+ realName+ "' AND IDENTITY_NO ='" +identityNo + "' AND MOBILE_NO='"+mobileNo+"' AND RESP_CODE = '01'"  +
				" ORDER BY CREATE_TIME DESC LIMIT 1";
			logger.info("根据四要素查询诺亚是否验证成功 ===========================查询:" + sql);
		return ConnManager.singleQuery(sql, this);
		
	}
}
