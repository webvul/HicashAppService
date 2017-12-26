package com.hengyuan.hicash.domain.service.user;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.Policy;
import com.hengyuan.hicash.exception.TongDunValRulesException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.TongDunValRulesReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.TongDunValRulesResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 同盾接口验证客户黑名单，客户身份证号码、手机号码、邮箱、qq
 * 
 * @author lihua.Ren
 * @create date 2015-11-10
 *
 */
public class TongDunValRulesService implements RespService {

	private static Logger logger = Logger
			.getLogger(TongDunValRulesService.class);
	/**
	 * 需要签名字段字段
	 */
	static final String[] jsonKey = new String[] {
			"partner_code",// 此处值填写您的合作方标识
			"secret_key",// 此处填写对应app密钥
			"event_id",// 此处填写策略集上的事件标识
			"token_id",// 此处填写设备指纹服务的回话标识，和部署设备脚本的token一致
//			"account_login",// 以下填写其他要传的参数，比如系统字段，扩展字段
			"ip_address", "account_email", "id_number", "account_mobile",
			"account_name" };

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		TongDunValRulesReq req = (TongDunValRulesReq) parmRequest;
		TongDunValRulesResp resp = null;
		Map<String, String> params = new HashMap<String, String>();

		params.put("partner_code", "hicash");// 此处值填写您的合作方标识
		params.put("secret_key", "64f477a6c1464cb4b74d231ba1fd681e");// 此处填写对应app密钥
		params.put("event_id", "loan_web");// 此处填写策略集上的事件标识
		params.put("token_id", req.getToken_id());// 此处填写设备指纹服务的回话标识，和部署设备脚本的token一致
//		params.put("account_login", "abel.zhang@hengyuan-finance.com");// 以下填写其他要传的参数，比如系统字段，扩展字段
		params.put("ip_address", req.getIp_address());
		params.put("account_email", req.getAccount_email());
		params.put("id_number", req.getId_number());
		params.put("account_mobile", req.getAccount_mobile());

		params.put("account_name", req.getAccount_name());

		try {
			logger.info("uuid为：" + req.getUuid() + "的同盾请求参数为："
					+ getScrabbleContent(params, jsonKey));
			resp = CebankReq(getScrabbleContent(params, jsonKey));
			if (resp.isSuccess() == true) {
				// 结果有三种：Accept无风险，通过；Review低风险，审查；Reject高风险，拒绝
				// 最终的风险系数。权重模式下是策略中所有命中规则分数相加，首次匹配和最坏匹配时则由其中一条
				// 规则决定，最后取策略集下所有策略中分数最高的作为最终风险系数，该系数越高说明风险越大
				//

				if (resp.getFinal_decision().equals("Reject")) {

					resp.setResultCode(ResultCodes.TONGDUN_VALRULES_SCORE_HIGH);
				} else {
					// Accept无风险，通过；Review低风险，不加入黑名单
					resp.setResultCode(ResultCodes.NORMAL);

				}
			} else {
				resp.setResultCode(ResultCodes.TONGDUN_VALRULES_FALSE);
			}

		} catch (TongDunValRulesException e) {
			resp = new TongDunValRulesResp();
			resp.setResultCode(ResultCodes.TONGDUN_VALRULES_EXCEPTION);
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(),
					ResultCodes.TONGDUN_VALRULES_EXCEPTION, e);
		} catch (Exception e) {
			resp = new TongDunValRulesResp();
			resp.setResultCode(ResultCodes.TONGDUN_VALRULES_SELF_EXCEPTION);

			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(),
					ResultCodes.TONGDUN_VALRULES_SELF_EXCEPTION, e);
		} 
		
		return resp;
	}

	// 返回值：银行接口返回的 XML String
	private static TongDunValRulesResp CebankReq(String reqData)
			throws TongDunValRulesException {
		String retMsg = "";
//		String url = "https://apitest.fraudmetrix.cn/riskService";
		 String url="https://api.fraudmetrix.cn/riskService";

		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		try {
			ByteArrayEntity be = new ByteArrayEntity(reqData.getBytes("UTF-8"));
			be.setContentType("application/x-www-form-urlencoded");
			be.setContentEncoding("UTF-8");
			post.setEntity(be);
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				InputStream is = res.getEntity().getContent();
				retMsg = inStream2String(is);
			} else {
				throw new TongDunValRulesException();

			}
		} catch (Exception e) {

			throw new TongDunValRulesException();

		}
		System.out.println("retMsg:" + retMsg);
		return JSON.parseObject(retMsg.toString().trim(),
				TongDunValRulesResp.class);
	}

	// 将输入流转换成字符串
	public static String inStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = is.read(buf)) != -1) {
			baos.write(buf, 0, len);
		}
		return new String(baos.toByteArray(), "UTF-8");
	}

	/**
	 * 
	 * 
	 * @param values
	 *            所有参数
	 * @param keys
	 *            参与校验的顺序相关的关键字组
	 * @return 拼凑好的加密明文
	 * @throws UnsupportedEncodingException
	 */
	protected static String getScrabbleContent(Map<String, String> values,
			String[] keys) throws UnsupportedEncodingException {
		String content = "";
		for (int i = 0; i < keys.length; i++) {
			if (StringUtils.isEmpty((String) values.get(keys[i]))) {
				continue;
			}

			content += keys[i] + "=" + values.get(keys[i]) + "&";
		}
		if (content.length() > 0) {
			content = content.substring(0, content.length() - 1);
		}

		return content;
	}

	//
	//
	// private static final String apiUrl =
	// "https://apitest.fraudmetrix.cn/riskService";
	//
	//
	//
	// public static FraudApiResponse invoke(Map<String, Object> params) {
	// HttpURLConnection conn;
	// try {
	// URL url = new URL(apiUrl);
	// // 组织请求参数
	// StringBuilder postBody = new StringBuilder();
	// for (Map.Entry<String, Object> entry : params.entrySet()) {
	// if (entry.getValue() == null) continue;
	// postBody.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(),
	// "utf-8")).append("&");
	// }
	//
	// if (!params.isEmpty()) {
	// postBody.deleteCharAt(postBody.length() - 1);
	// }
	//
	// conn = (HttpURLConnection) url.openConnection();
	// // 设置长链接
	// conn.setRequestProperty("Connection", "Keep-Alive");
	// // 设置连接超时
	// conn.setConnectTimeout(1000);
	// // 设置读取超时
	// conn.setReadTimeout(500);
	// // 提交参数
	// conn.setRequestMethod("POST");
	// conn.setDoOutput(true);
	// conn.getOutputStream().write(postBody.toString().getBytes());
	// conn.getOutputStream().flush();
	// int responseCode = conn.getResponseCode();
	// if (responseCode != 200) {
	// logger.warn("[FraudApiInvoker] invoke failed, response status:" +
	// responseCode);
	// return null;
	// }
	// BufferedReader bufferedReader = new BufferedReader(new
	// InputStreamReader(conn.getInputStream(), "utf-8"));
	// StringBuilder result = new StringBuilder();
	// String line;
	// while ((line = bufferedReader.readLine()) != null) {
	// result.append(line).append("\n");
	// }
	// return JSON.parseObject(result.toString().trim(),
	// FraudApiResponse.class);
	// } catch (Exception e) {
	// logger.error("[FraudApiInvoker] invoke throw exception, details: " + e);
	// }
	// return null;
	// }
	//
	// public static void main(String[] args) throws
	// UnsupportedEncodingException, TongDunValRulesException {
	// Map<String, String> params = new HashMap<String, String>();
	// params.put("partner_code", "hicash");// 此处值填写您的合作方标识
	// params.put("secret_key", "fa485761aa4e4d23ab8e9c58380fed27");//
	// 此处填写对应app密钥
	// params.put("event_id", "register_web");// 此处填写策略集上的事件标识
	// params.put("token_id", "abdfadsfadafdadsfadfs");//
	// 此处填写设备指纹服务的回话标识，和部署设备脚本的token一致
	// params.put("account_login", "abel.zhang@hengyuan-finance.com");//
	// 以下填写其他要传的参数，比如系统字段，扩展字段
	// params.put("ip_address", "192.168.0.63");
	// params.put("account_email", "1736@qq.com");
	// params.put("id_number", "411422199111130640");
	// params.put("account_mobile", "13004189687");
	// params.put("qq", "2624521990");
	// params.put("account_name", "任利华");
	// FraudApiResponse apiResp =
	// TongDunValRulesService.CebankReq(getScrabbleContent(params, jsonKey));
	// System.out.println(apiResp.toJson());
	// System.out.println(apiResp.isSuccess());
	// System.out.println(apiResp.getReason_code());
	// }

}
