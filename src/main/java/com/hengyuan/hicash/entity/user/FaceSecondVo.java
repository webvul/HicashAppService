package com.hengyuan.hicash.entity.user;

import java.math.BigDecimal;

public class FaceSecondVo {

	private BigDecimal confidence;
	private String thresholds;
	
	public String getThresholds() {
		return thresholds;
	}
	public void setThresholds(String thresholds) {
		this.thresholds = thresholds;
	}
	public BigDecimal getConfidence() {
		return confidence;
	}
	public void setConfidence(BigDecimal confidence) {
		this.confidence = confidence;
	}
	
//	"result_faceid": {
//		"confidence": 92.111,
//		"thresholds": {
//			"1e-3": 62.169,
//			"1e-5": 74.399,
//			"1e-4": 69.315,
//			"1e-6": 78.038
//		}
//	}
	
//	"result_ref1": {
//		"confidence": 88.476,
//		"thresholds": {
//			"1e-3": 62.169,
//			"1e-5": 74.399,
//			"1e-4": 69.315,
//			"1e-6": 78.038
//		}
//	}
}
