package com.hengyuan.hicash.domain.service.user;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.dao.dict.CertificationType;
import com.hengyuan.hicash.domain.event.user.MobileUpdateEvent;
import com.hengyuan.hicash.domain.query.user.MobileUpdateQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.MobileUpdateEntity;
import com.hengyuan.hicash.exception.DeleteCertificationException;
import com.hengyuan.hicash.exception.SaveCertificationException;
import com.hengyuan.hicash.exception.UpdateCustUserException;
import com.hengyuan.hicash.exception.UpdateMobileNoException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.MobileUpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.MobileUpdateResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 修改注册手机
 * 
 * @author Eric.shi
 * @create date 2014-07-23
 * 
 */
public class MobileUpdateService implements RespService {
	
	private static Logger logger = Logger.getLogger(MobileUpdateService.class);

	private String resultCode = ""; // 返回码

	MobileUpdateQuery mobileUpdateQuery = new MobileUpdateQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		MobileUpdateReq mobileUpdateReq = (MobileUpdateReq) parmRequest;
		MobileUpdateResp resp = new MobileUpdateResp();

		try{
			
			/* 取得用户对象 */
			MobileUpdateEntity custUser = queryCustUser(mobileUpdateReq.getUserName());
			
			if (custUser != null) {
				
				if(custUser.getMobile() != null&&mobileUpdateReq.getOldMobile().equals(custUser.getMobile())){
					
					if (custUser.getAmountValidateTempMobile().equals(mobileUpdateReq.getNewMobile())) {
						if (mobileUpdateReq.getCertificationCode().equals(
								custUser.getMobileValidateCode())) {
							
							Calendar nowTime = Calendar.getInstance();
							Calendar validateTime = Calendar.getInstance();
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							validateTime.setTime(sdf.parse(custUser.getMobileValidateCodeValidTime()));
							
							// 判断验证时间是否已经过期
							if (nowTime.before(validateTime)) {
								resultCode = updateNewMobile(mobileUpdateReq);
							}else {
								resultCode = ResultCodes.CHANGE_MOBILE_CODE_TIMEOUT;
							}
							
						}else{
							resultCode = ResultCodes.CHANGE_MOBILE_CODE_ERROR;
						}
					}else {
						resultCode = ResultCodes.MOBILE_NEWMOBILE_ERROR;
					}
					
				}else{
					resultCode = ResultCodes.UPDATE_MOBILENO_OLD_FAIL;
				}
				
			}else{
				resultCode = ResultCodes.CHANGE_MOBILE_USER_NOT_FOUND;
			}
			
		}catch (Exception e){
			resultCode = ResultCodes.CHANGE_MOBILE_EXCEPTION;
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CHANGE_MOBILE_EXCEPTION, e);
		}finally {
			ConnManager.closeConn();
		}

		resp.setResultCode(resultCode);
		return resp;
	}

	public String updateNewMobile(MobileUpdateReq mobileUpdateReq) throws Exception {

		try {
			
			ConnManager.beginTransaction();
			
			/* 删除用户认证项 */
			deleteCertification(CertificationType.MOBD.toString(),mobileUpdateReq.getUserName());

			/* 更新用户手机 */
			updateCustCustomer(mobileUpdateReq);

			/* 添加认证对象 基本认证 */
			addCertification(mobileUpdateReq);

			/* 更新user表中手机号码，用于手机登陆 */
			updateCustUser(mobileUpdateReq);

			ConnManager.commit();
			return ResultCodes.NORMAL;
		} catch (DeleteCertificationException e){
			
			ConnManager.rollback();
			return ResultCodes.DELETE_MOBILE_CER_ERROR;
			
		} catch (UpdateMobileNoException e) {
			ConnManager.rollback();
			return ResultCodes.UPDATE_USER_MOBILE_ERROR;
			
		} catch (SaveCertificationException e) {
			ConnManager.rollback();
			return ResultCodes.SAVE_MOBILE_CER_ERROR;
			
		} catch (UpdateCustUserException e) {
			ConnManager.rollback();
			return ResultCodes.UPDATE_USER_LOGIN_ERROR;
			
		} catch (Exception e) {
			ConnManager.rollback();
			throw new Exception();
		} finally {
			ConnManager.closeConn();
		}
	}

	/**
	 * 删除用户认证项
	 * 
	 * @param 认证类型
	 * @param username
	 *            用户名
	 * @throws DeleteCertificationException 
	 * */
	public void deleteCertification(String certificationType, String username) 
			throws DeleteCertificationException {
		MobileUpdateEvent mobileUpdateEvent = new MobileUpdateEvent();
		mobileUpdateEvent.deleteCertification(certificationType, username);
	}

	/**
	 * 查询用户数据
	 * 
	 * @param username
	 *            用户名
	 * @return custuserEntity
	 * */
	public MobileUpdateEntity queryCustUser(String username) {
		MobileUpdateEntity entity = mobileUpdateQuery.queryCustUser(username);
		return entity != null ? entity : new MobileUpdateEntity();
	}

	/**
	 * 修改用户手机号
	 * 
	 * @param MobileUpdateReq
	 * @return
	 * @throws UpdateMobileNoException 
	 * */
	public void updateCustCustomer(MobileUpdateReq mobileUpdateReq) throws UpdateMobileNoException {
		MobileUpdateEvent mobileUpdateEvent = new MobileUpdateEvent();
		mobileUpdateEvent.updateCustCustomer(mobileUpdateReq);
	}

	/**
	 * 添加基本认证对象
	 * 
	 * @param MobileUpdateReq
	 * @return
	 * @throws SaveCertificationException 
	 * */
	public void addCertification(MobileUpdateReq mobileUpdateReq) 
			throws SaveCertificationException {
		MobileUpdateEvent mobileUpdateEvent = new MobileUpdateEvent();
		mobileUpdateEvent.addCertification(mobileUpdateReq);
	}

	/**
	 * 更新user表中手机号码，用于手机登陆
	 * 
	 * @param MobileUpdateReq
	 * @return
	 * @throws UpdateCustUserException 
	 * */
	public void updateCustUser(MobileUpdateReq mobileUpdateReq) 
			throws UpdateCustUserException {
		MobileUpdateEvent mobileUpdateEvent = new MobileUpdateEvent();
		mobileUpdateEvent.updateCustUser(mobileUpdateReq);
	}



}
