package file;

import java.io.File;
import java.io.IOException;

public class fileope {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("d:/tmp/tmp/tmp/name1xt");
		f.getParentFile().mkdirs();
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("OK");
		

	}

}
