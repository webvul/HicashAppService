package com.hengyuan.hicash.parameters.response.param;

import java.util.List;

import com.hengyuan.hicash.entity.param.ProShowEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 产品展示信息 返回参数
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ProShowInfoResp extends ParmResponse {

	/** 展示商品集合 */
	private List<ProShowEntity> proShowList;

	public List<ProShowEntity> getProShowList() {
		return proShowList;
	}

	public void setProShowList(List<ProShowEntity> proShowList) {
		this.proShowList = proShowList;
	}

}
