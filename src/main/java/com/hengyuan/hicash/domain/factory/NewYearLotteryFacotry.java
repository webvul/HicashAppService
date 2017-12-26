package com.hengyuan.hicash.domain.factory;

import com.hengyuan.hicash.domain.Activity;
import com.hengyuan.hicash.domain.Provider;
import com.hengyuan.hicash.domain.service.activity.LotteryActiveImpl;
import com.hengyuan.hicash.domain.service.activity.LotteryByShareActiveImpl;
import com.hengyuan.hicash.domain.service.activity.RedPackActiveImpl;

public class NewYearLotteryFacotry implements Provider{

	@Override
	public Activity createActivity(int lotteryType) {
		
		if(lotteryType == 1){
			
			return new LotteryActiveImpl();
			
		}else if (lotteryType == 2){
			
			return new RedPackActiveImpl();
			
		}else if (lotteryType == 3){
			
			return new LotteryByShareActiveImpl();
			
		} else{
			
			return null;
			
		}
		
		
	}

	
}
