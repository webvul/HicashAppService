package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.SaveInfoException;
import com.hengyuan.hicash.parameters.request.user.NewStuApp1UpdateReq;
import com.hengyuan.hicash.parameters.request.user.StuApp1UpdateReq;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * hicash手机端学生提现申请1完善Dao
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class NewStuApp1UpdateEvent {
	private static Logger logger = Logger.getLogger(NewStuApp1UpdateEvent.class);

	/**
	 * 修改学校信息
	 * 
	 * @param updateMsgReq
	 * @return
	 * @throws SaveInfoException 
	 */
	public void updateStuApp1(NewStuApp1UpdateReq updateMsgReq) 
			throws SaveInfoException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("name", updateMsgReq.getRealName());
		setMap.put("identity_no", updateMsgReq.getIdentiyNo());
		setMap.put("school_Area_Province", updateMsgReq.getSchoolProvince());
		setMap.put("school_Area_City", updateMsgReq.getSchoolCity());
		setMap.put("school_Area_Area", updateMsgReq.getSchoolDistrict());
		setMap.put("school_Area_Road", updateMsgReq.getSchoolDetails());
		setMap.put("fimily_Area_Province", updateMsgReq.getHomeProvince());
		setMap.put("fimily_Area_City", updateMsgReq.getHomeCity());
		setMap.put("fimily_Area_Area", updateMsgReq.getHomeDistrict());
		setMap.put("fimily_Area_Road", updateMsgReq.getHomeDetails());
		setMap.put("EXPRESS_ADDRESSTYPE", updateMsgReq.getExpressAddressType());
		if(Consts.UNIVERSITY_CODE.equals(updateMsgReq.getExpressAddressType())){
			/* 邮寄地址 为 学校地址 */
			setMap.put("EXPRESS_PROVINCE", updateMsgReq.getSchoolProvince());
			setMap.put("EXPRESS_CITY", updateMsgReq.getSchoolCity());
			setMap.put("EXPRESS_AREA", updateMsgReq.getSchoolDistrict());
			setMap.put("EXPRESS_DETAIL", updateMsgReq.getSchoolDetails());

			setMap.put("other_Adress_Province", updateMsgReq.getSchoolProvince());
			setMap.put("other_Adress_City", updateMsgReq.getSchoolCity());
			setMap.put("Other_Adress_Area", updateMsgReq.getSchoolDistrict());
			setMap.put("other_Accommodation_Address", updateMsgReq.getSchoolDetails());
			//同步现居住地址类型
			setMap.put("now_Fimily_Adress", Consts.UNIVERSITY_CODE);
		}else if(Consts.HOME_CODE.equals(updateMsgReq.getExpressAddressType())){
			/* 邮寄地址 为 家庭地址 */
			setMap.put("EXPRESS_PROVINCE", updateMsgReq.getHomeProvince());
			setMap.put("EXPRESS_CITY", updateMsgReq.getHomeCity());
			setMap.put("EXPRESS_AREA", updateMsgReq.getHomeDistrict());
			setMap.put("EXPRESS_DETAIL", updateMsgReq.getHomeDetails());
			
			setMap.put("other_Adress_Province", updateMsgReq.getHomeProvince());
			setMap.put("other_Adress_City", updateMsgReq.getHomeCity());
			setMap.put("Other_Adress_Area", updateMsgReq.getHomeDistrict());
			setMap.put("other_Accommodation_Address", updateMsgReq.getHomeDetails());
			//同步现居住地址类型
			setMap.put("now_Fimily_Adress", Consts.HOME_CODE);
		}else if(Consts.OTHER_CODE.equals(updateMsgReq.getExpressAddressType())){
			setMap.put("EXPRESS_PROVINCE", updateMsgReq.getExpressProvince());
			setMap.put("EXPRESS_CITY", updateMsgReq.getExpressCity());
			setMap.put("EXPRESS_AREA", updateMsgReq.getExpressDistrict());
			setMap.put("EXPRESS_DETAIL", updateMsgReq.getExpressDetails());	
			
			
			setMap.put("other_Adress_Province", updateMsgReq.getExpressProvince());
			setMap.put("other_Adress_City", updateMsgReq.getExpressCity());
			setMap.put("Other_Adress_Area", updateMsgReq.getExpressDistrict());
			setMap.put("other_Accommodation_Address", updateMsgReq.getExpressDetails());
			//同步现居住地址类型
			setMap.put("now_Fimily_Adress", Consts.OTHER_CODE);
		}	
		
		
		setMap.put("qq_number", updateMsgReq.getQqNumber());//qq
		setMap.put("qqNumber", updateMsgReq.getQqNumber());
		setMap.put("fixed_tel", updateMsgReq.getHomePhoneNum());//家庭电话
		setMap.put("fixed_tel_area", updateMsgReq.getHomePhoneArea());
		
		/* 2016-03-10屏蔽 本次不上线 */
		setMap.put("NATION", updateMsgReq.getNation());
		setMap.put("IDCARD_VALIDITY_STARTDATE", updateMsgReq.getIdCardValStartDate());
		setMap.put("IDCARD_VALIDITY_ENDDATE", updateMsgReq.getIdCardValEndDate());
		setMap.put("IDCARD_VALIDITY", updateMsgReq.getIdCardValidity());
		
		setMap.put("borrow_money_use", updateMsgReq.getLoanUse());
		
		//学校地址
//		setMap.put("school_Area_Province", updateMsgReq.getSchoolProvince());
//		setMap.put("school_Area_City", updateMsgReq.getSchoolCity());
//		setMap.put("school_Area_Area", updateMsgReq.getSchoolArea());
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", updateMsgReq.getUserName());
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_CUSTOMER, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, updateMsgReq.getUuid(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new SaveInfoException();
		}
	}
	
}
