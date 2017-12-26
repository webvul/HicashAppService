package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

public class CheckPersonFaceResp extends ParmResponse {

	/**支持哪个识别机构*/
	private String whichPart;
	private String doGongAn;

	public String getWhichPart() {
		return whichPart;
	}

	public void setWhichPart(String whichPart) {
		this.whichPart = whichPart;
	}

	public String getDoGongAn() {
		return doGongAn;
	}

	public void setDoGongAn(String doGongAn) {
		this.doGongAn = doGongAn;
	}

	
	
	
	


}
