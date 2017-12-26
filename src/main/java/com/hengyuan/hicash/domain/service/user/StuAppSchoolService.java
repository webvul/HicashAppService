package com.hengyuan.hicash.domain.service.user;

import java.util.ArrayList;
import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.UniverstyQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.University;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.StuAppSchoolReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuAppSchoolResp;


/**
 * @author Administrator
 *
 */
public class StuAppSchoolService implements RespService {

	private UniverstyQuery universityQuery = new UniverstyQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		StuAppSchoolReq cityReq = (StuAppSchoolReq)parmRequest;
		StuAppSchoolResp schoolResp = new StuAppSchoolResp();
		List<University> schoolsList  = new ArrayList<University>();
		schoolsList = universityQuery.querySchoolByCityCode(cityReq.getCityCode());

		schoolResp.setSchools(schoolsList);
		schoolResp.setResultCode(ResultCodes.NORMAL);

		return schoolResp;
	}

}
