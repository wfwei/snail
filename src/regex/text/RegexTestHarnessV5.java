package regex.text;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTestHarnessV5 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			// System.out.printf("%nEnter your regex: ");
			// Pattern pattern = Pattern.compile(scanner.nextLine());
			Pattern pattern = Pattern
					.compile("(href|src)\\s*=\\s*\"(/|((http://)?([^.]*.)?(baidu.com)[/]?))([^\"]*\")");
			Matcher matcher = pattern.matcher("first line src = \"http://www.baidu.com/asfsd/fsdfsd/\" endline \nsecond line href = \"/asfsd/fsdfsd\" endline ");
//			while (matcher.find()) {
//				for (int i = 0; i <= matcher.groupCount(); i++) {
//					System.out.println(matcher.group(i));
//				}
//				
//			}
			StringBuffer sb = new StringBuffer();
			while(matcher.find()){
				matcher.appendReplacement(sb, matcher.group(1)+"=\"./"+matcher.group(matcher.groupCount()));
				if(sb.charAt(sb.length()-2) == '/')
					 sb.insert(sb.length()-1,"index.html");
					
			}
			matcher.appendTail(sb);
			
			System.out.println(sb.toString());
			
			// boolean found = false;
			//System.out.println(matcher.replaceAll(matcher.group(1)+"=\"./"));
			// while (matcher.find()) {
			// System.out.printf("I found the text \"%s\" starting at index %d and ending at index %d.%n",
			// matcher.group(), matcher.start(), matcher.end());
			// found = true;
			// }
			// if (!found) {
			// System.out.printf("No match found.%n");
			// }
			break;
		}
	}
}