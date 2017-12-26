package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.param.AreaQuery;
import com.hengyuan.hicash.domain.query.param.CityQuery;
import com.hengyuan.hicash.domain.query.param.ProvinceQuery;
import com.hengyuan.hicash.domain.query.param.SystemParamQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.StuApp2Query;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.StuAppReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuApp2Resp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * hicash手机端学生提现申请2业务逻辑类
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuApp2Service implements RespService{

	private static Logger logger = Logger.getLogger(StuApp2Service.class);

	private StuApp2Query app2Query = new StuApp2Query();
	
	private CustomerQuery customerQuery = new CustomerQuery();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		StuAppReq req = (StuAppReq) parmRequest;
		StuApp2Resp resp = null;
		
		try{
			String custType = customerQuery.queryCustType(req.getUserName());
			if (Consts.APP_CUSTOMER_TYPE_KHL1.equals(custType)) {
				resp = app2Query.queryStuApp2Resp(req.getUserName());
				if(resp != null){
					resp.setCustTypeName(Consts.APP_CUSTOMER_TYPE_KHL1_NAME);
					resp = queryAddressName(resp);
					resp=queryParmName(resp);
					resp.setResultCode(ResultCodes.NORMAL);
				}else{
					resp = new StuApp2Resp();
					resp.setResultCode(ResultCodes.STU_APP_DETAIL_QUERY_NOT_FOUND);
				}
			}else {
				if (custType != null) {
					resp = new StuApp2Resp();
					resp.setResultCode(ResultCodes.STU_APP_DETAIL_CUSTTYPE_NOT_FOUND);
				}else {
					resp = new StuApp2Resp();
					resp.setResultCode(ResultCodes.STU_APP_DETAIL_QUERY_NOT_FOUND);
				}
			}
			
		}catch (Exception e) {
			resp = new StuApp2Resp();
			resp.setResultCode(ResultCodes.STU_APP_DETAIL_QUERY_EXCEPTION);
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.STU_APP_DETAIL_QUERY_EXCEPTION, e);
		} finally {
			ConnManager.closeConn();
		}

		
		return resp;
	}
	
	/**
	 * 查询地址ID所对应的名称
	 * @param resp
	 * @return
	 */
	public StuApp2Resp queryAddressName(StuApp2Resp resp){
		ProvinceQuery provinceQuery = new ProvinceQuery();
		CityQuery cityQuery = new CityQuery();
		AreaQuery areaQuery = new AreaQuery();	
		resp.setFimilyProName(provinceQuery.queryProvince(resp.getFimilyPro()).getProvName());
		resp.setFimilyCityName(cityQuery.queryCity(resp.getFimilyCity()).getCityName());
		resp.setFimilyAreaName(areaQuery.queryArea(resp.getFimilyArea()).getAreaName());
		
		resp.setSchoolProName(provinceQuery.queryProvince(resp.getSchoolPro()).getProvName());
		resp.setSchoolCityName(cityQuery.queryCity(resp.getSchoolCity()).getCityName());
		resp.setSchoolAreaName(areaQuery.queryArea(resp.getSchoolArea()).getAreaName());
		resp.setExpressProvinceName(provinceQuery.queryProvince(resp.getExpressProvince()).getProvName());
		resp.setExpressCityName(cityQuery.queryCity(resp.getExpressCity()).getCityName());
		resp.setExpressDistrictName(areaQuery.queryArea(resp.getExpressDistrict()).getAreaName());
		
		
		return resp;
	}
	/**
	 * 查询系统参数代码对应的名称
	 * @param resp
	 * @return
	 */
	public StuApp2Resp queryParmName(StuApp2Resp resp){
		
		SystemParamQuery query = new SystemParamQuery();
		resp.setExpressTypeName(query.queryParamByDicCode(resp.getExpressType()).getDicName());
		return resp;
	}

}
