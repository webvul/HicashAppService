package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.user.WithHoldingBankCardQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.mktApp.NoahValidIdentityService;
import com.hengyuan.hicash.entity.app.NoahValidIdentityEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.WithHoldingBankCardReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.WithHoldingBankCardResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 查询申请件的代扣银行卡
 * 
 * @author lihua.Ren
 * @create date 2015-12-03
 *
 */
public class WithHoldingBankCardMsgService implements RespService {
	private static Logger logger = Logger
			.getLogger(WithHoldingBankCardMsgService.class);

	private WithHoldingBankCardQuery bankQuery = new WithHoldingBankCardQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		WithHoldingBankCardReq req = (WithHoldingBankCardReq) parmRequest;
		WithHoldingBankCardResp resp = null;
		try {

			resp = bankQuery.queryBankMobile(req.getAppNo());

		
			if (resp != null) {
				//根据申请件的银行卡信息，查询认证表中是否已经认证成功
//				CmbcIdentifySendCodeEntity entity = confirmQuery
//						.queryConfirmSuccVal(resp.getRealName(),
//								resp.getBankCard(), resp.getIdentifyNo(),
//								resp.getMobileNo());
				// 诺亚金通实名认证结果
				NoahValidIdentityService ns = new NoahValidIdentityService();
				NoahValidIdentityEntity entity = ns.getNoahValidIdentity(resp.getBankCard(), resp.getRealName(),
						resp.getIdentifyNo());
				
				{
					//如果认证表中存在
					if (entity != null) {
						String status = entity.getRespCode();
						//如果认证状态为已通过
						if ("01".equals(status)) {
							resp.setValStatus("1");
						}
					}
				}
			}
			resp.setResultCode(ResultCodes.NORMAL);
		} catch (Exception e) {
			resp = new WithHoldingBankCardResp();
			resp.setResultCode(ResultCodes.WITH_HOLDINGBANK_CARDQUERY_EXCEPTION);

			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(),
					ResultCodes.WITH_HOLDINGBANK_CARDQUERY_EXCEPTION, e);
		} finally {
			ConnManager.closeConn();
		}
		return resp;

	}
}
