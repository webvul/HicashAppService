package com.hengyuan.hicash.parameters.request.user;



import com.hengyuan.hicash.parameters.request.RequestSequence;

/**  运营商认证
 * @author blanke.qin
 * 2017年1月11日 下午6:24:33
 * 类说明 
 */
public class JxlOrgApiApplicationsReq extends RequestSequence  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1296530493873600098L;
	
	private String username ;//	是  	string 	用户名
	private String time;	//是	string	“1”：提交运营商授权申请，“2”：提交用户授权，“3”：提交短信验证码，“4”用户重新获取短信验证码；
	private String industryCode;//	是	string	行业代码
	private String website;//	否	string	网站英文名称，第一次调接口时可能会返回。如果不为空，从第二次开始调接口时必传
	private String password;  //	当time为2、3、4时必要  	string 	服务密码
	private String message;//	time为3时为必要  	string 	短信验证码
	private String seq_no;  // 	当time为2、3、4时必要  	string 	认证流水token
	private String currentMsgType;//	string	当time为3时必要	下一次需要提
	
	private String mobile;
	private String temp_app_no;//临时单号
	
	private String idCard;//身份证
	private String name;//姓名
	
	private String familyName;//直系亲属姓名
	private String familyRelation;//直系亲属关系
	private String familyMobile;//直系亲属手机号
	private String relaName;//紧急联系人姓名
	private String relation;//紧急联系人关系
	private String relaMobile;//紧急联系人手机号
	
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getFamilyRelation() {
		return familyRelation;
	}
	public void setFamilyRelation(String familyRelation) {
		this.familyRelation = familyRelation;
	}
	public String getFamilyMobile() {
		return familyMobile;
	}
	public void setFamilyMobile(String familyMobile) {
		this.familyMobile = familyMobile;
	}
	public String getRelaName() {
		return relaName;
	}
	public void setRelaName(String relaName) {
		this.relaName = relaName;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getRelaMobile() {
		return relaMobile;
	}
	public void setRelaMobile(String relaMobile) {
		this.relaMobile = relaMobile;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemp_app_no() {
		return temp_app_no;
	}
	public void setTemp_app_no(String temp_app_no) {
		this.temp_app_no = temp_app_no;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}
	public String getCurrentMsgType() {
		return currentMsgType;
	}
	public void setCurrentMsgType(String currentMsgType) {
		this.currentMsgType = currentMsgType;
	}
	
	public static void main(String[] args) {
		JxlOrgApiApplicationsReq j=new JxlOrgApiApplicationsReq();
		j.username="张文豪";
		j.setIndustryCode("LDDD");
		j.setMessage("123456789");
		j.setPassword("112233");
		j.setTime("1");
		j.setWebsite("jxl");
		j.setSeq_no("586348564554");
		
		
		System.out.println(j.toJson());
	}
	
	
	

}
