package com.hengyuan.hicash.domain.service.param;

import java.util.ArrayList;
import java.util.List;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.param.CityQuery;
import com.hengyuan.hicash.domain.query.param.IndustryQuery;
import com.hengyuan.hicash.domain.query.param.MerProPicQuery;
import com.hengyuan.hicash.domain.query.param.ProShowDetailQuery;
import com.hengyuan.hicash.domain.query.param.SaleSiteQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.IndustryEntity;
import com.hengyuan.hicash.entity.param.MerProPicEntity;
import com.hengyuan.hicash.entity.param.ProShowEntity;
import com.hengyuan.hicash.entity.param.SaleSiteListEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.param.ProShowDetailReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.param.ProShowDetailResp;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 产品展示详情 业务
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ProShowDetailService implements RespService {

	private String resultCode = "";
	
	private List<SaleSiteListEntity> siteList;
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		ProShowDetailReq proShowReq = (ProShowDetailReq)parmRequest;
		ProShowDetailResp proShowResp = new ProShowDetailResp();
		
		try {
			
			ProShowEntity proShowEntity = queryProShowDetail(proShowReq);
			if(proShowEntity != null){
				proShowResp.setProShowEntity(proShowEntity);
				if(siteList != null && siteList.size() > 0){
					proShowResp.setSiteList(siteList);
				}else{
					proShowResp.setSiteList(new ArrayList<SaleSiteListEntity>());
				}
				/* 商户网店访问url */
				String supplierUrl = ResourceUtils.getValue(Consts.FENQIMALL_URL) + proShowEntity.getSupplierId();
				proShowEntity.setSupplierUrl(supplierUrl);
				resultCode = ResultCodes.NORMAL;
			}else{
				resultCode = ResultCodes.NO_RESULT;
			}
			
		} catch (Exception e) {
			resultCode = ResultCodes.PROSHOWDETAIL_EXCEPTION;
		} finally {
			ConnManager.closeConn();
		}
		
		proShowResp.setResultCode(resultCode);
		return proShowResp;
	}
	
	/**
	 * 获取商品详情
	 * @param proShowReq
	 * @return
	 */
	public ProShowEntity queryProShowDetail(ProShowDetailReq proShowReq){
		
		ProShowEntity proShowEntity = null;
		
		ProShowDetailQuery proShowQuery = new ProShowDetailQuery();
		proShowEntity = proShowQuery.queryProShowList(proShowReq);
		
		if(proShowEntity != null){
			
			IndustryQuery industryQuery = new IndustryQuery();
			MerProPicQuery picQuery = new MerProPicQuery();
			IndustryEntity industry = industryQuery.queryIndustry(proShowEntity.getIndustryCode());
			if(industry!= null){
				proShowEntity.setProTypeName(industry.getIndustryName());
			}
			List<MerProPicEntity> picList = picQuery.queryPicList(proShowEntity.getMerProId());
			proShowEntity.setProPicList(picList);
			proShowEntity.setApplyType(getProApplyType(proShowEntity.getIndustryCode()));
			proShowEntity.setSupplierCityName(queryCityName(proShowEntity.getSupplierCity()));
			/* 查询商户门店 */
			SaleSiteQuery siteQuery = new SaleSiteQuery();
			
			if(Consts.QUERY_SITE_MARK.equals(proShowReq.getQuerySiteMark())){
				siteList = siteQuery.querySiteListByLimit(proShowEntity.getSupplierId());
			}else{
				siteList = siteQuery.querySiteList(proShowEntity.getSupplierId());
			}
		}
		
		return proShowEntity;
	}
	
	/**
	 * 获取商品分期类型
	 * 除周转哥为现金类型CASH，其它都为数码NORMAL
	 * @return
	 */
	private String getProApplyType(String industryCode){
		
		if(Consts.ZZG_INDUSTRY_CODE.equals(industryCode)){
			return Consts.APPFLOW_TYPE_CASH;
		}else{
			return Consts.APPFLOW_TYPE_3C;
		}
	}
	
	public String queryCityName(String cityCode){
		
		CityQuery query = new CityQuery();
		return query.queryCity(cityCode).getCityName();
	}

}
