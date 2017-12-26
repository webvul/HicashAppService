package com.hengyuan.hicash.domain.service.activity;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.Activity;
import com.hengyuan.hicash.domain.Provider;
import com.hengyuan.hicash.domain.factory.NewYearLotteryFacotry;
import com.hengyuan.hicash.domain.query.activity.NewYearLotterQuery;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.activity.NewYearLotteryEntity;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.exception.ActivityEndedException;
import com.hengyuan.hicash.exception.ActivityIsLotteryException;
import com.hengyuan.hicash.exception.ActivityLotteryNumbzException;
import com.hengyuan.hicash.exception.ActivityNoStartException;
import com.hengyuan.hicash.exception.UserNameNoFindException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.activity.NewYearActivityReq;
import com.hengyuan.hicash.parameters.request.activity.NewYearRedPackReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.activity.NewYearActivityResp;
import com.hengyuan.hicash.parameters.response.activity.NewYearRedPackResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 新年红包抽取 service
 * @author Cary.Liu
 * @createDate 2016-02-01
 *
 */
public class NewYearRedPackService implements RespService {

	private String resultCode = "";
	
	private static Logger logger = Logger.getLogger(NewYearRedPackService.class);
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		NewYearRedPackReq redPackReq = (NewYearRedPackReq)parmRequest;
		NewYearRedPackResp redPackResp = new NewYearRedPackResp();
				
		try {
			
			CustUserEntity custUser = queryCustUser(redPackReq.getUserName());
			CustomerEntity customer = queryCustomer(redPackReq.getUserName());
			
			/* 验证 */
			validate(redPackReq, custUser, customer);
			
			ConnManager.beginTransaction();
			
			/* 线程同步抽奖 */
			Provider provider = new NewYearLotteryFacotry();
			Activity activity = provider.createActivity(Consts.FINAL_INT_2);
			
			NewYearActivityReq actReq = new NewYearActivityReq();
			actReq.setUserName(redPackReq.getUserName());
			/* 获取抽奖结果 */
			NewYearActivityResp actResp = activity.newYearRedPackService(actReq);
			
			redPackResp.setIsLottery(actResp.getIsLottery()); // 是否中奖
			redPackResp.setLotteryName(actResp.getLotteryName()); // 奖品名称
			redPackResp.setRtnNoLotteryMsg(actResp.getRtnNoLotteryMsg()); // 返回未中奖消息提示
			
			ConnManager.commit();
			
			resultCode = ResultCodes.NORMAL;
			
		} catch (ActivityLotteryNumbzException e) {
			resultCode = ResultCodes.NEWYEARREDPACK_LOTNUM_ISZERO;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (ActivityIsLotteryException e) {
			resultCode = ResultCodes.NEWYEARREDPACK_LOTONE;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (ActivityEndedException e){
			resultCode = ResultCodes.NEWYEARREDPACK_ACT_ENDED;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (ActivityNoStartException e) {
			resultCode = ResultCodes.NEWYEARREDPACK_ACT_NOSTART;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (UserNameNoFindException e) {
			resultCode = ResultCodes.NEWYEARREDPACK_USER_NOTFOUND;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (Exception e) {
			e.printStackTrace();
			resultCode = ResultCodes.NEWYEARREDPACK_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} finally {
			ConnManager.closeConn();
		}
		
		redPackResp.setResultCode(resultCode);
		return redPackResp;
	}

	private void validate(NewYearRedPackReq redPackReq, CustUserEntity custUser, CustomerEntity customer) throws UserNameNoFindException, ParseException, ActivityNoStartException, ActivityEndedException, ActivityIsLotteryException, ActivityLotteryNumbzException{
		
		/* 验证用户 */
		if(custUser == null || customer == null){
			throw new UserNameNoFindException();
		}
		
		/* 活动时间 */
		String actStartDate = ResourceUtils.getValue("newyear_act_startdate");
		String actEndDate = ResourceUtils.getValue("newyear_act_enddate");	
		// 是否到了活动开始时间
		if(DateUtils.outCurrentTimeFlag(actStartDate)){
			throw new ActivityNoStartException();
		}
		// 活动时间是否结束
		if (!DateUtils.outCurrentTimeFlag(actEndDate)) {
			throw new ActivityEndedException();
		}
		
		/* 是否已经有过抽奖记录 */
		int isLottery = custUser.getIsLottery();
		if(isLottery > 0 || isUserLotteryRecord(redPackReq.getUserName())){
			throw new ActivityIsLotteryException();
		}
		
		/* 剩余抽奖次数是否大于0 */
		int lotteryNum = custUser.getLotteryNum();
		if(lotteryNum <= 0){
			throw new ActivityLotteryNumbzException();
		}
		
	}
	
	private CustUserEntity queryCustUser(String userName){
		
		CustUserQuery custUserQuery = new CustUserQuery();
		
		return custUserQuery.queryByUserName(userName);
	}
	
	private CustomerEntity queryCustomer(String userName){
		
		CustomerQuery customerQuery = new CustomerQuery();
		
		return customerQuery.queryCustomerByUserName(userName);
	}
	
	/**
	 * 用户是否有过抽奖记录
	 * @param userName
	 * @return
	 */
	private boolean isUserLotteryRecord(String userName){
		
		NewYearLotterQuery lotQuery = new NewYearLotterQuery();
		
		List<NewYearLotteryEntity> list = lotQuery.queryUserlotteryRecord(userName, Consts.FINAL_NUMBER_2);
		
		if(list != null && list.size() > 0){
			return true;
		}
		
		return false;
	}
	
}
