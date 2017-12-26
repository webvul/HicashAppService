package com.hengyuan.hicash.entity.user;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class Policy {
	  private static final long serialVersionUID = 2971731835604653516L;
	    private String            policy_uuid;                            // 策略uuid
	    private String            policy_decision;                        // 策略结果
	    private String            policy_mode;                            // 策略模式
	    private String            policy_name;                            // 策略名称
	    private String            policy_score;                           // 策略分数
	    private String            risk_type;                              // 风险类型

	    private List<HitRule>     hit_rules        = new ArrayList<HitRule>();   // 命中规则列表

		/**
		 * @return the policy_uuid
		 */
		public String getPolicy_uuid() {
			return policy_uuid;
		}

		/**
		 * @param policy_uuid the policy_uuid to set
		 */
		public void setPolicy_uuid(String policy_uuid) {
			this.policy_uuid = policy_uuid;
		}

		/**
		 * @return the policy_decision
		 */
		public String getPolicy_decision() {
			return policy_decision;
		}

		/**
		 * @param policy_decision the policy_decision to set
		 */
		public void setPolicy_decision(String policy_decision) {
			this.policy_decision = policy_decision;
		}

		/**
		 * @return the policy_mode
		 */
		public String getPolicy_mode() {
			return policy_mode;
		}

		/**
		 * @param policy_mode the policy_mode to set
		 */
		public void setPolicy_mode(String policy_mode) {
			this.policy_mode = policy_mode;
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

		/**
		 * @return the policy_score
		 */
		public String getPolicy_score() {
			return policy_score;
		}

		/**
		 * @param policy_score the policy_score to set
		 */
		public void setPolicy_score(String policy_score) {
			this.policy_score = policy_score;
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
		
	    //    ...省略若干Getter与Setter
		
//		@Override
//	    public String toString() {
//	        return "policy_name:" + this.policy_name + "\npolicy_mode:" + this.policy_mode + "\nhit_rules:"
//	               + this.hit_rules;
//	    }
		
}
