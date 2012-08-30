package mine.acc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public final class MySqlDB {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://10.214.52.126/check?useUnicode=true&characterEncoding=UTF-8";
	private static String user = "wangfengwei";
	private static String password = "wangfengwei";
	private static Connection con = null;

	private static final Logger LOGGER = Logger.getLogger(MySqlDB.class);

	private static MySqlDB mysql = null;

	private MySqlDB() {
	}

	public static void initDB() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		LOGGER.info("initDB Success!");
	}

	public static MySqlDB getInstance() {
		synchronized (mysql) {
			if (mysql == null) {
				mysql = new MySqlDB();
			}
		}
		return mysql;
	}

	public static Connection getCon() {
		if (null == con) {
			initDB();
		}
		return con;
	}

	public static void closeDB() {
		try {
			if (null != con && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			LOGGER.warn(e.toString());
		}
	}
}
