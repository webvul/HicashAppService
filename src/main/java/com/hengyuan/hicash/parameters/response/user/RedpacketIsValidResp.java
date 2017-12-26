package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.entity.user.RedpacketInfoEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * @author xing.yuan
 * @date 2017年12月21日 下午3:34:05
 * 类说明
 */
public class RedpacketIsValidResp extends ParmResponse{
	
	private RedpacketInfoEntity redpacketInfo;

	public RedpacketInfoEntity getRedpacketInfo() {
		return redpacketInfo;
	}

	public void setRedpacketInfo(RedpacketInfoEntity redpacketInfo) {
		this.redpacketInfo = redpacketInfo;
	}
	

}
