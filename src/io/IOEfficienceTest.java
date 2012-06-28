package io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.*;

public class IOEfficienceTest {

	public static void main(String[] args) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		FileWriter fw = null;
		int count = 10000;// 写文件行数
		try {
			fos = new FileOutputStream(File.createTempFile("res_fos", "tmp",
					new File("d:/temp/")));
			long begin = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				fos.write("JavaTest".getBytes());
			}
			fos.close();
			long end = System.currentTimeMillis();
			System.out.println("FileOutputStream执行耗时:" + (end - begin) + " 毫秒");

			bos = new BufferedOutputStream(new FileOutputStream(File.createTempFile("res_bos", "tmp",
					new File("d:/temp/"))),90000);
			
			long begin0 = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				bos.write("JavaTest".getBytes());
			}
			bos.flush();
			bos.close();
			long end0 = System.currentTimeMillis();
			System.out.println("BufferedOutputStream执行耗时:" + (end0 - begin0)
					+ " 毫秒");

			fw = new FileWriter(File.createTempFile("res_fw", "tmp",
					new File("d:/temp/")));
			long begin3 = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				fw.write("JavaTest");
			}
			fw.close();
			long end3 = System.currentTimeMillis();
			System.out.println("FileWriter执行耗时:" + (end3 - begin3) + " 毫秒");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
				bos.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
