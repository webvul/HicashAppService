package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.InputAppEvent;
import com.hengyuan.hicash.domain.event.user.NewStuApp1UpdateEvent;
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
public class NewStuApp1UpdateService  implements RespService{

	private static Logger logger = Logger.getLogger(NewStuApp1UpdateService.class);

	private NewStuApp1UpdateEvent schoolMsgEvent = new NewStuApp1UpdateEvent();
	
	private SchoolQuery schoolQuery = new SchoolQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		NewStuApp1UpdateReq updateMsgReq = (NewStuApp1UpdateReq) parmRequest;
		StuApp1UpdateResp updateMsgResp = new StuApp1UpdateResp();
		
		String resultCode = "";
		
		try{
			
			
			
            if(Consts.UPDATE_UNIVERSITY_TYPE_P.equals(updateMsgReq.getUpdateType())){
				
				if(queryAppNoExist(updateMsgReq.getAppNo())){
					schoolMsgEvent.updateStuApp1(updateMsgReq);
					
					/* 远程调用提交申请接口 */
//					String resultStr = remoteHttp(updateMsgReq);
					
					/* 短信通知业务员 */
					sendToApprove(updateMsgReq.getUserName(), updateMsgReq.getAppNo());
					
					resultCode = ResultCodes.NORMAL;
				}else{
					resultCode = ResultCodes.STU_UPDATE_APPNO_ISNOTEXIST;
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
