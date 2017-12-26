package com.hengyuan.hicash.domain.event.apply;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.mktApp.ApprovalRecord;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * @author mary.luo
 * 
 */
public class ApproveRecordEvent {

	private static Logger logger = Logger.getLogger(ApproveRecordEvent.class);

	public int createAppRecord(ApprovalRecord approvalRecord) {
		String appRecordSql = "INSERT INTO d_approval_record " + "("
				+ "applicationNo," + "approvalType," + "approvalAction,"
				+ "approvalDesc," + "approvalResult," + "create_date,"
				+ "create_user )" + "VALUES" + "('"
				+ approvalRecord.getApplicationNo() + "'," + "'"
				+ approvalRecord.getApprovalType() + "'," + "'"
				+ approvalRecord.getApprovalAction() + "'," + "'"
				+ approvalRecord.getApprovalDesc() + "'," + "'"
				+ approvalRecord.getApprovalResult() + "'," + "'"
				+ approvalRecord.getCreateDate() + "'," + "'"
				+ approvalRecord.getCreateUser() + "')";
		RecordUtils.writeAction(logger, null, appRecordSql);
		return ConnManager.executeUpdate(appRecordSql);

	}

	public void updateRecord(String id) {

		String sql = "update d_approval_record set approvalResult = 'RES4' where recordId = '"
				+ id + "'";
		ConnManager.executeUpdate(sql);
	}

}
