package com.hengyuan.hicash.entity.app;
/** 
 * @author blianke.qin
 * 2017年1月10日 下午1:59:27
 * 类说明     规则批量
 */
public class RuleOutEngine {
	
	private  Integer id;
	//申请单号
	private  String appno;
	//一级原因码
	private  String reasononegrade;
	//二级原因吗
	private  String reasononegrade2;
	//审核结果
	private String facresult;
	//提醒信息
	private String alert;
	//获取评分
	private String a_score_card;
	//创建时间
	private String create_time;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAppno() {
		return appno;
	}
	public void setAppno(String appno) {
		this.appno = appno;
	}
	public String getReasononegrade() {
		return reasononegrade;
	}
	public void setReasononegrade(String reasononegrade) {
		this.reasononegrade = reasononegrade;
	}
	public String getReasononegrade2() {
		return reasononegrade2;
	}
	public void setReasononegrade2(String reasononegrade2) {
		this.reasononegrade2 = reasononegrade2;
	}
	public String getFacresult() {
		return facresult;
	}
	public void setFacresult(String facresult) {
		this.facresult = facresult;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getA_score_card() {
		return a_score_card;
	}
	public void setA_score_card(String a_score_card) {
		this.a_score_card = a_score_card;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	
	
	
	
	
	

}
