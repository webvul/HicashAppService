package com.hengyuan.hicash.domain.service.remote;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.domain.query.user.ServiceConfigQuery;
import com.hengyuan.hicash.entity.user.ServiceConfigEntity;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.utils.HttpRemotePost;

/**
 * 远程调用
 * @author Cary.Liu
 * @createDate 2015-06-09
 *
 */
public class RemoteService {

	public String httpRemoteByJson(String url,String jsonStr) throws HttpException, IOException{
		
		HttpClient httpClient = new HttpClient();  
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		
		System.out.println("远程请求地址：" + url + "\r请求json字符串：");
		System.out.println("" + jsonStr + "");
		
		StringRequestEntity strEntity = new StringRequestEntity(jsonStr, "application/json", Consts.ENCODING);
		postMethod.setRequestEntity(strEntity);
		int code = httpClient.executeMethod(postMethod);
		System.out.println("提交结果：" + code);
		
		String str = postMethod.getResponseBodyAsString();
		System.out.println("响应结果：\r" + str);
		
		return str;
	}
	
	/**
	 * http 远程调用
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 * @author Cary.Liu
	 * @throws HttpReturnException 
	 * @throws HttpUrlRemoteException 
	 * @create 2015-08-26
	 */
	public String remoteHttpAmt(String serviceCode,HashMap<String, String> params)throws HttpException, IOException, HttpReturnException, HttpUrlRemoteException {

		ServiceConfigQuery configQuery = new ServiceConfigQuery();
		ServiceConfigEntity serviceConfigEntity = configQuery.queryServiceByCode(serviceCode);

		/* 远程调用 */
		String resultStr = HttpRemotePost.sendHttp(serviceConfigEntity.getServiceUrl(), params);
		
		return resultStr;
	}
	
	public String httpPostRemote(String url) throws HttpException, IOException{
		
		HttpClient httpClient = new HttpClient();  
		GetMethod getMethod = new GetMethod(url);
		getMethod.releaseConnection();
		getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, Consts.ENCODING);
		
		System.out.println("远程请求地址：" + url);
		
		int code = httpClient.executeMethod(getMethod);
		System.out.println("提交结果：" + code);
		
		String str = getMethod.getResponseBodyAsString();
		System.out.println("响应结果字符串：" + str);
		
		return str;
	}
	
}
