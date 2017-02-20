package com.vbusiness.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

/**
 * 数据库链接对象管理类
 * 
 * @author mango
 *
 */
public class ConnectionManager {

	private static ConnectionManager instance;

	private ComboPooledDataSource ds;

	private ConnectionManager() throws Exception {
		ds = new ComboPooledDataSource("mysql");
	}

	public static final ConnectionManager getInstance() {
		if (instance == null) {
			try {
				instance = new ConnectionManager();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public synchronized final Connection getConnection() {
		try {
			return (Connection) ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void finalize() throws Throwable {
		DataSources.destroy(ds);
		super.finalize();
	}
}
