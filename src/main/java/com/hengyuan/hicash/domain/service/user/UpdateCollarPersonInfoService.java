package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.ReceiveAddressEvent;
import com.hengyuan.hicash.domain.event.user.UpdateCollarPersonInfoEvent;
import com.hengyuan.hicash.domain.query.user.AddressQuery;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustReceiveAddressEntity;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.exception.SaveAddressException;
import com.hengyuan.hicash.exception.UpdateAddressException;
import com.hengyuan.hicash.exception.UpdateCustomerException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CollarPersonReq;
import com.hengyuan.hicash.parameters.request.user.CustReceiveAddressReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollarPersonResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * 
 * @author fish
 *
 * @date 2017年1月10日 上午9:56:00
 */
public class UpdateCollarPersonInfoService implements RespService {
	
	private Logger logger =Logger.getLogger(UpdateCollarPersonInfoService.class);

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		UpdateCollarPersonInfoEvent event = new UpdateCollarPersonInfoEvent();

		CollarPersonReq req = (CollarPersonReq) parmRequest;
		CollarPersonResp resp = new CollarPersonResp();

		try {
			String result = validate(req);
			if (result.equals(ResultCodes.NORMAL)) {
				ConnManager.beginTransaction();
				event.updateCustomerPersonInfo(req);
				SaveOrUpdateAddress(req);
				ConnManager.commit();
				resp.setResultCode(ResultCodes.NORMAL);
			} else {
				resp.setResultCode(result);
			}
		} catch (UpdateCustomerException e) {
			ConnManager.rollback();
			resp.setResultCode(ResultCodes.UPDATE_CUSTOMER_EXCEPTION);
			
		} catch (SaveAddressException e) {
			ConnManager.rollback();
			resp.setResultCode(ResultCodes.ADD_ADRESS_EXCEPTION);
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.SAVEAPPACCT_EXCEPTION1, e);
		} catch (UpdateAddressException e) {
			ConnManager.rollback();
			resp.setResultCode(ResultCodes.UPDATE_ADDRESS_EXCCEPTION);
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.UPDATE_ADDRESS_EXCCEPTION, e);
		}finally {
			ConnManager.closeConn();
		}
		resp.setUuid(req.getUuid());
		return resp;
	}

	public String validate(CollarPersonReq req) {

		// 验证UUID
		if (RegexValidate.isNull(req.getUuid())) {
			return ResultCodes.UUIDNULL;
		}

		// 验证用户名
		if (RegexValidate.isNull(req.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}

		// 验证真实姓名
		if (RegexValidate.isNull(req.getReal_name())) {
			return ResultCodes.REGISTER_REALNAME_ISNULL;
		}

		// 验证身份证号码
		if (RegexValidate.isNull(req.getId_no())) {
			return ResultCodes.REGISTER_IDCARD_ISNULL;
		}
		if (!RegexValidate.isIdCard(req.getId_no())) {
			return ResultCodes.REGISTER_IDCARD_ILLEGAL;
		}

		// 验证婚姻状况
		if (RegexValidate.isNull(req.getMarital_status())) {
			return ResultCodes.COLLAR_INFO_MATRIMONY_NOTNULL;
		}

//		// 验证学历
//		if (RegexValidate.isNull(req.getEducation_code())) {
//			return ResultCodes.STU_APP_UPDATE_SCHOOL_EDUCATION_NOTNULL;
//		}

		// 验证现居地址
		if (RegexValidate.isNull(req.getProvince())) {
			return ResultCodes.PROVINCE_CITY_AREA_ISNULL;
		}
		if (RegexValidate.isNull(req.getCity())) {
			return ResultCodes.PROVINCE_CITY_AREA_ISNULL;
		}
		if (RegexValidate.isNull(req.getArea())) {
			return ResultCodes.PROVINCE_CITY_AREA_ISNULL;
		}

		// 验证现居详细地址
		if (RegexValidate.isNull(req.getAddress())) {
			return ResultCodes.XJDZ_ADDRESS_ISNULL;
		}

		// 验证邮箱  
		if (RegexValidate.isNull(req.getEmail())) {
			return ResultCodes.CALL_PHONE_QUARTET_PROTOCOL_EMAIL_ISNULL;
		}
		if (!RegexValidate.isEmail(req.getEmail())) {
			return ResultCodes.CALL_PHONE_QUARTET_PROTOCOL_EMAIL_ERROR;
		}

		//0其他，按照原来逻辑即可
		if(req.getFulltimeDriver().equals("")){
			// 验证单位名称
			if (RegexValidate.isNull(req.getUnit_name())) {
				return ResultCodes.UPDATELANUSERINFO_UNITNAME_ISNULL;
			}

			// 验证单位电话区号
			if (RegexValidate.isNull(req.getUnit_phone_area())) {
				return ResultCodes.UNIT_PHONE_AREA_ISNULL;
			}

			// 验证单位电话&格式
			if (RegexValidate.isNull(req.getUnit_phone())) {
				return ResultCodes.UPDATELANUSERINFO_UNITPHONE_ISNULL;
			}
			if (!RegexValidate.isTel(req.getUnit_phone_area(),req.getUnit_phone())) {
				return ResultCodes.UPDATELANUSERINFO_UNITPHONE_ILLEGAL;
			}

			// 验证工作年限
			if (RegexValidate.isNull(req.getWork_year())) {
				return ResultCodes.COLLAR_UNIT_WORKTIME_NOTNULL;
			}

			// 验证单位省市区
			if (RegexValidate.isNull(req.getUnit_province())) {
				return ResultCodes.COLLAR_CONTACT_UNIT_AREA_NOTNULL;
			}
			if (RegexValidate.isNull(req.getUnit_city())) {
				return ResultCodes.COLLAR_CONTACT_UNIT_AREA_NOTNULL;
			}
			if (RegexValidate.isNull(req.getUnit_area())) {
				return ResultCodes.COLLAR_CONTACT_UNIT_AREA_NOTNULL;
			}

			// 验证单位详细地址
			if (RegexValidate.isNull(req.getUnit_address())) {
				return ResultCodes.COLLAR_CONTACT_UNIT_DETAIL_NOTNUL;
			}
			//贷款用途
//			if (RegexValidate.isNull(req.getLoan_purpose())) {
//				return ResultCodes.CREATEAPPPAY_LOANUSE_ISNULL;
//			}
			
//			// 验证单位性质
//			if (RegexValidate.isNull(req.getUnit_properties())) {
//				return ResultCodes.UNIT_PROPERTIES_ISNULL;
//			}
		//1.兼职，必须填写工作单位
		}else if(req.getFulltimeDriver().equals("JZSJ")){
			// 验证单位名称
			if (RegexValidate.isNull(req.getUnit_name())) {
				return ResultCodes.UPDATELANUSERINFO_UNITNAME_ISNULL;
			}

			// 验证单位电话区号
			if (RegexValidate.isNull(req.getUnit_phone_area())) {
				return ResultCodes.UNIT_PHONE_AREA_ISNULL;
			}

			// 验证单位电话&格式
			if (RegexValidate.isNull(req.getUnit_phone())) {
				return ResultCodes.UPDATELANUSERINFO_UNITPHONE_ISNULL;
			}
			if (!RegexValidate.isTel(req.getUnit_phone_area(),req.getUnit_phone())) {
				return ResultCodes.UPDATELANUSERINFO_UNITPHONE_ILLEGAL;
			}

			// 验证工作年限
			if (RegexValidate.isNull(req.getWork_year())) {
				return ResultCodes.COLLAR_UNIT_WORKTIME_NOTNULL;
			}

			// 验证单位省市区
			if (RegexValidate.isNull(req.getUnit_province())) {
				return ResultCodes.COLLAR_CONTACT_UNIT_AREA_NOTNULL;
			}
			if (RegexValidate.isNull(req.getUnit_city())) {
				return ResultCodes.COLLAR_CONTACT_UNIT_AREA_NOTNULL;
			}
			if (RegexValidate.isNull(req.getUnit_area())) {
				return ResultCodes.COLLAR_CONTACT_UNIT_AREA_NOTNULL;
			}

			// 验证单位详细地址
			if (RegexValidate.isNull(req.getUnit_address())) {
				return ResultCodes.COLLAR_CONTACT_UNIT_DETAIL_NOTNUL;
			}
		}

		// 验证信用卡
		// if (RegexValidate.isNull(req.getCredit_card())) {
		// return ResultCodes.CREDIT_CARD_ISNULL;
		// }

		// 验证身份证有效期起
		// if (RegexValidate.isNull(req.getIdcard_validity_startdate())) {
		// return ResultCodes.USERUPDATEINFO_IDCARDVAL_ISNULL;
		// }

		// 验证身份证有效期止
		// if (RegexValidate.isNull(req.getIdcard_validity_enddate())) {
		// return ResultCodes.USERUPDATEINFO_IDCARDVAL_ISNULL;
		// }

		return ResultCodes.NORMAL;
	}

	
	public void SaveOrUpdateAddress(CollarPersonReq req) throws SaveAddressException, UpdateAddressException{
		
		CustUserQuery custUserQuery = new CustUserQuery();
		CustUserEntity entity = custUserQuery.queryByUserName(req.getUserName());
		
		AddressQuery addressQuery = new AddressQuery();
		CustReceiveAddressEntity unitaddress = addressQuery.queryAddressByRemark("1", req.getUserName());
		CustReceiveAddressEntity familyaddress = addressQuery.queryAddressByRemark("2", req.getUserName());
		CustReceiveAddressReq custReq =null;
		
		ReceiveAddressEvent receiveAddressEvent = new ReceiveAddressEvent();
		if(unitaddress==null||familyaddress==null){
			//家庭地址
			custReq = new CustReceiveAddressReq();
			custReq.setReal_name(req.getReal_name());
			custReq.setMobile(entity.getMobileNo());
			custReq.setProvince_code(req.getProvince());
			custReq.setCity_code(req.getCity());
			custReq.setArea_code(req.getArea());
			custReq.setIs_default("1");
			custReq.setDetail_address(req.getAddress());
			custReq.setRemark("2");
			custReq.setCust_user(req.getUserName());
			receiveAddressEvent.createAddress(custReq);
			//单位地址
			custReq = new CustReceiveAddressReq();
			custReq.setReal_name(req.getReal_name());
			custReq.setMobile(entity.getMobileNo());
			custReq.setProvince_code(req.getUnit_province());
			custReq.setCity_code(req.getUnit_city());
			custReq.setArea_code(req.getUnit_area());
			custReq.setIs_default("0");
			custReq.setDetail_address(req.getUnit_address());
			custReq.setRemark("1");
			custReq.setCust_user(req.getUserName());
			receiveAddressEvent.createAddress(custReq);
		}else{
			//家庭地址
			custReq = new CustReceiveAddressReq();
			custReq.setReal_name(req.getReal_name());
			custReq.setMobile(entity.getMobileNo());
			custReq.setProvince_code(req.getProvince());
			custReq.setCity_code(req.getCity());
			custReq.setArea_code(req.getArea());
			custReq.setIs_default("1");
			custReq.setDetail_address(req.getAddress());
			custReq.setRemark("2");
			custReq.setCust_user(req.getUserName());
			custReq.setId(familyaddress.getId());
			receiveAddressEvent.UpdateAddress(custReq);
			//单位地址
			custReq = new CustReceiveAddressReq();
			custReq.setReal_name(req.getReal_name());
			custReq.setMobile(entity.getMobileNo());
			custReq.setProvince_code(req.getUnit_province());
			custReq.setCity_code(req.getUnit_city());
			custReq.setArea_code(req.getUnit_area());
			custReq.setIs_default("0");
			custReq.setDetail_address(req.getUnit_address());
			custReq.setRemark("1");
			custReq.setCust_user(req.getUserName());
			custReq.setId(unitaddress.getId());
			receiveAddressEvent.UpdateAddress(custReq);
		}
		
		
	}
	
	/**
	 * @title: 根据邮箱后缀截取当前邮箱前缀字符
	 * @param email 邮箱地址
	 * @param suffix 后缀
	 * */
	public String subQQEmail(String email,String suffix) {
		String qq = "";
		if(email.indexOf(suffix) > -1){
			qq=email.substring(0, email.length() - suffix.length());
		}
		return qq; 
	}
	
	/*public static void main(String[] args) {
		System.out.println(subQQEmail("305930712@qq.com","@qq.com"));
	}*/
	
}
