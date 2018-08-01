package com.hongwei.futures.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.codehaus.xfire.client.Client;

public class MYSQLDBHelper {


	public static Connection conn;
	
	public static Connection getConnection() {
		try {

			String driver = "com.mysql.jdbc.Driver"; 

			InputStream in = Client.class.getClassLoader().getResourceAsStream("application.properties");
   		 	Properties properties = new Properties();
   		 	properties.load(in);
			
			String url = properties.getProperty("dataSource.url");

			String user = properties.getProperty("dataSource.user");

			String password = properties.getProperty("dataSource.password");

			Class.forName(driver); 

			if (null == conn) {

				conn = DriverManager.getConnection(url, user, password);

			}

		} catch (ClassNotFoundException e) {

			System.out.println("Sorry,can't find the Driver!");

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return conn;

	}

	/**
	 * 
	 * ��ɾ�ġ�Add��Del��Update��
	 * 
	 * 
	 * 
	 * @param sql
	 * 
	 * @return int
	 */

	public static int executeNonQuery(String sql) {

		int result = 0;

		Connection conn = null;

		Statement stmt = null;

		try {

			conn = getConnection();

			stmt = conn.createStatement();

			result = stmt.executeUpdate(sql);

		} catch (SQLException err) {

			err.printStackTrace();

//			free(null, stmt, conn);

		} finally {

//			free(null, stmt, conn);

		}

		return result;

	}

	/**
	 * 
	 * ��ɾ�ġ�Add��Delete��Update��
	 * 
	 * 
	 * 
	 * @param sql
	 * 
	 * @param obj
	 * 
	 * @return int
	 */

	public static int executeNonQuery(String sql, Object... obj) {

		int result = 0;

		Connection conn = null;

		PreparedStatement pstmt = null;

		try {

			conn = getConnection();

			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < obj.length; i++) {

				pstmt.setObject(i + 1, obj[i]);

			}

			result = pstmt.executeUpdate();

		} catch (SQLException err) {

			err.printStackTrace();
			
			return result;

//			free(null, pstmt, conn);

		} finally {

//			free(null, pstmt, conn);

		}

		return result;

	}

	/**
	 * 
	 * �顾Query��
	 * 
	 * 
	 * 
	 * @param sql
	 * 
	 * @return ResultSet
	 */

	public static ResultSet executeQuery(String sql) {

//		Connection conn = null;

		Statement stmt = null;

		ResultSet rs = null;

		try {

			getConnection();

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

		} catch (SQLException err) {

			err.printStackTrace();

			free(rs, stmt, conn);

		}

		return rs;

	}

	/**
	 * 
	 * �顾Query��
	 * 
	 * 
	 * 
	 * @param sql
	 * 
	 * @param obj
	 * 
	 * @return ResultSet
	 */

	public static ResultSet executeQuery(String sql, Object... obj) {

//		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {

			getConnection();

			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < obj.length; i++) {

				pstmt.setObject(i + 1, obj[i]);

			}

			rs = pstmt.executeQuery();

		} catch (SQLException err) {

			err.printStackTrace();

			free(rs, pstmt, conn);

		} 

		return rs;

	}

	/**
	 * 
	 * �жϼ�¼�Ƿ����
	 * 
	 * 
	 * 
	 * @param sql
	 * 
	 * @return Boolean
	 */

	public static Boolean isExist(String sql) {

		ResultSet rs = null;

		try {

			rs = executeQuery(sql);

			rs.last();

			int count = rs.getRow();

			if (count > 0) {

				return true;

			} else {

				return false;

			}

		} catch (SQLException err) {

			err.printStackTrace();

			free(rs);

			return false;

		} finally {

			free(rs);

		}

	}

	/**
	 * 
	 * �жϼ�¼�Ƿ����
	 * 
	 * 
	 * 
	 * @param sql
	 * 
	 * @return Boolean
	 */

	public static Boolean isExist(String sql, Object... obj) {

		ResultSet rs = null;

		try {

			rs = executeQuery(sql, obj);

			rs.last();

			int count = rs.getRow();

			if (count > 0) {

				return true;

			} else {

				return false;

			}

		} catch (SQLException err) {

			err.printStackTrace();

			free(rs);

			return false;

		} finally {

			free(rs);

		}

	}

	/**
	 * 
	 * ��ȡ��ѯ��¼��������
	 * 
	 * 
	 * 
	 * @param sql
	 * 
	 * @return int
	 */

	public static int getCount(String sql) {

		int result = 0;

		ResultSet rs = null;

		try {

			rs = executeQuery(sql);

			rs.last();

			result = rs.getRow();

		} catch (SQLException err) {

			free(rs);

			err.printStackTrace();

		} finally {

			free(rs);

		}

		return result;

	}

	/**
	 * 
	 * ��ȡ��ѯ��¼��������
	 * 
	 * 
	 * 
	 * @param sql
	 * 
	 * @param obj
	 * 
	 * @return int
	 */

	public static int getCount(String sql, Object... obj) {

		int result = 0;

		ResultSet rs = null;

		try {

			rs = executeQuery(sql, obj);

			rs.last();

			result = rs.getRow();

		} catch (SQLException err) {

			err.printStackTrace();

		} finally {

			free(rs);

		}

		return result;

	}

	/**
	 * 
	 * �ͷš�ResultSet����Դ
	 * 
	 * 
	 * 
	 * @param rs
	 */

	public static void free(ResultSet rs) {

		try {

			if (rs != null && !rs.isClosed()) {

				rs.close();

			}

		} catch (SQLException err) {

			err.printStackTrace();

		}

	}

	/**
	 * 
	 * �ͷš�Statement����Դ
	 * 
	 * 
	 * 
	 * @param st
	 */

	public static void free(Statement st) {

		try {

			if (st != null && !st.isClosed()) {

				st.close();

			}

		} catch (SQLException err) {

			err.printStackTrace();

		}

	}

	/**
	 * 
	 * �ͷš�Connection����Դ
	 * 
	 * 
	 * 
	 * @param conn
	 */

	public static void free(Connection conn) {

		try {

			if (null != conn && !conn.isClosed()) {

				conn.close();

			}

		} catch (SQLException err) {

			err.printStackTrace();

		}

	}

	/**
	 * 
	 * �ͷ����������Դ
	 * 
	 * 
	 * 
	 * @param rs
	 * 
	 * @param st
	 * 
	 * @param conn
	 */

	public static void free(ResultSet rs, Statement st, Connection conn) {

		free(rs);

		free(st);

		free(conn);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
