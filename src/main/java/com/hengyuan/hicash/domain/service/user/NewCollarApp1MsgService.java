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
import com.hengyuan.hicash.domain.query.user.NewCollarApp1MsgQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CollarApp1MsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollarApp1MsgResp;
import com.hengyuan.hicash.parameters.response.user.NewCollarApp1MsgResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 手机app1白领查看我的资料逻辑类
 * 
 * @author LiHua.Ren
 * @create date 2015-06-17
 *
 */
public class NewCollarApp1MsgService implements RespService {
	
	private static Logger logger = Logger.getLogger(NewCollarApp1MsgService.class);

	private NewCollarApp1MsgQuery collarQuery = new NewCollarApp1MsgQuery();
	
	private CustomerQuery customerQuery = new CustomerQuery();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		CollarApp1MsgReq req = (CollarApp1MsgReq) parmRequest;
		NewCollarApp1MsgResp resp = null;
		
		try{
			String custType = customerQuery.queryCustType(req.getUserName());
			if (Consts.APP_CUSTOMER_TYPE_KHL2.equals(custType)) {
				resp = collarQuery.queryCollarQureyResp(req.getUserName());
				if(resp != null){
					resp = queryParmName(resp);
					resp.setCustTypeName(Consts.APP_CUSTOMER_TYPE_KHL2_NAME);
					resp=queryAddressName(resp);
					resp.setResultCode(ResultCodes.NORMAL);
				}else{
					resp = new NewCollarApp1MsgResp();
					resp.setResultCode(ResultCodes.COLLAR_DETAIL_QUERY_NOT_FOUND);
				}
			}else {
				if (custType != null) {
					resp = new NewCollarApp1MsgResp();
					resp.setResultCode(ResultCodes.COLLAR_DETAIL_CUSTTYPE_NOT_FOUND);
				}else {
					resp = new NewCollarApp1MsgResp();
					resp.setResultCode(ResultCodes.COLLAR_DETAIL_QUERY_NOT_FOUND);
				}
			}
			
		}catch (Exception e){
			resp = new NewCollarApp1MsgResp();
			resp.setResultCode(ResultCodes.COLLAR_DETAIL_QUERY_EXCEPTION);
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.COLLAR_DETAIL_QUERY_EXCEPTION, e);
		}finally{
			ConnManager.closeConn();
		}
		return resp;
	}
	
	
	/**
	 * 查询系统参数代码对应的名称
	 * @param resp
	 * @return
	 */
	public NewCollarApp1MsgResp queryParmName(NewCollarApp1MsgResp resp){
		
		SystemParamQuery query = new SystemParamQuery();
		
		
		resp.setMaritalStatusName(query.queryParamByDicCode(resp.getMaritalStatus()).getDicName());
		resp.setExpressTypeName(query.queryParamByDicCode(resp.getExpressType()).getDicName());
		return resp;
	}

	
	/**
	 * 查询地址ID所对应的名称
	 * @param resp
	 * @return
	 */
	public NewCollarApp1MsgResp queryAddressName(NewCollarApp1MsgResp resp){
		ProvinceQuery provinceQuery = new ProvinceQuery();
		CityQuery cityQuery = new CityQuery();
		AreaQuery areaQuery = new AreaQuery();

		
		resp.setFimilyProName(provinceQuery.queryProvince(resp.getFimilyPro()).getProvName());
		resp.setFimilyCityName(cityQuery.queryCity(resp.getFimilyCity()).getCityName());
		resp.setFimilyAreaName(areaQuery.queryArea(resp.getFimilyArea()).getAreaName());
//		
		
		
		return resp;
	}

}
