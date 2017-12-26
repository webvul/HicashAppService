package com.hengyuan.hicash.domain.service.user;

import java.util.List;

import org.apache.commons.httpclient.Cookie;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.ReceiveAddressEvent;
import com.hengyuan.hicash.domain.query.amount.AvailBalanceQuery;
import com.hengyuan.hicash.domain.query.app.ApplicationPayQuery;
import com.hengyuan.hicash.domain.query.param.AreaQuery;
import com.hengyuan.hicash.domain.query.param.CityQuery;
import com.hengyuan.hicash.domain.query.param.ProvinceQuery;
import com.hengyuan.hicash.domain.query.user.AddressQuery;
import com.hengyuan.hicash.domain.query.user.CollarApp2MsgQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.user.CustReceiveAddressEntity;
import com.hengyuan.hicash.exception.UpdateAddressException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CustReceiveAddressReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollarApp2MsgResp;
import com.hengyuan.hicash.parameters.response.user.CustAddressResp;
import com.hengyuan.hicash.parameters.response.user.NewCollarApp2MsgResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 查看列表
 * 
 * @author ding
 * 
 */
public class QueryCustAddressService implements RespService {

	private static Logger logger = Logger
			.getLogger(QueryCustAddressService.class);

	private String resultCode = "";

	private AddressQuery addressQuery = new AddressQuery();
	
	private CollarApp2MsgQuery collarApp2MsgQuery = new CollarApp2MsgQuery();

	private ApplicationPayQuery applicationPayQuery = new ApplicationPayQuery();
	
	private AvailBalanceQuery availBalanceQuery = new AvailBalanceQuery();
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CustReceiveAddressReq req = (CustReceiveAddressReq) parmRequest;
		CustAddressResp resp = new CustAddressResp();
		List<CustReceiveAddressEntity> list = null;
		ApplicationPay app = null;
		String hkamount = "";
		try {
			
			list = addressQuery.queryAddress(req.getCust_user());
			for(CustReceiveAddressEntity c:list){
				queryAddressName(c);
				if(c.getRemark()!=null && (c.getRemark().equals("1")||c.getRemark().equals("2"))){
					c.setIsCreditAddress("1");
				}else{
					c.setIsCreditAddress("0");
				}
			}
			app = applicationPayQuery.queryHINSAppFirst(req.getCust_user());
			
			if(app!=null){
				hkamount = availBalanceQuery.getHKAmount(app.getUsername(), app.getApplicationNo());
				//还款比例大于0。5
				if(Float.parseFloat(hkamount)>0.5f){
					resp.setIsAdd("1");
				} else{
					resp.setIsAdd("0");
				}
			}else{
				resp.setIsAdd("0");
			}
			
			
			
			
			
			resultCode = ResultCodes.NORMAL;
		
		
		} catch (Exception e) {
			resultCode = ResultCodes.QUERY_ADDRESS_EXCEPTION;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.QUERY_ADDRESS_EXCEPTION, e);
		}finally {
			ConnManager.closeConn();
		}

		resp.setResultCode(resultCode);
		resp.setList(list);
		return resp;
	}
	
	
	
	/**
	 * 查询地址ID所对应的名称
	 * @param resp
	 * @return
	 */
	public void  queryAddressName(CustReceiveAddressEntity resp){
		ProvinceQuery provinceQuery = new ProvinceQuery();
		CityQuery cityQuery = new CityQuery();
		AreaQuery areaQuery = new AreaQuery();

		
		resp.setProvince_name(provinceQuery.queryProvince(resp.getProvince_code()).getProvName());
		resp.setCity_name(cityQuery.queryCity(resp.getCity_code()).getCityName());
		resp.setArea_name(areaQuery.queryArea(resp.getArea_code()).getAreaName());
		
		

		
		
	}
	
	


}
