package com.hengyuan.hicash.parameters.request.user;

import java.util.HashMap;
import java.util.Map;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
 * @author fish
 *
 * @date 2017年1月10日 下午7:48:07
 */
public class YiDaoMarkReq extends RequestSequence {

	private static final long serialVersionUID = 1L;
	/** 8月迭代，个人头像照，自己获取，需要用户名*/
	private String user_name;
	/** 人脸识别分数，易道，自己用*/
	private String mark;

	/** 高阈值，易道用 ,自己用*/
	private String threshold_high;

	/** 低阈值 ，易道用,自己用*/
	private String threshold_low;
//	/** 姓名 ，老版本传入，新版本根据用户名server查库*/
	private String real_name;
	/** 身份证号，传入：先扫身份证，后扫脸，app判断没啥问题了， 就会更新身份信息。查所以在人脸识别的时候不能直接库，因为还没更新，需要app传 */
	private String id_no;
	/**识别结果，系统内部使用，规则输出,自己用*/
	private String result;

	/**识别机构,YDBS,FACE,YDBSFACE*/
	private String which_part;
	
	
	/**face++原始数据，走face++时，解析face原始数据,自己用*/
	private String face_str;

	//前端传入易道图片1，base64,前端传入参数类型是file
	private String yidao_pic1;
	//前端传入易道图片2,base64,前端传入参数类型是file
	private String yidao_pic2;
	//前端传入face需要的uuid
	private String app_uuid;//app传入的uuid
	//前端传入face需要的参数在配合MegLive SDK使用时，用于校验上传数据的校验字符串，此字符串会由MegLive SDK直接返回。
	private String delta;
	//前段传入用户身份证头像招/自己从服务器下载，face需要的
    private String image_ref1;
	
//face需要的参数
	private String image_best;
	private String image_action1;
	private String image_action2;
	private String image_action3;
	private String image_env;
	private String temp_no;
//	 /**新版本用*/
//     private String app_no;
	//face需要的所有图片,key为参数名称，value为图片地址，自己用
	private  Map<String, String> fileMap=new HashMap<String,String>();
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getThreshold_high() {
		return threshold_high;
	}

	public void setThreshold_high(String threshold_high) {
		this.threshold_high = threshold_high;
	}

	public String getThreshold_low() {
		return threshold_low;
	}

	public void setThreshold_low(String threshold_low) {
		this.threshold_low = threshold_low;
	}

	public String getId_no() {
		return id_no;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getWhich_part() {
		return which_part;
	}

	public void setWhich_part(String which_part) {
		this.which_part = which_part;
	}



	public String getFace_str() {
		return face_str;
	}

	public void setFace_str(String face_str) {
		this.face_str = face_str;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getYidao_pic1() {
		return yidao_pic1;
	}

	public void setYidao_pic1(String yidao_pic1) {
		this.yidao_pic1 = yidao_pic1;
	}

	public String getYidao_pic2() {
		return yidao_pic2;
	}

	public void setYidao_pic2(String yidao_pic2) {
		this.yidao_pic2 = yidao_pic2;
	}

	public String getApp_uuid() {
		return app_uuid;
	}

	public void setApp_uuid(String app_uuid) {
		this.app_uuid = app_uuid;
	}

	public String getDelta() {
		return delta;
	}

	public void setDelta(String delta) {
		this.delta = delta;
	}

	public String getImage_ref1() {
		return image_ref1;
	}

	public void setImage_ref1(String image_ref1) {
		this.image_ref1 = image_ref1;
	}

	public String getImage_best() {
		return image_best;
	}

	public void setImage_best(String image_best) {
		this.image_best = image_best;
	}

	public String getImage_action1() {
		return image_action1;
	}

	public void setImage_action1(String image_action1) {
		this.image_action1 = image_action1;
	}

	public String getImage_action2() {
		return image_action2;
	}

	public void setImage_action2(String image_action2) {
		this.image_action2 = image_action2;
	}

	public String getImage_action3() {
		return image_action3;
	}

	public void setImage_action3(String image_action3) {
		this.image_action3 = image_action3;
	}

	public String getImage_env() {
		return image_env;
	}

	public void setImage_env(String image_env) {
		this.image_env = image_env;
	}
//
//	public String getApp_no() {
//		return app_no;
//	}
//
//	public void setApp_no(String app_no) {
//		this.app_no = app_no;
//	}

	public Map<String, String> getFileMap() {
		return fileMap;
	}

	public void setFileMap(Map<String, String> fileMap) {
		this.fileMap = fileMap;
	}

	public String getTemp_no() {
		return temp_no;
	}

	public void setTemp_no(String temp_no) {
		this.temp_no = temp_no;
	}

	
	
}
