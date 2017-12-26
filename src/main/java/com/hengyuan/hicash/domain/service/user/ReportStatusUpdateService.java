package com.hengyuan.hicash.domain.service.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.apply.DreportStatusEvent;
import com.hengyuan.hicash.domain.event.apply.TempAppInfoEvent;
import com.hengyuan.hicash.domain.query.app.TempApplyQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.exception.UpdateTempAppException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.param.DreportStatusReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.param.DreportStatusResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 
* @author dong.liu 
* 2017-12-23 下午2:49:10
* 类说明
 */
public class ReportStatusUpdateService implements RespService {
	
	private static Logger logger = Logger.getLogger(ReportStatusUpdateService.class);

	private DreportStatusEvent collarUpdateMsgEvent = new DreportStatusEvent();
	
	private TempApplyQuery tempApplyQuery=new TempApplyQuery();
	private TempAppInfoEvent tempAppInfoEvent=new TempAppInfoEvent();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		DreportStatusReq req = (DreportStatusReq) parmRequest;
		DreportStatusResp resp = new DreportStatusResp();
		String resultCode = "";
		try {

			TempApplyEntity entity=tempApplyQuery.queryTempByTempNo(req.getTempAppNo());
			
			if(entity!=null){
				String status=entity.getPhoneNo();
				StringBuffer buffer = new StringBuffer(status);
				int end=Integer.parseInt(req.getAuthen());
				int start=end-1;
				status=buffer.replace(start, end,req.getAuthen()).toString();
				tempAppInfoEvent.updateTempPro(req.getTempAppNo(), status);
				logger.info("临时单号:"+req.getTempAppNo()+"认证状态更新成功");
				this.queryAppNoExist(req);
				resultCode = ResultCodes.NORMAL;
			}else{
				logger.info("临时单号:"+req.getTempAppNo()+"找不到该笔订单,认证状态更新失败");
				resultCode = ResultCodes.CREATEAPP_TEMPAPPLY_NOTFOUND;
			}
			
		}catch(UpdateTempAppException e){
			resultCode = ResultCodes.CREATEAPP_UPDATETEMP_EXCEPTION;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CREATEAPP_UPDATETEMP_EXCEPTION, e);
		} catch (Exception e) {
			resultCode = ResultCodes.AUTNENSTATUS_SAVE_EXCEPTION;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.AUTNENSTATUS_SAVE_EXCEPTION, e);
		}
		resp.setResultCode(resultCode);
		return resp;
	}
	
	/**
	 * 查询流水号是否存在
	 * @param appNo
	 * @return
	 */
	public boolean queryAppNoExist(DreportStatusReq req)throws UpdateTempAppException{
		
		boolean flag =true;
		if(Consts.FINAL_NUMBER_2.equals(req.getAuthenStatus())&&
		 Consts.AUTHEN_STATUS_2.equals(req.getAuthenStatus())){
			SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
			HashMap<String, Object> input=new HashMap<String, Object>();
			input.put("temp_appno", req.getTempAppNo());
			input.put("type", Consts.FINAL_NUMBER_1);
			input.put("confirm_date",sdf.format(new Date()));
			input.put("create_date", sdf.format(new Date()));
			collarUpdateMsgEvent.createTempApp(input);
		}
		
		return flag;
	}
	

	public static void main(String[] args) {
		StringBuffer buffer = new StringBuffer("0123456789");
	    System.out.println(buffer.toString());//输出123456
		System.out.println(buffer.replace(1, 2, "a"));
		
	}
}
