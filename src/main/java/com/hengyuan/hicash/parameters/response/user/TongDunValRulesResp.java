package com.hengyuan.hicash.parameters.response.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hengyuan.hicash.entity.user.HitRule;
import com.hengyuan.hicash.entity.user.Policy;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 同盾接口验证客户黑名单，客户身份证号码、手机号码、邮箱、qq
 * 
 * @author lihua.Ren
 * @create date 2015-11-10
 *
 */
public class TongDunValRulesResp extends ParmResponse {
	  private boolean             success;                               // 执行是否成功，不成功时对应reason_code
	    private String              reason_code;                           // 错误码及原因描述，正常执行完扫描时为空
	    private Integer             final_score;                           // 风险分数
	    private String              final_decision;                        // 最终的风险决策结果
	    private List<HitRule>       hit_rules        = new ArrayList<HitRule>();  // 命中规则列表
	    private String              seq_id;                                // 请求序列号，每个请求进来都分配一个全局唯一的id
	    private Integer             spend_time;                            // 花费的时间，单位ms
	    private Map<String, Object> device_info      = new HashMap<String, Object>();    // 设备信息
	    private Map<String, Object> geoip_info       = new HashMap<String, Object>();    // 地理位置信息
	    private String              policy_set_name;                       // 策略集名称
		private String              risk_type;                             // 命中的风险类型
	    private List<Policy>        policy_set       = new ArrayList<Policy>();  // 策略集
	    private String policy_name;
		/**
		 * @return the success
		 */
		public boolean isSuccess() {
			return success;
		}
		/**
		 * @param success the success to set
		 */
		public void setSuccess(boolean success) {
			this.success = success;
		}
		/**
		 * @return the reason_code
		 */
		public String getReason_code() {
			return reason_code;
		}
		/**
		 * @param reason_code the reason_code to set
		 */
		public void setReason_code(String reason_code) {
			this.reason_code = reason_code;
		}
		/**
		 * @return the final_score
		 */
		public Integer getFinal_score() {
			return final_score;
		}
		/**
		 * @param final_score the final_score to set
		 */
		public void setFinal_score(Integer final_score) {
			this.final_score = final_score;
		}
		/**
		 * @return the final_decision
		 */
		public String getFinal_decision() {
			return final_decision;
		}
		/**
		 * @param final_decision the final_decision to set
		 */
		public void setFinal_decision(String final_decision) {
			this.final_decision = final_decision;
		}
		/**
		 * @return the hit_rules
		 */
		public List<HitRule> getHit_rules() {
			return hit_rules;
		}
		/**
		 * @param hit_rules the hit_rules to set
		 */
		public void setHit_rules(List<HitRule> hit_rules) {
			this.hit_rules = hit_rules;
		}
		/**
		 * @return the seq_id
		 */
		public String getSeq_id() {
			return seq_id;
		}
		/**
		 * @param seq_id the seq_id to set
		 */
		public void setSeq_id(String seq_id) {
			this.seq_id = seq_id;
		}
		/**
		 * @return the spend_time
		 */
		public Integer getSpend_time() {
			return spend_time;
		}
		/**
		 * @param spend_time the spend_time to set
		 */
		public void setSpend_time(Integer spend_time) {
			this.spend_time = spend_time;
		}
		/**
		 * @return the device_info
		 */
		public Map<String, Object> getDevice_info() {
			return device_info;
		}
		/**
		 * @param device_info the device_info to set
		 */
		public void setDevice_info(Map<String, Object> device_info) {
			this.device_info = device_info;
		}
		/**
		 * @return the geoip_info
		 */
		public Map<String, Object> getGeoip_info() {
			return geoip_info;
		}
		/**
		 * @param geoip_info the geoip_info to set
		 */
		public void setGeoip_info(Map<String, Object> geoip_info) {
			this.geoip_info = geoip_info;
		}
		/**
		 * @return the policy_set_name
		 */
		public String getPolicy_set_name() {
			return policy_set_name;
		}
		/**
		 * @param policy_set_name the policy_set_name to set
		 */
		public void setPolicy_set_name(String policy_set_name) {
			this.policy_set_name = policy_set_name;
		}
		/**
		 * @return the risk_type
		 */
		public String getRisk_type() {
			return risk_type;
		}
		/**
		 * @param risk_type the risk_type to set
		 */
		public void setRisk_type(String risk_type) {
			this.risk_type = risk_type;
		}
		/**
		 * @return the policy_set
		 */
		public List<Policy> getPolicy_set() {
			return policy_set;
		}
		/**
		 * @param policy_set the policy_set to set
		 */
		public void setPolicy_set(List<Policy> policy_set) {
			this.policy_set = policy_set;
		}
		/**
		 * @return the policy_name
		 */
		public String getPolicy_name() {
			return policy_name;
		}
		/**
		 * @param policy_name the policy_name to set
		 */
		public void setPolicy_name(String policy_name) {
			this.policy_name = policy_name;
		}

}
