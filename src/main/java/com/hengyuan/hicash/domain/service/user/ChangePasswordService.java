package com.hengyuan.hicash.domain.service.user;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.ChangePasswordEvent;
import com.hengyuan.hicash.domain.query.user.ChangePasswordQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.ChangetPasswordEntity;
import com.hengyuan.hicash.exception.ChangePasswordException;
import com.hengyuan.hicash.exception.PassWordEmptyException;
import com.hengyuan.hicash.exception.PassWordEqualException;
import com.hengyuan.hicash.exception.PassWordErrorException;
import com.hengyuan.hicash.exception.PassWordFormatException;
import com.hengyuan.hicash.exception.PassWordNotEqualException;
import com.hengyuan.hicash.exception.UserNameEmptyException;
import com.hengyuan.hicash.exception.UserNameNoFindException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.ChangePasswordReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.ChangePasswordResp;

/**
 * 修改hicash用户密码的业务逻辑类 service
 * 
 * @author Cary.Liu
 * @createDate 2015-06-02
 * 
 */
public class ChangePasswordService implements RespService {
	
	private String resultCode = "";

	private ChangePasswordQuery changePasswordQuery = new ChangePasswordQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		/* 实例化修改密码请求参数 */
		ChangePasswordReq changePasswordReq = (ChangePasswordReq) parmRequest;
		/* 实例化修改密码响应参数 */
		ChangePasswordResp changePasswordResp = new ChangePasswordResp();

		try {
			// 密码验证
			checkPassWord(changePasswordReq);
			// 修改密码
			updateUserPassword(changePasswordReq);

			resultCode = ResultCodes.NORMAL;

		} catch (PassWordEmptyException e) {
			resultCode = ResultCodes.CHANGEPSW_OLDPASSWORD_ISNULL;
		}/* catch (PassWordNotEqualException e) {
			resultCode = ResultCodes.USER_PASSWORD_NOT_EQUAL;
			 记录错误信息 
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.USER_PASSWORD_NOT_EQUAL, e);
		} */catch (PassWordFormatException e) {
			resultCode = ResultCodes.CHANGEPSW_NEWPSWFORMAT_FAIL;
		} catch (PassWordEqualException e) {
			resultCode = ResultCodes.CHANGEPSW_OLDPASSWORD_EQUAL;
		} catch (UserNameEmptyException e) {
			resultCode = ResultCodes.CHANGEPSW_USERNAME_ISNULLNULL;
		} catch (UserNameNoFindException e) {
			resultCode = ResultCodes.CHANGEPSW_USERNAME_NOTFOUND;
		} catch (PassWordErrorException e) {
			resultCode = ResultCodes.CHANGEPSW_ORDPSW_FAIL;
		} catch (ChangePasswordException e) {
			resultCode = ResultCodes.CHANGEPSW_PASSWORD_FAIL;
		} catch (Exception e) {
			resultCode = ResultCodes.CHANGEPSW_EXCEPTION;
			e.printStackTrace();
		}finally{
			ConnManager.closeConn();
		}

		changePasswordResp.setResultCode(resultCode);
		return changePasswordResp;
	}

	/**
	 * 根据用户名查询用户是否存在
	 * 
	 * @param userName
	 * @return
	 */
	public boolean queryUserExistByUserName(String userName) {
		ChangetPasswordEntity changetPasswordEntity = changePasswordQuery
				.queryUserExistByUserName(userName);
		if (changetPasswordEntity != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取用户的原始密码 加密字符串等信息
	 * 
	 * @param userName
	 */
	public ChangetPasswordEntity getOldPassword(String userName) {
		ChangetPasswordEntity entity = changePasswordQuery.getUserOldPasswordByUserName(userName);
		return entity != null ? entity : new ChangetPasswordEntity();
	}

	/**
	 * 修改用户登录密码
	 * 
	 * @param changePasswordReq
	 * @return
	 * @throws ChangePasswordException
	 */
	public void updateUserPassword(ChangePasswordReq changePasswordReq)
			throws ChangePasswordException {
		ChangePasswordEvent changePasswordEvent = new ChangePasswordEvent();
		changePasswordEvent.updatePassoword(changePasswordReq);
	}

	/**
	 * 密码验证
	 * 
	 * @throws PassWordEmptyException
	 * @throws PassWordNotEqualException
	 * @throws PassWordFormatException
	 * @throws PassWordEqualException
	 * @throws UserNameEmptyException
	 * @throws UserNameNoFindException
	 * @throws PassWordErrorException
	 */
	public void checkPassWord(ChangePasswordReq changePasswordReq)
			throws PassWordEmptyException,
			PassWordFormatException, PassWordEqualException,
			UserNameEmptyException, UserNameNoFindException,
			PassWordErrorException {
		// 修改密码
		String newPassWord = changePasswordReq.getNewPassword();
		// 原始密码
		String oldPassWord = changePasswordReq.getOldPassword();
		// 确认密码
		//String confirmPassword = changePasswordReq.getConfirmPassword();

		String username = changePasswordReq.getUsername();

		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();

		if (!StringUtils.isEmpty(username)) {
			// 从数据库查询出的password 和 salt
			ChangetPasswordEntity entityByQuery = getOldPassword(changePasswordReq.getUsername());
			// 查询该用户是否存在
			boolean userExistFlag = queryUserExistByUserName(changePasswordReq.getUsername());
			if (userExistFlag) {
				//该用户是存在的
				if (!StringUtils.isEmpty(oldPassWord)) {

					System.out.println(changePasswordReq.getUsername() + "【"
							+ entityByQuery.getOldPassword() + "\t"
							+ entityByQuery.getOldSalt() + "】");
					// 验证原始密码输入是否正确
					boolean isFlag = md5PasswordEncoder.isPasswordValid(entityByQuery.getOldPassword(),changePasswordReq.getOldPassword(),entityByQuery.getOldSalt());

					if (isFlag) {

						if (!StringUtils.isEmpty(newPassWord)) {

							// 验证新密码与原始密码是否相同
							boolean isNewPassowrdFlag = md5PasswordEncoder.isPasswordValid(
											entityByQuery.getOldPassword(),
											changePasswordReq.getNewPassword(),
											entityByQuery.getOldSalt());

							if (!isNewPassowrdFlag) {

//								if (!newPassWord.equals(confirmPassword)) {
//									throw new PassWordNotEqualException();
//								}

							} else {
								throw new PassWordEqualException();
							}

						} else {
							throw new PassWordEmptyException();
						}

					} else {
						throw new PassWordErrorException();
					}

				} else {
					throw new PassWordEmptyException();
				}

			} else {
				//该客户不存在
				throw new UserNameNoFindException();
			}

		} else {
			throw new UserNameEmptyException();
		}

	}
}
