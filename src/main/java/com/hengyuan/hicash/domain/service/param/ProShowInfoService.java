package com.hengyuan.hicash.domain.service.param;

import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.param.ProShowInfoQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.ProShowEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.param.ProShowInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.param.ProShowInfoResp;

/**
 * 产品展示信息 业务处理
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ProShowInfoService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		ProShowInfoReq proShowReq = (ProShowInfoReq)parmRequest;
		ProShowInfoResp proShowResp = new ProShowInfoResp();
	
		try {
			
			List<ProShowEntity> proShowList = queryProShowList(proShowReq);
			if(proShowList != null && proShowList.size() > 0){
				proShowResp.setProShowList(proShowList);
				resultCode = ResultCodes.NORMAL;
			}else{
				resultCode = ResultCodes.NO_RESULT;
			}
			
		} catch (Exception e) {
			resultCode = ResultCodes.PROSHOWINFO_EXCEPTION;
		} finally {
			ConnManager.closeConn();
		}
		
		proShowResp.setResultCode(resultCode);
		return proShowResp;
	}
	
	/**
	 * 获取展示商品
	 * @param proShowReq
	 * @return
	 */
	public List<ProShowEntity> queryProShowList(ProShowInfoReq proShowReq){
		
		ProShowInfoQuery proShowQuery = new ProShowInfoQuery();
		
		return proShowQuery.queryProShowList(proShowReq);
	}

}
