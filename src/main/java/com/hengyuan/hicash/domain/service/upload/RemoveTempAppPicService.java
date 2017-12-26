package com.hengyuan.hicash.domain.service.upload;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.apply.TempAppInfoEvent;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.upload.RemoveTempAppPicReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.upload.RemoveTempAppPicResp;

/**
 * 删除临时申请件图片
 * @author Cary.Liu
 * @createDate 2015-12-02
 *
 */
public class RemoveTempAppPicService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		RemoveTempAppPicReq picReq = (RemoveTempAppPicReq)parmRequest;
		RemoveTempAppPicResp picResp = new RemoveTempAppPicResp();
		
		try {
			
			deleteAppPic(picReq);
			
			resultCode = ResultCodes.NORMAL;
			
		} catch (Exception e) {
			resultCode = ResultCodes.REMOVEAPPPIC_EXCETPION;
			e.printStackTrace();
		} finally {
			ConnManager.closeConn();
		}
		
		picResp.setResultCode(resultCode);
		return picResp;
	}
	
	/**
	 * 删除临时申请件图片
	 * @param picReq
	 */
	private void deleteAppPic(RemoveTempAppPicReq picReq){
		
		TempAppInfoEvent event = new TempAppInfoEvent();
		
		event.deleteTempAppPic(picReq.getPicId(), picReq.getUserName());
	}

}
