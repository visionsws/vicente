package com.fusio.tag.commons.exception;

import org.springframework.stereotype.Component;

/**
 * 校验从库表查出来的数据，他跟ValidParamUtil仅仅是抛出的异常不同而已<br>
 * 库表里查出来的数据若是垃圾数据(例如残缺，缺失，违反数据模型的)，例如<br>
 * 
 * 该类用于在Service进行校验参数，在Service层进行使用，当遇到垃圾数据，或者入参有问题抛出这个。
 * 
 * @author Stone
 *
 */
@Component
public class ValidDbDataUtil extends AbstractValid {

	private static final String PREFIX = "数据问题(残缺):";

	@Override
	protected void throwEx(String msg) {
		throw ExceptionUtil.getInvalidDbDataEx(PREFIX + msg);
	}
}
