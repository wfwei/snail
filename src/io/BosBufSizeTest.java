package io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class BosBufSizeTest extends BufferedOutputStream {

	public BosBufSizeTest(OutputStream out) {

		super(out);

		System.out.println("buf size: " + super.buf.length);
		System.out.println("count: " + super.count);

	}

	public static void main(String[] args) {

		try {
			new BosBufSizeTest(new FileOutputStream(""));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
