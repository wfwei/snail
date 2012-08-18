package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import db.MySqlDB;

public class ExtractInfo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		File f = new File(
				"D:\\pubfolder\\work\\ia-pro\\svn\\中国工信部的行标\\2012工信部标准\\网站设计无障碍评级测试方法-20120315.txt");
		if (!f.exists()) {
			System.err.println("File not found!");
			return;
		}
		Statement stmt;
		StringBuffer sb;
		String tempString;
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			sb = new StringBuffer();
			tempString = null;
			while ((tempString = br.readLine()) != null) {
				sb.append(tempString);
			}
			br.close();
			String regex = "预期结果：(.*?)测试说明：";
			Pattern pat = Pattern.compile(regex);
			Matcher mat = pat.matcher(sb.toString());
			Connection con = MySqlDB.getCon();
			stmt = con.createStatement();
			String query = "select * from rule  where check_method!='null'";
			ResultSet rs = stmt.executeQuery(query);
			ArrayList<Integer> ruleIds = new ArrayList<Integer>();
			while (rs.next()) {
				ruleIds.add(rs.getInt(1));
			}
			System.out.println(ruleIds.size());
			for (int idx = 0; mat.find(); idx++) {
				tempString = mat.group(1);
				query = "update rule set expected_result = '" + tempString
						+ "' where id=" + ruleIds.get(idx);
				System.out.println(idx + "\t" + query);
				stmt.executeUpdate(query);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
