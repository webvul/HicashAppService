package com.hengyuan.hicash.domain.service.notic;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.notic.CustNoticQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.notic.NoticeInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.notic.NoticeInfoResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 公告查询逻辑类
 * 
 * @author Eric.shi
 * @create date 2014-07-25
 *
 */

public class NoticeInfoService implements RespService {
	
	private static Logger logger = Logger.getLogger(NoticeInfoService.class);

	CustNoticQuery custNoticQuery = new CustNoticQuery();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		//查询公告
		NoticeInfoReq infoReq = (NoticeInfoReq)parmRequest;
		NoticeInfoResp custNoticResp = null;
		try {
			
			custNoticResp = custNoticQuery.queryCustNotics(infoReq.getId());
			if (custNoticResp != null) {
				custNoticResp.setResultCode(ResultCodes.NORMAL);
			} else {
				custNoticResp = new NoticeInfoResp();
				custNoticResp.setResultCode(ResultCodes.NOTICE_QUERY_NOT_FOUND);
			}
			
		} catch (Exception e) {
			custNoticResp = new NoticeInfoResp();
			custNoticResp.setResultCode(ResultCodes.NOTICE_QUERY_EXCEPTION);
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.NOTICE_QUERY_EXCEPTION, e);
		}finally{
			ConnManager.closeConn();
		}
		return custNoticResp;
	}
	


}
