package com.hengyuan.hicash.domain.service.param;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.param.AreaQuery;
import com.hengyuan.hicash.domain.query.param.CityQuery;
import com.hengyuan.hicash.domain.query.param.ProvinceQuery;
import com.hengyuan.hicash.domain.query.user.CustcustomerStuQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.param.StuInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.param.StuInfoResp;

/** 
 * @author dong.liu 
 * 2017-1-9 下午7:50:07
 * 类说明 :查询学生个人信息
 */
public class StuInfoService implements RespService{
	
	
	private CustcustomerStuQuery custcustomerStuQuery = new CustcustomerStuQuery();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		StuInfoReq stuInfoReq = (StuInfoReq)parmRequest;
		StuInfoResp resp=new StuInfoResp();
		String userName=stuInfoReq.getUserName();
		String resultCode="";
		try {
			
			resp  = querySamePro(userName);
			
			if(resp != null){
				resp = queryAddressName(resp);
				resultCode = ResultCodes.NORMAL;
			}else{
				resultCode = ResultCodes.NO_RESULT;
			}
			
		} catch (Exception e) {
			resultCode = ResultCodes.STU_APP_DETAIL_QUERY_EXCEPTION;
		} finally {
			ConnManager.closeConn();
		}
		
		resp.setResultCode(resultCode);
		return resp;
	}
	
	
	
	/**
	 * 查询地址ID所对应的名称
	 * @param resp
	 * @return
	 */
	public StuInfoResp queryAddressName(StuInfoResp resp){
		ProvinceQuery provinceQuery = new ProvinceQuery();
		CityQuery cityQuery = new CityQuery();
		AreaQuery areaQuery = new AreaQuery();

		resp.setOtherAdressProvinceName(provinceQuery.queryProvince(resp.getOtherAdressProvince()).getProvName());
		resp.setOtherAdressCityName(cityQuery.queryCity(resp.getOtherAdressCity()).getCityName());
		resp.setOtherAdressAreaName(areaQuery.queryArea(resp.getOtherAdressArea()).getAreaName());
		return resp;
	}
	
	
	public StuInfoResp querySamePro(String userName){
		
		StuInfoResp resp=new StuInfoResp();
		CustCustomer cust=custcustomerStuQuery.queryCustCustomer(userName);
		resp.setRealName(cust.getRealName());
		resp.setIdentityNo(cust.getIdentityNo());
		resp.setEmailAdress(cust.getEmailAdress());
		resp.setMaritalStatus(cust.getMaritalStatus());
		resp.setOtherAdressProvince(cust.getOtherAdressProvince());
		resp.setOtherAdressCity(cust.getOtherAdressCity());
		resp.setOtherAdressArea(cust.getOtherAdressArea());
		resp.setOtherAccommodationAddress(cust.getOtherAccommodationAddress());
		resp.setIdcard_validity_startdate(cust.getIdCardValStartDate());
		resp.setIdcard_validity_enddate(cust.getIdCardValEndDate());
		resp.setLoan_purpose(cust.getLoanUse());
		return resp;
	}
	
	


}
