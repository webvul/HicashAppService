package com.hengyuan.hicash.service.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.app.ProductQuery;
import com.hengyuan.hicash.domain.query.app.TempApplyQuery;
import com.hengyuan.hicash.entity.app.LoanProduct;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.parameters.request.order.TempOrderIsExistReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.order.TempOrderIsExistResp;
import com.hengyuan.hicash.service.validate.order.TempOrderIsExistVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 临时订单是否存在
 * 
 * @author yangkun
 *
 */
@WebServlet("/TempOrderIsExist")
public class TempOrderIsExistServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(TempOrderIsExistServlet.class);

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TempOrderIsExistReq tempOrderIsExistReq = new TempOrderIsExistReq();
		tempOrderIsExistReq.setUuid(request.getParameter("uuid"));
		tempOrderIsExistReq.setUsername(request.getParameter("username"));
		tempOrderIsExistReq.setIndustryCode(request.getParameter("industryCode"));
		
		RecordUtils.writeRequest(logger, request, tempOrderIsExistReq);
		TempOrderIsExistVal infoVal = new TempOrderIsExistVal(tempOrderIsExistReq);
		String valResult = infoVal.validate();
		
		if (!ResultCodes.NORMAL.endsWith(valResult)) {

			TempOrderIsExistResp tempOrderIsExistResp = new TempOrderIsExistResp();
			tempOrderIsExistResp.setResultCode(valResult);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(valResult);
			tempOrderIsExistResp.setResultMsg(resuMsg);
			tempOrderIsExistResp.setUuid(tempOrderIsExistReq.getUuid());

			RecordUtils.writeResponse(logger, tempOrderIsExistReq.getUuid(), tempOrderIsExistResp);
			response.getWriter().write(tempOrderIsExistResp.toJson());

		} else {
			TempOrderIsExistResp tempOrderIsExistResp = new TempOrderIsExistResp();
			
			TempApplyQuery tempApplyQuery=new TempApplyQuery();
			tempOrderIsExistResp.setResultCode(ResultCodes.NORMAL);
			tempOrderIsExistResp.setIndustryCode(tempOrderIsExistReq.getIndustryCode());
			
			List<TempApplyEntity> list=tempApplyQuery.queryIsExitTempApply(tempOrderIsExistReq.getUsername(), tempOrderIsExistReq.getIndustryCode());
			
			if(list==null||list.size()<=0){
				tempOrderIsExistResp.setIsExist("0");
			}else{
				tempOrderIsExistResp.setIsExist("1");
				tempOrderIsExistResp.setTempApplicationNo(list.get(0).getTempAppNo());
				tempOrderIsExistResp.setApplyRice(list.get(0).getApplyPrice());
				tempOrderIsExistResp.setLoanProductId(list.get(0).getLoanProduct());
				ProductQuery query = new ProductQuery();
				LoanProduct loanProduct=query.queryCreditProductById(list.get(0).getLoanProduct());
				if(loanProduct!=null){
					tempOrderIsExistResp.setPeriod(loanProduct.getPeriod()+"");
				}
			}
			
			
			ParmResponse parmResponse =tempOrderIsExistResp;
			parmResponse.setUuid(tempOrderIsExistReq.getUuid());
			
			RecordUtils.writeResponse(logger, tempOrderIsExistReq.getUuid(), parmResponse);
			response.getWriter().write(parmResponse.toJson());
		}

	}
}
