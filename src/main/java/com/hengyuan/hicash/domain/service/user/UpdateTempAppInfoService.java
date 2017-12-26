package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.user.UpdateTempAppInfoEvent;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.query.user.QueryTempAppQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.UpdateTempAppInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.UpdateTempAppInfoResp;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 修改临时订单表
* @author dong.liu 
* 2017-3-2 下午2:59:01
* 类说明
 */
public class UpdateTempAppInfoService implements RespService {
	
	private static Logger logger = Logger
			.getLogger(UpdateTempAppInfoService.class);

	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		UpdateTempAppInfoReq req = (UpdateTempAppInfoReq)parmRequest;
		UpdateTempAppInfoResp resp = new UpdateTempAppInfoResp();
		String resultCode = "";
		try{
			
			UpdateTempAppInfoEvent tempEvent = new UpdateTempAppInfoEvent();
			/**
			 * 拼接申请件流程状态
			 */
			
			QueryTempAppQuery query=new QueryTempAppQuery();
			UpdateTempAppInfoResp upResp=null;
			
			if("NSTE".equals(req.getIndustryCode())||"NSSX".equals(req.getIndustryCode())){
				upResp=query.queryNSSingleResp(req.getTempAppNo());
			}else{
				upResp=query.querySingleResp(req.getTempAppNo());
			}

			if(upResp==null){
				 resultCode = ResultCodes.SERVICEPSWVAL_TEMPAPPNO_NOTFOUND;
			 }else{
			 String valTypea= upResp.getValidateTypea();
			 //获取数据库保存的申请件流程状态
			 logger.info("valTypea:"+valTypea);
			 if(valTypea.length()>11){
				 valTypea=valTypea.substring(10,12);//获取数据库节点
				 String node=req.getNode();//获取前台节点
				 if(Integer.parseInt(node)<Integer.parseInt(valTypea)){
					 req.setValidateTypea(upResp.getValidateTypea());
				 }else{
					 req.setValidateTypea(this.getVal(req));
				 }
				 
			 }else{
				 req.setValidateTypea(this.getVal(req));
			 }
			req.setValidateType(this.getVal(req));
			
			if("NSTE".equals(req.getIndustryCode())||"NSSX".equals(req.getIndustryCode())){
				tempEvent.updateNSAppInfo(req);
			}else{
				tempEvent.updateTempAppInfo(req);
			}
			
				resultCode = ResultCodes.NORMAL;
			 }
		} catch(Exception e){
			logger.error("更新临时申请件验证状态失败", e);
			resultCode = ResultCodes.CREATEAPP_UPDATETEMP_EXCEPTION;
		}
		resp.setResultCode(resultCode);
		resp.setResultMsg(ResourceUtils.getString(resp
				.getResultCode()));
		return resp;
	}
	
	/**
	 * 拼接申请件流程状态
	 * @param req
	 * @return
	 */
	public String getVal(UpdateTempAppInfoReq req){
		
		String applyFrom=req.getApplyFrom();
		String custType=req.getCustType();
		String industryCode=req.getIndustryCode();
		String node=req.getNode();
		String status=req.getStatus();

		String val=applyFrom+custType+industryCode+node+status;
		
		return val;
		
	}


	
	
	
}
