package com.hengyuan.hicash.utils;

import java.util.List;

import com.hengyuan.hicash.utils.exception.ListNotFoundException;



/**
 * sql语句组装工具类
 * 目前只提供简单根据条件来组装正确的sql语句
 * 
 * @author andy.niu
 */
public class SqlAssemblyUtils{

	/**
	 * 组装like语句
	 * 
	 * @param fieldName
	 * @param value
	 * @param index
	 * @return
	 */
	public static String receiveLike(String fieldName, String value,
			String index) {

		String statement = SqlKeyWord.AND.toString()
				+ SqlOperators.LEBR.toString() + fieldName
				+ SqlKeyWord.LIKE.toString();
		/* 左边加like */
		if (UtilsConst.LIKE_LEFT_INDEX.equals(index)) {
			statement = statement + SqlOperators.PROINT.toString()
					+ SqlOperators.PERC.toString() + value
					+ SqlOperators.PROINT.toString();
			/* 右边加like */
		} else if (UtilsConst.LIKE_RIGHT_INDEX.equals(index)) {
			statement = statement + SqlOperators.PROINT.toString() + value
					+ SqlOperators.PERC.toString()
					+ SqlOperators.PROINT.toString();
		} else {
			/* 左右都加like */
			statement = statement + SqlOperators.PROINT.toString()
					+ SqlOperators.PERC.toString() + value
					+ SqlOperators.PERC.toString()
					+ SqlOperators.PROINT.toString();
		}
		return statement + SqlOperators.RIBR.toString();
	}

	/**
	 * 判读字段值于传入值是否相等
	 * 
	 * @param fieldName
	 * @param value
	 * @param nullFlag
	 *            是否允许为空值
	 * @return
	 */
	public static String receiveEqualSign(String fieldName, String value,
			boolean nullFlag) {
		String statement = SqlKeyWord.AND.toString()
				+ SqlOperators.LEBR.toString() + fieldName
				+ SqlOperators.EQ.toString()
				+ SqlOperators.PROINT.toString() + value
				+ SqlOperators.PROINT.toString();
		if (nullFlag) {
			statement = statement + SqlKeyWord.OR.toString() + fieldName
					+ SqlOperators.EQ.toString()
					+ SqlOperators.PROINT.toString()
					+ SqlOperators.PROINT.toString()
					+ SqlKeyWord.OR.toString() + fieldName + " is null";
		}
		return statement + SqlOperators.RIBR.toString();
	}

	/**
	 * 传入的字段于传入的值不相等
	 * 
	 * @param fieldName
	 * @param value
	 * @param nullFlag
	 *            true可以为空或者null
	 * @return
	 */
	public static String receiveEqualSignNot(String fieldName, String value,
			boolean nullFlag) {
		String statement = SqlKeyWord.AND.toString()
				+ SqlOperators.LEBR.toString() + fieldName
				+ SqlOperators.NE.toString()
				+ SqlOperators.PROINT.toString() + value
				+ SqlOperators.PROINT.toString();
		if (!nullFlag) {
			statement = statement + SqlKeyWord.AND.toString() + fieldName
					+ SqlOperators.NE.toString()
					+ SqlOperators.PROINT.toString()
					+ SqlOperators.PROINT.toString()
					+ SqlKeyWord.AND.toString() + fieldName + " is not null";
		}
		return statement + SqlOperators.RIBR.toString();
	}

	/**
	 * 组装字符串
	 * 
	 * @param strList
	 * @return 如 'test1','test2','test3','test4'
	 * @throws ListNotFoundException
	 */
	public static String receiveStrByList(List<String> strList)
			throws ListNotFoundException {
		if (strList == null || strList.isEmpty()) {
			throw new ListNotFoundException(UtilsMsg.LIST_NOT_FOUND);
		} else {
			String str = "";
			for (int i = 0; i < strList.size(); i++) {
				if (i == strList.size() - 1) {
					str = str + SqlOperators.PROINT.toString()
							+ strList.get(i) + SqlOperators.PROINT.toString();
				} else {
					str = str + SqlOperators.PROINT.toString()
							+ strList.get(i) + SqlOperators.PROINT.toString()
							+ SqlOperators.COMMA.toString();
				}
			}
			return str;
		}
	}

	/**
	 * 字段值存在于list集合中
	 * 
	 * @param fieldName
	 * @param strList
	 * @return
	 * @throws ListNotFoundException
	 */
	public static String receiveEqualSignByList(String fieldName,
			List<String> strList) throws ListNotFoundException {
		return SqlKeyWord.AND.toString() + fieldName
				+ SqlKeyWord.IN.toString() + SqlOperators.LEBR.toString()
				+ receiveStrByList(strList) + SqlOperators.RIBR.toString();
	}

	/**
	 * 比较值大小
	 * @param fieldName
	 * @param value
	 * @param eq
	 * @return
	 */
	public static String compareValue(String fieldName, String value, SqlOperators eq){
		return SqlKeyWord.AND.toString() +fieldName + eq + "'"+value+"'";
	}
	
	/**
	 * 组装查询字段
	 * @param fieldName
	 * @param value
	 * @param eq
	 * @param keyWord
	 * @return
	 */
	public static String assembleValue(String fieldName, String value, SqlOperators eq,SqlKeyWord keyWord){
		return keyWord.toString() +fieldName + eq + "'"+value+"'";
	}
	
	/**
	 * 组装日期类型的sql语句
	 * 
	 * @param fieldName
	 *            字段名
	 * @param value
	 *            值
	 * @param dateType
	 *            日期类型
	 * @param operator
	 *            操作符号
	 * @return
	 */
	public static String queryDateByMysql(String fieldName, String value,
			String dateType, String operator) {
		String statement = SqlKeyWord.AND.toString()
				+ SqlOperators.FROM_UNIXTIME.toString()
				+ SqlOperators.LEBR.toString()
				+ SqlOperators.UNIX_TIMESTAMP
				+ SqlOperators.LEBR.toString() + fieldName
				+ SqlOperators.RIBR.toString()
				+ SqlOperators.COMMA.toString()
				+ SqlOperators.PROINT.toString() + dateType
				+ SqlOperators.PROINT.toString()
				+ SqlOperators.RIBR.toString() + operator
				+ SqlOperators.FROM_UNIXTIME + SqlOperators.LEBR.toString()
				+ SqlOperators.UNIX_TIMESTAMP
				+ SqlOperators.LEBR.toString()
				+ SqlOperators.PROINT.toString() + value
				+ SqlOperators.PROINT.toString()
				+ SqlOperators.RIBR.toString()
				+ SqlOperators.COMMA.toString()
				+ SqlOperators.PROINT.toString() + dateType
				+ SqlOperators.PROINT.toString()
				+ SqlOperators.RIBR.toString();
		return statement;
	}

	/**
	 * 字段倒序
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String receiveOrderDesc(String fieldName) {
		return SqlKeyWord.ORDER.toString() + SqlKeyWord.BY.toString()
				+ fieldName + SqlKeyWord.DESC.toString();
	}
	
	/**
	 * 字段顺序
	 * @param fieldName
	 * @return
	 */
	public static String receiveOrderAsc(String fieldName){
		return SqlKeyWord.ORDER.toString() + SqlKeyWord.BY.toString()
				+ fieldName + SqlKeyWord.ASC.toString();
	}
	
	
	

	public static void main(String args[]) throws ListNotFoundException {
		// System.out.println(receiveLike("app_username", "test", null));
	/*	System.out.println(queryDateByMysql("app_username", "test",
				UtilsConst.DATE.toString(), SqlOperators.GE.toString()));*/

		
		
		// System.out.println(receiveEqualSignNot("app_username", "test",
		// true));

		// List<String> list = new ArrayList<String>();
		// list.add("test1");
		// list.add("test2");
		// list.add("test3");
		// list.add("test4");
		// System.out.println(receiveEqualSignByList("app_username",list));

	}

}
