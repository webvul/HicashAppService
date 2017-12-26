package com.hengyuan.hicash.domain.service.user;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.user.YiDaoMarkEvent;
import com.hengyuan.hicash.domain.query.mktApp.TempCreditQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.mktApp.TempCredit;
import com.hengyuan.hicash.entity.user.FaceFirstVo;
import com.hengyuan.hicash.entity.user.FaceOriginalDetailEntity;
import com.hengyuan.hicash.entity.user.FaceSecondVo;
import com.hengyuan.hicash.entity.user.HyScoreRecord;
import com.hengyuan.hicash.exception.SaveScoreResultException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.YiDaoApiResp;
import com.hengyuan.hicash.parameters.request.user.YiDaoMarkReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.YiDaoMarkResp;
import com.hengyuan.hicash.service.query.FaceEngineService;
import com.hengyuan.hicash.service.query.RuleExperian;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.HttpRemotePost;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

import sun.misc.BASE64Encoder;
/**
 * //司机贷只走face++,face++验证成功的，保存图片信息，易道失败保存图片信息
 * 
 * @author Hengyuan
 *
 */
public class YiDaoMarkService implements RespService {

	private static Logger logger = Logger.getLogger(YiDaoMarkService.class);
	static BASE64Encoder encoder = new BASE64Encoder();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		YiDaoMarkReq req = (YiDaoMarkReq) parmRequest;
		YiDaoMarkResp resp = new YiDaoMarkResp();

		// 人脸识别结果：默认不通过
		boolean is_passed = false;
		// 系统正常
		resp.setResultCode(ResultCodes.NORMAL);
		resp.setSavePic("Y");// 是否保存人脸照片，默认保存
	
        String userName=req.getUser_name();
		// 根据传入参数判断，走哪个人脸识别机构
		// 1:易道博识
		if (("YDBS".equals(req.getWhich_part()) || RegexValidate.isNull(req.getWhich_part()))) {// 如果是空走易道

			String result = "N";// 默认不通过
			req.setMark("0");
			req.setThreshold_high("0");
			req.setThreshold_low("0");

			try {
				// 调用易道Api接口
				YiDaoApiResp yidaoResp = sendYiDao(req);
				// 获取易道Api返回处理码（自己已处理）
				String yidaoErrorCode = yidaoResp.getError();
				if ("0".equals(yidaoErrorCode)) {// 易道接口正常
					// 人脸识别规则引擎所需参数
					Map<String, Object> ruleReqMap = new HashMap<String, Object>();
					ruleReqMap.put("score", yidaoResp.getScore());
					ruleReqMap.put("threshold_high", yidaoResp.getTh_high());
					ruleReqMap.put("threshold_low", yidaoResp.getTh_low());
					// 规则引擎输入参数
					logger.info("易道用户:"+userName + ",进入规则引擎参数:" + JSON.toJSONString(ruleReqMap));
					Map<String, Object> ruleRespMap = RuleExperian.doRule(ruleReqMap);
					logger.info("易道用户:"+userName+",规则引擎返回结果：" + JSON.toJSONString(ruleRespMap));
					result = (String) ruleRespMap.get("result");
					if (result != null) {
						req.setResult(result);
						req.setMark(yidaoResp.getScore());
						req.setThreshold_high(yidaoResp.getTh_high());
						req.setThreshold_low(yidaoResp.getTh_low());

						if (result.equals("Y")) {
							is_passed = true;
						}

						resp.setIs_passed(is_passed);

					} else {
						logger.info("易道用户:"+userName + "规则返回结果为空：");
						// resp.setResultCode(ResultCodes.YIDAO_MARK_ERROR);
					}
				} else {
					logger.info("易道用户:"+userName + "人脸识别返回error：" + yidaoErrorCode + "," + getErrorMsg(yidaoErrorCode));
					resp.setResultCode(ResultCodes.YIDAO_MARK_ERROR);
				}
			} catch (Exception e) {

				logger.error("易道用户:"+userName + "人脸识别异常", e);
				// resp.setResultCode(ResultCodes.YIDAO_MARK_ERROR);
			}
			if (!is_passed) {// 易道失败才保存图片
				//TODO,易道失败，不保存/因为ios有问题，server修改
				 
				resp.setSavePic("N");
			} else {
				resp.setSavePic("N");
			}
			// 保存验证记录
			try {
				saveScoreResult(req);
			} catch (SaveScoreResultException e) {
				logger.error("易道用户:"+userName + "保存验证结果异常", e);
			}
		} else if ("FACE".equals(req.getWhich_part())) {// 2： face++
			resp = faceEngine(req, resp);
		} else {

			logger.info("易道用户"+userName + ":人脸识别机构错误");
			// resp.setResultCode(ResultCodes.YIDAO_MARK_ERROR);
		}

		return resp;
	}

	/**
	 * 解析face结果给规则引擎
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	public YiDaoMarkResp faceEngine(YiDaoMarkReq req, YiDaoMarkResp resp) {
		// TODO 测试用，写死
		// String ori11 = "{\"result_faceid\": {\"confidence\":
		// 92.111,\"thresholds\": {\"1e-3\": 62.169,\"1e-5\": 74.399,\"1e-4\":
		// 69.315,\"1e-6\": 78.038}}}";
		// req.setFace_str(ori11);
       String userName=req.getUser_name();
		req.setFace_str(sendFace(req));

		// 默认人脸识别不通过
		boolean is_passed = false;

		BigDecimal confidenceGa = BigDecimal.ZERO;
		BigDecimal confidenceGaNo = BigDecimal.ZERO;
		BigDecimal e3 = BigDecimal.ZERO;
		BigDecimal e4 = BigDecimal.ZERO;
		BigDecimal e5 = BigDecimal.ZERO;
		BigDecimal e6 = BigDecimal.ZERO;

		// face++
		try {

			boolean faceInfoFlag = false;// 默认无face数据返回
			// 获取face原始数据
			if (!StringUtils.isEmpty(req.getFace_str())) {// 返回原始数据不为空
				// 保存face++原始数据
				saveFaceMsg(req);
				String ori = req.getFace_str();
				// 1:-------------------------------------解析返回原始数据，先判断公安，公安为空，解析非公安

				FaceFirstVo firstVo = JSON.parseObject(ori, FaceFirstVo.class);

				if (firstVo != null) {// result_faceid

					String result_faceid = firstVo.getResult_faceid();// 情况一：当公安不为空，走公安；情况二：如果公安为空，则走非公安

					// 优先解析result_faceid公安（），没有再解析result_ref1（非公安）
					if (!StringUtils.isEmpty(result_faceid)) {
						logger.info("易道用户:"+userName+ "公安,result_faceid：" + result_faceid);

						FaceSecondVo secVo = JSON.parseObject(result_faceid, FaceSecondVo.class);

						if (secVo != null) {

							confidenceGa = secVo.getConfidence();// 分数

							String thresholds = secVo.getThresholds();
							if (!StringUtils.isEmpty(thresholds)) {
								Map<String, Object> threMap = JSON.parseObject(thresholds);

								e3 = (BigDecimal) threMap.get("1e-3");
								e4 = (BigDecimal) threMap.get("1e-4");
								e5 = (BigDecimal) threMap.get("1e-5");
								e6 = (BigDecimal) threMap.get("1e-6");
								faceInfoFlag = true;
							}

						}
					} else {

						String result_ref1 = firstVo.getResult_ref1();

						logger.info("用户:"+userName + "非公安,result_ref1：" + result_ref1);

						FaceSecondVo secVo = JSON.parseObject(result_ref1, FaceSecondVo.class);

						if (secVo != null) {

							confidenceGaNo = secVo.getConfidence();// 分数

							String thresholds = secVo.getThresholds();
							if (!StringUtils.isEmpty(thresholds)) {
								Map<String, Object> threMap = JSON.parseObject(thresholds);

								e3 = (BigDecimal) threMap.get("1e-3");
								e4 = (BigDecimal) threMap.get("1e-4");
								e5 = (BigDecimal) threMap.get("1e-5");
								e6 = (BigDecimal) threMap.get("1e-6");
								faceInfoFlag = true;
							}

						}

					}
				} else {
					logger.info("用户:"+userName + ",face++原始数据转化的实体为空");
				}
			} else {
				logger.info("用户:"+userName + ",face++原始数据为空");
			}
			// 2：----------------------------------解析出的分数，公安和非公安的分数给规则----------------------------------
			if (faceInfoFlag) {// face++有数据，调用规则引擎

				Map<String, Object> faceInMap = new HashMap<String, Object>();

				if (confidenceGaNo.compareTo(BigDecimal.ZERO) == 0) {
					faceInMap.put("score_id", -1);
				} else {
					faceInMap.put("score_id", confidenceGaNo.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());

				}

				if (confidenceGa.compareTo(BigDecimal.ZERO) == 0) {
					faceInMap.put("score_ga", -1);
				} else {
					faceInMap.put("score_ga", confidenceGa.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());

				}

				faceInMap.put("level_3", e3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				faceInMap.put("level_4", e4.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				faceInMap.put("level_5", e5.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				faceInMap.put("level_6", e6.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

				logger.info("用户:"+req.getUser_name() + "face++规则进入规则数据：" + JSON.toJSONString(faceInMap));
				// 规则引擎输入参数

				Map<String, Object> ruleRespMap = FaceEngineService.doRule(faceInMap);

				logger.info("用户:"+userName + "face++规则引擎返回结果：" + JSON.toJSONString(ruleRespMap));
				logger.info("用户:"+userName+ ",face++规则引擎调用完成");

				String result = (String) ruleRespMap.get("result");
				if (result != null) {

					req.setResult(result);
					// 保存验证记录

					if (confidenceGa.compareTo(BigDecimal.ZERO) == 0) {// 如果公安为0，说明用的非公安，所以保存非公安的分数
						req.setMark(faceInMap.get("score_id") + "");
					} else {
						req.setMark(faceInMap.get("score_ga") + "");
					}

					if (result.equals("Y")) {
						is_passed = true;

					}

				} else {

					logger.info("用户:"+userName + "face++规则返回结果为空");
					// resp.setResultCode(ResultCodes.YIDAO_MARK_ERROR);
				}

			} else {// face++识别失败
				logger.info("用户:"+userName+ "face++返回数据为空");
				// resp.setResultCode(ResultCodes.YIDAO_MARK_ERROR);
			}
			// 保存结果
			if (is_passed) {// 验证成功
				req.setResult("Y");
			} else {
				req.setResult("N");
			}
			// 保存验证记录
			saveScoreResult(req);

		} catch (Exception e) {
			// resp.setResultCode(ResultCodes.YIDAO_MARK_ERROR);
			logger.error("用户:"+userName + "face++解析数据异常", e);
		}
		if (is_passed) {// face++保存图片
			resp.setSavePic("Y");
		} else {
			resp.setSavePic("N");
		}

		resp.setIs_passed(is_passed);
		return resp;
	}

	/**
	 * 保存人脸识别结果记录
	 * 
	 * @param reqParam
	 * @throws SaveScoreResultException
	 */
	public void saveScoreResult(YiDaoMarkReq req) throws SaveScoreResultException {

		YiDaoMarkEvent event = new YiDaoMarkEvent();

		HyScoreRecord hyScoreRecord = new HyScoreRecord();
		hyScoreRecord.setCreateDate(DateUtils.getDateStr(new Date()));
		if (!StringUtils.isEmpty(req.getMark())) {

			hyScoreRecord.setScore(req.getMark());
		} else {
			hyScoreRecord.setScore(0f + "");
		}
		hyScoreRecord.setIdNo(req.getId_no());
		hyScoreRecord.setResult(req.getResult());
		hyScoreRecord.setThresholdHigh(req.getThreshold_high());
		hyScoreRecord.setThresholdLow(req.getThreshold_low());
		hyScoreRecord.setWhichPart(req.getWhich_part());
		hyScoreRecord.setTempNo(req.getTemp_no());
		logger.info("用户名：" + req.getUser_name() + "保存人脸识别记录");
		event.saveFaceMsg(hyScoreRecord);

	}

	/**
	 * 保存face返回的原始数据
	 * 
	 * @param req
	 */
	public void saveFaceMsg(YiDaoMarkReq req) {

		YiDaoMarkEvent event = new YiDaoMarkEvent();

		FaceOriginalDetailEntity oriEntity = new FaceOriginalDetailEntity();

		oriEntity.setIdNo(req.getId_no());
		oriEntity.setFaceStr(req.getFace_str());
		logger.info("身份证号：" + req.getId_no() + "保存FACE原始数据记录");
		event.saveFaceMsg(oriEntity);

	}

	/**
	 * 调用易道的接口
	 * 
	 * @param req
	 * @return
	 */
	public YiDaoApiResp sendYiDao(YiDaoMarkReq req) {

		String identityNo = req.getId_no();

		YiDaoApiResp resp = new YiDaoApiResp();
		resp.setError("44");// 默认值，调用接口失败

		// 请求易道参数
		Map<String, String> yiDaoParam = new HashMap<String, String>();

		yiDaoParam.put("username", ResourceUtils.getValue("yidao_user"));
		yiDaoParam.put("password", ResourceUtils.getValue("yidao_pwd"));
		yiDaoParam.put("face1", req.getYidao_pic1());
		yiDaoParam.put("face2", req.getYidao_pic2());
		yiDaoParam.put("encoding", "utf-8");
		yiDaoParam.put("b64", "1");// 1:base64,0二进制流

		String result = "";
		try {
			result = HttpRemotePost.sendHttp(ResourceUtils.getValue("yidao_url"), yiDaoParam);
		} catch (Exception e) {
			logger.error("身份证号：" + identityNo + "调用易道博时远程链接异常：", e);
		}

		logger.info("身份证号：" + identityNo + "调用易道博时返回原始数据：" + result);

		if (!StringUtils.isEmpty(result)) {

			resp = JSONObject.parseObject(result, YiDaoApiResp.class);

		} else {
			logger.info("身份证号：" + identityNo + "调用易道博时返回原始数据：为空");
		}
		return resp;
	}

	/**
	 * 
	 * @param isGongAn
	 * @param realName
	 * @param identityNo
	 * @param uuid,前端传
	 */
	public String sendFace(YiDaoMarkReq req) {// 返回一个code，看调用是否成功，成功则解析，不成功不解析

		String faceStr = "";

		boolean isGongAn = false;// 默认不做公安
		if (!StringUtils.isEmpty(req.getTemp_no())) {//
			// 司机贷只走face++
			TempCreditQuery tempQuery = new TempCreditQuery();
			TempCredit tempCredit = tempQuery.queryTempCreditByAppId(req.getTemp_no());

			if (tempCredit != null && "SX".equals(tempCredit.getCredit_type())) {// 司机贷授信走公安

				isGongAn = true;
				logger.info(req.getId_no() + ",走公安");

			}
		}
//滴滴司机走公安，9月21号废弃
//		if (!StringUtils.isEmpty(req.getApp_no())) {//查询正式订单表
//			ApplicationPayQuery applicationQueryNew=new ApplicationPayQuery();
//			ApplicationPay payFace=applicationQueryNew.queryApplicationPayById(req.getApp_no());
//
//			if (payFace != null&&"DDSJ".equals(payFace.getIndustryCode())) {
//			
//				isGongAn = true;
//				logger.info(req.getId_no() + ",走公安");
//			
//			} 
//		}	
		
		Map<String, String> textMap = new HashMap<String, String>();
		if (isGongAn) {
			textMap.put("idcard_name", req.getReal_name());
			textMap.put("idcard_number", req.getId_no());
		} else {
			textMap.put("uuid", req.getUuid());
		}
		// try {
		//// faceParam.put("image_ref1", new FileInputStream(new File(
		//// model.getIdcardPath())));// 传入身份证头像照片路径
		// fileMap.put("image_ref1", req.getImage_ref1());// 传入图片地址
		// } catch (Exception e) {
		// }
		textMap.put("delta", req.getDelta());
		textMap.put("api_key", ResourceUtils.getValue("face_api_key"));
		textMap.put("api_secret", ResourceUtils.getValue("face_api_secret"));

		String isGongAnStr = isGongAn ? "1" : "0";
		textMap.put("comparison_type", isGongAnStr);
		textMap.put("face_image_type", "meglive");

		String contentType = "application/octet-stream";
		logger.info("用户:" + req.getId_no() + "face,请求参数:" + StringUtils.mapToJsons(textMap));
		logger.info("用户:" + req.getId_no() + "face,请求参数:" + StringUtils.mapToJsons(req.getFileMap()));
		String faceBackOri = formUpload(ResourceUtils.getValue("face_url"), textMap, req.getFileMap(), contentType,
				req.getId_no());// face返回的原始数据
		// 如果返回为空
		if (!StringUtils.isEmpty(faceBackOri)) {

			faceStr = faceBackOri;
		} else {
			logger.info("用户:" + req.getId_no() + ",调用face返回请求失败");
		}
		logger.info("用户:" + req.getId_no() + ",调用face给原始字符串赋值：" + faceStr);
		return faceStr;
	}

	/**
	 * 上传图片
	 */
	@SuppressWarnings("rawtypes")
	public String formUpload(String urlStr, Map<String, String> textMap, Map<String, String> fileMap,
			String contentType, String identityNo) {
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "-----------------12345654321-----------";
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			conn.setRequestProperty("Charset", "UTF-8");
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
					out.write(strBuf.toString().getBytes());
					DataInputStream in = new DataInputStream(new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();
			int responseCode = conn.getResponseCode();
			if (responseCode == 200) {
				// 读取返回数据
				StringBuffer strBuf = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String line = null;
				while ((line = reader.readLine()) != null) {
					strBuf.append(line).append("\n");
				}
				res = strBuf.toString();
				reader.close();
				reader = null;
				logger.info("用户：" + identityNo + "，调用face返回数据，" + res);
			} else {
				StringBuffer error = new StringBuffer();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				String line1 = null;
				while ((line1 = bufferedReader.readLine()) != null) {
					error.append(line1).append("\n");
				}
				res = error.toString();
				bufferedReader.close();
				bufferedReader = null;
				logger.info("用户：" + identityNo + "，调用face返回数据，" + res);
				// 当出现人脸识别，face返回请求失败时，这里置为空，就不用再解析了，直接返回人脸不通过
				// res = "";
			}

		} catch (Exception e) {
			logger.error("发送POST请求出错。" + e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}

		return res;
	}

	/**
	 * 易道博时返回码对应消息
	 * 
	 * @param error
	 * @return
	 */
	public String getErrorMsg(String error) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("0", "正确");
		map.put("1", "识别错误");
		map.put("2", "登录失败");
		map.put("3", "服务器忙");
		map.put("4", "服务未启动");
		map.put("5", "服务器异常");
		map.put("6", "数据库错误");
		map.put("7", "无合格图像");
		map.put("8", "识别次数已经用完");
		return map.get(error);

	}

	/**
	 * z转Base64
	 * 
	 * @param url
	 * @return
	 */
	public String getImageBinary(File file) {

		BufferedImage bi;
		try {
			bi = ImageIO.read(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			byte[] bytes = baos.toByteArray();

			return encoder.encodeBuffer(bytes).trim();
		} catch (IOException e) {
			logger.error("易道人脸照片转化为base64异常", e);
		}

		return null;
	}

	public static void main(String[] args) {

		// // 返回数据为空
		// String ori = "{\"result_faceid\": {\"confidence\":
		// 92.111,\"thresholds\": {\"1e-3\": 62.169,\"1e-5\": 74.399,\"1e-4\":
		// 69.315,\"1e-6\": 78.038}}}";
		//
		// FaceFirstVo firstVo = JSON.parseObject(ori, FaceFirstVo.class);
		// if (firstVo != null) {// result_faceid
		// String result_faceid = firstVo.getResult_faceid();
		//
		// FaceSecondVo secVo = JSON.parseObject(result_faceid,
		// FaceSecondVo.class);
		// if (secVo != null) {
		// BigDecimal confidence = secVo.getConfidence();
		// String thresholds = secVo.getThresholds();
		//
		// try {
		//
		// Map<String, Object> threMap = JSON.parseObject(thresholds);
		//
		// YiDaoMarkReq req = new YiDaoMarkReq();
		// req.setFace_str(ori);
		// YiDaoMarkService mark = new YiDaoMarkService();
		// mark.saveFaceMsg(req);
		// BigDecimal e3 = (BigDecimal) threMap.get("1e-3");
		// BigDecimal e4 = (BigDecimal) threMap.get("1e-4");
		// BigDecimal e5 = (BigDecimal) threMap.get("1e-5");
		// BigDecimal e6 = (BigDecimal) threMap.get("1e-6");
		//
		// System.out.println(e3);
		// System.out.println(e4);
		// System.out.println(e5);
		// System.out.println(e6);
		// } catch (IllegalArgumentException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// }

		try {
			InputStream in = new FileInputStream(new File("E:\\pictest\\juhua.jpg"));
			System.out.println(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
