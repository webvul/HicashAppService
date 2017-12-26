package com.hengyuan.hicash.domain.service.user;

import sun.swing.StringUIClientPropertyKey;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.param.AreaQuery;
import com.hengyuan.hicash.domain.query.param.BankQuery;
import com.hengyuan.hicash.domain.query.param.CityQuery;
import com.hengyuan.hicash.domain.query.param.ProvinceQuery;
import com.hengyuan.hicash.domain.query.user.CustUserCardQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.entity.user.CustUserCard;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.UserNameCardReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollarApp2MsgResp;
import com.hengyuan.hicash.parameters.response.user.UserNameCardResp;
import com.hengyuan.hicash.utils.BorrowmoneyuseUtil;
import com.hengyuan.hicash.utils.StringUtils;
/**
 * 查询用户信息（银行卡和真实姓名）
 * @author Administrator
 *
 */
public class UserNameCardService implements RespService{
	
	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		
		UserNameCardReq userNameCardReq = (UserNameCardReq)parmRequest;
		UserNameCardResp userNameCardResp = new UserNameCardResp();
		
		CustomerQuery customerQuery = new CustomerQuery();
		CustUserCardQuery custUserCardQuery = new CustUserCardQuery();
		BankQuery bankQuery = new BankQuery();
		CustomerEntity customerEntity = customerQuery.queryCustByUser(userNameCardReq.getUserName());
		if(customerEntity != null){
			userNameCardResp.setRealName(customerEntity.getRealName());
			userNameCardResp.setIdcard(customerEntity.getIdentityNo());
			
			CustUserCard custCard = custUserCardQuery.getCustUserCardDeFault(userNameCardReq.getUserName());
			if(custCard != null){
				userNameCardResp.setBank(custCard.getBank());
				BankEntity bank = bankQuery.queryBankName(custCard.getBank());
				userNameCardResp.setBankName(bank.getBankName());
				userNameCardResp.setCardNum(custCard.getCardNum());
			}else{
				userNameCardResp.setBank("");
				userNameCardResp.setBankName("");
				userNameCardResp.setCardNum("");
			}
			userNameCardResp = queryAddressName(customerEntity,userNameCardResp);
			userNameCardResp.setAddressRoad(customerEntity.getNowAddress());
			userNameCardResp.setMobile(customerEntity.getMobile());
			userNameCardResp.setEmail(customerEntity.getEmailAdress());
			if(StringUtils.isEmpty(customerEntity.getLoanPurpose())){
				userNameCardResp.setLoanPurpose("日常消费");
			}else{
				userNameCardResp.setLoanPurpose(BorrowmoneyuseUtil.getUseMap().get(customerEntity.getLoanPurpose()));
			}
			
			resultCode = ResultCodes.NORMAL;
		
	     }else{
		   resultCode = ResultCodes.USERINFO_USER_NOTFOUND;
	    }
		userNameCardResp.setResultCode(resultCode);
		return userNameCardResp;
	}

	
	/**
	 * 查询地址ID所对应的名称
	 * @param resp
	 * @return
	 */
	public UserNameCardResp queryAddressName(CustomerEntity customerEntity,UserNameCardResp resp){
		ProvinceQuery provinceQuery = new ProvinceQuery();
		CityQuery cityQuery = new CityQuery();
		AreaQuery areaQuery = new AreaQuery();

		resp.setProvinceName(provinceQuery.queryProvince(customerEntity.getNowProvince()).getProvName());
		resp.setCityName(cityQuery.queryCity(customerEntity.getNowCity()).getCityName());
		resp.setAreaName(areaQuery.queryArea(customerEntity.getNowArea()).getAreaName());
		
		
		return resp;
	}
}
