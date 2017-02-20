package com.vbusiness.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 数据库操作工具类
 * 
 * @author mango
 *
 */
public class DBUtil {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = ConnectionManager.getInstance().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection connection, PreparedStatement psmtStatement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (psmtStatement != null) {
				psmtStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int executeUpdate(String sql, Object... objects) {
		int result = 0;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = getConnection();
			psmt = (PreparedStatement) conn.prepareStatement(sql);
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					psmt.setObject(i + 1, objects[i]);//下标从1开始
				}
			}
			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, psmt, null);
		}
		return result;
	}

	public static Object executeQuery(String sql, IResultSetUtil rsHandler, Object... objects) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					preparedStatement.setObject(i + 1, objects[i]);

				}
			}
			resultSet = preparedStatement.executeQuery();

			return rsHandler.doHandler(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, preparedStatement, resultSet);
		}
		return null;
	}

	public static Object executeQuery(String sql, Object... objects) {
		return executeQuery(sql, new IResultSetUtil() {
			@Override
			public Object doHandler(ResultSet rs) throws SQLException {
				Object object = null;
				if (rs.next()) {
					object = rs.getObject(1);
				}
				return object;
			}
		}, objects);
	}

}
