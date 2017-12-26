package com.hengyuan.hicash.domain.service.user;

import java.util.ArrayList;
import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.param.AreaQuery;
import com.hengyuan.hicash.domain.query.param.BankQuery;
import com.hengyuan.hicash.domain.query.param.CityQuery;
import com.hengyuan.hicash.domain.query.param.ProvinceQuery;
import com.hengyuan.hicash.domain.query.param.SysBankInfoQuery;
import com.hengyuan.hicash.domain.query.user.CollectAccountQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.AreaEntity;
import com.hengyuan.hicash.entity.param.BankBranchEntity;
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.entity.user.CollectAccountEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.SearchBankCardReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.SearchBankCardListResp;
import com.hengyuan.hicash.parameters.response.user.SearchBankCardResp;
import com.hengyuan.hicash.utils.ResourceUtils;


/**
 * 查询银行卡信息  业务处理
 * 
 * @author Cary.Liu
 * @create 2014-09-29
 *
 */
public class SearchBankCardService implements RespService {

	private String resultCode = "";
	private SearchBankCardResp cardResp = new SearchBankCardResp();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		SearchBankCardReq cardReq = (SearchBankCardReq)parmRequest;
		
		try {
			
			if(cardExistFlag(cardReq)){
				cardResp.setSignUrl(ResourceUtils.getValue("sign_url"));
				resultCode = ResultCodes.NORMAL;
				
			}else{
				resultCode = ResultCodes.SEARCHBANKCARD_CARD_NOEXIST;
			}
			
		} catch (Exception e) {
			resultCode = ResultCodes.SEARCHBANKCARD_EXCEPTION;
		} finally {
			ConnManager.closeConn();
		}
		
		cardResp.setResultCode(resultCode);
		return cardResp;
	}
	
	/**
	 * 根据用户名和银行卡主键查询卡信息
	 * @param cardReq
	 * @return
	 */
	public List<SearchBankCardListResp> responseCardRespList (SearchBankCardReq cardReq){
		CollectAccountQuery accountQuery = new CollectAccountQuery();
		
		List<CollectAccountEntity> list = accountQuery.queryCardList(cardReq.getUserName());
		
		List<SearchBankCardListResp> listResp  = new ArrayList<SearchBankCardListResp>();
		if(list != null){
			for(CollectAccountEntity entity:list){
				SearchBankCardListResp cardResps = new SearchBankCardListResp();
				cardResps.setSignUrl(ResourceUtils.getValue("sign_url"));
				cardResps.setRealName(entity.getRealName());
				cardResps.setBank(entity.getBank());
				cardResps.setCardNum(entity.getCardNum());
				cardResps.setCardType(entity.getCardType());
				cardResps.setBankName(entity.getBankName());
				cardResps.setCardId(entity.getCardId());
				cardResps.setCity(entity.getCity());
				cardResps.setDefaultCard(entity.getDefaultCard());
				cardResps.setOpenBank(entity.getOpenBank());
				cardResps.setProvince(entity.getProvince());
				if("".equals(entity.getArea())||entity.getArea()==null){
					cardResps.setArea("");
				}else{
					cardResps.setArea(entity.getArea());
				}
				//获取开户行
				BankQuery bankQuery = new BankQuery();
				BankEntity bankEntity = bankQuery.queryBankName(entity.getBank());
				if(bankEntity != null){
					cardResps.setBankNo(bankEntity.getBankNo());
					SysBankInfoQuery bankInfoQuery = new SysBankInfoQuery();
					cardResps.setBankName(bankEntity.getBankName());
					//获取开户支行
					BankBranchEntity openBankInfo = bankInfoQuery.queryBankBranch(bankEntity.getBankNo(), entity.getOpenBank());
					if(openBankInfo != null){
						cardResps.setBankNo(openBankInfo.getBankNo());
						cardResps.setOpenBankName(openBankInfo.getBankName());
					}else{
						cardResps.setOpenBankName(entity.getOpenBankName());
					}
				}else{
					cardResps.setBankName("");
					cardResps.setBankNo("");
				}
				cardResps = queryParamList(cardResps);
				listResp.add(cardResps);
			}
		}
		return listResp;
	}
	

	/**
	 * 根据用户名和银行卡主键查询卡信息
	 * @param cardReq
	 * @return
	 */
	public boolean cardExistFlag (SearchBankCardReq cardReq){
		
		CollectAccountQuery accountQuery = new CollectAccountQuery();
		CollectAccountEntity entity = accountQuery.queryCardByDefault(cardReq.getUserName());
		
		if(entity != null){
			
			cardResp.setRealName(entity.getRealName());
			cardResp.setBank(entity.getBank());
			cardResp.setCardNum(entity.getCardNum());
			cardResp.setCardType(entity.getCardType());
			
			cardResp.setBankName(entity.getBankName());
			cardResp.setCardId(entity.getCardId());
			cardResp.setCity(entity.getCity());
			cardResp.setDefaultCard(entity.getDefaultCard());
			cardResp.setOpenBank(entity.getOpenBank());
			cardResp.setProvince(entity.getProvince());
			if("".equals(entity.getArea())||entity.getArea()==null){
				cardResp.setArea("");
			}else{
				cardResp.setArea(entity.getArea());
			}
			
			
//			for (Bank b : Bank.values()) {
//				if(b.name().equals(entity.getBank())){
//					cardResp.setBankName(b.getDispValue());
//					break;
//				}
//			}
			//获取开户行
			BankQuery bankQuery = new BankQuery();
			BankEntity bankEntity = bankQuery.queryBankName(entity.getBank());
			if(bankEntity != null){
				cardResp.setBankNo(bankEntity.getBankNo());
				SysBankInfoQuery bankInfoQuery = new SysBankInfoQuery();
				cardResp.setBankName(bankEntity.getBankName());
				//获取开户支行
				BankBranchEntity openBankInfo = bankInfoQuery.queryBankBranch(bankEntity.getBankNo(), entity.getOpenBank());
				if(openBankInfo != null){
					cardResp.setBankNo(openBankInfo.getBankNo());
					cardResp.setOpenBankName(openBankInfo.getBankName());
				}else{
					cardResp.setOpenBankName(entity.getOpenBank());
				}
			}else{
				cardResp.setBankName("");
				cardResp.setBankNo("");
			}
			cardResp = queryParam(cardResp);
			return true;
		}
		return false;
	}
	
	public SearchBankCardResp queryParam(SearchBankCardResp resp){
		ProvinceQuery provinceQuery = new ProvinceQuery();
		CityQuery cityQuery = new CityQuery();
		AreaQuery areaQuery = new AreaQuery();
		resp.setProvinceName(provinceQuery.queryProvince(resp.getProvince()).getProvName());
		resp.setCityName(cityQuery.queryCity(resp.getCity()).getCityName());
		if("".equals(resp.getArea())||resp.getArea()==null){
			resp.setAreaName("");
		}else{
			AreaEntity are=areaQuery.queryAreaByCityCode(resp.getArea());
			if(are!=null){
				resp.setAreaName(are.getAreaName());
			}else{
				resp.setAreaName("");
			}
		}
		
		
		return resp;
	}
	
	public SearchBankCardListResp queryParamList(SearchBankCardListResp resp){
		ProvinceQuery provinceQuery = new ProvinceQuery();
		CityQuery cityQuery = new CityQuery();
		AreaQuery areaQuery = new AreaQuery();
		resp.setProvinceName(provinceQuery.queryProvince(resp.getProvince()).getProvName());
		resp.setCityName(cityQuery.queryCity(resp.getCity()).getCityName());
		if("".equals(resp.getArea())||resp.getArea()==null){
			resp.setAreaName("");
		}else{
			AreaEntity are=areaQuery.queryAreaByCityCode(resp.getArea());
			if(are!=null){
				resp.setAreaName(are.getAreaName());
			}else{
				resp.setAreaName("");
			}
		}
		
		
		return resp;
	}
	
}
