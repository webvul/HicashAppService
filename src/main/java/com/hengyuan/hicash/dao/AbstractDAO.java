package com.hengyuan.hicash.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.collection.ConnManager;

public abstract class AbstractDAO<E> {
	
	protected static Logger logger = Logger.getLogger(AbstractDAO.class);
	
	
	protected static Connection getConn() {
		return ConnManager.getConn();
	}
	
	public abstract E mapping(ResultSet rs) throws SQLException;
	
	/**
	 * 计算总页数
	 * @author Cary.Liu
	 * @param maxLine 每页显示的行数
	 * @param countLine 列表总行数
	 * @return
	 */
	public Integer countPage(Integer maxLine,Integer countLine){
		Integer maxPage = 0;
		
		maxPage = countLine/maxLine;
		
		if(countLine % maxLine !=0){
			maxPage +=1;
		}
		
		return maxPage;
	}
	
	/**
	 * 计算当前页数
	 * @param curPage
	 * @param maxPage
	 * @return
	 */
	public Integer sumCurPage(String curPage,Integer maxPage){
		Integer strPage = 1;
		
		if(curPage != null){
			Integer intPage = Integer.parseInt(curPage);
			if(intPage<1){
				return strPage;
			}else{
				return intPage;
			}
		}else{
			return strPage;
		}
	}
	
}