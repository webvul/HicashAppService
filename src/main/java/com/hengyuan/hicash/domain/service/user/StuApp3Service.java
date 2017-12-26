package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.param.SystemParamQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.StuApp3Query;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.StuAppReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuApp3Resp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * hicash手机端学生提现申请3查询逻辑类
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuApp3Service implements RespService{
	private static Logger logger = Logger.getLogger(StuApp3Service.class);

	private StuApp3Query stu3Query = new StuApp3Query();
	
	private CustomerQuery customerQuery = new CustomerQuery();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		StuAppReq req = (StuAppReq) parmRequest;
		StuApp3Resp resp = null;
		
		try{
//			String custType = customerQuery.queryCustType(req.getUserName());
//			if (Consts.APP_CUSTOMER_TYPE_KHL1.equals(custType)) {
				resp = stu3Query.queryStuApp3Resp(req.getUserName());
				if(resp != null){
					if(Consts.CUSTTYPE_KHL1.equals(resp.getCustType())){
						resp.setCustTypeName(Consts.APP_CUSTOMER_TYPE_KHL1_NAME);
					}else{
						resp.setCustTypeName(Consts.APP_CUSTOMER_TYPE_KHL2_NAME);
					}
					resp = queryParmName(resp);
					resp.setResultCode(ResultCodes.NORMAL);
				}else{
					resp = new StuApp3Resp();
					resp.setResultCode(ResultCodes.STU_APP_DETAIL_QUERY_NOT_FOUND);
				}
//			}else {
//				if (custType != null) {
//					resp = new StuApp3Resp();
//					resp.setResultCode(ResultCodes.STU_APP_DETAIL_CUSTTYPE_NOT_FOUND);
//				}else {
//					resp = new StuApp3Resp();
//					resp.setResultCode(ResultCodes.STU_APP_DETAIL_QUERY_NOT_FOUND);
//				}
//			}
			
		}catch (Exception e) {
			resp = new StuApp3Resp();
			resp.setResultCode(ResultCodes.STU_APP_DETAIL_QUERY_EXCEPTION);
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.STU_APP_DETAIL_QUERY_EXCEPTION, e);
		} finally {
			ConnManager.closeConn();
		}

		
		return resp;
	}
	
	
	/**
	 * 查询系统参数代码对应的名称
	 * @param resp
	 * @return
	 */
	public StuApp3Resp queryParmName(StuApp3Resp resp){
		
		SystemParamQuery query = new SystemParamQuery();
		resp.setTionSalaName(query.queryParamByDicCode(resp.getTionSala()).getDicName());
		resp.setRelationName(query.queryParamByDicCode(resp.getRelation()).getDicName());
		
		return resp;
	}

}
