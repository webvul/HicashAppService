package com.hengyuan.hicash.entity.user;
//解析face++数据，只解析规则使用
public class FaceFirstVo {
	//优先解析result_faceid，没有再解析result_ref1
	private String result_faceid;
	private String result_ref1;
//     private String request_id;
//     private String time_used;
//     private String result_ref1;
//     private String face_genuineness;
//     private String id_exceptions;
	public String getResult_faceid() {
		return result_faceid;
	}

	public void setResult_faceid(String result_faceid) {
		this.result_faceid = result_faceid;
	}

	public String getResult_ref1() {
		return result_ref1;
	}

	public void setResult_ref1(String result_ref1) {
		this.result_ref1 = result_ref1;
	}
	

//	public String getRequest_id() {
//		return request_id;
//	}
//
//	public void setRequest_id(String request_id) {
//		this.request_id = request_id;
//	}
//
//	public String getTime_used() {
//		return time_used;
//	}
//
//	public void setTime_used(String time_used) {
//		this.time_used = time_used;
//	}
//
//	public String getResult_ref1() {
//		return result_ref1;
//	}
//
//	public void setResult_ref1(String result_ref1) {
//		this.result_ref1 = result_ref1;
//	}
//
//	public String getFace_genuineness() {
//		return face_genuineness;
//	}
//
//	public void setFace_genuineness(String face_genuineness) {
//		this.face_genuineness = face_genuineness;
//	}
//
//	public String getId_exceptions() {
//		return id_exceptions;
//	}
//
//	public void setId_exceptions(String id_exceptions) {
//		this.id_exceptions = id_exceptions;
//	}
//	
//	
//	{
//		"id_exceptions": {
//			"id_photo_monochrome": 0,
//			"id_attacked": 0
//		},
//		"face_genuineness": {
//			"mask_confidence": 0.0,
//			"screen_replay_confidence": 0.0,
//			"mask_threshold": 0.5,
//			"synthetic_face_confidence": 0.0,
//			"synthetic_face_threshold": 0.5,
//			"screen_replay_threshold": 0.5,
//			"face_replaced": 0
//		},
//		"result_ref1": {
//			"confidence": 87.059,
//			"thresholds": {
//				"1e-3": 62.169,
//				"1e-5": 74.399,
//				"1e-4": 69.315,
//				"1e-6": 78.038
//			}
//		},
//		"time_used": 1432,
//		"request_id": "1494407648,1598f746-e087-4dc6-90e9-26eabc57add6",
//		"result_faceid": {
//			"confidence": 92.111,
//			"thresholds": {
//				"1e-3": 62.169,
//				"1e-5": 74.399,
//				"1e-4": 69.315,
//				"1e-6": 78.038
//			}
//		}
//	}
	
//非公安
//	{
//		"time_used": 1208,
//		"result_ref1": {
//			"confidence": 88.476,
//			"thresholds": {
//				"1e-3": 62.169,
//				"1e-5": 74.399,
//				"1e-4": 69.315,
//				"1e-6": 78.038
//			}
//		},
//		"face_genuineness": {
//			"mask_confidence": 0.0,
//			"screen_replay_confidence": 0.0,
//			"mask_threshold": 0.5,
//			"synthetic_face_confidence": 0.0,
//			"synthetic_face_threshold": 0.5,
//			"screen_replay_threshold": 0.5,
//			"face_replaced": 0
//		},
//		"request_id": "1496397295,2768765d-9f1c-4f76-816f-a85906481f82"
//	}
}
