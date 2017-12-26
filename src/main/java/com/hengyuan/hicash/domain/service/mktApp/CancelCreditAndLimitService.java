package com.hengyuan.hicash.domain.service.mktApp;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.apply.CreateCreditEvent;
import com.hengyuan.hicash.domain.query.mktApp.ApplyCreditQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.mktApp.ApplyCredit;
import com.hengyuan.hicash.exception.UpdateCreateCreditException;
import com.hengyuan.hicash.exception.UpdateDdsjLimitException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.mktApp.CancelCreditAndLimitReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.mktApp.CancelCreditAndLimitResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 
* @author dong.liu 
* 2017-4-7 下午3:16:11
* 类说明:授信订单取消
 */
public class CancelCreditAndLimitService implements RespService {

	private static Logger logger = Logger
			.getLogger(CancelCreditAndLimitService.class);

	private ApplyCreditQuery applyCreditQuery = new ApplyCreditQuery();
	

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
     
		CancelCreditAndLimitResp resp = new CancelCreditAndLimitResp();
		String resultCode = "";
		Long start = System.currentTimeMillis();
		CancelCreditAndLimitReq req = (CancelCreditAndLimitReq) parmRequest;
		 logger.info("正式单号:"+req.getApp_no()+"开始查询");
		try {
			ApplyCredit applyCredit=applyCreditQuery.queryApplyCredit(req.getApp_no());
			
			if(applyCredit==null||"".equals(applyCredit.getNode())||applyCredit.getNode()==null){
				resultCode = ResultCodes.DDSJ_CREDIT_IS_NULL;
				logger.info("单号:"+req.getApp_no()+"该笔申请不存在 ");
			}else if(Consts.SHENHE_NODE.equals(applyCredit.getNode())){
				ConnManager.beginTransaction();
				CreateCreditEvent createCreditEvent = new CreateCreditEvent();
				createCreditEvent.updateCreateCredit(req.getApp_no());
				createCreditEvent.updateDdsjLimitSta(applyCredit.getUsername());
				ConnManager.commit();
				resultCode = ResultCodes.NORMAL;
			}else{
				resultCode = ResultCodes.DDSJ_CREDIT_NODE;
				logger.info("单号:"+req.getApp_no()+"该笔申请不在审核中 ");
			}
			
		} catch (UpdateCreateCreditException e) {
			resultCode = ResultCodes.DDSJ_CREDIT_UPDATE_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (UpdateDdsjLimitException e) {
			resultCode = ResultCodes.CREATEAPP_CREAT_FAILD;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			resultCode = ResultCodes.DDSJ_LIMIT_UPDATE_EXCEPTION;
			e.printStackTrace();
		} 
		finally {
			ConnManager.closeConn();
		}
		System.out.println("耗时[" + (System.currentTimeMillis() - start) + "]ms");
		resp.setResultCode(resultCode);
		return resp;
	}



}
