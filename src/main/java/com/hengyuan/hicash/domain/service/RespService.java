package com.hengyuan.hicash.domain.service;

import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 处理逻辑service总接口
 * @author Andy.Niu
 *
 */
public interface RespService {
	
	public ParmResponse responseValue(ParmRequest parmRequest);
	
}
