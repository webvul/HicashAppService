package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.apply.InputAppPayEvent;
import com.hengyuan.hicash.domain.query.app.TempApplyQuery;
import com.hengyuan.hicash.domain.query.param.BankQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.exception.UpdateInputAppException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.BankCardInputAppValReq;
import com.hengyuan.hicash.parameters.request.user.CmbcIdentifyConfirmReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.BankCardInputAppResp;
import com.hengyuan.hicash.parameters.response.user.CmbcIdentifyConfirmResp;

/**
 * 根据订单号修改绑定的代扣银行卡
 * 
 * @author Lihua.Ren
 * @createDate 2015-12-04
 *
 */
public class BankCardInputAppValService implements RespService {

	private static Logger logger = Logger
			.getLogger(BankCardInputAppValService.class);

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		BankCardInputAppValReq updateMsgReq = (BankCardInputAppValReq) parmRequest;
		BankCardInputAppResp updateMsgResp = new BankCardInputAppResp();

		String resultCode = "";
		try {

			CmbcIdentifyConfirmReq confirmReq = new CmbcIdentifyConfirmReq();
			confirmReq.setUserName(updateMsgReq.getUserName());// 用户名
			confirmReq.setAccountNo(updateMsgReq.getAccNo());// 账号
			confirmReq.setAccountName(updateMsgReq.getAccName());// 账户名称
			String bankName=queryBankName(updateMsgReq.getBankNo());
//			confirmReq.setBankName(bankName);// 银行名称
			confirmReq.setMobileNo(updateMsgReq.getMobile());// 手机号码
			confirmReq.setCertNo(updateMsgReq.getCertNo());// 身份证号
			CmbcIdentifyConfirmService confirmService = new CmbcIdentifyConfirmService();
			CmbcIdentifyConfirmResp confirmResp = (CmbcIdentifyConfirmResp) confirmService
					.responseValue(confirmReq);
			logger.info("根据订单号修改验证通过的绑定代扣银行卡返回结果："
			+ confirmResp.toJson() + "订单号为："
			+ updateMsgReq.getAccName());
			if (confirmResp.getResultCode().equals(ResultCodes.NORMAL)) {
				if (confirmResp.getValStatus().trim()
						.equals(Consts.IDENTIFY_STATUS_SUCC)) {
					InputAppPayEvent event = new InputAppPayEvent();
					event.updateInputAppCard(updateMsgReq.getSerialNo(),
							updateMsgReq.getAccNo(),
							updateMsgReq.getBankNo());
					resultCode = ResultCodes.NORMAL;
				} else {
					resultCode = ResultCodes.UPDATE_INPUTAPP_CARD_APP_VAL_FALSE;
				}
			} else {
				resultCode = ResultCodes.UPDATE_INPUTAPP_CARD_APP_VAL_EXCEPTION;
			}

		} catch (UpdateInputAppException e) {

			resultCode = ResultCodes.UPDATE_INPUTAPP_CARD_EXCEPTION;
		}

		catch (Exception e) {

			resultCode = ResultCodes.CALL_PHONE_DATA_SAVE_EXCEPTION;
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
	/**
	 * 获取代扣银行名称
	 * 
	 * @param bankCode
	 * @return
	 */
	private String queryBankName(String bankCode) {

		BankQuery bankQuery = new BankQuery();
		BankEntity entity = bankQuery.queryProxyBankByCode(bankCode);
		if (entity != null) {
			return entity.getBankName();
		}
		return "";
	}
}
