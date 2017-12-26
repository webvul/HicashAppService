package com.hengyuan.hicash.domain.service.mktApp;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.SaveMyMsgEvent;
import com.hengyuan.hicash.domain.query.app.ApplicationPayQuery;
import com.hengyuan.hicash.domain.query.user.DdsjLimitQuery;
import com.hengyuan.hicash.domain.query.user.DdsjLimitRecordQuery;
import com.hengyuan.hicash.domain.query.user.HyIndustryParamQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.DdsjLimit;
import com.hengyuan.hicash.entity.user.DdsjLimitRecord;
import com.hengyuan.hicash.entity.user.HyIndustryParam;
import com.hengyuan.hicash.entity.user.SaveMyMsgEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.SaveMyMsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.SaveMyMsgResp;
import com.hengyuan.hicash.utils.DateUtils;

/**
 * 
* @author xing.yuan
* 类说明:保存我的消息
 */
public class SaveMyMsgService implements RespService{
	
	private static Logger logger = Logger
			.getLogger(SaveMyMsgService.class);
	
	private SaveMyMsgEvent event=new SaveMyMsgEvent();
	
	private HyIndustryParamQuery hyIndustryParamQuery = new HyIndustryParamQuery();
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		
		SaveMyMsgResp resp = new SaveMyMsgResp();
		String resultCode = "";
		Long start = System.currentTimeMillis();
		SaveMyMsgReq req = (SaveMyMsgReq) parmRequest;
	
		try {
			SaveMyMsgEntity entity= new SaveMyMsgEntity();
			CustCustomer custCustomer = event.QueryInfo(req.getUsername());
				
			if(custCustomer!=null){
				entity.setId_no(custCustomer.getIdentityNo());
				entity.setMobile(custCustomer.getMobileNo());
				entity.setName(custCustomer.getRealName());
			}
			entity.setUsername(req.getUsername());
			entity.setCreate_time(DateUtils.getNowTime());
			entity.setCode(req.getCode());
			entity.setIs_read("0");
			entity.setAppNo(req.getAppNo());
			if("SJRZ".equals(req.getCode())){
				DdsjLimitQuery ddsjLimitQuery = new DdsjLimitQuery();
				DdsjLimit ddsjLimit = ddsjLimitQuery.queryLimit(req.getUsername());
				if(ddsjLimit!=null){
					entity.setTitle("司机贷认证结果消息");
					if("PASS".equals(req.getStatus())){
						if(StringUtils.isEmpty(ddsjLimit.getAmount())){
							entity.setContent("车主身份认证成功  恭喜你通过司机认证，授信额度：0 元。");
						}else{
							entity.setContent("车主身份认证成功  恭喜你通过司机认证，授信额度："+ddsjLimit.getAmount()+"元。");
						}
						entity.setOperate("立即提现");
						entity.setType("ljtx");
					}else if("REFUSE".equals(req.getStatus())){
						entity.setContent("车主身份认证未通过   很抱歉，您提交的资料未通过审核。");
						entity.setOperate("其他产品");
						entity.setType("qtcp");
					}
				}
			}else if("TXSH".equals(req.getCode())){
				ApplicationPayQuery applicationPayQuery = new ApplicationPayQuery();
				ApplicationPay applicationPay = applicationPayQuery.queryApplyAmount(req.getUsername());
				HyIndustryParam hyIndustryParam = hyIndustryParamQuery.queryIndustryName(req.getIndustryCode());
				if(hyIndustryParam !=null||applicationPay!=null){
					entity.setTitle("提现审核结果消息");
					if("PASS".equals(req.getStatus())){
						entity.setContent(hyIndustryParam.getHyIndustryName()+"提现审核通过   恭喜你，审核通过"+applicationPay.getApplyAmount()+
								"元提现，立即前往个人中心,签约完成最后一步，立即拿钱。");
						entity.setOperate("");
						entity.setType("");
					}else if("REFUSE".equals(req.getStatus())){
						entity.setContent(hyIndustryParam.getHyIndustryName()+"提现审核未通过   很抱歉，您提交的资料未通过审核");
						entity.setOperate("");
						entity.setType("");
					}
				}		
						
			}else if("TESH".equals(req.getCode())){
				DdsjLimitRecordQuery ddsjLimitRecordQuery = new DdsjLimitRecordQuery();
				DdsjLimitRecord ddsjLimitRecord = ddsjLimitRecordQuery.queryDdsjLimitRecord(req.getUsername());
				if(ddsjLimitRecord !=null){
					entity.setTitle("司机贷提额审核结果");
					if("PASS".equals(req.getStatus())){
						entity.setContent("提额申请审核通过   恭喜你，由于您良好的信用，成功提额为"+ddsjLimitRecord.getAmount()+"元。");
						entity.setOperate("立即提现");
						entity.setType("ljtx");
					}else if("REFUSE".equals(req.getStatus())){
						entity.setContent("提额申请通知   您的信息没有明显变化，请下月再来");
						entity.setOperate("立即提现");
						entity.setType("ljtx");
					}
				}	
			}
		
		event.saveMyMsg(entity);
		logger.info("单号:"+req.getUsername()+"保存我的消息成功");
	    resultCode=ResultCodes.NORMAL;
	
		}  catch (Exception e) {
			
			resultCode = ResultCodes.DDSJ_MY_MESSAGE_IS_NULL;
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
