package net;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class DownloadPage {
	/**
	 * 根据URL抓取网页内容
	 * 
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public static void getContentFormUrl(String url) throws Exception {
		HttpClient httpclient = new DefaultHttpClient(); // 实例化一个HttpClient

		HttpGet httpget = new HttpGet(url);

		System.out.println("executing request " + httpget.getURI());
		// 查看默认request头部信息
		System.out.println("Accept-Charset:"
				+ httpget.getFirstHeader("Accept-Charset"));
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
		// 用逗号分隔显示可以同时接受多种编码
		httpget.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httpget.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		// 验证头部信息设置生效
		System.out.println("Accept-Charset:"
				+ httpget.getFirstHeader("Accept-Charset").getValue());

		HttpResponse response = httpclient.execute(httpget);// 执行
		HttpEntity entity = response.getEntity(); // 返回服务器响应

		System.out.println("----------------------------------------");
		System.out.println(response.getStatusLine()); // 服务器返回状态
		if (entity != null) {
			Header[] headers = response.getAllHeaders(); // 返回的HTTP头信息
			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i]);
			}

			System.out.println("Response content length: "
					+ entity.getContentLength());
			System.out.println("----------------------------------------");
			System.out.println("Response content: ");
			// String responseString =
			// EntityUtils.toString(response.getEntity());//返回服务器响应的HTML代码
			// responseString = new
			// String(responseString.getBytes("gb2312"),"gbk");//转换中文
			// System.out.println(responseString); //打印出服务器响应的HTML代码

			// 将源码流保存在一个byte数组当中，因为可能需要两次用到该流
			// 注，如果上面的EntityUtils.toString(response.getEntity())执行了后，就不能再用下面的语句拿数据了，直接用上面的数据
			byte[] bytes = EntityUtils.toByteArray(entity);
			String charSet = "";

			// 如果头部Content-Type中包含了编码信息，那么我们可以直接在此处获取
			charSet = EntityUtils.getContentCharSet(entity);

			System.out.println("In header: " + charSet);
			// 如果头部中没有，那么我们需要 查看页面源码，这个方法虽然不能说完全正确，因为有些粗糙的网页编码者没有在页面中写头部编码信息
			if (charSet == null || charSet == "") {
				String regEx = "<meta.*charset=['|\"]?([[a-z]|[A-Z]|[0-9]|-]*)['|\"]?";
				Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(new String(bytes)); // 默认编码转成字符串，因为我们的匹配中无中文，所以串中可能的乱码对我们没有影响
				if (m.find()) {
					charSet = m.group(1);
				} else {
					charSet = "";
				}
			}

			if (charSet == null || charSet.equals("")) {
				charSet = "utf-8";
			}

			System.out.println("Last get: " + charSet);
			// 至此，我们可以将原byte数组按照正常编码专成字符串输出（如果找到了编码的话）
			System.out.println("Encoding string is: "
					+ new String(bytes, charSet));

		}
		System.out.println("----------------------------------------");

		// Do not feel like reading the response body
		// Call abort on the request object
		httpget.abort();

		// When HttpClient instance is no longer needed,
		// shut down the connection manager to ensure
		// immediate deallocation of all system resources
		httpclient.getConnectionManager().shutdown();
	}

	public static void main(String args[]) {
		try {
			getContentFormUrl("http://www.gddpf.org.cn/news/uploads/uploads/content/Js/Js/uploads/uploads/linkImg/20110329130212625.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
