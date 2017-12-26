package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.param.ChannelInfoReq;

/**
 * 获取频道列表 请求参数验证
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ChannelInfoVal {

	private ChannelInfoReq infoReq;

	public ChannelInfoVal(ChannelInfoReq infoReq) {
		this.infoReq = infoReq;
	}
	
	public String validate(){
		
		
		
		return ResultCodes.NORMAL;
	}

	public ChannelInfoReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(ChannelInfoReq infoReq) {
		this.infoReq = infoReq;
	}

}
