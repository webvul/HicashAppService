package com.hengyuan.hicash.domain.service.param;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.BankBinEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * @author fish
 *
 * @date 2017年2月14日 上午10:31:43
 */
public class BankBinQuery extends AbstractDAO<BankBinEntity> {

	@Override
	public BankBinEntity mapping(ResultSet rs) throws SQLException {

		BankBinEntity bank = new BankBinEntity();

		if (rs != null) {
			bank.setBankCode(StringUtils.valueOf(rs.getObject("BANK_CODE")));
			bank.setBankName(StringUtils.valueOf(rs.getObject("BANK_NAME")));
			bank.setBankBin(StringUtils.valueOf(rs.getObject("BANK_BIN")));
		} else {
			bank = null;
		}
		return bank;
	}
	

	public List<BankBinEntity> queryBankBins(String bankCard) {
		String sql = "SELECT * FROM SYS_BANK_BIN A WHERE  A.BANK_BIN=LEFT('" + bankCard + "',9) UNION ALL "
				+ "SELECT * FROM SYS_BANK_BIN A WHERE  A.BANK_BIN=LEFT('" + bankCard + "',8) UNION ALL "
				+ "SELECT * FROM SYS_BANK_BIN A WHERE  A.BANK_BIN=LEFT('" + bankCard + "',7) UNION ALL "
				+ "SELECT * FROM SYS_BANK_BIN A WHERE  A.BANK_BIN=LEFT('" + bankCard + "',6) UNION ALL "
				+ "SELECT * FROM SYS_BANK_BIN A WHERE  A.BANK_BIN=LEFT('" + bankCard + "',5) UNION ALL "
				+ "SELECT * FROM SYS_BANK_BIN A WHERE  A.BANK_BIN=LEFT('" + bankCard + "',4) UNION ALL "
				+ "SELECT * FROM SYS_BANK_BIN A WHERE  A.BANK_BIN=LEFT('" + bankCard + "',3)";
		RecordUtils.writeAction(logger, null, sql.toString());
		return ConnManager.executeQuery(sql.toString(), this);
	}
}
