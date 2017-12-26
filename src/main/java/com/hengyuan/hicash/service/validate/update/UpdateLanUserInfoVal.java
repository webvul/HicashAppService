package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.UpdateLanUserInfoReq;
import com.hengyuan.hicash.utils.RegexValidate;


public class UpdateLanUserInfoVal {

	private UpdateLanUserInfoReq userInfoReq;
	
	public UpdateLanUserInfoVal(UpdateLanUserInfoReq userInfoReq) {
		this.userInfoReq = userInfoReq;
	}
	
	public String validate(){
		
		if (RegexValidate.isNull(userInfoReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(userInfoReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		/* 邀请码 */
		if (RegexValidate.isNull(userInfoReq.getInviteCode())) {
			return ResultCodes.UPDATELANUSERINFO_INVITECODE_ISNULL;
		}

		if (!RegexValidate.isNumber(userInfoReq.getInviteCode())) {
			return ResultCodes.UPDATELANUSERINFO_INVITECODE_ILLEGAL;
		}
		
		/* 2016-03-07 需求新增 */
		
		/* 是否已有门店 1：是，0：否 */
		if (RegexValidate.isNull(userInfoReq.getIsHaveStore())) {
			return ResultCodes.UPDATELANUSERINFO_ISHAVESTORE_ISNULL;
		}

		if (!RegexValidate.isNumber(userInfoReq.getIsHaveStore())) {
			return ResultCodes.UPDATELANUSERINFO_ISHAVESTORE_ILLEGAL;
		}
		
		/** 如果没有门店 */
		if(Consts.FINAL_NUMBER_0.equals(userInfoReq.getIsHaveStore())){
			
			/* 单位名称 */
			if (RegexValidate.isNull(userInfoReq.getUnitName())) {
				return ResultCodes.UPDATELANUSERINFO_UNITNAME_ISNULL;
			}

			if (!RegexValidate.isDescFlag(userInfoReq.getUnitName(), 100)) {
				return ResultCodes.UPDATELANUSERINFO_UNITNAME_ILLEGAL;
			}
			
			/* 店名 */
			if (RegexValidate.isNull(userInfoReq.getStoreName())) {
				return ResultCodes.UPDATELANUSERINFO_STORENAME_ISNULL;
			}

			if (!RegexValidate.isDescFlag(userInfoReq.getStoreName(), 100)) {
				return ResultCodes.UPDATELANUSERINFO_STORENAME_ISNULL;
			}
			
			/* 省份城市 */
			if (RegexValidate.isNull(userInfoReq.getProvince()) || RegexValidate.isNull(userInfoReq.getCity())) {
				return ResultCodes.UPDATELANUSERINFO_CITY_ISNULL;
			}
			
			if (!RegexValidate.isSelectToAddress(userInfoReq.getProvince()) || !RegexValidate.isSelectToAddress(userInfoReq.getCity())) {
				return ResultCodes.UPDATELANUSERINFO_CITY_ILLEGAL;
			}
			
			/* 店址 */
			if (RegexValidate.isNull(userInfoReq.getAddress())) {
				return ResultCodes.UPDATELANUSERINFO_ADDRESS_ISNULL;
			}

			if (!RegexValidate.isDescFlag(userInfoReq.getAddress(), 100)) {
				return ResultCodes.UPDATELANUSERINFO_ADDRESS_ILLEGAL;
			}
			
			/* 门店路牌号 */
			if (RegexValidate.isNull(userInfoReq.getRoadNo())) {
				return ResultCodes.UPDATELANUSERINFO_ROADNO_ISNULL;
			}

//			if (!RegexValidate.isNumber(userInfoReq.getRoadNo())) {
//				return ResultCodes.UPDATELANUSERINFO_ROADNO_ILLEGAL;
//			}
			
			/* 经营权限 */
			if (RegexValidate.isNull(userInfoReq.getOperatePower())) {
				return ResultCodes.UPDATELANUSERINFO_OPERATEPOW_ISNULL;
			}

			if (!RegexValidate.isDescFlag(userInfoReq.getOperatePower(),10)) {
				return ResultCodes.UPDATELANUSERINFO_OPERATEPOW_ILLEGAL;
			}
			
			/* 经营时间 */
			if (RegexValidate.isNull(userInfoReq.getOperateTime())) {
				return ResultCodes.UPDATELANUSERINFO_OPERATETIM_ISNULL;
			}

			if (!RegexValidate.isDescFlag(userInfoReq.getOperateTime(),20)) {
				return ResultCodes.UPDATELANUSERINFO_OPERATETIM_ILLEGAL;
			}
			
			/* 法人姓名 */
			if (RegexValidate.isNull(userInfoReq.getLegalName())) {
				return ResultCodes.UPDATELANUSERINFO_LEGALNAME_ISNULL;
			}

			if (!RegexValidate.isDescFlag(userInfoReq.getLegalName(), 50)) {
				return ResultCodes.UPDATELANUSERINFO_LEGALNAME_ILLEGAL;
			}
			
			/* 单位电话 */
			if (RegexValidate.isNull(userInfoReq.getUnitPhone())) {
				return ResultCodes.UPDATELANUSERINFO_UNITPHONE_ISNULL;
			}

//			if (!RegexValidate.isNumber(userInfoReq.getUnitPhone())) {
//				return ResultCodes.UPDATELANUSERINFO_UNITPHONE_ILLEGAL;
//			}
			
			/* 单位照片url */
//			if (RegexValidate.isNull(userInfoReq.getUnitPicUrl())) {
//				return ResultCodes.UPDATELANUSERINFO_UNITPIC_ISNULL;
//			}
			
			/* 位置定位照片url */
			if (RegexValidate.isNull(userInfoReq.getLocaPicUrl())) {
				return ResultCodes.UPDATELANUSERINFO_LOCAPIC_ISNULL;
			}
			
			/* 门店照片 */
			if (RegexValidate.isNull(userInfoReq.getStorePicUrl())) {
				return ResultCodes.UPDATELANUSERINFO_STOREPIC_ISNULL;
			}
			
			/* 工商网查询截图 */
			if (RegexValidate.isNull(userInfoReq.getStorePicUrl1())) {
				return ResultCodes.UPDATELANUSERINFO_GSWCXPIC_ISNULL;
			}
			
//			if (RegexValidate.isNull(userInfoReq.getStorePicUrl2())) {
//				return ResultCodes.UPDATELANUSERINFO_OTHERPIC_ISNULL;
//			}
//			
//			if (RegexValidate.isNull(userInfoReq.getStorePicUrl3())) {
//				return ResultCodes.UPDATELANUSERINFO_OTHERPIC_ISNULL;
//			}
			
		}else{
			
			/* 门店号 */
			if (RegexValidate.isNull(userInfoReq.getStoreCode())) {
				return ResultCodes.UPDATELANUSERINFO_STORECODE_ISNULL;
			}
			
			/* 门店图片url */
			if (RegexValidate.isNull(userInfoReq.getUserScenepicUrl())) {
				return ResultCodes.UPDATELANUSERINFO_PICURL_ISNULL;
			}

			if (RegexValidate.isNull(userInfoReq.getUserScenepicThumUrl())) {
				return ResultCodes.UPDATELANUSERINFO_PICURL_ISNULL;
			}

		}
		
		return ResultCodes.NORMAL;
	}
	
}
