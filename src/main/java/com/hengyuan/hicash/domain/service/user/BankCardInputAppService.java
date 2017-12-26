package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.apply.InputAppPayEvent;
import com.hengyuan.hicash.domain.event.user.CallPhoneDataNumUpEvent;
import com.hengyuan.hicash.domain.query.app.TempApplyQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.exception.UpdateInputAppException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.BankCardInputAppReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.BankCardInputAppResp;

/**
 * 
 * 电信专案,保存套餐号码逻辑类
 * 
 * @author LiHua.Ren
 * @createDate 2015-08-18
 */
public class BankCardInputAppService implements RespService {

	private static Logger logger = Logger
			.getLogger(BankCardInputAppService.class);



	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		BankCardInputAppReq updateMsgReq = (BankCardInputAppReq) parmRequest;
		BankCardInputAppResp updateMsgResp = new BankCardInputAppResp();

		String resultCode = "";

		try {
			InputAppPayEvent event=new InputAppPayEvent();
			event.updateInputAppCard(updateMsgReq.getAppNo(), updateMsgReq.getBankCard(), updateMsgReq.getOpenBank());
	
			resultCode = ResultCodes.NORMAL;

		} catch (UpdateInputAppException e) {
			ConnManager.rollback();
			resultCode = ResultCodes.UPDATE_INPUTAPP_CARD_EXCEPTION;
		} 
		
		catch (Exception e) {
			ConnManager.rollback();
			resultCode = ResultCodes.CALL_PHONE_DATA_SAVE_EXCEPTION;
		} 
		
		finally {
			ConnManager.closeConn();
		}

		updateMsgResp.setResultCode(resultCode);
		return updateMsgResp;
	}

	/**
	 * 查询是否存在订单
	 * 
	 * @param appNo
	 * @return
	 */
	public TempApplyEntity queryuNameExist(String uname, String tempAppNo,
			String isDx) {

		TempApplyQuery tempQuery = new TempApplyQuery();
		return tempQuery.queryTempApplyByDX(uname, tempAppNo, isDx);
	}

}
