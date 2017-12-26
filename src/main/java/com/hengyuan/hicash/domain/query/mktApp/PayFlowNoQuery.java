package com.hengyuan.hicash.domain.query.mktApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.GenerateFlowNoException;
import com.hengyuan.hicash.exception.QueryFlowNoException;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * 查询申请件业务流水号
 * 
 * @author Andy.Niu
 * @create date 2014-07-16
 * 
 */
public class PayFlowNoQuery extends AbstractDAO<String> {
	
	private static Logger logger = Logger.getLogger(PayFlowNoQuery.class);

	@Override
	public String mapping(ResultSet rs) throws SQLException {
		return rs.getString("FLOWNO");
	}

	/**
	 * 生成业务流水号
	 * 
	 * @param applyType
	 * @param productType
	 * @return
	 * @throws GenerateFlowNoException
	 * @throws QueryFlowNoException
	 * @author Andy.Niu
	 * @create date 2014-07-16
	 */
	public String generateFlowNo(String applyType,String productType)
			throws GenerateFlowNoException, QueryFlowNoException {

		StringBuffer appFlowNo = new StringBuffer();

		/* 获取申请件前缀 */
		String prefix = Consts.APPFLOW_TYPE_3C.equals(applyType) ? Consts.APPFLOW_TYPE_3C_INDEX
				: Consts.APPFLOW_TYPE_CASH.equals(applyType) ? Consts.APPFLOW_TYPE_CASH_INDEX: Consts.APPFLOW_TYPE_DDSJ.equals(applyType)? Consts.APPFLOW_TYPE_DDSJ_INDEX: null;

		if (prefix != null) {

			/* 获取流水号第一位 */
			appFlowNo.append(prefix);

			/* 创建序列号第2到7位 */
			appFlowNo.append(DateUtils.getDateStrByFlow(new Date()));

			/* 从第三位插入产品类型代码 */
			appFlowNo.insert(3, productType);

			/* 添加当天唯一序列号 */
			appFlowNo.append(StringUtils.lampLeft(queryFlowNo(Consts.APPFLOW_SEQ), "0", 5));

		} else {

			throw new GenerateFlowNoException();
		}

		return appFlowNo.toString();
	}

	/**
	 * 获取当天序列号
	 * 
	 * @return 序列号
	 * @throws QueryFlowNoException
	 * @author Andy.Niu
	 * @create date 2014-07-16
	 */
	public String queryFlowNo() throws QueryFlowNoException {

		String flowNoSql = "select nextval('" + Consts.TEMP_APPFLOW_SEQ + "') AS FLOWNO";
		
		RecordUtils.writeAction(logger, null, flowNoSql);
		String flowNo = ConnManager.singleQuery(flowNoSql, this);

		if (flowNo != null) {
			return flowNo;
		} else {
			throw new QueryFlowNoException();
		}

	}
	
	
	/**
	 * 获取inputAPP自增长序列号
	 * 
	 * @return 序列号
	 * @throws QueryFlowNoException
	 * @author Mary.Luo
	 * @create date 2014-07-21
	 */
	public String queryFlowNo(String seqName) throws QueryFlowNoException {

		String flowNoSql = "select nextval('" + seqName+ "') AS FLOWNO";
		
		RecordUtils.writeAction(logger, null, flowNoSql);
		String flowNo = ConnManager.singleQuery(flowNoSql, this);

		if (flowNo != null) {
			return flowNo;
		} else {
			throw new QueryFlowNoException();
		}

	}
	/**
	 * 生成业务流水号（秒贷用）
	 * 
	 * @param applyType
	 * @param productType
	 * @return
	 * @throws GenerateFlowNoException
	 * @throws QueryFlowNoException
	 * @author Andy.Niu
	 * @create date 2014-07-16
	 * CASH
	 */
	public String generateFlowNo(String applyType) throws GenerateFlowNoException, QueryFlowNoException {

		StringBuffer appFlowNo = new StringBuffer();

		/* 获取申请件前缀 */
		String prefix = Consts.APPFLOW_TYPE_3C.equals(applyType) ? Consts.APPFLOW_TYPE_3C_INDEX
				: Consts.APPFLOW_TYPE_CASH.equals(applyType) ? Consts.APPFLOW_TYPE_CASH_INDEX : null;

		if (prefix != null) {

			/* 获取流水号第一位 */
			appFlowNo.append(prefix);

			/* 创建序列号第2到7位 */
			appFlowNo.append(DateUtils.getDateStrByFlow(new Date()));

			/* 从第三位插入产品类型代码 */
			appFlowNo.insert(3, "01");

			/* 添加当天唯一序列号 */
			appFlowNo.append(StringUtils.lampLeft(queryFlowNo(), "0", 5));

		} else {

			throw new GenerateFlowNoException();
		}

		return appFlowNo.toString();
	}
	/**
	 * 生成业务流水号
	 * 
	 * @param applyType
	 * @param productType
	 * @return
	 * @throws GenerateFlowNoException
	 * @throws QueryFlowNoException
	 * @author Andy.Niu
	 * @create date 2014-07-16
	 */
	public String queryAppFlowNoBlue(String applyType,String seqName)
			throws GenerateFlowNoException, QueryFlowNoException {

		StringBuffer appFlowNo = new StringBuffer();

		/* 获取申请件前缀 */
		String prefix = Consts.APPFLOW_TYPE_3C.equals(applyType) ? Consts.APPFLOW_TYPE_3C_INDEX
				: Consts.APPFLOW_TYPE_CASH.equals(applyType) ? Consts.APPFLOW_TYPE_CASH_INDEX : null;

		if (prefix != null) {

			/* 获取流水号第一位 */
			appFlowNo.append(prefix);

			/* 创建序列号第2到7位 */
			appFlowNo.append(DateUtils.getDateStrByFlow(new Date()));

			/* 从第三位插入产品类型代码 */
			appFlowNo.insert(3, Consts.APPNO_GENERATE_SEQ);

			/* 添加当天唯一序列号 */
			appFlowNo.append(StringUtils.lampLeft(queryFlowNo(seqName), "0", 5));

		} else {

			throw new GenerateFlowNoException();
		}

		return appFlowNo.toString();
	}
}
