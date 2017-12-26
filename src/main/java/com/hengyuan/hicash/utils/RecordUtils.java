package com.hengyuan.hicash.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 请求解析工具类
 * 
 * @author Andy.Niu
 * @create 2014-08-05
 */
public class RecordUtils {

	/**
	 * 取得请求的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 1，记录客户端请求信息
	 * 
	 * @param logger
	 * @param request
	 * @param uuid
	 * @author Andy.Niu
	 * @create 2014-08-05
	 */
	public static void writeRequest(Logger logger, HttpServletRequest request,
			ParmRequest parmRequest) {

		logger.info("\n[时间："+ DateUtils.getDateStr(new Date()) +"]\n请求序列号：" + parmRequest.getUuid() + "\n" + "客户端机器名："
				+ request.getRemoteHost() + "\n" + "客户端IP："
				+ getIpAddress(request) + "\n" + "请求方式：" + request.getMethod()
				+ "\n"+"请求的浏览器:"+request.getHeader("User-agent") +"\n" +"请求使用的协议：" + request.getRequestURL().toString() + "\n"
				+ "请求地址：" + request.getRequestURL().toString() + "\n" + "编码方式："
				+ request.getContentType() + "\n" + "请求序列号："
				+ parmRequest.getUuid() + "\n" + "提交参数：" + parmRequest.toJson());

	}

	/**
	 * 2，服务器参数验证记录
	 * 
	 * @param logger
	 * @param uuId
	 * @param resultCode
	 * @author Andy.Niu
	 * @create 2014-08-05
	 */
	public static void writeValidate(Logger logger, String uuId,
			String resultCode) {
		
		logger.info("\n请求序列号：" + uuId+"\n"+"参数验证结果：" + resultCode);
	}

	/**
	 * 3,执行语句
	 * 
	 * @param logger
	 * @param uuId
	 * @param sql
	 */
	public static void writeAction(Logger logger, String uuId, String sql) {

		logger.info("\n请求序列号：" + uuId+"\n"+"执行语句：" + sql);
	}

	/**
	 * 4,服务器返回信息记录
	 * 
	 * @param logger
	 * @param uuId
	 * @param parmResponse
	 */
	public static void writeResponse(Logger logger, String uuId,
			ParmResponse parmResponse) {

		logger.info("\n请求序列号：" + uuId+"\n"+"服务器返回结果：" + parmResponse.toJson());
	}

	/**
	 * 记录错误信息
	 * 
	 * @param logger
	 * @param uuId
	 * @param resultCode
	 * @param e
	 */
	public static void writeError(Logger logger, String uuId,
			String resultCode, Exception e) {

		logger.error("\n[时间："+ DateUtils.getDateStr(new Date()) +"]\n请求序列号：" + uuId+"\n"+"返回结果代码：" + resultCode+"\n"+"错误详情：", e);

	}

}
