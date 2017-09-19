package com.fusio.tag.commons.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 获得异常的工具类，因为自定义的异常不想被随意new，所以构造方法用protected，通过这个ExceptionUtil对外暴露创建异常的方法
 * 
 * @author Stone
 *
 */
public class ExceptionUtil {
	/**
	 * 创建业务异常(Service层才能抛出)
	 * 
	 * @param retCode
	 * @param msg
	 * @param data
	 * @return
	 * @author Stone
	 */
	public static BusinessException getBusiEx(String retCode, String msg, Object data) {
		return new BusinessException(retCode, msg, data);
	}
	

	/**
	 * 创建Controller非法入参异常(Controller层专用)
	 * 
	 * @param msg
	 * @return
	 * @author Stone
	 */
	public static InvalidControllerParamException getInvalidControllerParamEx(String msg) {
		return new InvalidControllerParamException(msg);
	}

	/**
	 * 前端枚举类入参错误(也是Controller入参异常)(Controller层专用)
	 * 
	 * @param msg
	 * @return
	 * @author Stone
	 */
	public static InvalidControllerParamException getInvalidEnumEx(String msg) {
		return getInvalidControllerParamEx(msg);
	}
	
	public static InvalidControllerParamException getInvalidEnumEx() {
		return getInvalidControllerParamEx("枚举错误");
	}

	/**
	 * 创建非法数据异常(从库表查出的"垃圾数据"、"残缺数据")(Service层专用)
	 * 
	 * @param msg
	 * @return
	 * @author Stone
	 */
	public static InvalidDbDataException getInvalidDbDataEx(String msg) {
		return new InvalidDbDataException(msg);
	}

	/**
	 * 创建非法参数入参异常(Service层需要检查方法入参的时候，遇到异常可以抛出)(Service层用)
	 * 
	 * @param msg
	 * @return
	 * @author Stone
	 */
	public static InvalidServiceParamException getInvalidServiceParamEx(String msg) {
		return new InvalidServiceParamException(msg);
	}

	/**
	 * 获得e.printStackTrace的内容
	 * 
	 * @param e
	 * @return
	 * @author Stone
	 */
	public static String getPrintStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw, true));
		String str = sw.toString();
		try {
			sw.close();
		} catch (IOException e1) {
		}
		return str;
	}
}
