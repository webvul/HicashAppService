package com.hengyuan.hicash.entity.user;

/**
 * @author Administrator
 *
 */
public class HitRule {
	private static final long serialVersionUID = 6297666052880082771L;
    private String            uuid;                                   // 规则uuid
	private String            id;                                     // 规则编号
    private String            name;                                   // 规则名称
    private String            decision;                               // 该条规则决策结果
    private int               score;                                  // 规则分数
	
    //    ...省略若干Getter与Setter
	
	@Override
    public String toString() {
        return "rule_name:" + this.name + "\nscore:" + this.score + "\ndescision:" + this.decision + "\n";
    }

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the decision
	 */
	public String getDecision() {
		return decision;
	}

	/**
	 * @param decision the decision to set
	 */
	public void setDecision(String decision) {
		this.decision = decision;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
}
