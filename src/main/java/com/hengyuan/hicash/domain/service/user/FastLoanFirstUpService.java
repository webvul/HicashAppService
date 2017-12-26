package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.FastLoanFirstUpEvent;
import com.hengyuan.hicash.domain.query.app.TempApplyQuery;
import com.hengyuan.hicash.domain.query.mktApp.PayFlowNoQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.exception.GenerateFlowNoException;
import com.hengyuan.hicash.exception.QueryFlowNoException;
import com.hengyuan.hicash.exception.UpdateInfoException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.FastLoanFirstUpReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.FastLoanFirstUpResp;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 立刻秒贷业务处理
 * 
 * @author LiHua.Ren
 * @create date 2015-05-23
 * 
 */
public class FastLoanFirstUpService implements RespService {

	private static Logger logger = Logger
			.getLogger(FastLoanFirstUpService.class);

	private FastLoanFirstUpEvent updateMsgEvent = new FastLoanFirstUpEvent();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		FastLoanFirstUpReq updateMsgReq = (FastLoanFirstUpReq) parmRequest;
		FastLoanFirstUpResp updateMsgResp = new FastLoanFirstUpResp();
		String resultCode = "";
		String resultMsg = ""; // 返回消息
		try {

			//新逻辑如果业务流水号为空则一直插入
			TempApplyEntity tempEntity=queryuNameExist(updateMsgReq.getUserName(),updateMsgReq.getAppFlowNo());
			if(tempEntity!=null)
			{
				if(tempEntity.getCreateAppFlg().equals("0")==false)
				{
					updateMsgResp.setResultCode(ResultCodes.STU_APP_APPLY_NOT_OPERO);
					return updateMsgResp;
				}
			}
			ConnManager.beginTransaction();
			if(tempEntity ==null){
			
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
				updateMsgReq.setAppFlowNo(appFlowNo);
				updateMsgEvent.saveFastMsg(updateMsgReq);
				updateMsgResp.setAppFlowNo(appFlowNo);
			}else{//业务流水号非空，则更改
				//原来的单子，只更改金额，期数
//				if(tempEntity.getCreateAppFlg().equals("0"))
//				{
//					updateMsgReq.setAppFlowNo(updateMsgReq.getAppFlowNo());
//					updateMsgEvent.updateAmtPro(updateMsgReq);
//					updateMsgResp.setAppFlowNo(updateMsgReq.getAppFlowNo());	
//				}else{
//					resultCode = ResultCodes.STU_APP_APPLY_NOT_OPERO;	
//				}
				updateMsgReq.setAppFlowNo(updateMsgReq.getAppFlowNo());
				updateMsgEvent.updateAmtPro(updateMsgReq);
				updateMsgResp.setAppFlowNo(updateMsgReq.getAppFlowNo());		
			}
			ConnManager.commit();
			resultCode = ResultCodes.NORMAL;
			

		} catch (UpdateInfoException e) {
			ConnManager.rollback();
			resultCode = ResultCodes.FAST_LOAN_SAVE_FAIL;
		} catch (Exception e) {
			ConnManager.rollback();
			resultCode = ResultCodes.FAST_LOAN_SAVE_EXCEPTION;
		} finally {
			ConnManager.closeConn();
		}

		updateMsgResp.setResultCode(resultCode);
		updateMsgResp.setResultMsg(resultMsg);
		return updateMsgResp;
	}

	/**
	 * 查询用户名
	 * 
	 * @param appNo
	 * @return
	 */
	public TempApplyEntity queryuNameExist(String uname,String tempAppNo) {

		TempApplyQuery tempQuery = new TempApplyQuery();
		return tempQuery.queryTempApplyByNo(uname,tempAppNo);
	}
}
