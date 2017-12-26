package com.hengyuan.hicash.domain.service.param;

import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.apply.TempAppInfoEvent;
import com.hengyuan.hicash.domain.query.app.ApplicationPicQuery;
import com.hengyuan.hicash.domain.query.app.ProductQuery;
import com.hengyuan.hicash.domain.query.app.TempApplyQuery;
import com.hengyuan.hicash.domain.query.param.ChannelInfoQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.LoanProduct;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.entity.param.ChannelEntity;
import com.hengyuan.hicash.exception.UpdateTempAppException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.param.ChannelInfoReq;
import com.hengyuan.hicash.parameters.request.param.TempAppInofoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.param.TempAppInofoResp;
import com.hengyuan.hicash.service.query.StuInfoQuery;

/**
 * 获取频道列表 处理业务
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class TempAppInfoInfoService implements RespService {

	private static Logger logger = Logger.getLogger(TempAppInfoInfoService.class);
	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		TempAppInofoReq infoReq = (TempAppInofoReq)parmRequest;
		TempAppInofoResp infoResp = new TempAppInofoResp();
		
		try {
			TempApplyEntity tempApplyEntity = queryTempData(infoReq.getTempAppNo());
			if(tempApplyEntity != null ){
				LoanProduct product=queryProductData(tempApplyEntity.getLoanProduct());
				
				infoResp=this.getStatus(tempApplyEntity, product);
				infoResp.setApplyRice(tempApplyEntity.getApplyPrice());
				infoResp.setPeriod(String.valueOf(product.getPeriod()));
				resultCode = ResultCodes.NORMAL;
			}else{
				resultCode = ResultCodes.NO_RESULT;
			}
		} catch (Exception e) {
			resultCode = ResultCodes.CREATEAPP_TEMPAPPLY_NOTFOUND;
			logger.error("查询异常",e);
		} finally {
			ConnManager.closeConn();
		}
		
		infoResp.setResultCode(resultCode);
		return infoResp;
	}
	
	protected List<ChannelEntity> queryChannelType(ChannelInfoReq infoReq){
		
		ChannelInfoQuery infoQuery = new ChannelInfoQuery();
		
		if(Consts.CHANNEL_TYPE_HICASH.equals(infoReq.getChannelType())){
			return infoQuery.queryMerType();
		}else{
			return infoQuery.queryHicashAppType();
		}
		
	}
	
	public TempApplyEntity queryTempData(String  tempAppNo) {

		TempApplyQuery tempApplyQuery = new TempApplyQuery();
		return tempApplyQuery.queryTempByTempNo(tempAppNo);
	}
	

	public LoanProduct queryProductData(String  productId) {

		ProductQuery query = new ProductQuery();
		return query.queryCreditProductById(productId);
	}
	
	/**
	 * 认证状态
	 * @param tempApp
	 * @param product
	 * @return
	 */
	public TempAppInofoResp getStatus(TempApplyEntity tempApp,LoanProduct product){
		TempAppInofoResp resp=new TempAppInofoResp();
		String status=tempApp.getPhoneNo();
		 String iscard="";//能否绑卡 0否 1是
		 String  idcardStatus="";//身份认证 /车主认证
		 String industryCode="";//行业代码
		 String personStatus="";//人行征信状态
		 String basicStatus="";//基本信息
		 String operateStatus="";//运营商
		 String zhimaStatus="";//芝麻信用
		 String gjjStatus="";//公积金状态
		 String xuexinStatus="";//学信网
		 idcardStatus=status.substring(0,1);
		 industryCode=product.getIndustryCode();
		 personStatus=status.substring(1,2);
		 basicStatus=status.substring(2,3);
		 operateStatus=status.substring(3,4);
		 zhimaStatus=status.substring(4,5);
		 gjjStatus=status.substring(5,6);
		 xuexinStatus=status.substring(6,7);
		 iscard=status.substring(7,8);
		 //如果是精英分期 ,并且运营商,
		TempApplyQuery tempApplyQuery = new TempApplyQuery();
		// 判断司机报告
		 if(Consts.DDSJ.equals(industryCode)){
			 if(!Consts.AUTHEN_STATUS_2.equals(idcardStatus)){
				 
				 int count=tempApplyQuery.ddsjCount(tempApp.getTempAppNo());
				 if(count>0){
					 idcardStatus=Consts.AUTHEN_STATUS_2;
				 }
			 }
			 
		 }
		 //人行征信
		 if(!Consts.AUTHEN_STATUS_2.equals(personStatus)||
				 !Consts.AUTHEN_STATUS_1.equals(personStatus) ){
			 int count=tempApplyQuery.creditCount(tempApp.getTempAppNo());
			 if(count>0){
				 personStatus=Consts.AUTHEN_STATUS_2;
			 }
		 }
		 //运营商
		 if(!Consts.AUTHEN_STATUS_2.equals(operateStatus)){
			 int count=tempApplyQuery.jxlCount(tempApp.getTempAppNo());
			 if(count>0){
				 operateStatus=Consts.AUTHEN_STATUS_2;
			 }
		 }
		 
		 resp.setIdcardStatus(idcardStatus);
		 resp.setIndustryCode(industryCode);
		 resp.setPersonStatus(personStatus);
		 resp.setBasicStatus(basicStatus);
		 
		 if(Consts.JYFQ.equals(industryCode)){
			 if(Consts.AUTHEN_STATUS_2.equals(operateStatus)
					 &&Consts.AUTHEN_STATUS_2.equals(zhimaStatus)
					 &&Consts.AUTHEN_STATUS_2.equals(gjjStatus)
					 &&Consts.AUTHEN_STATUS_2.equals(xuexinStatus)
					 ){
					 resp.setCreditStatus(Consts.AUTHEN_STATUS_2);
				 }else if(Consts.AUTHEN_STATUS_2.equals(operateStatus)){
					 resp.setCreditStatus(Consts.AUTHEN_STATUS_3);
				 }else{
					 resp.setCreditStatus(Consts.AUTHEN_STATUS_0);
				 }
			 
		 }else{
			 if(Consts.AUTHEN_STATUS_2.equals(operateStatus)
					 &&Consts.AUTHEN_STATUS_2.equals(zhimaStatus)
					 &&Consts.AUTHEN_STATUS_2.equals(xuexinStatus)
					 ){
					 resp.setCreditStatus(Consts.AUTHEN_STATUS_2);
				 }else if(Consts.AUTHEN_STATUS_2.equals(operateStatus)){
					 resp.setCreditStatus(Consts.AUTHEN_STATUS_3);
				 }else{
					 resp.setCreditStatus(Consts.AUTHEN_STATUS_1);
				 }
		 }
		 
		 if(Consts.AUTHEN_STATUS_2.equals(idcardStatus)
			 &&Consts.AUTHEN_STATUS_2.equals(personStatus)
			 &&Consts.AUTHEN_STATUS_2.equals(basicStatus)
			 &&Consts.AUTHEN_STATUS_2.equals(resp.getCreditStatus())){
			 resp.setIscard(Consts.FINAL_NUMBER_1);
		 }else{
			 resp.setIscard(Consts.FINAL_NUMBER_0);
		 }
		 //保存认证状态
	  String pro=idcardStatus+personStatus+basicStatus+operateStatus+zhimaStatus+gjjStatus+xuexinStatus+iscard+"00";
	  this.savePro(pro, tempApp.getTempAppNo());
	  resp=this.isDisable(resp);
		return resp;
	}
	
	//判断是否不可点击
	public TempAppInofoResp  isDisable(TempAppInofoResp resp){
		if(Consts.AUTHEN_STATUS_0.equals(resp.getIdcardStatus())){
			resp.setPersonStatus("");
			resp.setBasicStatus("");
			resp.setCreditStatus("");
			resp.setIscard("");
		}else if(Consts.AUTHEN_STATUS_0.equals(resp.getPersonStatus())){
			resp.setBasicStatus("");
			resp.setCreditStatus("");
			resp.setIscard("");
		}else if(Consts.AUTHEN_STATUS_0.equals(resp.getBasicStatus())){
			resp.setCreditStatus("");
			resp.setIscard("");
		}else if(Consts.AUTHEN_STATUS_0.equals(resp.getCreditStatus())){
			resp.setIscard("");
		}
		return resp;
	}
	public void savePro(String pro,String tempAppNo){
		TempAppInfoEvent tempAppEvent = new TempAppInfoEvent();
		 try {
			tempAppEvent.updateTempPro(tempAppNo, pro);
		} catch (UpdateTempAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("更新认证状态失败",e);
		}
	}
			
	 public static void main(String[] args) {
		String aa="01234567";
		System.out.println(aa.substring(0,1));
		System.out.println(aa.substring(1,2));
		System.out.println(aa.substring(2,3));
		System.out.println(aa.substring(2));
		
	}
}
