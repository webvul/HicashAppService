package com.hengyuan.hicash.utils;

public enum SqlOperators {
	FROM_UNIXTIME("FROM_UNIXTIME"),
	UNIX_TIMESTAMP("UNIX_TIMESTAMP"),
	EQ("="), 
	NE("<>"), 
	GT(">"), 
	GE(">="), 
	LT("<"), 
	LE("<="),
	LEBR("("),
	RIBR(")"),
	PROINT("'"),
	COMMA(","),
	PERC("%");
	
	private String value;
	private SqlOperators(String value) {
		this.value = value;
	}
	public String toString() {
		return new String(this.value);
	}
}
