package com.hongwei.futures.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hongwei.futures.model.FuServer;

public class JDBCUtil {

	// static {
	// try {
	// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	// 2连接数据库
	public static Connection getConnection(FuServer server) {
		Connection con = null;
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		} catch (ClassNotFoundException ex) {
//			ex.printStackTrace();
//		}
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://" + server.getDbIp() + ":" + server.getDbPort() + ";databaseName=" + server.getDbName() + ";Connect Timeout=500";
			System.out.println(url);
			if (null == con || con.isClosed()) {
				con = DriverManager.getConnection(url, server.getDbUsername(), server.getDbPassword());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	// 3释放数据库资源
	public static void release(ResultSet rs, Statement stmt, Connection con) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
			;
		}
		if (null != stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
			;
		}
		if (null != con) {
			try {
				con.close();
			} catch (SQLException e) {
			}
			;
		}
	}

	public static void release(Object o) {
		if (o instanceof ResultSet) {
			try {
				((ResultSet) o).close();
			} catch (SQLException e) {
			}
		} else if (o instanceof Statement) {
			try {
				((Statement) o).close();
			} catch (SQLException e) {
			}
		} else if (o instanceof Connection) {
			try {
				((Connection) o).close();
			} catch (SQLException e) {
			}
		}
	}
}
