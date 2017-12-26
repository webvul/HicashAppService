package com.hengyuan.hicash.domain.query.mktApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;

import com.google.gson.Gson;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.domain.query.param.ProductInfoQuery;
import com.hengyuan.hicash.domain.query.user.ApproveUserQuery;
import com.hengyuan.hicash.domain.query.user.AproveBussQuery;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.query.user.ServiceConfigQuery;
import com.hengyuan.hicash.domain.query.user.UniverstyQuery;
import com.hengyuan.hicash.entity.param.ProductInfoEntity;
import com.hengyuan.hicash.entity.user.ApproveUser;
import com.hengyuan.hicash.entity.user.ApproveuserBusiness;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.ServiceConfigEntity;
import com.hengyuan.hicash.entity.user.University;
import com.hengyuan.hicash.exception.ApproveBusinessException;
import com.hengyuan.hicash.exception.ApproveUserNotFound;
import com.hengyuan.hicash.exception.CustomerNotFoundException;
import com.hengyuan.hicash.exception.GenerateFlowNoException;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.exception.ProductNotFoundException;
import com.hengyuan.hicash.exception.QueryFlowNoException;
import com.hengyuan.hicash.exception.UniversityNotFound;
import com.hengyuan.hicash.parameters.request.param.LoanAmtCalReq;
import com.hengyuan.hicash.parameters.response.user.LoanAmtCalResp;
import com.hengyuan.hicash.utils.HttpRemotePost;


/**
 * 创建申请辅助查询类
 * @author Andy.Niu
 * @create 2014-08-06
 */
public class CreateAppPayQuery {
	
	/**
	 * 获取申请流水号
	 * @param parmRequest
	 * @return
	 * @throws QueryFlowNoException 
	 * @throws GenerateFlowNoException 
	 * @author Andy.Niu
	 * @create 2014-08-05
	 */
	public String getAppPayNo(String applyType, String proType) 
			throws GenerateFlowNoException, QueryFlowNoException{
		
		/* 实例化获得最新appPayNo */
		PayFlowNoQuery payFlowNoQuery = new PayFlowNoQuery();
		
		/* 特色分期，获得业务流水号,根据申请件类型及产品类型 */
		String appPayNo = payFlowNoQuery.generateFlowNo(applyType,proType);
		
		return appPayNo;
	}
	
	
	/**
	 * 根据用户名获取Cust对象
	 * @param userName
	 * @return
	 * @throws CustomerNotFoundException 
	 * @author Andy.Niu
	 * @create 2014-08-06
	 */
	public CustCustomer getCustCustomer(String userName) 
			throws CustomerNotFoundException{
		
		/* 实例化客户信息DAO */
		CustcustomerQuery custcustomerQuery = new CustcustomerQuery();
		
		CustCustomer custCustomer = custcustomerQuery.queryCustCustomer(userName);
		
		if(custCustomer == null){
			throw new CustomerNotFoundException();
		}
		
		return custCustomer;
	}
	
	/**
	 * 通过saleCode取得ApproveUser
	 * @param saleCode
	 * @return
	 * @throws ApproveUserNotFound
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	public ApproveUser getApproveUser(String saleCode) throws ApproveUserNotFound{
		
		ApproveUserQuery approveUserQuery = new ApproveUserQuery();
		return approveUserQuery.getApproveUserBySaleCode(saleCode);
		 
	}
	
	
	
	/**
	 * 根据产品ID获取产品对象
	 * @param proId
	 * @return
	 * @throws ProductNotFoundException
	 * @author Andy.Niu
	 * @create 2014-08-06
	 */
	public ProductInfoEntity getProductInfo(String proId) 
			throws ProductNotFoundException{
		ProductInfoQuery infoQuery = new ProductInfoQuery();
		
		ProductInfoEntity productInfo = infoQuery.queryProductInfoById(proId);
		
		if(productInfo == null){
			throw new ProductNotFoundException();
		}
		
		return productInfo;
	}
	
	/**
	 * 根据学校ID获取此学校绑定的销售关系
	 * @param schoolId
	 * @return
	 * @throws ApproveBusinessException
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	public ApproveuserBusiness getApproveuserBusiness(String schoolId) 
			throws ApproveBusinessException{
		
		/* 实例化梦想基金开放城市DAO */
		AproveBussQuery aproveBussQuery = new AproveBussQuery();
		
		// 通过学校ID去查询学校对应业务代表
		return aproveBussQuery.getApproveuserBusinessBySchool(schoolId);
	}
	
	/**
	 * 通过学校ID取得学校信息
	 * @param schoolId
	 * @return
	 * @throws UniversityNotFound
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	public University getUniversityById(String schoolId)  throws UniversityNotFound{
		UniverstyQuery universtyQuery = new UniverstyQuery();
		return universtyQuery.getUniversityById(schoolId);
	}
	
	
	/**
	 * 调用账户服务查询每月支付金额
	 * @param payFirstFeeReq
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 * @author Mary.Luo
	 * @throws HttpReturnException 
	 * @throws HttpUrlRemoteException 
	 * @create 2014-07-31
	 */
	public LoanAmtCalResp remoteHttpAmt(LoanAmtCalReq loanAmt)throws HttpException, IOException, HttpReturnException, HttpUrlRemoteException {

		/* 创建账务服务计算每月还款金额参数 */
		Map<String, String> parmMap = new HashMap<String, String>();
		parmMap.put("tranPrice", loanAmt.getTranPrice());//成交价格
		parmMap.put("firstRate", loanAmt.getFirstRate());//首付比率
		parmMap.put("loanProduct", loanAmt.getLoanProduct());//贷款ID
		parmMap.put("uuid", loanAmt.getUuid());
		
//		parmMap.put("custType", loanAmt.getCustType());//用户类型
//		parmMap.put("cityCode", loanAmt.getCityCode());//城市类型
//		parmMap.put("payType", loanAmt.getPayType());//产品类型
//		parmMap.put("productId", loanAmt.getProductId());//产品ID
//		parmMap.put("merProId", loanAmt.getMerProId());//产品ID

		ServiceConfigQuery configQuery = new ServiceConfigQuery();
		ServiceConfigEntity serviceConfigEntity = configQuery.queryServiceByCode(Consts.SERVICE_PAY_CALCULE_AMT_NEW);

		/* 调用分期计算器 */
//		String httpResp = HttpRemotePost.sendHttp(ResourceUtils.getValue(Consts.REMOTE_CALCULATE_PATH), parmMap);
		String httpResp = HttpRemotePost.sendHttp(serviceConfigEntity.getServiceUrl(), parmMap);
		
		return new Gson().fromJson(httpResp, LoanAmtCalResp.class);
	}
	
	
}
