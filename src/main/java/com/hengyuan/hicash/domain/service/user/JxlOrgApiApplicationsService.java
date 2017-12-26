package com.hengyuan.hicash.domain.service.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.JxlCreditReq;
import com.hengyuan.hicash.parameters.request.user.JxlOrgApiApplicationsReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.JxlOrgApiApplicationsResp;
import com.hengyuan.hicash.utils.HttpRemotePost;
import com.hengyuan.hicash.utils.ResourceUtils;

/** 
 * @author blanke.qin
 * 2017年1月11日 下午6:13:41
 * 类说明 
 */
public class JxlOrgApiApplicationsService  implements RespService{
	
	private static Logger logger = Logger.getLogger(JxlOrgApiApplicationsService.class);
	
	private CustcustomerQuery custcustomerQuery=new  CustcustomerQuery();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		  
		 JxlOrgApiApplicationsResp infoResp=new JxlOrgApiApplicationsResp();
		 JxlOrgApiApplicationsReq  infoReq=(JxlOrgApiApplicationsReq)parmRequest;
		 JxlCreditReq  creditReq = new   JxlCreditReq();
		 String args = "";
		 
		 if("SJZL".equals(infoReq.getIndustryCode())){
			 
			 args = outServerToArgs(infoReq);
			 creditReq.setArgs(args);
			 creditReq.setMobile(infoReq.getMobile());
			 
		 }else{
		 
			 CustCustomer custCustomer=new CustCustomer();
			 custCustomer =custcustomerQuery.queryCustCustomer(infoReq.getUsername());
			 if(custCustomer!=null){
				 //组装请求参数
				args=Show(custCustomer);
				creditReq.setArgs(args);
				creditReq.setMobile(custCustomer.getMobileNo());
			 }	
		 }
		 
		creditReq.setCurrentMsgType(infoReq.getCurrentMsgType());	 
		creditReq.setPassword(infoReq.getPassword());
		creditReq.setSeq_no(infoReq.getSeq_no());
		creditReq.setWebsite(infoReq.getWebsite());
		creditReq.setTime(infoReq.getTime());
		creditReq.setMessage(infoReq.getMessage());
		 
		HashMap<String, String> parmMap = new HashMap<String, String>();
		parmMap.put("args", args);
		parmMap.put("currentMsgType",creditReq.getCurrentMsgType());
		parmMap.put("message", creditReq.getMessage());
		parmMap.put("password", creditReq.getPassword());
		parmMap.put("seq_no", creditReq.getSeq_no());
		parmMap.put("website", creditReq.getWebsite());
		parmMap.put("time", creditReq.getTime());
		parmMap.put("mobile", creditReq.getMobile());
			 
		String str = null;
		logger.info("开始调用运营商API认证"+creditReq.getMobile());
			
		/*运营商  jxl,pdl**/
//		String operator =  getOperator(creditReq.getMobile());
		try {
//				if("jxl".equals(operator)){
//					System.out.println(creditReq.getMobile()+"进入聚信立运营商------");
//					if("1".equals(infoReq.getTime())){
//		            	str= HttpRemotePost.sendNoTimeHttp(ResourceUtils.getValue(Consts.JXL_APP_URL),parmMap);
//		            }else{	
//		            	str= HttpRemotePost.sendNoTimeHttp(ResourceUtils.getValue(Consts.JXL_AUTH_URL),parmMap);
//					}
//				}else{	
					System.out.println(creditReq.getMobile()+"进入磐多拉运营商------");
					//time为1时调提交运营商，time为2时提交授权
					if("1".equals(infoReq.getTime())){
						str= HttpRemotePost.sendNoTimeHttp(ResourceUtils.getValue(Consts.PDL_APP_URL),parmMap);
					}else {
						str= HttpRemotePost.sendNoTimeHttp(ResourceUtils.getValue(Consts.PDL_AUTH_URL),parmMap);
					}	
//				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("调运营商API接口异常！用户名："+creditReq.getMobile());
			infoResp.setResultCode("0");
			infoResp.setResultMsg("网络服务异常！运营商API错误！");
		} 
			
		try {
			JSONObject object = JSONObject.parseObject(str);
			
			if(!object.isEmpty()){
				
				infoResp.setResultCode(object.getString("resultCode"));
				infoResp.setResultMsg(object.getString("resultMsg"));
				
				if(object.containsKey("seq_no")){
					infoResp.setSeq_no(object.getString("seq_no"));
				}
				
				if(object.containsKey("website")){
					infoResp.setWebsite(object.getString("website"));
				}
				
			}else{
				infoResp.setResultCode("0");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("运营商认证返回JSON格式错误！手机号="+creditReq.getMobile());
			
			infoResp.setResultCode("0");
		}
			 
		return infoResp;
	}
	
	
	public String Show(CustCustomer custCustomer) {
		String name = custCustomer.getRealName();
		String idcard = custCustomer.getIdentityNo();
		String phone = custCustomer.getMobileNo();
		String unitTel = custCustomer.getUnitTel();
		String unitaddress = custCustomer.getWorkAreaRoad();
		String dormTel = custCustomer.getFixedTel();
		String dormAddress = custCustomer.getNowFimilyAdress();

		// 直系亲属信息
		String fatherName = custCustomer.getImmediateName();
		String fatherPhone = custCustomer.getImmediateMobile();
		String familyRelation = custCustomer.getImmediateRelation();
		String type1 = "";
		if (Consts.RELATION_ZX_01.equals(familyRelation)
				|| Consts.RELATION_ZX_02.equals(familyRelation)) {
			type1 = "1";// 父母
		} else if (Consts.RELATION_ZX_03.equals(familyRelation)) {
			type1 = "3";// 子女
		} else if (Consts.RELATION_ZX_04.equals(familyRelation)) {
			type1 = "0";// 配偶
		}
		
		// 紧急联系人信息
		String relaName = custCustomer.getEmergencyName();
		String relaPhone = custCustomer.getEmergencyMobile();
		String relation = custCustomer.getEmergencyRelation();
		String type2 = "";
		if (Consts.RELATION_JJ_01.equals(relation) || Consts.RELATION_JJ_02.equals(relation)) {
			type2 = "1";// 父母
		} else if (Consts.RELATION_JJ_03.equals(relation)) {
			type2 = "3";// 子女
		} else if (Consts.RELATION_JJ_04.equals(relation)) {
			type2 = "0";// 配偶
		} else if (Consts.RELATION_JJ_05.equals(relation)) {
			type2 = "2";// 兄弟姐妹
		} else if (Consts.RELATION_JJ_06.equals(relation)) {
			type2 = "4";// 同事
		} else if (Consts.RELATION_JJ_07.equals(relation)) {
			type2 = "6";// 朋友
		} else if(Consts.RELATION_JJ_08.equals(relation)){
			type2 = "5";// 同学
		}

		// 亲属朋友
		String kinShipName = null;
		String kinShipPhone = null;
		String kinShipRelation = null;
		String friendName = null;
		String friendPhone = null;
		String friendRelation = null;
		String type3 = "";
		if (Consts.RELATION_JJ_01.equals(kinShipRelation)
				|| Consts.RELATION_JJ_02.equals(kinShipRelation)) {
			type3 = "1";// 父母
		} else if (Consts.RELATION_JJ_03.equals(kinShipRelation)) {
			type3 = "3";// 子女
		} else if (Consts.RELATION_JJ_04.equals(kinShipRelation)) {
			type3 = "0";// 配偶
		} else if (Consts.RELATION_JJ_05.equals(kinShipRelation)) {
			type3 = "2";// 兄弟姐妹
		} else if (Consts.RELATION_JJ_06.equals(kinShipRelation)) {
			type3 = "4";// 同事
		} else if (Consts.RELATION_JJ_07.equals(kinShipRelation)) {
			type3 = "6";// 朋友
		}else if(Consts.RELATION_JJ_08.equals(relation)){
			type3 = "5";// 同学
		}

		String type4 = "";
		if (Consts.RELATION_JJ_01.equals(friendRelation)
				|| Consts.RELATION_JJ_02.equals(friendRelation)) {
			type4 = "1";// 父母
		} else if (Consts.RELATION_JJ_03.equals(friendRelation)) {
			type4 = "3";// 子女
		} else if (Consts.RELATION_JJ_04.equals(friendRelation)) {
			type4 = "0";// 配偶
		} else if (Consts.RELATION_JJ_05.equals(friendRelation)) {
			type4 = "2";// 兄弟姐妹
		} else if (Consts.RELATION_JJ_06.equals(friendRelation)) {
			type4 = "4";// 同事
		} else if (Consts.RELATION_JJ_07.equals(friendRelation)) {
			type4 = "6";// 朋友
		} else if(Consts.RELATION_JJ_08.equals(relation)){
			type4 = "5";// 同学
		}
		
		//distribute_api_token 默认是潘多拉的
		String args= "{\"orgInfo\":{\"distribute_api_token\":\"8a45788fa37d4a4eb69060331cb7966e\",\"customized\":[2]},\"applyInfo\":{\"applicant\":{\"name\":\""
				+ name
				+ "\",\"id_card_num\":\""
				+ idcard
				+ "\",\"cell_phone_num\":\""
				+ phone
				+ "\",\"work_tel\":\""
				+ unitTel
				+ "\",\"work_addr\":\""
				+ unitaddress
				+ "\",\"home_tel\":\""
				+ dormTel
				+ "\",\"home_addr\":\""
				+ dormAddress
				+ "\"},\"contact\":[{\"contact_name\":\""
				+ fatherName
				+ "\",\"contact_tel\":\""
				+ fatherPhone
				+ "\",\"contact_type\":\""
				+ type1
				+ "\"},{\"contact_name\":\""
				+ relaName
				+ "\",\"contact_tel\":\""
				+ relaPhone
				+ "\",\"contact_type\":\""
				+ type2
				+ "\"}]}}"/*,{\"contact_name\":\""
				+ kinShipName
				+ "\",\"contact_tel\":\""
				+ kinShipPhone
				+ "\",\"contact_type\":\""
				+ type3
				+ "\"},{\"contact_name\":\""
				+ friendName
				+ "\",\"contact_tel\":\""
				+ friendPhone
				+ "\",\"contact_type\":\"" + type4 + "\"}]}}"*/;

		logger.info("用户名:" + custCustomer.getRealName() + ",聚信立封装链接地址："
				+ args);
		
		return args;
		
		
	}
	
	public String outServerToArgs(JxlOrgApiApplicationsReq infoReq){
		
		// 直系亲属信息
		String fatherName = infoReq.getFamilyName();
		String fatherPhone = infoReq.getFamilyMobile();
		String familyRelation = infoReq.getFamilyRelation();
		String type1 = "";
		if (Consts.RELATION_ZX_01.equals(familyRelation)
				|| Consts.RELATION_ZX_02.equals(familyRelation)) {
			type1 = "1";// 父母
		} else if (Consts.RELATION_ZX_03.equals(familyRelation)) {
			type1 = "3";// 子女
		} else if (Consts.RELATION_ZX_04.equals(familyRelation)) {
			type1 = "0";// 配偶
		}
		
		// 紧急联系人信息
		String relaName = infoReq.getRelaName();
		String relaPhone = infoReq.getRelaMobile();
		String relation = infoReq.getRelation();
		String type2 = "";
		if (Consts.RELATION_JJ_01.equals(relation) || Consts.RELATION_JJ_02.equals(relation)) {
			type2 = "1";// 父母
		} else if (Consts.RELATION_JJ_03.equals(relation)) {
			type2 = "3";// 子女
		} else if (Consts.RELATION_JJ_04.equals(relation)) {
			type2 = "0";// 配偶
		} else if (Consts.RELATION_JJ_05.equals(relation)) {
			type2 = "2";// 兄弟姐妹
		} else if (Consts.RELATION_JJ_06.equals(relation)) {
			type2 = "4";// 同事
		} else if (Consts.RELATION_JJ_07.equals(relation)) {
			type2 = "6";// 朋友
		} else if(Consts.RELATION_JJ_08.equals(relation)){
			type2 = "5";// 同学
		}
		
		String args= "{\"orgInfo\":{\"distribute_api_token\":\"8a45788fa37d4a4eb69060331cb7966e\",\"customized\":[2]},\"applyInfo\":{\"applicant\":{\"name\":\""
				+ infoReq.getName()
				+ "\",\"id_card_num\":\""
				+ infoReq.getIdCard()
				+ "\",\"cell_phone_num\":\""
				+ infoReq.getMobile()
				+ "\",\"work_tel\":\""
				+ "null"
				+ "\",\"work_addr\":\""
				+ "null"
				+ "\",\"home_tel\":\""
				+ "null"
				+ "\",\"home_addr\":\""
				+ "null"
				+ "\"},\"contact\":[{\"contact_name\":\""
				+ fatherName
				+ "\",\"contact_tel\":\""
				+ fatherPhone
				+ "\",\"contact_type\":\""
				+ type1
				+ "\"},{\"contact_name\":\""
				+ relaName
				+ "\",\"contact_tel\":\""
				+ relaPhone
				+ "\",\"contact_type\":\""
				+ type2
				+ "\"}]}}";

		logger.info("手机:" + infoReq.getMobile() + ",聚信立封装链接地址："
				+ args);
		return args;
	}
	
	public String getOperator(String mobile){
		
		/*运营商  jxl,pdl**/
		String operator = "pdl";
		HashMap<String, String> parmMap = new HashMap<String, String>();
		parmMap.put("mobile", mobile); //手机号
		
		try {
			String str = HttpRemotePost.sendNoTimeHttp("http://118.178.248.104/pandora/mobileHomeCredit.do",parmMap);
			Map<String, Object> map =  JSON.parseObject(str);
			String operatorInfo = (String) map.get("mobileHome");
			
//			//所有联通走潘多拉,2017-2-27新增的移动与电信也走潘多拉
//			String operatorSub = operatorInfo.substring(operatorInfo.length()-2,operatorInfo.length());
//			System.out.println("截取数据是---"+operatorInfo);
//			if("联通".equals(operatorSub) || "移动".equals(operatorSub)){
//				System.out.println("进入磐多拉");
//				operator = "pdl";
//			}else if(ResourceUtils.getValue("pandora_range").indexOf(operatorInfo) > 0){
//				System.out.println("进入磐多拉");
//				operator = "pdl";
//				
//			}else{
//				System.out.println("进入聚信立");
//				operator = "jxl";
//			}
			
//			if(operatorInfo.indexOf("联通") > -1){
//				operator = "jxl";
//			}else{
//				operator = "pdl";
//			}
			
			if(operatorInfo.indexOf("移动") > -1){
				operator = "jxl";
			}else{
				operator = "pdl";
			}
			
		} catch (Exception e) {
			operator = "pdl";
			System.out.println("判断手机运营商接口异常");
		}
		
		return operator;
		
	}
	
	public static void main(String[] args){
		
//		HashMap<String, String> parmMap = new HashMap<String, String>();
//		//parmMap.put("mobile", "18616527371"); //上海联通
//		//parmMap.put("mobile", "15901605632"); //上海移动
//		parmMap.put("mobile", "11111111111"); //乱来
//		
//		/*运营商  jxl,pdl**/
//		String operator;
//		
//		try {
//			String str = HttpRemotePost.sendNoTimeHttp("http://10.139.111.152:8080/pandora/mobileHomeCredit.do",parmMap);
//			Map<String, Object> map =  JSON.parseObject(str);
//			String operatorInfo = (String) map.get("mobileHome");
//			operatorInfo = operatorInfo.substring(operatorInfo.length()-2,operatorInfo.length());
//			System.out.println("截取数据是---"+operatorInfo);
//			if("联通".equals(operatorInfo)){
//				System.out.println("进入磐多拉");
//				operator = "pdl";
//			}else{
//				System.out.println("进入聚信立");
//				operator = "jxl";
//			}
//			
//		} catch (Exception e) {
//			operator = "jxl";
//			System.out.println("判断手机运营商接口异常");
//		}
//		
//		if("jxl".equals(operator)){
//			System.out.println("进入聚信立运营商------");
//		}else{
//			System.out.println("进入磐多拉运营商------");
//		}
		
	}

}
