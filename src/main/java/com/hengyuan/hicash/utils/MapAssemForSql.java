package com.hengyuan.hicash.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过MAP获取对应的sql语句
 * 
 * @author Andy.Niu
 * @create 2014-08-07
 */
public class MapAssemForSql {

	/**
	 * 获取查询语句
	 * @param selects
	 * @param tableName
	 * @param condition
	 * @return
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	public static String getSelectSqlByGroup(List<String> selects, String tableName,
			Map<String, Object> condition,String groupstr) {

		StringBuffer selectSql = new StringBuffer("");
		selectSql.append(SqlKeyWord.SELECT);
		selectSql.append(getSelects(selects));
		selectSql.append(SqlKeyWord.FROM);
		selectSql.append(tableName);
		selectSql.append(SqlKeyWord.WHERE);
		selectSql.append("1 = 1");
		selectSql.append(getconditions(condition));
		selectSql.append(" "+groupstr);
		
		return selectSql.toString();
	}
	
	/**
	 * 获取新增语句新增字段
	 * 
	 * @param inputPay
	 * @return 例：applicationNo, productName, productType
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	private static String getInsertKey(Map<String, Object> inputPay) {

		StringBuffer keySql = new StringBuffer("");
		if (inputPay != null) {
			int i = 0;
			for (Map.Entry<String, Object> entry : inputPay.entrySet()) {
				if (i == inputPay.entrySet().size() - 1) {
					keySql.append(entry.getKey());
				} else {
					keySql.append(entry.getKey() + SqlOperators.COMMA);
				}
				i++;
			}
		}
		return keySql.toString();
	}

	/**
	 * 获取新增语句Value字段
	 * 
	 * @param inputPay
	 * @return 例如：'a', 'b', 'c'
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	private static String getInsertValue(Map<String, Object> inputPay) {

		StringBuffer valueSql = new StringBuffer("");

		if (inputPay != null) {
			int i = 0;
			for (Map.Entry<String, Object> entry : inputPay.entrySet()) {
				if (i == inputPay.entrySet().size() - 1) {
					valueSql.append(SqlOperators.PROINT.toString()
							+ entry.getValue() + SqlOperators.PROINT);
				} else {
					valueSql.append(SqlOperators.PROINT.toString()
							+ entry.getValue() + SqlOperators.PROINT
							+ SqlOperators.COMMA);
				}
				i++;
			}
		}
		return valueSql.toString();
	}
	
	/**
	 * 获取新增语句Value字段
	 * 
	 * @param inputPay
	 * @return 例如：'a', 'b', 'c'
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	private static String getInsertValueByselect(Map<String, Object> inputPay) {

		StringBuffer valueSql = new StringBuffer("");

		if (inputPay != null) {
			int i = 0;
			for (Map.Entry<String, Object> entry : inputPay.entrySet()) {
				if (i == inputPay.entrySet().size() - 1) {
					valueSql.append(entry.getValue());
				} else {
					valueSql.append(entry.getValue().toString()+ SqlOperators.COMMA);
				}
				i++;
			}
		}
		return valueSql.toString();
	}

	/**
	 * 获取更新语句需要设置的keyvalue
	 * 
	 * @param inputPay
	 * @return
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	private static String getUpdateKeyValue(Map<String, Object> inputPay) {

		StringBuffer keyValueSql = new StringBuffer("");

		if (inputPay != null) {
			int i = 0;
			for (Map.Entry<String, Object> entry : inputPay.entrySet()) {

				if (i == inputPay.entrySet().size() - 1) {
					keyValueSql.append(entry.getKey() + SqlOperators.EQ
							+ SqlOperators.PROINT + entry.getValue()
							+ SqlOperators.PROINT);
				} else {
					keyValueSql.append(entry.getKey() + SqlOperators.EQ
							+ SqlOperators.PROINT + entry.getValue()
							+ SqlOperators.PROINT + SqlOperators.COMMA);
				}
				i++;

			}
		}
		return keyValueSql.toString();
	}

	/**
	 * 获取条件语句
	 * 
	 * @param condition
	 * @return
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	private static String getconditions(Map<String, Object> condition) {
		StringBuffer conditionSql = new StringBuffer("");
		if (condition != null) {

			for (Map.Entry<String, Object> entry : condition.entrySet()) {
				conditionSql.append(SqlKeyWord.AND);
				conditionSql.append(entry.getKey() + SqlOperators.EQ
						+ SqlOperators.PROINT + entry.getValue()
						+ SqlOperators.PROINT);
			}
		}
		return conditionSql.toString();
	}

	/**
	 * 获取select字段
	 * @param selects
	 * @return
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	private static String getSelects(List<String> selects) {

		StringBuffer select = new StringBuffer("");
		if (selects != null && selects.size() > 0) {
			for (int i = 0; i < selects.size(); i++) {
				if (i == selects.size() - 1) {
					select.append(selects.get(i));
				} else {
					select.append(selects.get(i) + SqlOperators.COMMA);
				}

			}
		}

		return select.toString();
	}

	/**
	 * 获取insert语句
	 * 
	 * @param tableName
	 * @param inputPay
	 * @return
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	public static String getInsertSql(String tableName,
			Map<String, Object> inputPay) {

		StringBuffer insertSql = new StringBuffer();
		insertSql.append(SqlKeyWord.INSERT);
		insertSql.append(SqlKeyWord.INTO);
		insertSql.append(tableName);
		insertSql.append(SqlOperators.LEBR);
		insertSql.append(getInsertKey(inputPay));
		insertSql.append(SqlOperators.RIBR);
		insertSql.append(SqlKeyWord.VALUES);
		insertSql.append(SqlOperators.LEBR);
		insertSql.append(getInsertValue(inputPay));
		insertSql.append(SqlOperators.RIBR);

		return insertSql.toString();
	}
	
	/**
	 * 获取insert语句
	 * 
	 * @param tableName
	 * @param inputPay
	 * @param sql
	 * @return
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	public static String getInsertSqlBySelect(String tableName,
			Map<String, Object> inputPay,String sql) {

		StringBuffer insertSql = new StringBuffer();
		insertSql.append(SqlKeyWord.INSERT);
		insertSql.append(SqlKeyWord.INTO);
		insertSql.append(tableName);
		insertSql.append(SqlOperators.LEBR);
		insertSql.append(getInsertKey(inputPay));
		insertSql.append(SqlOperators.RIBR);
		insertSql.append(SqlKeyWord.SELECT);
		insertSql.append(getInsertValueByselect(inputPay));
		insertSql.append(" "+sql);
		
		return insertSql.toString();
	}

	public static String getUpdateSql(String tableName,
			Map<String, Object> inputPay, Map<String, Object> condition) {

		StringBuffer updateSql = new StringBuffer("");
		updateSql.append(SqlKeyWord.UPDATE);
		updateSql.append(tableName);
		updateSql.append(SqlKeyWord.SET);
		updateSql.append(getUpdateKeyValue(inputPay));
		updateSql.append(SqlKeyWord.WHERE);
		updateSql.append("1 = 1");
		updateSql.append(getconditions(condition));

		return updateSql.toString();
	}

	/**
	 * 获取查询语句
	 * @param selects
	 * @param tableName
	 * @param condition
	 * @return
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	public static String getSelectSql(List<String> selects, String tableName,
			Map<String, Object> condition) {

		StringBuffer selectSql = new StringBuffer("");
		selectSql.append(SqlKeyWord.SELECT);
		selectSql.append(getSelects(selects));
		selectSql.append(SqlKeyWord.FROM);
		selectSql.append(tableName);
		selectSql.append(SqlKeyWord.WHERE);
		selectSql.append("1 = 1");
		selectSql.append(getconditions(condition));
		
		return selectSql.toString();
	}
	
	/**
	 * 获取查询语句
	 * @param selects
	 * @param tableName
	 * @param condition
	 * @return
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	public static String getSelectSqlByGroupOrOrder(List<String> selects, String tableName,
			Map<String, Object> condition,String orderORgroup) {

		StringBuffer selectSql = new StringBuffer("");
		selectSql.append(SqlKeyWord.SELECT);
		selectSql.append(getSelects(selects));
		selectSql.append(SqlKeyWord.FROM);
		selectSql.append(tableName);
		selectSql.append(SqlKeyWord.WHERE);
		selectSql.append("1 = 1");
		selectSql.append(getconditions(condition));
		selectSql.append(" "+orderORgroup);
		return selectSql.toString();
	}

	public static String getDeleteSql(String tableName,Map<String, Object> condition) {

		StringBuffer updateSql = new StringBuffer("");
		updateSql.append(SqlKeyWord.DELETE);
		updateSql.append(SqlKeyWord.FROM);
		updateSql.append(tableName);
		updateSql.append(SqlKeyWord.WHERE);
		updateSql.append("1 = 1");
		updateSql.append(getconditions(condition));

		return updateSql.toString();
	}
	
	public static void main(String args[]) {
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("MOBILE_VALIDATE_CODE", "");
		setMap.put("MOBILE_VALIDATE_CODE_VALID_TIME", "");
		setMap.put("MOBILE", "asdasdas");
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", "asdasd");
		System.out.println(getUpdateSql("test_test", setMap, whereMap));
	}

}
