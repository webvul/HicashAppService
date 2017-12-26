package com.hengyuan.hicash.domain;

import java.text.ParseException;

import com.hengyuan.hicash.exception.ActivityEndedException;
import com.hengyuan.hicash.exception.ActivityIsLotteryException;
import com.hengyuan.hicash.exception.ActivityLotteryNumbzException;
import com.hengyuan.hicash.exception.ActivityNoStartException;
import com.hengyuan.hicash.exception.UpdateCustUserException;
import com.hengyuan.hicash.exception.UpdateLotteryException;
import com.hengyuan.hicash.exception.UpdateLotterycfgException;
import com.hengyuan.hicash.exception.UserNameNoFindException;
import com.hengyuan.hicash.parameters.request.activity.NewYearActivityReq;
import com.hengyuan.hicash.parameters.response.activity.NewYearActivityResp;

public interface Activity {

	/**
	 * 新年大转盘活动
	 * @param req
	 * @return
	 * @throws UpdateLotterycfgException
	 * @throws UpdateLotteryException
	 * @throws UpdateCustUserException
	 * @throws UserNameNoFindException
	 * @throws ActivityIsLotteryException
	 * @throws ActivityLotteryNumbzException
	 * @throws ParseException 
	 * @throws ActivityEndedException 
	 * @throws ActivityNoStartException 
	 */
//	public NewYearActivityResp newYearLotteryService(NewYearActivityReq req) throws UpdateLotterycfgException, UpdateLotteryException, UpdateCustUserException, UserNameNoFindException, ActivityIsLotteryException, ActivityLotteryNumbzException, ActivityNoStartException, ActivityEndedException, ParseException;
	public NewYearActivityResp newYearLotteryService(NewYearActivityReq req) throws Exception;
	
	public NewYearActivityResp newYearRedPackService(NewYearActivityReq req) throws UpdateLotterycfgException, UpdateLotteryException, UpdateCustUserException, UserNameNoFindException, ActivityIsLotteryException, ActivityLotteryNumbzException;
	
}
