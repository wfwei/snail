package mine.acc.net;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * HttpClient的工具类
 * 
 * @author WangFengwei
 * @time 2012-8-29
 */
public class HttpUtil {

	private static HttpClient httpClient = new DefaultHttpClient();

	private static final Logger LOGGER = Logger.getLogger(HttpUtil.class);

	/**
	 * 使用httpclient获取指定url的网页内容
	 * 
	 * @param url
	 */
	public static String fetchPage(String url) throws Exception {
		String html = null;

		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
		httpget.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");

		/* TODO 需要加锁么 */
		HttpResponse response = httpClient.execute(httpget);

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
			LOGGER.error("Failed: " + response.getStatusLine().toString()
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
	 * 模拟登陆网站，须借助工具（HttpWatch或者Chrome自带审查元素工具）查看网站的登陆信息
	 * 
	 * @param loginPostUrl
	 * @param parasStr
	 *            username:myloginname;password:mypassword;
	 * @param encoding
	 */
	public static boolean login(String loginPostUrl, String parasStr,
			String encoding) {
		try {
			HttpPost post = new HttpPost(loginPostUrl);
			// 设置需要提交的参数
			List<NameValuePair> paras = new ArrayList<NameValuePair>();
			String[] para_array = parasStr.split(";");
			for (String pair : para_array) {
				if (pair != null && pair.contains(":")) {
					String[] key_value = pair.split(":");
					paras.add(new BasicNameValuePair(key_value[0].trim(),
							key_value[1].trim()));
				}
			}
			post.setEntity(new UrlEncodedFormEntity(paras, encoding));
			httpClient.execute(post);
			post.abort();
		} catch (Exception e) {
			LOGGER.warn("login error:\t" + e.toString());
			return false;
		}
		LOGGER.warn("Login Success!!!\t");
		return true;
	}

	/**
	 * When HttpClient instance is no longer needed, shut down the connection
	 * manager to ensure immediate deallocation of all system resources
	 */
	public static void closeHttpClient() {
		try {
			httpClient.getConnectionManager().shutdown();
		} catch (Exception e) {
		}
	}

	public static void main(String args[]) {
		try {
			LOGGER.info(fetchPage("http://www.shdisabled.gov.cn/"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
