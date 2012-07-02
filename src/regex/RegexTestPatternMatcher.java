package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTestPatternMatcher {

	public static void main(String[] args) {
		Pattern p = Pattern.compile("(ca)(t[s]?)", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher("one Cat,two caits in the yard");

		/*进行第一次查找，matcher中存放第一次查找内容*/
		boolean first_res = m.find();
		System.out.println("该次查找获得匹配组的数量为：" + m.groupCount());
		for (int i = 1; i <= m.groupCount(); i++) {
			System.out.println("第" + i + "组的子串内容为： " + m.group(i));
		}
		System.out.println("0:" + m.group(0));
		System.out.println("==================================");

		/*进行第二次查找，matcher中存放第二次查找内容*/
		boolean second_res = m.find();
		System.out.println("该次查找获得匹配组的数量为：" + m.groupCount());
		for (int i = 1; i <= m.groupCount(); i++) {
			System.out.println("第" + i + "组的子串内容为： " + m.group(i));
		}
		System.out.println("0:" + m.group(0));

	}
}
