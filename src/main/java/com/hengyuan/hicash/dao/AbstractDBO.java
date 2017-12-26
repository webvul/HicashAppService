package com.hengyuan.hicash.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.collection.ConnRedis;



public abstract class AbstractDBO<E> {
	
	protected static Logger logger = Logger.getLogger(AbstractDBO.class);
	
	
	protected static Connection getConn() {
		return ConnRedis.getConn();
	}
	
	public abstract E mapping(ResultSet rs) throws SQLException;

}

