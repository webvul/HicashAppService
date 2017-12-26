package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.InputAppEvent;
import com.hengyuan.hicash.domain.event.user.StuApp1UpdateEvent;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.user.SchoolQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.SchoolEntity;
import com.hengyuan.hicash.exception.SaveInfoException;
import com.hengyuan.hicash.exception.UpdateInputAppException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.StuApp1UpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuApp1UpdateResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * hicash手机端学生提现申请完善1逻辑处理类
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuApp1UpdateService  implements RespService{

	private static Logger logger = Logger.getLogger(StuApp1UpdateService.class);

	private StuApp1UpdateEvent schoolMsgEvent = new StuApp1UpdateEvent();
	
	private SchoolQuery schoolQuery = new SchoolQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		StuApp1UpdateReq updateMsgReq = (StuApp1UpdateReq) parmRequest;
		StuApp1UpdateResp updateMsgResp = new StuApp1UpdateResp();
		
		String resultCode = "";
		
		try{
			boolean commitFlag = false;
			//查询学校信息
			SchoolEntity schoolEntity = schoolQuery.querySchoolInfo(Integer.parseInt(updateMsgReq.getSchoolId()));
			if (schoolEntity != null) {
				updateMsgReq.setIptSchoolName(schoolEntity.getShcoolName());
			}
			
			ConnManager.beginTransaction();
			schoolMsgEvent.updateStuApp1(updateMsgReq);
			resultCode = ResultCodes.NORMAL;
			commitFlag = true;
			/* 完善资料时修改学生类型 */
			if(Consts.UPDATE_UNIVERSITY_TYPE_P.equals(updateMsgReq.getUpdateType())){
				
				if(queryAppNoExist(updateMsgReq.getAppNo())){
					
					updateStuType(updateMsgReq.getAppNo(), updateMsgReq.getStuType());
					resultCode = ResultCodes.NORMAL;
					commitFlag = true;
				}else{
					resultCode = ResultCodes.STU_UPDATE_APPNO_ISNOTEXIST;
					commitFlag = false;
				}
				
			}
			
			/* 
			 * 一期客户信息单独保存暂不开放
			 * add by Andy.Niu 2014-08-09
			 *  */
			
			/*boolean isExist = existQuery.isExistToSchoolInfo(updateMsgReq.getUserName());

			保存用户学校信息,isExist=true执行更新操作,isExist=false执行插入操作
			if (isExist) {
				schoolMsgEvent.updateCustSchoolInfo(updateMsgReq);
			} else {
				schoolMsgEvent.saveSchoolInfo(updateMsgReq);
			}*/
			if(commitFlag){
				ConnManager.commit();
			}
		} catch (SaveInfoException e) {
		
			resultCode = ResultCodes.STU_UNIVERSITY_EXCEPTION;
			ConnManager.rollback();
			 
		} catch(UpdateInputAppException e){
			ConnManager.rollback();
			resultCode = ResultCodes.STU_UPDATE_STUTYPE_EXCEPTION;
			
		} catch (Exception e) {
			ConnManager.rollback();
			resultCode = ResultCodes.STU_SHCOOL_INFO_EXCEPTION;
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, updateMsgReq.getUuid(), ResultCodes.STU_APP1_INFO_EXCEPTION, e);
		} finally{
			ConnManager.closeConn();
		}
		
		updateMsgResp.setResultCode(resultCode);
		return updateMsgResp;
	}
	
	/**
	 * 修改申请件学生的学生类型
	 * @param appNo
	 * @throws UpdateInputAppException 
	 */
	public void updateStuType(String appNo, String studentType)
			throws UpdateInputAppException {

		InputAppEvent appEvent = new InputAppEvent();
		appEvent.updateStudentType(appNo, studentType);
	}
	
	/**
	 * 查询流水号是否存在
	 * @param appNo
	 * @return
	 */
	public boolean queryAppNoExist(String appNo){
		
		ApplicationQuery applicationQuery = new ApplicationQuery();
		return applicationQuery.queryAppNoExist(appNo);
	}
	
	
	


}
