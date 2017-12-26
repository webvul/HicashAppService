package com.hengyuan.hicash.entity.remote;

public class AuthorizeData {

	private String token;

	private String cell_phone_num;

	private AuthorizeDataSource datasource;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCell_phone_num() {
		return cell_phone_num;
	}

	public void setCell_phone_num(String cell_phone_num) {
		this.cell_phone_num = cell_phone_num;
	}

	public AuthorizeDataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(AuthorizeDataSource datasource) {
		this.datasource = datasource;
	}

}
