package io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;

public class StreamUtil {

	public static void StreamToString() throws Exception {
		// 方法一：通过制定stream的输出目的地
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(bout, true);
		pw.println("this will write into bout");
		System.out.println(bout.toString());

		// 方法二：通过读取
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
	}

	public static void StringToStream() throws Exception {
		// 方法一：通过把字符串设为流的源
		StringReader sr = new StringReader("this will be readed to sr");
		char[] cbuf = new char[10];
		int nRead = 0;
		while ((nRead = sr.read(cbuf)) != -1) {
			System.out.println(new String(cbuf, 0, nRead));
		}

		// 方法二：通过转化为字节
		byte[] bytes = "1234".getBytes();
		InputStream inputStream = new ByteArrayInputStream(bytes);
		while ((nRead = inputStream.read(bytes)) != -1) {
			System.out.println(new String(bytes, 0, nRead));
		}
	}

	/**
	 * @author WangFengwei
	 * @time 2012年5月28日
	 */
	public static void main(String[] args) throws Exception {
		// StringToStream();
		// StreamToString();
	}

}
