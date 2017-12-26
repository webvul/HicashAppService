package com.hengyuan.hicash.domain.service.notic;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.domain.query.app.InputAppPayQuery;
import com.hengyuan.hicash.domain.service.remote.RemoteService;
import com.hengyuan.hicash.entity.app.InputAppPay;
import com.hengyuan.hicash.entity.param.NoticeSendParam;
import com.hengyuan.hicash.entity.remote.GetBussAddrResp;
import com.hengyuan.hicash.exception.SendMobileMsgException;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 业务短信发送服务
 * @author Cary.Liu
 * @createDate 2015-12-23
 *
 */
public class NoticeSendService {
	
	private static Logger logger = Logger.getLogger(NoticeSendService.class);

	/** 聚信立短信行业代码集合 */
	private static List<String> jxlModelList = new ArrayList<String>();
	
	/** 非聚信立短信行业代码集合 暂不用*/
	private static List<String> otherModelList = new ArrayList<String>();
	
	private NoticeSendParam param;
	
	public NoticeSendService(NoticeSendParam param){
		this.param = param;
	}
	
	static {
		
		/* 发送聚信立短信模板的行业 */
		jxlModelList.add("SMTE"); // 数码分期-白领
		jxlModelList.add("ELWZ"); // 微整形分期
		jxlModelList.add("SYDK"); // 嗨商贷
		jxlModelList.add("MDCP"); // 嗨秒贷
		jxlModelList.add("MDOH"); // 嗨秒贷老客户
		jxlModelList.add("LLMD"); // 嗨秒贷老客户
		jxlModelList.add("DXSM"); // 电信数码
		jxlModelList.add("ZFFQ"); // 租房分期
		jxlModelList.add("LLSM"); // 蓝领数码
		jxlModelList.add("YZKH"); // 优质行业客户分期
		jxlModelList.add("FCKH"); // 房产客户分期
		jxlModelList.add("GXKH"); // 高薪客户分期

		/* 发送非聚信立短信模板的行业  (暂未用)*/
		otherModelList.add("SMTE"); // 数码分期-学生
		otherModelList.add("DDCH"); // 电动自行车分期
		otherModelList.add("MTCH"); // 摩托车行业
		otherModelList.add("HXSY"); // 婚纱摄影
		otherModelList.add("HQZB"); // 珠宝分期
		otherModelList.add("HQLY"); // 礼仪分期
		otherModelList.add("EJFQ"); // 家电分期
		otherModelList.add("JYPX"); // 培训分期
		otherModelList.add("LFYC"); // 江铃陆风汽车分期
		otherModelList.add("TZZX"); // 投资房装修分期
		otherModelList.add("ZZZX"); // 自住房装修分期
		otherModelList.add("ZZ01"); // 周转哥分期
		
	}
	
	
	/**
	 * 发送申请件申请通知
	 * 根据不同二级行业发送不同短信(模板)
	 * @throws SendMobileMsgException 
	 */
	public void sendAppApplyNotice(){
		
		ExternalService externalService = new ExternalService();
		int noticeModelNum = rtnNoticeModelNum(param.getIndustryCode(), param.getCustType());
		
		/* 1：SQDO申请通知短信1 ，2：SQDT申请通知短信2*/
		if(noticeModelNum == 1){
			
			String jxlUrl = getsmsJxlUrl();
			logger.info("发送聚信立短信验证链接url：" + jxlUrl);
			
			if(jxlUrl != null && !"".equals(jxlUrl)){
				/* 聚信立连接校验短信暂时屏蔽 */
				externalService.sendAppApplyModel1(param.getMobile(), param.getUserName(), param.getRealName(), jxlUrl);
			}
			
		}else if(noticeModelNum == 2){
			
			externalService.sendAppApplyModel2(param.getMobile(), param.getUserName(), param.getRealName());
		}
		
	}
	
	/**
	 * 区分短信模板
	 * 1：SQDO、2：SQDT
	 * @param industryCode
	 * @param custType
	 * @return
	 */
	private int rtnNoticeModelNum(String industryCode, String custType){
		
		if(jxlModelList.contains(industryCode)){
			
			if("SMTE".equals(industryCode) && Consts.CUSTTYPE_KHL1.equals(custType)){
				return 2;
			}
			
			return 1;
		}else if (otherModelList.contains(industryCode)){
			return 2;
		}
		
		return 0;
	}
	
	/**
	 * 获取聚信立验证链接
	 * @return
	 */
	private String getsmsJxlUrl(){
		
		String rtnUrl = "";
		
		try {
			
			InputAppPayQuery inputAppQuery = new InputAppPayQuery();
			InputAppPay inputApp = inputAppQuery.queryInputAppByAppNo(param.getAppNo());
			
			String name = inputApp.getRealName();
			String idcard = inputApp.getIdentityNo();
			String phone = inputApp.getMobileNo();
			String homeAddr = inputApp.getDormAddress();
			String homeTel = inputApp.getDormTelArea()+"-"+inputApp.getDormTel();
			String unitAddr = inputApp.getUnitAddress();
			String unitTel = inputApp.getUnitTel();
			//直系亲属信息
			String fatherName = inputApp.getFatherName();
			String fatherPhone = inputApp.getFatherMobile();
			String familyRelation = inputApp.getFamilyRelation();
			String type1 = "";
			
			if(Consts.RELATION_ZX_01.equals(familyRelation) || Consts.RELATION_ZX_02.equals(familyRelation)){
				type1 = "1";//父母
			}else if(Consts.RELATION_ZX_03.equals(familyRelation)){
				type1 = "3";//子女
			}else if(Consts.RELATION_ZX_04.equals(familyRelation)){
				type1 = "0";//配偶
			}
			//紧急联系人信息
			String relaName = inputApp.getRelaName();
			String relaPhone = inputApp.getRelaMobile();
			String relation = inputApp.getRelation();
			String type2 = "";
			if(Consts.RELATION_JJ_01.equals(relation) || Consts.RELATION_JJ_02.equals(relation)){
				type2 = "1";//父母
			}else if(Consts.RELATION_JJ_03.equals(relation)){
				type2 = "3";//子女
			}else if(Consts.RELATION_JJ_04.equals(relation)){
				type2 = "0";//配偶
			}else if(Consts.RELATION_JJ_05.equals(relation)){
				type2 = "2";//兄弟姐妹
			}else if(Consts.RELATION_JJ_06.equals(relation)){
				type2 = "4";//同事
			}else if(Consts.RELATION_JJ_07.equals(relation)){
				type2 = "6";//朋友
			}
			
			String bussAddr = "https://www.juxinli.com/OrgSpa/#?args={\"orgInfo\":{\"distribute_api_token\":\"8a45788fa37d4a4eb69060331cb7966e\",\"customized\":[2]},\"applyInfo\":{\"applicant\":{\"name\":\""+name+"\",\"id_card_num\":\""+idcard+"\",\"cell_phone_num\":\""+phone+"\",\"home_addr\":\""+homeAddr+"\",\"home_tel\":\""+homeTel+"\",\"work_addr\":\""+unitAddr+"\",\"work_tel\":\""+unitTel+"\"},\"contact\":[{\"contact_name\":\""+fatherName+"\",\"contact_tel\":\""+fatherPhone+"\",\"contact_type\":\""+type1+"\"},{\"contact_name\":\""+relaName+"\",\"contact_tel\":\""+relaPhone+"\",\"contact_type\":\""+type2+"\"}]}}";
			System.out.println("bussAddr="+bussAddr);
			bussAddr = URLEncoder.encode(bussAddr);
			
			String url = ResourceUtils.getValue("GetBussAddr")+"?bussAddr="+bussAddr;
			
			RemoteService remoteService = new RemoteService();
			String resultStr = remoteService.httpPostRemote(url);
			
			GetBussAddrResp resp = new Gson().fromJson(resultStr, GetBussAddrResp.class);
			logger.info("远程获取聚信立链接：【状态："+ resp.getStatus() +"，url：" + resp.getResult() + "】");
			
			if("succ".equals(resp.getStatus())){
				
				rtnUrl = resp.getResult();
				
			}
			
		} catch (Exception e) {
			logger.error("[error:获取聚信立短信链接异常]",e);
			RecordUtils.writeError(logger, null, null, e);
		}
		
		return rtnUrl;
	}
	
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		System.out.println(list.contains("3"));
//		System.out.println(rtnNoticeModelNum("DDCH", "KHL2"));
		
	}
	
}
