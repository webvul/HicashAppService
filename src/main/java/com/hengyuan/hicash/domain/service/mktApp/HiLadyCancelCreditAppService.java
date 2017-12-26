package com.hengyuan.hicash.domain.service.mktApp;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.apply.HinsCreateCreditEvent;
import com.hengyuan.hicash.domain.query.user.HinsApplyCreditQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.HinsApplyCredit;
import com.hengyuan.hicash.exception.UpdateCreateCreditException;
import com.hengyuan.hicash.exception.UpdateDdsjLimitException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.mktApp.HilLadyCancelCreditAndLimitReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.mktApp.HiLadyCancelCreditAndLimitResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 
* @author dong.liu 
* 2017-4-7 下午3:16:11
* 类说明:嗨女神授信订单取消
 */
public class HiLadyCancelCreditAppService implements RespService {

	private static Logger logger = Logger
			.getLogger(HiLadyCancelCreditAppService.class);

	
	private HinsApplyCreditQuery hinsApplyCreditQuery=new HinsApplyCreditQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
     
		HiLadyCancelCreditAndLimitResp resp = new HiLadyCancelCreditAndLimitResp();
		String resultCode = "";
		Long start = System.currentTimeMillis();
		HilLadyCancelCreditAndLimitReq req = (HilLadyCancelCreditAndLimitReq) parmRequest;
		 logger.info("正式单号:"+req.getApp_no()+"开始查询");
		try {
			HinsApplyCredit applyCredit=hinsApplyCreditQuery.queryApply(req.getApp_no());
			
			if(applyCredit==null||"".equals(applyCredit.getNode())||applyCredit.getNode()==null){
				resultCode = ResultCodes.DDSJ_CREDIT_IS_NULL;
				logger.info("单号:"+req.getApp_no()+"该笔申请不存在 ");
			}else if(Consts.SHENHE_NODE.equals(applyCredit.getNode())){
				ConnManager.beginTransaction();
				HinsCreateCreditEvent createCreditEvent = new HinsCreateCreditEvent();
				createCreditEvent.updateCreateCredit(req.getApp_no());
				createCreditEvent.updateHinsLimitSta(applyCredit.getUsername());
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
