package mine.learn.snippet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexRel {

	/**
	 * String.replaceAll(regex,replacement)
	 * 第一个参数是正则表达式，所有符合正则表达式的子字符串都被替换成replacement
	 * ，replacement也不是普通字符串，它可以以$0~$8方式引用被匹配字符串的分组
	 * ，如果你期望replacement是个普通字符串，必须对replacement中的$和\进行转义，以免被JAVA误认为引用分组
	 * 
	 * String.replace(CharSequence a, CharSequence b)
	 * 是直接全文搜索,直接将所有与第一个参数相等的字符串替换为第二个参数 
	 * public String replace(CharSequence target, CharSequence replacement) { 
	 * return Pattern.compile(target.toString(), Pattern.LITERAL).matcher(
	 * this).replaceAll(Matcher.quoteReplacement(replacement.toString())); }
	 */
	public static void StringReplaceTest() {
		String regex = "\\$\\{(.*?)\\}";
		String str = "my name is ${name}";
		String repl = "$1";
		/* 以下两种形式是等价的 */
		System.out.println(str.replaceAll(regex, repl));
		System.out
				.println(Pattern.compile(regex).matcher(str).replaceAll(repl));

		/**
		 * String java.util.regex.Matcher.quoteReplacement(String s).
		 * <P>
		 * Returns a literal replacement String for the specified String. This
		 * method produces a String that will work as a literal replacement s in
		 * the appendReplacement method of the Matcher class. The String
		 * produced will match the sequence of characters in s treated as a
		 * literal sequence. Slashes ('\') and dollar signs ('$') will be given
		 * no special meaning.
		 */
		System.out
				.println(str.replaceAll(regex, Matcher.quoteReplacement(repl)));
		System.out.println(Pattern.compile(regex).matcher(str)
				.replaceAll(Matcher.quoteReplacement(repl)));

	}

	public static void main(String[] args) {
		StringReplaceTest();
	}

}
