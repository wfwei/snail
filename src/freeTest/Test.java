package freeTest;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("TEST".getBytes().length);
		String href = "af;";
		href = href.substring(0, href.length());
		System.out.println(href);
	}

}
