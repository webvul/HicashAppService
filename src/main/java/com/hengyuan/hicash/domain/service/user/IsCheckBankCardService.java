package com.hengyuan.hicash.domain.service.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.param.WithholdPartQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.WhichPartRecord;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.IsCheckBankCardReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.IsCheckBankCardResp;


/**
 * 查询该用户名下的银行卡号是否有校验记录
 * 
 * @author jason
 * @createDate 20160804
 */

public class IsCheckBankCardService implements RespService {

	private static Logger logger = Logger
			.getLogger(IsCheckBankCardService.class);


	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		IsCheckBankCardReq isCheckBankCardReq = (IsCheckBankCardReq) parmRequest;
		IsCheckBankCardResp checkBankCardResp = new IsCheckBankCardResp();
		String resultCode = ResultCodes.NO_RESULT;
		try{
			//根据用户名以及银行卡号查询withhold_part_record 表中记录
			WithholdPartQuery whichQuery = new WithholdPartQuery();
			List<WhichPartRecord> listPart = whichQuery.queryWhichPartByUserBank(isCheckBankCardReq.getUserName(),isCheckBankCardReq.getAccountNo());
			if(listPart != null && listPart.size() > 0) {
				resultCode = ResultCodes.NORMAL;
			}
		}catch(Exception e){
			logger.error("查询银行卡验证记录异常:",e);
			resultCode = ResultCodes.QUERY_BANK_CARD_VALIDATE_EXCEPTION;
		}
	    checkBankCardResp.setResultCode(resultCode);
		return checkBankCardResp;
	}

}
