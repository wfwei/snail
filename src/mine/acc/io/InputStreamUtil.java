package mine.acc.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 有关InputStream的操作
 *  
 * @author WangFengwei
 * 
 */
public class InputStreamUtil {

	final static int BUFFER_SIZE = 4096;

	/**
	 * 将InputStream转换成某种字符编码的String
	 * 
	 * @param in
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String InputStreamTOString(InputStream in, String encoding)
			throws Exception {

		return new String(InputStreamTOByte(in), "ISO-8859-1");
	}

	/**
	 * 将InputStream转换成byte数组
	 * 
	 * @param in
	 *            InputStream
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] InputStreamTOByte(InputStream in) throws IOException {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return outStream.toByteArray();
	}
	
	/**
	 * 将Byte转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static InputStream ByteTOInputStream(byte [] b) throws Exception {

		ByteArrayInputStream is = new ByteArrayInputStream(b);
		return is;
	}

	public static void main(String[] args) throws Exception {
		byte [] b = "1".getBytes("utf-8");
		ByteArrayInputStream is = new ByteArrayInputStream(b);
		b[0] = 50;//2
		System.out.println(InputStreamTOString(is,"utf-8"));
	}
}
