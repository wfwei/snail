package mine.acc.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	private static HttpClient httpclient = new DefaultHttpClient();

	/**
	 * 使用httpclient获取指定url的网页内容
	 * 
	 * @author WangFengwei
	 * @time 2012-8-29
	 */
	public static String fetchPage(String url) throws Exception {
		String html = null;

		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
		httpget.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");

		/* TODO 需要加锁么 */
		HttpResponse response = httpclient.execute(httpget);

		/* 检查http状态 */
		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode != HttpStatus.SC_OK
				&& statusCode != HttpStatus.SC_NOT_FOUND) {
			/* 跳转 */
			if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
					|| statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
				Header header = response.getFirstHeader("Location");
				if (header != null) {
					// 爬取跳转后的网页
					return fetchPage(header.getValue());
				}
			}
			/* TODO 服务器错误可能是暂时的，可以考虑重新提交几次请求 */
			System.err.println("Failed: " + response.getStatusLine().toString()
					+ ", while fetching " + url);
			return null;
		}

		HttpEntity entity = response.getEntity();
		if (entity != null) {
			byte[] bytes = EntityUtils.toByteArray(entity);
			String charSet = "";

			/* 获取头部Content-Type中包含了编码信息 */
			charSet = EntityUtils.getContentCharSet(entity);
			/* 如果头部中没有，那么我们需要 查看页面源码，这个方法虽然不能说完全正确，因为有些粗糙的网页编码者没有在页面中写头部编码信息 */
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
			/* 如果还没能知道编码，默认使用gb2312 TODO 可以使用encoding的解析包 */
			if (charSet == null || charSet.equals("")) {
				charSet = "gb2312";
			}
			html = new String(bytes, charSet);
		}
		// Do not feel like reading the response body
		// Call abort on the request object
		httpget.abort();
		return html;
	}

	/**
	 * When HttpClient instance is no longer needed, shut down the connection
	 * manager to ensure immediate deallocation of all system resources
	 */
	public static void closeHttpClient() {
		try {
			httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
		}
	}

	public static void main(String args[]) {
		try {
			System.out.println(fetchPage("http://www.gddpf.org.cn/"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
