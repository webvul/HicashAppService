package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 
 * @author fish
 *
 * @date 2017年1月10日 下午8:28:54
 */
public class YiDaoMarkResp extends ParmResponse {

	private boolean is_passed;
	private String savePic;//Y保存图片信息，N不保存图片信息

	public boolean isIs_passed() {
		return is_passed;
	}

	public void setIs_passed(boolean is_passed) {
		this.is_passed = is_passed;
	}

	public String getSavePic() {
		return savePic;
	}

	public void setSavePic(String savePic) {
		this.savePic = savePic;
	}

}
