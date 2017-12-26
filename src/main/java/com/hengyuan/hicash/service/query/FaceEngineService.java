package com.hengyuan.hicash.service.query;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.experian.stratman.decisionagent.business.OS390.Batch.BatchJSEMObjectInterface;
/**
 * 
 * @author 0493
 *face++规则引擎
 * @date 2017-05-12
 */
public class FaceEngineService {
	private static Logger logger = Logger.getLogger(RuleExperian.class);

	// 输入 交易码：RLSBONL
	// 输入：RLSBIN
	// 输出：RLSBOT
	private final static String ALIAS = "FRAPPON";// 交易码
	private final static String SIGNATURE = "HYJR";// 所调用策略流的签名
	private final static String InputDataArea = "FRAPPIN";// 输入的Physical Layout
	private final static String OutputDataArea = "FRAPPOT";// 输出的Physical Layout
	private static int TraceLevel = 0; // 调用DA时的日志级别

	/**
	 * 获取规则策略结果
	 * 
	 * @param inputMap
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> doRule(Map<String, Object> inputMap) throws Exception {

		// 控制数据
		IDatas controlArea = new IDatas("OCONTROL");// 固定
		Map<String, Object> controlMap = new HashMap<String, Object>();
		controlMap.put("ALIAS", ALIAS);
		controlMap.put("SIGNATURE", SIGNATURE);
		controlMap.put("SystemDate", DateFormatUtils.format(Calendar.getInstance(), "yyyyMMdd"));
		controlArea.setAreaContents(controlMap);

		// 申请信息
		IDatas inputArea = new IDatas(InputDataArea);// 固定

		logger.info("face++规则输入：" + JSON.toJSONString(inputMap));
		inputArea.setAreaContents(inputMap);

		// 输出信息
		IDatas outputArea = new IDatas(OutputDataArea);

		IDatas[] idatas = new IDatas[3];
		idatas[0] = controlArea;
		idatas[1] = inputArea;
		idatas[2] = outputArea;

		int returnCode = BatchJSEMObjectInterface.instance().execute(idatas, TraceLevel);

		logger.info("face++规则returnCode:{}" + returnCode);
		logger.info("face++规则controlArea:{}" + JSON.toJSONString(controlArea));
		logger.info("face++规则outputArea:{}" + JSON.toJSONString(outputArea));

		Map<String, Object> areaContents = outputArea.getAreaContents();

		return areaContents;
	}
}
