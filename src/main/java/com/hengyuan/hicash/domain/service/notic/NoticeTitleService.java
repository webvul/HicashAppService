package com.hengyuan.hicash.domain.service.notic;

import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.notic.NoticeTitleQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.notic.NoticDetilResp;
import com.hengyuan.hicash.parameters.response.notic.NoticeTitleResp;

/**
 * hicash客户资料查询
 * 公告标题查询
 * @author Cary.Liu
 * @create date 2014-08-15
 *
 */
public class NoticeTitleService implements RespService {

	private NoticeTitleQuery noticeTitleQuery = new NoticeTitleQuery();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		NoticeTitleResp noticeTitleResp = null;
		
		try {
			noticeTitleResp = new NoticeTitleResp();
			List<NoticDetilResp> noticeTtiles = noticeTitleQuery.queryNoticeTitle();
			if(!noticeTtiles.isEmpty()){
				noticeTitleResp.setNoticeTtiles(noticeTtiles);
				noticeTitleResp.setResultCode(ResultCodes.NORMAL);
			}else{
				noticeTitleResp.setResultCode(ResultCodes.NOTICE_QUERY_NOT_FOUND);
			}
			
		} catch (Exception e) {
			noticeTitleResp.setResultCode(ResultCodes.NOTICE_QUERY_EXCEPTION);
		} finally{
			ConnManager.closeConn();
		}
		
		return noticeTitleResp;
	}

}
