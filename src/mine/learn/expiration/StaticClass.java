package mine.learn.expiration;

/**
 * 详解java类的生命周期 http://blog.csdn.net/zhengzhb/article/details/7517213
 * 
 * @author WangFengwei
 * 
 */
public class StaticClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		/* 设置值 */
		StClassA.pubStString = "initial state";
		StClassA.setPriStString("initial state");

		/* 查看值 */
		StClassA.check();
		Thread.sleep(100000);
		StClassA.check();
	}

}

class StClassA {
	public static String pubStString;
	private static String priStString;

	public static String getPriStString() {
		return priStString;
	}

	public static void setPriStString(String priStString) {
		StClassA.priStString = priStString;
	}

	/**
	 * 查看变量的值.
	 */
	public static void check() {
		System.out.println("pubStString:\t" + pubStString);
		System.out.println("priStString:\t" + getPriStString());
	}

}
