package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @describe 
 *    本类是用来测试JDK中java.util.regex包中Pattern和Matcher两个类
 * 的常用方法。
 * 
 * @author LoongHua
 *
 * @date 2009年4月13日
 */

public class JavaRegex {
	
	/**
	 * 创建四个Pattern对象。
	 */
	public static void createPattern(){
		String regex=".*";//正则表达式字符串。
		
		//将给定的正则表达式编译到模式中
		Pattern pattern1=Pattern.compile(regex);
		/*
		 * 将给定的正则表达式编译到具有给定标志的模式中。
		 * UNIX_LINES 
		 * 		启用 Unix 行模式。 在此模式中，.、^ 和 $ 的行为中仅识别 '\n' 行结束符。
		 *  
		 *      通过嵌入式标志表达式 (?d) 也可以启用 Unix 行模式。
		 *      
		 * CASE_INSENSITIVE
		 * 		启用不区分大小写的匹配。 
         *      默认情况下，不区分大小写的匹配假定仅匹配 US-ASCII 字符集中的字符。
         *      可以通过指定 UNICODE_CASE 标志连同此标志来启用 Unicode 感知的、不区分大小写的匹配。 
         *		通过嵌入式标志表达式  (?i) 也可以启用不区分大小写的匹配。
         * 
         *		指定此标志可能对性能产生一些影响。
         *
         * COMMENTS
         *      模式中允许空白和注释。 
		 *		此模式将忽略空白和在结束行之前以 # 开头的嵌入式注释。
		 * 
		 *		通过嵌入式标志表达式  (?x) 也可以启用注释模式。 
		 *
		 * MULTILINE
		 *      启用多行模式。
		 *      在多行模式中，表达式 ^ 和 $ 仅分别在行结束符前后匹配，或者在输入序列的结尾处匹配。
		 *      默认情况下，这些表达式仅在整个输入序列的开头和结尾处匹配。
		 *      
		 *      通过嵌入式标志表达式 (?m) 也可以启用多行模式。
		 *      
		 * LITERAL
		 *      启用模式的字面值解析
		 *      指定此标志后，指定模式的输入字符串就会作为字面值字符序列来对待。
		 *      输入序列中的元字符或转义序列不具有任何特殊意义。
		 *      
		 *      标志 CASE_INSENSITIVE 和 UNICODE_CASE 在与此标志一起使用时将对匹配产生影响。其他标志都变得多余了。
		 *      不存在可以启用字面值解析的嵌入式标志字符。
		 *      
		 * DOTALL  
		 *      启用 dotall 模式。
		 *      在 dotall 模式中，表达式 . 可以匹配任何字符，包括行结束符。默认情况下，此表达式不匹配行结束符。
		 *		通过嵌入式标志表达式 (?s) 也可以启用 dotall 模式（s 是 "single-line" 模式的助记符，在 Perl 中也使用它）。 
		 * 
		 * UNICODE_CASE
		 *      启用 Unicode 感知的大小写折叠。
		 *      指定此标志后，由 CASE_INSENSITIVE 标志启用时，不区分大小写的匹配将以符合 Unicode Standard 的方式完成。
		 *      默认情况下，不区分大小写的匹配假定仅匹配 US-ASCII 字符集中的字符。
		 *      通过嵌入式标志表达式 (?u) 也可以启用 Unicode 感知的大小写折叠。
		 *      指定此标志可能对性能产生影响。
		 *      
		 * CANON_EQ
		 *      启用规范等价。
		 *      指定此标志后，当且仅当其完整规范分解匹配时，两个字符才可视为匹配。
		 *      例如，当指定此标志时，表达式 "a\u030A" 将与字符串 "\u00E5" 匹配。默认情况下，匹配不考虑采用规范等价。
		 *      不存在可以启用规范等价的嵌入式标志字符。
		 *      指定此标志可能对性能产生影响。
		 *
		 */
		Pattern pattern2=Pattern.compile(regex,Pattern.MULTILINE);
		Pattern pattern3=Pattern.compile(regex,Pattern.MULTILINE|Pattern.DOTALL);
		Pattern pattern4=Pattern.compile(regex,Pattern.MULTILINE|Pattern.DOTALL|Pattern.CASE_INSENSITIVE);
	}

	/**
	 * 创建Matcher对象
	 */
	public static void createMatcher(){
		String regex=".*";//正则表达式字符串。
		String input="<a href='http://www.google.com'>Google</a>";
		//将给定的正则表达式编译到模式中
		Pattern pattern=Pattern.compile(regex);
		//用此模式创建匹配给定输入的匹配器。
		Matcher matcher=pattern.matcher(input);
	}
	
	/**
	 * 测试Pattern类 matcher作用：创建匹配给定输入与此模式的匹配器。	
	 * 			    matches作用：编译给定正则表达式并尝试将给定输入与其匹配。
	 */
	public static void testPatternMethod1(){
		String regex="<a[^>]*>(.*)</a>";//正则表达式字符串。
		String input="<a href='http://www.baidu.com'>百度</a>";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		boolean b1 = m.matches();
		System.out.println("第一种方式匹配的结果是："+b1);
		 
		boolean b2=Pattern.matches(regex, input);
		System.out.println("第二种方式匹配的结果是："+b2);
		
		//从上面两段代码的打印结果我们知道，他们是等价的。
	}
	/**
	 * 测试Pattern类的flags方法的作用：返回此模式的匹配标志。
	 */
	public static void testPatternMethod2(){
		String regex="<a[^>]*>(.*)</a>";//正则表达式字符串。
		Pattern p = Pattern.compile(regex,Pattern.DOTALL);
		System.out.println("Pattern.DOTALL的匹配标志是："+p.flags());
	}
	
	/**
	 * 测试Pattern类的quote方法的作用:返回指定 String 的字面值模式 String。
	 * 		此方法产生一个 String，可以将其用于创建与字符串 s 匹配的 Pattern，就好像它是字面值模式一样。
	 * 		输入序列中的元字符和转义序列不具有任何特殊意义。
	 */
	public static void testPatternMethod3(){
		//以下是创建一个正则表达式，使之与“*”匹配
		System.out.println("字面值模式:"+Pattern.quote("<[^>]>"));
	}
	
	/**
	 * 测试Pattern类的pattern:返回在其中编译过此模式的正则表达式。
	 * 				toString:返回此模式的字符串表示形式。此为在其中编译过此模式的正则表达式。
	 */
	public static void testPatternMethod4(){
		String regex="<a[^>]*>(.*)</a>";//正则表达式字符串。
		Pattern p = Pattern.compile(regex);
		System.out.println("编译过此模式的正则表达式："+p.pattern());
		System.out.println("模式的字符串表示形式："+p.toString());
	}
	/**
	 * 测试Pattern类的split：围绕此模式的匹配拆分给定输入序列。
	 * 测试String类的 split：根据给定正则表达式的匹配拆分此字符串。
	 * 以下测试我们知道模式中split和字符串中的split是相同的。
	 */
	public static void testPatternMethod5(){
		String regex="a+";
		String input="BBaCCCaaDDDaaaEEEE";
		
		Pattern p = Pattern.compile(regex);
		String str1[]=p.split(input);
		System.out.println("第一次输入的字符串被分割成数组的长度为："+str1.length);
		for (int i = 0; i < str1.length; i++) {
			System.out.println(str1[i]);
		}
		
		/*
		 * split方法中第二参数limit 参数控制应用模式的次数，从而影响结果数组的长度。
		 * 如果限制 n 大于零，那么模式至多应用 n> - 1 次，数组的长度不大于 n，并且数组的最后条目将包含除最后的匹配定界符之外的所有输入。
		 * 如果 n 非正，那么将应用模式的次数不受限制，并且数组可以为任意长度。如果 n 为零，那么应用模式的次数不受限制，数组可以为任意长度，并且将丢弃尾部空字符串。 
		 */
		String str2[]=p.split(input, 2);
		System.out.println("第二次输入的字符串被分割成数组的长度为："+str2.length);
		for (int i = 0; i < str2.length; i++) {
			System.out.println(str2[i]);
		}
		
		String str3[]=input.split(regex);
		System.out.println("第三次输入的字符串被分割成数组的长度为："+str3.length);
		for (int i = 0; i < str1.length; i++) {
			System.out.println(str1[i]);
		}
		String str4[]=input.split(regex,2);
		System.out.println("第四次输入的字符串被分割成数组的长度为："+str4.length);
		for (int i = 0; i < str2.length; i++) {
			System.out.println(str2[i]);
		}
		//从上面我们知道模式中split和字符串中的split是相同的。
	}
	
	/*
	 * 以下是对Matcher类的方法的简介
	 * Matcher appendReplacement(StringBuffer sb,String replacement) 将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
	 *	StringBuffer appendTail(StringBuffer sb) 将最后一次匹配工作后剩余的字符串添加到一个StringBuffer对象里。类包里的解释是：实现非终端追加和替换步骤。
	 *	
	 *	int end() 返回当前匹配的子串的最后一个字符在原目标字符串中的索引位置。
	 *
	 *	int end(int group) 返回与匹配模式里指定的组相匹配的子串最后一个字符的位置。
	 *
	 *	boolean find() 尝试在目标字符串里查找下一个匹配子串。
	 *
	 *	boolean find(int start) 重设Matcher对象，并且尝试在目标字符串里从指定的位置开始查找下一个匹配的子串。
	 *
	 *	String group() 返回当前查找而获得的与组匹配的所有子串内容
	 *
	 *	String group(int group) 返回当前查找而获得的与指定的组匹配的子串内容
	 *
	 *	int groupCount() 返回当前查找所获得的匹配组的数量。
	 *
	 *	boolean lookingAt() 检测目标字符串是否以匹配的子串起始。
	 *
	 *	boolean matches() 尝试对整个目标字符展开匹配检测，也就是只有整个目标字符串完全匹配时才返回真值。
	 *
	 *	Pattern pattern() 返回该Matcher对象的现有匹配模式，也就是对应的Pattern对象。
	 *
	 *	String replaceAll(String replacement) 将目标字符串里与既有模式相匹配的子串全部替换为指定的字符串。
	 *
	 *	String replaceFirst(String replacement) 将目标字符串里第一个与既有模式相匹配的子串替换为指定的字符串。
	 *
	 *	Matcher reset() 重设该Matcher对象。
	 *
	 *	Matcher reset(CharSequence input) 重设该Matcher对象并且指定一个新的目标字符串。
	 *
	 *	int start() 返回当前查找所获子串的开始字符在原目标字符串中的位置。
	 *
	 *	int start(int group) 返回当前查找所获得的和指定组匹配的子串的第一个字符在原目标字符串中的位置。
	 *
	 *  下面用例子来介绍这些方法。
	 */
	
	/**
	 * 测试Matcher类中以下三个方法:三个方法都将返回一个布尔值来表明成功与否
	 * matches()：方法尝试对整个目标字符展开匹配检测，也就是只有整个目标字符串完全匹配时才返回真值。
	 * lookingAt()：方法将检测目标字符串是否以匹配的子串起始。
	 * boolean find()：方法尝试在目标字符串里查找下一个匹配子串。
	 * boolean find(int start): 重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列。
	 * int start():返回以前匹配的初始索引。
	 * int start(int group) : 返回在以前的匹配操作期间，由给定组所捕获的子序列的初始索引。
	 * int end():返回最后匹配字符之后的偏移量。  
	 * int end(int group):返回在以前的匹配操作期间，由给定组所捕获子序列的最后字符之后的偏移量。  
	 */
	public static void testMatcherMethod1(){
		String regex="(B{2})";
		String input="aaBBcccBBBddddBBBBeeeeeBBBBBfffff";
		Pattern p = Pattern.compile(regex);
		Matcher matcher=p.matcher(input);		
		if(matcher.matches()){
			System.out.println("正则表达 匹配 整个输入的字符串！");
		}
		else{
			System.out.println("正则表达 不匹配 整个输入的字符串！");
		}
		if(matcher.find()){
			System.out.println("输入的字符串中 包含 匹配正则表达式的字串！");
		}
		else{
			System.out.println("输入的字符串中 不包含 匹配正则表达式的字串！");
		}
		if(matcher.lookingAt()){
			System.out.println("从输入的字符串中开始匹配正则表达式 成功 ！");
		}
		else{
			System.out.println("从输入的字符串中开始匹配正则表达式 失败 ！");
		}
		System.out.println("-----------------------------------------");
		
		regex=".*(B{2})";
		p = Pattern.compile(regex);
		matcher=p.matcher(input);
		if(matcher.matches()){
			System.out.println("正则表达 匹配 整个输入的字符串！");
		}
		else{
			System.out.println("正则表达 不匹配 整个输入的字符串！");
		}
		if(matcher.find()){
			System.out.println("输入的字符串中 包含 匹配正则表达式的字串！");
		}
		else{
			System.out.println("输入的字符串中 不包含 匹配正则表达式的字串！");
		}
		if(matcher.lookingAt()){
			System.out.println("从输入的字符串中开始匹配正则表达式 成功 ！");
		}
		else{
			System.out.println("从输入的字符串中开始匹配正则表达式 失败 ！");
		}
		System.out.println("-----------------------------------------");
		
		regex=".*(B{2}).*";
		p = Pattern.compile(regex);
		matcher=p.matcher(input);
		if(matcher.matches()){
			System.out.println("正则表达 匹配 整个输入的字符串！");
		}
		else{
			System.out.println("正则表达 不匹配 整个输入的字符串！");
		}
		if(matcher.find()){
			System.out.println("输入的字符串中 包含 匹配正则表达式的字串！");
		}
		else{
			System.out.println("输入的字符串中 不包含 匹配正则表达式的字串！");
		}
		if(matcher.lookingAt()){
			System.out.println("从输入的字符串中开始匹配正则表达式 成功 ！");
		}
		else{
			System.out.println("从输入的字符串中开始匹配正则表达式 失败 ！");
		}
		System.out.println("-----------------------------------------");
		
		//下面专门来测试find方法。
		regex="(B{2})";
		p = Pattern.compile(regex);
		matcher=p.matcher(input);
		
		//find() 尝试查找与该模式匹配的输入序列的下一个子序列。
		System.out.println("需要被匹配的字符串是："+input+"长度是："+input.length()+"\n");
		while(matcher.find()){
			System.out.println("开始匹配位置是："+matcher.start(1));
			System.out.println(matcher.group(1));
			System.out.println("结束匹配位置是："+matcher.end(1)+"\n");
		}
		System.out.println("-----------------------------------------");
		
		//find(int start) 重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列。
		//这里使用的是if 也就是每次调用find都是重start开始匹配，用while则死循环。
		if(matcher.find(4)){
			System.out.println("从第4个位置开始匹配捕获的子字符串是："+matcher.group(1));
		}
		
		//上面那些测试向我们展示那些方法的作用，和他们的区别。
		//matches、lookingAt、find方法是最常用的，我们必须掌握。
	}
	
	/**
	 * Matcher类同时提供了四个将匹配子串替换成指定字符串的方法：replaceFirst()、replaceAll()、appendReplacement()、appendTail() 
	 * String replaceAll(String replacement) 
	 * String replaceFirst(String replacement) 
	 *   
	 * Matcher appendReplacement(StringBuffer sb, String replacement) 
	 * 		将当前匹配子串替换为指定字符串，并且将替换后的子串及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
	 * StringBuffer appendTail(StringBuffer sb) 
	 * 		将最后一次匹配工作后剩余的字符串添加到一个StringBuffer对象里。
	 * 
	 * 		例如，有字符串fatcatfatcatfat,假设既有正则表达式模式为"cat"，第一次匹配后调用appendReplacement(sb,"dog"),
	 * 那么这时StringBuffer sb 的内容为fatdog，也就是fatcat中的cat被替换为dog并且与匹配子串前的内容加到sb里，
	 * 而第二次匹配后调用 appendReplaceme-nt(sb,"dog")，那么sb的内容就变为fatdogfatdog，如果最后再调用一次appendTail（sb）,
	 * 那么sb最终的内容将是fatdogfatdogfat。
	 *  
	 */
	public static void testMatcherMethod2(){
		//生成Pattern对象并且编译一个简单的正则表达式"Kelvin"
	    Pattern p = Pattern.compile("Kelvin");
	    //用Pattern类的matcher()方法生成一个Matcher对象
	    Matcher m = p.matcher("Kelvin Li and Kelvin Chan are both working in Kelvin Chen's KelvinSoftShop company");
	    StringBuffer sb = new StringBuffer();
	    int i=0;
	    //使用find()方法查找第一个匹配的对象
	    boolean result = m.find();
	    //使用循环将句子里所有的kelvin找出并替换再将内容加到sb里
	    while(result) {
	      i++;
	      m.appendReplacement(sb, "Kevin");
	      System.out.println("第"+i+"次匹配后sb的内容是："+sb);
	      //继续查找下一个匹配对象
	      result = m.find();
	    }
	    //最后调用appendTail()方法将最后一次匹配后的剩余字符串加到sb里
	    m.appendTail(sb);
	    System.out.println("调用m.appendTail(sb)后sb的最终内容是:"+ sb);
	}
	
	/**
	 * 测试Matcher类以下三个方法:
	 * String group() 返回由以前匹配操作所匹配的输入子序列
	 * String group(int group) 返回在以前匹配操作期间由给定组捕获的输入子序列。
	 * int groupCount() 返回此匹配器模式中的捕获组数。
	 * 
	 * 捕获组：就是可以用来提取与正则表达式匹配的字符串，提取捕获组的序号是从 1 开始的。下面是官方的解释：
	 * 
	 * 捕获组可以通过从左到右计算其开括号来编号。例如，在表达式 ((A)(B(C))) 中，存在四个这样的组： 
	 * 1     ((A)(B(C))) 
	 * 2     (A) 
	 * 3     (B(C)) 
	 * 4     (C) 
	 * 
	 * 组零始终代表整个表达式。
	 *  
	 * 之所以这样命名捕获组是因为在匹配中，保存了与这些组匹配的输入序列的每个子序列。捕获的子序列稍后可以通过 Back 引用在表达式中使用，也可以在匹配操作完成后从匹配器获取。 
	 * 与组关联的捕获输入始终是与组最近匹配的子序列。如果由于量化的缘故再次计算了组，则在第二次计算失败时将保留其以前捕获的值（如果有的话）例如，将字符串 "aba" 与表达式 (a(b)?)+ 相匹配，会将第二组设置为 "b"。
	 * 在每个匹配的开头，所有捕获的输入都会被丢弃。以 (?) 开头的组是纯的非捕获 组，它不捕获文本，也不针对组合计进行计数。 
	 */
	public static void testMatcherMethod3(){
		String regex="((A)(B(C)))";
		String input="DABCD";
		Pattern p=Pattern.compile(regex);
		Matcher matcher=p.matcher(input);
		
		//注意这里用的是find。如果用matches则group(0)或group()的返回的则是整个输入的字符串。
		if(matcher.find()){
			int count=matcher.groupCount();
			System.out.println("第0个捕获组是："+matcher.group(0));
			for (int i = 0; i < count; i++) {
				System.out.println("第"+(i+1)+"捕获组的值是："+matcher.group(i+1));
			}
			System.out.println("与正则表达式匹配的子字符串是："+matcher.group());
		}
		
		//从上面可以看出group(0)和group()作用是一样的。都是返回与正则表达式匹配的子字符串。
	}
	
	/**
	 * 测试Matcher类以下三个方法:
	 * 
	 * Matcher reset() 重置匹配器。个人感觉没有什么作用。
	 * Matcher reset(CharSequence input) 重置此具有新输入序列的匹配器。
	 *
	 * Matcher usePattern(Pattern newPattern) 更改此 Matcher 用于查找匹配项的 Pattern。
	 * 
	 * Matcher region(int start, int end) 设置此匹配器的区域限制。
	 * int regionEnd() 报告此匹配器区域的结束索引（不包括）。
 	 * int regionStart() 报告此匹配器区域的开始索引。
 	 * 
 	 * static String quoteReplacement(String s) 返回指定 String 的字面值替换 String。
	 */
	public static void testMatcherMethod4(){
		Pattern pattern=Pattern.compile("(A)");
		Matcher matcher=pattern.matcher("AB");
		if(matcher.find()){
			System.out.println("给定的输入字符串中包含了与正则表达式匹配的子字符串："+matcher.group(1));
		}
		//这里测试usePattern(Pattern newPattern)：可以更改匹配器中的匹配模式器。
		matcher.usePattern(Pattern.compile("(B)"));
		if(matcher.find()){
			System.out.println("给定的输入字符串中包含了与正则表达式匹配的子字符串："+matcher.group(1));
		}
		//这里测试reset(CharSequence input)：修改匹配器，让其匹配一个新的字符串。
		matcher=matcher.reset("AC");
		matcher.usePattern(Pattern.compile("(C)"));
		if(matcher.find()){
			System.out.println("给定的输入字符串中包含了与正则表达式匹配的子字符串："+matcher.group(1));
		}
		
		System.out.println("---------------------------------------");
		//以下两部分是用来验证region(int start, int end)、regionStart()、regionEnd()三个方法。
		//设置匹配器的匹配范围，和得到匹配器的开始、结束匹配范围。
		matcher=matcher.reset("EAABAACAAD");
		matcher.usePattern(Pattern.compile("(A{2,}.)"));
		if(matcher.find()){
			System.out.println("给定的输入字符串中包含了与正则表达式匹配的子字符串："+matcher.group(1));
		}
		
		matcher=matcher.reset("EAABAACAAD");
		matcher.usePattern(Pattern.compile("(A{2,}.)"));
		matcher.region(3, 8);//这里设置它的匹配范围，注意要和strar()、end()函数的区分开。
		if(matcher.find()){
			System.out.println("给定的输入字符串中包含了与正则表达式匹配的子字符串："+matcher.group(1));
			System.out.println("开始查找匹配位置："+matcher.regionStart()+"\t\t"+"结束查找匹配位置："+matcher.regionEnd());
		}
		
		System.out.println("---------------------------------------");
		/*
		 * 返回指定 String 的字面值替换 String。 
		 * 此方法将生成一个 String，它将用作 Matcher 类的 appendReplacement 方法中的字面值替换 s。
		 * 所产生的 String 将与作为字面值序列的 s 中的字符序列匹配。斜线 ('\') 和美元符号 ('$') 将不具有任何特殊意义。
		 * 
		 * 这个方法我也不是很清楚。
		 */
		System.out.println("普通字符a的字面值是："+Matcher.quoteReplacement("a"));
		System.out.println("特殊字符*的字面值是："+Matcher.quoteReplacement("*"));
		System.out.println("特殊字符\\的字面值是："+Matcher.quoteReplacement("\\"));
		System.out.println("特殊字符$的字面值是："+Matcher.quoteReplacement("$"));
	}
	
	public static void main(String[] args) {
		System.out.println("----------测试testPatternMethod1运行结果是----------\n");
		testPatternMethod1();
		
		System.out.println("\n----------测试testPatternMethod2运行结果是----------\n");
		testPatternMethod2();
		
		System.out.println("\n----------测试testPatternMethod3运行结果是----------\n");
		testPatternMethod3();
		
		System.out.println("\n----------测试testPatternMethod4运行结果是----------\n");
		testPatternMethod4();
		
		System.out.println("\n----------测试testPatternMethod5运行结果是----------\n");
		testPatternMethod5();
		
		System.out.println("\n----------测试testMatcherMethod1运行结果是----------\n");
		testMatcherMethod1();
		
		System.out.println("\n----------测试testMatcherMethod2运行结果是----------\n");
		testMatcherMethod2();
		
		System.out.println("\n----------测试testMatcherMethod3运行结果是----------\n");
		testMatcherMethod3();
		
		System.out.println("\n----------测试testMatcherMethod4运行结果是----------\n");
		testMatcherMethod4();
	}
}
/*

//以下是程序中各个方法运行的结果：

----------测试testPatternMethod1运行结果是----------

第一种方式匹配的结果是：true
第二种方式匹配的结果是：true

----------测试testPatternMethod2运行结果是----------

Pattern.DOTALL的匹配标志是：32

----------测试testPatternMethod3运行结果是----------

字面值模式:\Q<[^>]>\E

----------测试testPatternMethod4运行结果是----------

编译过此模式的正则表达式：<a[^>]*>(.*)</a>
模式的字符串表示形式：<a[^>]*>(.*)</a>

----------测试testPatternMethod5运行结果是----------

第一次输入的字符串被分割成数组的长度为：4
BB
CCC
DDD
EEEE
第二次输入的字符串被分割成数组的长度为：2
BB
CCCaaDDDaaaEEEE
第三次输入的字符串被分割成数组的长度为：4
BB
CCC
DDD
EEEE
第四次输入的字符串被分割成数组的长度为：2
BB
CCCaaDDDaaaEEEE

----------测试testMatcherMethod1运行结果是----------

正则表达 不匹配 整个输入的字符串！
输入的字符串中 包含 匹配正则表达式的字串！
从输入的字符串中开始匹配正则表达式 失败 ！
-----------------------------------------
正则表达 不匹配 整个输入的字符串！
输入的字符串中 包含 匹配正则表达式的字串！
从输入的字符串中开始匹配正则表达式 成功 ！
-----------------------------------------
正则表达 匹配 整个输入的字符串！
输入的字符串中 不包含 匹配正则表达式的字串！
从输入的字符串中开始匹配正则表达式 成功 ！
-----------------------------------------
需要被匹配的字符串是：aaBBcccBBBddddBBBBeeeeeBBBBBfffff长度是：33

开始匹配位置是：2
BB
结束匹配位置是：4

开始匹配位置是：7
BB
结束匹配位置是：9

开始匹配位置是：14
BB
结束匹配位置是：16

开始匹配位置是：16
BB
结束匹配位置是：18

开始匹配位置是：23
BB
结束匹配位置是：25

开始匹配位置是：25
BB
结束匹配位置是：27

-----------------------------------------
从第4个位置开始匹配捕获的子字符串是：BB

----------测试testMatcherMethod2运行结果是----------

第1次匹配后sb的内容是：Kevin
第2次匹配后sb的内容是：Kevin Li and Kevin
第3次匹配后sb的内容是：Kevin Li and Kevin Chan are both working in Kevin
第4次匹配后sb的内容是：Kevin Li and Kevin Chan are both working in Kevin Chen's Kevin
调用m.appendTail(sb)后sb的最终内容是:Kevin Li and Kevin Chan are both working in Kevin Chen's KevinSoftShop company

----------测试testMatcherMethod3运行结果是----------

第0个捕获组是：ABC
第1捕获组的值是：ABC
第2捕获组的值是：A
第3捕获组的值是：BC
第4捕获组的值是：C
与正则表达式匹配的子字符串是：ABC

----------测试testMatcherMethod4运行结果是----------

给定的输入字符串中包含了与正则表达式匹配的子字符串：A
给定的输入字符串中包含了与正则表达式匹配的子字符串：B
给定的输入字符串中包含了与正则表达式匹配的子字符串：C
---------------------------------------
给定的输入字符串中包含了与正则表达式匹配的子字符串：AAB
给定的输入字符串中包含了与正则表达式匹配的子字符串：AAC
开始查找匹配位置：3		结束查找匹配位置：8
---------------------------------------
普通字符a的字面值是：a
特殊字符*的字面值是：*
特殊字符\的字面值是：\\
特殊字符$的字面值是：\$

*/