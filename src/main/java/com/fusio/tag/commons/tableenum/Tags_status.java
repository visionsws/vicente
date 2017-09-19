package com.fusio.tag.commons.tableenum;

import com.fusio.tag.commons.exception.ExceptionUtil;

public enum Tags_status {
	// 标签类型,0-正常,1-异常
	NORMAL(0), ERROR(1);

	public short val;

	private Tags_status(int val) {
		this.val = (short) val;
	}

	public static Tags_status get(int val) {
		for (Tags_status s : values())
			if (val == s.val)
				return s;
		throw ExceptionUtil.getInvalidEnumEx();
	}
}
