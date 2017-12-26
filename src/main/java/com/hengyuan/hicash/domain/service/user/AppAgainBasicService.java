package com.hengyuan.hicash.domain.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.himoney.AuthenticationInfoUpdateEvent;
import com.hengyuan.hicash.domain.query.amount.JxlCountQuery;
import com.hengyuan.hicash.domain.query.himoney.AuthenticationInfoTableQuery;
import com.hengyuan.hicash.domain.query.param.AreaQuery;
import com.hengyuan.hicash.domain.query.param.CityQuery;
import com.hengyuan.hicash.domain.query.param.ProvinceQuery;
import com.hengyuan.hicash.domain.query.user.AppAgainBasicQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.himoney.AuthenticationInfoUpdateService;
import com.hengyuan.hicash.entity.himoney.AuthenticationInfoEntity;
import com.hengyuan.hicash.entity.user.AppAgainBasicEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.himoney.AuthenticationInfoReq;
import com.hengyuan.hicash.parameters.request.user.AppAgainBasicReq;
import com.hengyuan.hicash.parameters.request.user.AppAgainBasicResp;
import com.hengyuan.hicash.parameters.request.user.IdCardIsValidReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.IdCardIsValidResp;
import com.hengyuan.hicash.parameters.response.user.QueryLocalStuEduResp;
import com.hengyuan.hicash.service.query.AppAgainBasicInfo;
import com.hengyuan.hicash.utils.HRIdentityUtil;
import com.hengyuan.hicash.utils.HttpRemotePost;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class AppAgainBasicService implements RespService {
	private static Logger logger = Logger.getLogger(AppAgainBasicService.class);
	
	private String resultCode = "";
	private AuthenticationInfoUpdateService service=new AuthenticationInfoUpdateService();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		AppAgainBasicReq appAgainBasicReq = (AppAgainBasicReq)parmRequest;
		AppAgainBasicResp appAgainBasicResp = new AppAgainBasicResp();
		AppAgainBasicQuery appAgainBasicQuery = new AppAgainBasicQuery();
		AppAgainBasicEntity appAgainBasicEntity = new AppAgainBasicEntity();
		ProvinceQuery provinceQuery = new ProvinceQuery();
		CityQuery cityQuery = new CityQuery();
		AreaQuery areaQuery = new AreaQuery();
		JxlCountQuery jxlCountQuery = new JxlCountQuery();
		
		try {
			appAgainBasicEntity = appAgainBasicQuery.queryCustInfo(appAgainBasicReq.getAppTempNo());
			if(appAgainBasicEntity == null){
				resultCode = ResultCodes.LOAN_PRO_FAIL;
			}else{
				
				//8 已认证  9认证失败 0需认证 10不强制
				if("8".equals(appAgainBasicEntity.getxXStatus()) || "9".equals(appAgainBasicEntity.getxXStatus())){
					System.out.println(appAgainBasicReq.getAppTempNo()+"该客户已经有做学信网");
				}else{
					
					int age = HRIdentityUtil.getAppUseAge(appAgainBasicEntity.getIdentityNo());
						String url = ResourceUtils.getValue(Consts.xuexin_info);
						Map<String,String> map = new HashMap<String, String>();
						map.put("name", appAgainBasicEntity.getCustName());
						map.put("idCard", appAgainBasicEntity.getIdentityNo());
						try{
							String strTDInfo = HttpRemotePost.sendHttp(url, map);
							Gson gson = new Gson();
							QueryLocalStuEduResp res=gson.fromJson(strTDInfo, QueryLocalStuEduResp.class);
							logger.info("查询学信网返回结果:"+strTDInfo);
							if("KHL1".equals(appAgainBasicEntity.getCustType()) || age < 23 ){
								if("1".equals(res.getResultCode())){
									appAgainBasicEntity.setxXStatus("8");
								}else{
									appAgainBasicEntity.setxXStatus("0");
								}
							}else{
								if("1".equals(res.getResultCode())){
									//解析参数转实体类
									AuthenticationInfoReq re = new AuthenticationInfoReq();
									re.setTempAppNo(appAgainBasicReq.getAppTempNo()); //临时订单号
									re.setUserName(appAgainBasicEntity.getUsername()); //用户名称
									re.setAuthId("1");
									re.setStatus("status03");//认证中
									service.responseValue(re);
								}
								appAgainBasicEntity.setxXStatus("10");
							}
							
						}catch(Exception e){
							e.printStackTrace();
							appAgainBasicEntity.setxXStatus("0");
						}
					
				}
				
				if(!StringUtils.isEmpty(appAgainBasicEntity.getMaritalStatus())){
					appAgainBasicEntity.setMaritalStatus(getHYQKMap().get(appAgainBasicEntity.getMaritalStatus()));
				}
				if(!StringUtils.isEmpty(appAgainBasicEntity.getImmediateRelation())){
					appAgainBasicEntity.setImmediateRelation(getDIREMap().get(appAgainBasicEntity.getImmediateRelation()));
				}
				if(!StringUtils.isEmpty(appAgainBasicEntity.getLiveProvince())){
					appAgainBasicEntity.setLiveProvince(provinceQuery.queryProvince(appAgainBasicEntity.getLiveProvince()).getProvName());
				}
				if(!StringUtils.isEmpty(appAgainBasicEntity.getLiveCity())){
					appAgainBasicEntity.setLiveCity(cityQuery.queryCity(appAgainBasicEntity.getLiveCity()).getCityName());
				}
				if(!StringUtils.isEmpty(appAgainBasicEntity.getLiveArea())){
					appAgainBasicEntity.setLiveArea(areaQuery.queryArea(appAgainBasicEntity.getLiveArea()).getAreaName());
				}
				
				if(!StringUtils.isEmpty(appAgainBasicEntity.getWorkProvince())){
					appAgainBasicEntity.setWorkProvince(provinceQuery.queryProvince(appAgainBasicEntity.getWorkProvince()).getProvName());
				}
				if(!StringUtils.isEmpty(appAgainBasicEntity.getWorkCity())){
					appAgainBasicEntity.setWorkCity(cityQuery.queryCity(appAgainBasicEntity.getWorkCity()).getCityName());
				}
				if(!StringUtils.isEmpty(appAgainBasicEntity.getWorkArea())){
					appAgainBasicEntity.setWorkArea(areaQuery.queryArea(appAgainBasicEntity.getWorkArea()).getAreaName());
				}
				
				//8 已认证  9认证失败 0需认证
				if("8".equals(appAgainBasicEntity.getOperatorStatus())
						|| "9".equals(appAgainBasicEntity.getOperatorStatus())){
					System.out.println(appAgainBasicReq.getAppTempNo()+"该客户已经有做手机运营商");
				}else{
					//聚信立报告三天内有效
					String jxlCount = jxlCountQuery.queryJxlCount(appAgainBasicEntity.getCustName(),
												appAgainBasicEntity.getCustMobileNo(),appAgainBasicEntity.getIdentityNo());
					
					if(Integer.valueOf(jxlCount) > 0){
						appAgainBasicEntity.setOperatorStatus("8");
					}else{
						appAgainBasicEntity.setOperatorStatus("0");
					}
					
				}
				
				//解析参数转实体类
				AuthenticationInfoReq req = new AuthenticationInfoReq();
				
				//查询滴滴报告
				String ddCount=jxlCountQuery.queryddCount(appAgainBasicEntity.getCustName(),
						appAgainBasicEntity.getCustMobileNo(),appAgainBasicEntity.getIdentityNo());
				if(Integer.valueOf(ddCount) > 0){
					appAgainBasicEntity.setDdStatus("8");
					//查询用户是否已经提交过认证项
					req.setTempAppNo(appAgainBasicReq.getAppTempNo()); //临时订单号
					req.setUserName(appAgainBasicEntity.getUsername()); //用户名称
					req.setAuthId("2");
					req.setStatus("status03");//认证中
					service.responseValue(req);
				}else{
					appAgainBasicEntity.setDdStatus("0");
				}
				//查询公积金报告
				String gjjCount=jxlCountQuery.querygjjCount(appAgainBasicEntity.getCustName(),
						appAgainBasicEntity.getCustMobileNo(),appAgainBasicEntity.getIdentityNo());
				if(Integer.valueOf(gjjCount) > 0){
					appAgainBasicEntity.setGjjStatus("8");
					//查询用户是否已经提交过认证项
					req.setTempAppNo(appAgainBasicReq.getAppTempNo()); //临时订单号
					req.setUserName(appAgainBasicEntity.getUsername()); //用户名称
					req.setAuthId("4");
					req.setStatus("status03");//认证中
					service.responseValue(req);
				}else{
					appAgainBasicEntity.setGjjStatus("0");
				}
				//查询外卖报告
				String waimaiCount=jxlCountQuery.querywaimaiCount(appAgainBasicEntity.getCustName(),
						appAgainBasicEntity.getCustMobileNo(),appAgainBasicEntity.getIdentityNo());
				if(Integer.valueOf(waimaiCount) > 0){
					appAgainBasicEntity.setWaimaiStatus("8");
					//查询用户是否已经提交过认证项
					req.setTempAppNo(appAgainBasicReq.getAppTempNo()); //临时订单号
					req.setUserName(appAgainBasicEntity.getUsername()); //用户名称
					req.setAuthId("3");
					req.setStatus("status03");//认证中
					service.responseValue(req);
				}else{
					appAgainBasicEntity.setWaimaiStatus("0");
				}
				
				
				
				//判断身份有效期
				IdCardIsValidReq checkAppReq = new IdCardIsValidReq();
				checkAppReq.setUsername(appAgainBasicEntity.getUsername());
				IdCardIsValidService idCardIsValidService = new IdCardIsValidService();
				IdCardIsValidResp valresp = (IdCardIsValidResp) idCardIsValidService.responseValue(checkAppReq);
				appAgainBasicEntity.setIsValid(valresp.getIsValid());
				appAgainBasicEntity.setUnitIndustryIsNull(valresp.getUnitIndustryIsNull());
				appAgainBasicEntity.setLinkInfoIsPass("Y");//联系人信息默认通过,即不为空
				resultCode = ResultCodes.NORMAL;
			}
		} catch (Exception e) {
			resultCode = ResultCodes.LOAN_PRO_ERRER;
			e.printStackTrace();
		} finally {
			ConnManager.closeConn();
		}
		
		appAgainBasicResp.setAppAgainBasicEntity(appAgainBasicEntity);
		appAgainBasicResp.setResultCode(resultCode);
		return appAgainBasicResp;
	}
	
	public Map<String, String> getHYQKMap(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("Q001", "已婚");
		map.put("Q002", "未婚");
		map.put("Q003", "离异");
		map.put("Q004", "丧偶");
		map.put("Q005", "其他");
		return map;
	}
	
	public Map<String, String> getDIREMap(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("DIR1", "父亲");
		map.put("DIR2", "母亲");
		map.put("DIR3", "子女");
		map.put("DIR4", "配偶");
		return map;
	}
	
	public static void main(String[] args) {
		
		System.out.println(ResourceUtils.getString("500100"));
		
	}

}
