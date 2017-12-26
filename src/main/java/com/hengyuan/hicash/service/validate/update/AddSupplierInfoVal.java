package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.AddSupplierInfoReq;
import com.hengyuan.hicash.utils.RegexValidate;

/** 
 * 商户入驻-新增商户 validate
 * @author Cary.Liu
 * @createDate 2015-07-10
 * 
 */
public class AddSupplierInfoVal {
	
	private AddSupplierInfoReq addSupplierInfoReq;
	
	public AddSupplierInfoVal(AddSupplierInfoReq addSupplierInfoReq){
		this.addSupplierInfoReq = addSupplierInfoReq;
	}
	
	public String validate(){
		
		/* 验证uuid */
		if(RegexValidate.isNull(addSupplierInfoReq.getUuid())){
			return ResultCodes.UUIDNULL;
		}
		
		if(!RegexValidate.isUuid(addSupplierInfoReq.getUuid())){
			return ResultCodes.UUIDILLEGAL;
		}
		
		/* 手机号 */
		if(RegexValidate.isNull(addSupplierInfoReq.getMobileNo())){
			return ResultCodes.ADDMERAPP_MOBILE_ISNULL;
		}
		
		if(!RegexValidate.isIphon(addSupplierInfoReq.getMobileNo())){
			return ResultCodes.ADDMERAPP_MOBILE_ILLEGAL;
		}
		
		/* 验证码 */
		if(RegexValidate.isNull(addSupplierInfoReq.getIdentifyCode())){
			return ResultCodes.ADDMERAPP_IDCODE_ISNULL;
		}
		
		if(!RegexValidate.isNumber(addSupplierInfoReq.getIdentifyCode())){
			return ResultCodes.ADDMERAPP_IDCODE_ILLEGAL;
		}
		
		/* 验证用户名 */
		if(RegexValidate.isNull(addSupplierInfoReq.getSupplierUserName())){
			return ResultCodes.ADDMERAPP_USERNAME_ISNULL;
		}
		
		if(!RegexValidate.isApproveUsername(addSupplierInfoReq.getSupplierUserName())){
			return ResultCodes.ADDMERAPP_MERUSERNAME_ILLEGAL;
		}
		
		/* 验证密码 */
		if(RegexValidate.isNull(addSupplierInfoReq.getSupplierUserPassword())){
			return ResultCodes.ADDMERAPP_MERPASSWORD_ISNULL;
		}
		
		if(!RegexValidate.isPassword(addSupplierInfoReq.getSupplierUserPassword())){
			return ResultCodes.ADDMERAPP_MERPASSWORD_ILLEGAL;
		}
		
		/* 验证商户联系人(姓名) */
		if(RegexValidate.isNull(addSupplierInfoReq.getRealName())){
			return ResultCodes.ADDMERAPP_CONTACTNAME_ISNULL;
		}
		
		/* 验证商户名称 */
		if(RegexValidate.isNull(addSupplierInfoReq.getSupplierName())){
			return ResultCodes.ADDMERAPP_MERUSERNAME_ISNULL;
		}
		
		
		return ResultCodes.NORMAL;
	}

	public AddSupplierInfoReq getAddSupplierInfoReq() {
		return addSupplierInfoReq;
	}

	public void setAddSupplierInfoReq(AddSupplierInfoReq addSupplierInfoReq) {
		this.addSupplierInfoReq = addSupplierInfoReq;
	}
	
}
