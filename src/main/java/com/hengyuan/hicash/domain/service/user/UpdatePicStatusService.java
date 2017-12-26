package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.UploadAppPicEvent;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.exception.UpdateInfoException;
import com.hengyuan.hicash.exception.UpdatePicException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.QuerySingleImgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.upload.SaveSingleImgResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 修改图片状态有效
 * @author Administrator
 *
 */
public class UpdatePicStatusService implements RespService {

	private static Logger logger = Logger.getLogger(UpdatePicStatusService.class);
	
	private String resultCode = "";
	
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		QuerySingleImgReq uploadReq = (QuerySingleImgReq)parmRequest;
		SaveSingleImgResp uploadResp = new SaveSingleImgResp();
		
		try {
			
			   ConnManager.beginTransaction();
				
				updatePicStatus(uploadReq);
				
				ConnManager.commit();
			
			
			resultCode = ResultCodes.NORMAL;
		} catch (Exception e) {
			resultCode = ResultCodes.UPLOADPIC_APPPIC_FAIL;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.UPLOADPIC_APPPIC_FAIL, e);
			e.printStackTrace();
			ConnManager.rollback();
		} finally {
			ConnManager.closeConn();
		}
		
		uploadResp.setResultCode(resultCode);
		return uploadResp;
	}
	
	public void updatePicStatus(QuerySingleImgReq uploadReq) throws  UpdateInfoException, UpdatePicException{
		
		UploadAppPicEvent custTempApplyPicEvent = new UploadAppPicEvent();
		custTempApplyPicEvent.updatePicStatusByUser(uploadReq.getUserName(), 1);
		
	}
	
	

}
