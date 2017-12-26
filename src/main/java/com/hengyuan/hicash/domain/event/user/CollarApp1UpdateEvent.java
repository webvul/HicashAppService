package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdateInfoException;
import com.hengyuan.hicash.parameters.request.user.CollarApp1UpdateReq;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 白领完善资料dao
 * 
 * @author LiHua.Ren
 * @create date 2015-06-17
 * 
 */
public class CollarApp1UpdateEvent {

	private static Logger logger = Logger
			.getLogger(CollarApp1UpdateEvent.class);

	/**
	 * 修改个人信息
	 * 
	 * @param req
	 * @return
	 * @throws UpdateInfoException
	 */
	public void updateCollarMsg(CollarApp1UpdateReq req)
			throws UpdateInfoException {

		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("marital_Status", req.getMatrimonySystem());
		// 如果婚姻状况是已婚
		if (Consts.HUNYIN_Q001.equals(req.getMatrimonySystem())) {
			setMap.put("spouse_Name", req.getSpouseName());
			if (!StringUtils.isEmpty(req.getSpouseMobile())) {
				setMap.put("spouse_Mobile", req.getSpouseMobile());
			}
		}
		setMap.put("qq_number", req.getQqNumber());// qq
		setMap.put("qqnumber", req.getQqNumber());

		setMap.put("fixed_tel_area", req.getHomePhoneArea());// 家庭电话
		setMap.put("fixed_tel", req.getHomePhoneNum());// 家庭电话
		setMap.put("graduated_School", req.getSchoolName());// 毕业院校
		setMap.put("fullTime_flag", req.getEducationalSystem());// 学制
		setMap.put("now_education", req.getEducationBg());// 最高学历
		if (RegexValidate.isNull(req.getEndDateYear())
				&& RegexValidate.isNull(req.getEndDateMonth())) {
			setMap.put("end_school_time", null);
		} else {
			setMap.put("end_school_time",
					req.getEndDateYear() + "-" + req.getEndDateMonth());
		}// 毕业时间
		
		setMap.put("NATION", req.getNation());
		setMap.put("IDCARD_VALIDITY_STARTDATE", req.getIdCardValStartDate());
		setMap.put("IDCARD_VALIDITY_ENDDATE", req.getIdCardValEndDate());
		setMap.put("IDCARD_VALIDITY", req.getIdCardValidity());
		
		if(!RegexValidate.isNull(req.getLoanUse())){
			setMap.put("borrow_money_use", req.getLoanUse());
		}

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", req.getUserName());

		String updateSql = MapAssemForSql.getUpdateSql(
				TableConsts.CUST_CUSTOMER, setMap, whereMap);
		// 记录日志
		RecordUtils.writeAction(logger, req.getUuid(), updateSql);
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new UpdateInfoException();
		}
	}

}
