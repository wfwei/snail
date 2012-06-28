package io;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileOperTest {

	/**
	 * 测试结果
	 * 
	 * 1.file's long:16kb
	 * 
	 * fileWrite's time----------36 outputStreamTest's time----------167
	 * bufferedOutputTest's time----------17 bufferedWriteTest's
	 * time----------14 bufferedWrite And FileWriterTest's time----------9
	 * bufferedWrite And BufferedOutputStreamTest's time----------12
	 * 
	 * 2.file's long:1600kb fileWrite's time----------69 outputStreamTest's
	 * time----------1282 bufferedOutputTest's time----------68
	 * bufferedWriteTest's time----------40 bufferedWrite And FileWriterTest's
	 * time----------52 bufferedWrite And BufferedOutputStreamTest's
	 * time----------37
	 * 
	 * 3.file's long:16000kb fileWrite's time----------555 outputStreamTest's
	 * time----------12448 bufferedOutputTest's time----------599
	 * bufferedWriteTest's time----------346 bufferedWrite And FileWriterTest's
	 * time----------316 bufferedWrite And BufferedOutputStreamTest's
	 * time----------358
	 * 
	 * 4.file's long:160000kb
	 * 
	 * fileWrite's time----------5203 outputStreamTest's time----------127182
	 * bufferedOutputTest's time----------5972 bufferedWriteTest's
	 * time----------3445 最优 bufferedWrite And FileWriterTest's
	 * time----------5904 bufferedWrite And BufferedOutputStreamTest's
	 * time----------5353
	 * 
	 * 
	 * 5.file's long:1600000kb
	 * 
	 * fileWrite's time----------50416 outputStreamTest's time----------1303242
	 * bufferedOutputTest's time----------60931 bufferedWriteTest's
	 * time----------46697 bufferedWrite And FileWriterTest's
	 * time----------48710 bufferedWrite And BufferedOutputStreamTest's
	 * time----------64354
	 */

	public static void main(String[] args) {

		String str = "abcdefghiJKLMN！";
		FileOperTest t = new FileOperTest();
		long start = 0, end = 0;
		int count = 1000000;

		start = System.currentTimeMillis();
		t.fileWriteTest(count, str);
		end = System.currentTimeMillis();
		System.out.println("fileWrite's time---------" + (start - end));

		start = System.currentTimeMillis();
		t.outputStreamTest(count, str);
		end = System.currentTimeMillis();
		System.out.println("outputStreamTest's time---------" + (start - end));

		start = System.currentTimeMillis();
		t.bufferedOutputTest(count, str);
		end = System.currentTimeMillis();
		System.out
				.println("bufferedOutputTest's time---------" + (start - end));

		start = System.currentTimeMillis();
		t.bufferedWriteTest(count, str);
		end = System.currentTimeMillis();
		System.out.println("bufferedWriteTest's time---------" + (start - end));

		start = System.currentTimeMillis();
		t.bufferedWriteAndFileWriterTest(count, str);
		end = System.currentTimeMillis();
		System.out.println("bufferedWrite And FileWriterTest's time---------"
				+ (start - end));

		start = System.currentTimeMillis();
		t.bufferedWriteAndBufferedOutputStreamTest(count, str);
		end = System.currentTimeMillis();
		System.out
				.println("bufferedWrite And BufferedOutputStreamTest's time---------"
						+ (start - end));

	}

	/**
	 * 1 按字节写入 FileOutputStream
	 * 
	 * @param count
	 *            写入循环次数
	 * @param str
	 *            写入字符串
	 */
	public void outputStreamTest(int count, String str) {
		File f = new File("f:test1.txt");
		OutputStream os = null;
		try {
			os = new FileOutputStream(f);
			for (int i = 0; i < count; i++) {
				os.write(str.getBytes());
			}
			os.flush();
			System.out.println("file's long:" + f.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 2 按字节缓冲写入 BufferedOutputStream
	 * 
	 * @param count
	 *            写入循环次数
	 * @param str
	 *            写入字符串
	 */
	public void bufferedOutputTest(int count, String str) {
		File f = new File("f:test2.txt");
		BufferedOutputStream bos = null;
		try {
			OutputStream os = new FileOutputStream(f);
			bos = new BufferedOutputStream(os);
			for (int i = 0; i < count; i++) {
				bos.write(str.getBytes());
			}
			bos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 3 按字符写入 FileWriter
	 * 
	 * @param count
	 *            写入循环次数
	 * @param str
	 *            写入字符串
	 */
	public void fileWriteTest(int count, String str) {
		File f = new File("f:test.txt");
		Writer writer = null;
		try {
			writer = new FileWriter(f);
			for (int i = 0; i < count; i++) {
				writer.write(str);
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 4 按字符缓冲写入 BufferedWriter
	 * 
	 * @param count
	 *            写入循环次数
	 * @param str
	 *            写入字符串
	 */
	public void bufferedWriteTest(int count, String str) {
		File f = new File("f:test3.txt");
		OutputStreamWriter writer = null;
		BufferedWriter bw = null;
		try {
			OutputStream os = new FileOutputStream(f);
			writer = new OutputStreamWriter(os);
			bw = new BufferedWriter(writer);
			for (int i = 0; i < count; i++) {
				bw.write(str);
			}
			bw.flush();
			if (f.exists()) {
				f.delete();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 5 按字符缓冲写入 BufferedWriter and BufferedOutputStream
	 * 
	 * @param count
	 *            写入循环次数
	 * @param str
	 *            写入字符串
	 */
	public void bufferedWriteAndBufferedOutputStreamTest(int count, String str) {
		File f = new File("f:test4.txt");
		BufferedOutputStream bos = null;
		OutputStreamWriter writer = null;
		BufferedWriter bw = null;
		try {
			OutputStream os = new FileOutputStream(f);
			bos = new BufferedOutputStream(os);
			writer = new OutputStreamWriter(bos);
			bw = new BufferedWriter(writer);
			for (int i = 0; i < count; i++) {
				bw.write(str);
			}
			bw.flush();
			if (f.exists()) {
				f.delete();
				System.out.println("delete---");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 6 按字符缓冲写入 BufferedWriter and FileWriter
	 * 
	 * @param count
	 *            写入循环次数
	 * @param str
	 *            写入字符串
	 */
	public void bufferedWriteAndFileWriterTest(int count, String str) {
		File f = new File("f:test5.txt");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			for (int i = 0; i < count; i++) {
				bw.write(str);
			}
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				if (f.exists()) {
					f.delete();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
