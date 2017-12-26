package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.InputAppEvent;
import com.hengyuan.hicash.domain.event.user.NewStuApp1UpdateEvent;
import com.hengyuan.hicash.domain.event.user.NewStuApp3UpdateEvent;
import com.hengyuan.hicash.domain.event.user.StuApp1UpdateEvent;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.notic.NoticeToApproveService;
import com.hengyuan.hicash.domain.query.user.SchoolQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.SchoolEntity;
import com.hengyuan.hicash.exception.SaveInfoException;
import com.hengyuan.hicash.exception.UpdateInputAppException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.NewStuApp1UpdateReq;
import com.hengyuan.hicash.parameters.request.user.NewStuApp3UpdateReq;
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
public class NewStuApp3UpdateService  implements RespService{

	private static Logger logger = Logger.getLogger(NewStuApp3UpdateService.class);

	private NewStuApp3UpdateEvent schoolMsgEvent = new NewStuApp3UpdateEvent();
	
	private SchoolQuery schoolQuery = new SchoolQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		NewStuApp3UpdateReq updateMsgReq = (NewStuApp3UpdateReq) parmRequest;
		StuApp1UpdateResp updateMsgResp = new StuApp1UpdateResp();
		
		String resultCode = "";
		
		try{
			
			
			boolean commitFlag = false;
			//查询学校信息
			SchoolEntity schoolEntity = schoolQuery.querySchoolInfo(Integer.parseInt(updateMsgReq.getSchoolId()));
			if (schoolEntity != null) {
				updateMsgReq.setIptSchoolName(schoolEntity.getShcoolName());
			}
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
				
			}else{
				schoolMsgEvent.updateStuApp1(updateMsgReq);
				resultCode = ResultCodes.NORMAL;
			}
			
			
			
		} catch (SaveInfoException e) {
		
			resultCode = ResultCodes.STU_APP1_INFO_EXCEPTION;
			ConnManager.rollback();
			 
		} catch (Exception e) {
			ConnManager.rollback();
			resultCode = ResultCodes.STU_APP1_INFO_EXCEPTION;
			
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
	
	
	/**
	 * 短信通知业务员
	 * @param userName
	 * @param appNo
	 */
	public void sendToApprove(String userName,String appNo){
		
		NoticeToApproveService noticeToApprove = new NoticeToApproveService();
		
		noticeToApprove.sendToApprove(userName, appNo);
	}



}
