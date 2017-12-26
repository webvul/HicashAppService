package com.hengyuan.hicash.domain.service.param;

import java.util.List;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.param.ChannelCityQuery;
import com.hengyuan.hicash.domain.query.param.ChannelInfoQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.ChannelEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.param.ChannelInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.param.ChannelInfoResp;

/**
 * 获取频道列表 处理业务
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ChannelInfoService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		ChannelInfoReq infoReq = (ChannelInfoReq)parmRequest;
		ChannelInfoResp infoResp = new ChannelInfoResp();
		
		try {
			List<ChannelEntity> channelList = queryChannelList(infoReq);
			if(channelList != null && channelList.size() > 0){
				infoResp.setList(channelList);
				resultCode = ResultCodes.NORMAL;
			}else{
				resultCode = ResultCodes.NO_RESULT;
			}
		} catch (Exception e) {
			resultCode = ResultCodes.CHANNELINFO_EXCEPTION;
		} finally {
			ConnManager.closeConn();
		}
		
		infoResp.setResultCode(resultCode);
		return infoResp;
	}
	
	protected List<ChannelEntity> queryChannelType(ChannelInfoReq infoReq){
		
		ChannelInfoQuery infoQuery = new ChannelInfoQuery();
		
		if(Consts.CHANNEL_TYPE_HICASH.equals(infoReq.getChannelType())){
			return infoQuery.queryMerType();
		}else{
			return infoQuery.queryHicashAppType();
		}
		
	}
	
	protected List<ChannelEntity> queryChannelList(ChannelInfoReq infoReq) {

		ChannelCityQuery channelQuery = new ChannelCityQuery();
		return channelQuery.queryChannelList(infoReq.getCityCode());
	}
			

}
