package regex.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
	public static void main(String args[]) {
		Pattern p = Pattern.compile("cat");
		Matcher m = p.matcher("one cat two cats in the yard");
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "dog");
		}
		m.appendTail(sb);
		System.out.println(sb.toString());
	}
}
