package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.ReceiveAddressEvent;
import com.hengyuan.hicash.domain.query.user.AddressQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustReceiveAddressEntity;
import com.hengyuan.hicash.exception.UpdateAddressException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CustReceiveAddressReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CustAddressResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 修改收货地址信息默认地址
 * 
 * @author ding
 * 
 */
public class UpdateAddressDefaultService implements RespService {

	private static Logger logger = Logger
			.getLogger(UpdateAddressDefaultService.class);

	private String resultCode = "";

	private ReceiveAddressEvent receiveAddressEvent = new ReceiveAddressEvent();

	private AddressQuery addressQuery = new AddressQuery();
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CustReceiveAddressReq req = (CustReceiveAddressReq) parmRequest;
		CustAddressResp resp = new CustAddressResp();
		CustReceiveAddressEntity entity = null;
		try {
			ConnManager.beginTransaction();
			//先全部为非默认，再选中改为默认状态
			req.setIs_default("0");
			entity = addressQuery.queryAddressById(req.getId());
			req.setCust_user(entity.getCust_user());
			receiveAddressEvent.UpdateIsDefauleAll(req);
			req.setIs_default("1");
			receiveAddressEvent.UpdateIsDefaule(req);
			ConnManager.commit();
			resultCode = ResultCodes.NORMAL;
		
		
		} catch (UpdateAddressException e) {
			ConnManager.rollback();
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
