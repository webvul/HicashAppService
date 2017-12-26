package com.hengyuan.hicash.domain.service.user;

import java.sql.SQLException;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.FaceRecognitionQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.FaceRecognitionReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.FaceRecognitionResp;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 
 * @author fish
 *
 * @date 2017年2月22日 上午11:26:27
 */
public class FaceRecognitionService implements RespService {

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		FaceRecognitionQuery faceRecognition = new FaceRecognitionQuery();

		FaceRecognitionReq req = (FaceRecognitionReq) parmRequest;
		FaceRecognitionResp resp = new FaceRecognitionResp();

		String result = validate(req);
		try {
			if (result.equals(ResultCodes.NORMAL)) {
				int i = faceRecognition.query(req);
				resp.setResult(String.valueOf(i));
			}
		} catch (SQLException e) {
			result = ResultCodes.FACERECOGNITION_EXCEPTION;
			e.printStackTrace();
		}
		resp.setResultCode(result);
		return resp;
	}

	public String validate(FaceRecognitionReq faceReq) {

		// 验证身份证号码
		if (RegexValidate.isNull(faceReq.getIdNo())) {
			return ResultCodes.REGISTER_IDCARD_ISNULL;
		}

		return ResultCodes.NORMAL;
	}
}
