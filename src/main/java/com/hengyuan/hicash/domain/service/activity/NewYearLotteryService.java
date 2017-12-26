package com.hengyuan.hicash.domain.service.activity;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.Activity;
import com.hengyuan.hicash.domain.Provider;
import com.hengyuan.hicash.domain.factory.NewYearLotteryFacotry;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.exception.ActivityEndedException;
import com.hengyuan.hicash.exception.ActivityIsLotteryException;
import com.hengyuan.hicash.exception.ActivityLotteryNumbzException;
import com.hengyuan.hicash.exception.ActivityNoStartException;
import com.hengyuan.hicash.exception.NotInActivityTimeException;
import com.hengyuan.hicash.exception.UserNameNoFindException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.activity.NewYearActivityReq;
import com.hengyuan.hicash.parameters.request.activity.NewYearLotteryReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.activity.NewYearActivityResp;
import com.hengyuan.hicash.parameters.response.activity.NewYearLotteryResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 新年抽奖活动 service
 * @author Cary.Liu
 * @createDate 2015-12-29
 *
 */
public class NewYearLotteryService implements RespService {

	private static Logger logger = Logger.getLogger(NewYearLotteryService.class);
	
	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		NewYearLotteryReq lotReq = (NewYearLotteryReq)parmRequest;
		NewYearLotteryResp lotResp = new NewYearLotteryResp();
		
		try {
			
//			CustUserEntity custUser = queryCustUser(lotReq.getUserName());
//			CustomerEntity customer = queryCustomer(lotReq.getUserName());
			
			/* 验证 */
//			validate(lotReq, custUser, customer);
			
			ConnManager.beginTransaction();
			
			/* 线程同步抽奖 */
			Provider provider = new NewYearLotteryFacotry();
//			Activity activity = provider.createActivity(Consts.FINAL_INT_1);
			Activity activity = provider.createActivity(StringUtils.getActiveNo(lotReq.getActivityType()));
			
			NewYearActivityReq actReq = new NewYearActivityReq();
			actReq.setUserName(lotReq.getUserName());
			/* 获取抽奖结果 */
			NewYearActivityResp actResp = activity.newYearLotteryService(actReq);
			
			lotResp.setActCode(actResp.getActCode());  // 加密字符串
			lotResp.setRandomNum(actResp.getRandomNum()); // 随机数
			
			ConnManager.commit();
			
			resultCode = ResultCodes.NORMAL;
			
		} catch (ActivityLotteryNumbzException e) {
			resultCode = ResultCodes.NEWYEARLOT_LOTNUM_ISZERO;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (ActivityIsLotteryException e) {
			resultCode = ResultCodes.NEWYEARLOT_LOTONE;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (ActivityEndedException e){
			resultCode = ResultCodes.NEWYEARLOT_ACT_ENDED;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (ActivityNoStartException e) {
			resultCode = ResultCodes.NEWYEARLOT_ACT_NOSTART;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (UserNameNoFindException e) {
			resultCode = ResultCodes.NEWYEARLOT_USER_NOTFOUND;
			RecordUtils.writeError(logger, null, resultCode, e);
		} catch (NotInActivityTimeException e) {
			e.printStackTrace();
			resultCode = ResultCodes.NOT_IN_ACTIVITY_TIME;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			resultCode = ResultCodes.NEWYEARLOT_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
			ConnManager.rollback();
		}finally {
			ConnManager.closeConn();
		}
		
		lotResp.setResultCode(resultCode);
		return lotResp;
	}
	
//	private void validate(NewYearLotteryReq lotReq, CustUserEntity custUser, CustomerEntity customer) throws UserNameNoFindException, ParseException, ActivityNoStartException, ActivityEndedException, ActivityIsLotteryException, ActivityLotteryNumbzException{
//		
//		/* 验证用户 */
//		if(custUser == null || customer == null){
//			throw new UserNameNoFindException();
//		}
//		
//		/* 活动时间 */
//		String actStartDate = ResourceUtils.getValue("newyear_act_startdate");
//		String actEndDate = ResourceUtils.getValue("newyear_act_enddate");	
//		// 是否到了活动开始时间
//		if(DateUtils.outCurrentTimeFlag(actStartDate)){
//			throw new ActivityNoStartException();
//		}
//		// 活动时间是否结束
//		if (!DateUtils.outCurrentTimeFlag(actEndDate)) {
//			throw new ActivityEndedException();
//		}
//		
//		/* 是否已经有过抽奖记录 */
//		int isLottery = custUser.getIsLottery();
//		if(isLottery > 0 || isUserLotteryRecord(lotReq.getUserName())){
//			throw new ActivityIsLotteryException();
//		}
//		
//		/* 剩余抽奖次数是否大于0 */
//		int lotteryNum = custUser.getLotteryNum();
//		if(lotteryNum <= 0){
//			throw new ActivityLotteryNumbzException();
//		}
//		
//	}
	
//	private CustUserEntity queryCustUser(String userName){
//		
//		CustUserQuery custUserQuery = new CustUserQuery();
//		
//		return custUserQuery.queryByUserName(userName);
//	}
	
//	private CustomerEntity queryCustomer(String userName){
//		
//		CustomerQuery customerQuery = new CustomerQuery();
//		
//		return customerQuery.queryCustomerByUserName(userName);
//	}
	
	/**
	 * 用户是否有过抽奖记录
	 * @param userName
	 * @return
	 */
//	private boolean isUserLotteryRecord(String userName){
//		
//		NewYearLotterQuery lotQuery = new NewYearLotterQuery();
//		
//		List<NewYearLotteryEntity> list = lotQuery.queryUserlotteryRecord(userName, Consts.FINAL_NUMBER_1);
//		
//		if(list != null && list.size() > 0){
//			return true;
//		}
//		
//		return false;
//	}

}
