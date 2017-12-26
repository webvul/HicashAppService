package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

public class IdCardIsValidResp extends ParmResponse{
	
	private String isValid;//是否有效: Y/有效, N/无效
	
	private String unitIndustryIsNull;//单位行业是否为空:Y/空,N/不为空

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getUnitIndustryIsNull() {
		return unitIndustryIsNull;
	}

	public void setUnitIndustryIsNull(String unitIndustryIsNull) {
		this.unitIndustryIsNull = unitIndustryIsNull;
	}

}
