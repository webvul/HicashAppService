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
import com.hengyuan.hicash.parameters.request.user.NewCollarApp2UpdateReq;
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
public class NewCollarApp2UpdateEvent {

	private static Logger logger = Logger
			.getLogger(NewCollarApp2UpdateEvent.class);

	/**
	 * 修改个人信息
	 * 
	 * @param req
	 * @return
	 * @throws UpdateInfoException
	 */
	public void updateCollarMsg(NewCollarApp2UpdateReq req)
			throws UpdateInfoException {

		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("unitName", req.getUnitName());//单位名称
		setMap.put("job_nature", req.getInDepartment());//部门
		setMap.put("workExperience", req.getWorkDate());//工作年限
		if(RegexValidate.isNull(req.getUnitStartDateYear())&&RegexValidate.isNull(req.getUnitEndDateMonth()))//入职时间
		{
			setMap.put("entry_time",null);
		}else{
			setMap.put("entry_time", req.getUnitStartDateYear() + "-" + req.getUnitEndDateMonth());
		}
		setMap.put("work_Area_Province", req.getUnitProvince());
		setMap.put("work_Area_City", req.getUnitCity());
		setMap.put("work_Area_Area", req.getUnitDistrict());
		setMap.put("work_Area_Road", req.getUnitDetails());//单位地址
		setMap.put("unit_tel_area", req.getUnitPhoneArea());//单位电话
		setMap.put("unit_tel", req.getUnitPhoneNum());//单位电话
		setMap.put("EXPRESS_ADDRESSTYPE", req.getExpressAddressType());
		if(Consts.UNIT_CODE.equals(req.getExpressAddressType())){
			/* 邮寄地址 为 单位地址 */
			setMap.put("EXPRESS_PROVINCE", req.getUnitProvince());
			setMap.put("EXPRESS_CITY", req.getUnitCity());
			setMap.put("EXPRESS_AREA", req.getUnitDistrict());
			setMap.put("EXPRESS_DETAIL", req.getUnitDetails());
			
			
			setMap.put("other_Adress_Province", req.getUnitProvince());
			setMap.put("other_Adress_City", req.getUnitCity());
			setMap.put("Other_Adress_Area", req.getUnitDistrict());
			setMap.put("other_Accommodation_Address", req.getUnitDetails());
			//同步现居住地址类型
			setMap.put("now_Fimily_Adress", Consts.UNIT_CODE);
			
		}
		else if(Consts.OTHER_CODE.equals(req.getExpressAddressType())){
			setMap.put("EXPRESS_PROVINCE", req.getExpressProvince());
			setMap.put("EXPRESS_CITY", req.getExpressCity());
			setMap.put("EXPRESS_AREA", req.getExpressDistrict());
			setMap.put("EXPRESS_DETAIL", req.getExpressDetails());	
			
			setMap.put("other_Adress_Province", req.getExpressProvince());
			setMap.put("other_Adress_City", req.getExpressCity());
			setMap.put("Other_Adress_Area", req.getExpressDistrict());
			setMap.put("other_Accommodation_Address", req.getExpressDetails());
			//同步现居住地址类型
			setMap.put("now_Fimily_Adress", Consts.OTHER_CODE);
		}	
		
		setMap.put("monthly_income", req.getMonthlyIncome());
		
		/**2016-4-13*/
		setMap.put("COMPANY_SCALE", req.getCompanyScale());
		setMap.put("COMPANY_WORK_YEAR", req.getCompanyWorkYear());
		setMap.put("job_duties", req.getJobduties());

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
