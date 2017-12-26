package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.activity.NewYearLotteryReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 新年抽奖活动 val
 * 
 * @author Cary.Liu
 * @createDate 2015-12-29
 *
 */
public class NewYearLotteryVal {

	private NewYearLotteryReq lotReq;

	public NewYearLotteryVal(NewYearLotteryReq lotReq) {
		this.lotReq = lotReq;
	}
	
	public String validate(){
		
		//用户名
		if (RegexValidate.isNull(lotReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}

		if (!RegexValidate.isUsername(lotReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		//活动类型
		if (RegexValidate.isNull(lotReq.getActivityType())) {
			return ResultCodes.NEWYEARLOT_ACTTYPE_ISNULL;
		}

		if (!"MDCJ".equals(lotReq.getActivityType()) && !"FXCJ".equals(lotReq.getActivityType()) && !"NFXC".equals(lotReq.getActivityType())) {
			return ResultCodes.NEWYEARLOT_ACTTYPE_ILLEGAL;
		}
		
		return ResultCodes.NORMAL;
	}

	public NewYearLotteryReq getLotReq() {
		return lotReq;
	}

	public void setLotReq(NewYearLotteryReq lotReq) {
		this.lotReq = lotReq;
	}

}
