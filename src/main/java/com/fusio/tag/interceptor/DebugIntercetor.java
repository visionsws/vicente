package com.fusio.tag.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fusio.tag.commons.prop.ConfigProperties;
import com.fusio.tag.commons.utils.HttpUtil;
import com.fusio.tag.commons.utils.IPUtil;
import com.fusio.tag.commons.utils.JsonUtil;

/**
 * 用于debug<br>
 * 
 * @author Stone
 *
 */
public class DebugIntercetor implements HandlerInterceptor {
	private static Logger logger = LoggerFactory.getLogger(HandlerInterceptor.class);
	private ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();
	/**
	 * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
	 * SpringMVC中的Interceptor拦截器是链式的，可以同时存在
	 * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，
	 * 而且所有的Interceptor中的preHandle方法都会在
	 * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，
	 * 这种中断方式是令preHandle的返 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (isOpen()) {
			String url = request.getRequestURL().toString();// http://localhost:8080/new-media/testmodule/test2
			// 不会带上问号后面的东西,过滤掉这些不打印
			if (isExclude(request)) {
				threadLocal.set(false);
				return true;
			}
			threadLocal.set(true);
			String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
			print("");
			print("new-media: ======================= " + time + " =======================>");
			print("request url:\t" + url);
			Map<String, String[]> map = request.getParameterMap();
			print("request params:\t" + JsonUtil.toJSONString(map));
			
			try {
				print("request ip:\t" + IPUtil.getRequestSourceIp(HttpUtil.getRequest()));
			} catch (Exception e) {
			}
//			print("<======================= " + time + " =======================");
//			print("");
		}
		return true;
	}

	private static boolean isOpen() {
		String s = ConfigProperties.get("switch.print_req_param");
		return "1".equals(s);
	}
	
	private static boolean isExclude(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		if (url != null && 
				(//
				url.toLowerCase().endsWith(".js") //
				|| url.toLowerCase().endsWith(".css") //
				|| url.endsWith("userController/allProvinceCity")
				|| url.toLowerCase().endsWith(".jsp")//
				|| url.toLowerCase().endsWith(".html")//
				)//
		) {
			return true;
		}
		return false;
	}

	private void print(String msg) {
		//logger.info(msg);
		System.out.println(msg);
	}

	/**
	 * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，
	 * 它的执行时间是在处理器进行处理之
	 * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，
	 * 也就是说在这个方法中你可以对ModelAndView进行操
	 * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，
	 * 这跟Struts2里面的拦截器的执行过程有点像，
	 * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，
	 * Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
	 * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，
	 * 要在Interceptor之后调用的内容都写在调用invoke方法之后。
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// logger.info("debug postHandle");// Controller方法如果没被执行,则不会打印
	}

	/**
	 * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，
	 * 也就是DispatcherServlet渲染了视图执行，
	 * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO 如果可以在这里截获到返回的json格式参数就好
		if (isOpen() && threadLocal.get()) {
			String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
			print("<======================= " + time + " =======================: new-media");
			print("");
		}
		threadLocal.remove();
	}

}
