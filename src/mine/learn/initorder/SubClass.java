package mine.learn.initorder;

/**
 * 
 * 初始化顺序是：
 * 
 * （1） 先是父类的static变量和static初始化块
 * 
 * （2）然后是子类的static变量和static初始化块
 * 
 * （3）父类的实例变量、初始化快
 * 
 * （4）父类的构造方法
 * 
 * （5）子类的实例变量、初始化快
 * 
 * （6）子类构造方法
 * 
 * @src http://lgh3292.iteye.com/blog/368805
 */

/** 类A **/
class TestA {
	public TestA(String a) {
		System.out.println(a);
	}
}

/** 类B **/
class TestB {
	public TestB(String b) {
		System.out.println(b);
	}
}

/** 类 Parent **/
class Parent {
	private static TestA testA1 = new TestA("Parent的静态变量");
	static {
		System.out.println("Parent的静态初始化模块");
	}
	private TestA testA2 = new TestA("Parent变量");
	{
		System.out.println("Parent的初始化模块");
	}

	public Parent() {
		System.out.println("Parent的构造函数");
	}
}

/** Parent的子类SubClass **/
public class SubClass extends Parent {
	private static TestA testA1 = new TestA("SubClass的静态变量1");
	static {
		System.out.println("SubClass的静态初始化模块1");
	}
	private static TestA testA2 = new TestA("SubClass的静态变量2");
	static {
		System.out.println("SubClass的静态初始化模块2");
	}
	private TestA testA3 = new TestA("SubClass变量1");
	{
		System.out.println("SubClass的初始化模块1");
	}
	private TestA testA4 = new TestA("SubClass变量2");
	{
		System.out.println("SubClass的初始化模块2");
	}

	public SubClass() {
		System.out.println("SubClass的构造函数");
	}

	public static void main(String[] args) {
		SubClass subClass = new SubClass();
	}
}
