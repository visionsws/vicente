package com.fusio.tag.commons.exception;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public abstract class AbstractValid {
	
	
	 /**
     * 正则表达式：验证邮箱
     */
	private static final Pattern EMAIL_PATTERN = Pattern
			.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
	
	public static final String yrMonDayHrMinSec_ = "yyyy-MM-dd HH:mm:ss";
	public static final SimpleDateFormat yrMonDayHrMinSecSDF_ = new SimpleDateFormat(yrMonDayHrMinSec_);
	public static final String yrMonDay_ = "yyyy-MM-dd";
	public static final SimpleDateFormat yrMonDaySDF_ = new SimpleDateFormat(yrMonDay_);
 
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    
    protected abstract void throwEx(String msg);
    
	/**
	 * value符合min<=value<=max不抛异常，否则抛异常。
	 * 
	 * @param min
	 * @param max
	 * @param value
	 * @author Stone
	 */
	public void rangeBetween(int min, int max, Integer value) {
		if (value == null || !(min <= value && max >= value)) {
			String msg = String.format("参数错误：range范围错误，必须在%s和%s之间，实际值%s", min, max, value);
			throwEx(msg);
		}
	}

	/**
	 * 字符串不在指定的长度范围内，边界值不抛异常<br>
	 * value符合min<=value.length()<=max不抛异常，否则抛异常。
	 * 
	 * @param min
	 * @param max
	 * @param value
	 * @author Stone
	 */
	public void strLengthBetween(int min, int max, String value) {
		int len = value.length();
		if (value == null || !(min <= len && max >= len)) {
			String msg = String.format("参数错误：字符串length范围错误，必须在%s和%s之间，实际长度%s", min, max, value);
			throwEx(msg);
		}
	}

	/**
	 * 字符串不是指定的长度时抛出异常
	 * 
	 * @param len
	 * @param value
	 * @author Stone
	 */
	public void strLengthEqualTo(int len, String value) {
		if (value == null || !(value.length() == len)) {
			String msg = String.format("参数错误：字符串长度不等于%s，实际值%s", len, value);
			throwEx(msg);
		}
	}

	/**
	 * value符合min<=value不抛异常，否则抛异常
	 * 
	 * @param target
	 * @param value
	 * @author Stone
	 */
	public void greaterThan(int target, Number value) {
		if (value == null || !(target <= value.longValue())) {
			String msg = String.format("参数错误：必须大于等于%s，实际值%s", target, value);
			throwEx(msg);
		}
	}
	
	public void greaterThan(int target, Number value, String msg) {
		if (value == null || !(target <= value.longValue())) {
			throwEx(msg);
		}
	}

	/**
	 * value符合max>=value不抛异常，否则抛异常
	 * 
	 * @param target
	 * @param value
	 * @author Stone
	 */
	public void lessThan(int target, Integer value) {
		if (value == null || !(target >= value)) {
			String msg = String.format("参数错误：必须小于等于%s，实际值%s", target, value);
			throwEx(msg);
		}
	}

	/**
	 * value==null时抛异常
	 * 
	 * @param value
	 * @author Stone
	 */
	public void notNull(Object value) {
		if (value == null) {
			String msg = String.format("参数错误：不能是null值，实际值%s", value);
			throwEx(msg);
		}
	}
	
	/**
	 * value==null时抛异常
	 * 
	 * @param value
	 * @author Stone
	 */
	public void notNull(Object value, String paramName) {
		if (value == null) {
			String msg = String.format("参数错误：不能是null值，实际值%s，参数字段名%s", value, paramName);
			throwEx(msg);
		}
	}

	/**
	 * value不能是null，也不能是空串
	 * 
	 * @param value
	 * @author Stone
	 */
	public void notEmpty(String value) {
		if (value == null || value.length() == 0) {
			String msg = String.format("参数错误：不能是null或空串(无trim)，实际值[%s]", value);
			throwEx(msg);
		}
	}

	/**
	 * value在去除前后的空格后，不能是null，也不能是空串
	 * 
	 * @param value
	 * @author Stone
	 */
	public void notBlank(String value) {
		if (value == null || value.trim().length() == 0) {
			String msg = String.format("参数错误：不能是null或空串(有trim)，实际值[%s]", value);
			throwEx(msg);
		}
	}

	/**
	 * value在去除前后的空格后，不能是null，也不能是空串
	 * 
	 * @param value
	 * @author Stone
	 */
	public void notBlank(String value, String paramName) {
		if (value == null || value.trim().length() == 0) {
			String msg = String.format("参数错误：不能是null或空串(有trim)，实际值[%s]，参数字段名%s", value, paramName);
			throwEx(msg);
		}
	}
	
	/**
	 * 非邮箱的时候报错
	 * 
	 * @param value
	 * @author Stone
	 */
	public void assertEmail(String value) {
		Matcher m = EMAIL_PATTERN.matcher(value);
		if (!m.matches()) {
			String msg = String.format("参数错误：非法邮箱，实际值[%s]", value);
			throwEx(msg);
		}
	}
	
 
    /**
     * 校验手机号
     * 
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public void isMobile(String mobile) {
        if(Pattern.matches(REGEX_MOBILE, mobile)){
        	String msg = String.format("参数错误：非法手机号，实际值%s", mobile);
        	throwEx(msg);
        }
    }
 
    /**
     * 校验是数字
     * 
     * @param value
     * @author Stone
     */
    public void isNumber(String value) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher m = pattern.matcher(value);
		if (!m.matches()) {
			String msg = String.format("参数错误：不是数字，实际值%s", value);
			throwEx(msg);
		}
	}
    /**
     * 检查是否是dateTime格式,例如2016-07-01 14:13:44
     * @param dateTimeStr
     * @author Stone
     */
    public void isNotNullAndDateTimeStr(String dateTimeStr) {
        try {
        	yrMonDayHrMinSecSDF_.parse(dateTimeStr);
		} catch (Exception e) {
			String msg = String.format("参数错误：不是yyyy-MM-dd HH:mm:ss格式，实际值%s", dateTimeStr);
			throwEx(msg);
		}
    }
    
    /**
     * 检查是否是日期时间格式,如2016-07-01 14:21:30,如果是null或空串不检查
     * @param dateTimeStr
     * @author Stone
     */
    public void isNullableAndDateTimeStr(String dateTimeStr) {
        try {
        	if (dateTimeStr == null || dateTimeStr.length() == 0) {
				return;
			}
        	yrMonDayHrMinSecSDF_.parse(dateTimeStr);
		} catch (Exception e) {
			String msg = String.format("参数错误：不是yyyy-MM-dd HH:mm:ss格式，实际值%s", dateTimeStr);
			throwEx(msg);
		}
    }
    
    /**
     * 日期传错, 正确如2016-07-01
     * 
     * @param dateStr
     * @author Stone
     */
    public void isNotNullAndDateStr(String dateStr) {
        try {
        	yrMonDaySDF_.parse(dateStr);
		} catch (Exception e) {
			String msg = String.format("参数错误：不是yyyy-MM-dd格式，实际值%s", dateStr);
			throwEx(msg);
		}
    }
    
    /**
     * 检查是否是日期格式,如2016-07-01,如果是null或空串不检查
     * @param dateStr
     * @author Stone
     */
    public void isNullableAndDateStr(String dateStr) {
        try {
        	if (dateStr == null || dateStr.length() == 0) {
				return;
			}
        	yrMonDaySDF_.parse(dateStr);
		} catch (Exception e) {
			String msg = String.format("参数错误：不是yyyy-MM-dd格式，实际值%s", dateStr);
			throwEx(msg);
		}
    }

    
	/**
	 * 检验是否符合正则表达式
	 * 
	 * @param regex
	 * @param value
	 * @author Stone
	 */
	public void matchesPattern(String regex, String value) {
		if (!value.matches(regex)) {
			throw ExceptionUtil.getInvalidServiceParamEx("正则校验不通过");
		}
	}

	public void notEmpty(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			String msg = String.format("参数错误：集合为空，实际值%s", collection);
			throwEx(msg);
		}
	}
	
	
	/**
	 * 抛出异常(这个异常是Service层专用，在方法的入参有问题的时候抛出)，
	 * 与validDataUtil所抛出的InvalidDataException区别是，这个只是方法的入参异常，
	 * InvalidDataException是从库表里查出来的数据是"垃圾数据"、"残缺数据"<br>
	 * 
	 * 考虑到相同的Service接口可能会被不同的Controller调用，万一这个Controller校验了参数，
	 * 但是另外一个Controller没有校验参数，是否会因为传入错误的数据造成破坏?<br>
	 * 
	 * 当然为了避免"无休止"的校验，Service层可以挑选重要的校验下就好了<br>
	 * 
	 * 写错误信息的哲学：不是写得越详细越好，约详细，带上的信息越多，当更改时牵一发动全身;
	 * 错误信息有时候可以提示得"朦胧"一点，在变动的时候不需要改错误信息提示，只要在能花适当时间就能定位到错误即可，为"度"。
	 * 
	 * @param msg
	 * @author Stone
	 */
	public void throwInvalidParamEx(String msg) {
		throwEx(msg);
	}

	/**
	 * 重载
	 * 
	 * @author Stone
	 */
	public void throwInvalidParamEx() {
		throwEx("");
	}
}
