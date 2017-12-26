package com.hengyuan.hicash.utils;

public enum SqlKeyWord {
	SELECT("SELECT"),
	DELETE("DELETE"),
	INSERT("INSERT"),
	INTO("INTO"),
	SET("SET"),
	UPDATE("UPDATE"),
	VALUES("VALUES"),
	DISTINCT("DISTINCT"),
	AS("AS"),
	FROM("FROM"),
	WHERE("WHERE"),
	AND("AND"), 
	LIKE("LIKE"), 
	OR("OR"),
	IN("IN"),
	ASC("ASC"), 
	DESC("DESC"),
	GROUP("GROUP"),
	ORDER("ORDER"),
	BY("BY");
	    
	
	
	
	private String value;
	private SqlKeyWord(String value) {
		this.value = " " + value + " ";
	}
	public String toString() {
		return new String(this.value);
	}

}
