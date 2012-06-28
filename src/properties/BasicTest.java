package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BasicTest {

	public static void main(String[] args) {
		try {
			long start = System.currentTimeMillis();
			InputStream is = new FileInputStream("src/test.properties");
			Properties p = new Properties();
			p.load(is);
			is.close();
			System.out.println("author : " + p.getProperty("author"));
			p.list(System.out); 
			long end = System.currentTimeMillis();
			System.out.println("Cost : " + (end - start));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

}
