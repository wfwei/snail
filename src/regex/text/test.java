package regex.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
	public static void main(String args[]) {
		String str = "@charset \"gb2312\";";
		System.out.println(str.replaceAll("@charset[^;]*;",
				"@charset \"utf-8\";"));

		// Pattern p = Pattern.compile("cat");
		// Matcher m = p.matcher("one cat two cats in the yard");
		// StringBuffer sb = new StringBuffer();
		// while (m.find()) {
		// m.appendReplacement(sb, "dog");
		// }
		// m.appendTail(sb);
		// System.out.println(sb.toString());
	}
}
