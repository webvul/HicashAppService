package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.entity.user.AppAgainBasicEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

public class AppAgainBasicResp extends ParmResponse {
	
	private AppAgainBasicEntity appAgainBasicEntity;

	public AppAgainBasicEntity getAppAgainBasicEntity() {
		return appAgainBasicEntity;
	}

	public void setAppAgainBasicEntity(AppAgainBasicEntity appAgainBasicEntity) {
		this.appAgainBasicEntity = appAgainBasicEntity;
	}
	
}
