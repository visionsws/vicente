package com.fusio.tag.commons.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

// TODO 【这个类待改进，不完美，没有考虑调用接口多久后要超时的处理; 增加获取Response的方法，方便Service获得】参考http://www.tuicool.com/articles/a6ZJfu
/**
 * 用于发起HTTP请求。为了实现AOP改目标方法为静态。
 * 
 * @author Stone
 *
 */
@Component
public class HttpUtil {
	private static final String DEFAULT_COMMUNICATE_CHARSET = "UTF-8";

	/**
	 * 普通键值对的请求参数，一般key是String，value也是String，value也可以是数字等
	 * 
	 * @author Stone
	 *
	 */
	public static class ReqParam {
		private String key;
		private Object value;

		public ReqParam() {
			super();
		}

		public ReqParam(String key, Object value) {
			super();
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return JsonUtil.toJSONString(this);
		}

	}

	/**
	 * 二进制流的请求参数
	 * 
	 * @author Stone
	 *
	 */
	public static class BinaryReqParam {
		private String key;
		private BinaryFileStream value;

		public BinaryReqParam() {
			super();
		}

		public BinaryReqParam(String key, BinaryFileStream value) {
			super();
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public BinaryFileStream getValue() {
			return value;
		}

		public void setValue(BinaryFileStream value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "BinaryReqParam [key=" + key + ", value=" + value + "]";
		}
	}

	/**
	 * 由于要指定二进制流和二进制传送过去生成的文件名，所以这是个键值对的类
	 * 
	 * @author Stone
	 *
	 */
	public static class BinaryFileStream {
		private InputStream inputStream;
		private String filename;

		public BinaryFileStream() {
			super();
		}

		public BinaryFileStream(InputStream inputStream, String filename) {
			super();
			this.inputStream = inputStream;
			this.filename = filename;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		@Override
		public String toString() {
			return "BinaryFileStream [inputStream=文件流太大不打印了" + ", filename=" + filename + "]";
		}

	}

	/**
	 * HTTP请求的返回
	 * 
	 * @author Stone
	 *
	 */
	public static class HttpReturn {
		private int httpCode;
		private String retValue;

		public HttpReturn() {
			super();
		}

		public HttpReturn(int httpCode, String retValue) {
			super();
			this.httpCode = httpCode;
			this.retValue = retValue;
		}

		public int getHttpCode() {
			return httpCode;
		}

		public void setHttpCode(int httpCode) {
			this.httpCode = httpCode;
		}

		public String getRetValue() {
			return retValue;
		}

		public void setRetValue(String retValue) {
			this.retValue = retValue;
		}

		@Override
		public String toString() {
			return JsonUtil.toJSONString(this);
		}
	}

	
	
	/**
	 * 设置header
	 * 
	 * @param httpMethod
	 * @param headers
	 * @author Stone
	 */
	private static void setHeader(Object httpMethod, List<Header> headers) {
		if (headers != null && headers.size() > 0) {
			for (Header header : headers) {
				BasicHeader basicHeader = new BasicHeader(header.getName(), header.getValue());

				if (httpMethod instanceof HttpGet) {
					HttpGet httpGet = (HttpGet) httpMethod;
					httpGet.setHeader(basicHeader);
				} else if (httpMethod instanceof HttpPost) {
					HttpPost httpPost = (HttpPost) httpMethod;
					httpPost.setHeader(basicHeader);
				} else if (httpMethod instanceof HttpPut) {
					HttpPut httpPut = (HttpPut) httpMethod;
					httpPut.setHeader(basicHeader);
				} else if (httpMethod instanceof HttpDelete) {
					HttpDelete httpDelete = (HttpDelete) httpMethod;
					httpDelete.setHeader(basicHeader);
				} else {
					throw new RuntimeException("暂不支持的请求方法: " + httpMethod);
				}
			}
		}
	}
	
	private static HttpReturn getHttpReturn(CloseableHttpResponse response) {
		int httpCode = 0;
		String ret = null;
		try {
			httpCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			ret = EntityUtils.toString(entity, DEFAULT_COMMUNICATE_CHARSET);
			EntityUtils.consume(entity);
			return new HttpReturn(httpCode, ret);
		} catch (Exception e) {
			throw new RuntimeException("接口返回值不能转换成HttpReturn：httpCode:" + httpCode + ",ret:" + ret);
		}
	}

	/**
	 * 可以带二进制流的请求方法(POST)<br>
	 * 
	 * 已解决上传文件名包含中文的乱码问题
	 * 
	 * @param url
	 *            请求的URL，不要带上参数(?param1=value1&......)
	 * @param paramList
	 *            请求的普通参数，没有传null
	 * @param binaryParamList
	 *            请求的二进制流参数，没有传null
	 * @return
	 * @author Stone
	 */
	public static HttpReturn postWithBinaryFileStream(String url, Params params, List<BinaryReqParam> binaryParamList, List<Header> headers) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			setHeader(httpPost, headers);
			
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			// 以浏览器兼容模式运行，防止上传的文件名乱码。以下两行代码都必须同时写上，只有其一依然文件名乱码
			multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			multipartEntityBuilder.setCharset(Consts.UTF_8);
			// 普通参数
			List<ReqParam> paramList = params == null ? null : params.getList();
			if (paramList != null) {
				ContentType contentType = ContentType.create("text/plain", Consts.UTF_8);
				for (ReqParam requestParam : paramList) {
					String key = requestParam.getKey();
					Object value = requestParam.getValue();
					if (value == null) {
						continue;
					}
					multipartEntityBuilder.addTextBody(key, value.toString(), contentType);
				}
			}

			// 二进制流参数
			if (binaryParamList != null) {
				for (BinaryReqParam reqBiinaryParam : binaryParamList) {
					String key = reqBiinaryParam.getKey();
					BinaryFileStream value = reqBiinaryParam.getValue();
					if (value.getInputStream() == null || value.getFilename() == null) {
						continue;
					}
					// ContentType使用DEFAULT_BINARY或MULTIPART_FORM_DATA都可以
					ContentType contentType = ContentType.create("multipart/form-data", Consts.UTF_8);
					multipartEntityBuilder.addBinaryBody(key, value.getInputStream(), contentType, value.getFilename());
				}
			}

			httpPost.setEntity(multipartEntityBuilder.build());
			response = httpClient.execute(httpPost);
			return getHttpReturn(response);
		} catch (Exception e) {
			throw handleEx(e, url);
		} finally {
			closeResource(httpClient, response);
		}
	}

	
	public static HttpReturn postWithBinaryFileStream(String url, Params params, List<BinaryReqParam> binaryParamList) {
		return postWithBinaryFileStream(url, params, binaryParamList, null);
	}
	
	
	/**
	 * GET请求某路径<br>
	 * 已经测试带中文特殊字符的情况
	 * 
	 * @param url
	 *            不能带问号后的参数
	 * @param params
	 *            无参数可以传null
	 * @return
	 */
	public static HttpReturn get(String url, Params params, List<Header> headers) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			url = appendReqParameters(url, params);
			HttpGet httpGet = new HttpGet(url);
			setHeader(httpGet, headers);
			
			response = httpClient.execute(httpGet);
			return getHttpReturn(response);
		} catch (Exception e) {
			throw handleEx(e, url);
		} finally {
			closeResource(httpClient, response);
		}
	}

	/**
	 * GET请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @author Stone
	 */
	public static HttpReturn get(String url, Params params) {
		return get(url, params, null);
	}
	
	/**
	 * POST请求某路径<br>
	 * 已经测试中文参数问题
	 * 
	 * @param url
	 *            不能带问号后的参数
	 * @param params
	 *            无参数可以传null
	 * @return
	 */
	public static HttpReturn post(String url, Params params, List<Header> headers) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			setHeader(httpPost, headers);
			
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			if (params != null) {
				List<ReqParam> paramList = params.getList();
				if (paramList != null && !paramList.isEmpty()) {
					for (ReqParam reqParam : paramList) {
						Object value = reqParam.getValue();
						if (value == null) {
							continue;
						}
						formparams.add(new BasicNameValuePair(reqParam.getKey(), value.toString()));
					}
				}
			}
			UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(formparams, DEFAULT_COMMUNICATE_CHARSET);
			httpPost.setEntity(encodedFormEntity);
			response = httpClient.execute(httpPost);
			return getHttpReturn(response);
		} catch (Exception e) {
			throw handleEx(e, url);
		} finally {
			closeResource(httpClient, response);
		}
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @author Stone
	 */
	public static HttpReturn post(String url, Params params) {
		return post(url, params, null);
	}
	
	/**
	 * PUT请求某路径<br>
	 * 
	 * @param url
	 *            不能带问号后的参数
	 * @param params
	 *            无参数可以传null
	 * @return
	 */
	public static HttpReturn put(String url, Params params, List<Header> headers) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			url = appendReqParameters(url, params);
			HttpPut httpPut = new HttpPut(url);
			setHeader(httpPut, headers);
			
			response = httpClient.execute(httpPut);
			return getHttpReturn(response);
		} catch (Exception e) {
			throw handleEx(e, url);
		} finally {
			closeResource(httpClient, response);
		}
	}

	/**
	 * PUT请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @author Stone
	 */
	public static HttpReturn put(String url, Params params) {
		return put(url, params, null);
	}
	
	/**
	 * DELETE请求
	 * 备注: 请求的参数放在了url里,所以可能会参数超长
	 * @param url
	 *            不能带问号后的参数
	 * @param params
	 *            无参数可以传null
	 * @param headers
	 *            无参数可以传null
	 * @return
	 * @author Stone
	 */
	public static HttpReturn delete(String url, Params params, List<Header> headers) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			url = appendReqParameters(url, params);
			HttpDelete httpDelete = new HttpDelete(url);
			setHeader(httpDelete, headers);
			
			response = httpClient.execute(httpDelete);
			return getHttpReturn(response);
		} catch (Exception e) {
			throw handleEx(e, url);
		} finally {
			closeResource(httpClient, response);
		}
	}
	
	/**
	 * DELETE请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @author Stone
	 */
	public static HttpReturn delete(String url, Params params) {
		return delete(url, params, null);
	}
	
	
	public static final int cache = 10 * 1024;
	
	/**
	 * 下载文件(比较麻烦的是要清楚自己下载的是什么类型的，图片类型随便确定一种扩展名即可，不需要理会原来是什么扩展名)
	 * 
	 * @param url
	 * @param filepath
	 *            文件名，包括路径
	 * @author Stone
	 */
	public static void download(String url, String filepath) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			HttpGet httpget = new HttpGet(url);
			response = httpClient.execute(httpget);
			
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			
			File file = new File(filepath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			fos = new FileOutputStream(file);
			
			// 根据实际运行效果 设置缓冲区大小
			byte[] buffer = new byte[cache];
			int ch = 0;
			while ((ch = is.read(buffer)) != -1) {
				fos.write(buffer, 0, ch);
			}
			fos.flush();
		} catch (Exception e) {
			throw handleEx(e, url);
		} finally {
			closeResource(httpClient, response, fos);
		}
	}
	/**
	 * 组装URL<br>
	 * 
	 * @param url
	 *            原始的请求url,不要带问号后的参数部分
	 * @param params
	 *            键值对的字符串,键值都会进行URL编码,所以key支持有空格的特殊key,value也支持中文. 没有参数可以传null
	 * @return
	 */
	private static String appendReqParameters(String url, Params params) {
		StringBuffer fullUrl;
		try {
			fullUrl = new StringBuffer(url);
			if (params != null) {
				List<ReqParam> paramList = params.getList();
				if (paramList != null && !paramList.isEmpty()) {
					fullUrl.append("?");
					for (ReqParam reqParam : paramList) {
						Object value = reqParam.getValue();
						if (value == null) {
							continue;
						}
						fullUrl.append(URLEncoder.encode(reqParam.getKey(), DEFAULT_COMMUNICATE_CHARSET)).append("=")
								.append(URLEncoder.encode(value.toString(), DEFAULT_COMMUNICATE_CHARSET)).append("&");
					}
					if (fullUrl.length() > 0) {
						fullUrl.deleteCharAt(fullUrl.length() - 1);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("组装URL异常");
		}
		return fullUrl.toString();
	}

	/**
	 * 请求header
	 * 
	 * @author Stone
	 *
	 */
	public static class Header {
		private String name;
		private String value;

		public Header() {
			super();
		}

		public Header(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 简化入参,免得写一堆泛型
	 * 
	 * @author Stone
	 *
	 */
	public static class Params {
		private List<ReqParam> list = new ArrayList<>();

		public static Params create() {
			return new Params();
		}

		public Params add(String key, Object value) {
			list.add(new ReqParam(key, value));
			return this;
		}
		
		/*public Params add2(List<Map<String, Object>> paramList) {
			if (paramList != null) {
				for (Map<String, Object> map : paramList) {
					for (String key : map.keySet()) {
						Object value = map.get(key);
						list.add(new ReqParam(key, value));
					}
				}
			}
			return this;
		}*/
		
		public Params add(List<ReqParam> paramList) {
			if (paramList != null) {
				this.list = paramList;
			}
			return this;
		}

		public List<ReqParam> getList() {
			return list;
		}
	}

	/**
	 * 获取HttpServletRequest
	 * 
	 * @return
	 * @author Stone
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * 获得HttpServletResponse
	 * @return
	 * @author Stone
	 */
	public static HttpServletResponse getResponse() {
		 HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();  
		 return response;
	}
	
	/**
	 * 读取消息body里的内容
	 * 
	 * @return
	 * @author Stone
	 */
	public static String readReqBody() {
		BufferedReader br = null;
		try {
			StringBuilder result = new StringBuilder();
			br = getRequest().getReader();
			for (String line = null; (line = br.readLine()) != null;) {
				result.append(line);
			}

			return result.toString();
		} catch (IOException e) {
			throw new RuntimeException("readReqBody异常:", e);
		} finally {
			closeResource(br);
		}
	}
	
	
	/**
	 * 关闭资源
	 * 
	 * @param httpClient
	 * @param response
	 * @author Stone
	 */
	private static void closeResource(Closeable... closeables) {
		try {
			if (closeables != null) {
				for (Closeable closeable : closeables) {
					// 判断null很重要!!
					if (closeable != null) {
						closeable.close();
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("关闭CloseableHttpClient或CloseableHttpResponse异常");
		}
	}
	
	/**
	 * 处理异常
	 * 
	 * @param e
	 * @param url
	 * @author Stone
	 */
	private static RuntimeException handleEx(Exception e, String url) {
		return new RuntimeException("请求异常", e);
	}
	
	/**
	 * 获取网站home页
	 * @return
	 * @author Stone
	 */
	public static String getWebsiteHome() {
		HttpServletRequest req = getRequest();
		StringBuffer reqUrl = req.getRequestURL();
		// 如http://www.xxx.com/
		String websiteHome = reqUrl.delete(reqUrl.length() - req.getRequestURI().length(), reqUrl.length()).append(req.getContextPath())
				.append("/").toString();
		return websiteHome;
	}
}
