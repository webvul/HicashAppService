package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.param.HomePageInfoReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * app首页展示(图片和热卖商品) validate
 * @author Cary.Liu
 * @createDate 2015-06-05
 *
 */
public class HomePageInfoVal {

	private HomePageInfoReq infoReq;
	
	public HomePageInfoVal (HomePageInfoReq infoReq){
		this.infoReq = infoReq;
	}

	public String validate(){
		
		//验证城市
		if (RegexValidate.isNull(infoReq.getCityCode())) {
			return ResultCodes.HOMEPAGE_CITY_ISNULL;
		}

		if (!RegexValidate.isSelectToAddress(infoReq.getCityCode())) {
			return ResultCodes.HOMEPAGE_CITY_ILLEGAL;
		}
		
		return ResultCodes.NORMAL;
	}
	
	
	public HomePageInfoReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(HomePageInfoReq infoReq) {
		this.infoReq = infoReq;
	}
	
	
	
}
