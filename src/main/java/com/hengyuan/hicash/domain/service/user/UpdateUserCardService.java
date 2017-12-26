package com.hengyuan.hicash.domain.service.user;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.user.NoahValidIdentityEvent;
import com.hengyuan.hicash.domain.query.param.WithholdPartQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.WhichPartRecord;
import com.hengyuan.hicash.exception.BankCardNotVerifiedException;
import com.hengyuan.hicash.exception.DInputProxyBankException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CollectCardReq;
import com.hengyuan.hicash.parameters.request.user.UpdateUserCardReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.DoubleIdentityValResp;
import com.hengyuan.hicash.parameters.response.user.UpdateUserCardResp;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 修改银行卡 处理逻辑
 * 
 * @author Cary.Liu
 * @create 2014-09-29
 *
 */
public class UpdateUserCardService implements RespService {
	
	private static Logger logger = Logger
			.getLogger(UpdateUserCardService.class);

	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		UpdateUserCardReq updateUserCardReq = (UpdateUserCardReq)parmRequest;
		UpdateUserCardResp updateUserCardResp = new UpdateUserCardResp();
		String resultCode = "";
		try{
			//根据用户名以及银行卡号查询withhold_part_record 表中记录
			String whichPart = null;
			WithholdPartQuery whichQuery = new WithholdPartQuery();
			List<WhichPartRecord> listPart = whichQuery.queryWhichPartByUserBank(updateUserCardReq.getUserName(),updateUserCardReq.getAccountNo());
			if(listPart != null && listPart.size() == 0){
				throw new BankCardNotVerifiedException();//未查询到该银行卡的验证记录
			}else if(listPart.size() == 1){
				whichPart = listPart.get(0).getWhichPart();
			}else if(listPart.size() == 2){
				whichPart = Consts.ALLDK;
			}
			
			CollectCardReq collectCardReq=new CollectCardReq();
			collectCardReq.setUserName(updateUserCardReq.getUserName());//用户名
			collectCardReq.setRealName(updateUserCardReq.getAccountName());//持卡人姓名
			collectCardReq.setCardType("JJKT");//卡类型
			collectCardReq.setBank(updateUserCardReq.getBankCode());//开户行
			collectCardReq.setProvince(updateUserCardReq.getDkProvince());//开户城市-省
			collectCardReq.setCity(updateUserCardReq.getDkCity());//开户城市-市
			collectCardReq.setAreaCode(updateUserCardReq.getDkAreaCode());//开户城市-区
			collectCardReq.setOpenBank(updateUserCardReq.getDkBankBranch());//开户支行
			collectCardReq.setCardNum(updateUserCardReq.getAccountNo());//银行卡号
			collectCardReq.setDefaultCard("Y");//是否设置为默认卡
			collectCardReq.setMobile(updateUserCardReq.getMobile());//预留手机号
			collectCardReq.setUuid(UUID.randomUUID().toString());
			DoubleIdentityValResp bankCardInputAppResp = new DoubleIdentityValService().updateUserColleCard(collectCardReq);
			
			//如果更改收款银行成功
			if (bankCardInputAppResp.getResultCode().equals("1")) {
				// 更新代扣银行卡至d_input_app表
				NoahValidIdentityEvent noahValidEvent = new NoahValidIdentityEvent();
				noahValidEvent.updateInputByAppNo(updateUserCardReq.getAccountNo(),updateUserCardReq.getBankCode(),updateUserCardReq.getAppNo(),whichPart);
				resultCode = ResultCodes.NORMAL;
			}else{
				updateUserCardResp.setResultCode(ResultCodes.CARD_UPDATE_EXCEPTION);
				updateUserCardResp.setResultMsg(bankCardInputAppResp.getResultMsg());
				logger.info("更改用户收款卡号失败");
				return updateUserCardResp;
			}
		
		} catch (BankCardNotVerifiedException e) {
			logger.error("未查询到该银行卡的验证记录", e);
			resultCode = ResultCodes.BANK_CARD_NOT_VERIFIED;//未查询到该银行卡的验证记录
		} catch (DInputProxyBankException e) {
			logger.error("更新代扣银行卡至d_input_app表失败", e);
			resultCode = ResultCodes.CARD_UPDATE_EXCEPTION;
		} catch(Exception e){
			logger.error("更改验证,收款银行卡失败", e);
			resultCode = ResultCodes.CARD_UPDATE_EXCEPTION;
		}
		updateUserCardResp.setResultCode(resultCode);
		updateUserCardResp.setResultMsg(ResourceUtils.getString(updateUserCardResp
				.getResultCode()));
		return updateUserCardResp;
	}

	
}
