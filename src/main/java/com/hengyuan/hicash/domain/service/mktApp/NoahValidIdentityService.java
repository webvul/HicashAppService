package com.hengyuan.hicash.domain.service.mktApp;

import com.hengyuan.hicash.domain.event.user.NoahValidIdentityEvent;
import com.hengyuan.hicash.domain.query.app.NoahValidIdentityQuery;
import com.hengyuan.hicash.entity.app.NoahValidIdentityEntity;

public class NoahValidIdentityService {

	public NoahValidIdentityEntity getNoahValidIdentity(String bankCardNum,String realName, String identityNo) {
		NoahValidIdentityQuery nq = new NoahValidIdentityQuery();
		return nq.getNoahValidIdentity(bankCardNum, realName, identityNo);
	}

	/**
	 * 银行卡验证成功之后修改input_app表信息
	 * 
	 * @param bankCardNum
	 * @param accName
	 * @param mobleNo
	 * @param serialNo
	 */
	public void updateProxyFromByApplicationNo(String bankCardNum,
			String accName, String serialNo) {
		NoahValidIdentityEvent ne = new NoahValidIdentityEvent();
		ne.updateProxyFromByApplicationNo(bankCardNum, accName, 
				serialNo);

	}

}
