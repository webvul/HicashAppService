package com.hengyuan.hicash.domain.service.activity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.domain.Activity;
import com.hengyuan.hicash.domain.event.activity.LotteryConfigEvent;
import com.hengyuan.hicash.domain.event.activity.NewYearLotteryEvent;
import com.hengyuan.hicash.domain.event.user.CustUserEvent;
import com.hengyuan.hicash.domain.query.activity.LotteryConfigQuery;
import com.hengyuan.hicash.domain.query.activity.NewYearLotterQuery;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.entity.activity.LotteryConfigEntity;
import com.hengyuan.hicash.entity.activity.NewYearLotteryEntity;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.exception.ActivityIsLotteryException;
import com.hengyuan.hicash.exception.ActivityLotteryNumbzException;
import com.hengyuan.hicash.exception.UpdateCustUserException;
import com.hengyuan.hicash.exception.UpdateLotteryException;
import com.hengyuan.hicash.exception.UpdateLotterycfgException;
import com.hengyuan.hicash.exception.UserNameNoFindException;
import com.hengyuan.hicash.parameters.request.activity.NewYearActivityReq;
import com.hengyuan.hicash.parameters.response.activity.NewYearActivityResp;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 新年红包抽奖活动
 * @author Cary.Liu
 * @createDate 2016-02-01
 *
 */
public class RedPackActiveImpl implements Activity {

	/** 是否中奖 1：是、0：否 */
	private String isLottery = Consts.FINAL_NUMBER_0;

	/** 奖品名称 */
	private String lotteryName = "";

	/** 未中奖的提示消息 */
	private String rtnNoLotteryMsg = "";
	
	private LotteryConfigEvent lotteryCfgEvent = new LotteryConfigEvent();
	
	private NewYearLotteryEvent lotterRecordEvent = new NewYearLotteryEvent();
	
	private CustUserEvent custUserEvent = new CustUserEvent();
	
	@Override
	public NewYearActivityResp newYearLotteryService(NewYearActivityReq req)
			throws UpdateLotterycfgException, UpdateLotteryException,
			UpdateCustUserException, UserNameNoFindException,
			ActivityIsLotteryException, ActivityLotteryNumbzException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized NewYearActivityResp newYearRedPackService(NewYearActivityReq req) throws UpdateLotterycfgException, UserNameNoFindException, ActivityIsLotteryException, ActivityLotteryNumbzException, UpdateLotteryException, UpdateCustUserException {

		CustUserEntity custUser = queryCustUser(req.getUserName());
		CustomerEntity customer = queryCustomer(req.getUserName());
		
		validate(custUser, customer);
		
		NewYearActivityResp actResp = new NewYearActivityResp();
		
		/*  获取数据库配置的所有奖项 */
		List<LotteryConfigEntity> prizeList = queryAllPrize();
		
		String zjPrizeId = Consts.FINAL_NUMBER_0; // 中奖Id
		String zjPrizeName = ""; // 中奖奖品名称
		List<String> cjIdArray = new ArrayList<String>();
		
		if(prizeList != null && prizeList.size() > 0){
			
			
			for (LotteryConfigEntity lotteryCfg : prizeList) {
				
				cjIdArray.add(StringUtils.getString(lotteryCfg.getPrizeId()));
				
				int prizeNum = lotteryCfg.getPrizeNum(); // 奖品库存数量
				int alreadyLottoNum = lotteryCfg.getAlreadyLottoNumber(); // 已经送出奖品数量
				int lottoNum = lotteryCfg.getLottoNum(); // 用户抽奖次数
				BigDecimal prizeChance = lotteryCfg.getPrizeChance();
				
				/* 更新奖项抽取次数 */
				lotteryCfgEvent.updateLottoNum(lotteryCfg.getPrizeId());
				
				/* 如果 已送出奖品数量 小于 奖品库存数量 （即库存数量还未发放完）*/
				if(alreadyLottoNum < prizeNum){

					/*
					 * 中奖条件：用户当前抽奖次数等于该奖项中奖概率整数倍时中奖
					 */
					if((lottoNum + 1) % prizeChance.intValue() == 0){
						
						isLottery = Consts.FINAL_NUMBER_1; // 中奖标志1
						lotteryName = lotteryCfg.getPrizeName(); // 奖品名称
						
						zjPrizeId = lotteryCfg.getPrizeId() + "";
						zjPrizeName = lotteryCfg.getPrizeName();
						/* 更新奖项中奖次数 */
						lotteryCfgEvent.updateAreadyLottoNum(lotteryCfg.getPrizeId());
						
						break; // 中奖后跳出循环
					}
					
				}
				
			}
			
		}
		
		/* 用户抽奖记录 */
		NewYearLotteryEntity lotteryEntity = new NewYearLotteryEntity();
		lotteryEntity.setUserName(req.getUserName());
		lotteryEntity.setRealName(customer.getRealName());
		lotteryEntity.setMobile(customer.getMobile());
		lotteryEntity.setZjPrizeNum(zjPrizeId);
		lotteryEntity.setZjPrizeName(zjPrizeName);
		lotteryEntity.setCjPrizeNums(cjIdArray.toString());
		lotteryEntity.setExpressProvince(StringUtils.getString(customer.getExpressProvince()));
		lotteryEntity.setExpressCity(StringUtils.getString(customer.getExpressCity()));
		lotteryEntity.setExpressArea(StringUtils.getString(customer.getExpressArea()));
		lotteryEntity.setExpressDetail(StringUtils.getString(customer.getExpressDetail()));
		lotteryEntity.setLotteryType(Consts.FINAL_NUMBER_2);
		lotterRecordEvent.recordUserLottery(lotteryEntity);
		
		/* 更新用户抽奖次数 */
		custUserEvent.updateUserLotteryInfo(req.getUserName());
		
		/* 如果未中奖，随机提示消息 */
		if(Consts.FINAL_NUMBER_0.equals(isLottery)){
			rtnNoLotteryMsg = ResourceUtils.getString("rtnNoLotteryMsg_" + StringUtils.getRandom(1, 6));
		}
		
		actResp.setIsLottery(isLottery);
		actResp.setLotteryName(lotteryName);
		actResp.setRtnNoLotteryMsg(rtnNoLotteryMsg);
		return actResp;
	}
	
	/**
	 * 验证用户抽奖信息
	 * @param custUser
	 * @param customer
	 * @throws UserNameNoFindException
	 * @throws ActivityIsLotteryException
	 * @throws ActivityLotteryNumbzException
	 */
	private void validate(CustUserEntity custUser, CustomerEntity customer) throws UserNameNoFindException, ActivityIsLotteryException, ActivityLotteryNumbzException{
		
		/* 验证用户 */
		if(custUser == null || customer == null){
			throw new UserNameNoFindException();
		}
		
		/* 是否已经有过抽奖记录 */
		int isLottery = custUser.getIsLottery();
		if(isLottery > 0 || isUserLotteryRecord(custUser.getUsername())){
			throw new ActivityIsLotteryException();
		}
		
		/* 剩余抽奖次数是否大于0 */
		int lotteryNum = custUser.getLotteryNum();
		if(lotteryNum <= 0){
			throw new ActivityLotteryNumbzException();
		}
		
	}

	/**
	 * 获取所有奖项
	 * @return
	 */
	private List<LotteryConfigEntity> queryAllPrize(){
		
		LotteryConfigQuery lotteryCfgQuery = new LotteryConfigQuery();
		
		List<LotteryConfigEntity> list = lotteryCfgQuery.queryLotteryCfgByAll(Consts.FINAL_NUMBER_2);
		
		return list;
	}
	
	/**
	 * 获取随机数字
	 * @param length 数字长度
	 * @return
	 */
//	private static int getRandomNum(int length){
//		
//		return Integer.parseInt(RandomStringUtils.random(5, false, true));
//	}

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
