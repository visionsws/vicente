package com.fusio.tag.commons.exception;

import org.springframework.stereotype.Component;

/**
 * 校验参数，用于补充Controller方法非vo对象参数不能使用jsr303的注解<br>
 * 
 * 该类用于在Controller进行校验参数，一般不要在Service层使用
 * 
 * @author Stone
 *
 */
@Component
public class ValidControllerParamUtil extends AbstractValid {
	@Override
	protected void throwEx(String msg) {
		throw ExceptionUtil.getInvalidControllerParamEx(msg);
	}

}
