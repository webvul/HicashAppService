package com.hengyuan.hicash.domain.service.activity;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
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
import com.hengyuan.hicash.exception.ActivityEndedException;
import com.hengyuan.hicash.exception.ActivityIsLotteryException;
import com.hengyuan.hicash.exception.ActivityLotteryNumbzException;
import com.hengyuan.hicash.exception.ActivityNoStartException;
import com.hengyuan.hicash.exception.NotInActivityTimeException;
import com.hengyuan.hicash.exception.UpdateCustUserException;
import com.hengyuan.hicash.exception.UpdateLotteryException;
import com.hengyuan.hicash.exception.UpdateLotterycfgException;
import com.hengyuan.hicash.exception.UserNameNoFindException;
import com.hengyuan.hicash.parameters.request.activity.NewYearActivityReq;
import com.hengyuan.hicash.parameters.response.activity.NewYearActivityResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.RandomEncryptUtils;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 新年大转盘抽奖活动
 * 
 * @author Cary.Liu
 * @createDate 2015-12-29
 *
 */
public class LotteryActiveImpl implements Activity {

	/** 没有奖品的ID数组 */
	private static final int[] noPrizeArr = { 7, 8,9 };

	private static final int index = 6;

	private LotteryConfigEvent lotteryCfgEvent = new LotteryConfigEvent();

	private NewYearLotteryEvent lotterRecordEvent = new NewYearLotteryEvent();

	private CustUserEvent custUserEvent = new CustUserEvent();

	@Override
	public synchronized NewYearActivityResp newYearLotteryService(
			NewYearActivityReq req) throws UpdateLotterycfgException,
			UpdateLotteryException, UpdateCustUserException,
			UserNameNoFindException, ActivityIsLotteryException,
			ActivityLotteryNumbzException, ActivityNoStartException,
			ActivityEndedException, ParseException, NotInActivityTimeException {

		CustUserEntity custUser = queryCustUser(req.getUserName());
		CustomerEntity customer = queryCustomer(req.getUserName());

		validate(custUser, customer);

		NewYearActivityResp actResp = new NewYearActivityResp();

		String encryptStr = "";
		int randomNum = getRandomNum(5); // 随机数
		int prizeId = getRandomNoPrizeNumber(); // 奖项ID，默认为不中奖

		/* 获取数据库配置的所有奖项 */
		List<LotteryConfigEntity> prizeList = queryAllPrize();

		String zjPrizeId = Consts.FINAL_NUMBER_0; // 中奖Id
		String zjPrizeName = ""; // 中奖奖品名称
		List<String> cjIdArray = new ArrayList<String>();

		if (prizeList != null && prizeList.size() > 0) {

			for (LotteryConfigEntity lotteryCfg : prizeList) {

				cjIdArray.add(StringUtils.getString(lotteryCfg.getPrizeId()));

				int prizeNum = lotteryCfg.getPrizeNum(); // 奖品库存数量
				int alreadyLottoNum = lotteryCfg.getAlreadyLottoNumber(); // 已经送出奖品数量
				int lottoNum = lotteryCfg.getLottoNum(); // 用户抽奖次数
				BigDecimal prizeChance = lotteryCfg.getPrizeChance();

				/* 更新奖项抽取次数 */
				lotteryCfgEvent.updateLottoNum(lotteryCfg.getPrizeId());

				/* 如果 已送出奖品数量 小于 奖品库存数量 （即库存数量还未发放完） */
				if (alreadyLottoNum < prizeNum) {

					/*
					 * 中奖条件：用户当前抽奖次数等于该奖项中奖概率整数倍时中奖
					 */
					if ((lottoNum + 1) % prizeChance.intValue() == 0) {

						prizeId = lotteryCfg.getPrizeId();
						zjPrizeId = lotteryCfg.getPrizeId() + "";
						zjPrizeName = lotteryCfg.getPrizeName();
						/* 更新奖项中奖次数 */
						lotteryCfgEvent.updateAreadyLottoNum(lotteryCfg
								.getPrizeId());

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
		lotteryEntity.setExpressProvince(StringUtils.getString(customer
				.getExpressProvince()));
		lotteryEntity.setExpressCity(StringUtils.getString(customer
				.getExpressCity()));
		lotteryEntity.setExpressArea(StringUtils.getString(customer
				.getExpressArea()));
		lotteryEntity.setExpressDetail(StringUtils.getString(customer
				.getExpressDetail()));
		lotteryEntity.setLotteryType(Consts.FINAL_NUMBER_1);
		lotterRecordEvent.recordUserLottery(lotteryEntity);

		/* 更新用户抽奖次数 */
		custUserEvent.updateUserLotteryInfo(req.getUserName());

		/* 加密字符 */
		encryptStr = RandomEncryptUtils.encrypt(prizeId, randomNum, index);

		actResp.setActCode(encryptStr);
		actResp.setRandomNum(randomNum + "");
		return actResp;
	}

	/**
	 * 验证用户抽奖信息
	 * 
	 * @param custUser
	 * @param customer
	 * @throws UserNameNoFindException
	 * @throws ActivityIsLotteryException
	 * @throws ActivityLotteryNumbzException
	 * @throws ActivityNoStartException
	 * @throws ActivityEndedException
	 * @throws ParseException
	 * @throws NotInActivityTimeException
	 */
	private void validate(CustUserEntity custUser, CustomerEntity customer)
			throws UserNameNoFindException, ActivityIsLotteryException,
			ActivityLotteryNumbzException, ActivityNoStartException,
			ActivityEndedException, ParseException, NotInActivityTimeException {

		/* 验证用户 */
		if (custUser == null || customer == null) {
			throw new UserNameNoFindException();
		}

		/* 验证时间为7-17号 */
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (null == custUser.getCreateTime()
				|| "".equals(custUser.getCreateTime())) {
			throw new NotInActivityTimeException();
		} else {
			Long createTime = format.parse(custUser.getCreateTime()).getTime(); // 注册时间
			Long startTime = format.parse("2016-07-07").getTime();
			Long endTime = format.parse("2016-07-17").getTime();

			if (createTime < startTime || createTime > endTime) {
				throw new NotInActivityTimeException();
			}
		}

		String actStartDate = ResourceUtils.getValue("newyear_act_startdate");
		String actEndDate = ResourceUtils.getValue("newyear_act_enddate");
		// 是否到了活动开始时间
		if (DateUtils.outCurrentTimeFlag(actStartDate)) {
			throw new ActivityNoStartException();
		}
		// 活动时间是否结束
		if (!DateUtils.outCurrentTimeFlag(actEndDate)) {
			throw new ActivityEndedException();
		}

		/* 是否已经有过抽奖记录 */
		int isLottery = custUser.getIsLottery();
		if (isLottery > 0 || isUserLotteryRecord(custUser.getUsername())) {
			throw new ActivityIsLotteryException();
		}

		/* 剩余抽奖次数是否大于0  2016新年抽奖活动   每一个用户第一次成功申请嗨秒贷获取一次抽奖机会*/
//		int lotteryNum = custUser.getLotteryNum();
//		if (lotteryNum <= 0) {
//			throw new ActivityLotteryNumbzException();
//		}

	}

	/**
	 * 获取所有奖项
	 * 
	 * @return
	 */
	private List<LotteryConfigEntity> queryAllPrize() {

		LotteryConfigQuery lotteryCfgQuery = new LotteryConfigQuery();

		List<LotteryConfigEntity> list = lotteryCfgQuery
				.queryLotteryCfgByAll(Consts.FINAL_NUMBER_1);

		return list;
	}

	/**
	 * 随机获取没有奖品的ID
	 * 
	 * @return
	 */
	private static int getRandomNoPrizeNumber() {

		return noPrizeArr[RandomUtils.nextInt(noPrizeArr.length)];
	}

	/**
	 * 获取随机数字
	 * 
	 * @param length
	 *            数字长度
	 * @return
	 */
	private int getRandomNum(int length) {

		return Integer.parseInt(RandomStringUtils.random(5, false, true));
	}

	private CustUserEntity queryCustUser(String userName) {

		CustUserQuery custUserQuery = new CustUserQuery();

		return custUserQuery.queryByUserName(userName);
	}

	private CustomerEntity queryCustomer(String userName) {

		CustomerQuery customerQuery = new CustomerQuery();

		return customerQuery.queryCustomerByUserName(userName);
	}

	/**
	 * 用户是否有过抽奖记录
	 * 
	 * @param userName
	 * @return
	 */
	private boolean isUserLotteryRecord(String userName) {

		NewYearLotterQuery lotQuery = new NewYearLotterQuery();

		List<NewYearLotteryEntity> list = lotQuery.queryUserlotteryRecord(
				userName, Consts.FINAL_NUMBER_1);

		if (list != null && list.size() > 0) {
			return true;
		}

		return false;
	}

	@Override
	public NewYearActivityResp newYearRedPackService(NewYearActivityReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) throws ParseException {
		/* 验证时间为8-15号 */
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Long current = format.parse("2016-06-07").getTime();
		Long begin = format.parse("2016-06-08").getTime();
		Long end = format.parse("2016-06-15").getTime();

		if (current < begin || current > end) {
			try {
				throw new NotInActivityTimeException();
			} catch (NotInActivityTimeException e) {
				e.printStackTrace();
			}
		}

		System.out.println(current + "\tcurrent");
		System.out.println(begin + "\tbegin");
		System.out.println(end + "\tend");
	}

}
