package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.PageMessageReq;
import com.hengyuan.hicash.parameters.request.user.RestServerPwdReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 
 * @author blanke.qin 2017年4月6日 下午12:31:16 类说明
 */
public class PageMessageVal extends RegexValidate {

	private PageMessageReq pageMessageReq;

	public PageMessageVal(PageMessageReq pageMessageReq) {
		this.pageMessageReq = pageMessageReq;
	}

	public String validate() {

		if (RegexValidate.isNull(pageMessageReq.getPage_code())) {
			return ResultCodes.DK_INFO_BYAPP_NOTFOUND;
		}
       /* if("A004".equals(pageMessageReq.getPage_code())){
        	if (RegexValidate.isNull(pageMessageReq.getIs_xx())) {
    			return ResultCodes.DK_INFO_BYAPP_NOTFOUND;
    		}
        	if (RegexValidate.isNull(pageMessageReq.getIs_zm())) {
    			return ResultCodes.DK_INFO_BYAPP_NOTFOUND;
    		}
        }*/
		
		return ResultCodes.NORMAL;
	}
	
	public String validate2() {

		if (RegexValidate.isNull(pageMessageReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		return ResultCodes.NORMAL;
	}

	public PageMessageReq getPageMessageReq() {
		return pageMessageReq;
	}

	public void setPageMessageReq(PageMessageReq pageMessageReq) {
		this.pageMessageReq = pageMessageReq;
	}

}
