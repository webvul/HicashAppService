package com.hengyuan.hicash.domain.event.apply;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CouponRecord;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 
 * @author teng
 *
 */
public class CouponRecordEvent {

	private static Logger logger = Logger.getLogger(CouponRecordEvent.class);

	public int saveCouponRecord(CouponRecord record) {
		String sql = "INSERT INTO coupon_record " + "(" + "coupon_code_id,"
				+ "user_id," + "order_id," + "add_time," + "aux_info" + ")"
				+ "VALUES" + "('" + record.getCoupon_code_id() + "'," + "'"
				+ record.getUser_id() + "'," + "'" + record.getOrder_id()
				+ "'," + "NOW()," + "'" + record.getAux_info() + "'" + ")";

		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeUpdate(sql);

	}

}
