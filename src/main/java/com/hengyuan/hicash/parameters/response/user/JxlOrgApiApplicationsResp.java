package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**  运营商认证
 * @author blanke.qin
 * 2017年1月11日 下午6:23:49
 * 类说明 
 */
public class JxlOrgApiApplicationsResp extends ParmResponse{
	
	private String seq_no	;//String 	认证流水token
	private String website	;//string	网站英文名称 当time为1时可能会返回，如果返回不为空，则每次都会返回
	
	public String getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	

}
