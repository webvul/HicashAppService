package com.hengyuan.hicash.domain.service.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.CallPhoneDataNumUpEvent;
import com.hengyuan.hicash.domain.query.app.ApplicationPayQuery;
import com.hengyuan.hicash.domain.query.app.TempApplyQuery;
import com.hengyuan.hicash.domain.query.mer.MerProductQuery;
import com.hengyuan.hicash.domain.query.mktApp.PayFlowNoQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.entity.mer.MerProductEntity;
import com.hengyuan.hicash.exception.GenerateFlowNoException;
import com.hengyuan.hicash.exception.MerProductNotFoundException;
import com.hengyuan.hicash.exception.QueryFlowNoException;
import com.hengyuan.hicash.exception.RepeatApplyMDException;
import com.hengyuan.hicash.exception.UpdateInfoException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CallPhoneDataNumUpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CallPhoneDataNumUpdateResp;

/**
 * 
 * 电信专案,保存套餐号码逻辑类
 * 
 * @author LiHua.Ren
 * @createDate 2015-08-18
 */
public class CallPhoneDataNumUpdateService implements RespService {

	private static Logger logger = Logger
			.getLogger(CallPhoneDataNumUpdateService.class);

	private CallPhoneDataNumUpEvent updateMsgEvent = new CallPhoneDataNumUpEvent();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CallPhoneDataNumUpdateReq updateMsgReq = (CallPhoneDataNumUpdateReq) parmRequest;
		CallPhoneDataNumUpdateResp updateMsgResp = new CallPhoneDataNumUpdateResp();
		ApplicationPayQuery appQuery = new ApplicationPayQuery();
		String resultCode = "";
		String resultMsg = ""; // 返回消息
		try {

			// 新的单子插入
			String appFlowNo = "";
			// 生成业务流水号
			PayFlowNoQuery pfnQuery = new PayFlowNoQuery();

			try {
				appFlowNo = pfnQuery.generateFlowNo("CASH");

			} catch (GenerateFlowNoException e) {
				e.printStackTrace();
			} catch (QueryFlowNoException e) {

				e.printStackTrace();
			}
			// 用户满足，从未创建过订单，或者订单被拒绝，订单取消，才可以创建临时订单
			List<ApplicationPay> appEntityList = appQuery.queryAppPayByUser(updateMsgReq
					.getUserName());
			if (appEntityList != null && appEntityList.size() > 0) {	
				updateMsgResp
						.setResultCode(ResultCodes.STU_APP_APPLY_NOT_OPERO);
				updateMsgResp.setAppFlowNo(appFlowNo);
				return updateMsgResp;
			}
			MerProductQuery query = new MerProductQuery();
			MerProductEntity entity = query.queryMerProByProId(updateMsgReq
					.getProMerId());
			if (entity != null) {
				updateMsgReq.setProColor(entity.getProName() + "-"
						+ updateMsgReq.getProColor());// 临时表中，商品名字为，商品名字+颜色
			} else {
				throw new MerProductNotFoundException();

			}
			// 新逻辑如果业务流水号为空则一直插入

			TempApplyEntity tempEntity = queryuNameExist(
					updateMsgReq.getUserName(), updateMsgReq.getAppFlowNo(),
					updateMsgReq.getAppType());

			if (tempEntity != null) {
				if (tempEntity.getCreateAppFlg().equals("0") == false) {
					updateMsgResp
							.setResultCode(ResultCodes.STU_APP_APPLY_NOT_OPERO);
					// 如果订单已完成，生成新的订单号返回前端
					updateMsgResp.setAppFlowNo(appFlowNo);
					return updateMsgResp;
				}
			}
			ConnManager.beginTransaction();
			if (tempEntity == null) {

				updateMsgReq.setAppFlowNo(appFlowNo);
				updateMsgEvent.saveCallDataNum(updateMsgReq);
//				updateMsgEvent.updatePhoneStatue(updateMsgReq);
				updateMsgResp.setAppFlowNo(appFlowNo);
			} else {// 业务流水号非空，则更改
				updateMsgReq.setAppFlowNo(updateMsgReq.getAppFlowNo());
				updateMsgEvent.updateDataNumPro(updateMsgReq);
//				updateMsgEvent.updatePhoneStatue(updateMsgReq);
				updateMsgResp.setAppFlowNo(updateMsgReq.getAppFlowNo());
			}
			ConnManager.commit();
			resultCode = ResultCodes.NORMAL;

		} catch (UpdateInfoException e) {
			ConnManager.rollback();
			resultCode = ResultCodes.CALL_PHONE_DATA_SAVE_FAIL;
		} catch (Exception e) {
			ConnManager.rollback();
			resultCode = ResultCodes.CALL_PHONE_DATA_SAVE_EXCEPTION;
		} finally {
			ConnManager.closeConn();
		}

		updateMsgResp.setResultCode(resultCode);
		updateMsgResp.setResultMsg(resultMsg);
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
