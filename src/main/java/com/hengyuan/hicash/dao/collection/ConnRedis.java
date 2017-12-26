package com.hengyuan.hicash.dao.collection;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;



import com.hengyuan.hicash.dao.AbstractDBO;
import com.hengyuan.hicash.dao.dict.DataBaseType;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnRedis {
	
	private static Logger logger = Logger.getLogger(ConnManager.class);

	private static final ThreadLocal<Connection> threadLocal;

	private static Properties prop = new Properties();

//	private static DataSource dataSource;
	
	public static DataBaseType type = DataBaseType.MYSQL;
	
//	private static final String JNDINAME = "java:comp/env/jdbc/backService" ;
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

	static {
		
//		try {
//			
//			Context context = new InitialContext();
//			dataSource = (DataSource)context.lookup(JNDINAME);
//			
//			prop.load(ConnManager.class.getClassLoader().getResourceAsStream("jdbc.properties"));
//			
//		} catch (NamingException e) {
//			logger.error("连接池构造异常", e);
//		} catch (IOException e) {
//			logger.error("属性文件读取错误", e);
//		}
//		
//		threadLocal = new ThreadLocal<Connection>();
		try {
			prop.load(ConnManager.class.getClassLoader().getResourceAsStream("properties/redisjdbc.properties"));
		} catch (IOException e) {
			logger.error("属性文件读取错误", e);
		}
		
		dataSource.setUser(prop.getProperty("jdbc.mysql.username"));
		dataSource.setPassword(prop.getProperty("jdbc.mysql.password"));
		dataSource.setJdbcUrl(prop.getProperty("jdbc.mysql.url"));
		try {
			dataSource.setDriverClass(prop.getProperty("jdbc.mysql.driverClassName"));
		} catch (PropertyVetoException e) {
			logger.error("连接池构造异常", e);
			throw new IllegalStateException(e);
		}
		dataSource.setInitialPoolSize(Integer.parseInt(prop.getProperty("jdbc.initialPoolSize")));
		dataSource.setMinPoolSize(Integer.parseInt(prop.getProperty("jdbc.minPoolSize")));
		dataSource.setMaxPoolSize(Integer.parseInt(prop.getProperty("jdbc.maxPoolSize")));
		dataSource.setMaxStatements(Integer.parseInt(prop.getProperty("jdbc.maxStatements")));
		dataSource.setMaxIdleTime(Integer.parseInt(prop.getProperty("jdbc.maxidletime")));
		threadLocal = new ThreadLocal<Connection>();
	}

	public static Connection getConn() {
		Connection conn = threadLocal.get();
		try {
			if (conn == null || conn.isClosed()) {
				conn = dataSource.getConnection();
				threadLocal.set(conn);
			}
		} catch (SQLException e) {
			logger.error("获取连接异常", e);
			throw new IllegalStateException(e);
		}
		return conn;
	}

	public static void closeConn() {
		Connection conn = threadLocal.get();
		threadLocal.set(null);
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error("关闭连接异常", e);
			// throw new IllegalStateException(e);
		}
	}

	public static void beginTransaction() {
		try {
			getConn().setAutoCommit(false);
		} catch (SQLException e) {
			logger.error("开始事务异常", e);
			throw new IllegalStateException(e);
		}
	}

	public static void commit() {
		try {
			getConn().commit();
			getConn().setAutoCommit(true);
		} catch (SQLException e) {
			logger.error("提交事务异常", e);
			throw new IllegalStateException(e);
		}
	}

	public static void rollback() {
		try {
			getConn().rollback();
			getConn().setAutoCommit(true);
		} catch (SQLException e) {
			logger.error("事务回滚异常", e);
			throw new IllegalStateException(e);
		}
	}
	
	public static boolean call(String classname, String batchName) {
		CallableStatement call = null;
		try {
			call = getConn().prepareCall("{CALL " + classname + "}");
			return call.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (call != null) {
				try {
					call.close();
				} catch (SQLException e) {
					logger.error("CallableStatement关闭异常", e);
				}
			}
		}
	}

	

	public static <E> List<E> executeQuery(String sql, AbstractDBO<E> dao) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = getConn().createStatement();
			rs = stmt.executeQuery(sql);
			List<E> result = new ArrayList<E>();
			while (rs.next()) {
				result.add(dao.mapping(rs));
			}
			return result;
		} catch (SQLException e) {
			logger.error("查询异常，查询语句[" + sql + "]", e);
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				logger.error("statement关闭异常", e);
			}
		}
	}

	public static <E> E singleQuery(String sql, AbstractDBO<E> dao) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = getConn().createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return dao.mapping(rs);
			} else {
				return dao.mapping(null);
			}
		} catch (SQLException e) {
			logger.error("查询异常，查询语句[" + sql + "]", e);
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				logger.error("statement关闭异常", e);
			}
		}
	}

	public static <E> List<E> executeQuery(String sql, int firstRow,
			int maxRow, AbstractDBO<E> dao) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = getConn().createStatement();
			rs = stmt.executeQuery(sql);
			int CurrentRow = 0;
			List<E> result = new ArrayList<E>();
			while (rs.next()) {
				CurrentRow++;
				if (CurrentRow < firstRow) {
					continue;
				} else if (CurrentRow >= maxRow) {
					break;
				} else {
					E obj = dao.mapping(rs);
					result.add(obj);
				}
			}
			return result;
		} catch (SQLException e) {
			logger.error("查询异常，查询语句[" + sql + "]", e);
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				logger.error("statement关闭异常", e);
			}
		}
	}

	public static boolean execute(String sql) {
		Statement stmt = null;
		try {
			stmt = getConn().createStatement();
			return stmt.execute(sql);
		} catch (SQLException e) {
			logger.error("SQL异常,SQL语句[" + sql + "]", e);
			return false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					logger.error("statement关闭异常", e);
				}
			}
		}
	}

	public static int executeUpdate(String sql) {
		Statement stmt = null;
		try {
			stmt = getConn().createStatement();
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			logger.error("SQL异常,SQL语句[" + sql + "]", e);
			return 0;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					logger.error("statement关闭异常", e);
				}
			}
		}
	}
	
	
	
	/**
	 * 获取oracle sequence
	 * @param seqName
	 * @return
	 */
	public static long getSequence(String seqName) {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select " + seqName + ".nextval from dual";
		try {
			stmt = getConn().createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return rs.getLong(1);
			} else {
				return 0L;
			}		
		} catch (SQLException e) {
			logger.error("查询异常，查询语句[" + sql + "]", e);
			return 0L;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				logger.error("statement关闭异常", e);
			}
		}		
	}

	public static String getDate(Date date) {
		switch (type) {
		case MYSQL:
			return getMysqlDate(date);
		case ORACLE:
			return getOracleDate(date);
		default:
			return null;
		}
	}

	public static String getTime(Timestamp time) {
		switch (type) {
		case MYSQL:
			return getMysqlTime(time);
		case ORACLE:
			return getOracleTime(time);
		default:
			return null;
		}
	}

	public static String getKey(String seqName) {
		switch (type) {
		case MYSQL:
			return getMysqlIdentity();
		case ORACLE:
			return getOracleSequence(seqName);
		default:
			return null;
		}
	}
	
	private static String getMysqlIdentity() {
		return "DEFAULT";
	}
	
	private static String getMysqlDate(Date date) {
		return "date('" + date + "')";
	}
	
	private static String getMysqlTime(Timestamp time) {
		return "timestamp('" + time + "')";
	}

	
	
	private static String getOracleDate(Date date) {
		return "to_date('" + date + "', 'yyyy-mm-dd')";
	}

	private static String getOracleTime(Timestamp time) {
		return "to_timestamp('" + time + "', 'yyyy-mm-dd hh24:mi:ss.ff')";
	}

	private static String getOracleSequence(String seqName) {
		return seqName + ".nextval";
	}


}
