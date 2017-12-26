package com.hengyuan.hicash.domain.service.mktApp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.apply.ApplicationPicEvent;
import com.hengyuan.hicash.domain.event.apply.CreateCreditEvent;
import com.hengyuan.hicash.domain.query.mktApp.PayFlowNoQuery;
import com.hengyuan.hicash.domain.query.mktApp.TempCreditQuery;
import com.hengyuan.hicash.domain.query.param.TempApplyPicQuery;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.query.user.DdsjLimitQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.PicParam;
import com.hengyuan.hicash.entity.mktApp.TempCredit;
import com.hengyuan.hicash.entity.param.TempApplyPicEntity;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.DdsjLimit;
import com.hengyuan.hicash.exception.CreateAppPayException;
import com.hengyuan.hicash.exception.CreateDdsjLimitException;
import com.hengyuan.hicash.exception.UpdateApplicationPicException;
import com.hengyuan.hicash.exception.UpdateTempAppException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.mktApp.CreateCreditReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.mktApp.CreateCreditResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 
* @author dong.liu 
* 2017-3-24 下午5:38:30
* 类说明:滴滴司机授信订单表
 */
public class CreateCreditService implements RespService {

	private static Logger logger = Logger
			.getLogger(CreateCreditService.class);

	private TempCreditQuery tempCreditQuery = new TempCreditQuery();
	
	private CustcustomerQuery custcustomerQuery=new CustcustomerQuery();
	
	private DdsjLimitQuery ddsjLimitQuery=new DdsjLimitQuery();

	@Override
	public synchronized ParmResponse responseValue(ParmRequest parmRequest) {
     
		/* 创建申请件 */
		CreateCreditResp resp = new CreateCreditResp();
		String resultCode = "";
		String applicationNo = "";
		Long start = System.currentTimeMillis();
		CreateCreditReq req = (CreateCreditReq) parmRequest;
		 logger.info("临时单号:"+req.getTemp_no()+"开始创建授信正式订单");
		TempCredit tempCredit=new TempCredit();
		CustCustomer customer=new CustCustomer();
		try {
			 tempCredit=tempCreditQuery.queryTempCreditByAppId(req.getTemp_no());
			/* 订单是否已经提交过 */
			if (Consts.FINAL_NUMBER_N.equals(tempCredit.getCreateapp_flag())) {
				customer=custcustomerQuery.queryCustCustomer(tempCredit.getUsername());
                if(customer==null){
                	resultCode=ResultCodes.REGISTER_QUERY_EXCEPTION;
                	 logger.info("临时单号:"+req.getTemp_no()+"用户查询异常");
                }else{
                	ConnManager.beginTransaction();
					/* 创建申请件 */
				  applicationNo = initCredit(req,tempCredit,customer);
					ConnManager.commit();
					resultCode = ResultCodes.NORMAL;
                }
			} else {
				resultCode = ResultCodes.CREATEAPP_REPEATSUBMIT_FAIL;
			}
		} catch (UpdateApplicationPicException e) {
			resultCode = ResultCodes.CREATEAPP_UPDATEPIC_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (CreateAppPayException e) {
			resultCode = ResultCodes.CREATEAPP_CREAT_FAILD;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (UpdateTempAppException e) {
			resultCode = ResultCodes.CREATEAPP_UPDATETEMP_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (CreateDdsjLimitException e) {
			resultCode = ResultCodes.CREATEAPP_UPDATETEMP_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resultCode = ResultCodes.CREATE_APPPAY_INPUT_ERROR;
			e.printStackTrace();
		} 
		finally {
			ConnManager.closeConn();
		}
		System.out.println("耗时[" + (System.currentTimeMillis() - start) + "]ms");
		resp.setResultCode(resultCode);
		resp.setApp_no(applicationNo);

		return resp;
	}

	public String initCredit(CreateCreditReq req,TempCredit tempCredit,CustCustomer customer)
			throws Exception{

		/* 实例化获得最新appPayNo */
		PayFlowNoQuery payFlowNoQuery = new PayFlowNoQuery();
		/* 特色分期，获得业务流水号,根据申请件类型及产品类型 */
		String appPayNo = payFlowNoQuery.generateFlowNo("DDSJ",Consts.APPNO_GENERATE_SEQ);
		/*1. 创建申请PAY */
		Map<String, Object> creditMap = getCreditMap(appPayNo, tempCredit, customer);
		CreateCreditEvent createCreditEvent = new CreateCreditEvent();
		createCreditEvent.CreateCredit(creditMap);
		 logger.info("正式单号:"+req.getTemp_no()+"创建授信正式订单");
		/*2.更新图片*/
		if(Consts.DDSJ_CREDIT_TYPE_SX.equals(tempCredit.getCredit_type())){
			/* 将临时表图片信息更新到申请件图片表 */
			updateAppPicInfo(tempCredit.getUsername(), appPayNo, "DDSJ");
			 logger.info("正式单号:"+req.getTemp_no()+"更新图片信息");
		}
		/*3.更新授信额度表*/
		DdsjLimit ddlimit=ddsjLimitQuery.queryLimit(tempCredit.getUsername());
		if(ddlimit==null){
			Map<String, Object> ddsjLimitMap = getCreditDdsjLimit(tempCredit);
			createCreditEvent.createDdsjLimit(ddsjLimitMap);
		}else{
			String status=tempCredit.getCredit_type();
			if(Consts.DDSJ_CREDIT_TYPE_SX.equals(status)){
				status=Consts.DDSJ_CREDIT_TYPE_SXZ;
			}else if(Consts.DDSJ_CREDIT_TYPE_TE.equals(status)){
				status=Consts.DDSJ_CREDIT_TYPE_TEZ;
			}else{
				status="";
			}
			createCreditEvent.updateDdsjLimit(tempCredit.getUsername(),status,tempCredit.getDdsj_auth_phone(),tempCredit.getDdsj_auth_password());
		}
		 logger.info("正式单号:"+req.getTemp_no()+"更新授信额度表");
		/*3.更新临时订单表*/
		createCreditEvent.updateTempCredit(tempCredit.getTemp_application_no(), appPayNo);
		logger.info("正式单号:"+req.getTemp_no()+"更新临时订单表正式单号");
		return appPayNo;
	}

	
	
	/**
	 * 创建滴滴司机额度信息表
	 * @param tempCredit
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getCreditDdsjLimit(TempCredit tempCredit)
			throws Exception {
		Map<String, Object> ddsjLimitMap=new HashMap<String, Object>();
		ddsjLimitMap.put("username", tempCredit.getUsername());//用户名
		ddsjLimitMap.put("amount", 0);//授信额度
		ddsjLimitMap.put("sx_success_time",0);//授信成功次数
		ddsjLimitMap.put("sx_mobile", tempCredit.getDdsj_auth_phone());//授信手机号
		ddsjLimitMap.put("sx_mobile_password", tempCredit.getDdsj_auth_password());//手机司机密码
		
		String status=tempCredit.getCredit_type();
		if(Consts.DDSJ_CREDIT_TYPE_SX.equals(status)){
			status=Consts.DDSJ_CREDIT_TYPE_SXZ;
		}else if(Consts.DDSJ_CREDIT_TYPE_TE.equals(status)){
			status=Consts.DDSJ_CREDIT_TYPE_TEZ;
		}else{
			status="";
		}
		ddsjLimitMap.put("status",status);//授信成功次数
		return ddsjLimitMap;
	}

	
	
	
	
	public Map<String, Object> getCreditMap(String appPayNo,TempCredit tempCredit,CustCustomer custCustomer)
			throws Exception {
		Map<String, Object> creditMap=new HashMap<String, Object>();
		creditMap.put("app_application_no", appPayNo);//授权真实订单号
		creditMap.put("realname", custCustomer.getRealName());//姓名
		creditMap.put("mobile", custCustomer.getMobileNo());//手机号
		creditMap.put("identityno", custCustomer.getIdentityNo());//身份证号
		creditMap.put("username", tempCredit.getUsername());//用户名
		creditMap.put("sx_amount", 0);//授信额度
		creditMap.put("maritalStatus", custCustomer.getMaritalStatus());//婚姻状况
		creditMap.put("educational", custCustomer.getNowEduCation());//最高学历
		creditMap.put("nowProv", custCustomer.getOtherAdressProvince());//居住地址(省)
		creditMap.put("nowCity", custCustomer.getOtherAdressCity());//居住地址(市)
		creditMap.put("nowArea", custCustomer.getOtherAdressArea());//居住地址(区)
		creditMap.put("nowAddress", custCustomer.getOtherAccommodationAddress());//现居详细地址
		creditMap.put("emailAdress", custCustomer.getEmailAdress());//电子邮箱
		creditMap.put("fulltimeDriver", custCustomer.getFulltimeDriver());//是否全职司机
		creditMap.put("unitName", custCustomer.getUnitName());//单位名称
		creditMap.put("unitTelArea", custCustomer.getUnitTelArea());//单位电话（区号）
		creditMap.put("unitTel",custCustomer.getUnitTel());//单位电话（电话）
		creditMap.put("unitWorkYear", custCustomer.getWorkExperience());//工作年限
		creditMap.put("unitProv", custCustomer.getWorkAreaProvince());//单位地址(省)
		creditMap.put("unitCity", custCustomer.getWorkAreaCity());//单位地址(市)
		creditMap.put("unitArea", custCustomer.getWorkAreaArea());//单位地址(区)
		creditMap.put("unitAddress", custCustomer.getWorkAreaRoad());//单位详细地址
		creditMap.put("zXRealname",custCustomer.getImmediateName() );//直系亲属姓名
		creditMap.put("zXRelation", custCustomer.getImmediateRelation());//关系
		creditMap.put("zXMobile", custCustomer.getImmediateMobile());//联系方式（电话）
		creditMap.put("jJRealname", custCustomer.getEmergencyName());//紧急联系人姓名
		creditMap.put("jJRelation", custCustomer.getEmergencyRelation());//关系
		creditMap.put("jJMobile", custCustomer.getEmergencyMobile());//联系方式（电话）
		creditMap.put("credit_type", tempCredit.getCredit_type());//类型（授信，提额）
		
		if(tempCredit.getCredit_type()!=null && "TE".equals(tempCredit.getCredit_type())){
			creditMap.put("status", Consts.SYS_STATUS);
		}else{
			creditMap.put("status", Consts.FIRST_STATUS);//审核状态（新申请，等待司机报告，等待运营商报告，待系统审核，待人工审核，审核通过，取消,审核拒绝）
		}
		creditMap.put("node", Consts.SHENHE_NODE);//审核节点（审核中，审核完成,取消）
		
		creditMap.put("create_date", DateUtils.getDateStr(new Date()));//创建时间
		creditMap.put("create_user", tempCredit.getUsername());//创建人
		creditMap.put("sx_mobile_password", tempCredit.getDdsj_auth_password());//授信滴滴密码
		creditMap.put("ddsj_auth_phone", tempCredit.getDdsj_auth_phone());//滴滴司机认证手机号
 
		return creditMap;
	}

	/**
	 * 更新申请件图片信息
	 * 
	 * @throws UpdateApplicationPicException
	 */
	public void updateAppPicInfo(String userName, String appNo, String industryCode)
			throws UpdateApplicationPicException {


			TempApplyPicQuery tempApplyQuery = new TempApplyPicQuery();
			List<TempApplyPicEntity> tempPicList = tempApplyQuery.queryTempApplyPic(userName);
			ApplicationPicEvent picEvent = new ApplicationPicEvent();
			
			if (tempPicList != null && tempPicList.size() > 0) {

				List<PicParam> picList = new ArrayList<PicParam>();

				for (TempApplyPicEntity tempPic : tempPicList) {
					if(picList.size()==0){
						logger.info("用户名:"+tempPic.getUserName()+"更新第一张图片");
						PicParam picParam = new PicParam();
    					picParam.setAppNo(appNo);
    					picParam.setPicType(tempPic.getPicType());
    					picParam.setPicName(tempPic.getPicName());
    					picParam.setPicPath(tempPic.getPicPath());
    					picParam.setThumPath(tempPic.getThumPath());
    					picParam.setCaption(tempPic.getPicCaption());
    					picList.add(picParam);
					}else{
						boolean flag=true;
						for(int i=0;i<picList.size();i++){
	                    	if(tempPic.getPicType().equals(picList.get(i).getPicType())){
	                    		logger.info("用户名:"+tempPic.getUserName()+"图片重复");
	                    		flag=false;
	                    		break;
	                    	}
	                    }
						if(flag){
							PicParam picParam = new PicParam();
        					picParam.setAppNo(appNo);
        					picParam.setPicType(tempPic.getPicType());
        					picParam.setPicName(tempPic.getPicName());
        					picParam.setPicPath(tempPic.getPicPath());
        					picParam.setThumPath(tempPic.getThumPath());
        					picParam.setCaption(tempPic.getPicCaption());
        					picList.add(picParam);
						}
						
						
					}
				}
				for (PicParam picParam : picList) {
					picEvent.saveAppPic(picParam, userName);
				}

			}
	}
}
