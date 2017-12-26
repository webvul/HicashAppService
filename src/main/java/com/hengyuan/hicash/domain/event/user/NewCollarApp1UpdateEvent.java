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
import com.hengyuan.hicash.parameters.request.user.NewCollarApp1UpdateReq;
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
public class NewCollarApp1UpdateEvent {

	private static Logger logger = Logger
			.getLogger(NewCollarApp1UpdateEvent.class);

	/**
	 * 修改个人信息
	 * 
	 * @param req
	 * @return
	 * @throws UpdateInfoException
	 */
	public void updateCollarMsg(NewCollarApp1UpdateReq req)
			throws UpdateInfoException {

		Map<String, Object> setMap = new HashMap<String, Object>();
		
		
		setMap.put("name", req.getRealName());
		setMap.put("identity_no", req.getIdentiyNo());
		setMap.put("marital_Status", req.getMatrimonySystem());
		
	    setMap.put("qq_number", req.getQqNumber());// qq
		setMap.put("qqnumber", req.getQqNumber());

		setMap.put("fixed_tel_area", req.getHomePhoneArea());// 家庭电话
		setMap.put("fixed_tel", req.getHomePhoneNum());// 家庭电话
		setMap.put("fimily_Area_Province", req.getHomeProvince());
		setMap.put("fimily_Area_City", req.getHomeCity());
		setMap.put("fimily_Area_Area", req.getHomeDistrict());
		setMap.put("fimily_Area_Road", req.getHomeDetails());
		setMap.put("monthlyConsumption", req.getMonthlyConsumption());
		
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
