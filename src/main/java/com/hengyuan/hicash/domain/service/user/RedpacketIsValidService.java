package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.user.LoginQuery;
import com.hengyuan.hicash.domain.query.user.RedpacketIsValidQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.entity.user.LoginEntity;
import com.hengyuan.hicash.entity.user.RedpacketInfoEntity;
import com.hengyuan.hicash.exception.SaveDeviceException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.LoginReq;
import com.hengyuan.hicash.parameters.request.user.RedpacketIsValidReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.RedpacketIsValidResp;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * @author xing.yuan
 * @date 2017年12月21日 下午3:41:47
 * 类说明
 */
public class RedpacketIsValidService implements RespService{
	
	private static Logger logger = Logger.getLogger(RedpacketIsValidService.class);
	
	private LoginQuery loginQuery = new LoginQuery();
	
	private ApplicationQuery applicationQuery = new ApplicationQuery();

	private RedpacketIsValidQuery redpacketIsValidQuery = new RedpacketIsValidQuery(); 
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		RedpacketIsValidReq redpacketIsValidReq = (RedpacketIsValidReq) parmRequest;
		RedpacketIsValidResp redpacketIsValidResp = new RedpacketIsValidResp();
		LoginReq login = new LoginReq();
		if("1".equals(redpacketIsValidReq.getType())){
			login.setUserName(redpacketIsValidReq.getMoblieNo());
			login.setPassWord(redpacketIsValidReq.getPassword());
		
			LoginEntity existFlag = userLogin(login);
			
			if (existFlag != null) {
				login.setUserName(existFlag.getUserName());
				//已登录
				if(StringUtils.isEmpty(redpacketIsValidReq.getPassword())){
					redpacketIsValidResp = validLogin(login,existFlag);
				}else{
					// 未登录,验证密码是否正确
					if (isLoginSuccess(login)) {
						redpacketIsValidResp = validLogin(login,existFlag);
					}else{
						redpacketIsValidResp.setResultCode(ResultCodes.BLACK_CERTNO_UPDATE_FALLUSERPWD);
						logger.info("密码验证错误");
					}
				}
			}
		}else if("2".equals(redpacketIsValidReq.getType())){
			login.setUserName(redpacketIsValidReq.getMoblieNo());//用户名
			try {
				//根据用户名查询申请表
				ApplicationEntity appPay= applicationQuery.queryAppPayByUserNameAndStatus(login.getUserName());
				//修改info信息
				if(appPay!=null){
					redpacketIsValidQuery.updateStatus(appPay.getAppNo());
					logger.info("修改状态为不可用"+appPay.getAppNo());
				}
				redpacketIsValidResp.setResultCode(ResultCodes.NORMAL);
			} catch (SaveDeviceException e) {
				e.printStackTrace();
			}
		}
		return redpacketIsValidResp;
	}
	
	public RedpacketIsValidResp validLogin(LoginReq login,LoginEntity loginEntity){
		RedpacketIsValidResp redpacketIsValidResp = new RedpacketIsValidResp();
		
		logger.info("手机号:"+login.getUserName());
		try{
			//根据用户名查询申请表
			ApplicationEntity appPay= applicationQuery.queryAppPayByUserNameAndStatus(login.getUserName());
			if(appPay!=null){
				//查询info信息
				redpacketIsValidResp = redpacketIsValidQuery.queryRedpacketAmount(appPay.getAppNo(),loginEntity);
				logger.info("查询红包信息"+appPay.getAppNo());
				redpacketIsValidResp.setResultCode(ResultCodes.NORMAL);
			}
		}catch(Exception e){
			redpacketIsValidResp.setResultCode(ResultCodes.NORMAL);
			logger.info("查询结果异常");
		}
		return redpacketIsValidResp;
		
	}

	public LoginEntity userLogin(LoginReq login) {
		LoginEntity entity = loginQuery.userExistQuery(login);

		if (entity != null && entity.isExistFlag()) {
			return entity;
		}
		return null;
	}
	
	/**
	 * 验证密码是否正确
	 * 
	 * @return
	 */
	public boolean isLoginSuccess(LoginReq login) {
		LoginEntity entity = loginQuery.userExistQuery(login);
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		boolean isSucc = md5PasswordEncoder.isPasswordValid(
				entity.getOldPassword(), login.getPassWord(),
				entity.getOldSalt());
		return isSucc;
	}
}
