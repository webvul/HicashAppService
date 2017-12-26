package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.param.CityQuery;
import com.hengyuan.hicash.domain.query.param.ProvinceQuery;
import com.hengyuan.hicash.domain.query.param.SystemParamQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.StuApp1Query;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.StuAppReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuApp1Resp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * hicash手机端学生提现申请1查询逻辑类
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuApp1Service implements RespService {
	

	private static Logger logger = Logger.getLogger(StuApp1Service.class);

	private StuApp1Query stuQuery = new StuApp1Query();
	
	private CustomerQuery customerQuery = new CustomerQuery();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		StuAppReq req = (StuAppReq) parmRequest;
		StuApp1Resp resp = null;
		
		try{
			String custType = customerQuery.queryCustType(req.getUserName());
			if (Consts.APP_CUSTOMER_TYPE_KHL1.equals(custType)) {
				resp = stuQuery.querystuApp1QureyResp(req.getUserName());
				if(resp != null){
					resp.setCustTypeName(Consts.APP_CUSTOMER_TYPE_KHL1_NAME);
					resp = queryParmName(resp);
					resp.setResultCode(ResultCodes.NORMAL);
				}else{
					resp = new StuApp1Resp();
					resp.setResultCode(ResultCodes.STU_APP_DETAIL_QUERY_NOT_FOUND);
				}
			}else {
				if (custType != null) {
					resp = new StuApp1Resp();
					resp.setResultCode(ResultCodes.STU_APP_DETAIL_CUSTTYPE_NOT_FOUND);
				}else {
					resp = new StuApp1Resp();
					resp.setResultCode(ResultCodes.STU_APP_DETAIL_QUERY_NOT_FOUND);
				}
			}
			
		}catch (Exception e) {
			resp = new StuApp1Resp();
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
	public StuApp1Resp queryParmName(StuApp1Resp resp){
		
		SystemParamQuery query = new SystemParamQuery();
		resp.setEducationalName(query.queryParamByDicCode(resp.getEducational()).getDicName());//学历
		resp.setStudentClassName(query.queryParamByDicCode(resp.getStudentClass()).getDicName());//年级
		resp.setFulltimeName(query.queryParamByDicCode(resp.getFulltime()).getDicName());//学制
		resp.setStuTypeName(query.queryParamByDicCode(resp.getStuType()).getDicName());//学生类型
		
		return resp;
	}
	/**
	 * 查询地址ID所对应的名称
	 * @param resp
	 * @return
	 */
//	public StuApp1Resp queryAddressName(StuApp1Resp resp){
//		ProvinceQuery provinceQuery = new ProvinceQuery();
//		CityQuery cityQuery = new CityQuery();
//		
//		resp.setSchoolProName(provinceQuery.queryProvince(resp.getSchoolPro()).getProvName());
//		resp.setSchoolCityName(cityQuery.queryCity(resp.getSchoolCity()).getCityName());
//		
//		
//		return resp;
//	}

}
