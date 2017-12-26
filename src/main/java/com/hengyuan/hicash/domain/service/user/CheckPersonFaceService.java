package com.hengyuan.hicash.domain.service.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.app.ApplicationPayQuery;
import com.hengyuan.hicash.domain.query.mktApp.TempCreditQuery;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.query.user.FaceValConfigQuery;
import com.hengyuan.hicash.domain.query.user.HyScoreRecordQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.mktApp.TempCredit;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.FaceValConfigEntity;
import com.hengyuan.hicash.entity.user.HyScoreRecord;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CheckPersonFaceReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CheckPersonFaceResp;
import com.hengyuan.hicash.utils.StringUtils;

public class CheckPersonFaceService implements RespService {
	//2017-06-06 colin总
//司机贷：只face++，2次，公安
//秒贷：只face++ 2次，非公安
//其他：易道2次，face++2次，非公安
	private static Logger logger = Logger.getLogger(CheckSupportAppService.class);

//输出为face++，都调用公安接口
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CheckPersonFaceReq personFaceReq = (CheckPersonFaceReq) parmRequest;
		CheckPersonFaceResp personFaceResp = new CheckPersonFaceResp();

		HyScoreRecordQuery hyScoreRecordQuery = new HyScoreRecordQuery();
		FaceValConfigQuery faceConfigQuery = new FaceValConfigQuery();
		personFaceResp.setDoGongAn("N");//默认不调用公安
		String resultCode = ResultCodes.NORMAL;
		String whichPart="";
		
		try {

			
			// 查询配置
			FaceValConfigEntity faceValConfig = faceConfigQuery.queryFaceConfig(personFaceReq.getUuid());
			// 一道验证失败次数，默认2
			int fstCount = 2;
			// face++验证失败次数，默认2
			int secCount = 2;

			if (faceValConfig == null) {//无配置 只走易道

				// 查询用户当天易道验证失败次数（根据临时单子）
				List<HyScoreRecord> fstList = new ArrayList<HyScoreRecord>();
				fstList = hyScoreRecordQuery.queryFirByIdNoTemp(personFaceReq.getIdNo(), personFaceReq.getUuid(),
						"YDBS",personFaceReq.getTempNo());
				if (fstList.size() >= fstCount) {
					logger.info("易道设置次数用完11");
					whichPart="";
				}else{
					whichPart="YDBS";	
				}
				

			} else {
				
			//1:---------------------判断走哪个人脸识别机构

			
				if (!StringUtils.isEmpty(personFaceReq.getTempNo())) {//
					// 司机贷只走face++
					TempCreditQuery tempQuery = new TempCreditQuery();
					TempCredit tempCredit =tempQuery.queryTempCreditByAppId(personFaceReq.getTempNo());

					if (tempCredit != null&&"SX".equals(tempCredit.getCredit_type())) {
					
							faceValConfig.setWhichPart("FACE");
							// 如果司机贷，只走face++
							personFaceResp.setDoGongAn("Y");// 如果是司机贷调用公安系统
					
					} 
				}
				
				// 配置易道次数
				if (!StringUtils.isEmpty(faceValConfig.getFstCount())) {
					fstCount = Integer.parseInt(faceValConfig.getFstCount());
				}
				// 配置face++次数
				if (!StringUtils.isEmpty(faceValConfig.getSecCount())) {
					secCount = Integer.parseInt(faceValConfig.getSecCount());
				}
//2------------------------------------------------------,根据人脸识别机构，查询数据库配置失败次数，判断是否还能继续人脸识别
				// 无配置识别机构默认走易道
				if (StringUtils.isEmpty(faceValConfig.getWhichPart())) {// 走易道

					logger.info(personFaceReq.getIdNo()+"未知别机构");
					// 查询用户当天易道验证失败次数（根据临时单子）
					List<HyScoreRecord> fstList = new ArrayList<HyScoreRecord>();
					fstList = hyScoreRecordQuery.queryFirByIdNoTemp(personFaceReq.getIdNo(), personFaceReq.getUuid(),
							"YDBS",personFaceReq.getTempNo());
					if (fstList.size() >= fstCount) {
						logger.info("易道设置次数用完");
						whichPart="";
					}else{
						whichPart="YDBS";	
					}

				} else {

					// 配置易道
					if ("YDBS".equals(faceValConfig.getWhichPart())) {// 如果走易道

						// 查询用户当天易道验证失败次数（根据临时单子）
						List<HyScoreRecord> fstList = new ArrayList<HyScoreRecord>();
						fstList = hyScoreRecordQuery.queryFirByIdNoTemp(personFaceReq.getIdNo(), personFaceReq.getUuid(),
								"YDBS",personFaceReq.getTempNo());

						if (fstList.size() >= fstCount) {
							logger.info("易道设置次数用完");
							whichPart="";	
						}else{
							whichPart="YDBS";	
						}

						// 配置face++
					} else if ("FACE".equals(faceValConfig.getWhichPart())) {// 如果走face++
						// 查询用户当天face++验证失败次数
						List<HyScoreRecord> secList = new ArrayList<HyScoreRecord>();
						secList = hyScoreRecordQuery.querySecByIdNo(personFaceReq.getIdNo(), personFaceReq.getUuid(),
								"FACE");

						if (secList.size() >= secCount) {

							logger.info("face++设置次数用完");
							whichPart="";	
						}else{
							whichPart="FACE";	
						}
					} else if ("YDBSFACE".equals(faceValConfig.getWhichPart())) {// 先走易道再走face++
						// 查询用户当天易道验证失败次数（根据临时单子）
						List<HyScoreRecord> fstList = new ArrayList<HyScoreRecord>();
						fstList = hyScoreRecordQuery.queryFirByIdNoTemp(personFaceReq.getIdNo(), personFaceReq.getUuid(),
								"YDBS",personFaceReq.getTempNo());

						if (fstList.size() >= fstCount) {
							logger.info("易道设置次数用完走face++");
							// face++次数
							List<HyScoreRecord> secList = new ArrayList<HyScoreRecord>();
							secList = hyScoreRecordQuery.querySecByIdNo(personFaceReq.getIdNo(),
									personFaceReq.getUuid(), "FACE");
							if (secList.size() >= secCount) {
								logger.info("易道和face++设置次数用完");
								whichPart="";	
							}else{
								whichPart="FACE";	
							}

						}else{
							whichPart="YDBS";		
						}

					} else {// 默认，走易道
						logger.info("配置识别机构不能判别");
						// 查询用户当天易道验证失败次数（根据临时单子）
						List<HyScoreRecord> fstList = new ArrayList<HyScoreRecord>();
						fstList = hyScoreRecordQuery.queryFirByIdNoTemp(personFaceReq.getIdNo(), personFaceReq.getUuid(),
								"YDBS",personFaceReq.getTempNo());
						if (fstList.size() >= fstCount) {
							logger.info("易道设置次数用完");
							whichPart="";	
						}else{
							whichPart="YDBS";	
						}
					}

				}

			}

			
			
		} catch (Exception e) {
			logger.error("身份证：" + personFaceReq.getIdNo() + "判断是否能够人脸识别异常", e);
			resultCode = ResultCodes.CHECK_FACE_VAL_EXCEPTION;
			whichPart="";
		}
		if(StringUtils.isEmpty(whichPart)){
			resultCode=ResultCodes.CHECK_FACE_INOT_SUPPORT;	
		}
		
		personFaceResp.setResultCode(resultCode);
		personFaceResp.setWhichPart(whichPart);
		
		return personFaceResp;
	}

}
