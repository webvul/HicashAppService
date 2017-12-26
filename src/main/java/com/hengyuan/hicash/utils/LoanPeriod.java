package com.hengyuan.hicash.utils;

/**
 * 借款期限枚举
 * 
 * @author zhangdh
 *
 */
public enum LoanPeriod {
	ONMN(1,"1个月"),TWON(2,"2个月"),THMN(3,"3个月"),FOMN(4,"4个月"),FIMN(5,"5个月"),SIMN(6,"6个月"), SEMN(7,"7个月"),EIMN(8,"8个月"),NIMN(9,"9个月"),
	TEMN(10,"10个月"),ELMN(11,"11个月"),TWMN(12,"12个月"), FOUT(14,"14个月"), FIFE(15,"15个月"), SEVT(17,"17个月"), EITE(18,"18个月"), TWTH(23, "23个月") ,TWET(24,"24个月"),THSI(36,"36个月");
	
	private Integer period;
	private String dispValue;

	LoanPeriod(Integer period, String dispValue) {
		this.period = period;
		this.dispValue = dispValue;
	}
	public String getDispValue() {
		return dispValue;
	}
	public Integer getPeriod() {
		return period;
	}
	
    public static LoanPeriod  mapLoanPeriod(Integer period) {
    	LoanPeriod curLoanPeriod = null;
    	if (period != null) {
    	LoanPeriod[] periodArray = LoanPeriod.values();
    	for(LoanPeriod loadPeriod: periodArray) {
    		if (loadPeriod.getPeriod().intValue() == period.intValue()) {
    			curLoanPeriod = loadPeriod;
    		}
    	 }
    	}
    	return curLoanPeriod;
    }
}
