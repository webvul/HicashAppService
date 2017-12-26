package com.hengyuan.hicash.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;

/**
 * 
 * @author laughing.Peng
 * @create date 2014-07-22
 * http 请求工具类
 *
 */
public class HttpRemotePost {
  
	/**
	 * 发送httpPost请求
	 * @param ReqUrl
	 * @param map
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 * @throws HttpReturnException 
	 * @throws HttpUrlRemoteException 
	 */
	public static String sendHttp(String reqUrl,Map<String,String> map) 
			throws HttpException, IOException, HttpReturnException, HttpUrlRemoteException{
		
		if(StringUtils.isEmpty(reqUrl)){
			throw new HttpUrlRemoteException();
		}
		
		String str = "";
     		
		/* 构造httpClient实例 */
		HttpClient httpClient = new HttpClient();  
		
		/* 连接超时时间 15 秒 */
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(1000 * 15);
		/* 读取超时时间 15秒 */
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(1000 * 15);
		
		/* 创建post方法实例 */
		PostMethod postMethod = new PostMethod(reqUrl);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,Consts.ENCODING);  
		NameValuePair[] data = resolveMap(map);
		
		// 将表单的值放入postMethod中
		postMethod.setRequestBody(data);
		
		int code = httpClient.executeMethod(postMethod);
		if(code == 200){
			
			System.out.println("提交结果：" + code);
			
		    str = postMethod.getResponseBodyAsString();
			
			System.out.println(str);
		}else{
			/* 测试时候远程服务不存在，屏蔽此异常抛出 */
			throw new HttpReturnException(code);
		}
		
		return str;
		
	}
	
	public static String sendNoTimeHttp(String reqUrl,Map<String,String> map) 
			throws HttpException, IOException, HttpReturnException, HttpUrlRemoteException{
		
		if(StringUtils.isEmpty(reqUrl)){
			throw new HttpUrlRemoteException();
		}
		
		String str = "";
     		
		/* 构造httpClient实例 */
		HttpClient httpClient = new HttpClient();  
		
		/* 创建post方法实例 */
		PostMethod postMethod = new PostMethod(reqUrl);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,Consts.ENCODING);  
		NameValuePair[] data = resolveMap(map);
		
		// 将表单的值放入postMethod中
		postMethod.setRequestBody(data);
		
		int code = httpClient.executeMethod(postMethod);
		if(code == 200){
			
			System.out.println("提交结果：" + code);
			
		    str = postMethod.getResponseBodyAsString();
			
			System.out.println(str);
		}else{
			/* 测试时候远程服务不存在，屏蔽此异常抛出 */
			throw new HttpReturnException(code);
		}
		
		return str;
		
	}
	
	/***
	 * 把map解析成http请求需要的表单的值
	 * @param map
	 * @return
	 */
	private static NameValuePair[] resolveMap(Map<String,String> map){
		
		Object s[] = map.keySet().toArray();
		
	    NameValuePair[] date = new NameValuePair[map.size()];
	    
		for(int i=0;i<map.size();i++){
			
			String key = String.valueOf(s[i]);
			
			String value = map.get(key);
			
			date[i] = new NameValuePair(key,value);
		}
		
		return date;
	}
	
//	public static void main(String[] args) {
//		Map<String,String> map = new HashMap<String, String>();
//		map.put("1", "01");
//		map.put("2", "02");
//		map.put("3", "03");
//		map.put("4", "04");
//		map.put("5", "05");
//		map.put("6", "06");
//		map.put("7", "07");
//		
//		Object s[] = map.keySet().toArray();
//		String[] date = new String[map.size()];
//		for(int i=0;i<map.size();i++){
//			String key = String.valueOf(s[i]) ;
//			String value = map.get(key);
//			date[i] = new String(key);
//			System.out.println(date[i]);
//		}
//		
//		
//	}
	
	
	/**
	 * 发送httpPost请求
	 * 
	 * @param ReqUrl
	 * @param map
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 * @throws HttpReturnException
	 * @throws HttpUrlRemoteException
	 */
	public static String sendHttp2(String reqUrl, Map<String, Object> map)
			throws HttpException, IOException, HttpReturnException,
			HttpUrlRemoteException {

		if (StringUtils.isEmpty(reqUrl)) {
			throw new HttpUrlRemoteException();
		}

		String str = null;

		/* 构造httpClient实例 */
		HttpClient httpClient = new HttpClient();

		/* 创建post方法实例 */
		PostMethod postMethod = new PostMethod(reqUrl);
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
		NameValuePair[] data = resolveMap2(map);

		// 将表单的值放入postMethod中
		postMethod.setRequestBody(data);

		int code = httpClient.executeMethod(postMethod);
		
		if (code == 200) {

			System.out.println("提交结果：" + code);

			str = postMethod.getResponseBodyAsString();

			System.out.println(str);
		} else {
			/* 测试时候远程服务不存在，屏蔽此异常抛出 */
			throw new HttpReturnException(code);
		}

		return str;

	}
	
	/***
	 * 把map解析成http请求需要的表单的值
	 * 
	 * @param map
	 * @return
	 */
	private static NameValuePair[] resolveMap2(Map<String, Object> map) {

		Object s[] = map.keySet().toArray();

		NameValuePair[] date = new NameValuePair[map.size()];

		for (int i = 0; i < map.size(); i++) {

			String key = String.valueOf(s[i]);
			System.out.print(key+"===========");
			String value = String.valueOf(map.get(key));
			System.out.println(value);

			date[i] = new NameValuePair(key, value);
		}

		return date;
	}
	
}
