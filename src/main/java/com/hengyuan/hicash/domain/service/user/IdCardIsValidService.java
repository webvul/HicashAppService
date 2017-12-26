package com.hengyuan.hicash.domain.service.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hengyuan.hicash.domain.query.app.ApplicationPayQuery;
import com.hengyuan.hicash.domain.query.mktApp.CreateAppPayQuery;
import com.hengyuan.hicash.domain.query.user.CustTempApplyPicQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.CustTempApplyPicEntity;
import com.hengyuan.hicash.exception.CustomerNotFoundException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.IdCardIsValidReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.IdCardIsValidResp;
import com.hengyuan.hicash.utils.ResourceUtils;

public class IdCardIsValidService implements RespService{

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		IdCardIsValidReq idCardIsValidReq = (IdCardIsValidReq) parmRequest;
		IdCardIsValidResp idCardIsValidResp  = new IdCardIsValidResp();
		try {
			idCardIsValidResp = isValid(idCardIsValidReq);
		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
		}
		return idCardIsValidResp;
	}

	private IdCardIsValidResp isValid(IdCardIsValidReq idCardIsValidReq) throws CustomerNotFoundException {
		IdCardIsValidResp idCardIsValidResp  = new IdCardIsValidResp();
		
		idCardIsValidResp.setIsValid("N");//无效
		
		CreateAppPayQuery appPayQuery = new CreateAppPayQuery(); 		
		CustCustomer custCustomer =	appPayQuery.getCustCustomer(idCardIsValidReq.getUsername());
		CustTempApplyPicQuery custTempApplyPicQuery = new CustTempApplyPicQuery(); 
		
		List<CustTempApplyPicEntity> listl02 = custTempApplyPicQuery.queryInfoByUnPt(idCardIsValidReq.getUsername(), "ZL02");
		List<CustTempApplyPicEntity> listl03 = custTempApplyPicQuery.queryInfoByUnPt(idCardIsValidReq.getUsername(), "ZL03");	
		
		if(listl02.size() > 0 && listl03.size() > 0){

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			if("H5".equals(idCardIsValidReq.getApplyFrom())){
		
				List<CustTempApplyPicEntity> listl12 = custTempApplyPicQuery.queryInfoByUnPt(idCardIsValidReq.getUsername(), "ZL112");
				CustTempApplyPicEntity custTempApply = custTempApplyPicQuery.queryInfoByPic(idCardIsValidReq.getUsername());
			
		        Calendar c = Calendar.getInstance();
		        //过去三个月
		        c.setTime(new Date());
		        c.add(Calendar.MONTH, -3);
		        Date m3 = c.getTime();
		        String mon3 = sdf.format(m3);
				if(listl12.size() > 0&&!StringUtils.isEmpty(custTempApply.getUpdateDate())){//并且上传时间小于3个月
					int result = mon3.compareTo(custTempApply.getUpdateDate()); 
					if(result >=0){
						idCardIsValidResp.setIsValid("Y");
					}
				}	
			}else{
				ApplicationPayQuery pay = new ApplicationPayQuery();
				ApplicationPay app = pay.queryAppPayByUserNameAndApp(idCardIsValidReq.getUsername());
				if(app!=null){
					List<CustTempApplyPicEntity> listl68 = custTempApplyPicQuery.queryInfoByUnPt(idCardIsValidReq.getUsername(), "ZL168");
					if(listl68.size() > 0){
						if(!app.getApplyFrom().equals("HTML5")||(app.getApplyFrom().equals("HTML5")&&listl02.get(0).getPicCaption().equals("APP")&&
								listl03.get(0).getPicCaption().equals("APP")&&listl68.get(0).getPicCaption().equals("APP"))){													
							if( !StringUtils.isEmpty(custCustomer.getIdCardValEndDate())){  //查询到过期日期	
								String nowTime =sdf.format(new Date());						// new Date()为获取当前系统时间
								int result = nowTime.compareTo(custCustomer.getIdCardValEndDate()); 
								if(result <= 0){
									idCardIsValidResp.setIsValid("Y");
								}					
							}					
						}
					}
				}
			}
		}
		
		idCardIsValidResp.setUnitIndustryIsNull("Y");	
		
		if(StringUtils.isNotEmpty(custCustomer.getUnitProperties())){
			idCardIsValidResp.setUnitIndustryIsNull("N");	
		}
		
		return idCardIsValidResp;
	}

}
