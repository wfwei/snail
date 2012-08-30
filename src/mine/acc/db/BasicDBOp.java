package mine.acc.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 一些常用的数据库操作
 * 
 * @author WangFengwei
 * 
 */
public class BasicDBOp {
	private static Connection con = MySqlDB.getCon();
	private static Logger logger = Logger.getLogger(BasicDBOp.class);

	/**
	 * 插入一条rss item，数据库中link属性定义了唯一性索引，索引没有去重
	 * 
	 * @author WangFengwei
	 */
	public static void insertOp(int id, String content, Date date) {
		String sql = "insert into test(title, pubDate, status) values (?, ?, ?)";
		try {
			java.sql.PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.setString(2, rmInvalidChar(content));
			// MARK mysql存储日期时间类型，java.sql.Date只有日期！！！
			pStmt.setString(3, new java.text.SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(date));
			pStmt.executeUpdate();
			pStmt.close();
		} catch (SQLException e) {
			logger.warn("insert rss item error:\t" + e.toString());
		}
	}

	private static String rmInvalidChar(String input) {
		if (input == null) {
			return null;
		}
		return input.trim().replaceAll("'", "’");
	}
}
