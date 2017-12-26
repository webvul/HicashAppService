package com.hengyuan.hicash.entity.remote;

import java.util.List;

public class AuthorizeReqParam {

	private BasicInfoEntity basic_info;

	private List<ContactsEntity> contacts;

	public BasicInfoEntity getBasic_info() {
		return basic_info;
	}

	public void setBasic_info(BasicInfoEntity basic_info) {
		this.basic_info = basic_info;
	}

	public List<ContactsEntity> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactsEntity> contacts) {
		this.contacts = contacts;
	}

}
