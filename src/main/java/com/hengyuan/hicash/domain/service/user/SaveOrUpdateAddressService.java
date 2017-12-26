package com.hengyuan.hicash.domain.service.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.ReceiveAddressEvent;
import com.hengyuan.hicash.domain.query.user.AccountQuery;
import com.hengyuan.hicash.domain.query.user.CustLimitQuery;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.query.user.TransactionQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.AccountEntity;
import com.hengyuan.hicash.entity.user.CustLimitEntity;
import com.hengyuan.hicash.entity.user.TransactionEntity;
import com.hengyuan.hicash.exception.SaveAddressException;
import com.hengyuan.hicash.exception.UpdateAddressException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CustReceiveAddressReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CustAddressResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 新增或者修改收货地址信息
 * 
 * @author ding
 * 
 */
public class SaveOrUpdateAddressService implements RespService {

	private static Logger logger = Logger
			.getLogger(SaveOrUpdateAddressService.class);

	private String resultCode = "";

	private ReceiveAddressEvent receiveAddressEvent = new ReceiveAddressEvent();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CustReceiveAddressReq req = (CustReceiveAddressReq) parmRequest;
		CustAddressResp resp = new CustAddressResp();

		try {
			// 带id修改
			if (RegexValidate.isNull(req.getId())) {

				receiveAddressEvent.createAddress(req);

			} else {
				receiveAddressEvent.UpdateAddress(req);
			}
			resultCode = ResultCodes.NORMAL;
		} catch (SaveAddressException e) {
			resultCode = ResultCodes.ADD_ADRESS_EXCEPTION;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.SAVEAPPACCT_EXCEPTION1, e);
		
		} catch (UpdateAddressException e) {
			resultCode = ResultCodes.UPDATE_ADDRESS_EXCCEPTION;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.UPDATE_ADDRESS_EXCCEPTION, e);
		}finally {
			ConnManager.closeConn();
		}

		resp.setResultCode(resultCode);

		return resp;
	}

}
