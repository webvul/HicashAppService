package com.hengyuan.hicash.domain.service.param;

import java.util.List;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.BankBinEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.param.BankBinReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.param.BankBinResp;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 
 * @author fish
 *
 * @date 2017年2月14日 上午10:26:48
 */

public class BankBinService implements RespService {

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		BankBinReq req = (BankBinReq) parmRequest;
		BankBinResp resp = new BankBinResp();
		String result = validate(req);
		if (result.equals(ResultCodes.NORMAL)) {
			String bankCard = req.getBankCard();
			BankBinEntity bean = getBankBinEntity(bankCard);
			if (bean.getBankCode() == null) {
				result = ResultCodes.BANK_NOT_FOUND;
			} else {
				resp.setBankCode(bean.getBankCode());
				resp.setBankName(bean.getBankName());
			}
		}
		resp.setResultCode(result);
		return resp;

	}

	public BankBinEntity getBankBinEntity(String bankCard) {
		BankBinQuery bankBinQuery = new BankBinQuery();
		List<BankBinEntity> bins = bankBinQuery.queryBankBins(bankCard);
		BankBinEntity bean = new BankBinEntity();
		if (bins.size() > 0) {
			bean = bins.get(0);
		}
		return bean;
	}

	public String validate(BankBinReq req) {

		// 验证银行卡号
		if (RegexValidate.isNull(req.getBankCard())) {
			return ResultCodes.CREATEAPPPAY_BANKCARD_ISNULL;
		}
		return ResultCodes.NORMAL;
	}
}
