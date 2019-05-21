package dev.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dev.pizzeria.exception.TechnicalException;

public class ConnectionMgr {

	private static ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
	private static Connection conn;

	public static Connection getInstance() {

		try {
			if (conn == null || conn.isClosed()) {

				String url = monFichierConf.getString("database.url");
				String userName = monFichierConf.getString("database.user");
				String password = monFichierConf.getString("database.password");

				conn = DriverManager.getConnection(url, userName, password);
			}
		} catch (SQLException e) {
			throw new TechnicalException("Impossible de se connecter à la base", e);
		}

		return conn;
	}
}
