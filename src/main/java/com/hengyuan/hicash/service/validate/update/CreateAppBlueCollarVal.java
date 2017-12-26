package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppBlueCollarReq;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * 创建申请件 validate
 * 创建蓝领数码申请 
 * @author LiHua.Ren
 * @createDate 2016-01-25
 * */

public class CreateAppBlueCollarVal extends RegexValidate {
	
	private CreateAppBlueCollarReq valReq;
	
	public CreateAppBlueCollarVal(CreateAppBlueCollarReq valReq){
		this.valReq = valReq;
	}

	public String validate(){
		
		/* 验证uuid */
		if(RegexValidate.isNull(valReq.getUuid())){
			return ResultCodes.UUIDNULL;
		}
		
		if(!RegexValidate.isUuid(valReq.getUuid())){
			return ResultCodes.UUIDILLEGAL;
		}
		
		/* 登陆业务员用户名 */
//		if(RegexValidate.isNull(valReq.getApproveUser())){
//			return ResultCodes.CREATEPAY_APPROUSER_ISNULL;
//		}
//		
//		if(!RegexValidate.isUsername(valReq.getApproveUser())){
//			return ResultCodes.CREATEPAY_APPROUSER_ILLEGAL;
//		}
		
		/* 验证用户名 */
		if(RegexValidate.isNull(valReq.getUserName())){
			return ResultCodes.USERNAME_NOT_NULL;
		}
		
		if(!RegexValidate.isUsername(valReq.getUserName())){
			return ResultCodes.USERNAME_ILLEGAL;
		}
		
		if(RegexValidate.isNull(valReq.getMerProId())){
			return ResultCodes.CREATEAPPPAY_MERPROID_ISNULL;
		}
		
		if(!RegexValidate.isNumber(valReq.getMerProId())){
			return ResultCodes.CREATEAPPPAY_MERPROID_ILLEGAL;
		}
		
		//3c需要产品名称
//		if(Consts.APPFLOW_TYPE_3C.equals(valReq.getApplyType())){
					
		if(RegexValidate.isNull(valReq.getProductName())){
			return ResultCodes.REGISTER_PRODUCTNAME_ISNULL;
		}
				
//		if(!RegexValidate.isNumOrLetter(valReq.getProductName())){
//			return ResultCodes.REGISTER_PRODUCTNAME_ILLEGAL;
//		}
		
//		}
		
		/* 交易金额 */
		if(RegexValidate.isNull(valReq.getTranPrice())){
			return ResultCodes.APPLYAMOUNT_ERROR_ISNULL;
		}
		
		if(!RegexValidate.isDigitPos(valReq.getTranPrice())){
			return ResultCodes.APPLYAMOUNT_ERROR_CANTCHAR;
		}
		
		if(!RegexValidate.isLength(valReq.getTranPrice(),1,10)){
			return ResultCodes.APPLYAMOUNT_ERROR_LENGTH;
		}
		
		/* 商品总价(商户输入) */
//		if(!RegexValidate.isNull(valReq.getMerProPrice())){
//			if(!RegexValidate.isDigitPos(valReq.getMerProPrice())){
//				return ResultCodes.CREATEAPPPAY_PRO_PRICE_ILLEGAL;
//			}
//			
//			if(!RegexValidate.isLength(valReq.getTranPrice(),1,10)){
//				return ResultCodes.CREATEAPPPAY_PRO_PRICE_ILLEGAL;
//			}
//		}
		
		/* 借款用途 */
		if(RegexValidate.isNull(valReq.getLoanUse())){
			return ResultCodes.CREATEAPPPAY_LOANUSE_ISNULL;
		}
		
		if(!RegexValidate.isSelect(valReq.getLoanUse())){
			return ResultCodes.CREATEAPPPAY_LOANUSE_ILLEGAL;
		}
		
		/* 商户ID */
		if(RegexValidate.isNull(valReq.getSupplierId())){
			return ResultCodes.CREATEPAY_SUPPLIERID_ISNULL;
		}
		
		if(!RegexValidate.isNumber(valReq.getSupplierId())){
			return ResultCodes.CREATEPAY_SUPPLIERID_ILLEGAL;
		}
		
		/* 售点ID */
		if(RegexValidate.isNull(valReq.getSiteId())){
			return ResultCodes.CREATEPAY_SITEID_ISNULL;
		}
		
		if(!RegexValidate.isNumber(valReq.getSiteId())){
			return ResultCodes.CREATEPAY_SITEID_ILLEGAL;
		}
		
		/* 贷款产品ID */
		if(RegexValidate.isNull(valReq.getLoanProduct())){
			return ResultCodes.LOANPRODUCT_ERROR_ISNULL;
		}
		
		/* 首付比率 */
		if(RegexValidate.isNull(valReq.getFirstRate())){
			return ResultCodes.FIRSTRATE_ERROR_ISNULL;
		}
		
		if(!RegexValidate.isStuId(valReq.getFirstRate())){
			return ResultCodes.FIRSTRATE_ERROR_CANTCHAR;
		}
		
		if(!RegexValidate.isLength(valReq.getFirstRate(),1,2)){
			return ResultCodes.FIRSTRATE_ERROR_LENGTH;
		}
		
		/* 申请备注 */
//		if(RegexValidate.isNull(valReq.getProductDetail())){
//			return ResultCodes.CREATEAPPPAY_DETAIL_ISNULL;
//		}
//		
//		if(!RegexValidate.isDescFlag(valReq.getProductDetail(), 200)){
//			return ResultCodes.CREATEAPPPAY_DETAIL_ILLEGAL;
//		}
		
		/* 申请方式 */
		if(RegexValidate.isNull(valReq.getApplyType())){
			return ResultCodes.APPLYTYPE_ERROR_ISNULL;
		}
		
		if(!RegexValidate.isApplyType(valReq.getApplyType())){
			return ResultCodes.APPLYTYPE_ERROR_CANTCHAR;
		}
		
		//客户类型
		if(RegexValidate.isNull(valReq.getCustType())){
			return ResultCodes.CUSTTYPE_ERROR_ISNULL;
		}
		
		if(!RegexValidate.isCustType(valReq.getCustType())){
			return ResultCodes.CUSTTYPE_ERROR_CANTCHAR;
		}
		
//		if(Consts.APPFLOW_TYPE_CASH.equals(valReq.getApplyType())){
//			
//			// 车辆抵押，租房分期 行业不添加收款账户
//			
//			if(!"ZFFQ".equals(valReq.getIndustryCode())){
//				if(RegexValidate.isNull(valReq.getBankNo())){
//					return ResultCodes.CREATEAPPPAY_BANKCARDNO_ISNULL;
//				}
//				
//				if(!RegexValidate.isNumber(valReq.getBankNo())){
//					return ResultCodes.CREATEAPPPAY_BANKCARDNO_ILLEGAL;
//				}
//			}
//			
//		}
		
		return ResultCodes.NORMAL;
	}
	
	public CreateAppBlueCollarReq getValReq() {
		return valReq;
	}

	public void setValReq(CreateAppBlueCollarReq valReq) {
		this.valReq = valReq;
	}
}
