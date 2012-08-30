package mine.acc.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 常用的数据库操作
 * 
 * @author WangFengwei
 * 
 */
public class BasicDBOp {
	private static Connection con = MySqlDB.getCon();
	private static final Logger LOGGER = Logger.getLogger(BasicDBOp.class);

	/**
	 * 插入rss item，数据库中link属性定义了唯一索引，索引没有去排查冲突
	 * 
	 * @author WangFengwei
	 */
	public static void insertOp(final int rssId, final String content,
			final Date date) {
		final String sql = "insert into test(title, pubDate, status) values (?, ?, ?)";
		try {
			final java.sql.PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, rssId);
			pStmt.setString(2, rmInvalidChar(content));
			// MARK mysql存储日期时间类型，java.sql.Date只有日期！！
			pStmt.setString(3, new java.text.SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(date));
			pStmt.executeUpdate();
			pStmt.close();
		} catch (SQLException e) {
			LOGGER.warn("insert rss item error:\t" + e.toString());
		}
	}

	private static String rmInvalidChar(final String input) {
		String output = null;
		if (input != null) {
			output = input.trim().replaceAll("'", "\'");
		}
		return output;
	}
}
