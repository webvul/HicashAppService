package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.user.CustTempApplyPicQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.QuerySingleImgQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.QuerySingleImgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.QuerySingleImgResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 图片查询
 * 嗨秒贷图片查询
 * @author LiHua.Ren
 * @createDate 2015-06-01
 *
 */
public class QuerySingleImgService  implements RespService{
	private static Logger logger = Logger.getLogger(StuApp2Service.class);

//	private CustomerQuery customerQuery = new CustomerQuery();
	private CustTempApplyPicQuery tempQuery=new CustTempApplyPicQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		QuerySingleImgReq req = (QuerySingleImgReq) parmRequest;
		QuerySingleImgResp resp = null;
		try{
			
//			 CustomerEntity cust = queryPicInfo(req.getUserName());
//			 if(cust != null){
//				 	resp = new QuerySingleImgResp();
//				 	resp.setIdcardFrontUrl(cust.getIdcardFrontUrl());
//					resp.setUserIdcardFrontUrl(cust.getUserIdcardFrontUrl());
//					resp.setIdcardVersoUrl(cust.getIdcardVersoUrl());
//					resp.setSchoolCardFrontUrl(cust.getSchoolCardFrontUrl());
//					resp.setSchoolCardVersoUrl(cust.getSchoolCardVersoUrl());
//					resp.setStuCardFrontUrl(cust.getStuCardFrontUrl());
//					resp.setStuPhotoInfoUrl(cust.getStuPhotoInfoUrl());
//					resp.setStuRegistInfoUrl(cust.getStuRegistInfoUrl());
//					/* 小图url */
//					resp.setIdcardFrontUrlThum(cust.getIdcardFrontUrlThum());
//					resp.setUserIdcardFrontUrlThum(cust.getUserIdcardFrontUrlThum());
//					resp.setIdcardVersoUrlThum(cust.getIdcardVersoUrlThum());
//					resp.setSchoolCardFrontUrlThum(cust.getSchoolCardFrontUrlThum());
//					resp.setSchoolCardVersoUrlThum(cust.getSchoolCardVersoUrlThum());
//					resp.setStuCardFrontUrlThum(cust.getStuCardFrontUrlThum());
//					resp.setStuPhotoInfoUrlThum(cust.getStuPhotoInfoUrlThum());
//					resp.setStuRegistInfoUrlThum(cust.getStuRegistInfoUrlThum());
//			 }else{
				 
				 resp = tempQuery.queryImage(req.getUserName());
//			 }
				 resp.setResultCode(ResultCodes.NORMAL);
//				if(resp != null){
//					
//					resp.setResultCode(ResultCodes.NORMAL);
//				}else{
//					resp = new QuerySingleImgResp();
//					resp.setResultCode(ResultCodes.STU_APP_QUERYIMG_NOT_FOUND);
//				}
//			}else {
//				if (custType != null) {
//					resp = new QuerySingleImgResp();
//					resp.setResultCode(ResultCodes.STU_DETAIL_CUSTTYPE_NOT_FOUND);
//				}else {
//					resp = new QuerySingleImgResp();
//					resp.setResultCode(ResultCodes.STU_APP_QUERYIMG_NOT_FOUND);
//				}
//			}
			
		}catch (Exception e) {
			resp = new QuerySingleImgResp();
			resp.setResultCode(ResultCodes.STU_APP_QUERYIMG_EXPRESION);
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.STU_APP_QUERYIMG_EXPRESION, e);
		} finally {
			ConnManager.closeConn();
		}
		return resp;
		

	}

//	@Override
//	public ParmResponse responseValue(ParmRequest parmRequest) {
//		QuerySingleImgReq req = (QuerySingleImgReq) parmRequest;
//		QuerySingleImgResp resp = null;
//		
//		try{
//			
//			 CustomerEntity cust = queryPicInfo(req.getUserName());
//			 if(cust != null){
//				 	resp = new QuerySingleImgResp();
//				 	resp.setIdcardFrontUrl(cust.getIdcardFrontUrl());
//					resp.setUserIdcardFrontUrl(cust.getUserIdcardFrontUrl());
//					resp.setIdcardVersoUrl(cust.getIdcardVersoUrl());
//					resp.setSchoolCardFrontUrl(cust.getSchoolCardFrontUrl());
//					resp.setSchoolCardVersoUrl(cust.getSchoolCardVersoUrl());
//					resp.setStuCardFrontUrl(cust.getStuCardFrontUrl());
//					resp.setStuPhotoInfoUrl(cust.getStuPhotoInfoUrl());
//					resp.setStuRegistInfoUrl(cust.getStuRegistInfoUrl());
//					/* 小图url */
//					resp.setIdcardFrontUrlThum(cust.getIdcardFrontUrlThum());
//					resp.setUserIdcardFrontUrlThum(cust.getUserIdcardFrontUrlThum());
//					resp.setIdcardVersoUrlThum(cust.getIdcardVersoUrlThum());
//					resp.setSchoolCardFrontUrlThum(cust.getSchoolCardFrontUrlThum());
//					resp.setSchoolCardVersoUrlThum(cust.getSchoolCardVersoUrlThum());
//					resp.setStuCardFrontUrlThum(cust.getStuCardFrontUrlThum());
//					resp.setStuPhotoInfoUrlThum(cust.getStuPhotoInfoUrlThum());
//					resp.setStuRegistInfoUrlThum(cust.getStuRegistInfoUrlThum());
//			 }else{
//				 resp = tempQuery.querySingleResp(req.getUserName(), req.getTempAppNo());
//			 }
//				 resp.setResultCode(ResultCodes.NORMAL);
////				if(resp != null){
////					
////					resp.setResultCode(ResultCodes.NORMAL);
////				}else{
////					resp = new QuerySingleImgResp();
////					resp.setResultCode(ResultCodes.STU_APP_QUERYIMG_NOT_FOUND);
////				}
////			}else {
////				if (custType != null) {
////					resp = new QuerySingleImgResp();
////					resp.setResultCode(ResultCodes.STU_DETAIL_CUSTTYPE_NOT_FOUND);
////				}else {
////					resp = new QuerySingleImgResp();
////					resp.setResultCode(ResultCodes.STU_APP_QUERYIMG_NOT_FOUND);
////				}
////			}
//			
//		}catch (Exception e) {
//			resp = new QuerySingleImgResp();
//			resp.setResultCode(ResultCodes.STU_APP_QUERYIMG_EXPRESION);
//			
//			/* 记录错误信息 */
//			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.STU_APP_QUERYIMG_EXPRESION, e);
//		} finally {
//			ConnManager.closeConn();
//		}
//		return resp;
//	}
//	
	public CustomerEntity queryPicInfo(String userName){
		
		CustomerQuery custQuery = new CustomerQuery();
		return custQuery.queryCustomerByUserName(userName);
	}
}
