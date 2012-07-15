package db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class MySqlDB {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://10.214.52.126/check?useUnicode=true&characterEncoding=UTF-8";
	private static String user = "wangfengwei";
	private static String password = "wangfengwei";
	private static Connection con = null;

	public static void initDB() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("initDB Success!");
	}

	public static Connection getCon() {
		if (null == con)
			initDB();
		return con;
	}

	public static void closeDB() {
		try {
			if (con == null)
				return;
			if (false == con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		initDB();

	}
}
